package com.cml.learning.framework.processor;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemProcessor;

public abstract class BaseProcessor<I, O> implements ItemProcessor<I, O> {

	protected StepExecution stepExecution;

	@BeforeStep
	public void saveStepExecution(StepExecution stepExecution) {
		this.stepExecution = stepExecution;
	}

	@Override
	final public O process(I input) throws Exception {
		JobParameters params = stepExecution.getJobParameters();
		ExecutionContext stepContext = stepExecution.getExecutionContext();

		return doProcess(input, params, stepContext);
	}

	public abstract O doProcess(I input, JobParameters params, ExecutionContext stepContext) throws Exception;
}
