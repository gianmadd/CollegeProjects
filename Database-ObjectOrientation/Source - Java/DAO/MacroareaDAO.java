package DAO;

import java.util.ArrayList;

import Entita.Macroarea;

public interface MacroareaDAO {

	public Macroarea ricavaMacroareaSelezionata(String nomeMacroarea);
	
	public ArrayList<Macroarea> ricavaMacroareeFiltrate(boolean ordine);
	
	public void queryInsertMacroarea(String nomeMacroarea);
	
	public void queryUpdateMacroarea(String vecchiaMacroarea, String nuovaMacroarea);
	
	public void eliminaMacroarea(String nomeMacroarea);
}
