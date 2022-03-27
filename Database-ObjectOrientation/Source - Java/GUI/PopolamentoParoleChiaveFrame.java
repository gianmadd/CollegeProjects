package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

import Entita.ParoleChiave;

public abstract class PopolamentoParoleChiaveFrame extends PopolamentoFrame {

	private JTextField inserisciParolaChiaveField;
	private JLabel inserisciParolaChiaveLabel;
	
	public PopolamentoParoleChiaveFrame() {

		inserisciParolaChiaveField = new JTextField();
		inserisciParolaChiaveField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				inserisciParolaChiaveField.setBackground(bluScuro);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				inserisciParolaChiaveField.setBackground(blu);
			}
		});
		
		inserisciParolaChiaveField.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		inserisciParolaChiaveField.setForeground(new Color(255, 255, 255));
		inserisciParolaChiaveField.setBackground(blu);
		inserisciParolaChiaveField.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		inserisciParolaChiaveField.setBounds(138, 65, 361, 20);
		getContentPanel().add(inserisciParolaChiaveField);
		inserisciParolaChiaveField.setColumns(10);
		
		inserisciParolaChiaveLabel = new JLabel("Inserisci parola:");
		inserisciParolaChiaveLabel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 13));
		inserisciParolaChiaveLabel.setForeground(new Color(255, 255, 255));
		inserisciParolaChiaveLabel.setBounds(20, 70, 108, 13);
		getContentPanel().add(inserisciParolaChiaveLabel);

	}

	public JTextField getInserisciParolaChiaveField() {
		return inserisciParolaChiaveField;
	}

	public void setInserisciParolaChiaveField(JTextField parolaChiaveField) {
		this.inserisciParolaChiaveField = parolaChiaveField;
	}

	public boolean checkEsistenzaStessaParolaChiave(ArrayList<ParoleChiave> lista, JTextField textField ) {
		
		boolean cond = false;
		for(ParoleChiave p : lista) {
				if(textField.getText().equalsIgnoreCase(p.getParola()))
					cond = true;
		}	
		return cond;
	}

	
}
