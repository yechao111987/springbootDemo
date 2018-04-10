package com;

import com.api.service.TestSystemService;
import com.reposity.mysql.apitest.TestSystem;
import com.reposity.mysql.apitest.TestSystemDao;
import com.reposity.mysql.dao.UserDao;
import com.yechao.springboot.demo.Bookbean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    @Autowired
    private TestSystemDao testSystemDao;
    @Autowired
    private TestSystemService testSystemService;

    private static Logger logger = LoggerFactory.getLogger(DemoApplication.class);


    public static void main(String[] args) {
//        SpringApplication.run(DemoApplication.class, args);
        new SpringApplicationBuilder(DemoApplication.class).web(true).run(args);
    }

    @RequestMapping(value = "/test1", produces = "text/plain;charset=UTF-8")
    String indextest() {
        String str = "My Name is " + appname + "," + "age is " + age + " !";
        return "test1";
    }

    @RequestMapping(value = "/sdktest", produces = "text/plain;charset=UTF-8")
    String sdktest(HttpServletResponse response, HttpServletRequest request) {
        Cookie cookie=new Cookie("yechao","yechaovalue");
        cookie.setMaxAge(600);
        response.addCookie(cookie);
        return "sdktest";
    }

    @RequestMapping(value = "/")
    public String index(ModelMap map) {
        map.addAttribute("host", "121212");
        String str = "My Name is " + appname + "," + "age is " + age + " !";
        return "index";
    }

    @RequestMapping(value = "/index")
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
    public String toTables(String name, String password,Model model) {
        logger.info("/user/login接收参数name={},password={}", name, password);
//        return userDao.countByNameAndPassword(name, password);
        Page<TestSystem> pages=testSystemService.queryByPage(0,100);
        model.addAttribute("data",pages.getContent());
        return "tables";
    }

    @GetMapping("/sdk.js")
    public String toSdkJs(Model model, HttpServletResponse response) {
        model.addAttribute("tenantCode", "yechao");
//        logger.info("/user/login接收参数name={},password={}", name, password);
//        return userDao.countByNameAndPassword(name, password);
        //设置响应信息，不设置无法调用js内容
        response.setHeader("Content-Type","application/javascript;charset=UTF-8");
        return "sdk";
    }
}
