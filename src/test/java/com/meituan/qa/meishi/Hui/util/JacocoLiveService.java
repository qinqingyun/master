package com.meituan.qa.meishi.Hui.util;

import com.sankuai.daodian.qa.jacocolive.api.JacocoLiveUtil;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;


public class JacocoLiveService {

//    @Test,dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
//    public void test(){
//        JacocoLiveUtil.startJacoco("hui-m-web", "10.73.218.76");
//        JacocoLiveUtil.stopJacoco("hui-m-web", "10.73.218.76");
//
//    }
    @BeforeSuite
    public void beforeSuiteForJacoco()  {
        //服务启动及数据重置接口。【必填】
        JacocoLiveUtil.startJacoco("hui-m-web", "10.73.218.76");
        JacocoLiveUtil.resetJacoco("hui-m-web", "10.73.218.76");
        JacocoLiveUtil.startJacoco("hui-mapi-web", "10.73.245.151");
        JacocoLiveUtil.resetJacoco("hui-mapi-web", "10.73.245.151");
    }

    @AfterSuite
    public void afterSuiteForJacoco()  {
        //第一组核心范围设置，及数据拉取接口。【选填】
        JacocoLiveUtil.setCoreScope("hui-m-web", "10.73.218.76",
//                "**/m/facade/**," + "**/m/help/facade/**,**/m/help/service/impl/**" +
//                ",**/m/share/**,**/m/ticket/TicketShareAction.java,**/m/ticket/UserTicketsAction.java" +
//                ",**/maiton/facade/**,**/m/tms/**,**/maiton/action/**,**/mm/qq.action/**,**/mm/qr.action/**" +
                  "**/maiton/cashier/action/MCashierCreateOrderAction.java,**/m/order/OrderRefundAction.java," +
//                        "**/maiton/cashier/service/impl/**," +
                "**/maiton/action/BScanCAuthStringAction.java,**/maiton/action/BScanCQueryPayTokenAction.java," +
                "**/mm/action/**", "**/mm/action/ListOrderAction.java,**/mm/action/LoadBuffetsAction.java," +
                "**/mm/action/OrderResultAction.java,**/mm/action/ShopPayAction.java,**/mm/action/TransferAction.java,"+
                "**/mm/action/WxaPoiAction.java");
        JacocoLiveUtil.stopJacoco("hui-m-web", "10.73.218.76");

//        //第二组核心范围设置，及数据拉取接口。【选填】
        JacocoLiveUtil.setCoreScope("hui-mapi-web", "10.73.245.151", "**/action/**," +
//                "**/facade/**,**/generator/factory/**,**/generator/impl/**,**/mq/**,**/translator/**," +
                "**/service/impl/DepositeJudgeServiceImpl.java,**/service/impl/HuiOrderStashServiceImpl.java",
                "**/action/LoadVirtualUnifiedCashierAction.java,**/action/abandoned/**," +
                        "**/action/GetPayPromosAction.java,**/action/HuiPreProcessAction.java," +
                        "");
        JacocoLiveUtil.stopJacoco("hui-mapi-web", "10.73.245.151");
//
//        //第三组核心范围设置，及数据拉取&停止服务接口。【必填】
//        JacocoLiveUtil.setCoreScope("$服务名称", "$服务IP", "feedback", "");
//        JacocoLiveUtil.stopJacoco("$服务名称", "$服务IP");
    }
}
