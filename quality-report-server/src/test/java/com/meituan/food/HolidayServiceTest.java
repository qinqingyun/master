package com.meituan.food;

import com.sankuai.meituan.attendance.api.HolidayService;
import groovy.util.logging.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Calendar;
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
//   验证9月27是工作日
//        Calendar now=Calendar.getInstance();
//        now.setTime(date);
//        now.set(Calendar.DATE,now.get(Calendar.DATE)+10);
//        System.out.println("is :" + holidayService.getDayHoliday(now.getTime()));
        Assert.assertTrue(holidayService.isWorkDay(date));
    }
}
