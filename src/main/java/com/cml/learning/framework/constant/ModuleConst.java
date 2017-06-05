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
		 * framework基础配置包
		 */
		String PACKAGE_FRAMEWORK = "com.cml.learning.framework";
		/**
		 * batch基础包名
		 */
		String PACKAGE_BATCH = "com.cml.learning";
		/**
		 * 只读mapper interface所在包配置
		 */
		String MAPPER_SCAN_R_PACKAGE = "com.cml.learning.module";
		/**
		 * 读写mapper interface所在包配置
		 */
		String MAPPER_SCAN_RW_PACKAGE = "com.cml.learning.module";

		/***
		 * common 工程下的读写配置
		 */
		String MAPPER_SCAN_RW_PACKAGE_COMMON = "com.bolstra.common.dao.write";

		/**
		 * common 工程下的读写配置
		 */
		String MAPPER_SCAN_R_PACKAGE_COMMON = "com.bolstra.common.dao.read";
	}

	interface Module {
		/**
		 * batch 指定properties位置key
		 */
		String BATCH_PROPERTIES = "batchProperties";
		String BATCH_PROPERTIES_PLACEHOLDER = "${batchProperties}";
		String BATCH_PROPERTIES_FORMAT = "classpath:config/module/%s.properties";
	}
}
