package com.cml.learning.module.bat000.writer;

import java.util.List;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cml.learning.module.bat000.beans.LogBean;
import com.cml.learning.module.bat000.beans.Person;
import com.cml.learning.module.bat000.db.Bat000WriteMapper;

@Component
public class PersonWriter implements ItemWriter<Person> {
	private static final Logger log = LoggerFactory.getLogger(PersonWriter.class);

	@Autowired
	private Bat000WriteMapper logMapper;

	@Override
	public void write(List<? extends Person> items) throws Exception {

		for (Person p : items) {
			LogBean logbean = new LogBean();
			logbean.setApiUrl("batchTest" + p.getFirstName());
			logbean.setCallDayStr(new DateTime().toString("yyyyMMdd"));
			logbean.setParameters("param");
			logbean.setCreateDate(new DateTime());
			logbean.setReturnStatusCode(200);
			logbean.setReturns("returns" + System.currentTimeMillis());
			logMapper.insertLog(logbean);
			log.info("insert into a log!!!!");
			if ("END".equals(p.getFirstName())) {
				throw new IllegalStateException("-------END------");
			}
		}

		Thread.sleep(1000);
	}

}
