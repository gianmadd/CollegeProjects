package Controller;

import java.sql.Time;
import java.util.ArrayList;

import Entita.AreaTematica;
import Entita.Corso;
import Entita.Lezione;
import Entita.Macroarea;
import Entita.Operatore;
import Entita.ParoleChiave;
import Entita.ParoleLezione;
import Entita.Professore;
import Entita.Studente;
import GUI.HomePage;
import GUI.LoginFrame;
import PostgresDAO.AppartenenzaAreaTematicaPostgresDAO;
import PostgresDAO.AreaTematicaPostgresDAO;
import PostgresDAO.CorsoPostgresDAO;
import PostgresDAO.IscrizionePostgresDAO;
import PostgresDAO.LezionePostgresDAO;
import PostgresDAO.MacroareaPostgresDAO;
import PostgresDAO.OperatorePostgresDAO;
import PostgresDAO.ParoleChiavePostgresDAO;
import PostgresDAO.ParoleLezionePostgresDAO;
import PostgresDAO.PartecipazionePostgresDAO;
import PostgresDAO.ProfessorePostgresDAO;
import PostgresDAO.StudentePostgresDAO;

public class Controller {
	
	private CorsoPostgresDAO corsoPostgresDAO;
	private IscrizionePostgresDAO iscrizionePostgresDAO;
	private LezionePostgresDAO lezionePostgresDAO;
	private StudentePostgresDAO studentePostgresDAO;
	private ProfessorePostgresDAO professorePostgresDAO;
	private ParoleChiavePostgresDAO paroleChiavePostgresDAO;
	private PartecipazionePostgresDAO partecipazionePostgresDAO;
	private AreaTematicaPostgresDAO areaTematicaDAO;
	private ParoleLezionePostgresDAO parolelezionePostgresDAO;
	private OperatorePostgresDAO operatorePostgresDAO;
	private AppartenenzaAreaTematicaPostgresDAO appartenenzaAreaTematicaPostgresDAO;
	private MacroareaPostgresDAO macroareaPostgresDAO;
	private HomePage homePage;
	private LoginFrame loginFrame;
	
	public Controller() {
			
		corsoPostgresDAO = new CorsoPostgresDAO();
		iscrizionePostgresDAO = new IscrizionePostgresDAO();
		lezionePostgresDAO = new LezionePostgresDAO();
		studentePostgresDAO = new StudentePostgresDAO();
		paroleChiavePostgresDAO = new ParoleChiavePostgresDAO();
		partecipazionePostgresDAO = new PartecipazionePostgresDAO();
		professorePostgresDAO = new ProfessorePostgresDAO();
		areaTematicaDAO = new AreaTematicaPostgresDAO();
		macroareaPostgresDAO = new MacroareaPostgresDAO();
		parolelezionePostgresDAO = new ParoleLezionePostgresDAO();
		appartenenzaAreaTematicaPostgresDAO = new AppartenenzaAreaTematicaPostgresDAO();
		operatorePostgresDAO = new OperatorePostgresDAO();

		loginFrame = new LoginFrame(this);
		loginFrame.setVisible(true);
		
	}
	

	public static void main(String[] args) {
		Controller controller = new Controller();
	}
	
	
	//GETTER
	public HomePage getHomePage() {
		return loginFrame.getHomePage();
	}
	
	
	//CORSO

	public ArrayList<Corso> getCorsiFiltrati(boolean ordine, int filtroStatoCorso, ArrayList<String> filtroAreeTematicheSelezionati){
		return corsoPostgresDAO.ricavaCorsiFiltrati(ordine,filtroStatoCorso,filtroAreeTematicheSelezionati);
	}
	
	public Corso getCorsoDaLezione() {
		String titoloLezione = getLezioneSelezionata().getTitolo();
		return corsoPostgresDAO.ricavaCorsoDaLezione(titoloLezione);
	}
	
