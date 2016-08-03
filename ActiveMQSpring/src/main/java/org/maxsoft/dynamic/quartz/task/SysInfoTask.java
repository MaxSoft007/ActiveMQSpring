package org.maxsoft.dynamic.quartz.task;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.maxsoft.dynamic.quartz.pojo.ScheduleJob;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * ����ʵ��
 * @author zhaoyl
 * @date May 3, 2015
 */
public class SysInfoTask implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		ScheduleJob scheduleJob = (ScheduleJob)context.getMergedJobDataMap().get("scheduleJob");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy��MM��dd�� HHʱmm��ss��");    
	    System.out.println("��ʱ------>�������� = [" + scheduleJob.getName() + "]"+ " �� " + dateFormat.format(new Date())+" ʱ����"); 
	}
}
