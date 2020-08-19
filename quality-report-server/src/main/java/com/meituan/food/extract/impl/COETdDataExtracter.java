package com.meituan.food.extract.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import com.meituan.food.extract.ICOETdDataExtract;
import com.meituan.food.mapper.*;
import com.meituan.food.po.CoeAtpPO;
import com.meituan.food.po.McdCoePO;
import com.meituan.food.po.McdCoeTodoPO;
import com.meituan.food.po.OrgMcdIdPO;
import com.meituan.food.utils.DaXiangUtils;
import com.meituan.food.utils.HttpUtils;
import com.sankuai.meituan.org.opensdk.service.OrgService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.security.Timestamp;

@Component
@Slf4j
public class COETdDataExtracter implements ICOETdDataExtract {

    private static final String url = "http://coe.sankuai.com/api/v1.0/query/incidents";

    private static final String coeUrl = "https://coe.mws.sankuai.com/detail/";

    private static final String coeDetailUrl = "https://coe.sankuai.com/api/v1.0/incidents/";

    private static final String coeTypeUrl = "https://coe.sankuai.com/api/v1.0/incidents/";

    private static final String coeImprovementsUrl = "https://coe.sankuai.com/api/v1.0/incidents/";

    private static final String customUrl = "https://coe.sankuai.com/api/v1.0/incidents/";

    private static final String coeHistoryUrl = "https://coe.sankuai.com/api/v1.0/incidents/";



    @Resource
    private McdCoePOMapper mcdCoePOMapper;

    @Resource
    private McdCoeTodoPOMapper mcdCoeTodoPOMapper;

    @Resource
    private OrgMcdIdPOMapper orgMcdIdPOMapper;

    @Resource
    private OrgDaxiangPOMapper orgDaxiangPOMapper;

    @Resource
    private CoeAtpPOMapper coeAtpPOMapper;