	public void setTerminato(String nomeCorso) {
		corsoPostgresDAO.setTerminaCorso(nomeCorso);
	}
	
	public void insertCorso (ArrayList<?> caratteristicheCorso) {
		corsoPostgresDAO.queryInsertDatiCorso(caratteristicheCorso);
	}
	
	public void updateCorso(ArrayList caratteristicheCorso) {
		String s = getHomePage().getPannelloCorsi().ricavaCorsoSelezionato();
		corsoPostgresDAO.queryUpdateDatiCorso(caratteristicheCorso,s);
	}
	
	public void eliminaCorsoSelezionato(String nomeCorso) {
		corsoPostgresDAO.eliminaCorso(nomeCorso);
	}
	
	public int getIdCorsoInserito(Corso corso) {
		return corsoPostgresDAO.queryIdCorsoInserito(corso);
	}
	
	public Corso getCorsoSelezionato() {
		String nomeCorso = getHomePage().getPannelloCorsi().getList().getSelectedValue().toString();
		return corsoPostgresDAO.ricavaCorsoScelto(nomeCorso);
	}
	
	
	
	//LEZIONE
	
	public ArrayList<Lezione> getLezioniFiltrate(boolean ordine, ArrayList<String> date, ArrayList<String> filtroParoleChiaveSelezionate){
		return lezionePostgresDAO.ricavaLezioniFiltrate(ordine, date, filtroParoleChiaveSelezionate);
	}
	
	public ArrayList<Lezione> getLezioniAssociateAlCorso(){
		String nomeCorso = getCorsoSelezionato().getNome();
		return lezionePostgresDAO.getLezioniDelCorso(nomeCorso);
	}
	
	public void insertLezione(Lezione lezione) {
		lezionePostgresDAO.queryInsertLezione(lezione);
	}
	
	public void updateLezione(Lezione lezione) {
		lezionePostgresDAO.queryUpdateLezione(lezione);
	}

	public void eliminaLezioneSelezionata(String titoloLezione) {
		lezionePostgresDAO.eliminaLezione(titoloLezione);
	}
	
	public Lezione getLezioneSelezionata() {
		String nomeLezione = getHomePage().getPannelloLezioni().getList().getSelectedValue().toString();
		return lezionePostgresDAO.ricavaLezioneScelta(nomeLezione);
	}
	
	public Time getDurataLezione() {
		return lezionePostgresDAO.ricavaDurataLezione(getLezioneSelezionata());
	}
	
	//STUDENTE
	
	public ArrayList<Studente> getStudentiFiltrati(boolean ordine,ArrayList<String> filtroCorsiSelezionati){
		return studentePostgresDAO.ricavaStudentiFiltrati(ordine,filtroCorsiSelezionati);
	}
	
	public void insertStudente(Studente studente) {
		studentePostgresDAO.queryInsertStudente(studente);
	}
	
	public void eliminaStudenteSelezionato(int idStudente) {
		studentePostgresDAO.eliminaStudente(idStudente);
	}
	
	public ArrayList<Studente> getStudentiNonIscritti(String nomeCorso){
		return studentePostgresDAO.queryStudentiNonIscritti(nomeCorso);
	}
	
	public ArrayList<Studente> getStudentiNonPartecipanti(Lezione lezione){
		return studentePostgresDAO.queryStudentiNonPartecipanti(lezione);
	}
	
	public Studente getStudenteSelezionato() {
		int idStudente = estraiMatricola(getHomePage().getPannelloStudenti().getList().getSelectedValue().toString()); 
		return studentePostgresDAO.ricavaStudenteSelezionato(idStudente);
	}
	
	public int estraiMatricola(String matricola) {
		Integer idStudente = Integer.valueOf(matricola.substring(matricola.indexOf('-')+2));
		return idStudente.intValue();
	}
	
	//PROFESSORE
	
