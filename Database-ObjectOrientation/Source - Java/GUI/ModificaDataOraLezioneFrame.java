package GUI;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

import Controller.Controller;
import Entita.Lezione;

import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.awt.event.ActionEvent;

public class ModificaDataOraLezioneFrame extends PopolamentoLezioneFrame {
	
	
	private Controller controller;
	
	private JTextField modificaLezioneLabel;
	private JButton annullaButton;
	private JButton modificaButton;
	
	private Lezione lezioneUpdate = new Lezione();

	
	public ModificaDataOraLezioneFrame(Controller ctrl) {
		
		controller = ctrl;
		
		getOraFineSpinner().setLocation(397, 268);
		getModificaOraFineLabel().setLocation(265, 273);

		setTitle("Modifica lezione");
		
		modificaLezioneLabel = new JTextField();
		modificaLezioneLabel.setEditable(false);
		modificaLezioneLabel.setText("Modifica lezione");
		modificaLezioneLabel.setForeground(panna);
		modificaLezioneLabel.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 17));
		modificaLezioneLabel.setColumns(10);
		modificaLezioneLabel.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		modificaLezioneLabel.setBackground(blu);
		modificaLezioneLabel.setBounds(20, 18, 170, 27);
		getContentPanel().add(modificaLezioneLabel);
		
		annullaButton = new JButton("Annulla");
		annullaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		annullaButton.setForeground(blu);
		annullaButton.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));
		annullaButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		annullaButton.setBackground(new Color(205, 205, 205));
		annullaButton.setBounds(279, 310, 108, 39);
		getContentPanel().add(annullaButton);
		
		modificaButton = new JButton("Modifica");
		modificaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lezioneUpdate = acquisisciModificheLezione(lezioneUpdate);
				controller.updateLezione(lezioneUpdate);
				setVisible(false);
			}
		});
		modificaButton.setForeground(blu);
		modificaButton.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));
		modificaButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		modificaButton.setBackground(new Color(205, 205, 205));
		modificaButton.setBounds(397, 310, 108, 39);
		getContentPanel().add(modificaButton);

		
		
		
		
		setVisible(true);
	}
	
	public Lezione acquisisciModificheLezione(Lezione lezione) {
		
		Object oraIniziObject = getOraInizioSpinner().getValue();
		java.util.Date oraInizio = (java.util.Date)oraIniziObject;
		Time tempoInizio = new Time(oraInizio.getHours(),oraInizio.getMinutes(),0);
		
		Object oraFineObject = getOraFineSpinner().getValue();
		java.util.Date oraFine = (java.util.Date)oraFineObject;
		Time tempoFine = new Time(oraFine.getHours(),oraFine.getMinutes(),0);
		

		lezione.setTitolo(controller.getLezioneSelezionata().getTitolo());
		lezione.setData(getCalendar().getDate());
		lezione.setOraInizio(tempoInizio);
		lezione.setOraFine(tempoFine);

		return lezione;
	}
	
	

}
