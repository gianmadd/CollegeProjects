package GUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;

import com.toedter.calendar.JCalendar;

import Controller.Controller;
import Entita.Studente;

public class InserimentoStudenteFrame extends PopolamentoAteneoFrame {

	private Controller controller;
	
	private JTextField inserisciEmailField;
	private JTextField inserisciTelefonoField;
	private JLabel inserisciDataNascitaLabel;
	private JCalendar inserisciDataDiNascitaCalendar;
	private Studente studente;
	private JTextField creaNuovoStudenteLabel;
	private JComponent emailLabel;
	private JLabel telefonoLabel;
	private JButton creaButton;
	private JButton annullaButton;
	
	
	public InserimentoStudenteFrame(Controller ctrl) {
		
		controller = ctrl;
		
		setSize(558, 610);
		
		creaNuovoStudenteLabel = new JTextField();
		creaNuovoStudenteLabel.setEditable(false);
		creaNuovoStudenteLabel.setText("Inserisci nuovo studente");
		creaNuovoStudenteLabel.setForeground(Color.WHITE);
		creaNuovoStudenteLabel.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 17));
		creaNuovoStudenteLabel.setColumns(10);
		creaNuovoStudenteLabel.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		creaNuovoStudenteLabel.setBackground(blu);
		creaNuovoStudenteLabel.setBounds(20, 18, 217, 27);
		getContentPanel().add(creaNuovoStudenteLabel);
		
		
		inserisciDataNascitaLabel = new JLabel("Data di nascita:");
		inserisciDataNascitaLabel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 13));
		inserisciDataNascitaLabel.setForeground(panna);
		inserisciDataNascitaLabel.setBounds(32, 255, 104, 13);
		getContentPanel().add(inserisciDataNascitaLabel);
		
		inserisciDataDiNascitaCalendar = new JCalendar();
		inserisciDataDiNascitaCalendar.setBounds(138, 176, 361, 174);
		getContentPanel().add(inserisciDataDiNascitaCalendar);
		
		emailLabel = new JLabel("Email:");
		emailLabel.setForeground(Color.WHITE);
		emailLabel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 13));
		emailLabel.setBounds(32, 393, 104, 13);
		getContentPanel().add(emailLabel);
		
		inserisciEmailField = new JTextField();
		inserisciEmailField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				inserisciEmailField.setBackground(bluScuro);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				inserisciEmailField.setBackground(blu);
			}
		});
		inserisciEmailField.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		inserisciEmailField.setForeground(panna);
		inserisciEmailField.setBackground(blu);
		inserisciEmailField.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		inserisciEmailField.setBounds(138, 387, 361, 20);
		getContentPanel().add(inserisciEmailField);
		inserisciEmailField.setColumns(10);
		
		telefonoLabel = new JLabel("Telefono:");
		telefonoLabel.setForeground(Color.WHITE);
		telefonoLabel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 13));
		telefonoLabel.setBounds(32, 439, 104, 13);
		getContentPanel().add(telefonoLabel);
		
		inserisciTelefonoField = new JTextField();
		inserisciTelefonoField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				inserisciTelefonoField.setBackground(bluScuro);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				inserisciTelefonoField.setBackground(blu);
			}
		});
		inserisciTelefonoField.setForeground(Color.WHITE);
		inserisciTelefonoField.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		inserisciTelefonoField.setColumns(10);
		inserisciTelefonoField.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		inserisciTelefonoField.setBackground(blu);
		inserisciTelefonoField.setBounds(138, 433, 361, 20);
		getContentPanel().add(inserisciTelefonoField);
		
		
		creaButton = new JButton("Crea");
		creaButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		creaButton.addActionListener(listenerCreaButton());
		creaButton.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));
		creaButton.setForeground(blu);
		creaButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		creaButton.setBackground(panna);
		creaButton.setBounds(391, 500, 108, 39);
		getContentPanel().add(creaButton);
		
		
		
		annullaButton = new JButton("Annulla");
		annullaButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		annullaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		annullaButton.setForeground(blu);
		annullaButton.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));
		annullaButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		annullaButton.setBackground(panna);
		annullaButton.setBounds(273, 500, 108, 39);
		getContentPanel().add(annullaButton);
		
		setVisible(true);
	}
	
	
	//LISTENER
	
	public ActionListener listenerCreaButton() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(getInserisciNomeField().getText().isBlank()) {
					JOptionPane.showInternalMessageDialog(null, "Impossibile creare uno studente senza nome.", getTitle(), JOptionPane.ERROR_MESSAGE);
				}
				else if(getInserisciCognomeField().getText().isBlank()) {
					JOptionPane.showInternalMessageDialog(null, "Impossibile creare uno studente senza cognome.", getTitle(), JOptionPane.ERROR_MESSAGE);
				}
				else if(inserisciDataDiNascitaCalendar.getDate().after(new Date())) {
					JOptionPane.showInternalMessageDialog(null, "La data di nascita non può essere nel futuro.", getTitle(), JOptionPane.ERROR_MESSAGE);
				}
				else if(inserisciEmailField.getText().isBlank()) {
					JOptionPane.showInternalMessageDialog(null, "Impossibile creare uno studente senza email.", getTitle(), JOptionPane.ERROR_MESSAGE);
				}
				else if(inserisciTelefonoField.getText().isBlank()) {
					JOptionPane.showInternalMessageDialog(null, "Impossibile creare uno studente senza numero di telefono.", getTitle(), JOptionPane.ERROR_MESSAGE);
				}
				else {
					studente = new Studente();
					studente.setNome(getInserisciNomeField().getText());
					studente.setCognome(getInserisciCognomeField().getText());
					studente.setDataDiNascita(inserisciDataDiNascitaCalendar.getDate());
					studente.setEmail(inserisciEmailField.getText());
					studente.setTelefono(inserisciTelefonoField.getText());

					controller.insertStudente(studente);
					setVisible(false);
					//REFRESH LISTA DEI NOMI DEI CORSI SECONDO I FILTRI ATTUALI
				}
			}
		};
	}
	
}
