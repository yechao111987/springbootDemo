package com.controllers;

import com.reposity.mysql.dao.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PdfController {

    private static Logger logger = LoggerFactory.getLogger(PdfController.class);


    @GetMapping("/pdf/toImage")
    @ResponseBody
    public void toImage() {


    }


}
