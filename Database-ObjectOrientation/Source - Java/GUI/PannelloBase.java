package GUI;

import java.awt.Color;

import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.ListModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionListener;

import Controller.Controller;
import Entita.ModelSetter;

import javax.swing.event.ListSelectionEvent;
import javax.swing.border.LineBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import java.awt.Cursor;
import javax.swing.border.SoftBevelBorder;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import javax.swing.JTree;
import javax.swing.JMenu;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.EmptyBorder;
import javax.swing.ListSelectionModel;

public abstract class PannelloBase extends JPanel {
	
	private JList list;
	private JScrollPane listScrollPane;
	private JLabel filtriLabel;
	private JPanel filtriPanel;
	private JButton refreshButton;
	private JToggleButton ascDescToggle;
	private JLabel ordineVisualizzazioneLabel;
	private ModelSetter modelSetter;
	final Color panna;
	final Color bluScuro;
	final Color blu;
	final Color rosso;
	final Color bluCobalto;

	public PannelloBase() {
		
		panna = new Color(241, 250 , 238);
		bluCobalto = new Color(29, 53, 87);
		bluScuro = new Color(32, 44, 57);
		blu = new Color(40, 56, 69);
		rosso = new Color(230, 57, 70);

		modelSetter = new ModelSetter();
		
		setBackground(blu);
		setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		setLayout(null);
		
		listScrollPane = new JScrollPane();
		listScrollPane.setBorder(new LineBorder(rosso, 3));
		listScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		listScrollPane.setBounds(276, 62, 257, 590);
		add(listScrollPane);
		
		list = new JList();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		list.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		list.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 13));
		list.setBackground(panna);
		listScrollPane.setViewportView(list);
		
		filtriPanel = new JPanel();
		filtriPanel.setBackground(new Color(32, 44, 57));
		filtriPanel.setBounds(0, 0, 248, 773);
		filtriPanel.setBorder(null);
		add(filtriPanel);
		filtriPanel.setLayout(null);
		
		filtriLabel = new JLabel("FILTRI");
		filtriLabel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		filtriLabel.setHorizontalAlignment(SwingConstants.CENTER);
		filtriLabel.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 20));
		filtriLabel.setForeground(panna);
		filtriLabel.setBounds(11, 11, 226, 32);
		filtriPanel.add(filtriLabel);
		
		refreshButton = new JButton("REFRESH");
		refreshButton.setForeground(panna);
		refreshButton.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 18));
		refreshButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		refreshButton.setBackground(rosso);
		refreshButton.setBounds(10, 658, 226, 89);
		filtriPanel.add(refreshButton);
			
		ascDescToggle = new JToggleButton("");
		ascDescToggle.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		ascDescToggle.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));
		ascDescToggle.setBackground(panna);
		ascDescToggle.setBounds(10, 81, 226, 41);
		getFiltriPanel().add(ascDescToggle);
		
		ordineVisualizzazioneLabel = new JLabel("Ordine della visualizzazione:");
		ordineVisualizzazioneLabel.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 13));
		ordineVisualizzazioneLabel.setForeground(panna);
		ordineVisualizzazioneLabel.setBounds(10, 56, 199, 14);
		getFiltriPanel().add(ordineVisualizzazioneLabel);

	}

	//Metodi
	public abstract void metodoListener();
	
	public abstract void resetFiltri();
	
	
	//Getter e setter

	public JList getList() {
		return list;
	}
	public void setList(JList list) {
		this.list = list;
	}
	public JPanel getFiltriPanel() {
		return filtriPanel;
	}
	public void setFiltriPanel(JPanel filtriPanel) {
		this.filtriPanel = filtriPanel;
	}
	public JButton getRefreshButton() {
		return refreshButton;
	}
	public void setRefreshButton(JButton refreshButton) {
		this.refreshButton = refreshButton;
	}
	public JToggleButton getAscDescToggle() {
		return ascDescToggle;
	}
	public void setAscDescToggle(JToggleButton ascDescToggle) {
		this.ascDescToggle = ascDescToggle;
	}

	public ModelSetter getModelSetter() {
		return modelSetter;
	}

	public void setModelSetter(ModelSetter modelSetter) {
		this.modelSetter = modelSetter;
	}

}
