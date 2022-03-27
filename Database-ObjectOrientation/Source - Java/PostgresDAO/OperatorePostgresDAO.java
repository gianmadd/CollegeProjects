package PostgresDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import ConnessioneDB.Connessione;
import DAO.OperatoreDAO;
import Entita.Operatore;

public class OperatorePostgresDAO implements OperatoreDAO{

	private Connessione connessione;
	private Statement statement;
	private ResultSet resultSet;
	
	public OperatorePostgresDAO() {
		connessione = Connessione.getConnessione();		
		statement = connessione.getStatement();
	}
	
	
	public boolean checkDatiLogin(Operatore operatore) {
		
		int conteggio = 0;
		
		try {
			resultSet = statement.executeQuery("SELECT COUNT(*) FROM operatore o "
											 + "WHERE o.nome_utente = '"+operatore.getNomeUtente().toLowerCase()+"' AND o.password = '"+operatore.getPassword().toLowerCase() +"'");
			
			while(resultSet.next()) {
				conteggio = resultSet.getInt(1);
			}
			
		} catch (SQLException e) {
			JOptionPane.showInternalMessageDialog(null, "Si è verificato un errore.", "Errore", JOptionPane.ERROR_MESSAGE);		
		}
		
		if(conteggio == 0) 
			return false;
		else 
			return true;
	}
}
