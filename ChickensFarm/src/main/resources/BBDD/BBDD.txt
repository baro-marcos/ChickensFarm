-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: chickensfarm
-- ------------------------------------------------------
-- Server version	8.0.23

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `gallinas`
--

DROP TABLE IF EXISTS `gallinas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gallinas` (
  `idGallina` int NOT NULL AUTO_INCREMENT,
  `idGranja` int NOT NULL,
  `fechaGallina` date DEFAULT NULL,
  `gallinaVendida` tinyint DEFAULT '0',
  `gallinaMurio` tinyint DEFAULT '0',
  `precioCompraGallina` decimal(10,2) DEFAULT '0.00',
  `precioVtaGallina` decimal(10,2) DEFAULT '0.00',
  PRIMARY KEY (`idGallina`),
  KEY `FK_idGranja_idx` (`idGranja`),
  CONSTRAINT `FK_idGranja` FOREIGN KEY (`idGranja`) REFERENCES `granjas` (`idGranja`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gallinas`
--

LOCK TABLES `gallinas` WRITE;
/*!40000 ALTER TABLE `gallinas` DISABLE KEYS */;
INSERT INTO `gallinas` VALUES (1,12,'2021-01-05',1,0,50.50,100.00),(2,12,'2021-01-05',1,0,50.50,100.00),(3,12,'2021-01-05',1,0,50.50,200.00),(4,12,'2021-01-05',0,0,50.50,0.00),(5,12,'2021-01-05',0,0,50.50,0.00),(6,12,'2021-01-05',0,0,50.50,0.00),(7,12,'2021-01-05',0,0,50.50,0.00),(8,12,'2021-01-05',0,0,50.50,0.00),(9,12,'2021-01-05',0,0,50.50,0.00),(10,12,'2021-01-05',0,0,50.50,0.00),(11,12,'2021-01-05',0,0,50.50,0.00),(12,12,'2021-01-05',0,0,50.50,0.00),(13,12,'2021-01-05',0,0,50.50,0.00),(14,12,'2021-01-05',0,0,50.50,0.00),(15,12,'2021-01-05',0,0,50.50,0.00),(16,12,'2021-01-05',0,0,50.50,0.00),(17,12,'2021-01-05',0,0,50.50,0.00),(18,12,'2021-01-05',0,0,50.50,0.00),(19,12,'2021-01-05',0,0,50.50,0.00),(20,12,'2021-01-05',0,0,50.50,0.00),(41,12,'2021-01-06',0,0,70.70,0.00),(42,12,'2021-01-06',0,0,70.70,0.00),(43,12,'2021-01-10',0,0,25.00,0.00);
/*!40000 ALTER TABLE `gallinas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `granjas`
--

DROP TABLE IF EXISTS `granjas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `granjas` (
  `idGranja` int NOT NULL AUTO_INCREMENT,
  `nombreGranja` varchar(100) DEFAULT NULL,
  `fechaGranja` date DEFAULT NULL,
  `cantidadHuevos` int DEFAULT '0',
  `cantidadGallinas` int DEFAULT '0',
  `importeTotalHuevos` decimal(10,2) DEFAULT '0.00',
  `importeTotalGallinas` decimal(10,2) DEFAULT '0.00',
  `importeTotalGranja` decimal(10,2) DEFAULT '0.00',
  PRIMARY KEY (`idGranja`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `granjas`
--

LOCK TABLES `granjas` WRITE;
/*!40000 ALTER TABLE `granjas` DISABLE KEYS */;
INSERT INTO `granjas` VALUES (2,'marcos','2021-01-06',0,0,0.00,0.00,10000.00),(12,'marcos 2','2021-01-05',18,20,104.50,1024.90,10157.70);
/*!40000 ALTER TABLE `granjas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `huevos`
--

DROP TABLE IF EXISTS `huevos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `huevos` (
  `idHuevo` int NOT NULL AUTO_INCREMENT,
  `idGranja` int DEFAULT NULL,
  `fechaHuevo` date DEFAULT NULL,
  `huevoVendido` tinyint DEFAULT '0',
  `huevoNacioGallina` tinyint DEFAULT '0',
  `precioCompraHuevo` decimal(10,2) DEFAULT '0.00',
  `precioVtaHuevo` decimal(10,2) DEFAULT '0.00',
  PRIMARY KEY (`idHuevo`),
  KEY `FK_idGranja_idx` (`idGranja`),
  CONSTRAINT `FK_Huevo_idGranja` FOREIGN KEY (`idGranja`) REFERENCES `granjas` (`idGranja`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `huevos`
--

LOCK TABLES `huevos` WRITE;
/*!40000 ALTER TABLE `huevos` DISABLE KEYS */;
INSERT INTO `huevos` VALUES (1,12,'2021-01-05',1,0,10.00,30.00),(2,12,'2021-01-05',1,0,10.00,30.00),(3,12,'2021-01-05',1,0,10.00,30.00),(4,12,'2021-01-05',1,0,10.00,20.00),(5,12,'2021-01-05',0,0,10.00,0.00),(6,12,'2021-01-05',0,0,10.00,0.00),(7,12,'2021-01-05',0,0,10.00,0.00),(8,12,'2021-01-05',0,0,10.00,0.00),(9,12,'2021-01-05',0,0,10.00,0.00),(10,12,'2021-01-05',0,0,10.00,0.00),(31,12,'2021-01-06',0,0,3.00,0.00),(32,12,'2021-01-06',0,0,3.00,0.00),(33,12,'2021-01-07',0,0,2.00,0.00),(34,12,'2021-01-08',0,0,2.00,0.00),(35,12,'2021-01-08',0,0,2.00,0.00),(36,12,'2021-01-08',0,0,4.00,0.00),(37,12,'2021-01-08',0,0,4.00,0.00),(38,12,'2021-01-08',0,0,4.00,0.00),(39,12,'2021-01-08',0,0,4.00,0.00),(40,12,'2021-01-08',0,0,4.00,0.00),(41,12,'2021-01-09',0,0,7.00,0.00),(42,12,'2021-01-10',0,0,5.50,0.00);
/*!40000 ALTER TABLE `huevos` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-03-01 20:24:16
