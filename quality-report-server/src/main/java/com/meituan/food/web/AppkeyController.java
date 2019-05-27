package com.meituan.food.web;

import com.meituan.food.extract.IGetAppkeyList;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@RequestMapping("/appkey")
public class AppkeyController {

    @Resource
    private IGetAppkeyList appkeyList;

    @GetMapping("/insert")
    public String insertAppkey(){
        appkeyList.getAppkeyList();
        return "OK!";
    }
}
