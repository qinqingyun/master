package com.meituan.food.job.impl;

import com.meituan.food.extract.ICOEDataExtract;
import com.meituan.food.extract.ICOETdDataExtract;
import com.meituan.food.job.IOneHourJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Component
@Slf4j
public class OneHourJobImpl implements IOneHourJob {

    @Resource
    private ICOEDataExtract coeDataExtract;

    @Resource
    private ICOETdDataExtract coeDataTdExtract;


    @Override
    public void extractData4Hour() throws ParseException {

        String first = getFirst();
        String second = getSecond();

        log.info("前60天的日期为{}",first);
        log.info("当前的日期为{}",second);
        coeDataExtract.getCOEData(first,second);
    }

    public void extractDataTd4Hour() throws ParseException{

        String first = getFirst();
        String second = getSecond();

        log.info("前60天的日期为{}",first);
        log.info("当前的日期为{}",second);
        log.info("没有转换的当前的时间为{}",second.toString());
        coeDataTdExtract.getCOETdData(first,second);

    }

    public String getSecond(){
        LocalDate secondDay=LocalDate.now();
        String secondDayStr = secondDay.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return secondDayStr;
    }

    public String getFirst(){
        LocalDate firstDay=LocalDate.now().minusDays(60);
        String firstDayStr = firstDay.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return firstDayStr;
    }
}
