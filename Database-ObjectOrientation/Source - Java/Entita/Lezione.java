package Entita;
import java.sql.Time;
import java.util.Date;
import java.util.List;

public class Lezione {

	private String titolo;
	private String descrizione;
	private Date data;
	private Time oraInizio;
	private Time oraFine;
	private float durata;
	private Professore professore;
	private Corso delCorso;
	private List<Partecipazione> presenze;
	private List<ParoleLezione> paroleInerenti;
	
	//Getter e setter
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Time getOraInizio() {
		return oraInizio;
	}
	public void setOraInizio(Time oraInizio) {
		this.oraInizio = oraInizio;
	}
	public float getDurata() {
		return durata;
	}
	public void setDurata(float durata) {
		this.durata = durata;
	}
	public Professore getProfessore() {
		return professore;
	}
	public void setProfessore(Professore professore) {
		this.professore = professore;
	}
	public Corso getDelCorso() {
		return delCorso;
	}
	public void setDelCorso(Corso delCorso) {
		this.delCorso = delCorso;
	}
	public List<Partecipazione> getPresenze() {
		return presenze;
	}
	public void setPresenze(List<Partecipazione> presenze) {
		this.presenze = presenze;
	}
	public List<ParoleLezione> getParoleInerenti() {
		return paroleInerenti;
	}
	public void setParoleInerenti(List<ParoleLezione> paroleInerenti) {
		this.paroleInerenti = paroleInerenti;
	}
	public Time getOraFine() {
		return oraFine;
	}
	public void setOraFine(Time oraFine) {
		this.oraFine = oraFine;
	}
	
	
}
