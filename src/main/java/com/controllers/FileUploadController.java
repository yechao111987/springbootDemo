package com.controllers;

import com.common.util.TimeStampUtil;
import com.pojo.ro.Response;
import com.reposity.mysql.dao.PdfTask;
import com.reposity.mysql.dao.PdfTaskDao;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Author yechao111987@126.com
 * @date 2018/12/12 15:09
 */
@Controller
public class FileUploadController {

    private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

    @Autowired
    private PdfTaskDao pdfTaskDao;

    @GetMapping("/upload")
    public String upload() {
        return "apiDiff/upload";
    }

    @PostMapping("/api/file/upload")
    @ResponseBody
    public Response upload(@RequestParam("file") MultipartFile file) {
        Response response = new Response();
        if (file.isEmpty()) {
            response.setCode("4000");
            response.setMessage("上传失败，file参数为空");
            return response;
        }
        String fileName = file.getOriginalFilename();
        if (!fileName.split("\\.")[1].equals("pdf")) {
            response.setCode("4001");
            response.setMessage("文件格式不正确，请上传pdf文件");
            return response;
        }
        File tFile = new File(System.getProperty("user.dir") + "/file/" + fileName);
        if (tFile.isFile() && tFile.exists()) {
            fileName = fileName.split("\\.")[0] + "_" + System.currentTimeMillis() + ".pdf";
        }
        Map<String, String> result = new HashMap<>();
        result.put("name", fileName);
        String filePath = System.getProperty("user.dir") + "/file/";
        File dest = new File(filePath + fileName);
        try {
            file.transferTo(dest);
            logger.info("上传成功");
            response.setCode("200");
            response.setMessage("上传成功");
            result.put("url", filePath + fileName);
            response.setDataResult(result);
        } catch (IOException e) {
            logger.error(e.toString(), e);
            response.setCode("500");
            response.setMessage("上传失败");
        }
        return response;
    }

    @RequestMapping(value = "/api/pdf/task/dowload/{taskId}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> downloadFile(@PathVariable String taskId, String fileName) throws IOException {
        PdfTask pdfTask = pdfTaskDao.findByTaskId(taskId);
        String filePath = System.getProperty("user.dir") + "/file/out/" + pdfTask.getDest() + "/" + fileName;
        FileSystemResource file = new FileSystemResource(filePath);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getFilename()));
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.contentLength())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new InputStreamResource(file.getInputStream()));
    }

    @Test
    public void testFile() {
        File file = new File("file/");
        String[] ss = file.list();
        for (String s : ss) {
            System.out.println(s);
        }
    }
}
