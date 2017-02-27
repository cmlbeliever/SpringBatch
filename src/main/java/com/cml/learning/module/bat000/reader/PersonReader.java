package com.cml.learning.module.bat000.reader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

import com.cml.learning.module.bat000.beans.Person;

public class PersonReader implements ItemReader<Person> {
	private static final Logger log = LoggerFactory.getLogger(PersonReader.class);
	private int count = 16;

	@Override
	public Person read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		log.info("================reader===========");
		count--;
		if (count == 0) {
			return new Person("END", "last");
		}
		return new Person("firstName" + count, "lastName" + count);
	}
}
