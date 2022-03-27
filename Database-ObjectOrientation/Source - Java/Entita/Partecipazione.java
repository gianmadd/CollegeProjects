package Entita;

public class Partecipazione {
	
	private Lezione lezioneFrequentata;
	private Studente studentePartecipante;
	
	public Partecipazione(Lezione lezione, Studente studente) {
		setLezioneFrequentata(lezione);
		setStudentePartecipante(studente);
	}
	
	
	//Getter e setter
	public Lezione getLezioneFrequentata() {
		return lezioneFrequentata;
	}
	public void setLezioneFrequentata(Lezione lezioneFrequentata) {
		this.lezioneFrequentata = lezioneFrequentata;
	}
	public Studente getStudentePartecipante() {
		return studentePartecipante;
	}
	public void setStudentePartecipante(Studente studentePartecipante) {
		this.studentePartecipante = studentePartecipante;
	}
	

	
}
