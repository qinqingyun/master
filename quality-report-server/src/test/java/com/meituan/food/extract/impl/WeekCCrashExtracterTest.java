package com.meituan.food.extract.impl;

import com.meituan.food.ApplicationLoader;
import com.meituan.food.extract.IOneWeekCrashExtract;
import groovy.util.logging.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.time.LocalDate;

import static org.junit.Assert.*;
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationLoader.class)
public class WeekCCrashExtracterTest {

    @Resource
    private IOneWeekCrashExtract oneWeekCrashExtract;

    @Test
    public void extractData4Week() {

        LocalDate first=LocalDate.now().minusDays(11);
        LocalDate second=LocalDate.now().minusDays(5);
        oneWeekCrashExtract.extractData4Week(first,second);
    }
}