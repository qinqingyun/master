package com.meituan.qa.meishi.Hui.cases.base;

import com.meituan.qa.meishi.util.LionUtil;
import com.meituan.toolchain.mario.AnnotationProcessor.MarioProxyUtil;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import static com.meituan.qa.meishi.Hui.util.TestDPLogin.mtUserId;

/**
 * Created by buyuqi on 2020/5/29.
 */
public class TestBase {
    public static ThriftApi thriftApi = MarioProxyUtil.create(ThriftApi.class);
    public static MaitonApi maitonApi = MarioProxyUtil.create(MaitonApi.class);
    protected static String doubleWriteMode;
    //#是否校验老订单系统
    public static boolean IS_CHECK_OLD_ORDER_SYSTEM= true;
    //是否进行db数据diff
    public static boolean IS_CHECK_DB_RECORD=true;
    //#是否校验新订单系统
    public static boolean IS_CHECK_NEW_ORDER_SYSTEM = true;

    @BeforeClass(alwaysRun = true)
    public void beforeSuite() {
        maitonApi.userLogin();
    }

    @BeforeTest
    public void beforeTest() throws Exception {
        // 判断并改写双写模式
        if( doubleWriteMode.equals("NEW")){
            LionUtil.setUserWriteList(mtUserId+"_1");
        }
        if( doubleWriteMode.equals("OLD")){
        LionUtil.setUserBlackList(mtUserId+"_1");
        }
    }

}
