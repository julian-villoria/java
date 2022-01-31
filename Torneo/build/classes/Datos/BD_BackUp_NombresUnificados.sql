CREATE DATABASE  IF NOT EXISTS `torneo`; 
USE `torneo`;
-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: localhost    Database: torneo
-- ------------------------------------------------------
-- Server version	8.0.26

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0*/;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


DROP TABLE IF EXISTS `tipo_torneo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipo_torneo` (
  `id` int NOT NULL AUTO_INCREMENT,
  `denominacion` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Table structure for table `paises`
--

DROP TABLE IF EXISTS `paises`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
CREATE TABLE `paises` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `juegos`
--

DROP TABLE IF EXISTS `juegos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `juegos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `denominacion` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dificultad`
--

DROP TABLE IF EXISTS `dificultad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dificultad` (
  `nombre` varchar(45) DEFAULT NULL,
  `rango_min_puntaje` int DEFAULT NULL,
  `rango_max_puntaje` int DEFAULT NULL,
  `rango_min_victoria` int DEFAULT NULL,
  `rango_max_victoria` int DEFAULT NULL,
  `id_juego` int NOT NULL,
  PRIMARY KEY (`id_juego`,`nombre`),
  KEY `FK_juegos_idx` (`id_juego`),
  CONSTRAINT `FK_juegos` FOREIGN KEY (`id_juego`) REFERENCES `juegos` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `torneo`
--

DROP TABLE IF EXISTS `torneos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
CREATE TABLE `torneos` (
  `id_juego` int NOT NULL,
  `id_tipo` int NOT NULL,
  `fecha_inicio` date NOT NULL,
  `fecha_fin` date DEFAULT NULL,
  `intentos` int DEFAULT NULL,
  `cupo` int DEFAULT NULL,
  `ganador` varchar(45) DEFAULT NULL,
  `monto_insc` float DEFAULT NULL,
  PRIMARY KEY (`id_juego`,`id_tipo`, `fecha_inicio`),
  KEY `FK_tipo_torneo_idx` (`id_tipo`),
  CONSTRAINT `FK_juegos_torneo` FOREIGN KEY (`id_juego`) REFERENCES `juegos` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `FK_tipo_torneo` FOREIGN KEY (`id_tipo`) REFERENCES `tipo_torneo` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;



--
-- Table structure for table `jugadores`
--

DROP TABLE IF EXISTS `jugadores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jugadores` (
  `id` int NOT NULL AUTO_INCREMENT,
  `usuario` varchar(45) NOT NULL UNIQUE,
  `nombre` varchar(45) NOT NULL,
  `apellido` varchar(45) NOT NULL,
  `contraseña` varchar(100) NOT NULL,
  `id_pais` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_paises_idx` (`id_pais`),
  CONSTRAINT `FK_paises` FOREIGN KEY (`id_pais`) REFERENCES `paises` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `inscripcion`
--
DROP TABLE IF EXISTS `inscripciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inscripciones` (
  `id_jugador` int NOT NULL,
  `id_tipo` int NOT NULL,
  `id_juego` int NOT NULL,
  `fecha` date DEFAULT NULL,
  `fecha_inicio_torneo` date NOT NULL,
  PRIMARY KEY (`id_jugador`,`id_tipo`,`id_juego`,`fecha_inicio_torneo`),
  KEY `FK_torneo_insc_idx` (`id_tipo`, `id_juego`,`fecha_inicio_torneo`),
  CONSTRAINT `FK_jugador_insc` FOREIGN KEY (`id_jugador`) REFERENCES `jugadores` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `FK_torneo_insc` FOREIGN KEY (`id_juego`, `id_tipo`,`fecha_inicio_torneo`) REFERENCES `torneos` (`id_juego`, `id_tipo`, `fecha_inicio`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `nivel_juego`
--

DROP TABLE IF EXISTS `nivel_juego`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
CREATE TABLE `nivel_juego` (
  `id_juego` int NOT NULL,
  `id_jugador` int NOT NULL,
  `puntaje_total` double DEFAULT NULL,
  PRIMARY KEY (`id_juego`,`id_jugador`),
  KEY `FK_jugadores_nivel_idx` (`id_jugador`),
  CONSTRAINT `FK_juegos_nivel` FOREIGN KEY (`id_juego`) REFERENCES `juegos` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `FK_jugadores_nivel` FOREIGN KEY (`id_jugador`) REFERENCES `jugadores` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `partida`
--

DROP TABLE IF EXISTS `partidas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
CREATE TABLE `partidas` (
  `id_juego` int NOT NULL,
  `id_jugador` int NOT NULL,
  `puntaje` double DEFAULT NULL,
  `fecha_hora` datetime DEFAULT NULL,
  PRIMARY KEY (`id_juego`,`id_jugador`),
  KEY `FK_jugadores_partida_idx` (`id_jugador`),
  CONSTRAINT `FK_juego_partida` FOREIGN KEY (`id_juego`) REFERENCES `juegos` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `FK_jugadores_partida` FOREIGN KEY (`id_jugador`) REFERENCES `jugadores` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `periodo_inscripcion`
--

DROP TABLE IF EXISTS `periodo_inscripcion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `periodo_inscripcion` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fecha_desde` date DEFAULT NULL,
  `fecha_hasta` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tipo_torneo`
--


/*!40101 SET character_set_client = @saved_cs_client */;



/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

INSERT INTO `torneo`.`juegos` (`id`, `denominacion`) VALUES ('1', 'snake');
INSERT INTO `torneo`.`juegos` (`id`, `denominacion`) VALUES ('2', 'tetris');

INSERT INTO `torneo`.`tipo_torneo` (`id`, `denominacion`) VALUES ('1', 'Eliminatorias');
INSERT INTO `torneo`.`tipo_torneo` (`id`, `denominacion`) VALUES ('2', 'Puntaje');

INSERT INTO `torneo`.`paises` (`id`, `nombre`) VALUES ('2', 'Brasil');
INSERT INTO `torneo`.`paises` (`id`, `nombre`) VALUES ('1', 'Argentina');

INSERT INTO `torneo`.`periodo_inscripcion` (`id`, `fecha_desde`, `fecha_hasta`) VALUES ('2', '2022-02-15', '2022-03-31');
INSERT INTO `torneo`.`periodo_inscripcion` (`id`, `fecha_desde`, `fecha_hasta`) VALUES ('1', '2022-01-25', '2022-03-30');

INSERT INTO `torneo`.`jugadores` (`id`, `usuario`, `nombre`, `apellido`, `contraseña`, `id_pais`) VALUES ('1', 'juliiian99', 'Julian', 'Villoria', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', '1');

INSERT INTO `torneo`.`dificultad` (`nombre`, `rango_min_puntaje`, `rango_max_puntaje`, `rango_min_victoria`, `rango_max_victoria`, `id_juego`) VALUES ('facil', '0', '1000', '0', '10', '1');
INSERT INTO `torneo`.`dificultad` (`nombre`, `rango_min_puntaje`, `rango_max_puntaje`, `rango_min_victoria`, `rango_max_victoria`, `id_juego`) VALUES ('normal', '0', '500', '0', '5', '2');

INSERT INTO torneos(id_juego, id_tipo, fecha_inicio, fecha_fin, intentos, cupo, ganador, monto_insc) VALUES(1,1,"2022-03-30","2022-03-31",10,10,"Vacante",1000);
INSERT INTO inscripciones(id_juego, id_tipo, id_jugador, fecha_inicio_torneo, fecha) VALUES(1,1,1,"2022-03-30", curdate());