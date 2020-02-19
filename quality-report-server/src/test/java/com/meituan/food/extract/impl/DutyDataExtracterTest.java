package com.meituan.food.extract.impl;

import com.meituan.food.ApplicationLoader;
import com.meituan.food.extract.IOneDayDutyDataExtract;
import groovy.util.logging.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

import java.time.LocalDate;

import static org.junit.Assert.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationLoader.class)
public class DutyDataExtracterTest {

    @Resource
    private IOneDayDutyDataExtract oneDayDutyDataExtract;

    @Test
    public void pushToAdmin() {
        LocalDate today=LocalDate.now().minusDays(7);
        System.out.println(today);
        oneDayDutyDataExtract.pushToAdmin(today);
    }

    @Test
    public void extractData4Day() {
        LocalDate today=LocalDate.now().minusDays(3);
        oneDayDutyDataExtract.extractData4Day(today);
    }
}