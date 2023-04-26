# Spring-Boot-Assignment
Assignment 1 - spring + spring data jpa + hibernate + thymleaf + mysql

Assignment 2 - spring + spring security

MYSQL Script:
Assignment 1


CREATE TABLE `departmentmstr` (
  `departmentId` int(4) NOT NULL AUTO_INCREMENT,
  `departmentName` varchar(50) NOT NULL,
  `departmentDescription` varchar(50) NOT NULL,
  `updatedBy` varchar(50) DEFAULT NULL,
  `updatedDate` timestamp NULL DEFAULT NULL,
  `createdBy` varchar(50) DEFAULT NULL,
  `createdDate` timestamp NULL DEFAULT NULL,
  `activeStatus` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`departmentId`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci


CREATE TABLE `employeemstr` (
  `employeeId` int(4) NOT NULL AUTO_INCREMENT,
  `employeeName` varchar(50) NOT NULL,
  `employeeDescription` varchar(50) NOT NULL,
  `departmentId` int(4) NOT NULL,
  `updatedBy` varchar(50) DEFAULT NULL,
  `updatedDate` timestamp NULL DEFAULT NULL,
  `createdBy` varchar(50) DEFAULT NULL,
  `createdDate` timestamp NULL DEFAULT NULL,
  `activeStatus` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`employeeId`),
  FOREIGN KEY (departmentId) REFERENCES departmentmstr(departmentId)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci


INSERT INTO `ems`.`departmentmstr` (`departmentId`, `departmentName`, `departmentDescription`, `activeStatus`) VALUES ('1', 'Development', 'Software development', 'Y');
INSERT INTO `ems`.`departmentmstr` (`departmentId`, `departmentName`, `departmentDescription`, `activeStatus`) VALUES ('2', 'Devops', 'Cloud Computing', 'Y');
INSERT INTO `ems`.`departmentmstr` (`departmentId`, `departmentName`, `departmentDescription`, `activeStatus`) VALUES ('3', 'Cyber Security', 'Software and Infrastructure Security', 'Y');


INSERT INTO `ems`.`employeemstr` (`employeeId`, `employeeName`, `departmentId`, `activeStatus`) VALUES ('100', 'Lin Dan', '1', 'Y');

