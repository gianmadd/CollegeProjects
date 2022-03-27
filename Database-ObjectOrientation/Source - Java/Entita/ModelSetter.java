package Entita;

import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.JList;

public class ModelSetter {
	
	//Set models per pannello corsi
	public void setModelListaCorsi(ArrayList<Corso> arrayList,JList list) {	
		list.setModel(new AbstractListModel() {
			public int getSize() {
				return arrayList.size();
			}
			public Object getElementAt(int index) {
				return arrayList.get(index).getNome().toUpperCase();
			}
		});
	}
	
	
	public void setModelListaIscrizioniAlCorso(ArrayList<String> arrayList,JList list) {	
		list.setModel(new AbstractListModel() {
			public int getSize() {
				return arrayList.size();
			}
			public String getElementAt(int index) {
				return arrayList.get(index).toUpperCase();
			}
		});
	}
	
	public void setModelListaLezioniDelCorso(ArrayList<Lezione> arrayList,JList list) {	
		list.setModel(new AbstractListModel() {
			public int getSize() {
				return arrayList.size();
			}
			public Object getElementAt(int index) {
				return arrayList.get(index).getTitolo();
			}
		});
	}
	
	//Set models per pannello lezioni
	public void setModelListaLezioni(ArrayList<Lezione> arrayList,JList list) {	
		list.setModel(new AbstractListModel() {
			public int getSize() {
				return arrayList.size();
			}
			public Object getElementAt(int index) {
				return arrayList.get(index).getTitolo();
			}
		});
	}
	
	public void setModelListaPresenzeAllaLezione(ArrayList<String> arrayList, JList presenzeLista) {
		presenzeLista.setModel(new AbstractListModel() {
			public int getSize() {
				return arrayList.size();
			}
			public Object getElementAt(int index) {
				return arrayList.get(index);
			}
		});
	}
	
	public void setModelListaParoleChiaveDellaLezione(ArrayList<ParoleLezione> arrayList, JList paroleChiaveLista) {
		paroleChiaveLista.setModel(new AbstractListModel() {
			public int getSize() {
				return arrayList.size();
			}
			public Object getElementAt(int index) {
				return arrayList.get(index).getParoleChiave().getParola();
			}
		});
	}

	//Set models per pannello parole chiave
	public void setModelListaParoleChiave(ArrayList<ParoleChiave> arrayList,JList list) {	
		list.setModel(new AbstractListModel() {
			public int getSize() {
				return arrayList.size();
			}
			public Object getElementAt(int index) {
				return arrayList.get(index).getParola().toUpperCase();
			}
		});
	}

	//Set models per pannello professori
	public void setModelListaProfessori(ArrayList<Professore> arrayList,JList list) {	
		list.setModel(new AbstractListModel() {
			public int getSize() {
				return arrayList.size();
			}
			public Object getElementAt(int index) {
				return arrayList.get(index).getCognome().toUpperCase()+" "+arrayList.get(index).getNome().toUpperCase() +" - "+arrayList.get(index).getMatricolaProfessore();
			}
		});
	}
	
	public void setModelListaInsegnamentiDelProfessore(ArrayList<Corso> arrayList,JList list) {	
		list.setModel(new AbstractListModel() {
			public int getSize() {
				return arrayList.size();
			}
			public Object getElementAt(int index) {
				return arrayList.get(index).getNome();
			}
		});
	}
	
	//Set models per pannello studenti
	public void setModelListaStudenti(ArrayList<Studente> arrayList,JList list) {	
		list.setModel(new AbstractListModel() {
			public int getSize() {
				return arrayList.size();
			}
			public Object getElementAt(int index) {
				return arrayList.get(index).getCognome().toUpperCase() + " " + arrayList.get(index).getNome().toUpperCase() +" - "+arrayList.get(index).getMatricola();
			}
		});
	}
	
	public void setModelListaIscrizioniStudenti(ArrayList<Corso> arrayList,JList list) {	
		list.setModel(new AbstractListModel() {
			public int getSize() {
				return arrayList.size();
			}
			public Object getElementAt(int index) {
				return arrayList.get(index).getNome();
			}
		});
	}
	
	public void setModelListaPartecipazioniStudenti(ArrayList<Lezione> arrayList,JList list) {	
		list.setModel(new AbstractListModel() {
			public int getSize() {
				return arrayList.size();
			}
			public Object getElementAt(int index) {
				return arrayList.get(index).getTitolo();
			}
		});
	}

	
	//Set models per pannello aree tematiche
	public void setModelListaMacroaree(ArrayList<Macroarea> arrayList,JList list) {	
		list.setModel(new AbstractListModel() {
			public int getSize() {
				return arrayList.size();
			}
			public Object getElementAt(int index) {
				return arrayList.get(index).getNome().toUpperCase();
			}
		});
	}
	
	public void setModelListaAreeTematiche(ArrayList<AreaTematica> arrayList,JList list) {	
		list.setModel(new AbstractListModel() {
			public int getSize() {
				return arrayList.size();
			}
			public Object getElementAt(int index) {
				return arrayList.get(index).getTema().toUpperCase();
			}
		});
	}
	
	public void setModelListaCorsiAppartenentiAreaTematica(ArrayList<Corso> arrayList,JList list) {	
		list.setModel(new AbstractListModel() {
			public int getSize() {
				return arrayList.size();
			}
			public Object getElementAt(int index) {
				return arrayList.get(index).getNome().toUpperCase();
			}
		});
	}
}
