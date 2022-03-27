/* INTRARELAZIONALI */

/* Un corso deve avere almeno un partecipante */
ALTER TABLE corso
ADD CONSTRAINT numero_partecipanti_maggiore_di_0
CHECK (max_partecipanti>0);


/* Vincoli di unicità per le associazioni *...*  */
ALTER TABLE iscrizione
ADD CONSTRAINT iscrizione_unique
UNIQUE (studente_iscritto, corso_scelto);

ALTER TABLE parole_lezione
ADD CONSTRAINT parole_lezione_unique
UNIQUE (parola,lezione);

ALTER TABLE appartenenza_area
ADD CONSTRAINT area_tematica_corso_unique
UNIQUE (corso,tema_trattato);

ALTER TABLE partecipazione
ADD CONSTRAINT lezione_frequentata_studente_partecipante_unique
UNIQUE (lezione_frequentata, studente_partecipante)


/* L'ora di fine di una lezione deve essere posteriore a quella di inizio */
CREATE FUNCTION check_correttezza_orario_lezioni_on_insert()
RETURNS TRIGGER AS
$$
DECLARE
BEGIN						
	IF (NEW.ora_inizio>=NEW.ora_fine)
	THEN
		DELETE FROM lezione WHERE lezione.lezione_id = NEW.lezione_id;
		RAISE SQLSTATE '12308';
		RAISE EXCEPTION 'Una lezione non può avere orario di inizio posteriore a quello di fine.';
	END IF;
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER no_orario_illegale_lezioni_on_insert
AFTER INSERT ON lezione
FOR EACH ROW
EXECUTE PROCEDURE check_correttezza_orario_lezioni_on_insert();


CREATE FUNCTION check_correttezza_orario_lezioni_on_update()
RETURNS TRIGGER AS
$$
DECLARE
BEGIN						
	IF (NEW.ora_inizio>=NEW.ora_fine)
	THEN
		RAISE SQLSTATE '12309';
		ROLLBACK;
		RAISE EXCEPTION 'Una lezione non può avere orario di inizio posteriore a quello di fine.';
	END IF;
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER no_orario_illegale_lezioni_on_update
AFTER UPDATE ON lezione
FOR EACH ROW
EXECUTE PROCEDURE check_correttezza_orario_lezioni_on_update();


/* Un corso non può iniziare prima delle 8:00 e non può finire dopo le 20:00 */
CREATE FUNCTION check_orario_valido_lezione_on_insert()
RETURNS TRIGGER AS
$$
DECLARE
BEGIN						
	IF (NEW.ora_inizio < '8:00' OR NEW.ora_fine > '20:00')
	THEN
		DELETE FROM lezione WHERE (lezione_id = NEW.lezione_id);
		RAISE SQLSTATE '12311';
		RAISE EXCEPTION 'Impossibile creare una lezione che inizi prima delle 8:00 o che finisca dopo le 20:00.';
	END IF;
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER solo_orari_consentiti_lezioni_on_insert
AFTER INSERT ON lezione
FOR EACH ROW
EXECUTE PROCEDURE check_orario_valido_lezione_on_insert();


CREATE FUNCTION check_orario_valido_lezione_on_update()
RETURNS TRIGGER AS
$$
DECLARE
BEGIN						
	IF (NEW.ora_inizio < '8:00' OR NEW.ora_fine > '20:00')
	THEN
		RAISE SQLSTATE '12312';
		ROLLBACK;
		RAISE EXCEPTION 'Impossibile creare una lezione che inizi prima delle 8:00 o che finisca dopo le 20:00.';
	END IF;
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER solo_orari_consentiti_lezioni_on_update
AFTER UPDATE ON lezione
FOR EACH ROW
EXECUTE PROCEDURE check_orario_valido_lezione_on_update();


/* Una lezione può durare al massimo due ore */

