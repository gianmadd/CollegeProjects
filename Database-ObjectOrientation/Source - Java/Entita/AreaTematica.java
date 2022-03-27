package Entita;
import java.util.List;

public class AreaTematica {

	private String tema;
	private Macroarea macroarea;
	private List<AppartenenzaAreaTematica> areeAppartenenti;

	
	
	
	//Getter e setter
	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}
	
	public Macroarea getMacroarea() {
		return macroarea;
	}

	public void setMacroarea(Macroarea macroarea) {
		this.macroarea = macroarea;
	}

	public List<AppartenenzaAreaTematica> getAreeAppartenenti() {
		return areeAppartenenti;
	}

	public void setAreeAppartenenti(List<AppartenenzaAreaTematica> areeAppartenenti) {
		this.areeAppartenenti = areeAppartenenti;
	}

}
