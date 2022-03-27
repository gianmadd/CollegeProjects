package PostgresDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import ConnessioneDB.Connessione;
import DAO.LezioneDAO;
import Entita.Lezione;

public class LezionePostgresDAO implements LezioneDAO {
	
	private Connessione connessione;
	private Statement statement;
	private ResultSet resultSet;
	private ArrayList<Lezione> listaLezioni = new ArrayList<Lezione>();
	
	public LezionePostgresDAO() {
		
		connessione = Connessione.getConnessione();		
		statement = connessione.getStatement();
		
	}
	
	public ArrayList<Lezione> getLezioniDelCorso(String nomeCorso) {
		try {
			Lezione temp;
			listaLezioni.clear();
			resultSet = statement.executeQuery("SELECT *"
					+ "FROM lezione l , corso c "
					+ "WHERE c.nome ='"+nomeCorso.toLowerCase()+"' AND l.del_corso = c.corso_id "
					+ "ORDER BY l.titolo");
			
			while(resultSet.next()) {
				temp = new Lezione();
				temp.setTitolo(resultSet.getString(2).toUpperCase());
				temp.setDescrizione(resultSet.getString(3));
				temp.setData(resultSet.getDate(4));
				temp.setOraInizio(resultSet.getTime(5));
				temp.setOraFine(resultSet.getTime(6));
				
				listaLezioni.add(temp);
			}
		}
		catch(SQLException e){
			JOptionPane.showInternalMessageDialog(null, "Si è verificato un errore nel recupero delle lezioni.", "Errore", JOptionPane.ERROR_MESSAGE);
		}
		return listaLezioni;
		
	}
	
	
	public void queryInsertLezione(Lezione lezione) {
		
		int temp = 0;
		
		try {
			resultSet = statement.executeQuery("SELECT corso_id FROM corso WHERE nome='"
						+ lezione.getDelCorso().getNome().toLowerCase() + "'");
			
			while (resultSet.next()) {
				temp = resultSet.getInt(1);		
			}
			
			statement.executeUpdate("INSERT INTO lezione VALUES(nextval('sequenza_lezione_id'), '"
					+ lezione.getTitolo().toLowerCase() + "' , '" + lezione.getDescrizione().toLowerCase() + "' , '"
					+ lezione.getData() + "' ,'" + lezione.getOraInizio() + "', '" + lezione.getOraFine() + "', '"
					+ lezione.getProfessore().getMatricolaProfessore() + "', '" + temp + "');");
			
		} catch (SQLException e) {
			if(e.getSQLState().equals("12300"))
				JOptionPane.showInternalMessageDialog(null, "Errore nell'inserimento della lezione:\n Corso terminato.", "Errore", JOptionPane.ERROR_MESSAGE);
			else if(e.getSQLState().equals("12305"))
				JOptionPane.showInternalMessageDialog(null, "Errore nell'inserimento della lezione:\n "
														  + "Esiste già una lezione del corso che si svolge a quell'ora.", "Errore", JOptionPane.ERROR_MESSAGE);
			else if(e.getSQLState().equals("12308")){
				JOptionPane.showInternalMessageDialog(null, "Errore nell'inserimento della lezione:\n "
														  + "Inserisci un orario corretto.", "Errore", JOptionPane.ERROR_MESSAGE);
			}
			else if(e.getSQLState().equals("12311")){
				JOptionPane.showInternalMessageDialog(null, "Errore nell'inserimento della lezione:\n"
						+ "Impossibile creare una lezione che inizi prima delle 8:00 o che finisca dopo le 20:00.", "Errore", JOptionPane.ERROR_MESSAGE);
			}
			else if(e.getSQLState().equals("12313")) {
				JOptionPane.showInternalMessageDialog(null, "Errore nell'inserimento della lezione:\n"
						+ "Impossibile creare una lezione che duri più di due ore.");
			}
			else
				JOptionPane.showInternalMessageDialog(null, "Errore nell'inserimento della lezione.", "Errore", JOptionPane.ERROR_MESSAGE);
		}
	
		
	}
	
