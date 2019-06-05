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
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=18003 DEFAULT CHARSET=utf8 COMMENT='班级信息表';

/*Data for the table `class_info` */

insert  into `class_info`(`ID`,`School_Name`,`Dept_Name`,`Class_Name`,`Class_ShortName`) values 
(18001,'苏州科技大学','计算机科学与技术','网络管理（嵌入式）','18计网1'),
(18002,'苏州科技大学','计算机科学与技术','计算机应用','18计应1');

/*Table structure for table `classroom_info` */

DROP TABLE IF EXISTS `classroom_info`;

CREATE TABLE `classroom_info` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Floor_No` int(11) DEFAULT NULL COMMENT '楼号',
  `Room_No` int(11) DEFAULT NULL COMMENT '房间号',
  `seat` int(4) DEFAULT NULL COMMENT '座位',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='学校楼层教室表';

/*Data for the table `classroom_info` */

insert  into `classroom_info`(`ID`,`Floor_No`,`Room_No`,`seat`) values 
(1,1,101,100),
(2,1,102,50);

/*Table structure for table `course` */

DROP TABLE IF EXISTS `course`;

CREATE TABLE `course` (
  `ID` int(11) NOT NULL,
  `Course_Info_ID` int(11) DEFAULT NULL,
  `Teacher_ID` int(10) DEFAULT NULL,
  `Class_ID` int(11) DEFAULT NULL,
  `Classroom_ID` int(11) DEFAULT NULL,
  `Teach_Time` int(2) DEFAULT NULL COMMENT '上课时间（PS:周一上午12节课）',
  `Begin_Date` varchar(100) DEFAULT NULL COMMENT '开课日期',
  `End_Date` varchar(100) DEFAULT NULL COMMENT '结课日期',
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
(1,1,180101,18001,1,14,'2019-05-19','2019-05-19',50),
(2,1,180101,18002,1,13,'2019-05-19','2019-05-19',40),
(3,2,180101,18001,1,11,'2019-05-19','2019-05-19',40),
(4,3,180101,18001,2,21,'2019-05-19','2019-05-19',40),
(5,4,180101,18002,2,12,'2019-05-19','2019-05-19',45),
(6,4,180101,18001,1,31,'2019-05-19','2019-05-19',40),
(7,5,180101,18001,2,41,'2019-05-19','2019-05-19',40),
(8,10,180101,18001,2,51,'2019-05-19','2019-05-19',51),
(9,23,180101,18002,2,61,'2019-06-06','2019-06-27',40);

/*Table structure for table `course_info` */

DROP TABLE IF EXISTS `course_info`;

CREATE TABLE `course_info` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(20) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `credit` double(3,1) NOT NULL COMMENT '学分',
  `act` int(1) DEFAULT NULL COMMENT '课程性质(1.必修课/2.公选课)',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

/*Data for the table `course_info` */

insert  into `course_info`(`ID`,`Name`,`description`,`credit`,`act`) values 
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
(19,'密码学下册','锻炼逻辑思维',4.0,2),
(22,'密码学中册','学不会丫',4.0,2),
(23,'Hadoop','Hadoop入门',5.0,2);

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
) ENGINE=InnoDB AUTO_INCREMENT=93 DEFAULT CHARSET=utf8 COMMENT='资源表';

/*Data for the table `resource` */

insert  into `resource`(`id`,`name`,`type`,`url`,`parent_id`,`parent_ids`,`permission`,`available`,`icon`,`priority`,`leaf`) values 
(1,'资源','MENU','#',0,'0/','',1,'',0,0),
(11,'课程管理','MENU','#courseinfo',69,'0/1/','',1,'fa fa-sitemap',3,0),
(12,'课程新增','BUTTON','',11,'0/1/11/','courseinfo:create',1,NULL,NULL,0),
(13,'课程修改','BUTTON','',11,'0/1/11/','courseinfo:update',1,NULL,NULL,0),
(14,'课程删除','BUTTON','',11,'0/1/11/','courseinfo:delete',1,NULL,NULL,0),
(15,'课程查看','BUTTON','',11,'0/1/11/','courseinfo:view',1,NULL,NULL,0),
(16,'教室管理','MENU','#classroom',69,'0/1/','classroom:*',1,'fa fa-user',4,0),
(17,'教室新增','BUTTON','',16,'0/1/16/','classroom:create',1,NULL,NULL,0),
(18,'教室修改','BUTTON','',16,'0/1/16/','classroom:update',1,'',NULL,0),
(19,'教室删除','BUTTON','',16,'0/1/16/','classroom:delete',1,NULL,NULL,0),
(20,'教室查看','BUTTON','',16,'0/1/16/','classroom:view',1,NULL,NULL,0),
(21,'用户管理','MENU','#',1,'0/1/','',1,'fa fa-user',1,0),
(22,'班级管理','MENU','#class',69,'0/1/','class:*',1,'fa fa-sitemap',5,0),
(23,'班级新增','BUTTON','',22,'0/1/22/','class:create',1,NULL,NULL,0),
(24,'班级修改','BUTTON','',22,'0/1/22/','class:update',1,'',NULL,0),
(25,'班级删除','BUTTON','',22,'0/1/22/','class:delete',1,NULL,NULL,0),
(26,'班级查看','BUTTON','',22,'0/1/22/','class:view',1,NULL,NULL,0),
(31,'资源管理','MENU','#',1,'0/1/','resource:*',1,'fa fa-desktop',2,0),
(32,'资源新增','BUTTON','',51,'0/1/51/','resource:create',1,NULL,NULL,0),
(33,'资源修改','BUTTON','',51,'0/1/51/','resource:update',1,'',NULL,0),
(34,'资源删除','BUTTON','',51,'0/1/51/','resource:delete',1,NULL,NULL,0),
(35,'资源查看','BUTTON','',51,'0/1/51/','resource:view',1,NULL,NULL,0),
(36,'班级成员管理','MENU','#student',69,'0/1/','',1,'fa fa-user',9,0),
(37,'班级成员新增','BUTTON','',36,'0/1/36','student:create',1,NULL,NULL,0),
(38,'班级成员修改','BUTTON','',36,'0/1/36','student:update',1,'',NULL,0),
(39,'班级成员删除','BUTTON','',36,'0/1/36','student:delete',1,NULL,NULL,0),
(40,'班级成员查看','BUTTON','',36,'0/1/36','student:view',1,NULL,NULL,0),
(41,'角色管理','MENU','#role',21,'0/1/','role:*',1,'fa fa-child',2,1),
(42,'角色新增','BUTTON','',41,'0/1/41/','role:create',1,NULL,NULL,0),
(43,'角色修改','BUTTON','',41,'0/1/41/','role:update',1,NULL,NULL,0),
(44,'角色删除','BUTTON','',41,'0/1/41/','role:delete',1,NULL,NULL,0),
(45,'角色查看','BUTTON','',41,'0/1/41/','role:view',1,NULL,NULL,0),
(46,'系统用户','MENU','#user',21,'0/1/11/','user:*',1,'fa fa-wrench',1,1),
(51,'资源管理','MENU','#resource',31,'0/1/','resource:*',1,'fa fa-cubes',NULL,1),
(52,'网上选课','MENU','#',1,'0/1/','chooseClass:*',1,'fa fa-sitemap',5,0),
(53,'校选修课','MENU','#chooseClass',52,'0/1/52','chooseClass:*',1,'fa fa-user',NULL,0),
(54,'选体育课','MENU','#',52,'0/1/52',NULL,1,'fa fa-user',NULL,0),
(69,'系统管理','MENU','#',1,'0/1/','system:*',1,'fa fa-wrench',4,0),
(70,'系统日志','MENU','#log',69,'0/1/69/','log:*',1,'fa fa-history',NULL,1),
(73,'信息维护','MENU','#',1,'0/1/','secure:*',1,'fa fa-sitemap',8,0),
(74,'个人信息','MENU','#userinfo',73,'0/1/73/','userinfo:*',1,'fa fa-user',NULL,0),
(75,'修改密码','MENU','#updatePwd',73,'0/1/73/','updatePwd:*',1,'fa fa-user',NULL,0),
(78,'信息查询','MENU','#',1,'0/1/','information:*',1,'fa fa-user',9,0),
(80,'个人课表查询','MENU','#schedule',78,'0/1/78/','schedule:*',1,'fa fa-user',NULL,0),
(82,'成绩管理','MENU','#score',78,'0/1/78/','',1,'fa fa-user',NULL,0),
(83,'成绩新增','BUTTON','',82,'0/1/78/82/','score:create',1,NULL,NULL,0),
(84,'成绩修改','BUTTON','',82,'0/1/78/82/','score:update',1,'',NULL,0),
(85,'成绩删除','BUTTON','',82,'0/1/78/82/','score:delete',1,NULL,NULL,0),
(86,'成绩查看','BUTTON','',82,'0/1/78/82/','score:view',1,NULL,NULL,0),
(87,'课程表管理','MENU','#course',69,'0/1/','course:*',1,'fa fa-sitemap',10,0),
(88,'课程表新增','BUTTON','',87,'0/1/88/','course:create',1,NULL,NULL,0),
(89,'课程表修改','BUTTON','',87,'0/1/88/','course:update',1,'',NULL,0),
(90,'课程表删除','BUTTON','',87,'0/1/88/','course:delete',1,NULL,NULL,0),
(91,'课程表查看','BUTTON','',87,'0/1/88/','course:view',1,NULL,NULL,0);

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
(1,'spuer_admin','超级管理员','1,11,21,31,41,46,51,69,73,88',0),
(2,'admin','管理员','11,12,13,14,15,16,21,22,31,36,37,38,39,40,41,46,52,69,73,74,75,78,82,83,84,85,86,87',0),
(3,'teacher','教职工','15,20,26,46,52,69,73,74,75,78,79,80,83,84,85,86,88,91',NULL),
(4,'student','学生','15,20,26,52,73,74,75,78,79,80,86,91',NULL),
(5,'tourist','游客','11',NULL);

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
  CONSTRAINT `course_link` FOREIGN KEY (`Course_ID`) REFERENCES `course_info` (`ID`),
  CONSTRAINT `stu_score` FOREIGN KEY (`Stu_ID`) REFERENCES `student` (`Stu_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `score` */

