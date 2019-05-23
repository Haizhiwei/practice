/*
Navicat MySQL Data Transfer

Source Server         : stone
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2018-09-26 15:25:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for de_wight
-- ----------------------------
DROP TABLE IF EXISTS `de_wight`;
CREATE TABLE `de_wight` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(20) DEFAULT NULL COMMENT '姓名',
  `age` int(3) DEFAULT NULL COMMENT '年龄',
  `number` int(4) DEFAULT '1' COMMENT '编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=121 DEFAULT CHARSET=utf8 COMMENT='去重表';

-- ----------------------------
-- Records of de_wight
-- ----------------------------
INSERT INTO `de_wight` VALUES ('98', 'zhangsan', '18', '1');
INSERT INTO `de_wight` VALUES ('99', 'zhangsan', '18', '1');
INSERT INTO `de_wight` VALUES ('100', 'lisi', '19', '2');
INSERT INTO `de_wight` VALUES ('101', 'wanwu', '20', '3');
INSERT INTO `de_wight` VALUES ('102', '张飞', '21', '4');
INSERT INTO `de_wight` VALUES ('103', 'zhangsan', '18', '1');
INSERT INTO `de_wight` VALUES ('104', 'lisi', '19', '2');
INSERT INTO `de_wight` VALUES ('105', 'wanwu', '20', '3');
INSERT INTO `de_wight` VALUES ('106', '张飞', '21', '4');
INSERT INTO `de_wight` VALUES ('107', 'zhangsan', '18', '1');
INSERT INTO `de_wight` VALUES ('108', 'lisi', '19', '2');
INSERT INTO `de_wight` VALUES ('109', 'wanwu', '20', '3');
INSERT INTO `de_wight` VALUES ('110', '张飞', '21', '4');
INSERT INTO `de_wight` VALUES ('111', 'zhangsan', '18', '1');
INSERT INTO `de_wight` VALUES ('112', 'lisi', '19', '2');
INSERT INTO `de_wight` VALUES ('113', 'wanwu', '20', '3');
INSERT INTO `de_wight` VALUES ('114', '张飞', '21', '4');
INSERT INTO `de_wight` VALUES ('115', 'zhangsan', '18', '1');
INSERT INTO `de_wight` VALUES ('116', 'lisi', '19', '2');
INSERT INTO `de_wight` VALUES ('117', 'wanwu', '20', '3');
INSERT INTO `de_wight` VALUES ('118', '小明', '18', '1');
INSERT INTO `de_wight` VALUES ('119', '小明', '18', '1');
INSERT INTO `de_wight` VALUES ('120', '小明', '18', '1');

-- ----------------------------
-- Table structure for de_wight_three
-- ----------------------------
DROP TABLE IF EXISTS `de_wight_three`;
CREATE TABLE `de_wight_three` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(20) DEFAULT NULL COMMENT '姓名',
  `age` int(3) DEFAULT NULL COMMENT '年龄',
  `number` int(4) DEFAULT '1' COMMENT '编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8 COMMENT='去重表';

-- ----------------------------
-- Records of de_wight_three
-- ----------------------------
INSERT INTO `de_wight_three` VALUES ('98', 'zhangsan', '18', '1');
INSERT INTO `de_wight_three` VALUES ('99', 'lisi', '19', '2');
INSERT INTO `de_wight_three` VALUES ('100', 'wanwu', '20', '3');
INSERT INTO `de_wight_three` VALUES ('101', '张飞', '21', '4');

-- ----------------------------
-- Table structure for de_wight_two
-- ----------------------------
DROP TABLE IF EXISTS `de_wight_two`;
CREATE TABLE `de_wight_two` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(20) DEFAULT NULL COMMENT '姓名',
  `age` int(3) DEFAULT NULL COMMENT '年龄',
  `number` int(4) DEFAULT '1' COMMENT '编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8 COMMENT='去重表2';

-- ----------------------------
-- Records of de_wight_two
-- ----------------------------
INSERT INTO `de_wight_two` VALUES ('98', 'zhangsan', '18', '1');
INSERT INTO `de_wight_two` VALUES ('99', 'lisi', '19', '2');
INSERT INTO `de_wight_two` VALUES ('100', 'wanwu', '20', '3');
INSERT INTO `de_wight_two` VALUES ('101', '张飞', '21', '4');

-- ----------------------------
-- Table structure for sys_permissions
-- ----------------------------
DROP TABLE IF EXISTS `sys_permissions`;
CREATE TABLE `sys_permissions` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `permission` varchar(100) DEFAULT NULL COMMENT '权限编号',
  `description` varchar(100) DEFAULT NULL COMMENT '权限描述',
  `rid` bigint(20) DEFAULT NULL COMMENT '此权限关联角色的id',
  `available` tinyint(1) DEFAULT '0' COMMENT '是否锁定',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_sys_permissions_permission` (`permission`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_permissions
-- ----------------------------

-- ----------------------------
-- Table structure for sys_roles
-- ----------------------------
DROP TABLE IF EXISTS `sys_roles`;
CREATE TABLE `sys_roles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色编号',
  `role` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `description` varchar(100) DEFAULT NULL COMMENT '角色描述',
  `pid` bigint(20) DEFAULT NULL COMMENT '父节点',
  `available` tinyint(1) DEFAULT '0' COMMENT '是否锁定',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_sys_roles_role` (`role`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_roles
-- ----------------------------

-- ----------------------------
-- Table structure for sys_roles_permissions
-- ----------------------------
DROP TABLE IF EXISTS `sys_roles_permissions`;
CREATE TABLE `sys_roles_permissions` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色编号',
  `permission_id` bigint(20) DEFAULT NULL COMMENT '权限编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_roles_permissions
-- ----------------------------

-- ----------------------------
-- Table structure for sys_users
-- ----------------------------
DROP TABLE IF EXISTS `sys_users`;
CREATE TABLE `sys_users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `username` varchar(100) DEFAULT NULL COMMENT '用户名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `salt` varchar(100) DEFAULT NULL COMMENT '盐值',
  `role_id` varchar(50) DEFAULT NULL COMMENT '角色列表',
  `locked` tinyint(1) DEFAULT '0' COMMENT '是否锁定',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_sys_users_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_users
-- ----------------------------

-- ----------------------------
-- Table structure for sys_users_roles
-- ----------------------------
DROP TABLE IF EXISTS `sys_users_roles`;
CREATE TABLE `sys_users_roles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户编号',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_users_roles
-- ----------------------------

-- ----------------------------
-- Table structure for t
-- ----------------------------
DROP TABLE IF EXISTS `t`;
CREATE TABLE `t` (
  `id` int(11) NOT NULL,
  `a` varchar(255) DEFAULT NULL,
  `c` varchar(255) DEFAULT NULL,
  `b` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t
-- ----------------------------
INSERT INTO `t` VALUES ('1', '1', '2', '有');
INSERT INTO `t` VALUES ('2', '1', '3', '有');
INSERT INTO `t` VALUES ('3', '2', '1', '有');
INSERT INTO `t` VALUES ('4', '3', '2', '有');
INSERT INTO `t` VALUES ('5', '1', '2', '有');

-- ----------------------------
-- Table structure for tb_content
-- ----------------------------
DROP TABLE IF EXISTS `tb_content`;
CREATE TABLE `tb_content` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `category_id` bigint(20) NOT NULL COMMENT '内容类目ID',
  `title` varchar(200) DEFAULT NULL COMMENT '内容标题',
  `sub_title` varchar(100) DEFAULT NULL COMMENT '子标题',
  `title_desc` varchar(500) DEFAULT NULL COMMENT '标题描述',
  `url` varchar(500) DEFAULT NULL COMMENT '链接',
  `pic` varchar(300) DEFAULT NULL COMMENT '图片绝对路径',
  `pic2` varchar(300) DEFAULT NULL COMMENT '图片2',
  `content` text COMMENT '内容',
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  `column_name` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `category_id` (`category_id`),
  KEY `updated` (`updated`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_content
-- ----------------------------

-- ----------------------------
-- Table structure for tb_content_category
-- ----------------------------
DROP TABLE IF EXISTS `tb_content_category`;
CREATE TABLE `tb_content_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '类目ID',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父类目ID=0时，代表的是一级的类目',
  `name` varchar(50) DEFAULT NULL COMMENT '分类名称',
  `status` int(1) DEFAULT '1' COMMENT '状态。可选值:1(正常),2(删除)',
  `sort_order` int(4) DEFAULT NULL COMMENT '排列序号，表示同级类目的展现次序，如数值相等则按名称次序排列。取值范围:大于零的整数',
  `is_parent` tinyint(1) DEFAULT '1' COMMENT '该类目是否为父类目，1为true，0为false',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `updated` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `parent_id` (`parent_id`,`status`) USING BTREE,
  KEY `sort_order` (`sort_order`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='内容分类';

-- ----------------------------
-- Records of tb_content_category
-- ----------------------------

-- ----------------------------
-- Table structure for temp
-- ----------------------------
DROP TABLE IF EXISTS `temp`;
CREATE TABLE `temp` (
  `name` varchar(20) DEFAULT NULL COMMENT '姓名',
  `age` int(3) DEFAULT NULL COMMENT '年龄',
  `number` int(4) DEFAULT '1' COMMENT '编号'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of temp
-- ----------------------------
INSERT INTO `temp` VALUES ('zhangsan', '18', '1');
INSERT INTO `temp` VALUES ('lisi', '19', '2');
INSERT INTO `temp` VALUES ('wanwu', '20', '3');
INSERT INTO `temp` VALUES ('张飞', '21', '4');
