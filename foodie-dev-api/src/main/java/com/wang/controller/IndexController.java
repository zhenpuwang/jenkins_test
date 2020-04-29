package com.wang.controller;

import com.wang.enums.YesOrNo;
import com.wang.pojo.Carousel;
import com.wang.pojo.Category;
import com.wang.pojo.vo.CategoryVO;
import com.wang.pojo.vo.NewItemsVO;
import com.wang.service.CarouseService;
import com.wang.service.CategoryService;
import com.wang.utils.IMOOCJSONResult;
import com.wang.utils.JsonUtils;
import com.wang.utils.RedisOperator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


/**
 * @program: foodie-dev
 * @description: 首页控制类
 * @author: Mr.Wang
 * @create: 2020-03-22 19:54
 **/
@Api(value = "首页",tags = "首页展示的相关接口")
@RestController
@RequestMapping("index")
public class IndexController {
    @Autowired
    private CarouseService carouseService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private RedisOperator redisOperator;
    /*
     * 功能描述: 获取轮播图列表
     * @Return: 轮播图列表信息
     * @Author: lenovo
     * @Date: 2020-03-27 10:35
     */

    @ApiOperation(value = "获取首页轮播图列表",notes = "获取首页轮播图列表",
            httpMethod = "GET")
    @GetMapping("/carousel")
    public IMOOCJSONResult carousel(){

        List<Carousel> list = new ArrayList<>();
        //查询缓存
        String carouselStr = redisOperator.get("carousel");
        if (StringUtils.isBlank(carouselStr)){
           list = carouseService.queryAll(YesOrNo.YES.type);

            //如果缓存中不存在，将其放入缓存
            redisOperator.set("carousel", JsonUtils.objectToJson(list));
        }else{
            list = JsonUtils.jsonToList(carouselStr,Carousel.class);
        }

        return IMOOCJSONResult.ok(list);
    }
    /**
     *（1）后台运营系统，一旦广告（轮播图）发生更改，就可以删除缓存，。然后重置
     *（2）定时重置，比如每天凌晨三点重置
     *（3）每一个轮播图都有可能是一个广告，每个广告都会有过期时间，过期了在重置
     */



    /*
     * 功能描述: 首页分类展示需求
     *      （1）第一次刷新主页查询大分类，渲染展示到主页
     *      （2）如果鼠标上移到大分类，则加载其子分类的内容，如果已经存在子分类，则不需要加载（懒加载）
     * @Param:
     * @Return:
     * @Author: lenovo
     * @Date: 2020-03-27 10:36
     */
    @ApiOperation(value = "获取商品分类（一级）",notes = "获取商品分类（一级）",
            httpMethod = "GET")
    @GetMapping("/cats")
    public IMOOCJSONResult cats(){
        //查询缓存
        String catsStr = redisOperator.get("cats");
        List<Category> list = new ArrayList<>();
        if (StringUtils.isBlank(catsStr)){
            list = categoryService.queryAllRootLevelCat();
            redisOperator.set("cats", JsonUtils.objectToJson(list));
        }else{
            list = JsonUtils.jsonToList(catsStr,Category.class);
        }


        return IMOOCJSONResult.ok(list);
    }

    @ApiOperation(value = "获取商品子分类", notes = "获取商品子分类", httpMethod = "GET")
    @GetMapping("/subCat/{rootCatId}")
    public IMOOCJSONResult subCat(
            @ApiParam(name = "rootCatId", value = "一级分类id", required = true)
            @PathVariable Integer rootCatId) {

        if (rootCatId == null) {
            return IMOOCJSONResult.errorMsg("分类不存在");
        }
        List<CategoryVO> list = new ArrayList<>();
        String subCatStr = redisOperator.get("subCat");
        if (StringUtils.isBlank(subCatStr)){
            list = categoryService.getSubCatList(rootCatId);
            redisOperator.set("subCar",JsonUtils.objectToJson(list));
        }else{
            list  = JsonUtils.jsonToList(subCatStr,CategoryVO.class);
        }

        return IMOOCJSONResult.ok(list);
    }

    @ApiOperation(value = "查询每个一级分类下的最新6条商品数据", notes = "查询每个一级分类下的最新6条商品数据", httpMethod = "GET")
    @GetMapping("/sixNewItems/{rootCatId}")
    public IMOOCJSONResult sixNewItems(
            @ApiParam(name = "rootCatId", value = "一级分类id", required = true)
            @PathVariable Integer rootCatId) {

        if (rootCatId == null) {
            return IMOOCJSONResult.errorMsg("分类不存在");
        }

        List<NewItemsVO> list = categoryService.getSixNewItemsLazy(rootCatId);
        return IMOOCJSONResult.ok(list);
    }






}
