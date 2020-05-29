package com.meituan.qa.meishi.Hui.cases.huimweb;

import com.alibaba.fastjson.JSONObject;
import com.meituan.qa.meishi.Hui.util.CommonLoginUtil;
import com.meituan.qa.meishi.Hui.util.CreateOrderUtil;
import com.meituan.qa.meishi.util.ClassAnnotation;
import com.meituan.qa.meishi.util.MethodAnotation;
import com.meituan.toolchain.mario.framework.DBDataProvider;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

@ClassAnnotation(author = "liukang",depart = "C",apiName = "/hui/mm/createOrder",
        type = "http",des="mm站下单")
@Slf4j
public class TestMMCreateOrder {
    protected final Log logger = LogFactory.getLog(this.getClass());

    String token = CommonLoginUtil.ms_c_DPLogin_02();
    String userAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36";
    Map<String,String> orderCreateResult = new HashMap();

	@Test(dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author = "liukang",createTime = "2018-10-21",updateTime = "2018-10-21",des = "正确创单")
    public void ms_c_mmCreateOrder_01(JSONObject request, JSONObject expect){
        orderCreateResult = CreateOrderUtil.mmCreateOrder(token,userAgent,"ms_c_mmCreateOrder_01");
        log.info(orderCreateResult.toString());
        Assert.assertTrue("创单成功".equals(orderCreateResult.get("msg")));
        Assert.assertTrue(!orderCreateResult.get("serializedId").isEmpty());

    }
}
