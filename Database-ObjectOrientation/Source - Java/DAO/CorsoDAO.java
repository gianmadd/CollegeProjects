package DAO;

import java.util.ArrayList;

import Entita.Corso;

public interface CorsoDAO {
	
	public abstract Corso ricavaCorsoDaLezione(String titoloLezione);
	
	public abstract void setTerminaCorso(String nomeCorso);
	
	public abstract void queryInsertDatiCorso(ArrayList<?> listaDati);
	
	public abstract void queryUpdateDatiCorso(ArrayList<?> listaDati, String nomeCorso);
	
	public abstract void eliminaCorso(String s);
	
	public abstract int queryIdCorsoInserito(Corso corso);
	
	public abstract ArrayList<Corso> ricavaCorsiFiltrati(boolean ordine, int filtroStatoCorso, ArrayList<String> filtroAreeTematicheSelezionati);
	
	public abstract Corso ricavaCorsoScelto(String nomeCorso);
}
