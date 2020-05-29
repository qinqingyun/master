package com.meituan.qa.meishi.Hui.cases.huicouponoffer;

import com.alibaba.fastjson.JSONObject;
import com.dianping.discountcenter.order.request.CouponOfferValidateRequest;
import com.dianping.discountcenter.order.response.CouponOfferValidateResponse;
import com.dianping.discountcenter.order.service.BizCouponOrderProcedureService;
import com.meituan.qa.meishi.util.ClassAnnotation;
import com.meituan.qa.meishi.util.MethodAnotation;
import com.meituan.toolchain.mario.annotation.ThriftAPI;
import com.meituan.toolchain.mario.framework.DBDataProvider;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;


@ClassAnnotation(author = "wqf",depart = "C",apiName = "/BizCouponJudgeService.judgeCouponByShopAndUser",
        type = "http",des="")
@Slf4j
@ThriftAPI(methodName = "")
public class TestValidate {

	@ThriftAPI(appkey = "com.dianping.discountcenter.order.service.BizCouponOrderProcedureService")
    private BizCouponOrderProcedureService bizCouponOrderProcedureService;

    @Test(groups = {"all","P3"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "wqf",createTime = "2019-2-15",updateTime = "2019-2-15",des = "")
    public void ms_c_validate_01(JSONObject request, JSONObject expect){
//        RequestsFromDB requests = new RequestsFromDB(Constants.Protocol.thrift,_APIPATH,"ms_c_bizCouponJudgeServcie_001");
        CouponOfferValidateRequest couponOfferValidateRequest = new CouponOfferValidateRequest();
        couponOfferValidateRequest.setShopId(65358944);
//        couponOfferValidateRequest.setUserId();

        CouponOfferValidateResponse   couponOfferValidateResponse =bizCouponOrderProcedureService.validate(couponOfferValidateRequest);
        log.info(JSONObject.toJSONString(couponOfferValidateResponse));

    }


}
