package com.meituan.food.job.impl;

import com.meituan.food.extract.IAllDDCoePushEctract;
import com.meituan.food.job.IWeekAllDDCoePushJob;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

@Component
public class WeekAllDDCoePushJobImpl implements IWeekAllDDCoePushJob {

    @Resource
    public IAllDDCoePushEctract  allDDCoePushEctract;

    @Override
    public void sync() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        LocalDate firstDay=LocalDate.now().minusDays(7);
        String firstDayStr = firstDay.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Date firstDate = sdf.parse(firstDayStr +" 00:00:00");

        LocalDate secondDay=LocalDate.now().minusDays(1);
        String secondDayStr = secondDay.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Date secondDate = sdf.parse(secondDayStr +" 23:59:59");

        allDDCoePushEctract.pushData(firstDate,secondDate,firstDayStr,secondDayStr);
    }

    @Override
    public void syncForMonth() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal_1 = Calendar.getInstance();
        cal_1.add(Calendar.MONTH, -1);
        cal_1.set(Calendar.DAY_OF_MONTH, 1);
        String firstDay = format.format(cal_1.getTime());
        Date firstDate = sdf.parse(firstDay +" 00:00:00");


        Calendar cale = Calendar.getInstance();
        cale.set(Calendar.DAY_OF_MONTH, 0);
        String lastDay = format.format(cale.getTime());
        Date secondDate = sdf.parse(lastDay +" 23:59:59");

        allDDCoePushEctract.pushData(firstDate,secondDate,firstDay,lastDay);

    }
}
