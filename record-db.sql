/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 8.0.12 : Database - record-db
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`record-db` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `record-db`;

/*Table structure for table `record_message` */

DROP TABLE IF EXISTS `record_message`;

CREATE TABLE `record_message` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，自动增长',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '报告人名称',
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '报告题目',
  `time` datetime NOT NULL COMMENT '报告时间',
  `place` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '报告地点',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

/*Data for the table `record_message` */

insert  into `record_message`(`id`,`name`,`title`,`time`,`place`) values (1,'小舞','柔骨兔','1888-12-26 23:48:06','史莱克学院'),(2,'水冰儿','冰凤凰','2019-12-24 19:19:26','天水学院'),(3,'火舞','火影','2019-12-24 19:27:59','炽火学院'),(4,'邱若水','碧莲薰鲈','2019-12-25 00:00:00','天水学院'),(5,'唐三','蓝银草','2019-12-25 20:20:42','史莱克学院'),(6,'朱竹清','幽冥灵猫','2019-12-25 20:25:07','史莱克学院'),(7,'奥斯卡','香肠','2019-12-25 20:42:31','史莱克学院'),(8,'马红俊','火凤凰','2019-12-25 20:43:29','史莱克学院'),(11,'戴沐白','邪眸白虎','2019-12-26 21:59:13','史莱克学院'),(12,'水月儿','莹玉海豚','2019-12-26 23:12:39','天水学院'),(13,'沈流玉','珐琅福鳄','2019-12-26 23:14:16','天水学院'),(14,'顾清波','苍蓝旗鱼','2019-12-26 23:16:09','天水学院'),(15,'泰隆','金刚猩猩','2019-12-26 23:16:56','史莱克学院'),(16,'火无双','火爆龙','2019-12-26 23:20:08','炽火学院'),(17,'风笑天','神风','2019-12-26 23:20:25','神风学院'),(19,'陈传诚','改变世界的工程师','2048-01-02 17:20:14','吉林大学');

/*Table structure for table `record_user` */

DROP TABLE IF EXISTS `record_user`;

CREATE TABLE `record_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(50) NOT NULL COMMENT '密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `record_user` */

insert  into `record_user`(`id`,`username`,`password`) values (1,'meethigher','meethigher'),(2,'kitchen','kitchen');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
