package org.maxsoft.dynamic.quartz.service.impl;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.maxsoft.dynamic.quartz.pojo.ScheduleJob;
import org.maxsoft.dynamic.quartz.service.IScheduleJobService;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.Trigger.TriggerState;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleJobService implements IScheduleJobService {
	
	private static final Logger logger = Logger.getLogger(ScheduleJobService.class);
	
	@Autowired
	private Scheduler scheduler;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void add(ScheduleJob scheduleJob) {
		if (logger.isDebugEnabled()) {
			logger.debug("add(ScheduleJob) - start");
		}

		Class job = null;
		try {
			job = Class.forName(scheduleJob.getClassName());
		} catch (ClassNotFoundException e) {
			logger.error("add(ScheduleJob)", e);
			e.printStackTrace();
		}
		
		JobBuilder jobBuilder = JobBuilder.newJob(job);
		JobBuilder s_jobBuilder = jobBuilder.withIdentity(scheduleJob.getName(), scheduleJob.getGroup());
		JobDetail jobDetail = s_jobBuilder.build();
		jobDetail.getJobDataMap().put("scheduleJob", scheduleJob);
		Trigger trigger = null;
		if(scheduleJob.getTriggerType().equalsIgnoreCase("SimpleTrigger")){
			
			SimpleScheduleBuilder.simpleSchedule();
			SimpleScheduleBuilder builder = SimpleScheduleBuilder.repeatSecondlyForTotalCount(Integer.valueOf(scheduleJob.getCronExpression()));

			
			trigger = TriggerBuilder.newTrigger().withIdentity(scheduleJob.getName(), scheduleJob.getGroup()).startNow().withSchedule(builder).build();

			
			// 创建一个SimpleTrigger实例，指定该Trigger在Scheduler中所属组及名称。  
	        // 接着设置调度的时间规则.当前时间15秒后运行，每10秒运行一次，共运行5次  
			
	        /*SimpleTrigger trigger = (SimpleTrigger) newTrigger().withIdentity("trigger1", "group1") 
	                                .startAt(startTime).withSchedule(simpleSchedule()                                            
	                                .withIntervalInSeconds(10) 
	                                .withRepeatCount(5) 
	                                ) 
	                                .build();*/  
	          
	        //从当前时间开始,每10秒钟执行一次,直到程序结束为止  
	        /*SimpleTrigger trigger = (SimpleTrigger) newTrigger().withIdentity("trigger1", "group1") 
	                                .startAt(startTime).withSchedule(simpleSchedule()                                            
	                                .withIntervalInSeconds(10) 
	                                .repeatForever() 
	                                ) 
	                                .build();*/  
	          
	        //从当前时间开始,每一分钟执行一次,在设置withIntervalInSeconds(10)无效  
	        /*SimpleTrigger trigger = (SimpleTrigger) newTrigger().withIdentity("trigger1", "group1") 
	        .startAt(startTime).withSchedule(simpleSchedule()                                            
	        //.withIntervalInSeconds(10) 
	        .repeatMinutelyForever() 
	        ) 
	        .build();*/  
	  
	          
	        //从当前时间开始,repeatMinutelyForever(10),不带参数为每分钟执行一次,带了参数等于: 60*10  
	        /*SimpleTrigger trigger = (SimpleTrigger) newTrigger().withIdentity("trigger1", "group1") 
	        .startAt(startTime).withSchedule(simpleSchedule()    
	        .repeatMinutelyForever(10) 
	        ) 
	        .build();*/  
	          
	          
	        //从当前时间开始,每秒钟执行一次,当然带了参数等于1*? ^_^  
	        /*SimpleTrigger trigger = (SimpleTrigger) newTrigger().withIdentity("trigger1", "group1") 
	        .startAt(startTime).withSchedule(simpleSchedule()    
	        .repeatSecondlyForever() 
	        ) 
	        .build();*/  
	          
	        //repeatHourlyForever()方法是第1小时执行一次,带了参数等于1*?  
	          
	        //从当前时间开始,每1分钟执行一次,并且只执行4次  
	        /*SimpleTrigger trigger = (SimpleTrigger) newTrigger().withIdentity("trigger1", "group1") 
	        .startAt(startTime).withSchedule(simpleSchedule()    
	        .repeatMinutelyForTotalCount(5) 
	        ) 
	        .build();*/  
	          
	        //repeatSecondlyForTotalCount(5),反过来说：只执行4次,每次执行的间隔为1秒钟  
	          
	        //当前时间的加上5分钟  
	        /*Date endTime = DateBuilder.nextGivenMinuteDate(null, 5);  
	        System.out.println("开始时间: "+ sdf.format(startTime)+",结束时间: "+sdf.format(endTime));  
	          
	        //这个比较常用吧  
	        //指定从: startTime为开始时间,endTime为结束时间,规则:每2秒钟执行一次,repeatForever()方法表示重复执行,一直到结束时间  
	        SimpleTrigger trigger = (SimpleTrigger) newTrigger().withIdentity("trigger1", "group1")  
	        .startAt(startTime)   
	        .endAt(endTime)       
	        .withSchedule(  
	                simpleSchedule()  
	                .withIntervalInSeconds(2)  
	                .repeatForever()  
	        )                     
	        .build();*/         
			
			
			
		}else{
			//表达式调度构建器（可判断创建SimpleScheduleBuilder）
			CronScheduleBuilder cronSchedule = CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression());
			
			//按新的cronExpression表达式构建一个新的trigger
			TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
			TriggerBuilder<Trigger> builder = triggerBuilder.withIdentity(scheduleJob.getName(), scheduleJob.getGroup());
			TriggerBuilder<CronTrigger> c_triggerBuilder = builder.withSchedule(cronSchedule);
			
			trigger = c_triggerBuilder.build();
		}
		
		try {
			scheduler.scheduleJob(jobDetail, trigger);
		} catch (SchedulerException e) {
			logger.error("add(ScheduleJob)", e);
			e.printStackTrace();
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("add(ScheduleJob) - end");
		}
	}

	@Override
	public List<JobDetail> getJobs() {
		if (logger.isDebugEnabled()) {
			logger.debug("getJobs() - start");
		}
		
		GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
		try {
			Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
			List<JobDetail> jobDetails = new ArrayList<JobDetail>();
			for (JobKey key : jobKeys) {
				jobDetails.add(scheduler.getJobDetail(key));
			}
			return jobDetails;
		} catch (SchedulerException e) {
			logger.debug("getJobs()",e);
			e.printStackTrace();
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("getJobs() - end");
		}
		return null;
	}

	@Override
	public List<ScheduleJob> getAllScheduleJob() {
		if (logger.isDebugEnabled()) {
			logger.debug("getAllScheduleJob() - start");
		}
		List<ScheduleJob> scheduleJobList = new ArrayList<ScheduleJob>();
		GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
		try {
			Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
			for (JobKey jobKey : jobKeys) {
				List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
				for (Trigger trigger : triggers) {
					ScheduleJob job = new ScheduleJob();
					job.setName(jobKey.getName());
					job.setGroup(jobKey.getGroup());
					TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
					job.setStatus(triggerState.name());
					//获取要执行的定时任务类名
					JobDetail jobDetail = scheduler.getJobDetail(jobKey);
					job.setClassName(jobDetail.getJobClass().getName());
					//判断触发器
					if(trigger instanceof SimpleTrigger){
						SimpleTrigger simple = (SimpleTrigger)trigger;
						job.setCronExpression("重复次数:"+ (simple.getRepeatCount() == -1 ? 
								"无限" : simple.getRepeatCount()) +",重复间隔:"+(simple.getRepeatInterval()/1000L));
						job.setDescription(simple.getDescription());
					}
					if(trigger instanceof CronTrigger){
						CronTrigger cron = (CronTrigger)trigger;
						job.setCronExpression(cron.getCronExpression());
						job.setDescription(cron.getDescription()==null?("触发器:" + trigger.getKey()):cron.getDescription());
					}
					scheduleJobList.add(job);
				}
			}
			return scheduleJobList;
		} catch (SchedulerException e) {
			logger.debug("getAllScheduleJob()",e);
			e.printStackTrace();
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getAllScheduleJob() - end");
		}
		return null;
	}

	@Override
	public List<ScheduleJob> getAllRuningScheduleJob() {
		if (logger.isDebugEnabled()) {
			logger.debug("getAllRuningScheduleJob() - start");
		}
		List<ScheduleJob> scheduleJobList = null;
		
		try {
			List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
			scheduleJobList = new ArrayList<ScheduleJob>(executingJobs.size());
			for (JobExecutionContext executingJob : executingJobs) {
				ScheduleJob job = new ScheduleJob();
				JobDetail jobDetail = executingJob.getJobDetail();
				JobKey jobKey = jobDetail.getKey();
				job.setName(jobKey.getName());
				job.setGroup(jobKey.getGroup());
				Trigger trigger = executingJob.getTrigger();
				TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
				job.setStatus(triggerState.name());
				job.setClassName(jobDetail.getJobClass().getName());
				
				if(trigger instanceof SimpleTrigger){
					SimpleTrigger simple = (SimpleTrigger)trigger;
					job.setCronExpression("重复次数:"+ (simple.getRepeatCount() == -1 ? 
							"无限" : simple.getRepeatCount()) +",重复间隔:"+(simple.getRepeatInterval()/1000L));
					job.setDescription(simple.getDescription());
				}
				
				if(trigger instanceof CronTrigger){
					CronTrigger cron = (CronTrigger)trigger;
					job.setCronExpression(cron.getCronExpression());
					job.setDescription(cron.getDescription());
				}
				
				scheduleJobList.add(job);
			}
			return scheduleJobList;
		} catch (SchedulerException e) {
			logger.debug("getAllRuningScheduleJob()",e);
			e.printStackTrace();
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getAllRuningScheduleJob() - end");
		}
		return null;
	}

	@Override
	public List<ScheduleJob> getTriggersInfo() {
		if (logger.isDebugEnabled()) {
			logger.debug("getTriggersInfo() - start");
		}
		
		GroupMatcher<TriggerKey> matcher = GroupMatcher.anyTriggerGroup();

		try {
			Set<TriggerKey> Keys = scheduler.getTriggerKeys(matcher);
			List<ScheduleJob> triggers = new ArrayList<ScheduleJob>();
			for (TriggerKey key : Keys) {
				Trigger trigger = scheduler.getTrigger(key);
				ScheduleJob job = new ScheduleJob();
				job.setName(key.getName());
				job.setGroup(key.getGroup());
				job.setStatus(scheduler.getTriggerState(trigger.getKey()).name());
				if(trigger instanceof SimpleTrigger){
					SimpleTrigger simple = (SimpleTrigger)trigger;
					job.setCronExpression("重复次数:"+ (simple.getRepeatCount() == -1 ? 
							"无限" : simple.getRepeatCount()) +",重复间隔:"+(simple.getRepeatInterval()/1000L));
					job.setDescription(simple.getDescription());
				}
				
				if(trigger instanceof CronTrigger){
					CronTrigger cron = (CronTrigger)trigger;
					job.setCronExpression(cron.getCronExpression());
					job.setDescription(cron.getDescription());
				}
				triggers.add(job);
			}
			return triggers;
		} catch (SchedulerException e) {
			logger.debug("getTriggersInfo()",e);
			e.printStackTrace();
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getTriggersInfo() - end");
		}
		return null;
	}

	@Override
	public void stopJob(String name, String group) {
		JobKey key = new JobKey(name, group);
		try {
			scheduler.pauseJob(key);
		} catch (SchedulerException e) {
			logger.debug("JobKey(name, group)",e);
			e.printStackTrace();
		}
	}

	@Override
	public void restartJob(String name, String group) {
		JobKey key = new JobKey(name, group);
		try {
			scheduler.resumeJob(key);
		} catch (SchedulerException e) {
			logger.debug("restartJob(name, group)",e);
			e.printStackTrace();
		}
	}

	@Override
	public void startNowJob(String name, String group) {
		JobKey key = new JobKey(name, group);
		try {
			scheduler.triggerJob(key);
		} catch (SchedulerException e) {
			logger.error("restartJob(name, group)",e);
			e.printStackTrace();
		}
	}

	@Override
	public void delJob(String name, String group) {
		JobKey key = new JobKey(name, group);
		try {
			scheduler.deleteJob(key);
		} catch (SchedulerException e) {
			logger.error("delJob(name, group)",e);
			e.printStackTrace();
		}
	}

	@Override
	public void modifyTrigger(String name, String group, String cron) {
		TriggerKey key = TriggerKey.triggerKey(name, group);
		
		Trigger trigger = null;
		
		if("SimpleTrigger".equals("")){//频次任务
			SimpleScheduleBuilder.simpleSchedule();
			SimpleScheduleBuilder builder = SimpleScheduleBuilder.repeatSecondlyForTotalCount(10);

			
			trigger = TriggerBuilder.newTrigger().withIdentity("", "").startNow().withSchedule(builder).build();
			//TriggerBuilder.newTrigger().withIdentity("", "").startAt(new Date()).endAt(new Date("2017-12-31")).build();

		}else{
			trigger = TriggerBuilder.newTrigger()
					.withIdentity(key)
					.withSchedule(CronScheduleBuilder.cronSchedule(cron))
					.build();
		}
		
		try {
			scheduler.rescheduleJob(key, trigger);
		} catch (SchedulerException e) {
			logger.error("modifyTrigger(name, group,cron)",e);
			e.printStackTrace();
		}
	}

	@Override
	public void stopScheduler() {
		try {
			if(!scheduler.isInStandbyMode()){
				scheduler.standby();
			}
		} catch (SchedulerException e) {
			logger.error("stopScheduler()",e);
			e.printStackTrace();
		}
	}

}
