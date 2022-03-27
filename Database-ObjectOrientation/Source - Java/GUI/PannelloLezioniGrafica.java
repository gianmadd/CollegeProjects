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
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import com.toedter.calendar.JDateChooser;

import Entita.JCheckBoxList;

public abstract class PannelloLezioniGrafica extends PannelloBase {

	private JLabel titoloPannelloLezioniLabel;
	private JPanel informazioniGeneraliLezionePanel;
	private JTextPane nomeDellaLezioneLabel;
	private JTextPane dataField;
	private JTextPane oraInizioField;
	private JTextPane oraFineField;
	private JTextPane professoreField;
	private JTextPane delCorsoField;
	private JTextPane descrizioneField;
	private JButton eliminaButton;
	private JButton modificaButton;
	private JList presenzeList;
	private JScrollPane presenzeScrollPane;
	private JLabel titoloListaPresenzeLabel;
	private JList paroleChiaveList;
	private JCheckBoxList paroleChiaveFiltroList;
	private JRadioButton ignoraDataRadioButton;
	private JSpinner dataMinimaFiltroSpinner;
	private JSpinner dataMassimaFiltroSpinner;
	private JDateChooser selezioneDataInizialeFiltroDateChooser;
	private JDateChooser selezioneDataFinaleFiltroDateChooser;
	private JLabel dataLabel;
	private JLabel oraInizioLabel;
	private JLabel oraFineLabel;
	private JLabel descrizioneLabel;
	private JLabel tenutaDaLabel;
	private JLabel delCorsoLabel;
	private JButton associaParoleChiaveButton;
	private JScrollPane paroleChiaveScrollPanel;
	private JScrollPane descrizioneScrollPane;
	private JLabel titoloListaParoleChiaveLabel;
	private JButton aggiungiPresenzeButton;
	private JScrollPane paroleChiaveFiltroScrollPanel;
	private JLabel paroleChiaveFiltroLabel;
	private JLabel dataFiltroLabel;
	private JLabel selezioneDataInizialeFiltroLabel;
	private JLabel selezioneDataFinaleFiltroLabel;
	private JTextPane durataField;
	private JLabel durataLabel;
	
