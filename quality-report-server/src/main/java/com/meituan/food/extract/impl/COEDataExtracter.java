package com.meituan.food.extract.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import com.meituan.food.extract.ICOEDataExtract;
import com.meituan.food.mapper.CoeListP0Mapper;
import com.meituan.food.po.CoeListP0;
import com.meituan.food.utils.HttpUtils;
import com.meituan.food.utils.SsoUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.events.Event;
import retrofit2.http.PUT;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class COEDataExtracter implements ICOEDataExtract {

    private static final String url = "http://coe.sankuai.com/api/v1.0/query/incidents";

    private static final String coeUrl = "https://coe.mws.sankuai.com/detail/";

    private static final String availabilityUrl = "https://coe.sankuai.com/api/v1.0/trend/availability?start=2019-01-01&end=2019-12-31";

    private static final String coeDetailUrl = "https://coe.sankuai.com/api/v1.0/incidents/";

    @Resource
    private CoeListP0Mapper coeListP0Mapper;

    @Override
    public void getCOEData(String firstDateStr, String secondDateStr) {

        List<Integer> orgList=new ArrayList<>();
        orgList.add(43442);
        orgList.add(70);
        orgList.add(43330);
        orgList.add(35756);
        List<Integer> coeIdList=coeListP0Mapper.selectCoeIdList();

        for (Integer org : orgList) {
            JSONObject params=new JSONObject();
            params.put("occur_start",firstDateStr);
            params.put("occur_end",secondDateStr);
            params.put("org",org);
            params.put("page",1);
            params.put("page_size",100);
            params.put("sort","desc");
            params.put("sort_by","create_at");
            params.put("list_type","all");

            JSONObject resp=HttpUtils.doPost(url,params.toJSONString(),JSONObject.class,ImmutableMap.of("content-type", "application/json","Accept","text/plain, text/html,application/json","Authorization", "Bearer 4feddd87883b416c6c2d79b9dbdbe47b5284dc57"));
            JSONArray incidentsArray=resp.getJSONArray("incidents");
            if(incidentsArray.size()!=0){
                for (Object o : incidentsArray) {
                    CoeListP0 coeP0=new CoeListP0();
                    coeP0.setBrief(((JSONObject)o).getString("brief"));
                    coeP0.setOccurDate(((JSONObject)o).getDate("occur_time"));
                    coeP0.setCoeId(((JSONObject)o).getInteger("_id"));
                    coeP0.setLevel(((JSONObject)o).getString("level"));
                    String ownerStr=((JSONObject)o).getString("owner");
                    coeP0.setFindTime(((JSONObject)o).getDate("find_time"));
                    coeP0.setFminusoTime(((JSONObject)o).getInteger("fminuso_time"));
                    coeP0.setHandleTime(((JSONObject)o).getDate("handle_time"));
                    coeP0.setLminusfTime(((JSONObject)o).getInteger("lminusf_time"));
                    coeP0.setLocationTime(((JSONObject)o).getDate("location_time"));
                    coeP0.setNotifyTime(((JSONObject)o).getDate("notify_time"));
                    coeP0.setSminushTime(((JSONObject)o).getInteger("lminusf_time"));
                    coeP0.setSolvedTime(((JSONObject)o).getDate("solved_time"));
                    coeP0.setWiki(((JSONObject)o).getString("wiki"));
                    coeP0.setCoeLink(coeUrl+((JSONObject)o).getInteger("_id"));
                    coeP0.setCategory(((JSONObject)o).getString("category"));
                    coeP0.setAppearance(((JSONObject)o).getString("appearance"));
                    if (ownerStr!=null){
                        String ownerMis=ownerStr.substring(0,ownerStr.indexOf("/"));
                        String ownerName=ownerStr.substring(ownerStr.indexOf("/")+1);
                        coeP0.setOwnerMis(ownerMis);
                        coeP0.setOwnerName(ownerName);
                    }
                    int coeId=((JSONObject)o).getInteger("_id");
                    if (coeIdList.contains(coeId)){
                        CoeListP0 coeListP0 = coeListP0Mapper.selectByCoeId(coeId);
                        coeP0.setId(coeListP0.getId());
                        coeListP0Mapper.updateByPrimaryKey(coeP0);
                    }else {
                        coeListP0Mapper.insert(coeP0);
                    }
                }
            }
        }

        JSONObject availabilityResp = HttpUtils.doGet(availabilityUrl, JSONObject.class, ImmutableMap.of("content-type", "application/json", "Accept", "text/plain, text/html,application/json", "Authorization", "Bearer 4feddd87883b416c6c2d79b9dbdbe47b5284dc57"));
        JSONArray bgAvaiDataArray = availabilityResp.getJSONArray("availabilities");
        List<Integer> coeIdList2 = coeListP0Mapper.selectCoeIdList();
        if (bgAvaiDataArray.size() != 0) {
            for (Object bgData : bgAvaiDataArray) {
                String bgName = ((JSONObject) bgData).getString("bg");
                if (bgName.equals("到店餐饮研发中心")) {
                    JSONArray issueData = ((JSONObject) bgData).getJSONArray("detail");
                    if (issueData.size() != 0) {
                        for (Object data : issueData) {
                            int coeId = ((JSONObject) data).getInteger("_id");
                            JSONObject coeDetailResp = HttpUtils.doGet(coeDetailUrl + coeId, JSONObject.class, ImmutableMap.of("content-type", "application/json", "Accept", "text/plain, text/html,application/json", "Authorization", "Bearer 4feddd87883b416c6c2d79b9dbdbe47b5284dc57"));
                            JSONObject incidentDetail=coeDetailResp.getJSONObject("incident");
                            CoeListP0 coeP0 = new CoeListP0();
                            coeP0.setCoeId(coeId);
                            coeP0.setCoeLink(coeUrl + coeId);
                            coeP0.setBrief(incidentDetail.getString("brief"));
                            Date occurTime = (incidentDetail.getDate("occur_time"));
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            String format = sdf.format(occurTime);
                            try {
                                Date occurDate = sdf.parse(format);
                                coeP0.setOccurDate(occurDate);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            coeP0.setNotifyTime(incidentDetail.getDate("notify_time"));
                            coeP0.setFindTime(incidentDetail.getDate("find_time"));
                            coeP0.setLocationTime(incidentDetail.getDate("location_time"));
                            coeP0.setHandleTime(incidentDetail.getDate("handle_time"));
                            coeP0.setSolvedTime(incidentDetail.getDate("solved_time"));
                            coeP0.setFminusoTime(incidentDetail.getInteger("fminuso_time"));
                            coeP0.setLminusfTime(incidentDetail.getInteger("lminusf_time"));
                            coeP0.setSminushTime(incidentDetail.getInteger("sminush_time"));
                            coeP0.setWiki(incidentDetail.getString("wiki"));
                            coeP0.setLevel(incidentDetail.getString("level"));
                            String ownerStr=(incidentDetail.getString("owner"));
                            coeP0.setAppearance(incidentDetail.getString("appearance"));
                            if (ownerStr!=null){
                                if (ownerStr.contains("/")) {
                                    String ownerMis = ownerStr.substring(0, ownerStr.indexOf("/"));
                                    String ownerName = ownerStr.substring(ownerStr.indexOf("/") + 1);
                                    coeP0.setOwnerMis(ownerMis);
                                    coeP0.setOwnerName(ownerName);
                                } else {
                                    coeP0.setOwnerMis(ownerStr);
                                }
                            }
                            coeP0.setCategory(incidentDetail.getString("category"));
                            if (coeIdList2.contains(coeId)){
                                CoeListP0 coeListP0 = coeListP0Mapper.selectByCoeId(coeId);
                                coeP0.setId(coeListP0.getId());
                                coeListP0Mapper.updateByPrimaryKey(coeP0);
                            }else {
                                coeListP0Mapper.insert(coeP0);
                            }
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        ICOEDataExtract a=new COEDataExtracter();
        a.getCOEData("2019-01-01","2019-12-31");
    }
}
