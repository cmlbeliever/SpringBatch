package com.cml.learning.module.bat00X.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.item.ExecutionContext;

import com.cml.learning.framework.processor.BaseProcessor;
import com.cml.learning.module.bat00X.beans.Person;

public class PersonItemProcessor extends BaseProcessor<Person, Person> {

	private static final Logger log = LoggerFactory.getLogger(PersonItemProcessor.class);

	@Override
	public Person doProcess(Person person, JobParameters params, ExecutionContext stepContext) throws Exception {
		final String firstName = person.getFirstName().toUpperCase();
		final String lastName = person.getLastName().toUpperCase();

		final Person transformedPerson = new Person(firstName, lastName);

		log.info("PersonItemProcessor====>params:" + params);

		return transformedPerson;
	}

}