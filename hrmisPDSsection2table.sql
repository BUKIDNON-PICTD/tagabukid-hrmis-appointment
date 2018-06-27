/*
SQLyog Community v13.0.1 (64 bit)
MySQL - 5.6.37-log : Database - tagabukid_hrmis
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`tagabukid_hrmis` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `tagabukid_hrmis`;

/*Table structure for table `hrmis_pds_govid` */

DROP TABLE IF EXISTS `hrmis_pds_govid`;

CREATE TABLE `hrmis_pds_govid` (
  `objid` varchar(50) NOT NULL,
  `pdsid` varchar(50) NOT NULL,
  `idtype` varchar(100) NOT NULL,
  `idno` varchar(50) NOT NULL,
  `issuance` text NOT NULL,
  `recordlog_datecreated` datetime NOT NULL,
  `recordlog_createdbyuserid` varchar(36) NOT NULL,
  `recordlog_createdbyuser` varchar(500) NOT NULL,
  `recordlog_dateoflastupdate` datetime NOT NULL,
  `recordlog_lastupdatedbyuserid` varchar(36) NOT NULL,
  `recordlog_lastupdatedbyuser` varchar(500) NOT NULL,
  PRIMARY KEY (`objid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `hrmis_pds_organizationorassociation` */

DROP TABLE IF EXISTS `hrmis_pds_organizationorassociation`;

CREATE TABLE `hrmis_pds_organizationorassociation` (
  `objid` varchar(50) NOT NULL,
  `pdsid` varchar(50) NOT NULL,
  `name` text NOT NULL,
  `recordlog_datecreated` datetime NOT NULL,
  `recordlog_createdbyuserid` varchar(36) NOT NULL,
  `recordlog_createdbyuser` varchar(500) NOT NULL,
  `recordlog_dateoflastupdate` datetime NOT NULL,
  `recordlog_lastupdatedbyuserid` varchar(36) NOT NULL,
  `recordlog_lastupdatedbyuser` varchar(500) NOT NULL,
  PRIMARY KEY (`objid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `hrmis_pds_recognition` */

DROP TABLE IF EXISTS `hrmis_pds_recognition`;

CREATE TABLE `hrmis_pds_recognition` (
  `objid` varchar(50) NOT NULL,
  `pdsid` varchar(50) NOT NULL,
  `name` text NOT NULL,
  `recognitioncategoryid` varchar(50) DEFAULT NULL,
  `recordlog_datecreated` datetime NOT NULL,
  `recordlog_createdbyuserid` varchar(36) NOT NULL,
  `recordlog_createdbyuser` varchar(500) NOT NULL,
  `recordlog_dateoflastupdate` datetime NOT NULL,
  `recordlog_lastupdatedbyuserid` varchar(36) NOT NULL,
  `recordlog_lastupdatedbyuser` varchar(500) NOT NULL,
  PRIMARY KEY (`objid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `hrmis_pds_reference` */

DROP TABLE IF EXISTS `hrmis_pds_reference`;

CREATE TABLE `hrmis_pds_reference` (
  `objid` varchar(50) NOT NULL,
  `pdsid` varchar(50) NOT NULL,
  `name` varchar(100) NOT NULL,
  `address` text NOT NULL,
  `contact` varchar(50) NOT NULL,
  `recordlog_datecreated` datetime NOT NULL,
  `recordlog_createdbyuserid` varchar(36) NOT NULL,
  `recordlog_createdbyuser` varchar(500) NOT NULL,
  `recordlog_dateoflastupdate` datetime NOT NULL,
  `recordlog_lastupdatedbyuserid` varchar(36) NOT NULL,
  `recordlog_lastupdatedbyuser` varchar(500) NOT NULL,
  PRIMARY KEY (`objid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `hrmis_pds_skill` */

DROP TABLE IF EXISTS `hrmis_pds_skill`;

CREATE TABLE `hrmis_pds_skill` (
  `objid` varchar(50) NOT NULL,
  `pdsid` varchar(50) NOT NULL,
  `name` text NOT NULL,
  `description` text,
  `recordlog_datecreated` datetime NOT NULL,
  `recordlog_createdbyuserid` varchar(36) NOT NULL,
  `recordlog_createdbyuser` varchar(500) NOT NULL,
  `recordlog_dateoflastupdate` datetime NOT NULL,
  `recordlog_lastupdatedbyuserid` varchar(36) NOT NULL,
  `recordlog_lastupdatedbyuser` varchar(500) NOT NULL,
  PRIMARY KEY (`objid`),
  KEY `pdsid` (`pdsid`),
  CONSTRAINT `hrmis_pds_skill_ibfk_1` FOREIGN KEY (`pdsid`) REFERENCES `hrmis_pds` (`objid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `hrmis_pds_training` */

DROP TABLE IF EXISTS `hrmis_pds_training`;

CREATE TABLE `hrmis_pds_training` (
  `objid` varchar(50) NOT NULL,
  `pdsid` varchar(50) NOT NULL,
  `name` text NOT NULL,
  `from` date NOT NULL,
  `to` date NOT NULL,
  `hours` varchar(100) DEFAULT NULL,
  `trainingcategoryid` varchar(50) NOT NULL,
  `sponsor` text,
  `recordlog_datecreated` datetime NOT NULL,
  `recordlog_createdbyuserid` varchar(36) NOT NULL,
  `recordlog_createdbyuser` varchar(500) NOT NULL,
  `recordlog_dateoflastupdate` datetime NOT NULL,
  `recordlog_lastupdatedbyuserid` varchar(36) NOT NULL,
  `recordlog_lastupdatedbyuser` varchar(500) NOT NULL,
  PRIMARY KEY (`objid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `hrmis_pds_voluntarywork` */

DROP TABLE IF EXISTS `hrmis_pds_voluntarywork`;

CREATE TABLE `hrmis_pds_voluntarywork` (
  `objid` varchar(50) NOT NULL,
  `pdsid` varchar(50) NOT NULL,
  `name` text,
  `from` date DEFAULT NULL,
  `to` date DEFAULT NULL,
  `hours` varchar(10) DEFAULT NULL,
  `natureofwork` varchar(50) DEFAULT NULL,
  `recordlog_datecreated` datetime NOT NULL,
  `recordlog_createdbyuserid` varchar(36) NOT NULL,
  `recordlog_createdbyuser` varchar(500) NOT NULL,
  `recordlog_dateoflastupdate` datetime NOT NULL,
  `recordlog_lastupdatedbyuserid` varchar(36) NOT NULL,
  `recordlog_lastupdatedbyuser` varchar(500) NOT NULL,
  PRIMARY KEY (`objid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
