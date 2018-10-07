package com.meituan.food.extract.impl;

import com.meituan.food.ApplicationLoader;
import com.meituan.food.extract.IDataExtract;
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
public class WeekIssueExtracterTest {

    @Resource
    private IDataExtract dataExtract;

    @Test
    public void extractData4Day() throws Exception {
        LocalDate today = LocalDate.now();
        for (int i = 0; i < 30; i++) {
            dataExtract.extractData4Day(today.minusDays(i));
        }
    }
}