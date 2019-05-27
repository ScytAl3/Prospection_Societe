-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  jeu. 31 jan. 2019 à 18:18
-- Version du serveur :  5.7.23
-- Version de PHP :  7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `db_projet_active`
--

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

DROP TABLE IF EXISTS `client`;
CREATE TABLE IF NOT EXISTS `client` (
  `IDCLIENT` int(11) NOT NULL AUTO_INCREMENT,
  `RSCLIENT` varchar(20) NOT NULL,
  `TYPECLIENT` varchar(6) NOT NULL,
  `DOMAINECLIENT` varchar(20) NOT NULL,
  `ADRCLIENT` varchar(40) NOT NULL,
  `TELCLIENT` varchar(14) NOT NULL,
  `CACLIENT` decimal(10,0) NOT NULL,
  `COMMENTAIRESCLIENT` varchar(100) NOT NULL,
  `NBREMPCLIENT` decimal(3,0) NOT NULL,
  `DATECONTRAT` date NOT NULL,
  `ADRESSEMAIL` varchar(50) NOT NULL,
  PRIMARY KEY (`IDCLIENT`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `client`
--

INSERT INTO `client` (`IDCLIENT`, `RSCLIENT`, `TYPECLIENT`, `DOMAINECLIENT`, `ADRCLIENT`, `TELCLIENT`, `CACLIENT`, `COMMENTAIRESCLIENT`, `NBREMPCLIENT`, `DATECONTRAT`, `ADRESSEMAIL`) VALUES
(1, 'Pomme SA', 'public', 'Agroalimentaire', '6, rue du petit doigt', '+33610203040', '10000', 'Commentaire Pomme SA', '10', '2017-09-07', 'pommesa@agro.fr'),
(2, 'Jaiponaiserie SA', 'privé', 'Commerce', '24, rue du rhum', '+33610304050', '70000', 'Commentaire Jaiponaiserie SA', '4', '2018-10-30', 'japonaiserie_sa@jpn.com'),
(3, 'Raphael SA', 'public', 'Commerce', '9, rue charles de gaulle', '+33610405060', '50000', 'Commentaire Martin Dupont SA', '5', '2018-12-08', 'sa.raphael@sondomaine.com'),
(4, 'Choucroute SARL', 'privé', 'Agroalimentaire', '42, rue de la vie', '+33610506070', '15000', 'Commentaire Choucroute SARL', '15', '2018-12-26', 'chouchou.croute@lol.fr'),
(5, 'Capsule SARL', 'privé', 'Agroalimentaire', '2, rue de la petite soif', '+33610607080', '125000', 'Commentaire Capsule SARL', '50', '2018-12-27', 'capsulesarl@gmail.com'),
(6, 'testRS', 'privé', 'testDom', 'testAdresse', '0610253540', '10000', 'testComm', '10', '2018-11-21', 'mon.adresse@mon-domaine.com'),
(7, 'Bat\'iment', 'privé', 'L\'ammeublement', '87bis, rue de l\'Hermitte gris-vert', '+33610254585', '251000', 'Bien à l\'abris', '10', '2019-01-30', 'prenom.nom@gmail.com');

-- --------------------------------------------------------

--
-- Structure de la table `prospect`
--

DROP TABLE IF EXISTS `prospect`;
CREATE TABLE IF NOT EXISTS `prospect` (
  `IDPROSPECT` decimal(10,0) NOT NULL,
  `RSPROSPECT` varchar(20) NOT NULL,
  `TYPEPROSPECT` varchar(6) NOT NULL,
  `DOMAINEPROSPECT` varchar(20) NOT NULL,
  `ADRPROSPECT` varchar(40) NOT NULL,
  `TELPROSPECT` varchar(14) NOT NULL,
  `CAPROSPECT` decimal(10,0) NOT NULL,
  `COMMENTAIRESPROSPECT` varchar(100) NOT NULL,
  `NBREMPPROSPECT` decimal(3,0) NOT NULL,
  `DATEPROSPECT` date NOT NULL,
  `INTERETPROSPECT` bit(1) NOT NULL,
  PRIMARY KEY (`IDPROSPECT`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `prospect`
--

INSERT INTO `prospect` (`IDPROSPECT`, `RSPROSPECT`, `TYPEPROSPECT`, `DOMAINEPROSPECT`, `ADRPROSPECT`, `TELPROSPECT`, `CAPROSPECT`, `COMMENTAIRESPROSPECT`, `NBREMPPROSPECT`, `DATEPROSPECT`, `INTERETPROSPECT`) VALUES
('201701', 'Prospect un', 'public', 'Domaine un', 'Adresse du prospect un', '+33612102030', '150000', 'Commentaire un', '10', '2017-06-15', b'0'),
('201702', 'Prospect deux', 'privé', 'Domaine deux', 'Adresse du prospect deux', '+33612203040', '25000', 'Commentaire deux', '1', '2017-09-03', b'1'),
('201801', 'Prospect trois', 'public', 'Domaine trois', 'Adresse du prospect trois', '+33612304050', '100000', 'Commentaire trois', '5', '2018-08-05', b'0'),
('201802', 'Prospect quatre', 'public', 'Domaine quatre', 'Adresse du prospect quatre', '+33612405060', '10000000', 'Commentaire quatre', '20', '2018-10-15', b'0'),
('201803', 'Prospect cinq', 'public', 'Domaine cinq', 'Adresse du prospect cinq', '+33612506070', '50000', 'Commentaire cinq', '3', '2018-10-30', b'1'),
('201804', 'Prospect six', 'privé', 'Domaine six', 'Adresse du prospect six', '+33612607080', '255000', 'Commentaire six', '7', '2018-11-07', b'0'),
('201805', 'Prospect sept', 'public', 'Domaine sept', 'Adresse du prospect sept', '+33612708090', '750000', 'Commentaire sept', '11', '2018-12-08', b'1'),
('201806', 'Prospect huit', 'public', 'Domaine huit', 'Adresse du prospect huit', '+33612809000', '1000', 'Commentaire huit', '1', '2018-12-19', b'0'),
('201807', 'Prospect neuf', 'privé', 'Domaine neuf', 'Adresse du prospect neuf', '+33612900010', '2150000', 'Commentaire neuf', '5', '2018-12-26', b'1'),
('201808', 'Prospect dix', 'privé', 'Domaine dix', 'Adresse du prospect dix', '+33612001020', '159500', 'Commentaire dix', '8', '2018-12-27', b'1');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
