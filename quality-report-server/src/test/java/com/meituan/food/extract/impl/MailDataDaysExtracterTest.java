package com.meituan.food.extract.impl;

import com.meituan.food.ApplicationLoader;
import com.meituan.food.extract.IMailDataDaysExtract;
import com.sankuai.meituan.org.queryservice.exception.MDMThriftException;
import groovy.util.logging.Slf4j;
import org.apache.thrift.TException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationLoader.class)
public class MailDataDaysExtracterTest {

    @Resource
    private IMailDataDaysExtract mailDataDaysExtract;

    @Test
    public void extractEfficiencyData4Days() throws TException, MDMThriftException {
        mailDataDaysExtract.extractEfficiencyData4Days("2019-01-01","2019-02-20");
    }
}