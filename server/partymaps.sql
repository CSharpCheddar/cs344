-- MySQL dump 10.13  Distrib 5.7.28, for Linux (x86_64)
--
-- Host: localhost    Database: partymaps
-- ------------------------------------------------------
-- Server version	5.7.28-0ubuntu0.18.04.4

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
-- Table structure for table `Addresses`
--

DROP TABLE IF EXISTS `Addresses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Addresses` (
  `hoster` text NOT NULL,
  `handle` text NOT NULL,
  `address` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Addresses`
--

LOCK TABLES `Addresses` WRITE;
/*!40000 ALTER TABLE `Addresses` DISABLE KEYS */;
INSERT INTO `Addresses` VALUES ('admin3','test3','321123'),('dczosek2','Drew','12edmfm'),('Tester1','Tester1.1','420 High ave.'),('Timmy','Tester1.1','420 High ave.'),('jimmerson','jimmerson','test'),('Lyle12345','hdjdjsne','16931 south 96th ct'),('admin','gerald','123'),('admin10','gerald','123');
/*!40000 ALTER TABLE `Addresses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Credentials`
--

DROP TABLE IF EXISTS `Credentials`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Credentials` (
  `username` text NOT NULL,
  `password` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Credentials`
--

LOCK TABLES `Credentials` WRITE;
/*!40000 ALTER TABLE `Credentials` DISABLE KEYS */;
INSERT INTO `Credentials` VALUES ('admin','null'),('dczosek','Password1'),('dczosek2','Password1'),('Timmy','Timmy'),('mengebose',''),('someone','null'),('tim','Pass12'),('Kyle1234','Kyle1234'),('Kyle12345','Kyle12345'),('David','null'),('Lyle12345','12345'),('2883jdne','dnemekkxjz'),('ujejksi','dnenenisus'),('jekemzks','ehsnzioaknw'),('keksmzkaks','dnejsokzmw'),('uhgdruh','fhjiigcd'),('admin4','12345'),('admin2','12345'),('admin10','12345');
/*!40000 ALTER TABLE `Credentials` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Friends`
--

DROP TABLE IF EXISTS `Friends`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Friends` (
  `pid` text NOT NULL,
  `user1` text NOT NULL,
  `user2` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Friends`
--

LOCK TABLES `Friends` WRITE;
/*!40000 ALTER TABLE `Friends` DISABLE KEYS */;
INSERT INTO `Friends` VALUES ('31ae7f2d-0b10-11ea-a8b8-1251ae7cbb8d','admin','admin3'),('1fe89638-0e40-11ea-a8b8-1251ae7cbb8d','dczosek2','dczosek'),('2c1275ac-0fbb-11ea-a8b8-1251ae7cbb8d','Tester1','Tester1'),('b9032987-0fe8-11ea-a8b8-1251ae7cbb8d','Tester1.1','Tester1'),('98b32308-10b2-11ea-a8b8-1251ae7cbb8d','tim','tim'),('d8e12c13-10b4-11ea-a8b8-1251ae7cbb8d','tim','someone'),('0b708361-10bd-11ea-a8b8-1251ae7cbb8d','David','David'),('6d328615-10c1-11ea-a8b8-1251ae7cbb8d','uhgdruh','david'),('349c556d-1ba3-11ea-a8b8-1251ae7cbb8d','admin','admin2'),('d1de1dbf-1ba3-11ea-a8b8-1251ae7cbb8d','admin10','admin');
/*!40000 ALTER TABLE `Friends` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Messaging`
--

DROP TABLE IF EXISTS `Messaging`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Messaging` (
  `mid` text NOT NULL,
  `sid` text NOT NULL,
  `rid` text NOT NULL,
  `msg` text NOT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `wasRead` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Messaging`
--

LOCK TABLES `Messaging` WRITE;
/*!40000 ALTER TABLE `Messaging` DISABLE KEYS */;
INSERT INTO `Messaging` VALUES ('9ffb8c93-0cbe-11ea-a8b8-1251ae7cbb8d','Tester1','Tester1.1','test2','2019-11-22 00:25:45',1),('b60d2ee3-0cbe-11ea-a8b8-1251ae7cbb8d','Tester1.1','Tester1','Tester1.1\'__','2019-11-22 00:26:22',1),('8f02b056-0f8b-11ea-a8b8-1251ae7cbb8d','Tester1','Tester1','What was that?','2019-11-25 13:57:46',1),('a8c856e9-0f8b-11ea-a8b8-1251ae7cbb8d','Tester1','Tester1.1','\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n','2019-11-25 13:58:29',1),('cc9d7787-0f8b-11ea-a8b8-1251ae7cbb8d','Tester1.1','Tester1','\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n','2019-11-25 13:59:29',1),('34613d5a-0fe8-11ea-a8b8-1251ae7cbb8d','Tester1','Tester1','\\\'\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\\'','2019-11-26 01:00:57',1),('7538f784-0fe8-11ea-a8b8-1251ae7cbb8d','Tester1.1','Tester1','test','2019-11-26 01:02:46',1),('eb4f8d03-10a1-11ea-a8b8-1251ae7cbb8d','','','null','2019-11-26 23:10:21',1),('edeed709-10a1-11ea-a8b8-1251ae7cbb8d','','','','2019-11-26 23:10:25',1),('abd28ea8-10b2-11ea-a8b8-1251ae7cbb8d','tim','someone','null','2019-11-27 01:10:16',1),('b47f074e-10b4-11ea-a8b8-1251ae7cbb8d','someone','someone','hello','2019-11-27 01:24:49',1),('d0500236-10b4-11ea-a8b8-1251ae7cbb8d','tim','someone','hello','2019-11-27 01:25:36',0),('cfc7c22f-10c1-11ea-a8b8-1251ae7cbb8d','uhgdruh','david','david','2019-11-27 02:58:38',0),('dd7051e4-10c1-11ea-a8b8-1251ae7cbb8d','uhgdruh','david','david','2019-11-27 02:59:01',0),('e7b0d65e-10c1-11ea-a8b8-1251ae7cbb8d','uhgdruh','david','yoooooo','2019-11-27 02:59:19',0),('0323816e-10c2-11ea-a8b8-1251ae7cbb8d','uhgdruh','david','yooooooo','2019-11-27 03:00:05',0),('0aedda4c-10c2-11ea-a8b8-1251ae7cbb8d','uhgdruh','david','yooooooooooo','2019-11-27 03:00:18',0),('21e6935d-10c2-11ea-a8b8-1251ae7cbb8d','uhgdruh','david','yooooooooooooo78','2019-11-27 03:00:56',0),('8a5a3f92-1249-11ea-a8b8-1251ae7cbb8d','uhgdruh','david','yooooooooooooo78','2019-11-29 01:42:45',0),('3f7378f0-1ba3-11ea-a8b8-1251ae7cbb8d','admin','admin3','null','2019-12-10 23:17:34',1);
/*!40000 ALTER TABLE `Messaging` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-12-11  1:40:56
