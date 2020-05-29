package com.meituan.qa.meishi.Hui.cases.huimtweb;/*
package com.meituan.qa.meishi.Hui.cases.huimtweb;

import com.alibaba.fastjson.JSONObject;
import com.meituan.qa.meishi.util.ClassAnnotation;
import com.meituan.qa.meishi.util.MethodAnotation;
import com.meituan.toolchain.mario.annotation.ThriftAPI;
import com.meituan.toolchain.mario.framework.DBDataProvider;
import com.sankuai.web.luffy.merchant.tservice.CommonResponse;
import com.sankuai.web.luffy.merchant.tservice.LuffyMerchantOpAdminTService;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

@ClassAnnotation(author ="zwh", depart = "C", apiName="/LuffyMerchantOpAdminTService/searchRegDealInfos", type="thrift",
        des="招商平台－驳回报名deal,目前全是直接审核通过，先不接入自动化")

@Slf4j
@ThriftAPI(methodName = "/LuffyMerchantOpAdminTService/approveRegDeal")

public class TestsendNotify {
    @ThriftAPI(appkey = "hui-mt-web")
    NotifyService.;
    private String _APIPATH="/LuffyMerchantOpAdminTService/approveRegDeal";

    @Test(  groups = { "P3","all","coverage"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    @MethodAnotation(author="zwh",createTime = "2018-01-24",updateTime ="2018-01-24",des = "驳回")
    public void ms_c_approveRegDeal_0(JSONObject request, JSONObject expect)throws Exception {
        String caseName = "ms_c_approveRegDeal_0";
//        RequestsFromDB requests = new RequestsFromDB(Constants.Protocol.thrift, _APIPATH, caseName);
        int regId = request.getInteger("regId");
        String mis = request.getString("mis");
        String reason = request.getString("reason");

        CommonResponse re=luffyMerchantOpAdminTService.cancelRegDeal(regId,mis,reason);
        //获取期望的response
        System.out.println(re.toString());
//        Response expectedResponse = new Response(_APIPATH, caseName,true);
//        int exceptsuccess = expect.getInteger("success");
//        String exceptmsg = expect.getString("msg");
//        Assert.assertEquals(re.success,exceptsuccess,"查询数据正确性校验");
//        Assert.assertEquals(re.msg,exceptmsg,"查询数据正确性校验");
    }
}
*/
