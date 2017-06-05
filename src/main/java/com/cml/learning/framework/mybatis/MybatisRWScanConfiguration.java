package com.cml.learning.framework.mybatis;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Configuration;

import com.cml.learning.framework.constant.ModuleConst;
import com.cml.learning.framework.mybatis.marker.WriteMapper;

@Configuration
@AutoConfigureAfter(MybatisRWConfig.class)
@MapperScan(markerInterface = WriteMapper.class, basePackages = { ModuleConst.Framwwork.MAPPER_SCAN_RW_PACKAGE,
		ModuleConst.Framwwork.MAPPER_SCAN_RW_PACKAGE_COMMON }, sqlSessionFactoryRef = "sqlSessionFactory")
public class MybatisRWScanConfiguration {
	protected static Log log = LogFactory.getLog(MybatisRWScanConfiguration.class);

	public MybatisRWScanConfiguration() {
		log.info("*************************MybatisScanConfiguration***********************");
	}
}
