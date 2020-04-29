package com.wang.enums;

/**
 * @program: foodie-dev
 * @description: sex的枚举类
 * @author: Mr.Wang
 * @create: 2020-03-24 11:54
 **/
public enum  Sex {
    woman(0,"女"),
    man(1,"男"),
    secret(2,"保密");

    public final Integer type;
    public final String value;

    Sex(Integer type, String value) {
        this.type = type;
        this.value = value;
    }}
