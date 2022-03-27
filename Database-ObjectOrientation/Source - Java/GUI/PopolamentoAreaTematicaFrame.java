package GUI;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

import Controller.Controller;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public abstract class PopolamentoAreaTematicaFrame extends PopolamentoFrame {
	
	private Controller controller;
	private JTextField inserisciAreaTematicaField;
	
	
	public PopolamentoAreaTematicaFrame() {
		
		inserisciAreaTematicaField = new JTextField();
		inserisciAreaTematicaField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				inserisciAreaTematicaField.setBackground(bluScuro);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				inserisciAreaTematicaField.setBackground(blu);
			}
		});
		
		inserisciAreaTematicaField.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		inserisciAreaTematicaField.setForeground(panna);
		inserisciAreaTematicaField.setBackground(blu);
		inserisciAreaTematicaField.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		inserisciAreaTematicaField.setBounds(184, 64, 328, 20);
		getContentPanel().add(inserisciAreaTematicaField);
		inserisciAreaTematicaField.setColumns(10);
		
		JLabel inserisciAreaTematicaLabel = new JLabel("Inserisci area tematica: ");
		inserisciAreaTematicaLabel .setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 13));
		inserisciAreaTematicaLabel .setForeground(panna);
		inserisciAreaTematicaLabel .setBounds(31, 66, 143, 13);
		getContentPanel().add(inserisciAreaTematicaLabel );
		
	}

	public JTextField getInserisciAreaTematicaField() {
		return inserisciAreaTematicaField;
	}

	public void setInserisciAreaTematicaField(JTextField inserisciAreaTematicaField) {
		this.inserisciAreaTematicaField = inserisciAreaTematicaField;
	}



	
	
	
	
	
	
}
