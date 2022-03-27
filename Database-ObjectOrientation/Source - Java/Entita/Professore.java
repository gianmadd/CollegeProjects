package Entita;
import java.util.List;

public class Professore {
	
	private String nome;
	private String cognome;
	private List<Lezione> lezioniTenuteDalProfessore;
	private int matricolaProfessore; 
	
	//Getter e setter
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
	public List<Lezione> getLezioniTenuteDalProfessore() {
		return lezioniTenuteDalProfessore;
	}
	public void setLezioniTenuteDalProfessore(List<Lezione> lezioniTenuteDalProfessore) {
		this.lezioniTenuteDalProfessore = lezioniTenuteDalProfessore;
	}
	public int getMatricolaProfessore() {
		return matricolaProfessore;
	}
	public void setMatricolaProfessore(int matricolaProfessore) {
		this.matricolaProfessore = matricolaProfessore;
	}
	
	
	
	
}
