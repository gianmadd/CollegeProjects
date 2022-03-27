package GUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;

public abstract class PannelloParoleChiaveGrafica extends PannelloBase {

	private JButton modificaButton;
	private JLabel titoloPannelloParoleChiaveLabel;
	private JButton eliminaButton;
	private AbstractButton nuovaButton;
	
	public PannelloParoleChiaveGrafica() {
		
		getList().setSelectionForeground(panna);
		getList().setSelectionBackground(rosso);
		getList().setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));
		getList().setForeground(blu);
		getList().setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		getList().setBackground(panna);
		
		titoloPannelloParoleChiaveLabel = new JLabel("PAROLE CHIAVE");
		titoloPannelloParoleChiaveLabel.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
		titoloPannelloParoleChiaveLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titoloPannelloParoleChiaveLabel.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 29));
		titoloPannelloParoleChiaveLabel.setForeground(panna);
		titoloPannelloParoleChiaveLabel.setBounds(280, 9, 243, 40);
		add(titoloPannelloParoleChiaveLabel);

		modificaButton = new JButton("Modifica");
		modificaButton.addActionListener(listenerModificaButton());
		modificaButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		modificaButton.setForeground(blu);
		modificaButton.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 16));
		modificaButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		modificaButton.setBackground(panna);
		modificaButton.setBounds(362, 657, 83, 58);
		add(modificaButton);
		
		eliminaButton = new JButton("Elimina");
		eliminaButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		eliminaButton.addActionListener(listenerEliminaButton());
		eliminaButton.setForeground(blu);
		eliminaButton.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 16));
		eliminaButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		eliminaButton.setBackground(panna);
		eliminaButton.setBounds(449, 657, 83, 58);
		add(eliminaButton);
		
		nuovaButton = new JButton("Nuova");
		nuovaButton.addActionListener(listenerNuovaButton());
		nuovaButton.setForeground(blu);
		nuovaButton.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 16));
		nuovaButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		nuovaButton.setBackground(panna);
		nuovaButton.setBounds(275, 657, 83, 58);
		add(nuovaButton);
		
		getAscDescToggle().setText("A-Z");
		getAscDescToggle().addActionListener(listenerAscDescToggle());
		
		getRefreshButton().addActionListener(listenerRefreshButton());
		
	}

	public abstract ActionListener listenerModificaButton();
	public abstract ActionListener listenerEliminaButton();
	public abstract ActionListener listenerNuovaButton();
	public abstract ActionListener listenerAscDescToggle();
	public abstract ActionListener listenerRefreshButton();

	
	
	//GETTERS AND SETTERS
	public JButton getModificaButton() {
		return modificaButton;
	}
	public void setModificaButton(JButton modificaButton) {
		this.modificaButton = modificaButton;
	}
	public JLabel getTitoloPannelloParoleChiaveLabel() {
		return titoloPannelloParoleChiaveLabel;
	}
	public void setTitoloPannelloParoleChiaveLabel(JLabel titoloPannelloParoleChiaveLabel) {
		this.titoloPannelloParoleChiaveLabel = titoloPannelloParoleChiaveLabel;
	}
	public JButton getEliminaButton() {
		return eliminaButton;
	}
	public void setEliminaButton(JButton eliminaButton) {
		this.eliminaButton = eliminaButton;
	}
	public AbstractButton getNuovaButton() {
		return nuovaButton;
	}
	public void setNuovaButton(AbstractButton nuovaButton) {
		this.nuovaButton = nuovaButton;
	}
}
