INSERT INTO joueur (id, nom, prenom, numero) VALUES (1, 'Doe', 'John', 1);
INSERT INTO joueur (id, nom, prenom, numero) VALUES (2, 'Doe', 'Jane', 2);
INSERT INTO joueur (id, nom, prenom, numero) VALUES (3, 'Doe', 'Jack', 3);
INSERT INTO joueur (id, nom, prenom, numero) VALUES (4, 'Doe', 'Jill', 4);
INSERT INTO joueur (id, nom, prenom, numero) VALUES (5, 'Doe', 'Jim', 5);
INSERT INTO joueur (id, nom, prenom, numero) VALUES (6, 'Doe', 'Jenny', 6);

INSERT INTO equipe (id, nom) VALUES (1, 'Equipe 1');
INSERT INTO equipe (id, nom) VALUES (2, 'Equipe 2');

INSERT INTO equipe_joueur (equipe_id, joueur_id) VALUES (1, 1);
INSERT INTO equipe_joueur (equipe_id, joueur_id) VALUES (1, 2);
INSERT INTO equipe_joueur (equipe_id, joueur_id) VALUES (1, 3);
INSERT INTO equipe_joueur (equipe_id, joueur_id) VALUES (2, 4);
INSERT INTO equipe_joueur (equipe_id, joueur_id) VALUES (2, 5);
INSERT INTO equipe_joueur (equipe_id, joueur_id) VALUES (2, 6);

INSERT INTO round (id, scorea, scoreb, round_number) VALUES (1, 21, 14, 1);
INSERT INTO round (id, scorea, scoreb, round_number) VALUES (2, 19, 21, 2);
INSERT INTO round (id, scorea, scoreb, round_number) VALUES (3, 21, 17, 3);
INSERT INTO round (id, scorea, scoreb, round_number) VALUES (4, 2, 1, 4);
INSERT INTO round (id, scorea, scoreb, round_number) VALUES (5, 1, 2, 5);
INSERT INTO round (id, scorea, scoreb, round_number) VALUES (6, 2, 1, 6);

-- Match 1 : joué récemment (aujourd'hui)
INSERT INTO `match` (id, equipea_id, equipeb_id, status, date_joue) VALUES (1, 1, 2, 2, CURRENT_TIMESTAMP);
-- Match 2 : joué il y a 2 mois (pour tester le HealthIndicator)
INSERT INTO `match` (id, equipea_id, equipeb_id, status, date_joue) VALUES (2, 1, 2, 2, DATEADD('MONTH', -2, CURRENT_TIMESTAMP));

INSERT INTO match_round (match_id, round_id) VALUES (1, 1);
INSERT INTO match_round (match_id, round_id) VALUES (1, 2);
INSERT INTO match_round (match_id, round_id) VALUES (1, 3);
INSERT INTO match_round (match_id, round_id) VALUES (2, 4);
INSERT INTO match_round (match_id, round_id) VALUES (2, 5);
INSERT INTO match_round (match_id, round_id) VALUES (2, 6);

INSERT INTO resultat (id, match_id) VALUES (1, 1);
INSERT INTO resultat (id, match_id) VALUES (2, 2);