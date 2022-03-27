package GUI;

import java.awt.Color;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

import javax.swing.ListModel;
import javax.swing.table.DefaultTableModel;

import Controller.Controller;
import Entita.AreaTematica;
import Entita.Corso;
import Entita.JCheckBoxList;
import Entita.Lezione;
import Entita.ModelSetter;

import javax.swing.ListSelectionModel;

public class PannelloCorsi extends PannelloCorsiGrafica {

	private Controller controller;
	private PannelloCorsi pannelloCorsi;
	private ArrayList<String> areeTematicheSelezionateArrayList;
	
	public PannelloCorsi(Controller ctrl) {
		

		

		controller = ctrl;
		getModelSetter().setModelListaCorsi(controller.getCorsiFiltrati(true, 1, new ArrayList<String>()), getList());
		stampaAreeTematiche(controller);
		areeTematicheSelezionateArrayList = new ArrayList<String>();
		metodoListener();
	}
	
	//METODI

	@Override
	public void metodoListener() {
		
		getList().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				
				
				//STAMPA INFORMAZIONI CORSO
				Integer x;
				if(e.getValueIsAdjusting()) {		
					getNomeCorsoSelezionato().setText(controller.getCorsoSelezionato().getNome().toUpperCase());
					getDescrizioneCorsoSelezionato().setText(controller.getCorsoSelezionato().getDescrizione());
					x = Integer.valueOf(controller.getCorsoSelezionato().getMaxPartecipanti());
					getNumMaxPartecipantiCorsoSelezionato().setText(x.toString());
					x = Integer.valueOf(controller.getCorsoSelezionato().getPercentualeMinimaPresenza());
					getPercMinSuperamentoCorsoSelezionato().setText(x.toString());
					
					if(controller.getCorsoSelezionato().isTerminato()) {
						getStatoCorsoSelezionato().setText("  Terminato.");
						getLedCorsoTerminato().setBackground(new Color(220,20,60));
					}
					else
					{
						getStatoCorsoSelezionato().setText("    In corso.");
						getLedCorsoTerminato().setBackground(new Color(0,190,0));
					}
				}
				
				
				//STAMPA ISCRIZIONI/LEZIONI CORSO
				if(!getToggleListaComposizione().isSelected()) {
					if(e.getValueIsAdjusting()) {
						getModelSetter().setModelListaIscrizioniAlCorso(controller.getIscrizioniCorso(controller.getCorsoSelezionato().getNome()), getComposizioneCorsoList());
					}
				}
				else {
					if(e.getValueIsAdjusting()) {
						getModelSetter().setModelListaLezioniDelCorso(controller.getLezioniAssociateAlCorso(), getComposizioneCorsoList());
					}
				}
				
				
				//RIEMPIMENTO DEL PANNELLO STATISTICHE
				if(e.getValueIsAdjusting()) {
					
					getStatisticheTable().setModel(new DefaultTableModel(
							controller.getStatisticheCorso(controller.getCorsoSelezionato().getNome()),
							new String[] {"Lezione", "Presenze"}
						));
					
					Integer y = controller.getMediaPresenze(controller.getCorsoSelezionato().getNome());
					getNumMedioPartecipantiCorsoSelezionato().setText(" " +y.toString());
					getMediaRiempimentoProgressBar().setValue(controller.getPercentualeRiempimentoMedia(controller.getCorsoSelezionato().getNome()));
					
				}
				
			}
		});
	}
	
	
	public void stampaAreeTematiche(Controller ctrl) {
		
		ArrayList<JCheckBox> arrayList = new ArrayList<JCheckBox>();
		
		for (AreaTematica areaTematica : ctrl.getAreeTematiche()){
			arrayList.add(new JCheckBox(areaTematica.getTema().toUpperCase()));		
		}
		
		DefaultListModel<JCheckBox> model = new DefaultListModel<JCheckBox>();
		model.addAll(arrayList);
		getAreeTematicheFiltriList().setModel(model);
	}
	
	
	
	public ArrayList<String> acquisisciAreeTematicheSelezionate(ArrayList<String> arrayList,JCheckBoxList checkBoxList){
		
		ListModel<JCheckBox> listModel = checkBoxList.getModel();
		
		for(int i=0; i<listModel.getSize();i++) {
			
			if(listModel.getElementAt(i).isSelected()) {
				arrayList.add(listModel.getElementAt(i).getText());
			}
		}

		return arrayList;
		}
		
	public boolean acquisisciFiltroOrdinaCorsi() {
		if(getAscDescToggle().getText().equals("A-Z"))
			return true;
		else
			return false;
	}
	
	public int acquisisciFiltroStatoCorso() {
		if(getIgnoraStatoRadioButton().isSelected())
			return 1;
		else if(getInCorsoStatoRadioButton().isSelected())
			return 2;
		else 
			return 3;
	}
	
	public void refreshCorsi() {
		
		areeTematicheSelezionateArrayList = new ArrayList<String>();
		
		acquisisciAreeTematicheSelezionate(areeTematicheSelezionateArrayList,getAreeTematicheFiltriList());
		
		getModelSetter().setModelListaCorsi(controller.getCorsiFiltrati(acquisisciFiltroOrdinaCorsi(), acquisisciFiltroStatoCorso() ,areeTematicheSelezionateArrayList), getList());
		
		areeTematicheSelezionateArrayList.clear();
		
	}
	
	
	@Override
	public void resetFiltri() {
		if(getAscDescToggle().isSelected()) {
			getAscDescToggle().doClick();
		}
			
		getIgnoraStatoRadioButton().setSelected(true);
		getInCorsoStatoRadioButton().setSelected(false);
		getTerminatoStatoRadioButton().setSelected(false);
	}
	
	
	public String ricavaCorsoSelezionato() {
		return controller.getCorsoSelezionato().getNome();
	}

	
	
	//LISTENERS
	
	@Override
	public ActionListener listenerTerminaButton() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(getList().getSelectedIndex()!=-1) {
					
					if(controller.getCorsoSelezionato().isTerminato()) 
						JOptionPane.showMessageDialog(null, "Il corso è già terminato.");
					else {
					
						Object[] options = {"OK","Annulla"};
						int result = JOptionPane.showOptionDialog(null, "Questa azione non può essere annullata, confermare?", "ATTENZIONE!", 
								JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE,null, options, options);
				
						if(result == JOptionPane.OK_OPTION ) {	
							controller.setTerminato(controller.getCorsoSelezionato().getNome());
							refreshCorsi();
						}
					}
				}
			}
		};
	}
	
	@Override
	public ActionListener listenerToggleListaComposizione() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!getToggleListaComposizione().isSelected()) {
					getToggleListaComposizione().setText("Studenti Iscritti");
					getToggleAddButton().setText("Iscrivi Studenti");
					if(!getList().isSelectionEmpty())
						getModelSetter().setModelListaIscrizioniAlCorso(controller.getIscrizioniCorso(controller.getCorsoSelezionato().getNome()), getComposizioneCorsoList());				
				}
				else {
					getToggleListaComposizione().setText("Lezioni del Corso");
					getToggleAddButton().setText("Aggiungi Lezione");
					if(!getList().isSelectionEmpty())
						getModelSetter().setModelListaLezioniDelCorso(controller.getLezioniAssociateAlCorso(), getComposizioneCorsoList());						
				}
			}
		};
	}
	
	@Override
	public ActionListener listenerToggleAddButton() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (getToggleAddButton().getText().equals("Iscrivi Studenti")) {
					//ISCRIVERE STUDENTI
					if(getList().getSelectedIndex()!=-1) {
						new InserimentoIscrizioneFrame(controller);
					}
					
					
				}
				else if (getToggleAddButton().getText().equals("Aggiungi Lezione")) {
					//AGGIUNGERE UNA LEZIONE
					if(getList().getSelectedIndex()!=-1) {
						new InserimentoLezioneFrame(controller);
					}
			
				}
			}
		};
	}
	
	@Override
	public ActionListener listenerEliminaButton() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(getList().getSelectedIndex()!=-1) {
					
					int result = JOptionPane.showConfirmDialog(null, "Sei sicuro di voler eliminare il corso?\n Eliminerai anche tutte le sue lezioni.",
												"Attenzione", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null);
					
					if(result == JOptionPane.OK_OPTION) {
						controller.eliminaCorsoSelezionato(controller.getCorsoSelezionato().getNome());
						refreshCorsi();
					}
				
				}
			}
		};
	}
	
	@Override
	public ActionListener listenerModificaButton(){
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(getList().getSelectedIndex()!=-1) {
					new ModificaCorsoFrame(controller,pannelloCorsi);
					refreshCorsi();
				}
			}
		};
	}
	
	@Override
	public ActionListener listenerNuovoButton() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new InserimentoCorsoFrame(controller);
				refreshCorsi();
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
	public ActionListener listenerIgnoraStatoRadioButton() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(getIgnoraStatoRadioButton().isSelected()) {
					getInCorsoStatoRadioButton().setSelected(false);
					getTerminatoStatoRadioButton().setSelected(false);
				}
				else {
					getIgnoraStatoRadioButton().setSelected(true);
				}
			}
		};
	}
	
	@Override
	public ActionListener listenerInCorsoStatoRadioButton() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(getInCorsoStatoRadioButton().isSelected()) {
					getIgnoraStatoRadioButton().setSelected(false);
					getTerminatoStatoRadioButton().setSelected(false);
				}
				if(!getInCorsoStatoRadioButton().isSelected() && !getTerminatoStatoRadioButton().isSelected())
					getIgnoraStatoRadioButton().setSelected(true);
			}
		};
	}
	
	@Override
	public ActionListener listenerTerminatoStatoRadioButton() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(getTerminatoStatoRadioButton().isSelected()) {
					getIgnoraStatoRadioButton().setSelected(false);
					getInCorsoStatoRadioButton().setSelected(false);
				}
				if(!getInCorsoStatoRadioButton().isSelected() && !getTerminatoStatoRadioButton().isSelected())
					getIgnoraStatoRadioButton().setSelected(true);
			}
		};
	}
	
	@Override
	public ActionListener listenerRefreshButton() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshCorsi();
			}
		};
	}
	
}