	public void queryUpdateLezione(Lezione lezione) {
		try {		
			statement.executeUpdate("UPDATE lezione SET data = '" +lezione.getData()+ "', ora_inizio = '" +lezione.getOraInizio()+ "', ora_fine = '" +lezione.getOraFine()+ "'"
								 + " WHERE titolo = '"+lezione.getTitolo().toLowerCase()+"'");			
		} catch (SQLException e) {
			if(e.getSQLState().equals("12306"))
				JOptionPane.showInternalMessageDialog(null, "Errore nella modifica della lezione:\n Esiste già una lezione del corso che si svolge a quell'ora.", "Errore", JOptionPane.ERROR_MESSAGE);
			else if(e.getSQLState().equals("12309")){
				JOptionPane.showInternalMessageDialog(null, "Errore nella modifica della lezione:\n "
														  + "Inserisci un orario corretto.", "Errore", JOptionPane.ERROR_MESSAGE);
			}
			else if(e.getSQLState().equals("12312")){
				JOptionPane.showInternalMessageDialog(null, "Errore nella modifica della lezione:\n"
						+ "Impossibile creare una lezione che inizi prima delle 8:00 o che finisca dopo le 20:00.", "Errore", JOptionPane.ERROR_MESSAGE);
			}
			else if(e.getSQLState().equals("12314")) {
				JOptionPane.showInternalMessageDialog(null, "Errore nella modifica della lezione:\n"
						+ "Impossibile creare una lezione che duri più di due ore.");
			}
			else
				JOptionPane.showInternalMessageDialog(null, "Errore nella modifica della lezione.", "Errore", JOptionPane.ERROR_MESSAGE);
		}
	}
	

	
	public void eliminaLezione(String titoloLezione) {
		try {
			statement.executeUpdate("DELETE FROM lezione WHERE(titolo= '" + titoloLezione.toLowerCase() +"')");
		} catch (SQLException e) {
			JOptionPane.showInternalMessageDialog(null, "Si è verificato un errore nell'eliminazione della lezione.", "Errore", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public Lezione ricavaLezioneScelta(String nomeLezione) {
		Lezione temp = new Lezione();
		
		try {
			resultSet = statement.executeQuery("SELECT * from lezione WHERE titolo = '"+nomeLezione.toLowerCase()+"'");
			
			while (resultSet.next()) {
				temp.setTitolo(resultSet.getString(2));
				temp.setDescrizione(resultSet.getString(3));
				temp.setData(resultSet.getDate(4));
				temp.setOraInizio(resultSet.getTime(5));
				temp.setOraFine(resultSet.getTime(6));
				
			}
		} catch (SQLException e) {
			JOptionPane.showInternalMessageDialog(null, "Si è verificato un errore nel recupero delle lezioni.", "Errore", JOptionPane.ERROR_MESSAGE);
		}
		
		
		
		return temp;
	}
	
	
	public ArrayList<Lezione> ricavaLezioniFiltrate(boolean ordine, ArrayList<String> date, ArrayList<String> filtroParoleChiaveSelezionateArrayList){
		
		ArrayList<Lezione> lezioniFiltrate = new ArrayList<Lezione>();
		Lezione tempLezione;
		String comando = new String();
		
		//Il set del datestyle viene effettuato ogni volta che si effettua la query a causa di un problema che resettava il datestyle nel database
		try {
			statement.executeUpdate("SET DATESTYLE TO ISO, DMY;");
		}
		catch(SQLException e1){
			e1.printStackTrace();
		}
		
		comando = "SELECT DISTINCT l.titolo,l.descrizione,l.data,l.ora_inizio,l.ora_fine FROM lezione l WHERE ";
		
		if(!date.isEmpty()) {
			comando = comando.concat("l.data BETWEEN '" + date.get(0) + "' AND '" + date.get(1) + "' AND ");
		}
		
		for (String string : filtroParoleChiaveSelezionateArrayList) {
			comando = comando.concat(" EXISTS (SELECT parola FROM parole_lezione pl WHERE parola='"+string.toLowerCase()+"' AND pl.lezione=l.lezione_id) AND ");
		}
		
		
		comando = comando.concat("true");
		
		if(ordine) {
			comando = comando.concat(" ORDER BY l.data DESC");
		}
		else {
			comando = comando.concat(" ORDER BY l.data ASC");
		}
		
		try {
			resultSet = statement.executeQuery(comando);
			
			while(resultSet.next()) {
				tempLezione = new Lezione();
				tempLezione.setTitolo(resultSet.getString(1).toUpperCase());
				tempLezione.setDescrizione(resultSet.getString(2));
				tempLezione.setData(resultSet.getDate(3));
				tempLezione.setOraInizio(resultSet.getTime(4));
				tempLezione.setOraFine(resultSet.getTime(5));
				
				lezioniFiltrate.add(tempLezione);
			}
		} catch (SQLException e) {
			JOptionPane.showInternalMessageDialog(null, "Si è verificato un errore nel recupero delle lezioni.", "Errore", JOptionPane.ERROR_MESSAGE);
		}
		
		return lezioniFiltrate;
	}
	
	public Time ricavaDurataLezione(Lezione lezione) {
		Time temp = null;
		try {
			resultSet = statement.executeQuery("SELECT calcolo_durata_lezione('" + lezione.getOraInizio() + "', '" + lezione.getOraFine() + "')");
			while(resultSet.next()) {
				temp = resultSet.getTime(1);
			}
		} catch (SQLException e) {
			JOptionPane.showInternalMessageDialog(null, "Si è verificato un errore nel recupero delle lezioni.", "Errore", JOptionPane.ERROR_MESSAGE);
		}
		return temp;
	}

}
