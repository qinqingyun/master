package com.meituan.food.web;

import com.meituan.food.ApplicationLoader;
import groovy.util.logging.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationLoader.class)
public class AppkeyControllerTest {

    @Resource
    private AppkeyController appkeyController;

    @Test
    public void getAppkeyDetail() {
        appkeyController.getAppkeyDetail("com.sankuai.web.campaign.api");
    }
}