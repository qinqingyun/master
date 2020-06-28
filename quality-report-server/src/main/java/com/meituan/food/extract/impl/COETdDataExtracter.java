package com.meituan.food.extract.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import com.meituan.food.extract.ICOETdDataExtract;
import com.meituan.food.mapper.*;
import com.meituan.food.po.McdCoePO;
import com.meituan.food.po.McdCoeTodoPO;
import com.meituan.food.utils.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class COETdDataExtracter implements ICOETdDataExtract {

    private static final String url = "http://coe.sankuai.com/api/v1.0/query/incidents";

    private static final String coeUrl = "https://coe.mws.sankuai.com/detail/";

    private static final String coeDetailUrl = "https://coe.sankuai.com/api/v1.0/incidents/";

    private static final String coeTypeUrl = "https://coe.sankuai.com/api/v1.0/incidents/";

    private static final String coeImprovementsUrl = "https://coe.sankuai.com/api/v1.0/incidents/";

    private static final String customUrl = "https://coe.sankuai.com/api/v1.0/incidents/";


    @Resource
    private McdCoePOMapper mcdCoePOMapper;

    @Resource
    private McdCoeTodoPOMapper mcdCoeTodoPOMapper;

    @Override
    public void getCOETdData(String firstDayStr, String secondDayStr) throws ParseException {

        //配置coe入参
        String  org="44254";
        JSONObject inflowtParams = new JSONObject();
        inflowtParams.put("occur_start", firstDayStr);
        inflowtParams.put("occur_end", secondDayStr);
        inflowtParams.put("page", 1);
        inflowtParams.put("page_size", 100000);
        inflowtParams.put("sort", "desc");
        inflowtParams.put("sort_by", "create_at");
        inflowtParams.put("list_type", "all");
        inflowtParams.put("org", org);


        //获取coe列表数据-到店数据
        JSONObject Resp = HttpUtils.doPost(url, inflowtParams.toJSONString(), JSONObject.class, ImmutableMap.of("content-type", "application/json", "Accept", "text/plain, text/html,application/json", "Authorization", "Bearer 4feddd87883b416c6c2d79b9dbdbe47b5284dc57"));
        JSONArray incidentsArray = Resp.getJSONArray("incidents");
        log.info("开始时间：{}",firstDayStr);
        log.info("结束时间：{}",secondDayStr);
        log.info("获取coe的数量：{}",incidentsArray.size());
        if (incidentsArray.size() != 0) {
            for (Object o : incidentsArray) {

                    McdCoePO coePO = new McdCoePO();
                    getBaseInfo(o, coePO);

                    if (!coePO.getOrgName().contains("餐饮解决方案中心")){
                    getTodoList(coePO, coePO.getCoeId());
                    }

                    List<Integer> coeIdList2 = mcdCoePOMapper.selectMcdCoeIdList();

                    if (coeIdList2.contains(coePO.getCoeId())) {
                        //coeid 存在时，为啥只有修改这几个字段
                        McdCoePO coeListPO = mcdCoePOMapper.selectByCoeId(coePO.getCoeId());
                        coePO.setId(coeListPO.getId());
                        coePO.setAvailable(coeListPO.getAvailable());
/*
                    //获取损失时， 为啥有时间上的对比
                   *//* if (coePO.getOccurDate().compareTo(inceptionDate) <= 0) {*//*
                    coeListPO.setCapitalLoss(coePO.getCapitalLoss());
                    coeListPO.setOrderLoss(coePO.getOrderLoss());
                  *//*  }*/

                        mcdCoePOMapper.updateByPrimaryKey(coePO);

                    }
                    else {
                        if (!coePO.getOrgName().contains("餐饮解决方案中心")){
                            mcdCoePOMapper.insert(coePO);
                        }
                    }
                }



            }


       /* //获取第三方coe数据的入参
        List<String> inflowt = new ArrayList<>();
        //到餐
        inflowt.add("meituan.web");
        inflowt.add("meituan.resv");
        inflowt.add("dianping.dc");
        inflowt.add("meituan.meishi");
        //住宿度假
        inflowt.add("meituan.trip");
        inflowt.add("meituan.hotel");
        inflowt.add("dianping.hotelsh");
        inflowt.add("dianping.tripsh");
        inflowt.add("meituan.nibhtp");
        //到综
        inflowt.add("meituan.gct");
        inflowt.add("meituan.oversea");
        inflowt.add("dianping.dzs");
        inflowt.add("dianping.dzu");
        inflowtParams.put("inflowt", inflowt);
        inflowtParams.put("org",null);


        //获取coe列表数据-影响到店的数据
        JSONObject inflowtResp = HttpUtils.doPost(url, inflowtParams.toJSONString(), JSONObject.class, ImmutableMap.of("content-type", "application/json", "Accept", "text/plain, text/html,application/json", "Authorization", "Bearer 4feddd87883b416c6c2d79b9dbdbe47b5284dc57"));
        JSONArray inflowtIncidentsArray = inflowtResp.getJSONArray("incidents");
        if (inflowtIncidentsArray.size() != 0) {
            for (Object o : inflowtIncidentsArray) {
                McdCoePO coePO = new McdCoePO();
                getBaseInfo(o, coePO);
                getTodoList(coePO, coePO.getCoeId());


                List<Integer> coeIdList2 = mcdCoePOMapper.selectMcdCoeIdList();

                if (coeIdList2.contains(coePO.getCoeId())) {
                    //coeid 存在时，为啥只有修改这几个字段
                    McdCoePO coeListPO = mcdCoePOMapper.selectByCoeId(coePO.getCoeId());
                    coePO.setId(coeListPO.getId());
                    coePO.setAvailable(coeListPO.getAvailable());

                    //获取损失时， 为啥有时间上的对比
                    *//* if (coePO.getOccurDate().compareTo(inceptionDate) <= 0) {*//*
                    coePO.setCapitalLoss(coeListPO.getCapitalLoss());
                    coePO.setOrderLoss(coeListPO.getOrderLoss());
                    *//*  }*//*

                    mcdCoePOMapper.updateByPrimaryKey(coePO);

                } else {
                    mcdCoePOMapper.insert(coePO);
                }

            }


        }

*/

    }

    //获取每一个case的信息
    public void getBaseInfo(Object o, McdCoePO coePO) throws ParseException {
        coePO.setBrief(((JSONObject) o).getString("brief"));
        Date occurTime = ((JSONObject) o).getDate("occur_time");
        coePO.setOccurDate(occurTime);
        coePO.setCoeId(((JSONObject) o).getInteger("_id"));
        coePO.setLevel(((JSONObject) o).getString("level"));
        String ownerStr = ((JSONObject) o).getString("owner");
        Date findTime = ((JSONObject) o).getDate("find_time");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (findTime != null) {
            String format = sdf.format(findTime);
            coePO.setFindTime(findTime);
            Date findDate = sdf.parse(format);
            coePO.setFindDate(findDate);
        }
        coePO.setFminusoTime(((JSONObject) o).getInteger("fminuso_time"));
        coePO.setHandleTime(((JSONObject) o).getDate("handle_time"));
        coePO.setLminusfTime(((JSONObject) o).getInteger("lminusf_time"));
        coePO.setLocationTime(((JSONObject) o).getDate("location_time"));
        coePO.setNotifyTime(((JSONObject) o).getDate("notify_time"));
        coePO.setSminushTime(((JSONObject) o).getInteger("sminush_time"));
        coePO.setSolvedTime(((JSONObject) o).getDate("solved_time"));
        coePO.setWiki(((JSONObject) o).getString("wiki"));
        coePO.setCoeLink(coeUrl + ((JSONObject) o).getInteger("_id"));
        coePO.setCategory(((JSONObject) o).getString("category"));
        coePO.setAppearance(((JSONObject) o).getString("appearance"));
        Date clearTime = ((JSONObject) o).getDate("clear_time");
        coePO.setClearTime(clearTime);
        if (clearTime != null && findTime != null) {
            long incluence = (clearTime.getTime() - findTime.getTime()) / 1000 / 60;
            if (((JSONObject) o).getInteger("fminuso_time") != null) {
                int incluenceTime = new Long(incluence).intValue() + ((JSONObject) o).getInteger("fminuso_time");
                coePO.setInfluenceTime(incluenceTime);
            }
        }
        JSONArray finderArray = ((JSONObject) o).getJSONArray("finders");
        if (finderArray.size() != 0) {
            String finder = finderArray.get(0).toString();
            coePO.setFinder(finder);

        }

        JSONArray locatorArray = ((JSONObject) o).getJSONArray("locators");
        if (locatorArray.size() != 0) {
            String locator = locatorArray.get(0).toString();
            coePO.setLocator(locator);
        }
        coePO.setAvailable(true);
        getOwnerMis(ownerStr, coePO);
        int coeId = ((JSONObject) o).getInteger("_id");

        JSONObject coeTypeResp = HttpUtils.doGet(coeTypeUrl + coeId + "/types", JSONObject.class, ImmutableMap.of("content-type", "application/json", "Accept", "text/plain, text/html,application/json", "Authorization", "Bearer 4feddd87883b416c6c2d79b9dbdbe47b5284dc57"));
        JSONArray coeTypeArray = coeTypeResp.getJSONArray("types");
        if (coeTypeArray.size() != 0) {
            JSONObject reason = (JSONObject) coeTypeArray.get(0);
            coePO.setSubCategory(reason.getString("parent"));
        }

        getTodoList(coePO, coeId);

        JSONObject coeDetailResp = HttpUtils.doGet(coeDetailUrl + coeId, JSONObject.class, ImmutableMap.of("content-type", "application/json", "Accept", "text/plain, text/html,application/json", "Authorization", "Bearer 4feddd87883b416c6c2d79b9dbdbe47b5284dc57"));
        JSONObject incidentDetail = coeDetailResp.getJSONObject("incident");
        String orgPath = incidentDetail.getString("org_path");

        getShortOrgName(orgPath, coePO);
        log.info("coeId:{}",coePO.getCoeId());
        getOther(coeId, coePO);
    }

    //获取owner的mis以及name
    public void getOwnerMis(String ownerStr, McdCoePO coePO) {
        if (ownerStr != null) {
            if (ownerStr.contains("/")) {
                String ownerMis = ownerStr.substring(0, ownerStr.indexOf("/"));
                String ownerName = ownerStr.substring(ownerStr.indexOf("/") + 1);
                coePO.setOwnerMis(ownerMis);
                coePO.setOwnerName(ownerName);
            } else {
                coePO.setOwnerMis(ownerStr);
            }
        }
    }

    //获取每一个case中的todo项
    public void getTodoList(McdCoePO coePO, int coeId) {
        JSONObject coeImprovementsResp = HttpUtils.doGet(coeImprovementsUrl + coeId + "/improvements", JSONObject.class, ImmutableMap.of("content-type", "application/json", "Accept", "text/plain, text/html,application/json", "Authorization", "Bearer 4feddd87883b416c6c2d79b9dbdbe47b5284dc57"));
        JSONArray coeImproArr = coeImprovementsResp.getJSONArray("improvements");
        int doneCount = 0;
        int todoCount = 0;
        String taskLink = "";
        if (coeImproArr.size() != 0) {
            coePO.setAllTodo(coeImproArr.size());
            for (Object o1 : coeImproArr) {
                McdCoeTodoPO todoPo = new McdCoeTodoPO();
                todoPo.setCoeId(coeId);
                int onesId = ((JSONObject) o1).getInteger("_id");
                todoPo.setOnesId(onesId);
                todoPo.setOnesLink(((JSONObject) o1).getString("url"));
                todoPo.setOnesTitle(((JSONObject) o1).getString("brief"));
                todoPo.setOwnerMis(((JSONObject) o1).getString("handler"));
                todoPo.setOwnerName(((JSONObject) o1).getString("handler_name"));
                todoPo.setDealline(((JSONObject) o1).getDate("estimate_time"));
                todoPo.setStartDate(((JSONObject) o1).getDate("start_time"));
                todoPo.setActualDate(((JSONObject) o1).getDate("actual_time"));
                String status = ((JSONObject) o1).getString("status");
                if (status.equals("DONE")) {
                    doneCount++;
                    todoPo.setIsDelay(false);
                    todoPo.setIsFinish(true);
                } else {
                    todoCount++;
                    if (todoCount != 1) {
                        taskLink = taskLink + " ; " + ((JSONObject) o1).getString("url");
                    } else {
                        taskLink = taskLink + ((JSONObject) o1).getString("url");
                    }
                    if (status.equals("OVERDUE")) {
                        todoPo.setIsDelay(true);
                        todoPo.setIsFinish(false);
                    } else {
                        todoPo.setIsDelay(false);
                        todoPo.setIsFinish(false);
                    }
                }

                List<Integer> onesIdList = mcdCoeTodoPOMapper.selectOnesIdList();
                if (!onesIdList.contains(onesId)) {
                    mcdCoeTodoPOMapper.insert(todoPo);
                } else {
                    McdCoeTodoPO existToDoPo = mcdCoeTodoPOMapper.selectByOnesId(onesId);
                    todoPo.setId(existToDoPo.getId());
                    mcdCoeTodoPOMapper.updateByPrimaryKey(todoPo);
                }
            }
        }
        coePO.setNotFinishTodo(todoCount);
        coePO.setFinishTodo(doneCount);
        coePO.setNotFinishTodoTask(taskLink);
    }

    //获取case对应的组织架构节点(平台技术部子节点)
    public void getShortOrgName(String orgPath, McdCoePO coePO) {
        if (orgPath.contains("平台技术部/")) {
            String subOrgPath = orgPath.substring(orgPath.indexOf("平台技术部/") + 6);
            coePO.setOrgName(subOrgPath);

        } else {
            String subOrgPath = orgPath.substring(orgPath.indexOf("集团/") + 3);
            coePO.setOrgName(subOrgPath);
        }

    }

    //获取case涉及到的损失信息
    public void getOther(int coeId, McdCoePO po) {
        String cUrl = customUrl + coeId + "/custom";
        log.info("损失信息的URL为：" + cUrl);
        JSONObject resp = HttpUtils.doGet(cUrl, JSONObject.class, ImmutableMap.of("content-type", "application/json", "Accept", "text/plain, text/html,application/json", "Authorization", "Bearer 4feddd87883b416c6c2d79b9dbdbe47b5284dc57"));
        JSONObject custom = resp.getJSONObject("custom");
        if (custom != null) {
            JSONArray instances = custom.getJSONArray("instances");
            if (instances.size() != 0) {
                for (Object instance : instances) {
                    if (instance != null) {
                        String label = ((JSONObject) instance).getJSONObject("custom_template").getString("label");
                        String value = ((JSONObject) instance).getString("value");
                        switch (label){
                            case "损失支付间夜/门票/消费券":
                                po.setCouponLoss(value);
                                break;
                            case "线上问题发现方式":
                                po.setOnlineDiscovery(value);
                                break;
                            case "线上问题原因分类":
                                po.setOnlineClassification(value);
                                break;
                            case "业务线":
                                po.setLine(value);
                                break;
                            case "内部定级":
                                po.setCustomLevel(value);
                                break;
                            case "前期测试未发现原因":
                                po.setNofundReason(value);
                        }

                        if (value != null&& !"".equals(value)) {
                           if (label.equals("到餐订单损失量")||label.equals("订单损失量")) {

                                po.setOrderLoss(new BigDecimal(value));

                            } else if (label.equals("资金损失（元）")) {

                                po.setCapitalLoss(new BigDecimal(value));
                            }
                        }
                    }
                }
            }
        }
    }
}
