package ConnessioneDB;

import java.sql.*;

public class Connessione {
	
	private static Connection connection = null;
	private static Connessione istanza;
	private Statement statement;
	
	private Connessione(){

		statement = null;
		
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println(e);
		}
		
		try {
			String url = "jdbc:postgresql://localhost:5432/postgres";
			connection = DriverManager.getConnection(url,"postgres","pinodaniele");
			System.out.println("connessione a postgres effettuata");
			
			connettiDBCorsiDiFormazione();
			
		} catch (SQLException e) {
			System.err.println(e);
		}
		

		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public static Connessione getConnessione() {
		
		if(istanza == null) 
			istanza = new Connessione();
		
		return istanza;
	}
	
	public Statement getStatement() {
		return statement;
	}

	
	public void connettiDBCorsiDiFormazione() throws MissingDBException{
		String url = "jdbc:postgresql://localhost:5432/corsidiformazione";
		try {
			connection = DriverManager.getConnection(url,"postgres","pinodaniele");
		} catch (SQLException e) {
			throw new MissingDBException(connection);
		}
		System.out.println("connessione effettuata");
	}
	
}	

