package com.meituan.qa.meishi.Hui.cases.huimapiweb;

import com.alibaba.fastjson.JSONObject;
import com.meituan.qa.meishi.Hui.util.TestDPLogin;
import com.meituan.qa.meishi.util.ClassAnnotation;
import com.meituan.qa.meishi.util.MethodAnotation;
import com.meituan.toolchain.mario.annotation.HTTPAPI;
import com.meituan.toolchain.mario.framework.DBDataProvider;
import com.meituan.toolchain.mario.model.ResponseMap;
import com.meituan.toolchain.mario.util.AssertUtil;
import lombok.extern.slf4j.Slf4j;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
* 场景：点评、美团APP，买单订单支付完成后调用查询订单支付结果，轮询接口"
* 返回结果部分参数说明：
*   支付的状态(0 未支付，1支付成功，-1 支付失败 -10 创建之后发起支付失败，不对用户显示)，2（退款中），3（已退款）
* */
@ClassAnnotation(author = "zhenyumin",depart = "C",apiName = "",type = "mapi",des = "查询订单支付状态")
@Slf4j
@HTTPAPI(apiPath = "hui/querymopaystatus.bin")
public class TestQueryMOPayStatusAction extends TestDPLogin {
    private HuiMapiWebLoopCheck loopCheck = new HuiMapiWebLoopCheck();
    private List<String> key_list = Arrays.asList("ErrorMsg","StatusMsg","Status");

    @Test(dataProvider = "dbdata",dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "zhenyumin", createTime = "2020-08-04",updateTime = "2020-08-04",des = "正确用例-查询订单状态为支付中的订单")
    public void ms_c_querymopaystatusaction_01(JSONObject request,JSONObject expect){
        log.info("入参：{}",JSONObject.toJSONString(request));

        request.getJSONObject("params").put("token",mtToken);

        ResponseMap responseMap = loopCheck.getLoopQuery(mtToken,mtClient,request);
        JSONObject body = JSONObject.parseObject(responseMap.getResponseBody());
        log.info("返回结果：{}",JSONObject.toJSONString(responseMap));

        queryMOPayStatusAssert(responseMap, expect);
    }

    //支付成功
    @Test(dataProvider = "dbdata",dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "zhenyumin", createTime = "2020-08-04",updateTime = "2020-08-04",des = "正确用例-查询订单状态为支付成功的订单")
    public void ms_c_querymopaystatusaction_02(JSONObject request,JSONObject expect){
        log.info("入参：{}",JSONObject.toJSONString(request));

        request.getJSONObject("params").put("token",mtToken);

        ResponseMap responseMap = loopCheck.getLoopQuery(mtToken,mtClient,request);
        JSONObject body = JSONObject.parseObject(responseMap.getResponseBody());
        log.info("返回结果：{}",JSONObject.toJSONString(responseMap));

        queryMOPayStatusAssert(responseMap, expect);
    }

    @Test(dataProvider = "dbdata",dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "zhenyumin", createTime = "2020-08-04",updateTime = "2020-08-04",des = "正确用例-查询订单状态为已经退款的订单")
    public void ms_c_querymopaystatusaction_03(JSONObject request,JSONObject expect){
        log.info("入参：{}",JSONObject.toJSONString(request));

        request.getJSONObject("params").put("token",mtToken);

        ResponseMap responseMap = loopCheck.getLoopQuery(mtToken,mtClient,request);
        JSONObject body = JSONObject.parseObject(responseMap.getResponseBody());
        log.info("返回结果：{}",JSONObject.toJSONString(responseMap));

       queryMOPayStatusAssert(responseMap,expect);
    }

