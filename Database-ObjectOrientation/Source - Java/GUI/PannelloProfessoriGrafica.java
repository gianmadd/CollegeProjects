package GUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import Entita.JCheckBoxList;

public abstract class PannelloProfessoriGrafica extends PannelloBase {

	private JLabel titoloPannelloProfessoriLabel;
	private JPanel informazioniProfessorePanel;
	private JTextPane nomeProfessoreField;
	private JButton eliminaButton;
	private JScrollPane insegnamentiScrollPane;
	private JList insegnamentiList;
	private JLabel titoloInsegnamentiLabel;
	private JButton nuovoButton;
	private JCheckBoxList insegnamentiFiltroList;
	private JScrollPane insegnamentiFiltroScrollPane;
	private JLabel insegnamentiFiltroLabel;
	
	public PannelloProfessoriGrafica() {

		getList().setSelectionForeground(panna);
		getList().setSelectionBackground(rosso);
		getList().setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));
		getList().setForeground(new Color(29, 53, 87));
		getList().setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		getList().setBackground(new Color(241, 250, 238));
		
		titoloPannelloProfessoriLabel = new JLabel("PROFESSORI");
		titoloPannelloProfessoriLabel.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
		titoloPannelloProfessoriLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titoloPannelloProfessoriLabel.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 30));
		titoloPannelloProfessoriLabel.setForeground(panna);
		titoloPannelloProfessoriLabel.setBounds(293, 9, 220, 47);
		add(titoloPannelloProfessoriLabel);
		
		informazioniProfessorePanel = new JPanel();
		informazioniProfessorePanel.setLayout(null);
		informazioniProfessorePanel.setForeground(panna);
		informazioniProfessorePanel.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(255, 255, 255), new Color(255, 255, 255), null, null));
		informazioniProfessorePanel.setBackground(bluCobalto);
		informazioniProfessorePanel.setBounds(566, 63, 477, 47);
		add(informazioniProfessorePanel);
		
		nomeProfessoreField = new JTextPane();
		nomeProfessoreField.setText("Nome del professore.");
		nomeProfessoreField.setForeground(panna);
		nomeProfessoreField.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));
		nomeProfessoreField.setEditable(false);
		nomeProfessoreField.setBorder(BorderFactory.createMatteBorder(0,0,1,0, Color.white));
		nomeProfessoreField.setBackground(bluCobalto);
		nomeProfessoreField.setBounds(10, 11, 457, 20);
		informazioniProfessorePanel.add(nomeProfessoreField);
		
		eliminaButton = new JButton("Elimina");
		eliminaButton.addActionListener(listenerEliminaButton());
		eliminaButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		eliminaButton.setForeground(blu);
		eliminaButton.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 17));
		eliminaButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		eliminaButton.setBackground(panna);
		eliminaButton.setBounds(409, 657, 125, 58);
		add(eliminaButton);
		
		insegnamentiScrollPane = new JScrollPane();
		insegnamentiScrollPane.setBorder(new LineBorder(new Color(255, 255, 255), 3));
		insegnamentiScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		insegnamentiScrollPane.setBounds(566, 196, 229, 519);
		add(insegnamentiScrollPane);
		
		insegnamentiList = new JList();
		insegnamentiList.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
		insegnamentiList.setForeground(panna);
		insegnamentiList.setSelectionForeground(blu);
		insegnamentiList.setSelectionBackground(panna);
		insegnamentiList.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		insegnamentiList.setBackground(bluCobalto);
		insegnamentiScrollPane.setViewportView(insegnamentiList);
		
		titoloInsegnamentiLabel = new JLabel("INSEGNAMENTI");
		titoloInsegnamentiLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titoloInsegnamentiLabel.setForeground(panna);
		titoloInsegnamentiLabel.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 26));
		titoloInsegnamentiLabel.setBounds(566, 161, 229, 35);
		add(titoloInsegnamentiLabel);
		
		nuovoButton = new JButton("Nuovo");
		nuovoButton.addActionListener(listenerNuovoButton());
		nuovoButton.setForeground(blu);
		nuovoButton.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 17));
		nuovoButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		nuovoButton.setBackground(panna);
		nuovoButton.setBounds(276, 657, 126, 58);
		add(nuovoButton);
		
		insegnamentiFiltroScrollPane = new JScrollPane();
		insegnamentiFiltroScrollPane.setBorder(new LineBorder(Color.WHITE, 3));
		insegnamentiFiltroScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		insegnamentiFiltroScrollPane.setBounds(10, 176, 226, 452);
		getFiltriPanel().add(insegnamentiFiltroScrollPane);
		
		insegnamentiFiltroList = new JCheckBoxList();
		insegnamentiFiltroList.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 13));
		insegnamentiFiltroList.setBackground(panna);
		insegnamentiFiltroList.setForeground(bluScuro);
		insegnamentiFiltroList.setSelectionBackground(rosso);
		insegnamentiFiltroList.setSelectionForeground(panna);
		insegnamentiFiltroList.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		insegnamentiFiltroList.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		insegnamentiFiltroScrollPane.setViewportView(insegnamentiFiltroList);
		
		insegnamentiFiltroLabel = new JLabel("Insegnanti di:");
		insegnamentiFiltroLabel.setForeground(panna);
		insegnamentiFiltroLabel.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 13));
		insegnamentiFiltroLabel.setBounds(10, 147, 115, 18);
		getFiltriPanel().add(insegnamentiFiltroLabel);
		
		getAscDescToggle().setText("A-Z");
		getAscDescToggle().addActionListener(listenerAscDescButton());
		
		getRefreshButton().addActionListener(listenerRefreshButton());
		
	}

	//LISTENERS ASTRATTI
	public abstract ActionListener listenerEliminaButton();
	public abstract ActionListener listenerNuovoButton();
	public abstract ActionListener listenerAscDescButton();
	public abstract ActionListener listenerRefreshButton();

	//GETTERS E SETTERS
	public JCheckBoxList getInsegnamentiFiltroList() {
		return insegnamentiFiltroList;
	}
	public void setInsegnamentiFiltroList(JCheckBoxList insegnamentiFiltroList) {
		this.insegnamentiFiltroList = insegnamentiFiltroList;
	}
	public JLabel getTitoloPannelloProfessoriLabel() {
		return titoloPannelloProfessoriLabel;
	}
	public void setTitoloPannelloProfessoriLabel(JLabel titoloPannelloProfessoriLabel) {
		this.titoloPannelloProfessoriLabel = titoloPannelloProfessoriLabel;
	}
	public JPanel getInformazioniProfessorePanel() {
		return informazioniProfessorePanel;
	}
	public void setInformazioniProfessorePanel(JPanel informazioniProfessorePanel) {
		this.informazioniProfessorePanel = informazioniProfessorePanel;
	}
	public JTextPane getNomeProfessoreField() {
		return nomeProfessoreField;
	}
	public void setNomeProfessoreField(JTextPane nomeProfessoreField) {
		this.nomeProfessoreField = nomeProfessoreField;
	}
	public JButton getEliminaButton() {
		return eliminaButton;
	}
	public void setEliminaButton(JButton eliminaButton) {
		this.eliminaButton = eliminaButton;
	}
	public JScrollPane getInsegnamentiScrollPane() {
		return insegnamentiScrollPane;
	}
	public void setInsegnamentiScrollPane(JScrollPane insegnamentiScrollPane) {
		this.insegnamentiScrollPane = insegnamentiScrollPane;
	}
	public JList getInsegnamentiList() {
		return insegnamentiList;
	}
	public void setInsegnamentiList(JList insegnamentiList) {
		this.insegnamentiList = insegnamentiList;
	}
	public JLabel getTitoloInsegnamentiLabel() {
		return titoloInsegnamentiLabel;
	}
	public void setTitoloInsegnamentiLabel(JLabel titoloInsegnamentiLabel) {
		this.titoloInsegnamentiLabel = titoloInsegnamentiLabel;
	}
	public JButton getNuovoButton() {
		return nuovoButton;
	}
	public void setNuovoButton(JButton nuovoButton) {
		this.nuovoButton = nuovoButton;
	}
	public JScrollPane getInsegnamentiFiltroScrollPane() {
		return insegnamentiFiltroScrollPane;
	}
	public void setInsegnamentiFiltroScrollPane(JScrollPane insegnamentiFiltroScrollPane) {
		this.insegnamentiFiltroScrollPane = insegnamentiFiltroScrollPane;
	}
	public JLabel getInsegnamentiFiltroLabel() {
		return insegnamentiFiltroLabel;
	}
	public void setInsegnamentiFiltroLabel(JLabel insegnamentiFiltroLabel) {
		this.insegnamentiFiltroLabel = insegnamentiFiltroLabel;
	}
	
}
