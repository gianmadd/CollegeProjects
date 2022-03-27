package DAO;

import java.util.ArrayList;

import Entita.Corso;
import Entita.Professore;

public interface ProfessoreDAO {

	public ArrayList<Professore> ricavaProfessoriFiltrati(boolean ordine,ArrayList<String> filtroCorsiSelezionati);
	public ArrayList<Corso> ricavaInsegnamenti(int idProfessore);
	public Professore ricavaProfessoreDaLezione(String titoloLezione);
	public void queryInsertProfessore(String nome, String cognome);
	public void eliminaProfessore(int idProfessore);
	public Professore ricavaProfessoreSelezionato(int idProfessore);
	
}
