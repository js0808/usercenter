ALTER TABLE `usercenter`.`ent_info`
ADD COLUMN `real_name_ids_trans_id` varchar(64) NOT NULL DEFAULT '' COMMENT '身份核实业务id。企业打款认证时使用。' AFTER `real_name_flag`;
