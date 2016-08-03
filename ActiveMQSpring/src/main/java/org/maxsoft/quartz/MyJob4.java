package org.maxsoft.quartz;

import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class MyJob4 extends QuartzJobBean{

	@SuppressWarnings("unused")
	private int timeout;  
	@SuppressWarnings("unused")
	private static int i = 0;  
	//调度工厂实例化后，经过timeout时间开始执行调度  
	public void setTimeout(int timeout) {  
		this.timeout = timeout;  
	}  
	
	@Override
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		System.out.println("继承了特定的类QuartzJobBean的任务--->" + "{ " + context.toString() + " }" + new Date());
	}

}
