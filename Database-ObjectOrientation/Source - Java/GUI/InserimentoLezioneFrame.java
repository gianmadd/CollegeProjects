package GUI;

import java.awt.Color;
import java.awt.Font;
import java.sql.Time;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

import Controller.Controller;
import Entita.Corso;
import Entita.Lezione;
import Entita.Professore;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ListSelectionModel;

public class InserimentoLezioneFrame extends PopolamentoLezioneFrame {
	
	private Controller controller;
	
	private JTextField titoloField;
	private JTextArea descrizioneField;
	private Lezione lezioneInserita = new Lezione();
	private JTextField creaNuovaLezioneLabel;
	private JLabel titoloLabel;
	private JLabel inserisciDescrizioneLabel;
	private JScrollPane descrizioneScrollPane;
	private JScrollPane professoriScrollPane;
	private JList professoriList;
	private JButton annullaButton;
	private JButton creaButton;
	private JLabel tenutaDaLabel;

	
	public InserimentoLezioneFrame(Controller ctrl) {
		
		controller = ctrl;
		
		setTitle("Inserisci nuova lezione");
		setSize(558,550);

		//Sposta più giù i componenti di PopolamentoLezioneFrame
		getModificaDataLabel().setBounds(35, 225, 99, 14);
		getCalendar().setBounds(175, 155, 330, 160);
		getModificaOraInizioLabel().setBounds(35, 352, 122, 14);
		getModificaOraFineLabel().setBounds(293, 352, 122, 14);
		getOraInizioSpinner().setBounds(175, 345, 69, 26);
		getOraFineSpinner().setBounds( 413, 345, 69, 26);
		
		
		creaNuovaLezioneLabel = new JTextField();
		creaNuovaLezioneLabel.setEditable(false);
		creaNuovaLezioneLabel.setText("Crea nuova lezione");
		creaNuovaLezioneLabel.setForeground(panna);
		creaNuovaLezioneLabel.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 17));
		creaNuovaLezioneLabel.setColumns(10);
		creaNuovaLezioneLabel.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		creaNuovaLezioneLabel.setBackground(blu);
		creaNuovaLezioneLabel.setBounds(20, 18, 170, 27);
		getContentPanel().add(creaNuovaLezioneLabel);
		
		titoloField = new JTextField();
		titoloField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				titoloField.setBackground(bluScuro);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				titoloField.setBackground(blu);
			}
		});
		titoloField.setForeground(panna);
		titoloField.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		titoloField.setColumns(10);
		titoloField.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		titoloField.setBackground(blu);
		titoloField.setBounds(144, 68, 361, 20);
		getContentPanel().add(titoloField);
		
		titoloLabel = new JLabel("Titolo:");
		titoloLabel.setForeground(panna);
		titoloLabel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 13));
		titoloLabel.setBounds(35, 70, 108, 13);
		getContentPanel().add(titoloLabel);
		
		inserisciDescrizioneLabel = new JLabel("Descrizione:");
		inserisciDescrizioneLabel.setForeground(panna);
		inserisciDescrizioneLabel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 13));
		inserisciDescrizioneLabel.setBounds(35, 112, 108, 13);
		getContentPanel().add(inserisciDescrizioneLabel);
		
		descrizioneScrollPane = new JScrollPane();
		descrizioneScrollPane.setFont(new Font("Microsoft YaHei UI", Font.ITALIC, 11));
		descrizioneScrollPane.setBorder(new LineBorder(Color.WHITE, 3));
		descrizioneScrollPane.setBounds(144, 99, 362, 40);
		getContentPanel().add(descrizioneScrollPane);
		
		descrizioneField = new JTextArea();
		descrizioneField.setFont(new Font("Microsoft YaHei UI", Font.ITALIC, 13));
		descrizioneField.setLineWrap(true);
		descrizioneField.setBackground(panna);
		descrizioneField.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		descrizioneScrollPane.setViewportView(descrizioneField);
		
		professoriScrollPane = new JScrollPane();
		professoriScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		professoriScrollPane.setBorder(new LineBorder(new Color(255, 255, 255), 3));
		professoriScrollPane.setBounds(35, 418, 209, 65);
		getContentPanel().add(professoriScrollPane);
		
		professoriList = new JList();
		professoriList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		professoriList.setForeground(panna);
		professoriList.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 11));
		professoriList.setSelectionForeground(blu);
		professoriList.setSelectionBackground(panna);
		professoriList.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		professoriList.setBackground(blu);
		professoriScrollPane.setViewportView(professoriList);
		
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
		annullaButton.setBounds(279, 446, 108, 39);
		getContentPanel().add(annullaButton);
		
		creaButton = new JButton("Crea");
		creaButton.addActionListener(listenerCreaButton());
		creaButton.setForeground(blu);
		creaButton.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));
		creaButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		creaButton.setBackground(panna);
		creaButton.setBounds(397, 446, 108, 39);
		getContentPanel().add(creaButton);
		
		tenutaDaLabel = new JLabel("Tenuta da:");
		tenutaDaLabel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		tenutaDaLabel.setForeground(panna);
		tenutaDaLabel.setBounds(36, 393, 74, 14);
		getContentPanel().add(tenutaDaLabel);
				
		setModelListaProfessori(controller.getProfessoriFiltrati(true, new ArrayList<String>()), professoriList);
		
		setVisible(true);
	
	}
	
	public void setModelListaProfessori(ArrayList<Professore> arrayList,JList list) {	
		list.setModel(new AbstractListModel() {
			public int getSize() {
				return arrayList.size();
			}
			public Object getElementAt(int index) {
				return arrayList.get(index).getCognome().toUpperCase()+" "+arrayList.get(index).getNome().toUpperCase();
			}
		});
	}
	
	
	public Lezione riempiDatiLezione(Lezione lezione,Professore professore,Corso delCorso) {
		
		Object oraIniziObject = getOraInizioSpinner().getValue();
		java.util.Date oraInizio = (java.util.Date)oraIniziObject;
		Time tempoInizio = new Time(oraInizio.getHours(),oraInizio.getMinutes(),0);
		
		Object oraFineObject = getOraFineSpinner().getValue();
		java.util.Date oraFine = (java.util.Date)oraFineObject;
		Time tempoFine = new Time(oraFine.getHours(),oraFine.getMinutes(),0);

		lezione.setTitolo(titoloField.getText());
		lezione.setDescrizione(descrizioneField.getText());
		lezione.setData(getCalendar().getDate());
		lezione.setOraInizio(tempoInizio);
		lezione.setOraFine(tempoFine);
		lezione.setProfessore(professore);
		lezione.setDelCorso(delCorso);
		
		return lezione;
	}
	
	
	//LISTENER
	
	public ActionListener listenerCreaButton() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(professoriList.getSelectedIndex()!=-1) {
					lezioneInserita = riempiDatiLezione(lezioneInserita,controller.getProfessoriFiltrati(true, new ArrayList<String>()).get(professoriList.getSelectedIndex()), controller.getCorsoSelezionato());
					controller.insertLezione(lezioneInserita);
					setVisible(false);
				}
				else {
					JOptionPane.showInternalMessageDialog(null, "Errore nell'inserimento della lezione:\nDevi inserire un professore.", "Errore", JOptionPane.ERROR_MESSAGE);
				}
			}
		};
	}
	
}
