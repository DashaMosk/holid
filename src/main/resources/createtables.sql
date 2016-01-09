USE `hthollydays`;

CREATE TABLE `holidays` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DATESET` datetime DEFAULT NULL,
  `HDATE` date DEFAULT NULL,
  `HUSER` varchar(255) DEFAULT NULL,
  `IDUSERSET` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8;

CREATE TABLE `users` (
  `iduser` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `rights` int(11) NOT NULL,
  `regdate` timestamp NULL DEFAULT NULL,
  `blockdate` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`iduser`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*ALTER TABLE `hthollydays`.`users`
CHANGE COLUMN `blockdate` `blockdate` TIMESTAMP NULL DEFAULT NULL COMMENT '';*/