    @Override
    public void getCOETdData(String firstDayStr, String secondDayStr) throws ParseException {

        //配置coe入参
        String org = "44254";
        JSONObject inflowtParams = new JSONObject();
        inflowtParams.put("occur_start", firstDayStr);
        inflowtParams.put("occur_end", secondDayStr);
        inflowtParams.put("page", 1);
        inflowtParams.put("page_size", 100000);
        inflowtParams.put("sort", "desc");
        inflowtParams.put("sort_by", "create_at");
        inflowtParams.put("list_type", "all");
        inflowtParams.put("org", org);

        List<McdCoePO> mcdCoePOList = new ArrayList<>();


        //获取coe列表数据-到店数据
        JSONObject Resp = HttpUtils.doPost(url, inflowtParams.toJSONString(), JSONObject.class, ImmutableMap.of("content-type", "application/json", "Accept", "text/plain, text/html,application/json", "Authorization", "Bearer 4feddd87883b416c6c2d79b9dbdbe47b5284dc57"));
        JSONArray incidentsArray = Resp.getJSONArray("incidents");
        log.info("coe获取的数量：{}", incidentsArray.size());
        log.info("开始时间：{}",firstDayStr);
        log.info("结束时间：{}",secondDayStr);
        log.info("获取coe的数量：{}",incidentsArray.size());
        if (incidentsArray.size() != 0) {
            for (Object o : incidentsArray) {

                 McdCoePO coePO = new McdCoePO();
                 getBaseInfo(o, coePO);

                 if (!coePO.getOrgName().contains("餐饮解决方案")) {

                     getTodoList(coePO, coePO.getCoeId(),coePO.getOrgName());
                 }

                 List<Integer> coeIdList2 = mcdCoePOMapper.selectMcdCoeIdList();

                 if (coeIdList2.contains(coePO.getCoeId())) {
                     //coeid 存在时，为啥只有修改这几个字段
                     McdCoePO coeListPO = mcdCoePOMapper.selectByCoeId(coePO.getCoeId());
                     coePO.setId(coeListPO.getId());
                     coePO.setAvailable(coeListPO.getAvailable());
                     coePO.setBuildTime(coeListPO.getBuildTime());
                     coePO.setUpdateTime(new Date());
/*
                    //获取损失时， 为啥有时间上的对比
                   *//* if (coePO.getOccurDate().compareTo(inceptionDate) <= 0) {*//*
                    coeListPO.setCapitalLoss(coePO.getCapitalLoss());
                    coeListPO.setOrderLoss(coePO.getOrderLoss());
                  *//*  }*/

                     mcdCoePOMapper.updateByPrimaryKey(coePO);

                 } else {
                     if (!coePO.getOrgName().contains("餐饮解决方案")) {
                         coePO.setBuildTime(new Date());
                         coePO.setUpdateTime(new Date());
                         mcdCoePOMapper.insert(coePO);
                         mcdCoePOList.add(coePO);
                     }
                 }

            }


        }

        if (mcdCoePOList.size() != 0) {
            for (McdCoePO mcdCoePO : mcdCoePOList) {
                String orgName = mcdCoePO.getOrgName();
                Integer orgId = orgMcdIdPOMapper.selectOrgIdByOrgName("美团/到店事业群/平台技术部/" + orgName);
                if (Objects.isNull(orgId)) {
                    log.warn("orgName: {}, can't find orgId，will ignore!", orgName, orgId);
                    continue;
                }
                List<Long> daxiangIds = orgDaxiangPOMapper.selectByOrgId(orgId);
                if (CollectionUtils.isEmpty(daxiangIds)) {
                    log.warn("orgName: {},orgId: {} daxiang push list is empty!", orgName, orgId);
                }
                String pushStr = "❗️❗️❗️您关注的组织架构下新增COE，请及时跟进";
                pushStr = pushStr + "\n\n△【" + "[" + mcdCoePO.getBrief() + "|" + mcdCoePO.getCoeLink() + "]" + "】";
                pushStr = pushStr + "\n● 组织：" + mcdCoePO.getOrgName() + "\n● 责任人:" + mcdCoePO.getOwnerName() + "(" + mcdCoePO.getOwnerMis() + ")";
                for (Long daxiangId : daxiangIds) {
                    DaXiangUtils.pushToPerson( pushStr, "guomengyao","yuan.ding");
                    DaXiangUtils.pushToRoom( pushStr, daxiangId);

                }

            }
        }

        List<McdCoePO> lossCoePOList = mcdCoePOMapper.selectLossCoe();
        List<Integer> allCoeList = coeAtpPOMapper.selectAllCoeList();

        if (lossCoePOList != null) {
            for (McdCoePO po : lossCoePOList) {
                if (!allCoeList.contains(po.getCoeId())) {
                    String business = po.getLine();
                    if (business != null && !business.equals("")) {
                        String pushText = "";
                        CoeAtpPO atpPO=new CoeAtpPO();
                        atpPO.setCoeId(po.getCoeId());
                        atpPO.setIsPush(false);
                        Date now =new Date();
                        atpPO.setFirstPushDate(now);
                        if (po.getCouponLoss() != null && !po.getCouponLoss().equals("")) {
                            pushText = pushText + "\n●损失支付间夜/门票/消费券" + po.getCouponLoss() + "张";
                        } else if (po.getOrderLoss() != null && po.getOrderLoss().compareTo(BigDecimal.ZERO) != 0) {
                            log.info("这条有订单损失的COE是: {}",po.getCoeLink());
                            pushText = pushText + "\n●订单损失" + po.getOrderLoss().setScale(0) + "单";
                        } else if (po.getCapitalLoss() != null && po.getCapitalLoss().compareTo(BigDecimal.ZERO) != 0) {
                            pushText = pushText + "\n●资金损失" + po.getCapitalLoss().setScale(2) + "元";
                        }
                        pushText=pushText+"\n[如已录入请点击此处|http://10.41.94.92:8080/atp/update?coeId="+po.getCoeId()+"]";
                        if (business.equals("住宿") ) {
                            pushText = business + "业务下新增有损失的COE，请及时录入ATP\nATP地址：http://jiudian.sankuai.com/atp/atp.jsp#/\n【[" + po.getBrief() + "|" + po.getCoeLink() + "]】" + pushText;
                            DaXiangUtils.pushToPerson(pushText,"guomengyao","yuan.ding");
                            DaXiangUtils.pushToPerson(pushText,"chenchaoyi");
                            atpPO.setReceiver("chenchaoyi");
                            atpPO.setPushText(pushText);
                            coeAtpPOMapper.insert(atpPO);
                        } else if (business.equals("门票")) {
                            pushText = business + "业务下新增有损失的COE，请及时录入ATP\nATP地址：http://jiudian.sankuai.com/atp/dual.jsp#/apt_trip\n【[" + po.getBrief() + "|" + po.getCoeLink() + "]】" + pushText;
                            DaXiangUtils.pushToPerson(pushText,"guomengyao","yuan.ding");
                            DaXiangUtils.pushToPerson(pushText,"chenchaoyi");
                            atpPO.setReceiver("chenchaoyi");
                            atpPO.setPushText(pushText);
                            coeAtpPOMapper.insert(atpPO);
                        } else if (business.equals("到餐") || business.equals("收单")) {
                            pushText = business + "业务下新增有损失的COE，请及时录入ATP\n【[" + po.getBrief() + "|" + po.getCoeLink() + "]】" + pushText;
                            DaXiangUtils.pushToPerson(pushText,"guomengyao");
                            DaXiangUtils.pushToPerson(pushText,"wangjianming02");
                            atpPO.setReceiver("wangjianming02");
                            atpPO.setPushText(pushText);
                            coeAtpPOMapper.insert(atpPO);
                        } else if (business.equals("到综")) {
                            pushText = business + "业务下新增有损失的COE，请及时录入ATP\n地址：https://km.sankuai.com/page/259031052\n【[" + po.getBrief() + "|" + po.getCoeLink() + "]】" + pushText;
                            DaXiangUtils.pushToPerson(pushText,"guomengyao","yuan.ding");
                            DaXiangUtils.pushToPerson(pushText,"yuan.ding");
                            atpPO.setReceiver("yuan.ding");
                            atpPO.setPushText(pushText);
                            coeAtpPOMapper.insert(atpPO);
                        }
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
        coePO.setOccurTime(occurTime);
        coePO.setOccurDate(occurTime);
        coePO.setCoeId(((JSONObject) o).getInteger("_id"));
        coePO.setLevel(((JSONObject) o).getString("level"));
        coePO.setRootCause(((JSONObject) o).getString("appkey"));
        coePO.setCustomLevel(((JSONObject) o).getString("bglevel"));
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

         JSONObject coeDetailResp = HttpUtils.doGet(coeDetailUrl + coeId, JSONObject.class, ImmutableMap.of("content-type", "application/json", "Accept", "text/plain, text/html,application/json", "Authorization", "Bearer 4feddd87883b416c6c2d79b9dbdbe47b5284dc57"));
                JSONObject incidentDetail = coeDetailResp.getJSONObject("incident");
                String orgPath = incidentDetail.getString("org_path");

      //  getTodoList(coePO, coeId);

        //coe的创建时间
        JSONObject coeHistoryResp = HttpUtils.doGet(coeHistoryUrl + coeId + "/history", JSONObject.class, ImmutableMap.of("content-type", "application/json", "Accept", "text/plain, text/html,application/json", "Authorization", "Bearer 4feddd87883b416c6c2d79b9dbdbe47b5284dc57"));
        JSONArray history=coeHistoryResp.getJSONArray("history");
        List<Object> historyList = new ArrayList<Object>();
        historyList=JSONObject.parseArray(history.toJSONString(),Object.class);
        log.info("创建时间对应的coeid：{}",coeId);
        log.info("historyList 的长度：{}",historyList.size());

        //判断历史记录接口中与否有记录存在
        if(historyList.size()>0){
            Date time= ( (JSONObject)historyList.get(historyList.size()-1)).getDate("time");
            coePO.setCreateTime(time);
        }
        else{
            //历史记录没有数据时，取coe详情中的create_at时间
            coePO.setCreateTime(((JSONObject) o).getDate("create_at"));
        }



        getShortOrgName(orgPath, coePO);
        log.info("coeId:{}", coePO.getCoeId());
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
    public void getTodoList(McdCoePO coePO, int coeId,String orgName) {
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
                todoPo.setOrgName(orgName);
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
                        switch (label) {
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
                            case "前期测试未发现原因":
                                po.setNofundReason(value);
                                break;
                            case "是否影响数仓":
                                po.setAffectData(value);
                        }

                        if (value != null && !"".equals(value)) {
                            if (label.equals("到餐订单损失量") || label.equals("订单损失量")) {

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