	public PannelloLezioniGrafica() {

		getList().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		getList().setSelectionForeground(panna);
		getList().setSelectionBackground(rosso);
		getList().setBackground(panna);
		getList().setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		getList().setForeground(new Color(29, 53, 87));
		getList().setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));
		
		titoloPannelloLezioniLabel = new JLabel("LEZIONI");
		titoloPannelloLezioniLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titoloPannelloLezioniLabel.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		titoloPannelloLezioniLabel.setForeground(panna);
		titoloPannelloLezioniLabel.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 33));
		titoloPannelloLezioniLabel.setBounds(333, 8, 143, 47);
		add(titoloPannelloLezioniLabel);
		
		informazioniGeneraliLezionePanel = new JPanel();
		informazioniGeneraliLezionePanel.setLayout(null);
		informazioniGeneraliLezionePanel.setForeground(panna);
		informazioniGeneraliLezionePanel.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(255, 255, 255), new Color(255, 255, 255), null, null));
		informazioniGeneraliLezionePanel.setBackground(bluCobalto);
		informazioniGeneraliLezionePanel.setBounds(565, 64, 386, 302);
		add(informazioniGeneraliLezionePanel);
		
		nomeDellaLezioneLabel = new JTextPane();
		nomeDellaLezioneLabel.setText("Nome della lezione.");
		nomeDellaLezioneLabel.setForeground(panna);
		nomeDellaLezioneLabel.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));
		nomeDellaLezioneLabel.setEditable(false);
		nomeDellaLezioneLabel.setBorder(BorderFactory.createMatteBorder(0,0,1,0, Color.white));
		nomeDellaLezioneLabel.setBackground(bluCobalto);
		nomeDellaLezioneLabel.setBounds(10, 11, 361, 20);
		informazioniGeneraliLezionePanel.add(nomeDellaLezioneLabel);
		
		dataLabel = new JLabel("Data:");
		dataLabel.setForeground(panna);
		dataLabel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		dataLabel.setBounds(10, 195, 42, 14);
		informazioniGeneraliLezionePanel.add(dataLabel);
		
		oraInizioLabel = new JLabel("Ora inizio:");
		oraInizioLabel.setForeground(panna);
		oraInizioLabel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		oraInizioLabel.setBounds(10, 224, 74, 20);
		informazioniGeneraliLezionePanel.add(oraInizioLabel);
		
		descrizioneLabel = new JLabel("Descrizione");
		descrizioneLabel.setForeground(panna);
		descrizioneLabel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		descrizioneLabel.setBounds(10, 83, 74, 14);
		informazioniGeneraliLezionePanel.add(descrizioneLabel);
		
		dataField = new JTextPane();
		dataField.setForeground(panna);
		dataField.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 11));
		dataField.setEditable(false);
		dataField.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		dataField.setBackground(bluCobalto);
		dataField.setBounds(70, 193, 108, 20);
		informazioniGeneraliLezionePanel.add(dataField);
		
		oraInizioField = new JTextPane();
		oraInizioField.setForeground(panna);
		oraInizioField.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 11));
		oraInizioField.setEditable(false);
		oraInizioField.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		oraInizioField.setBackground(bluCobalto);
		oraInizioField.setBounds(80, 224, 98, 20);
		informazioniGeneraliLezionePanel.add(oraInizioField);
		
		oraFineLabel = new JLabel("Ora fine:");
		oraFineLabel.setForeground(panna);
		oraFineLabel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		oraFineLabel.setBounds(203, 224, 74, 20);
		informazioniGeneraliLezionePanel.add(oraFineLabel);
		
		oraFineField = new JTextPane();
		oraFineField.setForeground(panna);
		oraFineField.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 11));
		oraFineField.setEditable(false);
		oraFineField.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		oraFineField.setBackground(bluCobalto);
		oraFineField.setBounds(273, 224, 98, 20);
		informazioniGeneraliLezionePanel.add(oraFineField);
		
		tenutaDaLabel = new JLabel("Tenuta da:");
		tenutaDaLabel.setForeground(panna);
		tenutaDaLabel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		tenutaDaLabel.setBounds(10, 269, 74, 20);
		informazioniGeneraliLezionePanel.add(tenutaDaLabel);
		
		professoreField = new JTextPane();
		professoreField.setText("Nome del professore");
		professoreField.setForeground(panna);
		professoreField.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 11));
		professoreField.setEditable(false);
		professoreField.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		professoreField.setBackground(bluCobalto);
		professoreField.setBounds(80, 269, 291, 20);
		informazioniGeneraliLezionePanel.add(professoreField);
		
		delCorsoLabel = new JLabel("Corso:");
		delCorsoLabel.setForeground(panna);
		delCorsoLabel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		delCorsoLabel.setBounds(10, 42, 42, 14);
		informazioniGeneraliLezionePanel.add(delCorsoLabel);
		
		delCorsoField = new JTextPane();
		delCorsoField.setText("Nome del corso");
		delCorsoField.setForeground(panna);
		delCorsoField.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 11));
		delCorsoField.setEditable(false);
		delCorsoField.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		delCorsoField.setBackground(bluCobalto);
		delCorsoField.setBounds(70, 42, 301, 20);
		informazioniGeneraliLezionePanel.add(delCorsoField);
		
		descrizioneScrollPane = new JScrollPane();
		descrizioneScrollPane.setBounds(20, 108, 351, 62);
		informazioniGeneraliLezionePanel.add(descrizioneScrollPane);
		
		descrizioneField = new JTextPane();
		descrizioneField.setText("Descrizione della lezione");
		descrizioneField.setBackground(panna);
		descrizioneField.setForeground(blu);
		descrizioneField.setEditable(false);
		descrizioneField.setFont(new Font("Microsoft YaHei UI", Font.ITALIC, 13));
		descrizioneScrollPane.setViewportView(descrizioneField);
		
		durataLabel = new JLabel("Durata:");
		durataLabel.setForeground((Color) null);
		durataLabel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		durataLabel.setBounds(203, 196, 42, 14);
		informazioniGeneraliLezionePanel.add(durataLabel);
		
		durataField = new JTextPane();
		durataField.setForeground(panna);
		durataField.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 11));
		durataField.setEditable(false);
		durataField.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		durataField.setBackground((Color) null);
		durataField.setBounds(263, 189, 108, 20);
		informazioniGeneraliLezionePanel.add(durataField);
		
		eliminaButton = new JButton("Elimina");
		eliminaButton.addActionListener(listenerEliminaButton());
		eliminaButton.setForeground(bluCobalto);
		eliminaButton.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 16));
		eliminaButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		eliminaButton.setBackground(panna);
		eliminaButton.setBounds(409, 657, 125, 58);
		add(eliminaButton);
		
		modificaButton = new JButton("Modifica");
		modificaButton.addActionListener(listenerModificaButton());
		modificaButton.setForeground(bluCobalto);
		modificaButton.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 16));
		modificaButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		modificaButton.setBackground(panna);
		modificaButton.setBounds(276, 657, 126, 58);
		add(modificaButton);
		
		presenzeScrollPane = new JScrollPane();
		presenzeScrollPane.setBorder(new LineBorder(new Color(255, 255, 255), 3));
		presenzeScrollPane.setBounds(979, 64, 259, 581);
		add(presenzeScrollPane);
		
		presenzeList = new JList();
		presenzeList.setSelectionForeground(bluCobalto);
		presenzeList.setSelectionBackground(new Color(255, 255, 255));
		presenzeList.setForeground(new Color(255, 255, 255));
		presenzeList.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		presenzeList.setBackground(bluCobalto);
		presenzeList.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		presenzeScrollPane.setViewportView(presenzeList);
		
		titoloListaPresenzeLabel = new JLabel("PRESENZE");
		titoloListaPresenzeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titoloListaPresenzeLabel.setForeground(panna);
		titoloListaPresenzeLabel.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 30));
		titoloListaPresenzeLabel.setBounds(1024, 16, 179, 39);
		add(titoloListaPresenzeLabel);
		
		associaParoleChiaveButton = new JButton("Associa Parole Chiave");
		associaParoleChiaveButton.addActionListener(listenerAssociaParoleChiaveButton());
		associaParoleChiaveButton.setForeground(bluCobalto);
		associaParoleChiaveButton.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 17));
		associaParoleChiaveButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		associaParoleChiaveButton.setBackground(panna);
		associaParoleChiaveButton.setBounds(565, 656, 386, 58);
		add(associaParoleChiaveButton);
		
		paroleChiaveScrollPanel = new JScrollPane();
		paroleChiaveScrollPanel.setBorder(new LineBorder(new Color(255, 255, 255), 3));
		paroleChiaveScrollPanel.setBounds(565, 426, 386, 219);
		add(paroleChiaveScrollPanel);
		
		paroleChiaveList = new JList();
		paroleChiaveList.setSelectionForeground(bluCobalto);
		paroleChiaveList.setSelectionBackground(panna);
		paroleChiaveList.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		paroleChiaveList.setBackground(bluCobalto);
		paroleChiaveList.setForeground(panna);
		paroleChiaveList.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 13));
		paroleChiaveScrollPanel.setViewportView(paroleChiaveList);
		
		titoloListaParoleChiaveLabel = new JLabel("PAROLE CHIAVE");
		titoloListaParoleChiaveLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titoloListaParoleChiaveLabel.setForeground(panna);
		titoloListaParoleChiaveLabel.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 30));
		titoloListaParoleChiaveLabel.setBounds(635, 377, 259, 39);
		add(titoloListaParoleChiaveLabel);
		
		aggiungiPresenzeButton = new JButton("Aggiungi Presenze");
		aggiungiPresenzeButton.addActionListener(listenerAggiungiPresenzeButton());
		aggiungiPresenzeButton.setForeground(bluCobalto);
		aggiungiPresenzeButton.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 17));
		aggiungiPresenzeButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		aggiungiPresenzeButton.setBackground(panna);
		aggiungiPresenzeButton.setBounds(979, 656, 259, 58);
		add(aggiungiPresenzeButton);
		
		paroleChiaveFiltroScrollPanel = new JScrollPane();
		paroleChiaveFiltroScrollPanel.setBorder(new LineBorder(Color.WHITE, 3));
		paroleChiaveFiltroScrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		paroleChiaveFiltroScrollPanel.setBounds(10, 286, 226, 337);
		getFiltriPanel().add(paroleChiaveFiltroScrollPanel);
		
		paroleChiaveFiltroList = new JCheckBoxList();
		paroleChiaveFiltroList.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 13));
		paroleChiaveFiltroList.setForeground(bluScuro);
		paroleChiaveFiltroList.setBackground(panna);
		paroleChiaveFiltroList.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		paroleChiaveFiltroList.setSelectionForeground(panna);
		paroleChiaveFiltroList.setSelectionBackground(rosso);
		paroleChiaveFiltroScrollPanel.setViewportView(paroleChiaveFiltroList);
		
		paroleChiaveFiltroLabel = new JLabel("Parole Chiave:");
		paroleChiaveFiltroLabel.setForeground(panna);
		paroleChiaveFiltroLabel.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 13));
		paroleChiaveFiltroLabel.setBounds(10, 261, 115, 14);
		getFiltriPanel().add(paroleChiaveFiltroLabel);
		
		ignoraDataRadioButton = new JRadioButton("Ignora");
		ignoraDataRadioButton.setSelected(true);
		ignoraDataRadioButton.setForeground(panna);
		ignoraDataRadioButton.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 13));
		ignoraDataRadioButton.setBackground(new Color(34, 44, 57));
		ignoraDataRadioButton.setBounds(148, 150, 76, 18);
		getFiltriPanel().add(ignoraDataRadioButton);
		
		dataFiltroLabel = new JLabel("Data:");
		dataFiltroLabel.setForeground(panna);
		dataFiltroLabel.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 13));
		dataFiltroLabel.setBounds(10, 153, 115, 14);
		getFiltriPanel().add(dataFiltroLabel);
		
		selezioneDataInizialeFiltroLabel = new JLabel("da:");
		selezioneDataInizialeFiltroLabel.setForeground(panna);
		selezioneDataInizialeFiltroLabel.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 13));
		selezioneDataInizialeFiltroLabel.setBounds(73, 190, 26, 14);
		getFiltriPanel().add(selezioneDataInizialeFiltroLabel);
		
		selezioneDataFinaleFiltroLabel = new JLabel("a:");
		selezioneDataFinaleFiltroLabel.setForeground(panna);
		selezioneDataFinaleFiltroLabel.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 13));
		selezioneDataFinaleFiltroLabel.setBounds(73, 222, 26, 14);
		getFiltriPanel().add(selezioneDataFinaleFiltroLabel);
		
		selezioneDataInizialeFiltroDateChooser = new JDateChooser();
		selezioneDataInizialeFiltroDateChooser.setDateFormatString("dd MM yyyy");
		selezioneDataInizialeFiltroDateChooser.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		selezioneDataInizialeFiltroDateChooser.setBounds(109, 184, 127, 20);
		getFiltriPanel().add(selezioneDataInizialeFiltroDateChooser);
		
		selezioneDataFinaleFiltroDateChooser = new JDateChooser();
		selezioneDataFinaleFiltroDateChooser.setDateFormatString("dd MM yyyy");
		selezioneDataFinaleFiltroDateChooser.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		selezioneDataFinaleFiltroDateChooser.setBounds(109, 215, 127, 20);
		getFiltriPanel().add(selezioneDataFinaleFiltroDateChooser);
		
		getAscDescToggle().setText("Più recenti");
		getAscDescToggle().addActionListener(listenerAscDescToggle());
		
		getRefreshButton().addActionListener(listenerRefreshButton());

	}

	
	//LISTENERS ASTRATTI
	public abstract ActionListener listenerEliminaButton();
	public abstract ActionListener listenerModificaButton();
	public abstract ActionListener listenerAssociaParoleChiaveButton();
	public abstract ActionListener listenerAggiungiPresenzeButton();
	public abstract ActionListener listenerRefreshButton();
	public abstract ActionListener listenerAscDescToggle();

	//GETTERS E SETTERS

	public JLabel getTitoloPannelloLezioniLabel() {
		return titoloPannelloLezioniLabel;
	}
	public void setTitoloPannelloLezioniLabel(JLabel titoloPannelloLezioniLabel) {
		this.titoloPannelloLezioniLabel = titoloPannelloLezioniLabel;
	}
	public JPanel getInformazioniGeneraliLezionePanel() {
		return informazioniGeneraliLezionePanel;
	}
	public void setInformazioniGeneraliLezionePanel(JPanel informazioniGeneraliLezionePanel) {
		this.informazioniGeneraliLezionePanel = informazioniGeneraliLezionePanel;
	}
	public JTextPane getNomeDellaLezioneLabel() {
		return nomeDellaLezioneLabel;
	}
	public void setNomeDellaLezioneLabel(JTextPane nomeDellaLezioneLabel) {
		this.nomeDellaLezioneLabel = nomeDellaLezioneLabel;
	}
	public JTextPane getDataField() {
		return dataField;
	}
	public void setDataField(JTextPane dataField) {
		this.dataField = dataField;
	}
	public JTextPane getOraInizioField() {
		return oraInizioField;
	}
	public void setOraInizioField(JTextPane oraInizioField) {
		this.oraInizioField = oraInizioField;
	}
	public JTextPane getOraFineField() {
		return oraFineField;
	}
	public void setOraFineField(JTextPane oraFineField) {
		this.oraFineField = oraFineField;
	}
	public JTextPane getProfessoreField() {
		return professoreField;
	}
	public void setProfessoreField(JTextPane professoreField) {
		this.professoreField = professoreField;
	}
	public JTextPane getDelCorsoField() {
		return delCorsoField;
	}
	public void setDelCorsoField(JTextPane delCorsoField) {
		this.delCorsoField = delCorsoField;
	}
	public JTextPane getDescrizioneField() {
		return descrizioneField;
	}
	public void setDescrizioneField(JTextPane descrizioneField) {
		this.descrizioneField = descrizioneField;
	}
	public JButton getEliminaButton() {
		return eliminaButton;
	}
	public void setEliminaButton(JButton eliminaButton) {
		this.eliminaButton = eliminaButton;
	}
	public JButton getModificaButton() {
		return modificaButton;
	}
	public void setModificaButton(JButton modificaButton) {
		this.modificaButton = modificaButton;
	}
	public JList getPresenzeList() {
		return presenzeList;
	}
	public void setPresenzeList(JList presenzeList) {
		this.presenzeList = presenzeList;
	}
	public JScrollPane getPresenzeScrollPane() {
		return presenzeScrollPane;
	}
	public void setPresenzeScrollPane(JScrollPane presenzeScrollPane) {
		this.presenzeScrollPane = presenzeScrollPane;
	}
	public JLabel getTitoloListaPresenzeLabel() {
		return titoloListaPresenzeLabel;
	}
	public void setTitoloListaPresenzeLabel(JLabel titoloListaPresenzeLabel) {
		this.titoloListaPresenzeLabel = titoloListaPresenzeLabel;
	}
	public JList getParoleChiaveList() {
		return paroleChiaveList;
	}
	public void setParoleChiaveList(JList paroleChiaveList) {
		this.paroleChiaveList = paroleChiaveList;
	}
	public JCheckBoxList getParoleChiaveFiltroList() {
		return paroleChiaveFiltroList;
	}
	public void setParoleChiaveFiltroList(JCheckBoxList paroleChiaveFiltroList) {
		this.paroleChiaveFiltroList = paroleChiaveFiltroList;
	}
	public JRadioButton getIgnoraDataRadioButton() {
		return ignoraDataRadioButton;
	}
	public void setIgnoraDataRadioButton(JRadioButton ignoraDataRadioButton) {
		this.ignoraDataRadioButton = ignoraDataRadioButton;
	}
	public JSpinner getDataMinimaFiltroSpinner() {
		return dataMinimaFiltroSpinner;
	}
	public void setDataMinimaFiltroSpinner(JSpinner dataMinimaFiltroSpinner) {
		this.dataMinimaFiltroSpinner = dataMinimaFiltroSpinner;
	}
	public JSpinner getDataMassimaFiltroSpinner() {
		return dataMassimaFiltroSpinner;
	}
	public void setDataMassimaFiltroSpinner(JSpinner dataMassimaFiltroSpinner) {
		this.dataMassimaFiltroSpinner = dataMassimaFiltroSpinner;
	}
	public JDateChooser getSelezioneDataInizialeFiltroDateChooser() {
		return selezioneDataInizialeFiltroDateChooser;
	}
	public void setSelezioneDataInizialeFiltroDateChooser(JDateChooser selezioneDataInizialeFiltroDateChooser) {
		this.selezioneDataInizialeFiltroDateChooser = selezioneDataInizialeFiltroDateChooser;
	}
	public JDateChooser getSelezioneDataFinaleFiltroDateChooser() {
		return selezioneDataFinaleFiltroDateChooser;
	}
	public void setSelezioneDataFinaleFiltroDateChooser(JDateChooser selezioneDataFinaleFiltroDateChooser) {
		this.selezioneDataFinaleFiltroDateChooser = selezioneDataFinaleFiltroDateChooser;
	}
	public JLabel getDataLabel() {
		return dataLabel;
	}
	public void setDataLabel(JLabel dataLabel) {
		this.dataLabel = dataLabel;
	}
	public JLabel getOraInizioLabel() {
		return oraInizioLabel;
	}
	public void setOraInizioLabel(JLabel oraInizioLabel) {
		this.oraInizioLabel = oraInizioLabel;
	}
	public JLabel getOraFineLabel() {
		return oraFineLabel;
	}
	public void setOraFineLabel(JLabel oraFineLabel) {
		this.oraFineLabel = oraFineLabel;
	}
	public JLabel getDescrizioneLabel() {
		return descrizioneLabel;
	}
	public void setDescrizioneLabel(JLabel descrizioneLabel) {
		this.descrizioneLabel = descrizioneLabel;
	}
	public JLabel getTenutaDaLabel() {
		return tenutaDaLabel;
	}
	public void setTenutaDaLabel(JLabel tenutaDaLabel) {
		this.tenutaDaLabel = tenutaDaLabel;
	}
	public JLabel getDelCorsoLabel() {
		return delCorsoLabel;
	}
	public void setDelCorsoLabel(JLabel delCorsoLabel) {
		this.delCorsoLabel = delCorsoLabel;
	}
	public JButton getAssociaParoleChiaveButton() {
		return associaParoleChiaveButton;
	}
	public void setAssociaParoleChiaveButton(JButton associaParoleChiaveButton) {
		this.associaParoleChiaveButton = associaParoleChiaveButton;
	}
	public JScrollPane getParoleChiaveScrollPanel() {
		return paroleChiaveScrollPanel;
	}
	public void setParoleChiaveScrollPanel(JScrollPane paroleChiaveScrollPanel) {
		this.paroleChiaveScrollPanel = paroleChiaveScrollPanel;
	}
	public JScrollPane getDescrizioneScrollPane() {
		return descrizioneScrollPane;
	}
	public void setDescrizioneScrollPane(JScrollPane descrizioneScrollPane) {
		this.descrizioneScrollPane = descrizioneScrollPane;
	}
	public JLabel getTitoloListaParoleChiaveLabel() {
		return titoloListaParoleChiaveLabel;
	}
	public void setTitoloListaParoleChiaveLabel(JLabel titoloListaParoleChiaveLabel) {
		this.titoloListaParoleChiaveLabel = titoloListaParoleChiaveLabel;
	}
	public JButton getAggiungiPresenzeButton() {
		return aggiungiPresenzeButton;
	}
	public void setAggiungiPresenzeButton(JButton aggiungiPresenzeButton) {
		this.aggiungiPresenzeButton = aggiungiPresenzeButton;
	}
	public JScrollPane getParoleChiaveFiltroScrollPanel() {
		return paroleChiaveFiltroScrollPanel;
	}
	public void setParoleChiaveFiltroScrollPanel(JScrollPane paroleChiaveFiltroScrollPanel) {
		this.paroleChiaveFiltroScrollPanel = paroleChiaveFiltroScrollPanel;
	}
	public JLabel getParoleChiaveFiltroLabel() {
		return paroleChiaveFiltroLabel;
	}
	public void setParoleChiaveFiltroLabel(JLabel paroleChiaveFiltroLabel) {
		this.paroleChiaveFiltroLabel = paroleChiaveFiltroLabel;
	}
	public JLabel getDataFiltroLabel() {
		return dataFiltroLabel;
	}
	public void setDataFiltroLabel(JLabel dataFiltroLabel) {
		this.dataFiltroLabel = dataFiltroLabel;
	}
	public JLabel getSelezioneDataInizialeFiltroLabel() {
		return selezioneDataInizialeFiltroLabel;
	}
	public void setSelezioneDataInizialeFiltroLabel(JLabel selezioneDataInizialeFiltroLabel) {
		this.selezioneDataInizialeFiltroLabel = selezioneDataInizialeFiltroLabel;
	}
	public JLabel getSelezioneDataFinaleFiltroLabel() {
		return selezioneDataFinaleFiltroLabel;
	}
	public void setSelezioneDataFinaleFiltroLabel(JLabel selezioneDataFinaleFiltroLabel) {
		this.selezioneDataFinaleFiltroLabel = selezioneDataFinaleFiltroLabel;
	}
	public JTextPane getDurataField() {
		return durataField;
	}
	public void setDurataField(JTextPane durataField) {
		this.durataField = durataField;
	}
	public JLabel getDurataLabel() {
		return durataLabel;
	}
	public void setDurataLabel(JLabel durataLabel) {
		this.durataLabel = durataLabel;
	}
}
