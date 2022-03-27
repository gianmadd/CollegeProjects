package PostgresDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import ConnessioneDB.Connessione;
import DAO.ProfessoreDAO;
import Entita.Corso;
import Entita.Professore;
import Entita.Studente;

public class ProfessorePostgresDAO implements ProfessoreDAO{

	private Connessione connessione;
	private Statement statement;
	private ResultSet resultSet;
	private ArrayList<Corso> listaInsegnamenti = new ArrayList<Corso>();
	
	public ProfessorePostgresDAO() {
		
		connessione = Connessione.getConnessione();
		statement = connessione.getStatement();
		
	}
	
	public ArrayList<Professore> ricavaProfessoriFiltrati(boolean ordine,ArrayList<String> filtroCorsiSelezionati){
		
		ArrayList<Professore> professoriFiltrati = new ArrayList<Professore>();
		
		Professore temp;
		
		String comando = new String();
		
		comando = "SELECT * FROM professore p WHERE ";
		
		for (String string : filtroCorsiSelezionati) {
			comando = comando.concat(" EXISTS (SELECT * FROM lezione l JOIN corso c ON l.del_corso=c.corso_id WHERE c.nome='"+string.toLowerCase()+"' AND l.professore=p.professore_id) AND ");
		}
		
		comando = comando.concat("'true'");
		
		if(ordine) {
			comando = comando.concat(" ORDER BY p.cognome ASC");
		}
		else {
			comando = comando.concat(" ORDER BY p.cognome DESC");
		}
		
		try {
			resultSet = statement.executeQuery(comando);
			
			while(resultSet.next()) {
				temp = new Professore();
				temp.setMatricolaProfessore(resultSet.getInt(1));
				temp.setNome(resultSet.getString(2));
				temp.setCognome(resultSet.getString(3));
				
				professoriFiltrati.add(temp);

			}
		} catch (SQLException e) {
			JOptionPane.showInternalMessageDialog(null, "Si è verificato un errore nel recupero dei professori.", "Errore", JOptionPane.ERROR_MESSAGE);
		}
		
		return professoriFiltrati;
	}
	
	
	public ArrayList<Corso> ricavaInsegnamenti(int idProfessore){
		try {
			Corso temp;
			listaInsegnamenti.clear();
			
			resultSet = statement.executeQuery("SELECT DISTINCT c.nome "
											 + "FROM insegnamenti i JOIN corso c ON i.corso_id = c.corso_id "
											 + "WHERE i.professore_id= '"+ idProfessore +"' "
											 + "ORDER BY c.nome");
			
			while (resultSet.next()) {
				temp = new Corso();
				
				temp.setNome(resultSet.getString(1).toUpperCase());
				listaInsegnamenti.add(temp);
			}
			
		} catch (SQLException e) {
			JOptionPane.showInternalMessageDialog(null, "Si è verificato un errore nel recupero degli insegnamenti.", "Errore", JOptionPane.ERROR_MESSAGE);
		}
		return listaInsegnamenti;
	}
	
	public Professore ricavaProfessoreDaLezione(String titoloLezione) {
		
		Professore temp = null;
		
		try {
			
			resultSet = statement.executeQuery("SELECT p.professore_id, p.nome, p.cognome "
											 + "FROM professore p JOIN lezione l ON p.professore_id = l.professore "
											 + "WHERE l.titolo = '" + titoloLezione.toLowerCase() + "'");
			while (resultSet.next()) {
				temp = new Professore();
				temp.setMatricolaProfessore(resultSet.getInt(1));
				temp.setNome(resultSet.getString(2).toUpperCase());
				temp.setCognome(resultSet.getString(3).toUpperCase());
			}
		} catch (SQLException e) {
			JOptionPane.showInternalMessageDialog(null, "Si è verificato un errore nel recupero dei professori.", "Errore", JOptionPane.ERROR_MESSAGE);
		}
	
	return temp;
	}
	
	public void queryInsertProfessore(String nome, String cognome) {
		try {
			statement.executeUpdate("INSERT INTO professore VALUES (nextval('sequenza_professore_id'), '"+ nome.toLowerCase()+"', '" + cognome.toLowerCase() + "')");
		} catch (SQLException e) {
			JOptionPane.showInternalMessageDialog(null, "Si è verificato un errore nell'inserimento dei professori.", "Errore", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void eliminaProfessore(int idProfessore) {
		try {
			statement.executeUpdate("DELETE FROM professore WHERE(professore_id = '" + idProfessore +"')");
		} catch (SQLException e) {
			JOptionPane.showInternalMessageDialog(null, "Si è verificato un errore nella modifica dei professori.", "Errore", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	
	public Professore ricavaProfessoreSelezionato(int idProfessore) {
		
		Professore temp = new Professore();
		
		try {
			resultSet = statement.executeQuery("SELECT * FROM professore WHERE professore_id='"+idProfessore+"'");
			
			while (resultSet.next()) {
				temp = new Professore();
				temp.setMatricolaProfessore(resultSet.getInt(1));
				temp.setNome(resultSet.getString(2));
				temp.setCognome(resultSet.getString(3));
				
				
				
			}
		} catch (SQLException e) {
			JOptionPane.showInternalMessageDialog(null, "Si è verificato un errore nel recupero dei professori.", "Errore", JOptionPane.ERROR_MESSAGE);
		}
		
		return temp;
	}
	
}