	public ArrayList<Professore> getProfessoriFiltrati(boolean ordine,ArrayList<String> filtroCorsiSelezionati){
		return professorePostgresDAO.ricavaProfessoriFiltrati(ordine,filtroCorsiSelezionati);
	}
	
	public Professore getProfessoreDaLezione() {
		String titoloLezione = getLezioneSelezionata().getTitolo();
		return professorePostgresDAO.ricavaProfessoreDaLezione(titoloLezione);
	}
	
	public ArrayList<Corso> getInsegnamento(int idProfessore){
		return professorePostgresDAO.ricavaInsegnamenti(idProfessore);
	}
	
	public void insertProfessore(String nomeProfessore, String cognomeProfessore) {
		professorePostgresDAO.queryInsertProfessore(nomeProfessore,cognomeProfessore);
	}
	
	public void eliminaProfessoreSelezionato(int idProfessore) {
		professorePostgresDAO.eliminaProfessore(idProfessore);
	}
	
	public Professore getProfessoreSelezionato() {
		int idProfessore = estraiMatricola(getHomePage().getPannelloProfessori().getList().getSelectedValue().toString());
		return professorePostgresDAO.ricavaProfessoreSelezionato(idProfessore);
	}
	
	
	//ISCRIZIONE
	
	public ArrayList<String> getIscrizioniCorso(String nomeCorso) {
		return iscrizionePostgresDAO.getIscrizioni(nomeCorso);
	}
	
	public ArrayList<Corso> getIscrizioniStudente(int idStudente) {
		return iscrizionePostgresDAO.getIscrizioniStudente(idStudente);
	}
	
	public void insertIscrizione (String nomeCorso, ArrayList<String> arrayList) {
		iscrizionePostgresDAO.queryInsertIscrizioni(nomeCorso,arrayList);
	}
	
	
	//PARTECIPAZIONE
	
	public ArrayList<Lezione> getPartecipazioniStudente(int idStudente) {
		return partecipazionePostgresDAO.getPartecipazioniStudente(idStudente);
	}
	
	public String[][] getStatisticheCorso(String nomeCorso) {
		return partecipazionePostgresDAO.getStatisticheMinMaxPresenze(nomeCorso);
	}
	
	public int getPercentualeRiempimentoMedia(String nomeCorso) {
		return partecipazionePostgresDAO.calcolaPercentualeRiempimentoMedia(nomeCorso);
	}
	
	public int getMediaPresenze(String nomeCorso) {
		return partecipazionePostgresDAO.getNumeroPresenzeMedio(nomeCorso);
	}
	
	public void insertPartecipazione(Lezione lezione, ArrayList<String> studentiNonPartecipantiSelezionati) {
		partecipazionePostgresDAO.queryInsertPartecipazione(lezione,studentiNonPartecipantiSelezionati);
	}
	
	public ArrayList<String> getPresenze(String titoloLezione){
		return partecipazionePostgresDAO.ricavaPresenze(titoloLezione);
	}
	
	
	//MACROAREA
	
	public ArrayList<Macroarea> getMacroareeFiltrate(boolean ordine){
		return macroareaPostgresDAO.ricavaMacroareeFiltrate(ordine);
	}

	public void insertMacroarea(String nomeMacroarea) {
		macroareaPostgresDAO.queryInsertMacroarea(nomeMacroarea);
	}
	
	public void updateMacroarea(String nuovaMacroarea) {
		String vecchiaMacroarea = getMacroareaSelezionata().getNome();
		macroareaPostgresDAO.queryUpdateMacroarea(vecchiaMacroarea,nuovaMacroarea);
	}
	
	public void eliminaMacroareaSelezionata(String nomeMacroarea) {
		macroareaPostgresDAO.eliminaMacroarea(nomeMacroarea);
	}
	
	public Macroarea getMacroareaSelezionata() {
		String nomeMacroarea = getHomePage().getPannelloAreeTematiche().getList().getSelectedValue().toString();
		return macroareaPostgresDAO.ricavaMacroareaSelezionata(nomeMacroarea);
	}
	
