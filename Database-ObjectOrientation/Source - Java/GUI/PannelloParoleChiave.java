package GUI;


import javax.swing.JList;
import javax.swing.JOptionPane;

import Controller.Controller;
import Entita.ParoleChiave;

import java.util.ArrayList;
import javax.swing.AbstractListModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class PannelloParoleChiave extends PannelloParoleChiaveGrafica {

	private Controller controller;
	
	public PannelloParoleChiave(Controller ctrl) {
		
		controller = ctrl;
	
		getModelSetter().setModelListaParoleChiave(controller.getParoleChiaveFiltrate(true),getList());
	
	}

	
	//METODI
	
	public String ricavaParolaChiaveSelezionata() {
		return controller.getParoleChiaveFiltrate(true).get(getList().getSelectedIndex()).getParola();
	}
	
	public boolean acquisisciFiltroOrdinaParoleChiave() {
		if(getAscDescToggle().getText().equals("A-Z"))
			return true;
		else
			return false;
	}
	
	public void refreshParoleChiave() {
		
		getModelSetter().setModelListaParoleChiave(controller.getParoleChiaveFiltrate(acquisisciFiltroOrdinaParoleChiave()), getList());
	}
	
	@Override
	public void metodoListener() {}
	
	@Override
	public void resetFiltri() {
		if(getAscDescToggle().isSelected()) {
			getAscDescToggle().doClick();
		}

	}
	

	//LISTENERS
	
	@Override
	public ActionListener listenerNuovaButton() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new InserimentoParoleChiaveFrame(controller);
				refreshParoleChiave();
			}
		};
	}
	
	@Override
	public ActionListener listenerModificaButton() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(getList().getSelectedIndex()!=-1) {
					new ModificaParoleChiaveFrame(controller);
					refreshParoleChiave();
				}
			}
		};
	}
	
	@Override
	public ActionListener listenerEliminaButton() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(getList().getSelectedIndex()!=-1) {
					
					int result = JOptionPane.showConfirmDialog(null, "Sei sicuro di voler eliminare la parola chiave?\n Eliminerai anche le corrispondenze con le lezioni!",
												"Attenzione", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null);
					
					if(result == JOptionPane.OK_OPTION) {
						controller.eliminaParolaChiaveSelezionata(controller.getParolaChiaveSelezionata().getParola());
						refreshParoleChiave();

					}
					
					
				}
			}
		};
	}
	
	@Override
	public ActionListener listenerAscDescToggle() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(getAscDescToggle().getText().equals("A-Z")) 
					getAscDescToggle().setText("Z-A");
				else 
					getAscDescToggle().setText("A-Z");
			}
		};
	}
	
	@Override
	public ActionListener listenerRefreshButton() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshParoleChiave();
			}
		};
	}
	
}