    public void queryMOPayStatusAssert(ResponseMap responseMap,JSONObject expect){
        AssertUtil.assertNotNull(responseMap);
        AssertUtil.assertHttp200(responseMap);

        AssertUtil.assertJSONPathExists(responseMap,"$.StatusMsg");
        AssertUtil.assertJSONPathExists(responseMap,"$.ErrorMsg");
        AssertUtil.assertJSONPathExists(responseMap,"$.Status");

        AssertUtil.assertNotNull(JSONObject.parseObject(responseMap.getResponseBody()).get("StatusMsg"),"订单状态描述（StatusMsg）为空");
        AssertUtil.assertTrue(JSONObject.parseObject(responseMap.getResponseBody()).getIntValue("Status")>= 0,"支付状态码（Status）不是大于0的整数！");

        AssertUtil.assertJsonEquals(JSONObject.parseObject(responseMap.getResponseBody()),expect, JSONCompareMode.LENIENT,key_list,true);

    }

}
/*
* 支付成功：4818856010597871203
* 支付中：4818856010597515875
* //作废-退款中：4818856010598028899
* 已经退款：4818856010598054499
*         System.out.println(mtUserId);
        System.out.println(mtbyqUserId);
        System.out.println(dpUserId);
        System.out.println(dpUserIdByq);
5002907380
5021293687
9007199254760212
9000000000010553349
*  */
/*
tradeNo: 20080410100000000004307367878380
{
  "cityid": "1",
  "token": "",
  "orderid": "4818856010596996707",
  "idfa": "1BA375F7-08FB-4F6F-A35C-69C2E2CD6A4A",
  "uuid": "A022350138FC357E3E54CA40781A592FACB7AEE3387FA3E051DC11650C6E612B",
  "serializedid": "4818856010596996707",
  "cx": "i2HKpOmsirDPavelVfQBZOi2lnN9hjm+zSz2yUWEYvXgADgAUMLBB6ctbTn5LrD3DJCC1XPlyqsCcsxtYFVL/RVJsDxU0QCe4kXm8OcKhKnxNI6lllMPqB/g2k+bebFK4ydkK3awpivvlIn1YtWhwL9ZzaMHcKpyqNMHTMeSdNPpdIsjjrfRAfWE5jKY2ihx3lo+IQgqtBNsMxlRNOji/wkFX7ehzeQ6QouR6o/AU73bqElKK3Ie0b4R7/sfi00MeX/DUZsNiCGGdMRm2RkUlF8mduRPVo2Go0W39Bq/+3zU8nuWZMRE3Ch7xYrJyA1HXbvII+CpsKdbgJOfCmPp8/oKwb2H6ZdziPBWUKP+2BpdzN117L9FRln08rhQNHjg5O6tcr0uSJsSzWXZb+P9QCuYVj+kfu4h3Nk8+cAEhN2x92uLNgUkQAnaSarj309FjA2fL5BG5LuGKehxqQAPZl0ruqWdQL/92vJxQMFAvPa/hF7vZU1xio/3F5bacbEwjZXv3yEApC6CO5GGazDWbMk3GUhRSRLqWwXkpLzfR7ptScn9VXELGeVsZRwnmc8r78yOasBrMjpuKfeXZKhE3mJsudhkO5hcisAUcDj7UBoL9joTULqBNxQ/9x7GJXM83xy1kTrJvcmeeludZ5N0PUobkHpcVu2ExGCDH800/Xtr3AcjtTKjSbp8ztNtwA2Zzwgwfe6bPOEKkrSyzYfSLWuHI5vzBJwu/ZhOHPXozzEWtCFlt0mJkKG2rn2OjbtwbovvyAjulC8naiM7MrmwZLF7lF5Ykzj1gUce2tc+FVMED1HyUCAkqCQCiFNLGAID+XnBbOMsLrUOVbV/kUKGytkwdxQVP1DwYkMNQAQiDvluoAuRlvrraXenQ+v97q4r/3FAQC4VPO9FBBy6rbtyYX+uT6HRKYB+07imHKM8Ufy4otP1VlGJjxbnV7DYpNBJsheKUEV5tM06Y57stlhp6yn3UUMGJtrTAeWcUOtRCd5am1wBZCTUcEVenAEIbY/3bOy5TugnflF/EEiLrU4HSggIlZ7rcyXZGYhels2/H74Q7qRw72sL2sfL85ASuljN+wL22bm/hXe46XeBjs3rJ8C8oElRQYlFboI+811WKdfoJiDKdLU2s9N+0YK2VGCAJHBvaYUk4hjF3js5uOb8W6Qs3QfDBs4sbAr6qquDHlIyENcu2k2EnvZOfdgmRAKUX8g8bSMg8sklzh0MIohGVwzWiGYFIizftgIKUQXx5Z8B25nOpZKZKxwtXKaF2FMIYOS35JIrPcmTdUoVrLvGXvcZ0JCgpOaIyW5lCgQvWRKblRIhBPzbbfsR3HYQ1CG3Y5D4GexFBkRQJxUA6Hsstw54f2obtwE96ZwNS9GGDLy66evwvL0gIeMpiCxJ/v4XtxU8zk+f98eRVMlQgTArRA=="
 }
 返回结果：{"headers":{},"logMsg":"[]","responseBody":"{\"__modelHash__\":\"MOPayOrderDo\",\"ShopID\":41782956,\"OrderDetailTipsDo\":null,\"QuanList\":[{\"__modelHash__\":\"QuanDo\",\"SubTitle\":\"单单有折扣 随时出发\",\"ButtonText\":\"一键叫车\",\"Title\":\"美团打车大额立减\",\"JumpUrl\":\"imeituan://www.meituan.com/web?url=https%3A%2F%2Fqcs.meituan.com%2Fc%2Ffe%2FoperationGuide%3Fqcs_channel%3Dgroup_pay_meishi\",\"IconUrl\":\"http://p0.meituan.net/scarlett/6df4196ba5f704566421e92c2ed5f43e1202.png\"}],\"SaveAmount\":0.0,\"OrderDetailUrl\":\"dianping://web?url=http%3A%2F%2Fm.51ping.com%2Fhui%2Fmaiton%2Forder%3ForderId%3D4818856010596996707%26platform%3Dmain%26product%3Ddpapp%26pushEnabled%3D1%26ordertime%3D1596534707000%26version_name%3D10.1.400\",\"NoDiscountAmount\":0.0,\"MOPayShare\":null,\"receiptVerify\":null,\"PointMallDo\":null,\"RemainAmount\":0.0,\"Discounts\":[],\"MagicCard\":{\"__modelHash__\":\"MagicCard\",\"GainedHongBaoListTitle\":null,\"CanReceivedHongBaoListTitle\":null,\"GainedHongBaoList\":null,\"CanReceivedHongBaoList\":null},\"UserAmountString\":null,\"WebViewReserved\":null,\"shopPic\":\"\",\"OrderID\":0,\"WebViewDo\":null,\"SuccessMsg\":\"请向服务员出示验证码\",\"AlertLoginTips\":\"绑定手机后订单不会丢哦\",\"Status\":0,\"bookResult\":null,\"HuiTicketShareDo\":null,\"MerchantAmountString\":\"\",\"orderIdStr\":\"4818856010596996707\",\"StatusMsg\":\"支付中\",\"CurrentAmount\":7.0,\"BuffetDescs\":null,\"SerialNumber\":null,\"OriAmount\":10.0,\"RefundDetails\":null,\"TipTextArea\":null,\"RefundAmount\":0.0,\"BtnList\":[{\"__modelHash__\":\"OrderActionBtn\",\"BtnLink\":\"\",\"BtnPhones\":[],\"BtnLabel\":\"paydetails_success_contact\",\"BtnType\":1,\"Title\":\"联系客服\"},{\"__modelHash__\":\"OrderActionBtn\",\"BtnLink\":\"\",\"BtnPhones\":[\"028-69759766\"],\"BtnLabel\":\"contact_shop\",\"BtnType\":0,\"Title\":\"联系商家\"}],\"PromotionBanner\":{\"__modelHash__\":\"MOPayOperationBanner\",\"LinkUrl\":\"\\\"http://14223\\\"\",\"PicUrl\":\"\\\"http://14234254253454365364647\\\"\"},\"IsHobbit\":0,\"RepayUrl\":\"dianping://shopinfo?id=24799161\",\"OperationBanners\":null,\"RightDos\":[],\"VerifyStatus\":null,\"NoticeMessage\":null,\"shopuuid\":null,\"isNightMarket\":0,\"ShopName\":\"北海海鲜料理\",\"ServiceUrl\":null,\"VoucherSerials\":null,\"PayFailDescription\":null,\"BizType\":10,\"ShopPhones\":[\"028-69759766\"],\"HuiReviewInfo\":null,\"BaseOrderId\":null,\"Time\":\"Thu Dec 18 12:34:32 CST 1969\",\"AlertLoginLink\":{\"__modelHash__\":\"Link\",\"Url\":\"\",\"Name\":\"现在去绑定\"},\"OrderRemarks\":null,\"ContactMerchantTip\":\"\",\"RefundOrderLink\":null,\"AideList\":[{\"__modelHash__\":\"AideDo\",\"Text\":\"评价有机会获得100积分\",\"JumpUrl\":\"imeituan://www.meituan.com/addreview?referid=4818856010596996707&refertype=104\"}],\"couponDescription\":[\"买单折扣 ¥3.00\"],\"SerializedId\":null,\"MobileNo\":\"16601134169\",\"Banner\":null,\"HasVoiceReport\":false,\"ErrorMsg\":\"支付失败|对不起，支付遇到问题\",\"HongbaoPopDo\":{\"__modelHash__\":\"HongbaoPopDo\",\"Url\":\"imeituan://www.meituan.com/food/subcatelistB?sub_category_id=17\",\"Icon\":\"https://img.meituan.net/msmerchant/522ab4ca74e10ecebfa67a925330e93a953408.png@630w_630h_2e\"},\"BussinessAmount\":0.0,\"opbVipDo\":null,\"DeductAmount\":0.0,\"MerchantAmount\":7.0}","statusCode":200,"traceId":"1011442112738738889","url":"https://mapi.51ping.com/hui/querymopaystatus.bin?orderid=4818856010596996707&cx=i2HKpOmsirDPavelVfQBZOi2lnN9hjm+zSz2yUWEYvXgADgAUMLBB6ctbTn5LrD3DJCC1XPlyqsCcsxtYFVL/RVJsDxU0QCe4kXm8OcKhKnxNI6lllMPqB/g2k+bebFK4ydkK3awpivvlIn1YtWhwL9ZzaMHcKpyqNMHTMeSdNPpdIsjjrfRAfWE5jKY2ihx3lo+IQgqtBNsMxlRNOji/wkFX7ehzeQ6QouR6o/AU73bqElKK3Ie0b4R7/sfi00MeX/DUZsNiCGGdMRm2RkUlF8mduRPVo2Go0W39Bq/+3zU8nuWZMRE3Ch7xYrJyA1HXbvII+CpsKdbgJOfCmPp8/oKwb2H6ZdziPBWUKP+2BpdzN117L9FRln08rhQNHjg5O6tcr0uSJsSzWXZb+P9QCuYVj+kfu4h3Nk8+cAEhN2x92uLNgUkQAnaSarj309FjA2fL5BG5LuGKehxqQAPZl0ruqWdQL/92vJxQMFAvPa/hF7vZU1xio/3F5bacbEwjZXv3yEApC6CO5GGazDWbMk3GUhRSRLqWwXkpLzfR7ptScn9VXELGeVsZRwnmc8r78yOasBrMjpuKfeXZKhE3mJsudhkO5hcisAUcDj7UBoL9joTULqBNxQ/9x7GJXM83xy1kTrJvcmeeludZ5N0PUobkHpcVu2ExGCDH800/Xtr3AcjtTKjSbp8ztNtwA2Zzwgwfe6bPOEKkrSyzYfSLWuHI5vzBJwu/ZhOHPXozzEWtCFlt0mJkKG2rn2OjbtwbovvyAjulC8naiM7MrmwZLF7lF5Ykzj1gUce2tc+FVMED1HyUCAkqCQCiFNLGAID+XnBbOMsLrUOVbV/kUKGytkwdxQVP1DwYkMNQAQiDvluoAuRlvrraXenQ+v97q4r/3FAQC4VPO9FBBy6rbtyYX+uT6HRKYB+07imHKM8Ufy4otP1VlGJjxbnV7DYpNBJsheKUEV5tM06Y57stlhp6yn3UUMGJtrTAeWcUOtRCd5am1wBZCTUcEVenAEIbY/3bOy5TugnflF/EEiLrU4HSggIlZ7rcyXZGYhels2/H74Q7qRw72sL2sfL85ASuljN+wL22bm/hXe46XeBjs3rJ8C8oElRQYlFboI+811WKdfoJiDKdLU2s9N+0YK2VGCAJHBvaYUk4hjF3js5uOb8W6Qs3QfDBs4sbAr6qquDHlIyENcu2k2EnvZOfdgmRAKUX8g8bSMg8sklzh0MIohGVwzWiGYFIizftgIKUQXx5Z8B25nOpZKZKxwtXKaF2FMIYOS35JIrPcmTdUoVrLvGXvcZ0JCgpOaIyW5lCgQvWRKblRIhBPzbbfsR3HYQ1CG3Y5D4GexFBkRQJxUA6Hsstw54f2obtwE96ZwNS9GGDLy66evwvL0gIeMpiCxJ/v4XtxU8zk+f98eRVMlQgTArRA==&idfa=1BA375F7-08FB-4F6F-A35C-69C2E2CD6A4A&cityid=1&serializedid=4818856010596996707&uuid=A022350138FC357E3E54CA40781A592FACB7AEE3387FA3E051DC11650C6E612B&token=aik5EpqVUJYb3xZbrI775szkppoAAAAAlOkBADb_xX6cw2eY6T8UtnOdZ2WJBHReI_ooRPcFaquIWBIDMHZFh7QFClc3zRlwDUUBEg&"}

4818856010596996707

4818856010597515875 20080410100000000004307370136380


HGKPE41Z6GIDY4H6Y   mobileNo: 16601134169,orderId 200804928749610,cashierOrderId 4818856010597515875,shopId 24799161,mtShopId 41782956,userId 5002907380
 */