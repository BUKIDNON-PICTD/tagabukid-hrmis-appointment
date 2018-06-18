/*
SQLyog Ultimate v12.09 (64 bit)
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

/*Table structure for table `hrmis_pds` */

DROP TABLE IF EXISTS `hrmis_pds`;

CREATE TABLE `hrmis_pds` (
  `objid` varchar(50) NOT NULL,
  `entityid` varchar(50) NOT NULL,
  `name` varchar(100) NOT NULL,
  `pdsno` varchar(50) NOT NULL,
  `currentversionid` varchar(50) NOT NULL,
  `currentversionno` varchar(50) DEFAULT NULL,
  `currentstate` varchar(10) NOT NULL,
  `recordlog_datecreated` datetime NOT NULL,
  `recordlog_createdbyuserid` varchar(36) NOT NULL,
  `recordlog_createdbyuser` varchar(500) NOT NULL,
  `recordlog_dateoflastupdate` datetime NOT NULL,
  `recordlog_lastupdatedbyuserid` varchar(36) NOT NULL,
  `recordlog_lastupdatedbyuser` varchar(500) NOT NULL,
  PRIMARY KEY (`objid`),
  KEY `versionid` (`currentversionid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `hrmis_pds` */

insert  into `hrmis_pds`(`objid`,`entityid`,`name`,`pdsno`,`currentversionid`,`currentversionno`,`currentstate`,`recordlog_datecreated`,`recordlog_createdbyuserid`,`recordlog_createdbyuser`,`recordlog_dateoflastupdate`,`recordlog_lastupdatedbyuserid`,`recordlog_lastupdatedbyuser`) values ('PDL7150ec59:16411661cc1:-7fc7-118','IND-20cdb35c:164116dbb6c:-7c35','ROJAS, RALPH JIMBER NAMOCA','PDS201800038','PDV7150ec59:16411661cc1:-7fc6-118-N','V201800039','DRAFT','2018-06-18 13:46:29','USR7e4356b9:161e091fc04:-7e03','ADMIN ADMIN','2018-06-18 13:46:29','USR7e4356b9:161e091fc04:-7e03','ADMIN ADMIN');

/*Table structure for table `hrmis_pds_version` */

DROP TABLE IF EXISTS `hrmis_pds_version`;

CREATE TABLE `hrmis_pds_version` (
  `objid` varchar(50) NOT NULL,
  `state` varchar(50) NOT NULL,
  `pdsid` varchar(50) NOT NULL,
  `versionno` varchar(100) NOT NULL,
  `recordlog_datecreated` datetime NOT NULL,
  `recordlog_createdbyuserid` varchar(36) NOT NULL,
  `recordlog_createdbyuser` varchar(500) NOT NULL,
  `recordlog_dateoflastupdate` datetime NOT NULL,
  `recordlog_lastupdatedbyuserid` varchar(36) NOT NULL,
  `recordlog_lastupdatedbyuser` varchar(500) NOT NULL,
  PRIMARY KEY (`objid`),
  KEY `editid` (`pdsid`),
  CONSTRAINT `hrmis_pds_version_ibfk_1` FOREIGN KEY (`pdsid`) REFERENCES `hrmis_pds` (`objid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `hrmis_pds_version` */

insert  into `hrmis_pds_version`(`objid`,`state`,`pdsid`,`versionno`,`recordlog_datecreated`,`recordlog_createdbyuserid`,`recordlog_createdbyuser`,`recordlog_dateoflastupdate`,`recordlog_lastupdatedbyuserid`,`recordlog_lastupdatedbyuser`) values ('PDV7150ec59:16411661cc1:-7fc6-118-N','DRAFT','PDL7150ec59:16411661cc1:-7fc7-118','V201800039','2018-06-18 13:46:29','USR7e4356b9:161e091fc04:-7e03','ADMIN ADMIN','2018-06-18 13:46:29','USR7e4356b9:161e091fc04:-7e03','ADMIN ADMIN');

/*Table structure for table `hrmis_pds_version_govid` */

DROP TABLE IF EXISTS `hrmis_pds_version_govid`;

CREATE TABLE `hrmis_pds_version_govid` (
  `objid` varchar(50) NOT NULL,
  `versionid` varchar(50) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `idno` varchar(50) DEFAULT NULL,
  `dateofissuance` date DEFAULT NULL,
  `placeofissuance` text,
  `state` varchar(10) NOT NULL,
  `recordlog_datecreated` datetime NOT NULL,
  `recordlog_createdbyuserid` varchar(36) NOT NULL,
  `recordlog_createdbyuser` varchar(500) NOT NULL,
  `recordlog_dateoflastupdate` datetime NOT NULL,
  `recordlog_lastupdatedbyuserid` varchar(36) NOT NULL,
  `recordlog_lastupdatedbyuser` varchar(500) NOT NULL,
  PRIMARY KEY (`objid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `hrmis_pds_version_govid` */

insert  into `hrmis_pds_version_govid`(`objid`,`versionid`,`name`,`idno`,`dateofissuance`,`placeofissuance`,`state`,`recordlog_datecreated`,`recordlog_createdbyuserid`,`recordlog_createdbyuser`,`recordlog_dateoflastupdate`,`recordlog_lastupdatedbyuserid`,`recordlog_lastupdatedbyuser`) values ('GID7150ec59:16411661cc1:-7fae','PDV7150ec59:16411661cc1:-7fc6-118-N','PASSPORT','0987654321','2018-06-18','MALAYBALAY CITY, BUKIDNON','DRAFT','2018-06-18 13:50:38','USR7e4356b9:161e091fc04:-7e03','ADMIN ADMIN','2018-06-18 13:50:38','USR7e4356b9:161e091fc04:-7e03','ADMIN ADMIN');

/*Table structure for table `hrmis_pds_version_organizationorassociation` */

DROP TABLE IF EXISTS `hrmis_pds_version_organizationorassociation`;

CREATE TABLE `hrmis_pds_version_organizationorassociation` (
  `objid` varchar(50) NOT NULL,
  `pdseditid` varchar(50) DEFAULT NULL,
  `name` text NOT NULL,
  `description` longtext,
  `organizationorassociationcategoryid` varchar(50) NOT NULL,
  `state` varchar(10) NOT NULL,
  `recordlog_datecreated` datetime NOT NULL,
  `recordlog_createdbyuserid` varchar(36) NOT NULL,
  `recordlog_createdbyuser` varchar(500) NOT NULL,
  `recordlog_dateoflastupdate` datetime NOT NULL,
  `recordlog_lastupdatebyuserid` varchar(36) NOT NULL,
  `recordlog_lastupdatebyuser` varchar(500) NOT NULL,
  PRIMARY KEY (`objid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `hrmis_pds_version_organizationorassociation` */

/*Table structure for table `hrmis_pds_version_personalinfo` */

DROP TABLE IF EXISTS `hrmis_pds_version_personalinfo`;

CREATE TABLE `hrmis_pds_version_personalinfo` (
  `objid` varchar(50) NOT NULL,
  `versionid` varchar(50) NOT NULL,
  `person_objid` varchar(50) NOT NULL,
  `person_name` varchar(300) NOT NULL,
  `person_lastname` varchar(100) NOT NULL,
  `person_firstname` varchar(100) NOT NULL,
  `person_middlename` varchar(100) DEFAULT NULL,
  `person_nameextension` varchar(100) DEFAULT NULL,
  `person_prenametitle` varchar(100) DEFAULT NULL,
  `person_postnametitle` varchar(100) DEFAULT NULL,
  `person_birthdate` date NOT NULL,
  `person_birthplace` text,
  `residentialaddress_text` text,
  `residentialaddress` text,
  `extensionname` text,
  `birthdate` date DEFAULT NULL,
  `birthplace_text` varchar(255) DEFAULT NULL,
  `gender` text,
  `civilstatus` varchar(15) DEFAULT NULL,
  `height` double DEFAULT NULL,
  `weight` double DEFAULT NULL,
  `bloodtype` text,
  `gsisid` int(15) DEFAULT NULL,
  `pagibigid` int(15) DEFAULT NULL,
  `philhealth` int(15) DEFAULT NULL,
  `sss` int(15) DEFAULT NULL,
  `tin` int(15) DEFAULT NULL,
  `angencyemployeeno` int(15) DEFAULT NULL,
  `citizenship` varchar(255) DEFAULT NULL,
  `permanentaddress_text` varchar(255) DEFAULT NULL,
  `telno` int(11) DEFAULT NULL,
  `mobileno` int(11) DEFAULT NULL,
  `emailadd` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`objid`),
  KEY `versionid` (`versionid`),
  CONSTRAINT `hrmis_pds_version_personalinfo_ibfk_1` FOREIGN KEY (`versionid`) REFERENCES `hrmis_pds_version` (`objid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `hrmis_pds_version_personalinfo` */

insert  into `hrmis_pds_version_personalinfo`(`objid`,`versionid`,`person_objid`,`person_name`,`person_lastname`,`person_firstname`,`person_middlename`,`person_nameextension`,`person_prenametitle`,`person_postnametitle`,`person_birthdate`,`person_birthplace`,`residentialaddress_text`,`residentialaddress`,`extensionname`,`birthdate`,`birthplace_text`,`gender`,`civilstatus`,`height`,`weight`,`bloodtype`,`gsisid`,`pagibigid`,`philhealth`,`sss`,`tin`,`angencyemployeeno`,`citizenship`,`permanentaddress_text`,`telno`,`mobileno`,`emailadd`) values ('VP7150ec59:16411661cc1:-7fb1','PDV7150ec59:16411661cc1:-7fc6-118-N','IND-20cdb35c:164116dbb6c:-7c35','ROJAS, RALPH JIMBER NAMOCA','ROJAS','RALPH JIMBER','NAMOCA',NULL,NULL,NULL,'1994-03-30','TACLOBAN CITY, LEYTE','PUROK 3 \nVALENCIA CITY\nBUKIDNON','[pin:null,unitno:null,text:\"PUROK 3 \\nVALENCIA CITY\\nBUKIDNON\",city:\"VALENCIA CITY\",barangay:[name:null,objid:null],subdivision:null,municipality:\"\",province:\"BUKIDNON\",objid:\"ADDR7150ec59:16411661cc1:-7fb8\",bldgname:null,bldgno:null,type:\"nonlocal\",street:\"PUROK 3\"]',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `hrmis_pds_version_recognition` */

DROP TABLE IF EXISTS `hrmis_pds_version_recognition`;

CREATE TABLE `hrmis_pds_version_recognition` (
  `objid` varchar(50) NOT NULL,
  `pdseditid` varchar(50) DEFAULT NULL,
  `name` text NOT NULL,
  `description` longtext,
  `recognitioncategoryid` varchar(50) NOT NULL,
  `state` varchar(10) NOT NULL,
  `recordlog_datecreated` datetime NOT NULL,
  `recordlog_createdbyuserid` varchar(36) NOT NULL,
  `recordlog_createdbyuser` varchar(500) NOT NULL,
  `recordlog_dateoflastupdate` datetime NOT NULL,
  `recordlog_lastupdatebyuserid` varchar(36) NOT NULL,
  `recordlog_lastupdatebyuser` varchar(500) NOT NULL,
  PRIMARY KEY (`objid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `hrmis_pds_version_recognition` */

/*Table structure for table `hrmis_pds_version_references` */

DROP TABLE IF EXISTS `hrmis_pds_version_references`;

CREATE TABLE `hrmis_pds_version_references` (
  `objid` varchar(50) NOT NULL,
  `versionid` varchar(50) NOT NULL,
  `name` text,
  `address` text,
  `contact` varchar(100) DEFAULT NULL,
  `state` varchar(10) NOT NULL,
  `recordlog_datecreated` datetime DEFAULT NULL,
  `recordlog_createdbyuserid` varchar(36) DEFAULT NULL,
  `recordlog_createdbyuser` varchar(100) DEFAULT NULL,
  `recordlog_dateoflastupdate` datetime DEFAULT NULL,
  `recordlog_lastupdatedbyuserid` varchar(36) DEFAULT NULL,
  `recordlog_lastupdatedbyuser` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`objid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `hrmis_pds_version_references` */

/*Table structure for table `hrmis_pds_version_skill` */

DROP TABLE IF EXISTS `hrmis_pds_version_skill`;

CREATE TABLE `hrmis_pds_version_skill` (
  `objid` varchar(50) NOT NULL,
  `pdseditid` varchar(50) DEFAULT NULL,
  `name` text NOT NULL,
  `description` text,
  `state` varchar(10) NOT NULL,
  `recordlog_datecreated` datetime NOT NULL,
  `recordlog_createdbyuserid` varchar(36) NOT NULL,
  `recordlog_createdbyuser` varchar(500) NOT NULL,
  `recordlog_dateoflastupdate` datetime NOT NULL,
  `recordlog_lastupdatebyuserid` varchar(36) NOT NULL,
  `recordlog_lastupdatebyuser` varchar(500) NOT NULL,
  PRIMARY KEY (`objid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `hrmis_pds_version_skill` */

/*Table structure for table `hrmis_pds_version_training` */

DROP TABLE IF EXISTS `hrmis_pds_version_training`;

CREATE TABLE `hrmis_pds_version_training` (
  `objid` varchar(50) NOT NULL,
  `pdseditid` varchar(50) NOT NULL,
  `name` text NOT NULL,
  `from` date NOT NULL,
  `to` date NOT NULL,
  `hours` varchar(100) DEFAULT NULL,
  `trainingcategory_objid` varchar(50) NOT NULL,
  `sponsor` text,
  `state` varchar(10) NOT NULL,
  `recordlog_datecreated` datetime NOT NULL,
  `recordlog_createdbyuserid` varchar(36) NOT NULL,
  `recordlog_createdbyuser` varchar(500) NOT NULL,
  `recordlog_dateoflastupdate` datetime NOT NULL,
  `recordlog_lastupdatebyuserid` varchar(36) NOT NULL,
  `recordlog_lastupdatebyuser` varchar(500) NOT NULL,
  PRIMARY KEY (`objid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `hrmis_pds_version_training` */

/*Table structure for table `hrmis_pds_version_voluntarywork` */

DROP TABLE IF EXISTS `hrmis_pds_version_voluntarywork`;

CREATE TABLE `hrmis_pds_version_voluntarywork` (
  `objid` varchar(50) NOT NULL,
  `versionid` varchar(50) NOT NULL,
  `orgid` varchar(50) NOT NULL,
  `name` text,
  `from` date DEFAULT NULL,
  `to` date DEFAULT NULL,
  `hours` varchar(10) DEFAULT NULL,
  `natureofwork` varchar(50) DEFAULT NULL,
  `state` varchar(10) NOT NULL,
  `recordlog_datecreated` datetime NOT NULL,
  `recordlog_createdbyuserid` varchar(36) NOT NULL,
  `recordlog_createdbyuser` varchar(500) NOT NULL,
  `recordlog_dateoflastupdate` datetime NOT NULL,
  `recordlog_lastupdatebyuserid` varchar(36) NOT NULL,
  `recordlog_lastupdatebyuser` varchar(500) NOT NULL,
  PRIMARY KEY (`objid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `hrmis_pds_version_voluntarywork` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
