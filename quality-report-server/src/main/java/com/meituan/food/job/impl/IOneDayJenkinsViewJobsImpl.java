package com.meituan.food.job.impl;

import com.meituan.food.extract.IOneDayJenkinsViewsExtract;
import com.meituan.food.job.IOneDayJenkinsViewsJob;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by ntflc on 2019-04-26.
 */
@Component
public class IOneDayJenkinsViewJobsImpl implements IOneDayJenkinsViewsJob {

    @Resource
    private IOneDayJenkinsViewsExtract jenkinsViewExtract;

    @Override
    public void sync() {
        jenkinsViewExtract.updateJenkinsView();
    }

}
