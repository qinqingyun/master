package com.meituan.qa.meishi.Hui.cases.base;

import com.meituan.toolchain.mario.AnnotationProcessor.MarioProxyUtil;
import org.testng.annotations.BeforeClass;

/**
 * Created by buyuqi on 2020/5/29.
 */
public class TestBase {
    public static ThriftApi thriftApi = MarioProxyUtil.create(ThriftApi.class);
    public static MaitonApi maitonApi = MarioProxyUtil.create(MaitonApi.class);

    @BeforeClass(alwaysRun = true)
    public void beforeSuite() {
        maitonApi.userLogin();
    }

}
