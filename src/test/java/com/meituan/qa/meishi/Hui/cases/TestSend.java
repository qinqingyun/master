package com.meituan.qa.meishi.Hui.cases;

import com.alibaba.fastjson.JSON;
import com.meituan.service.mobile.mtthrift.util.json.JacksonUtils;
import com.sankuai.pay.trade.notify.thrift.NotifyReq;

import java.util.Map;

public class TestSend {
    public static void main(String[] args) {
        String a ="{\"foreverContext\":{\"INF_SWIMLANE\":\"wanglinlin02-fxftb\",\"masterPromotionKey\":\"013e6676-8670-412e-bb2c-ee475c4ce558\",\"masterTraceId\":\"013e6676-8670-412e-bb2c-ee475c4ce558\",\"needDealReplayTraffic\":\"true\",\"bizCode\":\"nib.food.paybill\",\"bucategory\":\"food\"},\"notifyMessage\":{\"body\":\"美团买单订单-4782822807583987717\",\"buyer\":\"2088102594396395\",\"charset\":\"UTF-8\",\"createTime\":\"2019-09-23 11:44:05\",\"feeDetail\":{\"third\":800},\"feeDetailSize\":1,\"merchantNo\":\"11000001560545\",\"notifyTime\":\"2019-09-23 11:44:11\",\"outNo\":\"1ERGFW5Y5LY0005\",\"partnerId\":\"2016012000000001\",\"payTime\":\"2019-09-23 11:44:11\",\"payType\":\"alipaysimple\",\"payTypeTxt\":\"支付宝极简收银台支付\",\"setBody\":true,\"setBuyer\":true,\"setCharset\":true,\"setCreateTime\":true,\"setErrorCode\":false,\"setErrorMsg\":false,\"setExtra\":false,\"setFeeDetail\":true,\"setMerchantNo\":true,\"setNotifyTime\":true,\"setOutNo\":true,\"setPartnerId\":true,\"setPayTime\":true,\"setPayType\":true,\"setPayTypeTxt\":true,\"setSign\":true,\"setSignType\":true,\"setSubMerchantNo\":true,\"setTotalFee\":true,\"setTradeNo\":true,\"setTradeStatus\":true,\"sign\":\"0e0bba2ee60998266779ed0d94a67456\",\"signType\":\"MD5\",\"subMerchantNo\":\"\",\"totalFee\":800,\"tradeNo\":\"19092310100000000004273642821968\",\"tradeStatus\":\"TRADE_SUCCESS\"},\"retriedTimes\":0}";
                //"{\"foreverContext\":{\"INF_SWIMLANE\":\"wanglinlin02-fxftb\",\"masterPromotionKey\":\"20d811ed-5338-4317-88c9-b48b1855fc46\",\"masterTraceId\":\"20d811ed-5338-4317-88c9-b48b1855fc46\",\"needDealReplayTraffic\":\"true\",\"bizCode\":\"nib.food.paybill\",\"bucategory\":\"food\"},\"notifyMessage\":{\"body\":\"美团买单订单-4782822807522859013\",\"buyer\":\"2088102594396395\",\"charset\":\"UTF-8\",\"createTime\":\"2019-09-19 17:12:04\",\"feeDetail\":{\"third\":4200},\"feeDetailSize\":1,\"merchantNo\":\"11000001560545\",\"notifyTime\":\"2019-09-19 17:12:12\",\"outNo\":\"1ER6HK4UH2P0005\",\"partnerId\":\"2016012000000001\",\"payTime\":\"2019-09-19 17:12:12\",\"payType\":\"alipaysimple\",\"payTypeTxt\":\"支付宝极简收银台支付\",\"setBody\":true,\"setBuyer\":true,\"setCharset\":true,\"setCreateTime\":true,\"setErrorCode\":false,\"setErrorMsg\":false,\"setExtra\":false,\"setFeeDetail\":true,\"setMerchantNo\":true,\"setNotifyTime\":true,\"setOutNo\":true,\"setPartnerId\":true,\"setPayTime\":true,\"setPayType\":true,\"setPayTypeTxt\":true,\"setSign\":true,\"setSignType\":true,\"setSubMerchantNo\":true,\"setTotalFee\":true,\"setTradeNo\":true,\"setTradeStatus\":true,\"sign\":\"203c5f69a769f4e934f3fb48822f2d90\",\"signType\":\"MD5\",\"subMerchantNo\":\"\",\"totalFee\":4200,\"tradeNo\":\"19091911100100000004273528705968\",\"tradeStatus\":\"TRADE_SUCCESS\"},\"retriedTimes\":0}";
        CashierNotifyMessage cashierNotifyMessage = JSON.parseObject(a, CashierNotifyMessage.class);

        String r = JacksonUtils.serialize(cashierNotifyMessage.getNotifyMessage());
        System.out.println(r);
    }
}


class CashierNotifyMessage {

    private int retriedTimes = -1;

    private Map<String, String> foreverContext;

    private NotifyReq notifyMessage;

    public int getRetriedTimes() {
        return retriedTimes;
    }

    public void setRetriedTimes(int retriedTimes) {
        this.retriedTimes = retriedTimes;
    }

    public Map<String, String> getForeverContext() {
        return foreverContext;
    }

    public void setForeverContext(Map<String, String> foreverContext) {
        this.foreverContext = foreverContext;
    }

    public NotifyReq getNotifyMessage() {
        return notifyMessage;
    }

    public void setNotifyMessage(NotifyReq notifyMessage) {
        this.notifyMessage = notifyMessage;
    }
}