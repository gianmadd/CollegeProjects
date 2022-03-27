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

public class InserimentoMacroareaFrame extends PopolamentoMacroareaFrame {

	private Controller controller;
	
	private JTextField creaNuovaMacroareaLabel;
	private JButton creaButton;
	private JButton annullaButton;
	
	
	public InserimentoMacroareaFrame(Controller ctrl) {
		
		setTitle("Creazione macroarea");

		controller = ctrl;
		
		creaNuovaMacroareaLabel = new JTextField();
		creaNuovaMacroareaLabel.setEditable(false);
		creaNuovaMacroareaLabel.setText("Crea nuova macroarea");
		creaNuovaMacroareaLabel.setForeground(panna);
		creaNuovaMacroareaLabel.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 17));
		creaNuovaMacroareaLabel.setColumns(10);
		creaNuovaMacroareaLabel.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		creaNuovaMacroareaLabel.setBackground(blu);
		creaNuovaMacroareaLabel.setBounds(20, 18, 219, 27);
		getContentPanel().add(creaNuovaMacroareaLabel);
		
		
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
					JOptionPane.showInternalMessageDialog(null, "Impossibile creare una macroarea vuota.", getTitle(), JOptionPane.ERROR_MESSAGE);
				}
				else if(checkEsistenzaStessaMacroarea(controller.getMacroareeFiltrate(true), getInserisciNomeField())) {
					JOptionPane.showInternalMessageDialog(null, "Nome della macroarea già esistente.", getTitle(), JOptionPane.ERROR_MESSAGE);
				}
				else {
					controller.insertMacroarea(getInserisciNomeField().getText());
					setVisible(false);
					//REFRESH LISTA DEI NOMI DEI CORSI SECONDO I FILTRI ATTUALI
				}
			}
		};
	}
}
