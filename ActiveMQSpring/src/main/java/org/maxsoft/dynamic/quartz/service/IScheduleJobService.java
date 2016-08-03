package org.maxsoft.dynamic.quartz.service;

import java.util.List;

import org.maxsoft.dynamic.quartz.pojo.ScheduleJob;
import org.quartz.JobDetail;

/**
 * �������ҵ��ӿ�
 * @author zhaoyl
 * @date May 3, 2015
 */
public interface IScheduleJobService {

	/**
	 * @function ��Ӷ�ʱ����
	 * @param scheduleJob
	 */
	public void add(ScheduleJob scheduleJob);
	
	/**
	 * @function ��ȡ���е�JobDetails
	 * @return
	 */
	public List<JobDetail> getJobs();
	
	/**
	 * @function ��ȡ���е�ScheduleJob
	 * @return
	 */
	public List<ScheduleJob> getAllScheduleJob();
	
	/**
	 * @function ��ȡ���е������е�ScheduleJob
	 * @return
	 */
	public List<ScheduleJob> getAllRuningScheduleJob();
	
	/**
	 * @function ��ȡ���еĴ�����
	 * @return
	 */
	List<ScheduleJob> getTriggersInfo();
	
	/**
	 * @function ֹͣ����
	 * @param name
	 * @param group
	 */
	public void stopJob(String name, String group);
	
	/**
	 * @function ��������
	 * @param name
	 * @param group
	 */
	public void restartJob(String name, String group);
	
	/**
	 * @function ����ִ��һ������
	 * @param name
	 * @param group
	 */
	public void startNowJob(String name, String group);
	
	/**
	 * @function ɾ������
	 * @param name
	 * @param group
	 */
	public void delJob(String name, String group);
	
	/**
	 * @function �޸Ĵ�����
	 * @param name
	 * @param group
	 * @param cron
	 */
	public void modifyTrigger(String name,String group,String cron);
	
	/**
	 * @function ���Ƶ�����
	 */
	public void stopScheduler();
	
}
