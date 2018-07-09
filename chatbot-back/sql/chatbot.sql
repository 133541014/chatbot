/*
Navicat MySQL Data Transfer

Source Server         : chao
Source Server Version : 50711
Source Host           : localhost:3306
Source Database       : chatbot

Target Server Type    : MYSQL
Target Server Version : 50711
File Encoding         : 65001

Date: 2018-06-29 21:46:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` char(32) NOT NULL,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(32) NOT NULL COMMENT '密码，加密存储',
  `email` varchar(50) DEFAULT NULL COMMENT '注册邮箱',
  `code` varchar(32) DEFAULT NULL COMMENT '激活码',
  `is_active` varchar(32) DEFAULT '0' COMMENT '是否激活 0：未激活 1：已激活',
  `login_address` varchar(255) DEFAULT NULL COMMENT '登陆地址',
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `online` varchar(255) DEFAULT NULL COMMENT '是否在线',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`) USING BTREE,
  UNIQUE KEY `email` (`email`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Table structure for t_user_friend
-- ----------------------------
DROP TABLE IF EXISTS `t_user_friend`;
CREATE TABLE `t_user_friend` (
  `id` char(32) NOT NULL,
  `user_id` char(32) DEFAULT NULL,
  `friend_id` char(32) DEFAULT NULL,
  `del_flag` varchar(5) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
