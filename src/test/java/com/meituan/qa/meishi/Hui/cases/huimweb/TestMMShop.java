package com.meituan.qa.meishi.Hui.cases.huimweb;

import com.meituan.qa.meishi.Hui.cases.base.TestBase;
import com.meituan.qa.meishi.util.ClassAnnotation;
import com.meituan.qa.meishi.util.MethodAnotation;
import com.meituan.toolchain.mario.model.ResponseMap;
import com.meituan.toolchain.mario.util.AssertUtil;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import static com.meituan.qa.meishi.Hui.entity.OrderSourceEnum.*;

/**
 * url：http://mm.51ping.com/hui/mm/shop?shopId=66526423
 * 场景：买单码对应的url。微信扫码后，进入点评mm站的提单页面之前的跳转页面，微信小程序跳转页
 *      0-APP跳商戶页并且MM站跳到ERROR页
 *      1-APP跳收银台并且MM站跳shop页
 *      2-APP判断版本跳中间页并且MM站跳自助餐选择页面
 *
 * rd仓库目录：com.dianping.hui.mm.action.TransferAction
 */
@ClassAnnotation(author = "zhenyumin",depart = "C",type = "http",httpMethod = "get",des = "点评mm站的提单页面入口")
@Slf4j
public class TestMMShop extends TestBase {

    @Test(groups = "{P1}")
    @MethodAnotation(author = "zhenyumin",createTime = "2020-10-26",des = "点评mm站提单页面入口")
    public void ms_c_mmShop_01(){
        ResponseMap responseMap = huiMWebApi.mmShop("ms_c_mmShop_01",DPApp,maitonApi.dpWxClient);
        log.info("返回结果：{}",responseMap.getResponseBody());

        AssertUtil.assertHttpCode(responseMap,200,"http状态码!=200");
        AssertUtil.assertContain(responseMap.getResponseBody(),"跳转中");

    }
}