CREATE FUNCTION check_durata_massima_lezioni_on_insert()
RETURNS TRIGGER AS
$$
DECLARE
BEGIN						
	IF ((SELECT calcolo_durata_lezione(NEW.ora_inizio, NEW.ora_fine)) > '2:00:00')
	THEN
		DELETE FROM lezione WHERE lezione.lezione_id = NEW.lezione_id;
		RAISE SQLSTATE '12313';
		RAISE EXCEPTION 'Una lezione non può durare più di due ore.';
	END IF;
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER no_lezioni_più_di2ore_on_insert
AFTER INSERT ON lezione
FOR EACH ROW
EXECUTE PROCEDURE check_durata_massima_lezioni_on_insert();


CREATE FUNCTION check_durata_massima_lezioni_on_update()
RETURNS TRIGGER AS
$$
DECLARE
BEGIN						
	IF ((SELECT calcolo_durata_lezione(NEW.ora_inizio, NEW.ora_fine)) > '2:00:00')
	THEN
		RAISE SQLSTATE '12314';
		ROLLBACK;
		RAISE EXCEPTION 'Una lezione non può durare più di due ore.';
	END IF;
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER no_lezioni_più_di2ore_on_update
AFTER UPDATE ON lezione
FOR EACH ROW
EXECUTE PROCEDURE check_durata_massima_lezioni_on_update();









/* INTERRELAZIONALI */


/* Ad un corso terminato non si possono aggiungere lezioni  */
CREATE FUNCTION check_corso_terminato_lezione() 
RETURNS TRIGGER AS
$$
BEGIN
	IF ((SELECT c.terminato
		FROM lezione l JOIN corso c ON l.del_corso = c.corso_id
		WHERE l.lezione_id = NEW.lezione_id) = TRUE)
	THEN
		DELETE FROM lezione WHERE lezione.lezione_id = NEW.lezione_id;
		RAISE SQLSTATE '12300';
		RAISE EXCEPTION 'Non puoi inserire una lezione se il corso è terminato.';
	END IF;
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;


CREATE TRIGGER no_lezioni_corso_terminato 
AFTER INSERT ON lezione 
FOR EACH ROW
EXECUTE PROCEDURE check_corso_terminato_lezione();


/* Ad un corso terminato non ci si può iscrivere */
CREATE FUNCTION check_corso_terminato_iscrizione()
RETURNS TRIGGER AS
$$
BEGIN 
	IF ((SELECT c.terminato
		 FROM iscrizione i JOIN corso c ON i.corso_scelto=c.corso_id
		 WHERE i.corso_scelto = NEW.corso_scelto AND i.studente_iscritto=NEW.studente_iscritto) = TRUE)
	THEN
		DELETE FROM iscrizione WHERE iscrizione.corso_scelto=NEW.corso_scelto;
		RAISE SQLSTATE '12301';
		RAISE EXCEPTION 'Non puoi iscrivere uno studente ad un corso terminato';
	END IF;
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER no_iscrizioni_corso_terminato
AFTER INSERT ON iscrizione
FOR EACH ROW
EXECUTE PROCEDURE check_corso_terminato_iscrizione();


/* Uno studente non può partecipare ad una lezione di un corso terminato. */
CREATE FUNCTION check_corso_terminato_partecipazione()
RETURNS TRIGGER AS
$$
BEGIN
	IF ((SELECT c.terminato
	     FROM lezione l JOIN corso c ON l.del_corso=c.corso_id JOIN partecipazione p ON l.lezione_id=p.lezione_frequentata
		 WHERE p.lezione_frequentata=NEW.lezione_frequentata AND p.studente_partecipante=NEW.studente_partecipante) = TRUE)
	THEN
		DELETE FROM partecipazione p WHERE p.lezione_frequentata=NEW.lezione_frequentata;
		RAISE SQLSTATE '12302';
		RAISE EXCEPTION 'Non puoi partecipare ad una lezione di un corso terminato';
	END IF;
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER no_partecipazioni_lezione_corso_terminato
AFTER INSERT ON partecipazione
FOR EACH ROW
EXECUTE PROCEDURE check_corso_terminato_partecipazione();


