-- MySQL dump 10.16  Distrib 10.1.26-MariaDB, for Win32 (AMD64)
--
-- Host: localhost    Database: sisobita
-- ------------------------------------------------------
-- Server version	10.1.26-MariaDB

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
-- Table structure for table `tbl_cliente`
--

DROP TABLE IF EXISTS `tbl_cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_cliente` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome_completo` varchar(80) DEFAULT NULL,
  `telefone` varchar(45) DEFAULT NULL,
  `endereco` text,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_cliente`
--

LOCK TABLES `tbl_cliente` WRITE;
/*!40000 ALTER TABLE `tbl_cliente` DISABLE KEYS */;
INSERT INTO `tbl_cliente` VALUES (1,'Romário de Sousa Carvalho Marinho','63992532920','Rua Outubro, 228, Centro'),(2,'Alexandre Hiena Silva','34125234','Rua Oliveira, 480, Felipe Camarão'),(3,'Enrico Fábio Henrique dos Santos','(84)99210-3844','Rua Silva, 404, Felipe Camarão'),(4,'Anthony Lucca das Neves','(21) 99741-9855','Rua Vinte e Sete, 246, Balneário Bambuí (Ponta Negra)');
/*!40000 ALTER TABLE `tbl_cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_os`
--

DROP TABLE IF EXISTS `tbl_os`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_os` (
  `num_os` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Se você estiver utilizando um programa de os para oficina mecânica cada vez que adicionar uma nova ordem de serviço ela será numerada automaticamente e terá um registro único.',
  `data` date DEFAULT NULL COMMENT 'Registra a data em que a ordem de serviço foi aberta e gravada. Após a ordem ser gravada ela pode ser impressa e utilizada como orçamento. Somente quando houver a autorização do cliente os serviços serão executados, então ela será ela será finalizada.',
  `hora` time(4) DEFAULT NULL COMMENT 'Tem a função de registrar em que hora foi aberta a ordem de serviço. Serve para controlar, por exemplo, o tempo que levou deste a entrada na oficina até o termino do conserto.',
  `cliente` int(11) NOT NULL,
  `funcionario` int(11) NOT NULL,
  `desc_problema` text COMMENT 'Neste campo devem ser colocados os problemas relatados pelo cliente e confirmados pela pessoa que vai fazer o serviço. Como sabemos, nem sempre o problema relatado pelo cliente é efetivamente o que está acontecendo, por isso deve ser confirmado pelo profissional especializado.',
  `desc_servico` mediumtext COMMENT 'Deverão ser registrados e descritos todos os serviços que serão executados para a solução e conserto dos defeitos apresentados no item descrição do problema. Lembre-se que este campo refere-se somente a descrição dos serviços e não as peças que serão usadas.',
  `valor_servico` decimal(10,2) DEFAULT NULL,
  `total` decimal(10,2) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`num_os`),
  UNIQUE KEY `num_os_UNIQUE` (`num_os`),
  KEY `fk_tbl_os_tbl_cliente1_idx` (`cliente`),
  KEY `fk_tbl_os_usuarios1_idx` (`funcionario`),
  CONSTRAINT `fk_tbl_os_tbl_cliente1` FOREIGN KEY (`cliente`) REFERENCES `tbl_cliente` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_os`
--

LOCK TABLES `tbl_os` WRITE;
/*!40000 ALTER TABLE `tbl_os` DISABLE KEYS */;
INSERT INTO `tbl_os` VALUES (1,'2018-07-04','02:26:59.1020',1,1,'Os freios estão com folga','Apertado',1.00,1.00,'Pendente'),(2,'2018-07-04','02:30:42.1740',2,1,'A roda dianteira está empinado','Será feito o desempinamento da roda dianteira',20.00,20.00,'Pendente'),(3,'2018-07-04','03:12:35.5330',3,2,'Bicicleta com freios desregulados','Os freios serão regulados',15.00,15.00,'Pendente'),(22,'2018-07-05','16:09:23.0410',1,1,'Troca de sela, e colocar freios e pedais novos.','É para trocar a sela, freios e pedais.',10.00,393.00,'Concluido'),(23,'2018-07-05','16:41:32.0100',4,1,'problema com a corrente.','Foi colocado uma corrente nova',2.00,111.90,'Concluido');
/*!40000 ALTER TABLE `tbl_os` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_produtos`
--

DROP TABLE IF EXISTS `tbl_produtos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_produtos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` text,
  `quantidade` int(11) DEFAULT NULL,
  `valor` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_produtos`
--

LOCK TABLES `tbl_produtos` WRITE;
/*!40000 ALTER TABLE `tbl_produtos` DISABLE KEYS */;
INSERT INTO `tbl_produtos` VALUES (1,'Selim Bontrager Kovee Comp Masculino para Ciclismo MTB Postura 3 - Azul - Bontrager',10,350.00),(2,'Pedal Xerama Nylon Refletivo com borda de Aço e Rosca 1/ 2 MTB - Xerama - Preto - Xerama',12,16.00),(3,'Sapata de Freio Calypso ZX-5 V-Brake para Shimano Deore XT e XTR 70mm - Calypso',20,17.00),(4,'Corrente Shimano Deore SLX HG-73 9 Velocidades Super Estreita 116 Elos - Shimano',10,109.90),(5,'Aro Bontrager Mustang MTB Clincher Aro 26 Assimétrico Disco 32 Furos - Preto - Bontrager',10,363.00),(7,'Capacete Bontrager Solstice para Ciclismo Masculino - Branco - Bontrager',10,249.00);
/*!40000 ALTER TABLE `tbl_produtos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_produtos_has_tbl_os`
--

DROP TABLE IF EXISTS `tbl_produtos_has_tbl_os`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_produtos_has_tbl_os` (
  `tbl_produtos_id` int(11) NOT NULL,
  `tbl_os_num_os` int(11) NOT NULL,
  PRIMARY KEY (`tbl_produtos_id`,`tbl_os_num_os`),
  KEY `fk_tbl_produtos_has_tbl_os_tbl_os1_idx` (`tbl_os_num_os`),
  KEY `fk_tbl_produtos_has_tbl_os_tbl_produtos1_idx` (`tbl_produtos_id`),
  CONSTRAINT `fk_tbl_produtos_has_tbl_os_tbl_os1` FOREIGN KEY (`tbl_os_num_os`) REFERENCES `tbl_os` (`num_os`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tbl_produtos_has_tbl_os_tbl_produtos1` FOREIGN KEY (`tbl_produtos_id`) REFERENCES `tbl_produtos` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_produtos_has_tbl_os`
--

LOCK TABLES `tbl_produtos_has_tbl_os` WRITE;
/*!40000 ALTER TABLE `tbl_produtos_has_tbl_os` DISABLE KEYS */;
INSERT INTO `tbl_produtos_has_tbl_os` VALUES (1,22),(2,22),(3,22),(4,23);
/*!40000 ALTER TABLE `tbl_produtos_has_tbl_os` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) DEFAULT NULL,
  `senha` varchar(45) DEFAULT NULL,
  `sobrenome` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `telefone` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `nome_UNIQUE` (`nome`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'Wellysson','root','Rocha',NULL,NULL),(2,'Rodrigo','123','Maia',NULL,NULL),(3,'Laila Kivia','123','Kivia',NULL,NULL),(5,'Flavio','123','Manoel',NULL,NULL),(7,'Luiz Marcelo','admin','Barbosa',NULL,NULL),(8,'Renikelli','proibida',NULL,NULL,NULL),(12,'Hudson','123',NULL,NULL,NULL),(13,'Cristiano','123',NULL,NULL,NULL);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-07-08 17:49:11
