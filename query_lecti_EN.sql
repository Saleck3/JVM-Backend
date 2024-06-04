-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: lecti
-- ------------------------------------------------------
-- Server version	8.0.33

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
-- Table structure for table `apple`
--

DROP TABLE IF EXISTS `apple`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `apple` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(40) NOT NULL,
  `module_id` int DEFAULT NULL,
  `max_score` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `module_id` (`module_id`),
  CONSTRAINT `apple_ibfk_1` FOREIGN KEY (`module_id`) REFERENCES `module` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `apple`
--

LOCK TABLES `apple` WRITE;
/*!40000 ALTER TABLE `apple` DISABLE KEYS */;
INSERT INTO `apple` VALUES (1,'A',1,0),(2,'E',1,0),(3,'I',1,0),(4,'O',1,0),(5,'U',1,0),(6,'M',1,0),(7,'P',1,0),(8,'B',1,0),(9,'S',1,0),(10,'L',1,0),(11,'LL',1,0),(12,'N',1,0),(13,'D',1,0),(14,'T',1,0),(15,'R',1,0),(16,'RR',1,0),(17,'F',1,0),(18,'Ñ',1,0),(19,'K',1,0),(20,'Y',1,0),(21,'C',1,0),(22,'H',1,0),(23,'V',1,0),(24,'Q',1,0),(25,'J',1,0),(26,'G',1,0),(27,'Z',1,0),(28,'W',1,0),(29,'X',1,0),(30,'CH',1,0),(31,'BL',2,0),(32,'BR',2,0),(33,'CL',2,0),(34,'CR',2,0),(35,'DR',2,0),(36,'FL',2,0),(37,'FR',2,0),(38,'GL',2,0),(39,'GR',2,0),(40,'PL',2,0),(41,'PR',2,0),(42,'TR',2,0),(43,'TL',2,0),(44,'ORACIONES',3,0),(45,'ADIVINANZAS',3,0),(46,'TRABALENGUAS',3,0),(47,'TEST',NULL,NULL);
/*!40000 ALTER TABLE `apple` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `avatar`
--

DROP TABLE IF EXISTS `avatar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `avatar` (
  `id` int NOT NULL AUTO_INCREMENT,
  `avatar_name` varchar(40) NOT NULL,
  `url` varchar(50) DEFAULT NULL,
  `value` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `avatar`
--

LOCK TABLES `avatar` WRITE;
/*!40000 ALTER TABLE `avatar` DISABLE KEYS */;
/*!40000 ALTER TABLE `avatar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exercise`
--

DROP TABLE IF EXISTS `exercise`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `exercise` (
  `id` int NOT NULL AUTO_INCREMENT,
  `apple_id` int NOT NULL,
  `parameters` varchar(2000) NOT NULL,
  `exercise_type` enum('letter_ordering','image_selection','image_writing','audio_repeating','text_read','video','worksheets') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `apple_id` (`apple_id`),
  CONSTRAINT `exercise_ibfk_2` FOREIGN KEY (`apple_id`) REFERENCES `apple` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=106 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exercise`
--

LOCK TABLES `exercise` WRITE;
/*!40000 ALTER TABLE `exercise` DISABLE KEYS */;
INSERT INTO `exercise` VALUES (2,6,'{\"options\": [\"MA\",\"NO\"],\"correctAnswer\": \"MANO\",\"image\":\"\"}','letter_ordering'),(3,6,'{\"options\": [\"MESA\",\"MOTO\",\"MONO\",\"MARCO\"],\"correctAnswer\": \"MONO\",\"image\": \"ImageURL\"}','image_selection'),(4,6,'{\"correctAnswer\": \"MONO\",\"preSelectedLetters\":[\"N\"], \"image\": \"ImageURL\"}','image_writing'),(5,6,'{\"image\":\"\"}','video'),(6,6,'{\"image\":\"\"}','worksheets'),(7,6,'{\"correctAnswer\": \"MOMIA\"}','audio_repeating'),(8,6,'{\"correctAnswer\": \"MIMA\"}','text_read'),(9,6,'{\"options\": [\"MA\", \"ME\", \"MI\", \"MO\", \"MU\"], \"label\":\"_ _ SA\", \"correctAnswer\": \"ME\",\"image\": \"ImageURL\"}','image_selection'),(10,6,'{\"options\": [\"ME\",\"LON\"],\"correctAnswer\": \"MELON\",\"image\":\"\"}','letter_ordering'),(11,6,'{\"options\": [\"MA\",\"ME\",\"MI\",\"MO\", \"MU\"],\"label\": \"TO\",\"correctAnswer\": \"MO\",\"image\": \"ImageURL_moto\"}','image_selection'),(12,6,'{\"options\": [\"MA\",\"ME\",\"MI\",\"MO\", \"MU\"],\"label\": \"LETA\",\"correctAnswer\": \"MU\",\"image\": \"ImageURL_muleta\"}','image_selection'),(13,6,'{\"correctAnswer\": \"MI MAMA\"}','text_read'),(14,6,'{\"correctAnswer\": \"MI MAMA ME AMA\"}','audio_repeating'),(15,6,'{\"correctAnswer\": \"MI MAMA ME MIMA\"}','text_read'),(16,6,'{\"correctAnswer\": \"MI MAMA ME MIMA A MI\"}','audio_repeating'),(17,1,'{\"options\": [\"A\",\"VION\"],\"correctAnswer\": \"AVION\",\"image\":\"\"}','letter_ordering'),(18,1,'{\"options\": [\"A\", \"E\", \"I\", \"O\", \"U\"], \"label\":\"_ RAÑA\", \"correctAnswer\": \"A\",\"image\": \"ImageURL\"}','image_selection'),(19,1,'{\"correctAnswer\": \"ABEJA\",\"preSelectedLetters\": [{\"B\": 2},{\"E\": 3},{\"J\": 4}], \"image\": \"ImageURL\"}','image_writing'),(20,1,'{\"correctAnswer\": \"ANANA\"}','audio_repeating'),(21,2,'{\"options\": [\"CO\",\"ES\", \"BA\"],\"correctAnswer\": \"ESCOBA\",\"image\":\"\"}','letter_ordering'),(22,2,'{\"correctAnswer\": \"ELEFANTE\",\"preSelectedLetters\": [{\"L\": 2},{\"F\": 4},{\"N\": 6}, {\"T\": 7}], \"image\": \"ImageURL\"}','image_writing'),(23,2,'{\"options\": [\"JO\",\"PE\", \"ES\"],\"correctAnswer\": \"ESPEJO\",\"image\":\"\"}','letter_ordering'),(24,2,'{\"correctAnswer\": \"ESCOBA\"}','audio_repeating'),(25,2,'{\"correctAnswer\": \"ESCALERA\",\"preSelectedLetters\": [{\"S\": 2},{\"C\": 3},{\"L\": 5}, {\"R\": 7}], \"image\": \"ImageURL\"}','image_writing'),(26,3,'{\"correctAnswer\": \"ISLA\",\"preSelectedLetters\": [{\"S\": 2},{\"L\": 3}], \"image\": \"ImageURL\"}','image_writing'),(27,3,'{\"options\": [\"MAN\",\"I\"],\"correctAnswer\": \"ESCOBA\",\"image\":\"\"}','letter_ordering'),(28,3,'{\"options\": [\"IGLU\",\"ISLA\",\"INODORO\",\"IGLESIA\"],\"correctAnswer\": \"ISLA\",\"image\": \"ImageURL\"}','image_selection'),(29,3,'{\"correctAnswer\": \"IGLESIA\",\"preSelectedLetters\": [{\"G\": 2},{\"L\": 3}, {\"S\": 5}], \"image\": \"ImageURL\"}','image_writing'),(30,3,'{\"correctAnswer\": \"IGUANA\"}','audio_repeating'),(31,4,'{\"correctAnswer\": \"OLLA\",\"preSelectedLetters\": [{\"L\": 2},{\"L\": 3}], \"image\": \"ImageURL\"}','image_writing'),(32,4,'{\"options\": [\"JO\",\"O\"],\"correctAnswer\": \"OJO\",\"image\":\"\"}','letter_ordering'),(33,4,'{\"options\": [\"VE\",\"JA\", \"O\"],\"correctAnswer\": \"OVEJA\",\"image\":\"\"}','letter_ordering'),(34,4,'{\"correctAnswer\": \"OSO\",\"preSelectedLetters\": [{\"S\": 2}], \"image\": \"ImageURL\"}','image_writing'),(35,4,'{\"correctAnswer\": \"OSO\"}','audio_repeating'),(36,5,'{\"correctAnswer\": \"UVA\",\"preSelectedLetters\": [{\"v\": 2}], \"image\": \"ImageURL\"}','image_writing'),(37,5,'{\"correctAnswer\": \"UNICORNIO\",\"preSelectedLetters\": [{\"N\": 2}, {\"C\": 3},{\"R\": 5}, {\"N\": 6}], \"image\": \"ImageURL\"}','letter_ordering'),(38,5,'{\"options\": [\"UNIVERSO\",\"UÑA\",\"UNION\",\"UTILIDAD\"],\"correctAnswer\": \"UÑA\",\"image\": \"ImageURL\"}','image_selection'),(39,5,'{\"correctAnswer\": \"UVA\"}','audio_repeating'),(40,7,'{\"options\": [\"PA\",\"PE\",\"PI\",\"PO\", \"PU\"],\"correctAnswer\": \"PO\"}','image_selection'),(41,7,'{\"correctAnswer\": \"PA\"}','text_read'),(42,7,'{\"correctAnswer\": \"PI\"}','text_read'),(43,7,'{\"correctAnswer\": \"PE\"}','text_read'),(44,7,'{\"correctAnswer\": \"PU\"}','text_read'),(45,7,'{\"correctAnswer\": \"PIPA\"}','text_read'),(46,7,'{\"correctAnswer\": \"PEPA\"}','text_read'),(47,7,'{\"correctAnswer\": \"PUPO\"}','text_read'),(48,7,'{\"correctAnswer\": \"MI PAPA\"}','text_read'),(49,7,'{\"correctAnswer\": \"MI PAPA ME AMA\"}','text_read'),(50,7,'{\"options\": [\"PA\", \"PE\", \"PI\", \"PO\", \"PU\"], \"label\":\"_ _ RATA\", \"correctAnswer\": \"PI\",\"image\": \"ImageURL\"}','image_selection'),(51,7,'{\"correctAnswer\": \"PIRATA\",\"preSelectedLetters\": [{\"R\": 3}, {\"T\": 5}], \"image\": \"ImageURL\"}','image_writing'),(52,7,'{\"options\": [\"LO\",\"PE\", \"TA\"],\"correctAnswer\": \"PELOTA\",\"image\":\"\"}','letter_ordering'),(53,7,'{\"correctAnswer\": \"PATO\",\"preSelectedLetters\": [{\"T\": 3}], \"image\": \"ImageURL\"}','image_writing'),(54,7,'{\"correctAnswer\": \"PIRATA\",\"preSelectedLetters\": [{\"R\": 3}, {\"G\": 5}, {\"S\":8}], \"image\": \"ImageURL\"}','image_writing'),(55,7,'{\"correctAnswer\": \"PERA\",\"preSelectedLetters\": [{\"R\": 3}], \"image\": \"ImageURL\"}','image_writing'),(56,7,'{\"correctAnswer\": \"PIPA\",\"preSelectedLetters\": [], \"image\": \"ImageURL\"}','image_writing'),(57,7,'{\"correctAnswer\": \"MI PAPA\",\"preSelectedLetters\": [\"\"]}','image_writing'),(58,7,'{\"correctAnswer\": \"MI PAPA ME MIMA\",\"preSelectedLetters\": []}','image_writing'),(59,7,'{\"correctAnswer\": \"MI PAPA ME AMA\",\"preSelectedLetters\": []}','image_writing'),(60,8,'{\"options\": [\"BA\",\"BE\",\"BI\",\"BO\", \"BU\"],\"correctAnswer\": \"BO\"}','image_selection'),(61,8,'{\"correctAnswer\": \"BO\"}','text_read'),(62,8,'{\"correctAnswer\": \"BI\"}','text_read'),(63,8,'{\"correctAnswer\": \"BU\"}','text_read'),(64,8,'{\"correctAnswer\": \"BE\"}','text_read'),(65,8,'{\"correctAnswer\": \"BA\"}','text_read'),(66,8,'{\"correctAnswer\": \"BEBE\"}','text_read'),(67,8,'{\"correctAnswer\": \"BEBA\"}','text_read'),(68,8,'{\"correctAnswer\": \"MI BEBE\"}','text_read'),(69,8,'{\"options\": [\"BA\", \"BE\", \"BI\", \"BO\", \"BU\"], \"label\":\"_ _FANDA \", \"correctAnswer\": \"BU\",\"image\": \"ImageURL\"}','image_selection'),(70,8,'{\"correctAnswer\": \"BARCO\",\"preSelectedLetters\": [{\"R\": 3}, {\"C\": 4}], \"image\": \"ImageURL\"}','image_writing'),(71,8,'{\"correctAnswer\": \"BICICLETA\",\"preSelectedLetters\": [{\"B\": 1}, {\"C\": 3}, {\"C\":5}, {\"L\":6}, {\"T\":7} ], \"image\": \"ImageURL\"}','image_writing'),(72,8,'{\"options\": [\"TA\",\"CLE\", \"BI\", \"CI\"],\"correctAnswer\": \"BICICLETA\",\"image\":\"\"}','letter_ordering'),(73,8,'{\"correctAnswer\": \"BEBE\",\"preSelectedLetters\": [], \"image\": \"ImageURL\"}','image_writing'),(74,8,'{\"correctAnswer\": \"MI BEBE\",\"preSelectedLetters\": [], \"image\": \"ImageURL\"}','image_writing'),(75,31,'{\"options\": [\"BLA\",\"BLE\",\"BLI\",\"BLO\", \"BLU\"],\"correctAnswer\": \"BLU\"}','image_selection'),(76,31,'{\"correctAnswer\": \"BLA\"}','text_read'),(77,31,'{\"correctAnswer\": \"BLE\"}','text_read'),(78,31,'{\"correctAnswer\": \"BLI\"}','text_read'),(79,31,'{\"correctAnswer\": \"BLO\"}','text_read'),(80,31,'{\"correctAnswer\": \"BLU\"}','text_read'),(81,31,'{\"correctAnswer\": \"BLUSA\"}','text_read'),(82,31,'{\"correctAnswer\": \"PABLO\"}','text_read'),(83,31,'{\"correctAnswer\": \"TABLETA\"}','text_read'),(84,31,'{\"correctAnswer\": \"NEBLINA\"}','text_read'),(85,31,'{\"correctAnswer\": \"TABLA\"}','text_read'),(86,31,'{\"correctAnswer\": \"LA TABLA\"}','text_read'),(87,31,'{\"correctAnswer\": \"LA TABLETA DE PABLO\"}','text_read'),(88,31,'{\"correctAnswer\": \"NUBLADO\",\"preSelectedLetters\": [{\"N\": 1}, {\"D\": } ], \"image\": \"ImageURL\"}','image_writing'),(89,31,'{\"options\": [\"BLA\", \"BLE\", \"BLI\", \"BLO\", \"BLU\"], \"label\":\"CA_ _ _ \", \"correctAnswer\": \"BLE\",\"image\": \"ImageURL\"}','image_selection'),(90,31,'{\"options\": [\"DO\",\"NU\", \"BLA\"],\"correctAnswer\": \"NUBLADO\",\"image\":\"\"}','letter_ordering'),(91,31,'{\"correctAnswer\": \"TABLETA\",\"preSelectedLetters\": [ ], \"image\": \"ImageURL\"}','image_writing'),(92,31,'{\"correctAnswer\": \"LA TABLA DUELE\",\"preSelectedLetters\": [ ]}','image_writing'),(93,31,'{\"correctAnswer\": \"LA TABLETA DE PABLO\",\"preSelectedLetters\": [ ]}','image_writing'),(94,44,'{\"correctAnswer\": \"LA BEBE DE MARIA\"}','text_read'),(95,44,'{\"correctAnswer\": \"LAS NENAS AMAN A SU MAMA\"}','text_read'),(96,44,'{\"correctAnswer\": \"LOS NENES Y LAS NENAS SE PELEAN\"}','text_read'),(97,44,'{\"correctAnswer\": \"UN BEBE Y UNA NENA\"}','text_read'),(98,44,'{\"correctAnswer\": \"UNOS BOTINES NUEVOS SE PUSO\"}','text_read'),(99,44,'{\"correctAnswer\": \"UNOS BOTINES NUEVOS SE PUSO PARA JUGAR A LA PELOTA\"}','text_read'),(100,47,'{\"correctAnswer\": \"MONO\",\"preSelectedLetters\": [ ], \"image\": \"ImageURL\"}','image_writing'),(101,47,'{\"correctAnswer\": \"ARBOL\",\"preSelectedLetters\": [ ], \"image\": \"ImageURL\"}','image_writing'),(102,47,'{\"correctAnswer\": \"GLOBO\",\"preSelectedLetters\": [ ], \"image\": \"ImageURL\"}','image_writing'),(103,47,'{\"correctAnswer\": \"BICICLETA\",\"preSelectedLetters\": [ ], \"image\": \"ImageURL\"}','image_writing'),(104,47,'{\"options\": [\"DE\",\"CASA\", \"MIGUEL\", \"LA\", \"ES\", \"ROJA\"],\"correctAnswer\": \"LA CASA DE MIGUEL ES ROJA\",\"image\":\"\"}','letter_ordering'),(105,47,'{\"options\": [\"EDIFICIOS\",\"PEDRO\", \"UNOS\", \"CONSTRUYÓ\"],\"correctAnswer\": \"PEDRO CONSTRUYÓ UNOS EDIFICIOS\",\"image\":\"\"}','letter_ordering');
/*!40000 ALTER TABLE `exercise` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `module`
--

DROP TABLE IF EXISTS `module`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `module` (
  `id` int NOT NULL AUTO_INCREMENT,
  `description` varchar(40) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `module`
--

LOCK TABLES `module` WRITE;
/*!40000 ALTER TABLE `module` DISABLE KEYS */;
INSERT INTO `module` VALUES (1,'Basico'),(2,'Intermedio'),(3,'Avanzado');
/*!40000 ALTER TABLE `module` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `player`
--

DROP TABLE IF EXISTS `player`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `player` (
  `id` int NOT NULL AUTO_INCREMENT,
  `player_name` varchar(40) NOT NULL,
  `birth_date` datetime NOT NULL,
  `total_crowns` int DEFAULT '0',
  `spent_crowns` int DEFAULT '0',
  `user_id` int DEFAULT NULL,
  `recommended_module` int DEFAULT NULL,
  `alias` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `player_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `player`
--

LOCK TABLES `player` WRITE;
/*!40000 ALTER TABLE `player` DISABLE KEYS */;
INSERT INTO `player` VALUES (1,'saleck\'s child','2022-06-23 00:00:00',12,6,1,NULL,'saleck80');
/*!40000 ALTER TABLE `player` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `result`
--

DROP TABLE IF EXISTS `result`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `result` (
  `my_row_id` bigint unsigned NOT NULL AUTO_INCREMENT /*!80023 INVISIBLE */,
  `player_id` int DEFAULT NULL,
  `apple_id` int DEFAULT NULL,
  `score` int DEFAULT NULL,
  PRIMARY KEY (`my_row_id`),
  KEY `player_id` (`player_id`),
  KEY `apple_id` (`apple_id`),
  CONSTRAINT `result_ibfk_1` FOREIGN KEY (`player_id`) REFERENCES `player` (`id`),
  CONSTRAINT `result_ibfk_2` FOREIGN KEY (`apple_id`) REFERENCES `apple` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `result`
