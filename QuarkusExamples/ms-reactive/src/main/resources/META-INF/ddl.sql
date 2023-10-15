USE `library`;

--
-- Table structure for table `BOARDGAME`
--

DROP TABLE IF EXISTS `BOARDGAME`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `BOARDGAME` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(45) DEFAULT NULL,
  `DESIGNER` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;