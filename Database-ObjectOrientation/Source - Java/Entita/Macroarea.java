package Entita;
import java.util.List;

public class Macroarea {
	
	private String nome;
	private List<AreaTematica> areeTematicheContenute;

	//Getter e setter
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<AreaTematica> getAreeTematicheContenute() {
		return areeTematicheContenute;
	}

	public void setAreeTematicheContenute(List<AreaTematica> areeTematicheContenute) {
		this.areeTematicheContenute = areeTematicheContenute;
	}
	
	
	
}
