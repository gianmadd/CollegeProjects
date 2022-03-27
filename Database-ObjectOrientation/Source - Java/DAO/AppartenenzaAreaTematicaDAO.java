package DAO;

import java.util.ArrayList;

import Entita.Corso;

public interface AppartenenzaAreaTematicaDAO {

	public ArrayList<Corso> ricavaCorsiAppartenentiAreaTematica(String temaTrattato);
	
	public void queryInsertAppartenenzaArea(int idCorso,ArrayList<String> areeScelte);
	
}
