package com.meituan.qa.meishi.Hui.cases.huiactivitydemoteweb;

import com.alibaba.fastjson.JSONObject;
import com.dianping.hui.activity.demote.response.QueryPoiStatusResponse;
import com.dianping.hui.activity.demote.service.QueryPoiInfosService;
import com.dianping.hui.activity.demote.thriftdto.ShopStatusThriftDto;
import com.meituan.qa.meishi.util.ClassAnnotation;
import com.meituan.toolchain.mario.annotation.ThriftAPI;
import com.meituan.toolchain.mario.framework.DBDataProvider;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.List;

@ClassAnnotation(author = "liukang",depart = "C",apiName = "/hui/querypoiinfos/querypoistatus",
        type = "thrift",des="查询门店状态")
@Slf4j
@ThriftAPI(methodName = "/hui/querypoiinfos/querypoistatus")
public class TestQueryPoiStatus {

    private String _APIPATH = "/hui/querypoiinfos/querypoistatus";

	@ThriftAPI(appkey = "hui-activity-demote-web")
    QueryPoiInfosService queryPoiInfosService;


    @Test(groups = {"P1"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    public void ms_c_queryPoiStatus_001(JSONObject request, JSONObject expect){

        JSONObject requesJson = request;
        List<Integer> poiIds = JSONObject.parseArray(request.getJSONArray("poiIds").toJSONString(), Integer.class);
        QueryPoiStatusResponse queryPoiStatusResponse = null;
        try {
            queryPoiStatusResponse = queryPoiInfosService.queryPoiStatus(poiIds);
        } catch (TException e) {
            e.printStackTrace();
        }
        String message = queryPoiStatusResponse.getMessage();
        List<ShopStatusThriftDto> lists = queryPoiStatusResponse.getData();
        Iterator<ShopStatusThriftDto> iterator = lists.iterator();
        while(iterator.hasNext()){
            ShopStatusThriftDto shopStatusThriftDto = iterator.next();
            System.out.println(shopStatusThriftDto.getPoiId() + ":"+shopStatusThriftDto.getStatus());
        }
    }

}
