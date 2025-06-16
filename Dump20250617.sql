-- MySQL dump 10.13  Distrib 8.0.42, for macos15 (arm64)
--
-- Host: localhost    Database: doctor_booking_v1
-- ------------------------------------------------------
-- Server version	9.3.0

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
-- Table structure for table `appointment`
--

DROP TABLE IF EXISTS `appointment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `appointment` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `version` bigint NOT NULL,
  `patient_id` bigint NOT NULL,
  `slot_id` bigint NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `doctor_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_bpeuy47ge8oi9u1tlvtxelh9u` (`slot_id`),
  KEY `FKg90ck1kd0p4rbamwc22jd2oql` (`patient_id`),
  KEY `FKoeb98n82eph1dx43v3y2bcmsl` (`doctor_id`),
  CONSTRAINT `FKg90ck1kd0p4rbamwc22jd2oql` FOREIGN KEY (`patient_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKoeb98n82eph1dx43v3y2bcmsl` FOREIGN KEY (`doctor_id`) REFERENCES `doctor` (`id`),
  CONSTRAINT `FKscqgyimyyk4b8uv1ut4atiaif` FOREIGN KEY (`slot_id`) REFERENCES `appointment_slot` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appointment`
--

LOCK TABLES `appointment` WRITE;
/*!40000 ALTER TABLE `appointment` DISABLE KEYS */;
INSERT INTO `appointment` VALUES (1,0,3,421,'2025-06-16 17:51:24.396000',4),(2,0,3,689,'2025-06-16 19:44:10.856000',4);
/*!40000 ALTER TABLE `appointment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `appointment_slot`
--

DROP TABLE IF EXISTS `appointment_slot`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `appointment_slot` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `version` bigint NOT NULL,
  `end_time` datetime(6) NOT NULL,
  `start_time` datetime(6) NOT NULL,
  `doctor_id` bigint NOT NULL,
  `booked` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKb88e0qlwkady907krxcwmy7q0` (`doctor_id`),
  CONSTRAINT `FKb88e0qlwkady907krxcwmy7q0` FOREIGN KEY (`doctor_id`) REFERENCES `doctor` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=698 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appointment_slot`
--

LOCK TABLES `appointment_slot` WRITE;
/*!40000 ALTER TABLE `appointment_slot` DISABLE KEYS */;
INSERT INTO `appointment_slot` VALUES (410,0,'2025-06-18 02:15:00.000000','2025-06-18 02:00:00.000000',4,_binary '\0'),(411,0,'2025-06-18 02:35:00.000000','2025-06-18 02:20:00.000000',4,_binary '\0'),(412,0,'2025-06-18 02:55:00.000000','2025-06-18 02:40:00.000000',4,_binary '\0'),(413,0,'2025-06-18 03:15:00.000000','2025-06-18 03:00:00.000000',4,_binary '\0'),(414,0,'2025-06-18 03:35:00.000000','2025-06-18 03:20:00.000000',4,_binary '\0'),(415,0,'2025-06-18 03:55:00.000000','2025-06-18 03:40:00.000000',4,_binary '\0'),(416,0,'2025-06-18 04:15:00.000000','2025-06-18 04:00:00.000000',4,_binary '\0'),(417,0,'2025-06-18 04:35:00.000000','2025-06-18 04:20:00.000000',4,_binary '\0'),(418,0,'2025-06-18 04:55:00.000000','2025-06-18 04:40:00.000000',4,_binary '\0'),(419,0,'2025-06-18 05:15:00.000000','2025-06-18 05:00:00.000000',4,_binary '\0'),(420,0,'2025-06-18 05:35:00.000000','2025-06-18 05:20:00.000000',4,_binary '\0'),(421,1,'2025-06-18 05:55:00.000000','2025-06-18 05:40:00.000000',4,_binary ''),(422,0,'2025-06-18 06:15:00.000000','2025-06-18 06:00:00.000000',4,_binary '\0'),(423,0,'2025-06-18 06:35:00.000000','2025-06-18 06:20:00.000000',4,_binary '\0'),(424,0,'2025-06-18 06:55:00.000000','2025-06-18 06:40:00.000000',4,_binary '\0'),(425,0,'2025-06-18 07:15:00.000000','2025-06-18 07:00:00.000000',4,_binary '\0'),(426,0,'2025-06-18 07:35:00.000000','2025-06-18 07:20:00.000000',4,_binary '\0'),(427,0,'2025-06-18 07:55:00.000000','2025-06-18 07:40:00.000000',4,_binary '\0'),(428,0,'2025-06-18 08:15:00.000000','2025-06-18 08:00:00.000000',4,_binary '\0'),(429,0,'2025-06-18 08:35:00.000000','2025-06-18 08:20:00.000000',4,_binary '\0'),(430,0,'2025-06-18 08:55:00.000000','2025-06-18 08:40:00.000000',4,_binary '\0'),(431,0,'2025-06-18 09:15:00.000000','2025-06-18 09:00:00.000000',4,_binary '\0'),(432,0,'2025-06-18 09:35:00.000000','2025-06-18 09:20:00.000000',4,_binary '\0'),(433,0,'2025-06-18 09:55:00.000000','2025-06-18 09:40:00.000000',4,_binary '\0'),(668,0,'2025-06-19 02:15:00.000000','2025-06-19 02:00:00.000000',4,_binary '\0'),(669,0,'2025-06-19 02:35:00.000000','2025-06-19 02:20:00.000000',4,_binary '\0'),(670,0,'2025-06-19 02:55:00.000000','2025-06-19 02:40:00.000000',4,_binary '\0'),(671,0,'2025-06-19 03:15:00.000000','2025-06-19 03:00:00.000000',4,_binary '\0'),(672,0,'2025-06-19 03:35:00.000000','2025-06-19 03:20:00.000000',4,_binary '\0'),(673,0,'2025-06-19 03:55:00.000000','2025-06-19 03:40:00.000000',4,_binary '\0'),(674,0,'2025-06-19 04:15:00.000000','2025-06-19 04:00:00.000000',4,_binary '\0'),(675,0,'2025-06-19 04:35:00.000000','2025-06-19 04:20:00.000000',4,_binary '\0'),(676,0,'2025-06-19 04:55:00.000000','2025-06-19 04:40:00.000000',4,_binary '\0'),(677,0,'2025-06-19 06:15:00.000000','2025-06-19 06:00:00.000000',4,_binary '\0'),(678,0,'2025-06-19 06:35:00.000000','2025-06-19 06:20:00.000000',4,_binary '\0'),(679,0,'2025-06-19 06:55:00.000000','2025-06-19 06:40:00.000000',4,_binary '\0'),(680,0,'2025-06-19 07:15:00.000000','2025-06-19 07:00:00.000000',4,_binary '\0'),(681,0,'2025-06-19 07:35:00.000000','2025-06-19 07:20:00.000000',4,_binary '\0'),(682,0,'2025-06-19 07:55:00.000000','2025-06-19 07:40:00.000000',4,_binary '\0'),(683,0,'2025-06-19 08:15:00.000000','2025-06-19 08:00:00.000000',4,_binary '\0'),(684,0,'2025-06-19 08:35:00.000000','2025-06-19 08:20:00.000000',4,_binary '\0'),(685,0,'2025-06-19 08:55:00.000000','2025-06-19 08:40:00.000000',4,_binary '\0'),(686,0,'2025-06-19 09:15:00.000000','2025-06-19 09:00:00.000000',4,_binary '\0'),(687,0,'2025-06-19 09:35:00.000000','2025-06-19 09:20:00.000000',4,_binary '\0'),(688,0,'2025-06-19 09:55:00.000000','2025-06-19 09:40:00.000000',4,_binary '\0'),(689,1,'2025-06-20 02:15:00.000000','2025-06-20 02:00:00.000000',4,_binary ''),(690,0,'2025-06-20 02:35:00.000000','2025-06-20 02:20:00.000000',4,_binary '\0'),(691,0,'2025-06-20 02:55:00.000000','2025-06-20 02:40:00.000000',4,_binary '\0'),(692,0,'2025-06-20 03:15:00.000000','2025-06-20 03:00:00.000000',4,_binary '\0'),(693,0,'2025-06-20 03:35:00.000000','2025-06-20 03:20:00.000000',4,_binary '\0'),(694,0,'2025-06-20 03:55:00.000000','2025-06-20 03:40:00.000000',4,_binary '\0'),(695,0,'2025-06-20 04:15:00.000000','2025-06-20 04:00:00.000000',4,_binary '\0'),(696,0,'2025-06-20 04:35:00.000000','2025-06-20 04:20:00.000000',4,_binary '\0'),(697,0,'2025-06-20 04:55:00.000000','2025-06-20 04:40:00.000000',4,_binary '\0');
/*!40000 ALTER TABLE `appointment_slot` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doctor`
--

DROP TABLE IF EXISTS `doctor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `doctor` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `version` bigint NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `specialty` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `license_number` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_qp93rhmjry3jlfr9thw5hmgty` (`license_number`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctor`
--

LOCK TABLES `doctor` WRITE;
/*!40000 ALTER TABLE `doctor` DISABLE KEYS */;
INSERT INTO `doctor` VALUES (4,0,'081-xxx-xxxx','อยุกรรม','Dr. POP','dr-pop-vnurser-1@gmail.com',NULL),(5,0,'081-xxx-xxxx','อยุกรรม','Dr. POP','dr-pop-vnurser-1@gmail.com',NULL),(6,0,'081-xxx-xxxx','อยุกรรม','Dr. POP','dr-pop-vnurser-1@gmail.com',NULL),(7,0,'081-xxx-xxxx','อยุกรรม','Dr. POP','dr-pop-vnurser-1@gmail.com',NULL),(8,0,'081-xxx-xxxx','อยุกรรม','Dr. POP','dr-pop-vnurser-1@gmail.com',NULL),(9,0,'081-xxx-xxxx','อยุกรรม','Dr. POP','dr-pop-vnurser-1@gmail.com',NULL),(10,0,'081-xxx-xxxx','อยุกรรม','Dr. POP','dr-pop-vnurser-1@gmail.com',NULL),(11,0,'081-xxx-xxxx','อยุกรรม','Dr. POP','dr-pop-vnurser-1@gmail.com',NULL),(12,0,'081-xxx-xxxx','อยุกรรม','Dr. POP-2','dr-pop-vnurser-2@gmail.com',NULL),(13,0,'081-xxx-xxxx','อยุกรรม','Dr. POP-3','dr-pop-vnurser-3@gmail.com',NULL);
/*!40000 ALTER TABLE `doctor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doctor_schedule`
--

DROP TABLE IF EXISTS `doctor_schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `doctor_schedule` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `version` bigint NOT NULL,
  `end_time` time NOT NULL,
  `start_time` time NOT NULL,
  `doctor_id` bigint NOT NULL,
  `day_of_week` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrresxag4ex638q3fincrya0wr` (`doctor_id`),
  CONSTRAINT `FKrresxag4ex638q3fincrya0wr` FOREIGN KEY (`doctor_id`) REFERENCES `doctor` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctor_schedule`
--

LOCK TABLES `doctor_schedule` WRITE;
/*!40000 ALTER TABLE `doctor_schedule` DISABLE KEYS */;
INSERT INTO `doctor_schedule` VALUES (12,0,'17:00:00','13:00:00',4,'THURSDAY'),(13,0,'12:00:00','09:00:00',4,'FRIDAY'),(15,0,'17:00:00','13:00:00',4,'WEDNESDAY'),(16,0,'12:00:00','09:00:00',4,'WEDNESDAY');
/*!40000 ALTER TABLE `doctor_schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `version` bigint NOT NULL,
  `username` varchar(255) NOT NULL,
  `role` varchar(7) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,0,'testdoc','DOCTOR','1234'),(2,0,'testdoc1','DOCTOR','1234'),(3,0,'testpatient','PATIENT','test123'),(4,0,'doctor01','DOCTOR','1234');
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

-- Dump completed on 2025-06-17  4:09:03
