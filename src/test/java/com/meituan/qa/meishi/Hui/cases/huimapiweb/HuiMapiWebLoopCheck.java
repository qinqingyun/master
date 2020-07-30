package com.meituan.qa.meishi.Hui.cases.huimapiweb;

import com.alibaba.fastjson.JSONObject;
import com.meituan.toolchain.mario.annotation.LoopCheck;
import com.meituan.toolchain.mario.model.ResponseMap;

public class HuiMapiWebLoopCheck {
    @LoopCheck(desc = "创建订单请求轮询",interval = 500, timeout = 500*10)
    public ResponseMap createOrderLoopQuery(JSONObject request){
        return null;
    }
}
