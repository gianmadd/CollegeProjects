package GUI;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButtonMenuItem;

import Controller.Controller;
import Entita.ModelSetter;

public class HomePage extends JFrame {
	
	
	private Controller controller;
	
	private PannelloCorsi homePagePanel;
	private PannelloParoleChiave pannelloParoleChiave;
	private PannelloAreeTematiche pannelloAreeTematiche;
	private PannelloLezioni pannelloLezioni;
	private PannelloStudenti pannelloStudenti;
	private PannelloProfessori pannelloProfessori;
	private JMenu menuVisualizza;
	private JMenuBar menuBar;
	private JButton visualizzaPannelloCorsiButton;
	private JRadioButtonMenuItem fakeButton;
	private JButton visualizzaPannelloLezioniButton;
	private JButton visualizzaPannelloStudentiButton;
	private JButton visualizzaPannelloProfessoriButton;
	private JButton visualizzaPannelloParoleChiaveButton;
	private JButton visualizzaPannelloAreeTematicheButton;
	private ImageIcon img;
	private ModelSetter modelSetter;
	
	final Color panna;
	final Color bluScuro;
	final Color blu;
	final Color rosso;
	final Color bluCobalto;
	
	
	public HomePage(Controller ctrl) {
		
		controller = ctrl;
		
		modelSetter = new ModelSetter();
		
		panna = new Color(241, 250 , 238);
		bluCobalto = new Color(29, 53, 87);
		bluScuro = new Color(32, 44, 57);
		blu = new Color(40, 56, 69);
		rosso = new Color(230, 57, 70);
		
		img = new ImageIcon("iconaCourseManager.png");
		setIconImage(img.getImage());
		setTitle("CourseManager");
		setForeground(panna);
		setBackground(panna);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1280, 830);
		setLocationRelativeTo(null);
		
		//PANNELLI
		homePagePanel = new PannelloCorsi(controller);
		homePagePanel.setBorder(null);
		pannelloLezioni = new PannelloLezioni(controller);
		pannelloStudenti = new PannelloStudenti(controller);
		pannelloProfessori = new PannelloProfessori(controller);
		pannelloAreeTematiche = new PannelloAreeTematiche(controller);
		pannelloParoleChiave = new PannelloParoleChiave(controller);
				
		setContentPane(homePagePanel);	//Pannello di default
		
		

		//CREATE MENU
		menuBar = new JMenuBar();
		menuBar.setBackground(panna);
		menuBar.setForeground(panna);
		setJMenuBar(menuBar);
		
