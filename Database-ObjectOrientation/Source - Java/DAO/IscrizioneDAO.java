package DAO;

import java.util.ArrayList;

import Entita.Corso;

public interface IscrizioneDAO {

	public ArrayList<String> getIscrizioni(String nomeCorso);
	
	public ArrayList<Corso> getIscrizioniStudente(int idStudente);
	
	public void queryInsertIscrizioni(String nomeCorso, ArrayList<String> studenti);
}
