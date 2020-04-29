package com.wang.service.impl;

import com.wang.mapper.StuMapper;
import com.wang.pojo.Stu;
import com.wang.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @program: foodie-dev
 * @description: 实现类
 * @author: Mr.Wang
 * @create: 2020-03-23 22:25
 **/
@Service
public class StuServiceImpl implements StuService {

    @Autowired
    private StuMapper stuMapper;
    /*
       事物传播 - Propagation
            REQUIRED:使用当前的事务，如果当前没有事务，则自己新建一个事务，子方法是必须运行在一个事务中的
                     如果当前存在事务，则加入这个事务，成为一个整体
                     举例：领导没饭吃，我有钱，我会自己买了自己吃；领导有的吃，会分给你一起吃。
            SUPPORTS:如果当前有事务，则使用事务；如果当前没有事物，则不使用事物。
                     举例：领导没饭吃，我也没饭吃；领导有饭吃，我也有饭吃。
            MANDATORY:该传播属性强制必须存在一个事务，如果不存在，则抛出异常。
                     举例：领导必须管饭，不管饭没饭吃，我就不乐意了，就不干了（抛出异常）
            REQUIRES_NEW:如果当前有事务，则挂起该事务，并且创建一个新事物给自己使用
                         如果当前没有事物，则同REQUIRED
                         举例：领导有饭吃，我偏不要，自己买饭吃
            NOT_SUPPORTED:如果当前有事物，则把事物挂起，自己不使用事物去运行数据库操作
                          举例：领导有饭吃，也管吃，但是我太忙了，放一边，我不吃
            NEVER:如果当前有事物存在，则抛出异常
                  举例：领导有饭给你吃，我热爱工作，抛出异常
            NESTED:如果当前有事物，则开启子事物（嵌套事务），嵌套事务是独立提交或者回滚
                   如果没有事物，则同REQUIRED
                   但是如果主事物提交，则会携带子事物一起提交
                   如果主事物回滚，则子事物会一起回滚。相反，子事物异常，则父事物可以回滚或不会滚
                   举例：领导决策不对，老板怪罪，领导带着小弟一同受罪。
                        小弟出了差错，领导可以推卸责任。
     */

    @Transactional(propagation = Propagation.SUPPORTS )
    @Override
    public Stu getStuInfo(int id) {
        return stuMapper.selectByPrimaryKey(id);
    }

    @Override
    public void saveStu() {

    }

    @Override
    public void updateStu(int id) {

    }

    @Override
    public void deleteStu(int id) {

    }
}
