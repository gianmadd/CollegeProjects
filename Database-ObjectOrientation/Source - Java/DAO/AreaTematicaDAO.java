package DAO;

import java.util.ArrayList;

import Entita.AreaTematica;
import Entita.Corso;

public interface AreaTematicaDAO {

	public ArrayList<AreaTematica> getAreeTematiche(String nomeMacroarea);
	
	public ArrayList<AreaTematica> queryAreeTematiche();
	
	public ArrayList<AreaTematica> queryAreaTematicaNonAssociata(Corso corso);
	
	public void queryInsertAreaTematica(String areaTematica,String macroareaSelezionata);
	
	public void queryUpdateAreaTematica(String vecchiaAreaTematica, String nuovaAreaTematica);
	
	public void eliminaAreaTematica(String temaTrattato);
}
