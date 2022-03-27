package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.Cursor;
import javax.swing.border.LineBorder;
import javax.swing.border.BevelBorder;

public abstract class PopolamentoCorsoFrame extends PopolamentoFrame {

	private JSlider massimoIscrittiSlider;
	private JTextField massimoIscrittiTextArea;
	private JSlider percentualeMinimaPresenzeSlider;
	private JTextField percentualeMinimaPresenzeTextArea;
	private JTextField inserisciNomeField;
	private JTextArea inserisciDescrizioneField;
	private JTextArea percentualeMinimaPresenzeLabel;
	private JLabel inserisciNomeLabel;
	private JScrollPane inserisciDescrizioneScrollPane;
	private JLabel inserisciDescrizioneLabel;
	private JLabel inserisciMaxIscrittiLabel;
	
	

	public PopolamentoCorsoFrame() {

		setSize(558, 600);
		
		inserisciNomeField = new JTextField();
		inserisciNomeField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				inserisciNomeField.setBackground(bluScuro);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				inserisciNomeField.setBackground(blu);
			}
		});
		inserisciNomeField.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		inserisciNomeField.setForeground(panna);
		inserisciNomeField.setBackground(blu);
		inserisciNomeField.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		inserisciNomeField.setBounds(138, 65, 361, 20);
		getContentPanel().add(inserisciNomeField);
		inserisciNomeField.setColumns(10);
		
		
		inserisciNomeLabel = new JLabel("Nome:");
		inserisciNomeLabel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 13));
		inserisciNomeLabel.setForeground(panna);
		inserisciNomeLabel.setBounds(20, 70, 108, 13);
		getContentPanel().add(inserisciNomeLabel);
		
		inserisciDescrizioneScrollPane = new JScrollPane();
		inserisciDescrizioneScrollPane.setBorder(new LineBorder(Color.WHITE, 3));
		inserisciDescrizioneScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		inserisciDescrizioneScrollPane.setBounds(138, 100, 363, 69);
		getContentPanel().add(inserisciDescrizioneScrollPane);
		
		inserisciDescrizioneField = new JTextArea();
		inserisciDescrizioneField.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		inserisciDescrizioneScrollPane.setViewportView(inserisciDescrizioneField);
		inserisciDescrizioneField.setLineWrap(true);
		inserisciDescrizioneField.setFont(new Font("Microsoft YaHei UI", Font.ITALIC, 13));
		
		inserisciDescrizioneLabel = new JLabel("Descrizione:");
		inserisciDescrizioneLabel.setForeground(panna);
		inserisciDescrizioneLabel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 13));
		inserisciDescrizioneLabel.setBounds(20, 126, 108, 13);
		getContentPanel().add(inserisciDescrizioneLabel);
		
		inserisciMaxIscrittiLabel = new JLabel("Massimo iscritti:");
		inserisciMaxIscrittiLabel.setForeground(panna);
		inserisciMaxIscrittiLabel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 13));
		inserisciMaxIscrittiLabel.setBounds(20, 200, 108, 13);
		getContentPanel().add(inserisciMaxIscrittiLabel);
		
		

		massimoIscrittiTextArea = new JTextField();
		massimoIscrittiTextArea.setHorizontalAlignment(SwingConstants.CENTER);
		massimoIscrittiTextArea.setEditable(false);
		massimoIscrittiTextArea.setForeground(panna);
		massimoIscrittiTextArea.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		massimoIscrittiTextArea.setColumns(10);
		massimoIscrittiTextArea.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		massimoIscrittiTextArea.setBackground(blu);
		massimoIscrittiTextArea.setBounds(457, 198, 42, 20);
		getContentPanel().add(massimoIscrittiTextArea);
		
		
		
		percentualeMinimaPresenzeTextArea = new JTextField();
		percentualeMinimaPresenzeTextArea.setHorizontalAlignment(SwingConstants.CENTER);
		percentualeMinimaPresenzeTextArea.setForeground(panna);
		percentualeMinimaPresenzeTextArea.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		percentualeMinimaPresenzeTextArea.setEditable(false);
		percentualeMinimaPresenzeTextArea.setColumns(10);
		percentualeMinimaPresenzeTextArea.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		percentualeMinimaPresenzeTextArea.setBackground(blu);
		percentualeMinimaPresenzeTextArea.setBounds(457, 261, 42, 20);
		getContentPanel().add(percentualeMinimaPresenzeTextArea);
		
		
		
		
		percentualeMinimaPresenzeLabel = new JTextArea();
		percentualeMinimaPresenzeLabel.setEditable(false);
		percentualeMinimaPresenzeLabel.setWrapStyleWord(true);
		percentualeMinimaPresenzeLabel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 13));
		percentualeMinimaPresenzeLabel.setForeground(panna);
		percentualeMinimaPresenzeLabel.setBackground(blu);
		percentualeMinimaPresenzeLabel.setLineWrap(true);
		percentualeMinimaPresenzeLabel.setText("Percentuale     minima          presenze:");
		percentualeMinimaPresenzeLabel.setBounds(20, 234, 92, 58);
		getContentPanel().add(percentualeMinimaPresenzeLabel);
		
		
		
		massimoIscrittiSlider = new JSlider();
		massimoIscrittiSlider.setCursor(Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR));
		massimoIscrittiSlider.setValue(0);
		massimoIscrittiSlider.setFont(new Font("Tahoma", Font.BOLD, 11));
		massimoIscrittiSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				Integer temp = massimoIscrittiSlider.getValue();
				massimoIscrittiTextArea.setText(temp.toString());   
			}
		});
		massimoIscrittiSlider.setMinorTickSpacing(25);
		massimoIscrittiSlider.setBackground(blu);
		massimoIscrittiSlider.setPaintTicks(true);
		massimoIscrittiSlider.setMajorTickSpacing(100);
		massimoIscrittiSlider.setSnapToTicks(true);
		massimoIscrittiSlider.setMaximum(500);
		massimoIscrittiSlider.setBounds(138, 187, 305, 39);
		getContentPanel().add(massimoIscrittiSlider);
		
		percentualeMinimaPresenzeSlider = new JSlider();
		percentualeMinimaPresenzeSlider.setCursor(Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR));
		percentualeMinimaPresenzeSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				Integer temp = percentualeMinimaPresenzeSlider.getValue();
				percentualeMinimaPresenzeTextArea.setText(temp.toString());
			}
		});
		percentualeMinimaPresenzeSlider.setMajorTickSpacing(25);
		percentualeMinimaPresenzeSlider.setMinorTickSpacing(5);
		percentualeMinimaPresenzeSlider.setValue(0);
		percentualeMinimaPresenzeSlider.setSnapToTicks(true);
		percentualeMinimaPresenzeSlider.setPaintTicks(true);
		percentualeMinimaPresenzeSlider.setBackground(blu);
		percentualeMinimaPresenzeSlider.setBounds(138, 250, 305, 39);
		getContentPanel().add(percentualeMinimaPresenzeSlider);
		
	}



	public JSlider getMassimoIscrittiSlider() {
		return massimoIscrittiSlider;
	}



	public void setMassimoIscrittiSlider(JSlider massimoIscrittiSlider) {
		this.massimoIscrittiSlider = massimoIscrittiSlider;
	}



	public JTextField getMassimoIscrittiTextArea() {
		return massimoIscrittiTextArea;
	}



	public void setMassimoIscrittiTextArea(JTextField massimoIscrittiTextArea) {
		this.massimoIscrittiTextArea = massimoIscrittiTextArea;
	}



	public JSlider getPercentualeMinimaSlider() {
		return percentualeMinimaPresenzeSlider;
	}



	public void setPercentualeMinimaSlider(JSlider percentualeMinimaSlider) {
		this.percentualeMinimaPresenzeSlider = percentualeMinimaSlider;
	}



	public JTextField getPercentualeMinimaTextArea() {
		return percentualeMinimaPresenzeTextArea;
	}



	public void setPercentualeMinimaTextArea(JTextField percentualeMinimaTextArea) {
		this.percentualeMinimaPresenzeTextArea = percentualeMinimaTextArea;
	}



	public JTextField getInserisciNomeField() {
		return inserisciNomeField;
	}



	public void setInserisciNomeField(JTextField inserisciNomeField) {
		this.inserisciNomeField = inserisciNomeField;
	}



	public JTextArea getInserisciDescrizioneField() {
		return inserisciDescrizioneField;
	}



	public void setInserisciDescrizioneField(JTextArea inserisciDescrizioneField) {
		this.inserisciDescrizioneField = inserisciDescrizioneField;
	}



	public JTextArea getTxtrPercentualeMinimaPresenze() {
		return percentualeMinimaPresenzeLabel;
	}



	public void setTxtrPercentualeMinimaPresenze(JTextArea txtrPercentualeMinimaPresenze) {
		this.percentualeMinimaPresenzeLabel = txtrPercentualeMinimaPresenze;
	}
	
	
	
	
	
	

}
