package com.api;

import com.alibaba.fastjson.JSON;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class RedisTestController {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @GetMapping("/redis/add")
    @ResponseBody
    public String redisadd(@RequestParam("key") String key, @RequestParam("value") String value) {
        redisTemplate.opsForValue().set(key, value);
        return "redis add sucessful";
    }

    @GetMapping("/redis/get")
    @ResponseBody
    public String redisget(@RequestParam("key") String key) {
        Object ob = redisTemplate.opsForValue().get(key);
        return JSON.toJSONString(ob);
    }
}
