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

@Component
@Slf4j
public class OneHourJobImpl implements IOneHourJob {

    @Resource
    private ICOEDataExtract coeDataExtract;

    @Resource
    private ICOETdDataExtract coeDataTdExtract;


    LocalDate firstDay=LocalDate.now().minusDays(60);
    LocalDate secondDay=LocalDate.now();
    String firstDayStr = firstDay.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    String secondDayStr = secondDay.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));


    @Override
    public void extractData4Hour() throws ParseException {

        log.info("前60天的日期为{}",firstDayStr);
        log.info("当前的日期为{}",secondDayStr);
        coeDataExtract.getCOEData(firstDayStr,secondDayStr);
    }

    public void extractDataTd4Hour() throws ParseException{

        log.info("前60天的日期为{}",firstDayStr);
        log.info("当前的日期为{}",secondDayStr);
        log.info("没有转换的当前的时间为{}",secondDay.toString());
        coeDataTdExtract.getCOETdData(firstDayStr,secondDayStr);

    }
}
