-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: cerealkillers
-- ------------------------------------------------------
-- Server version	8.0.31

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
-- Table structure for table `day plan`
--

DROP TABLE IF EXISTS `day plan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `day plan` (
  `id` int NOT NULL AUTO_INCREMENT,
  `day` varchar(24) DEFAULT NULL,
  `breakfast` varchar(24) DEFAULT NULL,
  `lunch` varchar(24) DEFAULT NULL,
  `dinner` varchar(24) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `day plan`
--

LOCK TABLES `day plan` WRITE;
/*!40000 ALTER TABLE `day plan` DISABLE KEYS */;
/*!40000 ALTER TABLE `day plan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dayplan`
--

DROP TABLE IF EXISTS `dayplan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dayplan` (
  `id` int NOT NULL AUTO_INCREMENT,
  `day` varchar(24) DEFAULT NULL,
  `breakfast` varchar(24) DEFAULT NULL,
  `lunch` varchar(24) DEFAULT NULL,
  `dinner` varchar(24) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dayplan`
--

LOCK TABLES `dayplan` WRITE;
/*!40000 ALTER TABLE `dayplan` DISABLE KEYS */;
INSERT INTO `dayplan` VALUES (1,'Sunday','BeaverTails','Baingan Bharta','Beef Lo Mein'),(2,'Monday','Beef and Mustard Pie','Broccoli & Stilton soup','Baingan Bharta'),(3,'Tuesday','Chicken Handi','Chicken Handi','Broccoli & Stilton soup'),(4,'Wednesday','','',''),(5,'Thursday','','',''),(6,'Friday','','',''),(7,'Saturday','','','');
/*!40000 ALTER TABLE `dayplan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `favourites`
--

DROP TABLE IF EXISTS `favourites`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `favourites` (
  `id` int NOT NULL AUTO_INCREMENT,
  `mealname` varchar(24) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `favourites`
--

LOCK TABLES `favourites` WRITE;
/*!40000 ALTER TABLE `favourites` DISABLE KEYS */;
INSERT INTO `favourites` VALUES (1,'Baingan Bharta'),(2,'Beef and Mustard Pie'),(3,'Beef Lo Mein'),(4,'Broccoli & Stilton soup'),(5,'Chicken Handi'),(6,'Kung Pao Chicken'),(7,'BeaverTails'),(8,'Chicken Handi');
/*!40000 ALTER TABLE `favourites` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `groceries`
--

DROP TABLE IF EXISTS `groceries`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `groceries` (
  `id` int NOT NULL AUTO_INCREMENT,
  `grocery_name` varchar(24) DEFAULT NULL,
  `quantity` varchar(24) DEFAULT NULL,
  `useby` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `groceries`
--

LOCK TABLES `groceries` WRITE;
/*!40000 ALTER TABLE `groceries` DISABLE KEYS */;
INSERT INTO `groceries` VALUES (1,'Chicken','5 pounds','2022-12-12'),(2,'Eggs','12','2022-12-31');
/*!40000 ALTER TABLE `groceries` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(24) DEFAULT NULL,
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'brian','F3FE5A51A2BE8C6DC715028858FCBA82EE021BE7687E4F95B45086B8FFB1A23F');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-12-10 23:11:35
