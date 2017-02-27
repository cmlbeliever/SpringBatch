package com.cml.learning.framework.constant;

public interface ModuleConst {
	interface Framwwork {
		/**
		 * 读写property配置
		 */
		String DB_CONFIG_RW = "classpath:config/jdbc-rw.properties";
		/**
		 * 只读property配置
		 */
		String DB_CONFIG_R = "classpath:config/jdbc-r.properties";

		/**
		 * 基础配置包
		 */
		String PACKAGE_FRAMEWORK = "com.cml.learning.framework";
	}
}
