
/* ELIMINAZIONE DATI DAL DB  */

DELETE FROM iscrizione;
DELETE FROM partecipazione;
DELETE FROM lezione;
DELETE FROM area_tematica;
DELETE FROM appartenenza_area;
DELETE FROM parole_lezione;
DELETE FROM corso;
DELETE FROM professore;
DELETE FROM macroarea;
DELETE FROM studente;
DELETE FROM parole_chiave;
DELETE FROM operatore;

/* RESTART SEQUENZE  */
ALTER SEQUENCE sequenza_corso_id
	RESTART;
ALTER SEQUENCE sequenza_lezione_id
	RESTART;
ALTER SEQUENCE sequenza_studente_id
	RESTART;
ALTER SEQUENCE sequenza_professore_id
	RESTART;
	
/* INSERIMENTO  */

/* CORSO  */
INSERT INTO corso VALUES(nextval('sequenza_corso_id'), 'public speaking', 'Impara a parlare in pubblico con sicurezza e spigliatezza.', '25', '20', 'false');
INSERT INTO corso VALUES(nextval('sequenza_corso_id'), 'chitarra classica', 'Impara a suonare le tue canzoni preferite.', '50', '30', 'false');
INSERT INTO corso VALUES(nextval('sequenza_corso_id'), 'finanza personale', 'Impara a gestire la tua disponibilità economica.', '75', '30', 'false');
INSERT INTO corso VALUES(nextval('sequenza_corso_id'), 'java per tutti', 'Le basi del linguaggio e della programmazione o-o.', '100', '90', 'false');
INSERT INTO corso VALUES(nextval('sequenza_corso_id'), 'produzione musicale', 'Imparare a produrre tracce audio con i moderni software di produzione musicale.', '50', '75', 'false');
INSERT INTO corso VALUES(nextval('sequenza_corso_id'), 'tedesco base', 'Introduzione alla lingua e alla cultura tedesca.', '150', '60', 'false');
INSERT INTO corso VALUES(nextval('sequenza_corso_id'), 'c for dummies', 'Introduzione al linguaggio c.', '200', '80', 'false');

/* PROFESSORE */
INSERT INTO professore VALUES(nextval('sequenza_professore_id'), 'Ambrogio', 'Cristoforetti');
INSERT INTO professore VALUES(nextval('sequenza_professore_id'), 'Laura', 'De Luca');
INSERT INTO professore VALUES(nextval('sequenza_professore_id'), 'Tommaso', 'Amodio');
INSERT INTO professore VALUES(nextval('sequenza_professore_id'), 'Melania', 'Castronuovo');
INSERT INTO professore VALUES(nextval('sequenza_professore_id'), 'Rossella', 'De Simone');
INSERT INTO professore VALUES(nextval('sequenza_professore_id'), 'Filippo', 'Secondo');
INSERT INTO professore VALUES(nextval('sequenza_professore_id'), 'Salvatore', 'Orecchio');
INSERT INTO professore VALUES(nextval('sequenza_professore_id'), 'Francesca', 'Pardini');


/* LEZIONE  */

