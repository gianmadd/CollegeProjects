package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

public abstract class PopolamentoAteneoFrame extends PopolamentoFrame {
	
	private JTextField inserisciNomeField;
	private JTextField inserisciCognomeField;
	private JLabel inserisciNomeLabel;
	private JLabel inserisciCognomeLabel;
	
	public PopolamentoAteneoFrame() {
		
		inserisciNomeLabel = new JLabel("Nome:");
		inserisciNomeLabel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 13));
		inserisciNomeLabel.setForeground(panna);
		inserisciNomeLabel.setBounds(32, 67, 62, 13);
		getContentPanel().add(inserisciNomeLabel);
		
		inserisciNomeField = new JTextField();
		inserisciNomeField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				inserisciNomeField.setBackground(bluScuro);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				inserisciNomeField.setBackground(blu);
			}
		});
		inserisciNomeField.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		inserisciNomeField.setForeground(panna);
		inserisciNomeField.setBackground(blu);
		inserisciNomeField.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		inserisciNomeField.setBounds(138, 65, 361, 20);
		getContentPanel().add(inserisciNomeField);
		inserisciNomeField.setColumns(10);
		
		
		inserisciCognomeField = new JTextField();
		inserisciCognomeField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				inserisciCognomeField.setBackground(bluScuro);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				inserisciCognomeField.setBackground(blu);
			}
		});
		inserisciCognomeField.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		inserisciCognomeField.setForeground(panna);
		inserisciCognomeField.setBackground(blu);
		inserisciCognomeField.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		inserisciCognomeField.setBounds(138, 120, 361, 20);
		getContentPanel().add(inserisciCognomeField);
		inserisciCognomeField.setColumns(10);
		
		inserisciCognomeLabel = new JLabel("Cognome:");
		inserisciCognomeLabel.setForeground(panna);
		inserisciCognomeLabel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 13));
		inserisciCognomeLabel.setBounds(32, 119, 73, 18);
		getContentPanel().add(inserisciCognomeLabel);
		
		
		
	}

	public JTextField getInserisciNomeField() {
		return inserisciNomeField;
	}

	public void setInserisciNomeField(JTextField inserisciNomeField) {
		this.inserisciNomeField = inserisciNomeField;
	}

	public JTextField getInserisciCognomeField() {
		return inserisciCognomeField;
	}

	public void setInserisciCognomeField(JTextField inserisciCognomeField) {
		this.inserisciCognomeField = inserisciCognomeField;
	}
	
	
	
}
