/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50729
 Source Host           : localhost:3306
 Source Schema         : abc

 Target Server Type    : MySQL
 Target Server Version : 50729
 File Encoding         : 65001

 Date: 29/03/2021 17:17:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `adminid` int(50) NOT NULL AUTO_INCREMENT COMMENT '管理员Id',
  `adminaccount` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '管理员账号 ',
  `adminpwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '管理员密码',
  PRIMARY KEY (`adminid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, '123', '123');
INSERT INTO `admin` VALUES (4, '222', '222');
INSERT INTO `admin` VALUES (5, 'admin', 'admin');
INSERT INTO `admin` VALUES (8, '233', '233');

-- ----------------------------
-- Table structure for classorder
-- ----------------------------
DROP TABLE IF EXISTS `classorder`;
CREATE TABLE `classorder`  (
  `coid` int(11) NOT NULL AUTO_INCREMENT COMMENT '报名表id',
  `conum` int(50) NULL DEFAULT NULL COMMENT '课程编号',
  `coname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程名称',
  `coteacher` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '代课教练',
  `comembername` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '会员名称',
  `conumber` int(50) NULL DEFAULT NULL COMMENT '会员账号',
  `cotime` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '开课时间',
  `classtime` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单课时长',
  `classnumber` int(255) NULL DEFAULT NULL COMMENT '课程剩余次数',
  PRIMARY KEY (`coid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of classorder
-- ----------------------------
INSERT INTO `classorder` VALUES (17, 123122, '拳击', '超级教练', '阿珍', 202168459, '2021-03-06T16:07', '5', 5);
INSERT INTO `classorder` VALUES (18, 123124, '轻活舒体', '王二麻子', '阿强', 202129165, '2021-03-07T14:54', '5', 9);
INSERT INTO `classorder` VALUES (19, 123122, '拳击', '超级教练', '阿强', 202129165, '2021-03-06T16:07', '5', 5);
INSERT INTO `classorder` VALUES (20, 123124, '轻活舒体', '王二麻子', '阿珍', 202168459, '2021-03-07T14:54', '5', 9);
INSERT INTO `classorder` VALUES (21, 123127, '防身术', '王二麻子', '阿珍', 202168459, '2021-03-13T13:15', '2', 20);
INSERT INTO `classorder` VALUES (22, 123128, '太极拳', '王二麻子', '阿珍', 202168459, '2021-03-11T14:40', '1', 15);
INSERT INTO `classorder` VALUES (23, 123122, '拳击', '超级教练', '志明', 202106886, '2021-03-06T16:07', '5', 5);
INSERT INTO `classorder` VALUES (24, 123125, '胸肌撕裂者', '超级教练', '志明', 202106886, '2021-03-14T14:54', '5', 10);
INSERT INTO `classorder` VALUES (25, 123128, '太极拳', '王二麻子', '志明', 202106886, '2021-03-11T14:40', '1', 15);
INSERT INTO `classorder` VALUES (26, 123127, '防身术', '王二麻子', '春娇', 202176002, '2021-03-13T13:15', '2', 20);
INSERT INTO `classorder` VALUES (27, 123124, '轻活舒体', '王二麻子', '春娇', 202176002, '2021-03-07T14:54', '5', 9);
INSERT INTO `classorder` VALUES (28, 123122, '拳击', '超级教练', '春娇', 202176002, '2021-03-06T16:07', '5', 5);
INSERT INTO `classorder` VALUES (29, 123125, '胸肌撕裂者', '超级教练', '阿珍', 202168459, '2021-03-14T14:54', '5', 10);

-- ----------------------------
-- Table structure for classtable
-- ----------------------------
DROP TABLE IF EXISTS `classtable`;
CREATE TABLE `classtable`  (
  `classid` int(11) NOT NULL AUTO_INCREMENT COMMENT '课程id',
  `classname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程名称',
  `classbegin` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '开课时间',
  `classtime` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程时长',
  `teacher` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '代课教练',
  `classprice` int(10) NULL DEFAULT NULL COMMENT '课程价格',
  `classnumber` int(11) NULL DEFAULT NULL COMMENT '课程次数',
  `state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程状态',
  PRIMARY KEY (`classid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 123131 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of classtable
-- ----------------------------
INSERT INTO `classtable` VALUES (123122, '拳击', '2021-03-06T16:07', '5', '超级教练', 1000, 10, '课程进行中');
INSERT INTO `classtable` VALUES (123123, '瑜伽', '2021-03-14T14:58', '2', '李四', 1000, 10, '课程进行中');
INSERT INTO `classtable` VALUES (123124, '轻活舒体', '2021-03-07T14:54', '5', '王二麻子', 1000, 10, '课程进行中');
INSERT INTO `classtable` VALUES (123125, '胸肌撕裂者', '2021-03-14T14:54', '5', '超级教练', 1000, 10, '待开始');
INSERT INTO `classtable` VALUES (123126, '动感单车', '2021-03-27T14:55', '1', '張三', 1000, 10, '待开始');
INSERT INTO `classtable` VALUES (123127, '防身术', '2021-03-13T13:15', '2', '王二麻子', 1000, 20, '待开始');
INSERT INTO `classtable` VALUES (123128, '太极拳', '2021-03-11T14:40', '1', '王二麻子', 1000, 15, '待开始');
INSERT INTO `classtable` VALUES (123129, '90天减肥训练营', '2021-04-02T12:39', '4', '超级教练', 5000, 90, '待开始');
INSERT INTO `classtable` VALUES (123130, '游泳课', '2021-03-03T20:23', '2', '李四', 3000, 30, '待开始');

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee`  (
  `empid` int(11) NOT NULL AUTO_INCREMENT COMMENT '员工id',
  `empnumber` int(11) NULL DEFAULT NULL COMMENT '员工账号',
  `emppwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '员工密码',
  `empname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '员工姓名',
  `empgender` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '员工性别',
  `empage` int(11) NULL DEFAULT NULL COMMENT '员工年龄',
  `entrytime` date NULL DEFAULT NULL COMMENT '入职时间',
  `staff` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职务',
  `message` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`empid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES (4, 68283, '666', '超级教练', '男', 25, '2020-05-13', '健身教练', '健美亚军');
INSERT INTO `employee` VALUES (7, 68152, '1111111', '阿大', '男', 25, '2021-03-15', '管理员', '无');
INSERT INTO `employee` VALUES (8, 94926, '1111111', '阿二', '女', 25, '2021-03-17', '收银', '无');
INSERT INTO `employee` VALUES (9, 14795, '1111111', '張三', '女', 25, '2021-03-18', '健身教练', '中央体院毕业');
INSERT INTO `employee` VALUES (10, 68305, '1111111', '李四', '女', 25, '2021-03-18', '健身教练', '无');
INSERT INTO `employee` VALUES (11, 98210, '123', '王二麻子', '男', 25, '2021-03-24', '健身教练', '无');

-- ----------------------------
-- Table structure for equipment
-- ----------------------------
DROP TABLE IF EXISTS `equipment`;
CREATE TABLE `equipment`  (
  `eqid` int(11) NOT NULL AUTO_INCREMENT COMMENT '器材编号',
  `eqname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '器材名称',
  `eqlocation` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '器材位置',
  `eqstatus` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '器材状态',
  `eqmessage` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '器材备注信息',
  PRIMARY KEY (`eqid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of equipment
-- ----------------------------
INSERT INTO `equipment` VALUES (2, '哑铃', '1号房间', '维修中', '无');
INSERT INTO `equipment` VALUES (7, '哑铃2', '器材室', '损坏', '无');
INSERT INTO `equipment` VALUES (9, '跑步机1', '瑜伽教室', '正常', '无');
INSERT INTO `equipment` VALUES (10, '跑步机1', '大堂', '正常', '无');

-- ----------------------------
-- Table structure for member
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member`  (
  `mid` int(255) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `mnumber` int(100) NULL DEFAULT NULL COMMENT '会员账号',
  `mpwd` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '123456' COMMENT '会员密码',
  `mname` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '会员名字',
  `mgender` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '会员性别',
  `mage` int(100) NULL DEFAULT NULL COMMENT '会员年龄',
  `mheight` int(100) NULL DEFAULT NULL COMMENT '会员身高',
  `mweight` int(100) NULL DEFAULT NULL COMMENT '会员体重',
  `mphone` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '会员电话',
  `cardid` int(100) NULL DEFAULT NULL COMMENT '会员卡id',
  `cardtime` date NULL DEFAULT NULL COMMENT '办卡时间',
  `cardclass` int(50) NULL DEFAULT NULL COMMENT '购买课时',
  `expires` date NULL DEFAULT NULL COMMENT '会员到期时间',
  PRIMARY KEY (`mid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 148 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of member
-- ----------------------------
INSERT INTO `member` VALUES (144, 202168459, '123456', '阿珍', '女', 22, 169, 110, '18085011111', 202168459, '2021-03-27', 16, '2022-07-27');
INSERT INTO `member` VALUES (145, 202129165, '123456', '阿强', '男', 22, 172, 120, '18085011112', 202129165, '2021-03-27', 11, '2022-02-27');
INSERT INTO `member` VALUES (146, 202106886, '123456', '志明', '男', 30, 175, 130, '18085011113', 202106886, '2021-03-27', 12, '2022-03-27');
INSERT INTO `member` VALUES (147, 202176002, '123456', '春娇', '女', 29, 170, 140, '18085011114', 202176002, '2021-03-27', 13, '2022-04-27');

SET FOREIGN_KEY_CHECKS = 1;
