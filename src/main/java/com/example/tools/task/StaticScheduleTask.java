package com.example.tools.task;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class StaticScheduleTask {

    @Scheduled(cron = "0 0 15 ? * 2,3,4,5,6 ")
    private void sendEmail(){
        System.out.println("执行任务");
    }
}
