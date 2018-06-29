package com.schedule;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.testng.annotations.Test;

@Component
public class ScheduleTask {
    private Logger logger = LoggerFactory.getLogger(ScheduleTask.class);


    @Scheduled(fixedRate = 3000 * 10)   //定时任务间隔3S,没3S轮询任务
    public void JobStatusSchedule() {
        Long currentTime = System.currentTimeMillis();
        logger.info("schedule start:" + currentTime);
    }

    @Test
    public void test1(){
        String aa="{\"id\":\"cc83ecfe-8f7f-4e41-af4b-b3cbdb6a7e69\",\"bidid\":\"22\",\"seatbid\":[{\"bid\":[{\"id\":\"123\",\"impid\":\"1\",\"price\":200}],\"seat\":\"yechao\"}]}";
        JSONObject jsonObject= JSON.parseObject(aa);
        System.out.println(JSON.toJSONString(jsonObject));
    }


}
