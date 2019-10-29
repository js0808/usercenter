ALTER TABLE `usercenter`.`ent_pay_verify_request`
ADD COLUMN `real_name_record_version` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT 'ream_name_id记录对应的版本' AFTER `real_name_id`;
