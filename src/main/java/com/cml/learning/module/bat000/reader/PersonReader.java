package com.cml.learning.module.bat000.reader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import com.cml.learning.module.bat000.beans.Person;

public class PersonReader implements ItemReader<Person> {
	private static final Logger log = LoggerFactory.getLogger(PersonReader.class);
	private int count = 4;

	protected StepExecution stepExecution;

	@BeforeStep
	public void saveStepExecution(StepExecution stepExecution) {
		this.stepExecution = stepExecution;
	}

	@Override
	public Person read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		log.info("================reader===========");
		stepExecution.getExecutionContext().put("testStep", "1");
		count--;
		if (count <= 0) {
			return new Person("END", "last");
		}
		return new Person("firstName-" + stepExecution.getStepName(), "lastName" + count);
	}
}
