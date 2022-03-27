package PostgresDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.postgresql.shaded.com.ongres.scram.common.util.CharAttribute;

import ConnessioneDB.Connessione;
import DAO.AppartenenzaAreaTematicaDAO;
import Entita.AppartenenzaAreaTematica;
import Entita.AreaTematica;
import Entita.Corso;
import Entita.Macroarea;

public class AppartenenzaAreaTematicaPostgresDAO implements AppartenenzaAreaTematicaDAO{
	
	private Connessione connessione;
	private Statement statement;
	private ResultSet resultSet;
	private ArrayList<Corso> listaCorsiAppartenentiAreaTematica = new ArrayList<Corso>();


	public AppartenenzaAreaTematicaPostgresDAO() {
		connessione = Connessione.getConnessione();		
		statement = connessione.getStatement();
	}

	
	public ArrayList<Corso> ricavaCorsiAppartenentiAreaTematica(String temaTrattato){
		try {
			Corso temp;
			listaCorsiAppartenentiAreaTematica.clear();
			resultSet = statement.executeQuery("SELECT DISTINCT c.nome,c.descrizione,c.max_partecipanti,c.percentuale_minima_presenze,c.terminato"
					+ " FROM appartenenza_area a JOIN corso c ON c.corso_id=a.corso WHERE a.tema_trattato='"+temaTrattato.toLowerCase()+"'");
			
			while(resultSet.next()) {
				temp = new Corso();
				temp.setNome(resultSet.getString(1).toUpperCase());
				temp.setDescrizione(resultSet.getString(2));
				temp.setMaxPartecipanti(resultSet.getInt(3));
				temp.setPercentualeMinimaPresenza(resultSet.getInt(4));
				temp.setTerminato(resultSet.getBoolean(5));
				listaCorsiAppartenentiAreaTematica.add(temp);
			}	
			
		} catch (SQLException e) {
			JOptionPane.showInternalMessageDialog(null, "Si è verificato un errore.", "Errore", JOptionPane.ERROR_MESSAGE);
		}
		return listaCorsiAppartenentiAreaTematica;
	}
	
	
	public void queryInsertAppartenenzaArea(int idCorso,ArrayList<String> areeScelte) {
		
		AreaTematica tempAreaTematica;
		Corso tempCorso = new Corso();
		
		
		try {
			resultSet = statement.executeQuery("SELECT nome FROM corso WHERE corso_id='"+idCorso+"'");
			
			while (resultSet.next()) {
				tempCorso = new Corso();
				tempCorso.setNome(resultSet.getString(1));
			}
		} catch (SQLException e) {
			JOptionPane.showInternalMessageDialog(null, "Si è verificato un errore.", "Errore", JOptionPane.ERROR_MESSAGE);
		}
		
		
		try {
			
			for (String string : areeScelte) {
				
				tempAreaTematica = new AreaTematica();
				tempAreaTematica.setTema(string);
								
				statement.executeUpdate("INSERT INTO appartenenza_area VALUES('"+idCorso+"', '"+tempAreaTematica.getTema().toLowerCase()+"');");			
			}
			
		} catch (SQLException e) {
			JOptionPane.showInternalMessageDialog(null, "Si è verificato un errore.", "Errore", JOptionPane.ERROR_MESSAGE);
		}
	}
	

}
