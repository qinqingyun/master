package com.meituan.food.web;

import com.meituan.food.mapper.*;
import com.meituan.food.po.LeakRatePO;
import com.meituan.food.po.RestaurantXueCheng;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class TestController {

   /* @Resource
    private RestaurantXueChengPOMapper restaurantXueChengPOMapper;*/

   @Autowired
   private RestaurantXueChengMapper restaurantXueChengMapper;

   @Resource
   private BCrashRatePOMapper bCrashRatePOMapper;

   @Resource
   private MomaCrashRatePOMapper momaCrashRatePOMapper;

   @Resource
   private LeakRatePOMapper leakRatePOMapper;

   @Resource
   private IssuePOMapper issuePOMapper;

   @Resource
   private CrashRatePOMapper crashRatePOMapper;

    @GetMapping("/hello/{name}")
    public String hello(@PathVariable String name) {
        return "hello world," + name;
    }

    @GetMapping("/xuecheng")
    public String xuechengEx(@RequestParam("mis") String mis,@RequestParam("xuechengdate") String xuechengDate){
        RestaurantXueCheng restaurantXueChengPO = restaurantXueChengMapper.selectByPrimaryMis(mis,xuechengDate);
        return restaurantXueChengPO.toString();
    }

    @GetMapping("/delete/bcrashrate")
    public void deleteBCrashRate(@RequestParam("id") int id){
        bCrashRatePOMapper.deleteByPrimaryKey(id);
    }


    @GetMapping("/delete/moma")
    public void deleteMoma(@RequestParam("id")int id){
        momaCrashRatePOMapper.deleteByPrimaryKey(id);
    }

    @GetMapping("/delete/leakrate")
    public void deleteLeakRate(@RequestParam("id") int id){
        leakRatePOMapper.deleteByPrimaryKey(id);
    }

    @GetMapping("/delete/issue")
    public void deleteIssue(@RequestParam("id") int id){
        issuePOMapper.deleteByPrimaryKey(id);
    }

    @GetMapping("/delete/ccrash")
    public void deleteCCrashRate(@RequestParam("id") int id){
        crashRatePOMapper.deleteByPrimaryKey(id);
    }

}
