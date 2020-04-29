package com.wang.service;

import com.wang.pojo.Carousel;
import com.wang.pojo.UserAddress;
import com.wang.pojo.bo.AddressBO;

import java.util.List;

/**
 * @program: foodie-dev
 * @description: 地址接口
 * @author: Mr.Wang
 * @create: 2020-03-27 09:31
 **/
public interface AddressService {

    /*
     * 功能描述: 根据用户id查询用户的收货地址
     * @Param: isShow 是否展示
     * @Return: List<Carousel> 轮播图列表
     * @Author: lenovo
     * @Date: 2020-03-27 9:33
     */
    
    public List<UserAddress> queryAll(String userId);

    /*
     * 功能描述: 用户新增地址
     * @Param: AddressBO 前端传来后端的BO
     * @Return: void
     * @Author: lenovo
     * @Date: 2020-04-06 13:56
     */

    public void addNewUserAddress(AddressBO addressBO);

    /*
     * 功能描述: 用户修改地址
     * @Param: AddressBO 前端传来后端的BO
     * @Return: void
     * @Author: lenovo
     * @Date: 2020-04-06 13:56
     */
    public void updateUserAddress(AddressBO addressBO);

    /**
     * 根据用户id和地址id，删除对应的用户地址信息
     * @param userId
     * @param addressId
     */
    public void deleteUserAddress(String userId, String addressId);

    /**
     * 修改默认地址
     * @param userId
     * @param addressId
     */
    public void updateUserAddressToBeDefault(String userId, String addressId);

    /**
     * 根据用户id和地址id，查询具体的用户地址对象信息
     * @param userId
     * @param addressId
     * @return
     */
    public UserAddress queryUserAddres(String userId, String addressId);
}
