package com.meituan.qa.meishi.Hui.domain;

import com.alibaba.fastjson.JSONObject;
import com.meituan.nibtp.trade.client.buy.service.OrderMappingService;
import com.meituan.toolchain.mario.framework.DBDataProvider;
import com.meituan.toolchain.mario.model.ResponseMap;
import com.meituan.toolchain.mario.util.DBCaseRequestUtil;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Builder
@Data
public class MaitonCheck {

    String orderId;
    String caseId;
    OrderMappingService orderMappingService;

    public JSONObject maitonInfoCheck(){
        ResponseMap responseMap=null;
        String _APIPATH = "/mis/hui/query";
        JSONObject request = new JSONObject();
        try{
            request = DBDataProvider.getRequest(_APIPATH, caseId);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        request.put("path",request.getString("path").replaceAll("\\{orderid\\}",orderId));

        responseMap = DBCaseRequestUtil.get("env.mis.51ping.host", request);
        String body= responseMap.getResponseBody();
        log.info("订单详情页获取状态：" + body);
        JSONObject maitonInfo = JSONObject.parseObject(body);
       // maitonInfo.
        return maitonInfo;

    }



}

