package com.meituan.qa.meishi.Hui.cases.huimapiweb;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.meituan.qa.meishi.Hui.dto.DeskCoupon;
import com.meituan.qa.meishi.Hui.util.TestDPLogin;
import com.meituan.qa.meishi.util.ClassAnnotation;
import com.meituan.qa.meishi.util.MethodAnotation;
import com.meituan.toolchain.mario.annotation.HTTPAPI;
import com.meituan.toolchain.mario.annotation.ThriftAPI;
import com.meituan.toolchain.mario.framework.DBDataProvider;
import com.meituan.toolchain.mario.model.ResponseMap;
import com.meituan.toolchain.mario.util.AssertUtil;

import com.sankuai.web.campaign.assigncard.tservice.maitonhongbao.*;
import lombok.extern.slf4j.Slf4j;

import org.apache.thrift.TException;
import org.testng.Assert;
import org.testng.annotations.Test;
import scala.Int;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.*;

/*
* 场景：点评、美团创建订单接口 ， 给 >= 7.8.1 版本的APP专用 （底层调用Unicashier接口） 之前版本，请查看 MOpayAction
* */

@ClassAnnotation(author = "zhenyumin",depart = "C",apiName = "",type = "mapi", des = "点评、美团创建订单，给 >= 7.8.1 版本的APP专用")
@Slf4j
@HTTPAPI(apiPath = "hui/unicashiercreateorder.bin")
public class TestUniCashierCreateOrderAction extends TestDPLogin{

    private HuiMapiWebLoopCheck hmwLoop = new HuiMapiWebLoopCheck();

    //营销平台发券
    @ThriftAPI(appkey = "com.sankuai.web.campaign.assigncard")
    MaitonHongbaoTService.Iface maitonHongbaoTService;

