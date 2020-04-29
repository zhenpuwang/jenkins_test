package com.wang.enums;

/**
 * @program: foodie-dev
 * @description: 是否枚举
 * @author: Mr.Wang
 * @create: 2020-03-24 11:54
 **/
public enum YesOrNo {
   NO(0,"否"),
    YES(1,"是");

    public final Integer type;
    public final String value;

    YesOrNo(Integer type, String value) {
        this.type = type;
        this.value = value;
    }}
