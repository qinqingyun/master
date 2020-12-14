package com.meituan.qa.meishi.Hui.cases.base;

import com.meituan.qa.meishi.Hui.cases.huifullproce.TestCheckLoop;
import com.meituan.qa.meishi.Hui.cases.huimweb.HuiMWebApi;
import com.meituan.qa.meishi.Hui.cases.huiorderservice.HuiOrderApi;
import com.meituan.qa.meishi.Hui.cases.huiorderservice.HuiOrderLoopCheck;
import com.meituan.qa.meishi.Hui.cases.scene.LoopCheckUtil;
import com.meituan.qa.meishi.Hui.entity.LoginEnum;
import com.meituan.qa.meishi.Hui.entity.OrderSourceEnum;
import com.meituan.qa.meishi.Hui.entity.model.UserModel;
import com.meituan.qa.meishi.util.LionUtil;
import com.meituan.toolchain.mario.AnnotationProcessor.MarioProxyUtil;
import lombok.extern.slf4j.Slf4j;
import org.testng.ITestContext;
import org.testng.annotations.*;

import javax.sound.midi.SysexMessage;
import java.util.HashMap;
import java.util.Map;
import static com.meituan.qa.meishi.Hui.entity.LoginEnum.*;



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
    public static HuiMWebApi huiMWebApi = MarioProxyUtil.create(HuiMWebApi.class);

    protected static String doubleWriteMode;
    public Map<LoginEnum, UserModel> userModelMap = new HashMap<LoginEnum, UserModel>();
    //OLD_MAIN 以老为主双写  NEW_MAIN 以新为主双写  NEW_ONLY 单写新
    public static String MainSystem = "NEW_MAIN";
    //#是否校验老订单系统
    public static boolean IS_CHECK_OLD_ORDER_SYSTEM = true;
    //#是否校验新订单系统
    public static boolean IS_CHECK_NEW_ORDER_SYSTEM = true;

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite(ITestContext context) {
        maitonApi.merchantLogin();
        maitonApi.userLogin("maitonuseronlynew");//单写新
        maitonApi.replaceUserInfo2(NEW_ONLY);
        maitonApi.userLogin("maitonuser");//新为主
        maitonApi.replaceUserInfo2(NEW_MAIN);
        maitonApi.userLogin("maitonuseroldmain");//老为主
        maitonApi.replaceUserInfo2(OLD_MAIN);
    }


    @BeforeTest(alwaysRun = true)
    public void beforeTest(ITestContext context) {
        String main = context.getCurrentXmlTest().getParameter("DoubleWriteMode");
        if (main != null) {
            MainSystem = main;
        }
        maitonApi.setCurrentUserModel(MainSystem);
        switch (MainSystem) {
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

//    @BeforeSuite
//    public void beforeTestSetUserForLion() throws Exception {
//        // 判断并改写双写模式
//        if (MainSystem.equals("NEW_MAIN")) {
//            LionUtil.setUserWriteList(maitonApi.getUserModelMap().get(NEW_MAIN.getText() + "_" + OrderSourceEnum.MTApp.getText()).getUserId() + "_1");
//            LionUtil.setUserWriteList(maitonApi.getUserModelMap().get(NEW_MAIN.getText() + "_" + OrderSourceEnum.DPApp.getText()).getUserId() + "_0");
//        }
//        if (MainSystem.equals("OLD_MAIN")) {
//            LionUtil.setUserBlackList(maitonApi.getUserModelMap().get(NEW_MAIN.getText() + "_" + OrderSourceEnum.MTApp.getText()).getUserId() + "_1");
//            LionUtil.setUserBlackList(maitonApi.getUserModelMap().get(NEW_MAIN.getText() + "_" + OrderSourceEnum.DPApp.getText()).getUserId() + "_0");
//        }
//    }
}
