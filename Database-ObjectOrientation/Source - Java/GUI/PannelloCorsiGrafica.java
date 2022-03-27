package GUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;

import Entita.JCheckBoxList;

public abstract class PannelloCorsiGrafica extends PannelloBase {

	private JTextPane nomeCorsoSelezionato;
	private JTextArea descrizioneCorsoSelezionato;
	private JTextPane numMaxPartecipantiCorsoSelezionato;
	private JTextPane percMinSuperamentoCorsoSelezionato;
	private JTextPane statoCorsoSelezionato;
	private JPanel ledCorsoTerminato;
	private JLabel titoloPannelloCorsiLabel;
	private JPanel informazioniGeneraliCorsoPanel;
	private JScrollPane descrizioneScrollPane;
	private JLabel numMaxPartecipantiLabel ;
	private JLabel percMinPresenzeLabel;
	private JLabel descrizioneLabel;
	private JPanel statoCorsoPanel;
	private JButton terminaButton;
	private JLabel statoCorsoLabel;
	private JList composizioneCorsoList;
	private JScrollPane composizioneCorsoPanel;
	private JToggleButton toggleListaComposizione;
	private JButton toggleAddButton;
	private JTable statisticheTable;
	private JLabel numPresenzeLabel;
	private JLabel minimoLabel;
	private JLabel massimoLabel;
	private JProgressBar mediaRiempimentoProgressBar;
	private JLabel percMinRiempimentoLabel;
	private JTextPane numMedioPartecipantiCorsoSelezionato;
	private JPanel statisticheCorsoPanel;
	private JScrollPane statisticheTabellaScrollPane;
	private JTextPane statisticheLabel;
	private JLabel filtriStatoCorsoLabel;
	private JLabel filtriAreeTematicheLabel;
	private JLabel mediaLabel;
	private JButton eliminaButton;
	private JScrollPane filtriAreeTematichePane;
	private JButton modificaButton;
	private JRadioButton ignoraStatoRadioButton;
	private JRadioButton inCorsoStatoRadioButton;
	private JRadioButton terminatoStatoRadioButton;
	private JButton nuovoButton;
	private JCheckBoxList areeTematicheFiltriList;
	
	
	public PannelloCorsiGrafica() {

		getList().setSelectionForeground(panna);
		getList().setSelectionBackground(rosso);
		getList().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		getList().setForeground(new Color(29, 53, 87));
		getList().setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		getList().setBackground(panna);
		getList().setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));
		getList().setBounds(277, 63, 238, 683);
		getList().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setLayout(null);
		
		titoloPannelloCorsiLabel = new JLabel("CORSI");
		titoloPannelloCorsiLabel.setBorder(new MatteBorder(0, 0, 2, 0, panna));
		titoloPannelloCorsiLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titoloPannelloCorsiLabel.setBounds(337, 11, 130, 39);
		titoloPannelloCorsiLabel.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 33));
		titoloPannelloCorsiLabel.setBackground(blu);
		titoloPannelloCorsiLabel.setForeground(panna);
		add(titoloPannelloCorsiLabel);
		
		informazioniGeneraliCorsoPanel = new JPanel();
		informazioniGeneraliCorsoPanel.setForeground(panna);
		informazioniGeneraliCorsoPanel.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(255, 255, 255), new Color(255, 255, 255), null, null));
		informazioniGeneraliCorsoPanel.setBounds(566, 62, 477, 215);
		informazioniGeneraliCorsoPanel.setBackground(bluCobalto);
		add(informazioniGeneraliCorsoPanel);
		informazioniGeneraliCorsoPanel.setLayout(null);
		
		nomeCorsoSelezionato = new JTextPane();
		nomeCorsoSelezionato.setText("Nome del corso.");
		nomeCorsoSelezionato.setForeground(panna);
		nomeCorsoSelezionato.setBackground(new Color(29, 53, 87));
		nomeCorsoSelezionato.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));
		nomeCorsoSelezionato.setBounds(10, 11, 257, 20);
		nomeCorsoSelezionato.setEditable(false);
		nomeCorsoSelezionato.setBorder(BorderFactory.createMatteBorder(0,0,1,0, Color.white));
		informazioniGeneraliCorsoPanel.add(nomeCorsoSelezionato);
		
		descrizioneScrollPane = new JScrollPane();
		descrizioneScrollPane.setViewportBorder(null);
		descrizioneScrollPane.setBounds(10, 68, 457, 67);
		informazioniGeneraliCorsoPanel.add(descrizioneScrollPane);
		
		descrizioneCorsoSelezionato = new JTextArea();
		descrizioneCorsoSelezionato.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		descrizioneCorsoSelezionato.setForeground(Color.BLACK);
		descrizioneCorsoSelezionato.setText("Descrizione del corso.");
		descrizioneCorsoSelezionato.setFont(new Font("Microsoft YaHei Light", Font.ITALIC, 13));
		descrizioneCorsoSelezionato.setBackground(panna);
		descrizioneScrollPane.setViewportView(descrizioneCorsoSelezionato);
		descrizioneCorsoSelezionato.setLineWrap(true);
		descrizioneCorsoSelezionato.setEditable(false);
		
		numMaxPartecipantiLabel = new JLabel("N. massimo partecipanti:");
		numMaxPartecipantiLabel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		numMaxPartecipantiLabel.setForeground(panna);
		numMaxPartecipantiLabel.setBounds(10, 155, 153, 14);
		informazioniGeneraliCorsoPanel.add(numMaxPartecipantiLabel);
		
		percMinPresenzeLabel = new JLabel("Percentuale minima di presenze per superare il corso: ");
		percMinPresenzeLabel.setForeground(panna);
		percMinPresenzeLabel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		percMinPresenzeLabel.setBounds(10, 184, 319, 20);
		informazioniGeneraliCorsoPanel.add(percMinPresenzeLabel);
		
		descrizioneLabel = new JLabel("Descrizione");
		descrizioneLabel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		descrizioneLabel.setForeground(panna);
		descrizioneLabel.setBounds(10, 43, 74, 14);
		informazioniGeneraliCorsoPanel.add(descrizioneLabel);
		
		numMaxPartecipantiCorsoSelezionato = new JTextPane();
		numMaxPartecipantiCorsoSelezionato.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		numMaxPartecipantiCorsoSelezionato.setBackground(new Color(29, 53, 87));
		numMaxPartecipantiCorsoSelezionato.setForeground(new Color(255, 255, 255));
		numMaxPartecipantiCorsoSelezionato.setEditable(false);
		numMaxPartecipantiCorsoSelezionato.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 11));
		numMaxPartecipantiCorsoSelezionato.setBounds(173, 153, 37, 20);
		informazioniGeneraliCorsoPanel.add(numMaxPartecipantiCorsoSelezionato);
		
		percMinSuperamentoCorsoSelezionato = new JTextPane();
		percMinSuperamentoCorsoSelezionato.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		percMinSuperamentoCorsoSelezionato.setBackground(new Color(29, 53, 87));
		percMinSuperamentoCorsoSelezionato.setForeground(panna);
		percMinSuperamentoCorsoSelezionato.setEditable(false);
		percMinSuperamentoCorsoSelezionato.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 11));
		percMinSuperamentoCorsoSelezionato.setBounds(339, 184, 37, 20);
		informazioniGeneraliCorsoPanel.add(percMinSuperamentoCorsoSelezionato);
		
		statoCorsoPanel = new JPanel();
		statoCorsoPanel.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(255, 255, 255), new Color(255, 255, 255), null, null));
		statoCorsoPanel.setBounds(1070, 84, 137, 169);
		statoCorsoPanel.setBackground(new Color(29, 53, 87));
		add(statoCorsoPanel);
		statoCorsoPanel.setLayout(null);
		
		terminaButton = new JButton("Termina");
		terminaButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		terminaButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		terminaButton.addActionListener(listenerTerminaButton());
		terminaButton.setForeground(blu);
		terminaButton.setBackground(panna);
		terminaButton.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 13));
		terminaButton.setBounds(10, 117, 117, 41);
		statoCorsoPanel.add(terminaButton);
		
		statoCorsoLabel = new JLabel("Stato:");
		statoCorsoLabel.setForeground(panna);
		statoCorsoLabel.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 15));
		statoCorsoLabel.setBounds(44, 11, 46, 14);
		statoCorsoPanel.add(statoCorsoLabel);
		
		ledCorsoTerminato = new JPanel();
		ledCorsoTerminato.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		ledCorsoTerminato.setBounds(44, 36, 46, 41);
		statoCorsoPanel.add(ledCorsoTerminato);
		
		statoCorsoSelezionato = new JTextPane();
		statoCorsoSelezionato.setForeground(panna);
		statoCorsoSelezionato.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		statoCorsoSelezionato.setBackground(new Color(29, 53, 87));
		statoCorsoSelezionato.setEditable(false);
		statoCorsoSelezionato.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));
		statoCorsoSelezionato.setBounds(25, 88, 90, 20);
		statoCorsoPanel.add(statoCorsoSelezionato);
		
		composizioneCorsoPanel = new JScrollPane();
		composizioneCorsoPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		composizioneCorsoPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		composizioneCorsoPanel.setBounds(566, 344, 248, 347);
		add(composizioneCorsoPanel);
		
		composizioneCorsoList = new JList();
		composizioneCorsoList.setSelectionForeground(bluCobalto);
		composizioneCorsoList.setSelectionBackground(panna);
		composizioneCorsoList.setForeground(panna);
		composizioneCorsoList.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		composizioneCorsoList.setBackground(bluCobalto);
		composizioneCorsoList.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 13));
		composizioneCorsoPanel.setViewportView(composizioneCorsoList);
		
		toggleListaComposizione = new JToggleButton("Studenti Iscritti");
		toggleListaComposizione.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		toggleListaComposizione.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		toggleListaComposizione.setForeground(blu);
		toggleListaComposizione.setBackground(panna);
		toggleListaComposizione.addActionListener(listenerToggleListaComposizione());
		toggleListaComposizione.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		toggleListaComposizione.setBounds(566, 320, 248, 23);
		add(toggleListaComposizione);
		
		toggleAddButton = new JButton("Iscrivi Studenti");
		toggleAddButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		toggleAddButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		toggleAddButton.addActionListener(listenerToggleAddButton());
		toggleAddButton.setForeground(blu);
		toggleAddButton.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		toggleAddButton.setBackground(panna);
		toggleAddButton.setBounds(566, 692, 248, 23);
		add(toggleAddButton);
		
		statisticheCorsoPanel = new JPanel();
		statisticheCorsoPanel.setLayout(null);
		statisticheCorsoPanel.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(255, 255, 255), new Color(255, 255, 255), null, null));
		statisticheCorsoPanel.setBackground(new Color(29, 53, 87));
		statisticheCorsoPanel.setBounds(868, 320, 339, 295);
		add(statisticheCorsoPanel);
		
		statisticheTabellaScrollPane = new JScrollPane();
		statisticheTabellaScrollPane.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		statisticheTabellaScrollPane.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 11));
		statisticheTabellaScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		statisticheTabellaScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		statisticheTabellaScrollPane.setBounds(77, 84, 252, 84);
		statisticheCorsoPanel.add(statisticheTabellaScrollPane);
		
		statisticheTable = new JTable();
		statisticheTable.setBackground(panna);
		statisticheTable.setEnabled(false);
		statisticheTabellaScrollPane.setViewportView(statisticheTable);
		statisticheTable.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
				{null, null},
			},
			new String[] {
				"Lezione", "Presenze"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		statisticheTable.getColumnModel().getColumn(0).setResizable(false);
		statisticheTable.getColumnModel().getColumn(1).setResizable(false);
		statisticheTable.setRowSelectionAllowed(false);
		statisticheTable.setRowHeight(30);
		statisticheTable.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
		statisticheTable.setGridColor(bluScuro);
		statisticheTable.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		statisticheLabel = new JTextPane();
		statisticheLabel.setText("STATISTICHE CORSO");
		statisticheLabel.setForeground(panna);
		statisticheLabel.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));
		statisticheLabel.setEditable(false);
		statisticheLabel.setBorder(BorderFactory.createMatteBorder(0,0,1,0, Color.white));
		statisticheLabel.setBackground(new Color(29, 53, 87));
		statisticheLabel.setBounds(10, 11, 189, 20);
		statisticheCorsoPanel.add(statisticheLabel);
		
		numPresenzeLabel = new JLabel("Numero di presenze...");
		numPresenzeLabel.setForeground(panna);
		numPresenzeLabel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		numPresenzeLabel.setBounds(24, 50, 144, 14);
		statisticheCorsoPanel.add(numPresenzeLabel);
		
		minimoLabel = new JLabel("minimo");
		minimoLabel.setForeground(panna);
		minimoLabel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		minimoLabel.setBounds(18, 118, 43, 14);
		statisticheCorsoPanel.add(minimoLabel);
		
		massimoLabel = new JLabel("massimo");
		massimoLabel.setForeground(panna);
		massimoLabel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		massimoLabel.setBounds(10, 148, 57, 14);
		statisticheCorsoPanel.add(massimoLabel);
		
		mediaRiempimentoProgressBar = new JProgressBar();
		mediaRiempimentoProgressBar.setBackground(panna);
		mediaRiempimentoProgressBar.setForeground(new Color(69, 123, 157));
		mediaRiempimentoProgressBar.setFont(new Font("Microsoft YaHei UI", Font.BOLD | Font.ITALIC, 13));
		mediaRiempimentoProgressBar.setStringPainted(true);
		mediaRiempimentoProgressBar.setBounds(20, 258, 302, 20);
		statisticheCorsoPanel.add(mediaRiempimentoProgressBar);
		
		percMinRiempimentoLabel = new JLabel("Percentuale media riempimento:");
		percMinRiempimentoLabel.setForeground(panna);
		percMinRiempimentoLabel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		percMinRiempimentoLabel.setBounds(20, 233, 196, 14);
		statisticheCorsoPanel.add(percMinRiempimentoLabel);
		
		mediaLabel = new JLabel("Numero di presenze in media:");
		mediaLabel.setForeground(panna);
		mediaLabel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		mediaLabel.setBounds(20, 195, 196, 14);
		statisticheCorsoPanel.add(mediaLabel);
		
		numMedioPartecipantiCorsoSelezionato = new JTextPane();
		numMedioPartecipantiCorsoSelezionato.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		numMedioPartecipantiCorsoSelezionato.setBackground(new Color(29, 53, 87));
		numMedioPartecipantiCorsoSelezionato.setForeground(new Color(255, 255, 255));
		numMedioPartecipantiCorsoSelezionato.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 11));
		numMedioPartecipantiCorsoSelezionato.setEditable(false);
		numMedioPartecipantiCorsoSelezionato.setBounds(221, 189, 37, 20);
		statisticheCorsoPanel.add(numMedioPartecipantiCorsoSelezionato);
		
		eliminaButton = new JButton("Elimina");
		eliminaButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		eliminaButton.addActionListener(listenerEliminaButton());
		eliminaButton.setForeground(blu);
		eliminaButton.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 16));
		eliminaButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		eliminaButton.setBackground(panna);
		eliminaButton.setBounds(449, 657, 83, 58);
		add(eliminaButton);
		
		modificaButton = new JButton("Modifica");
		modificaButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		modificaButton.addActionListener(listenerModificaButton());
		modificaButton.setForeground(blu);
		modificaButton.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 16));
		modificaButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		modificaButton.setBackground(panna);
		modificaButton.setBounds(362, 657, 83, 58);
		add(modificaButton);
		
		nuovoButton = new JButton("Nuovo");
		nuovoButton.addActionListener(listenerNuovoButton());
		nuovoButton.setForeground(blu);
		nuovoButton.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 16));
		nuovoButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		nuovoButton.setBackground(panna);
		nuovoButton.setBounds(275, 657, 83, 58);
		add(nuovoButton);
		
		filtriAreeTematichePane = new JScrollPane();
		filtriAreeTematichePane.setBorder(new LineBorder(new Color(255, 255, 255), 3));
		filtriAreeTematichePane.setBackground(new Color(255, 255, 255));
		filtriAreeTematichePane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		filtriAreeTematichePane.setBounds(10, 280, 226, 343);
		getFiltriPanel().add(filtriAreeTematichePane);
		
		areeTematicheFiltriList = new JCheckBoxList();
		areeTematicheFiltriList.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 13));
		areeTematicheFiltriList.setForeground(bluScuro);
		areeTematicheFiltriList.setSelectionForeground(panna);
		areeTematicheFiltriList.setSelectionBackground(rosso);
		areeTematicheFiltriList.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		areeTematicheFiltriList.setBackground(new Color(241, 250, 238));
		areeTematicheFiltriList.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		filtriAreeTematichePane.setViewportView(areeTematicheFiltriList);
		
		getAscDescToggle().setText("A-Z");
		getAscDescToggle().addActionListener(listenerAscDescToggle());
		
		ignoraStatoRadioButton = new JRadioButton("Ignora");
		ignoraStatoRadioButton.addActionListener(listenerIgnoraStatoRadioButton());
		ignoraStatoRadioButton.setForeground(new Color(255, 255, 255));
		ignoraStatoRadioButton.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 13));
		ignoraStatoRadioButton.setBackground(new Color(34, 44, 57));
		ignoraStatoRadioButton.setSelected(true);
		ignoraStatoRadioButton.setBounds(121, 147, 76, 18);
		getFiltriPanel().add(ignoraStatoRadioButton);
		
		inCorsoStatoRadioButton = new JRadioButton("In corso");
		inCorsoStatoRadioButton.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 13));
		inCorsoStatoRadioButton.setForeground(new Color(255, 255, 255));
		inCorsoStatoRadioButton.addActionListener(listenerInCorsoStatoRadioButton());
		inCorsoStatoRadioButton.setBounds(26, 185, 109, 23);
		inCorsoStatoRadioButton.setBackground(new Color(34, 44, 57));
		getFiltriPanel().add(inCorsoStatoRadioButton);
		
		terminatoStatoRadioButton = new JRadioButton("Terminato");
		terminatoStatoRadioButton.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 13));
		terminatoStatoRadioButton.setForeground(new Color(255, 255, 255));
		terminatoStatoRadioButton.addActionListener(listenerTerminatoStatoRadioButton());
		terminatoStatoRadioButton.setBounds(26, 211, 109, 23);
		terminatoStatoRadioButton.setBackground(new Color(34, 44, 57));
		getFiltriPanel().add(terminatoStatoRadioButton);
		
		filtriAreeTematicheLabel = new JLabel("Aree tematiche:");
		filtriAreeTematicheLabel.setForeground(panna);
		filtriAreeTematicheLabel.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 13));
		filtriAreeTematicheLabel.setBounds(10, 255, 115, 14);
		getFiltriPanel().add(filtriAreeTematicheLabel);
		
		filtriStatoCorsoLabel = new JLabel("Stato corso:");
		filtriStatoCorsoLabel.setForeground(panna);
		filtriStatoCorsoLabel.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 13));
		filtriStatoCorsoLabel.setBounds(10, 149, 82, 14);
		getFiltriPanel().add(filtriStatoCorsoLabel);
		
		getRefreshButton().setLocation(11, 658);
		getRefreshButton().addActionListener(listenerRefreshButton());
		
	}

	
	//LISTENERS ASTRATTI
	public abstract ActionListener listenerTerminaButton();
	public abstract ActionListener listenerToggleListaComposizione();
	public abstract ActionListener listenerToggleAddButton();
	public abstract ActionListener listenerEliminaButton();
	public abstract ActionListener listenerModificaButton();
	public abstract ActionListener listenerNuovoButton();
	public abstract ActionListener listenerAscDescToggle();
	public abstract ActionListener listenerIgnoraStatoRadioButton();
	public abstract ActionListener listenerInCorsoStatoRadioButton();
	public abstract ActionListener listenerTerminatoStatoRadioButton();
	public abstract ActionListener listenerRefreshButton();

	//GETTERS E SETTERS

	public JTextPane getNomeCorsoSelezionato() {
		return nomeCorsoSelezionato;
	}
	public void setNomeCorsoSelezionato(JTextPane nomeCorsoSelezionato) {
		this.nomeCorsoSelezionato = nomeCorsoSelezionato;
	}
	public JTextArea getDescrizioneCorsoSelezionato() {
		return descrizioneCorsoSelezionato;
	}
	public void setDescrizioneCorsoSelezionato(JTextArea descrizioneCorsoSelezionato) {
		this.descrizioneCorsoSelezionato = descrizioneCorsoSelezionato;
	}
	public JTextPane getNumMaxPartecipantiCorsoSelezionato() {
		return numMaxPartecipantiCorsoSelezionato;
	}
	public void setNumMaxPartecipantiCorsoSelezionato(JTextPane numMaxPartecipantiCorsoSelezionato) {
		this.numMaxPartecipantiCorsoSelezionato = numMaxPartecipantiCorsoSelezionato;
	}
	public JTextPane getPercMinSuperamentoCorsoSelezionato() {
		return percMinSuperamentoCorsoSelezionato;
	}
	public void setPercMinSuperamentoCorsoSelezionato(JTextPane percMinSuperamentoCorsoSelezionato) {
		this.percMinSuperamentoCorsoSelezionato = percMinSuperamentoCorsoSelezionato;
	}
	public JTextPane getStatoCorsoSelezionato() {
		return statoCorsoSelezionato;
	}
	public void setStatoCorsoSelezionato(JTextPane statoCorsoSelezionato) {
		this.statoCorsoSelezionato = statoCorsoSelezionato;
	}
	public JPanel getLedCorsoTerminato() {
		return ledCorsoTerminato;
	}
	public void setLedCorsoTerminato(JPanel ledCorsoTerminato) {
		this.ledCorsoTerminato = ledCorsoTerminato;
	}
	public JLabel getTitoloPannelloCorsiLabel() {
		return titoloPannelloCorsiLabel;
	}
	public void setTitoloPannelloCorsiLabel(JLabel titoloPannelloCorsiLabel) {
		this.titoloPannelloCorsiLabel = titoloPannelloCorsiLabel;
	}
	public JPanel getInformazioniGeneraliCorsoPanel() {
		return informazioniGeneraliCorsoPanel;
	}
	public void setInformazioniGeneraliCorsoPanel(JPanel informazioniGeneraliCorsoPanel) {
		this.informazioniGeneraliCorsoPanel = informazioniGeneraliCorsoPanel;
	}
	public JScrollPane getDescrizioneScrollPane() {
		return descrizioneScrollPane;
	}
	public void setDescrizioneScrollPane(JScrollPane descrizioneScrollPane) {
		this.descrizioneScrollPane = descrizioneScrollPane;
	}
	public JLabel getNumMaxPartecipantiLabel() {
		return numMaxPartecipantiLabel;
	}
	public void setNumMaxPartecipantiLabel(JLabel numMaxPartecipantiLabel) {
		this.numMaxPartecipantiLabel = numMaxPartecipantiLabel;
	}
	public JLabel getPercMinPresenzeLabel() {
		return percMinPresenzeLabel;
	}
	public void setPercMinPresenzeLabel(JLabel percMinPresenzeLabel) {
		this.percMinPresenzeLabel = percMinPresenzeLabel;
	}
	public JLabel getDescrizioneLabel() {
		return descrizioneLabel;
	}
	public void setDescrizioneLabel(JLabel descrizioneLabel) {
		this.descrizioneLabel = descrizioneLabel;
	}
	public JPanel getStatoCorsoPanel() {
		return statoCorsoPanel;
	}
	public void setStatoCorsoPanel(JPanel statoCorsoPanel) {
		this.statoCorsoPanel = statoCorsoPanel;
	}
	public JButton getTerminaButton() {
		return terminaButton;
	}
	public void setTerminaButton(JButton terminaButton) {
		this.terminaButton = terminaButton;
	}
	public JLabel getStatoCorsoLabel() {
		return statoCorsoLabel;
	}
	public void setStatoCorsoLabel(JLabel statoCorsoLabel) {
		this.statoCorsoLabel = statoCorsoLabel;
	}
	public JList getComposizioneCorsoList() {
		return composizioneCorsoList;
	}
	public void setComposizioneCorsoList(JList composizioneCorsoList) {
		this.composizioneCorsoList = composizioneCorsoList;
	}
	public JScrollPane getComposizioneCorsoPanel() {
		return composizioneCorsoPanel;
	}
	public void setComposizioneCorsoPanel(JScrollPane composizioneCorsoPanel) {
		this.composizioneCorsoPanel = composizioneCorsoPanel;
	}
	public JToggleButton getToggleListaComposizione() {
		return toggleListaComposizione;
	}
	public void setToggleListaComposizione(JToggleButton toggleListaComposizione) {
		this.toggleListaComposizione = toggleListaComposizione;
	}
	public JButton getToggleAddButton() {
		return toggleAddButton;
	}
	public void setToggleAddButton(JButton toggleAddButton) {
		this.toggleAddButton = toggleAddButton;
	}
	public JTable getStatisticheTable() {
		return statisticheTable;
	}
	public void setStatisticheTable(JTable statisticheTable) {
		this.statisticheTable = statisticheTable;
	}
	public JLabel getNumPresenzeLabel() {
		return numPresenzeLabel;
	}
	public void setNumPresenzeLabel(JLabel numPresenzeLabel) {
		this.numPresenzeLabel = numPresenzeLabel;
	}
	public JLabel getMinimoLabel() {
		return minimoLabel;
	}
	public void setMinimoLabel(JLabel minimoLabel) {
		this.minimoLabel = minimoLabel;
	}
	public JLabel getMassimoLabel() {
		return massimoLabel;
	}
	public void setMassimoLabel(JLabel massimoLabel) {
		this.massimoLabel = massimoLabel;
	}
	public JProgressBar getMediaRiempimentoProgressBar() {
		return mediaRiempimentoProgressBar;
	}
	public void setMediaRiempimentoProgressBar(JProgressBar mediaRiempimentoProgressBar) {
		this.mediaRiempimentoProgressBar = mediaRiempimentoProgressBar;
	}
	public JLabel getPercMinRiempimentoLabel() {
		return percMinRiempimentoLabel;
	}
	public void setPercMinRiempimentoLabel(JLabel percMinRiempimentoLabel) {
		this.percMinRiempimentoLabel = percMinRiempimentoLabel;
	}
	public JTextPane getNumMedioPartecipantiCorsoSelezionato() {
		return numMedioPartecipantiCorsoSelezionato;
	}
	public void setNumMedioPartecipantiCorsoSelezionato(JTextPane numMedioPartecipantiCorsoSelezionato) {
		this.numMedioPartecipantiCorsoSelezionato = numMedioPartecipantiCorsoSelezionato;
	}
	public JPanel getStatisticheCorsoPanel() {
		return statisticheCorsoPanel;
	}
	public void setStatisticheCorsoPanel(JPanel statisticheCorsoPanel) {
		this.statisticheCorsoPanel = statisticheCorsoPanel;
	}
	public JScrollPane getStatisticheTabellaScrollPane() {
		return statisticheTabellaScrollPane;
	}
	public void setStatisticheTabellaScrollPane(JScrollPane statisticheTabellaScrollPane) {
		this.statisticheTabellaScrollPane = statisticheTabellaScrollPane;
	}
	public JTextPane getStatisticheLabel() {
		return statisticheLabel;
	}
	public void setStatisticheLabel(JTextPane statisticheLabel) {
		this.statisticheLabel = statisticheLabel;
	}
	public JLabel getFiltriStatoCorsoLabel() {
		return filtriStatoCorsoLabel;
	}
	public void setFiltriStatoCorsoLabel(JLabel filtriStatoCorsoLabel) {
		this.filtriStatoCorsoLabel = filtriStatoCorsoLabel;
	}
	public JLabel getFiltriAreeTematicheLabel() {
		return filtriAreeTematicheLabel;
	}
	public void setFiltriAreeTematicheLabel(JLabel filtriAreeTematicheLabel) {
		this.filtriAreeTematicheLabel = filtriAreeTematicheLabel;
	}
	public JLabel getMediaLabel() {
		return mediaLabel;
	}
	public void setMediaLabel(JLabel mediaLabel) {
		this.mediaLabel = mediaLabel;
	}
	public JButton getEliminaButton() {
		return eliminaButton;
	}
	public void setEliminaButton(JButton eliminaButton) {
		this.eliminaButton = eliminaButton;
	}
	public JScrollPane getFiltriAreeTematichePane() {
		return filtriAreeTematichePane;
	}
	public void setFiltriAreeTematichePane(JScrollPane filtriAreeTematichePane) {
		this.filtriAreeTematichePane = filtriAreeTematichePane;
	}
	public JButton getModificaButton() {
		return modificaButton;
	}
	public void setModificaButton(JButton modificaButton) {
		this.modificaButton = modificaButton;
	}
	public JRadioButton getIgnoraStatoRadioButton() {
		return ignoraStatoRadioButton;
	}
	public void setIgnoraStatoRadioButton(JRadioButton ignoraStatoRadioButton) {
		this.ignoraStatoRadioButton = ignoraStatoRadioButton;
	}
	public JRadioButton getInCorsoStatoRadioButton() {
		return inCorsoStatoRadioButton;
	}
	public void setInCorsoStatoRadioButton(JRadioButton inCorsoStatoRadioButton) {
		this.inCorsoStatoRadioButton = inCorsoStatoRadioButton;
	}
	public JRadioButton getTerminatoStatoRadioButton() {
		return terminatoStatoRadioButton;
	}
	public void setTerminatoStatoRadioButton(JRadioButton terminatoStatoRadioButton) {
		this.terminatoStatoRadioButton = terminatoStatoRadioButton;
	}
	public JButton getNuovoButton() {
		return nuovoButton;
	}
	public void setNuovoButton(JButton nuovoButton) {
		this.nuovoButton = nuovoButton;
	}
	public JCheckBoxList getAreeTematicheFiltriList() {
		return areeTematicheFiltriList;
	}
	public void setAreeTematicheFiltriList(JCheckBoxList areeTematicheFiltriList) {
		this.areeTematicheFiltriList = areeTematicheFiltriList;
	}
		
}
