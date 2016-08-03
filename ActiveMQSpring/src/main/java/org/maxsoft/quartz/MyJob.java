package org.maxsoft.quartz;

import java.util.Date;

public class MyJob {

	public void execute(){
		System.out.println("定时任务--->现在时间是：" + new Date());
	}
}
