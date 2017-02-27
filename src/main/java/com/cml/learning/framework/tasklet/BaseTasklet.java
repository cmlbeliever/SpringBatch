package com.cml.learning.framework.tasklet;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;

public abstract class BaseTasklet implements Tasklet {
	private StepContribution stepContribution;
	private ChunkContext chunkContext;

	/**
	 * 设定Step返回状态(此返回值不是Job的返回值)
	 * 
	 * @param exitStatus
	 */
	public void setExitStatus(ExitStatus exitStatus) {
		this.stepContribution.setExitStatus(exitStatus);
	}

	@Override
	final public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
		this.stepContribution = stepContribution;
		this.chunkContext = chunkContext;

		// 画面传入参数取得
		Map<String, Object> jobParameters = chunkContext.getStepContext().getJobParameters();
		this.doExec(jobParameters);
		return RepeatStatus.FINISHED;
	}

	public abstract void doExec(Map<String, Object> jobParameters) throws Exception;

	/**
	 * STEP参数保存
	 * 
	 * @param exitStatus
	 */
	public ExecutionContext getExecutionContext() {
		return this.chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext();
	}

	/**
	 * STEP参数保存
	 * 
	 * @param exitStatus
	 */
	public void saveStepParameter(String key, String Value) {
		this.getExecutionContext().putString(key, Value);
	}

	/**
	 * STEP参数保存
	 * 
	 * @param exitStatus
	 */
	public void saveStepParameter(String key, long Value) {
		this.getExecutionContext().putLong(key, Value);
	}

	/**
	 * STEP参数保存
	 * 
	 * @param exitStatus
	 */
	public void saveStepParameter(String key, int Value) {
		this.getExecutionContext().putInt(key, Value);
	}

	/**
	 * STEP参数保存
	 * 
	 * @param exitStatus
	 */
	public void saveStepParameter(String key, Object Value) {
		this.getExecutionContext().put(key, Value);
	}

	/**
	 * STEP参数保存
	 * 
	 * @param exitStatus
	 */
	public void saveStepParameter(String key, List<Object> value) {
		this.getExecutionContext().put(key, value);
	}

	/**
	 * STEP参数取得
	 * 
	 * @param exitStatus
	 */
	public List<Object> getListStepParameter(String key) {
		List<Object> list = new ArrayList<Object>();
		Object object = this.getExecutionContext().get(key);
		if (null == object) {
			list = (List<Object>) object;
		}
		return list;
	}

	/**
	 * STEP参数取得
	 * 
	 * @param exitStatus
	 */
	public Object getStepParameter(String key) {
		return this.getExecutionContext().get(key);
	}

	/**
	 * STEP参数取得
	 * 
	 * @param exitStatus
	 */
	public String getStringStepParameter(String key) {
		return this.getExecutionContext().getString(key);
	}

	/**
	 * STEP参数取得
	 * 
	 * @param exitStatus
	 */
	public long getLongStepParameter(String key) {
		return this.getExecutionContext().getLong(key);
	}

	/**
	 * STEP参数取得
	 * 
	 * @param exitStatus
	 */
	public int getIntStepParameter(String key) {
		return this.getExecutionContext().getInt(key);
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

	/**
	 * STEP参数 增加 （list）
	 * 
	 * @param exitStatus
	 */
	public void addListStepParameter(String key, Object value) {
		List<Object> list = this.getListStepParameter(key);
		if (!list.contains(value)) {
			list.add(value);
		}
		this.saveStepParameter(key, list);
	}

	/**
	 * STEP参数 减少 （list）
	 * 
	 * @param exitStatus
	 */
	public void removeListStepParameter(String key, Object value) {
		List<Object> list = this.getListStepParameter(key);
		if (list.contains(value)) {
			list.remove(value);
		}
		this.saveStepParameter(key, list);
	}

	/**
	 * STEP参数保存
	 * 
	 * @param exitStatus
	 */
	public void saveStepParameter(String key, Map<String, Object> value) {
		this.getExecutionContext().put(key, value);
	}

}
