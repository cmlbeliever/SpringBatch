package com.cml.learning.module.bat00X.listener;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cml.learning.module.bat00X.Bat00XModule;
import com.cml.learning.module.bat00X.beans.LogBean;
import com.cml.learning.module.bat00X.db.Bat00XWriteMapper;

@Component()
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

	private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

	@Autowired
	private Bat00XWriteMapper logMapper;

	@Override
	public void beforeJob(JobExecution jobExecution) {
		log.info("before execu job  " + jobExecution.getJobParameters());
	}

	@Override
	public void afterJob(JobExecution jobExecution) {

		LogBean logbean = new LogBean();
		logbean.setApiUrl("job operate success " + jobExecution.getStatus() + ",operate count = "
				+ jobExecution.getExecutionContext().getInt(Bat00XModule.KEY_OPERATE_COUNT) + ",job:" + jobExecution.getJobInstance().getJobName());
		logbean.setCallDayStr(new DateTime().toString("yyyyMMdd"));
		logbean.setParameters("param" + jobExecution.getJobParameters());
		logbean.setCreateDate(new DateTime());
		logbean.setReturnStatusCode(200);
		logbean.setReturns("batch[" + Bat00XModule.MODULE_NAME + "]" + jobExecution.getJobParameters());
		logMapper.insertLog(logbean);
	}
}
