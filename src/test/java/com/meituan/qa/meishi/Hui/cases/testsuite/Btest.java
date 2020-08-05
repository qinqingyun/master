package com.meituan.qa.meishi.Hui.cases.testsuite;

import com.alibaba.fastjson.JSON;
import com.dianping.lion.client.ConfigRepository;
import com.dianping.lion.client.Lion;
import com.dianping.mopayprocess.refundflow.service.RefundFlowService;
import com.dianping.unified.coupon.issue.api.UnifiedCouponIssueTrustService;
import com.dianping.unified.coupon.issue.api.dto.UnifiedCouponIssueOption;
import com.dianping.unified.coupon.issue.api.request.UnifiedCouponIssueRequest;
import com.dianping.unified.coupon.issue.api.response.UnifiedCouponIssueResponse;
import com.google.common.collect.Lists;
import com.meituan.toolchain.mario.annotation.PigeonAPI;
import com.meituan.toolchain.mario.annotation.ThriftAPI;
import com.sankuai.meituan.config.MtConfigClient;
import com.sankuai.web.campaign.assigncard.tservice.maitonhongbao.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.testng.annotations.Test;
import java.util.Map;
import java.util.Optional;

/**
 * Created by buyuqi on 2019/12/17.
 */
//申请退款接口
@Slf4j
public class Btest {
    @PigeonAPI(url = "http://service.dianping.com/mopayService/refundFlowService_1.0.0")
    private RefundFlowService refundFlowService;
    @ThriftAPI(appkey = "com.sankuai.web.campaign.assigncard")
    MaitonHongbaoTService.Iface maitonHongbaoTService;

    @PigeonAPI(url = "http://service.dianping.com/UnifiedCouponIssueTrustRemoteService/UnifiedCouponIssueTrustService_1.0.0_pigeontest")
    private UnifiedCouponIssueTrustService unifiedCouponIssueTrustService;
    @Test
    public void Btest(){

//        AppealRefundRequest request = new AppealRefundRequest();
//        request.setApp(1);
//        request.setCreditPlatform(1);
//        request.setLoginType(1);
//        request.setMobile("17610420086");
//        request.setUa("ua-test");
//        request.setOrderId(4800841605845480070l);
//        request.setDesc("test nib apply");
//        request.setOrderTime(System.currentTimeMillis());
//        request.setFingerprint("finger");
//        request.setOperator("9007199254760212");
//        request.setOrderSource(0);
//        request.setPlatform(1);
//        request.setIp("127.0.0.1");
//        request.setReason("付款错误");
//        request.setDesc("付款错误");
//        request.setTarget(RefundFlowTargetEnum.USER.getCode());
//        request.setUuid("uuid");
//        // 原路
//        request.setRouter(1);
//        request.setUserId(9007199254760212l);
//        System.out.println(JSON.toJSONString(request));
//        refundFlowService.appealRefund(request);

//        AppealRefundRequest request = new AppealRefundRequest();
//        request.setApp(1);
//        request.setCreditPlatform(1);
//        request.setLoginType(1);
//        request.setMobile("17610420086");
//        request.setUa("ua-test");
//        request.setOrderId(4800841606201380451l);
//        request.setDesc("test nib apply");
//        request.setOrderTime(System.currentTimeMillis());
//        request.setFingerprint("finger");
//        request.setOperator("5002907380");
//        request.setOrderSource(0);
//        request.setPlatform(1);
//        request.setIp("127.0.0.1");
//        request.setReason("付款错误");
//        request.setDesc("付款错误");
//        request.setTarget(RefundFlowTargetEnum.USER.getCode());
//        request.setUuid("uuid");
//        // 原路
//        request.setRouter(1);
//        request.setUserId(5002907380l);
//        System.out.println(JSON.toJSONString(request));
//        refundFlowService.appealRefund(request);


    }
    @Test
    public void faquantest() throws TException {
        MaitonHongbaoTRequest maitonHongbaoTRequest = new MaitonHongbaoTRequest();
        maitonHongbaoTRequest.setPlatform(Platform.MT);
        maitonHongbaoTRequest.setUserId(29060740);
        maitonHongbaoTRequest.setPoiId(95191712);
        maitonHongbaoTRequest.setAssignChannelTEnum(AssignChannelTEnum.MAITON);
        maitonHongbaoTRequest.setOrderId(123132131);
        maitonHongbaoTRequest.setOrderPrice(1);
        MaitonHongbaoTResponse response= maitonHongbaoTService.assignMaitonHongbao(maitonHongbaoTRequest);
        Optional<MaitonHongbaoTBean> detailOptional=response.data.stream().findFirst();
        log.info("发券接口返回======={}"+ JSON.toJSONString(detailOptional));
        String id= detailOptional.get().id;
        log.info("发券接口返回======="+ id);
    }
    @Test
    public void test(){
    Long a = Long.valueOf("740");
    long b = a%128;
        System.out.println(b);
    }
    @Test
    public void setShopPromo() throws TException {
        MaitonHongbaoTRequest maitonHongbaoTRequest = new MaitonHongbaoTRequest();
        maitonHongbaoTRequest.setPlatform(Platform.MT);
        maitonHongbaoTRequest.setUserId(29060740);
        maitonHongbaoTRequest.setPoiId(97224769);
        maitonHongbaoTRequest.setAssignChannelTEnum(AssignChannelTEnum.MAITON);
        maitonHongbaoTRequest.setOrderId(123132131);
        maitonHongbaoTRequest.setOrderPrice(1);
        MaitonHongbaoTResponse response = maitonHongbaoTService.assignMaitonHongbao(maitonHongbaoTRequest);
        Optional<MaitonHongbaoTBean> detailOptional = response.data.stream().findFirst();
        String shopPromoid = detailOptional.get().id;
        log.info("发券接口返回=======" + shopPromoid);
    }
    @Test
    public void setCouponPromo(){
        UnifiedCouponIssueRequest couponIssueRequest = new UnifiedCouponIssueRequest();
        couponIssueRequest.setUserType("MT");
        couponIssueRequest.setUnifiedCouponGroupIdList(Lists.newArrayList());
        couponIssueRequest.setCouponGroupIdList(Lists.newArrayList(763075395));
        couponIssueRequest.setOperator("qa-system");
        couponIssueRequest.setUserId(29060740);

        UnifiedCouponIssueOption option = new UnifiedCouponIssueOption();
        option.setCreditType(0);
        option.setNotifyType(0);
        UnifiedCouponIssueResponse unifiedCouponIssueResponse = unifiedCouponIssueTrustService.issueTrustCoupon(couponIssueRequest, option);
    }
    @Test void getLion(){
        ConfigRepository config = Lion.getConfigRepository("hui-order-service");
        Map<String, String> configs = config.getConfigs();
        log.info("结果返回：{}", JSON.toJSONString(config));
        log.info("配置结果：{}", JSON.toJSONString(configs));
    }
    @Test void getMCC(){
        MtConfigClient client = new MtConfigClient();
        //1.0.0及后面版本使用
        client.setModel("v2");
        //octo上申请的appkey
        client.setAppkey("com.sankuai.resv.c.i");
        //可选,可指定使用的环境
        //client.setEnv("test");
        // 配置实例的标识(id),必须在服务进程内全局唯一
        client.setId("123321123321");
        //可选，扫描注解的根目录，默认全部扫描, jar包里面的也会扫描
        //client.setScanBasePackage("自定义扫描路径");
        // 初始化client
        client.init();
        Map<String, String> allKeyValues = client.getAllKeyValues();
        log.info("结果返回：{}",JSON.toJSONString(allKeyValues));
    }
}

