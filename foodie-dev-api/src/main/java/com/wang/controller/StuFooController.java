package com.wang.controller;

import com.wang.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @program: foodie-dev
 * @description: 测试
 * @author: Mr.Wang
 * @create: 2020-03-22 19:54
 **/
@ApiIgnore
@RestController
public class StuFooController {

    @Autowired
    private StuService stuService;
    @GetMapping("/getStu")
    public Object getStu(int id)
    {
        return stuService.getStuInfo(id);
    }
}
