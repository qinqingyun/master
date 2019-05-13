package com.meituan.food.web;

import com.meituan.food.mapper.WeekBugDetailPOMapper;
import com.meituan.food.mapper.WeekBugTotalCountPOMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/bug")
public class WeekBugController {

    @Resource
    public WeekBugTotalCountPOMapper weekBugTotalCountPOMapper;

    @Resource
    public WeekBugDetailPOMapper weekBugDetailPOMapper;

    @GetMapping("/count/delete")
    public String deleteWeekBugData(@RequestParam("id") int id){
        weekBugTotalCountPOMapper.deleteLessByPrimaryKey(id);
        return "OK!";
    }

    @GetMapping("/detail/delete")
    public String deleteWeekBugDetailData(@RequestParam("id") int id){
        weekBugDetailPOMapper.deleteLessByPrimaryKey(id);
        return "OK!";
    }
}
