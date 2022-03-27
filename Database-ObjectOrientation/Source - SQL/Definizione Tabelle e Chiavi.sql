
/* SET DATESTYLE */
SET DATESTYLE TO Postgres, DMY;

/* DEFINIZIONE TABELLE */

CREATE TABLE IF NOT EXISTS corso(
	corso_id INTEGER PRIMARY KEY,
	nome VARCHAR(50) NOT NULL UNIQUE,
	descrizione VARCHAR(500) NOT NULL,
	max_partecipanti INTEGER NOT NULL,
	percentuale_minima_presenze INTEGER NOT NULL,
	terminato BOOLEAN);
	
CREATE TABLE IF NOT EXISTS professore(
		professore_id INTEGER PRIMARY KEY,
		nome VARCHAR(50) NOT NULL,
		cognome VARCHAR(50) NOT NULL);

CREATE TABLE IF NOT EXISTS lezione(
		lezione_id INTEGER PRIMARY KEY,
		titolo VARCHAR(50) UNIQUE NOT NULL,
		descrizione VARCHAR(500),
		data DATE NOT NULL,
		ora_inizio TIME NOT NULL,
		ora_fine TIME NOT NULL,
		professore INTEGER NOT NULL,
		del_corso INTEGER NOT NULL);
	
CREATE TABLE IF NOT EXISTS macroarea(
	nome VARCHAR(50) PRIMARY KEY);
	
CREATE TABLE IF NOT EXISTS area_tematica(
	tema VARCHAR(100) NOT NULL PRIMARY KEY,
	nome_macroarea VARCHAR(50) NOT NULL);

CREATE TABLE IF NOT EXISTS appartenenza_area(
	corso INTEGER NOT NULL,
	tema_trattato VARCHAR(100) NOT NULL);
	
CREATE TABLE IF NOT EXISTS studente(
	studente_id INTEGER PRIMARY KEY,
	nome VARCHAR(25) NOT NULL,
	cognome VARCHAR(25) NOT NULL,
	data_nascita DATE NOT NULL,
	email VARCHAR(50) UNIQUE NOT NULL,
	telefono VARCHAR(25) UNIQUE);
	
CREATE TABLE IF NOT EXISTS partecipazione(
	lezione_frequentata INTEGER NOT NULL,
	studente_partecipante INTEGER NOT NULL);
	
CREATE TABLE IF NOT EXISTS parole_chiave(
	parola VARCHAR(25) PRIMARY KEY);

CREATE TABLE IF NOT EXISTS parole_lezione(
	parola VARCHAR(25) NOT NULL,
	lezione INTEGER NOT NULL);

CREATE TABLE IF NOT EXISTS iscrizione(
	superato BOOLEAN DEFAULT FALSE,
	studente_iscritto INTEGER NOT NULL,
	corso_scelto INTEGER NOT NULL);

CREATE TABLE IF NOT EXISTS operatore(
	nome_utente VARCHAR(50) PRIMARY KEY,
	password VARCHAR(50) NOT NULL);


/* DEFINIZIONI FOREIGN KEY */

ALTER TABLE area_tematica
	ADD CONSTRAINT area_tematica_fk1 FOREIGN KEY (nome_macroarea)
	REFERENCES macroarea(nome) ON DELETE CASCADE ON UPDATE CASCADE;
	
ALTER TABLE appartenenza_area
	ADD CONSTRAINT appartenenza_area_fk1 FOREIGN KEY (corso)
	REFERENCES corso(corso_id) ON DELETE CASCADE,
	ADD CONSTRAINT appartenenza_area_fk2 FOREIGN KEY (tema_trattato)
	REFERENCES area_tematica(tema) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE iscrizione
	ADD CONSTRAINT iscrizione_fk1 FOREIGN KEY (studente_iscritto)
	REFERENCES studente(studente_id) ON DELETE CASCADE ON UPDATE CASCADE,
	ADD CONSTRAINT iscrizione_fk2 FOREIGN KEY (corso_scelto)
	REFERENCES corso(corso_id) ON DELETE CASCADE ON UPDATE CASCADE;
	
ALTER TABLE partecipazione
	ADD CONSTRAINT partecipazione_fk1 FOREIGN KEY (lezione_frequentata)
	REFERENCES lezione(lezione_id) ON DELETE CASCADE ON UPDATE CASCADE,
	ADD CONSTRAINT partecipazione_fk2 FOREIGN KEY (studente_partecipante)
	REFERENCES studente(studente_id) ON DELETE CASCADE ON UPDATE CASCADE;
	
ALTER TABLE lezione
	ADD CONSTRAINT lezione_fk1 FOREIGN KEY (professore)
	REFERENCES professore(professore_id) ON DELETE CASCADE ON UPDATE CASCADE,
	ADD CONSTRAINT lezione_fk2 FOREIGN KEY (del_corso)
	REFERENCES corso(corso_id) ON DELETE CASCADE ON UPDATE CASCADE;
	
ALTER TABLE parole_lezione
	ADD CONSTRAINT parole_lezione_fk1 FOREIGN KEY (parola)
	REFERENCES parole_chiave(parola) ON DELETE CASCADE ON UPDATE CASCADE,
	ADD CONSTRAINT parole_lezione_fk2 FOREIGN KEY (lezione)
	REFERENCES lezione(lezione_id) ON DELETE CASCADE ON UPDATE CASCADE;

/* DEFINIZIONE VISTE */
CREATE OR REPLACE VIEW presenze (corso_id, lezione_id, titolo_lezione, conteggio) AS(
	SELECT c.corso_id, l.lezione_id, l.titolo, count(*)
	FROM corso c JOIN lezione l ON c.corso_id = l.del_corso JOIN partecipazione p ON l.lezione_id = p.lezione_frequentata
	GROUP BY c.corso_id, l.lezione_id
	ORDER BY c.corso_id);

CREATE OR REPLACE VIEW insegnamenti(professore_id, corso_id) AS(
	SELECT DISTINCT p.professore_id, c.corso_id
	FROM professore p JOIN lezione l ON l.professore=p.professore_id JOIN corso c ON l.del_corso=c.corso_id
	ORDER BY professore_id);

/* DEFINIZIONE SEQUENZE */

CREATE SEQUENCE sequenza_corso_id
START 1
INCREMENT 1
OWNED BY corso.corso_id;

CREATE SEQUENCE sequenza_lezione_id
START 1
INCREMENT 1
OWNED BY lezione.lezione_id;

CREATE SEQUENCE sequenza_studente_id
START 1
INCREMENT 1
OWNED BY studente.studente_id;

CREATE SEQUENCE sequenza_professore_id
START 1
INCREMENT 1
OWNED BY professore.professore_id;





	