/*  Uno studente non può partecipare ad una lezione se non è iscritto al relativo corso */
CREATE FUNCTION check_iscrizione()
RETURNS TRIGGER AS
$$
DECLARE
tot integer;
BEGIN
    SELECT count(*) INTO tot
    FROM iscrizione i
    WHERE i.studente_iscritto=NEW.studente_partecipante AND
          i.corso_scelto = (SELECT c.corso_id
                            FROM corso c JOIN lezione l ON c.corso_id=l.del_corso
                            WHERE NEW.lezione_frequentata = l.lezione_id);


    IF (tot <= 0) 
    THEN
        DELETE FROM partecipazione WHERE partecipazione.lezione_frequentata = NEW.lezione_frequentata 
                                         AND partecipazione.studente_partecipante = NEW.studente_partecipante;
		RAISE SQLSTATE '12303';
        RAISE EXCEPTION 'Uno studente non può partecipare ad una lezione se non è iscritto al relativo corso.';
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;


CREATE TRIGGER no_partecipazioni_non_iscritti_corso
AFTER INSERT ON partecipazione
FOR EACH ROW
EXECUTE PROCEDURE check_iscrizione();


/*  Un corso non può avere più iscritti del numero massimo di partecipanti */

CREATE FUNCTION check_numero_massimo_partecipanti()
RETURNS TRIGGER AS
$$
DECLARE numero_iscritti INTEGER;
BEGIN 
	SELECT COUNT(*) INTO numero_iscritti 
	FROM iscrizione i 
	WHERE i.corso_scelto=NEW.corso_scelto;
	
	IF (numero_iscritti > (SELECT c.max_partecipanti
						   FROM corso c JOIN iscrizione i ON c.corso_id=i.corso_scelto
						   WHERE i.corso_scelto=NEW.corso_scelto AND i.studente_iscritto=NEW.studente_iscritto))	
	THEN
		DELETE FROM iscrizione i WHERE i.corso_scelto=NEW.corso_scelto AND i.studente_iscritto=NEW.studente_iscritto;
		RAISE SQLSTATE '12304';
		RAISE EXCEPTION 'Un corso non può avere più iscritti del numero massimo di partecipanti';
	END IF;
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER no_iscrizioni_corso_pieno
AFTER INSERT ON iscrizione
FOR EACH ROW
EXECUTE PROCEDURE check_numero_massimo_partecipanti();

/* Due lezioni dello stesso corso non possono svolgersi contemporaneamente */
CREATE FUNCTION check_orario_lezioni_on_insert()
RETURNS TRIGGER AS
$$
DECLARE
lezioni_sovrapposte INTEGER;
BEGIN
	SELECT count(*) INTO lezioni_sovrapposte
	FROM lezione l JOIN corso c ON l.del_corso = c.corso_id
	WHERE c.corso_id = NEW.del_corso
		  AND l.data = NEW.data AND (NEW.ora_inizio BETWEEN l.ora_inizio AND l.ora_fine 
								     OR NEW.ora_fine BETWEEN l.ora_inizio AND l.ora_fine
								     OR (NEW.ora_inizio < l.ora_inizio AND NEW.ora_fine > l.ora_fine));
							
	IF (lezioni_sovrapposte > 1)
	THEN
		DELETE FROM lezione WHERE lezione.lezione_id = NEW.lezione_id;
		RAISE SQLSTATE '12305';
		RAISE EXCEPTION 'Due lezioni dello stesso corso non possono svolgersi contemporaneamente';
	END IF;
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER no_lezioni_contemporanee_stesso_corso_on_insert
AFTER INSERT ON lezione
FOR EACH ROW
EXECUTE PROCEDURE check_orario_lezioni_on_insert();


