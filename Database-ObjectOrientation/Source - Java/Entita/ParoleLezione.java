package Entita;

public class ParoleLezione {
	
	private ParoleChiave paroleChiave;
	private Lezione lezione;
	
	public ParoleLezione(ParoleChiave parola, Lezione lezione) {
		setParoleChiave(parola);
		setLezione(lezione);

	}
	
	//Getter e setter
	public ParoleChiave getParoleChiave() {
		return paroleChiave;
	}
	public void setParoleChiave(ParoleChiave paroleChiave) {
		this.paroleChiave = paroleChiave;
	}
	public Lezione getLezione() {
		return lezione;
	}
	public void setLezione(Lezione lezione) {
		this.lezione = lezione;
	}
	
	
}
