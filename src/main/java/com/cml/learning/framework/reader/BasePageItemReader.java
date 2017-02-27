package com.cml.learning.framework.reader;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.database.AbstractPagingItemReader;
import org.springframework.beans.factory.annotation.Value;

/**
 * 
 * @author cml
 *
 * @param <T>
 */
public abstract class BasePageItemReader<T> extends AbstractPagingItemReader<T> {

	private static Log log = LogFactory.getLog(BasePageItemReader.class);

	@Value("${batch.defaultPageSize}")
	private int defaultPageSize;

	protected StepExecution stepExecution;

	@BeforeStep
	public void saveStepExecution(StepExecution stepExecution) {
		this.stepExecution = stepExecution;
	}

	/**
	 * Check mandatory properties.
	 * 
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	public void afterPropertiesSet() throws Exception {
		super.afterPropertiesSet();
		this.setPageSize(defaultPageSize);
	}

	@Override
	protected void doReadPage() {

		if (results == null) {
			results = new CopyOnWriteArrayList<T>();
		} else {
			results.clear();
		}

		JobParameters params = stepExecution.getJobParameters();
		ExecutionContext stepContext = stepExecution.getExecutionContext();

		results.addAll(this.doRead(params, stepContext));
	}

	public abstract List<T> doRead(JobParameters params, ExecutionContext stepContext);

	@Override
	protected void doJumpToPage(int itemIndex) {
		log.info("record index:" + itemIndex);
	}

	protected void doOpen(JobParameters params, ExecutionContext stepContext) throws Exception {

	}

	/*
	 * 处理记录开始
	 */
	@Override
	final protected void doOpen() throws Exception {
		log.debug("处理记录开始 ");
		super.doOpen();

		// JobParameters params = JobParametersThreadLocal.get();
		JobParameters params = stepExecution.getJobParameters();
		ExecutionContext stepContext = stepExecution.getExecutionContext();

		doOpen(params, stepContext);
	}

	/*
	 * 处理记录结束
	 */
	@Override
	protected void doClose() throws Exception {
		super.doClose();
		log.debug("处理记录结束 ");
	}

	/**
	 * STEP参数保存
	 * 
	 * @param exitStatus
	 */
	public void saveStepParameter(String key, String Value) {
		this.stepExecution.getJobExecution().getExecutionContext().putString(key, Value);
	}

	/**
	 * STEP参数保存
	 * 
	 * @param exitStatus
	 */
	public void saveStepParameter(String key, long Value) {
		this.stepExecution.getJobExecution().getExecutionContext().putLong(key, Value);
	}

	/**
	 * STEP参数保存
	 * 
	 * @param exitStatus
	 */
	public void saveStepParameter(String key, int Value) {
		this.stepExecution.getJobExecution().getExecutionContext().putInt(key, Value);
	}

	/**
	 * STEP参数保存
	 * 
	 * @param exitStatus
	 */
	public void saveStepParameter(String key, List<Object> value) {
		this.stepExecution.getJobExecution().getExecutionContext().put(key, value);
	}


	/**
	 * STEP参数取得
	 * 
	 * @param exitStatus
	 */
	public Object getStepParameter(String key) {
		return this.stepExecution.getJobExecution().getExecutionContext().get(key);
	}

	/**
	 * STEP参数取得
	 * 
	 * @param exitStatus
	 */
	public String getStringStepParameter(String key) {
		return this.stepExecution.getJobExecution().getExecutionContext().getString(key);
	}

	/**
	 * STEP参数取得
	 * 
	 * @param exitStatus
	 */
	public long getLongStepParameter(String key) {
		return this.stepExecution.getJobExecution().getExecutionContext().getLong(key);
	}

	/**
	 * STEP参数取得
	 * 
	 * @param exitStatus
	 */
	public int getIntStepParameter(String key) {
		return this.stepExecution.getJobExecution().getExecutionContext().getInt(key);
	}

	/**
	 * STEP参数递增(+1)
	 * 
	 * @param exitStatus
	 */
	public void addLongStepParameter(String key) {
		this.addLongStepParameter(key, 1);
	}

	/**
	 * STEP参数递增
	 * 
	 * @param exitStatus
	 */
	public void addLongStepParameter(String key, long cnt) {
		long value = this.getLongStepParameter(key);
		value = value + cnt;
		this.saveStepParameter(key, value);
	}

	/**
	 * STEP参数递增(+1)
	 * 
	 * @param exitStatus
	 */
	public void addIntStepParameter(String key) {
		this.addIntStepParameter(key, 1);
	}

	/**
	 * STEP参数递增
	 * 
	 * @param exitStatus
	 */
	public void addIntStepParameter(String key, int cnt) {
		int value = this.getIntStepParameter(key);
		value = value + cnt;
		this.saveStepParameter(key, value);
	}

}
