package DAO;

import java.util.ArrayList;

import Entita.Lezione;
import Entita.Studente;

public interface StudenteDAO {

	public void queryInsertStudente(Studente studente);
	public void eliminaStudente(int idStudente);
	public ArrayList<Studente> queryStudentiNonIscritti(String nomeCorso);
	public ArrayList<Studente> queryStudentiNonPartecipanti(Lezione lezione);
	public Studente ricavaStudenteSelezionato(int idStudente);
	public ArrayList<Studente> ricavaStudentiFiltrati(boolean ordine,ArrayList<String> filtroCorsiSelezionati);
}