/* Public Speaking */
INSERT INTO lezione VALUES (nextval('sequenza_lezione_id'), 'la giusta postura', 'Una buona postura è necessaria per dare una buona impressione.', '05/01/2022', '13:00','14:30', '1', '1');
INSERT INTO lezione VALUES (nextval('sequenza_lezione_id'), 'la giusta dizione', 'Imparare a esprimersi correttamente e fluentemente.', '07/01/2022', '12:00', '14:00', '1', '1');
INSERT INTO lezione VALUES (nextval('sequenza_lezione_id'), 'storytelling', 'Costruire un racconto nel tuo discorso.', '09/01/2022', '17:00', '19:00', '1', '1');
INSERT INTO lezione VALUES (nextval('sequenza_lezione_id'), 'tecniche di persuasione', 'I segreti della persuasione durante un discorso.', '11/01/2022', '13:00', '14:00', '1', '1');
INSERT INTO lezione VALUES (nextval('sequenza_lezione_id'), 'lezione conclusiva', 'Esercitazione pratica.', '13/01/2022', '18:00', '20:00', '1', '1');
/* Chitarra Classica */
INSERT INTO lezione VALUES (nextval('sequenza_lezione_id'), 'introduzione allo strumento', 'I primi passi.', '02/02/2022', '13:00', '15:00', '2', '2');
INSERT INTO lezione VALUES (nextval('sequenza_lezione_id'), 'lettura dello spartito', 'Impara a leggere la musica.', '03/02/2022', '14:00', '16:00', '2', '2');
INSERT INTO lezione VALUES (nextval('sequenza_lezione_id'), 'solfeggio', 'Il solfeggio è una parte fondamentale della formazione di uno stumentista', '05/02/2022', '13:00', '15:00', '2', '2');
INSERT INTO lezione VALUES (nextval('sequenza_lezione_id'), 'primi accordi', 'Impara a suonare le tue prime canzoni.', '07/02/2022', '9:30', '11:00', '2', '2');
INSERT INTO lezione VALUES (nextval('sequenza_lezione_id'), 'le scale', 'Impara ad eseguire la scala di do.', '09/02/2022', '10:30', '12:30', '2', '2');
/* Finanza Personale */
INSERT INTO lezione VALUES (nextval('sequenza_lezione_id'), 'fondamenti di economia', 'Comprendere le nozioni base.', '05/03/2022', '14:00', '16:00', '3', '3');
INSERT INTO lezione VALUES (nextval('sequenza_lezione_id'), 'risparmiare', 'Come e perchè risparmiare.', '07/03/2022', '14:00', '16:00', '3', '3');
INSERT INTO lezione VALUES (nextval('sequenza_lezione_id'), 'gestione delle spese', 'Riconoscere i motivi delle proprie spese e come tracciarle.', '10/03/2022', '13:30', '15:00', '3', '3');
INSERT INTO lezione VALUES (nextval('sequenza_lezione_id'), 'investire', 'Nozioni di base per investire passivamente.', '11/03/2022', '10:30', '12:00', '3', '3');
/* Java per Tutti */
INSERT INTO lezione VALUES (nextval('sequenza_lezione_id'), 'introduzione alla programmazione ad oggetti', 'Classi, oggetti, incapsulamento.', '05/03/2022', '15:00', '16:00', '4', '4');
INSERT INTO lezione VALUES (nextval('sequenza_lezione_id'), 'gerarchie', 'Specializzazioni e polimorfismo.', '10/03/2022', '14:00', '16:00', '4', '4');
INSERT INTO lezione VALUES (nextval('sequenza_lezione_id'), 'classi astratte ed interfacce', 'Parole chiave abstract ed interface.', '14/03/2022', '14:00', '15:00', '4', '4');
INSERT INTO lezione VALUES (nextval('sequenza_lezione_id'), 'gui', 'Costruire interfacce grafiche in Java.', '17/03/2022', '11:30', '12:30', '4', '4');
/* Produzione Musicale  */
INSERT INTO lezione VALUES (nextval('sequenza_lezione_id'), 'introduzione alle daw', 'Primi passi nelle Digital Audio Workstation.', '02/05/2022', '14:00', '16:00', '5', '5');
INSERT INTO lezione VALUES (nextval('sequenza_lezione_id'), 'teoria musicale', 'Elementi di teoria musicale per produzioni audio.', '07/05/2022', '14:00', '16:00', '5', '5');
INSERT INTO lezione VALUES (nextval('sequenza_lezione_id'), 'mixing e mastering', 'Nozioni di tecnica del suono.', '10/05/2022', '12:00', '14:00', '5', '5');
/* Tedesco Base */
INSERT INTO lezione VALUES (nextval('sequenza_lezione_id'), 'presentarsi', 'Saluti e presentazioni in tedesco.', '25/06/2022', '11:00', '12:20', '6', '6');
INSERT INTO lezione VALUES (nextval('sequenza_lezione_id'), 'la struttura della frase', 'Struttura di frasi affermative, negative e interrogative in tedesco.', '26/06/2022', '14:00', '16:00', '6', '6');
INSERT INTO lezione VALUES (nextval('sequenza_lezione_id'), 'generi e articoli', 'I 3 generi, gli articoli e i pronomi possessivi.', '27/06/2022', '13:20', '15:10', '6', '6');
INSERT INTO lezione VALUES (nextval('sequenza_lezione_id'), 'i casi', 'Le declinazioni e i casi.', '28/06/2022', '11:30', '12:30', '6', '6');
/* C for Dummies */
INSERT INTO lezione VALUES (nextval('sequenza_lezione_id'), 'funzioni e variabili', 'Primi passi nel linguaggio C.', '02/05/2021', '14:00', '16:00', '7', '7');
INSERT INTO lezione VALUES (nextval('sequenza_lezione_id'), 'puntatori', 'Fondamenti sui puntatori.', '07/05/2021', '14:00', '16:00', '7', '7');
INSERT INTO lezione VALUES (nextval('sequenza_lezione_id'), 'ancora puntatori', 'Sempre puntatori.', '10/05/2021', '12:00', '14:00', '7', '7');


