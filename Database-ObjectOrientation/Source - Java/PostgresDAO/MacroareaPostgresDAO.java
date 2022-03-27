package PostgresDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import ConnessioneDB.Connessione;
import DAO.MacroareaDAO;
import Entita.Lezione;
import Entita.Macroarea;
import Entita.ParoleChiave;

public class MacroareaPostgresDAO implements MacroareaDAO {
	private Connessione connessione;
	private Statement statement;
	private ResultSet resultSet;
	
	public MacroareaPostgresDAO() {
		connessione = Connessione.getConnessione();		
		statement = connessione.getStatement();
	}
	
	public Macroarea ricavaMacroareaSelezionata(String nomeMacroarea) {
		
		Macroarea temp = new Macroarea();
		
		try {
			resultSet = statement.executeQuery("SELECT * FROM macroarea WHERE nome='"+nomeMacroarea.toLowerCase()+"'");
			
			while (resultSet.next()) {
				temp = new Macroarea();
				temp.setNome(resultSet.getString(1));
				
			}
		} catch (SQLException e) {
			JOptionPane.showInternalMessageDialog(null, "Si è verificato un errore nel recupero delle macroaree.", "Errore", JOptionPane.ERROR_MESSAGE);
		}
		
		return temp;
		
	}
	
	
	public ArrayList<Macroarea> ricavaMacroareeFiltrate(boolean ordine){
		
		ArrayList<Macroarea> macroareeFiltrate = new ArrayList<Macroarea>();
		
		Macroarea temp;
		
		String comando = new String();
		
		comando = "SELECT * FROM macroarea ";
		
		if(ordine) {
			comando = comando.concat("ORDER BY nome ASC");
		}
		else {
			comando = comando.concat("ORDER BY nome DESC");
		}
		
		
		try {
			resultSet = statement.executeQuery(comando);
			
			while (resultSet.next()) {
				temp = new Macroarea();
				temp.setNome(resultSet.getString(1));
				
				macroareeFiltrate.add(temp);
			}
		} catch (SQLException e) {
			JOptionPane.showInternalMessageDialog(null, "Si è verificato un errore nel recupero delle macroaree.", "Errore", JOptionPane.ERROR_MESSAGE);
		}
		
		return macroareeFiltrate;
		
		
	}
	

	public void queryInsertMacroarea(String nomeMacroarea) {
		try {
			statement.executeUpdate("INSERT INTO macroarea VALUES ('"+nomeMacroarea.toLowerCase()+"')");
		} catch (SQLException e) {
			JOptionPane.showInternalMessageDialog(null, "Si è verificato un errore nel recupero delle macroaree.", "Errore", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void queryUpdateMacroarea(String vecchiaMacroarea, String nuovaMacroarea) {
		try {		
			statement.executeUpdate("UPDATE macroarea SET  nome = '"+nuovaMacroarea.toLowerCase()+"' WHERE nome = '"+vecchiaMacroarea.toLowerCase()+"'");			
		} catch (SQLException e) {
			JOptionPane.showInternalMessageDialog(null, "Si è verificato un errore nel recupero delle macroaree.", "Errore", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void eliminaMacroarea(String nomeMacroarea) {
		try {
			statement.executeUpdate("DELETE FROM macroarea WHERE(nome= '" + nomeMacroarea.toLowerCase() +"')");
		} catch (SQLException e) {
			JOptionPane.showInternalMessageDialog(null, "Si è verificato un errore nel recupero delle macroaree.", "Errore", JOptionPane.ERROR_MESSAGE);		
		}
	}
}