--

LOCK TABLES `result` WRITE;
/*!40000 ALTER TABLE `result` DISABLE KEYS */;
/*!40000 ALTER TABLE `result` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `unlocked_avatar`
--

DROP TABLE IF EXISTS `unlocked_avatar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `unlocked_avatar` (
  `my_row_id` bigint unsigned NOT NULL AUTO_INCREMENT /*!80023 INVISIBLE */,
  `player_id` int DEFAULT NULL,
  `avatar_id` int DEFAULT NULL,
  PRIMARY KEY (`my_row_id`),
  KEY `player_id` (`player_id`),
  KEY `avatar_id` (`avatar_id`),
  CONSTRAINT `unlocked_avatar_ibfk_1` FOREIGN KEY (`player_id`) REFERENCES `player` (`id`),
  CONSTRAINT `unlocked_avatar_ibfk_2` FOREIGN KEY (`avatar_id`) REFERENCES `avatar` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `unlocked_avatar`
--

LOCK TABLES `unlocked_avatar` WRITE;
/*!40000 ALTER TABLE `unlocked_avatar` DISABLE KEYS */;
/*!40000 ALTER TABLE `unlocked_avatar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(40) NOT NULL,
  `last_name` varchar(40) NOT NULL,
  `password` varchar(200) NOT NULL,
  `email` varchar(40) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Saleck','Gonzalez','{PsMlP92WRzalaIOXG5UiZ6hocAyx9slEpXnL7IFnaSY=}63139bd5292412c3a705b8bef38be05ab1dd900aa27c096d4797a2a871add2f3','saleck@lecti.com'),(2,'laura','martin','{wrAdYHxcT1IvzZY1QZHCMin5TbPG+BnZN3oaXc4NJjY=}b021857a0fc701324cd37610675bf71cdef4a5e0d32fbf2fa47b33ac04ff3234','sssss@gmail.com'),(3,'laura','martin','{FppYYmZB8N6/2yruLmtZXGsQKcK4/iCi6XiDLUeiwFs=}acd540dfe299c953026c8d2c26e26f344815e6fb748dc8b141a62f4760c47aee','sssss@g'),(4,'laura','mlmlmlm','{ctUlRRQJztF7KHN0b64U6R+zuncGUyDUK1zxtqGc9BM=}c15b5d7550fe56dca913d788f3ed3a66b82a0fa57054c409cf75698880e2603f','gerfman@grg.com'),(5,'laura','mlmlmlm','{yYeoH1g2o3Ker/TP75iDUs1qGh6s+UPbHHlXmSlhpAw=}36cd556516604035b5aafce4a357e61cdb1f3705c8b96d60c4e7bbc146747cbf','german@grg.com'),(6,'Laura Giselle','Martinez','{TbavI3vciVj7w+4+kFEr6D9uhEuoCtHBrQkC5tj/NAk=}c11dc87c7c65e06ff128aeb819ffeed6c840293337b5d68161c8671b7cb1f85d','laury_charly@hotmail.com'),(7,'Laura Giselle','Martinez','{Ljf2OQA7smw9Qlm+dTn67qn+yAsWgwqt7rAdzVHCaHU=}a133d5fb4dd9899ec2f4baf7ad1e08c1f43e14800c4fb556955f8d87b0a2dc9b','lauragisellemz@hotmail.com'),(8,'laura','martin','{nGlgX9EkIXSdlV0EjUq0BPXw0FNdMJYg7zru7poKPCs=}dfcdd84501bfc2b56605d959dbadd71da751d1971fce2ab0498773c52f718202','aaaaasssss@g'),(9,'laura','aaaaaaaaaaa','{O7DbklfRrjFDr2AqbO6vou/8u7Om0JwTJZvH+F/dBKM=}9bc219ff56da388dc267d70f36ea987b1b51386bedc09a153d3fc0899ff9128a','germhghghgan@grg.com'),(10,'laura','aaaaaaaaaaa','{/lMipOx+huVbNFvzzEuEIbWS9FB+YdOfkRJyZEyYJoY=}d2edbbf1b49febf86cebafe777157887083bd349fdb70327a0d637e21f1281cb','gersssman@grg.com'),(11,'laura','mlmlmlm','{vgoOjqO5vtHi0mN8rBMeSWMgZrTEHDiZTEuicp3yDDM=}d76d2f9d9b0325bd24bd4a9e3ee45470e042bb03fd766fc3921547762628423c','gerssssssssman@grg.com'),(12,'laura','aaaaaaaaaaa','{msS1jtprTng2AB40YVfvdy5eT8c7EG3s7tFYslcqyPo=}fe287c890c415d23aaa23119ea35f7859a3242246144e81cc3c6e51dafe01971',' german@grg.com'),(13,'pepem','mlmlmlm','{otWIq0P0/0gZwMwW1AAHtMZAMNNmomPhZOe4W3EBxDI=}5fddafeec346446e46cd4082d09d9b04cb1e6043b4e79a373b083547d27a4d30','dededeman@grg.com'),(14,'pedal','pile','{Tne6d3GVBapI77vRE1a4PFhsFIyX4EkqJMzlZUQqCUI=}2be13e80fd098c5900ca7d91e0754ff6edcd4c1950d2a5dbe6cb13b018a32519','pepen@grg.com'),(15,'vrtrtr','mlmlmlm','{YTx0a8pm6rRrOEJyLg5U58pHylocOZHze3bvnrVRuD8=}d30a69a90b2d67f2635a4987989e4878f3abc9b150a95ccd72c241115d66b53d','germrrrrrrrran@grg.com'),(16,'laura','mlmlmlm','{JTTelrRsuQyGoJya1w94JqOxsXE1D+gKwtpFfUjYSn0=}215f90e3c46d14440dd73fe62aad66143bda11e61abd1a88865a663de9f2dabb','dadasdasdadsadasda@gmail.com'),(17,'dedede','mlmlmlm','{wcZ+cwqcpVD2gJBx1RxlxayVRgeACB5AXKSPpo2lhlw=}77d34f85db295813578a82ab93122f92fe05198b98ab14ebff901de92e87edfe','vvvvvvvn@grg.com'),(18,'swswswsw','aaaaaa','{z2+crs7uisCzyetENKPORAIbs1txxOQYb5WieK+ENpk=}5121981129c1dcdf98d78a111551ef56c36e544a29683c26aa78f97548af41f3','germanaaaaaa@grg.com'),(19,'sqsqsqs','mlmlmlm','{BsZU6DQsdJUXPuqLLJ1o9gbyclilfQUnfZ66LsgMIHs=}d129707ed0f8eff5a83723404132cbc4a636cc1af0fbbac16e398c66f62d97a1','gaaaaaaaerman@grg.com'),(20,'laurawwwwwwww','mlmlmlm','{I8PhaLHS+XSysVQVeDk82vVvGbK21t3wFbQPrCJ53Oo=}5239448b0e66c7f9c6b27917ff0e70ea5e847b8d2116ba180375afcbc35171aa','gerwwwwwwwwwwwman@grg.com'),(21,'rerere','iiiiiiii','{5KcEcrs5neged5nZPRaS5y8rEueeZ6qT3HaJ6UlsByo=}4ac377fabc84d0000aa7c76bab8d035a7747417b5e56a9973c0f8bc7a750994d','geeeeeeeeeeeerman@grg.com'),(22,'frfrfr','mlmlmlm','{CwrkL4XBCHp5DFN44G9sji4BgQURjSkqU/NUOPhrOVM=}4f12d4246f4aa434ee238c14a204d8196365b3db07e4d21493dbacea4062dd3b','aaaaaaagerman@grg.com'),(23,'laura','mlmlmlmeeee','{a20iLY2v3qxOxYC7G9qHELJzsOijBM+84vTR8JwCj4Y=}38e1c396374c031cc760a848b46f0f10bdb62bea1cb7c486229d4b702d06d555','gereeeeman@grg.com'),(24,'lauraww','mlmlmlm','{vJRDReIWDLUtRGDejGTBE/JN1Itclddfhwe3O3lflGU=}3b42eabc4acfd57311b847b72d0a9f4e1efe5a3998487a01f36f22df0dc977df','gewwwwwwwrman@grg.com'),(25,'laura','mlmlmlmsdsds','{4OCra+60UkOavWw+v6gN32qqQ4gsABpSYUWAob/NM9M=}7c76d2b0bbda51354fc1ff23272058ffe361f33c368bebf90a960103f5361b17','germsdsdsdsdsdan@grg.com'),(26,'lauradede','mlmlmlmddd','{Ztcm93zAEAJCLshB7dFErpfAhmLaZ8sDeUl4Uk+DGWk=}b9b4c265c1e8adadc6b832a6d9be03f4b2a8eec67e8006a410270d4c77c5ad8f','germdddan@grg.com'),(27,'laira ','mlmlmlm','{OLuvLh3P47WGusxYjSpasYbTc4D/MluyHX4D43Pd3fk=}500716fcb0896f6be85dc48042e54aec14bd8971f98c8efb71871de452b67656','geaarman@grg.com'),(28,'pedro','paramo','{HO/3LaBcsjNduC3qj/5J9fAd6oyhVtjPWcpq8/+pB4M=}8acafc67ccd2d39c893707d33a473a7f91fffb1bffe0382464bf03164ca446df','pedro@gmail.com'),(29,'pipio','dsadas','{Edz7yvx/Y9POQCEb/Q6wEPMvLsZFNZsGW9bob9jVYnQ=}8195c31530b87d7a7e2d95e06fdec8ac4b901e17ce36599451127a38831a5334','germxan@grg.com'),(30,'laura','mlmlmlm','{OBTaSwAwpwJHlIO9A3f8+eiDTJrxSdYToeCeSnexLcA=}ba58ed4b2358a02b79b3f07c117d0108853dbd572dbf6076a784004b1c21cbb2','germgrgrgrgrgran@grg.com'),(31,'laura','mlmlmlm','{G9oP1ci72FyOQbLBnXuvW3pO0//rBX+T1LQRuIOU+ns=}f8e7550a361adc86ec7f71447bf1ad4a2725ca5bb4b8fa95e1adebd2fe2debec','dsadazdssds@gmail.com'),(32,'laura','mlmlmlm','{8c2duafAckLUO1yCu9uIGiPrkjIUxMQhMxGUi/bC8TA=}662abb3720bf5851362a08669b87a791dcfe1a8f24b95a5a49da25c36a17f798','gerdsdaasdman@grg.com'),(33,'laura','iiiiiiii','{rACQYCt4kPJFORO4R/cMR4BteSdMR1NAgmuD7LTrxmw=}969f22869393a5881c3a23738c927aaf49f5d3ee1f112c3919f0376d0145ec20','germadsdaasasasadddn@grg.com'),(34,'laura','iiiiiiii','{1S7Ym18Rpw5vgUATL3sK+ze0cldInxteJ7ep0TnX30o=}88f83237b8087a0d19c0b2fd8400c27a2644db8f6b22f237e73e735d00590155','fsfsff@gmail.com');
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

-- Dump completed on 2024-06-03 21:11:04