/* MACROAREA */
INSERT INTO macroarea VALUES ('crescita personale');
INSERT INTO macroarea VALUES ('musica');
INSERT INTO macroarea VALUES ('economia e finanza');
INSERT INTO macroarea VALUES ('informatica');
INSERT INTO macroarea VALUES ('lingue');

/* AREA TEMATICA */
INSERT INTO area_tematica VALUES ('dialettica', 'crescita personale');
INSERT INTO area_tematica VALUES ('strumenti musicali', 'musica');
INSERT INTO area_tematica VALUES ('personal economy', 'economia e finanza');
INSERT INTO area_tematica VALUES ('linguaggi di programmazione', 'informatica');
INSERT INTO area_tematica VALUES ('music production', 'musica');
INSERT INTO area_tematica VALUES ('lingua e cultura tedesca', 'lingue');

/* APPARTENENZA AREA TEMATICA */
INSERT INTO appartenenza_area VALUES ('1', 'dialettica');
INSERT INTO appartenenza_area VALUES ('2', 'strumenti musicali');
INSERT INTO appartenenza_area VALUES ('3', 'personal economy');
INSERT INTO appartenenza_area VALUES ('4', 'linguaggi di programmazione');
INSERT INTO appartenenza_area VALUES ('5', 'music production');
INSERT INTO appartenenza_area VALUES ('5', 'strumenti musicali');
INSERT INTO appartenenza_area VALUES ('6', 'lingua e cultura tedesca');
INSERT INTO appartenenza_area VALUES ('7', 'linguaggi di programmazione');


/* PAROLE CHIAVE */
INSERT INTO parole_chiave VALUES('oratoria');
INSERT INTO parole_chiave VALUES('eloquenza');
INSERT INTO parole_chiave VALUES('convincimento');
INSERT INTO parole_chiave VALUES('parlare');
INSERT INTO parole_chiave VALUES('note');
INSERT INTO parole_chiave VALUES('spartito');
INSERT INTO parole_chiave VALUES('performance');
INSERT INTO parole_chiave VALUES('ritmo');
INSERT INTO parole_chiave VALUES('investimenti');
INSERT INTO parole_chiave VALUES('risparmio');
INSERT INTO parole_chiave VALUES('denaro');
INSERT INTO parole_chiave VALUES('programmazione');
INSERT INTO parole_chiave VALUES('sviluppo');
INSERT INTO parole_chiave VALUES('mixing');
INSERT INTO parole_chiave VALUES('mastering');
INSERT INTO parole_chiave VALUES('composizione');
INSERT INTO parole_chiave VALUES('software');
INSERT INTO parole_chiave VALUES('grammatica');
INSERT INTO parole_chiave VALUES('lessico');

/* PAROLE LEZIONE */

