package com.wang.service;

import com.wang.pojo.Users;
import com.wang.pojo.bo.UserBO;

/**
 * @program: foodie-dev
 * @description: 用户接口
 * @author: Mr.Wang
 * @create: 2020-03-24 10:44
 **/
public interface UserService {
    /*
     * 功能描述: 判断用户名是否存在
     * @Param: 用户名
     * @Return: true / false
     * @Author: lenovo
     * @Date: 2020-03-24 10:45
     */
    public boolean queryUsernameIsExist(String name);

    /*
     * 功能描述: 创建用户
     * @Param: Bo对象：用于去接收前端所传来的数据体
     * @Return: User实体类
     * @Author: lenovo
     * @Date: 2020-03-24 11:33
     */
    public Users createUser(UserBO userBo) throws Exception;

    /*
     * 功能描述: 用户登录
     * @Param: username:用户名，password:密码
     * @Return: User实体类
     * @Author: lenovo
     * @Date: 2020-03-26 11:54
     */
    public Users queryUserForLogin(String username,String password);









}
