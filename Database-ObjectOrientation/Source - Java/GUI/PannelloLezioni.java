package GUI;


import javax.swing.AbstractListModel;

import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import javax.swing.ListModel;
import javax.swing.DefaultListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Controller.Controller;
import Entita.JCheckBoxList;
import Entita.Lezione;
import Entita.ParoleChiave;
import Entita.ParoleLezione;

import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

import javax.swing.ListSelectionModel;

public class PannelloLezioni extends PannelloLezioniGrafica {

	private Controller controller;
	private ArrayList<String> paroleChiaveSelezionateArrayList;
	
	public PannelloLezioni(Controller ctrl) {
		
		
		controller = ctrl;
		getModelSetter().setModelListaLezioni(controller.getLezioniFiltrate(true, new ArrayList<String>(), new ArrayList<String>()),getList());
		stampaParoleChiaveFiltro(controller);
		metodoListener();
		
	}
	
	//METODI
	
	@Override
	public void metodoListener() {
		
		getList().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if(e.getValueIsAdjusting()) {		
					
					//STAMPA INFORMAZIONI LEZIONI

					getNomeDellaLezioneLabel().setText(controller.getLezioneSelezionata().getTitolo().toUpperCase());
					getDelCorsoField().setText(controller.getCorsoDaLezione().getNome());
					getDescrizioneField().setText(controller.getLezioneSelezionata().getDescrizione());
					getDataField().setText(controller.getLezioneSelezionata().getData().toString());
					getOraInizioField().setText(controller.getLezioneSelezionata().getOraInizio().toString());
					getOraFineField().setText(controller.getLezioneSelezionata().getOraFine().toString());
					getDurataField().setText(controller.getDurataLezione().toString());
					getProfessoreField().setText( controller.getProfessoreDaLezione().getCognome()
											+ " " + controller.getProfessoreDaLezione().getNome());
					
					
					//STAMPA PRESENZE
					
					getModelSetter().setModelListaPresenzeAllaLezione(controller.getPresenze(controller.getLezioneSelezionata().getTitolo()), getPresenzeList());
					
					//STAMPA PAROLE CHIAVE
					
					getModelSetter().setModelListaParoleChiaveDellaLezione(controller.getParoleChiaveLezione(controller.getLezioneSelezionata()), getParoleChiaveList());
				
				}
				

			}
		});
		
	}
	
	
	public void stampaParoleChiaveFiltro(Controller ctrl) {
		
		ArrayList<JCheckBox> arrayList = new ArrayList<JCheckBox>();
		
		for (ParoleChiave paroleChiave : ctrl.getParoleChiaveFiltrate(true)){
			arrayList.add(new JCheckBox(paroleChiave.getParola().toUpperCase()));		
		}
		
		DefaultListModel<JCheckBox> model = new DefaultListModel<JCheckBox>();
		model.addAll(arrayList);
		getParoleChiaveFiltroList().setModel(model);
	}
	
	public ArrayList<String> acquisisciParoleChiaveSelezionate(ArrayList<String> arrayList,JCheckBoxList checkBoxList){
		
		ListModel<JCheckBox> listModel = checkBoxList.getModel();
		
		for(int i=0; i<listModel.getSize();i++) {
			
			if(listModel.getElementAt(i).isSelected()) {
				arrayList.add(listModel.getElementAt(i).getText());
			}
		}

		return arrayList;
	}
	
	public boolean acquisisciFiltroOrdinaLezioni() {
		if(getAscDescToggle().getText().equals("Più recenti"))
			return true;
		else
			return false;
	}
	
	public ArrayList<String> acquisisciFiltroDate() {
		
		ArrayList<String> date = new ArrayList<String>();
		
		if(!getIgnoraDataRadioButton().isSelected()) {
			
			DateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
			String dataInizialeStringa = fmt.format(getSelezioneDataInizialeFiltroDateChooser().getJCalendar().getDate());
			String dataFinaleStringa = fmt.format(getSelezioneDataFinaleFiltroDateChooser().getJCalendar().getDate());
			
			date.add(dataInizialeStringa);
			date.add(dataFinaleStringa);
			
		}
		
		return date;
	}
	
	@Override
	public void resetFiltri() {
		if(getAscDescToggle().isSelected()) {
			getAscDescToggle().doClick();
		}
			
		getIgnoraDataRadioButton().setSelected(true);
	}
	
	
	public void refreshLezioni() {
		
		paroleChiaveSelezionateArrayList = new ArrayList<String>();
		
		acquisisciParoleChiaveSelezionate(paroleChiaveSelezionateArrayList,getParoleChiaveFiltroList());
		
		getModelSetter().setModelListaLezioni(controller.getLezioniFiltrate(acquisisciFiltroOrdinaLezioni(), acquisisciFiltroDate(), paroleChiaveSelezionateArrayList), getList());
		
		paroleChiaveSelezionateArrayList.clear();
		
		
	}
	
	//LISTENERS
	
	@Override
	public ActionListener listenerEliminaButton() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(getList().getSelectedIndex()!=-1) {
					
					int result = JOptionPane.showConfirmDialog(null, "Sei sicuro di voler eliminare la lezione?",
							"Attenzione", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null);
					
					if(result == JOptionPane.OK_OPTION) {
						controller.eliminaLezioneSelezionata(controller.getLezioneSelezionata().getTitolo());
						refreshLezioni();
					}
					
				}
			}
		};
	}
	
	@Override
	public ActionListener listenerModificaButton() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(getList().getSelectedIndex()!=-1) {
					new ModificaDataOraLezioneFrame(controller);
					refreshLezioni();
				}
				
			}
		};
	}
	
	@Override
	public ActionListener listenerAssociaParoleChiaveButton() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(getList().getSelectedIndex()!=-1) {
					new InserimentoParoleLezioneFrame(controller);
				}
			}
		};
	}
	
	@Override
	public ActionListener listenerAggiungiPresenzeButton() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(getList().getSelectedIndex()!=-1) {
					new InserimentoPartecipazioniFrame(controller);
				}
			}
		};
	}
	
	@Override
	public ActionListener listenerRefreshButton() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshLezioni();
			}
		};
	}
	
	@Override
	public ActionListener listenerAscDescToggle() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(getAscDescToggle().getText().equals("Più recenti")) {
					getAscDescToggle().setText("Meno recenti");
					
				}
				else {
					getAscDescToggle().setText("Più recenti");
				}
			}
		};
	}
}
