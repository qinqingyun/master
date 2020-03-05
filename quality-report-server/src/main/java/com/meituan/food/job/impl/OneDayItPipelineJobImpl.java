package com.meituan.food.job.impl;

import com.meituan.food.extract.IOneDayItPipelineExtract;
import com.meituan.food.job.IOneDayItPipelineJob;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Component
public class OneDayItPipelineJobImpl implements IOneDayItPipelineJob {

    @Resource
    private IOneDayItPipelineExtract oneDayItPipelineExtract;

    @Override
    public void sync() {
        LocalDate today = LocalDate.now();
        oneDayItPipelineExtract.updateItPipelineData(today);

    }


}
