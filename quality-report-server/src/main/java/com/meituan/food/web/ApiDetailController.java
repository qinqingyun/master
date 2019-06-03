package com.meituan.food.web;

import com.meituan.food.extract.IGetApiDetailExtract;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api")
public class ApiDetailController {
    @Resource
    private IGetApiDetailExtract apiDetailExtract;


    @GetMapping("/insert")
    public String insertAppkey(){
        apiDetailExtract.getApiDetail();
        return "OK!";
    }
}
