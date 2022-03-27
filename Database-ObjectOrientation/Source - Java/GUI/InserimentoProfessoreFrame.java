package GUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;

import Controller.Controller;

public class InserimentoProfessoreFrame extends PopolamentoAteneoFrame {

	private Controller controller;
	
	private JTextField creaNuovoProfessoreLabel;
	private JButton creaButton;
	private JButton annullaButton;
	
	
	public InserimentoProfessoreFrame(Controller ctrl) {
		
		controller = ctrl;

		creaNuovoProfessoreLabel = new JTextField();
		creaNuovoProfessoreLabel.setEditable(false);
		creaNuovoProfessoreLabel.setText("Inserisci nuovo professore");
		creaNuovoProfessoreLabel.setForeground(panna);
		creaNuovoProfessoreLabel.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 17));
		creaNuovoProfessoreLabel.setColumns(10);
		creaNuovoProfessoreLabel.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		creaNuovoProfessoreLabel.setBackground(blu);
		creaNuovoProfessoreLabel.setBounds(20, 18, 235, 27);
		getContentPanel().add(creaNuovoProfessoreLabel);
		
		
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
		
		setVisible(true);

	}
	
	
	//LISTENER
	
	public ActionListener listenerCreaButton() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(getInserisciNomeField().getText().isBlank()) {
					JOptionPane.showInternalMessageDialog(null, "Impossibile aggiungere un professore senza nome.", getTitle(), JOptionPane.ERROR_MESSAGE);
				}
				else if(getInserisciCognomeField().getText().isBlank()) {
					JOptionPane.showInternalMessageDialog(null, "Impossibile aggiungere un professore senza cognome.", getTitle(), JOptionPane.ERROR_MESSAGE);
				}
				else {
					controller.insertProfessore(getInserisciNomeField().getText(), getInserisciCognomeField().getText());
					setVisible(false);
				}
			}
		};
	}

}
