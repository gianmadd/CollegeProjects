package GUI;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.border.MatteBorder;

import Controller.Controller;
import Entita.AreaTematica;
import Entita.Corso;
import Entita.JCheckBoxList;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Cursor;

public class InserimentoCorsoFrame extends PopolamentoCorsoFrame {

	private Controller controller;
	
	private JTextField creaNuovoCorsoLabel;
	private JButton creaButton;
	private JButton annullaButton;
	private ArrayList<?> listaDatiAcquisiti;
	private JCheckBoxList areeTematiche;
	private JScrollPane areeTematicheScrollPane;
	
	private ArrayList<String> areeTematicheSelezionateList;
	
	
	public InserimentoCorsoFrame(Controller ctrl) {
		
		controller = ctrl;
		
		listaDatiAcquisiti = new ArrayList<>();
		areeTematicheSelezionateList = new ArrayList<String>();
		
		setTitle("Creazione corso");
				
		creaNuovoCorsoLabel = new JTextField();
		creaNuovoCorsoLabel.setEditable(false);
		creaNuovoCorsoLabel.setText("Crea nuovo corso");
		creaNuovoCorsoLabel.setForeground(panna);
		creaNuovoCorsoLabel.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 17));
		creaNuovoCorsoLabel.setColumns(10);
		creaNuovoCorsoLabel.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		creaNuovoCorsoLabel.setBackground(blu);
		creaNuovoCorsoLabel.setBounds(20, 18, 151, 27);
		getContentPanel().add(creaNuovoCorsoLabel);
		
		areeTematicheScrollPane = new JScrollPane();
		areeTematicheScrollPane.setBorder(new LineBorder(Color.WHITE, 3));
		areeTematicheScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		areeTematicheScrollPane.setBounds(20, 320, 229, 210);
		getContentPanel().add(areeTematicheScrollPane);
		
		
		areeTematiche = new JCheckBoxList();
		areeTematiche.setSelectionForeground(panna);
		areeTematiche.setSelectionBackground(blu);
		areeTematiche.setForeground(blu);
		areeTematiche.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		areeTematiche.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		areeTematiche.setBackground(panna);
		areeTematiche.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		areeTematicheScrollPane.setViewportView(areeTematiche);
		
		
		creaButton = new JButton("Crea");
		creaButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		creaButton.addActionListener(listenerCreaButton());
		creaButton.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));
		creaButton.setForeground(blu);
		creaButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		creaButton.setBackground(panna);
		creaButton.setBounds(391, 492, 108, 39);
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
	
	public ArrayList inserimentoCorso(ArrayList datiAcquisiti) {
		
		datiAcquisiti.add(getInserisciNomeField().getText().toLowerCase());
		datiAcquisiti.add(getInserisciDescrizioneField().getText().toLowerCase());
		datiAcquisiti.add(getMassimoIscrittiSlider().getValue());
		datiAcquisiti.add(getPercentualeMinimaSlider().getValue());
		return datiAcquisiti;
	}
	
	
	public void stampaAreeTematiche(Controller ctrl) {
		
		
		ArrayList<JCheckBox> arrayList = new ArrayList<JCheckBox>();
		
		for (AreaTematica areaTematica : ctrl.getAreeTematiche()){
			arrayList.add(new JCheckBox(areaTematica.getTema().toUpperCase()));	
		}
		
		DefaultListModel<JCheckBox> model = new DefaultListModel<JCheckBox>();
		model.addAll(arrayList);
		areeTematiche.setModel(model);
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
	


	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}


	public ArrayList getListaDatiAcquisiti() {
		return listaDatiAcquisiti;
	}


	public void setListaDatiAcquisiti(ArrayList listaDatiAcquisiti) {
		this.listaDatiAcquisiti = listaDatiAcquisiti;
	}
	
	
	//LISTENER 
	
	public ActionListener listenerCreaButton() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				areeTematicheSelezionateList = acquisisciAreeTematicheSelezionate(areeTematicheSelezionateList, areeTematiche);
				
				if(getInserisciNomeField().getText().isBlank()) {
					JOptionPane.showInternalMessageDialog(null, "Impossibile creare un corso senza nome.", getTitle(), JOptionPane.ERROR_MESSAGE);
				}
				else if(checkEsistenzaCorsoStessoNome(controller.getCorsiFiltrati(true, 1, new ArrayList<String>()), getInserisciNomeField())) {
					JOptionPane.showInternalMessageDialog(null, "Nome del corso già esistente.", getTitle(), JOptionPane.ERROR_MESSAGE);
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
				else if(areeTematicheSelezionateList.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Devi inserire almeno un'area tematica.", "Attenzione", JOptionPane.ERROR_MESSAGE);
				}
				else {
					listaDatiAcquisiti.clear();
					Corso temp = new Corso();
					controller.insertCorso(inserimentoCorso(getListaDatiAcquisiti()));
					temp.setNome(inserimentoCorso(getListaDatiAcquisiti()).get(0).toString());
					controller.insertAppartanenzaArea(controller.getIdCorsoInserito(temp), areeTematicheSelezionateList);
					setVisible(false);
				}
			}
		};
	}
	
	
	
	
	
	
	
	
	
	
}
