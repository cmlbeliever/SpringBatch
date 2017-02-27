package com.cml.learning.module.bat000;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import com.cml.learning.framework.configuration.SimpleBatchConfiguration;
import com.cml.learning.framework.constant.ModuleConst;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@Import({ SimpleBatchConfiguration.class })
@EnableBatchProcessing
@ComponentScan
@ComponentScans({ @ComponentScan("com.cml.learning.framework") })
@PropertySources({ @PropertySource("classpath:application.properties"), @PropertySource("classpath:config/jdbc-rw.properties"),
		@PropertySource("classpath:config/jdbc-r.properties"), @PropertySource("classpath:config/module/bat000.properties") })
public class Bat000Module implements ModuleConst, CommandLineRunner {

	public static final String MODULE_NAME = "bat000";

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	public static void main(String[] args) {
		// SpringApplication.run(Bat000Module.class,
		// "spring.batch.job.names=secondJob");
		SpringApplication.run(Bat000Module.class, "spring.batch.job.names=secondJob");
	}

	@Override
	public void run(String... args) throws Exception {
		for (String arg : args) {
			System.out.println("run=-====================================" + arg);
		}

	}
}