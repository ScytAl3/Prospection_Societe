DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE copy_prospect_to_client(IN p_idprospect INT)
BEGIN
	--	Declaration des variables
	DECLARE v_done 				INT DEFAULT FALSE;
	DECLARE v_rsprospect 		VARCHAR(20);
	DECLARE v_typeprospect 		VARCHAR(6);
	DECLARE v_domaineprospect 	VARCHAR(20);
	DECLARE v_adrprospect		VARCHAR(100);
	DECLARE v_telprospect		VARCHAR(14);
	DECLARE v_caprospect		INT(10);
	DECLARE v_commprospect		VARCHAR(100);
	DECLARE v_nbrempprospect	INT(3);
	DECLARE v_datecontrat		DATE;
	DECLARE v_defaultmail		VARCHAR(50);
		
	--	Declaration du curseur
	DECLARE curseurForProspect CURSOR FOR
	SELECT 
		rsprospect, 
		typeprospect,
		domaineprospect,
		adrprospect, 
		telprospect,
		caprospect, 
		commentairesprospect, 
		nbrempprospect
	FROM 
		prospect
	WHERE 
		idprospect = p_idprospect;
		
	--	Declaration du Handler pour gérer les exceptions ou les erreurs 
	--	rencontrées dans les procédures stockées
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET v_done = TRUE;
	DECLARE EXIT HANDLER FOR SQLEXCEPTION, SQLWARNING
	BEGIN
    ROLLBACK;
    RESIGNAL;
	END;  
	
	IF NOT EXISTS(SELECT * FROM prospect WHERE idprospect = p_idprospect) THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Pas de Prospect correspondant';
	END IF;
	
	OPEN curseurForProspect;
	START TRANSACTION;
	
	--	Recuperation des informations du Prospect
	read_loop: LOOP
		FETCH curseurForProspect INTO v_rsprospect,
										v_typeprospect,
										v_domaineprospect,
										v_adrprospect,
										v_telprospect,
										v_caprospect,
										v_commprospect,
										v_nbrempprospect;
		IF v_done THEN
			LEAVE read_loop;
		END IF;
		
		--	Insertion des informations du Prospect dans la table Client
		INSERT INTO	
			client
		SET
			idclient = NULL,
			rsclient = v_rsprospect,
			typeclient = v_typeprospect,
			domaineclient = v_domaineprospect,
			adrclient = v_adrprospect,
			telclient = v_telprospect,
			caclient = v_caprospect,
			commentairesclient = v_commprospect,
			nbrempclient = v_nbrempprospect,
			datecontrat = CURDATE(),
			adressemail = 'nom.prenom@domaine.com';
			
		-- Suppression de la ligne correspondant au Prospect dans la table Prospect
		DELETE FROM 
			prospect
		WHERE 
		idprospect = p_idprospect;
		END LOOP;
		
		COMMIT;
		CLOSE curseurForProspect;

END $$
DELIMITER ;

