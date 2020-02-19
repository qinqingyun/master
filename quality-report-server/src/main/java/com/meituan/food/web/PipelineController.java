package com.meituan.food.web;

import com.meituan.food.extract.IOneDayItPipelineExtract;
import com.meituan.food.extract.IReleaseNameExtract;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/pipeline")
public class PipelineController {

    @Resource
    private IOneDayItPipelineExtract oneDayItPipelineExtract;

    @GetMapping("/insert/it")
    public String insertReleaseName(@RequestParam("date") String date) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateParam = simpleDateFormat.parse(date);
        oneDayItPipelineExtract.updateItPipelineData(dateParam);
        return "OK!";
    }
}
