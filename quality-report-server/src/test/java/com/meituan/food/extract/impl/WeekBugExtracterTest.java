package com.meituan.food.extract.impl;

import com.meituan.food.ApplicationLoader;
import com.meituan.food.extract.IWeekBugDataExtract;
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
public class WeekBugExtracterTest {

    @Resource
    private IWeekBugDataExtract weekBugDataExtract;

    @Test
    public void extractData4Week() {
        LocalDate day=LocalDate.now().minusDays(4);
        weekBugDataExtract.extractData4Week(day,day);
    }
}