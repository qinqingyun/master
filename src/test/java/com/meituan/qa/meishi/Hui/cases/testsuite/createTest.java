package com.meituan.qa.meishi.Hui.cases.testsuite;

import com.meituan.mtrace.TraceParam;
import com.meituan.mtrace.Tracer;
import com.meituan.qa.meishi.Hui.cases.base.TestBase;
import com.meituan.toolchain.mario.annotation.ThriftAPI;
import com.sankuai.meituan.resv.trade.idl.TResvTradeService;
import com.sankuai.meituan.resv.trade.thrift.DTO.BillDTO;
import com.sankuai.meituan.resv.trade.thrift.DTO.BillQueryDTO;
import com.sankuai.meituan.resv.trade.thrift.TResvBillService;
import org.testng.annotations.Test;

import static com.meituan.qa.meishi.Hui.entity.OrderSourceEnum.MTApp;

/**
 * Created by buyuqi on 2020/5/29.
 */
public class createTest extends TestBase{
    @ThriftAPI(appkey = "com.sankuai.resv.c.trade",localAppkey = "com.sankuai.meishi.qa.merchantapi")
    TResvBillService tResvBillService;
    @Test()
    public void testCreate() throws Exception {
        String caseId = "ms_c_4Verify_mtloadUnifiedCashier_05";

        maitonApi.uniCashierCreateOrder(caseId,MTApp);
    }

    @Test()
    public void testTradebill() throws Exception {
        Tracer.serverRecv(new TraceParam(""));
        Tracer.getServerTracer().getSpan().getForeverContext().put("userIdStr","5002907380");
        Tracer.getServerTracer().getSpan().getForeverContext().put("uuid","75FC45E70DA7FD7024468E0077DE660951ADFAD5FB298651AFB59400E67CA63F");
        Tracer.putContext("platform", String.valueOf(10));
        BillQueryDTO billQueryDTO = new BillQueryDTO();
        billQueryDTO.setPoiId(6207656);
        billQueryDTO.setOrderId(1000037733);
        billQueryDTO.setPlatform(10);
        BillDTO billDTO = tResvBillService.queryOrder(billQueryDTO);

    }
}

