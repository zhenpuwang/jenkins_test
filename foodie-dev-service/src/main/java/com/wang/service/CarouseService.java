package com.wang.service;

import com.wang.pojo.Carousel;

import java.util.List;

/**
 * @program: foodie-dev
 * @description: 轮播图接口
 * @author: Mr.Wang
 * @create: 2020-03-27 09:31
 **/
public interface CarouseService {

    /*
     * 功能描述: 查询所有轮播图列表
     * @Param: isShow 是否展示
     * @Return: List<Carousel> 轮播图列表
     * @Author: lenovo
     * @Date: 2020-03-27 9:33
     */
    
    public List<Carousel>  queryAll(Integer isShow);


}
