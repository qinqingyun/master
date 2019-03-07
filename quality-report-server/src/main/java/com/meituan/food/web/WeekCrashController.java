package com.meituan.food.web;

import com.meituan.food.mapper.WeekBCrashPOMapper;
import com.meituan.food.mapper.WeekCCrashRatePOMapper;
import com.meituan.food.mapper.WeekMomaCrashPOMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/weekcrash")
public class WeekCrashController {

    @Resource
    public WeekCCrashRatePOMapper weekCCrashRatePOMapper;

    @Resource
    public WeekMomaCrashPOMapper weekMomaCrashPOMapper;

    @Resource
    public WeekBCrashPOMapper weekBCrashPOMapper;

    @GetMapping("/delete/c")
    public void deleteWeekCCrash(@RequestParam("id") int id){
        weekCCrashRatePOMapper.deleteByPrimaryKey(id);
    }

    @GetMapping("/delete/b")
    public void deleteWeekBCrash(@RequestParam("id") int id){
        weekBCrashPOMapper.deleteByPrimaryKey(id);
    }

    @GetMapping("/delete/moma")
    public void deleteWeekMomaCrash(@RequestParam("id") int id){
        weekMomaCrashPOMapper.deleteByPrimaryKey(id);
    }
}