		menuVisualizza = new JMenu("Visualizza...");
		menuVisualizza.setForeground(Color.GRAY);
		menuVisualizza.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));
		menuVisualizza.setHideActionText(true);
		menuVisualizza.setBackground(panna);
		menuBar.add(menuVisualizza);
		
		visualizzaPannelloCorsiButton = new JButton("Corsi");
		visualizzaPannelloCorsiButton.setBorderPainted(false);
		visualizzaPannelloCorsiButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(getContentPane() != homePagePanel) {
					setContentPane(homePagePanel);
					modelSetter.setModelListaCorsi(controller.getCorsiFiltrati(true, 1, new ArrayList<String>()),homePagePanel.getList());
					homePagePanel.stampaAreeTematiche(controller);
					homePagePanel.resetFiltri();
					homePagePanel.refreshCorsi();
					menuVisualizza.doClick();
				}
			}
		});
		
		
		  
		fakeButton = new JRadioButtonMenuItem("New radio item");
		fakeButton.setVisible(false);
		menuVisualizza.add(fakeButton);
		visualizzaPannelloCorsiButton.setBackground(panna);
		visualizzaPannelloCorsiButton.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));
		menuBar.add(visualizzaPannelloCorsiButton);
		
		visualizzaPannelloLezioniButton = new JButton("Lezioni");
		visualizzaPannelloLezioniButton.setBorderPainted(false);
		visualizzaPannelloLezioniButton.setBackground(panna);
		visualizzaPannelloLezioniButton.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));
		visualizzaPannelloLezioniButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(getContentPane() != pannelloLezioni) {
					setContentPane(pannelloLezioni);
					modelSetter.setModelListaLezioni(controller.getLezioniFiltrate(true, new ArrayList<String>(), new ArrayList<String>()),pannelloLezioni.getList());
					pannelloLezioni.stampaParoleChiaveFiltro(controller);
					pannelloLezioni.resetFiltri();
					pannelloLezioni.refreshLezioni();
					menuVisualizza.doClick();
				}
			}
		});
		menuBar.add(visualizzaPannelloLezioniButton);
		
		visualizzaPannelloStudentiButton = new JButton("Studenti");
		visualizzaPannelloStudentiButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(getContentPane() != pannelloStudenti) {
					setContentPane(pannelloStudenti);
					modelSetter.setModelListaStudenti(controller.getStudentiFiltrati(true, new ArrayList<String>()), pannelloStudenti.getList());
					pannelloStudenti.stampaCorsiFiltro(controller);
					pannelloStudenti.resetFiltri();
					pannelloStudenti.refreshStudenti();
					menuVisualizza.doClick();
				}
			}
		});
		visualizzaPannelloStudentiButton.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));
		visualizzaPannelloStudentiButton.setBorderPainted(false);
		visualizzaPannelloStudentiButton.setBackground(panna);
		menuBar.add(visualizzaPannelloStudentiButton);
		
		visualizzaPannelloProfessoriButton = new JButton("Professori");
		visualizzaPannelloProfessoriButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(getContentPane() != pannelloProfessori) {
					setContentPane(pannelloProfessori);
					modelSetter.setModelListaProfessori(controller.getProfessoriFiltrati(true, new ArrayList<String>()), pannelloProfessori.getList());
					pannelloProfessori.stampaCorsiFiltro(controller);
					pannelloProfessori.resetFiltri();
					pannelloProfessori.refreshProfessori();
					menuVisualizza.doClick();
				}
			}
		});
		visualizzaPannelloProfessoriButton.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));
		visualizzaPannelloProfessoriButton.setBorderPainted(false);
		visualizzaPannelloProfessoriButton.setBackground(panna);
		menuBar.add(visualizzaPannelloProfessoriButton);
		
		visualizzaPannelloParoleChiaveButton = new JButton("Parole Chiave");
		visualizzaPannelloParoleChiaveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(getContentPane() != pannelloParoleChiave) {
					setContentPane(pannelloParoleChiave);
					modelSetter.setModelListaParoleChiave(controller.getParoleChiaveFiltrate(true),pannelloParoleChiave.getList());
					pannelloParoleChiave.resetFiltri();
					pannelloParoleChiave.refreshParoleChiave();
					menuVisualizza.doClick();
				}
			}
		});
		visualizzaPannelloParoleChiaveButton.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));
		visualizzaPannelloParoleChiaveButton.setBorderPainted(false);
		visualizzaPannelloParoleChiaveButton.setBackground(panna);
		menuBar.add(visualizzaPannelloParoleChiaveButton);
		
		visualizzaPannelloAreeTematicheButton = new JButton("Aree Tematiche");
		visualizzaPannelloAreeTematicheButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(getContentPane() != pannelloAreeTematiche) {
					setContentPane(pannelloAreeTematiche);
					modelSetter.setModelListaMacroaree(controller.getMacroareeFiltrate(true),pannelloAreeTematiche.getList());
					pannelloAreeTematiche.resetFiltri();
					pannelloAreeTematiche.refreshMacroaree();
					menuVisualizza.doClick();
				}
				
			}
		});
		visualizzaPannelloAreeTematicheButton.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));
		visualizzaPannelloAreeTematicheButton.setBorderPainted(false);
		visualizzaPannelloAreeTematicheButton.setBackground(panna);
		menuBar.add(visualizzaPannelloAreeTematicheButton);
		
		

	}


	public Controller getController() {
		return controller;
	}


	public void setController(Controller controller) {
		this.controller = controller;
	}


	public PannelloCorsi getPannelloCorsi() {
		return homePagePanel;
	}


	public void setPannelloCorsi(PannelloCorsi pannelloCorsi) {
		this.homePagePanel = pannelloCorsi;
	}


	public PannelloParoleChiave getPannelloParoleChiave() {
		return pannelloParoleChiave;
	}


	public void setPannelloParoleChiave(PannelloParoleChiave pannelloParoleChiave) {
		this.pannelloParoleChiave = pannelloParoleChiave;
	}


	public PannelloAreeTematiche getPannelloAreeTematiche() {
		return pannelloAreeTematiche;
	}


	public void setPannelloAreeTematiche(PannelloAreeTematiche pannelloAreeTematiche) {
		this.pannelloAreeTematiche = pannelloAreeTematiche;
	}


	public PannelloLezioni getPannelloLezioni() {
		return pannelloLezioni;
	}


	public void setPannelloLezioni(PannelloLezioni pannelloLezioni) {
		this.pannelloLezioni = pannelloLezioni;
	}


	public PannelloStudenti getPannelloStudenti() {
		return pannelloStudenti;
	}


	public void setPannelloStudenti(PannelloStudenti pannelloStudenti) {
		this.pannelloStudenti = pannelloStudenti;
	}


	public PannelloProfessori getPannelloProfessori() {
		return pannelloProfessori;
	}


	public void setPannelloProfessori(PannelloProfessori pannelloProfessori) {
		this.pannelloProfessori = pannelloProfessori;
	}

	
	

}
