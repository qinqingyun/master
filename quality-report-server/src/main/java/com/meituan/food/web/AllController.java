package com.meituan.food.web;

import com.dianping.lion.client.api.annotation.GET;
import com.meituan.food.mapper.EfficiencyTotalDatePOMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/all")
public class AllController {

    @Resource
    private EfficiencyTotalDatePOMapper efficiencyTotalDatePOMapper;

    @GetMapping("/delete")
    public String deleteBug(@RequestParam("id") int id){
        efficiencyTotalDatePOMapper.deleteLessPrimaryKey(id);
        return "OK!";
    }

    @GetMapping("/delete2")
    public String deleteAll(@RequestParam("id") int id){
        efficiencyTotalDatePOMapper.deleteMorePrimaryKey(id);
        return "OK!";
    }
}
