-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : mar. 09 jan. 2024 à 13:58
-- Version du serveur : 8.0.31
-- Version de PHP : 8.0.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `planning_hospitalier`
--

-- --------------------------------------------------------

--
-- Structure de la table `concerne`
--

DROP TABLE IF EXISTS `concerne`;
CREATE TABLE IF NOT EXISTS `concerne` (
  `num_creneau` int NOT NULL,
  `id_specialite` int NOT NULL,
  PRIMARY KEY (`num_creneau`,`id_specialite`),
  KEY `id_specialite` (`id_specialite`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `contraintes`
--

DROP TABLE IF EXISTS `contraintes`;
CREATE TABLE IF NOT EXISTS `contraintes` (
  `id_contrainte` int NOT NULL AUTO_INCREMENT,
  `explication_contrainte` text,
  `duree` int DEFAULT NULL,
  `id_type` int DEFAULT NULL,
  PRIMARY KEY (`id_contrainte`),
  KEY `id_type` (`id_type`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `contraintes`
--

INSERT INTO `contraintes` (`id_contrainte`, `explication_contrainte`, `duree`, `id_type`) VALUES
(1, 'le nombre des heures autorisées dépassé', 2, 1),
(2, 'Travailler en radiologie plus de 13h', 13, 2),
(3, 'surplus du personnel dans une specialité', 2, 2);

-- --------------------------------------------------------

--
-- Structure de la table `contraintes_violees`
--

DROP TABLE IF EXISTS `contraintes_violees`;
CREATE TABLE IF NOT EXISTS `contraintes_violees` (
  `id_contrainte_violee` int NOT NULL AUTO_INCREMENT,
  `id_contrainte` int DEFAULT NULL,
  `id_personnel` int DEFAULT NULL,
  `id_message` int DEFAULT NULL,
  PRIMARY KEY (`id_contrainte_violee`),
  KEY `id_contrainte` (`id_contrainte`),
  KEY `id_personnel` (`id_personnel`),
  KEY `id_message` (`id_message`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `creneau`
--

DROP TABLE IF EXISTS `creneau`;
CREATE TABLE IF NOT EXISTS `creneau` (
  `num_creneau` bigint NOT NULL AUTO_INCREMENT,
  `nomcreneau` varchar(45) NOT NULL,
  `date_creneau` date DEFAULT NULL,
  `date_fin` date DEFAULT NULL,
  `heure_debut` time DEFAULT NULL,
  `heure_fin` time DEFAULT NULL,
  `idEmploi` int NOT NULL,
  PRIMARY KEY (`num_creneau`),
  KEY `idEmploi` (`idEmploi`)
) ENGINE=MyISAM AUTO_INCREMENT=105 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `creneau`
--

INSERT INTO `creneau` (`num_creneau`, `nomcreneau`, `date_creneau`, `date_fin`, `heure_debut`, `heure_fin`, `idEmploi`) VALUES
(38, 'OKK', '2024-01-11', '2024-01-15', '12:45:00', '11:22:00', 22),
(4, 'sab', '2024-01-14', '2024-01-14', '12:45:00', '12:45:00', 11),
(37, 'OKK', '2024-01-11', '2024-01-15', '12:45:00', '11:22:00', 22),
(36, 'OKK', '2024-01-11', '2024-01-15', '12:45:00', '11:22:00', 22),
(103, 'Creneau AA2', '2024-01-12', '2024-01-13', '12:34:00', '11:10:00', 11),
(28, 'ok', '2024-01-20', '2024-01-21', '12:45:00', '12:45:00', 11),
(102, 'crneauA', '2024-01-13', '2024-01-14', '11:56:00', '17:28:00', 11),
(31, 'ok', '2024-01-20', '2024-01-21', '12:45:00', '12:45:00', 11),
(104, 'Creneau AA2', '2024-01-12', '2024-01-13', '12:34:00', '11:10:00', 11),
(67, 'OKK', '2024-01-11', '2024-01-15', '12:45:00', '11:22:00', 22),
(93, 'essai', '2024-01-19', '2024-01-11', '13:22:00', '13:56:00', 21),;

-- --------------------------------------------------------

--
-- Structure de la table `emploi`
--

DROP TABLE IF EXISTS `emploi`;
CREATE TABLE IF NOT EXISTS `emploi` (
  `idEmploi` int NOT NULL AUTO_INCREMENT,
  `date_debut` date NOT NULL,
  `date_fin` date NOT NULL,
  `heure_debut` time NOT NULL,
  `heure_fin` time NOT NULL,
  PRIMARY KEY (`idEmploi`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `fonction`
--

DROP TABLE IF EXISTS `fonction`;
CREATE TABLE IF NOT EXISTS `fonction` (
  `id_fonction` int NOT NULL AUTO_INCREMENT,
  `nom_fonction` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_fonction`)
) ENGINE=MyISAM AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `fonction`
--

INSERT INTO `fonction` (`id_fonction`, `nom_fonction`) VALUES
(1, 'Modified Function'),
(2, 'CDD'),
(3, 'CDI'),
(4, 'Depart_retraite');

-- --------------------------------------------------------

--
-- Structure de la table `message_erreur`
--

DROP TABLE IF EXISTS `message_erreur`;
CREATE TABLE IF NOT EXISTS `message_erreur` (
  `id_message` int NOT NULL AUTO_INCREMENT,
  `message` text,
  PRIMARY KEY (`id_message`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `message_erreur`
--

INSERT INTO `message_erreur` (`id_message`, `message`) VALUES
(1, 'cet employé a fait plus de 4H sans pause'),
(2, 'l\''infirmier a travaille pour cette specialite dans son dernier creneau');

-- --------------------------------------------------------

--
-- Structure de la table `participe`
--

DROP TABLE IF EXISTS `participe`;
CREATE TABLE IF NOT EXISTS `participe` (
  `num_creneau` int NOT NULL,
  `id_personnel` int NOT NULL,
  PRIMARY KEY (`num_creneau`,`id_personnel`),
  KEY `id_personnel` (`id_personnel`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `personnel`
--

DROP TABLE IF EXISTS `personnel`;
CREATE TABLE IF NOT EXISTS `personnel` (
  `id_personnel` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `date_naissance` date DEFAULT NULL,
  `temps_de_travail_mensuel` int DEFAULT NULL,
  `id_fonction` int DEFAULT NULL,
  `id_specialite` int DEFAULT NULL,
  PRIMARY KEY (`id_personnel`),
  KEY `id_fonction` (`id_fonction`),
  KEY `id_specialite` (`id_specialite`)
) ENGINE=MyISAM AUTO_INCREMENT=57 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `personnel`
--

INSERT INTO `personnel` (`id_personnel`, `nom`, `prenom`, `date_naissance`, `temps_de_travail_mensuel`, `id_fonction`, `id_specialite`) VALUES
(50, 'Lefebvre', 'Isabelle', '1978-12-05', 160, 1, 4),
(48, 'Simon', 'Chloé', '1988-04-26', 0, 2, 2),
(49, 'Michel', 'Pierre', '1973-09-15', 160, 3, 3),
(47, 'Moreau', 'Luc', '1992-01-19', 160, 3, 1),
(46, 'Leroy', 'Sophie', '1985-11-30', 160, 4, 3),
(45, 'Petit', 'Emmanuel', '1975-07-08', 160, 1, 2),
(44, 'Durand', 'Marie', '1990-03-22', 160, 2, 4),
(43, 'Chevalier', 'camille', '1989-04-04', 160, 2, 1),
(51, 'lidia', 'mera', '2024-02-06', 0, 3, 2),
(42, 'Beranard', 'aurelie', '1963-10-23', 0, 4, 2),
(41, 'Roux', 'Nicolas', '1988-05-26', 160, 2, 3),
(40, 'Dubois', 'lydie', '1990-02-23', 160, 3, 2),
(39, 'Martin', 'Lucie', '1994-12-02', 100, 1, 4),
(38, 'Dupont', 'Jean', '1980-05-15', 160, 3, 1);

-- --------------------------------------------------------

--
-- Structure de la table `specialite`
--

DROP TABLE IF EXISTS `specialite`;
CREATE TABLE IF NOT EXISTS `specialite` (
  `id_specialite` int NOT NULL AUTO_INCREMENT,
  `nom_specialite` varchar(255) DEFAULT NULL,
  `nb_min_personnel` int DEFAULT NULL,
  PRIMARY KEY (`id_specialite`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `specialite`
--

INSERT INTO `specialite` (`id_specialite`, `nom_specialite`, `nb_min_personnel`) VALUES
(2, 'Cardiologie', 9),
(3, 'Urgences', 12),
(4, 'Gynecologie', 7),
(5, 'Radiologie', 4),
(6, NULL, 0),
(7, 'Cardiologie', 5),
(8, NULL, 0),
(9, 'Cardiologie', 5),
(10, 'Cardiologie', 5),
(11, 'Cardiologie', 5),
(12, 'Cardiologie', 5);

-- --------------------------------------------------------

--
-- Structure de la table `type`
--

DROP TABLE IF EXISTS `type`;
CREATE TABLE IF NOT EXISTS `type` (
  `id_type` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_type`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `type`
--

INSERT INTO `type` (`id_type`, `nom`) VALUES
(1, 'Legale'),
(2, 'De_Specialite');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
