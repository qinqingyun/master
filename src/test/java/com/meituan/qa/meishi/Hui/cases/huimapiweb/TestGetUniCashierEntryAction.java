package com.meituan.qa.meishi.Hui.cases.huimapiweb;

import com.meituan.qa.meishi.Hui.util.TestDPLogin;
import com.meituan.qa.meishi.util.ClassAnnotation;
import com.meituan.toolchain.mario.annotation.HTTPAPI;
import lombok.extern.slf4j.Slf4j;

/*
* 场景：搜索列表页该商户的HasPay或HasMOPay为 true 时调用.
* 使用平台：点评侧
* */
@ClassAnnotation(author = "zhenyumin",depart = "C",apiName = "",type = "mapi",des = "获取商户页统一收银台入口")
@Slf4j
@HTTPAPI(apiPath = "hui/getunicashierentry.hui")
public class TestGetUniCashierEntryAction extends TestDPLogin {

}
