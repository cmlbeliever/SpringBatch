package com.cml.learning.module.bat00X.writer;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.cml.learning.framework.writer.BaseWriter;
import com.cml.learning.module.bat00X.Bat00XModule;
import com.cml.learning.module.bat00X.beans.LogBean;
import com.cml.learning.module.bat00X.beans.Person;
import com.cml.learning.module.bat00X.db.Bat00XWriteMapper;

@Component
@Scope("prototype")
public class PersonWriter extends BaseWriter<Person> {
	private static final Logger log = LoggerFactory.getLogger(PersonWriter.class);

	@Autowired
	private Bat00XWriteMapper logMapper;

	@Override
	public void doWrite(Person item, JobParameters params, ExecutionContext stepContext) throws Exception {
		LogBean logbean = new LogBean();
		logbean.setApiUrl("batchTest");
		logbean.setCallDayStr(new DateTime().toString("yyyyMMdd"));
		logbean.setParameters("param");
		logbean.setCreateDate(new DateTime());
		logbean.setReturnStatusCode(200);
		logbean.setReturns("returns" + System.currentTimeMillis());
		logMapper.insertLog(logbean);
		log.info("insert into a log!!!!");
		addIntStepParameter(Bat00XModule.KEY_OPERATE_COUNT);

	}

}
