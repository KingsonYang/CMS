/*
SQLyog Ultimate v12.3.1 (64 bit)
MySQL - 5.6.20 : Database - cms
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`cms` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `cms`;

/*Table structure for table `class_info` */

DROP TABLE IF EXISTS `class_info`;

CREATE TABLE `class_info` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `School_Name` varchar(20) NOT NULL,
  `Dept_Name` varchar(20) NOT NULL,
  `Class_Name` varchar(20) DEFAULT NULL,
  `Class_ShortName` varchar(20) DEFAULT NULL,
  `Create_Time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `Update_Time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='班级信息表';

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
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

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
(16,'社会工程学','从入门到放弃',3.0,2),
(17,'密码学','从入门到放弃',3.0,2),
(18,'密码学上册','不会啊',3.0,2),
(19,'密码学下册','从入门到放弃',3.0,2),
(22,'密码学中册','学不会丫',3.0,2);

/*Table structure for table `resource` */

DROP TABLE IF EXISTS `resource`;

CREATE TABLE `resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `type` varchar(50) DEFAULT NULL COMMENT '资源类型',
  `url` varchar(200) DEFAULT NULL COMMENT '链接地址',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父编号',
  `parent_ids` varchar(100) DEFAULT NULL COMMENT '父编号列表',
  `permission` varchar(100) DEFAULT NULL COMMENT '权限字符串',
  `available` tinyint(1) DEFAULT '0' COMMENT '是否有效',
  `icon` varchar(100) DEFAULT NULL COMMENT '图标',
  `priority` int(11) DEFAULT NULL COMMENT '优先级',
  `leaf` tinyint(1) DEFAULT '0' COMMENT '叶子节点',
  PRIMARY KEY (`id`),
  KEY `idx_sys_resource_parent_id` (`parent_id`),
  KEY `idx_sys_resource_parent_ids` (`parent_ids`)
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=utf8 COMMENT='资源表';

/*Data for the table `resource` */

insert  into `resource`(`id`,`name`,`type`,`url`,`parent_id`,`parent_ids`,`permission`,`available`,`icon`,`priority`,`leaf`) values 
(1,'资源','MENU','#',0,'0/','',1,'',0,0),
(11,'课程管理','MENU','#',1,'0/1/','courseinfo:*',1,'fa fa-sitemap',3,0),
(12,'课程新增','BUTTON','',11,'0/1/11/','courseinfo:create',1,NULL,NULL,0),
(13,'课程修改','BUTTON','',11,'0/1/11/','courseinfo:update',1,NULL,NULL,0),
(14,'课程删除','BUTTON','',11,'0/1/11/','courseinfo:delete',1,NULL,NULL,0),
(15,'课程查看','BUTTON','',11,'0/1/11/','courseinfo:view',1,NULL,NULL,0),
(21,'用户管理','MENU','#',1,'0/1/','',1,'fa fa-user',1,0),
(22,'用户新增','BUTTON','',46,'0/1/21/','user:create',1,NULL,NULL,0),
(23,'用户修改','BUTTON','',46,'0/1/21/','user:update',1,'',NULL,0),
(24,'用户删除','BUTTON','',46,'0/1/21/','user:delete',1,NULL,NULL,0),
(25,'用户查看','BUTTON','',46,'0/1/21/','user:view',1,NULL,NULL,0),
(31,'资源管理','MENU','#',1,'0/1/','resource:*',1,'fa fa-desktop',2,0),
(32,'资源新增','BUTTON','',31,'0/1/31/','resource:create',1,NULL,NULL,0),
(33,'资源修改','BUTTON','',31,'0/1/31/','resource:update',1,'',NULL,0),
(34,'资源删除','BUTTON','',31,'0/1/31/','resource:delete',1,NULL,NULL,0),
(35,'资源查看','BUTTON','',31,'0/1/31/','resource:view',1,NULL,NULL,0),
(41,'角色管理','MENU','#role',21,'0/1/','role:*',1,'fa fa-child',2,1),
(42,'角色新增','BUTTON','',41,'0/1/41/','role:create',1,NULL,NULL,0),
(43,'角色修改','BUTTON','',41,'0/1/41/','role:update',1,NULL,NULL,0),
(44,'角色删除','BUTTON','',41,'0/1/41/','role:delete',1,NULL,NULL,0),
(45,'角色查看','BUTTON','',41,'0/1/41/','role:view',1,NULL,NULL,0),
(46,'系统用户','MENU','#user',21,'0/1/11/','user:*',1,'fa fa-wrench',1,1),
(47,'课程','MENU','#courseinfo',11,'0/1/11/','courseinfo:*',1,'fa fa-suitcase',NULL,1),
(50,'资源管理','MENU','#resource',31,'0/1/','resource:*',1,'fa fa-cubes',NULL,1),
(69,'系统管理','MENU','#',1,'0/1/','system:*',1,'fa fa-wrench',4,0),
(70,'系统日志','MENU','#log',69,'0/1/69/','log:*',1,'fa fa-history',NULL,1),
(73,'一级菜单','MENU','#',1,'0/1/','',1,'',999,0),
(83,'用户组管理','MENU','#group',21,'0/1/21/','group:*',1,'fa fa-users',3,0),
(84,'用户组新增','BUTTON','',83,'0/1/21/83/','group:create',1,'',NULL,0),
(85,'用户组修改','BUTTON','',83,'0/1/21/83/','group:update',1,'',NULL,0),
(86,'用户组删除','BUTTON','',83,'0/1/21/83/','group:delete',1,'',NULL,0),
(87,'用户组查看','BUTTON','#',83,'0/1/21/83/','group:view',1,'',NULL,0),
(88,'百度','MENU','https://www.baidu.com',73,'0/1/73/','',1,'',NULL,0);

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `Role` varchar(20) DEFAULT NULL,
  `Description` varchar(255) DEFAULT NULL,
  `Resource_ids` varchar(100) DEFAULT NULL COMMENT '资源序号',
  `available` tinyint(1) DEFAULT NULL COMMENT '是否有效',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `role` */

insert  into `role`(`ID`,`Role`,`Description`,`Resource_ids`,`available`) values 
(1,'spuer_admin','超级管理员','11',0),
(2,'admin','管理员','11,21,31,41,46,69,70,83',0),
(3,'teacher','教职工',NULL,NULL),
(4,'student','学生',NULL,NULL),
(5,'tourist','游客',NULL,NULL);

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
  CONSTRAINT `所在班级` FOREIGN KEY (`Class_ID`) REFERENCES `class_info` (`ID`)
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
  `Role_IDs` varchar(100) DEFAULT '3',
  `Create_Time` varchar(20) DEFAULT NULL,
  `Update_Time` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `Role_ID` (`Role_IDs`)
) ENGINE=InnoDB AUTO_INCREMENT=20180110 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`ID`,`Name`,`Password`,`Sex`,`Age`,`PhoneNo`,`Email`,`Role_IDs`,`Create_Time`,`Update_Time`) values 
(1,'admin','202cb962ac59075b964b07152d234b70','男',18,'15151936139','1311399510@qq.com','2','2018/12/28 16:47','2019-01-29 19:47:55'),
(180101,'杨大仙','202cb962ac59075b964b07152d234b70','男',20,NULL,NULL,'3',NULL,NULL),
(20180101,'乔璐','81dc9bdb52d04dc20036dbd8313ed055','女',18,NULL,NULL,'4','2018-09-13 12:00:00','2019-04-16 13:37:10'),
(20180102,'陶哲浩','f5bb0c8de146c67b44babbf4e6584cc0',NULL,NULL,NULL,NULL,'4',NULL,NULL),
(20180103,'严飞杨','f5bb0c8de146c67b44babbf4e6584cc0',NULL,NULL,NULL,NULL,'4',NULL,NULL),
(20180104,'张天昊','f5bb0c8de146c67b44babbf4e6584cc0',NULL,NULL,NULL,NULL,'4',NULL,NULL),
(20180108,'马云','200820e3227815ed1756a6b531e7e0d2','男',18,'','','4','2019-01-23 11:10:07','2019-01-23 11:10:07'),
(20180109,'张泰智','e10adc3949ba59abbe56e057f20f883e','男',18,'','','4','2019-01-30 15:05:59','2019-01-30 15:05:59');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
