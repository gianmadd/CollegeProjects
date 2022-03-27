package GUI;


import com.toedter.calendar.JCalendar;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import java.awt.Font;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import javax.swing.border.BevelBorder;

public abstract class PopolamentoLezioneFrame extends PopolamentoFrame {

	private JLabel modificaDataLabel;
	private JCalendar modificaDataLezioneCalendar;
	private JLabel modificaOraInizioLezioneLabel;
	private JLabel modificaOraFineLezioneLabel;
	private JSpinner modificaOraInizioLezioneSpinner;
	private JSpinner modificaOraFineLezioneSpinner;
	private Date date;

	
	public PopolamentoLezioneFrame() {
		
		modificaDataLabel = new JLabel("Modifica data:");
		modificaDataLabel.setForeground(panna);
		modificaDataLabel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		modificaDataLabel.setBounds(35, 149, 99, 14);
		getContentPanel().add(modificaDataLabel);
		
		modificaDataLezioneCalendar = new JCalendar();
		modificaDataLezioneCalendar.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		modificaDataLezioneCalendar.setBounds(175, 79, 330, 160);
		getContentPanel().add(modificaDataLezioneCalendar);
		
		modificaOraInizioLezioneLabel = new JLabel("Modifica ora inizio:");
		modificaOraInizioLezioneLabel.setForeground(panna);
		modificaOraInizioLezioneLabel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		modificaOraInizioLezioneLabel.setBounds(35, 273, 122, 14);
		getContentPanel().add(modificaOraInizioLezioneLabel);
		
		modificaOraFineLezioneLabel = new JLabel("Modifica ora fine:");
		modificaOraFineLezioneLabel.setForeground(panna);
		modificaOraFineLezioneLabel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		modificaOraFineLezioneLabel.setBounds(35, 318, 122, 14);
		getContentPanel().add(modificaOraFineLezioneLabel);
		
		date = new Date();
		SpinnerDateModel inizioModel = new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
		
		modificaOraInizioLezioneSpinner = new JSpinner(inizioModel);
		modificaOraInizioLezioneSpinner.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 11));
		JSpinner.DateEditor de_modificaOraInizioLezioneSpinner = new JSpinner.DateEditor(modificaOraInizioLezioneSpinner, "HH:mm");
		modificaOraInizioLezioneSpinner.setEditor(de_modificaOraInizioLezioneSpinner);
		modificaOraInizioLezioneSpinner.setBounds(175, 268, 69, 26);
		getContentPanel().add(modificaOraInizioLezioneSpinner);
		
		SpinnerDateModel fineModel = new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
		modificaOraFineLezioneSpinner = new JSpinner(fineModel);
		modificaOraFineLezioneSpinner.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 11));
		JSpinner.DateEditor de_modificaOraFineLezioneSpinner = new JSpinner.DateEditor(modificaOraFineLezioneSpinner, "HH:mm");
		modificaOraFineLezioneSpinner.setEditor(de_modificaOraFineLezioneSpinner);
		modificaOraFineLezioneSpinner.setBounds(175, 306, 69, 26);
		getContentPanel().add(modificaOraFineLezioneSpinner);

		
	}


	public JLabel getModificaDataLabel() {
		return modificaDataLabel;
	}


	public void setModificaDataLabel(JLabel modificaDataLabel) {
		this.modificaDataLabel = modificaDataLabel;
	}


	public JCalendar getCalendar() {
		return modificaDataLezioneCalendar;
	}


	public void setCalendar(JCalendar calendar) {
		this.modificaDataLezioneCalendar = calendar;
	}


	public JLabel getModificaOraInizioLabel() {
		return modificaOraInizioLezioneLabel;
	}


	public void setModificaOraInizioLabel(JLabel modificaOraInizioLabel) {
		this.modificaOraInizioLezioneLabel = modificaOraInizioLabel;
	}


	public JLabel getModificaOraFineLabel() {
		return modificaOraFineLezioneLabel;
	}


	public void setModificaOraFineLabel(JLabel modificaOraFineLabel) {
		this.modificaOraFineLezioneLabel = modificaOraFineLabel;
	}


	public JSpinner getOraInizioSpinner() {
		return modificaOraInizioLezioneSpinner;
	}


	public void setOraInizioSpinner(JSpinner oraInizioSpinner) {
		this.modificaOraInizioLezioneSpinner = oraInizioSpinner;
	}


	public JSpinner getOraFineSpinner() {
		return modificaOraFineLezioneSpinner;
	}


	public void setOraFineSpinner(JSpinner oraFineSpinner) {
		this.modificaOraFineLezioneSpinner = oraFineSpinner;
	}
	
	
}
