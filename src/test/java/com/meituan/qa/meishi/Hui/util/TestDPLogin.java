package com.meituan.qa.meishi.Hui.util;

import com.meituan.qa.meishi.Hui.cases.huifullproce.TestCheckLoop;
import com.meituan.toolchain.mario.AnnotationProcessor.MarioProxyUtil;
import com.meituan.toolchain.mario.config.ConfigMange;
import com.meituan.toolchain.mario.login.LoginUtil;
import com.meituan.toolchain.mario.login.model.DPCUser;
import com.meituan.toolchain.mario.login.model.LoginType;
import com.meituan.toolchain.mario.login.model.MTCUser;
import com.sankuai.nibqa.trade.api.service.TradeVerifyService;
import com.sankuai.nibqa.trade.api.util.VerifyServiceHander;
import lombok.extern.slf4j.Slf4j;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;

@Slf4j
public class TestDPLogin {
    public String envpath;
    public static String dpToken;
    public static String dpUserId;
    public static String dpUserIdByq;
    public static String mtToken;
    public static Long mtUserId;
    public static String dpTokenByq;
    public static String dpWxClient = "Mozilla/5.0 (iPhone; CPU iPhone OS 13_2_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/15E148 MicroMessenger/7.0.8(0x17000820) NetType/WIFI Language/zh_CN";
    public static String mtClient = "MApi 1.1 (mtscope 10.1.400 appstore; iPhone 11.3.1 iPhone10,3; a0d0)";
    public static String dpClient = "MApi 1.1(dpscope 10.16.0 appstore; iPhone 12.3.2 iPhone10,2; a0d0)";
    public static String mClient="Mozilla/5.0 (iPhone; CPU iPhone OS 11_0 like Mac OS X) AppleWebKit/604.1.38 (KHTML, like Gecko) Version/11.0 Mobile/15A372 Safari/604.1";
    public TradeVerifyService tradeVerifyService = VerifyServiceHander.getInstance();
    protected static String doubleWriteMode;
    public static TestCheckLoop checkLoop = MarioProxyUtil.create(TestCheckLoop.class);
    public static String resvOrderId;

    @BeforeSuite
    public void beforeSuite(ITestContext context) {
        // 判断并改写双写模式
        doubleWriteMode = context.getCurrentXmlTest().getParameter("DoubleWriteMode");
    }
    /**
     * 美团登录
     */
    @BeforeClass(alwaysRun = true, groups = {"P3", "mapi"})
    public void ms_c_login_01() {
        envpath = ConfigMange.getValue("testData");
        MTCUser mtcUser = (MTCUser) LoginUtil.login(LoginType.MT_C_LOGIN, "user1");
        mtToken = mtcUser.getToken();
        mtUserId = mtcUser.getId();
        log.info("mtToken:{}", mtToken);
        DPCUser user = (DPCUser) LoginUtil.login(LoginType.DP_C_LOGIN, "dp151");
        dpToken = user.getToken();

        log.info("dpToken:{}", dpToken);
    }

    /**
     * 点评登录
     */
    @BeforeClass(alwaysRun = true, groups = {"P3", "mapi"})
    public void ms_c_login_02() {
        DPCUser user = (DPCUser) LoginUtil.login(LoginType.DP_C_LOGIN, "dp151");
        dpToken = user.getToken();
        dpUserId = ConfigMange.getValue("dp151_DP_C_USER_ID");
        log.info("dpToken:{}", dpToken);
        DPCUser userByq = (DPCUser) LoginUtil.login(LoginType.DP_C_LOGIN, "dp7191");
        dpTokenByq = userByq.getToken();
        dpUserIdByq = ConfigMange.getValue("dp7191_DP_C_USER_ID");
        log.info("dpTokenByq:{}", dpTokenByq);
    }


    @DataProvider(name = "param")
    public static Object[][] test(Method method) throws Exception {
        String methodName = method.getName();
        String[] caseSplit = methodName.split("_");
        int i = Integer.parseInt(caseSplit[caseSplit.length - 1]);
        String caseId = "ms_c_" + caseSplit[2];
        if (i < 10) {
            return new Object[][]{
                    {dpToken, dpClient, caseId + "_00" + i},
                    {mtToken, mtClient, caseId + "_10" + i}
            };
        } else {
            return new Object[][]{
                    {dpToken, dpClient, caseId + "_0" + i},
                    {mtToken, mtClient, caseId + "_1" + i}
            };
        }

    }
}
