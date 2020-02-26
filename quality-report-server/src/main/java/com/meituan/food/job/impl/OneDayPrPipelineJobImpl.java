package com.meituan.food.job.impl;

import com.meituan.food.extract.IOneDayPrPipelineExtract;
import com.meituan.food.job.IOneDayPrPipelineJob;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDate;

@Component
public class OneDayPrPipelineJobImpl implements IOneDayPrPipelineJob {

    @Resource
    private IOneDayPrPipelineExtract oneDayPrPipelineExtract;

    @Override
    public void sync() {
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.plusDays(-1);
        oneDayPrPipelineExtract.UpdatePrPipelineData(yesterday);
        }


}
