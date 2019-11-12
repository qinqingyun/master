package com.meituan.food.extract.impl;

import com.meituan.food.ApplicationLoader;
import groovy.util.logging.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import static org.junit.Assert.*;
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationLoader.class)
public class NewCrashExtracterTest {

    @Resource
    private NewCrashExtracter newCrashExtracter;

    DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");


    @Test
    public void sync() {
        LocalDate a=LocalDate.now().minusDays(6);
        LocalDate b=LocalDate.now().minusDays(5);
        newCrashExtracter.sync(a);
        newCrashExtracter.sync(b);
    }

    @Test
    public void syncForDays() throws ParseException {
        newCrashExtracter.syncForDays(format1.parse("2019-11-04"),format1.parse("2019-11-05"));
    }
}