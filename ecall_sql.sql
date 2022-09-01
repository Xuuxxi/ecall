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
(1,1,'1','1',2.00,'2022-08-28 20:04:19','2022-08-28 20:04:25'),
(2,2,'2','2',3.00,'2022-08-28 20:04:44','2022-08-28 20:04:46');

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
(1,'a','1',NULL,NULL,NULL,0,NULL,NULL),
(2,'b','1',NULL,NULL,NULL,0,NULL,NULL),
(3,'c','1',NULL,NULL,NULL,0,NULL,NULL);

/*Table structure for table `user_match` */

DROP TABLE IF EXISTS `user_match`;

CREATE TABLE `user_match` (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `diary_id` bigint(20) DEFAULT NULL,
  `match_user` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `user_match` */

insert  into `user_match`(`id`,`user_id`,`diary_id`,`match_user`) values 
(1,1,2,NULL),
(2,2,3,NULL),
(3,3,1,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
