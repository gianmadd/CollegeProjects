package GUI;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.MatteBorder;

import Controller.Controller;
import Entita.Operatore;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginFrame extends JFrame {
	private JPasswordField passwordField;
	private JTextField usernameField;
	private JLabel icona;
	private JLabel welcomeLabel;
	private JLabel inserisciCredenzialiLabel;
	private JLabel usernameLabel ;
	private JLabel passwordLabel;
	private JButton submitButton;
	private Controller controller;
	private HomePage homePage;

	public LoginFrame(Controller ctrl) {
		
		controller = ctrl;
		
		ImageIcon img = new ImageIcon("iconaCourseManager.png");
		setResizable(false);
		getContentPane().setBackground(new Color(40, 56, 69));
		getContentPane().setLayout(null);
		setTitle("Login");
		setIconImage(img.getImage());
		
		Color panna = new Color(241, 250 , 238);
		Color bluCobalto = new Color(29, 53, 87);
		welcomeLabel = new JLabel("Benvenuto in CourseManager Online!");
		welcomeLabel.setForeground(panna);
		welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeLabel.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 40));
		welcomeLabel.setBounds(0, 0, 1288, 182);
		getContentPane().add(welcomeLabel);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 30));
		passwordField.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		passwordField.setBounds(402, 552, 464, 52);
		passwordField.setBackground(panna);
		passwordField.setForeground(bluCobalto);
		getContentPane().add(passwordField);
		
		usernameField = new JTextField();
		usernameField.setForeground(bluCobalto);
		usernameField.setBackground(panna);
		usernameField.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		usernameField.setFont(new Font("Microsoft YaHei UI Light", Font.BOLD, 30));
		usernameField.setBounds(402, 372, 464, 52);
		getContentPane().add(usernameField);
		usernameField.setColumns(10);
		
		inserisciCredenzialiLabel = new JLabel("Inserisci le tue credenziali:");
		inserisciCredenzialiLabel.setForeground(panna);
		inserisciCredenzialiLabel.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 34));
		inserisciCredenzialiLabel.setHorizontalAlignment(SwingConstants.CENTER);
		inserisciCredenzialiLabel.setBounds(0, 192, 1264, 31);
		getContentPane().add(inserisciCredenzialiLabel);
		
		usernameLabel = new JLabel("Username");
		usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		usernameLabel.setForeground(panna);
		usernameLabel.setFont(new Font("Microsoft YaHei UI", Font.BOLD | Font.ITALIC, 33));
		usernameLabel.setBounds(0, 311, 1264, 31);
		getContentPane().add(usernameLabel);
		
		passwordLabel = new JLabel("Password");
		passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
		passwordLabel.setForeground(new Color(241, 250, 238));
		passwordLabel.setFont(new Font("Microsoft YaHei UI", Font.BOLD | Font.ITALIC, 33));
		passwordLabel.setBounds(0, 485, 1264, 31);
		getContentPane().add(passwordLabel);
		
		submitButton = new JButton("Accedi");
		submitButton.addActionListener(listenerSubmitButton());
		submitButton.setBackground(panna);
		submitButton.setForeground(bluCobalto);
		submitButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		submitButton.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 22));
		submitButton.setBounds(402, 679, 464, 64);
		getContentPane().add(submitButton);
		
		icona = new JLabel("New label");
		icona.setBounds(378, 0, 784, 791);
		icona.setIcon(img);
		getContentPane().add(icona);
		setSize(1280, 830);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setLocationRelativeTo(null);
	}
	
	
	//Metodi
	
	public ActionListener listenerSubmitButton() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(usernameField.getText().isBlank() || passwordField.getPassword().length==0)
					JOptionPane.showInternalMessageDialog(null, "Errore: inserire username e password.", "Errore", JOptionPane.WARNING_MESSAGE);
				else {
					
					Operatore operatore = new Operatore(usernameField.getText(), conversioneArrayCharString(passwordField.getPassword()));
					
					if(controller.datiLoginChecker(operatore)) {
						homePage = new HomePage(controller);
						homePage.setVisible(true);
						setVisible(false);
					}
					else {
						JOptionPane.showMessageDialog(null, "Errore: credenziali errate.", "Errore", JOptionPane.WARNING_MESSAGE);
					}
					
				}
			
			}
		};
	}
	
	public String conversioneArrayCharString(char[] array) {
		String risultato = new String();
		return risultato = String.copyValueOf(array);
		
	}


	public HomePage getHomePage() {
		return homePage;
	}


}
