package com.meituan.food;

import com.sankuai.meituan.attendance.api.HolidayService;
import groovy.util.logging.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationLoader.class)
public class HolidayServiceTest {

    @Resource
    private HolidayService holidayService;

    @Test
    public void isWorkDayTest() {
        Date date = new Date();
        System.out.println("is :" + holidayService.getDayHoliday(date));
        Assert.assertTrue(holidayService.isWorkDay(date));
    }
}