/* Lezioni di Public Speaking */
INSERT INTO parole_lezione VALUES('oratoria', '2');
INSERT INTO parole_lezione VALUES('oratoria', '3');
INSERT INTO parole_lezione VALUES('oratoria', '4');
INSERT INTO parole_lezione VALUES('oratoria', '5');
INSERT INTO parole_lezione VALUES('eloquenza','3');
INSERT INTO parole_lezione VALUES('convincimento','4');
INSERT INTO parole_lezione VALUES('parlare','5');
INSERT INTO parole_lezione VALUES('parlare','2');
INSERT INTO parole_lezione VALUES('performance', '5');
/* Lezioni di Chitarra Classica */
INSERT INTO parole_lezione VALUES('note', '7');
INSERT INTO parole_lezione VALUES('note', '8');
INSERT INTO parole_lezione VALUES('note', '9');
INSERT INTO parole_lezione VALUES('note', '10');
INSERT INTO parole_lezione VALUES('spartito', '7');
INSERT INTO parole_lezione VALUES('performance', '8');
INSERT INTO parole_lezione VALUES('ritmo', '10');
/* Lezioni di Finanza Personale */
INSERT INTO parole_lezione VALUES('denaro', '11');
INSERT INTO parole_lezione VALUES('denaro', '12');
INSERT INTO parole_lezione VALUES('risparmio', '12');
INSERT INTO parole_lezione VALUES('denaro', '13');
INSERT INTO parole_lezione VALUES('risparmio', '13');
INSERT INTO parole_lezione VALUES('denaro', '14');
INSERT INTO parole_lezione VALUES('investimenti', '14');
/* Lezioni di Java per Tutti */
INSERT INTO parole_lezione VALUES('programmazione', '15');
INSERT INTO parole_lezione VALUES('programmazione', '16');
INSERT INTO parole_lezione VALUES('programmazione', '17');
INSERT INTO parole_lezione VALUES('sviluppo', '17');
INSERT INTO parole_lezione VALUES('programmazione', '18');
INSERT INTO parole_lezione VALUES('sviluppo', '18');
INSERT INTO parole_lezione VALUES('software', '18');
/* Lezioni di Produzione Musicale */
INSERT INTO parole_lezione VALUES('software', '19');
INSERT INTO parole_lezione VALUES('note', '20');
INSERT INTO parole_lezione VALUES('spartito', '20');
INSERT INTO parole_lezione VALUES('ritmo', '20');
INSERT INTO parole_lezione VALUES('composizione', '20');
INSERT INTO parole_lezione VALUES('mixing', '21');
INSERT INTO parole_lezione VALUES('mastering', '21');
/* Lezioni di Tedesco Base */
INSERT INTO parole_lezione VALUES('parlare', '22');
INSERT INTO parole_lezione VALUES('lessico', '22');
INSERT INTO parole_lezione VALUES('grammatica', '23');
INSERT INTO parole_lezione VALUES('grammatica', '24');
INSERT INTO parole_lezione VALUES('grammatica', '25');
INSERT INTO parole_lezione VALUES('lessico', '25');
/* Lezioni di C for Dummies */
INSERT INTO parole_lezione VALUES('programmazione', '26');
INSERT INTO parole_lezione VALUES('programmazione', '27');
INSERT INTO parole_lezione VALUES('programmazione', '28');
INSERT INTO parole_lezione VALUES('sviluppo', '27');
INSERT INTO parole_lezione VALUES('sviluppo', '28');