CREATE FUNCTION check_orario_lezioni_on_update()
RETURNS TRIGGER AS
$$
DECLARE
lezioni_sovrapposte INTEGER;
BEGIN
	SELECT count(*) INTO lezioni_sovrapposte
	FROM lezione l JOIN corso c ON l.del_corso = c.corso_id
	WHERE c.corso_id = NEW.del_corso
		  AND l.data = NEW.data AND (NEW.ora_inizio BETWEEN l.ora_inizio AND l.ora_fine 
								     OR NEW.ora_fine BETWEEN l.ora_inizio AND l.ora_fine
								     OR (NEW.ora_inizio < l.ora_inizio AND NEW.ora_fine > l.ora_fine));
							
	IF (lezioni_sovrapposte > 1)
	THEN
		RAISE SQLSTATE '12306';
		ROLLBACK;
		RAISE EXCEPTION 'Due lezioni dello stesso corso non possono svolgersi contemporaneamente';
	END IF;
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER no_lezioni_contemporanee_stesso_corso_on_update
AFTER UPDATE ON lezione
FOR EACH ROW
EXECUTE PROCEDURE check_orario_lezioni_on_update();


/* Uno studente non può partecipare a due lezioni (di corsi diversi) che si svolgono nello stesso intervallo di tempo */

CREATE FUNCTION check_orario_partecipazioni()
RETURNS TRIGGER AS
$$
DECLARE
lezioni_stesso_intervallo INTEGER;
new_ora_inizio TIME;
new_ora_fine TIME;
new_data DATE;
BEGIN
	SELECT l1.data, l1.ora_inizio, l1.ora_fine INTO new_data, new_ora_inizio, new_ora_fine
	FROM lezione l1
	WHERE l1.lezione_id = NEW.lezione_frequentata;


	SELECT count(*) INTO lezioni_stesso_intervallo
	FROM partecipazione p 
	WHERE p.studente_partecipante = NEW.studente_partecipante AND 
		  p.lezione_frequentata IN (SELECT l.lezione_id
									  FROM lezione l JOIN partecipazione p1 ON l.lezione_id = p1.lezione_frequentata									  
									  WHERE l.data = new_data 
											AND
											    (new_ora_inizio BETWEEN l.ora_inizio AND l.ora_fine 
												OR new_ora_fine BETWEEN l.ora_inizio AND l.ora_fine
												OR (new_ora_inizio < l.ora_inizio AND new_ora_fine > l.ora_fine)));
										
	IF (lezioni_stesso_intervallo > 1)
	THEN
		DELETE FROM partecipazione p WHERE p.lezione_frequentata = NEW.lezione_frequentata AND
										   p.studente_partecipante = NEW.studente_partecipante;
										   
		RAISE SQLSTATE '12307';								  
		RAISE EXCEPTION 'Uno studente non può partecipare a due lezioni (di corsi diversi) 
					  che si svolgono nello stesso intervallo di tempo';
	END IF;
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER no_partecipazioni_contemporanee
AFTER INSERT ON partecipazione
FOR EACH ROW
EXECUTE PROCEDURE check_orario_partecipazioni();




/* Quando si modifica un corso, il numero massimo di partecipanti non può diventare minore del numero di iscritti attuale */
CREATE FUNCTION check_nuovo_num_max_partecipanti()
RETURNS TRIGGER AS
$$
DECLARE
BEGIN						
	IF (NEW.max_partecipanti < (SELECT count(*)
								FROM iscrizione i
								WHERE i.corso_scelto = NEW.corso_id))
	THEN
		RAISE SQLSTATE '12310';
		ROLLBACK;
		RAISE EXCEPTION 'Impossibile diminuire il numero massimo di iscritti al di sotto del numero di iscritti attuale.';
	END IF;
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER no_decremento_num_max_partecipanti
AFTER UPDATE ON corso
FOR EACH ROW
EXECUTE PROCEDURE check_nuovo_num_max_partecipanti();