insert  into `score`(`id`,`Stu_ID`,`Course_ID`,`score`,`credit`) values 
(1,20180101,1,90.00,5.0),
(2,20180102,1,59.00,0.0);

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
(1,20180101,18001),
(2,20180104,18002),
(3,20180102,18001);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `ID` int(10) NOT NULL AUTO_INCREMENT COMMENT '工号',
  `username` varchar(100) DEFAULT NULL COMMENT '用于登录的学号',
  `Name` varchar(20) NOT NULL COMMENT '用户名',
  `Password` varchar(255) NOT NULL COMMENT '密码',
  `salt` varchar(100) DEFAULT NULL COMMENT '盐值',
  `Sex` char(2) DEFAULT NULL,
  `Age` int(3) DEFAULT NULL,
  `PhoneNo` varchar(13) DEFAULT NULL,
  `Email` varchar(255) DEFAULT NULL,
  `Role_IDs` varchar(100) DEFAULT '3',
  `Create_Time` varchar(20) DEFAULT NULL,
  `Update_Time` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `Role_ID` (`Role_IDs`)
) ENGINE=InnoDB AUTO_INCREMENT=20180108 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`ID`,`username`,`Name`,`Password`,`salt`,`Sex`,`Age`,`PhoneNo`,`Email`,`Role_IDs`,`Create_Time`,`Update_Time`) values 
(100001,'admin','admin','d3c59d25033dbf980d29554025c23a75','8d78869f470951332959580424d4bf4f','男',18,'15151936139','1311399510@qq.com','2','2018/12/28 16:47','2019-01-29 19:47:55'),
(180101,'杨庆升','杨庆升','3254baadc5b1f03aab73e19bffcafe89','76ad8072f76b82004fd40bc67b9707e6','男',22,'15151936139','1311399510@qq.com','3',NULL,NULL),
(20180101,'乔璐','乔璐','b29c65d3cdff825f3d91f20d7feee633','d9301939f7a91a4afa8e633b7b26b81d','女',18,'19122984791','1311399510@qq.com','4',NULL,NULL),
(20180102,'乔煜','乔煜','040d676db610b42204aebae90638e038','bcb793bf2545a3a8a7296b8791c57971','男',15,'123123123123','1311399510@qq.com','4',NULL,NULL),
(20180104,'张天昊','张天昊','f5bb0c8de146c67b44babbf4e6584cc0',NULL,'男',22,'15151937162','1311399510@qq.com','4',NULL,NULL),
(20180107,'莫尚义','莫尚义','dbe8514a6acf92bc249ca958ce3393ec','3094abce1e044a1c1aba5af9f9704f91','男',24,'13676761126','1311399510@qq.com','2,4',NULL,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
