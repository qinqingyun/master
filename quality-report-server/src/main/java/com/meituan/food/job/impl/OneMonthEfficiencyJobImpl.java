package com.meituan.food.job.impl;

import com.meituan.food.extract.ICOEDataExtract;
import com.meituan.food.extract.IMailDataDaysExtract;
import com.meituan.food.job.IOneMonthEfficiencyJob;
import com.sankuai.meituan.org.queryservice.exception.MDMThriftException;
import org.apache.thrift.TException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Component
public class OneMonthEfficiencyJobImpl implements IOneMonthEfficiencyJob {
    @Resource
    private  IMailDataDaysExtract dataDaysExtract;

    private static String startDate;
    private static String endDate;

    @Resource
    private ICOEDataExtract coeDataExtract;

    @Override
    public void sync() throws MDMThriftException, TException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal_1 = Calendar.getInstance();
        cal_1.add(Calendar.MONTH, -1);
        cal_1.set(Calendar.DAY_OF_MONTH, 1);
        startDate = format.format(cal_1.getTime());

        Calendar cale = Calendar.getInstance();
        cale.set(Calendar.DAY_OF_MONTH, 0);
        endDate = format.format(cale.getTime());
        dataDaysExtract.extractEfficiencyData4Days(startDate,endDate);

        coeDataExtract.getCOEData(startDate,endDate);
    }
}
