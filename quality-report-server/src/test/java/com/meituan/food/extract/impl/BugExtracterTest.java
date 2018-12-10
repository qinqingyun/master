package com.meituan.food.extract.impl;

import com.meituan.food.ApplicationLoader;
import com.meituan.food.extract.IOneMonthDataExtract;
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
public class BugExtracterTest {

    @Resource(name = "bugExtracter")
    private IOneMonthDataExtract iOneMonthDataExtract;

    @Test
    public void extractData4Month() {
        iOneMonthDataExtract.extractData4Month("2018-11-01", "2018-11-30");
    }
}