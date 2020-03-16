package com.meituan.food.job.impl;

import com.meituan.food.extract.ICOEDataExtract;
import com.meituan.food.job.IGetApiCoverageJob;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class CoeDataJobImpl implements IGetApiCoverageJob {

    @Resource
    private ICOEDataExtract coeDataExtract;

    @Override
    public void sync() throws ParseException {
      /*  LocalDate firstDay=LocalDate.now().minusDays(60);
        LocalDate secondDay=LocalDate.now().minusDays(1);
        String firstDayStr = firstDay.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String secondDayStr = secondDay.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        coeDataExtract.getCOEData(firstDayStr,secondDayStr);*/
    }
}
