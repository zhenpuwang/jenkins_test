package com.wang.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * @program: foodie-dev
 * @description: 测试
 * @author: Mr.Wang
 * @create: 2020-03-22 19:54
 **/
//apiignore忽略该api
@ApiIgnore
@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/set")
    public Object set(String key,String value)
    {
        redisTemplate.opsForValue().set(key,value);
        return "ok";
    }
    @GetMapping("/get")
    public String get(String key)
    {
        return (String)redisTemplate.opsForValue().get(key);
    }
    @GetMapping("/delete")
    public Object delete(String key)
    {
        redisTemplate.delete(key);
        return "ok";
    }

}
