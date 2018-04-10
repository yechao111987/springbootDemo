package com.yechao.springboot.demo.test.controller;


import com.DemoApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

//https://blog.csdn.net/qq383264679/article/details/50542290
//springboot测试方法
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
public class TestUserController {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void testGetRequest() {
        Map<String, String> multiValueMap = new HashMap<>();
        multiValueMap.put("username", "yechao");//传值，但要在url上配置相应的参数
        multiValueMap.put("password", "0aa1d51b28b24742225cf000c24cc42e");//传值，但要在url上配置相应的参数
        String result = testRestTemplate.getForObject("/user/l?name={username}&password={password}", String.class, multiValueMap);
        System.out.println(result);
        Assert.assertEquals(result, "1");

    }


}
