package com.wang.service.impl;

import com.wang.mapper.CategoryMapper;
import com.wang.mapper.CategoryMapperCustom;
import com.wang.pojo.Category;
import com.wang.pojo.vo.CategoryVO;
import com.wang.pojo.vo.NewItemsVO;
import com.wang.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: foodie-dev
 * @description: 分类展示实现类
 * @author: Mr.Wang
 * @create: 2020-03-27 09:34
 **/
@Service
public class CategorylServiceImpl implements CategoryService {

    //注入通用mapper
    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private CategoryMapperCustom categoryMapperCustom;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Category> queryAllRootLevelCat() {

        Example example = new Example(Category.class);

        Example.Criteria criteria = example.createCriteria();

        criteria.andEqualTo("type",1);

        List<Category> result = categoryMapper.selectByExample(example);

        return result;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<CategoryVO> getSubCatList(Integer rootCatId) {
        return categoryMapperCustom.getSubCatList(rootCatId);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<NewItemsVO> getSixNewItemsLazy(Integer rootCatId) {

        Map<String, Object> map = new HashMap<>();
        map.put("rootCatId", rootCatId);

        return categoryMapperCustom.getSixNewItemsLazy(map);
    }
}
