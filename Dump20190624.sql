-- MySQL dump 10.16  Distrib 10.1.14-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: lms
-- ------------------------------------------------------
-- Server version	10.1.14-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Dumping data for table `account_data`
--

LOCK TABLES `account_data` WRITE;
/*!40000 ALTER TABLE `account_data` DISABLE KEYS */;
INSERT INTO `account_data` VALUES (1,'admin@email_1','{bcrypt}$2a$10$u5nBomxHtyUvodCcewSIgOrKtcW5wTzOZng1yOLDVzkHC5kaUNFeC','admin_1'),(2,'admin@email_2','{bcrypt}$2a$10$u5nBomxHtyUvodCcewSIgOrKtcW5wTzOZng1yOLDVzkHC5kaUNFeC','admin_2'),(3,'admin@email_3','{bcrypt}$2a$10$u5nBomxHtyUvodCcewSIgOrKtcW5wTzOZng1yOLDVzkHC5kaUNFeC','admin_3'),(4,'admin@email_4','{bcrypt}$2a$10$u5nBomxHtyUvodCcewSIgOrKtcW5wTzOZng1yOLDVzkHC5kaUNFeC','admin_4'),(5,'admin@email_5','{bcrypt}$2a$10$u5nBomxHtyUvodCcewSIgOrKtcW5wTzOZng1yOLDVzkHC5kaUNFeC','admin_5'),(6,'admin@email_6','{bcrypt}$2a$10$u5nBomxHtyUvodCcewSIgOrKtcW5wTzOZng1yOLDVzkHC5kaUNFeC','admin_6'),(7,'admin@email_7','{bcrypt}$2a$10$u5nBomxHtyUvodCcewSIgOrKtcW5wTzOZng1yOLDVzkHC5kaUNFeC','admin_7'),(8,'admin@email_8','{bcrypt}$2a$10$u5nBomxHtyUvodCcewSIgOrKtcW5wTzOZng1yOLDVzkHC5kaUNFeC','admin_8'),(9,'admin@email_9','{bcrypt}$2a$10$u5nBomxHtyUvodCcewSIgOrKtcW5wTzOZng1yOLDVzkHC5kaUNFeC','admin_9'),(10,'admin@email_10','{bcrypt}$2a$10$u5nBomxHtyUvodCcewSIgOrKtcW5wTzOZng1yOLDVzkHC5kaUNFeC','admin_10'),(11,'staff@email_1','{bcrypt}$2a$10$u5nBomxHtyUvodCcewSIgOrKtcW5wTzOZng1yOLDVzkHC5kaUNFeC','staff_1'),(12,'staff@email_2','{bcrypt}$2a$10$u5nBomxHtyUvodCcewSIgOrKtcW5wTzOZng1yOLDVzkHC5kaUNFeC','staff_2'),(13,'staff@email_3','{bcrypt}$2a$10$u5nBomxHtyUvodCcewSIgOrKtcW5wTzOZng1yOLDVzkHC5kaUNFeC','staff_3'),(14,'staff@email_4','{bcrypt}$2a$10$u5nBomxHtyUvodCcewSIgOrKtcW5wTzOZng1yOLDVzkHC5kaUNFeC','staff_4'),(15,'staff@email_5','{bcrypt}$2a$10$u5nBomxHtyUvodCcewSIgOrKtcW5wTzOZng1yOLDVzkHC5kaUNFeC','staff_5'),(16,'staff@email_6','{bcrypt}$2a$10$u5nBomxHtyUvodCcewSIgOrKtcW5wTzOZng1yOLDVzkHC5kaUNFeC','staff_6'),(17,'staff@email_7','{bcrypt}$2a$10$u5nBomxHtyUvodCcewSIgOrKtcW5wTzOZng1yOLDVzkHC5kaUNFeC','staff_7'),(18,'staff@email_8','{bcrypt}$2a$10$u5nBomxHtyUvodCcewSIgOrKtcW5wTzOZng1yOLDVzkHC5kaUNFeC','staff_8'),(19,'staff@email_9','{bcrypt}$2a$10$u5nBomxHtyUvodCcewSIgOrKtcW5wTzOZng1yOLDVzkHC5kaUNFeC','staff_9'),(20,'staff@email_10','{bcrypt}$2a$10$u5nBomxHtyUvodCcewSIgOrKtcW5wTzOZng1yOLDVzkHC5kaUNFeC','staff_10'),(21,'teacher@email_1','{bcrypt}$2a$10$u5nBomxHtyUvodCcewSIgOrKtcW5wTzOZng1yOLDVzkHC5kaUNFeC','teacher_1'),(22,'teacher@email_2','{bcrypt}$2a$10$u5nBomxHtyUvodCcewSIgOrKtcW5wTzOZng1yOLDVzkHC5kaUNFeC','teacher_2'),(23,'teacher@email_3','{bcrypt}$2a$10$u5nBomxHtyUvodCcewSIgOrKtcW5wTzOZng1yOLDVzkHC5kaUNFeC','teacher_3'),(24,'teacher@email_4','{bcrypt}$2a$10$u5nBomxHtyUvodCcewSIgOrKtcW5wTzOZng1yOLDVzkHC5kaUNFeC','teacher_4'),(25,'teacher@email_5','{bcrypt}$2a$10$u5nBomxHtyUvodCcewSIgOrKtcW5wTzOZng1yOLDVzkHC5kaUNFeC','teacher_5'),(26,'teacher@email_6','{bcrypt}$2a$10$u5nBomxHtyUvodCcewSIgOrKtcW5wTzOZng1yOLDVzkHC5kaUNFeC','teacher_6'),(27,'teacher@email_7','{bcrypt}$2a$10$u5nBomxHtyUvodCcewSIgOrKtcW5wTzOZng1yOLDVzkHC5kaUNFeC','teacher_7'),(28,'teacher@email_8','{bcrypt}$2a$10$u5nBomxHtyUvodCcewSIgOrKtcW5wTzOZng1yOLDVzkHC5kaUNFeC','teacher_8'),(29,'teacher@email_9','{bcrypt}$2a$10$u5nBomxHtyUvodCcewSIgOrKtcW5wTzOZng1yOLDVzkHC5kaUNFeC','teacher_9'),(30,'teacher@email_10','{bcrypt}$2a$10$u5nBomxHtyUvodCcewSIgOrKtcW5wTzOZng1yOLDVzkHC5kaUNFeC','teacher_10'),(31,'student@email_1','{bcrypt}$2a$10$u5nBomxHtyUvodCcewSIgOrKtcW5wTzOZng1yOLDVzkHC5kaUNFeC','student_1'),(32,'student@email_2','{bcrypt}$2a$10$u5nBomxHtyUvodCcewSIgOrKtcW5wTzOZng1yOLDVzkHC5kaUNFeC','student_2'),(33,'student@email_3','{bcrypt}$2a$10$u5nBomxHtyUvodCcewSIgOrKtcW5wTzOZng1yOLDVzkHC5kaUNFeC','student_3'),(34,'student@email_4','{bcrypt}$2a$10$u5nBomxHtyUvodCcewSIgOrKtcW5wTzOZng1yOLDVzkHC5kaUNFeC','student_4'),(35,'student@email_5','{bcrypt}$2a$10$u5nBomxHtyUvodCcewSIgOrKtcW5wTzOZng1yOLDVzkHC5kaUNFeC','student_5'),(36,'student@email_6','{bcrypt}$2a$10$u5nBomxHtyUvodCcewSIgOrKtcW5wTzOZng1yOLDVzkHC5kaUNFeC','student_6'),(37,'student@email_7','{bcrypt}$2a$10$u5nBomxHtyUvodCcewSIgOrKtcW5wTzOZng1yOLDVzkHC5kaUNFeC','student_7'),(38,'student@email_8','{bcrypt}$2a$10$u5nBomxHtyUvodCcewSIgOrKtcW5wTzOZng1yOLDVzkHC5kaUNFeC','student_8'),(39,'student@email_9','{bcrypt}$2a$10$u5nBomxHtyUvodCcewSIgOrKtcW5wTzOZng1yOLDVzkHC5kaUNFeC','student_9'),(40,'student@email_10','{bcrypt}$2a$10$u5nBomxHtyUvodCcewSIgOrKtcW5wTzOZng1yOLDVzkHC5kaUNFeC','student_10');
/*!40000 ALTER TABLE `account_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `account_data_permission`
--

LOCK TABLES `account_data_permission` WRITE;
/*!40000 ALTER TABLE `account_data_permission` DISABLE KEYS */;
INSERT INTO `account_data_permission` VALUES (1,1,1),(2,2,1),(3,3,1),(4,4,1),(5,5,1),(6,6,1),(7,7,1),(8,8,1),(9,9,1),(10,10,1),(11,11,2),(12,12,2),(13,13,2),(14,14,2),(15,15,2),(16,16,2),(17,17,2),(18,18,2),(19,19,2),(20,20,2),(21,21,3),(22,22,3),(23,23,3),(24,24,3),(25,25,3),(26,26,3),(27,27,3),(28,28,3),(29,29,3),(30,30,3),(31,31,4),(32,32,4),(33,33,4),(34,34,4),(35,35,4),(36,36,4),(37,37,4),(38,38,4),(39,39,4),(40,40,4);
/*!40000 ALTER TABLE `account_data_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,'Address_number_1','Address_street_1',1),(2,'Address_number_2','Address_street_2',5),(3,'Address_number_3','Address_street_3',9),(4,'Address_number_4','Address_street_4',4),(5,'Address_number_5','Address_street_5',4),(6,'Address_number_6','Address_street_6',9),(7,'Address_number_7','Address_street_7',9),(8,'Address_number_8','Address_street_8',1),(9,'Address_number_9','Address_street_9',2),(10,'Address_number_10','Address_street_10',9);
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `administrative_staff`
--

LOCK TABLES `administrative_staff` WRITE;
/*!40000 ALTER TABLE `administrative_staff` DISABLE KEYS */;
INSERT INTO `administrative_staff` VALUES (1,'\0',11,5,11),(2,'\0',12,8,12),(3,'\0',13,5,13),(4,'\0',14,7,14),(5,'\0',15,5,15),(6,'\0',16,5,16),(7,'\0',17,8,17),(8,'\0',18,5,18),(9,'\0',19,3,19),(10,'\0',20,6,20);
/*!40000 ALTER TABLE `administrative_staff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `administrator`
--

LOCK TABLES `administrator` WRITE;
/*!40000 ALTER TABLE `administrator` DISABLE KEYS */;
INSERT INTO `administrator` VALUES (1,'\0',1,1),(2,'\0',2,2),(3,'\0',3,3),(4,'\0',4,4),(5,'\0',5,5),(6,'\0',6,6),(7,'\0',7,7),(8,'\0',8,8),(9,'\0',9,9),(10,'\0',10,10);
/*!40000 ALTER TABLE `administrator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `author`
--

LOCK TABLES `author` WRITE;
/*!40000 ALTER TABLE `author` DISABLE KEYS */;
/*!40000 ALTER TABLE `author` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'\0','Category_title_1',1),(2,'\0','Category_title_2',1),(3,'\0','Category_title_3',1),(4,'\0','Category_title_4',1),(5,'\0','Category_title_5',1),(6,'\0','Category_title_6',1),(7,'\0','Category_title_7',1),(8,'\0','Category_title_8',1),(9,'\0','Category_title_9',1),(10,'\0','Category_title_10',1);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `category_role`
--

LOCK TABLES `category_role` WRITE;
/*!40000 ALTER TABLE `category_role` DISABLE KEYS */;
INSERT INTO `category_role` VALUES (1,'Category_Role_could_Read_1','Category_Role_could_Write_1',7,1),(2,'Category_Role_could_Read_2','Category_Role_could_Write_2',7,3),(3,'Category_Role_could_Read_3','Category_Role_could_Write_3',6,1),(4,'Category_Role_could_Read_4','Category_Role_could_Write_4',6,7),(5,'Category_Role_could_Read_5','Category_Role_could_Write_5',8,4),(6,'Category_Role_could_Read_6','Category_Role_could_Write_6',1,8),(7,'Category_Role_could_Read_7','Category_Role_could_Write_7',7,6),(8,'Category_Role_could_Read_8','Category_Role_could_Write_8',10,3),(9,'Category_Role_could_Read_9','Category_Role_could_Write_9',1,3),(10,'Category_Role_could_Read_10','Category_Role_could_Write_10',7,5);
/*!40000 ALTER TABLE `category_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `city`
--

LOCK TABLES `city` WRITE;
/*!40000 ALTER TABLE `city` DISABLE KEYS */;
INSERT INTO `city` VALUES (1,'City_name_1',1),(2,'City_name_2',2),(3,'City_name_3',7),(4,'City_name_4',8),(5,'City_name_5',3),(6,'City_name_6',3),(7,'City_name_7',9),(8,'City_name_8',5),(9,'City_name_9',6),(10,'City_name_10',1);
/*!40000 ALTER TABLE `city` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `classroom`
--

LOCK TABLES `classroom` WRITE;
/*!40000 ALTER TABLE `classroom` DISABLE KEYS */;
INSERT INTO `classroom` VALUES (1,306,'Classroom_name_1','Classroom_type_1',1),(2,844,'Classroom_name_2','Classroom_type_2',2),(3,505,'Classroom_name_3','Classroom_type_3',3),(4,78,'Classroom_name_4','Classroom_type_4',1),(5,61,'Classroom_name_5','Classroom_type_5',2),(6,438,'Classroom_name_6','Classroom_type_6',3),(7,429,'Classroom_name_7','Classroom_type_7',1),(8,352,'Classroom_name_8','Classroom_type_8',2),(9,734,'Classroom_name_9','Classroom_type_9',3),(10,19,'Classroom_name_10','Classroom_type_10',1);
/*!40000 ALTER TABLE `classroom` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `country`
--

LOCK TABLES `country` WRITE;
/*!40000 ALTER TABLE `country` DISABLE KEYS */;
INSERT INTO `country` VALUES (1,'Country_name_1'),(2,'Country_name_2'),(3,'Country_name_3'),(4,'Country_name_4'),(5,'Country_name_5'),(6,'Country_name_6'),(7,'Country_name_7'),(8,'Country_name_8'),(9,'Country_name_9'),(10,'Country_name_10');
/*!40000 ALTER TABLE `country` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `dissertation`
--

LOCK TABLES `dissertation` WRITE;
/*!40000 ALTER TABLE `dissertation` DISABLE KEYS */;
INSERT INTO `dissertation` VALUES (1,'2017-03-24','2015-11-22','Dissertation_title_1',1,8,8),(2,'2020-11-21','2015-03-25','Dissertation_title_2',9,6,4),(3,'2018-02-28','2017-06-21','Dissertation_title_3',7,8,7),(4,'2016-04-22','2020-01-21','Dissertation_title_4',6,6,5),(5,'2018-01-05','2016-06-22','Dissertation_title_5',9,9,6),(6,'2016-04-05','2019-01-22','Dissertation_title_6',5,1,8),(7,'2020-10-25','2015-03-07','Dissertation_title_7',4,10,9),(8,'2018-03-14','2019-04-14','Dissertation_title_8',9,4,2),(9,'2020-06-28','2017-04-19','Dissertation_title_9',10,8,5),(10,'2020-04-26','2019-01-07','Dissertation_title_10',9,7,3);
/*!40000 ALTER TABLE `dissertation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `dissertation_file`
--

LOCK TABLES `dissertation_file` WRITE;
/*!40000 ALTER TABLE `dissertation_file` DISABLE KEYS */;
INSERT INTO `dissertation_file` VALUES (1,'Dissertation_File_url_1'),(2,'Dissertation_File_url_2'),(3,'Dissertation_File_url_3'),(4,'Dissertation_File_url_4'),(5,'Dissertation_File_url_5'),(6,'Dissertation_File_url_6'),(7,'Dissertation_File_url_7'),(8,'Dissertation_File_url_8'),(9,'Dissertation_File_url_9'),(10,'Dissertation_File_url_10');
/*!40000 ALTER TABLE `dissertation_file` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `elective_subject_attendance`
--

LOCK TABLES `elective_subject_attendance` WRITE;
/*!40000 ALTER TABLE `elective_subject_attendance` DISABLE KEYS */;
/*!40000 ALTER TABLE `elective_subject_attendance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `email`
--

LOCK TABLES `email` WRITE;
/*!40000 ALTER TABLE `email` DISABLE KEYS */;
INSERT INTO `email` VALUES (1,'email@email_1'),(2,'email@email_2'),(3,'email@email_3'),(4,'email@email_4'),(5,'email@email_5'),(6,'email@email_6'),(7,'email@email_7'),(8,'email@email_8'),(9,'email@email_9'),(10,'email@email_10');
/*!40000 ALTER TABLE `email` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `exam`
--

LOCK TABLES `exam` WRITE;
/*!40000 ALTER TABLE `exam` DISABLE KEYS */;
INSERT INTO `exam` VALUES (1,141,'2019-01-05 00:00:00',810,'2019-04-17 00:00:00',1,2),(2,850,'2020-07-22 00:00:00',759,'2020-04-20 00:00:00',1,4),(3,482,'2018-10-12 00:00:00',782,'2018-09-16 00:00:00',1,9),(4,446,'2018-12-10 00:00:00',502,'2020-05-08 00:00:00',2,4),(5,584,'2017-11-18 00:00:00',724,'2016-04-06 00:00:00',9,5),(6,612,'2015-03-06 00:00:00',323,'2018-05-17 00:00:00',1,4),(7,218,'2019-06-28 00:00:00',492,'2016-12-21 00:00:00',5,2),(8,495,'2017-02-09 00:00:00',135,'2018-08-05 00:00:00',9,8),(9,656,'2017-02-23 00:00:00',489,'2020-09-18 00:00:00',3,2),(10,400,'2015-04-04 00:00:00',455,'2015-04-28 00:00:00',9,8);
/*!40000 ALTER TABLE `exam` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `exam_realization`
--

LOCK TABLES `exam_realization` WRITE;
/*!40000 ALTER TABLE `exam_realization` DISABLE KEYS */;
INSERT INTO `exam_realization` VALUES (1,'Exam_Realization_note_1',212,7,5),(2,'Exam_Realization_note_2',521,3,5),(3,'Exam_Realization_note_3',488,4,4),(4,'Exam_Realization_note_4',544,3,9),(5,'Exam_Realization_note_5',829,1,5),(6,'Exam_Realization_note_6',105,4,4),(7,'Exam_Realization_note_7',697,4,9),(8,'Exam_Realization_note_8',263,9,7),(9,'Exam_Realization_note_9',473,1,2),(10,'Exam_Realization_note_10',585,6,8);
/*!40000 ALTER TABLE `exam_realization` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `exam_topic`
--

LOCK TABLES `exam_topic` WRITE;
/*!40000 ALTER TABLE `exam_topic` DISABLE KEYS */;
INSERT INTO `exam_topic` VALUES (1,'Exam_Topic_description_1',2),(2,'Exam_Topic_description_2',4),(3,'Exam_Topic_description_3',1),(4,'Exam_Topic_description_4',7),(5,'Exam_Topic_description_5',8),(6,'Exam_Topic_description_6',1),(7,'Exam_Topic_description_7',8),(8,'Exam_Topic_description_8',10),(9,'Exam_Topic_description_9',6),(10,'Exam_Topic_description_10',4);
/*!40000 ALTER TABLE `exam_topic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `exam_type`
--

LOCK TABLES `exam_type` WRITE;
/*!40000 ALTER TABLE `exam_type` DISABLE KEYS */;
INSERT INTO `exam_type` VALUES (1,'Exam_Type_name_1'),(2,'Exam_Type_name_2'),(3,'Exam_Type_name_3'),(4,'Exam_Type_name_4'),(5,'Exam_Type_name_5'),(6,'Exam_Type_name_6'),(7,'Exam_Type_name_7'),(8,'Exam_Type_name_8'),(9,'Exam_Type_name_9'),(10,'Exam_Type_name_10');
/*!40000 ALTER TABLE `exam_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `faculty`
--

LOCK TABLES `faculty` WRITE;
/*!40000 ALTER TABLE `faculty` DISABLE KEYS */;
INSERT INTO `faculty` VALUES (1,'Faculty_description_1','Faculty_name_1',1,2,1),(2,'Faculty_description_2','Faculty_name_2',1,4,1),(3,'Faculty_description_3','Faculty_name_3',1,7,1);
/*!40000 ALTER TABLE `faculty` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `faculty_emails`
--

LOCK TABLES `faculty_emails` WRITE;
/*!40000 ALTER TABLE `faculty_emails` DISABLE KEYS */;
INSERT INTO `faculty_emails` VALUES (1,4,1),(2,5,2),(3,6,3),(4,4,1),(5,5,2),(6,6,3),(7,7,1),(8,8,2),(9,9,3),(10,10,1);
/*!40000 ALTER TABLE `faculty_emails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `faculty_phones`
--

LOCK TABLES `faculty_phones` WRITE;
/*!40000 ALTER TABLE `faculty_phones` DISABLE KEYS */;
INSERT INTO `faculty_phones` VALUES (1,1,4),(2,2,5),(3,3,6),(4,1,7),(5,2,8),(6,3,9),(7,1,10),(8,2,4),(9,3,5),(10,1,6);
/*!40000 ALTER TABLE `faculty_phones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `file`
--

LOCK TABLES `file` WRITE;
/*!40000 ALTER TABLE `file` DISABLE KEYS */;
INSERT INTO `file` VALUES (14,'description','teaching_materials/1/init.txt',NULL,11),(15,'description2','teaching_materials/1/init/init2.txt',NULL,12),(16,'description3','teaching_materials/1/init/init2/init3.txt',NULL,13);
/*!40000 ALTER TABLE `file` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `forum`
--

LOCK TABLES `forum` WRITE;
/*!40000 ALTER TABLE `forum` DISABLE KEYS */;
INSERT INTO `forum` VALUES (1);
/*!40000 ALTER TABLE `forum` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `forum_role`
--

LOCK TABLES `forum_role` WRITE;
/*!40000 ALTER TABLE `forum_role` DISABLE KEYS */;
INSERT INTO `forum_role` VALUES (1,'Forum_Role_title_1'),(2,'Forum_Role_title_2'),(3,'Forum_Role_title_3'),(4,'Forum_Role_title_4'),(5,'Forum_Role_title_5'),(6,'Forum_Role_title_6'),(7,'Forum_Role_title_7'),(8,'Forum_Role_title_8'),(9,'Forum_Role_title_9'),(10,'Forum_Role_title_10');
/*!40000 ALTER TABLE `forum_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `forum_topic`
--

LOCK TABLES `forum_topic` WRITE;
/*!40000 ALTER TABLE `forum_topic` DISABLE KEYS */;
INSERT INTO `forum_topic` VALUES (1,'\0','\0','Forum_Topic_title_1',7,1),(2,'\0','\0','Forum_Topic_title_2',5,6),(3,'\0','\0','Forum_Topic_title_3',10,4),(4,'\0','\0','Forum_Topic_title_4',4,8),(5,'\0','\0','Forum_Topic_title_5',9,7),(6,'\0','\0','Forum_Topic_title_6',5,1),(7,'\0','\0','Forum_Topic_title_7',6,4),(8,'\0','\0','Forum_Topic_title_8',10,8),(9,'\0','\0','Forum_Topic_title_9',5,7),(10,'\0','\0','Forum_Topic_title_10',1,9);
/*!40000 ALTER TABLE `forum_topic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `forum_user`
--

LOCK TABLES `forum_user` WRITE;
/*!40000 ALTER TABLE `forum_user` DISABLE KEYS */;
INSERT INTO `forum_user` VALUES (1,22,1),(2,13,1),(3,15,1),(4,15,1),(5,25,1),(6,19,1),(7,21,1),(8,30,1),(9,7,1),(10,4,1);
/*!40000 ALTER TABLE `forum_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `forum_user_forum_role`
--

LOCK TABLES `forum_user_forum_role` WRITE;
/*!40000 ALTER TABLE `forum_user_forum_role` DISABLE KEYS */;
INSERT INTO `forum_user_forum_role` VALUES (1,8,2),(2,5,1),(3,6,7),(4,9,2),(5,9,2),(6,6,9),(7,8,5),(8,10,6),(9,9,1),(10,9,4);
/*!40000 ALTER TABLE `forum_user_forum_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `permission`
--

LOCK TABLES `permission` WRITE;
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
INSERT INTO `permission` VALUES (2,'ROLE_ADMINISTRATIVE_STAFF'),(1,'ROLE_ADMINISTRATOR'),(4,'ROLE_STUDENT'),(3,'ROLE_TEACHER');
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `personal_data`
--

LOCK TABLES `personal_data` WRITE;
/*!40000 ALTER TABLE `personal_data` DISABLE KEYS */;
INSERT INTO `personal_data` VALUES (1,'Personal_Data_first_Name_1','Personal_Data_last_Name_1','1234567891234','images/profile_images/admin_1.png'),(2,'Personal_Data_first_Name_2','Personal_Data_last_Name_2','1234567891234','images/profile_images/admin_2.png'),(3,'Personal_Data_first_Name_3','Personal_Data_last_Name_3','1234567891234','images/profile_images/admin_3.png'),(4,'Personal_Data_first_Name_4','Personal_Data_last_Name_4','1234567891234','images/profile_images/admin_4.png'),(5,'Personal_Data_first_Name_5','Personal_Data_last_Name_5','1234567891234','images/profile_images/admin_5.png'),(6,'Personal_Data_first_Name_6','Personal_Data_last_Name_6','1234567891234','images/profile_images/admin_6.png'),(7,'Personal_Data_first_Name_7','Personal_Data_last_Name_7','1234567891234','images/profile_images/admin_7.png'),(8,'Personal_Data_first_Name_8','Personal_Data_last_Name_8','1234567891234','images/profile_images/admin_8.png'),(9,'Personal_Data_first_Name_9','Personal_Data_last_Name_9','1234567891234','images/profile_images/admin_9.png'),(10,'Personal_Data_first_Name_10','Personal_Data_last_Name_10','1234567891234','images/profile_images/admin_10.png'),(11,'Personal_Data_first_Name_11','Personal_Data_last_Name_11','1234567891234','images/profile_images/staff_1.png'),(12,'Personal_Data_first_Name_12','Personal_Data_last_Name_12','1234567891234','images/profile_images/staff_2.png'),(13,'Personal_Data_first_Name_13','Personal_Data_last_Name_13','1234567891234','images/profile_images/staff_3.png'),(14,'Personal_Data_first_Name_14','Personal_Data_last_Name_14','1234567891234','images/profile_images/staff_4.png'),(15,'Personal_Data_first_Name_15','Personal_Data_last_Name_15','1234567891234','images/profile_images/staff_5.png'),(16,'Personal_Data_first_Name_16','Personal_Data_last_Name_16','1234567891234','images/profile_images/staff_6.png'),(17,'Personal_Data_first_Name_17','Personal_Data_last_Name_17','1234567891234','images/profile_images/staff_7.png'),(18,'Personal_Data_first_Name_18','Personal_Data_last_Name_18','1234567891234','images/profile_images/staff_8.png'),(19,'Personal_Data_first_Name_19','Personal_Data_last_Name_19','1234567891234','images/profile_images/staff_9.png'),(20,'Personal_Data_first_Name_20','Personal_Data_last_Name_20','1234567891234','images/profile_images/staff_10.png'),(21,'Personal_Data_first_Name_21','Personal_Data_last_Name_21','1234567891234','images/profile_images/teacher_1.png'),(22,'Personal_Data_first_Name_22','Personal_Data_last_Name_22','1234567891234','images/profile_images/teacher_2.png'),(23,'Personal_Data_first_Name_23','Personal_Data_last_Name_23','1234567891234','images/profile_images/teacher_3.png'),(24,'Personal_Data_first_Name_24','Personal_Data_last_Name_24','1234567891234','images/profile_images/teacher_4.png'),(25,'Personal_Data_first_Name_25','Personal_Data_last_Name_25','1234567891234','images/profile_images/teacher_5.png'),(26,'Personal_Data_first_Name_26','Personal_Data_last_Name_26','1234567891234','images/profile_images/teacher_6.png'),(27,'Personal_Data_first_Name_27','Personal_Data_last_Name_27','1234567891234','images/profile_images/teacher_7.png'),(28,'Personal_Data_first_Name_28','Personal_Data_last_Name_28','1234567891234','images/profile_images/teacher_8.png'),(29,'Personal_Data_first_Name_29','Personal_Data_last_Name_29','1234567891234','images/profile_images/teacher_9.png'),(30,'Personal_Data_first_Name_30','Personal_Data_last_Name_30','1234567891234','images/profile_images/teacher_10.png'),(31,'Personal_Data_first_Name_31','Personal_Data_last_Name_31','1234567891234','images/profile_images/student_1.png'),(32,'Personal_Data_first_Name_32','Personal_Data_last_Name_32','1234567891234','images/profile_images/student_2.png'),(33,'Personal_Data_first_Name_33','Personal_Data_last_Name_33','1234567891234','images/profile_images/student_3.png'),(34,'Personal_Data_first_Name_34','Personal_Data_last_Name_34','1234567891234','images/profile_images/student_4.png'),(35,'Personal_Data_first_Name_35','Personal_Data_last_Name_35','1234567891234','images/profile_images/student_5.png'),(36,'Personal_Data_first_Name_36','Personal_Data_last_Name_36','1234567891234','images/profile_images/student_6.png'),(37,'Personal_Data_first_Name_37','Personal_Data_last_Name_37','1234567891234','images/profile_images/student_7.png'),(38,'Personal_Data_first_Name_38','Personal_Data_last_Name_38','1234567891234','images/profile_images/student_8.png'),(39,'Personal_Data_first_Name_39','Personal_Data_last_Name_39','1234567891234','images/profile_images/student_9.png'),(40,'Personal_Data_first_Name_40','Personal_Data_last_Name_40','1234567891234','images/profile_images/student_10.png');
/*!40000 ALTER TABLE `personal_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `phone`
--

LOCK TABLES `phone` WRITE;
/*!40000 ALTER TABLE `phone` DISABLE KEYS */;
INSERT INTO `phone` VALUES (1,'Phone_phone_1'),(2,'Phone_phone_2'),(3,'Phone_phone_3'),(4,'Phone_phone_4'),(5,'Phone_phone_5'),(6,'Phone_phone_6'),(7,'Phone_phone_7'),(8,'Phone_phone_8'),(9,'Phone_phone_9'),(10,'Phone_phone_10');
/*!40000 ALTER TABLE `phone` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `post`
--

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
INSERT INTO `post` VALUES (1,'Post_content_1','\0','2015-08-22 00:00:00',1,1),(2,'Post_content_2','\0','2017-03-09 00:00:00',5,3),(3,'Post_content_3','\0','2019-07-21 00:00:00',1,8),(4,'Post_content_4','\0','2019-02-19 00:00:00',3,4),(5,'Post_content_5','\0','2020-01-24 00:00:00',3,3),(6,'Post_content_6','\0','2019-08-07 00:00:00',9,10),(7,'Post_content_7','\0','2019-07-05 00:00:00',9,3),(8,'Post_content_8','\0','2015-03-23 00:00:00',1,8),(9,'Post_content_9','\0','2018-07-06 00:00:00',4,6),(10,'Post_content_10','\0','2016-05-12 00:00:00',10,4);
/*!40000 ALTER TABLE `post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `post_file`
--

LOCK TABLES `post_file` WRITE;
/*!40000 ALTER TABLE `post_file` DISABLE KEYS */;
INSERT INTO `post_file` VALUES (1,'Post_File_description_1','Post_File_url_1',1),(2,'Post_File_description_2','Post_File_url_2',6),(3,'Post_File_description_3','Post_File_url_3',6),(4,'Post_File_description_4','Post_File_url_4',3),(5,'Post_File_description_5','Post_File_url_5',5),(6,'Post_File_description_6','Post_File_url_6',5),(7,'Post_File_description_7','Post_File_url_7',3),(8,'Post_File_description_8','Post_File_url_8',4),(9,'Post_File_description_9','Post_File_url_9',8),(10,'Post_File_description_10','Post_File_url_10',4);
/*!40000 ALTER TABLE `post_file` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `prerequisite`
--

LOCK TABLES `prerequisite` WRITE;
/*!40000 ALTER TABLE `prerequisite` DISABLE KEYS */;
/*!40000 ALTER TABLE `prerequisite` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `scientific_field`
--

LOCK TABLES `scientific_field` WRITE;
/*!40000 ALTER TABLE `scientific_field` DISABLE KEYS */;
INSERT INTO `scientific_field` VALUES (1,'Scientific_Field_name_1'),(2,'Scientific_Field_name_2'),(3,'Scientific_Field_name_3'),(4,'Scientific_Field_name_4'),(5,'Scientific_Field_name_5'),(6,'Scientific_Field_name_6'),(7,'Scientific_Field_name_7'),(8,'Scientific_Field_name_8'),(9,'Scientific_Field_name_9'),(10,'Scientific_Field_name_10');
/*!40000 ALTER TABLE `scientific_field` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (1,'\0',31,4,31),(2,'\0',32,9,32),(3,'\0',33,1,33),(4,'\0',34,8,34),(5,'\0',35,10,35),(6,'\0',36,2,36),(7,'\0',37,1,37),(8,'\0',38,6,38),(9,'\0',39,6,39),(10,'\0',40,9,40);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `student_year`
--

LOCK TABLES `student_year` WRITE;
/*!40000 ALTER TABLE `student_year` DISABLE KEYS */;
INSERT INTO `student_year` VALUES (1,'2017-05-27 00:00:00','Student_Year_num_Index_1',1,1),(2,'2016-04-05 00:00:00','Student_Year_num_Index_2',2,1),(3,'2019-04-24 00:00:00','Student_Year_num_Index_3',3,1),(4,'2016-03-15 00:00:00','Student_Year_num_Index_4',4,2),(5,'2020-02-02 00:00:00','Student_Year_num_Index_5',5,3),(6,'2017-08-03 00:00:00','Student_Year_num_Index_6',6,4),(7,'2017-03-16 00:00:00','Student_Year_num_Index_7',7,5),(8,'2017-11-02 00:00:00','Student_Year_num_Index_8',8,6),(9,'2018-03-17 00:00:00','Student_Year_num_Index_9',9,7),(10,'2017-09-23 00:00:00','Student_Year_num_Index_10',10,8);
/*!40000 ALTER TABLE `student_year` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `study_program`
--

LOCK TABLES `study_program` WRITE;
/*!40000 ALTER TABLE `study_program` DISABLE KEYS */;
INSERT INTO `study_program` VALUES (1,'\0','Study_Program_description_1','Study_Program_name_1',1,1),(2,'\0','Study_Program_description_2','Study_Program_name_2',2,2),(3,'\0','Study_Program_description_3','Study_Program_name_3',3,3);
/*!40000 ALTER TABLE `study_program` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `subject`
--

LOCK TABLES `subject` WRITE;
/*!40000 ALTER TABLE `subject` DISABLE KEYS */;
INSERT INTO `subject` VALUES (1,'\0',204,363,322,'','Subject_name_1',745,282,459,1),(2,'\0',745,430,575,'','Subject_name_2',275,236,103,1),(3,'\0',500,580,2,'','Subject_name_3',789,871,855,2),(4,'\0',500,333,226,'','Subject_name_4',168,697,494,2),(5,'\0',179,677,743,'','Subject_name_5',420,778,20,5),(6,'\0',720,969,415,'','Subject_name_6',960,497,790,5),(7,'\0',302,644,84,'','Subject_name_7',285,805,529,6),(8,'\0',648,69,896,'','Subject_name_8',410,153,242,6),(9,'\0',547,288,741,'','Subject_name_9',441,624,820,9),(10,'\0',607,534,792,'','Subject_name_10',209,600,915,9);
/*!40000 ALTER TABLE `subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `subject_attendance`
--

LOCK TABLES `subject_attendance` WRITE;
/*!40000 ALTER TABLE `subject_attendance` DISABLE KEYS */;
INSERT INTO `subject_attendance` VALUES (1,10,1,1),(2,10,1,2),(3,10,3,3),(4,10,4,4),(5,10,5,5),(6,10,6,6),(7,10,7,7),(8,10,8,8),(9,10,9,9),(10,10,10,10);
/*!40000 ALTER TABLE `subject_attendance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `subject_prerequisites`
--

LOCK TABLES `subject_prerequisites` WRITE;
/*!40000 ALTER TABLE `subject_prerequisites` DISABLE KEYS */;
/*!40000 ALTER TABLE `subject_prerequisites` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `subject_realization`
--

LOCK TABLES `subject_realization` WRITE;
/*!40000 ALTER TABLE `subject_realization` DISABLE KEYS */;
INSERT INTO `subject_realization` VALUES (1,1,1),(2,2,1),(3,3,2),(4,4,2),(5,5,5),(6,6,5),(7,7,6),(8,8,6),(9,9,9),(10,10,9);
/*!40000 ALTER TABLE `subject_realization` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `teacher`
--

LOCK TABLES `teacher` WRITE;
/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;
INSERT INTO `teacher` VALUES (1,'Teacher_biography_1','\0',21,1,21),(2,'Teacher_biography_2','\0',22,8,22),(3,'Teacher_biography_3','\0',23,4,23),(4,'Teacher_biography_4','\0',24,2,24),(5,'Teacher_biography_5','\0',25,10,25),(6,'Teacher_biography_6','\0',26,10,26),(7,'Teacher_biography_7','\0',27,8,27),(8,'Teacher_biography_8','\0',28,4,28),(9,'Teacher_biography_9','\0',29,5,29),(10,'Teacher_biography_10','\0',30,3,30);
/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `teacher_realization`
--

LOCK TABLES `teacher_realization` WRITE;
/*!40000 ALTER TABLE `teacher_realization` DISABLE KEYS */;
INSERT INTO `teacher_realization` VALUES (1,425,1,1,1),(2,407,2,1,1),(3,313,3,2,2),(4,659,4,3,2),(5,941,5,4,2),(6,679,6,5,1),(7,745,7,6,1),(8,669,8,7,2),(9,95,9,7,2),(10,856,10,8,1);
/*!40000 ALTER TABLE `teacher_realization` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `teaching_material`
--

LOCK TABLES `teaching_material` WRITE;
/*!40000 ALTER TABLE `teaching_material` DISABLE KEYS */;
INSERT INTO `teaching_material` VALUES (11,'material','2012-12-12 00:00:00',1),(12,'material2','2011-12-12 00:00:00',1),(13,'material3','2010-12-12 00:00:00',1);
/*!40000 ALTER TABLE `teaching_material` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `teaching_term`
--

LOCK TABLES `teaching_term` WRITE;
/*!40000 ALTER TABLE `teaching_term` DISABLE KEYS */;
/*!40000 ALTER TABLE `teaching_term` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `teaching_type`
--

LOCK TABLES `teaching_type` WRITE;
/*!40000 ALTER TABLE `teaching_type` DISABLE KEYS */;
INSERT INTO `teaching_type` VALUES (1,'lectures'),(2,'exercises');
/*!40000 ALTER TABLE `teaching_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `title`
--

LOCK TABLES `title` WRITE;
/*!40000 ALTER TABLE `title` DISABLE KEYS */;
INSERT INTO `title` VALUES (1,'2015-08-18 00:00:00','2019-02-16 00:00:00',1,9),(2,'2017-09-03 00:00:00','2017-08-15 00:00:00',8,4),(3,'2018-05-22 00:00:00','2020-03-17 00:00:00',3,1),(4,'2017-07-12 00:00:00','2016-06-23 00:00:00',6,3),(5,'2018-07-17 00:00:00','2020-07-13 00:00:00',2,9),(6,'2020-12-28 00:00:00','2015-06-04 00:00:00',9,3),(7,'2020-08-28 00:00:00','2018-01-03 00:00:00',10,9),(8,'2019-09-28 00:00:00','2018-06-20 00:00:00',2,6),(9,'2017-07-09 00:00:00','2018-03-17 00:00:00',3,6),(10,'2019-07-02 00:00:00','2016-06-12 00:00:00',1,4);
/*!40000 ALTER TABLE `title` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `title_type`
--

LOCK TABLES `title_type` WRITE;
/*!40000 ALTER TABLE `title_type` DISABLE KEYS */;
INSERT INTO `title_type` VALUES (1,'Title_Type_name_1'),(2,'Title_Type_name_2'),(3,'Title_Type_name_3'),(4,'Title_Type_name_4'),(5,'Title_Type_name_5');
/*!40000 ALTER TABLE `title_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `topic`
--

LOCK TABLES `topic` WRITE;
/*!40000 ALTER TABLE `topic` DISABLE KEYS */;
/*!40000 ALTER TABLE `topic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `university`
--

LOCK TABLES `university` WRITE;
/*!40000 ALTER TABLE `university` DISABLE KEYS */;
INSERT INTO `university` VALUES (1,'2017-12-22 00:00:00','University_description_1','University_name_1',1,1);
/*!40000 ALTER TABLE `university` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `university_emails`
--

LOCK TABLES `university_emails` WRITE;
/*!40000 ALTER TABLE `university_emails` DISABLE KEYS */;
INSERT INTO `university_emails` VALUES (1,1,1),(2,2,1),(3,3,1);
/*!40000 ALTER TABLE `university_emails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `university_phones`
--

LOCK TABLES `university_phones` WRITE;
/*!40000 ALTER TABLE `university_phones` DISABLE KEYS */;
INSERT INTO `university_phones` VALUES (1,1,1),(2,2,1),(3,3,1);
/*!40000 ALTER TABLE `university_phones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `year_of_study`
--

LOCK TABLES `year_of_study` WRITE;
/*!40000 ALTER TABLE `year_of_study` DISABLE KEYS */;
INSERT INTO `year_of_study` VALUES (1,'2018-01-09','2016-01-03',1,1),(2,'2016-07-01','2020-05-07',2,1),(3,'2016-09-20','2020-08-25',3,1),(4,'2016-02-28','2017-01-13',4,1),(5,'2018-02-23','2017-08-26',1,2),(6,'2020-06-04','2015-11-07',2,2),(7,'2020-11-20','2017-01-16',3,2),(8,'2016-12-01','2017-07-11',4,2),(9,'2018-02-19','2016-10-06',1,3),(10,'2017-03-01','2017-07-25',2,3);
/*!40000 ALTER TABLE `year_of_study` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-06-24 22:09:39
