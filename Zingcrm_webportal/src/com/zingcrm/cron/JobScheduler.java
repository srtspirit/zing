package com.zingcrm.cron;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class JobScheduler {
	@Scheduled(fixedRate = 3000) //30 minutes
	public void resendJms(){
		System.out.println("hui");
	}
}
