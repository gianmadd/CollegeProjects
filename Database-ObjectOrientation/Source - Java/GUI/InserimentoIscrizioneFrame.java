package GUI;

import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import Controller.Controller;
import Entita.JCheckBoxList;
import Entita.Studente;

import java.awt.Color;
import javax.swing.border.BevelBorder;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class InserimentoIscrizioneFrame extends PopolamentoFrame {

	private Controller controller; 
	
	private JCheckBoxList studentiNonIscrittiList;
	private JTextField creaNuovoCorsoLabel;
	private JScrollPane studentiNonIscrittiScrollPane;
	private JLabel selezionaStudentiLabel;
	private JButton iscriviButton;
	private JButton annullaButton;
	
	private ArrayList<String> studentiSelezionatiList;
	
	
	public InserimentoIscrizioneFrame(Controller ctrl) {

		controller = ctrl;
		
		setTitle("Iscrivi studente");
		
		studentiSelezionatiList = new ArrayList<String>();
		
		creaNuovoCorsoLabel = new JTextField();
		creaNuovoCorsoLabel.setEditable(false);
		creaNuovoCorsoLabel.setText("Iscrivi studenti");
		creaNuovoCorsoLabel.setForeground(panna);
		creaNuovoCorsoLabel.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 17));
		creaNuovoCorsoLabel.setColumns(10);
		creaNuovoCorsoLabel.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		creaNuovoCorsoLabel.setBackground(blu);
		creaNuovoCorsoLabel.setBounds(20, 18, 151, 27);
		getContentPanel().add(creaNuovoCorsoLabel);
		
		studentiNonIscrittiScrollPane = new JScrollPane();
		studentiNonIscrittiScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		studentiNonIscrittiScrollPane.setBorder(new LineBorder(Color.WHITE, 3));
		studentiNonIscrittiScrollPane.setBounds(31, 97, 217, 230);
		getContentPanel().add(studentiNonIscrittiScrollPane);
		studentiNonIscrittiScrollPane.setViewportView(studentiNonIscrittiList);
		
		studentiNonIscrittiList = new JCheckBoxList();
		studentiNonIscrittiList.setSelectionForeground(blu);
		studentiNonIscrittiList.setSelectionBackground(new Color(255, 255, 255));
		studentiNonIscrittiList.setForeground(new Color(255, 255, 255));
		studentiNonIscrittiList.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		studentiNonIscrittiList.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		studentiNonIscrittiList.setBackground(blu);
		studentiNonIscrittiList.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		studentiNonIscrittiScrollPane.setViewportView(studentiNonIscrittiList);
		
		selezionaStudentiLabel = new JLabel("Seleziona studenti da iscrivere:");
		selezionaStudentiLabel.setForeground(new Color(255, 255, 255));
		selezionaStudentiLabel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		selezionaStudentiLabel.setBounds(30, 72, 217, 14);
		getContentPanel().add(selezionaStudentiLabel);
		
		stampaStudentiNonIscrittiLista(controller);
		
		iscriviButton = new JButton("Iscrivi");
		iscriviButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		iscriviButton.addActionListener(listenerIscriviButton());
		iscriviButton.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));
		iscriviButton.setForeground(blu);
		iscriviButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		iscriviButton.setBackground(panna);
		iscriviButton.setBounds(391, 321, 108, 39);
		getContentPanel().add(iscriviButton);
		

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
	
	
	public void stampaStudentiNonIscrittiLista(Controller ctrl) {
		
		
		ArrayList<JCheckBox> arrayList = new ArrayList<JCheckBox>();
		
		for (Studente studente : ctrl.getStudentiNonIscritti(ctrl.getCorsoSelezionato().getNome())){
			arrayList.add(new JCheckBox(studente.getCognome().toUpperCase()+" "+studente.getNome().toUpperCase()+" - "+studente.getMatricola()));		
		}
		
		DefaultListModel<JCheckBox> model = new DefaultListModel<JCheckBox>();
		model.addAll(arrayList);
		studentiNonIscrittiList.setModel(model);
	}


	public ArrayList<String> acquisisciStudentiNonIscrittiSelezionati(ArrayList<String> arrayList,JCheckBoxList checkBoxList){
	
	ListModel<JCheckBox> listModel = checkBoxList.getModel();
	
	for(int i=0; i<listModel.getSize();i++) {
		
		if(listModel.getElementAt(i).isSelected()) {
			arrayList.add(listModel.getElementAt(i).getText());
		}
	}

	return arrayList;
	}
	
	
	
	public boolean isNumeroIscrizioniMassimoSuperato(ArrayList<String> arrayList) {
		
		if(arrayList.size()+controller.getIscrizioniCorso(controller.getCorsoSelezionato().getNome()).size() > 
	       controller.getCorsoSelezionato().getMaxPartecipanti()) {
			
				return true;	
		}
		
		else {
			return false;
		}
	
	}
	
	public int calcoloNumeroIscrizioniDisponibili() {
		int risultato=0;
		
		risultato = controller.getCorsoSelezionato().getMaxPartecipanti() - 
				    controller.getIscrizioniCorso(controller.getCorsoSelezionato().getNome()).size();
		
		return risultato;
	}
	
	
	//LISTENER
	
	public ActionListener listenerIscriviButton() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				studentiSelezionatiList.clear();
				studentiSelezionatiList = acquisisciStudentiNonIscrittiSelezionati(studentiSelezionatiList, studentiNonIscrittiList);
				if(!studentiSelezionatiList.isEmpty()) {
					if(!isNumeroIscrizioniMassimoSuperato(studentiSelezionatiList)) {
						controller.insertIscrizione(controller.getCorsoSelezionato().getNome(), studentiSelezionatiList);
						setVisible(false);
					}
					else {
							JOptionPane.showInternalMessageDialog(null, "Numero massimo di iscritti superato.\n Puoi aggiungere al massimo altri "+calcoloNumeroIscrizioniDisponibili()+" studenti.", getTitle(), JOptionPane.ERROR_MESSAGE);
					}
				}
				else {	
					JOptionPane.showInternalMessageDialog(null, "Devi inserire almeno uno studente da iscrivere", getTitle(), JOptionPane.ERROR_MESSAGE);
				}	
			}
		};
	}

}
