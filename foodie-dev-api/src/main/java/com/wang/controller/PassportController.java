package com.wang.controller;

import com.wang.pojo.Users;
import com.wang.pojo.bo.UserBO;
import com.wang.service.UserService;
import com.wang.utils.CookieUtils;
import com.wang.utils.IMOOCJSONResult;
import com.wang.utils.JsonUtils;
import com.wang.utils.MD5Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @program: foodie-dev
 * @description: 通行证，UserService的Controller
 * @author: Mr.Wang
 * @create: 2020-03-24 10:54
 **/
@Api(value = "注册登录",tags = "用于注册登录的相关接口")
@RestController
@RequestMapping("passport")
public class PassportController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "用户名是否存在",notes = "用户名是否存在",
    httpMethod = "GET")
    @GetMapping("/usernameIsExist")
    public IMOOCJSONResult usernameIsExists(@RequestParam  String username){
        //1.判断用户名为空
        if(StringUtils.isBlank(username)){
            return IMOOCJSONResult.errorException("用户名不能为空");
        }
        //2.查找注册的用户名是否存在
        boolean isExist = userService.queryUsernameIsExist(username);
        if (isExist ){
            return IMOOCJSONResult.errorException("用户名已存在");
        }
        //3.请求成功，用户名没有重复
        return IMOOCJSONResult.ok();
    }
    @ApiOperation(value = "用户名注册",notes = "用户名注册",
            httpMethod = "POST")
    @PostMapping("/regist")
    public IMOOCJSONResult regist(@RequestBody UserBO userBO, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String username = userBO.getUsername();
        String password = userBO.getPassword();
        String confirm = userBO.getConfirmPassword();

        //0.用户名和密码必须不为空
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)
                || StringUtils.isBlank(confirm)) {
            return IMOOCJSONResult.errorException("用户名和密码不能为空");
        }
        //1.查询用户名是否存在
        boolean isExist = userService.queryUsernameIsExist(username);
        if (isExist) {
            return IMOOCJSONResult.errorException("用户名已经存在");
        }
        //2.密码长度不能小于6位
        if (password.length() < 6) {
            return IMOOCJSONResult.errorException("密码长度不能小于6位");
        }
        //3.判断两次密码是否一致
        if (!password.equals(confirm)) {
            return IMOOCJSONResult.errorException("两次密码输入不一致");
        }
        //4.实现注册
        Users userRusult = userService.createUser(userBO);

        //设置cookie
        userRusult=setNullProperty(userRusult);

        CookieUtils.setCookie(request,response,"user", JsonUtils.objectToJson(userRusult),true);

        return IMOOCJSONResult.ok();
    }

    @ApiOperation(value = "用户登录",notes = "用户登录",
            httpMethod = "POST")
    @PostMapping("/login")
    public IMOOCJSONResult login(@RequestBody UserBO userBO, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String username = userBO.getUsername();
        String password = userBO.getPassword();

        //0.用户名和密码必须不为空
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            return IMOOCJSONResult.errorException("用户名和密码不能为空");
        }

        //4.实现登录
        Users userRusult = userService.queryUserForLogin(username,
                MD5Utils.getMD5Str(password));

        if (userRusult == null){
            return IMOOCJSONResult.errorException("用户名或密码错误");
        }

        //设置cookie
        userRusult=setNullProperty(userRusult);

        CookieUtils.setCookie(request,response,"user", JsonUtils.objectToJson(userRusult),true);


        return IMOOCJSONResult.ok(userRusult);
    }
    private Users setNullProperty(Users userRusult){
        userRusult.setPassword(null);
        userRusult.setMobile(null);
        userRusult.setEmail(null);
        userRusult.setCreatedTime(null);
        userRusult.setUpdatedTime(null);
        userRusult.setBirthday(null);
        return userRusult;
    }
    @ApiOperation(value = "退出登录",notes = "退出登录",
            httpMethod = "POST")
    @PostMapping("/logout")
    public IMOOCJSONResult logout(HttpServletRequest request, HttpServletResponse response,@RequestParam String userId){

        //清除用户的相关信息的cookie
        CookieUtils.deleteCookie(request , response ,"user");

        //TODO 用户退出登录需要清空购物车
        //TODO 分布式会话中需要清空用户数据

        return IMOOCJSONResult.ok();
    }


}
