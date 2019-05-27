package com.meituan.food.extract.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import com.google.gson.JsonNull;
import com.meituan.food.extract.IGetAppkeyList;
import com.meituan.food.job.vo.AppkeyData;
import com.meituan.food.mapper.AppkeyListPOMapper;
import com.meituan.food.po.AppkeyListPO;
import com.meituan.food.utils.HttpUtils;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.events.Event;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class GetAppkeyListExtracter implements IGetAppkeyList {
    String url="http://ops.vip.sankuai.com/api/v0.2/owts/meituan.web/pdls";

    @Resource
    public AppkeyListPOMapper appkeyListPOMapper;

    @Override
    public void getAppkeyList() {
        JSONObject resp=HttpUtils.doGet(url,JSONObject.class,ImmutableMap.of("Authorization","Bearer 960526c96313d1cf42b6c3c36751ef931ecac858"));
        JSONArray respArr=resp.getJSONArray("pdls");
        for (Object o : respArr) {
            String pdlName=((JSONObject)o).getString("key");
            JSONObject srvResp=HttpUtils.doGet("http://ops.vip.sankuai.com/api/v0.2/pdls/"+pdlName+"/srvs",JSONObject.class,ImmutableMap.of("Authorization","Bearer 960526c96313d1cf42b6c3c36751ef931ecac858"));
            JSONArray srvRespArr=srvResp.getJSONArray("srvs");
            for (Object o1 : srvRespArr) {
                AppkeyListPO po=new AppkeyListPO();
                po.setOwt("meituan.web");
                po.setPdl(pdlName);
                String srvName=((JSONObject)o1).getString("key");
                po.setSrv(srvName);
                JSONObject appkeyResp=HttpUtils.doGet("http://ops.vip.sankuai.com/api/v0.2/srvs/"+srvName+"/appkeys",JSONObject.class,ImmutableMap.of("Authorization","Bearer 960526c96313d1cf42b6c3c36751ef931ecac858"));
                String appkeyName=((JSONArray)(appkeyResp.get("appkeys"))).get(0).toString();
                po.setAppkey(appkeyName);
                po.setDepartmentId(1);
                System.out.println(po.toString());
                appkeyListPOMapper.insert(po);
            }
        }
       /* List<AppkeyData> appkeyDataList=new ArrayList<>();
        appkeyDataList.add(new AppkeyData("meituan.meishi","meituan.meishi.cis","meituan.meishi.cis.salersagent","com.sankuai.meishi.cis.salersagent",2));
        appkeyDataList.add(new AppkeyData("meituan.meishi","meituan.meishi.crm","meituan.meishi.crm.agentcore","com.sankuai.meishi.crm.agentcore",2));
        appkeyDataList.add(new AppkeyData("meituan.meishi","meituan.meishi.crm","meituan.meishi.crm.agent.achiever","com.sankuai.meishi.crm.agent.achiever",2));

        for (AppkeyData data : appkeyDataList) {
            AppkeyListPO a=new AppkeyListPO();
            a.setDepartmentId(data.getDepartmentId());
            a.setAppkey(data.getAppkeyName());
            a.setSrv(data.getSrvName());
            a.setPdl(data.getPdlName());
            a.setOwt(data.getOwtName());

            appkeyListPOMapper.insert(a);
        }*/

        List<AppkeyData> pdlList=new ArrayList<>();
        pdlList.add(new AppkeyData("meituan.nibmp","meituan.nibmp.infra","","",6));
        pdlList.add(new AppkeyData("meituan.nibmp","meituan.nibmp.mbo","","",6));
        pdlList.add(new AppkeyData("meituan.nibmp","meituan.nibmp.mva","","",6));
        pdlList.add(new AppkeyData("meituan.meishi","meituan.meishi.crm","","",2));
        pdlList.add(new AppkeyData("meituan.meishi","meituan.meishi.merchant","","",3));
        pdlList.add(new AppkeyData("meituan.meishi","meituan.meishi.scp","","",4));
        pdlList.add(new AppkeyData("meituan.meishi","meituan.meishi.finance","","",5));
        pdlList.add(new AppkeyData("meituan.resv","meituan.resv.b","","",7));
        pdlList.add(new AppkeyData("meituan.resv","meituan.resv.c","","",7));
        pdlList.add(new AppkeyData("meituan.resv","meituan.resv.m","","",7));

        List<String> appkeyStrList2=new ArrayList<>();
        appkeyStrList2.add("com.sankuai.meishi.cis.salersagent");
        appkeyStrList2.add("com.sankuai.meishi.crm.agentcore");
        appkeyStrList2.add("com.sankuai.meishi.crm.agent.achiever");
        appkeyStrList2.add("com.sankuai.meishi.scp.mtagent");
        appkeyStrList2.add("com.sankuai.meishi.scp.esignplatform");

        List<String> appkeyStrList3=new ArrayList<>();
        appkeyStrList3.add("com.sankuai.meishi.scp.mtcharge");

        for (AppkeyData appkeyData : pdlList) {
            String pdlName=appkeyData.getPdlName();
            JSONObject srvResp=HttpUtils.doGet("http://ops.vip.sankuai.com/api/v0.2/pdls/"+pdlName+"/srvs",JSONObject.class,ImmutableMap.of("Authorization","Bearer 960526c96313d1cf42b6c3c36751ef931ecac858"));
            JSONArray srvRespArr=srvResp.getJSONArray("srvs");
            for (Object o1 : srvRespArr) {
                AppkeyListPO listPO=new AppkeyListPO();
                listPO.setOwt(appkeyData.getOwtName());
                listPO.setPdl(appkeyData.getPdlName());
                String srvName=((JSONObject)o1).getString("key");
                listPO.setSrv(srvName);
                JSONObject appkeyResp=HttpUtils.doGet("http://ops.vip.sankuai.com/api/v0.2/srvs/"+srvName+"/appkeys",JSONObject.class,ImmutableMap.of("Authorization","Bearer 960526c96313d1cf42b6c3c36751ef931ecac858"));
                if (!appkeyResp.get("appkeys").toString().equals("[]")){
                    System.out.println("appkey="+appkeyResp.get("appkeys").toString());
                    String appkeyName = ((JSONArray) (appkeyResp.get("appkeys"))).get(0).toString();
                    listPO.setAppkey(appkeyName);
                    if (appkeyData.getDepartmentId() == 4) {
                        if (appkeyStrList2.contains(appkeyName)) {
                            listPO.setDepartmentId(2);
                        } else if (appkeyStrList3.contains(appkeyName)) {
                            listPO.setDepartmentId(3);
                        } else {
                            listPO.setDepartmentId(4);
                        }
                    } else {
                        listPO.setDepartmentId(appkeyData.getDepartmentId());
                    }
                    appkeyListPOMapper.insert(listPO);
                }
            }
        }


    }
}
