package com.meituan.qa.meishi.Hui.cases.huimweb;

import com.alibaba.fastjson.JSONObject;
import com.meituan.toolchain.mario.annotation.LoopCheck;
import com.meituan.toolchain.mario.framework.DBDataProvider;
import com.meituan.toolchain.mario.model.ResponseMap;
import com.meituan.toolchain.mario.util.DBCaseRequestUtil;
import com.meituan.toolchain.mario.util.JsonPathUtil;
import lombok.extern.slf4j.Slf4j;

import static com.meituan.qa.meishi.Hui.cases.base.TestBase.maitonApi;
import static com.meituan.qa.meishi.Hui.entity.OrderSourceEnum.DPApp;

@Slf4j
public class HuiMWebApi {
    static String merchantDtailUrl = "/hui/orderdetail";//商家订单详情页接口
    /**
     * pc端商家订单详情
     * 例：https://hui-e.51ping.com/hui/orderdetail?serializedId=HGKPET1Z6RZUB3AND&poiId=97224769
     */
    @LoopCheck(desc = "获取商家订单详情轮训", interval = 500, timeout = 1000 * 10) // 每间隔500ms请求一次，共10s
    public ResponseMap getMerchentOrderDetail(String caseId) {
        maitonApi.replaceUserInfo(DPApp);
        String merchantBsid = maitonApi.getUserModel().get().getMerchantBsid();
        JSONObject request = new JSONObject();
        try {
            request = DBDataProvider.getRequest(merchantDtailUrl, caseId);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        JsonPathUtil.setJsonPathVaule(request, "$.headers.Cookie","hui_bsid_https=" + merchantBsid);
        log.info("商家订单详情查询入参：{}",request);
        ResponseMap responseMap = DBCaseRequestUtil.get("env.api.meishi.merchant.host", request);
        return responseMap;
    }
}
