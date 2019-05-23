/*
Navicat MySQL Data Transfer

Source Server         : stone
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : taotao

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2018-09-13 16:49:53
*/

SET FOREIGN_KEY_CHECKS=0;

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
  PRIMARY KEY (`id`),
  KEY `category_id` (`category_id`),
  KEY `updated` (`updated`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_content
-- ----------------------------
INSERT INTO `tb_content` VALUES ('32', '90', '电器1', '苏尼电器', '还不快买', 'www.jingdong.com', 'http://192.168.230.128/images/2018/03/30/1522393767691840.jpg', 'http://192.168.230.128/images/2018/03/30/1522393790254521.jpg', '卖的的是水果，不是电器，哈哈', '2018-03-30 15:10:15', '2018-03-30 15:10:15');
INSERT INTO `tb_content` VALUES ('33', '89', '大广告1', '大广告1', '大广告1', 'http://www.baidu.com', 'http://192.168.230.128/images/2018/04/09/1523237754827921.jpg', 'http://192.168.230.128/images/2018/04/09/1523237763681531.jpg', '大广告1大广告1大广告1大广告1', '2018-04-09 09:36:09', '2018-04-09 09:36:09');
INSERT INTO `tb_content` VALUES ('34', '89', '大广告2', '大广告2', '大广告2', '#', 'http://192.168.230.128/images/2018/04/09/1523237794528152.jpg', 'http://192.168.230.128/images/2018/04/09/1523237800664386.jpg', '大广告2大广告2大广告2大广告2', '2018-04-09 09:36:44', '2018-04-09 09:36:44');
INSERT INTO `tb_content` VALUES ('35', '89', '大广告3', '大广告3', '大广告3大广告3', '#', 'http://192.168.230.128/images/2018/04/09/1523237835915067.jpg', 'http://192.168.230.128/images/2018/04/09/1523237844001640.jpg', '大广告3大广告3大广告3', '2018-04-09 09:37:31', '2018-04-09 09:37:31');
INSERT INTO `tb_content` VALUES ('36', '89', '大广告4', '大广告4', '大广告4大广告4', '#', 'http://192.168.230.128/images/2018/04/09/1523237984409939.jpg', 'http://192.168.230.128/images/2018/04/09/1523237990774823.jpg', '大广告3大广告3大广告3大广告3大广告3', '2018-04-09 09:39:55', '2018-04-09 09:39:55');
INSERT INTO `tb_content` VALUES ('37', '89', 'sync-big', 'sync-big', 'test', 'https://www.jingdong.com', 'http://192.168.230.128/images/2018/04/12/1523499964362397.jpg', 'http://192.168.230.128/images/2018/04/12/1523499973626270.jpg', 'sync-bigsync-bigsync-bigsync-big', '2018-04-12 10:26:20', '2018-04-12 10:26:20');
INSERT INTO `tb_content` VALUES ('41', '89', 'sysnc-testsysnc-test', 'sysnc-testsysnc-testsysnc-testsysnc-test', 'sysnc-testsysnc-test', 'sysnc-testsysnc-test', 'http://192.168.230.128/images/2018/04/12/1523500972283993.jpg', 'http://192.168.230.128/images/2018/04/12/1523500981418621.jpg', 'sysnc-testsysnc-testsysnc-test', '2018-04-12 10:43:05', '2018-04-12 10:43:05');
INSERT INTO `tb_content` VALUES ('42', '89', 'dasfgd', 'sdfgh', 'sdfghhg', 'ddhgfds', 'http://192.168.230.128/images/2018/04/12/1523501338062377.jpg', 'http://192.168.230.128/images/2018/04/12/1523501347077906.jpg', 'dsafdgfds', '2018-04-12 10:49:10', '2018-04-12 10:49:10');
