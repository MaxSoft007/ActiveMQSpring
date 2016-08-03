package org.maxsoft.quartz;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component("myJob3")
public class MyJob3 {
	
	@Scheduled(cron = "0/10 * * * * ?")
	public void work(){
		System.out.println("×¢½âÈÎÎñ--->Hello World , I am maxsoft ... ");
	}
}
