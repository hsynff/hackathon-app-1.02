CREATE DATABASE  IF NOT EXISTS `ticketing` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `ticketing`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: ticketing
-- ------------------------------------------------------
-- Server version	5.7.20-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `action`
--

DROP TABLE IF EXISTS `action`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `action` (
  `id_action` int(11) NOT NULL AUTO_INCREMENT,
  `action_name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id_action`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `action`
--

LOCK TABLES `action` WRITE;
/*!40000 ALTER TABLE `action` DISABLE KEYS */;
INSERT INTO `action` VALUES (4,'/login'),(5,'/logout'),(6,'/user/search'),(7,'/user/track'),(8,'/staff/main'),(9,'/staff/history'),(10,'/staff/repair/'),(11,'/staffManager/main'),(12,'/staffManager/clients'),(13,'/staffManager/new-repair'),(14,'/doLogin'),(15,'/updateStatus'),(16,'/cancelRepair'),(17,'/createRepair'),(18,'/getAllModel'),(19,'/getDeviceByModelId'),(20,'/generateQR'),(21,'/changePassword'),(22,'/add-repairer'),(23,'/getUserByFin'),(24,'/staffManager/new-repairer');
/*!40000 ALTER TABLE `action` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `device`
--

DROP TABLE IF EXISTS `device`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `device` (
  `id_device` int(11) NOT NULL AUTO_INCREMENT,
  `brand` varchar(100) DEFAULT NULL,
  `id_model` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_device`),
  KEY `fk_model_phone_idx` (`id_model`),
  CONSTRAINT `fk_model_phone` FOREIGN KEY (`id_model`) REFERENCES `model` (`id_model`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `device`
--

LOCK TABLES `device` WRITE;
/*!40000 ALTER TABLE `device` DISABLE KEYS */;
INSERT INTO `device` VALUES (8,'galaxy8',8),(10,'5S',7),(11,'X',7);
/*!40000 ALTER TABLE `device` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `model`
--

DROP TABLE IF EXISTS `model`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `model` (
  `id_model` int(11) NOT NULL AUTO_INCREMENT,
  `model` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_model`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `model`
--

LOCK TABLES `model` WRITE;
/*!40000 ALTER TABLE `model` DISABLE KEYS */;
INSERT INTO `model` VALUES (7,'Apple Iphone'),(8,'Samsung');
/*!40000 ALTER TABLE `model` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `progress`
--

DROP TABLE IF EXISTS `progress`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `progress` (
  `id_progress` int(11) NOT NULL AUTO_INCREMENT,
  `percent` int(11) DEFAULT NULL,
  `comment` text,
  `date` datetime DEFAULT NULL,
  `id_repair` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_progress`),
  KEY `progress_repair_id_repair_fk` (`id_repair`),
  CONSTRAINT `progress_repair_id_repair_fk` FOREIGN KEY (`id_repair`) REFERENCES `repair` (`id_repair`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `progress`
--

LOCK TABLES `progress` WRITE;
/*!40000 ALTER TABLE `progress` DISABLE KEYS */;
INSERT INTO `progress` VALUES (11,0,NULL,NULL,11),(12,0,NULL,NULL,12),(13,30,'sss','2018-05-06 09:56:29',12),(14,70,'rre','2018-05-06 09:59:56',12),(15,0,NULL,NULL,13),(16,20,'new','2018-05-07 10:59:16',13),(17,0,NULL,NULL,14),(18,60,'new comment','2018-05-07 11:03:35',14),(19,0,NULL,NULL,15);
/*!40000 ALTER TABLE `progress` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `repair`
--

DROP TABLE IF EXISTS `repair`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `repair` (
  `id_repair` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) DEFAULT NULL,
  `id_device` int(11) DEFAULT NULL,
  `id_staff` int(11) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `title` varchar(45) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `active` int(11) DEFAULT NULL,
  `tracking_number` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_repair`),
  KEY `fk_user_repair_idx` (`id_user`),
  KEY `fk_staff_repair_idx` (`id_staff`),
  CONSTRAINT `fk_staff_repair` FOREIGN KEY (`id_staff`) REFERENCES `staff` (`id_staff`),
  CONSTRAINT `fk_user_repair` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `repair`
--

LOCK TABLES `repair` WRITE;
/*!40000 ALTER TABLE `repair` DISABLE KEYS */;
INSERT INTO `repair` VALUES (6,7,8,9,3,'ewew','2018-05-06',NULL,1,''),(7,8,8,9,1,'dsdsds','2018-05-06',NULL,1,''),(8,9,8,9,1,'ewweew','2018-05-06',NULL,1,''),(9,7,8,9,2323,'dsdsd','2018-05-06',NULL,1,''),(10,5,8,9,32,'asasa','2018-05-06',NULL,1,'896-156-849'),(11,10,8,9,23,'1adadsads','2018-05-06',NULL,1,'318-207-158'),(12,11,8,9,34,'dfsdfs','2018-05-06',NULL,1,'904-836-862'),(13,12,8,12,44,'Battery','2018-05-07','2018-05-07',2,'522-596-620'),(14,12,8,12,44,'Screen','2018-05-07',NULL,1,'314-700-274'),(15,13,8,12,66,'Screen crack','2018-05-07',NULL,1,'244-996-613');
/*!40000 ALTER TABLE `repair` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id_role` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_role`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'role_repairer'),(2,'role_manager'),(3,'role_user'),(4,'role_default');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_action`
--

DROP TABLE IF EXISTS `role_action`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_action` (
  `id_role` int(11) NOT NULL,
  `id_action` int(11) DEFAULT NULL,
  `id_role_action` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id_role_action`),
  KEY `fk_action_roleaction_idx` (`id_action`),
  KEY `role_action_role_id_role_fk` (`id_role`),
  CONSTRAINT `role_action_action_id_action_fk` FOREIGN KEY (`id_action`) REFERENCES `action` (`id_action`),
  CONSTRAINT `role_action_role_id_role_fk` FOREIGN KEY (`id_role`) REFERENCES `role` (`id_role`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_action`
--

LOCK TABLES `role_action` WRITE;
/*!40000 ALTER TABLE `role_action` DISABLE KEYS */;
INSERT INTO `role_action` VALUES (1,4,3),(1,5,4),(1,6,5),(1,7,6),(1,8,7),(1,9,8),(1,10,9),(1,14,10),(1,15,11),(1,16,12),(1,21,13),(2,4,14),(2,5,15),(2,6,16),(2,7,17),(2,11,18),(2,12,19),(2,13,20),(2,14,21),(2,17,22),(2,18,23),(2,19,24),(2,20,25),(2,21,26),(2,22,27),(2,23,28),(4,4,29),(4,6,30),(4,7,31),(4,14,32),(2,24,33);
/*!40000 ALTER TABLE `role_action` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staff`
--

DROP TABLE IF EXISTS `staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `staff` (
  `id_staff` int(11) NOT NULL AUTO_INCREMENT,
  `full_name` varchar(45) DEFAULT NULL,
  `contact_number` varchar(100) DEFAULT NULL,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `id_role` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_staff`),
  KEY `fk_role_staff_idx` (`id_role`),
  CONSTRAINT `fk_role_staff` FOREIGN KEY (`id_role`) REFERENCES `role` (`id_role`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff`
--

LOCK TABLES `staff` WRITE;
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
INSERT INTO `staff` VALUES (8,'cosqun','43333','cosqun','HvU9AoYpvpzwudpSKTAwCw==',2),(9,'Fuad','323221','Fuad','HvU9AoYpvpzwudpSKTAwCw==',1),(10,'Memmed Huseynov','050 334 56 65','manager001','12345',2),(11,'Elmir Veliyev','050 663 36 63','staff001','12345',1),(12,'Hesen Eliyev','050 663 33 33','repairer001','HvU9AoYpvpzwudpSKTAwCw==',1);
/*!40000 ALTER TABLE `staff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id_user` int(11) NOT NULL AUTO_INCREMENT,
  `full_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `contact_number` varchar(45) DEFAULT NULL,
  `id_role` int(11) DEFAULT NULL,
  `fin` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_user`),
  KEY `fk_role_user_idx` (`id_role`),
  CONSTRAINT `fk_role_user` FOREIGN KEY (`id_role`) REFERENCES `role` (`id_role`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (4,'vusal','vusalrzayev0693@gmail.com','address','31331',3,'123456'),(5,'Cosqun','cosqunhsynv@gmail.com','dds','ewweew',3,'34322'),(6,'orxan','orxan@gmail.com','hdds','dsddf',3,'2121'),(7,'rauf','rauf@gma.cp','dfsf','323',3,'3223'),(8,'asasas','e@gnmai.com','ffsfds','rreer',3,'ewewrrewrwrwer'),(9,'weew','wewew','ewew','weew',3,'ewerrewerwwr'),(10,'Memmedov Hesen','hesen@gmail.de','dadasd','333 33 33 ',3,'14URLNd'),(11,'sdasds','adasdas','dasdas','dasdasdas',3,'123312'),(12,'Eli Huseynov','cosqunhsynv@gmail.com','Baku','055 764 65 66',3,'15TREGF'),(13,'Memmed Veliyev','cosqunhsynv@gmail.com','Baki','050 888 88 88',3,'14FDREe');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-05-07 11:08:04
