package com.meituan.food.extract.impl;

import com.meituan.food.ApplicationLoader;
import com.sankuai.meituan.org.queryservice.exception.MDMThriftException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationLoader.class)
public class OrgToMcdDirectionExtracterTest {

    @Resource
    private OrgToMcdDirectionExtracter orgToMcdDirectionExtracter;

    @Test
    public void getAllOrgName() throws MDMThriftException {
        orgToMcdDirectionExtracter.getAllOrgName();
    }

    @Test
    public void getAllMCD() {

        orgToMcdDirectionExtracter.getAllMCD();
    }
}