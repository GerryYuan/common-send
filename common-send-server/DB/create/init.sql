CREATE TABLE `common_send_sms` (
	`id` INT(20) NOT NULL AUTO_INCREMENT COMMENT 'id自增主键',
	`phone` VARCHAR(25) NOT NULL COMMENT '手机号',
	`content` VARCHAR(255) NOT NULL COMMENT '短信内容',
	`remark` VARCHAR(255) NULL DEFAULT NULL COMMENT '返回内容',
	`supplier` VARCHAR(50) NOT NULL COMMENT '短信供应商',
	`platform` TINYINT(4) NOT NULL DEFAULT '1' COMMENT '1：北京聚义通科技',
	`type` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '发送类型，0：注册',
	`status` TINYINT(2) NOT NULL DEFAULT '1' COMMENT '1：成功，2：失败',
	`create_time` DATETIME NOT NULL COMMENT '创建时间',
	PRIMARY KEY (`id`)
)
COMMENT='发送短信'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;
