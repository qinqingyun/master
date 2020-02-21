package com.meituan.food.extract.impl;

import com.meituan.food.ApplicationLoader;
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
public class GitExtracterTest {

    @Resource
    private GitExtracter gitExtracter;

    @Test
    public void extractEfficiencyData4Day() {
        LocalDate day=LocalDate.now().minusDays(2);
    }
}