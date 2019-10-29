package com.meituan.food.extract.impl;

import com.meituan.food.ApplicationLoader;
import com.meituan.food.job.IUpdateAppkeyListJob;
import groovy.util.logging.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationLoader.class)
public class GetAppkeyListExtracterTest {

    @Resource
    private IUpdateAppkeyListJob updateAppkeyListJob;

    @Test
    public void getAppkeyList() {
        updateAppkeyListJob.sync();
    }
}