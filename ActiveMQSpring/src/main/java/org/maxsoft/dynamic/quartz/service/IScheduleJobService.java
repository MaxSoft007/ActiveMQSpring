package org.maxsoft.dynamic.quartz.service;

import java.util.List;

import org.maxsoft.dynamic.quartz.pojo.ScheduleJob;
import org.quartz.JobDetail;

/**
 * 任务调度业务接口
 * @author zhaoyl
 * @date May 3, 2015
 */
public interface IScheduleJobService {

	/**
	 * @function 添加定时任务
	 * @param scheduleJob
	 */
	public void add(ScheduleJob scheduleJob);
	
	/**
	 * @function 获取所有的JobDetails
	 * @return
	 */
	public List<JobDetail> getJobs();
	
	/**
	 * @function 获取所有的ScheduleJob
	 * @return
	 */
	public List<ScheduleJob> getAllScheduleJob();
	
	/**
	 * @function 获取所有的运行中的ScheduleJob
	 * @return
	 */
	public List<ScheduleJob> getAllRuningScheduleJob();
	
	/**
	 * @function 获取所有的触发器
	 * @return
	 */
	List<ScheduleJob> getTriggersInfo();
	
	/**
	 * @function 停止任务
	 * @param name
	 * @param group
	 */
	public void stopJob(String name, String group);
	
	/**
	 * @function 重启任务
	 * @param name
	 * @param group
	 */
	public void restartJob(String name, String group);
	
	/**
	 * @function 立即执行一次任务
	 * @param name
	 * @param group
	 */
	public void startNowJob(String name, String group);
	
	/**
	 * @function 删除任务
	 * @param name
	 * @param group
	 */
	public void delJob(String name, String group);
	
	/**
	 * @function 修改触发器
	 * @param name
	 * @param group
	 * @param cron
	 */
	public void modifyTrigger(String name,String group,String cron);
	
	/**
	 * @function 定制调度器
	 */
	public void stopScheduler();
	
}
