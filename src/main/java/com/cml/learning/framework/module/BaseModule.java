package com.cml.learning.framework.module;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;

public class BaseModule {
	public static void run(Class<? extends BaseModule> module, String[] args) {
		SpringApplication app = new SpringApplication(module);

		if (null != args && args.length > 0) {
			Map<String, Object> param = new HashMap<String, Object>();
			for (String arg : args) {
				String[] regexValue = arg.split("=");
				String key = regexValue[0];
				String value = regexValue[1];
				param.put(key, value);
			}
			app.setDefaultProperties(param);
		}
		app.run(args);
	}
}
