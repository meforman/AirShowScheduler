CREATE DATABASE  IF NOT EXISTS `airshowscheduler` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;
USE `airshowscheduler`;
-- MySQL dump 10.13  Distrib 8.0.12, for Win64 (x86_64)
--
-- Host: localhost    Database: airshowscheduler
-- ------------------------------------------------------
-- Server version	8.0.12

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `airports`
--

DROP TABLE IF EXISTS `airports`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `airports` (
  `AirPortID` int(11) NOT NULL AUTO_INCREMENT,
  `AirportName` varchar(45) DEFAULT NULL,
  `AirportRunwayLength` int(11) DEFAULT NULL,
  `AirportFuelAvailable` varchar(45) DEFAULT NULL,
  `AirportCode` varchar(3) DEFAULT NULL,
  PRIMARY KEY (`AirPortID`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `airports`
--

LOCK TABLES `airports` WRITE;
/*!40000 ALTER TABLE `airports` DISABLE KEYS */;
INSERT INTO `airports` VALUES (2,'Orlando International',10000,'Both','MCO'),(3,'Memorial Field Airport (Hot Springs, AR)',5500,'AvGas','HOT'),(4,'Terre Haute Intl Airport',9000,'Both','HUF'),(5,'Chesterfield County Airport (Richmond, VA)',7500,'AvGas','FCI'),(7,'Denver International Airport',12000,'JetFuel','DIA'),(10,'Los Angeles Intl',12000,'Both','LAX'),(11,'Forbes Field (Topeka, KS)',13000,'Both','FOE'),(12,'Aspen Airport (Aspen, CO)',8000,'Both','ASE'),(13,'Mid-Continent International (Kansas City)',11000,'Both','MCI'),(14,'Tuscon International Airport',10000,'Both','TUS'),(15,'Long Beach Airport',8000,'Both','LGB'),(16,'Santa Ana Airport',10000,'Both','SNA'),(17,'Hillsboro Airport (Portland, OR)',7000,'AvGas','HIO'),(18,'Spokane Intl Airport',8000,'Both','GEG'),(19,'Rapid City (SD) Airport',10000,'Both','RAP'),(20,'Billard Air Park (Topeka, KS)',8000,'AvGas','TBA');
/*!40000 ALTER TABLE `airports` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `airshowacts`
--

DROP TABLE IF EXISTS `airshowacts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `airshowacts` (
  `ActID` int(11) NOT NULL AUTO_INCREMENT,
  `ActName` varchar(45) DEFAULT NULL,
  `ActFuelType` varchar(45) DEFAULT NULL,
  `ActRunwayRequired` int(11) DEFAULT NULL,
  `ActCost` int(11) DEFAULT NULL,
  PRIMARY KEY (`ActID`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `airshowacts`
--

LOCK TABLES `airshowacts` WRITE;
/*!40000 ALTER TABLE `airshowacts` DISABLE KEYS */;
INSERT INTO `airshowacts` VALUES (1,'U.S. Navy Blue Angels','JetFuel',10000,12000),(2,'U.S. Air Force Thunderbirds','JetFuel',10000,13000),(3,'Class of \'45 (P-51 and F4-U)','AvGas',5000,3000),(4,'Jeff Bourboon (Twin Extra 300)','AvGas',5000,2500),(5,'Shell Aerobatic Team (T-6 Texans)','AvGas',6000,7500),(6,'Bob Carlton (Bede Mini-Jet)','JetFuel',5000,4000),(7,'Justin Lewis (BEDE-5J Mini Jet)','JetFuel',6000,3500),(8,'Kyle Franklin (Waco Biplane)','AvGas',5000,2000),(9,'Heritage Flight (P-51, F-16, A-10)','JetFuel',10000,8000),(10,'Geico Skytypers (A-6 Texans)','AvGas',6500,8000),(11,'Skip Stewart (Pitts S2-A)','AvGas',5000,1500),(12,'Jim Peltz (Piper Commanche)','AvGas',5000,1000),(13,'U>S. Army Golden Knights (Parachute Demo)','None',5000,2500),(14,'Julie Clark (Pitts S2-A)','AvGas',5000,1500),(15,'Commemorative Air Force (Tora, Tora, Tora)','AvGas',7000,6000),(16,'Panchito (B-25 Demo)','AvGas',8000,750),(17,'Gunfighter (P-51 Demo)','AvGas',6000,550),(18,'Red Bull P-38 Demo','AvGas',8000,1200),(19,'Peggy Wagstaff (Extra 300)','AvGas',6000,1500),(20,'Gene Soucy (Grumman Showcat)','AvGas',5000,2000),(21,'Combat Air Museum SBD Dauntless II Demo','AvGas',5000,1500);
/*!40000 ALTER TABLE `airshowacts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `airshows`
--

DROP TABLE IF EXISTS `airshows`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `airshows` (
  `AirShowID` int(11) NOT NULL AUTO_INCREMENT,
  `AirShowAirportID` varchar(45) DEFAULT NULL,
  `AirShowDate` date DEFAULT NULL,
  `AirshowName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`AirShowID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `airshows`
--

LOCK TABLES `airshows` WRITE;
/*!40000 ALTER TABLE `airshows` DISABLE KEYS */;
INSERT INTO `airshows` VALUES (1,'13','2019-06-21','Great Mid-American AirShow'),(3,'14','2019-06-28','Tuscon International Fly-In'),(4,'3','2019-06-02','Greater Arkansas Fancy of Flight'),(5,'11','2019-05-27','SuperBatics 2019'),(6,'16','2019-05-21','Santa Ana Vintage Warbird Show'),(7,'19','2019-05-21','Thunder over the Black Hills'),(8,'4','2019-07-04','Wheel\'s Up Indiana!!'),(9,'17','2019-07-11','Oregon Wings Over the Columbia'),(10,'20','2019-07-21','Billard Old Bipes Fly-In and Show');
/*!40000 ALTER TABLE `airshows` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `performances`
--

DROP TABLE IF EXISTS `performances`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `performances` (
  `PerformanceId` int(11) NOT NULL AUTO_INCREMENT,
  `PerformanceAirShowID` int(11) DEFAULT NULL,
  `PerformanceActID` int(11) DEFAULT NULL,
  PRIMARY KEY (`PerformanceId`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `performances`
--

LOCK TABLES `performances` WRITE;
/*!40000 ALTER TABLE `performances` DISABLE KEYS */;
INSERT INTO `performances` VALUES (1,1,10),(2,4,12),(3,4,6),(5,4,9),(10,4,3),(11,4,20),(12,4,8),(13,4,4),(14,10,14),(15,4,21),(16,4,11),(17,7,1),(18,3,9),(19,7,17);
/*!40000 ALTER TABLE `performances` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'airshowscheduler'
--
/*!50003 DROP PROCEDURE IF EXISTS `getQualActs` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `getQualActs`(IN aShowId INT)
BEGIN
SELECT * FROM airshowacts WHERE ActRunwayRequired <=
(SELECT AirportRunwayLength from airports 
	join airshows on (airports.AirPortID = airshows.AirShowAirportID)
		WHERE airshows.AirShowID = aShowId) AND ActFuelType = (SELECT AirportFuelAvailable from airports 
	join airshows on (airports.AirPortID = airshows.AirShowAirportID)
		WHERE airshows.AirShowID = aShowId) OR (SELECT AirportFuelAvailable from airports 
	join airshows on (airports.AirPortID = airshows.AirShowAirportID) WHERE airshows.AirShowID = aShowId) = 'Both';
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-03-16 22:54:26
