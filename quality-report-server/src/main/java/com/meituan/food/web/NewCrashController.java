package com.meituan.food.web;

import com.meituan.food.extract.INewCrashExtract;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

@RestController
@RequestMapping("/newcrash")
public class NewCrashController {

    @Resource
    public INewCrashExtract newCrashExtract;

    DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");


    @GetMapping("/update")
    public String updateAllData(){
        for (int i=122;i>=1;i--){
            System.out.println(LocalDate.now().minusDays(i));
            newCrashExtract.sync(LocalDate.now().minusDays(i));
        }
        return "OK!";
    }

    @GetMapping("/updateweek")
    public String updateAllWeekData()throws ParseException {
        newCrashExtract.syncForDays(format1.parse("2019-07-18"),format1.parse("2019-07-24"));
        newCrashExtract.syncForDays(format1.parse("2019-07-25"),format1.parse("2019-07-31"));
        newCrashExtract.syncForDays(format1.parse("2019-08-01"),format1.parse("2019-08-07"));
        newCrashExtract.syncForDays(format1.parse("2019-08-08"),format1.parse("2019-08-14"));
        newCrashExtract.syncForDays(format1.parse("2019-08-15"),format1.parse("2019-08-21"));
        newCrashExtract.syncForDays(format1.parse("2019-08-22"),format1.parse("2019-08-28"));
        newCrashExtract.syncForDays(format1.parse("2019-08-29"),format1.parse("2019-09-04"));
        newCrashExtract.syncForDays(format1.parse("2019-09-05"),format1.parse("2019-09-11"));
        newCrashExtract.syncForDays(format1.parse("2019-09-12"),format1.parse("2019-09-18"));
        newCrashExtract.syncForDays(format1.parse("2019-09-19"),format1.parse("2019-09-25"));
        newCrashExtract.syncForDays(format1.parse("2019-09-26"),format1.parse("2019-10-02"));
        newCrashExtract.syncForDays(format1.parse("2019-10-03"),format1.parse("2019-10-09"));
        newCrashExtract.syncForDays(format1.parse("2019-10-10"),format1.parse("2019-10-16"));
        newCrashExtract.syncForDays(format1.parse("2019-10-17"),format1.parse("2019-10-23"));
        newCrashExtract.syncForDays(format1.parse("2019-10-24"),format1.parse("2019-10-30"));
        newCrashExtract.syncForDays(format1.parse("2019-10-31"),format1.parse("2019-11-06"));
        newCrashExtract.syncForDays(format1.parse("2019-11-07"),format1.parse("2019-11-13"));


        return "OK!";
    }

    @GetMapping("/updatemonth")
    public String updateAllMonthData()throws ParseException{
        newCrashExtract.syncForDays(format1.parse("2019-07-15"),format1.parse("2019-07-31"));
        newCrashExtract.syncForDays(format1.parse("2019-08-01"),format1.parse("2019-08-31"));
        newCrashExtract.syncForDays(format1.parse("2019-09-01"),format1.parse("2019-09-30"));
        newCrashExtract.syncForDays(format1.parse("2019-10-01"),format1.parse("2019-10-31"));

        return "!";
    }
}
