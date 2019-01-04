package com.meituan.food.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TestController {

   /* @Resource
    private RestaurantXueChengPOMapper restaurantXueChengPOMapper;*/

    @GetMapping("/hello/{name}")
    public String hello(@PathVariable String name) {
        return "hello world," + name;
    }

  /*  @GetMapping("/xuecheng")
    public String xuechengEx(@RequestParam("mis") String mis){
        RestaurantXueChengPO restaurantXueChengPO = restaurantXueChengPOMapper.selectByPrimaryMis(mis);
        return restaurantXueChengPO.toString();
    }*/
}
