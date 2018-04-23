package com.schedule;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduleTask {
    private Logger logger = LoggerFactory.getLogger(ScheduleTask.class);


    @Scheduled(fixedRate = 3000)   //定时任务间隔3S,没3S轮询任务
    public void JobStatusSchedule() {
        Long currentTime = System.currentTimeMillis();
        logger.error("schedule start:" + currentTime);
    }


}
