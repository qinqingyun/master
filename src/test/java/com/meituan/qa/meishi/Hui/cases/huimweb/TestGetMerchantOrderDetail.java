package com.meituan.qa.meishi.Hui.cases.huimweb;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dianping.hui.order.response.QueryOrderResponse;
import com.meituan.qa.meishi.Hui.cases.base.TestBase;
import com.meituan.qa.meishi.util.ClassAnnotation;
import com.meituan.qa.meishi.util.MethodAnotation;
import com.meituan.toolchain.mario.annotation.PigeonAPI;
import com.meituan.toolchain.mario.framework.DBDataProvider;
import com.meituan.toolchain.mario.model.ResponseMap;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.meituan.qa.meishi.Hui.entity.OrderSourceEnum.DPApp;
import static com.meituan.qa.meishi.Hui.util.CheckOrderUtil.getHuiOrderDetailVo;


/**
 * 商家后台订单详情页查询
 * 接口文档：
 * @return
 */
@ClassAnnotation(author = "buyuqi",depart = "C",apiName = "", type = "http",des="商家后台订单详情页查询")
@Slf4j
public class TestGetMerchantOrderDetail extends TestBase {
    @Test(groups = {"P1"})
    @MethodAnotation(author = "buyuqi",createTime = "2020-09-18",des = "商家后台订单详情页查询")
    public void ms_c_getMerchantOrderDetai_01(){
        ResponseMap merchentOrderDetail = huiMWebApi.getMerchentOrderDetail("ms_c_orderDetail_001");
        JSONObject jsonObject = getHuiOrderDetailVo(merchentOrderDetail.getResponseBody());


    }
}
