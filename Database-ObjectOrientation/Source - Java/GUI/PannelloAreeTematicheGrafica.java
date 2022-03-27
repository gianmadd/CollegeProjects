package GUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

public abstract class PannelloAreeTematicheGrafica extends PannelloBase {

	
	private JLabel titoloAreeTematicheLabel;
	private JButton modificaAreaTematicaButton;
	private JButton eliminaAreaTematicaButton;
	private JLabel titoloMacroareeLabel;
	private JScrollPane areeTematicheScrollPane;
	private JList areeTematicheList;
	private JButton modificaMacroareaButton;
	private JButton eliminaMacroareaButton;
	private JScrollPane corsiAppartenentiAreaTematicaScrollPane;
	private JLabel titoloCorsiLabel;
	private JList corsiAppartenentiAreaTematicaList;
	private JButton nuovaMacroareaButton;
	private JButton nuovaAreaTematicaButton;

	public PannelloAreeTematicheGrafica() {
		
		getList().setSelectionForeground(panna);
		getList().setSelectionBackground(rosso);
		getList().setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));
		getList().setForeground(new Color(29, 53, 87));
		getList().setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		getList().setBackground(new Color(241, 250, 238));
		
		titoloAreeTematicheLabel = new JLabel("AREE TEMATICHE");
		titoloAreeTematicheLabel.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
		titoloAreeTematicheLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titoloAreeTematicheLabel.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 29));
		titoloAreeTematicheLabel.setForeground(panna);
		titoloAreeTematicheLabel.setBounds(569, 9, 261, 39);
		add(titoloAreeTematicheLabel);
		
		modificaAreaTematicaButton = new JButton("Modifica");
		modificaAreaTematicaButton.addActionListener(listenerModificaAreaTematicaButton());
		modificaAreaTematicaButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		modificaAreaTematicaButton.setForeground(blu);
		modificaAreaTematicaButton.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 16));
		modificaAreaTematicaButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		modificaAreaTematicaButton.setBackground(panna);
		modificaAreaTematicaButton.setBounds(659, 657, 83, 58);
		add(modificaAreaTematicaButton);
		
		eliminaAreaTematicaButton = new JButton("Elimina");
		eliminaAreaTematicaButton.addActionListener(listenerEliminaAreaTematicaButton());
		eliminaAreaTematicaButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		eliminaAreaTematicaButton.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 16));
		eliminaAreaTematicaButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		eliminaAreaTematicaButton.setBackground(panna);
		eliminaAreaTematicaButton.setForeground(blu);
		eliminaAreaTematicaButton.setBounds(747, 657, 83, 58);
		add(eliminaAreaTematicaButton);
		
		titoloMacroareeLabel = new JLabel("MACROAREE");
		titoloMacroareeLabel.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
		titoloMacroareeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titoloMacroareeLabel.setForeground(panna);
		titoloMacroareeLabel.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 30));
		titoloMacroareeLabel.setBounds(288, 9, 227, 39);
		add(titoloMacroareeLabel);
		
		areeTematicheScrollPane = new JScrollPane();
		areeTematicheScrollPane.setBorder(new LineBorder(new Color(230, 57, 70), 3));
		areeTematicheScrollPane.setForeground(panna);
		areeTematicheScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		areeTematicheScrollPane.setBackground(panna);
		areeTematicheScrollPane.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
		areeTematicheScrollPane.setBounds(572, 61, 257, 591);
		add(areeTematicheScrollPane);
		
		areeTematicheList = new JList();
		areeTematicheList.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));
		areeTematicheList.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		areeTematicheList.setSelectionForeground(panna);
		areeTematicheList.setSelectionBackground(rosso);
		areeTematicheList.setForeground(new Color(29, 53, 87));
		areeTematicheList.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		areeTematicheList.setBackground(new Color(241, 250, 238));
		areeTematicheList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		areeTematicheScrollPane.setViewportView(areeTematicheList);
		
		modificaMacroareaButton = new JButton("Modifica\r\n");
		modificaMacroareaButton.addActionListener(listenerModificaMacroareaButton());
		modificaMacroareaButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		modificaMacroareaButton.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 16));
		modificaMacroareaButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		modificaMacroareaButton.setBackground(panna);
		modificaMacroareaButton.setForeground(blu);
		modificaMacroareaButton.setBounds(362, 657, 83, 58);
		add(modificaMacroareaButton);
		
		eliminaMacroareaButton = new JButton("Elimina");
		eliminaMacroareaButton.addActionListener(listenerEliminaMacroareaButton());
		eliminaMacroareaButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		eliminaMacroareaButton.setForeground(blu);
		eliminaMacroareaButton.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 16));
		eliminaMacroareaButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		eliminaMacroareaButton.setBackground(panna);
		eliminaMacroareaButton.setBounds(449, 657, 83, 58);
		add(eliminaMacroareaButton);
		
		corsiAppartenentiAreaTematicaScrollPane = new JScrollPane();
		corsiAppartenentiAreaTematicaScrollPane.setBorder(new LineBorder(new Color(255, 255, 255), 3));
		corsiAppartenentiAreaTematicaScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		corsiAppartenentiAreaTematicaScrollPane.setBounds(868, 61, 257, 654);
		add(corsiAppartenentiAreaTematicaScrollPane);
		
		corsiAppartenentiAreaTematicaList = new JList(); 
		corsiAppartenentiAreaTematicaList.setSelectionForeground(blu);
		corsiAppartenentiAreaTematicaList.setSelectionBackground(panna);
		corsiAppartenentiAreaTematicaList.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		corsiAppartenentiAreaTematicaList.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
		corsiAppartenentiAreaTematicaList.setForeground(panna);
		corsiAppartenentiAreaTematicaList.setBackground(bluCobalto);
		corsiAppartenentiAreaTematicaScrollPane.setViewportView(corsiAppartenentiAreaTematicaList);
		
		titoloCorsiLabel = new JLabel("CORSI");
		titoloCorsiLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titoloCorsiLabel.setForeground(panna);
		titoloCorsiLabel.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 30));
		titoloCorsiLabel.setBounds(936, 9, 107, 39);
		add(titoloCorsiLabel);
		
		nuovaMacroareaButton = new JButton("Nuova");
		nuovaMacroareaButton.addActionListener(listenerNuovaMacroareaButton());
		nuovaMacroareaButton.setForeground(blu);
		nuovaMacroareaButton.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 16));
		nuovaMacroareaButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		nuovaMacroareaButton.setBackground(panna);
		nuovaMacroareaButton.setBounds(275, 657, 83, 58);
		add(nuovaMacroareaButton);
		
		nuovaAreaTematicaButton = new JButton("Nuova");
		nuovaAreaTematicaButton.addActionListener(listenerNuovaAreaTematicaButton());
		nuovaAreaTematicaButton.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 16));
		nuovaAreaTematicaButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		nuovaAreaTematicaButton.setBackground(panna);
		nuovaAreaTematicaButton.setForeground(blu);
		nuovaAreaTematicaButton.setBounds(571, 657, 83, 58);
		add(nuovaAreaTematicaButton);
		
		getAscDescToggle().setText("A-Z");
		getAscDescToggle().addActionListener(listenerAscDescToggle());
		
		getRefreshButton().addActionListener(listenerRefreshButton());

	}
	
	public abstract ActionListener listenerModificaAreaTematicaButton();
	public abstract ActionListener listenerEliminaAreaTematicaButton();
	public abstract ActionListener listenerModificaMacroareaButton();
	public abstract ActionListener listenerEliminaMacroareaButton();
	public abstract ActionListener listenerNuovaMacroareaButton();
	public abstract ActionListener listenerNuovaAreaTematicaButton();
	public abstract ActionListener listenerAscDescToggle();
	public abstract ActionListener listenerRefreshButton();

	
	//GETTERS E SETTERS
	public JLabel getTitoloAreeTematicheLabel() {
		return titoloAreeTematicheLabel;
	}

	public void setTitoloAreeTematicheLabel(JLabel titoloAreeTematicheLabel) {
		this.titoloAreeTematicheLabel = titoloAreeTematicheLabel;
	}

	public JButton getModificaAreaTematicaButton() {
		return modificaAreaTematicaButton;
	}

	public void setModificaAreaTematicaButton(JButton modificaAreaTematicaButton) {
		this.modificaAreaTematicaButton = modificaAreaTematicaButton;
	}

	public JButton getEliminaAreaTematicaButton() {
		return eliminaAreaTematicaButton;
	}

	public void setEliminaAreaTematicaButton(JButton eliminaAreaTematicaButton) {
		this.eliminaAreaTematicaButton = eliminaAreaTematicaButton;
	}

	public JLabel getTitoloMacroareeLabel() {
		return titoloMacroareeLabel;
	}

	public void setTitoloMacroareeLabel(JLabel titoloMacroareeLabel) {
		this.titoloMacroareeLabel = titoloMacroareeLabel;
	}

	public JScrollPane getAreeTematicheScrollPane() {
		return areeTematicheScrollPane;
	}

	public void setAreeTematicheScrollPane(JScrollPane areeTematicheScrollPane) {
		this.areeTematicheScrollPane = areeTematicheScrollPane;
	}

	public JList getAreeTematicheList() {
		return areeTematicheList;
	}

	public void setAreeTematicheList(JList areeTematicheList) {
		this.areeTematicheList = areeTematicheList;
	}

	public JButton getModificaMacroareaButton() {
		return modificaMacroareaButton;
	}

	public void setModificaMacroareaButton(JButton modificaMacroareaButton) {
		this.modificaMacroareaButton = modificaMacroareaButton;
	}

	public JButton getEliminaMacroareaButton() {
		return eliminaMacroareaButton;
	}

	public void setEliminaMacroareaButton(JButton eliminaMacroareaButton) {
		this.eliminaMacroareaButton = eliminaMacroareaButton;
	}

	public JScrollPane getCorsiAppartenentiAreaTematicaScrollPane() {
		return corsiAppartenentiAreaTematicaScrollPane;
	}

	public void setCorsiAppartenentiAreaTematicaScrollPane(JScrollPane corsiAppartenentiAreaTematicaScrollPane) {
		this.corsiAppartenentiAreaTematicaScrollPane = corsiAppartenentiAreaTematicaScrollPane;
	}

	public JLabel getTitoloCorsiLabel() {
		return titoloCorsiLabel;
	}

	public void setTitoloCorsiLabel(JLabel titoloCorsiLabel) {
		this.titoloCorsiLabel = titoloCorsiLabel;
	}

	public JList getCorsiAppartenentiAreaTematicaList() {
		return corsiAppartenentiAreaTematicaList;
	}

	public void setCorsiAppartenentiAreaTematicaList(JList corsiAppartenentiAreaTematicaList) {
		this.corsiAppartenentiAreaTematicaList = corsiAppartenentiAreaTematicaList;
	}

	public JButton getNuovaMacroareaButton() {
		return nuovaMacroareaButton;
	}

	public void setNuovaMacroareaButton(JButton nuovaMacroareaButton) {
		this.nuovaMacroareaButton = nuovaMacroareaButton;
	}

	public JButton getNuovaAreaTematicaButton() {
		return nuovaAreaTematicaButton;
	}

	public void setNuovaAreaTematicaButton(JButton nuovaAreaTematicaButton) {
		this.nuovaAreaTematicaButton = nuovaAreaTematicaButton;
	}
	
	
}
