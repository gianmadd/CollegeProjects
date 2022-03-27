package PostgresDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import ConnessioneDB.Connessione;
import DAO.IscrizioneDAO;
import Entita.Corso;

public class IscrizionePostgresDAO implements IscrizioneDAO {

	private Connessione connessione;
	private Statement statement;
	private ResultSet resultSet;
	private ArrayList<String> listaIscrizioni = new ArrayList<String>();
	private ArrayList<Corso> listaIscrizioniStudente = new ArrayList<Corso>();

	
	public IscrizionePostgresDAO() {
		
		connessione = Connessione.getConnessione();
		statement = connessione.getStatement();
		
	}
	
	
	public ArrayList<String> getIscrizioni(String nomeCorso) {
		try {
			listaIscrizioni.clear();
			resultSet = statement.executeQuery("SELECT s.cognome, s.nome, i.superato "
					+ "FROM (iscrizione i JOIN studente s ON i.studente_iscritto=s.studente_id), corso c "
					+ "WHERE c.nome='"+nomeCorso.toLowerCase()+"' AND i.corso_scelto=c.corso_id " 
					+ "ORDER BY i.superato DESC, s.cognome");
			
			
			while(resultSet.next()) {
				
				if(resultSet.getBoolean(3)) {
					listaIscrizioni.add(resultSet.getString(1)+" "+resultSet.getString(2)+" : Idoneo");
				}
				else {
					listaIscrizioni.add(resultSet.getString(1)+" "+resultSet.getString(2)+" : Non Idoneo");
				}
			}	
		} catch (SQLException e) {
			JOptionPane.showInternalMessageDialog(null, "Si è verificato un errore nel recupero delle lezioni.", "Errore", JOptionPane.ERROR_MESSAGE);
		}
		return listaIscrizioni;	
	}
	
	
	public ArrayList<Corso> getIscrizioniStudente(int idStudente){
		try {
			Corso temp;
			listaIscrizioniStudente.clear();
			resultSet = statement.executeQuery("SELECT c.corso_id , c.nome, c.descrizione, c.max_partecipanti, c.percentuale_minima_presenze, c.terminato "
											+  "FROM iscrizione i JOIN corso c ON i.corso_scelto = c.corso_id "
											+  "WHERE i.studente_iscritto ='"+ idStudente + "' " 
											+  "ORDER BY i.superato DESC, c.nome");
			
			
			while(resultSet.next()) {
				temp = new Corso();
				
				temp.setNome(resultSet.getString(2).toUpperCase());
				temp.setDescrizione(resultSet.getString(3));
				temp.setMaxPartecipanti(resultSet.getInt(4));
				temp.setPercentualeMinimaPresenza(resultSet.getInt(5));
				temp.setTerminato(resultSet.getBoolean(6));

				listaIscrizioniStudente.add(temp);
			}	
		} catch (SQLException e) {
			JOptionPane.showInternalMessageDialog(null, "Si è verificato un errore nel recupero delle lezioni.", "Errore", JOptionPane.ERROR_MESSAGE);
		}
		return listaIscrizioniStudente;	
	}
	
	public void queryInsertIscrizioni(String nomeCorso, ArrayList<String> studenti) {
		
		int idCorso = 0;
		ArrayList<Integer> idStudenti = new ArrayList<>();
		
		idStudenti.clear();
		
		try {
			resultSet = statement.executeQuery("SELECT corso_id FROM corso WHERE nome= '"+nomeCorso.toLowerCase()+"'");
			
			while (resultSet.next()) {
				idCorso = resultSet.getInt(1);	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		for (String s : studenti) {
			idStudenti.add(Integer.valueOf(s.substring(s.indexOf('-')+2)));
		}

		
		try {
			for (Integer integer : idStudenti) {
				statement.executeUpdate("INSERT INTO iscrizione VALUES('false' , '"+integer.intValue()+"', '"+idCorso+"');");
			}
		} catch (SQLException e) {
			if(e.getSQLState().equals("12301")) {
				JOptionPane.showInternalMessageDialog(null, "Errore durante l'iscrizione:\n Corso terminato.", "Errore", JOptionPane.ERROR_MESSAGE);
			}
			else {
				JOptionPane.showInternalMessageDialog(null, "Errore durante l'iscrizione.", "Errore", JOptionPane.ERROR_MESSAGE);
			}
			
		}
	}
	
}
