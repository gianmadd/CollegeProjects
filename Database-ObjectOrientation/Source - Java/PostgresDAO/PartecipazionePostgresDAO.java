package PostgresDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import ConnessioneDB.Connessione;
import DAO.PartecipazioneDAO;
import Entita.Corso;
import Entita.Lezione;

public class PartecipazionePostgresDAO implements PartecipazioneDAO{

	private Connessione connessione;
	private Statement statement;
	private ResultSet resultSet;
	private String[][] matriceStatistiche = new String[3][2];
	private ArrayList<Lezione> listaPartecipazioniStudente = new ArrayList<Lezione>(); 
	
	
	public PartecipazionePostgresDAO() {
		connessione = Connessione.getConnessione();
		statement = connessione.getStatement();
	}
	
	public ArrayList<Lezione> getPartecipazioniStudente(int idStudente){
		try {
			Lezione temp;
			listaPartecipazioniStudente.clear();
			
			resultSet = statement.executeQuery("SELECT l.lezione_id, l.titolo, l.descrizione, l.data, l.ora_inizio, l.ora_fine, l.professore, l.del_corso "
											+  "FROM partecipazione p JOIN lezione l ON p.lezione_frequentata = l.lezione_id "
											+  "WHERE p.studente_partecipante = '" + idStudente + "' "
											+  "ORDER BY l.data DESC");
			
			
			while(resultSet.next()) {
				temp = new Lezione();
				
				temp.setTitolo(resultSet.getString(2).toUpperCase());
				temp.setDescrizione(resultSet.getString(3));
				temp.setData(resultSet.getDate(4));
				temp.setOraInizio(resultSet.getTime(5));
				temp.setOraFine(resultSet.getTime(6));

				listaPartecipazioniStudente.add(temp);
			}	
		} catch (SQLException e) {
			//e.printStackTrace();
			JOptionPane.showInternalMessageDialog(null, "Si è verificato un errore nel recupero delle partecipazioni per la lezione.", "Errore", JOptionPane.ERROR_MESSAGE);
		}
		return listaPartecipazioniStudente;	
		
	}
	
	
	
	
	public String[][] getStatisticheMinMaxPresenze(String nomeCorso){
		
		matriceStatistiche = initMatrice(matriceStatistiche);
		
		Integer temp = null;		
		try {
			
			//Minimo di presenze
			resultSet = statement.executeQuery("SELECT calcolo_statistiche_min_presenze('" + nomeCorso.toLowerCase() + "')");
			
			while(resultSet.next()) {
				temp = Integer.valueOf(resultSet.getInt(1));
				matriceStatistiche[0][1] = "  " + temp.toString();
			}

			resultSet = statement.executeQuery("SELECT p.titolo_lezione "
											+  "FROM presenze p JOIN corso c ON p.corso_id = c.corso_id "
											 + "WHERE c.nome = '" + nomeCorso.toLowerCase() + "' AND "
											 		+ "p.conteggio = '" + matriceStatistiche[0][1] + "'");
			
			while(resultSet.next()) {
				matriceStatistiche[0][0] = "  " + resultSet.getString(1);
			}
			
			
			//Massimo di presenze
			resultSet = statement.executeQuery("SELECT calcolo_statistiche_max_presenze('" + nomeCorso.toLowerCase() + "')");
			
			while(resultSet.next()) {
				temp = Integer.valueOf(resultSet.getInt(1));
				matriceStatistiche[1][1] = "  " + temp.toString();
			}
			
			resultSet = statement.executeQuery("SELECT p.titolo_lezione "
					+  "FROM presenze p JOIN corso c ON p.corso_id = c.corso_id "
					 + "WHERE c.nome = '" + nomeCorso.toLowerCase() + "' AND "
					 		+ "p.conteggio = '" + matriceStatistiche[1][1] + "'");
			
			while(resultSet.next()) {
				matriceStatistiche[1][0] = "  " + resultSet.getString(1);
			}
			
		

		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showInternalMessageDialog(null, "Si è verificato un errore nel recupero delle statistiche del corso.", "Errore", JOptionPane.ERROR_MESSAGE);
		} 

		return matriceStatistiche;
	}
	
	
	public int calcolaPercentualeRiempimentoMedia(String nomeCorso) {
		
		int percentuale = 0;
		
		try {
			
			resultSet = statement.executeQuery("SELECT calcolo_percentuale_riempimento_media('" + nomeCorso.toLowerCase() + "')");
			while(resultSet.next()) {
				percentuale = resultSet.getInt(1);
			}
			
		} catch (SQLException e) {
			JOptionPane.showInternalMessageDialog(null, "Si è verificato un errore nel recupero delle statistiche del corso.", "Errore", JOptionPane.ERROR_MESSAGE);
		}
		
		return percentuale;		
	}
	
	
	public int getNumeroPresenzeMedio(String nomeCorso) {
		int media = 0;
		try {
			
			resultSet = statement.executeQuery("SELECT calcolo_statistiche_media_presenze('" + nomeCorso.toLowerCase() + "')");
			
			while(resultSet.next()) {
				media = resultSet.getInt(1);
			}
			
		} catch (SQLException e) {
			JOptionPane.showInternalMessageDialog(null, "Si è verificato un errore nel recupero delle statistiche del corso.", "Errore", JOptionPane.ERROR_MESSAGE);
		}
		return media;
	}
	
	
	public String[][] initMatrice(String[][] matrix){
		matrix[0][0] = " ";
		matrix[0][1] = " ";
		matrix[1][0] = " ";
		matrix[1][1] = " ";
		matrix[2][0] = " ";
		matrix[2][1] = " ";

		return matrix;
	}
	
	
	
