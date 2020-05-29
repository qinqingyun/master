package com.meituan.qa.meishi.Hui.cases.huimtweb;


import com.alibaba.fastjson.JSONObject;
import com.meituan.qa.meishi.util.ClassAnnotation;
import com.meituan.qa.meishi.util.MethodAnotation;
import com.meituan.toolchain.mario.annotation.HTTPAPI;
import com.meituan.toolchain.mario.framework.DBDataProvider;
import com.meituan.toolchain.mario.model.ResponseMap;
import com.meituan.toolchain.mario.util.DBCaseRequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;

@ClassAnnotation(author ="yq", depart = "C", apiName="/hui/mtpay/refundnotify", type="http",
        des="买单v1接口,")
@Slf4j
@HTTPAPI(apiPath = "/hui/mtpay/refundnotify")
public class TestRefundnotify {

        private String _APIPATH = "/hui/mtpay/refundnotify";

        @Test(groups = {"P1"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
        @MethodAnotation(author = "qqy", createTime = "2019-06-01", updateTime = "2019-06-01", des = "美团app客户端上美食POI详情页买单")
        public void ms_c_refundnotify_0(JSONObject request, JSONObject expect) {
            log.info(request.toString());
            ResponseMap response = DBCaseRequestUtil.get("env.api.meishi.hui.maiton.host.paynotify", request);
            log.info(response.toString());
            Assert.assertEquals(200,response.getStatusCode());

        }
    }

