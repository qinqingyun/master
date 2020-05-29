package com.meituan.qa.meishi.Hui.util.service;

import com.meituan.qa.meishi.Hui.util.CreateOrderUtil;

public class QueryStatusServiceImpl implements QueryStatusService {

    @Override
    public void queryMopay(String mtToken, String mtClient, String serializedId) {
        CreateOrderUtil.queryMopayStatus(mtToken, mtClient, serializedId, "ms_c_huiFullProcess_101_queryMopayStatus");
    }

}
