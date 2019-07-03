package com.meituan.food.extract.impl;

import com.meituan.food.ApplicationLoader;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.junit.Assert.*;
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationLoader.class)
public class Org2EmpsExtracterTest {

    @Autowired
    Org2EmpsExtracter org2EmpsExtracter;
    @Test
    public void updateEmpsData() {
        org2EmpsExtracter.updateEmpsData(LocalDate.now().minusDays(1));
    }
}