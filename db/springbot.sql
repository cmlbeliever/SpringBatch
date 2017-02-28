
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_api_log`
-- ----------------------------
DROP TABLE IF EXISTS `t_api_log`;
CREATE TABLE `t_api_log` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `call_day` int(8) NOT NULL,
  `PARAMETERS` mediumtext,
  `RETURN_STATUS_CODE` int(11) DEFAULT NULL,
  `RETURNS` mediumtext,
  `API_URL` varchar(255) DEFAULT NULL,
  `create_date` datetime NOT NULL,
  `update_date` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1440 DEFAULT CHARSET=utf8;

