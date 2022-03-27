package PostgresDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import ConnessioneDB.Connessione;
import DAO.CorsoDAO;
import Entita.Corso;
public class CorsoPostgresDAO implements CorsoDAO {

	private Connessione connessione;
	private Statement statement;
	private ResultSet resultSet;
	
	

	public CorsoPostgresDAO(){
		
		connessione=Connessione.getConnessione();
		
		statement = connessione.getStatement();
		
	}
	
	
	public Corso ricavaCorsoDaLezione(String titoloLezione) {
		
		Corso temp = null;
		
		try {
			
			resultSet = statement.executeQuery("SELECT c.corso_id, c.nome, c.descrizione, c.max_partecipanti, c.percentuale_minima_presenze, c.terminato "
											 + "FROM corso c JOIN lezione l ON c.corso_id = l.del_corso "
											 + "WHERE l.titolo = '" + titoloLezione.toLowerCase() + "'");
			while (resultSet.next()) {
				temp = new Corso();
				temp.setNome(resultSet.getString(2).toUpperCase());
				temp.setDescrizione(resultSet.getString(3));
				temp.setMaxPartecipanti(resultSet.getInt(4));
				temp.setPercentualeMinimaPresenza(resultSet.getInt(5));
				temp.setTerminato(resultSet.getBoolean(6));
			}
		} catch (SQLException e) {
			JOptionPane.showInternalMessageDialog(null, "Si è verificato un errore nel recupero dei corsi.", "Errore", JOptionPane.ERROR_MESSAGE);
		}
	
	return temp;
	}
	
	public void setTerminaCorso(String nomeCorso) {
		try {
			statement.executeUpdate("UPDATE corso SET terminato='true' WHERE nome='"+nomeCorso.toLowerCase()+"'");
		} catch (SQLException e) {
			JOptionPane.showInternalMessageDialog(null, "Si è verificato un errore nel recupero dei corsi.", "Errore", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	public void queryInsertDatiCorso(ArrayList<?> listaDati) {
		try {
			statement.executeUpdate("INSERT INTO corso VALUES (nextval('sequenza_corso_id'), '"
					+ listaDati.get(0) + "', '" + listaDati.get(1) + "', '" + listaDati.get(2) + "', '" + listaDati.get(3) + "' , 'false');");
		} catch (SQLException e) {
			JOptionPane.showInternalMessageDialog(null, "Si è verificato un errore nel recupero dei corsi.", "Errore", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void queryUpdateDatiCorso(ArrayList<?> listaDati, String nomeCorso) {
		try {		
			statement.executeUpdate("UPDATE corso SET  nome = '" +listaDati.get(0)+ "', descrizione = '" +listaDati.get(1)+ "', max_partecipanti = '" +listaDati.get(2)+ "',"
					+ " percentuale_minima_presenze =  '" +listaDati.get(3)+ "' WHERE nome = '"+nomeCorso.toLowerCase()+"'");			
		} catch (SQLException e) {
			if(e.getSQLState().equals("12310"))	{
				JOptionPane.showInternalMessageDialog(null, "Errore durante la modifica del corso:\n Il nuovo numero massimo "
						+ "di iscritti non può essere minore del numero attuale di iscritti.", "Errore", JOptionPane.ERROR_MESSAGE);
			}
			else
				JOptionPane.showInternalMessageDialog(null, "Errore durante la modifica del corso.", "Errore", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void eliminaCorso(String s) {
		try {
			statement.executeUpdate("DELETE FROM corso WHERE(nome= '" + s.toLowerCase() +"')");
		} catch (SQLException e) {
			JOptionPane.showInternalMessageDialog(null, "Si è verificato un errore nel recupero dei corsi.", "Errore", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	public int queryIdCorsoInserito(Corso corso) {
		
		int idCorso = 0;
		
		try {
			resultSet = statement.executeQuery("SELECT corso_id FROM corso WHERE nome='"+corso.getNome()+"'");
			
			while(resultSet.next()) {
				idCorso = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			JOptionPane.showInternalMessageDialog(null, "Si è verificato un errore nel recupero dei corsi.", "Errore", JOptionPane.ERROR_MESSAGE);
		}
		
		return idCorso;
	}
	
	
	
	public ArrayList<Corso> ricavaCorsiFiltrati(boolean ordine, int filtroStatoCorso, ArrayList<String> filtroAreeTematicheSelezionati){
		
		ArrayList<Corso> corsiFiltrati = new ArrayList<Corso>();
		Corso tempCorso;
		
		String comando = new String();
		
		
		comando = "SELECT DISTINCT c.nome,c.descrizione,c.max_partecipanti,c.percentuale_minima_presenze,c.terminato FROM corso c WHERE ";
		
		if(filtroStatoCorso==1) {
			comando = comando.concat("'true' AND ");
		}
		else if(filtroStatoCorso==2) {
			comando = comando.concat("terminato = 'false' AND ");
		} 
		else if(filtroStatoCorso==3) {
			comando = comando.concat("terminato = 'true' AND ");
		}
		
		
		for (String areaTematica : filtroAreeTematicheSelezionati) {
			comando = comando.concat("EXISTS (SELECT tema_trattato FROM appartenenza_area a WHERE a.tema_trattato='"+areaTematica.toLowerCase()+"' AND a.corso=c.corso_id) AND ");
		}
		
		
		comando = comando.concat("'true'");

		if(ordine) {
			comando = comando.concat(" ORDER BY c.nome ASC");
		}
		else {
			comando = comando.concat(" ORDER BY c.nome DESC");
		}
				
		try {
			resultSet = statement.executeQuery(comando);
			
			while (resultSet.next()) {
				tempCorso = new Corso();
				tempCorso.setNome(resultSet.getString(1));
				tempCorso.setDescrizione(resultSet.getString(2));
				tempCorso.setMaxPartecipanti(resultSet.getInt(3));
				tempCorso.setPercentualeMinimaPresenza(resultSet.getInt(4));
				tempCorso.setTerminato(resultSet.getBoolean(5));
				
				corsiFiltrati.add(tempCorso);
			}
		} catch (SQLException e) {
			JOptionPane.showInternalMessageDialog(null, "Si è verificato un errore nel recupero dei corsi.", "Errore", JOptionPane.ERROR_MESSAGE);
		}
		
		return corsiFiltrati;
	}
	
	
	
	public Corso ricavaCorsoScelto(String nomeCorso) {
		Corso temp = new Corso();
		
		try {
			resultSet = statement.executeQuery("SELECT * from corso WHERE nome = '"+nomeCorso.toLowerCase()+"'");
			
			while (resultSet.next()) {
				temp.setNome(resultSet.getString(2));
				temp.setDescrizione(resultSet.getString(3));
				temp.setMaxPartecipanti(resultSet.getInt(4));
				temp.setPercentualeMinimaPresenza(resultSet.getInt(5));
				temp.setTerminato(resultSet.getBoolean(6));
				
			}
		} catch (SQLException e) {
			JOptionPane.showInternalMessageDialog(null, "Si è verificato un errore nel recupero dei corsi.", "Errore", JOptionPane.ERROR_MESSAGE);
		}
		
		return temp;
	}
	
}
