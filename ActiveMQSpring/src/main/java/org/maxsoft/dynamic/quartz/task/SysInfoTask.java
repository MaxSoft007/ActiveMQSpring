package org.maxsoft.dynamic.quartz.task;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.maxsoft.dynamic.quartz.pojo.ScheduleJob;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 任务实例
 * @author zhaoyl
 * @date May 3, 2015
 */
public class SysInfoTask implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		ScheduleJob scheduleJob = (ScheduleJob)context.getMergedJobDataMap().get("scheduleJob");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");    
	    System.out.println("定时------>任务名称 = [" + scheduleJob.getName() + "]"+ " 在 " + dateFormat.format(new Date())+" 时运行"); 
	}
}
