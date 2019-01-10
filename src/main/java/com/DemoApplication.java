package com;


import com.api.service.TestSystemService;
import com.reposity.mysql.apitest.TestSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.domain.Page;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@SpringBootApplication   //整个Spring Boot的核心注解，它的目的就是开启Spring Boot的自动配置
//@RestController   //使之变为一个Controller，然后里边提供一个地址转换方法
@Controller
@EnableCaching    //开启缓存注解
@EnableScheduling   //开启定时任务注解
public class DemoApplication {

    @Value("${config.name}")
    private String appname;
    @Value(value = "${config.age}")
    private String age;

    @Resource
    private Bookbean bookbean;

    @Autowired
    private TestSystemService testSystemService;

    private static Logger logger = LoggerFactory.getLogger(DemoApplication.class);

    @RequestMapping(value = "/app/house", produces = "text/plain;charset=UTF-8")
    String house() {
        return "app/house";
    }

    public static void main(String[] args) {
//        SpringApplication.run(DemoApplication.class, args);
        new SpringApplicationBuilder(DemoApplication.class).web(true).run(args);
    }


    @RequestMapping(value = {"/index", "/"})
    public String toIndex(ModelMap map) {
        map.addAttribute("host", "121212");
        String str = "My Name is " + appname + "," + "age is " + age + " !";
        return "index";
    }

    @RequestMapping(value = "/book", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    String book() {
        String str = "The name of the book is " + bookbean.getName() + "," + "The price of the book is " + bookbean.getPrice() + "," + ",the book's author is " + bookbean.getAuthor() + " !";
        return str;
    }

    @GetMapping("/login")
    public String login(String name, String password) {
        logger.info("/user/login接收参数name={},password={}", name, password);
//        return userDao.countByNameAndPassword(name, password);
        return "login";
    }

    @GetMapping("/tables")
    public String toTables(String name, String password, Model model) {
        logger.info("/user/login接收参数name={},password={}", name, password);
        Page<TestSystem> pages = testSystemService.queryByPage(0, 100);
        model.addAttribute("data", pages.getContent());
        return "tables";
    }

}
