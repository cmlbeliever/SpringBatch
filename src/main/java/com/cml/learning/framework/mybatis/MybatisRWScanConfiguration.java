package com.cml.learning.framework.mybatis;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Configuration;

import com.cml.learning.framework.mybatis.marker.WriteMapper;

@Configuration
@AutoConfigureAfter(MybatisRWConfig.class)
@MapperScan(markerInterface = WriteMapper.class, basePackages = { "com.cml.learning.module.bat000.db" }, sqlSessionFactoryRef = "sqlSessionFactory")
public class MybatisRWScanConfiguration {
	protected static Log log = LogFactory.getLog(MybatisRWScanConfiguration.class);

	public MybatisRWScanConfiguration() {
		log.info("*************************MybatisScanConfiguration***********************");
	}
}
