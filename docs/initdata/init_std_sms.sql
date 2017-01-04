INSERT INTO `tjc_company` VALUES ('GJ1002', '个金投资有限公司', '个金所');
INSERT INTO `tjc_configure` (`company_code`,`channel`,`k`,`v`,`remark`)  VALUES ('GJ1002', 'CSMD', 'csmd_sn', 'SDK-JDH-010-00046', 'account');
INSERT INTO `tjc_configure` (`company_code`,`channel`,`k`,`v`,`remark`)  VALUES ('GJ1002', 'CSMD', 'csmd_password', 'df9f0e__', 'password');

INSERT INTO `tjc_company` VALUES ('SYJ1003', '生意家', '生意家');
INSERT INTO `tjc_configure` (`company_code`,`channel`,`k`,`v`,`remark`)  VALUES ('SYJ1003', 'HHXX', 'hhxx_userid_1', '48', 'userid');
INSERT INTO `tjc_configure` (`company_code`,`channel`,`k`,`v`,`remark`)  VALUES ('SYJ1003', 'HHXX', 'hhxx_account_1', 'hh8004', 'account');
INSERT INTO `tjc_configure` (`company_code`,`channel`,`k`,`v`,`remark`)  VALUES ('SYJ1003', 'HHXX', 'hhxx_password_1', 'hh8004', 'password');
INSERT INTO `tjc_configure` (`company_code`,`channel`,`k`,`v`,`remark`)  VALUES ('SYJ1003', 'HHXX', 'hhxx_userid_2', '131', 'userid');
INSERT INTO `tjc_configure` (`company_code`,`channel`,`k`,`v`,`remark`)  VALUES ('SYJ1003', 'HHXX', 'hhxx_account_2', 'hh8044', 'account');
INSERT INTO `tjc_configure` (`company_code`,`channel`,`k`,`v`,`remark`)  VALUES ('SYJ1003', 'HHXX', 'hhxx_password_2', 'a123456', 'password');

INSERT INTO `tjc_company` VALUES ('CSW1004', '温州城市网', '城市网');
INSERT INTO `tjc_configure` (`company_code`,`channel`,`k`,`v`,`remark`) VALUES ('CSW1004', 'HHXX', 'hhxx_userid_1', '162', 'userid');
INSERT INTO `tjc_configure` (`company_code`,`channel`,`k`,`v`,`remark`) VALUES ('CSW1004', 'HHXX', 'hhxx_account_1', 'hh8071', 'account');
INSERT INTO `tjc_configure` (`company_code`,`channel`,`k`,`v`,`remark`) VALUES ('CSW1004', 'HHXX', 'hhxx_password_1', 'ahh8070', 'password');
INSERT INTO `tjc_configure` (`company_code`,`channel`,`k`,`v`,`remark`) VALUES ('CSW1004', 'HHXX', 'hhxx_userid_2', '162', 'userid');
INSERT INTO `tjc_configure` (`company_code`,`channel`,`k`,`v`,`remark`) VALUES ('CSW1004', 'HHXX', 'hhxx_account_2', 'hh8071', 'account');
INSERT INTO `tjc_configure` (`company_code`,`channel`,`k`,`v`,`remark`) VALUES ('CSW1004', 'HHXX', 'hhxx_password_2', 'ahh8070', 'password');

