package org.maxsoft.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.maxsoft.dynamic.quartz.pojo.ScheduleJob;
import org.maxsoft.dynamic.quartz.service.IScheduleJobService;
import org.maxsoft.dynamic.quartz.task.SendMailTask;
import org.maxsoft.dynamic.quartz.task.SysInfoTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml","classpath:ActiveMQ.xml"})
public class DynamicQuartzTest {

	@Autowired
	IScheduleJobService scheduleJobService;
	
	@Test
	public void test(){
		ScheduleJob scheduleJob = new ScheduleJob();
		scheduleJob.setName("MaxSoft");
		scheduleJob.setGroup("MaxSoftGroup");
		scheduleJob.setCronExpression("0/1 * * ? * * *");
		scheduleJob.setClassName(SysInfoTask.class.getName());
		scheduleJob.setStatus("1");
		scheduleJobService.add(scheduleJob);
//		scheduleJobService.stopJob("MaxSoft", "MaxSoftGroup");
//		scheduleJobService.restartJob("MaxSoft", "MaxSoftGroup");
		//scheduleJobService.delJob("MaxSoft", "MaxSoftGroup");
		//scheduleJobService.restartJob("MaxSoft", "MaxSoftGroup");
		//scheduleJobService.modifyTrigger("MaxSoft", "MaxSoftGroup", "0/1 * * ? * * *");
		System.out.println("----------------------");
		try {
			Thread.sleep(1000 * 300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test_insert_cron(){
		ScheduleJob scheduleJob = new ScheduleJob();
		scheduleJob.setName("MaxSoft！！Cron");
		scheduleJob.setGroup("MaxSoftGroup！！Cron");
		scheduleJob.setCronExpression("0/5 * * ? * *");
		scheduleJob.setClassName(SysInfoTask.class.getName());
		scheduleJob.setStatus("1");
		scheduleJob.setTriggerType("CronTrigger");
		scheduleJobService.add(scheduleJob);
		scheduleJobService.modifyTrigger("MaxSoft", "MaxSoftGroup", "0/1 * * ? * * *");
		System.out.println("----------------------");
		try {
			Thread.sleep(1000 * 300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test_insert_simple(){
		ScheduleJob scheduleJob = new ScheduleJob();
		scheduleJob.setName("MaxSoft！！Simple");
		scheduleJob.setGroup("MaxSoftGroup！！Simple");
		scheduleJob.setCronExpression("100");
		scheduleJob.setClassName(SendMailTask.class.getName());
		scheduleJob.setStatus("1");
		scheduleJob.setTriggerType("SimpleTrigger");
		//scheduleJobService.add(scheduleJob);
		System.out.println("----------------------");
		try {
			Thread.sleep(1000 * 300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}	
	
}


