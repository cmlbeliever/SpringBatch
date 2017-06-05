package com.cml.learning.module.bat00X;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TestSchedule {
	@Scheduled(fixedRate = 1000)
	public void scheduleTest() {
		System.out.println("========================sssssssss===================" + Thread.currentThread().getId());
	}
}
