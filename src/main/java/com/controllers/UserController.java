package com.controllers;

import com.reposity.mysql.dao.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);


    @Autowired
    private UserDao userDao;

    @GetMapping("/user/get")
    @ResponseBody
    public void test() {

    }

    @GetMapping("/user/logintest")
    @ResponseBody
    public int login(String name, String password) {
        logger.info("/user/login接收参数name={},password={}", name, password);
        return userDao.countByNameAndPassword(name, password);
    }

    @RequestMapping(value = "/user/l", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String log(@RequestParam("name") String name, @RequestParam("password") String password) {
        logger.info("/user/login接收参数name={},password={}", name, password);
        System.out.println(name);
        int result=userDao.countByNameAndPassword(name,password);
        return String.valueOf(result);
    }


}
