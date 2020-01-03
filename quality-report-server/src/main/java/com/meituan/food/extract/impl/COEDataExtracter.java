package com.meituan.food.extract.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import com.meituan.food.extract.ICOEDataExtract;
import com.meituan.food.mapper.CoeListP0Mapper;
import com.meituan.food.po.CoeListP0;
import com.meituan.food.utils.DaXiangUtils;
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

    private static final String availabilityUrl = "https://coe.sankuai.com/api/v1.0/trend/availability?start=2019-01-01&end=2020-12-31";

    private static final String coeDetailUrl = "https://coe.sankuai.com/api/v1.0/incidents/";

    private static final String coeTypeUrl="https://coe.sankuai.com/api/v1.0/incidents/";

    private static final String coeImprovementsUrl="https://coe.sankuai.com/api/v1.0/incidents/";

    @Resource
    private CoeListP0Mapper coeListP0Mapper;

    @Override
    public void getCOEData(String firstDateStr, String secondDateStr) throws ParseException {

        List<Integer> orgList=new ArrayList<>();
     //   orgList.add(43442);
        orgList.add(70); //到店餐饮研发中心
       // orgList.add(43330);
        orgList.add(35756);//平台终端研发组/到店餐饮研发组
        orgList.add(53235);//商家平台研发组
        orgList.add(53242);//客户平台研发组
        orgList.add(42154);//平台业务研发中心 /供应链平台研发组 / 到餐供应链研发组
        orgList.add(15435);//平台业务研发中心 /销售支持平台研发组 / 到餐销售支持研发组
        orgList.add(119);//平台业务研发中心 /交易平台研发组 / 餐饮交易技术组
        orgList.add(126);//平台业务研发中心 /营销平台研发组 / 到餐营销支持组
        orgList.add(75272);//平台业务研发中心/门店信息研发组

        String pushStr="商家平台新增COE：";
        int newCoe=0;

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
                            Date findTime = (incidentDetail).getDate("find_time");
                            coeP0.setFindTime(findTime);
                            if (findTime!=null){
                                Date findDate = sdf.parse(format);
                                coeP0.setFindDate(findDate);
                            }
                            coeP0.setLocationTime(incidentDetail.getDate("location_time"));
                            coeP0.setHandleTime(incidentDetail.getDate("handle_time"));
                            coeP0.setSolvedTime(incidentDetail.getDate("solved_time"));
                            coeP0.setFminusoTime(incidentDetail.getInteger("fminuso_time"));
                            coeP0.setLminusfTime(incidentDetail.getInteger("lminusf_time"));
                            coeP0.setSminushTime(incidentDetail.getInteger("sminush_time"));
                            coeP0.setWiki(incidentDetail.getString("wiki"));
                            coeP0.setLevel(incidentDetail.getString("level"));
                            String ownerStr=(incidentDetail.getString("owner"));
                            Date clearTime = (incidentDetail).getDate("clear_time");
                            coeP0.setClearTime(clearTime);
                            if (clearTime!=null&&findTime!=null){
                                long incluence=(clearTime.getTime()-occurTime.getTime())/1000/60;
                                if (incidentDetail.getInteger("fminuso_time")!=null){
                                    int incluenceTime=new Long(incluence).intValue()+incidentDetail.getInteger("fminuso_time");
                                    coeP0.setInfluenceTime(incluenceTime);
                                }
                            }
                            JSONArray finderArray=(incidentDetail).getJSONArray("finders");
                            if (finderArray.size()!=0){
                                String finder = finderArray.get(0).toString();
                                coeP0.setFinder(finder);

                            }
                            coeP0.setAvailable(true);
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
                            String orgPath = incidentDetail.getString("org_path");
                            if (orgPath.contains("平台技术部/"))
                            {
                                String subOrgPath = orgPath.substring(orgPath.indexOf("平台技术部/") + 6);
                                coeP0.setOrgName(subOrgPath);

                            }else {
                                String subOrgPath = orgPath.substring(orgPath.indexOf("集团/") + 3);
                                coeP0.setOrgName(subOrgPath);
                            }
                            if (orgPath.contains("到店餐饮研发中心")||orgPath.contains("平台业务研发中心/商家平台研发组/增值平台研发组")||orgPath.contains("平台业务研发中心/商家平台研发组/客户平台研发组")||orgPath.contains("平台终端研发组/到店餐饮研发组")){
                                coeP0.setCategory(incidentDetail.getString("category"));

                                JSONObject coeTypeResp=HttpUtils.doGet(coeTypeUrl+coeId+"/types",JSONObject.class,ImmutableMap.of("content-type", "application/json", "Accept", "text/plain, text/html,application/json", "Authorization", "Bearer 4feddd87883b416c6c2d79b9dbdbe47b5284dc57"));
                                JSONArray coeTypeArray=coeTypeResp.getJSONArray("types");
                                if (coeTypeArray.size()!=0){
                                    JSONObject reason = (JSONObject) coeTypeArray.get(0);
                                    coeP0.setSubCategory(reason.getString("parent"));
                                }
                            }else {
                                coeP0.setCategory("第三方");
                                coeP0.setSubCategory("第三方");
                            }

                            JSONObject coeImprovementsResp=HttpUtils.doGet(coeImprovementsUrl+coeId+"/improvements",JSONObject.class,ImmutableMap.of("content-type", "application/json", "Accept", "text/plain, text/html,application/json", "Authorization", "Bearer 4feddd87883b416c6c2d79b9dbdbe47b5284dc57"));
                            JSONArray coeImproArr=coeImprovementsResp.getJSONArray("improvements");
                            int doneCount=0;
                            int todoCount=0;
                            String taskLink="";
                            if (coeImproArr.size()!=0){
                                coeP0.setAllTodo(coeImproArr.size());
                                for (Object o1 : coeImproArr) {
                                    String status=((JSONObject)o1).getString("status");
                                    if (status.equals("DONE")){
                                        doneCount++;
                                    }else {
                                        todoCount++;
                                        if (todoCount!=1){
                                            taskLink=taskLink+" ; "+((JSONObject)o1).getString("url");
                                        }else {
                                            taskLink=taskLink+((JSONObject)o1).getString("url");
                                        }
                                    }
                                }
                            }
                            coeP0.setNotFinishTodo(todoCount);
                            coeP0.setFinishTodo(doneCount);
                            coeP0.setNotFinishTodoTask(taskLink);

                            if (coeIdList2.contains(coeId)){
                                CoeListP0 coeListP0 = coeListP0Mapper.selectByCoeId(coeId);
                                coeP0.setId(coeListP0.getId());
                                coeP0.setAvailable(coeListP0.getAvailable());
                                coeListP0Mapper.updateByPrimaryKey(coeP0);
                            }else {
                                coeListP0Mapper.insert(coeP0);
                            }
                        }
                    }
                }
            }
        }


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
            List<Integer> coeIdList=coeListP0Mapper.selectCoeIdList();

            JSONObject resp=HttpUtils.doPost(url,params.toJSONString(),JSONObject.class,ImmutableMap.of("content-type", "application/json","Accept","text/plain, text/html,application/json","Authorization", "Bearer 4feddd87883b416c6c2d79b9dbdbe47b5284dc57"));
            JSONArray incidentsArray=resp.getJSONArray("incidents");
            if(incidentsArray.size()!=0){
                for (Object o : incidentsArray) {
                    CoeListP0 coeP0=new CoeListP0();
                    coeP0.setBrief(((JSONObject)o).getString("brief"));
                    Date occurTime = ((JSONObject) o).getDate("occur_time");
                    coeP0.setOccurDate(occurTime);
                    coeP0.setCoeId(((JSONObject)o).getInteger("_id"));
                    coeP0.setLevel(((JSONObject)o).getString("level"));
                    String ownerStr=((JSONObject)o).getString("owner");
                    Date findTime = ((JSONObject) o).getDate("find_time");
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    if (findTime!=null){
                        String format = sdf.format(findTime);
                        coeP0.setFindTime(findTime);
                        Date findDate = sdf.parse(format);
                        coeP0.setFindDate(findDate);
                    }
                    coeP0.setFminusoTime(((JSONObject)o).getInteger("fminuso_time"));
                    coeP0.setHandleTime(((JSONObject)o).getDate("handle_time"));
                    coeP0.setLminusfTime(((JSONObject)o).getInteger("lminusf_time"));
                    coeP0.setLocationTime(((JSONObject)o).getDate("location_time"));
                    coeP0.setNotifyTime(((JSONObject)o).getDate("notify_time"));
                    coeP0.setSminushTime(((JSONObject)o).getInteger("sminush_time"));
                    coeP0.setSolvedTime(((JSONObject)o).getDate("solved_time"));
                    coeP0.setWiki(((JSONObject)o).getString("wiki"));
                    coeP0.setCoeLink(coeUrl+((JSONObject)o).getInteger("_id"));
                    coeP0.setCategory(((JSONObject)o).getString("category"));
                    coeP0.setAppearance(((JSONObject)o).getString("appearance"));
                    Date clearTime = ((JSONObject) o).getDate("clear_time");
                    coeP0.setClearTime(clearTime);
                    if (clearTime!=null&&findTime!=null){
                        long incluence=(clearTime.getTime()-findTime.getTime())/1000/60;
                        if (((JSONObject)o).getInteger("fminuso_time")!=null){
                            int incluenceTime=new Long(incluence).intValue()+((JSONObject)o).getInteger("fminuso_time");
                            coeP0.setInfluenceTime(incluenceTime);
                        }
                    }
                    JSONArray finderArray=((JSONObject) o).getJSONArray("finders");
                    if (finderArray.size()!=0){
                        String finder = finderArray.get(0).toString();
                        coeP0.setFinder(finder);

                    }
                    coeP0.setAvailable(true);
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
                    int coeId=((JSONObject)o).getInteger("_id");

                    JSONObject coeTypeResp=HttpUtils.doGet(coeTypeUrl+coeId+"/types",JSONObject.class,ImmutableMap.of("content-type", "application/json", "Accept", "text/plain, text/html,application/json", "Authorization", "Bearer 4feddd87883b416c6c2d79b9dbdbe47b5284dc57"));
                    JSONArray coeTypeArray=coeTypeResp.getJSONArray("types");
                    if (coeTypeArray.size()!=0){
                        JSONObject reason = (JSONObject) coeTypeArray.get(0);
                        coeP0.setSubCategory(reason.getString("parent"));
                    }

                    JSONObject coeImprovementsResp=HttpUtils.doGet(coeImprovementsUrl+coeId+"/improvements",JSONObject.class,ImmutableMap.of("content-type", "application/json", "Accept", "text/plain, text/html,application/json", "Authorization", "Bearer 4feddd87883b416c6c2d79b9dbdbe47b5284dc57"));
                    JSONArray coeImproArr=coeImprovementsResp.getJSONArray("improvements");
                    int doneCount=0;
                    int todoCount=0;
                    String taskLink="";
                    if (coeImproArr.size()!=0){
                        coeP0.setAllTodo(coeImproArr.size());
                        for (Object o1 : coeImproArr) {
                            String status=((JSONObject)o1).getString("status");
                            if (status.equals("DONE")){
                                doneCount++;
                            }else {
                                todoCount++;
                                if (todoCount!=1){
                                    taskLink=taskLink+" ; "+((JSONObject)o1).getString("url");
                                }else {
                                    taskLink=taskLink+((JSONObject)o1).getString("url");
                                }
                            }
                        }
                    }
                    coeP0.setNotFinishTodo(todoCount);
                    coeP0.setFinishTodo(doneCount);
                    coeP0.setNotFinishTodoTask(taskLink);

                    JSONObject coeDetailResp = HttpUtils.doGet(coeDetailUrl + coeId, JSONObject.class, ImmutableMap.of("content-type", "application/json", "Accept", "text/plain, text/html,application/json", "Authorization", "Bearer 4feddd87883b416c6c2d79b9dbdbe47b5284dc57"));
                    JSONObject incidentDetail=coeDetailResp.getJSONObject("incident");
                    String orgPath = incidentDetail.getString("org_path");
                    if (orgPath.contains("平台技术部/"))
                    {
                        String subOrgPath = orgPath.substring(orgPath.indexOf("平台技术部/") + 6);
                        coeP0.setOrgName(subOrgPath);

                    }else {
                        String subOrgPath = orgPath.substring(orgPath.indexOf("集团/") + 3);
                        coeP0.setOrgName(subOrgPath);
                    }

                    if (orgPath.contains("到综研发组")){
                        coeP0.setAvailable(false);
                    }else {
                        coeP0.setAvailable(true);
                    }

                    if (coeIdList.contains(coeId)){
                        CoeListP0 coeListP0 = coeListP0Mapper.selectByCoeId(coeId);
                        coeP0.setId(coeListP0.getId());
                        coeListP0Mapper.updateByPrimaryKey(coeP0);
                    }else {
                        if (!coeP0.getOrgName().contains("住宿门票研发组")){
                            if (coeP0.getOrgName().contains("商家平台研发组")){
                                pushStr=pushStr+"\n\n△【" +"["+ coeP0.getBrief() +"|"+coeP0.getCoeLink()+"]"+ "】";
                                String minorOrgParh = orgPath.substring(orgPath.indexOf("商家平台研发组/") + 8);

                                pushStr = pushStr + "\n● 组织："+minorOrgParh+"   RD:"+coeP0.getOwnerName()+"("+coeP0.getOwnerMis()+")";
                                newCoe++;
                            }
                            coeListP0Mapper.insert(coeP0);
                        }
                    }
                }
            }
        }
        if (newCoe>0){
            DaXiangUtils.pushToPerson(pushStr,"guomengyao");
         //   DaXiangUtils.pushToRoom(pushStr,64057026090l);
        }
    }
}
