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

public class ModificaParoleChiaveFrame extends PopolamentoParoleChiaveFrame {

	private Controller controller;
	
	private JTextField modificaParolaChiaveLabel;
	private JButton modificaButton;
	private JButton annullaButton;
	
	
	public ModificaParoleChiaveFrame(Controller ctrl) {
		
		controller = ctrl;
		
		modificaParolaChiaveLabel = new JTextField();
		modificaParolaChiaveLabel.setEditable(false);
		modificaParolaChiaveLabel.setText("Modifica parola chiave");
		modificaParolaChiaveLabel.setForeground(panna);
		modificaParolaChiaveLabel.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 17));
		modificaParolaChiaveLabel.setColumns(10);
		modificaParolaChiaveLabel.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		modificaParolaChiaveLabel.setBackground(blu);
		modificaParolaChiaveLabel.setBounds(20, 18, 219, 27);
		getContentPanel().add(modificaParolaChiaveLabel);
		
		modificaButton = new JButton("Modifica");
		modificaButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		modificaButton.addActionListener(listenerModificaButton());
		
		modificaButton.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));
		modificaButton.setForeground(blu);
		modificaButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		modificaButton.setBackground(panna);
		modificaButton.setBounds(391, 321, 108, 39);
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
		annullaButton.setBounds(273, 321, 108, 39);
		getContentPanel().add(annullaButton);
		
		setVisible(true);
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
				if(getInserisciParolaChiaveField().getText().isBlank()) {
					JOptionPane.showInternalMessageDialog(null, "Impossibile creare una parola chiave vuota.", getTitle(), JOptionPane.ERROR_MESSAGE);
				}
				else if(checkEsistenzaStessaParolaChiave(controller.getParoleChiaveFiltrate(true), getInserisciParolaChiaveField())) {
					JOptionPane.showInternalMessageDialog(null, "Nome della parola chiave già esistente.", getTitle(), JOptionPane.ERROR_MESSAGE);
				}
				else {
					controller.updateParolaChiave(getInserisciParolaChiaveField().getText());
					setVisible(false);
					//REFRESH LISTA DEI NOMI DEI CORSI SECONDO I FILTRI ATTUALI
				}
			}
		};
	}
	
	
	
}