/* STUDENTE */
INSERT INTO studente VALUES (nextval('sequenza_studente_id'), 'Francesco', 'Pio', '05/05/2001', 'francescopio@gmail.com', '081258784');
INSERT INTO studente VALUES (nextval('sequenza_studente_id'), 'Luigi', 'Avezzano', '03/03/2001', 'luiave@libero.it', '081778465');
INSERT INTO studente VALUES (nextval('sequenza_studente_id'), 'Miriam', 'Giacobelli', '07/08/2000', 'mirigiac@gmail.com', '089654784');
INSERT INTO studente VALUES (nextval('sequenza_studente_id'), 'Luca', 'Mirabile', '04/11/2002', 'lucamirabile@libero.it', '081235432');
INSERT INTO studente VALUES (nextval('sequenza_studente_id'), 'Marco', 'Nappa', '12/05/2000', 'marknapp@hotmail.com', '081512577');
INSERT INTO studente VALUES (nextval('sequenza_studente_id'), 'Umberto', 'Guerra', '11/05/2002', 'ginnywar@gmail.com', '081561291');
INSERT INTO studente VALUES (nextval('sequenza_studente_id'), 'Mario', 'Vasaturo', '12/09/2000', 'mavmav@libero.it', '081235684');
INSERT INTO studente VALUES (nextval('sequenza_studente_id'), 'Luca', 'Zampino', '29/03/1999', 'zampluk@hotmail.com', '089448478');
INSERT INTO studente VALUES (nextval('sequenza_studente_id'), 'Valentina', 'Corvino', '12/05/2000', 'corvinovale@gmail.com', '02213595');
INSERT INTO studente VALUES (nextval('sequenza_studente_id'), 'Sofia', 'Carini', '01/08/2003', 'sofiacar@hotmail.com', '02111717');
INSERT INTO studente VALUES (nextval('sequenza_studente_id'), 'Camilla', 'Russo', '30/01/2002', 'camirusso@gmail.com', '081113222');
INSERT INTO studente VALUES (nextval('sequenza_studente_id'), 'Ana', 'Diaz', '15/10/2003', 'anaziad@gmail.com', '345165852');
INSERT INTO studente VALUES (nextval('sequenza_studente_id'), 'Maria', 'Picon', '23/12/2000', 'maripicon@gmail.com', '341002131');
INSERT INTO studente VALUES (nextval('sequenza_studente_id'), 'Luca', 'Salzano', '14/08/1999', 'luksalz@gmail.com', '081114765');
INSERT INTO studente VALUES (nextval('sequenza_studente_id'), 'Sabrina', 'Romano', '12/11/2000', 'sabbrr@gmail.com', '081424212');
INSERT INTO studente VALUES (nextval('sequenza_studente_id'), 'Simone', 'Albano', '30/03/1998', 'simmaf@libero.it', '342154575');
INSERT INTO studente VALUES (nextval('sequenza_studente_id'), 'Salvatore', 'Marciano', '07/07/2001', 'sasimar@gmail.com', '345768668');
INSERT INTO studente VALUES (nextval('sequenza_studente_id'), 'Pino', 'Esposito', '14/02/2000', 'pinesps@gmail.com', '081456999');
INSERT INTO studente VALUES (nextval('sequenza_studente_id'), 'Viviana', 'Daniele', '03/07/1999', 'vividannn@libero.it', '089112435');
INSERT INTO studente VALUES (nextval('sequenza_studente_id'), 'Alessandro', 'Insegno', '10/02/1980', 'asmaf@gmail.com', '089974511');


/* ISCRIZIONE */
INSERT INTO iscrizione VALUES ('false', '1', '1');
INSERT INTO iscrizione VALUES ('false', '2', '1');
INSERT INTO iscrizione VALUES ('false', '3', '1');
INSERT INTO iscrizione VALUES ('false', '5', '1');

INSERT INTO iscrizione VALUES ('false', '2', '2');
INSERT INTO iscrizione VALUES ('false', '4', '2');
INSERT INTO iscrizione VALUES ('false', '5', '2');

INSERT INTO iscrizione VALUES ('false', '5', '3');
INSERT INTO iscrizione VALUES ('false', '9', '3');
INSERT INTO iscrizione VALUES ('false', '10', '3');
INSERT INTO iscrizione VALUES ('false', '11', '3');

INSERT INTO iscrizione VALUES ('false', '12', '4');
INSERT INTO iscrizione VALUES ('false', '13', '4');
INSERT INTO iscrizione VALUES ('false', '14', '4');

INSERT INTO iscrizione VALUES ('false', '12', '5');
INSERT INTO iscrizione VALUES ('false', '15', '5');
INSERT INTO iscrizione VALUES ('false', '10', '5');
INSERT INTO iscrizione VALUES ('false', '5', '5');

INSERT INTO iscrizione VALUES ('false', '16', '6');
INSERT INTO iscrizione VALUES ('false', '17', '6');
INSERT INTO iscrizione VALUES ('false', '10', '6');
INSERT INTO iscrizione VALUES ('false', '18', '6');

INSERT INTO iscrizione VALUES ('false', '6', '7');
INSERT INTO iscrizione VALUES ('false', '19', '7');
INSERT INTO iscrizione VALUES ('false', '7', '7');

/* PARTECIPAZIONE */

/* Corso di public speaking, partecipazione alle lezioni */
INSERT INTO partecipazione VALUES ('1','1');
INSERT INTO partecipazione VALUES ('1','2');
INSERT INTO partecipazione VALUES ('1','3');
INSERT INTO partecipazione VALUES ('1','5');

INSERT INTO partecipazione VALUES ('2','1');
INSERT INTO partecipazione VALUES ('2','2');
INSERT INTO partecipazione VALUES ('2','3');

INSERT INTO partecipazione VALUES ('3','1');
INSERT INTO partecipazione VALUES ('3','2');
INSERT INTO partecipazione VALUES ('3','3');

