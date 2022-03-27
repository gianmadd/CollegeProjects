package GUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import Controller.Controller;
import Entita.AreaTematica;
import Entita.Corso;
import Entita.JCheckBoxList;
import Entita.Macroarea;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

public class InserimentoAreaTematicaFrame extends PopolamentoAreaTematicaFrame {
	
	private Controller controller;
	
	private JScrollPane macroareeScrollPane;
	private JList macroareeList;
	private JTextField inserisciAreaTematicaLabel;
	private JButton creaButton;
	private JButton annullaButton;
	private JLabel selezionaMacroareeLabel;
	
	private ArrayList<String> listaMacroareeSelezionate;
	
	
	public InserimentoAreaTematicaFrame(Controller ctrl) {
		
		controller = ctrl;
		
		listaMacroareeSelezionate = new ArrayList<String>();
		
		inserisciAreaTematicaLabel = new JTextField();
		inserisciAreaTematicaLabel.setEditable(false);
		inserisciAreaTematicaLabel.setText("Inserisci Area Tematica");
		inserisciAreaTematicaLabel.setForeground(panna);
		inserisciAreaTematicaLabel.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 17));
		inserisciAreaTematicaLabel.setColumns(10);
		inserisciAreaTematicaLabel.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		inserisciAreaTematicaLabel.setBackground(blu);
		inserisciAreaTematicaLabel.setBounds(20, 18, 219, 27);
		getContentPanel().add(inserisciAreaTematicaLabel);
		
		macroareeScrollPane = new JScrollPane();
		macroareeScrollPane.setBorder(new LineBorder(Color.WHITE, 3));
		macroareeScrollPane.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		macroareeScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		macroareeScrollPane.setBounds(27, 135, 220, 214);
		getContentPanel().add(macroareeScrollPane);
		
		macroareeList = new JList();
		macroareeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		macroareeList.setSelectionForeground(panna);
		macroareeList.setSelectionBackground(blu);
		macroareeList.setForeground(blu);
		macroareeList.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		macroareeList.setBackground(panna);
		macroareeList.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		macroareeScrollPane.setViewportView(macroareeList);
		macroareeList.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
		
		creaButton = new JButton("Crea");
		creaButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		creaButton.addActionListener(listenerCreaButton());
		creaButton.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));
		creaButton.setForeground(blu);
		creaButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		creaButton.setBackground(panna);
		creaButton.setBounds(391, 321, 108, 39);
		getContentPanel().add(creaButton);
		
		annullaButton = new JButton("Annulla");
		annullaButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		annullaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		annullaButton.setForeground(blu);
		annullaButton.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));
		annullaButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		annullaButton.setBackground(panna);
		annullaButton.setBounds(273, 321, 108, 39);
		getContentPanel().add(annullaButton);
		
		selezionaMacroareeLabel = new JLabel("Seleziona macroaree di appartenenza: ");
		selezionaMacroareeLabel.setForeground(panna);
		selezionaMacroareeLabel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 13));
		selezionaMacroareeLabel.setBounds(27, 111, 240, 13);
		getContentPanel().add(selezionaMacroareeLabel);
		
		setModelLista(controller.getMacroareeFiltrate(true), macroareeList);

		setVisible(true);
	}
	
	
	//METODI
	
	
	public void setModelLista(ArrayList<Macroarea> arrayList,JList list) {	
		list.setModel(new AbstractListModel() {
			public int getSize() {
				return arrayList.size();
			}
			public Object getElementAt(int index) {
				return arrayList.get(index).getNome().toUpperCase();
			}
		});
	}


	public ArrayList<String> acquisisciMacroareeSelezionate(ArrayList<String> arrayList,JCheckBoxList checkBoxList){
			
			ListModel<JCheckBox> listModel = checkBoxList.getModel();
			
			for(int i=0; i<listModel.getSize();i++) {
				
				if(listModel.getElementAt(i).isSelected()) {
					arrayList.add(listModel.getElementAt(i).getText());
				}
			}
			
	
			return arrayList;
		}
	
	public ArrayList<String> getListaMacroareeSelezionate() {
		return listaMacroareeSelezionate;
	}


	public void setListaMacroareeSelezionate(ArrayList<String> listaMacroareeSelezionate) {
		this.listaMacroareeSelezionate = listaMacroareeSelezionate;
	}
	
	
	
	
	//LISTENER
	public ActionListener listenerCreaButton() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(getInserisciAreaTematicaField().getText().isBlank()) {
					JOptionPane.showInternalMessageDialog(null, "Impossibile creare un'area tematica vuota.", getTitle(), JOptionPane.ERROR_MESSAGE);
				}
				else if(macroareeList.getSelectedIndex()==-1) {
					JOptionPane.showInternalMessageDialog(null, "Impossibile creare un'area tematica non contenuta in una macroarea.", getTitle(), JOptionPane.ERROR_MESSAGE);
				}
				else {
					controller.insertAreaTematica(getInserisciAreaTematicaField().getText(), macroareeList.getSelectedValue().toString());
					setVisible(false);
				}
			}
		};
	}
	
	
	
	
	
}
