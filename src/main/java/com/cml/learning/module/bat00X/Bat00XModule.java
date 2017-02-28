package com.cml.learning.module.bat00X;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import com.cml.learning.framework.configuration.SimpleBatchConfiguration;
import com.cml.learning.framework.constant.ModuleConst;
import com.cml.learning.framework.module.BaseModule;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@Import({ SimpleBatchConfiguration.class })
@EnableBatchProcessing
@ComponentScan
@ComponentScans({ @ComponentScan(ModuleConst.Framwwork.PACKAGE_FRAMEWORK) })
@PropertySources({ @PropertySource(ModuleConst.Framwwork.DB_CONFIG_RW), @PropertySource(ModuleConst.Framwwork.DB_CONFIG_R) })
@PropertySource("classpath:config/module/bat00X.properties")
public class Bat00XModule extends BaseModule implements ModuleConst {

	public static final String MODULE_NAME = "bat00X";

	public static final String KEY_OPERATE_COUNT = "operateCount";

	/**
	 * 测试batch，用户配置基础batch module信息
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		run(Bat00XModule.class, args);
	}

}