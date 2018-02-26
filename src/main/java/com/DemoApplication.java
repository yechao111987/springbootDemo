package com;

import com.reposity.mysql.dao.UserDao;
import com.yechao.springboot.demo.Bookbean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@SpringBootApplication   //整个Spring Boot的核心注解，它的目的就是开启Spring Boot的自动配置
//@RestController   //使之变为一个Controller，然后里边提供一个地址转换方法
@Controller
public class DemoApplication {

    @Value("${config.name}")
    private String appname;
    @Value(value = "${config.age}")
    private String age;

    //@Autowired
    @Resource
    private Bookbean bookbean;

    @Autowired
    private UserDao userDao;

    private static Logger logger = LoggerFactory.getLogger(DemoApplication.class);


    public static void main(String[] args) {
//        SpringApplication.run(DemoApplication.class, args);
        new SpringApplicationBuilder(DemoApplication.class).web(true).run(args);
    }

    @RequestMapping(value = "/test", produces = "text/plain;charset=UTF-8")
    String indextest() {
        String str = "My Name is " + appname + "," + "age is " + age + " !";
        return str;
    }

    @RequestMapping(value = "/")
    public String index(ModelMap map) {
        map.addAttribute("host", "121212");
        String str = "My Name is " + appname + "," + "age is " + age + " !";
        return "test1";
    }

    @RequestMapping(value = "/book", produces = "text/plain;charset=UTF-8")
    String book() {
        String str = "The name of the book is " + bookbean.getName() + "," + "The price of the book is " + bookbean.getPrice() + "," + ",the book's author is " + bookbean.getAuthor() + " !";
        return str;
    }

    @GetMapping("/user/login")
    public String login(String name, String password) {
        logger.info("/user/login接收参数name={},password={}", name, password);
//        return userDao.countByNameAndPassword(name, password);
        return "aa";
    }
}
