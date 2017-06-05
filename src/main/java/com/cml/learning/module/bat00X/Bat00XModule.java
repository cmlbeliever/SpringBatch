package com.cml.learning.module.bat00X;

import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.cml.learning.framework.annotation.BatchAnnotation;
import com.cml.learning.framework.constant.ModuleConst;
import com.cml.learning.framework.module.BaseModule;

@EnableAsync
@EnableScheduling
@BatchAnnotation
public class Bat00XModule extends BaseModule implements ModuleConst {

	public static final String MODULE_NAME = "bat00X";

	public static final String KEY_OPERATE_COUNT = "operateCount";

	/**
	 * 测试batch，用户配置基础batch module信息
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		run(Bat00XModule.class, MODULE_NAME, args);
	}

}