package GUI;


import java.util.ArrayList;
import javax.swing.ListModel;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JCheckBox;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Controller.Controller;
import Entita.Corso;
import Entita.JCheckBoxList;
import Entita.Lezione;
import Entita.Studente;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PannelloStudenti extends PannelloStudentiGrafica {

	private Controller controller;

	
	
	private ArrayList<String> corsiSelezionatiArrayList;
	
	public PannelloStudenti(Controller ctrl) {
		
		controller = ctrl;
		
		getModelSetter().setModelListaStudenti(controller.getStudentiFiltrati(true, new ArrayList<String>()),getList());
		
		stampaCorsiFiltro(controller);
		
		metodoListener();

	}
	
	
	//METODI
	
	@Override
	public void metodoListener() {
		
		getList().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				
				//STAMPA INFORMAZIONI STUDENTE
				if(e.getValueIsAdjusting()) {		
					getNomeDelloStudenteLabel().setText(controller.getStudenteSelezionato().getCognome() + " " +
							controller.getStudenteSelezionato().getNome());
					
					getDataDiNascitaTextPane().setText(controller.getStudenteSelezionato().getDataDiNascita().toString());
					getEmailTextPane().setText(controller.getStudenteSelezionato().getEmail());
					getTelefonoTextPane().setText(controller.getStudenteSelezionato().getTelefono());	
				}
				
				
				//STAMPA ISCRIZIONI AI CORSI DELLO STUDENTE
				if(e.getValueIsAdjusting()) {
					getModelSetter().setModelListaIscrizioniStudenti(controller.getIscrizioniStudente(controller.getStudenteSelezionato().getMatricola()), getIscrizioniStudentiList());
				}
			
				//STAMPA PARTECIPAZIONI A LEZIONI DELLO STUDENTE
				if(e.getValueIsAdjusting()) {
					getModelSetter().setModelListaPartecipazioniStudenti(controller.getPartecipazioniStudente(controller.getStudenteSelezionato().getMatricola()), getPartecipazioniStudentiList());
				}
			
			}
		});
	}
	
	
	
	public void stampaCorsiFiltro(Controller ctrl) {
		
		ArrayList<JCheckBox> arrayList = new ArrayList<JCheckBox>();
		
		for (Corso corso : ctrl.getCorsiFiltrati(true, 1, new ArrayList<String>())){
			arrayList.add(new JCheckBox(corso.getNome().toUpperCase()));		
		}
		
		DefaultListModel<JCheckBox> model = new DefaultListModel<JCheckBox>();
		model.addAll(arrayList);
		getIscrizioniFiltroList().setModel(model);
	}
	
	public boolean acquisisciFiltroOrdinaCorsi() {
		if(getAscDescToggle().getText().equals("A-Z"))
			return true;
		else
			return false;
	}
	
	public ArrayList<String> acquisisciCorsiSelezionati(ArrayList<String> arrayList,JCheckBoxList checkBoxList){
		
		ListModel<JCheckBox> listModel = checkBoxList.getModel();
		
		for(int i=0; i<listModel.getSize();i++) {
			
			if(listModel.getElementAt(i).isSelected()) {
				arrayList.add(listModel.getElementAt(i).getText());
			}
		}

		return arrayList;
	}
	
	
	public void refreshStudenti() {
		
			corsiSelezionatiArrayList = new ArrayList<String>();
			
			acquisisciCorsiSelezionati(corsiSelezionatiArrayList,getIscrizioniFiltroList());
			
			getModelSetter().setModelListaStudenti(controller.getStudentiFiltrati(acquisisciFiltroOrdinaCorsi(),corsiSelezionatiArrayList), getList());
			
			corsiSelezionatiArrayList.clear();
			
	}
	

	@Override
	public void resetFiltri() {
		if(getAscDescToggle().isSelected()) {
			getAscDescToggle().doClick();
		}

	}
	
	
	//LISTENER 
	
	@Override
	public ActionListener listenerEliminaButton() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(getList().getSelectedIndex()!=-1) {
					
					int result = JOptionPane.showConfirmDialog(null, "Sei sicuro di voler eliminare lo studente?\n Eliminerai anche le sue iscrizioni e partecipazioni!",
												"Attenzione", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null);
					
					if(result == JOptionPane.OK_OPTION) {
						controller.eliminaStudenteSelezionato(controller.getStudenteSelezionato().getMatricola());
						refreshStudenti();
						
					}
				}
			}
		};
	}
	
	
	@Override
	public ActionListener listenerNuovoButton() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new InserimentoStudenteFrame(controller);
				refreshStudenti();
			}
		};
	}
	
	@Override
	public ActionListener listenerAscDescToggle() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(getAscDescToggle().getText().equals("A-Z")) {
					getAscDescToggle().setText("Z-A");
				}
				else {
					getAscDescToggle().setText("A-Z");
				}
			}
		};
	}
	
	@Override
	public ActionListener listenerRefreshButton() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshStudenti();
			}
		};
	}
	
}
