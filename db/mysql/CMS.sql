/*
SQLyog Ultimate v12.3.1 (64 bit)
MySQL - 5.7.24-log : Database - CMS
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`CMS` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `CMS`;

/*Table structure for table `class_info` */

DROP TABLE IF EXISTS `class_info`;

CREATE TABLE `class_info` (
  `ID` int(11) NOT NULL,
  `School_Name` varchar(20) NOT NULL,
  `Dept_Name` varchar(20) NOT NULL,
  `Class_Name` varchar(20) DEFAULT NULL,
  `Class_ShortName` varchar(20) DEFAULT NULL,
  `Create_Time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `Update_Time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='班级信息表';

/*Data for the table `class_info` */

insert  into `class_info`(`ID`,`School_Name`,`Dept_Name`,`Class_Name`,`Class_ShortName`,`Create_Time`,`Update_Time`) values 
(1,'苏州科技大学','计算机系','软件专业1班','18软件1','2019-01-23 11:18:39','2019-01-23 11:18:39'),
(2,'苏州科技大学','计算机系','软件专业1班','18软件2','2019-01-30 10:14:43','2019-01-30 10:14:43');

/*Table structure for table `classroom_info` */

DROP TABLE IF EXISTS `classroom_info`;

CREATE TABLE `classroom_info` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Floor_No` int(11) DEFAULT NULL COMMENT '楼号',
  `Room_No` int(11) DEFAULT NULL COMMENT '房间号',
  `seat` int(4) DEFAULT NULL COMMENT '座位',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='学校楼层教室表';

/*Data for the table `classroom_info` */

insert  into `classroom_info`(`ID`,`Floor_No`,`Room_No`,`seat`) values 
(1,1,101,100);

/*Table structure for table `course` */

DROP TABLE IF EXISTS `course`;

CREATE TABLE `course` (
  `ID` int(11) NOT NULL,
  `Course_Info_ID` int(11) DEFAULT NULL,
  `Teacher_ID` int(10) DEFAULT NULL,
  `Class_ID` int(11) DEFAULT NULL,
  `Classroom_ID` int(11) DEFAULT NULL,
  `Teach_Time` int(2) DEFAULT NULL COMMENT '上课时间（PS:周一上午12节课）',
  `Begin_Date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '开课日期',
  `End_Date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '结课日期',
  `seat` int(4) DEFAULT '0' COMMENT '已选座位',
  PRIMARY KEY (`ID`),
  KEY `授课教师` (`Teacher_ID`),
  KEY `班级` (`Class_ID`),
  KEY `教室` (`Classroom_ID`),
  KEY `课程` (`Course_Info_ID`),
  CONSTRAINT `授课教师` FOREIGN KEY (`Teacher_ID`) REFERENCES `user` (`ID`),
  CONSTRAINT `教室` FOREIGN KEY (`Classroom_ID`) REFERENCES `classroom_info` (`ID`),
  CONSTRAINT `班级` FOREIGN KEY (`Class_ID`) REFERENCES `class_info` (`ID`),
  CONSTRAINT `课程` FOREIGN KEY (`Course_Info_ID`) REFERENCES `course_info` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='课程表';

/*Data for the table `course` */

insert  into `course`(`ID`,`Course_Info_ID`,`Teacher_ID`,`Class_ID`,`Classroom_ID`,`Teach_Time`,`Begin_Date`,`End_Date`,`seat`) values 
(1,1,180101,1,1,11,'2018-12-01 12:41:04','2019-01-31 12:41:08',0);

/*Table structure for table `course_info` */

DROP TABLE IF EXISTS `course_info`;

CREATE TABLE `course_info` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(20) NOT NULL,
  `Dec` varchar(255) DEFAULT NULL,
  `credit` double(3,1) NOT NULL COMMENT '学分',
  `Character` int(1) DEFAULT NULL COMMENT '课程性质(1.必修课/2.公选课)',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

/*Data for the table `course_info` */

insert  into `course_info`(`ID`,`Name`,`Dec`,`credit`,`Character`) values 
(1,'Java基础','从入门到放弃',5.0,1),
(2,'高等数学（上册）','从入门到放弃',5.0,1),
(3,'高等数学（下册）','从入门到放弃',5.0,1),
(4,'线性代数','从入门到放弃',5.0,1),
(5,'离散数学','从入门到放弃',5.0,1),
(6,'数学分析','从入门到放弃',5.0,1),
(7,'高等代数','从入门到放弃',5.0,1),
(8,'解析几何','从入门到放弃',5.0,1),
(9,'常微分方程','从入门到放弃',5.0,1),
(10,'复变函数','从入门到放弃',5.0,1),
(11,'概率论与数理统计','从入门到放弃',5.0,1),
(12,'数学建模','从入门到放弃',5.0,1),
(13,'数学教学论','从入门到放弃',5.0,1),
(14,'教育概论','从入门到放弃',5.0,1),
(15,'学习心理学','从入门到放弃',5.0,1),
(16,'古典音乐鉴赏','xxx',2.0,2);

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `ID` int(2) NOT NULL AUTO_INCREMENT,
  `Name` varchar(20) DEFAULT NULL,
  `Descript` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `role` */

insert  into `role`(`ID`,`Name`,`Descript`) values 
(1,'spuer_admin','超级管理员'),
(2,'admin','管理员'),
(3,'teacher','教职工'),
(4,'student','学生');

/*Table structure for table `score` */

DROP TABLE IF EXISTS `score`;

CREATE TABLE `score` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `Stu_ID` int(11) DEFAULT NULL COMMENT '学号',
  `Course_ID` int(11) DEFAULT NULL COMMENT '课程表ID',
  `score` double(5,2) DEFAULT NULL COMMENT '分数',
  `credit` double(3,1) DEFAULT NULL COMMENT '学分',
  PRIMARY KEY (`id`),
  KEY `stu_score` (`Stu_ID`),
  KEY `course_link` (`Course_ID`),
  CONSTRAINT `course_link` FOREIGN KEY (`Course_ID`) REFERENCES `course` (`ID`),
  CONSTRAINT `stu_score` FOREIGN KEY (`Stu_ID`) REFERENCES `student` (`Stu_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `score` */

insert  into `score`(`id`,`Stu_ID`,`Course_ID`,`score`,`credit`) values 
(1,20180101,1,100.00,5.0);

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `ID` int(11) NOT NULL,
  `Stu_ID` int(10) DEFAULT NULL,
  `Class_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `学生` (`Stu_ID`),
  KEY `学生所属班级` (`Class_ID`),
  CONSTRAINT `学生` FOREIGN KEY (`Stu_ID`) REFERENCES `user` (`ID`),
  CONSTRAINT `学生所属班级` FOREIGN KEY (`Class_ID`) REFERENCES `class_info` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学生表';

/*Data for the table `student` */

insert  into `student`(`ID`,`Stu_ID`,`Class_ID`) values 
(1,20180101,1);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `ID` int(10) NOT NULL AUTO_INCREMENT COMMENT '工号',
  `Name` varchar(20) NOT NULL COMMENT '用户名',
  `Password` varchar(255) NOT NULL,
  `Sex` char(2) DEFAULT NULL,
  `Age` int(3) DEFAULT NULL,
  `PhoneNo` varchar(13) DEFAULT NULL,
  `Email` varchar(255) DEFAULT NULL,
  `Role_ID` int(2) DEFAULT '3',
  `Create_Time` varchar(20) DEFAULT NULL,
  `Update_Time` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `Role_ID` (`Role_ID`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`Role_ID`) REFERENCES `role` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=20180110 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`ID`,`Name`,`Password`,`Sex`,`Age`,`PhoneNo`,`Email`,`Role_ID`,`Create_Time`,`Update_Time`) values 
(1,'admin','202cb962ac59075b964b07152d234b70','男',18,'15151936139','1311399510@qq.com',2,'2018/12/28 16:47','2019-01-29 19:47:55'),
(180101,'杨大仙','202cb962ac59075b964b07152d234b70','男',20,NULL,NULL,3,NULL,NULL),
(20180101,'乔璐','202cb962ac59075b964b07152d234b70','女',18,NULL,NULL,4,'2018-09-13 12:00:00',NULL),
(20180102,'陶哲浩','f5bb0c8de146c67b44babbf4e6584cc0',NULL,NULL,NULL,NULL,4,NULL,NULL),
(20180103,'严飞杨','f5bb0c8de146c67b44babbf4e6584cc0',NULL,NULL,NULL,NULL,4,NULL,NULL),
(20180104,'张天昊','f5bb0c8de146c67b44babbf4e6584cc0',NULL,NULL,NULL,NULL,4,NULL,NULL),
(20180108,'马云','200820e3227815ed1756a6b531e7e0d2','男',18,'','',4,'2019-01-23 11:10:07','2019-01-23 11:10:07'),
(20180109,'张泰智','e10adc3949ba59abbe56e057f20f883e','男',18,'','',4,'2019-01-30 15:05:59','2019-01-30 15:05:59');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
