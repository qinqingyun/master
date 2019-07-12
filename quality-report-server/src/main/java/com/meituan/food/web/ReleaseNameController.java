package com.meituan.food.web;

import com.meituan.food.extract.IReleaseNameExtract;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/release")
public class ReleaseNameController {

    @Resource
    private IReleaseNameExtract releaseNameExtract;

    @GetMapping("/insert")
    public String insertReleaseName(){
        releaseNameExtract.sync();
        return "OK!";
    }
}
