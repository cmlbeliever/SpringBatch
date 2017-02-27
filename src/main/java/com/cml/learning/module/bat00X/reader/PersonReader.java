package com.cml.learning.module.bat00X.reader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import com.cml.learning.framework.reader.BaseItemReader;
import com.cml.learning.module.bat00X.beans.Person;

public class PersonReader extends BaseItemReader<Person> {
	private static final Logger log = LoggerFactory.getLogger(PersonReader.class);
	private int count = 4;

	@Override
	public Person read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		log.info("================reader===========" + count);
		stepExecution.getExecutionContext().put("testStep", "1");
		count--;
		if (count == 1) {
			return new Person("END", "last");
		} else if (count <= 0) {
			return null;
		}
		return new Person("firstName-" + stepExecution.getStepName(), "lastName" + count);
	}
}
