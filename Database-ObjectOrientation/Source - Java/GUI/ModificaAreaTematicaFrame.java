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

public class ModificaAreaTematicaFrame extends PopolamentoAreaTematicaFrame {

	private Controller controller;
	
	private JTextField modificaAreaTematicaLabel;
	private JButton modificaButton;
	private JButton annullaButton;
	
	
	public ModificaAreaTematicaFrame(Controller ctrl) {
		
		controller = ctrl;
		
		modificaAreaTematicaLabel = new JTextField();
		modificaAreaTematicaLabel.setEditable(false);
		modificaAreaTematicaLabel.setText("Modifica area tematica");
		modificaAreaTematicaLabel.setForeground(panna);
		modificaAreaTematicaLabel.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 17));
		modificaAreaTematicaLabel.setColumns(10);
		modificaAreaTematicaLabel.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		modificaAreaTematicaLabel.setBackground(blu);
		modificaAreaTematicaLabel.setBounds(20, 18, 219, 27);
		getContentPanel().add(modificaAreaTematicaLabel);
		
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

	
	//LISTENER
	
	public ActionListener listenerModificaButton() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(getInserisciAreaTematicaField().getText().isBlank()) {
					JOptionPane.showInternalMessageDialog(null, "Impossibile creare un'area tematica vuota.", getTitle(), JOptionPane.ERROR_MESSAGE);
				}
				else {
					controller.updateAreaTematica(getInserisciAreaTematicaField().getText());
					setVisible(false);
				}
			}
		};
	}
}