	//AREATEMATICA
	
	public ArrayList<AreaTematica> getAreeTematicheRelativeMacroarea(){
		String nomeMacroarea = getMacroareaSelezionata().getNome();
		return areaTematicaDAO.getAreeTematiche(nomeMacroarea);
	}
	
	public void insertAreaTematica (String temaTrattato,String macroareaSelezionata) {
		areaTematicaDAO.queryInsertAreaTematica(temaTrattato,macroareaSelezionata);
	}
	
	public void updateAreaTematica(String nuovaAreaTematica ) {
		String vecchiaAreaTematica = getHomePage().getPannelloAreeTematiche().ricavaAreaTematicaSelezionata();
		areaTematicaDAO.queryUpdateAreaTematica(vecchiaAreaTematica, nuovaAreaTematica);
	}
	
	public void eliminaAreaTematicaSelezionata(String nomeAreaTematica) {
		areaTematicaDAO.eliminaAreaTematica(nomeAreaTematica);
	}
	
	public ArrayList<AreaTematica> getAreeTematiche(){
		return areaTematicaDAO.queryAreeTematiche();
	}
	
	public ArrayList<AreaTematica> getAreaTematicaNonAssociata(Corso corso){
		return areaTematicaDAO.queryAreaTematicaNonAssociata(corso);
	}
	
	
	//APPARTENENZA AREA
	
	public ArrayList<Corso> getCorsiAppartenentiAreaTematica(String nomeAreaTematica){
		return appartenenzaAreaTematicaPostgresDAO.ricavaCorsiAppartenentiAreaTematica(nomeAreaTematica);
	}
	
	public void insertAppartanenzaArea(int idCorso, ArrayList<String> areeScelte) {
		appartenenzaAreaTematicaPostgresDAO.queryInsertAppartenenzaArea(idCorso,areeScelte);
	}
	
	
	//PAROLE CHIAVE
	
	public ArrayList<ParoleChiave> getParoleChiaveFiltrate(boolean ordine){
		return paroleChiavePostgresDAO.ricavaParoleChiaveFiltrate(ordine);
	}
	
	public void updateParolaChiave(String nuovaParolaChiave) {
		String vecchiaParolaChiave = getHomePage().getPannelloParoleChiave().ricavaParolaChiaveSelezionata();
		paroleChiavePostgresDAO.queryUpdateParolaChiave(vecchiaParolaChiave,nuovaParolaChiave);
	}

	public void eliminaParolaChiaveSelezionata(String parolaChiave) {
		paroleChiavePostgresDAO.eliminaParolaChiave(parolaChiave);
	}

	public void insertParolaChiave (String parolaChiave) {
		paroleChiavePostgresDAO.queryInsertParolaChiave(parolaChiave);
	}
	
	public ArrayList<ParoleChiave> getParoleChiaveNonAssociate(Lezione lezione){
		return paroleChiavePostgresDAO.ricavaParoleChiaveNonAssociate(lezione);
	}

	public ParoleChiave getParolaChiaveSelezionata() {
		String parolaChiave = getHomePage().getPannelloParoleChiave().getList().getSelectedValue().toString();
		return paroleChiavePostgresDAO.ricavaParolaChiaveSelezionata(parolaChiave);
	}
	
	//PAROLE LEZIONE
	
	public ArrayList<ParoleLezione> getParoleChiaveLezione(Lezione lezione){
		return parolelezionePostgresDAO.ricavaParoleChiaveLezione(lezione);
	}
	
	public void insertParoleLezione(Lezione lezione, ArrayList<String> paroleChiave) {
		parolelezionePostgresDAO.queryInsertParoleLezione(lezione,paroleChiave);
	}
	
	//OPERATORE
	public boolean datiLoginChecker(Operatore operatore) {
		return operatorePostgresDAO.checkDatiLogin(operatore);
	}
	
}
