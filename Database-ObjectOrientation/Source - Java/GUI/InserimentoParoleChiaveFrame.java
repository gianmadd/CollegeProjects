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

public class InserimentoParoleChiaveFrame extends PopolamentoParoleChiaveFrame {

	private Controller controller;
	
	private JTextField creaNuovaParolaChiaveLabel;
	private JButton creaButton;
	private JButton annullaButton;
	
	
	public InserimentoParoleChiaveFrame(Controller ctrl) {
		
		controller = ctrl;
		
		creaNuovaParolaChiaveLabel = new JTextField();
		creaNuovaParolaChiaveLabel.setEditable(false);
		creaNuovaParolaChiaveLabel.setText("Crea nuova parola chiave");
		creaNuovaParolaChiaveLabel.setForeground(panna);
		creaNuovaParolaChiaveLabel.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 17));
		creaNuovaParolaChiaveLabel.setColumns(10);
		creaNuovaParolaChiaveLabel.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		creaNuovaParolaChiaveLabel.setBackground(blu);
		creaNuovaParolaChiaveLabel.setBounds(20, 18, 219, 27);
		getContentPanel().add(creaNuovaParolaChiaveLabel);
		
		
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
				if(getInserisciParolaChiaveField().getText().isBlank()) {
					JOptionPane.showInternalMessageDialog(null, "Impossibile creare una parola chiave vuota.", getTitle(), JOptionPane.ERROR_MESSAGE);
				}
				else if(checkEsistenzaStessaParolaChiave(controller.getParoleChiaveFiltrate(true), getInserisciParolaChiaveField())) {
					JOptionPane.showInternalMessageDialog(null, "Nome della parola chiave già esistente.", getTitle(), JOptionPane.ERROR_MESSAGE);
				}
				else {
					controller.insertParolaChiave(getInserisciParolaChiaveField().getText());
					setVisible(false);
				}
			}
		};
	}
}
