package com.meituan.food.web;

import com.meituan.food.mapper.RestaurantXueChengMapper;
import com.meituan.food.po.RestaurantXueCheng;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TestController {

   /* @Resource
    private RestaurantXueChengPOMapper restaurantXueChengPOMapper;*/

   @Autowired
   private RestaurantXueChengMapper restaurantXueChengMapper;

    @GetMapping("/hello/{name}")
    public String hello(@PathVariable String name) {
        return "hello world," + name;
    }

    @GetMapping("/xuecheng")
    public String xuechengEx(@RequestParam("mis") String mis,@RequestParam("xuechengdate") String xuechengDate){
        RestaurantXueCheng restaurantXueChengPO = restaurantXueChengMapper.selectByPrimaryMis(mis,xuechengDate);
        return restaurantXueChengPO.toString();
    }
}
