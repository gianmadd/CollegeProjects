package Entita;
import java.util.List;

public class Corso {
	
	private String nome;
	private String descrizione;
	private int maxPartecipanti;
	private int percentualeMinimaPresenza;
	private boolean terminato;
	private List<Lezione> lezioniAssociate;
	private List<AppartenenzaAreaTematica> areeAppartenenti;
	private List<Iscrizione> iscrizioniAlCorso;
	
	//Getter e setter
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public int getMaxPartecipanti() {
		return maxPartecipanti;
	}
	public void setMaxPartecipanti(int maxPartecipanti) {
		this.maxPartecipanti = maxPartecipanti;
	}
	public int getPercentualeMinimaPresenza() {
		return percentualeMinimaPresenza;
	}
	public void setPercentualeMinimaPresenza(int percentualeMinimaPresenza) {
		this.percentualeMinimaPresenza = percentualeMinimaPresenza;
	}
	public boolean isTerminato() {
		return terminato;
	}
	public void setTerminato(boolean terminato) {
		this.terminato = terminato;
	}
	public List<Lezione> getLezioniAssociate() {
		return lezioniAssociate;
	}
	public void setLezioniAssociate(List<Lezione> lezioniAssociate) {
		this.lezioniAssociate = lezioniAssociate;
	}
	public List<AppartenenzaAreaTematica> getAreeAppartenenti() {
		return areeAppartenenti;
	}
	public void setAreeAppartenenti(List<AppartenenzaAreaTematica> areeAppartenenti) {
		this.areeAppartenenti = areeAppartenenti;
	}
	public List<Iscrizione> getIscrizioniAlCorso() {
		return iscrizioniAlCorso;
	}
	public void setIscrizioniAlCorso(List<Iscrizione> iscrizioniAlCorso) {
		this.iscrizioniAlCorso = iscrizioniAlCorso;
	}

}
