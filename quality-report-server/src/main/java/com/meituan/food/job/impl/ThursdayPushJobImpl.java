package com.meituan.food.job.impl;

import com.meituan.food.extract.IThursdayPushDaXiang;
import com.meituan.food.job.IThursdayPushDaXiangJob;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ThursdayPushJobImpl implements IThursdayPushDaXiangJob {

    @Resource
    private IThursdayPushDaXiang thursdayPushDaXiang;

    @Override
    public void sync() {
        thursdayPushDaXiang.pushDaXiang();
    }
}
