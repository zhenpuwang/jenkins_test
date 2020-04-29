package com.wang.service;

import com.wang.pojo.Stu;

/**
 * @program: foodie-dev
 * @description: StuService接口
 * @author: Mr.Wang
 * @create: 2020-03-23 22:23
 **/
public interface StuService {

    public Stu getStuInfo(int id);

    public void saveStu();

    public void updateStu(int id);

    public void deleteStu(int id);

}
