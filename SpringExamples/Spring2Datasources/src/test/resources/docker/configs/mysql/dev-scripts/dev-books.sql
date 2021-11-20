CREATE DATABASE library;
GRANT ALL PRIVILEGES ON library.* TO 'reader'@'%' IDENTIFIED BY 'password';

USE `library`;

--
-- Table structure for table `stock_logiters`
--

DROP TABLE IF EXISTS `BOOK`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `BOOK` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(45) DEFAULT NULL,
  `AUTHOR` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

INSERT INTO BOOK (NAME, AUTHOR) VALUES('The Witcher Saga', 'Andrzej Sapkowski');
INSERT INTO BOOK (NAME, AUTHOR) VALUES('The Lord of the Rings', 'J. R. R. Tolkien');
INSERT INTO BOOK (NAME, AUTHOR) VALUES('A Song of Ice and Fire', 'George R. R. Martin');