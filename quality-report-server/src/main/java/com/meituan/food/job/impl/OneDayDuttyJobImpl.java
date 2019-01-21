package com.meituan.food.job.impl;

import com.meituan.food.extract.IOneDayDutyDataExtract;
import com.meituan.food.job.IOneDayDuttyJob;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDate;

@Component
public class OneDayDuttyJobImpl implements IOneDayDuttyJob {

    @Resource
    private IOneDayDutyDataExtract oneDayDutyDataExtract;

    @Override
    public void sync() {
    //    LocalDate day=LocalDate.now().minusDays(1);
        LocalDate day=LocalDate.now().plusDays(15);

        oneDayDutyDataExtract.extractData4Day(day);
    }

    public static void main(String[] args) {
        LocalDate day=LocalDate.now().plusDays(15);
        System.out.println(day);
    }
}
