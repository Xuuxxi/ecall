/*
SQLyog Trial v13.1.8 (64 bit)
MySQL - 5.7.19 : Database - ecall
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ecall` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */;

USE `ecall`;

/*Table structure for table `diary` */

DROP TABLE IF EXISTS `diary`;

CREATE TABLE `diary` (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 NOT NULL,
  `content` varchar(10000) CHARACTER SET utf8mb4 DEFAULT NULL,
  `mood` decimal(10,2) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=armscii8;

/*Data for the table `diary` */

insert  into `diary`(`id`,`user_id`,`title`,`content`,`mood`,`update_time`,`create_time`) values 
(0,123,'string','string',0.00,'2022-08-26 21:28:09','2022-08-26 21:28:09'),
(1,1563142062357716994,'1',NULL,4.00,'2022-08-20 20:27:40','2022-08-20 20:27:42'),
(2,1563142062357716994,'2',NULL,5.00,'2022-08-21 20:27:28','2022-08-21 20:27:31'),
(3,1563142062357716994,'3',NULL,6.00,'2022-08-22 20:27:17','2022-08-22 20:27:21'),
(4,1563142062357716994,'4',NULL,7.00,'2022-08-23 20:27:05','2022-08-23 20:27:08'),
(5,1563142062357716994,'5',NULL,8.00,'2022-08-24 20:26:41','2022-08-24 20:26:44'),
(6,1563142062357716994,'6',NULL,9.00,'2022-08-25 20:24:20','2022-08-25 20:24:27'),
(1557877997737607169,1557299366464430081,'test1','string',10.00,'2022-08-12 07:53:53','2022-08-12 07:53:53'),
(1557878078020780033,1557299366464430081,'test2','string',9.00,'2022-08-12 07:54:12','2022-08-12 07:54:12'),
(1557884229592670209,1557877150421090305,'test3','string',9.00,'2022-08-12 08:18:39','2022-08-12 08:18:39'),
(1563142956696576001,1563142062357716994,'string','string',10.00,'2022-08-26 20:34:57','2022-08-26 20:34:57');

/*Table structure for table `socket_data` */

DROP TABLE IF EXISTS `socket_data`;

CREATE TABLE `socket_data` (
  `origin` bigint(20) NOT NULL,
  `target` bigint(20) NOT NULL,
  `send_time` datetime DEFAULT NULL,
  `msg` varchar(255) DEFAULT NULL,
  `is_read` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `socket_data` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `online` int(11) DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=armscii8;

/*Data for the table `user` */

insert  into `user`(`id`,`username`,`password`,`phone`,`sex`,`avatar`,`online`,`create_time`,`update_time`) values 
(1563142062357716994,'test','$2a$10$bEln3oc7piHQTyxNgmWazO52NgL1/QmrH7F7unEYD7quC6GG4yEum',NULL,NULL,NULL,0,'2022-08-26 20:31:24','2022-08-26 20:31:24');

/*Table structure for table `user_match` */

DROP TABLE IF EXISTS `user_match`;

CREATE TABLE `user_match` (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `diary_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `user_match` */

insert  into `user_match`(`id`,`user_id`,`diary_id`) values 
(1,1563142062357716994,4),
(2,1563142062357716994,3),
(1563158878266134529,1,1),
(1563158948579446785,123,6);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
