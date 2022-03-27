package Entita;

public class AppartenenzaAreaTematica {
	
	private Corso corso;
	private AreaTematica temaTrattato;

	
	public AppartenenzaAreaTematica(Corso c,AreaTematica a) {
		setCorso(c);
		setTemaTrattato(a);
	}
	
	//Getter e setter
	public Corso getCorso() {
		return corso;
	}
	public void setCorso(Corso corso) {
		this.corso = corso;
	}
	public AreaTematica getTemaTrattato() {
		return temaTrattato;
	}
	public void setTemaTrattato(AreaTematica temaTrattato) {
		this.temaTrattato = temaTrattato;
	}

	
}
