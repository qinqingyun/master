package com.meituan.food.crane;

import com.cip.crane.client.spring.annotation.Crane;
import com.meituan.food.Services.MailService;
import com.meituan.food.job.*;
import com.sankuai.meituan.org.queryservice.exception.MDMThriftException;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Slf4j
@Component
public class ScheduleJob {

    @Resource
    private IOneDayJob oneDayJob;

    @Resource
    private IOneMonthJob oneMonthJob;

    @Resource
    private IOneQuarterJob oneQuarterJob;

    @Resource
    private IOneWeekJob oneWeekJob;

    @Resource
    private MailService mailService;

    @Resource
    private IOneDayEffJob oneDayEffJob;

    @Resource
    private IOneDayForEfficiencyJob oneDayForEfficiencyJob;

    @Resource
    private IMailJob mailJob;

    @Resource
    private IImportantProjectReviewJob importantProjectReviewJob;

    @Resource IThursdayPushDaXiangJob thursdayPushDaXiangJob;

    @Resource
    private IOneDayFourteenJob oneDayFourteenJob;

    @Resource
    private IOneDayDuttyJob oneDayDuttyJob;

    @Resource
    private IOneWeekEfficiencyJob oneWeekEfficiencyJob;

    @Resource
    private IOneMonthEfficiencyJob oneMonthEfficiencyJob;

    @Resource
    private IOneQMailJob oneQMailJob;

    @Crane("one.week.sync.job")
    public void syncOneWeek() {
        log.info("one week job execute at: {}", new Date());
        oneWeekJob.sync();
    }

    @Crane("one.day.sync.job")
    public void syncOneDay() {
        log.info("one day job execute at: {}", new Date());
        oneDayJob.sync();
    }

    @Crane("one.month.sync.job")
    public void syncOneMonth() {
        log.info("one month job execute at: {}", new Date());
        oneMonthJob.sync();
    }

    @Crane("one.quarter.sync.job")
    public void syncOneQuarter(){
        log.info("one month job execute at: {}", new Date());
        oneQuarterJob.sync();
    }

    @Crane("mail.test.job")
    public void mailTest() throws TException {
        log.info("mail job execute at: {}", new Date());
        mailService.sendMailTo();
    }


    @Crane("one.day.eff.job")
    public void syncOneDayEffJob() throws TException{
        log.info("one month job execute at: {}", new Date());
        oneDayEffJob.sync();
    }

    @Crane("one.day.for.efficiency.sync.job")
    public void syncOneDayForEfficiency(){
        log.info("one day for efficiency job execute at: {}", new Date());
        oneDayForEfficiencyJob.sync();
    }

    @Crane("mail.sync.job")
    public void syncMail(){
        log.info("mail job execute at: {}", new Date());
        mailJob.sync();
    }

    @Crane("one.week.eight.sync.job")
    public void syncOneWeekEight(){
        log.info("one week job execute at: {}", new Date());
        importantProjectReviewJob.sync();
    }

    @Crane("thursday.push.daxiang.job")
    public void pushDaXiang(){
        log.info("push daxiang job execute at: {}", new Date());
        thursdayPushDaXiangJob.sync();
    }

    @Crane("one.day.fourteen.sync")
    public void syncFourteen() throws MDMThriftException {
        log.info("oneDayFourteenJob job execute at: {}", new Date());
        oneDayFourteenJob.sync();
    }

    @Crane("one.day.duty.job")
    public void syncDuty(){
        log.info("oneDutyJob job execute at: {}", new Date());
        oneDayDuttyJob.sync();
    }

    @Crane("one.week.efficiency.job")
    public void syncOneWeekEfficiency() throws MDMThriftException, TException {
        log.info("one week efficiency job execute at: {}", new Date());
        oneWeekEfficiencyJob.sync();
    }

    @Crane("one.month.efficiency.job")
    public void syncOneMonthEfficiency() throws MDMThriftException, TException {
        log.info("one month efficiency job execute at: {}", new Date());
        oneMonthEfficiencyJob.sync();
    }

    @Crane("one.quarter.mail.job")
    public void syncOneQMailJob() throws TException, MDMThriftException {
        log.info("one quarter mail  efficiency job execute at: {}", new Date());
        oneQMailJob.sync();
    }
}
