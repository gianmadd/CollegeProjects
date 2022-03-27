package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

import Entita.Macroarea;

public abstract class PopolamentoMacroareaFrame extends PopolamentoFrame {

	private JTextField inserisciNomeMacroareaField;
	private JLabel inserisciNomeMacroareaLabel;
	
	public PopolamentoMacroareaFrame() {

		inserisciNomeMacroareaField = new JTextField();
		inserisciNomeMacroareaField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				inserisciNomeMacroareaField.setBackground(bluScuro);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				inserisciNomeMacroareaField.setBackground(blu);
			}
		});
		
		inserisciNomeMacroareaField.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		inserisciNomeMacroareaField.setForeground(panna);
		inserisciNomeMacroareaField.setBackground(blu);
		inserisciNomeMacroareaField.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		inserisciNomeMacroareaField.setBounds(158, 65, 341, 20);
		getContentPanel().add(inserisciNomeMacroareaField);
		inserisciNomeMacroareaField.setColumns(10);
		
		inserisciNomeMacroareaLabel = new JLabel("Inserisci macroarea:");
		inserisciNomeMacroareaLabel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 13));
		inserisciNomeMacroareaLabel.setForeground(panna);
		inserisciNomeMacroareaLabel.setBounds(20, 70, 128, 13);
		getContentPanel().add(inserisciNomeMacroareaLabel);

	}

	public JTextField getInserisciNomeField() {
		return inserisciNomeMacroareaField;
	}

	public void setInserisciNomeField(JTextField inserisciNomeField) {
		this.inserisciNomeMacroareaField = inserisciNomeField;
	}

	public boolean checkEsistenzaStessaMacroarea(ArrayList<Macroarea> lista, JTextField textField ) {
		
		boolean cond = false;
		for(Macroarea m : lista) {
				if(textField.getText().equalsIgnoreCase(m.getNome()))
					cond = true;
		}	
		return cond;
	}

}
