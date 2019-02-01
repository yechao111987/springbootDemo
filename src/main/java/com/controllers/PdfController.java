package com.controllers;

import com.common.codes.PdfTaskStatus;
import com.common.util.TimeStampUtil;
import com.pojo.ro.Response;
import com.pojo.vo.PdfTaskVo;
import com.reposity.mysql.dao.PdfTask;
import com.reposity.mysql.dao.PdfTaskDao;
import com.reposity.mysql.dao.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Controller
public class PdfController {

    private static Logger logger = LoggerFactory.getLogger(PdfController.class);

    @Autowired
    private PdfTaskDao pdfTaskDao;


    @GetMapping("/app/pdf")
    String toPdfPage() {
        return "app/pdf";
    }


    @GetMapping("/api/pdf/task/add")
    @ResponseBody
    public Response toImage(@RequestParam("dpi") Integer dpi, @RequestParam("file") String file) {
        logger.info("file:{},dpi:{}", file, dpi);
        Response response = new Response();
        try {
            PdfTask pdfTask = new PdfTask();
            pdfTask.setTarget(file);
            int index = file.indexOf(".");
            file = file.substring(0, index);
            pdfTask.setCreateTime(TimeStampUtil.getTimeFormate("second"));
            pdfTask.setUpdateTime(TimeStampUtil.getTimeFormate("second"));
            pdfTask.setStatus(0);
            pdfTask.setState(1);
            pdfTask.setTaskId(UUID.randomUUID().toString());
            pdfTask.setDpi(dpi);
            pdfTask.setDest(file);
            PdfTask pdfTask1 = pdfTaskDao.save(pdfTask);
            PdfTaskVo pdfTaskVo = new PdfTaskVo();
            pdfTaskVo.setTaskId(pdfTask1.getTaskId());
            pdfTaskVo.setTarget(pdfTask1.getTarget());
            pdfTaskVo.setCreateTime(pdfTask1.getCreateTime());
            pdfTaskVo.setStatus(PdfTaskStatus.getName(pdfTask1.getStatus()));
            response.setCode("200");
            response.setMessage("新增任务成功");
            response.setDataResult(pdfTaskVo);
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode("500");
            response.setMessage("新增任务失败");
        }
        return response;
    }


    @GetMapping("/api/pdf/task/list")
    @ResponseBody
    public Response list() {
        Response response = new Response();
        List<PdfTask> pdfTaskList = pdfTaskDao.findAllByState(1);
        List<PdfTaskVo> pdfTaskVoList = new ArrayList<>();
        for (PdfTask pdfTask : pdfTaskList) {
            PdfTaskVo pdfTaskVo = new PdfTaskVo();
            pdfTaskVo.setCreateTime(pdfTask.getCreateTime());
            pdfTaskVo.setTaskId(pdfTask.getTaskId());
            pdfTaskVo.setTarget(pdfTask.getTarget());
            pdfTaskVo.setStatus(PdfTaskStatus.getName(pdfTask.getStatus()));
            pdfTaskVoList.add(pdfTaskVo);
        }
        response.setCode("200");
        response.setMessage("成功");
        response.setDataResult(pdfTaskVoList);
        return response;
    }

    @GetMapping("/api/pdf/task/{taskId}")
    @ResponseBody
    public Response getTask(@PathVariable String taskId) {
        Response response = new Response();
        PdfTask pdfTask = pdfTaskDao.findByTaskId(taskId);
        if (pdfTask == null) {
            response.setCode("5001");
            response.setMessage("任务不存在");
            return response;
        }
        String path = "file/out/" + pdfTask.getDest() + "/";
        File file = new File(path);
        String[] fileNames = file.list();
        PdfTaskVo pdfTaskVo = new PdfTaskVo();
        pdfTaskVo.setDest(Arrays.asList(fileNames));
        pdfTaskVo.setStatus(PdfTaskStatus.getName(pdfTask.getStatus()));
        response.setCode("200");
        response.setMessage("success");
        response.setDataResult(pdfTaskVo);
        return response;
    }

    @GetMapping("/api/pdf/task/delete/{taskId}")
    @ResponseBody
    public Response deleteTask(@PathVariable String taskId) {
        Response response = new Response();
        PdfTask pdfTask = pdfTaskDao.findByTaskId(taskId);
        if (pdfTask == null) {
            response.setCode("5002");
            response.setMessage("删除失败");
            return response;
        }
        response.setDataResult(pdfTask);
        try {
            pdfTask.setState(0);
            pdfTask.setUpdateTime(TimeStampUtil.getTimeFormate("second"));
            pdfTaskDao.save(pdfTask);
        } catch (Exception e) {
            e.printStackTrace();
            response.setMessage("删除失败");
            response.setCode("500");
        }
        return response;


    }

}
