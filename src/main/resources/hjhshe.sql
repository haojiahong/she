/*
Navicat MySQL Data Transfer

Source Server         : hao
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : hjhshe

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2015-11-16 23:29:25
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `my_role`
-- ----------------------------
DROP TABLE IF EXISTS `my_role`;
CREATE TABLE `my_role` (
  `ROLE_ID` char(32) NOT NULL,
  `ROLE_NAME` varchar(100) DEFAULT NULL,
  `ROLE_DESC` varchar(100) DEFAULT NULL,
  `REMARK` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of my_role
-- ----------------------------
INSERT INTO `my_role` VALUES ('999caacc40ac4efaae5d2da5729228ff', 'ROLE_CUSTOM', '普通用户', null);
INSERT INTO `my_role` VALUES ('999caacc40ac4efaae5d2da5729228gg', 'ROLE_ADMIN', '超级管理员', null);

-- ----------------------------
-- Table structure for `my_user`
-- ----------------------------
DROP TABLE IF EXISTS `my_user`;
CREATE TABLE `my_user` (
  `USER_ID` char(32) NOT NULL,
  `USER_DESCRIPTION` varchar(255) DEFAULT NULL,
  `USER_NAME` varchar(255) DEFAULT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `GENDER` varchar(255) DEFAULT NULL,
  `LOGIN_NAME` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `PHONE_NUMBER` varchar(255) DEFAULT NULL,
  `STATUS` int(1) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of my_user
-- ----------------------------
INSERT INTO `my_user` VALUES ('999caacc40ac4efaae5d2da57292288d', 'aaa啊啊啊', 'customer', null, null, null, '123', null, '1');
INSERT INTO `my_user` VALUES ('999caacc40ac4efaae5d2da57292288f', null, 'admin', null, null, null, '123', null, '1');
INSERT INTO `my_user` VALUES ('999caacc40ac4efaae5d2da572922896', null, 'customer2', null, null, null, '123', null, '1');

-- ----------------------------
-- Table structure for `my_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `my_user_role`;
CREATE TABLE `my_user_role` (
  `UUID` char(32) NOT NULL,
  `USER_ID` char(32) DEFAULT NULL,
  `ROLE_ID` char(32) DEFAULT NULL,
  PRIMARY KEY (`UUID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of my_user_role
-- ----------------------------
INSERT INTO `my_user_role` VALUES ('888caacc40ac4efaae5d2da57299288d', '999caacc40ac4efaae5d2da57292288f', '999caacc40ac4efaae5d2da5729228ff');
INSERT INTO `my_user_role` VALUES ('999caacc40ac4efaae5d2da54492288d', '999caacc40ac4efaae5d2da57292288d', '999caacc40ac4efaae5d2da5729228ff');
INSERT INTO `my_user_role` VALUES ('999caacc40ac4efaae5d2da57299288d', '999caacc40ac4efaae5d2da57292288f', '999caacc40ac4efaae5d2da5729228gg');
INSERT INTO `my_user_role` VALUES ('999caacc40ac4efaae5d2ff57292288d', '999caacc40ac4efaae5d2da572922896', '999caacc40ac4efaae5d2da5729228ff');

-- ----------------------------
-- Table structure for `oa_permission`
-- ----------------------------
DROP TABLE IF EXISTS `oa_permission`;
CREATE TABLE `oa_permission` (
  `PERMISSION_ID` char(32) NOT NULL DEFAULT '' COMMENT '权限代码',
  `PID` char(32) DEFAULT NULL,
  `NAME` varchar(100) DEFAULT NULL,
  `PNAME` varchar(100) DEFAULT NULL,
  `SORT_NUM` int(11) DEFAULT NULL,
  `MYID` varchar(55) DEFAULT NULL,
  `TYPE` char(1) DEFAULT NULL COMMENT 'F:FUNCTION,O:OPERATION',
  `ISUSED` char(1) DEFAULT NULL COMMENT 'Y,N',
  `STATE` varchar(20) DEFAULT NULL,
  `URL` varchar(200) DEFAULT NULL COMMENT '链接地址',
  `ICONCLS` varchar(100) DEFAULT NULL COMMENT '功能模块',
  `HAVE_SUB` char(1) DEFAULT NULL,
  `STATUS` char(1) DEFAULT NULL COMMENT '状态',
  `DESCRIPTION` varchar(2000) DEFAULT NULL COMMENT '备注',
  `CREATED` datetime DEFAULT NULL COMMENT '创造日期',
  `LASTMOD` datetime DEFAULT NULL COMMENT '修改日期',
  `CREATER` varchar(50) DEFAULT NULL COMMENT '创建人',
  `MODIFYER` varchar(50) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`PERMISSION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_permission
-- ----------------------------
INSERT INTO `oa_permission` VALUES ('0df0a287f92349ee8cdd4af14d85e6aa', null, '系统管理', null, '0', 'sysMgr', 'F', 'Y', 'closed', 'javascript:void(0);', 'icon-sys', 'Y', 'A', '系统管理', '2013-05-23 00:00:00', '2013-05-23 00:00:00', 'admin', 'admin');
INSERT INTO `oa_permission` VALUES ('0df0a287f92349ee8cdd4af14d85e6ab', '0df0a287f92349ee8cdd4af14d85e6aa', '用户管理', '系统管理', '15', 'userList', 'F', 'Y', 'closed', 'shejsp/oa/userlist.jsp', 'icon-adds', 'N', 'A', '用户管理', '2015-11-08 12:38:09', null, 'admin', 'admin');
INSERT INTO `oa_permission` VALUES ('0df0a287f92349ee8cdd4af14d85e6ac', '0df0a287f92349ee8cdd4af14d85e6aa', '角色管理', '系统管理', '16', 'roleList', 'F', 'Y', 'closed', 'shejsp/oa/rolelist.jsp', 'icon-adds', 'N', 'A', '角色管理', '2015-11-12 20:29:19', null, 'admin', null);
INSERT INTO `oa_permission` VALUES ('0df0a287f92349ee8cdd4af14d85e6ad', '0df0a287f92349ee8cdd4af14d85e6aa', '权限管理', '系统管理', '11', 'permissionList', 'F', 'Y', 'closed', 'shejsp/oa/privtreegridez.jsp', 'icon-adds', 'N', 'A', '权限管理', '2015-11-16 23:00:21', null, 'admin', null);

-- ----------------------------
-- Table structure for `oa_role`
-- ----------------------------
DROP TABLE IF EXISTS `oa_role`;
CREATE TABLE `oa_role` (
  `ROLE_ID` char(32) NOT NULL COMMENT '角色编码',
  `NAME` varchar(55) DEFAULT NULL COMMENT '角色名称',
  `DESCRIPTION` varchar(500) DEFAULT NULL COMMENT '角色描述',
  `STATUS` char(1) DEFAULT NULL COMMENT '角色状态',
  `SORT_NUM` int(10) DEFAULT NULL COMMENT '排序',
  `CREATED` datetime DEFAULT NULL COMMENT '创造时间',
  `LASTMOD` datetime DEFAULT NULL COMMENT '修改时间',
  `CREATER` varchar(50) DEFAULT NULL COMMENT '创建人',
  `MODIFYER` varchar(50) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_role
-- ----------------------------
INSERT INTO `oa_role` VALUES ('1678baf3acdc425696cb4933b907fb1a', '总经理', '总经理', 'A', '3', '2015-11-16 20:11:47', null, 'admin', null);
INSERT INTO `oa_role` VALUES ('71c87d56df2143bea1b65f6544e6220e', '超级管理员', ' ', 'A', '5', '2015-11-16 20:26:25', null, 'admin', null);
INSERT INTO `oa_role` VALUES ('800f7ce0010049a889c5c47eaf7cb50b', '测试工程师', '哈哈哈哈', 'A', '2', '2015-11-15 20:18:52', '2015-11-15 22:39:24', 'admin', 'admin');
INSERT INTO `oa_role` VALUES ('ad7afedd37ed4b91a77606002d931c1a', '开发工程师', '开发工程师', 'A', '1', '2015-11-16 20:06:36', '2015-11-16 20:06:51', 'admin', 'admin');

-- ----------------------------
-- Table structure for `oa_role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `oa_role_permission`;
CREATE TABLE `oa_role_permission` (
  `ID` char(32) NOT NULL COMMENT '主键',
  `ROLE_ID` char(32) NOT NULL COMMENT '角色代码',
  `PERMISSION_ID` char(32) NOT NULL COMMENT '权限代码',
  `STATUS` char(1) DEFAULT NULL COMMENT '状态',
  `CREATED` datetime DEFAULT NULL COMMENT '创造日期',
  `LASTMOD` datetime DEFAULT NULL COMMENT '修改日期',
  `CREATER` int(10) DEFAULT NULL COMMENT '创建人',
  `MODIFYER` int(10) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_role_permission
-- ----------------------------

-- ----------------------------
-- Table structure for `oa_user`
-- ----------------------------
DROP TABLE IF EXISTS `oa_user`;
CREATE TABLE `oa_user` (
  `USER_ID` char(32) NOT NULL COMMENT '用户代码',
  `ORGANIZE_ID` char(32) DEFAULT NULL COMMENT '组织代码',
  `DUTY_ID` char(32) DEFAULT NULL COMMENT '称位代码 表:SYSTEM_CODE  position',
  `TITLE_ID` char(32) DEFAULT NULL COMMENT '职等代码 表:SYSTEM_CODE  title',
  `MYID` varchar(50) DEFAULT NULL COMMENT '用户自编码',
  `ACCOUNT` varchar(50) DEFAULT NULL COMMENT '账号',
  `NAME` varchar(50) DEFAULT NULL COMMENT '名称',
  `TEL` varchar(30) DEFAULT NULL COMMENT '用户电话',
  `PASSWORD` varchar(128) DEFAULT NULL COMMENT '密码',
  `EMAIL` varchar(200) DEFAULT NULL COMMENT '电子邮箱',
  `LANG` varchar(20) DEFAULT NULL COMMENT '语言',
  `THEME` varchar(20) DEFAULT NULL COMMENT '样式',
  `FIRST_VISIT` datetime DEFAULT NULL COMMENT '第一次登录',
  `PREVIOUS_VISIT` datetime DEFAULT NULL COMMENT '上一次登录',
  `LAST_VISIT` datetime DEFAULT NULL COMMENT '最后一次登录',
  `LOGIN_COUNT` int(10) DEFAULT NULL COMMENT '登录次数',
  `IS_EMPLOYEE` char(1) DEFAULT NULL COMMENT '是否是职工',
  `STATUS` char(1) DEFAULT NULL COMMENT '状态',
  `IP` varchar(20) DEFAULT NULL COMMENT 'IP地址',
  `QUESTION_ID` char(32) DEFAULT NULL COMMENT '问题代码',
  `ANSWER` varchar(100) DEFAULT NULL COMMENT '回复',
  `IS_ONLINE` int(1) DEFAULT NULL COMMENT '是否在线',
  `DESCRIPTION` varchar(2000) DEFAULT NULL COMMENT '备注',
  `CREATED` datetime DEFAULT NULL COMMENT '创造日期',
  `LASTMOD` datetime DEFAULT NULL COMMENT '修改日期',
  `CREATER` varchar(50) DEFAULT NULL COMMENT '创建人',
  `GENDER` varchar(1) DEFAULT NULL,
  `MODIFYER` varchar(50) DEFAULT '0' COMMENT '修改人',
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_user
-- ----------------------------
INSERT INTO `oa_user` VALUES ('0df0a287f92349ee8cdd4af14d85e64f', null, null, null, '', 'admin', 'admin', '123', 'admin', '123', null, null, null, null, null, null, null, 'A', null, null, null, null, '', '2015-11-04 17:32:09', '2015-11-15 15:48:34', null, '', 'admin');
INSERT INTO `oa_user` VALUES ('46df9baeb72d46938374a8abeb752ace', null, null, null, '', 'haotest', 'haotest', '234', '', '234', null, null, null, null, null, null, null, null, null, null, null, null, '', null, '2015-11-14 20:34:03', null, null, 'admin');
INSERT INTO `oa_user` VALUES ('63c281d850e5413b883cf7546503664d', null, null, null, '', '222', 'haotest2222', '222', '', '2222', null, null, null, null, null, null, null, '', null, null, null, null, '2222', '2015-11-15 20:09:14', null, 'admin', '1', null);

-- ----------------------------
-- Table structure for `oa_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `oa_user_role`;
CREATE TABLE `oa_user_role` (
  `ID` char(32) NOT NULL,
  `USER_ID` char(32) NOT NULL DEFAULT '0' COMMENT '用户代码',
  `ROLE_ID` char(32) NOT NULL COMMENT '角色代码',
  `STATUS` char(1) DEFAULT NULL COMMENT '状态',
  `CREATED` datetime DEFAULT NULL COMMENT '创造日期',
  `LASTMOD` datetime DEFAULT NULL COMMENT '修改日期',
  `CREATER` int(10) DEFAULT NULL COMMENT '创建人',
  `MODIFYER` int(10) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_user_role
-- ----------------------------
