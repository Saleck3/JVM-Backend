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
  `apple_type` enum('NO_IA','VOICE_IA','IMAGE_IA') DEFAULT 'NO_IA',
  PRIMARY KEY (`id`),
  UNIQUE KEY `order_on_module` (`module_id`,`index`),
  KEY `module_id` (`module_id`),
  CONSTRAINT `apple_ibfk_1` FOREIGN KEY (`module_id`) REFERENCES `module` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

ALTER TABLE `apple`
    MODIFY `apple_type` ENUM('NO_IA', 'VOICE_IA', 'IMAGE_IA', 'RECOMMENDED_MODULE') DEFAULT 'NO_IA';
--
-- Dumping data for table `apple`
--

LOCK TABLES `apple` WRITE;
/*!40000 ALTER TABLE `apple` DISABLE KEYS */;
INSERT INTO `apple` VALUES (1,'A',1,1,'NO_IA'),(2,'E',1,2,'NO_IA'),(3,'I',1,4,'NO_IA'),(4,'O',1,6,'NO_IA'),(5,'U',1,8,'NO_IA'),(6,'M',1,10,'NO_IA'),(7,'P',1,12,'NO_IA'),(8,'B',1,14,'NO_IA'),(9,'S',1,16,'NO_IA'),(10,'L',1,17,'NO_IA'),(11,'LL',1,18,'NO_IA'),(12,'N',1,19,'NO_IA'),(13,'D',1,20,'NO_IA'),(14,'T',1,21,'NO_IA'),(15,'R',1,22,'NO_IA'),(16,'RR',1,23,'NO_IA'),(17,'F',1,24,'NO_IA'),(18,'Ñ',1,25,'NO_IA'),(19,'K',1,26,'NO_IA'),(20,'Y',1,27,'NO_IA'),(21,'C',1,28,'NO_IA'),(22,'H',1,29,'NO_IA'),(23,'V',1,30,'NO_IA'),(24,'Q',1,31,'NO_IA'),(25,'J',1,32,'NO_IA'),(26,'G',1,33,'NO_IA'),(27,'Z',1,34,'NO_IA'),(28,'W',1,35,'NO_IA'),(29,'X',1,36,'NO_IA'),(30,'CH',1,37,'NO_IA'),(31,'BL',2,1,'NO_IA'),(32,'BR',2,2,'NO_IA'),(33,'CL',2,3,'NO_IA'),(34,'CR',2,4,'NO_IA'),(35,'DR',2,5,'NO_IA'),(36,'FL',2,6,'NO_IA'),(37,'FR',2,7,'NO_IA'),(38,'GL',2,8,'NO_IA'),(39,'GR',2,9,'NO_IA'),(40,'PL',2,10,'NO_IA'),(41,'PR',2,11,'NO_IA'),(42,'TR',2,12,'NO_IA'),(43,'TL',2,13,'NO_IA'),(44,'ORACIONES',3,1,'NO_IA'),(45,'ADIVINANZAS',3,2,'NO_IA'),(46,'TRABALENGUAS',3,3,'NO_IA'),(47,'TEST',NULL,NULL,'RECOMMENDED_MODULE'),(48,'M',1,11,'VOICE_IA'),(49,'E',1,3,'VOICE_IA'),(50,'I',1,5,'VOICE_IA'),(51,'O',1,7,'VOICE_IA'),(52,'U',1,9,'VOICE_IA'),(53,'P',1,13,'VOICE_IA'),(54,'BL',2,NULL,'VOICE_IA'),(55,'B',1,15,'VOICE_IA');
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
) ENGINE=InnoDB AUTO_INCREMENT=115 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exercise`
--

LOCK TABLES `exercise` WRITE;
/*!40000 ALTER TABLE `exercise` DISABLE KEYS */;
INSERT INTO `exercise` VALUES (2,6,'{\"options\": [\"MA\",\"NO\"],\"correctAnswer\": \"MANO\",\"image\":\"https://i.imgur.com/C6hK02U.png\"}','letter_ordering'),(3,6,'{\"options\": [\"MESA\",\"MOTO\",\"MONO\",\"MARCO\"],\"correctAnswer\": \"MONO\",\"image\":\"https://i.imgur.com/C6hK02U.png\"}','image_selection'),(4,6,'{\"correctAnswer\": \"MONO\",\"preSelectedLetters\":[\"N\"], \"image\":\"https://i.imgur.com/C6hK02U.png\"}','image_writing'),(5,6,'{\"image\":\"https://i.imgur.com/C6hK02U.png\"}','video'),(6,6,'{\"image\":\"https://i.imgur.com/C6hK02U.png\"}','worksheets'),(7,48,'{\"correctAnswer\": \"MOMIA\"}','audio_repeating'),(8,48,'{\"correctAnswer\": \"MIMA\"}','text_read'),(9,6,'{\"options\": [\"MA\", \"ME\", \"MI\", \"MO\", \"MU\"], \"label\":\"_ _ SA\", \"correctAnswer\": \"ME\",\"image\":\"https://i.imgur.com/C6hK02U.png\"}','image_selection'),(10,6,'{\"options\": [\"ME\",\"LON\"],\"correctAnswer\": \"MELON\",\"image\":\"https://i.imgur.com/C6hK02U.png\"}','letter_ordering'),(11,6,'{\"options\": [\"MA\",\"ME\",\"MI\",\"MO\", \"MU\"],\"label\": \"TO\",\"correctAnswer\": \"MO\",\"image\":\"https://i.imgur.com/C6hK02U.png\"_moto\"}','image_selection'),(12,6,'{\"options\": [\"MA\",\"ME\",\"MI\",\"MO\", \"MU\"],\"label\": \"LETA\",\"correctAnswer\": \"MU\",\"image\":\"https://i.imgur.com/C6hK02U.png\"_muleta\"}','image_selection'),(13,48,'{\"correctAnswer\": \"MI MAMA\"}','text_read'),(14,48,'{\"correctAnswer\": \"MI MAMA ME AMA\"}','audio_repeating'),(15,48,'{\"correctAnswer\": \"MI MAMA ME MIMA\"}','text_read'),(16,48,'{\"correctAnswer\": \"MI MAMA ME MIMA A MI\"}','audio_repeating'),(17,1,'{\"options\": [\"VION\",\"A\"],\"correctAnswer\": \"AVION\",\"image\":\"https://i.imgur.com/C6hK02U.png\"}','letter_ordering'),(18,1,'{\"options\": [\"A\", \"E\", \"I\", \"O\", \"U\"], \"label\":\"_ RAÑA\", \"correctAnswer\": \"A\",\"image\":\"https://i.imgur.com/C6hK02U.png\"}','image_selection'),(19,1,'{\"correctAnswer\": \"ABEJA\",\"preSelectedLetters\": [{\"B\": 2},{\"E\": 3},{\"J\": 4}], \"image\": \"https://i.imgur.com/C6hK02U.png\"}','image_writing'),(20,48,'{\"correctAnswer\": \"ANANA\"}','audio_repeating'),(21,2,'{\"options\": [\"CO\",\"ES\", \"BA\"],\"correctAnswer\": \"ESCOBA\",\"image\":\"https://i.imgur.com/C6hK02U.png\"}','letter_ordering'),(22,2,'{\"correctAnswer\": \"ELEFANTE\",\"preSelectedLetters\": [{\"L\": 2},{\"F\": 4},{\"N\": 6}, {\"T\": 7}], \"image\":\"https://i.imgur.com/C6hK02U.png\"}','image_writing'),(23,2,'{\"options\": [\"JO\",\"PE\", \"ES\"],\"correctAnswer\": \"ESPEJO\",\"image\":\"https://i.imgur.com/C6hK02U.png\"}','letter_ordering'),(24,49,'{\"correctAnswer\": \"ESCOBA\"}','audio_repeating'),(25,2,'{\"correctAnswer\": \"ESCALERA\",\"preSelectedLetters\": [{\"S\": 2},{\"C\": 3},{\"L\": 5}, {\"R\": 7}], \"image\":\"https://i.imgur.com/C6hK02U.png\"}','image_writing'),(26,3,'{\"correctAnswer\": \"ISLA\",\"preSelectedLetters\": [{\"S\": 2},{\"L\": 3}], \"image\":\"https://i.imgur.com/C6hK02U.png\"}','image_writing'),(27,3,'{\"options\": [\"I\",\"MAN\"],\"correctAnswer\": \"MANI\",\"image\":\"https://i.imgur.com/C6hK02U.png\"}','letter_ordering'),(28,3,'{\"options\": [\"IGLU\",\"ISLA\",\"INODORO\",\"IGLESIA\"],\"correctAnswer\": \"ISLA\",\"image\":\"https://i.imgur.com/C6hK02U.png\"}','image_selection'),(29,3,'{\"correctAnswer\": \"IGLESIA\",\"preSelectedLetters\": [{\"G\": 2},{\"L\": 3}, {\"S\": 5}], \"image\":\"https://i.imgur.com/C6hK02U.png\"}','image_writing'),(30,50,'{\"correctAnswer\": \"IGUANA\"}','audio_repeating'),(31,4,'{\"correctAnswer\": \"OLLA\",\"preSelectedLetters\": [{\"L\": 2},{\"L\": 3}], \"image\":\"https://i.imgur.com/C6hK02U.png\"}','image_writing'),(32,4,'{\"options\": [\"JO\",\"O\"],\"correctAnswer\": \"OJO\",\"image\":\"https://i.imgur.com/C6hK02U.png\"}','letter_ordering'),(33,4,'{\"options\": [\"VE\",\"JA\", \"O\"],\"correctAnswer\": \"OVEJA\",\"image\":\"https://i.imgur.com/C6hK02U.png\"}','letter_ordering'),(34,4,'{\"correctAnswer\": \"OSO\",\"preSelectedLetters\": [{\"S\": 2}], \"image\":\"https://i.imgur.com/C6hK02U.png\"}','image_writing'),(35,51,'{\"correctAnswer\": \"OSO\"}','audio_repeating'),(36,5,'{\"correctAnswer\": \"UVA\",\"preSelectedLetters\": [{\"v\": 2}], \"image\":\"https://i.imgur.com/C6hK02U.png\"}','image_writing'),(37,5,'{\"correctAnswer\": \"UNICORNIO\",\"preSelectedLetters\": [{\"N\": 2}, {\"C\": 3},{\"R\": 5}, {\"N\": 6}], \"image\":\"https://i.imgur.com/C6hK02U.png\"}','letter_ordering'),(38,5,'{\"options\": [\"UNIVERSO\",\"UÑA\",\"UNION\",\"UTILIDAD\"],\"correctAnswer\": \"UÑA\",\"image\":\"https://i.imgur.com/C6hK02U.png\"}','image_selection'),(39,52,'{\"correctAnswer\": \"UVA\"}','audio_repeating'),(40,7,'{\"options\": [\"PA\",\"PE\",\"PI\",\"PO\", \"PU\"],\"correctAnswer\": \"PO\"}','image_selection'),(41,53,'{\"correctAnswer\": \"PA\"}','text_read'),(42,53,'{\"correctAnswer\": \"PI\"}','text_read'),(43,53,'{\"correctAnswer\": \"PE\"}','text_read'),(44,53,'{\"correctAnswer\": \"PU\"}','text_read'),(45,53,'{\"correctAnswer\": \"PIPA\"}','text_read'),(46,53,'{\"correctAnswer\": \"PEPA\"}','text_read'),(47,53,'{\"correctAnswer\": \"PUPO\"}','text_read'),(48,53,'{\"correctAnswer\": \"MI PAPA\"}','text_read'),(49,53,'{\"correctAnswer\": \"EL LEON RUGE\"}','audio_repeating'),(50,7,'{\"options\": [\"PA\", \"PE\", \"PI\", \"PO\", \"PU\"], \"label\":\"_ _ RATA\", \"correctAnswer\": \"PI\",\"image\":\"https://i.imgur.com/C6hK02U.png\"}','image_selection'),(51,7,'{\"correctAnswer\": \"PIRATA\",\"preSelectedLetters\": [{\"R\": 3}, {\"T\": 5}], \"image\":\"https://i.imgur.com/C6hK02U.png\"}','image_writing'),(52,7,'{\"options\": [\"LO\",\"PE\", \"TA\"],\"correctAnswer\": \"PELOTA\",\"image\":\"https://i.imgur.com/C6hK02U.png\"}','letter_ordering'),(53,7,'{\"correctAnswer\": \"PATO\",\"preSelectedLetters\": [{\"T\": 3}], \"image\":\"https://i.imgur.com/C6hK02U.png\"}','image_writing'),(54,7,'{\"correctAnswer\": \"PIRATA\",\"preSelectedLetters\": [{\"R\": 3}, {\"G\": 5}, {\"S\":8}], \"image\":\"https://i.imgur.com/C6hK02U.png\"}','image_writing'),(55,7,'{\"correctAnswer\": \"PERA\",\"preSelectedLetters\": [{\"R\": 3}], \"image\":\"https://i.imgur.com/C6hK02U.png\"}','image_writing'),(56,7,'{\"correctAnswer\": \"PIPA\",\"preSelectedLetters\": [], \"image\":\"https://i.imgur.com/C6hK02U.png\"}','image_writing'),(57,7,'{\"correctAnswer\": \"MI PAPA\",\"preSelectedLetters\": [\"\"]}','image_writing'),(58,7,'{\"correctAnswer\": \"MI PAPA ME MIMA\",\"preSelectedLetters\": []}','image_writing'),(59,7,'{\"correctAnswer\": \"MI PAPA ME AMA\",\"preSelectedLetters\": []}','image_writing'),(60,8,'{\"options\": [\"BA\",\"BE\",\"BI\",\"BO\", \"BU\"],\"correctAnswer\": \"BO\"}','image_selection'),(61,55,'{\"correctAnswer\": \"BO\"}','text_read'),(62,55,'{\"correctAnswer\": \"BI\"}','text_read'),(63,55,'{\"correctAnswer\": \"BU\"}','text_read'),(64,55,'{\"correctAnswer\": \"BE\"}','text_read'),(65,55,'{\"correctAnswer\": \"BA\"}','text_read'),(66,55,'{\"correctAnswer\": \"BEBE\"}','text_read'),(67,55,'{\"correctAnswer\": \"BEBA\"}','text_read'),(68,55,'{\"correctAnswer\": \"MI BEBE\"}','text_read'),(69,8,'{\"options\": [\"BA\", \"BE\", \"BI\", \"BO\", \"BU\"], \"label\":\"_ _FANDA \", \"correctAnswer\": \"BU\",\"image\":\"https://i.imgur.com/C6hK02U.png\"}','image_selection'),(70,8,'{\"correctAnswer\": \"BARCO\",\"preSelectedLetters\": [{\"R\": 3}, {\"C\": 4}], \"image\":\"https://i.imgur.com/C6hK02U.png\"}','image_writing'),(71,8,'{\"correctAnswer\": \"BICICLETA\",\"preSelectedLetters\": [{\"B\": 1}, {\"C\": 3}, {\"C\":5}, {\"L\":6}, {\"T\":7} ], \"image\":\"https://i.imgur.com/C6hK02U.png\"}','image_writing'),(72,8,'{\"options\": [\"TA\",\"CLE\", \"BI\", \"CI\"],\"correctAnswer\": \"BICICLETA\",\"image\":\"https://i.imgur.com/C6hK02U.png\"}','letter_ordering'),(73,8,'{\"correctAnswer\": \"BEBE\",\"preSelectedLetters\": [], \"image\":\"https://i.imgur.com/C6hK02U.png\"}','image_writing'),(74,8,'{\"correctAnswer\": \"MI BEBE\",\"preSelectedLetters\": [], \"image\":\"https://i.imgur.com/C6hK02U.png\"}','image_writing'),(75,31,'{\"options\": [\"BLA\",\"BLE\",\"BLI\",\"BLO\", \"BLU\"],\"correctAnswer\": \"BLU\"}','image_selection'),(76,54,'{\"correctAnswer\": \"BLA\"}','text_read'),(77,54,'{\"correctAnswer\": \"BLE\"}','text_read'),(78,54,'{\"correctAnswer\": \"BLI\"}','text_read'),(79,54,'{\"correctAnswer\": \"BLO\"}','text_read'),(80,54,'{\"correctAnswer\": \"BLU\"}','text_read'),(81,54,'{\"correctAnswer\": \"BLUSA\"}','text_read'),(82,54,'{\"correctAnswer\": \"PABLO\"}','text_read'),(83,54,'{\"correctAnswer\": \"TABLETA\"}','text_read'),(84,54,'{\"correctAnswer\": \"NEBLINA\"}','text_read'),(85,54,'{\"correctAnswer\": \"TABLA\"}','text_read'),(86,54,'{\"correctAnswer\": \"LA TABLA\"}','text_read'),(87,54,'{\"correctAnswer\": \"LA TABLETA DE PABLO\"}','text_read'),(88,31,'{\"correctAnswer\": \"NUBLADO\",\"preSelectedLetters\": [{\"N\": 1}, {\"D\": 6} ], \"image\":\"https://i.imgur.com/C6hK02U.png\"}','image_writing'),(89,31,'{\"options\": [\"BLA\", \"BLE\", \"BLI\", \"BLO\", \"BLU\"], \"label\":\"CA_ _ _ \", \"correctAnswer\": \"BLE\",\"image\":\"https://i.imgur.com/C6hK02U.png\"}','image_selection'),(90,31,'{\"options\": [\"DO\",\"NU\", \"BLA\"],\"correctAnswer\": \"NUBLADO\",\"image\":\"https://i.imgur.com/C6hK02U.png\"}','letter_ordering'),(91,31,'{\"correctAnswer\": \"TABLETA\",\"preSelectedLetters\": [ ], \"image\":\"https://i.imgur.com/C6hK02U.png\"}','image_writing'),(92,31,'{\"correctAnswer\": \"LA TABLA DUELE\",\"preSelectedLetters\": [ ]}','image_writing'),(93,31,'{\"correctAnswer\": \"LA TABLETA DE PABLO\",\"preSelectedLetters\": [ ]}','image_writing'),(94,44,'{\"correctAnswer\": \"LA BEBE DE MARIA\"}','text_read'),(95,44,'{\"correctAnswer\": \"LAS NENAS AMAN A SU MAMA\"}','text_read'),(96,44,'{\"correctAnswer\": \"LOS NENES Y LAS NENAS SE PELEAN\"}','text_read'),(97,44,'{\"correctAnswer\": \"UN BEBE Y UNA NENA\"}','text_read'),(98,44,'{\"correctAnswer\": \"UNOS BOTINES NUEVOS SE PUSO\"}','text_read'),(99,44,'{\"correctAnswer\": \"UNOS BOTINES NUEVOS SE PUSO PARA JUGAR A LA PELOTA\"}','text_read'),(100,47,'{\"correctAnswer\": \"MONO\",\"preSelectedLetters\": [ ], \"image\":\"https://i.imgur.com/C6hK02U.png\"}','image_writing'),(101,47,'{\"correctAnswer\": \"ARBOL\",\"preSelectedLetters\": [ ], \"image\":\"https://i.imgur.com/C6hK02U.png\"}','image_writing'),(102,47,'{\"correctAnswer\": \"GLOBO\",\"preSelectedLetters\": [ ], \"image\":\"https://i.imgur.com/C6hK02U.png\"}','image_writing'),(103,47,'{\"correctAnswer\": \"BICICLETA\",\"preSelectedLetters\": [ ], \"image\":\"https://i.imgur.com/C6hK02U.png\"}','image_writing'),(104,47,'{\"options\": [\"DE\",\"CASA\", \"MIGUEL\", \"LA\", \"ES\", \"ROJA\"],\"correctAnswer\": \"LA CASA DE MIGUEL ES ROJA\",\"image\":\"https://i.imgur.com/C6hK02U.png\"}','letter_ordering'),(105,47,'{\"options\": [\"EDIFICIOS\",\"PEDRO\", \"UNOS\", \"CONSTRUYÓ\"],\"correctAnswer\": \"PEDRO CONSTRUYÓ UNOS EDIFICIOS\",\"image\":\"https://i.imgur.com/C6hK02U.png\"}','letter_ordering'),(106,44,'{\"correctAnswer\": \"CAPERUCITA ROJA ERA UNA NIÑA MUY BUENA. POR ESO, SU MAMÁ LA MANDÓ A LA CASA DE LA ABUELA PARA QUE LE LLEVE UNA CANASTA CON ALIMENTOS Y REMEDIOS. CAPERUCITA MARCHÓ PERO EN EL CAMINO SE ENCONTRÓ CON EL LOBO. ESTE ANIMAL FEROZ QUISO ENGAÑAR A LA PEQUEÑA PERO ELLA YA CONOCÍA SU FAMA TRAICIONERA, ASÍ QUE LE CONVIDÓ UN CARAMELO HECHO CON HIERBAS CUYO EFECTO ERA LA IRRITACIÓN INTESTINAL. FINALMENTE, EL LOBO CORRIÓ AL BAÑO Y LA JOVEN SIGUIÓ SU CAMINO}','text_read'),(107,44,'{\"correctAnswer\": \"HELENA FUE AL HOGAR DE ANCIANOS Y LE LLEVÓ HELADO A SU QUERIDA ABUELA PERO LLEGÓ MUY TEMPRANO ASÍ QUE NO PUDO PASAR. POR SUERTE, EL GUARDIA DE LA PUERTA LA VIO Y, COMO YA LA CONOCÍA, LA DEJÓ PASAR\"}','text_read'),(108,46,'{\"correctAnswer\": \"Pablito clavó un clavito en la calva de un calvito. Un clavito clavó Pablito en la calva de un calvito. ¿Qué clavito clavó Pablito? \"}','text_read'),(109,46,'{\"correctAnswer\": \"Tres tristes tigres tragaban trigo en un trigal, en tres tristes trastros tragaban trigo tres tristes tigres \"}','text_read'),(110,46,'{\"correctAnswer\": \"Luengas lenguas hacen falta para no trabalenguarse. El que no tenga una luenga lengua bien podrá trabalenguarse.\"}','text_read'),(111,46,'{\"correctAnswer\": \"Cuando cuentes cuentos, cuenta cuántos cuentos cuentas, porque si no cuentas cuántos cuentos cuentas, nunca sabrás cuántos cuentos cuentas tú.\"}','text_read'),(112,44,'{\"correctAnswer\": \"LA NUEVA SEÑORITA LLAMADA EUGENIA SE QUEDÓ PEGADA A LA SILLA CON UN ASQUEROSO CHICLE QUE UN NIÑO DEJÓ\"}','text_read'),(113,44,'{\"correctAnswer\": \"Un asteroide es un tipo de roca espacial, mucho más pequeño que un planeta, y se traslada en órbita elíptica alrededor del Sol. Existen millones de asteroides y la mayoría de ellos se encuentran en el denominado “cinturón de asteroides”. El resto se distribuye en la trayectoria orbital de otros planetas del Sistema Solar, entre ellos, la Tierra.Los asteroides se caracterizan por tener una fuerza gravitatoria muy débil, que no les permite alcanzar una forma completamente de esfera. Su diámetro puede variar desde unos metros hasta cientos de kilómetros.\"}','text_read'),(114,44,'{\"correctAnswer\": \"La anatomía de las serpientes está especialmente adaptada para poder desplazarse careciendo de extremidades. Comparadas con vertebrados cuadrúpedos, las serpientes tienen un centro de gravedad muy bajo, pegado al suelo, y una mayor superficie corporal en contacto con el suelo, lo que genera mayor fricción y reparte más la masa corporal. A pesar de ello, son capaces de nadar, bucear, escalar, saltar, cavar e incluso algunas especies pueden planear. Presenta diferentes tipos de locomoción terrestre, la forma más común se realiza mediante ondulaciones laterales del cuerpo, que comienzan en la cabeza hasta terminar en la cola. Otro tipo de desplazamiento supone el uso de una parte del cuerpo como ancla estática para impulsar el resto del cuerpo. También algunas serpientes se pueden desplazar de manera rectilínea usando para impulsarse sus músculos y escamas ventrales. \"}','text_read');
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
  `birth_date` datetime DEFAULT NULL,
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
INSERT INTO `users` VALUES (1,'Saleck','Gonzalez','{cJjx/lXz/a7g4aSGFLghGGpUqPGQ6YXMBT+7C3Cl45E=}b1b1c6139ebc674ffaaac67a48f64a3a8cfdf67f2c588c9f41269f5b57c0debd','saleck@lecti.com'),(36,'marcelo','Juárez','{cJjx/lXz/a7g4aSGFLghGGpUqPGQ6YXMBT+7C3Cl45E=}b1b1c6139ebc674ffaaac67a48f64a3a8cfdf67f2c588c9f41269f5b57c0debd','marcelo@lecti.com');
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

-- Dump completed on 2024-06-08 17:43:27
