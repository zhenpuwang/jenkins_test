package com.wang.pojo.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;

/**
 * @program: foodie-dev
 * @description:
 * @author: Mr.Wang
 * @create: 2020-03-24 11:36
 **/
@ApiModel(value = "用户对象BO",description = "从客户端，由用户传入的数据封装在此entity中")
public class UserBO {
    @ApiModelProperty(value = "用户名",name = "username",example = "wang",required = true)
    private String username;
    @ApiModelProperty(value = "密码",name = "password",example = "wang",required = true)
    private String password;
    @ApiModelProperty(value = "确认密码",name = "confirmPassword",example = "wang",required = false)
    private String confirmPassword;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