    @Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class,groups = "P1")
    @MethodAnotation(author = "zhenyumin", createTime = "2020-07-29",updateTime = "2020-07-31",des = "正确用例-使用折扣买单")
    public void ms_c_unicashiercreateorderaction_01(JSONObject request, JSONObject expect){

        List<String> path_list = Arrays.asList("$.OrderId","$.SerializedId","$.PayToken","$.Tradeno");
        log.info("入参：{}",request);

        ResponseMap response = hmwLoop.createOrderLoopQuery(mtToken,mtClient,request);            // 美团用户
        log.info("结果返回：{}",JSON.toJSONString(response));

        // 校验返回结果
       createOrderAssert(response,path_list,"折扣");
    }


    @Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class,groups = "P1")
    @MethodAnotation(author = "zhenyumin", createTime = "2020-07-31",updateTime = "2020-07-31",des = "正确用例-使用满减方案买单")
    public void ms_c_unicashiercreateorderaction_02(JSONObject request, JSONObject expect){

        List<String> path_list = Arrays.asList("$.OrderId","$.SerializedId","$.PayToken","$.Tradeno");
        log.info("入参：{}",request);

        ResponseMap response = hmwLoop.createOrderLoopQuery(mtToken,mtClient,request);            // 美团用户
        log.info("结果返回：{}",JSON.toJSONString(response));

        // 校验返回结果
        createOrderAssert(response,path_list,"满减");
    }


    @Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class,groups = "P1")
    @MethodAnotation(author = "zhenyumin", createTime = "2020-07-31",updateTime = "2020-08-05",des = "正确用例-使用商家抵用券买单")
    public void ms_c_unicashiercreateorderaction_03(JSONObject request, JSONObject expect) throws TException, UnsupportedEncodingException {

        List<String> path_list = Arrays.asList("$.OrderId","$.SerializedId","$.PayToken","$.Tradeno");

        // 查询是否有可用的券
        // 本case使用的门店id要与与ms_c_hui_gethuipromodesk_01的门店id相同
        String id = "120000901026380";
        DeskCoupon deskCoupon = checkLoop.getShopCouponCipher(mtToken,mtClient,"ms_c_hui_gethuipromodesk_01",id);

        AssertUtil.assertNotNull(deskCoupon,"没有可用券！");
        if(deskCoupon == null){
            //调用营销接口发商家券
            MaitonHongbaoTRequest maitonHongbaoTRequest = new MaitonHongbaoTRequest();
            maitonHongbaoTRequest.setPlatform(Platform.MT);
            maitonHongbaoTRequest.setUserId(mtUserId);
            maitonHongbaoTRequest.setPoiId(97224769);
            maitonHongbaoTRequest.setAssignChannelTEnum(AssignChannelTEnum.MAITON);
            maitonHongbaoTRequest.setOrderId(123132131);
            maitonHongbaoTRequest.setOrderPrice(1);
            MaitonHongbaoTResponse mation_response= maitonHongbaoTService.assignMaitonHongbao(maitonHongbaoTRequest);
            Optional<MaitonHongbaoTBean> detailOptional=mation_response.data.stream().findFirst();
            String id_1= detailOptional.get().id;
            log.info("发券接口返回======="+ id_1);
            //下单前查询优惠
            deskCoupon = checkLoop.getShopCouponCipher(mtToken,mtClient,"ms_c_hui_gethuipromodesk_01",id_1);
            Assert.assertTrue(deskCoupon != null,"获取商家券失败，可能原因：调用商家券接口超时或者查券失败");
        }

        String dpDealString = deskCoupon.getCipher();
//        request.getJSONObject("body").put("dpdealstring",dpDealString);
        request.getJSONObject("body").put("dpdealstring",URLEncoder.encode(deskCoupon.getCipher(), "utf-8"));

//        String coupOfferId = deskCoupon.getId();
//        String shopdealString = "{\"dealGroupId\":0,\"dealId\":0,\"needBuyDealCount\":0,\"useDealCount\":0,\"couponList\":[{\"productType\":201,\"couponId\":"+ coupOfferId +",\"ticketId\":\"0\"}]}";

//        double userAmount = (double)request.getJSONObject("body").getIntValue("originamount") - deskCoupon.getAmount();
//        request.getJSONObject("body").put("useramount",String.valueOf(userAmount));
//        request.getJSONObject("body").put("shopdealstring",shopdealString);
        // int 不行，double不行

        BigDecimal couponAmount = BigDecimal.valueOf(deskCoupon.getAmount());
        if (BigDecimal.ZERO.compareTo(couponAmount) < 0) {
            BigDecimal userAmount = request.getJSONObject("body").getBigDecimal("originamount").subtract(couponAmount);
            if(userAmount.compareTo(BigDecimal.ZERO) == 0){
                request.getJSONObject("body").put("useramount", "0");
            }else {
                request.getJSONObject("body").put("useramount", String.valueOf(userAmount));
            }

        }

        log.info("入参：{}", request);
        ResponseMap response = hmwLoop.createOrderLoopQuery(mtToken,mtClient,request);            // 美团用户
        log.info("结果返回：{}",JSON.toJSONString(response));

        // 校验返回结果
        createOrderAssert(response,path_list,"商家抵用券");
    }


    @Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class,groups = "P1")
    @MethodAnotation(author = "zhenyumin", createTime = "2020-07-31",updateTime = "2020-07-31",des = "正确用例-原价买单")
    public void ms_c_unicashiercreateorderaction_04(JSONObject request, JSONObject expect){

        List<String> path_list = Arrays.asList("$.OrderId","$.SerializedId","$.PayToken","$.Tradeno");
        log.info("入参：{}",request);

        ResponseMap response = hmwLoop.createOrderLoopQuery(mtToken,mtClient,request);            // 美团用户
        log.info("结果返回：{}",JSON.toJSONString(response));

        // 校验返回结果
        createOrderAssert(response,path_list,"原价");
    }

    @Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class,groups = "P1")
    @MethodAnotation(author = "zhenyumin", createTime = "2020-08-05",updateTime = "2020-08-05",des = "正确用例-(点评侧)使用折扣买单")
    public void ms_c_unicashiercreateorderaction_101(JSONObject request, JSONObject expect){

        List<String> path_list = Arrays.asList("$.OrderId","$.SerializedId","$.PayToken","$.Tradeno");
        log.info("入参：{}",request);

        ResponseMap response = hmwLoop.createOrderLoopQuery(dpToken,dpClient,request);            // 点评用户
        log.info("结果返回：{}",JSON.toJSONString(response));

        // 校验返回结果
        createOrderAssert(response,path_list,"折扣");
    }


    @Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class,groups = "P1")
    @MethodAnotation(author = "zhenyumin", createTime = "2020-08-05",updateTime = "2020-08-05",des = "正确用例-(点评侧)使用满减方案买单")
    public void ms_c_unicashiercreateorderaction_102(JSONObject request, JSONObject expect){

        List<String> path_list = Arrays.asList("$.OrderId","$.SerializedId","$.PayToken","$.Tradeno");

        log.info("入参：{}",request);
        ResponseMap response = hmwLoop.createOrderLoopQuery(dpToken,dpClient,request);            // 点评用户
        log.info("结果返回：{}",JSON.toJSONString(response));

        // 校验返回结果
        createOrderAssert(response,path_list,"满减");
    }

    @Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class,groups = "P1")
    @MethodAnotation(author = "zhenyumin", createTime = "2020-08-05",updateTime = "2020-08-05",des = "正确用例-(点评侧)使用商家抵用券买单")
    public void ms_c_unicashiercreateorderaction_103(JSONObject request, JSONObject expect) throws TException, UnsupportedEncodingException {

        List<String> path_list = Arrays.asList("$.OrderId","$.SerializedId","$.PayToken","$.Tradeno");

        // 查询是否有可用的券
        // 本case使用的门店id要与与ms_c_hui_gethuipromodesk_01的门店id相同
        String id = "120000901026380";
        DeskCoupon deskCoupon = checkLoop.getShopCouponCipher(dpToken,dpClient,"ms_c_unicashiercreateorderaction_promo_103",id);
        AssertUtil.assertNotNull(deskCoupon,"没有可用券！");

        if(deskCoupon == null){
            //调用营销接口发商家券
            MaitonHongbaoTRequest maitonHongbaoTRequest = new MaitonHongbaoTRequest();
            maitonHongbaoTRequest.setPlatform(Platform.MT);
            maitonHongbaoTRequest.setUserId(mtUserId);
            maitonHongbaoTRequest.setPoiId(97224769);
            maitonHongbaoTRequest.setAssignChannelTEnum(AssignChannelTEnum.MAITON);
            maitonHongbaoTRequest.setOrderId(123132131);
            maitonHongbaoTRequest.setOrderPrice(1);
            MaitonHongbaoTResponse mation_response= maitonHongbaoTService.assignMaitonHongbao(maitonHongbaoTRequest);
            Optional<MaitonHongbaoTBean> detailOptional=mation_response.data.stream().findFirst();
            String id_1= detailOptional.get().id;
            log.info("发券接口返回======="+ id_1);
            //下单前查询优惠
            deskCoupon = checkLoop.getShopCouponCipher(dpToken,dpClient,"ms_c_unicashiercreateorderaction_promo_103",id_1);
            Assert.assertTrue(deskCoupon != null,"获取商家券失败，可能原因：调用商家券接口超时或者查券失败");
        }

        String dpDealString = deskCoupon.getCipher();
//        request.getJSONObject("body").put("dpdealstring",dpDealString);
        request.getJSONObject("body").put("dpdealstring",URLEncoder.encode(deskCoupon.getCipher(), "utf-8"));

        log.info("入参：{}",request);
        ResponseMap response = hmwLoop.createOrderLoopQuery(dpToken,dpClient,request);            // 点评用户
        log.info("结果返回：{}",JSON.toJSONString(response));

        // 校验返回结果
        createOrderAssert(response,path_list,"商家抵用券");
    }


    @Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class,groups = "P1")
    @MethodAnotation(author = "zhenyumin", createTime = "2020-08-05",updateTime = "2020-08-05",des = "正确用例-(点评侧)原价买单")
    public void ms_c_unicashiercreateorderaction_104(JSONObject request, JSONObject expect){

        List<String> path_list = Arrays.asList("$.OrderId","$.SerializedId","$.PayToken","$.Tradeno");
        log.info("入参：{}",request);
        ResponseMap response = hmwLoop.createOrderLoopQuery(dpToken,dpClient,request);            // 点评用户
        log.info("结果返回：{}",JSON.toJSONString(response));

        // 校验返回结果
        createOrderAssert(response,path_list,"原价");
    }

    /**
     * 校验创建订单接口的返回结果，包括是否JSONPath是否存在、值是否为空、OrderId是否大于0
     * @param response：接口返回结果
     * @param path_list：需要进行校验的字段的JSONPath
     */
    public void createOrderAssert(ResponseMap response,List<String> path_list,String message){
        // 校验返回结果
        // 0 校验返回结果不为空
        AssertUtil.assertNotNull(response,message+" 下单失败：返回结果为null/空");

        // 1 校验状态码
        AssertUtil.assertHttp200(response);

        // 2 校验实际结果与预期结果
        // 2.1 判断JSONPath是否存在
        for(String path:path_list ){
            AssertUtil.assertJSONPathExists(response,path,message+" 下单失败：返回结果中的"+path+"不存在");
        }

        // 2.2 判断是否为空
        for(String path:path_list) {
            AssertUtil.assertNotNull(response,path,message+" 下单失败：返回结果中的"+path+"为null/空");
        }

        // 2.3 其他判断
        Long orderId = response.getValueByJsonPath("$.OrderId");
        AssertUtil.assertTrue(orderId>0,message+" 下单失败：OrderId小于0");
    }

