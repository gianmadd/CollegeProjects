package Entita;
import java.util.Date;
import java.util.List;

public class Studente {

	private int matricola;
	private String nome;
	private String cognome;
	private Date dataDiNascita;
	private String email;
	private String telefono;
	private List<Iscrizione> iscrizioniEffettuate;
	private List<Partecipazione> lezioniSeguite;
	
	
	//Getter e setter
	public int getMatricola() {
		return matricola;
	}
	public void setMatricola(int matricola) {
		this.matricola = matricola;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public Date getDataDiNascita() {
		return dataDiNascita;
	}
	public void setDataDiNascita(Date dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public List<Iscrizione> getIscrizioniEffettuate() {
		return iscrizioniEffettuate;
	}
	public void setIscrizioniEffettuate(List<Iscrizione> iscrizioniEffettuate) {
		this.iscrizioniEffettuate = iscrizioniEffettuate;
	}
	public List<Partecipazione> getLezioniSeguite() {
		return lezioniSeguite;
	}
	public void setLezioniSeguite(List<Partecipazione> lezioniSeguite) {
		this.lezioniSeguite = lezioniSeguite;
	}
	
}
