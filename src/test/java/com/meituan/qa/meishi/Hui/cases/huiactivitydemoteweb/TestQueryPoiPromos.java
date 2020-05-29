package com.meituan.qa.meishi.Hui.cases.huiactivitydemoteweb;


import com.alibaba.fastjson.JSONObject;
import com.dianping.hui.activity.demote.response.QueryPoiPromosResponse;
import com.dianping.hui.activity.demote.service.QueryPoiInfosService;
import com.dianping.hui.activity.demote.thriftdto.ShopPromosThriftDto;
import com.meituan.qa.meishi.util.ClassAnnotation;
import com.meituan.toolchain.mario.annotation.ThriftAPI;
import com.meituan.toolchain.mario.framework.DBDataProvider;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

@ClassAnnotation(author = "liukang",depart = "C",apiName = "/hui/querypoiinfos/querypoipromos",
        type = "thrift",des="查询门店优惠信息（目前已知预定频道列表下的买单方案是由这个接口控制https://flow.sankuai.com/browse/MT-12158）")
@Slf4j
@ThriftAPI(methodName = "/hui/querypoiinfos/querypoipromos")
public class TestQueryPoiPromos {

    private String _APIPATH = "/hui/querypoiinfos/querypoipromos";
    private String _CASEFOLDER = "Hui/huiactivitydemoteweb/querypoipromos";

	@ThriftAPI(appkey = "hui-activity-demote-web")
    QueryPoiInfosService queryPoiInfosService;


    @Test(groups = {"P1"},dataProvider = "dbdata", dataProviderClass = DBDataProvider.class)
    public void ms_c_queryPoiPromos_001(JSONObject request, JSONObject expect)  {
//        RequestsFromDB requests = new RequestsFromDB(Constants.Protocol.thrift,_APIPATH,"ms_c_queryPoiPromos_001");
        JSONObject requesJson = request;
        List<Integer> poiIds = JSONObject.parseArray(requesJson.getJSONArray("poiIds").toJSONString(), Integer.class);
        QueryPoiPromosResponse queryPoiPromosResponse= null;
        try {

            queryPoiPromosResponse = queryPoiInfosService.queryPoiPromos(poiIds);
        } catch (TException e) {
            e.printStackTrace();
        }
        System.out.println(queryPoiPromosResponse);
        Map<Integer,List<ShopPromosThriftDto>> resp = queryPoiPromosResponse.getPromos();
        for( Map.Entry<Integer,List<ShopPromosThriftDto>> entry:resp.entrySet()){
            List<ShopPromosThriftDto> respList = entry.getValue();
            ShopPromosThriftDto shopPromosThriftDto = respList.get(0);
            System.out.println(shopPromosThriftDto.getTitle());
//            System.out.println(shopPromosThriftDto.getRatio());

        }



    }
}
