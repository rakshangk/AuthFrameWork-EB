Executig the following script creates the table "users", "authorities", "user_tenants", "user_details".
==============================================

DROP DATABASE  IF EXISTS `authFramework`;

CREATE DATABASE  IF NOT EXISTS `authFramework`;
USE `authFramework`;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserting data for table `users`
--

INSERT INTO `users` 
VALUES 
('Admin','{noop}5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8',1),
('Rakshan@techmust.com','{noop}5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8',1),
('kamal@techmust.com','{noop}5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8',1);


--
-- Table structure for table `authorities`
--

DROP TABLE IF EXISTS `authorities`;
CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `authorities_idx_1` (`username`,`authority`),
  CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserting data for table `authorities`
--

INSERT INTO `authorities` 
VALUES 
('Rakshan@techmust.com','ROLE_USER'),
('kamal@techmust.com','ROLE_USER'),
('kamal@techmust.com','ROLE_ADMIN');


--
-- Table structure for table `user_tenants`
--

DROP TABLE IF EXISTS `user_tenants`;
CREATE TABLE `user_tenants` (
  `username` varchar(50) NOT NULL,
  `tenant` varchar(50) NOT NULL,
  UNIQUE KEY `tenant_idx_1` (`username`,`tenant`),
  CONSTRAINT `tenant_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserting data for table `user_tenants`
--

INSERT INTO  `user_tenants`
VALUES
('kamal@techmust.com','dbtenant1');

--
-- Table structure for table `user_details`
--

DROP TABLE IF EXISTS `user_details`;
CREATE TABLE `user_details` (
  `username` varchar(64) NOT NULL,
  `user_firstname` varchar(45) DEFAULT NULL,
  `user_lastname` varchar(45) DEFAULT NULL,
  `user_phonenumber` int(20) DEFAULT NULL,
  PRIMARY KEY (`username`),
  KEY `username_idx` (`username`),
  CONSTRAINT `username` FOREIGN KEY (`username`) REFERENCES `users` (`username`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserting data for table `user_details`
--

INSERT INTO  `user_details`
VALUES
('kamal@techmust.com','Kamal', 'Nathan', 1234567890);
