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
        Date now=new Date();
        List<AppkeyListPO> appkeyListPOS=new ArrayList<>();
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
                po.setCreatedTime(now);
                po.setUpdatedTime(now);
                po.setOffline(0);
                JSONObject appkeyResp=HttpUtils.doGet("http://ops.vip.sankuai.com/api/v0.2/srvs/"+srvName+"/appkeys",JSONObject.class,ImmutableMap.of("Authorization","Bearer 960526c96313d1cf42b6c3c36751ef931ecac858"));
                String appkeyName=((JSONArray)(appkeyResp.get("appkeys"))).get(0).toString();
                po.setAppkey(appkeyName);
                po.setDepartmentId(1);
                po.setDepartmentId2(1);
                System.out.println(po.toString());
                appkeyListPOS.add(po);
              //  appkeyListPOMapper.insert(po);
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
                    appkeyListPOS.add(listPO);
                }
            }
        }

        //客户平台和合同平台的appkey
        List<String> appkeyList=new ArrayList<>();
        appkeyList.add("meituan.nibcus.inf.nibcus-inf-customer");
        appkeyList.add("meituan.nibcus.inf.contract-mtcontract");

        for (String s : appkeyList) {
            AppkeyListPO po=new AppkeyListPO();
            po.setOwt("meituan.nibcus");
            po.setPdl("meituan.nibcus.inf");
            po.setSrv(s);
            JSONObject appkeyResp=HttpUtils.doGet("http://ops.vip.sankuai.com/api/v0.2/srvs/"+s+"/appkeys",JSONObject.class,ImmutableMap.of("Authorization","Bearer 960526c96313d1cf42b6c3c36751ef931ecac858"));
            if (!appkeyResp.get("appkeys").toString().equals("[]")){
                System.out.println("appkey="+appkeyResp.get("appkeys").toString());
                String appkeyName = ((JSONArray) (appkeyResp.get("appkeys"))).get(0).toString();
                po.setAppkey(appkeyName);
                if (s.equals("meituan.nibcus.inf.nibcus-inf-customer")){
                    po.setDepartmentId(10);
                    po.setDepartmentId2(10);
                } else {
                    po.setDepartmentId(11);
                    po.setDepartmentId2(11);
                }
                po.setOffline(0);
                po.setRank(1);
                po.setCreatedTime(now);
                po.setUpdatedTime(now);
                appkeyListPOS.add(po);
            }
        }

        for (AppkeyListPO appkeyListPO : appkeyListPOS) {
            System.out.println(appkeyListPO.toString());
            AppkeyListPO po1 = appkeyListPOMapper.selectByAppKey(appkeyListPO.getAppkey());
            if (po1==null){
                appkeyListPOMapper.insert(appkeyListPO);
            }else {
                if (po1.getRank()==1&&appkeyListPO.getRank()==2){
                    appkeyListPOMapper.updateToNonCore(appkeyListPO.getAppkey(),now);
                }else if (po1.getRank()==2&&appkeyListPO.getRank()==1){
                    appkeyListPOMapper.updateToCore(appkeyListPO.getAppkey(),now);
                }
            }
        }


    }

    public static void main(String[] args) {
     JSONObject re=HttpUtils.doGet("http://ops.vip.sankuai.com/api/v0.2/pdls/meituan.meishi.scp/srvs",JSONObject.class,ImmutableMap.of("Authorization","Bearer 960526c96313d1cf42b6c3c36751ef931ecac858"));
     System.out.println(re.toString());

    }
}
