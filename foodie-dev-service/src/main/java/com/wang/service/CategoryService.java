package com.wang.service;

import com.wang.pojo.Carousel;
import com.wang.pojo.Category;
import com.wang.pojo.vo.CategoryVO;
import com.wang.pojo.vo.NewItemsVO;

import java.util.List;

/**
 * @program: foodie-dev
 * @description: 分类展示
 * @author: Mr.Wang
 * @create: 2020-03-27 09:31
 **/
public interface CategoryService {

    /*
     * 功能描述: 查询所有一级分类
     * @Return: List<Carousel> 分类列表
     * @Author: lenovo
     * @Date: 2020-03-27 9:33
     */
    
    public List<Category>  queryAllRootLevelCat();

    /**
     * 根据一级分类id查询子分类信息
     * @param rootCatId
     * @return
     */
    public List<CategoryVO> getSubCatList(Integer rootCatId);

    /**
     * 查询首页每个一级分类下的6条最新商品数据
     * @param rootCatId
     * @return
     */
    public List<NewItemsVO> getSixNewItemsLazy(Integer rootCatId);

}
