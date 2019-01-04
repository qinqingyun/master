package com.meituan.food.web;

import com.meituan.food.mapper.RestaurantXueChengPOMapper;
import com.meituan.food.po.RestaurantXueChengPO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TestController {

    @Resource
    private RestaurantXueChengPOMapper restaurantXueChengPOMapper;

    @GetMapping("/hello/{name}")
    public String hello(@PathVariable String name) {
        return "hello world," + name;
    }

    @GetMapping("/xuecheng")
    public String xuechengEx(@RequestParam("mis") String mis){
        RestaurantXueChengPO restaurantXueChengPO = restaurantXueChengPOMapper.selectByPrimaryMis("guomengyao");
        return restaurantXueChengPO.toString();
    }
}
