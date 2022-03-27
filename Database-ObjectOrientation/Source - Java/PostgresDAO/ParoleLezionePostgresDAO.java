package PostgresDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import ConnessioneDB.Connessione;
import DAO.ParoleLezioneDAO;
import Entita.Lezione;
import Entita.ParoleChiave;
import Entita.ParoleLezione;
import Entita.Studente;

public class ParoleLezionePostgresDAO implements ParoleLezioneDAO{

	private Connessione connessione;
	private Statement statement;
	private ResultSet resultSet;
	
	public ParoleLezionePostgresDAO() {
		
		connessione = Connessione.getConnessione();		
		statement = connessione.getStatement();
		
	}
	
	public ArrayList<ParoleLezione> ricavaParoleChiaveLezione(Lezione lezione){
		
		ArrayList<ParoleLezione> paroleChiaveLezioneList = new ArrayList<ParoleLezione>();
		ParoleChiave temp;
		
		try {
			resultSet = statement.executeQuery("SELECT pl.parola "
											 + "FROM parole_lezione pl JOIN lezione l ON pl.lezione = l.lezione_id "
											 + "WHERE l.titolo = '" + lezione.getTitolo().toLowerCase() + "' "
											 + "ORDER BY pl.parola");
			
			while(resultSet.next()) {
				temp = new ParoleChiave();
				temp.setParola(resultSet.getString(1).toUpperCase());
				
				paroleChiaveLezioneList.add(new ParoleLezione(temp, lezione));	
			}
			
		} catch (SQLException e) {
			JOptionPane.showInternalMessageDialog(null, "Si è verificato un errore nel recupero delle parole chiave per la lezione.", "Errore", JOptionPane.ERROR_MESSAGE);
		}
		
		return paroleChiaveLezioneList;
	}
	
	
	public void queryInsertParoleLezione(Lezione lezione,ArrayList<String> paroleChiave) {
		
		ArrayList<ParoleLezione> paroleLezione = new ArrayList<ParoleLezione>();
		ParoleChiave temp;
		int idLezione = 0;
		
		for(String string : paroleChiave) {
			temp = new ParoleChiave();
			
			temp.setParola(string);
			
			paroleLezione.add(new ParoleLezione(temp, lezione));
		}
		
		
		try {
			resultSet = statement.executeQuery("SELECT lezione_id FROM lezione WHERE titolo='"+lezione.getTitolo().toLowerCase()+"'");
			
			while(resultSet.next()) {
				idLezione = resultSet.getInt(1);
			}

			for(ParoleLezione p : paroleLezione) {
				statement.executeUpdate("INSERT INTO parole_lezione VALUES ('"+p.getParoleChiave().getParola().toLowerCase()+"', '"+idLezione+"');");
			}
			
		} catch (SQLException e) {
			JOptionPane.showInternalMessageDialog(null, "Si è verificato un errore nell'inserimento delle parole chiave per la lezione.", "Errore", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
}
	
	

	
	
	
	
	
	
	
	

