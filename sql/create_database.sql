CREATE DATABASE  IF NOT EXISTS `energynetsimdb`;

CREATE USER "NetSimUser" IDENTIFIED BY "NetSimUser";
GRANT ALL ON energynetsimdb.* TO 'NetSimUser';

USE `energynetsimdb`;

--
-- Table structure
--

DROP TABLE IF EXISTS `networks`;
CREATE TABLE `networks` (
  `networkId` int(11) NOT NULL AUTO_INCREMENT,
  `networkName` varchar(45) NOT NULL,
  PRIMARY KEY (`networkId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `settings`;
CREATE TABLE `settings` (
  `settingKey` varchar(30) NOT NULL,
  `settingValue` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`settingKey`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data
--

INSERT INTO `networks` VALUES (1,'Static Network'),(2,'Dynamic Network');

INSERT INTO `settings` VALUES ('Price(EUR)/MWh','31.21');
