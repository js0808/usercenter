ALTER TABLE `usercenter`.`ent_info`
ADD COLUMN `real_name_ids_trans_id` varchar(64) NOT NULL DEFAULT '' COMMENT '身份核实业务id。企业打款认证时使用。' AFTER `real_name_flag`;

ALTER TABLE `usercenter`.`ent_info`
MODIFY COLUMN `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) AFTER `create_time`;

ALTER TABLE `usercenter`.`ent_info`
ADD INDEX `idx_social_credit_code`(`social_credit_code`);
