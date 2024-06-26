-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: db-lecti-mysql.mysql.database.azure.com    Database: lecti
-- ------------------------------------------------------
-- Server version	8.0.36-cluster

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
  `apple_type` enum('NO_IA','VOICE_IA','IMAGE_IA','RECOMMENDED_MODULE') DEFAULT 'NO_IA',
  PRIMARY KEY (`id`),
  UNIQUE KEY `order_on_module` (`module_id`,`index`),
  KEY `module_id` (`module_id`),
  CONSTRAINT `apple_ibfk_1` FOREIGN KEY (`module_id`) REFERENCES `module` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `apple`
--

LOCK TABLES `apple` WRITE;
/*!40000 ALTER TABLE `apple` DISABLE KEYS */;
INSERT INTO `apple` VALUES (1,'A',1,1,'NO_IA'),(2,'E',1,2,'NO_IA'),(3,'I',1,4,'NO_IA'),(4,'O',1,6,'NO_IA'),(5,'U',1,8,'NO_IA'),(6,'M',1,10,'NO_IA'),(7,'P',1,12,'NO_IA'),(8,'B',1,14,'NO_IA'),(9,'S',1,16,'NO_IA'),(10,'L',1,17,'NO_IA'),(11,'D',1,18,'NO_IA'),(12,'N',1,19,'NO_IA'),(13,'F',1,20,'NO_IA'),(14,'T',1,21,'NO_IA'),(15,'R',1,22,'NO_IA'),(16,'J',1,23,'NO_IA'),(17,'G',1,24,'NO_IA'),(18,'C',1,25,'NO_IA'),(19,'K',1,26,'NO_IA'),(20,'Y',1,27,'NO_IA'),(21,'Ñ',1,28,'NO_IA'),(22,'H',1,29,'NO_IA'),(23,'V',1,30,'NO_IA'),(24,'Q',1,31,'NO_IA'),(25,'RR',1,32,'NO_IA'),(26,'LL',1,33,'NO_IA'),(27,'Z',1,34,'NO_IA'),(28,'W',1,35,'NO_IA'),(29,'X',1,36,'NO_IA'),(30,'CH',1,37,'NO_IA'),(31,'BL',2,1,'NO_IA'),(32,'BR',2,3,'NO_IA'),(33,'CL',2,4,'NO_IA'),(34,'CR',2,5,'NO_IA'),(35,'DR',2,6,'NO_IA'),(36,'FL',2,7,'NO_IA'),(37,'FR',2,8,'NO_IA'),(38,'GL',2,9,'NO_IA'),(39,'GR',2,10,'NO_IA'),(40,'PL',2,11,'NO_IA'),(41,'PR',2,12,'NO_IA'),(42,'TR',2,13,'NO_IA'),(43,'TL',2,14,'NO_IA'),(44,'1',3,1,'NO_IA'),(45,'2',3,2,'NO_IA'),(46,'3',3,3,'NO_IA'),(47,'TEST',NULL,NULL,'RECOMMENDED_MODULE'),(48,'M',1,11,'VOICE_IA'),(49,'E',1,NULL,'VOICE_IA'),(50,'I',1,NULL,'VOICE_IA'),(51,'O',1,NULL,'VOICE_IA'),(52,'U',1,NULL,'VOICE_IA'),(53,'P',1,13,'VOICE_IA'),(54,'BL',2,2,'VOICE_IA'),(55,'B',1,15,'VOICE_IA'),(57,'M',1,99,'VOICE_IA');
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
  `index` int unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `apple_id` (`apple_id`),
  CONSTRAINT `exercise_ibfk_2` FOREIGN KEY (`apple_id`) REFERENCES `apple` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=212 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exercise`
--

LOCK TABLES `exercise` WRITE;
/*!40000 ALTER TABLE `exercise` DISABLE KEYS */;
INSERT INTO `exercise` VALUES (1,5,'{\"src\":\"https://www.youtube.com/embed/loSTO3FFpbo?si=8YARKykaZ9NvwIW_\"}','video',1),(2,6,'{\"options\": [\"NO\",\"MA\"],\"correctAnswer\": \"MANO\",\"image\":\"https://i.imgur.com/9hNrozW.jpeg\"}','letter_ordering',NULL),(3,6,'{\"options\": [\"MESA\",\"MOTO\",\"MONO\",\"MARCO\"],\"correctAnswer\": \"MONO\",\"image\":\"https://i.imgur.com/2sm2I9e.jpeg\"}','image_selection',NULL),(4,6,'{\"correctAnswer\": \"MONO\",\"preSelectedLetters\":[{\"letter\":\"N\",\"index\":2}], \"image\":\"https://i.imgur.com/2sm2I9e.jpeg\"}','image_writing',NULL),(7,48,'{\"correctAnswer\": \"MOMIA\"}','audio_repeating',NULL),(8,57,'{\"correctAnswer\": \"MIMA\",\"onlyText\":\"true\"}','audio_repeating',NULL),(9,6,'{\"options\": [\"MA\", \"ME\", \"MI\", \"MO\", \"MU\"], \"label\":\"_ _ SA\", \"correctAnswer\": \"ME\",\"image\":\"https://i.imgur.com/BKtp84L.jpeg\"}','image_selection',NULL),(10,6,'{\"options\": [\"NE\",\"MO\",\"DA\"],\"correctAnswer\": \"MONEDA\",\"image\":\"https://i.imgur.com/JmD79By.jpeg\"}','letter_ordering',NULL),(11,6,'{\"options\": [\"MA\",\"ME\",\"MI\",\"MO\", \"MU\"],\"label\": \"_ _ TO\",\"correctAnswer\": \"MO\",\"image\":\"https://i.imgur.com/1IhfL4i.jpeg\"}','image_selection',NULL),(12,6,'{\"options\": [\"MA\",\"ME\",\"MI\",\"MO\", \"MU\"],\"label\": \"_ _ LETA\",\"correctAnswer\": \"MU\",\"image\":\"https://i.imgur.com/KFAaH5U.jpeg\"}','image_selection',NULL),(13,57,'{\"correctAnswer\": \"MI MAMÁ\",\"onlyText\":\"true\"}','audio_repeating',NULL),(14,48,'{\"correctAnswer\": \"MI MAMÁ ME AMA\"}','audio_repeating',NULL),(15,57,'{\"correctAnswer\": \"MI MAMÁ ME MIMA\",\"onlyText\":\"true\"}','audio_repeating',NULL),(16,48,'{\"correctAnswer\": \"MI MAMÁ ME MIMA A MI\"}','audio_repeating',NULL),(17,1,'{\"options\": [\"VION\",\"A\"],\"correctAnswer\": \"AVION\",\"image\":\"https://i.imgur.com/0g3fIlr.jpeg\",\"tts\":\"avión\"}','letter_ordering',3),(18,1,'{\"options\": [\"A\", \"E\", \"I\", \"O\", \"U\"], \"label\":\"_ RAÑA\", \"correctAnswer\": \"A\",\"image\":\"https://i.imgur.com/xrCNdFl.jpeg\",\"tts\":\"araña\"}','image_selection',4),(19,1,'{\"correctAnswer\": \"ABEJA\",\"preSelectedLetters\": [{\"letter\":\"B\",\"index\": 1},{\"letter\":\"E\",\"index\": 2},{\"letter\":\"J\",\"index\":3}], \"image\": \"https://i.imgur.com/QBQ7ykP.jpeg\",\"onlyText\":true}','image_writing',5),(21,2,'{\"options\": [\"CO\",\"ES\", \"BA\"],\"correctAnswer\": \"ESCOBA\",\"image\":\"https://i.imgur.com/GceNVDT.jpeg\"}','letter_ordering',2),(22,2,'{\"correctAnswer\": \"ELEFANTE\",\"preSelectedLetters\": [{\"letter\": \"L\",\"index\": 1},{\"letter\": \"F\",\"index\": 3},{\"letter\": \"N\",\"index\": 5},{\"letter\": \"T\",\"index\": 6}],\"image\": \"https://i.imgur.com/Hmwpd7u.jpeg\"}','image_writing',3),(23,2,'{\"options\": [\"JO\",\"PE\", \"ES\"],\"correctAnswer\": \"ESPEJO\",\"image\":\"https://i.imgur.com/DcZidGy.jpeg\"}','letter_ordering',4),(24,49,'{\"correctAnswer\": \"ESCOBA\"}','audio_repeating',NULL),(25,2,'{\"correctAnswer\": \"ESCALERA\",\"preSelectedLetters\": [{\"letter\":\"S\",\"index\": 1},{\"letter\":\"C\",\"index\": 2},{\"letter\":\"L\",\"index\": 4}, {\"letter\":\"R\",\"index\": 6}], \"image\":\"https://i.imgur.com/rt3xFEz.jpeg\"}','image_writing',5),(26,3,'{\"correctAnswer\": \"ISLA\",\"preSelectedLetters\": [{\"letter\":\"S\",\"index\": 1},{\"letter\":\"L\",\"index\": 2}], \"image\":\"https://i.imgur.com/MUC7GtY.jpeg\"}','image_writing',3),(27,3,'{\"correctAnswer\": \"IGLESIA\",\"preSelectedLetters\": [{\"letter\":\"G\",\"index\": 1},{\"letter\":\"L\",\"index\": 2}, {\"letter\":\"S\",\"index\": 4}], \"image\":\"https://i.imgur.com/GiWyncN.jpeg\"}','image_writing',5),(28,3,'{\"options\": [\"IGLU\",\"ISLA\",\"INODORO\",\"IGLESIA\"],\"correctAnswer\": \"ISLA\",\"image\":\"https://i.imgur.com/MUC7GtY.jpeg\"}','image_selection',4),(29,3,'{\"options\": [\"I\",\"SIA\", \"GLE\"],\"correctAnswer\": \"IGLESIA\",\"image\": \"https://i.imgur.com/GiWyncN.jpeg\"}','letter_ordering',2),(30,50,'{\"correctAnswer\": \"IGUANA\"}','audio_repeating',NULL),(31,4,'{\"correctAnswer\": \"OLLA\",\"preSelectedLetters\": [{\"letter\":\"L\",\"index\": 1},{\"letter\":\"L\",\"index\": 2}], \"image\":\"https://i.imgur.com/aSIsh5I.jpeg\"}','image_writing',NULL),(32,4,'{\"options\": [\"JO\",\"O\"],\"correctAnswer\": \"OJO\",\"image\":\"https://i.imgur.com/o801oKQ.jpeg\"}','letter_ordering',NULL),(33,4,'{\"options\": [\"VE\",\"JA\", \"O\"],\"correctAnswer\": \"OVEJA\",\"image\":\"https://i.imgur.com/3TvbOvf.jpeg\"}','letter_ordering',NULL),(34,4,'{\"correctAnswer\": \"OSO\",\"preSelectedLetters\": [{\"letter\":\"S\",\"index\": 1}], \"image\":\"https://i.imgur.com/qEc8eKB.jpeg\"}','image_writing',NULL),(35,51,'{\"correctAnswer\": \"OSO\"}','audio_repeating',NULL),(36,5,'{\"correctAnswer\": \"UVA\",\"preSelectedLetters\": [{\"letter\":\"v\",\"index\": 1}], \"image\":\"https://i.imgur.com/1oAg9bm.jpeg\"}','image_writing',2),(37,5,'{\"correctAnswer\": \"UNICORNIO\",\"preSelectedLetters\": [{\"letter\":\"N\",\"index\": 1}, {\"letter\":\"C\",\"index\": 3},{\"letter\":\"R\",\"index\": 5}, {\"letter\":\"N\",\"index\": 6}], \"image\":\"https://i.imgur.com/tmJ7txG.jpeg\"}','image_writing',3),(38,5,'{\"options\": [\"UNIVERSO\",\"UÑA\",\"UNION\",\"UTILIDAD\"],\"correctAnswer\": \"UÑA\",\"image\":\"https://i.imgur.com/wbqzONC.jpeg\"}','image_selection',4),(39,52,'{\"correctAnswer\": \"UVA\"}','audio_repeating',NULL),(45,53,'{\"correctAnswer\": \"PIPA\",\"onlyText\":\"true\"}','audio_repeating',NULL),(46,53,'{\"correctAnswer\": \"PEPA\",\"onlyText\":\"true\"}','audio_repeating',NULL),(47,53,'{\"correctAnswer\": \"PUPO\",\"onlyText\":\"true\"}','audio_repeating',NULL),(48,53,'{\"correctAnswer\": \"MI PAPA\",\"onlyText\":\"true\"}','audio_repeating',NULL),(50,7,'{\"options\": [\"PA\", \"PE\", \"PI\", \"PO\", \"PU\"], \"label\":\"_ _ RATA\", \"correctAnswer\": \"PI\",\"image\":\"https://i.imgur.com/dx7bpUI.jpeg\"}','image_selection',NULL),(51,7,'{\"correctAnswer\": \"PIRATA\",\"preSelectedLetters\": [{\"letter\":\"R\",\"index\": 2}, {\"letter\":\"T\",\"index\": 4}], \"image\":\"https://i.imgur.com/sus20rR.jpeg\"}','image_writing',NULL),(52,7,'{\"options\": [\"LO\",\"PE\", \"TA\"],\"correctAnswer\": \"PELOTA\", \"image\":\"https://i.imgur.com/gK0VwNp.jpeg\"}','letter_ordering',NULL),(53,7,'{\"correctAnswer\": \"PATO\",\"preSelectedLetters\": [{\"letter\":\"T\",\"index\": 2}], \"image\":\"https://i.imgur.com/HZPd73f.jpeg\"}','image_writing',NULL),(55,7,'{\"correctAnswer\": \"PERA\",\"preSelectedLetters\": [{\"letter\":\"R\",\"index\": 2}], \"image\":\"https://i.imgur.com/0V85zAY.jpeg\"}','image_writing',NULL),(56,7,'{\"correctAnswer\": \"PIPA\",\"preSelectedLetters\": [], \"image\":\"https://i.imgur.com/CZNTa4B.jpeg\"}','image_writing',NULL),(66,55,'{\"correctAnswer\": \"BEBE\",\"onlyText\":\"true\"}','audio_repeating',NULL),(67,55,'{\"correctAnswer\": \"BEBA\",\"onlyText\":\"true\"}','audio_repeating',NULL),(68,55,'{\"correctAnswer\": \"MI BEBE\",\"onlyText\":\"true\"}','audio_repeating',NULL),(69,8,'{\"options\": [\"BA\", \"BE\", \"BI\", \"BO\", \"BU\"], \"label\":\"_ _FANDA \", \"correctAnswer\": \"BU\",\"image\":\"https://i.imgur.com/rLC1zqy.png\"}','image_selection',NULL),(70,8,'{\"correctAnswer\": \"BARCO\",\"preSelectedLetters\": [{\"letter\":\"R\",\"index\": 2}, {\"letter\":\"C\",\"index\": 3}], \"image\":\"https://i.imgur.com/4qkIzoH.png\"}','image_writing',NULL),(71,33,'{\"correctAnswer\": \"BICICLETA\",\"preSelectedLetters\": [{\"letter\":\"B\",\"index\": 0}, {\"letter\":\"C\",\"index\": 2}, {\"letter\":\"C\",\"index\":4}, {\"letter\":\"L\",\"index\":5}, {\"letter\":\"T\",\"index\":7} ], \"image\":\"https://i.imgur.com/IVhzwly.jpeg\"}','image_writing',NULL),(72,33,'{\"options\": [\"TA\",\"CLE\", \"BI\", \"CI\"],\"correctAnswer\": \"BICICLETA\",\"image\":\"https://i.imgur.com/IVhzwly.jpeg\"}','letter_ordering',NULL),(73,8,'{\"correctAnswer\": \"BEBE\",\"preSelectedLetters\": [], \"image\":\"https://i.imgur.com/k3r6Qo1.jpeg\"}','image_writing',NULL),(82,54,'{\"correctAnswer\": \"TABLA\",\"onlyText\":\"true\"}','audio_repeating',NULL),(83,54,'{\"correctAnswer\": \"TABLETA\",\"onlyText\":\"true\"}','audio_repeating',NULL),(86,54,'{\"correctAnswer\": \"LA TABLA\",\"onlyText\":\"true\"}','audio_repeating',NULL),(87,54,'{\"correctAnswer\": \"LA TABLETA DE PABLO\",\"onlyText\":\"true\"}','audio_repeating',NULL),(88,31,'{\"options\": [\"DO\",\"NU\", \"BLA\"],\"correctAnswer\": \"NUBLADO\",\"image\":\"https://i.imgur.com/QvhQO3A.jpeg\"}','letter_ordering',NULL),(89,31,'{\"options\": [\"BLA\", \"BLE\", \"BLI\", \"BLO\", \"BLU\"], \"label\":\"CA_ _ _ \", \"correctAnswer\": \"BLE\",\"image\":\"https://i.imgur.com/zWkH4Bi.png\"}','image_selection',NULL),(90,31,'{\"correctAnswer\": \"NUBLADO\",\"preSelectedLetters\": [{\"N\": 0}, {\"D\": 6} ], \"image\":\"https://i.imgur.com/QvhQO3A.jpeg\"}','image_writing',NULL),(91,31,'{\"correctAnswer\": \"TABLA\",\"preSelectedLetters\": [], \"image\":\"https://i.imgur.com/7Lu9TW2.png\"}','image_writing',NULL),(94,44,'{\"correctAnswer\": \"LA BEBE DE MARIA\",\"onlyText\":\"true\"}','audio_repeating',NULL),(95,44,'{\"correctAnswer\": \"LAS NENAS AMAN A SU MAMA\",\"onlyText\":\"true\"}','audio_repeating',NULL),(96,44,'{\"correctAnswer\": \"LOS NENES Y LAS NENAS SE PELEAN\",\"onlyText\":\"true\"}','audio_repeating',NULL),(97,44,'{\"correctAnswer\": \"UN BEBE Y UNA NENA\",\"onlyText\":\"true\"}','audio_repeating',NULL),(98,44,'{\"correctAnswer\": \"SE PUSO UNOS BOTINES NUEVOS \",\"onlyText\":\"true\"}','audio_repeating',NULL),(99,44,'{\"correctAnswer\": \"SE PUSO UNOS BOTINES NUEVOS PARA JUGAR A LA PELOTA \",\"onlyText\":\"true\"}','audio_repeating',NULL),(100,47,'{\"correctAnswer\": \"MONO\",\"preSelectedLetters\": [ ], \"image\":\"https://i.imgur.com/2sm2I9e.jpeg\"}','image_writing',NULL),(101,47,'{\"correctAnswer\": \"TIGRE\",\"preSelectedLetters\": [ ], \"image\":\"https://i.imgur.com/yCJY0ND.jpeg\"}','image_writing',NULL),(102,47,'{\"correctAnswer\": \"GLOBO\",\"preSelectedLetters\": [ ], \"image\":\"https://i.imgur.com/hCgkden.png\"}','image_writing',NULL),(103,47,'{\"correctAnswer\": \"BICICLETA\",\"preSelectedLetters\": [ ], \"image\":\"https://i.imgur.com/IVhzwly.jpeg\"}','image_writing',NULL),(104,47,'{\"options\": [\"DE\",\"CASA\", \"MIGUEL\", \"LA\", \"ES\", \"ROJA\"],\"correctAnswer\": \"LA CASA DE MIGUEL ES ROJA\",\"image\":\"https://i.imgur.com/JUj7xKp.png\"}','letter_ordering',NULL),(105,47,'{\"options\": [\"EDIFICIOS\",\"PEDRO\", \"UNOS\", \"CONSTRUYÓ\"],\"correctAnswer\": \"PEDRO CONSTRUYÓ UNOS EDIFICIOS\",\"image\":\"https://i.imgur.com/PFEwHAe.png\"}','letter_ordering',NULL),(108,46,'{\"correctAnswer\": \"Pablito clavó un clavito en la calva de un calvito. Un clavito clavó Pablito en la calva de un calvito. ¿Qué clavito clavó Pablito? \",\"onlyText\":\"true\"}','audio_repeating',NULL),(109,46,'{\"correctAnswer\": \"Tres tristes tigres tragaban trigo en un trigal, en tres tristes trastros tragaban trigo tres tristes tigres \",\"onlyText\":\"true\"}','audio_repeating',NULL),(110,46,'{\"correctAnswer\": \"Luengas lenguas hacen falta para no trabalenguarse. El que no tenga una luenga lengua bien podrá trabalenguarse.\",\"onlyText\":\"true\"}','audio_repeating',NULL),(111,46,'{\"correctAnswer\": \"Cuando cuentes cuentos, cuenta cuántos cuentos cuentas, porque si no cuentas cuántos cuentos cuentas, nunca sabrás cuántos cuentos cuentas tú.\",\"onlyText\":\"true\"}','audio_repeating',NULL),(115,9,'{\"options\": [\"SA\", \"SE\", \"SI\", \"SO\", \"SU\"], \"label\":\"_ _ RENA\", \"correctAnswer\": \"SI\",\"image\":\"https://i.imgur.com/0OGVsXB.jpeg\"}','image_selection',NULL),(116,9,'{\"correctAnswer\": \"SERPIENTE\",\"preSelectedLetters\": [{\"letter\":\"R\",\"index\": 2}, {\"letter\":\"N\",\"index\": 6}, {\"letter\":\"T\",\"index\":7}], \"image\":\"https://i.imgur.com/UKLGs6U.jpeg\"}','image_writing',NULL),(117,9,'{\"options\": [\"PIEN\",\"TE\", \"SER\"],\"correctAnswer\": \"SERPIENTE\",\"image\":\"https://i.imgur.com/UKLGs6U.jpeg\"}','letter_ordering',NULL),(118,9,'{\"correctAnswer\": \"SAPO\",\"preSelectedLetters\":[], \"image\":\"https://i.imgur.com/pIeE001.jpeg\"}','image_writing',NULL),(119,10,'{\"options\": [\"PA\",\"LAM\", \"RA\"],\"correctAnswer\": \"LAMPARA\",\"image\":\"https://i.imgur.com/7HETgL3.jpeg\"}','letter_ordering',NULL),(120,10,'{\"correctAnswer\": \"LEON\",\"preSelectedLetters\": [{\"N\": 3}], \"image\":\"https://i.imgur.com/nHZpNyn.jpeg\"}','image_writing',NULL),(121,10,'{\"options\": [\"LA\", \"LE\", \"LI\", \"LO\", \"LU\"], \"label\":\"_ _ BRO\", \"correctAnswer\": \"LI\",\"image\":\"https://i.imgur.com/5uGyyc0.jpeg\"}','image_selection',NULL),(122,10,'{\"correctAnswer\": \"LUPA\"], \"image\":\"https://i.imgur.com/yGl0gOb.jpeg\"}','image_writing',NULL),(123,11,'{\"options\": [\"RIO\",\"DI\", \"NO\", \"SAU\"],\"correctAnswer\": \"DINOSAURIO\",\"image\":\"https://i.imgur.com/WMgbcRX.jpeg\"}','letter_ordering',NULL),(124,11,'{\"correctAnswer\": \"DEDOS\"], \"image\":\"https://i.imgur.com/8PVedCF.jpeg\"}','image_writing',NULL),(125,11,'{\"correctAnswer\": \"DADOS\"], \"image\":\"https://i.imgur.com/N7YKnNo.jpeg\"}','image_writing',NULL),(126,11,'{\"correctAnswer\": \"DIAMANTE\",\"preSelectedLetters\": [{\"N\": 5}, {\"T\":6}], \"image\":\"https://i.imgur.com/zkxIeCM.jpeg\"}','image_writing',NULL),(127,12,'{\"options\": [\"DO\", \"NI\"],\"correctAnswer\": \"NIDO\",\"image\":\"https://i.imgur.com/WMgbcRX.jpeg\"}','letter_ordering',NULL),(128,12,'{\"correctAnswer\": \"NUBES\",\"preSelectedLetters\": [], \"image\":\"https://i.imgur.com/zkxIeCM.jpeg\"}','image_writing',NULL),(129,13,'{\"options\": [\"MA\", \"FAN\", \"TAS\"],\"correctAnswer\": \"FANTASMA\",\"image\":\"https://i.imgur.com/WMgbcRX.jpeg\"}','letter_ordering',NULL),(130,13,'{\"correctAnswer\": \"FOCA\",\"preSelectedLetters\": [{\"C\": 2}], \"image\":\"https://i.imgur.com/zkxIeCM.jpeg\"}','image_writing',NULL),(131,13,'{\"correctAnswer\": \"FUEGO\",\"preSelectedLetters\": [{\"G\": 3}], \"image\":\"https://i.imgur.com/zkxIeCM.jpeg\"}','image_writing',NULL),(132,14,'{\"options\": [\"MA\", \"TE\", \"TO\"],\"correctAnswer\": \"TOMATE\",\"image\":\"https://i.imgur.com/WMgbcRX.jpeg\"}','letter_ordering',NULL),(133,14,'{\"correctAnswer\": \"TORNADO\",\"preSelectedLetters\": [{\"R\": 2}], \"image\":\"https://i.imgur.com/zkxIeCM.jpeg\"}','image_writing',NULL),(134,15,'{\"correctAnswer\": \"RATON\", \"image\":\"https://i.imgur.com/zkxIeCM.jpeg\"}','image_writing',NULL),(135,15,'{\"options\": [\"LO\", \"GA\", \"RE\"],\"correctAnswer\": \"REGALO\",\"image\":\"https://i.imgur.com/WMgbcRX.jpeg\"}','letter_ordering',NULL),(136,15,'{\"correctAnswer\": \"ROSA\", \"image\":\"https://i.imgur.com/zkxIeCM.jpeg\"}','image_writing',NULL),(137,16,'{\"options\": [\"FA\", \"JI\", \"RA\"],\"correctAnswer\": \"REGALO\",\"image\":\"https://i.imgur.com/WMgbcRX.jpeg\"}','letter_ordering',NULL),(138,16,'{\"correctAnswer\": \"JIRAFA\", \"image\":\"https://i.imgur.com/zkxIeCM.jpeg\"}','image_writing',NULL),(139,16,'{\"correctAnswer\": \"JABON\",\"preSelectedLetters\": [{\"N\": 4} ], \"image\":\"https://i.imgur.com/6wfI0yR.jpeg\"}','image_writing',NULL),(140,17,'{\"options\": [\"LA\", \"RI\", \"GO\"],\"correctAnswer\": \"GORILA\",\"image\":\"https://i.imgur.com/WMgbcRX.jpeg\"}','letter_ordering',NULL),(141,17,'{\"options\": [\"NO\", \"GU\", \"NA\"],\"correctAnswer\": \"GUSANO\",\"image\":\"https://i.imgur.com/WMgbcRX.jpeg\"}','letter_ordering',NULL),(142,17,'{\"options\": [\"TO\", \"GA\"],\"correctAnswer\": \"GATO\",\"image\":\"https://i.imgur.com/WMgbcRX.jpeg\"}','letter_ordering',NULL),(143,17,'{\"options\": [\"TI\", \"GE\", \"NA\", \"LA\"],\"correctAnswer\": \"GELATINA\",\"image\":\"https://i.imgur.com/WMgbcRX.jpeg\"}','letter_ordering',NULL),(144,17,'{\"options\": [\"RA\", \"GI\", \"SOL\"],\"correctAnswer\": \"GIRASOL\",\"image\":\"https://i.imgur.com/WMgbcRX.jpeg\"}','letter_ordering',NULL),(145,18,'{\"options\": [\"BA\", \"CA\", \"LLO\"],\"correctAnswer\": \"CABALLO\",\"image\":\"https://i.imgur.com/WMgbcRX.jpeg\"}','letter_ordering',NULL),(146,18,'{\"options\": [\"BRA\", \"CU\", \"LE\"],\"correctAnswer\": \"CULEBRA\",\"image\":\"https://i.imgur.com/WMgbcRX.jpeg\"}','letter_ordering',NULL),(147,18,'{\"options\": [\"CI\", \"NA\", \"CO\"],\"correctAnswer\": \"COCINA\",\"image\":\"https://i.imgur.com/WMgbcRX.jpeg\"}','letter_ordering',NULL),(148,18,'{\"options\": [\"PI\", \"LLO\", \"CE\"],\"correctAnswer\": \"CEPILLO\",\"image\":\"https://i.imgur.com/WMgbcRX.jpeg\"}','letter_ordering',NULL),(149,18,'{\"correctAnswer\": \"CACA\",\"preSelectedLetters\": [], \"image\":\"https://i.imgur.com/yOAn9Hh.jpeg\"}','image_writing',NULL),(150,19,'{\"options\": [\"TE\", \"KA\", \"RA\"],\"correctAnswer\": \"KARATE\",\"image\":\"https://i.imgur.com/WMgbcRX.jpeg\"}','letter_ordering',NULL),(151,19,'{\"correctAnswer\": \"KOALA\",\"preSelectedLetters\": [], \"image\":\"https://i.imgur.com/yOAn9Hh.jpeg\"}','image_writing',NULL),(152,20,'{\"options\": [\"TE\", \"YA\"],\"correctAnswer\": \"YATE\",\"image\":\"https://i.imgur.com/WMgbcRX.jpeg\"}','letter_ordering',NULL),(153,20,'{\"correctAnswer\": \"YOYO\",\"preSelectedLetters\": [], \"image\":\"https://i.imgur.com/yOAn9Hh.jpeg\"}','image_writing',NULL),(154,21,'{\"options\": [\"QUIS\", \"ÑO\"],\"correctAnswer\": \"ÑOQUIS\",\"image\":\"https://i.imgur.com/WMgbcRX.jpeg\"}','letter_ordering',NULL),(155,21,'{\"options\": [\"DU\", \"ÑAN\"],\"correctAnswer\": \"ÑANDU\",\"image\":\"https://i.imgur.com/WMgbcRX.jpeg\"}','letter_ordering',NULL),(156,21,'{\"options\": [\"ÑA\", \"ÑE\", \"ÑI\", \"ÑO\", \"ÑU\"], \"label\":\"MU_ _ CA \", \"correctAnswer\": \"ÑE\",\"image\":\"https://i.imgur.com/k3Oc6Ua.jpeg\"}','image_selection',NULL),(157,22,'{\"options\": [\"GA\", \"MI\", \"HOR\"],\"correctAnswer\": \"KARATE\",\"image\":\"https://i.imgur.com/WMgbcRX.jpeg\"}','letter_ordering',NULL),(158,22,'{\"correctAnswer\": \"HILO\",\"preSelectedLetters\": [], \"image\":\"https://i.imgur.com/yOAn9Hh.jpeg\"}','image_writing',NULL),(159,23,'{\"correctAnswer\": \"VACA\",\"preSelectedLetters\": [], \"image\":\"https://i.imgur.com/yOAn9Hh.jpeg\"}','image_writing',NULL),(160,23,'{\"options\": [\"NA\", \"TA\", \"VEN\"],\"correctAnswer\": \"VENTANA\",\"image\":\"https://i.imgur.com/WMgbcRX.jpeg\"}','letter_ordering',NULL),(161,24,'{\"options\": [\"DO\", \"MA\", \"QUE\"],\"correctAnswer\": \"QUEMADO\",\"image\":\"https://i.imgur.com/WMgbcRX.jpeg\"}','letter_ordering',NULL),(162,24,'{\"options\": [\"QUE\", \"QUI\"], \"label\":\"SO_ _ _TES \", \"correctAnswer\": \"QUE\",\"image\":\"https://i.imgur.com/k3Oc6Ua.jpeg\"}','image_selection',NULL),(163,24,'{\"correctAnswer\": \"QUINCE\",\"preSelectedLetters\": [], \"image\":\"https://i.imgur.com/yOAn9Hh.jpeg\"}','image_writing',NULL),(164,24,'{\"correctAnswer\": \"QUESO\",\"preSelectedLetters\": [], \"image\":\"https://i.imgur.com/yOAn9Hh.jpeg\"}','image_writing',NULL),(165,24,'{\"correctAnswer\": \"MANIQUI\",\"preSelectedLetters\": [{\"N\": 2} ], \"image\":\"https://i.imgur.com/6wfI0yR.jpeg\"}','image_writing',NULL),(166,25,'{\"correctAnswer\": \"PERRO\",\"preSelectedLetters\": [], \"image\":\"https://i.imgur.com/yOAn9Hh.jpeg\"}','image_writing',NULL),(167,25,'{\"options\": [\"RRA\", \"GUI\", \"TA\"],\"correctAnswer\": \"GUITARRA\",\"image\":\"https://i.imgur.com/WMgbcRX.jpeg\"}','letter_ordering',NULL),(168,26,'{\"correctAnswer\": \"LLAVE\",\"preSelectedLetters\": [{\"V\": 3} ], \"image\":\"https://i.imgur.com/6wfI0yR.jpeg\"}','image_writing',NULL),(169,26,'{\"correctAnswer\": \"LLUVIA\",\"preSelectedLetters\": [], \"image\":\"https://i.imgur.com/yOAn9Hh.jpeg\"}','image_writing',NULL),(170,27,'{\"options\": [\"TO\", \"PA\", \"ZA\"],\"correctAnswer\": \"ZAPATO\",\"image\":\"https://i.imgur.com/WMgbcRX.jpeg\"}','letter_ordering',NULL),(171,27,'{\"correctAnswer\": \"ZAPALLO\",\"preSelectedLetters\": [], \"image\":\"https://i.imgur.com/yOAn9Hh.jpeg\"}','image_writing',NULL),(172,28,'{\"correctAnswer\": \"KIWI\",\"preSelectedLetters\": [{\"W\": 2} ], \"image\":\"https://i.imgur.com/6wfI0yR.jpeg\"}','image_writing',NULL),(173,29,'{\"options\": [\"FON\", \"XI\", \"LO\"],\"correctAnswer\": \"XILOFON\",\"image\":\"https://i.imgur.com/WMgbcRX.jpeg\"}','letter_ordering',NULL),(174,29,'{\"correctAnswer\": \"SAXO\",\"preSelectedLetters\": [ ], \"image\":\"https://i.imgur.com/6wfI0yR.jpeg\"}','image_writing',NULL),(175,30,'{\"options\": [\"CO\", \"TE\", \"LA\", \"CHO\"],\"correctAnswer\": \"CHOCOLATE\",\"image\":\"https://i.imgur.com/WMgbcRX.jpeg\"}','letter_ordering',NULL),(176,30,'{\"correctAnswer\": \"CHANCHO\",\"preSelectedLetters\": [{\"N\": 3} ], \"image\":\"https://i.imgur.com/6wfI0yR.jpeg\"}','image_writing',NULL),(177,32,'{\"options\": [\"JU\", \"LA\", \"BRU\"],\"correctAnswer\": \"BRUJULA\",\"image\":\"https://i.imgur.com/4XLRsPO.jpg\"}','letter_ordering',NULL),(178,32,'{\"correctAnswer\": \"BRAZO\",\"preSelectedLetters\": [], \"image\":\"https://i.imgur.com/bleqerP.png\"}','image_writing',NULL),(179,32,'{\"correctAnswer\": \"BRUJA\",\"preSelectedLetters\": [], \"image\":\"https://i.imgur.com/FTt9j3k.png\"}','image_writing',NULL),(180,33,'{\"correctAnswer\": \"CLAVO\",\"preSelectedLetters\": [], \"image\":\"https://i.imgur.com/nBXC4CO.png\"}','image_writing',NULL),(181,33,'{\"correctAnswer\": \"CLIP\",\"preSelectedLetters\": [], \"image\":\"https://i.imgur.com/ZgxiVRE.jpeg\"}','image_writing',NULL),(182,34,'{\"correctAnswer\": \"CRUZ\",\"preSelectedLetters\": [{\"Z\": 3} ], \"image\":\"https://i.imgur.com/J7Dr5Cq.jpeg\"}','image_writing',NULL),(183,34,'{\"correctAnswer\": \"CRANEO\",\"preSelectedLetters\": [{\"N\": 3} ], \"image\":\"https://i.imgur.com/hWdyoAa.jpeg\"}','image_writing',NULL),(184,35,'{\"correctAnswer\": \"DRAGON\",\"preSelectedLetters\": [{\"G\": 3} ], \"image\":\"https://i.imgur.com/CEJty5Y.jpeg\"}','image_writing',NULL),(185,35,'{\"correctAnswer\": \"CUADRO\",\"preSelectedLetters\": [{\"C\": 0} ], \"image\":\"https://i.imgur.com/Sclwai9.jpeg\"}','image_writing',NULL),(186,36,'{\"correctAnswer\": \"FLOR\",\"preSelectedLetters\": [{\"R\": 3} ], \"image\":\"https://i.imgur.com/qZqZNwv.png\"}','image_writing',NULL),(187,36,'{\"options\": [\"CO\", \"MEN\", \"FLA\"],\"correctAnswer\": \"FLAMENCO\",\"image\":\"https://i.imgur.com/2MRGkSN.jpeg\"}','letter_ordering',NULL),(188,37,'{\"options\": [\"FRU\", \"LLA\", \"TI\"],\"correctAnswer\": \"FRUTILLA\",\"image\":\"https://i.imgur.com/OyBxQuS.jpeg\"}','letter_ordering',NULL),(189,37,'{\"correctAnswer\": \"COFRE\",\"preSelectedLetters\": [{\"C\": 0} ], \"image\":\"https://i.imgur.com/W3pRuS6.jpeg\"}','image_writing',NULL),(190,38,'{\"correctAnswer\": \"GLOBO\",\"preSelectedLetters\": [{\"B\": 3} ], \"image\":\"https://i.imgur.com/d4BoBiA.jpeg\"}','image_writing',NULL),(191,38,'{\"options\": [\"DOR\", \"GLA\", \"DIA\"],\"correctAnswer\": \"GLADIADOR\",\"image\":\"https://i.imgur.com/HpQnkp3.jpeg\"}','letter_ordering',NULL),(192,39,'{\"correctAnswer\": \"GRUA\",\"preSelectedLetters\": [ ], \"image\":\"https://i.imgur.com/MOSFsyV.jpeg\"}','image_writing',NULL),(193,39,'{\"correctAnswer\": \"GRANJA\",\"preSelectedLetters\": [{\"J\": 4} ], \"image\":\"https://i.imgur.com/l1PwJD8.jpeg\"}','image_writing',NULL),(194,40,'{\"correctAnswer\": \"PLUMA\",\"preSelectedLetters\": [ ], \"image\":\"https://i.imgur.com/21nuFd1.jpeg\"}','image_writing',NULL),(195,40,'{\"correctAnswer\": \"PLATO\",\"preSelectedLetters\": [ ], \"image\":\"https://i.imgur.com/1O2OGj1.jpeg\"}','image_writing',NULL),(196,41,'{\"options\": [\"CE\", \"SA\", \"PRIN\"],\"correctAnswer\": \"PRINCESA\", \"image\":\"https://i.imgur.com/SDAmkWJ.jpeg\"}','letter_ordering',NULL),(197,41,'{\"correctAnswer\": \"PRESO\",\"preSelectedLetters\": [], \"image\":\"https://i.imgur.com/o3hn58A.jpeg\"}','image_writing',NULL),(198,42,'{\"correctAnswer\": \"TREN\",\"preSelectedLetters\": [], \"image\":\"https://i.imgur.com/LNdaUpp.jpeg\"}','image_writing',NULL),(199,42,'{\"correctAnswer\": \"TROFEO\",\"preSelectedLetters\": [{\"F\": 3}], \"image\":\"https://i.imgur.com/XEDNgKD.jpeg\"}','image_writing',NULL),(200,42,'{\"options\": [\"CLO\", \"TRI\", \"CI\"],\"correctAnswer\": \"TRICICLO\",\"image\":\"https://i.imgur.com/92CwgdZ.jpeg\"}','letter_ordering',NULL),(201,43,'{\"options\": [\"TLE\", \"TA\", \"A\"],\"correctAnswer\": \"ATLETA\",\"image\":\"https://i.imgur.com/YnlmBUG.jpeg\"}','letter_ordering',NULL),(202,45,'{\"options\": [\"VASO\", \"ANILLO\", \"RUEDA\", \"PELOTA\"], \"label\":\"Redondo redondo, barril sin fondo\", \"correctAnswer\": \"ANILLO\",\"image\":\"https://i.imgur.com/daGxvEo.png\"}','image_selection',NULL),(203,43,'{\"correctAnswer\": \"Atlanta\"}','image_writing',NULL),(208,1,'{\"options\": [\"MA\", \"ME\", \"MI\", \"MO\", \"MU\"], \"correctAnswer\": \"MA\"}','image_selection',2),(209,1,'{\"src\":\"https://www.youtube.com/embed/loSTO3FFpbo?si=8YARKykaZ9NvwIW_\"}','video',1),(210,2,'{\"src\":\"https://www.youtube.com/embed/Be8ffJQZl6I?si=XdWPNfbxeAUniOyR\"}','video',1),(211,3,'{\"src\":\"https://www.youtube.com/embed/mVE0XVwaiKs?si=giRspiSvprJwIYYV\"}','video',1);
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
INSERT INTO `module` VALUES (1,'Básico'),(2,'Intermedio'),(3,'Avanzado');
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
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `player`
--

LOCK TABLES `player` WRITE;
/*!40000 ALTER TABLE `player` DISABLE KEYS */;
INSERT INTO `player` VALUES (1,'saleck\'s child','2022-06-23 00:00:00',12,6,1,NULL,'saleck80'),(2,'saleckin',NULL,0,0,37,0,'saleckin'),(3,'saleckin',NULL,0,0,38,0,'saleckin'),(4,'fafafaf',NULL,0,0,39,1,'fafafaf'),(5,'saleckin',NULL,0,0,40,0,'saleckin'),(6,'Saleckin',NULL,0,0,41,0,'Saleckin'),(7,'saleckin',NULL,0,0,42,0,'saleckin'),(8,'saleckin',NULL,0,0,43,0,'saleckin'),(9,'saleckin',NULL,0,0,44,1,'saleckin'),(10,'Saleckin',NULL,0,0,45,3,'Saleckin'),(11,'Santito',NULL,0,0,46,2,'Santito'),(12,'alelito',NULL,0,0,47,3,'alelito'),(13,'saleckin',NULL,0,0,48,3,'saleckin'),(14,'Santito',NULL,0,0,49,3,'Santito'),(15,'saleckin',NULL,0,0,50,3,'saleckin'),(16,'Milo',NULL,0,0,51,2,'Milo'),(17,'Milo',NULL,0,0,52,3,'Milo'),(18,'Teo',NULL,0,0,53,2,'Teo'),(19,'Teo',NULL,0,0,54,3,'Teo'),(20,'helena',NULL,0,0,55,3,'helena'),(21,'gruno',NULL,0,0,56,3,'gruno'),(22,'Teo',NULL,0,0,57,3,'Teo'),(23,'Teo',NULL,0,0,58,3,'Teo'),(24,'helena',NULL,0,0,59,3,'helena'),(25,'helena',NULL,0,0,60,3,'helena'),(35,'asfdgasergh','2020-12-12 00:00:00',0,0,1,NULL,'asfdgasergh'),(36,'Test',NULL,0,0,61,0,'Test');
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
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `result`
--

LOCK TABLES `result` WRITE;
/*!40000 ALTER TABLE `result` DISABLE KEYS */;
INSERT INTO `result` (`my_row_id`, `player_id`, `apple_id`, `score`) VALUES (1,1,1,3),(2,1,2,3),(3,1,3,3),(4,1,6,3),(5,2,1,3),(6,2,2,3),(7,3,1,3),(8,3,2,3),(9,1,31,2),(10,1,49,3),(12,1,50,3),(13,1,51,3),(14,1,48,2),(15,7,1,3),(16,7,2,3),(17,7,3,3),(18,1,5,3),(19,6,1,3),(20,11,1,3),(21,12,1,3),(22,13,1,3),(23,14,1,3),(24,15,1,3),(25,15,49,3),(26,15,50,3),(27,15,48,2),(28,16,1,2),(29,16,49,3),(30,16,2,3),(31,1,32,3),(32,17,1,3),(33,17,6,3),(34,17,48,2),(35,4,33,3),(36,4,31,3),(37,4,32,3),(38,1,45,3),(39,1,33,3),(40,1,34,3),(41,4,34,3),(42,1,4,3),(43,4,35,3),(44,4,36,3),(45,4,37,3),(46,1,35,3),(47,1,36,3),(48,1,37,3),(49,4,38,3),(50,4,39,3),(51,4,40,3),(52,1,7,3),(53,1,8,3),(54,4,41,3),(55,4,42,3),(56,4,43,3),(57,1,9,3),(58,4,2,3),(59,4,6,2),(60,18,2,2),(61,18,6,3),(62,18,48,3),(63,18,31,3),(64,18,54,2),(65,19,2,2),(66,19,6,2),(67,19,48,2),(68,19,31,3),(69,4,1,3),(70,4,18,1),(71,22,1,3),(72,1,43,2);
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
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Saleck','Gonzalez','{cJjx/lXz/a7g4aSGFLghGGpUqPGQ6YXMBT+7C3Cl45E=}b1b1c6139ebc674ffaaac67a48f64a3a8cfdf67f2c588c9f41269f5b57c0debd','saleck@lecti.com'),(36,'marcelo','Juárez','{cJjx/lXz/a7g4aSGFLghGGpUqPGQ6YXMBT+7C3Cl45E=}b1b1c6139ebc674ffaaac67a48f64a3a8cfdf67f2c588c9f41269f5b57c0debd','marcelo@lecti.com'),(37,'saleck','gonzalez','{xnrSopJDYqKcDU6IPsWOl+B1DuEy5ktqkQLaoGYGpV4=}092e6c55913feb3f51822084848de6f45f72ee00046dc44c4eb444a77b62c270','saleck1@gmail.com'),(38,'saleck','gonzalez','{LWKSTY1UkWsLt5MnDTGRUSFfoqhSqRhwcbFMgmdJ4lU=}3d0f24859c0f2056142ab1d80d55164788832e0401d0fe512b50477eee7de466','saleck2@gmail.com'),(39,'laura','iiiiiiii','{JXkcehEry1YKrTWvvQIpeJtU/9ZwEUtw5VfXwtIsBHI=}317ca4998746943bf7155e0025f86d64da4f2a0300ad8cc849de98f624c203ef','gerfman@grg.com'),(40,'saleck','gonzalez','{kMaON3UyLsosrfgw/MsyUiFEzcP/sssWnDbLt6/cLO4=}6b638d480a4872ca94a15da0a9735f7b4dcb25d429cc504a297cbd5c5040daf8','saleck4@gmail.com'),(41,'Saleck','Gonzalez','{GyWIAlKAGPo520XhHEkQMhPJNDH0H/OZUpBYipAimk8=}6a6a4a2e7b4a9fd04b5a08ce42a9d82b85653165996e9e1b3b872e85b6cb2563','saleck2@lecti.com'),(42,'saleck','gonzalez','{MVVziWagRTDjm0AkPICCi6qyHWNukEY/mzNHT91X5N4=}961463c36bc0d18787710ab3aa4150de4d05daad07b8cf522a77dcd6e6966192','saleck5@gmail.com'),(43,'saleck','gonzalez','{wY3ys5zLCKYZZpbiuVliWyrnd7xX1sAkyL/UcxliTwg=}f7a713d23e476d235a03011ed37472b7590196d2407ba3919408d60d4b03eb8e','saleck6@gmail.com'),(44,'saleck','gonzalez','{7pwXO3sdlbzKQGTlHP+kAVlPXtT1xmxRVxzx+usWKGY=}4baab7b3fab4d0b4b3c58e9c47256a1681ba5778134166c125f35ff1ce434d94','saleck7@gmail.com'),(45,'Saleck','Gonzalez','{moskUOZf7iu37YbljZS1xPnDwxGtF7nR9pv8M6L6sns=}e2ba4ffe86be6f619d9685388c4efee95f7c04317f4807744c108109be1c887a','saleck3@lecti.com'),(46,'Santiago','Fernandez Lopes','{Ad1OfQb2YIcvckw0z9Evp1JPProgvKwzY21D0vu1984=}758241bc955b06bae443eb4eac9fa2942e80555ecfa9e32517d65149d5de40e8','santifl27@gmail.com'),(47,'alejandoro','Gonzalez','{cWkcLkpj5FphwWuQzykKEhtPMjOsibgYZIKTe4cAsu8=}2f0c6e49163dcaadb43cb3fa444545a8c502d0a9b4137b7917dba8467804e938','ale@lecti.com'),(48,'saleck','gonzalez','{yrGafaiFkdAX1CQCSf5AOVpQqEnftgpm1t6cKsrd4X0=}bbaf3952c34a4c72aeb33c9c2fa2ad62c2913e97a21f08f8843a68ec86ea196e','saleck8@gmail.com'),(49,'Santi','Fernandez','{CfFLf0HKcSAV/LQswM01Et6L0DAKFczRnfopde+/QOo=}162bf0b81b8a5b9c2cb57e2c2044a0748c4abfc77e6a2172d3a9ca34fe99eac8','santi@gmail.com'),(50,'Alejandro','saleck@lecti.com','{It612eZZqAEhoMVPBjkhnv19HlAxiVHLn94rldUHxMQ=}2ab321ef4de3b5d13364f82420b562714b15e72c460bfc471df1952b2b648854','saleck9@lecti.com'),(51,'Alejandro','Gonzalez','{IwUeNsJ5trxLHQvpf+f4a0AMaC6NaomJWdmrojLHjss=}f17cea27668c10e85348ef12edb296b56a51acd30dcb45859e15e53254b0c9fe','saleck25@gmail.com'),(52,'Alejandro','Gonzalez','{y/bkkddBbZ9w3BIBT7fjDaEOKn/G+dvnx+jh580R4QY=}236e9cecee44f6d1f9ec069a826c176cf13d7e74d06f317cb6ca0e3caa20dca0','sle@lecti.com'),(53,'Alejandro','Gonzalez','{MLvNVVsdi3L21V2gh9OFmt5imTNVx7CVE/ydJr9Ek/g=}9d3e2787c3b9c9e7d2cc41907df21dba3a0bff5d8038b056f19562f0dedb35ee','saleck@gmail.com'),(54,'Tomas','Arce','{cVKUl7xgJoovsSaCLm7SJIYif2J+TkPgc3UllgCXW7w=}839f59b12661c173ffe77d47ac4790ff5db6e8cd0ebd20647a2138444fba0904','Tomas@lecti.com'),(55,'laura','iiiiiiii','{KwY1enjdB/j9Ntpt2mrpOko3ygkYlt+1SWhLzjInEDk=}80e4753148947c7e9a0fdb2cf1b73e7a97e3e12e020bbade32bbdfd926c9a602','gerfmanjjjj@grg.com'),(56,'laura','iiiiiiii','{mrWxk2X482eomxtPqSLzhWTJKqTTzIMOv6QRGJ8iXJM=}6244f81c7c9fb739972a50e87ed05d761dc353bc0447648ec107a638a2459b30','gerfmanz@grg.com'),(57,'Tomas','Arce','{px17mc19udTXdaE2jEoqV4M+ugz0Yy2oAzluvs/CUOg=}d7b2186ed062b3e116b994130989c704417bdab338c824c7b51d0aaa0def9521','saleck1@lecti.com'),(58,'Tomas','Arce','{UkzeZpUZsg2rmjfvw5Nkn+VzqxuSIhrAzZtd/xtAgsA=}a6513b3ab5d3f721cfbaad07fa43c1b102c9b3a5adecb792fdf682567cf3c4a6','saleck4@lecti.com'),(59,'Pedro','Pícapiedra','{UZA6XzCb+GAf69vzk+DUBNI3luJrX/bEkllyoP5Qb44=}4a6b0495f28627c0595631b30cbc1e961e81152506be0f9678002f266c5d4bb6','germanyyy@grg.com'),(60,'Pedro','Paramo','{Ek7vJTpdzbFNsAjYovBHsfYYU3c/A0SYiSnfxewtD1A=}b3ac94918002088e2bb518b5b92532da0f839579d523803c0b0b04a5c7b8a1dc','germanyata@grg.com'),(61,'Test','Test','{Qm8s+oCLO2KS9bZ1FZtKRAFJrGfgKXANFS9qAYDhVew=}16b3faaf119704bb2008211e269c1542dc26c3df6c95122bd3b08525201d9b1f','Test@gmail.com');
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

-- Dump completed on 2024-06-25 23:30:32
