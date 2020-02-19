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
import java.util.Calendar;
import java.util.Date;

@RestController
@RequestMapping("/pipeline")
public class PipelineController {

    @Resource
    private IOneDayItPipelineExtract oneDayItPipelineExtract;

    @GetMapping("/insert/itdate")
    public String insertDate(@RequestParam("date") String date) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateParam = simpleDateFormat.parse(date);
        oneDayItPipelineExtract.updateItPipelineData(dateParam);
        return "OK!";
    }
    @GetMapping("/insert/it")
    public String insertFromToDate(@RequestParam("from") String start,@RequestParam("to") String end) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = simpleDateFormat.parse(start);
        Date endDate = simpleDateFormat.parse(end);
        Date temp = startDate;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        while(temp.getTime()<endDate.getTime()) {
            temp = calendar.getTime();
            oneDayItPipelineExtract.updateItPipelineData(temp);
            calendar.add(Calendar.DAY_OF_MONTH,1);
        }
        return "OK!";
    }
}
