package Entita;

public class Operatore {

	private String nomeUtente;
	private String password;
	
	public Operatore(String nomeUtente, String password) {
		setNomeUtente(nomeUtente);
		setPassword(password);
	}
	
	
	public String getNomeUtente() {
		return nomeUtente;
	}
	public void setNomeUtente(String nomeUtente) {
		this.nomeUtente = nomeUtente;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
