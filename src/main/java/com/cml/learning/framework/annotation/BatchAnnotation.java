package com.cml.learning.framework.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

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

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@Import({ SimpleBatchConfiguration.class })
@EnableBatchProcessing
@ComponentScan
@ComponentScans({ @ComponentScan(ModuleConst.Framwwork.PACKAGE_FRAMEWORK) })
@PropertySources({ @PropertySource(ModuleConst.Framwwork.DB_CONFIG_RW), @PropertySource(ModuleConst.Framwwork.DB_CONFIG_R) })
@PropertySource(ModuleConst.Module.BATCH_PROPERTIES_PLACEHOLDER)
public @interface BatchAnnotation {

}
