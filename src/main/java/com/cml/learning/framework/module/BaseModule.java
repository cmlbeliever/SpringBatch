package com.cml.learning.framework.module;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

import com.cml.learning.framework.constant.ModuleConst;

public abstract class BaseModule implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(BaseModule.class);

	private static final String BASE_PACKAGE = ModuleConst.Framwwork.PACKAGE_BATCH;

	private static final String DB_R_ALIAS_KEY = "db.mybatis.r.typeAliasesPackage";
	private static final String DB_R_ALIAS_FORMAT = BASE_PACKAGE + ".module.%s.beans,com.bolstra.beans";
	private static final String DB_RW_ALIAS_KEY = "db.mybatis.rw.typeAliasesPackage";
	private static final String DB_RW_ALIAS_FORMAT = BASE_PACKAGE + ".module.%s.beans,com.bolstra.beans";

	/** 运行时传递的batch参数 **/
	private static final String ARGS_BATCH_NAME = "batchName";

	public static void run(Class<? extends BaseModule> module, String batchName, String[] args) {
		SpringApplication app = new SpringApplication(module);
		app.setWebEnvironment(false);

		// 解析参数信息
		Map<String, Object> param = retrieveArgs(batchName, args);
		app.setDefaultProperties(param);

		app.run(args);
	}

	/**
	 * 解析batch运行参数信息
	 * 
	 * @param args
	 * @return
	 */
	private static Map<String, Object> retrieveArgs(String batchName, String[] args) {
		Map<String, Object> param = new HashMap<String, Object>();
		for (String arg : args) {
			String[] regexValue = arg.split("=");
			String key = regexValue[0];
			String value = regexValue[1];

			param.put(key, value);
		}

		String readBeansPackage = String.format(DB_R_ALIAS_FORMAT, batchName);
		String writeBeansPackage = String.format(DB_RW_ALIAS_FORMAT, batchName);
		param.put(DB_R_ALIAS_KEY, readBeansPackage);
		param.put(DB_RW_ALIAS_KEY, writeBeansPackage);
		// 对应batch 配置目录
		param.put(ModuleConst.Module.BATCH_PROPERTIES, String.format(ModuleConst.Module.BATCH_PROPERTIES_FORMAT, batchName));

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
