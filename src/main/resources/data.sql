-- ============================================================
-- Olympic Management System - Jeu de données de démonstration
-- Base : MySQL
-- Emplacement conseillé :
-- src/main/resources/data.sql
--
-- IMPORTANT :
-- Ce script suppose les tables/colonnes suivantes :
-- disciplines(id, nom, description)
-- athletes(id, nom, prenom, date_naissance, nationalite, email, discipline_id)
-- epreuves(id, nom, date_epreuve, lieu, discipline_id)
-- resultats(id, classement, performance, medaille, athlete_id, epreuve_id)
--
-- Si les noms générés par vos entités JPA diffèrent, adaptez uniquement
-- les noms de tables ou de colonnes.
-- ============================================================

SET FOREIGN_KEY_CHECKS = 0;

DELETE FROM resultats;
DELETE FROM epreuves;
DELETE FROM athletes;
DELETE FROM disciplines;

ALTER TABLE resultats AUTO_INCREMENT = 1;
ALTER TABLE epreuves AUTO_INCREMENT = 1;
ALTER TABLE athletes AUTO_INCREMENT = 1;
ALTER TABLE disciplines AUTO_INCREMENT = 1;

SET FOREIGN_KEY_CHECKS = 1;

-- ----------------------------
-- Disciplines
-- ----------------------------
INSERT INTO disciplines (id, nom, description) VALUES
(1, 'Athlétisme', 'Courses, sauts et lancers'),
(2, 'Natation', 'Épreuves individuelles et collectives en bassin'),
(3, 'Judo', 'Sport de combat japonais');

-- ----------------------------
-- Athlètes
-- ----------------------------
INSERT INTO athletes
(id, nom, prenom, date_naissance, nationalite, email, discipline_id)
VALUES
(1, 'Diop', 'Awa', '1998-04-12', 'Sénégal', 'awa.diop@example.com', 1),
(2, 'Ndiaye', 'Moussa', '1996-09-03', 'Sénégal', 'moussa.ndiaye@example.com', 1),
(3, 'Traoré', 'Fatou', '1999-02-21', 'Mali', 'fatou.traore@example.com', 1),
(4, 'Koné', 'Yacouba', '1997-07-14', 'Côte d''Ivoire', 'yacouba.kone@example.com', 2),
(5, 'Diallo', 'Mariama', '2000-11-08', 'Guinée', 'mariama.diallo@example.com', 2),
(6, 'Sarr', 'Ibrahima', '1995-05-30', 'Sénégal', 'ibrahima.sarr@example.com', 2),
(7, 'Coulibaly', 'Aminata', '1998-01-17', 'Mali', 'aminata.coulibaly@example.com', 3),
(8, 'Touré', 'Souleymane', '1996-12-01', 'Côte d''Ivoire', 'souleymane.toure@example.com', 3),
(9, 'Ba', 'Oumar', '1999-06-25', 'Guinée', 'oumar.ba@example.com', 3);

-- ----------------------------
-- Épreuves
-- ----------------------------
INSERT INTO epreuves
(id, nom, date_epreuve, lieu, discipline_id)
VALUES
(1, '100 mètres', '2026-08-02', 'Stade Olympique', 1),
(2, '200 mètres nage libre', '2026-08-03', 'Centre Aquatique', 2),
(3, 'Judo -73 kg', '2026-08-04', 'Arena Olympique', 3);

-- ----------------------------
-- Résultats
-- Les médailles correspondent au classement :
-- 1 = OR, 2 = ARGENT, 3 = BRONZE
-- ----------------------------
INSERT INTO resultats
(id, classement, performance, medaille, athlete_id, epreuve_id)
VALUES
(1, 1, '10.12 s', 'OR', 1, 1),
(2, 2, '10.20 s', 'ARGENT', 3, 1),
(3, 3, '10.31 s', 'BRONZE', 2, 1),

(4, 1, '1 min 45.22 s', 'OR', 4, 2),
(5, 2, '1 min 45.80 s', 'ARGENT', 6, 2),
(6, 3, '1 min 46.10 s', 'BRONZE', 5, 2),

(7, 1, 'Ippon - 2 min 14 s', 'OR', 7, 3),
(8, 2, 'Waza-ari', 'ARGENT', 8, 3),
(9, 3, 'Ippon - 3 min 02 s', 'BRONZE', 9, 3);

-- Vérifications rapides
SELECT COUNT(*) AS total_disciplines FROM disciplines;
SELECT COUNT(*) AS total_athletes FROM athletes;
SELECT COUNT(*) AS total_epreuves FROM epreuves;
SELECT COUNT(*) AS total_resultats FROM resultats;
