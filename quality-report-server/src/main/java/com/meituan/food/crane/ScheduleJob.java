package com.meituan.food.crane;

import com.cip.crane.client.spring.annotation.Crane;
import com.meituan.food.Services.MailService;
import com.meituan.food.extract.IGetApiDetailExtract;
import com.meituan.food.job.*;
import com.sankuai.meituan.org.queryservice.exception.MDMThriftException;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

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

    @Resource
    private IOneMonthCrashJob oneMonthCrashJob;

    @Resource
    private IOneDayJenkinsViewsJob oneDayJenkinsViewsJob;

    @Resource
    private IWeekBugJob weekBugJob;

    @Resource
    private IOneDayNoticeJob iOneDayNoticeJob;

    @Resource
    private List<IGetApiCoverageJob> apiCoverageJobs;

    @Resource
    private IOneDayCargoJob oneDayCargoJob;

    @Resource
    private IGetApiDetailExtract getApiDetailExtract;

    @Resource
    private IUpdateApiJob updateApiJob;

    @Resource
    private IUpdateAppkeyListJob updateAppkeyListJob;

    @Resource
    private ICargoDataPushJob cargoDataPushJob;

    @Resource
    private IOneWeekOrg2EmpJob updateEmpJob;

    @Resource
    private IGetLineCoverageJob getLineCoverageJob;

    @Resource
    private IPushDutyDataToAdminJob pushDutyDataToAdminJob;

    @Resource
    private IHalfYearMailJob halfYearMailJob;

    //定时推送专项进度wiki---已暂停使用
    @Crane("one.week.sync.job")
    public void syncOneWeek() {
        log.info("one week job execute at: {}", new Date());
        oneWeekJob.sync();
    }

    //原Crash任务---已暂停使用
    @Crane("one.day.sync.job")
    public void syncOneDay() {
        log.info("one day job execute at: {}", new Date());
        oneDayJob.sync();
    }

    //月Crash任务
    @Crane("one.month.sync.job")
    public void syncOneMonth() throws ParseException {
        log.info("one month job execute at: {}", new Date());
        oneMonthJob.sync();
    }

    //原Sonar任务---已暂停使用
    @Crane("one.quarter.sync.job")
    public void syncOneQuarter(){
        log.info("one month job execute at: {}", new Date());
        oneQuarterJob.sync();
    }

    //测试邮件任务---已暂停使用
    @Crane("mail.test.job")
    public void mailTest() throws TException {
        log.info("mail job execute at: {}", new Date());
        mailService.sendMailTo();
    }

    //人效-bug数据定时任务
    @Crane("one.day.eff.job")
    public void syncOneDayEffJob() throws TException{
        log.info("one month job execute at: {}", new Date());
        oneDayEffJob.sync();
    }

    //人效-git数据定时任务
    @Crane("one.day.for.efficiency.sync.job")
    public void syncOneDayForEfficiency(){
        log.info("one day for efficiency job execute at: {}", new Date());
        oneDayForEfficiencyJob.sync();
    }

    //每日人效数据汇总任务
    @Crane("mail.sync.job")
    public void syncMail(){
        log.info("mail job execute at: {}", new Date());
        mailJob.sync();
    }

    //每周crash率任务
    @Crane("one.week.eight.sync.job")
    public void syncOneWeekEight(){
        log.info("one week job execute at: {}", new Date());
        importantProjectReviewJob.sync();
    }

    //周四推送项目review到大象--2小时/次----已暂停使用
    @Crane("thursday.push.daxiang.job")
    public void pushDaXiang(){
        log.info("push daxiang job execute at: {}", new Date());
        thursdayPushDaXiangJob.sync();
    }

    //工时数据的定时任务---已暂停使用
    @Crane("one.day.fourteen.sync")
    public void syncFourteen() throws MDMThriftException {
        log.info("oneDayFourteenJob job execute at: {}", new Date());
        oneDayFourteenJob.sync();
    }

    //节假日值班信息推送
    @Crane("one.day.duty.job")
    public void syncDuty(){
        log.info("oneDutyJob job execute at: {}", new Date());
        oneDayDuttyJob.sync();
    }

    //每周人效数据发送邮件---已暂停使用
    @Crane("one.week.efficiency.job")
    public void syncOneWeekEfficiency() throws MDMThriftException, TException {
        log.info("one week efficiency job execute at: {}", new Date());
        oneWeekEfficiencyJob.sync();
    }

    //每月人效数据邮件
    @Crane("one.month.efficiency.job")
    public void syncOneMonthEfficiency() throws MDMThriftException, TException {
        log.info("one month efficiency job execute at: {}", new Date());
        oneMonthEfficiencyJob.sync();
    }

    //季度人效邮件任务
    @Crane("one.quarter.mail.job")
    public void syncOneQMailJob() throws TException, MDMThriftException {
        log.info("one quarter mail  efficiency job execute at: {}", new Date());
        oneQMailJob.sync();
    }

    //四周平均Crash率任务
    @Crane("one.month.crash.job")
    public void syncFourWeek(){
        log.info("four week job  execute at: {}",new Date());
        oneMonthCrashJob.sync();
    }

    //更新数据库中Jenkins view与job的关系
    @Crane("one.day.jenkins.views.sync")
    public void syncJenkinsViews() {
        oneDayJenkinsViewsJob.sync();
    }

    //每天的bug情况任务
    @Crane("one.week.bug.job")
    public void syncWeekBug(){
        weekBugJob.sync();
    }

    //日报的定时任务---已暂停使用
    @Crane("one.day.night.eight.job")
    public void onedaynighteight(){ iOneDayNoticeJob.extractData4Day(); }

    //每天同步接口覆盖率任务
    @Crane("api.coverage.job")
    public void getApiCoverage(){
        List<CompletableFuture<Void>> crashExtractFutures = apiCoverageJobs.stream()
                .map(crashDataExtract -> CompletableFuture.runAsync(() -> crashDataExtract.sync()))
                .collect(Collectors.toList());
        crashExtractFutures.forEach(CompletableFuture::join);
    }

    //每天cargo数据收集
    @Crane("cargo.data.job")
    public void syncCargoAva() {
        oneDayCargoJob.sync();
    }

    //同步核心接口数据---已暂停使用
    @Crane("sync.api.status.job")
    public void syncApiData(){
        getApiDetailExtract.setApiStatus();
    }

    //每周更新接口数据
    @Crane("update.all.api.job")
    public void updateApiDate(){
        updateApiJob.sync();
    }

    //每周更新Appkey
    @Crane("update.all.appkey.job")
    public void updateAllAppkey(){
        updateAppkeyListJob.sync();
    }

    //每天推送Cargo数据
    @Crane("cargo.data.push.job")
    public void cargoDataPush(){
        cargoDataPushJob.sync();
    }

    //每周org2emp数据收集
    @Crane("com.sankuai.meishi.qa.meishireport.org2.emp.data.job")
    public void updateEmpJob(){
        updateEmpJob.sync();
    }

    //每天获取行覆盖率数据
    @Crane("line.coverage.sync.job")
    public void syncGetLineCoverage() throws InterruptedException {
        getLineCoverageJob.sync();
    }

    //节假日前推送值班人员给管理员
    @Crane("push.duty.data.to.admin")
    public void pushDutyToAdmin(){
        pushDutyDataToAdminJob.pushData();
    }

    //人效邮件-半年
    @Crane("one.half.mail.job")
    public void halfOfYearJob() throws TException, MDMThriftException {
        halfYearMailJob.sync();
    }
}
