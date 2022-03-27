package PostgresDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import ConnessioneDB.Connessione;
import DAO.AreaTematicaDAO;
import Entita.AreaTematica;
import Entita.Corso;
import Entita.Macroarea;

public class AreaTematicaPostgresDAO implements AreaTematicaDAO {
	
	private Connessione connessione;
	private Statement statement;
	private ResultSet resultSet;
	private ArrayList<AreaTematica> listaAree = new ArrayList<AreaTematica>();
	private ArrayList<AreaTematica> listaAreeTematiche = new ArrayList<AreaTematica>();
	private ArrayList<AreaTematica> listaAreeTematicheNonAssociate = new ArrayList<AreaTematica>();


	public AreaTematicaPostgresDAO() {
		connessione = Connessione.getConnessione();		
		statement = connessione.getStatement();
	}
	
	
	public ArrayList<AreaTematica> getAreeTematiche(String nomeMacroarea) {
		try {
			AreaTematica temp;
			listaAree.clear();
			resultSet = statement.executeQuery("SELECT tema FROM area_tematica "
											 + "WHERE nome_macroarea='" + nomeMacroarea.toLowerCase() + "' "
											 + "ORDER BY tema");
			
			
			while(resultSet.next()) {
				temp = new AreaTematica();
				temp.setTema(resultSet.getString(1).toUpperCase());
				listaAree.add(temp);
			}	
			
		} catch (SQLException e) {
			JOptionPane.showInternalMessageDialog(null, "Si è verificato un errore nel recupero delle aree tematiche.", "Errore", JOptionPane.ERROR_MESSAGE);
		}
		return listaAree;	
	}
	
	public ArrayList<AreaTematica> queryAreeTematiche(){
		
		AreaTematica temp;
		Macroarea macroareaTemp;
		listaAreeTematiche.clear();
		
		try {
			resultSet = statement.executeQuery("SELECT DISTINCT tema FROM area_tematica ORDER BY tema");
			
			while (resultSet.next()) {
				temp = new AreaTematica();
				macroareaTemp = new Macroarea();
				
				temp.setTema(resultSet.getString(1));
				
				listaAreeTematiche.add(temp);
			}
		} catch (SQLException e) {
			JOptionPane.showInternalMessageDialog(null, "Si è verificato un errore nel recupero delle aree tematiche.", "Errore", JOptionPane.ERROR_MESSAGE);
		}
	
		return listaAreeTematiche;
	}
	
	
	public ArrayList<AreaTematica> queryAreaTematicaNonAssociata(Corso corso){
		int idCorso = 0;
		AreaTematica temp;
		Macroarea macroareaTemp;
		listaAreeTematicheNonAssociate.clear();
		
		try {
			resultSet = statement.executeQuery("SELECT corso_id FROM corso WHERE nome = '"+corso.getNome().toLowerCase()+"'");
			
			while (resultSet.next()) {
				idCorso = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			JOptionPane.showInternalMessageDialog(null, "Si è verificato un errore nel recupero delle aree tematiche.", "Errore", JOptionPane.ERROR_MESSAGE);
		}
		
		
		try {
			resultSet = statement.executeQuery("SELECT DISTINCT tema FROM area_tematica "
											 + "WHERE tema NOT IN (SELECT tema_trattato "
											 					+ "FROM appartenenza_area "
											 					+ "WHERE corso = '"+idCorso+"') "
											 + "ORDER BY tema");
			
			while (resultSet.next()) {
				temp = new AreaTematica();
				macroareaTemp = new Macroarea();
				
				temp.setTema(resultSet.getString(1));
				
				listaAreeTematicheNonAssociate.add(temp);
			}
		} catch (SQLException e) {
			JOptionPane.showInternalMessageDialog(null, "Si è verificato un errore nel recupero delle aree tematiche.", "Errore", JOptionPane.ERROR_MESSAGE);
		}
		
		return listaAreeTematicheNonAssociate;
	}
	
	
	
	
	public void queryInsertAreaTematica(String areaTematica,String macroareaSelezionata) {
		
		try {
			statement.executeUpdate("INSERT INTO area_tematica VALUES('"+areaTematica.toLowerCase()+"' , '"+macroareaSelezionata.toLowerCase()+"')");	
		} catch (SQLException e) {
			JOptionPane.showInternalMessageDialog(null, "Si è verificato un errore nell' inserimento dell' area tematica.", "Errore", JOptionPane.ERROR_MESSAGE);
		}
		

	}
	
	public void queryUpdateAreaTematica(String vecchiaAreaTematica, String nuovaAreaTematica) {
		try {		
			statement.executeUpdate("UPDATE area_tematica SET  tema = '"+nuovaAreaTematica.toLowerCase()+"' WHERE tema = '"+vecchiaAreaTematica.toLowerCase()+"'");			
		} catch (SQLException e) {
			JOptionPane.showInternalMessageDialog(null, "Si è verificato un errore nell' aggiornamento delle aree tematiche.", "Errore", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void eliminaAreaTematica(String temaTrattato) {
		try {
			statement.executeUpdate("DELETE FROM area_tematica WHERE(tema= '" +temaTrattato.toLowerCase() +"')");
		} catch (SQLException e) {
			JOptionPane.showInternalMessageDialog(null, "Si è verificato un errore nell'eliminazione dell'area tematica.", "Errore", JOptionPane.ERROR_MESSAGE);
		}
		
	}

}
