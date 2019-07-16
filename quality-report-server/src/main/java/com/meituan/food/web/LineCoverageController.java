package com.meituan.food.web;

import com.meituan.food.extract.IGetLineCoverageExtract;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/linecoverage")
public class LineCoverageController {

    @Resource
    private IGetLineCoverageExtract lineCoverageExtract;

    @GetMapping("/insert")
    public String insertLineCoverage() {
        lineCoverageExtract.getLineCoverage();
        return "OK!";
    }
}
