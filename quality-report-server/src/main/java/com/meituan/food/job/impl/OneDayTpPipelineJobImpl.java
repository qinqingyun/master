package com.meituan.food.job.impl;

import com.meituan.food.extract.IOneDayTpPipelineExtract;
import com.meituan.food.job.IOneDayTpPipelineJob;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDate;

@Component
public class OneDayTpPipelineJobImpl implements IOneDayTpPipelineJob {

    @Resource
    private IOneDayTpPipelineExtract oneDayTpPipelineExtract;

    @Override
    public void sync() {
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.plusDays(-1);
        oneDayTpPipelineExtract.UpdateTpPipelineData(yesterday);
        }


}
