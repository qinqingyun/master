package com.meituan.food.job.impl;

import com.meituan.food.extract.IMailDataDaysExtract;
import com.meituan.food.job.IOneWeekEfficiencyJob;
import com.sankuai.meituan.org.queryservice.exception.MDMThriftException;
import org.apache.thrift.TException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class OneWeekEfficiencyJobImpl implements IOneWeekEfficiencyJob {
    @Resource
    private  IMailDataDaysExtract dataDaysExtract;

    @Override
    public void sync() throws MDMThriftException, TException {
        LocalDate firstDate = LocalDate.now().minusDays(8);
        LocalDate lastDate = LocalDate.now().minusDays(2);

        String startDate = firstDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String endDate = lastDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        dataDaysExtract.extractEfficiencyData4Days(startDate,endDate);
    }
}
