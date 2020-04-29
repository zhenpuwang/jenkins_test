package com.wang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @program: foodie-dev
 * @description: 启动类
 * @author: Mr.Wang
 * @create: 2020-03-22 19:52
 **/
@SpringBootApplication
// 扫描 mybatis 通用mapper所在的包
@MapperScan(basePackages = "com.wang.mapper")
// 扫描所有包以及相关组件包
@ComponentScan(basePackages = {"com.wang","com.wang.org"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
