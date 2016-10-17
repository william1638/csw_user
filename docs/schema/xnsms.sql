/*
Navicat MySQL Data Transfer

Source Server         : zxc
Source Server Version : 50630
Source Host           : localhost:3306
Source Database       : xn_sms

Target Server Type    : MYSQL
Target Server Version : 50630
File Encoding         : 65001

Date: 2016-07-11 10:31:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tjc_company`
-- ----------------------------
DROP TABLE IF EXISTS `tjc_company`;
CREATE TABLE `tjc_company` (
  `code` varchar(32) NOT NULL COMMENT '公司编号',
  `name` varchar(255) DEFAULT NULL COMMENT '公司名称',
  `prefix` varchar(255) DEFAULT NULL COMMENT '公司后缀',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `tjc_configure`
-- ----------------------------
DROP TABLE IF EXISTS `tjc_configure`;
CREATE TABLE `tjc_configure` (
  `code` varchar(32) NOT NULL COMMENT '配置信息编号',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  `channel` varchar(255) DEFAULT NULL COMMENT '通道',
  `k` varchar(255) DEFAULT NULL COMMENT 'key',
  `v` varchar(255) DEFAULT NULL COMMENT 'value',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `tjc_day_report`
-- ----------------------------
DROP TABLE IF EXISTS `tjc_day_report`;
CREATE TABLE `tjc_day_report` (
  `code` varchar(32) NOT NULL DEFAULT '报表编号',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  `channel` varchar(255) DEFAULT NULL COMMENT '通道',
  `suc_times` int(16) DEFAULT NULL COMMENT '成功次数',
  `fail_times` int(16) DEFAULT NULL COMMENT '失败次数',
  `report_date` datetime DEFAULT NULL COMMENT '报表日期',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for `tjc_sms_captcha`
-- ----------------------------
DROP TABLE IF EXISTS `tjc_sms_captcha`;
CREATE TABLE `tjc_sms_captcha` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `mobile` varchar(16) DEFAULT NULL COMMENT '手机号',
  `biz_type` varchar(32) DEFAULT NULL COMMENT '业务类型',
  `captcha` varchar(255) DEFAULT NULL COMMENT '验证码',
  `status` varchar(4) DEFAULT NULL COMMENT '状态',
  `send_datetime` datetime DEFAULT NULL COMMENT '发送时间',
  `invalid_datetime` datetime DEFAULT NULL COMMENT '失效时间',
  `check_datetime` datetime DEFAULT NULL COMMENT '验证时间',
  `company_code` varchar(32) DEFAULT NULL COMMENT '所属公司',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `tjc_sms_out`
-- ----------------------------
DROP TABLE IF EXISTS `tjc_sms_out`;
CREATE TABLE `tjc_sms_out` (
  `code` varchar(32) NOT NULL COMMENT '已发短信编号',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  `channel` varchar(255) DEFAULT NULL COMMENT '通道',
  `mobile` varchar(16) DEFAULT NULL COMMENT '手机号',
  `content` varchar(255) DEFAULT NULL COMMENT '发送内容',
  `error_code` varchar(2) DEFAULT NULL COMMENT '错误编号',
  `error_info` varchar(255) DEFAULT NULL COMMENT '错误信息',
  `send_datetime` datetime DEFAULT NULL COMMENT '发送时间',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `tjc_sms_pool`
-- ----------------------------
DROP TABLE IF EXISTS `tjc_sms_pool`;
CREATE TABLE `tjc_sms_pool` (
  `code` varchar(32) NOT NULL DEFAULT '' COMMENT '待发短信编号',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  `channel` varchar(255) DEFAULT NULL COMMENT '通道',
  `mobile` varchar(16) DEFAULT NULL COMMENT '手机号',
  `content` varchar(255) DEFAULT NULL COMMENT '发送内容',
  `to_send_datetime` datetime DEFAULT NULL COMMENT '待发时间',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;