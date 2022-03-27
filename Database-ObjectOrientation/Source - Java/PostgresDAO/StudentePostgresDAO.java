package PostgresDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;

import ConnessioneDB.Connessione;
import DAO.StudenteDAO;
import Entita.Corso;
import Entita.Lezione;
import Entita.Studente;

public class StudentePostgresDAO implements StudenteDAO{
	
	private Connessione connessione;
	private Statement statement;
	private ResultSet resultSet;
	private ArrayList<Studente> listaStudentiNonIscritti = new ArrayList<Studente>();
	private ArrayList<Studente> listaStudentiNonPartecipanti = new ArrayList<Studente>();

	
	public StudentePostgresDAO() {

		connessione = Connessione.getConnessione();		
		statement = connessione.getStatement();
		
	}

	public void queryInsertStudente(Studente studente) {
		try {
			statement.executeUpdate("INSERT INTO studente VALUES (nextval('sequenza_studente_id'), '"
									+ studente.getNome().toLowerCase() + "', '" + studente.getCognome().toLowerCase() 
									+ "', '" + studente.getDataDiNascita() + "', '" + studente.getEmail().toLowerCase() + "', '" 
									+ studente.getTelefono().toLowerCase() + "');");
			
		} catch (SQLException e) {
			JOptionPane.showInternalMessageDialog(null, "Si è verificato un errore nell'inserimento dello studente.", "Errore", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void eliminaStudente(int idStudente) {
		try {
			statement.executeUpdate("DELETE FROM studente WHERE(studente_id = '" + idStudente +"')");
		} catch (SQLException e) {
			JOptionPane.showInternalMessageDialog(null, "Si è verificato un errore nell'eliminazione dello studente.", "Errore", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	public ArrayList<Studente> queryStudentiNonIscritti(String nomeCorso){
		
		Studente temp;
		listaStudentiNonIscritti.clear();
		
		try {
			resultSet = statement.executeQuery("SELECT studente_id, nome, cognome "
											 + "FROM studente "
					                         + "WHERE studente_id NOT IN (SELECT studente_iscritto FROM iscrizione i JOIN corso c ON c.corso_id=i.corso_scelto "
					                                                    + "WHERE c.nome='"+nomeCorso.toLowerCase()+"') "
					                         + "ORDER BY cognome");
			
			while(resultSet.next()) {
				temp = new Studente();
				
				temp.setMatricola(resultSet.getInt(1));
				temp.setNome(resultSet.getString(2));
				temp.setCognome(resultSet.getString(3));
				
				listaStudentiNonIscritti.add(temp);

			}
			
			
		} catch (SQLException e) {
			JOptionPane.showInternalMessageDialog(null, "Si è verificato un errore nel recupero degli studenti.", "Errore", JOptionPane.ERROR_MESSAGE);
		}
		
		return listaStudentiNonIscritti;
		
		
	}
	
	
	public ArrayList<Studente> queryStudentiNonPartecipanti(Lezione lezione){
		
		Studente temp;
		listaStudentiNonPartecipanti.clear();
		
		try {
			resultSet = statement.executeQuery("SELECT studente_id, nome, cognome "
											 + "FROM studente "
					                         + "WHERE studente_id NOT IN (SELECT studente_partecipante FROM partecipazione p JOIN lezione l ON p.lezione_frequentata=l.lezione_id "
					                                                    + "WHERE l.titolo='"+lezione.getTitolo().toLowerCase()+"') "
					                               + "AND studente_id IN (SELECT studente_iscritto FROM iscrizione i JOIN corso c ON i.corso_scelto=c.corso_id "
					                               												+ "JOIN lezione l ON c.corso_id=l.del_corso "
					                               											    + "WHERE l.titolo='"+lezione.getTitolo().toLowerCase()+"') "
					                         + "ORDER BY cognome");
												
			while(resultSet.next()) {
				temp = new Studente();
				
				temp.setMatricola(resultSet.getInt(1));
				temp.setNome(resultSet.getString(2));
				temp.setCognome(resultSet.getString(3));
				
				listaStudentiNonPartecipanti.add(temp);

			}
			
			
		} catch (SQLException e) {
			JOptionPane.showInternalMessageDialog(null, "Si è verificato un errore nel recupero degli studenti.", "Errore", JOptionPane.ERROR_MESSAGE);
		}
		
		return listaStudentiNonPartecipanti;
		
		
	}
	
	
	public Studente ricavaStudenteSelezionato(int idStudente) {
		
		Studente tempStudente = new Studente();
		
		try {
			resultSet = statement.executeQuery("SELECT * FROM studente WHERE studente_id='"+idStudente+"'");
			
			while (resultSet.next()) {
				tempStudente = new Studente();
				tempStudente.setMatricola(resultSet.getInt(1));
				tempStudente.setNome(resultSet.getString(2));
				tempStudente.setCognome(resultSet.getString(3));
				tempStudente.setDataDiNascita(resultSet.getDate(4));
				tempStudente.setEmail(resultSet.getString(5));
				tempStudente.setTelefono(resultSet.getString(6));
			}
			
		} catch (SQLException e) {
			JOptionPane.showInternalMessageDialog(null, "Si è verificato un errore nel recupero degli studenti.", "Errore", JOptionPane.ERROR_MESSAGE);
		}
		
		return tempStudente;
	}
	
	
	public ArrayList<Studente> ricavaStudentiFiltrati(boolean ordine,ArrayList<String> filtroCorsiSelezionati){
		
		ArrayList<Studente> studentiFiltrati = new ArrayList<Studente>();
		
		Studente tempStudente;
		
		String comando = new String();
		
		
		comando = "SELECT * FROM studente s WHERE ";
		
		for(String string : filtroCorsiSelezionati) {
			comando = comando.concat(" EXISTS (SELECT * FROM iscrizione i JOIN corso c ON i.corso_scelto=c.corso_id WHERE c.nome='"+string.toLowerCase()+"' AND i.studente_iscritto=s.studente_id) AND ");
		}
		
		comando = comando.concat("'true'");
		
		if(ordine) {
			comando = comando.concat(" ORDER BY s.cognome ASC");
		}
		else {
			comando = comando.concat(" ORDER BY s.cognome DESC");
		}

		
		try {
			resultSet = statement.executeQuery(comando);
			
			while(resultSet.next()) {
				tempStudente = new Studente();
				tempStudente.setMatricola(resultSet.getInt(1));
				tempStudente.setNome(resultSet.getString(2));
				tempStudente.setCognome(resultSet.getString(3));
				tempStudente.setDataDiNascita(resultSet.getDate(4));
				tempStudente.setEmail(resultSet.getString(5));
				tempStudente.setTelefono(resultSet.getString(6));
				
				studentiFiltrati.add(tempStudente);
			}
		} catch (SQLException e) {
			JOptionPane.showInternalMessageDialog(null, "Si è verificato un errore nel recupero degli studenti.", "Errore", JOptionPane.ERROR_MESSAGE);
		}
	
		return studentiFiltrati;
	}
	
}
