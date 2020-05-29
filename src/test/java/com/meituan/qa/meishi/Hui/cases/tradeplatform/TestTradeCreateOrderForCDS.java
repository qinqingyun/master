package com.meituan.qa.meishi.Hui.cases.tradeplatform;//package com.meituan.qa.meishi.Hui.cases.tradeplatform;
//
//
//import com.alibaba.fastjson.JSONObject;
//import com.meituan.qa.meishi.Order.tradePlat.TestTradeCreateOrder;
//import com.meituan.toolchain.mario.framework.DBDataProvider;
//import com.meituan.toolchain.mario.util.AssertUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.skyscreamer.jsonassert.JSONCompareMode;
//import com.alibaba.fastjson.*;
//import com.meituan.qa.meishi.util.ClassAnnotation;
//import com.meituan.toolchain.mario.framework.DBDataProvider;
//import com.meituan.toolchain.mario.util.AssertUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.skyscreamer.jsonassert.JSONCompareMode;
//import com.alibaba.fastjson.*;
//import com.meituan.qa.meishi.util.MethodAnotation;
//import com.sankuai.web.trade.client.response.OrderCreateResponse;
//import org.testng.Assert;
//import org.testng.annotations.Test;
//
//@ClassAnnotation(author = "liukang",depart = "C",apiName = "/orderCreateServiceI.create",
//        type = "thrift",des="餐电商创单" +
//        "文档：https://km.sankuai.com/page/61242255")
//@Slf4j
//public class TestTradeCreateOrderForCDS extends TestTradeCreateOrder {
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-14",updateTime = "2018-08-14",des = "单个sku创建订单")
//    public void ms_c_TradeCreateOrderForCDS_1(JSONObject request, JSONObject expect){
//        OrderCreateResponse orderCreateResponse = callService("ms_c_TradeCreateOrderForCDS_1");
//        log.info("orderCreateResponse: " + JSONObject.toJSONString(orderCreateResponse));
//        Assert.assertTrue(!orderCreateResponse.getOrderIds().isEmpty());
//        Assert.assertTrue(!orderCreateResponse.getPayToken().isEmpty()&&!orderCreateResponse.getTradeNo().isEmpty());
//    }
////这一期不支持多个sku所以支付处理扣库存有校验，所以支付不能通过
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-14",updateTime = "2018-08-14",des = "多个不重复sku创建订单")
//    public void ms_c_TradeCreateOrderForCDS_2(JSONObject request, JSONObject expect){
//        OrderCreateResponse orderCreateResponse = callService("ms_c_TradeCreateOrderForCDS_2");
//        log.info("orderCreateResponse: " + JSONObject.toJSONString(orderCreateResponse));
//        Assert.assertTrue(!orderCreateResponse.getOrderIds().isEmpty());
//        Assert.assertTrue(!orderCreateResponse.getPayToken().isEmpty()&&!orderCreateResponse.getTradeNo().isEmpty());
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-14",updateTime = "2018-08-14",des = "重复sku创建订单")
//    public void ms_c_TradeCreateOrderForCDS_3(JSONObject request, JSONObject expect){
//        OrderCreateResponse orderCreateResponse = callService("ms_c_TradeCreateOrderForCDS_3");
//        log.info("orderCreateResponse: " + JSONObject.toJSONString(orderCreateResponse));
//        Assert.assertTrue("SKU_DUPLICATED".equals(orderCreateResponse.getErrCode()));
//        Assert.assertTrue("sku不可重复".equals(orderCreateResponse.getErrMsg()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-14",updateTime = "2018-08-14",des = "sku不可购买")
//    public void ms_c_TradeCreateOrderForCDS_4(JSONObject request, JSONObject expect){
//        OrderCreateResponse orderCreateResponse = callService("ms_c_TradeCreateOrderForCDS_4");
//        log.info("orderCreateResponse: " + JSONObject.toJSONString(orderCreateResponse));
//        Assert.assertTrue("500".equals(orderCreateResponse.getErrCode()));
//        Assert.assertTrue("sku不再售卖".equals(orderCreateResponse.getErrMsg()));
//    }
//
//    //目前提示商品已卖光  后期需要修改
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-14",updateTime = "2018-08-14",des = "sku库存不足")
//    public void ms_c_TradeCreateOrderForCDS_5(JSONObject request, JSONObject expect){
//        OrderCreateResponse orderCreateResponse = callService("ms_c_TradeCreateOrderForCDS_5");
//        log.info("orderCreateResponse: " + JSONObject.toJSONString(orderCreateResponse));
//        Assert.assertTrue("500".equals(orderCreateResponse.getErrCode()));
//        Assert.assertTrue("商品已卖光".equals(orderCreateResponse.getErrMsg()));
//
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-14",updateTime = "2018-08-14",des = "用户超过最大sku购买份数")
//    public void ms_c_TradeCreateOrderForCDS_6(JSONObject request, JSONObject expect){
//        OrderCreateResponse orderCreateResponse = callService("ms_c_TradeCreateOrderForCDS_6");
//        log.info("orderCreateResponse: " + JSONObject.toJSONString(orderCreateResponse));
//        Assert.assertTrue("500".equals(orderCreateResponse.getErrCode()));
//        Assert.assertTrue("商品已卖光".equals(orderCreateResponse.getErrMsg()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-14",updateTime = "2018-08-14",des = "商品售卖开始时间前下单")
//    public void ms_c_TradeCreateOrderForCDS_7(JSONObject request, JSONObject expect){
//        OrderCreateResponse orderCreateResponse = callService("ms_c_TradeCreateOrderForCDS_7");
//        log.info("orderCreateResponse: " + JSONObject.toJSONString(orderCreateResponse));
//        Assert.assertTrue("商品已结束售卖".equals(orderCreateResponse.getErrMsg()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-14",updateTime = "2018-08-14",des = "商品售卖截止时间后下单")
//    public void ms_c_TradeCreateOrderForCDS_8(JSONObject request, JSONObject expect){
//        OrderCreateResponse orderCreateResponse = callService("ms_c_TradeCreateOrderForCDS_8");
//        log.info("orderCreateResponse: " + JSONObject.toJSONString(orderCreateResponse));
//        Assert.assertTrue("商品已结束售卖".equals(orderCreateResponse.getErrMsg()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-14",updateTime = "2018-08-14",des = "商品在售卖时间，但已下线")
//    public void ms_c_TradeCreateOrderForCDS_8_1(JSONObject request, JSONObject expect){
//        OrderCreateResponse orderCreateResponse = callService("ms_c_TradeCreateOrderForCDS_8_1");
//        log.info("orderCreateResponse: " + JSONObject.toJSONString(orderCreateResponse));
//        Assert.assertTrue("商品不在售卖范围内".equals(orderCreateResponse.getErrMsg()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-14",updateTime = "2018-08-14",des = "不填写收货地址")
//    public void ms_c_TradeCreateOrderForCDS_9(JSONObject request, JSONObject expect){
//        OrderCreateResponse orderCreateResponse = callService("ms_c_TradeCreateOrderForCDS_9");
//        log.info("orderCreateResponse: " + JSONObject.toJSONString(orderCreateResponse));
//        Assert.assertTrue("PARAMS_ERROR".equals(orderCreateResponse.getErrCode()));
//        Assert.assertTrue("参数异常地址信息不能为空;".equals(orderCreateResponse.getErrMsg()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-14",updateTime = "2018-08-14",des = "收货地址不在配送范围")
//    public void ms_c_TradeCreateOrderForCDS_10(JSONObject request, JSONObject expect){
//        OrderCreateResponse orderCreateResponse = callService("ms_c_TradeCreateOrderForCDS_10");
//        log.info("orderCreateResponse: " + JSONObject.toJSONString(orderCreateResponse));
//        Assert.assertTrue("商品当前城市不可配送".equals(orderCreateResponse.getErrMsg()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-14",updateTime = "2018-08-14",des = "交易总金额和sku总金额不等")
//    public void ms_c_TradeCreateOrderForCDS_11(JSONObject request, JSONObject expect){
//        OrderCreateResponse orderCreateResponse = callService("ms_c_TradeCreateOrderForCDS_11");
//        log.info("orderCreateResponse: " + JSONObject.toJSONString(orderCreateResponse));
////        Assert.assertTrue("REQUEST_ERROR_PARAM".equals(orderCreateResponse.getErrCode()));
//        Assert.assertTrue("sku金额错误".equals(orderCreateResponse.getErrMsg()));
//    }
////st正常下单
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-14",updateTime = "2018-08-14",des = "总金额和支付金额不等")
//    public void ms_c_TradeCreateOrderForCDS_12(JSONObject request, JSONObject expect){
//        OrderCreateResponse orderCreateResponse = callService("ms_c_TradeCreateOrderForCDS_12");
//        log.info("orderCreateResponse: " + JSONObject.toJSONString(orderCreateResponse));
//        Assert.assertTrue("REQUEST_ERROR_PARAM".equals(orderCreateResponse.getErrCode()));
//        Assert.assertTrue("income not equal trade total".equals(orderCreateResponse.getErrMsg()));
//    }
////未执行
////    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-14",updateTime = "2018-08-14",des = "风控校验不通过")
//    public void ms_c_TradeCreateOrderForCDS_13(){
//        OrderCreateResponse orderCreateResponse = callService("ms_c_TradeCreateOrderForCDS_13");
//        log.info("orderCreateResponse: " + JSONObject.toJSONString(orderCreateResponse));
//        Assert.assertTrue("抱歉，本次购买不符合活动规则".equals(orderCreateResponse.getErrMsg()));
//    }
//
////    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-14",updateTime = "2018-08-14",des = "outNo为支付成功记录")
//    public void ms_c_TradeCreateOrderForCDS_14(){
//        OrderCreateResponse orderCreateResponse = callService("ms_c_TradeCreateOrderForCDS_14");
//        log.info("orderCreateResponse: " + JSONObject.toJSONString(orderCreateResponse));
//        Assert.assertTrue("outNo查询的订单状态不正确".equals(orderCreateResponse.getErrMsg()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-14",updateTime = "2018-08-14",des = "outNo为下单待支付状态再次下单")
//    public void ms_c_TradeCreateOrderForCDS_15(JSONObject request, JSONObject expect){
//        OrderCreateResponse orderCreateResponse = callService("ms_c_TradeCreateOrderForCDS_15");
//        log.info("orderCreateResponse: " + JSONObject.toJSONString(orderCreateResponse));
//        Assert.assertTrue(!orderCreateResponse.getOrderIds().isEmpty());
//        Assert.assertTrue(!orderCreateResponse.getPayToken().isEmpty()&&!orderCreateResponse.getTradeNo().isEmpty());
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-14",updateTime = "2018-08-14",des = "commonInfo字段为空")
//    public void ms_c_TradeCreateOrderForCDS_16(JSONObject request, JSONObject expect){
//        OrderCreateResponse orderCreateResponse = callService("ms_c_TradeCreateOrderForCDS_16");
//        log.info("orderCreateResponse: " + JSONObject.toJSONString(orderCreateResponse));
//        Assert.assertTrue("参数异常指纹信息不能为空;outNo必传字段且不能为空;uuid信息不能为空;下单ip不能为空;".equals(orderCreateResponse.getErrMsg()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-14",updateTime = "2018-08-14",des = "commonInfo中outNo为空")
//    public void ms_c_TradeCreateOrderForCDS_17(JSONObject request, JSONObject expect){
//        OrderCreateResponse orderCreateResponse = callService("ms_c_TradeCreateOrderForCDS_17");
//        log.info("orderCreateResponse: " + JSONObject.toJSONString(orderCreateResponse));
//        Assert.assertTrue("参数异常outNo必传字段且不能为空;".equals(orderCreateResponse.getErrMsg()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-14",updateTime = "2018-08-14",des = "commonInfo中uuid为空")
//    public void ms_c_TradeCreateOrderForCDS_18(JSONObject request, JSONObject expect){
//        OrderCreateResponse orderCreateResponse = callService("ms_c_TradeCreateOrderForCDS_18");
//        log.info("orderCreateResponse: " + JSONObject.toJSONString(orderCreateResponse));
//        Assert.assertTrue("参数异常uuid信息不能为空;".equals(orderCreateResponse.getErrMsg()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-14",updateTime = "2018-08-14",des = "commonInfo中ip为空")
//    public void ms_c_TradeCreateOrderForCDS_19(JSONObject request, JSONObject expect){
//        OrderCreateResponse orderCreateResponse = callService("ms_c_TradeCreateOrderForCDS_19");
//        log.info("orderCreateResponse: " + JSONObject.toJSONString(orderCreateResponse));
//        Assert.assertTrue("参数异常下单ip不能为空;".equals(orderCreateResponse.getErrMsg()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-14",updateTime = "2018-08-14",des = "commonInfo中notifyUrl为空")
//    public void ms_c_TradeCreateOrderForCDS_20(JSONObject request, JSONObject expect){
//        OrderCreateResponse orderCreateResponse = callService("ms_c_TradeCreateOrderForCDS_20");
//        log.info("orderCreateResponse: " + JSONObject.toJSONString(orderCreateResponse));
//        Assert.assertTrue(!orderCreateResponse.getOrderIds().isEmpty());
//        Assert.assertTrue(!orderCreateResponse.getPayToken().isEmpty()&&!orderCreateResponse.getTradeNo().isEmpty());
//    }
////返回为null
////    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-14",updateTime = "2018-08-14",des = "bizInfo字段为空")
//    public void ms_c_TradeCreateOrderForCDS_21(){
//        OrderCreateResponse orderCreateResponse = callService("ms_c_TradeCreateOrderForCDS_21");
//        log.info("orderCreateResponse: " + JSONObject.toJSONString(orderCreateResponse));
////        Assert.assertTrue();
//    }
////返回null
////    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-14",updateTime = "2018-08-14",des = "bizInfo中bizCode为空")
//    public void ms_c_TradeCreateOrderForCDS_22(){
//        OrderCreateResponse orderCreateResponse = callService("ms_c_TradeCreateOrderForCDS_22");
//        log.info("orderCreateResponse: " + JSONObject.toJSONString(orderCreateResponse));
////        Assert.assertTrue();
//    }
////可正常下单
////    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-14",updateTime = "2018-08-14",des = "bizInfo中tenantId为空")
//    public void ms_c_TradeCreateOrderForCDS_23(){
//        OrderCreateResponse orderCreateResponse = callService("ms_c_TradeCreateOrderForCDS_23");
//        log.info("orderCreateResponse: " + JSONObject.toJSONString(orderCreateResponse));
////        Assert.assertTrue();
//    }
//    //可正常下单
////    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-14",updateTime = "2018-08-14",des = "bizInfo中sign为空")
//    public void ms_c_TradeCreateOrderForCDS_24(){
//        OrderCreateResponse orderCreateResponse = callService("ms_c_TradeCreateOrderForCDS_24");
//        log.info("orderCreateResponse: " + JSONObject.toJSONString(orderCreateResponse));
////        Assert.assertTrue();
//    }
////返回null
////    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-14",updateTime = "2018-08-14",des = "bizInfo中bizCode传入错误值")
//    public void ms_c_TradeCreateOrderForCDS_25(){
//        OrderCreateResponse orderCreateResponse = callService("ms_c_TradeCreateOrderForCDS_25");
//        log.info("orderCreateResponse: " + JSONObject.toJSONString(orderCreateResponse));
////        Assert.assertTrue();
//    }
////正常创建订单
////    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-14",updateTime = "2018-08-14",des = "bizInfo中tenantId传入错误值")
//    public void ms_c_TradeCreateOrderForCDS_26(){
//        OrderCreateResponse orderCreateResponse = callService("ms_c_TradeCreateOrderForCDS_26");
//        log.info("orderCreateResponse: " + JSONObject.toJSONString(orderCreateResponse));
////        Assert.assertTrue();
//    }
//    //正常下单
////    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-14",updateTime = "2018-08-14",des = "bizInfo中sign传入错误值")
//    public void ms_c_TradeCreateOrderForCDS_27(){
//        OrderCreateResponse orderCreateResponse = callService("ms_c_TradeCreateOrderForCDS_27");
//        log.info("orderCreateResponse: " + JSONObject.toJSONString(orderCreateResponse));
////        Assert.assertTrue();
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-14",updateTime = "2018-08-14",des = "fillOrderInfo字段为空")
//    public void ms_c_TradeCreateOrderForCDS_28(JSONObject request, JSONObject expect){
//        OrderCreateResponse orderCreateResponse = callService("ms_c_TradeCreateOrderForCDS_28");
//        log.info("orderCreateResponse: " + JSONObject.toJSONString(orderCreateResponse));
//        Assert.assertTrue("参数异常地址信息不能为空;资金交易信息不能为空;".equals(orderCreateResponse.getErrMsg()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-14",updateTime = "2018-08-14",des = "fillOrderInfo中tradeInfo为空")
//    public void ms_c_TradeCreateOrderForCDS_29(JSONObject request, JSONObject expect){
//        OrderCreateResponse orderCreateResponse = callService("ms_c_TradeCreateOrderForCDS_29");
//        log.info("orderCreateResponse: " + JSONObject.toJSONString(orderCreateResponse));
////        Assert.assertTrue("PARAMS_ERROR".equals(orderCreateResponse.getErrCode()));
//        Assert.assertTrue("sku金额错误".equals(orderCreateResponse.getErrMsg()));
//    }
////买方信息为空时报错了 应该是出空指针了  userid为空时正确看看需不需要分成两个case（st应该空指针了）
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-14",updateTime = "2018-08-14",des = "买方信息为空或userid为空")
//    public void ms_c_TradeCreateOrderForCDS_30(JSONObject request, JSONObject expect){
//        OrderCreateResponse orderCreateResponse = callService("ms_c_TradeCreateOrderForCDS_30");
//        log.info("orderCreateResponse: " + JSONObject.toJSONString(orderCreateResponse));
//        Assert.assertTrue("FUNDS_MTCASHIER_ERROR_116022".equals(orderCreateResponse.getErrCode()));
//        Assert.assertTrue("code:缺少userid参数(116022), type:116022, message:cashier_lack_userid_param".equals(orderCreateResponse.getErrMsg()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-14",updateTime = "2018-08-14",des = "买方信息中userType为空或非指定值")
//    public void ms_c_TradeCreateOrderForCDS_31(JSONObject request, JSONObject expect){
//        OrderCreateResponse orderCreateResponse = callService("ms_c_TradeCreateOrderForCDS_31");
//        log.info("orderCreateResponse: " + JSONObject.toJSONString(orderCreateResponse));
////        Assert.assertTrue("FUNDS_MTCASHIER_ERROR_116022".equals(orderCreateResponse.getErrCode()));
//        Assert.assertTrue("参数异常用户type不合法;".equals(orderCreateResponse.getErrMsg()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-14",updateTime = "2018-08-14",des = "userid错误为-1")
//    public void ms_c_TradeCreateOrderForCDS_32(JSONObject request, JSONObject expect){
//        OrderCreateResponse orderCreateResponse = callService("ms_c_TradeCreateOrderForCDS_32");
//        log.info("orderCreateResponse: " + JSONObject.toJSONString(orderCreateResponse));
////        Assert.assertTrue("FUNDS_MTCASHIER_ERROR_116022".equals(orderCreateResponse.getErrCode()));
//        Assert.assertTrue("参数异常userId不能为空且必须大于0;".equals(orderCreateResponse.getErrMsg()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-14",updateTime = "2018-08-14",des = "addressInfo中provinceId为小于等于0（为空时传递值为0）")
//    public void ms_c_TradeCreateOrderForCDS_33(JSONObject request, JSONObject expect){
//        OrderCreateResponse orderCreateResponse = callService("ms_c_TradeCreateOrderForCDS_33");
//        log.info("orderCreateResponse: " + JSONObject.toJSONString(orderCreateResponse));
////        Assert.assertTrue("FUNDS_MTCASHIER_ERROR_116022".equals(orderCreateResponse.getErrCode()));
//        Assert.assertTrue("参数异常省份id不能为空且大于0;".equals(orderCreateResponse.getErrMsg()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-14",updateTime = "2018-08-14",des = "addressInfo中cityId为小于等于0（为空时传递值为0）")
//    public void ms_c_TradeCreateOrderForCDS_34(JSONObject request, JSONObject expect){
//        OrderCreateResponse orderCreateResponse = callService("ms_c_TradeCreateOrderForCDS_34");
//        log.info("orderCreateResponse: " + JSONObject.toJSONString(orderCreateResponse));
////        Assert.assertTrue("FUNDS_MTCASHIER_ERROR_116022".equals(orderCreateResponse.getErrCode()));
//        Assert.assertTrue("参数异常城市id不能为空且大于0;".equals(orderCreateResponse.getErrMsg()));
//    }
//
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-14",updateTime = "2018-08-14",des = "addressInfo中areaId为小于等于0（为空时传递值为0）")
//    public void ms_c_TradeCreateOrderForCDS_35(JSONObject request, JSONObject expect){
//        OrderCreateResponse orderCreateResponse = callService("ms_c_TradeCreateOrderForCDS_35");
//        log.info("orderCreateResponse: " + JSONObject.toJSONString(orderCreateResponse));
////        Assert.assertTrue("FUNDS_MTCASHIER_ERROR_116022".equals(orderCreateResponse.getErrCode()));
//        Assert.assertTrue("参数异常区县id不能为空且大于0;".equals(orderCreateResponse.getErrMsg()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-14",updateTime = "2018-08-14",des = "addressInfo中consignAddress为空")
//    public void ms_c_TradeCreateOrderForCDS_36(JSONObject request, JSONObject expect){
//        OrderCreateResponse orderCreateResponse = callService("ms_c_TradeCreateOrderForCDS_36");
//        log.info("orderCreateResponse: " + JSONObject.toJSONString(orderCreateResponse));
////        Assert.assertTrue("FUNDS_MTCASHIER_ERROR_116022".equals(orderCreateResponse.getErrCode()));
//        Assert.assertTrue("参数异常收货详细地址不能为空;".equals(orderCreateResponse.getErrMsg()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-14",updateTime = "2018-08-14",des = "addressInfo中consignMobile为空")
//    public void ms_c_TradeCreateOrderForCDS_37(JSONObject request, JSONObject expect){
//        OrderCreateResponse orderCreateResponse = callService("ms_c_TradeCreateOrderForCDS_37");
//        log.info("orderCreateResponse: " + JSONObject.toJSONString(orderCreateResponse));
////        Assert.assertTrue("FUNDS_MTCASHIER_ERROR_116022".equals(orderCreateResponse.getErrCode()));
//        Assert.assertTrue("参数异常收货手机不能为空;".equals(orderCreateResponse.getErrMsg()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-14",updateTime = "2018-08-14",des = "addressInfo中consignee为空")
//    public void ms_c_TradeCreateOrderForCDS_38(JSONObject request, JSONObject expect){
//        OrderCreateResponse orderCreateResponse = callService("ms_c_TradeCreateOrderForCDS_38");
//        log.info("orderCreateResponse: " + JSONObject.toJSONString(orderCreateResponse));
////        Assert.assertTrue("FUNDS_MTCASHIER_ERROR_116022".equals(orderCreateResponse.getErrCode()));
//        Assert.assertTrue("参数异常收货人不能为空;".equals(orderCreateResponse.getErrMsg()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-14",updateTime = "2018-08-14",des = "addressInfo中postCode为空")
//    public void ms_c_TradeCreateOrderForCDS_39(JSONObject request, JSONObject expect){
//        OrderCreateResponse orderCreateResponse = callService("ms_c_TradeCreateOrderForCDS_39");
//        log.info("orderCreateResponse: " + JSONObject.toJSONString(orderCreateResponse));
////        Assert.assertTrue("FUNDS_MTCASHIER_ERROR_116022".equals(orderCreateResponse.getErrCode()));
//        Assert.assertTrue("参数异常邮政编码不能为空;".equals(orderCreateResponse.getErrMsg()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-14",updateTime = "2018-08-14",des = "payInfos字段为空")
//    public void ms_c_TradeCreateOrderForCDS_40(JSONObject request, JSONObject expect){
//        OrderCreateResponse orderCreateResponse = callService("ms_c_TradeCreateOrderForCDS_40");
//        log.info("orderCreateResponse: " + JSONObject.toJSONString(orderCreateResponse));
////        Assert.assertTrue("REQUEST_ERROR_PARAM".equals(orderCreateResponse.getErrCode()));
//        Assert.assertTrue("参数异常支付信息不能为空;".equals(orderCreateResponse.getErrMsg()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-14",updateTime = "2018-08-14",des = "skuInfos字段为空")
//    public void ms_c_TradeCreateOrderForCDS_41(JSONObject request, JSONObject expect){
//        OrderCreateResponse orderCreateResponse = callService("ms_c_TradeCreateOrderForCDS_41");
//        log.info("orderCreateResponse: " + JSONObject.toJSONString(orderCreateResponse));
//        Assert.assertTrue("参数异常业务类型不能为空;财务明细业务类型不能为空;资金子类型不能为空且大于0;".equals(orderCreateResponse.getErrMsg()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-14",updateTime = "2018-08-14",des = "skuInfos中skuId字段为空")
//    public void ms_c_TradeCreateOrderForCDS_42(JSONObject request, JSONObject expect){
//        OrderCreateResponse orderCreateResponse = callService("ms_c_TradeCreateOrderForCDS_42");
//        log.info("orderCreateResponse: " + JSONObject.toJSONString(orderCreateResponse));
//        Assert.assertTrue("PARAMS_ERROR".equals(orderCreateResponse.getErrCode()));
//        Assert.assertTrue("参数异常sku_id不能为空且大于0;".equals(orderCreateResponse.getErrMsg()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-14",updateTime = "2018-08-14",des = "skuInfos中poiId字段为空")
//    public void ms_c_TradeCreateOrderForCDS_43(JSONObject request, JSONObject expect){
//        OrderCreateResponse orderCreateResponse = callService("ms_c_TradeCreateOrderForCDS_43");
//        log.info("orderCreateResponse: " + JSONObject.toJSONString(orderCreateResponse));
//        Assert.assertTrue("参数异常poi_id不能为空且大于0;".equals(orderCreateResponse.getErrMsg()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-14",updateTime = "2018-08-14",des = "skuInfos中quantity字段为空或为负数-1")
//    public void ms_c_TradeCreateOrderForCDS_44(JSONObject request, JSONObject expect){
//        OrderCreateResponse orderCreateResponse = callService("ms_c_TradeCreateOrderForCDS_44");
//        log.info("orderCreateResponse: " + JSONObject.toJSONString(orderCreateResponse));
//        Assert.assertTrue("500".equals(orderCreateResponse.getErrCode()));
//        Assert.assertTrue("参数异常数量最小为1;".equals(orderCreateResponse.getErrMsg()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-14",updateTime = "2018-08-14",des = "skuInfos中fundsInfo字段为空")
//    public void ms_c_TradeCreateOrderForCDS_45(JSONObject request, JSONObject expect){
//        OrderCreateResponse orderCreateResponse = callService("ms_c_TradeCreateOrderForCDS_45");
//        log.info("orderCreateResponse: " + JSONObject.toJSONString(orderCreateResponse));
//        Assert.assertTrue("BUSINESS_ERROR_TYPE".equals(orderCreateResponse.getErrCode()));
//        Assert.assertTrue("参数异常业务类型不能为空;资金子类型不能为空且大于0;财务明细业务类型不能为空;".equals(orderCreateResponse.getErrMsg()));
//    }
//    //商品不在售卖范围内
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-14",updateTime = "2018-08-14",des = "skuInfos中sku传错误不存在值")
//    public void ms_c_TradeCreateOrderForCDS_46(JSONObject request, JSONObject expect){
//        OrderCreateResponse orderCreateResponse = callService("ms_c_TradeCreateOrderForCDS_46");
//        log.info("orderCreateResponse: " + JSONObject.toJSONString(orderCreateResponse));
////        Assert.assertTrue("BUSINESS_ERROR_TYPE".equals(orderCreateResponse.getErrCode()));
//        Assert.assertTrue("商品不在售卖范围内".equals(orderCreateResponse.getErrMsg()));
//    }
//
//    @Test(groups = {"P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    @MethodAnotation(author = "liukang",createTime = "2018-08-14",updateTime = "2018-08-14",des = "费率为0的sku可正常下单")
//    public void ms_c_TradeCreateOrderForCDS_47(JSONObject request, JSONObject expect){
//        OrderCreateResponse orderCreateResponse = callService("ms_c_TradeCreateOrderForCDS_47");
//        log.info("orderCreateResponse: " + JSONObject.toJSONString(orderCreateResponse));
//        Assert.assertTrue(!orderCreateResponse.getOrderIds().isEmpty());
//        Assert.assertTrue(!orderCreateResponse.getPayToken().isEmpty()&&!orderCreateResponse.getTradeNo().isEmpty());
//    }
//}
