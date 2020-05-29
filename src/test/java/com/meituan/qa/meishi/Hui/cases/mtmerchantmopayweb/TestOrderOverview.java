package com.meituan.qa.meishi.Hui.cases.mtmerchantmopayweb;

import com.alibaba.fastjson.JSONObject;
import com.meituan.qa.meishi.Hui.cases.mtmerchantmopayweb.util.CallServiceUtil;
import com.meituan.qa.meishi.Hui.util.CommonLoginUtil;
import com.meituan.qa.meishi.util.ClassAnnotation;
import com.meituan.qa.meishi.util.MethodAnotation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.annotations.Test;

@ClassAnnotation(author = "liukang",depart = "C",apiName = "/hui/ajax/orderoverview",
        type = "http",des="商家后台订单查询后信息统计展示")
@Slf4j
public class TestOrderOverview {
    String bisid = CommonLoginUtil.merchantAPPLogin();
    private final Log logger = LogFactory.getLog(this.getClass());
    String apiPath = "/hui/ajax/orderoverview";

    @Test(groups = {"P3","orderquery"})
    @MethodAnotation(author = "liukang",createTime = "2018-09-30",updateTime = "2018-09-30",des = "买单订单查询列表")
    public void ms_c_orderOverview_01(){
        JSONObject result = callService("ms_c_orderOverview_01");
        log.info(JSONObject.toJSONString(result));
    }

    public JSONObject callService(String caseId){
        return CallServiceUtil.callService(caseId,apiPath,bisid);
    }

    public JSONObject callService(String caseId, long beginTime, long endTime){
        return CallServiceUtil.callService(caseId,beginTime,endTime,apiPath,bisid);
    }
}
