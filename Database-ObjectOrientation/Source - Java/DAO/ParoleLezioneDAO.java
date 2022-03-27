package DAO;

import java.util.ArrayList;

import Entita.Lezione;
import Entita.ParoleLezione;

public interface ParoleLezioneDAO {

	public ArrayList<ParoleLezione> ricavaParoleChiaveLezione(Lezione lezione);
	public void queryInsertParoleLezione(Lezione lezione,ArrayList<String> paroleChiave);
	
}
