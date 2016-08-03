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
	//���ȹ���ʵ�����󣬾���timeoutʱ�俪ʼִ�е���  
	public void setTimeout(int timeout) {  
		this.timeout = timeout;  
	}  
	
	@Override
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		System.out.println("�̳����ض�����QuartzJobBean������--->" + "{ " + context.toString() + " }" + new Date());
	}

}
