/*
 Navicat Premium Data Transfer

 Source Server         : CMS
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : localhost:3306
 Source Schema         : cms

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 29/12/2018 12:18:27
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for class_info
-- ----------------------------
DROP TABLE IF EXISTS `class_info`;
CREATE TABLE `class_info`  (
  `ID` int(11) NOT NULL,
  `School_Name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Dept_Name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Class_Name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Class_ShortName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Create_Time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `Update_Time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '班级信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of class_info
-- ----------------------------
INSERT INTO `class_info` VALUES (1, '苏州科技大学', '计算机系', '软件', '18软件1', '2018-09-01 12:00:00', '2018-09-01 12:00:00');

-- ----------------------------
-- Table structure for classroom_info
-- ----------------------------
DROP TABLE IF EXISTS `classroom_info`;
CREATE TABLE `classroom_info`  (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Floor_No` int(11) NULL DEFAULT NULL,
  `Room_No` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci COMMENT = '学校楼层教室表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of classroom_info
-- ----------------------------
INSERT INTO `classroom_info` VALUES (1, 1, 101);

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `ID` int(11) NOT NULL,
  `Name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Teacher_ID` int(10) NULL DEFAULT NULL,
  `Class_ID` int(11) NULL DEFAULT NULL,
  `Classroom_ID` int(11) NULL DEFAULT NULL,
  `Teach_Time` int(2) NULL DEFAULT NULL COMMENT '上课时间（PS:周一上午12节课）',
  `Begin_Date` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '开课日期',
  `End_Date` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '结课日期',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `授课教师`(`Teacher_ID`) USING BTREE,
  INDEX `班级`(`Class_ID`) USING BTREE,
  INDEX `教室`(`Classroom_ID`) USING BTREE,
  CONSTRAINT `授课教师` FOREIGN KEY (`Teacher_ID`) REFERENCES `user` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `教室` FOREIGN KEY (`Classroom_ID`) REFERENCES `classroom_info` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `班级` FOREIGN KEY (`Class_ID`) REFERENCES `class_info` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '课程表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES (1, 'Java基础课', 180101, 1, 1, 11, '2018-09-17 00:00:26', '2018-12-30 12:00:00');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `ID` int(2) NOT NULL AUTO_INCREMENT,
  `Name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Descript` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, 'spuer_admin', '超级管理员');
INSERT INTO `role` VALUES (2, 'admin', '管理员');
INSERT INTO `role` VALUES (3, 'teacher', '教职工');
INSERT INTO `role` VALUES (4, 'student', '学生');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `ID` int(11) NOT NULL,
  `Stu_ID` int(10) NULL DEFAULT NULL,
  `Class_ID` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `学生`(`Stu_ID`) USING BTREE,
  INDEX `学生所属班级`(`Class_ID`) USING BTREE,
  CONSTRAINT `学生` FOREIGN KEY (`Stu_ID`) REFERENCES `user` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `学生所属班级` FOREIGN KEY (`Class_ID`) REFERENCES `class_info` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci COMMENT = '学生表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `ID` int(10) NOT NULL AUTO_INCREMENT COMMENT '工号',
  `Name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `Password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Sex` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Age` int(3) NULL DEFAULT NULL,
  `PhoneNo` varchar(13) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Role_ID` int(2) NULL DEFAULT 3,
  `Role_Parent_ID` int(2) NULL DEFAULT NULL,
  `Create_Time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Update_Time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `Role_ID`(`Role_ID`) USING BTREE,
  INDEX `Role_Parent_ID`(`Role_Parent_ID`) USING BTREE,
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`Role_ID`) REFERENCES `role` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `user_ibfk_2` FOREIGN KEY (`Role_Parent_ID`) REFERENCES `role` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 20180102 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '202cb962ac59075b964b07152d234b70', '男', 18, '15151936139', '1311399510@qq.com', 1, 1, '2018/12/28 16:47', '2018/12/28 16:47');
INSERT INTO `user` VALUES (180101, '杨大仙', '202cb962ac59075b964b07152d234b70', '男', 20, NULL, NULL, 3, NULL, NULL, NULL);
INSERT INTO `user` VALUES (20180101, '乔璐', '202cb962ac59075b964b07152d234b70', '女', 18, NULL, NULL, 4, 3, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
