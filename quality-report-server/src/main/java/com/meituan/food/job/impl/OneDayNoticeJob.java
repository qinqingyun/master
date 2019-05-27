package com.meituan.food.job.impl;

import com.meituan.food.extract.IOneDayNoticeExtract;
import com.meituan.food.extract.IThursdayPushDaXiang;
import com.meituan.food.job.IOneDayNoticeJob;
import com.meituan.food.utils.DaXiangUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class OneDayNoticeJob implements IOneDayNoticeJob{


    @Resource
    private IOneDayNoticeExtract iOneDayNoticeExtract;

    @Override
    public void extractData4Day() {
        iOneDayNoticeExtract.extractData4Day();
    }
}
