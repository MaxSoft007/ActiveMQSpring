package org.maxsoft.quartz;
import java.util.Date;

public class MyJob1 {

	public void execute(){
		System.out.println("频次任务--->现在时间是：" + new Date().getTime());
	}
}