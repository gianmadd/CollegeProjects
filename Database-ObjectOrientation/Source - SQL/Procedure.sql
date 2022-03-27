
/* Calcolo durata lezione */

CREATE FUNCTION calcolo_durata_lezione(inizio TIME, fine TIME) 
RETURNS INTERVAL AS
$$
BEGIN
	RETURN fine - inizio;
END;
$$ LANGUAGE plpgsql;

/* Calcolo Statistiche numero minimo e massimo di presenze */

CREATE FUNCTION calcolo_statistiche_min_presenze(nome_corso VARCHAR)
RETURNS INTEGER AS
$$
DECLARE
	ret INTEGER;
BEGIN
	SELECT min(p1.conteggio) INTO ret
	FROM presenze p1 JOIN corso c1 ON p1.corso_id = c1.corso_id
	WHERE c1.nome = nome_corso
	GROUP BY c1.corso_id;
	
	RETURN ret;
END;
$$ LANGUAGE plpgsql;

CREATE FUNCTION calcolo_statistiche_max_presenze(nome_corso VARCHAR)
RETURNS INTEGER AS
$$
DECLARE
	ret INTEGER;
BEGIN
	SELECT max(p1.conteggio) INTO ret
	FROM presenze p1 JOIN corso c1 ON p1.corso_id = c1.corso_id
	WHERE c1.nome = nome_corso
	GROUP BY c1.corso_id;
	
	RETURN ret;
END;
$$ LANGUAGE plpgsql;

/* Calcolo Statistiche numero medio di presenze */

CREATE FUNCTION calcolo_statistiche_media_presenze(nome_corso VARCHAR)
RETURNS INTEGER AS
$$
DECLARE 
    num_lezioni INTEGER;
    somma_presenze_corso INTEGER;
    risultato INTEGER;
    
BEGIN
    SELECT SUM(conteggio) INTO somma_presenze_corso 
    FROM presenze p 
    WHERE p.corso_id = (SELECT c.corso_id 
					    FROM corso c 
						WHERE c.nome = nome_corso);
                        
    SELECT COUNT(*) INTO num_lezioni     
    FROM lezione l 
    WHERE l.del_corso = (SELECT c.corso_id 
					    FROM corso c 
						WHERE c.nome = nome_corso);
          
   risultato := (somma_presenze_corso/num_lezioni);  
          
   RETURN risultato;
   
END;
$$ LANGUAGE plpgsql;


/* Calcolo percentuale riempimento media */

CREATE FUNCTION calcolo_percentuale_riempimento_media(nome_corso VARCHAR)
RETURNS INTEGER AS
$$
DECLARE
	max_partecipanti INTEGER;
	ret INTEGER;
BEGIN

	SELECT c.max_partecipanti INTO max_partecipanti
	FROM corso c 
	WHERE c.nome = nome_corso;
	
	IF max_partecipanti= '0' THEN ret := '100';
	ELSE
		ret := calcolo_statistiche_media_presenze(nome_corso);
		ret := ret*'100';
		ret := ret/max_partecipanti;
				
	END IF;
	RETURN ret;
END;
$$ LANGUAGE plpgsql;




/* TRIGGER: Il corso viene superato dallo studente se il corso è terminato 
e ha raggiunto la percentuale minima di presenze */

CREATE FUNCTION calcolo_superamento_corso()
RETURNS TRIGGER AS
$$
DECLARE
lezioni_totali INTEGER;
presenze INTEGER;
percentuale FLOAT;
cursore CURSOR IS (SELECT *
			  FROM iscrizione i
			  WHERE i.corso_scelto = NEW.corso_id);
BEGIN
	FOR S IN cursore
	LOOP
        lezioni_totali := '0';
        presenze := '0';
        percentuale := '0';
		SELECT count(*) INTO presenze
		FROM partecipazione p JOIN lezione l ON p.lezione_frequentata = l.lezione_id
		WHERE l.del_corso = S.corso_scelto AND p.studente_partecipante = S.studente_iscritto;
		
		SELECT count(*) INTO lezioni_totali
		FROM lezione l
		WHERE l.del_corso = S.corso_scelto;
		
		IF(lezioni_totali<>0)
        THEN 
		percentuale := FLOOR((presenze*100)/lezioni_totali);
		END IF;
		
		UPDATE iscrizione
		SET superato = TRUE
		WHERE studente_iscritto = S.studente_iscritto AND
			  corso_scelto = S.corso_scelto AND
			  percentuale >= (SELECT c.percentuale_minima_presenze
							  FROM corso c
							  WHERE c.corso_id = S.corso_scelto);
	END LOOP;
	RETURN NEW;
END;

$$ LANGUAGE plpgsql;

CREATE TRIGGER su_terminazione_corso
AFTER UPDATE ON corso
FOR EACH ROW
WHEN (OLD.terminato = FALSE AND NEW.terminato = TRUE)
EXECUTE PROCEDURE calcolo_superamento_corso();
