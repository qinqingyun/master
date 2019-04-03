package com.meituan.food.web;

import com.meituan.food.mapper.EfficiencyBugNumPOMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/bug")
public class BugController {

    @Resource
    private EfficiencyBugNumPOMapper efficiencyBugNumPOMapper;

    @GetMapping("/delete")
    public String deleteBug(@RequestParam("id") int id){
        efficiencyBugNumPOMapper.deleteLessPrimaryKey(id);
        return "OK!";
    }
}
