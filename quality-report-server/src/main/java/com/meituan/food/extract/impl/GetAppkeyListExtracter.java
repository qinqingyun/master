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
import groovy.json.StringEscapeUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.events.Event;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
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
                String encodedRank=((JSONObject)o1).getString("rank");
                String rank=StringEscapeUtils.unescapeJava(encodedRank);
                if(rank.equals("核心服务")){
                    po.setRank(1);
                }else if (rank.equals("非核心服务")){
                    po.setRank(2);
                }else {
                    po.setRank(0);
                }
                Date now=new Date();
                po.setCreatedTime(now);
                po.setUpdatedTime(now);
                po.setOffline(0);
                JSONObject appkeyResp=HttpUtils.doGet("http://ops.vip.sankuai.com/api/v0.2/srvs/"+srvName+"/appkeys",JSONObject.class,ImmutableMap.of("Authorization","Bearer 960526c96313d1cf42b6c3c36751ef931ecac858"));
                String appkeyName=((JSONArray)(appkeyResp.get("appkeys"))).get(0).toString();
                po.setAppkey(appkeyName);
                po.setDepartmentId(1);
                po.setDepartmentId2(1);
                System.out.println(po.toString());
                appkeyListPOMapper.insert(po);
            }
        }

        List<AppkeyData> pdlList=new ArrayList<>();
        pdlList.add(new AppkeyData("meituan.nibmp","meituan.nibmp.infra","","",6,6));
        pdlList.add(new AppkeyData("meituan.nibmp","meituan.nibmp.mbo","","",6,8));
        pdlList.add(new AppkeyData("meituan.nibmp","meituan.nibmp.mva","","",6,9));
        pdlList.add(new AppkeyData("meituan.meishi","meituan.meishi.crm","","",2,2));
        pdlList.add(new AppkeyData("meituan.meishi","meituan.meishi.merchant","","",3,3));
        pdlList.add(new AppkeyData("meituan.meishi","meituan.meishi.scp","","",4,4));
        pdlList.add(new AppkeyData("meituan.meishi","meituan.meishi.finance","","",5,5));
        pdlList.add(new AppkeyData("meituan.resv","meituan.resv.b","","",7,7));
        pdlList.add(new AppkeyData("meituan.resv","meituan.resv.c","","",7,7));
        pdlList.add(new AppkeyData("meituan.resv","meituan.resv.m","","",7,7));

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
                String encodedRank=((JSONObject)o1).getString("rank");
                String rank=StringEscapeUtils.unescapeJava(encodedRank);
                if(rank.equals("核心服务")){
                    listPO.setRank(1);
                }else if (rank.equals("非核心服务")){
                    listPO.setRank(2);
                }else {
                    listPO.setRank(0);
                }
                Date now=new Date();
                listPO.setCreatedTime(now);
                listPO.setUpdatedTime(now);
                listPO.setOffline(0);
                JSONObject appkeyResp=HttpUtils.doGet("http://ops.vip.sankuai.com/api/v0.2/srvs/"+srvName+"/appkeys",JSONObject.class,ImmutableMap.of("Authorization","Bearer 960526c96313d1cf42b6c3c36751ef931ecac858"));
                if (!appkeyResp.get("appkeys").toString().equals("[]")){
                    System.out.println("appkey="+appkeyResp.get("appkeys").toString());
                    String appkeyName = ((JSONArray) (appkeyResp.get("appkeys"))).get(0).toString();
                    listPO.setAppkey(appkeyName);
                    if (appkeyData.getDepartmentId() == 4) {
                        if (appkeyStrList2.contains(appkeyName)) {
                            listPO.setDepartmentId(2);
                            listPO.setDepartmentId2(2);
                        } else if (appkeyStrList3.contains(appkeyName)) {
                            listPO.setDepartmentId(3);
                            listPO.setDepartmentId2(3);
                        } else {
                            listPO.setDepartmentId(4);
                            listPO.setDepartmentId2(4);
                        }
                    } else {
                        listPO.setDepartmentId(appkeyData.getDepartmentId());
                        listPO.setDepartmentId2(appkeyData.getDepartmentId2());
                    }
                    appkeyListPOMapper.insert(listPO);
                }
            }
        }


    }

    public static void main(String[] args) {
       String a= StringEscapeUtils.unescapeJava("\\u975e\\u6838\\u5fc3\\u670d\\u52a1");
        System.out.println(a);

        DateTime now=new DateTime();
        System.out.println(now.toString());
    }
}
