

DROP TABLE IF EXISTS `tjc_sms_captcha`;
CREATE TABLE `tjc_sms_captcha` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `mobile` varchar(16) NOT NULL COMMENT '手机号',
  `biz_type` varchar(4) NOT NULL COMMENT '业务类型',
  `sms_captcha` varchar(8) NOT NULL COMMENT '验证码',
  `status` varchar(2) NOT NULL COMMENT '状态',
  `create_datetime` datetime NOT NULL COMMENT '创建时间',
  `sent_datetime` datetime DEFAULT NULL COMMENT '发送时间',
  `invalid_datetime` datetime DEFAULT NULL COMMENT '失效时间',
  `check_datetime` datetime DEFAULT NULL COMMENT '验证时间',
  `check_times` int(11) DEFAULT NULL COMMENT '验证次数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `tjc_sms_out`;
CREATE TABLE `tjc_sms_out` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `mobile` varchar(16) NOT NULL COMMENT '手机号',
  `content` varchar(255) NOT NULL COMMENT '短信内容',
  `biz_type` varchar(2) DEFAULT NULL COMMENT '业务类型',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `status` varchar(2) NOT NULL COMMENT '状态',
  `create_datetime` datetime NOT NULL COMMENT '创建时间',
  `sent_datetime` datetime DEFAULT NULL COMMENT '发送时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
