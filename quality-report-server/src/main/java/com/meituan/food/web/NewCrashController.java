package com.meituan.food.web;

import com.meituan.food.extract.INewCrashExtract;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/newcrash")
public class NewCrashController {

    @Resource
    public INewCrashExtract newCrashExtract;

    DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");

    @GetMapping("/update")
    public String updateAllData(@RequestParam("crashDate") String crashDate){
        LocalDate beginDateTime = LocalDate.parse(crashDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        newCrashExtract.sync(beginDateTime);
        return "OK!";
    }


   @GetMapping("/update2")
    public String updateAllData(){
        for (int i=10;i>=1;i--){
            System.out.println(LocalDate.now().minusDays(i));
            newCrashExtract.sync(LocalDate.now().minusDays(i));
        }
        return "OK!";
    }

    @GetMapping("/updateweek")
    public String updateAllWeekData()throws ParseException {
        newCrashExtract.syncForDays(format1.parse("2020-03-13"),format1.parse("2020-03-19"));
        return "OK!";
    }

    /* @GetMapping("/updatemonth")
    public String updateAllMonthData()throws ParseException{
        newCrashExtract.syncForDays(format1.parse("2019-07-15"),format1.parse("2019-07-31"));
        newCrashExtract.syncForDays(format1.parse("2019-08-01"),format1.parse("2019-08-31"));
        newCrashExtract.syncForDays(format1.parse("2019-09-01"),format1.parse("2019-09-30"));
        newCrashExtract.syncForDays(format1.parse("2019-10-01"),format1.parse("2019-10-31"));

        return "!";
    }*/
}
