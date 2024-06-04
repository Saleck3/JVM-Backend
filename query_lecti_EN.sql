-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: lecti
-- ------------------------------------------------------
-- Server version	8.0.36-0ubuntu0.22.04.1

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
  `index` int unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `order_on_module` (`module_id`,`index`),
  KEY `module_id` (`module_id`),
  CONSTRAINT `apple_ibfk_1` FOREIGN KEY (`module_id`) REFERENCES `module` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `apple`
--

LOCK TABLES `apple` WRITE;
/*!40000 ALTER TABLE `apple` DISABLE KEYS */;
INSERT INTO `apple` VALUES (1,'A',1,1),(2,'E',1,2),(3,'I',1,3),(4,'O',1,4),(5,'U',1,5),(6,'M',1,6),(7,'P',1,7),(8,'B',1,8),(9,'S',1,9),(10,'L',1,10),(11,'LL',1,11),(12,'N',1,12),(13,'D',1,13),(14,'T',1,14),(15,'R',1,15),(16,'RR',1,16),(17,'F',1,17),(18,'Ñ',1,18),(19,'K',1,19),(20,'Y',1,20),(21,'C',1,21),(22,'H',1,22),(23,'V',1,23),(24,'Q',1,24),(25,'J',1,25),(26,'G',1,26),(27,'Z',1,27),(28,'W',1,28),(29,'X',1,29),(30,'CH',1,30),(31,'BL',2,1),(32,'BR',2,2),(33,'CL',2,3),(34,'CR',2,4),(35,'DR',2,5),(36,'FL',2,6),(37,'FR',2,7),(38,'GL',2,8),(39,'GR',2,9),(40,'PL',2,10),(41,'PR',2,11),(42,'TR',2,12),(43,'TL',2,13),(44,'ORACIONES',3,1),(45,'ADIVINANZAS',3,2),(46,'TRABALENGUAS',3,3),(47,'TEST',NULL,NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Saleck','Gonzalez','{PsMlP92WRzalaIOXG5UiZ6hocAyx9slEpXnL7IFnaSY=}63139bd5292412c3a705b8bef38be05ab1dd900aa27c096d4797a2a871add2f3','saleck@lecti.com'),(36,'marcelo','Juárez','{cJjx/lXz/a7g4aSGFLghGGpUqPGQ6YXMBT+7C3Cl45E=}b1b1c6139ebc674ffaaac67a48f64a3a8cfdf67f2c588c9f41269f5b57c0debd','marcelo@lecti.com');
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

-- Dump completed on 2024-06-03 22:26:15
