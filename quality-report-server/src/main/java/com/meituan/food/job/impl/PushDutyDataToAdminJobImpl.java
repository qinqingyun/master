package com.meituan.food.job.impl;

import com.meituan.food.extract.IOneDayDutyDataExtract;
import com.meituan.food.job.IPushDutyDataToAdminJob;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDate;

@Component
public class PushDutyDataToAdminJobImpl implements IPushDutyDataToAdminJob {

    @Resource
    private IOneDayDutyDataExtract oneDayDutyDataExtract;
    @Override
    public void pushData() {
        LocalDate now=LocalDate.now();
        oneDayDutyDataExtract.pushToAdmin(now);
    }
}
