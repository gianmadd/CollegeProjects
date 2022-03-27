package GUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;

import Controller.Controller;
import Entita.JCheckBoxList;
import Entita.Studente;

public class InserimentoPartecipazioniFrame extends PopolamentoFrame {
	
	private Controller controller;
	
	private JCheckBoxList studentiNonPartecipantiList;
	private JTextField inserisciPartecipazioneLabel;
	private JScrollPane studentiNonPartecipantiScrollPane;
	private JLabel selezionaStudentiLabel;
	private JButton aggiungiPresenzaButton;
	private JButton annullaButton;
	
	private ArrayList<String> studentiSelezionatiList;
	
	
	public InserimentoPartecipazioniFrame(Controller ctrl) {

		controller = ctrl;
		
		studentiSelezionatiList = new ArrayList<String>();
		
		setTitle("Aggiungi presenze alla lezione");
		
		inserisciPartecipazioneLabel = new JTextField();
		inserisciPartecipazioneLabel.setEditable(false);
		inserisciPartecipazioneLabel.setText("Aggiungi partecipazioni alla lezione");
		inserisciPartecipazioneLabel.setForeground(panna);
		inserisciPartecipazioneLabel.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 17));
		inserisciPartecipazioneLabel.setColumns(10);
		inserisciPartecipazioneLabel.setBorder(new MatteBorder(0, 0, 2, 0, (Color) panna));
		inserisciPartecipazioneLabel.setBackground(blu);
		inserisciPartecipazioneLabel.setBounds(20, 18, 315, 27);
		getContentPanel().add(inserisciPartecipazioneLabel);
		
		
		
		studentiNonPartecipantiScrollPane = new JScrollPane();
		studentiNonPartecipantiScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		studentiNonPartecipantiScrollPane.setBounds(31, 97, 217, 230);
		getContentPanel().add(studentiNonPartecipantiScrollPane);
		
		studentiNonPartecipantiList = new JCheckBoxList();
		studentiNonPartecipantiList.setSelectionForeground(blu);
		studentiNonPartecipantiList.setSelectionBackground(panna);
		studentiNonPartecipantiList.setForeground(panna);
		studentiNonPartecipantiList.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		studentiNonPartecipantiList.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		studentiNonPartecipantiList.setBackground(blu);
		studentiNonPartecipantiList.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		studentiNonPartecipantiScrollPane.setViewportView(studentiNonPartecipantiList);
		
		selezionaStudentiLabel = new JLabel("Seleziona studenti registrare :");
		selezionaStudentiLabel.setForeground(panna);
		selezionaStudentiLabel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		selezionaStudentiLabel.setBounds(30, 72, 217, 14);
		getContentPanel().add(selezionaStudentiLabel);

		stampaStudentiNonPartecipantiLista(controller);
		
		aggiungiPresenzaButton = new JButton("Aggiungi");
		aggiungiPresenzaButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		aggiungiPresenzaButton.addActionListener(listenerAggiungiPresenzaButton());
		aggiungiPresenzaButton.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));
		aggiungiPresenzaButton.setForeground(blu);
		aggiungiPresenzaButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		aggiungiPresenzaButton.setBackground(panna);
		aggiungiPresenzaButton.setBounds(391, 321, 108, 39);
		getContentPanel().add(aggiungiPresenzaButton);
		
		
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
		

		setVisible(true);
	}
	
	public void stampaStudentiNonPartecipantiLista(Controller ctrl) {
		
		ArrayList<JCheckBox> arrayList = new ArrayList<JCheckBox>();
		
		for (Studente studente : ctrl.getStudentiNonPartecipanti(ctrl.getLezioneSelezionata())){
			arrayList.add(new JCheckBox(studente.getCognome().toUpperCase()+" "+studente.getNome().toUpperCase()+" - "+studente.getMatricola()));		
		}
		
		DefaultListModel<JCheckBox> model = new DefaultListModel<JCheckBox>();
		model.addAll(arrayList);
		studentiNonPartecipantiList.setModel(model);
	}
	
	public ArrayList<String> acquisisciStudentiNonPartecipantiSelezionati(ArrayList<String> arrayList,JCheckBoxList checkBoxList){
		
		ListModel<JCheckBox> listModel = checkBoxList.getModel();
		
		for(int i=0; i<listModel.getSize();i++) {
			
			if(listModel.getElementAt(i).isSelected()) {
				arrayList.add(listModel.getElementAt(i).getText());
			}
		}

		return arrayList;
		}
	
	
	//LISTENER
	
	public ActionListener listenerAggiungiPresenzaButton() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				studentiSelezionatiList.clear();
				studentiSelezionatiList = acquisisciStudentiNonPartecipantiSelezionati(studentiSelezionatiList, studentiNonPartecipantiList);
				if(!studentiSelezionatiList.isEmpty()) {
					controller.insertPartecipazione(controller.getLezioneSelezionata(),studentiSelezionatiList);
					setVisible(false);
				}
				else {	
					JOptionPane.showInternalMessageDialog(null, "Devi inserire almeno uno studente da registrare", getTitle(), JOptionPane.ERROR_MESSAGE);
				}
			}
		};
	}
	
}
