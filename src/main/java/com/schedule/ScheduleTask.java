package com.schedule;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.common.codes.PdfTaskStatus;
import com.common.util.PdfUtil;
import com.common.util.TimeStampUtil;
import com.reposity.mysql.dao.PdfTask;
import com.reposity.mysql.dao.PdfTaskDao;
import org.apache.catalina.LifecycleState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.testng.annotations.Test;

import java.util.List;

@Component
public class ScheduleTask {

    private Logger logger = LoggerFactory.getLogger(ScheduleTask.class);

    @Autowired
    private PdfTaskDao pdfTaskDao;

    /**
     * pdf转图片定时任务轮询
     */
    @Scheduled(fixedRate = 3000 * 1)   //定时任务间隔3S,没3S轮询任务
    public void pdfTaskSchedule() {
        List<PdfTask> pdfTaskList = pdfTaskDao.findAllByStatusOrderById(PdfTaskStatus.WAITING.getIndex());
        if (null == pdfTaskList || pdfTaskList.size() == 0) {
            logger.info("暂无待处理任务");
        } else {
            String currentTime = TimeStampUtil.getTimeFormate("second");
            PdfTask pdfTask = pdfTaskList.get(0);
            pdfTask.setUpdateTime(currentTime);
            logger.info("pdf transfer to image task {} start at {}", pdfTask.getTarget(), currentTime);
            pdfTask.setStatus(PdfTaskStatus.PROGRESSING.getIndex());
            //1.更新任务状态为进行中
            pdfTaskDao.save(pdfTask);

            //2.pdf文件转化为image
            try {
                PdfUtil.pdf2Image("file/" + pdfTask.getTarget(), "file/out/", 400);
                pdfTask.setStatus(PdfTaskStatus.FINUSHED.getIndex());
                currentTime = TimeStampUtil.getTimeFormate("second");
                pdfTask.setUpdateTime(currentTime);
            } catch (Exception e) {
                currentTime = TimeStampUtil.getTimeFormate("second");
                pdfTask.setUpdateTime(currentTime);
                pdfTask.setStatus(PdfTaskStatus.FAIL.getIndex());
            }
            pdfTaskDao.save(pdfTask);
        }
    }

    @Test
    public void test1() {
        String aa = "{\"id\":\"cc83ecfe-8f7f-4e41-af4b-b3cbdb6a7e69\",\"bidid\":\"22\",\"seatbid\":[{\"bid\":[{\"id\":\"123\",\"impid\":\"1\",\"price\":200}],\"seat\":\"yechao\"}]}";
        JSONObject jsonObject = JSON.parseObject(aa);
        System.out.println(JSON.toJSONString(jsonObject));
    }


}