/*
    @Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "zhenyumin", createTime = "2020-08-03",updateTime = "2020-08-03",des = "useramount字段缺失")
    public void ms_c_unicashiercreateorderaction_05(JSONObject request, JSONObject expect){
        log.info("入参：{}",request);

        ResponseMap response = hmwLoop.getLoopQuery(mtToken,mtClient,request);            // 美团用户
        log.info("结果返回：{}",JSON.toJSONString(response));

        // 校验返回结果
        AssertUtil.assertJsonPathValueEquals(response,"缺少必要参数!","$.Content");
    }

    @Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "zhenyumin", createTime = "2020-08-03",updateTime = "2020-08-03",des = "originamount字段缺失")
    public void ms_c_unicashiercreateorderaction_06(JSONObject request, JSONObject expect){
        log.info("入参：{}",request);

        ResponseMap response = hmwLoop.getLoopQuery(mtToken,mtClient,request);            // 美团用户
        log.info("结果返回：{}",JSON.toJSONString(response));

        // 校验返回结果
        AssertUtil.assertJsonPathValueEquals(response,"缺少必要参数!","$.Content");
    }

    @Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "zhenyumin", createTime = "2020-08-03",updateTime = "2020-08-03",des = "shopid字段缺失")
    public void ms_c_unicashiercreateorderaction_07(JSONObject request, JSONObject expect){
        log.info("入参：{}",request);

        ResponseMap response = hmwLoop.getLoopQuery(mtToken,mtClient,request);            // 美团用户
        log.info("结果返回：{}",JSON.toJSONString(response));

        // 校验返回结果
        AssertUtil.assertJsonPathValueEquals(response,"缺少必要参数!","$.Content");
    }

    @Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "zhenyumin", createTime = "2020-08-03",updateTime = "2020-08-03",des = "nodiscountamount字段缺失")
    public void ms_c_unicashiercreateorderaction_08(JSONObject request, JSONObject expect){
        log.info("入参：{}",request);

        ResponseMap response = hmwLoop.getLoopQuery(mtToken,mtClient,request);            // 美团用户
        log.info("结果返回：{}",JSON.toJSONString(response));

        // 校验返回结果
        AssertUtil.assertJsonPathValueEquals(response,"缺少必要参数!","$.Content");
    }

 */

}
