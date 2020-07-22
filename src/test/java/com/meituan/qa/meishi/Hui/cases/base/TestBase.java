package com.meituan.qa.meishi.Hui.cases.base;

import com.meituan.qa.meishi.Hui.cases.huifullproce.TestCheckLoop;
import com.meituan.qa.meishi.Hui.cases.huiorderservice.HuiOrderApi;
import com.meituan.qa.meishi.Hui.cases.huiorderservice.HuiOrderLoopCheck;
import com.meituan.qa.meishi.Hui.cases.scene.LoopCheckUtil;
import com.meituan.qa.meishi.util.LionUtil;
import com.meituan.toolchain.mario.AnnotationProcessor.MarioProxyUtil;
import com.meituan.toolchain.mario.config.ConfigMange;
import com.meituan.toolchain.mario.login.LoginUtil;
import com.meituan.toolchain.mario.login.model.LoginType;
import com.meituan.toolchain.mario.login.model.MTCUser;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.internal.StringUtil;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import static com.meituan.qa.meishi.Hui.util.TestDPLogin.dpUserId;
import static com.meituan.qa.meishi.Hui.util.TestDPLogin.mtUserId;

/**
 * Created by buyuqi on 2020/5/29.
 */
@Slf4j
public class TestBase {
    public static ThriftApi thriftApi = MarioProxyUtil.create(ThriftApi.class);
    public static TestCheckLoop checkLoop = MarioProxyUtil.create(TestCheckLoop.class);
    public static MaitonApi maitonApi = MarioProxyUtil.create(MaitonApi.class);
    public static LoopCheckUtil loopCheck = MarioProxyUtil.create(LoopCheckUtil.class);
    public static HuiOrderLoopCheck huiOrderLoopCheck = MarioProxyUtil.create(HuiOrderLoopCheck.class);
    public static HuiOrderApi huiOrderApi = MarioProxyUtil.create(HuiOrderApi.class);
    protected static String doubleWriteMode;
    //OLD_ONLY 单写老  OLD_MAIN 以老为主双写  NEW_MAIN 以新为主双写  NEW_ONLY 单写新
    public static String MainSystem = "OLD_MAIN";
    //#是否校验老订单系统
    public static boolean IS_CHECK_OLD_ORDER_SYSTEM= false;
    //是否进行db数据diff
    public static boolean IS_CHECK_DB_RECORD=true;
    //#是否校验新订单系统
    public static boolean IS_CHECK_NEW_ORDER_SYSTEM = true;

    @BeforeClass(alwaysRun = true)
    public void beforeSuite() {
        maitonApi.userLogin();
    }

    @BeforeTest(alwaysRun = true)
    public void beforeTest(ITestContext context) {
        String main = context.getCurrentXmlTest().getParameter("main");

        if (main != null) {
            MainSystem = main;
        }
        switch (MainSystem) {
            case "OLD_ONLY":
                IS_CHECK_NEW_ORDER_SYSTEM = false;
                IS_CHECK_OLD_ORDER_SYSTEM = true;
                break;
            case "OLD_MAIN":
                IS_CHECK_NEW_ORDER_SYSTEM = true;
                IS_CHECK_OLD_ORDER_SYSTEM = true;
                break;
            case "NEW_MAIN":
                IS_CHECK_NEW_ORDER_SYSTEM = true;
                IS_CHECK_OLD_ORDER_SYSTEM = true;
                break;
            case "NEW_ONLY":
                IS_CHECK_NEW_ORDER_SYSTEM = true;
                IS_CHECK_OLD_ORDER_SYSTEM = false;
                break;
            default:
                IS_CHECK_NEW_ORDER_SYSTEM = false;
                IS_CHECK_OLD_ORDER_SYSTEM = true;
                break;
        }
        log.info("双写阶段：{} ", getStage());
    }
    public static String getStage() {
        switch (MainSystem) {
            case "OLD_ONLY":
                return "单写老";
            case "OLD_MAIN":
                return "以老为主双写";
            case "NEW_MAIN":
                return "以新为主双写";
            case "NEW_ONLY":
                return "单写新";
            default:
                return "单写老";
        }
    }
    @BeforeTest
    public void beforeTest() throws Exception {
        // 判断并改写双写模式
        if( MainSystem.equals("NEW_MAIN")){
            LionUtil.setUserWriteList(mtUserId+"_1");
            LionUtil.setUserWriteList(dpUserId+"_0");
        }
        if( MainSystem.equals("OLD_MAIN")){
            LionUtil.setUserBlackList(mtUserId+"_1");
            LionUtil.setUserWriteList(dpUserId+"_0");
        }
    }

}
