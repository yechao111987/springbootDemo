package com.yechao.springboot.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.awt.print.Book;

@SpringBootApplication //整个Spring Boot的核心注解，它的目的就是开启Spring Boot的自动配置
@RestController   //使之变为一个Controller，然后里边提供一个地址转换方法
public class DemoApplication {

    @Value("${config.name}")
    private String appname;
    @Value(value = "${config.age}")
    private String age;

    //@Autowired
    @Resource
    private Bookbean bookbean;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @RequestMapping(value = "/", produces = "text/plain;charset=UTF-8")
    String index() {
        String str = "My Name is " + appname + "," + "age is " + age + " !";
        return str;
    }

    @RequestMapping(value = "/book", produces = "text/plain;charset=UTF-8")
    String book() {
        String str = "The name of the book is " + bookbean.getName()+ ","+"The price of the book is " + bookbean.getPrice()+ "," + ",the book's author is " + bookbean.getAuthor() + " !";
        return str;
    }
}
