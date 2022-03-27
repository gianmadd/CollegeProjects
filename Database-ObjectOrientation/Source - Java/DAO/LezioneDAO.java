package DAO;

import java.sql.Time;
import java.util.ArrayList;

import Entita.Lezione;

public interface LezioneDAO {

	public ArrayList<Lezione> getLezioniDelCorso(String nomeCorso);
	
	public void queryInsertLezione(Lezione lezione);
	
	public void queryUpdateLezione(Lezione lezione);
	
	public void eliminaLezione(String titoloLezione);
	
	public Lezione ricavaLezioneScelta(String nomeLezione);
	
	public Time ricavaDurataLezione(Lezione lezione);
	
	public ArrayList<Lezione> ricavaLezioniFiltrate(boolean ordine, ArrayList<String> date, ArrayList<String> filtroParoleChiaveSelezionateArrayList);
}
