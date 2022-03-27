package DAO;

import java.util.ArrayList;

import Entita.Lezione;

public interface PartecipazioneDAO {
	public ArrayList<Lezione> getPartecipazioniStudente(int idStudente);
	public String[][] getStatisticheMinMaxPresenze(String nomeCorso);
	public int calcolaPercentualeRiempimentoMedia(String nomeCorso);
	public int getNumeroPresenzeMedio(String nomeCorso);
	public String[][] initMatrice(String[][] matrix);
	public ArrayList<String> ricavaPresenze(String titoloLezione);
	public void queryInsertPartecipazione(Lezione lezione,ArrayList<String> studentiNonPartecipantiSelezionati);
}
