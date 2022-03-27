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

public abstract class PannelloStudentiGrafica extends PannelloBase {
	
	private JLabel titoloPannelloStudentiLabel;
	private JPanel informazioniGeneraliCorsoPanel;
	private JTextPane nomeDelloStudenteLabel;
	private JLabel emailLabel;
	private JLabel telefonoLabel;
	private JLabel dataNascitaLabel;
	private JTextPane dataDiNascitaTextPane;
	private JTextPane emailTextPane;
	private JTextPane telefonoTextPane;
	private JScrollPane partecipazioniScrollPane;
	private JList partecipazioniStudentiList;
	private JButton eliminaButton;
	private JLabel titoloPartecipazioniLabel;
	private JScrollPane iscrizioniScrollPane;
	private JList iscrizioniStudentiList;
	private JLabel titoloIscrizioniLabel;
	private JButton nuovoButton;
	private JScrollPane iscrizioniFiltroScrollPane;
	private JCheckBoxList iscrizioniFiltroList;
	private JLabel iscrizioniFiltroLabel;

	public PannelloStudentiGrafica() {
		
		
		getList().setSelectionForeground(panna);
		getList().setSelectionBackground(rosso);
		getList().setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));
		getList().setForeground(blu);
		getList().setBackground(panna);
		getList().setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		
		titoloPannelloStudentiLabel = new JLabel("STUDENTI");
		titoloPannelloStudentiLabel.setForeground(panna);
		titoloPannelloStudentiLabel.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 36));
		titoloPannelloStudentiLabel.setBounds(315, 11, 181, 47);
		titoloPannelloStudentiLabel.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
		titoloPannelloStudentiLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titoloPannelloStudentiLabel.setForeground(Color.WHITE);
		titoloPannelloStudentiLabel.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 30));
		titoloPannelloStudentiLabel.setBounds(313, 11, 183, 47);
		add(titoloPannelloStudentiLabel);
		
		informazioniGeneraliCorsoPanel = new JPanel();
		informazioniGeneraliCorsoPanel.setLayout(null);
		informazioniGeneraliCorsoPanel.setForeground(panna);
		informazioniGeneraliCorsoPanel.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(255, 255, 255), new Color(255, 255, 255), null, null));
		informazioniGeneraliCorsoPanel.setBackground(bluCobalto);
		informazioniGeneraliCorsoPanel.setBounds(566, 62, 477, 215);
		add(informazioniGeneraliCorsoPanel);
		
		nomeDelloStudenteLabel = new JTextPane();
		nomeDelloStudenteLabel.setText("Nome dello studente.");
		nomeDelloStudenteLabel.setForeground(panna);
		nomeDelloStudenteLabel.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));
		nomeDelloStudenteLabel.setEditable(false);
		nomeDelloStudenteLabel.setBorder(BorderFactory.createMatteBorder(0,0,1,0, Color.white));
		nomeDelloStudenteLabel.setBackground(bluCobalto);
		nomeDelloStudenteLabel.setBounds(10, 11, 257, 20);
		informazioniGeneraliCorsoPanel.add(nomeDelloStudenteLabel);
		
		emailLabel = new JLabel("Email:");
		emailLabel.setForeground(panna);
		emailLabel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		emailLabel.setBounds(10, 105, 43, 14);
		informazioniGeneraliCorsoPanel.add(emailLabel);
		
		telefonoLabel = new JLabel("Telefono:");
		telefonoLabel.setForeground(panna);
		telefonoLabel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		telefonoLabel.setBounds(10, 157, 65, 20);
		informazioniGeneraliCorsoPanel.add(telefonoLabel);
		
		dataNascitaLabel = new JLabel("Data di nascita:");
		dataNascitaLabel.setForeground(panna);
		dataNascitaLabel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		dataNascitaLabel.setBounds(10, 56, 89, 14);
		informazioniGeneraliCorsoPanel.add(dataNascitaLabel);
		
		dataDiNascitaTextPane = new JTextPane();
		dataDiNascitaTextPane.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		dataDiNascitaTextPane.setBackground(bluCobalto);
		dataDiNascitaTextPane.setForeground(panna);
		dataDiNascitaTextPane.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 11));
		dataDiNascitaTextPane.setEditable(false);
		dataDiNascitaTextPane.setBounds(121, 50, 146, 20);
		informazioniGeneraliCorsoPanel.add(dataDiNascitaTextPane);
		
		emailTextPane = new JTextPane();
		emailTextPane.setForeground(panna);
		emailTextPane.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 11));
		emailTextPane.setEditable(false);
		emailTextPane.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		emailTextPane.setBackground(bluCobalto);
		emailTextPane.setBounds(63, 99, 204, 20);
		informazioniGeneraliCorsoPanel.add(emailTextPane);
		
		telefonoTextPane = new JTextPane();
		telefonoTextPane.setForeground(panna);
		telefonoTextPane.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 11));
		telefonoTextPane.setEditable(false);
		telefonoTextPane.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		telefonoTextPane.setBackground(bluCobalto);
		telefonoTextPane.setBounds(81, 157, 186, 20);
		informazioniGeneraliCorsoPanel.add(telefonoTextPane);
		
		partecipazioniScrollPane = new JScrollPane();
		partecipazioniScrollPane.setBorder(new LineBorder(new Color(255, 255, 255), 3));
		partecipazioniScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		partecipazioniScrollPane.setBounds(566, 356, 287, 359);
		add(partecipazioniScrollPane);
		
		partecipazioniStudentiList = new JList();
		partecipazioniStudentiList.setSelectionForeground(bluCobalto);
		partecipazioniStudentiList.setSelectionBackground(panna);
		partecipazioniStudentiList.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		partecipazioniScrollPane.setViewportView(partecipazioniStudentiList);
		partecipazioniStudentiList.setForeground(panna);
		partecipazioniStudentiList.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 13));
		partecipazioniStudentiList.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		partecipazioniStudentiList.setBackground(bluCobalto);
		
		eliminaButton = new JButton("Elimina");
		eliminaButton.addActionListener(listenerEliminaButton());
		eliminaButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		eliminaButton.setForeground(blu);
		eliminaButton.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 17));
		eliminaButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		eliminaButton.setBackground(panna);
		eliminaButton.setBounds(409, 657, 125, 58);
		add(eliminaButton);
		
		titoloPartecipazioniLabel = new JLabel("PARTECIPAZIONI");
		titoloPartecipazioniLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titoloPartecipazioniLabel.setForeground(panna);
		titoloPartecipazioniLabel.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 27));
		titoloPartecipazioniLabel.setBounds(566, 318, 260, 35);
		add(titoloPartecipazioniLabel);
		
		iscrizioniScrollPane = new JScrollPane();
		iscrizioniScrollPane.setBorder(new LineBorder(new Color(255, 255, 255), 3));
		iscrizioniScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		iscrizioniScrollPane.setBounds(907, 356, 287, 359);
		add(iscrizioniScrollPane);
		
		iscrizioniStudentiList = new JList();
		iscrizioniStudentiList.setForeground(panna);
		iscrizioniStudentiList.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 13));
		iscrizioniStudentiList.setSelectionForeground(bluCobalto);
		iscrizioniStudentiList.setSelectionBackground(panna);
		iscrizioniStudentiList.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		iscrizioniStudentiList.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		iscrizioniStudentiList.setBackground(bluCobalto);
		iscrizioniScrollPane.setViewportView(iscrizioniStudentiList);
		
		titoloIscrizioniLabel = new JLabel("ISCRIZIONI");
		titoloIscrizioniLabel.setForeground(panna);
		titoloIscrizioniLabel.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 27));
		titoloIscrizioniLabel.setBounds(972, 318, 160, 35);
		add(titoloIscrizioniLabel);
		
		nuovoButton = new JButton("Nuovo");
		nuovoButton.addActionListener(listenerNuovoButton());
		nuovoButton.setForeground(blu);
		nuovoButton.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 17));
		nuovoButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		nuovoButton.setBackground(panna);
		nuovoButton.setBounds(276, 657, 126, 58);
		add(nuovoButton);
		
		iscrizioniFiltroScrollPane = new JScrollPane();
		iscrizioniFiltroScrollPane.setBorder(new LineBorder(new Color(255, 255, 255), 3));
		iscrizioniFiltroScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		iscrizioniFiltroScrollPane.setBounds(10, 172, 226, 456);
		getFiltriPanel().add(iscrizioniFiltroScrollPane);
		
		iscrizioniFiltroList = new JCheckBoxList();
		iscrizioniFiltroList.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		iscrizioniFiltroList.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 13));
		iscrizioniFiltroList.setBackground(panna);
		iscrizioniFiltroList.setForeground(bluScuro);
		iscrizioniFiltroList.setSelectionBackground(rosso);
		iscrizioniFiltroList.setSelectionForeground(panna);
		iscrizioniFiltroScrollPane.setViewportView(iscrizioniFiltroList);
		
		iscrizioniFiltroLabel = new JLabel("Iscritti a:");
		iscrizioniFiltroLabel.setForeground(panna);
		iscrizioniFiltroLabel.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 13));
		iscrizioniFiltroLabel.setBounds(10, 147, 115, 14);
		getFiltriPanel().add(iscrizioniFiltroLabel);
		
		getAscDescToggle().setText("A-Z");
		getAscDescToggle().addActionListener(listenerAscDescToggle());
		
		getRefreshButton().addActionListener(listenerRefreshButton());
		
	}
	
	//LISTENERS ASTRATTI
	public abstract ActionListener listenerEliminaButton();
	public abstract ActionListener listenerNuovoButton();
	public abstract ActionListener listenerAscDescToggle();
	public abstract ActionListener listenerRefreshButton();

	
	//GETTERS E SETTERS
	public JLabel getTitoloPannelloStudentiLabel() {
		return titoloPannelloStudentiLabel;
	}
	public void setTitoloPannelloStudentiLabel(JLabel titoloPannelloStudentiLabel) {
		this.titoloPannelloStudentiLabel = titoloPannelloStudentiLabel;
	}
	public JPanel getInformazioniGeneraliCorsoPanel() {
		return informazioniGeneraliCorsoPanel;
	}
	public void setInformazioniGeneraliCorsoPanel(JPanel informazioniGeneraliCorsoPanel) {
		this.informazioniGeneraliCorsoPanel = informazioniGeneraliCorsoPanel;
	}
	public JTextPane getNomeDelloStudenteLabel() {
		return nomeDelloStudenteLabel;
	}
	public void setNomeDelloStudenteLabel(JTextPane nomeDelloStudenteLabel) {
		this.nomeDelloStudenteLabel = nomeDelloStudenteLabel;
	}
	public JLabel getEmailLabel() {
		return emailLabel;
	}
	public void setEmailLabel(JLabel emailLabel) {
		this.emailLabel = emailLabel;
	}
	public JLabel getTelefonoLabel() {
		return telefonoLabel;
	}
	public void setTelefonoLabel(JLabel telefonoLabel) {
		this.telefonoLabel = telefonoLabel;
	}
	public JLabel getDataNascitaLabel() {
		return dataNascitaLabel;
	}
	public void setDataNascitaLabel(JLabel dataNascitaLabel) {
		this.dataNascitaLabel = dataNascitaLabel;
	}
	public JTextPane getDataDiNascitaTextPane() {
		return dataDiNascitaTextPane;
	}
	public void setDataDiNascitaTextPane(JTextPane dataDiNascitaTextPane) {
		this.dataDiNascitaTextPane = dataDiNascitaTextPane;
	}
	public JTextPane getEmailTextPane() {
		return emailTextPane;
	}
	public void setEmailTextPane(JTextPane emailTextPane) {
		this.emailTextPane = emailTextPane;
	}
	public JTextPane getTelefonoTextPane() {
		return telefonoTextPane;
	}
	public void setTelefonoTextPane(JTextPane telefonoTextPane) {
		this.telefonoTextPane = telefonoTextPane;
	}
	public JScrollPane getPartecipazioniScrollPane() {
		return partecipazioniScrollPane;
	}
	public void setPartecipazioniScrollPane(JScrollPane partecipazioniScrollPane) {
		this.partecipazioniScrollPane = partecipazioniScrollPane;
	}
	public JList getPartecipazioniStudentiList() {
		return partecipazioniStudentiList;
	}
	public void setPartecipazioniStudentiList(JList partecipazioniStudentiList) {
		this.partecipazioniStudentiList = partecipazioniStudentiList;
	}
	public JButton getEliminaButton() {
		return eliminaButton;
	}
	public void setEliminaButton(JButton eliminaButton) {
		this.eliminaButton = eliminaButton;
	}
	public JLabel getTitoloPartecipazioniLabel() {
		return titoloPartecipazioniLabel;
	}
	public void setTitoloPartecipazioniLabel(JLabel titoloPartecipazioniLabel) {
		this.titoloPartecipazioniLabel = titoloPartecipazioniLabel;
	}
	public JScrollPane getIscrizioniScrollPane() {
		return iscrizioniScrollPane;
	}
	public void setIscrizioniScrollPane(JScrollPane iscrizioniScrollPane) {
		this.iscrizioniScrollPane = iscrizioniScrollPane;
	}
	public JList getIscrizioniStudentiList() {
		return iscrizioniStudentiList;
	}
	public void setIscrizioniStudentiList(JList iscrizioniStudentiList) {
		this.iscrizioniStudentiList = iscrizioniStudentiList;
	}
	public JLabel getTitoloIscrizioniLabel() {
		return titoloIscrizioniLabel;
	}
	public void setTitoloIscrizioniLabel(JLabel titoloIscrizioniLabel) {
		this.titoloIscrizioniLabel = titoloIscrizioniLabel;
	}
	public JButton getNuovoButton() {
		return nuovoButton;
	}
	public void setNuovoButton(JButton nuovoButton) {
		this.nuovoButton = nuovoButton;
	}
	public JScrollPane getIscrizioniFiltroScrollPane() {
		return iscrizioniFiltroScrollPane;
	}
	public void setIscrizioniFiltroScrollPane(JScrollPane iscrizioniFiltroScrollPane) {
		this.iscrizioniFiltroScrollPane = iscrizioniFiltroScrollPane;
	}
	public JCheckBoxList getIscrizioniFiltroList() {
		return iscrizioniFiltroList;
	}
	public void setIscrizioniFiltroList(JCheckBoxList iscrizioniFiltroList) {
		this.iscrizioniFiltroList = iscrizioniFiltroList;
	}
	public JLabel getIscrizioniFiltroLabel() {
		return iscrizioniFiltroLabel;
	}
	public void setIscrizioniFiltroLabel(JLabel iscrizioniFiltroLabel) {
		this.iscrizioniFiltroLabel = iscrizioniFiltroLabel;
	}
	
}
