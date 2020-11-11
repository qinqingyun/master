package com.meituan.qa.meishi.Hui.util;

import com.meituan.mtrace.Tracer;
import com.meituan.qa.meishi.Hui.cases.base.TestBase;

import lombok.extern.slf4j.Slf4j;

/**
 * 说明：有些特定的操作需要mock直接，在mock平台上配置相应接口或服务的mock，用trace信息来判断是否需要mock
 * 1、老为主退款回调mock：服务：com.sankuai.foodtrade.thriftproxy，接口：ITradeOrderThrift.applyRefund，mock特征值：trace.get("REFUND_OLDMAIN_MOCK")=="TRUE"
 * 2、新为主退款回调mock：服务：com.sankuai.mptrade.domainproxy，接口：ITradeOrderThrift.applyRefund，mock特征值：trace.get("MOCK_APPLY_REFUND")=="TRUE"
 * 3、新为主退款结算mock：服务：com.sankuai.mptrade.domainproxy，接口：ClearCommandService.getSettleAccountInfo，mock特征值：trace.get("MOCK_REFUND_SettleAccount")=="TRUE"
 * 4、单写新退款结算mock：服务：com.sankuai.mptrade.refund，接口：ClearCommandService.getSettleAccountInfo，mock特征值：trace.get("MOCK_ONLYNEWREFUND_SettleAccount")=="TRUE"
 * 5、新为主场景，买单接入交易平台pr流程的用例配置结算mock：
 *          服务：com.sankuai.travel.dsg.athena，
 *          接口：IPartnerInvoicePlatService.queryPartnerInvoiceInfoAndSettleInfo，
 *          mock特征值：trace.get('SettleMock') == true
 * 6、单写新场景，买单接入交易平台pr流程的用例配置结算mock：
 *          服务：com.sankuai.travel.dsg.athena，
 *          接口：IPartnerInvoicePlatService.queryPartnerInvoiceInfoAndSettleInfo，
 *          mock特征值：trace.get('SettleMock') == true
 */

@Slf4j
public class SetTraceUtil extends TestBase {
    public void setTrace(){
        switch (MainSystem){
            case "OLD_MAIN":
                Tracer.putContext("REFUND_OLDMAIN_MOCK","TRUE");
                break;
            case "NEW_MAIN":
                Tracer.putContext("MOCK_APPLY_REFUND","TRUE");
                Tracer.putContext("MOCK_REFUND_SettleAccount","TRUE");
                Tracer.putContext("SettleMock", "true");            // 买单接入平台pr流程的用例全部配置结算mock
                break;
            case "NEW_ONLY":
                Tracer.putContext("MOCK_ONLYNEWREFUND_SettleAccount","TRUE");
                Tracer.putContext("SettleMock", "true");              // 买单接入平台pr流程的用例全部配置结算mock
                break;
        }
    }

}