package com.meituan.qa.meishi.Hui.cases.huimapiweb;

import com.meituan.qa.meishi.Hui.util.TestDPLogin;
import com.meituan.qa.meishi.util.ClassAnnotation;
import com.meituan.toolchain.mario.annotation.HTTPAPI;
import lombok.extern.slf4j.Slf4j;

/*
* 场景：点评、美团APP，无买单自助验券直接调用,通过将userid、团购订单id、券码、数量写入缓存实现幂等
* */
@ClassAnnotation(author = "zhenyumin", depart = "C",apiName = "",type = "mapi",des = "无买单自助验券")
@Slf4j
@HTTPAPI(apiPath = "hui/receiptverify.bin")
public class TestReceiptVerifyAction extends TestDPLogin {
}
