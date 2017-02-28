package com.cml.learning.framework.module;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

public abstract class BaseModule implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(BaseModule.class);

	private static final String BASE_PACKAGE = "com.cml.learning";

	private static final String DB_R_ALIAS_KEY = "db.mybatis.r.typeAliasesPackage";
	private static final String DB_R_ALIAS_FORMAT = BASE_PACKAGE + ".module.%s.beans";
	private static final String DB_RW_ALIAS_KEY = "db.mybatis.rw.typeAliasesPackage";
	private static final String DB_RW_ALIAS_FORMAT = BASE_PACKAGE + ".module.%s.beans";

	/** 运行时传递的batch参数 **/
	private static final String ARGS_BATCH_NAME = "batchName";

	public static void run(Class<? extends BaseModule> module, String[] args) {
		SpringApplication app = new SpringApplication(module);

		if (null == args || args.length == 0) {
			throw new IllegalArgumentException("Args is required,Include " + ARGS_BATCH_NAME + " at least!!");
		}

		// 解析参数信息
		Map<String, Object> param = retrieveArgs(args);
		app.setDefaultProperties(param);

		app.run(args);
	}

	/**
	 * 解析batch运行参数信息
	 * 
	 * @param args
	 * @return
	 */
	private static Map<String, Object> retrieveArgs(String[] args) {
		Map<String, Object> param = new HashMap<String, Object>();
		for (String arg : args) {
			String[] regexValue = arg.split("=");
			String key = regexValue[0];
			String value = regexValue[1];

			// DB beans扫描配置
			if (ARGS_BATCH_NAME.equals(key)) {
				String readBeansPackage = String.format(DB_R_ALIAS_FORMAT, value);
				String writeBeansPackage = String.format(DB_RW_ALIAS_FORMAT, value);
				param.put(DB_R_ALIAS_KEY, readBeansPackage);
				param.put(DB_RW_ALIAS_KEY, writeBeansPackage);
			} else {
				param.put(key, value);
			}
		}

		if (!param.containsKey(DB_RW_ALIAS_KEY) || !param.containsKey(DB_R_ALIAS_KEY)) {
			throw new IllegalArgumentException("Param " + ARGS_BATCH_NAME + " is required for launch !!!!!");
		}
		return param;
	}

	@Override
	public void run(String... args) throws Exception {
		if (null != args) {
			for (String arg : args) {
				log.info("execute module with argument : " + arg);
			}
		}
	}
}
