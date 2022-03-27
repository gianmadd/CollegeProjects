package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;

import Controller.Controller;
import Entita.AreaTematica;
import Entita.Corso;
import Entita.JCheckBoxList;

import java.awt.Cursor;
import javax.swing.border.LineBorder;

public class ModificaCorsoFrame extends PopolamentoCorsoFrame {

	private Controller controller;
	
	private JTextField modificaCorsoEsistenteLabel;
	private JButton modificaButton;
	private JButton annullaButton;
	private ArrayList listaDatiAcquisiti;
	private JCheckBoxList areeTematicheList;
	private JScrollPane areeTematicheScrollPane;

	private ArrayList<String> areeTematicheSelezionateList;
	
	
	public ModificaCorsoFrame(Controller ctrl, PannelloCorsi pannello) {
		
		controller = ctrl;
		
		
		
		listaDatiAcquisiti = new ArrayList();
		areeTematicheSelezionateList = new ArrayList<String>();
		
		setTitle("Modifica corso esistente");
		
		
		modificaCorsoEsistenteLabel = new JTextField();
		modificaCorsoEsistenteLabel.setEditable(false);
		modificaCorsoEsistenteLabel.setText("Modifica corso esistente");
		modificaCorsoEsistenteLabel.setForeground(panna);
		modificaCorsoEsistenteLabel.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 17));
		modificaCorsoEsistenteLabel.setColumns(10);
		modificaCorsoEsistenteLabel.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		modificaCorsoEsistenteLabel.setBackground(blu);
		modificaCorsoEsistenteLabel.setBounds(20, 18, 217, 27);
		getContentPanel().add(modificaCorsoEsistenteLabel);
		
		areeTematicheScrollPane = new JScrollPane();
		areeTematicheScrollPane.setBorder(new LineBorder(Color.WHITE, 3));
		areeTematicheScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		areeTematicheScrollPane.setBounds(20, 320, 229, 210);
		getContentPanel().add(areeTematicheScrollPane);
		
		
		areeTematicheList = new JCheckBoxList();
		areeTematicheList.setSelectionForeground(panna);
		areeTematicheList.setSelectionBackground(blu);
		areeTematicheList.setForeground(blu);
		areeTematicheList.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		areeTematicheList.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		areeTematicheList.setBackground(panna);
		areeTematicheList.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		areeTematicheScrollPane.setViewportView(areeTematicheList);
		
		
		modificaButton = new JButton("Modifica");
		modificaButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		modificaButton.addActionListener(listenerModificaButton());
		modificaButton.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));
		modificaButton.setForeground(blu);
		modificaButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		modificaButton.setBackground(panna);
		modificaButton.setBounds(391, 492, 108, 39);
		getContentPanel().add(modificaButton);
		
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
		annullaButton.setBounds(273, 492, 108, 39);
		getContentPanel().add(annullaButton);
		
		
		
		stampaAreeTematiche(controller);
		
		setVisible(true);
		
	}
	
	
		
	public boolean checkEsistenzaCorsoStessoNome(ArrayList<Corso> lista, JTextField textField ) {
			
			
		boolean cond = false;
			
		for(Corso c:lista) {
				if(textField.getText().equalsIgnoreCase(c.getNome()))
					cond = true;
		}
			
		return cond;
	}
	
	public ArrayList modificaCorso(ArrayList datiAcquisiti) {
		datiAcquisiti.add(getInserisciNomeField().getText().toLowerCase());
		datiAcquisiti.add(getInserisciDescrizioneField().getText().toLowerCase());
		datiAcquisiti.add(getMassimoIscrittiSlider().getValue());
		datiAcquisiti.add(getPercentualeMinimaSlider().getValue());
		return datiAcquisiti;
	}
	
	public void stampaAreeTematiche(Controller ctrl) {
		
		
		ArrayList<JCheckBox> arrayList = new ArrayList<JCheckBox>();
		
		for (AreaTematica areaTematica : ctrl.getAreaTematicaNonAssociata(ctrl.getCorsoSelezionato())){
			arrayList.add(new JCheckBox(areaTematica.getTema().toUpperCase()));	
		}
		
		DefaultListModel<JCheckBox> model = new DefaultListModel<JCheckBox>();
		model.addAll(arrayList);
		areeTematicheList.setModel(model);
	}
	
	
	public ArrayList<String> acquisisciAreeTematicheSelezionate(ArrayList<String> arrayList,JCheckBoxList checkBoxList){
		
		ListModel<JCheckBox> listModel = checkBoxList.getModel();
		
		for(int i=0; i<listModel.getSize();i++) {
			
			if(listModel.getElementAt(i).isSelected()) {
				arrayList.add(listModel.getElementAt(i).getText().toLowerCase());
			}
		}

		return arrayList;
		}
	
	
	
	
	
	
	public ArrayList getListaDatiAcquisiti() {
		return listaDatiAcquisiti;
	}
	
	
	
	public void setListaDatiAcquisiti(ArrayList listaDatiAcquisiti) {
		this.listaDatiAcquisiti = listaDatiAcquisiti;
	}



	public Controller getController() {
		return controller;
	}



	public void setController(Controller controller) {
		this.controller = controller;
	}
	

	
	//LISTENER
	
	public ActionListener listenerModificaButton() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				areeTematicheSelezionateList = acquisisciAreeTematicheSelezionate(areeTematicheSelezionateList, areeTematicheList);
				
				if(getInserisciNomeField().getText().isBlank()) {
					JOptionPane.showInternalMessageDialog(null, "Impossibile creare un corso senza nome.", getTitle(), JOptionPane.ERROR_MESSAGE);
				}
				else if(getInserisciDescrizioneField().getText().isBlank()) {
					JOptionPane.showInternalMessageDialog(null, "Impossibile creare un corso senza descrizione.", getTitle(), JOptionPane.ERROR_MESSAGE);
				}
				else if(getMassimoIscrittiSlider().getValue() <= 0){
					JOptionPane.showInternalMessageDialog(null, "Impossibile creare un corso senza partecipanti.", getTitle(), JOptionPane.ERROR_MESSAGE);
				}
				else if(getInserisciNomeField().getText().contains("'")  || getInserisciDescrizioneField().getText().contains("'")) {
					JOptionPane.showMessageDialog(null, "Nome e descrizione del corso non devono contenere apostrofi.", "Attenzione", JOptionPane.ERROR_MESSAGE);
				}
				else {
					listaDatiAcquisiti.clear();
					Corso temp = new Corso();
					controller.updateCorso(modificaCorso(getListaDatiAcquisiti()));
					temp.setNome(modificaCorso(getListaDatiAcquisiti()).get(0).toString());
					controller.insertAppartanenzaArea(controller.getIdCorsoInserito(temp), areeTematicheSelezionateList);
					setVisible(false);					
				}
			}
		};
	}

}
