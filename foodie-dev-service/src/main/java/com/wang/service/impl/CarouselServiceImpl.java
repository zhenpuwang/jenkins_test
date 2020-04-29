package com.wang.service.impl;

import com.wang.mapper.CarouselMapper;
import com.wang.pojo.Carousel;
import com.wang.service.CarouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @program: foodie-dev
 * @description: 轮播图实现类
 * @author: Mr.Wang
 * @create: 2020-03-27 09:34
 **/
@Service
public class CarouselServiceImpl implements CarouseService {

    //注入通用mapper
    @Autowired
    private CarouselMapper carouselMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Carousel> queryAll(Integer isShow) {
        Example example = new Example(Carousel.class);
        //设置排序，asc正序，desc倒叙
        example.orderBy("sort").desc();
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isShow",isShow);

        List<Carousel> result = carouselMapper.selectByExample(example);

        return result;
    }
}