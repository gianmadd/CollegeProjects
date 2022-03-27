package DAO;

import java.util.ArrayList;

import Entita.Lezione;
import Entita.ParoleChiave;

public interface ParoleChiaveDAO {

	public ArrayList<ParoleChiave> ricavaParoleChiaveFiltrate(boolean ordine);
	public ArrayList<ParoleChiave> ricavaParoleChiaveNonAssociate(Lezione lezione);
	public ParoleChiave ricavaParolaChiaveSelezionata(String parolaSelezionata);
	public void queryInsertParolaChiave(String parolaChiave);
	public void queryUpdateParolaChiave(String vecchiaParolaChiave, String nuovaParolaChiave);
	public void eliminaParolaChiave(String s);
}
