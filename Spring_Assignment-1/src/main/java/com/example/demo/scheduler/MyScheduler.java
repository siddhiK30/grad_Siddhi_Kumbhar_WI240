package com.example.demo.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MyScheduler {

    @Scheduled(fixedRate = 10000)  
    public void runTask() {
        System.out.println("Helloooooo  Alllll !!!!!!!!!!!!...");
    }
    @Scheduled(cron = "*/20 * * * * ?")
    public void runEvery20Seconds() {
        System.out.println("Running every 20 seconds...");
    }

}
