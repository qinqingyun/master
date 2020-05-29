package com.meituan.qa.meishi.Hui.cases.mtmerchantmopayweb;

import com.alibaba.fastjson.JSONObject;
import com.meituan.qa.meishi.Hui.cases.mtmerchantmopayweb.util.CallServiceUtil;
import com.meituan.qa.meishi.Hui.util.CommonLoginUtil;
import com.meituan.qa.meishi.util.ClassAnnotation;
import com.meituan.qa.meishi.util.MethodAnotation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

@ClassAnnotation(author = "liukang",depart = "C",apiName = "/hui/ajax/orderquery",
        type = "http",des="商家后台订单查询接口" +
        "需求文档：https://flow.sankuai.com/browse/MT-11654")
@Slf4j
public class TestOrderQuery {
    String bisid = CommonLoginUtil.merchantAPPLogin();
    private final Log logger = LogFactory.getLog(this.getClass());
    private String apiPath = "/hui/ajax/orderquery";

    @Test(groups = {"P3","orderquery"})
    @MethodAnotation(author = "liukang",createTime = "2018-05-23",updateTime = "2018-05-23",des = "查询特定的一条订单")
    public void ms_c_orderQuery_01(){
        JSONObject result = callService("ms_c_orderQuery_001");
        log.info(JSONObject.toJSONString(result));
        Assert.assertTrue(result.getJSONArray("$.huiOrderPageResponseVo.pageVo.resultList").size() == 1 && !result.equals("null"));
    }

    @Test(groups = {"P3","orderquery"})
    @MethodAnotation(author = "liukang",createTime = "2018-05-23",updateTime = "2018-09-30",des = "查询不到订单")
    public void ms_c_orderQuery_02(){
        JSONObject result = callService("ms_c_orderQuery_002");
        log.info(JSONObject.toJSONString(result));
    }

    public JSONObject callService(String caseId){
        return CallServiceUtil.callService(caseId,apiPath,bisid);
    }

    public JSONObject callService(String caseId, long beginTime, long endTime){
        return CallServiceUtil.callService(caseId,beginTime,endTime,apiPath,bisid);
    }
}