INSERT INTO partecipazione VALUES ('4','1');
INSERT INTO partecipazione VALUES ('4','2');

INSERT INTO partecipazione VALUES ('5','1');
INSERT INTO partecipazione VALUES ('5','2');

/* Corso di chitarra classica, partecipazione alle lezioni */
INSERT INTO partecipazione VALUES ('6','2');
INSERT INTO partecipazione VALUES ('6','4');
INSERT INTO partecipazione VALUES ('6','5');

INSERT INTO partecipazione VALUES ('7','2');
INSERT INTO partecipazione VALUES ('7','4');
INSERT INTO partecipazione VALUES ('7','5');

INSERT INTO partecipazione VALUES ('8','4');

INSERT INTO partecipazione VALUES ('9','2');
INSERT INTO partecipazione VALUES ('9','4');

INSERT INTO partecipazione VALUES ('10','4');

/* Corso di finanza personale, partecipazione alle lezioni */
INSERT INTO partecipazione VALUES ('11','5');
INSERT INTO partecipazione VALUES ('11','9');
INSERT INTO partecipazione VALUES ('11','10');
INSERT INTO partecipazione VALUES ('11','11');

INSERT INTO partecipazione VALUES ('12','9');
INSERT INTO partecipazione VALUES ('12','10');
INSERT INTO partecipazione VALUES ('12','11');

INSERT INTO partecipazione VALUES ('13','9');
INSERT INTO partecipazione VALUES ('13','10');
INSERT INTO partecipazione VALUES ('13','11');

INSERT INTO partecipazione VALUES ('14','9');
INSERT INTO partecipazione VALUES ('14','10');

/* Corso di java per tutti, partecipazione alle lezioni */
INSERT INTO partecipazione VALUES ('15','12');
INSERT INTO partecipazione VALUES ('15','13');
INSERT INTO partecipazione VALUES ('15','14');

INSERT INTO partecipazione VALUES ('16','12');
INSERT INTO partecipazione VALUES ('16','14');

INSERT INTO partecipazione VALUES ('17','12');
INSERT INTO partecipazione VALUES ('17','13');
INSERT INTO partecipazione VALUES ('17','14');

INSERT INTO partecipazione VALUES ('18','13');

/* Corso di produzione musicale, partecipazione alle lezioni */
INSERT INTO partecipazione VALUES ('19','5');
INSERT INTO partecipazione VALUES ('19','12');
INSERT INTO partecipazione VALUES ('19','10');
INSERT INTO partecipazione VALUES ('19','15');

INSERT INTO partecipazione VALUES ('20','5');
INSERT INTO partecipazione VALUES ('20','12');
INSERT INTO partecipazione VALUES ('20','15');

INSERT INTO partecipazione VALUES ('21','12');
INSERT INTO partecipazione VALUES ('21','10');
INSERT INTO partecipazione VALUES ('21','15');

/* Corso di tedesco base, partecipazione alle lezioni */
INSERT INTO partecipazione VALUES ('22','17');
INSERT INTO partecipazione VALUES ('22','18');
INSERT INTO partecipazione VALUES ('22','10');
INSERT INTO partecipazione VALUES ('22','16');

INSERT INTO partecipazione VALUES ('23','16');
INSERT INTO partecipazione VALUES ('23','18');
INSERT INTO partecipazione VALUES ('23','10');

INSERT INTO partecipazione VALUES ('24','17');
INSERT INTO partecipazione VALUES ('24','10');
INSERT INTO partecipazione VALUES ('24','16');

INSERT INTO partecipazione VALUES ('25','18');
INSERT INTO partecipazione VALUES ('25','17');

/* Corso di C for dummies, partecipazione alle lezioni */
INSERT INTO partecipazione VALUES ('26','6');
INSERT INTO partecipazione VALUES ('26','7');
INSERT INTO partecipazione VALUES ('26','19');

INSERT INTO partecipazione VALUES ('27','6');
INSERT INTO partecipazione VALUES ('27','7');
INSERT INTO partecipazione VALUES ('27','19');

INSERT INTO partecipazione VALUES ('28','19');


/* OPERATORI  */
INSERT INTO operatore VALUES('matteo_giordano', 'password1');
INSERT INTO operatore VALUES('gian_marco_addati', 'password2');
INSERT INTO operatore VALUES('admin', 'admin');