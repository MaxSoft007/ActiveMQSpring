package org.maxsoft.dynamic.quartz.pojo;

import java.io.Serializable;

/**
 * @function ��ʱ�����POJO
 * @author zhaoyl
 * @date May 3, 2015
 */
public class ScheduleJob implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String name;//����
	private String group;//����
	private String cronExpression;//corn���ʽ
	private String status;//״̬
	private String description;//״̬
	private String className;//Ҫִ�е����������
	private String triggerType;//����������
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getCronExpression() {
		return cronExpression;
	}
	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getTriggerType() {
		return triggerType;
	}
	public void setTriggerType(String triggerType) {
		this.triggerType = triggerType;
	}
	
}
