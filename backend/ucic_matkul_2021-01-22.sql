# ************************************************************
# Sequel Ace SQL dump
# Version 3008
#
# https://sequel-ace.com/
# https://github.com/Sequel-Ace/Sequel-Ace
#
# Host: 127.0.0.1 (MySQL 8.0.22)
# Database: ucic_matkul
# Generation Time: 2021-01-22 08:40:16 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
SET NAMES utf8mb4;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table tbl_hari
# ------------------------------------------------------------

DROP TABLE IF EXISTS `tbl_hari`;

CREATE TABLE `tbl_hari` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `hari` varchar(40) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE= utf8_unicode_ci;

LOCK TABLES `tbl_hari` WRITE;
/*!40000 ALTER TABLE `tbl_hari` DISABLE KEYS */;

INSERT INTO `tbl_hari` (`id`, `hari`)
VALUES
	(1,'Senin'),
	(2,'Selasa'),
	(3,'Rabu'),
	(4,'Kamis'),
	(5,'Jumat'),
	(6,'Sabtu'),
	(7,'Minggu');

/*!40000 ALTER TABLE `tbl_hari` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table tbl_matkul
# ------------------------------------------------------------

DROP TABLE IF EXISTS `tbl_matkul`;

CREATE TABLE `tbl_matkul` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `matkul` varchar(90) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT '',
  `id_hari` int unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE= utf8_unicode_ci;

LOCK TABLES `tbl_matkul` WRITE;
/*!40000 ALTER TABLE `tbl_matkul` DISABLE KEYS */;

INSERT INTO `tbl_matkul` (`id`, `matkul`, `id_hari`)
VALUES
	(1,'Logika Algoritma',1),
	(2,'Matematika Diskrit',1),
	(3,'Struktur Data',2),
	(4,'Algoritma dan Pemrograman',2),
	(5,'Jaringan Komputer',3),
	(6,'Multimedia',3),
	(7,'Basis Data',4),
	(8,'Pemrograman Web',4),
	(9,'Pemrograman Jaringan',5),
	(10,'User Interface Design',5);

/*!40000 ALTER TABLE `tbl_matkul` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table tbl_users
# ------------------------------------------------------------

DROP TABLE IF EXISTS `tbl_users`;

CREATE TABLE `tbl_users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(70) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `password` varchar(40) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `created_at` datetime NOT NULL,
  `updated_at` datetime DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE= utf8_unicode_ci;

LOCK TABLES `tbl_users` WRITE;
/*!40000 ALTER TABLE `tbl_users` DISABLE KEYS */;

INSERT INTO `tbl_users` (`id`, `username`, `password`, `created_at`, `updated_at`, `name`)
VALUES
	(1,'admin','admin','2020-01-01 00:00:00','2020-01-01 00:00:00','Kyald');

/*!40000 ALTER TABLE `tbl_users` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
