package com.cml.learning.module.bat000;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cml.learning.module.bat000.beans.Person;
import com.cml.learning.module.bat000.listener.JobCompletionNotificationListener;
import com.cml.learning.module.bat000.processor.PersonItemProcessor;
import com.cml.learning.module.bat000.reader.PersonReader;
import com.cml.learning.module.bat000.writer.PersonWriter;

@Configuration
@EnableBatchProcessing
public class Bat000Configuration {

	private static final Logger log = LoggerFactory.getLogger(Bat000Configuration.class);

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	private PersonWriter personWriter;

	// tag::readerwriterprocessor[]
	@Bean
	public ItemReader<Person> reader() {
		return new PersonReader();
	}

	@Bean
	public PersonItemProcessor processor() {
		return new PersonItemProcessor();
	}

	// end::readerwriterprocessor[]

	// tag::jobstep[]
	@Bean
	public Job importUserJob(JobCompletionNotificationListener listener) {
		return jobBuilderFactory.get("importUserJob").incrementer(new RunIdIncrementer()).listener(listener).flow(step1()).end().build();
	}

	@Bean
	public Job secondJob(JobCompletionNotificationListener listener) {
		return jobBuilderFactory.get("secondJob").incrementer(new RunIdIncrementer()).listener(listener).flow(step2()).end().build();
	}

	@Bean
	public Step step2() {
		return stepBuilderFactory.get("step2").<Person, Person> chunk(2).reader(reader()).processor(processor()).writer(personWriter).build();
	}

	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1").<Person, Person> chunk(2).reader(reader()).processor(processor()).writer(personWriter).build();
	}
	// end::jobstep[]
}