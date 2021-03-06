package com.wang.controller;

import com.wang.pojo.bo.ShopcartBO;
import com.wang.utils.IMOOCJSONResult;
import com.wang.utils.JsonUtils;
import com.wang.utils.RedisOperator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Api(value = "购物车接口controller", tags = {"购物车接口相关的api"})
@RequestMapping("shopcart")
@RestController
public class ShopcatController extends BaseController{
    @Autowired
    private RedisOperator redisOperator;
    @ApiOperation(value = "添加商品到购物车", notes = "添加商品到购物车", httpMethod = "POST")
    @PostMapping("/add")
    public IMOOCJSONResult add(
            @RequestParam String userId,
            @RequestBody ShopcartBO shopcartBO,
            HttpServletRequest request,
            HttpServletResponse response
    ) {

        if (StringUtils.isBlank(userId)) {
            return IMOOCJSONResult.errorMsg("");
        }

        System.out.println(shopcartBO);

        // TODO 前端用户在登录的情况下，添加商品到购物车，会同时在后端同步购物车到redis缓存
        // TODO 需要判断当前购物车中包含已经存在的商品，如果存在则累加购买数量
        String shopcartJson = redisOperator.get(FOODIE_SHOPCART+":"+userId);
        List<ShopcartBO> shopcartList = null;
        if (StringUtils.isNotBlank(shopcartJson)){
            //redis当中已经有购物车了
            shopcartList = JsonUtils.jsonToList(shopcartJson,ShopcartBO.class);
            //判断购物车中是否存在已有商品，如果存在则累加购买数量
            boolean isHaving = false;
            for (ShopcartBO sc:shopcartList
                 ) {
                String tmpSpecId = sc.getSpecId();
                if (tmpSpecId.equals(shopcartBO.getSpecId())){
                    sc.setBuyCounts(sc.getBuyCounts()+shopcartBO.getBuyCounts());
                    isHaving = true;
                }
            }
            if (!isHaving){
                shopcartList.add(shopcartBO);
            }
        }else{
            //redis中没有购物车
            shopcartList = new ArrayList<>();
            //直接加入购物车
            shopcartList.add(shopcartBO);

        }
        return IMOOCJSONResult.ok();
    }

    @ApiOperation(value = "从购物车中删除商品", notes = "从购物车中删除商品", httpMethod = "POST")
    @PostMapping("/del")
    public IMOOCJSONResult del(
            @RequestParam String userId,
            @RequestParam String itemSpecId,
            HttpServletRequest request,
            HttpServletResponse response
    ) {

        if (StringUtils.isBlank(userId) || StringUtils.isBlank(itemSpecId)) {
            return IMOOCJSONResult.errorMsg("参数不能为空");
        }

        // TODO 用户在页面删除购物车中的商品数据，如果此时用户已经登录，则需要同步删除后端购物车中的商品
        String shopcartJson = redisOperator.get(FOODIE_SHOPCART+":"+userId);
        if (StringUtils.isNotBlank(shopcartJson)){
            //redis中已经存在购物车数据
            List<ShopcartBO> shopcartList = JsonUtils.jsonToList(shopcartJson, ShopcartBO.class);
            for (ShopcartBO sc:shopcartList
                 ) {
                String tmpSpecId = sc.getSpecId();
                if (tmpSpecId.equals(itemSpecId)){
                    shopcartList.remove(sc);
                    break;
                }
            }
            //覆盖redis现有购物车数据
            redisOperator.set(FOODIE_SHOPCART+":"+userId,JsonUtils.objectToJson((shopcartList)));
        }
        return IMOOCJSONResult.ok();
    }

}
