package GUI;

import java.awt.Color;
import java.awt.Font;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.border.MatteBorder;

import Controller.Controller;
import Entita.JCheckBoxList;
import Entita.ParoleChiave;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class InserimentoParoleLezioneFrame extends PopolamentoFrame {

	private Controller controller;
	
	private JCheckBoxList paroleChiaveCheckBoxList;
	private JTextField inserisciParoleLezioneLabel;
	private JScrollPane paroleChiaveScrollPane;
	private JLabel paroleChiaveLabel;
	private JButton annullaButton;
	private JButton aggiungiButton; 
	
	private ArrayList<String> listaParoleChiaveSelezionate;
	
	
	public InserimentoParoleLezioneFrame(Controller ctrl) {
		
		controller = ctrl;
		
		listaParoleChiaveSelezionate = new ArrayList<String>();
		
		inserisciParoleLezioneLabel = new JTextField();
		inserisciParoleLezioneLabel.setEditable(false);
		inserisciParoleLezioneLabel.setText("Aggiungi parole chiave alla lezione");
		inserisciParoleLezioneLabel.setForeground(panna);
		inserisciParoleLezioneLabel.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 17));
		inserisciParoleLezioneLabel.setColumns(10);
		inserisciParoleLezioneLabel.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		inserisciParoleLezioneLabel.setBackground(blu);
		inserisciParoleLezioneLabel.setBounds(20, 18, 310, 27);
		getContentPanel().add(inserisciParoleLezioneLabel);
		
		paroleChiaveScrollPane = new JScrollPane();
		paroleChiaveScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		paroleChiaveScrollPane.setBorder(new LineBorder(new Color(255, 255, 255), 3));
		paroleChiaveScrollPane.setBounds(41, 122, 200, 212);
		getContentPanel().add(paroleChiaveScrollPane);
		
		paroleChiaveCheckBoxList = new JCheckBoxList();
		paroleChiaveCheckBoxList.setSelectionForeground(blu);
		paroleChiaveCheckBoxList.setSelectionBackground(panna);
		paroleChiaveCheckBoxList.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		paroleChiaveCheckBoxList.setForeground(panna);
		paroleChiaveCheckBoxList.setBackground(blu);
		paroleChiaveCheckBoxList.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		paroleChiaveScrollPane.setViewportView(paroleChiaveCheckBoxList);
		
		paroleChiaveLabel = new JLabel("Parole Chiave:");
		paroleChiaveLabel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		paroleChiaveLabel.setForeground(panna);
		paroleChiaveLabel.setBounds(41, 91, 99, 14);
		getContentPanel().add(paroleChiaveLabel);
		
		annullaButton = new JButton("Annulla");
		annullaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		annullaButton.setForeground(blu);
		annullaButton.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));
		annullaButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		annullaButton.setBackground(panna);
		annullaButton.setBounds(288, 305, 108, 39);
		getContentPanel().add(annullaButton);
		
		aggiungiButton = new JButton("Aggiungi");
		aggiungiButton.addActionListener(listenerAggiungiButton());
		aggiungiButton.setForeground(blu);
		aggiungiButton.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));
		aggiungiButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		aggiungiButton.setBackground(panna);
		aggiungiButton.setBounds(406, 305, 108, 39);
		getContentPanel().add(aggiungiButton);
		
		
		stampaParoleChiaveNonAssociateLista(controller);
		
		
		
		
		setVisible(true);
	}
	
	public void stampaParoleChiaveNonAssociateLista(Controller ctrl) {
		
		
		ArrayList<JCheckBox> arrayList = new ArrayList<JCheckBox>();
		
		for (ParoleChiave paroleChiave : ctrl.getParoleChiaveNonAssociate(ctrl.getLezioneSelezionata())){
			arrayList.add(new JCheckBox(paroleChiave.getParola().toUpperCase()));
		}
		
		DefaultListModel<JCheckBox> model = new DefaultListModel<JCheckBox>();
		model.addAll(arrayList);
		paroleChiaveCheckBoxList.setModel(model);
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

	public ArrayList<String> getListaParoleChiaveSelezionate() {
		return listaParoleChiaveSelezionate;
	}

	public void setListaParoleChiaveSelezionate(ArrayList<String> listaParoleChiaveSelezionate) {
		this.listaParoleChiaveSelezionate = listaParoleChiaveSelezionate;
	}
	
	//LISTENER
	
	public ActionListener listenerAggiungiButton() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 if(acquisisciParoleChiaveSelezionate(getListaParoleChiaveSelezionate(), paroleChiaveCheckBoxList).isEmpty()) {
					JOptionPane.showInternalMessageDialog(null, "Impossibile completare l'operazione senza aver scelto almeno una parola chiave", getTitle(), JOptionPane.ERROR_MESSAGE);
				}
				else {
					controller.insertParoleLezione(controller.getLezioneSelezionata(), getListaParoleChiaveSelezionate());
					setVisible(false);
					//REFRESH LISTA DEI NOMI DEI CORSI SECONDO I FILTRI ATTUALI
				}
			}
		};
	}
	
}