	public ArrayList<String> ricavaPresenze(String titoloLezione){
		ArrayList<String> presenzeLezioni = new ArrayList<String>();
		
		try {
			resultSet = statement.executeQuery("SELECT s.cognome, s.nome"
										   	+ " FROM studente s JOIN iscrizione i ON s.studente_id = i.studente_iscritto "
										   	+ "		 JOIN lezione l ON i.corso_scelto = l.del_corso "
										   	+ " WHERE l.titolo = '" + titoloLezione.toLowerCase() +"' AND "
										   			+ "s.studente_id IN (SELECT p.studente_partecipante "
										   			+ "					 FROM partecipazione p JOIN lezione l1 ON l.lezione_id=p.lezione_frequentata "
										   			+ "					 WHERE l1.titolo = '" + titoloLezione.toLowerCase() + "')"
										   	+ " ORDER BY s.cognome");
			
			while(resultSet.next()) {
				presenzeLezioni.add(resultSet.getString(1).toUpperCase() + " " +resultSet.getString(2).toUpperCase() + ": PRESENTE");
			}
		} catch (SQLException e) {
			JOptionPane.showInternalMessageDialog(null, "Si è verificato un errore nel recupero delle presenze.", "Errore", JOptionPane.ERROR_MESSAGE);
		}
		
		try {
			resultSet.close();
			resultSet = statement.executeQuery("SELECT s.cognome, s.nome"
				   	+ " FROM studente s JOIN iscrizione i ON s.studente_id = i.studente_iscritto "
				   	+ "		 JOIN lezione l ON i.corso_scelto = l.del_corso "
				   	+ " WHERE l.titolo = '" + titoloLezione.toLowerCase() +"' AND "
				   			+ "s.studente_id NOT IN (SELECT p.studente_partecipante "
				   			+ "					 FROM partecipazione p JOIN lezione l1 ON l.lezione_id=p.lezione_frequentata "
				   			+ "					 WHERE l1.titolo = '" + titoloLezione.toLowerCase() + "')"
				   	+ " ORDER BY s.cognome");

			while(resultSet.next()) {
				presenzeLezioni.add(resultSet.getString(1).toUpperCase() + " " +resultSet.getString(2).toUpperCase() + ": ASSENTE");
			}
		} catch (SQLException e) {
			JOptionPane.showInternalMessageDialog(null, "Si è verificato un errore nel recupero delle presenze.", "Errore", JOptionPane.ERROR_MESSAGE);
		}		
	
		return presenzeLezioni;
	
	}
	
	public void queryInsertPartecipazione(Lezione lezione,ArrayList<String> studentiNonPartecipantiSelezionati) {
		
		int idLezione = 0;
		ArrayList<Integer> idStudenti = new ArrayList<Integer>();
		idStudenti.clear();
		
		try {
			resultSet = statement.executeQuery("SELECT lezione_id FROM lezione WHERE titolo= '"+lezione.getTitolo().toLowerCase()+"'");
			
			while (resultSet.next()) {
				idLezione = resultSet.getInt(1);	
			}
		} catch (SQLException e) {
			JOptionPane.showInternalMessageDialog(null, "Si è verificato un errore nell'inserimento delle partecipazioni.", "Errore", JOptionPane.ERROR_MESSAGE);
		}
		
		
		for (String s : studentiNonPartecipantiSelezionati) {
			idStudenti.add(Integer.valueOf(s.substring(s.indexOf('-')+2)));
		}
		
		
		try {
			for (Integer integer : idStudenti) {
				statement.executeUpdate("INSERT INTO partecipazione VALUES('"+idLezione+"', '"+integer.intValue()+"');");

			}
		} catch (SQLException e) {
			
			if(e.getSQLState().equals("12302"))
				JOptionPane.showInternalMessageDialog(null, "Uno studente non può partecipare\n ad una lezione di un corso terminato.", "Errore", JOptionPane.ERROR_MESSAGE);
			else if(e.getSQLState().equals("12303"))
				JOptionPane.showInternalMessageDialog(null, "Uno studente non può partecipare ad una lezione\n di un corso a cui non è iscritto.", "Errore", JOptionPane.ERROR_MESSAGE);
			else if(e.getSQLState().equals("12307"))
				JOptionPane.showInternalMessageDialog(null, "Uno studente non può partecipare a due lezioni\n contemporaneamente.", "Errore", JOptionPane.ERROR_MESSAGE);
			else
				JOptionPane.showInternalMessageDialog(null, "Errore nell'iscrizione alla lezione.", "Errore", JOptionPane.ERROR_MESSAGE);

		}
	}
}
