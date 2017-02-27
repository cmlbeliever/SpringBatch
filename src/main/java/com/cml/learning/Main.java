package com.cml.learning;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import com.cml.learning.batch.configuration.SimpleBatchConfiguration;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@Import(SimpleBatchConfiguration.class)
@EnableBatchProcessing
@PropertySources({ @PropertySource("classpath:config/jdbc-rw.properties"), @PropertySource("classpath:config/jdbc-r.properties") })
public class Main {
	public static void main(String[] args) {
		SpringApplication.run(Main.class, "jobName=secondJob");
	}
}