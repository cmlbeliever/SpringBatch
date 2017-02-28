package com.cml.learning.framework.mybatis;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Configuration;

import com.cml.learning.framework.constant.ModuleConst;
import com.cml.learning.framework.mybatis.marker.ReadMapper;

@Configuration
@AutoConfigureAfter(MybatisRConfig.class)
@MapperScan(markerInterface = ReadMapper.class, basePackages = {
		ModuleConst.Framwwork.MAPPER_SCAN_R_PACKAGE }, sqlSessionFactoryRef = "readOnlySqlSessionFactory")
public class MybatisRScanConfiguration {
	protected static Log log = LogFactory.getLog(MybatisRScanConfiguration.class);

	public MybatisRScanConfiguration() {
		log.info("*************************MybatisScanConfiguration***********************");
	}
}
