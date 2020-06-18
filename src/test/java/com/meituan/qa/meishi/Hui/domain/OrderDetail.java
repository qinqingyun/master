package com.meituan.qa.meishi.Hui.domain;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.meituan.qa.meishi.Hui.dto.OrderDetailCheck;
import com.meituan.qa.meishi.Hui.util.TracerUtil;
import com.meituan.toolchain.mario.framework.DBDataProvider;
import com.meituan.toolchain.mario.model.ResponseMap;
import com.meituan.toolchain.mario.util.DBCaseRequestUtil;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

@Builder
@Data
@Slf4j
public class OrderDetail {
    String token;
    String userAgent;
    String serializedId;
    String orderId;
    String caseId;

    public String MtOrderDetail() {
        // 生成新Trace
        TracerUtil.initAndLogTrace();

        ResponseMap responseMap=null;
        String _APIPATH = "/maiton/order/{orderid}";
        JSONObject request = new JSONObject();
        try{
            request = DBDataProvider.getRequest(_APIPATH, caseId);
        }catch (Exception e){
            log.error("", e);
        }
        request.getJSONObject("params").put("token",token);
        request.put("path",request.getString("path").replaceAll("\\{orderid\\}",orderId));
        long currentTime = System.currentTimeMillis();
        try {
            responseMap = DBCaseRequestUtil.get("env.api.meishi.hui.maiton.host.mt", request);
        } catch (Exception e) {
            log.error("查询订单详情Exception, Request:{}, 耗时: {}",
                    JSON.toJSONString(request),
                    System.currentTimeMillis() - currentTime,
                    e);
        }
        String body= responseMap.getResponseBody();
        OrderDetailCheck  orderDetailinfo = parseHtml(responseMap.getResponseBody());
        String orderDetailinfoContent = orderDetailinfo.getContent();
        return orderDetailinfoContent;
     }

    public String DpOrderDetail() {
        // 生成新Trace
        TracerUtil.initAndLogTrace();

        ResponseMap responseMap=null;
        String _APIPATH = "/hui/maiton/order";
        JSONObject request = new JSONObject();
        try{
            request = DBDataProvider.getRequest(_APIPATH, caseId);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        request.getJSONObject("params").put("token",token);
        request.getJSONObject("params").put("orderId",orderId);
        request.getJSONObject("params").put("product","dpapp");
        long currentTime = System.currentTimeMillis();
        try {
            responseMap = DBCaseRequestUtil.get("env.api.meishi.hui.maiton.host.dp", request);
        } catch (Exception e) {
            log.error("查询订单详情Exception, Request:{}, 耗时: {}",
                    JSON.toJSONString(request),
                    System.currentTimeMillis() - currentTime,
                    e);
        }
        String body= responseMap.getResponseBody();
        OrderDetailCheck  orderDetailinfo = parseHtml(responseMap.getResponseBody());
        String orderDetailinfoContent = orderDetailinfo.getContent();
        //Assert.assertEquals(orderDetailinfo.getContent(),"支付成功");
       // Assert.assertEquals(orderDetailinfo.getTotalAmount(),"消费金额：1.00元");
        log.info("订单详情页获取状态=======" + orderDetailinfo.getContent()+"原价金额====="+orderDetailinfo.getTotalAmount()+"用户金额====="+orderDetailinfo.getPayAmount());
        return orderDetailinfoContent;
    }

     public OrderDetailCheck parseHtml(String html){
        OrderDetailCheck orderDetailCheck =new OrderDetailCheck();
        Document doc = Jsoup.parse(html);
        String content = doc.select("section[class=st-con st-succ]").get(0).select("div[class=st-wrap]").select("div[class=st-tit]").text();
        String  totalAmount = doc.select("section[class=dt-con]").get(0).select("ul[class=detail]").select("li[class=desc]").get(0).text();
        String  payAmount = doc.select("section[class=dt-con]").get(0).select("ul[class=detail]").select("li[class=desc]").get(1).text();
        orderDetailCheck.setContent(content);
        orderDetailCheck.setPayAmount(payAmount);
        orderDetailCheck.setTotalAmount(totalAmount);
        return orderDetailCheck;
    }
    public  String mOrderDetail(){
        // 生成新Trace
        TracerUtil.initAndLogTrace();

        String _MPATH = "/hui/maiton/order";
        String cookie = "dper=" + token;
        JSONObject mrequest = DBDataProvider.getRequest(_MPATH, caseId);
        mrequest.getJSONObject("params").put("token",token);
        mrequest.getJSONObject("headers").put("User-Agent", userAgent);
        mrequest.getJSONObject("headers").put("pragma-os", userAgent);
        mrequest.getJSONObject("params").put("serializedid", orderId);
        mrequest.getJSONObject("headers").put("Cookie", cookie);

        ResponseMap responseMap = DBCaseRequestUtil.get("env.api.51ping.host", mrequest);
        if (responseMap == null) {
            log.error("orderdetail error:{}", serializedId);
            return "";
        }
        log.info("query order result:{}", responseMap.getResponseBody());
        //具体校验信息问下rd
       // JSONObject jsonObject = JSONObject.parseObject(responseMap.getResponseBody());
        return responseMap.getResponseBody();
    }
    public  String dpWxOrderDetail(){
        // 生成新Trace
        TracerUtil.initAndLogTrace();

        String _MPATH = "/hui/mm/wxadetail";
        String cookie = "dper=" + token;
        JSONObject mrequest = DBDataProvider.getRequest(_MPATH, caseId);
        mrequest.getJSONObject("params").put("token",token);
        mrequest.getJSONObject("headers").put("User-Agent", userAgent);
        mrequest.getJSONObject("headers").put("pragma-os", userAgent);
        mrequest.getJSONObject("params").put("orderId", orderId);
        mrequest.getJSONObject("headers").put("Cookie", cookie);

        ResponseMap responseMap = DBCaseRequestUtil.get("env.api.mm51ping.host", mrequest);
        if (responseMap == null) {
            log.error("orderdetail error:{}", serializedId);
            return "";
        }
        log.info("query order result:{}", responseMap.getResponseBody());
        //具体校验信息问下rd
        // JSONObject jsonObject = JSONObject.parseObject(responseMap.getResponseBody());
        return responseMap.getResponseBody();
    }
}
