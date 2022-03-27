package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import Controller.Controller;

public abstract class PopolamentoFrame extends JDialog {
	
	private JPanel popolamentoPanel;
	private Controller controller;
	final Color panna;
	final Color bluScuro;
	final Color blu;
	final Color rosso;
	final Color bluCobalto;
	
	public PopolamentoFrame() {
					
		panna = new Color(241, 250 , 238);
		bluCobalto = new Color(29, 53, 87);
		bluScuro = new Color(32, 44, 57);
		blu = new Color(40, 56, 69);
		rosso = new Color(230, 57, 70);
		
		popolamentoPanel = new JPanel();
		setResizable(false);
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(558, 410);
		getContentPane().setLayout(new BorderLayout());
		popolamentoPanel.setBorder(new LineBorder(new Color(255, 255, 255), 3));
		popolamentoPanel.setBackground(blu);
		setLocationRelativeTo(null);
		getContentPane().add(popolamentoPanel, BorderLayout.CENTER);
		popolamentoPanel.setLayout(null);
		
	}

	public JPanel getContentPanel() {
		return popolamentoPanel;
	}

	public void setContentPanel(JPanel contentPanel) {
		this.popolamentoPanel = contentPanel;
	}

	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}
	
	
	
	

}
