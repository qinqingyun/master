package com.meituan.food.extract.impl;

import com.meituan.food.ApplicationLoader;
import com.meituan.food.extract.ICOEDataExtract;
import groovy.util.logging.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.text.ParseException;

import static org.junit.Assert.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationLoader.class)
public class COEDataExtracterTest {

    @Resource
    private ICOEDataExtract coeDataExtract;

    @Test
    public void getCOEData() throws ParseException {
        coeDataExtract.getCOEData("2020-01-01","2020-03-02");
    }
}