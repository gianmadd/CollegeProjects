package PostgresDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import ConnessioneDB.Connessione;
import DAO.ParoleChiaveDAO;
import Entita.Corso;
import Entita.Lezione;
import Entita.ParoleChiave;
import Entita.ParoleLezione;
import Entita.Professore;

public class ParoleChiavePostgresDAO implements ParoleChiaveDAO{

	private Connessione connessione;
	private Statement statement;
	private ResultSet resultSet;
	
	public ParoleChiavePostgresDAO(){
		
		connessione = Connessione.getConnessione();		
		statement = connessione.getStatement();
		
	}
	
	
	public ArrayList<ParoleChiave> ricavaParoleChiaveFiltrate(boolean ordine){
		
		ArrayList<ParoleChiave> paroleChiaveFiltrate = new ArrayList<ParoleChiave>();
		
		ParoleChiave temp;
		
		String comando = new String();
		
		comando = "SELECT * FROM parole_chiave ";
		
		if(ordine) {
			comando = comando.concat("ORDER BY parola ASC");
		}
		else {
			comando = comando.concat("ORDER BY parola DESC");
		}
		
		
		try {
			resultSet = statement.executeQuery(comando);
			
			while (resultSet.next()) {
				temp = new ParoleChiave();
				temp.setParola(resultSet.getString(1));
				
				paroleChiaveFiltrate.add(temp);
			}
		} catch (SQLException e) {
			JOptionPane.showInternalMessageDialog(null, "Si è verificato un errore nel recupero delle parole chiave.", "Errore", JOptionPane.ERROR_MESSAGE);
		}
		
		return paroleChiaveFiltrate;
	}
	
	
	public ArrayList<ParoleChiave> ricavaParoleChiaveNonAssociate(Lezione lezione){
		
		ArrayList<ParoleChiave> paroleChiaveNonAssociateList = new ArrayList<ParoleChiave>();
		ParoleChiave temp;
		
		try {
			resultSet = statement.executeQuery("SELECT parola FROM parole_chiave WHERE parola NOT IN (SELECT pl.parola "
																					+         "FROM parole_lezione pl JOIN lezione l ON pl.lezione=l.lezione_id "
																					+         "WHERE l.titolo='"+lezione.getTitolo().toLowerCase()+"');");
			
			while(resultSet.next()) {
				temp = new ParoleChiave();
				temp.setParola(resultSet.getString(1).toUpperCase());
				
				paroleChiaveNonAssociateList.add(temp);
			}
		} catch (SQLException e) {
			JOptionPane.showInternalMessageDialog(null, "Si è verificato un errore nel recupero delle parole chiave.", "Errore", JOptionPane.ERROR_MESSAGE);
		}
		
		return paroleChiaveNonAssociateList;
	}
	
	
	public ParoleChiave ricavaParolaChiaveSelezionata(String parolaSelezionata) {
		
		ParoleChiave temp = new ParoleChiave();
		
		try {
			resultSet = statement.executeQuery("SELECT * FROM parole_chiave WHERE parola='"+parolaSelezionata.toLowerCase()+"'");
			
			while (resultSet.next()) {
				temp = new ParoleChiave();
				temp.setParola(resultSet.getString(1));
				
			}
		} catch (SQLException e) {
			JOptionPane.showInternalMessageDialog(null, "Si è verificato un errore nel recupero delle parole chiave.", "Errore", JOptionPane.ERROR_MESSAGE);
		}
		
		return temp;
	}
	

	
	public void queryInsertParolaChiave(String parolaChiave) {
		try {
			statement.executeUpdate("INSERT INTO parole_chiave VALUES ('"+parolaChiave.toLowerCase()+"')");
		} catch (SQLException e) {
			JOptionPane.showInternalMessageDialog(null, "Si è verificato un errore nell'inserimento delle parole chiave.", "Errore", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	public void queryUpdateParolaChiave(String vecchiaParolaChiave, String nuovaParolaChiave) {
		try {		
			statement.executeUpdate("UPDATE parole_chiave SET  parola = '"+nuovaParolaChiave.toLowerCase()+"' WHERE parola = '"+vecchiaParolaChiave.toLowerCase()+"'");			
		} catch (SQLException e) {
			JOptionPane.showInternalMessageDialog(null, "Si è verificato un errore nell'update delle parole chiave.", "Errore", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	
	public void eliminaParolaChiave(String parolaChiave) {
		try {
			statement.executeUpdate("DELETE FROM parole_chiave WHERE(parola= '" + parolaChiave.toLowerCase() +"')");
		} catch (SQLException e) {
			JOptionPane.showInternalMessageDialog(null, "Si è verificato un errore nell'eliminazione delle parole chiave.", "Errore", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
}
