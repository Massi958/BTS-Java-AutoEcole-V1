-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : mar. 06 déc. 2022 à 09:05
-- Version du serveur : 10.4.24-MariaDB
-- Version de PHP : 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `bddautoecolesoftware`
--

-- --------------------------------------------------------

--
-- Structure de la table `categorie`
--

CREATE TABLE `categorie` (
  `CodeCategorie` int(11) NOT NULL,
  `Libelle` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `Prix` double(5,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `categorie`
--

INSERT INTO `categorie` (`CodeCategorie`, `Libelle`, `Prix`) VALUES
(1, 'Automobile', 34.95),
(2, 'Poids lourd', 43.00),
(3, 'Bateau', 51.25),
(4, 'Moto', 38.15),
(5, 'Transport en commun', 40.50);

-- --------------------------------------------------------

--
-- Structure de la table `lecon`
--

CREATE TABLE `lecon` (
  `CodeLecon` int(11) NOT NULL,
  `Date` date DEFAULT NULL,
  `Heure` varchar(50) DEFAULT NULL,
  `Immatriculation` varchar(50) DEFAULT NULL,
  `Reglee` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `lecon`
--

INSERT INTO `lecon` (`CodeLecon`, `Date`, `Heure`, `Immatriculation`, `Reglee`) VALUES
(143, '2022-12-01', '16:00:00', '123 AB 21', 1),
(144, '2022-12-07', '10:00:00', '234 BC 21', 1),
(146, '2022-12-06', '12:00:00', '345 CD 21', 1),
(147, '2022-12-26', '8:00:00', '456 DE 21', 1),
(148, '2022-12-06', '11:00:00', '567 EF 21', 1),
(149, '2022-12-29', '11:00:00', '678 FG 21', 1),
(150, '2022-12-31', '15:00:00', '789 GH 21', 1),
(151, '2022-12-17', '18:00:00', '890 HJ 21', 1),
(152, '2021-12-11', '12:00:00', '234 BC 21', 1),
(153, '2021-12-03', '8:00:00', '789 GH 21', 0),
(154, '2021-12-24', '11:00:00', '678 FG 21', 1),
(155, '2020-12-05', '10:00:00', '123 AB 21', 1),
(156, '2020-12-05', '12:00:00', '123 AB 21', 1),
(157, '2020-12-05', '13:00:00', '123 AB 21', 0),
(158, '2020-12-19', '17:00:00', '123 AB 21', 1),
(159, '2020-09-16', '19:00:00', '890 HJ 21', 0),
(160, '2020-09-03', '12:00:00', '789 GH 21', 1),
(161, '2020-09-03', '19:00:00', '567 EF 21', 1),
(162, '2020-07-17', '19:00:00', '567 EF 21', 0),
(163, '2019-07-13', '13:00:00', '567 EF 21', 1),
(164, '2019-07-05', '14:00:00', '678 FG 21', 0),
(165, '2019-07-06', '14:00:00', '678 FG 21', 1),
(166, '2019-05-01', '14:00:00', '678 FG 21', 0),
(167, '2019-05-01', '17:00:00', '678 FG 21', 1),
(168, '2023-12-01', '8:00:00', '123 AB 21', 0),
(170, '2023-12-14', '8:00:00', '123 AB 21', 0),
(171, '2023-12-01', '12:00:00', '789 GH 21', 1),
(172, '2023-12-01', '14:00:00', '789 GH 21', 0),
(173, '2023-07-07', '12:00:00', '789 GH 21', 0),
(174, '2023-05-11', '12:00:00', '890 HJ 21', 1),
(175, '2023-05-11', '14:00:00', '890 HJ 21', 0),
(176, '2023-05-20', '14:00:00', '890 HJ 21', 0),
(177, '2023-05-04', '15:00:00', '567 EF 21', 0),
(178, '2023-05-05', '11:00:00', '567 EF 21', 0),
(179, '2023-02-08', '10:00:00', '567 EF 21', 1),
(180, '2023-02-01', '10:00:00', '678 FG 21', 1),
(181, '2023-02-11', '14:00:00', '678 FG 21', 0),
(182, '2023-02-01', '16:00:00', '678 FG 21', 0),
(183, '2021-12-02', '8:00:00', '890 HJ 21', 0),
(184, '2024-12-07', '10:00:00', '890 HJ 21', 1),
(185, '2024-12-08', '10:00:00', '890 HJ 21', 1),
(186, '2024-12-13', '12:00:00', '567 EF 21', 1),
(187, '2024-03-02', '13:00:00', '567 EF 21', 0),
(188, '2020-03-06', '13:00:00', '567 EF 21', 0),
(189, '2020-03-06', '17:00:00', '567 EF 21', 0),
(190, '2022-12-15', '8:00:00', '789 GH 21', 0),
(191, '2022-12-01', '8:00:00', '345 CD 21', 0),
(192, '2022-12-02', '8:00:00', '456 DE 21', 0),
(193, '2022-12-02', '11:00:00', '123 AB 21', 1),
(194, '2022-12-02', '16:00:00', '123 AB 21', 0),
(195, '2022-12-02', '18:00:00', '123 AB 21', 0),
(196, '2023-12-21', '18:00:00', '123 AB 21', 0),
(197, '2023-12-14', '18:00:00', '123 AB 21', 0),
(198, '2023-12-01', '18:00:00', '123 AB 21', 0),
(199, '2023-07-08', '13:00:00', '123 AB 21', 0),
(200, '2023-07-01', '12:00:00', '789 GH 21', 1),
(201, '2023-02-01', '12:00:00', '789 GH 21', 1),
(202, '2023-02-01', '14:00:00', '789 GH 21', 0),
(203, '2023-02-23', '14:00:00', '789 GH 21', 0),
(204, '2020-02-13', '14:00:00', '789 GH 21', 0),
(205, '2020-02-13', '17:00:00', '789 GH 21', 0),
(206, '2020-02-13', '17:00:00', '789 GH 21', 0),
(207, '2020-07-09', '17:00:00', '789 GH 21', 0),
(208, '2020-07-02', '8:00:00', '890 HJ 21', 0),
(209, '2020-07-02', '10:00:00', '890 HJ 21', 0),
(210, '2020-07-02', '12:00:00', '890 HJ 21', 1),
(211, '2020-07-02', '13:00:00', '890 HJ 21', 0),
(212, '2024-07-05', '8:00:00', '890 HJ 21', 0),
(213, '2024-07-05', '10:00:00', '890 HJ 21', 1),
(214, '2024-07-05', '13:00:00', '890 HJ 21', 0),
(215, '2024-07-05', '17:00:00', '890 HJ 21', 0),
(216, '2024-07-01', '8:00:00', '567 EF 21', 0),
(217, '2024-07-01', '10:00:00', '567 EF 21', 1),
(218, '2024-07-01', '12:00:00', '567 EF 21', 0),
(219, '2024-07-01', '14:00:00', '567 EF 21', 0),
(220, '2019-07-12', '14:00:00', '567 EF 21', 0),
(221, '2019-07-12', '13:00:00', '567 EF 21', 0),
(222, '2019-07-12', '11:00:00', '567 EF 21', 0),
(223, '2019-07-12', '8:00:00', '567 EF 21', 0),
(224, '2019-07-12', '16:00:00', '567 EF 21', 0),
(225, '2019-02-02', '8:00:00', '678 FG 21', 0),
(226, '2019-02-02', '12:00:00', '678 FG 21', 1),
(227, '2019-02-02', '18:00:00', '678 FG 21', 0),
(228, '2019-02-21', '18:00:00', '678 FG 21', 0),
(229, '2025-12-06', '8:00:00', '678 FG 21', 0),
(230, '2025-12-06', '9:00:00', '678 FG 21', 0),
(231, '2025-12-06', '14:00:00', '678 FG 21', 0),
(232, '2022-12-01', '8:00:00', '123 AB 21', 0),
(233, '2022-06-02', '10:00:00', '789 GH 21', 0),
(234, '2023-02-01', '8:00:00', '123 AB 21', 0),
(235, '2023-02-23', '8:00:00', '567 EF 21', 0),
(236, '2018-06-06', '8:00:00', '123 AB 21', 0),
(237, '2022-06-17', '8:00:00', '567 EF 21', 0),
(238, '2022-12-18', '15:00:00', '890 HJ 21', 0);

-- --------------------------------------------------------

--
-- Structure de la table `licence`
--

CREATE TABLE `licence` (
  `CodeLicence` int(11) NOT NULL,
  `CodeUser` int(11) DEFAULT NULL,
  `CodeCategorie` int(11) DEFAULT NULL,
  `DateObtention` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `licence`
--

INSERT INTO `licence` (`CodeLicence`, `CodeUser`, `CodeCategorie`, `DateObtention`) VALUES
(1, 4, 1, '2022-01-23'),
(2, 2, 5, '2021-02-24'),
(3, 2, 1, '2020-01-05'),
(4, 4, 5, '2019-04-14'),
(5, 2, 3, '2018-06-23'),
(20, 2, 4, '2022-12-02'),
(21, 4, 2, '2022-12-03'),
(22, 2, 2, '2022-12-03');

-- --------------------------------------------------------

--
-- Structure de la table `participe`
--

CREATE TABLE `participe` (
  `CodeLecon` int(11) NOT NULL,
  `CodeUser` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `participe`
--

INSERT INTO `participe` (`CodeLecon`, `CodeUser`) VALUES
(143, 2),
(143, 1),
(144, 1),
(144, 2),
(146, 3),
(146, 4),
(147, 2),
(147, 3),
(148, 2),
(148, 1),
(149, 2),
(149, 3),
(150, 2),
(150, 3),
(151, 2),
(151, 1),
(152, 1),
(152, 4),
(153, 1),
(153, 4),
(154, 1),
(154, 2),
(155, 1),
(155, 4),
(156, 1),
(156, 4),
(157, 1),
(157, 2),
(158, 1),
(158, 4),
(159, 1),
(159, 2),
(160, 1),
(160, 4),
(161, 1),
(161, 2),
(162, 1),
(162, 2),
(163, 1),
(163, 2),
(164, 1),
(164, 2),
(165, 1),
(165, 4),
(166, 1),
(166, 4),
(167, 1),
(167, 2),
(168, 1),
(168, 4),
(170, 1),
(170, 2),
(171, 1),
(171, 4),
(172, 1),
(172, 2),
(173, 1),
(173, 4),
(174, 1),
(174, 2),
(175, 1),
(175, 2),
(176, 1),
(176, 2),
(177, 1),
(177, 2),
(178, 1),
(178, 2),
(179, 1),
(179, 2),
(180, 1),
(180, 4),
(181, 1),
(181, 2),
(182, 1),
(182, 4),
(183, 1),
(183, 2),
(184, 1),
(184, 2),
(185, 1),
(185, 2),
(186, 1),
(186, 2),
(187, 1),
(187, 2),
(188, 1),
(188, 2),
(189, 1),
(189, 2),
(190, 1),
(190, 4),
(191, 3),
(191, 4),
(192, 3),
(192, 2),
(193, 3),
(193, 4),
(194, 3),
(194, 2),
(195, 3),
(195, 4),
(196, 3),
(196, 4),
(197, 3),
(197, 4),
(198, 3),
(198, 4),
(199, 3),
(199, 4),
(200, 3),
(200, 2),
(201, 3),
(201, 2),
(202, 3),
(202, 2),
(203, 3),
(203, 2),
(204, 3),
(204, 4),
(205, 3),
(205, 4),
(206, 3),
(206, 4),
(207, 3),
(207, 2),
(208, 3),
(208, 2),
(209, 3),
(209, 2),
(210, 3),
(210, 2),
(211, 3),
(211, 2),
(212, 3),
(212, 2),
(213, 3),
(213, 2),
(214, 3),
(214, 2),
(215, 3),
(215, 2),
(216, 3),
(216, 2),
(217, 3),
(217, 2),
(218, 3),
(218, 2),
(219, 3),
(219, 2),
(220, 3),
(220, 2),
(221, 3),
(221, 2),
(222, 3),
(222, 2),
(223, 3),
(223, 2),
(224, 3),
(224, 2),
(225, 3),
(225, 4),
(226, 3),
(226, 2),
(227, 3),
(227, 4),
(228, 3),
(228, 2),
(229, 3),
(229, 2),
(230, 3),
(230, 4),
(231, 3),
(231, 2),
(232, 1),
(232, 2),
(233, 1),
(233, 4),
(234, 1),
(234, 4),
(235, 1),
(235, 2),
(236, 1),
(236, 4),
(237, 1),
(237, 2),
(238, 1),
(238, 2);

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE `users` (
  `CodeUser` int(11) NOT NULL,
  `Nom` varchar(50) DEFAULT NULL,
  `Prenom` varchar(50) DEFAULT NULL,
  `Email` varchar(255) NOT NULL,
  `MotdePasse` varchar(255) NOT NULL,
  `Statut` varchar(11) NOT NULL,
  `Sexe` varchar(14) NOT NULL,
  `DateDeNaissance` date DEFAULT NULL,
  `Adresse1` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `CodePostal` varchar(50) DEFAULT NULL,
  `Ville` varchar(50) DEFAULT NULL,
  `Telephone` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `users`
--

INSERT INTO `users` (`CodeUser`, `Nom`, `Prenom`, `Email`, `MotdePasse`, `Statut`, `Sexe`, `DateDeNaissance`, `Adresse1`, `CodePostal`, `Ville`, `Telephone`) VALUES
(1, 'Béal', 'Géraldine', 'geraldine@gmail.com', '1234', 'eleve', 'Femme', '1972-01-23', '12, avenue de Romainville', '93230', 'Romainville', '0180123444'),
(2, 'Bouaza', 'Massi', 'mb@gmail.com', '1234', 'moniteur', 'Homme', '1969-01-04', '23, rue du Lycée', '75019', 'Paris', '0180234567'),
(3, 'Catard', 'Olivier', 'olivier@gmail.com', '1234', 'eleve', 'homme', '1963-09-12', '34, boulevard de l\'Université', '75005', 'Paris', '0180345677'),
(4, 'Ezard', 'Régine', 'regine@gmail.com', '1234', 'moniteur', 'femme', '1978-04-06', '45, rue des Écoles', '75020', 'Paris', '0180456789'),
(16, 'Bouaza', 'Ines', 'Ines@gmail.com', '1234', 'eleve', 'Femme', '2006-01-29', '44 Rue du Coin', '75011', 'Paris', '0781748236');

-- --------------------------------------------------------

--
-- Structure de la table `vehicule`
--

CREATE TABLE `vehicule` (
  `Immatriculation` varchar(50) NOT NULL,
  `Marque` varchar(50) DEFAULT NULL,
  `Modele` varchar(50) DEFAULT NULL,
  `Annee` int(11) DEFAULT NULL,
  `CodeCategorie` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `vehicule`
--

INSERT INTO `vehicule` (`Immatriculation`, `Marque`, `Modele`, `Annee`, `CodeCategorie`) VALUES
('123 AB 21', 'Mercedes', 'Spania', 2000, 1),
('234 BC 21', 'Peugeot', 'Sisancys', 1996, 1),
('345 CD 21', 'Renault', 'Morgane', 1995, 1),
('456 DE 21', 'Peugeot', 'Catsansys', 1999, 1),
('567 EF 21', 'Kawasaki', 'Zephyr', 1997, 4),
('678 FG 21', 'Renault', 'Betton', 1999, 5),
('789 GH 21', 'Iveco', 'Roader', 1998, 2),
('890 HJ 21', 'Oceansea', 'Tempest', 1999, 3);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `categorie`
--
ALTER TABLE `categorie`
  ADD PRIMARY KEY (`CodeCategorie`);

--
-- Index pour la table `lecon`
--
ALTER TABLE `lecon`
  ADD PRIMARY KEY (`CodeLecon`),
  ADD KEY `Immatriculation` (`Immatriculation`);

--
-- Index pour la table `licence`
--
ALTER TABLE `licence`
  ADD PRIMARY KEY (`CodeLicence`),
  ADD KEY `CodeMoniteur` (`CodeUser`,`CodeCategorie`),
  ADD KEY `CodeCategorie` (`CodeCategorie`);

--
-- Index pour la table `participe`
--
ALTER TABLE `participe`
  ADD KEY `idLecon` (`CodeLecon`),
  ADD KEY `idUser` (`CodeUser`) USING BTREE;

--
-- Index pour la table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`CodeUser`);

--
-- Index pour la table `vehicule`
--
ALTER TABLE `vehicule`
  ADD PRIMARY KEY (`Immatriculation`),
  ADD KEY `CodeCategorie` (`CodeCategorie`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `lecon`
--
ALTER TABLE `lecon`
  MODIFY `CodeLecon` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=239;

--
-- AUTO_INCREMENT pour la table `licence`
--
ALTER TABLE `licence`
  MODIFY `CodeLicence` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT pour la table `users`
--
ALTER TABLE `users`
  MODIFY `CodeUser` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `lecon`
--
ALTER TABLE `lecon`
  ADD CONSTRAINT `lecon_ibfk_1` FOREIGN KEY (`Immatriculation`) REFERENCES `vehicule` (`Immatriculation`);

--
-- Contraintes pour la table `licence`
--
ALTER TABLE `licence`
  ADD CONSTRAINT `licence_ibfk_2` FOREIGN KEY (`CodeCategorie`) REFERENCES `categorie` (`CodeCategorie`),
  ADD CONSTRAINT `licence_ibfk_3` FOREIGN KEY (`CodeUser`) REFERENCES `users` (`CodeUser`);

--
-- Contraintes pour la table `participe`
--
ALTER TABLE `participe`
  ADD CONSTRAINT `participe_ibfk_2` FOREIGN KEY (`CodeUser`) REFERENCES `users` (`CodeUser`),
  ADD CONSTRAINT `participe_ibfk_3` FOREIGN KEY (`CodeLecon`) REFERENCES `lecon` (`CodeLecon`);

--
-- Contraintes pour la table `vehicule`
--
ALTER TABLE `vehicule`
  ADD CONSTRAINT `vehicule_ibfk_1` FOREIGN KEY (`CodeCategorie`) REFERENCES `categorie` (`CodeCategorie`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