INSERT INTO `tjc_company` VALUES ('CG1005', '菜狗', '菜狗商城');
INSERT INTO `tjc_configure` (`company_code`,`channel`,`k`,`v`,`remark`) VALUES ('CG1005', 'HHXX', 'hhxx_userid_1', '158', 'userid');
INSERT INTO `tjc_configure` (`company_code`,`channel`,`k`,`v`,`remark`) VALUES ('CG1005', 'HHXX', 'hhxx_account_1', 'hh8066', 'account');
INSERT INTO `tjc_configure` (`company_code`,`channel`,`k`,`v`,`remark`) VALUES ('CG1005', 'HHXX', 'hhxx_password_1', 'a123456', 'password');
INSERT INTO `tjc_configure` (`company_code`,`channel`,`k`,`v`,`remark`) VALUES ('CG1005', 'HHXX', 'hhxx_userid_2', '158', 'userid');
INSERT INTO `tjc_configure` (`company_code`,`channel`,`k`,`v`,`remark`) VALUES ('CG1005', 'HHXX', 'hhxx_account_2', 'hh8066', 'account');
INSERT INTO `tjc_configure` (`company_code`,`channel`,`k`,`v`,`remark`) VALUES ('CG1005', 'HHXX', 'hhxx_password_2', 'a123456', 'password');

INSERT INTO `tjc_company` (`code`,`name`,`prefix`) VALUES ('QLQQ1006','骑来骑去',NULL);
INSERT INTO `tjc_configure` (`company_code`,`channel`,`k`,`v`,`remark`) VALUES ('QLQQ1006','SYKJ','sykj_product','60894573','sykj_product');
INSERT INTO `tjc_configure` (`company_code`,`channel`,`k`,`v`,`remark`) VALUES ('QLQQ1006','SYKJ','sykj_account','001107','sykj_account');
INSERT INTO `tjc_configure` (`company_code`,`channel`,`k`,`v`,`remark`) VALUES ('QLQQ1006','SYKJ','sykj_password','a7zyPAN3','sykj_password');

INSERT INTO `tjc_company` (`code`,`name`,`prefix`) VALUES ('ZHP1007','正汇科技','正汇科技');
INSERT INTO `tjc_configure` (`company_code`,`channel`,`k`,`v`,`remark`) VALUES ('ZHP1007','Z253','z253_account','N1315527','z253_account');
INSERT INTO `tjc_configure` (`company_code`,`channel`,`k`,`v`,`remark`) VALUES ('ZHP1007','Z253','z253_password','Ps7f18ea','z253_password');
/*
-- Query: SELECT * FROM push_std_sms.tstd_system_channel
LIMIT 0, 50000

-- Date: 2016-11-30 14:06
*/
INSERT INTO `tstd_system_channel` (`system_code`,`channel_type`,`push_type`,`status`,`push_system`,`private_key1`,`private_key2`,`private_key3`,`remark`) VALUES ('1','1','12','1','','162','hh8070','ahh8070','城市网');
INSERT INTO `tstd_system_channel` (`system_code`,`channel_type`,`push_type`,`status`,`push_system`,`private_key1`,`private_key2`,`private_key3`,`remark`) VALUES ('1','2','21','1','','e614d2a82d038160f707f1a8','22d4796873b7f002537f30b6','','');
INSERT INTO `tstd_system_channel` (`system_code`,`channel_type`,`push_type`,`status`,`push_system`,`private_key1`,`private_key2`,`private_key3`,`private_key4`,`private_key5`,`repay_text1`,`repay_text2`,`repay_text3`,`remark`) VALUES ('1','3','31','1','','wxef7fda595f81f6d6','057815f636178d3a81c3b065f395a209',NULL,'cswXm','dt8XIq63Xk1VOsoc59cQfXUjU2x2Gvndrh2yTxTQ7yb','请输入手机号，当前案件办完后小蜜将通知您过来领取。','谢谢您的配合，小蜜会尽快通知您办件结果。',NULL,'');
INSERT INTO `tstd_system_channel` (`system_code`,`channel_type`,`push_type`,`status`,`push_system`,`private_key1`,`private_key2`,`private_key3`,`remark`) VALUES ('1','4','41','1','','','','','');

INSERT INTO `tstd_system_template` (`system_code`,`template_id`,`url`,`color1`,`color2`,`content`,`remark`) VALUES ('1','xotLTdJvA0JhXZH506v9TGPyDmQVK2eN1ZnJsvdo6mI','http://cswapp.hichengdai.com','#FF0000','#173177','',NULL);
