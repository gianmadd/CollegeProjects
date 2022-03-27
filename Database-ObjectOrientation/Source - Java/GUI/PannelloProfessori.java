package GUI;


import java.util.ArrayList;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Controller.Controller;
import Entita.Corso;
import Entita.JCheckBoxList;
import Entita.Professore;

import javax.swing.ListModel;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PannelloProfessori extends PannelloProfessoriGrafica {

	private Controller controller;
	
	private ArrayList<String> corsiSelezionatiArrayList;
	
	public PannelloProfessori(Controller ctrl) {
		
		controller = ctrl;
		getModelSetter().setModelListaProfessori(controller.getProfessoriFiltrati(true, new ArrayList<String>()),getList());
		stampaCorsiFiltro(controller);
		metodoListener();

	}
	
	
	
	
	
	@Override
	public void metodoListener() {
		
		getList().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				
				//STAMPA INFORMAZIONI PROFESSORE
				Integer x;
				if(e.getValueIsAdjusting()) {	
					
					getNomeProfessoreField().setText(controller.getProfessoreSelezionato().getCognome() +" "+
												   controller.getProfessoreSelezionato().getNome());
					
				}
				
				//STAMPA INSEGNAMENTI PROFESSORE
				
				if(e.getValueIsAdjusting()) {
					getModelSetter().setModelListaInsegnamentiDelProfessore(controller.getInsegnamento(controller.getProfessoreSelezionato().getMatricolaProfessore()), getInsegnamentiList());
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
		getInsegnamentiFiltroList().setModel(model);
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
	
	
	public int estraiMatricolaProfessore(String string) {
		
		Integer id = Integer.valueOf(string.substring(string.indexOf('-')+2));

		
		return id.intValue();
	}
	
	public boolean acquisisciFiltroOrdinaCorsi() {
		if(getAscDescToggle().getText().equals("A-Z"))
			return true;
		else
			return false;
	}
	
	
	public void refreshProfessori() {
		
		corsiSelezionatiArrayList = new ArrayList<String>();
		
		acquisisciCorsiSelezionati(corsiSelezionatiArrayList,getInsegnamentiFiltroList());
		
		getModelSetter().setModelListaProfessori(controller.getProfessoriFiltrati(acquisisciFiltroOrdinaCorsi(),corsiSelezionatiArrayList), getList());
		
		corsiSelezionatiArrayList.clear();
		
	}
	
	@Override
	public void resetFiltri() {
		if(getAscDescToggle().isSelected()) {
			getAscDescToggle().doClick();
		}

	}
	
	//LISTENERS
	@Override
	public ActionListener listenerEliminaButton() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(getList().getSelectedIndex()!=-1) {
					
					int result = JOptionPane.showConfirmDialog(null, "Sei sicuro di voler eliminare il professore?\n Eliminerai anche le sue lezioni!",
												"Attenzione", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null);
					
					if(result == JOptionPane.OK_OPTION) {
						controller.eliminaProfessoreSelezionato(controller.getProfessoreSelezionato().getMatricolaProfessore());
						refreshProfessori();
				
						
					}
				}
			}
		};
	}
	
	@Override
	public ActionListener listenerNuovoButton() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new InserimentoProfessoreFrame(controller);
				refreshProfessori();
			}
		};
	}
	
	@Override
	public ActionListener listenerAscDescButton() {
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
				refreshProfessori();
			}
		};
	}
}