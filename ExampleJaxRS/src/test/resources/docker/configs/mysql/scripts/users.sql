CREATE DATABASE almacen;
GRANT ALL PRIVILEGES ON almacen.* TO 'administrator'@'%' IDENTIFIED BY 'password';

USE `almacen`;

CREATE TABLE `users` (
  `USER_ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `USERNAME` varchar(45) DEFAULT NULL,
  `PASSWORD` varchar(100) DEFAULT NULL,
  `ENABLED` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- user:admin password:admin
insert into users (USER_ID, USERNAME, PASSWORD, ENABLED) values ('1', 'admin', '$2a$10$h.p9sfywxm02e0tU8Zlu2O5VEb6JFOUU6V5RhdVhxIvOeduMppMvS', '1');

