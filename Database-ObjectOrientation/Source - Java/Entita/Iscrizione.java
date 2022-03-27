package Entita;

public class Iscrizione {

	private Studente studenteIscritto;
	private Corso corsoScelto;
	private boolean superato = false;
	
	public Iscrizione(Studente studente, Corso corso) {
		setStudente(studente);
		setCorso(corso);
	}
	
	//Getter e setter
	public Studente getStudente() {
		return studenteIscritto;
	}
	public void setStudente(Studente studente) {
		this.studenteIscritto = studente;
	}
	public Corso getCorso() {
		return corsoScelto;
	}
	public void setCorso(Corso corso) {
		this.corsoScelto = corso;
	}
	public boolean isSuperato() {
		return superato;
	}
	public void setSuperato(boolean superato) {
		this.superato = superato;
	}
}
