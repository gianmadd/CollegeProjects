package GUI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Controller.Controller;
import Entita.AreaTematica;
import Entita.Corso;
import Entita.Lezione;
import Entita.Macroarea;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.border.BevelBorder;
import java.awt.Cursor;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.ListSelectionModel;

public class PannelloAreeTematiche extends PannelloAreeTematicheGrafica {
	
	private Controller controller;
	
	public PannelloAreeTematiche(Controller ctrl) {
		
		
		controller = ctrl;
		
		getModelSetter().setModelListaMacroaree(controller.getMacroareeFiltrate(true), getList());

		metodoListener();
		
	}
	
	
	//METODI
	
	public void metodoListener() {
		
		getList().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				//STAMPA AREE TEMATICHE DELLA MACROAREA
				if(e.getValueIsAdjusting()) {
					getModelSetter().setModelListaAreeTematiche(controller.getAreeTematicheRelativeMacroarea(), getAreeTematicheList());
				}
			}
		});
			
		
		getAreeTematicheList().addListSelectionListener(new ListSelectionListener() {			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting() && getList().getSelectedIndex() != -1){
					getModelSetter().setModelListaCorsiAppartenentiAreaTematica(controller.getCorsiAppartenentiAreaTematica(ricavaAreaTematicaSelezionata()), getCorsiAppartenentiAreaTematicaList());
				}
			}			
		});	
	}
	
	
	public void refreshMacroaree() {
		getModelSetter().setModelListaMacroaree(controller.getMacroareeFiltrate(acquisisciFiltroOrdinaMacroaree()), getList());
	}
	
	public void refreshAreeTematicheRelativeMacroarea() {
		getModelSetter().setModelListaAreeTematiche(controller.getAreeTematicheRelativeMacroarea(), getAreeTematicheList());
	}
	
	public void resetAreeTematicheRelativeMacroarea() {
		getModelSetter().setModelListaAreeTematiche(new ArrayList<AreaTematica>(), getAreeTematicheList());
	}
	
	public void resetListaCorsiAppartenentiAreaTematica() {
		getModelSetter().setModelListaCorsiAppartenentiAreaTematica(new ArrayList<Corso>(), getCorsiAppartenentiAreaTematicaList());
	}
	
	
	
	@Override
	public void resetFiltri() {
		if(getAscDescToggle().isSelected()) {
			getAscDescToggle().doClick();
		}

	}
	
	public boolean acquisisciFiltroOrdinaMacroaree() {
		if(getAscDescToggle().getText().equals("A-Z"))
			return true;
		else
			return false;
	}

	public String ricavaAreaTematicaSelezionata() {
		return controller.getAreeTematicheRelativeMacroarea().get(getAreeTematicheList().getSelectedIndex()).getTema();
	}
	

	//LISTENER 
	
	@Override
	public ActionListener listenerModificaAreaTematicaButton() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(getAreeTematicheList().getSelectedIndex()!=-1) {
					new ModificaAreaTematicaFrame(controller);
					refreshAreeTematicheRelativeMacroarea();
					resetListaCorsiAppartenentiAreaTematica();
				}
			}
		};
	}
	
	
	@Override
	public ActionListener listenerEliminaAreaTematicaButton() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(getAreeTematicheList().getSelectedIndex()!=-1) {
					int result = JOptionPane.showConfirmDialog(null, "Sei sicuro di voler eliminare l'area tematica?", "Attenzione", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null);
					if(result == JOptionPane.OK_OPTION) {
						controller.eliminaAreaTematicaSelezionata(controller.getAreeTematicheRelativeMacroarea().get(getAreeTematicheList().getSelectedIndex()).getTema());		
						refreshAreeTematicheRelativeMacroarea();
						resetListaCorsiAppartenentiAreaTematica();
					}	
				}
			}
		};
	}
	
	
	@Override
	public ActionListener listenerModificaMacroareaButton() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(getList().getSelectedIndex()!=-1) {
					new ModificaMacroareaFrame(controller);
					refreshMacroaree();
					resetAreeTematicheRelativeMacroarea();
					resetListaCorsiAppartenentiAreaTematica();
				}
			}
		};
	}
	
	
	@Override
	public ActionListener listenerEliminaMacroareaButton() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(getList().getSelectedIndex()!=-1) {
			
					int result = JOptionPane.showConfirmDialog(null, "Sei sicuro di voler eliminare la macroarea?\n Eliminerai tutte le aree tematiche associate!",	"Attenzione", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null);
					
					if(result == JOptionPane.OK_OPTION) {
						controller.eliminaMacroareaSelezionata(controller.getMacroareaSelezionata().getNome());
						refreshMacroaree();	
						resetAreeTematicheRelativeMacroarea();
						resetListaCorsiAppartenentiAreaTematica();
					}
				}
			}
		};
	}
	
	@Override
	public ActionListener listenerNuovaMacroareaButton() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new InserimentoMacroareaFrame(controller);
				refreshMacroaree();
				resetAreeTematicheRelativeMacroarea();
				resetListaCorsiAppartenentiAreaTematica();
			}
		};
	}
	
	@Override
	public ActionListener listenerNuovaAreaTematicaButton() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new InserimentoAreaTematicaFrame(controller);
				resetListaCorsiAppartenentiAreaTematica();
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
				refreshMacroaree();
				resetAreeTematicheRelativeMacroarea();
				resetListaCorsiAppartenentiAreaTematica();
			}
		};
	}
	
}
