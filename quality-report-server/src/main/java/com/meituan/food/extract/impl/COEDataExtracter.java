package com.meituan.food.extract.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import com.meituan.food.extract.ICOEDataExtract;
import com.meituan.food.mapper.CoeListPOMapper;
import com.meituan.food.mapper.ToDoPoMapper;
import com.meituan.food.po.CoeListPO;
import com.meituan.food.po.ToDoPo;
import com.meituan.food.utils.HttpUtils;
import com.meituan.food.utils.SsoUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class COEDataExtracter implements ICOEDataExtract {

    private static final String url = "http://coe.sankuai.com/api/v1.0/query/incidents";

    private static final String coeUrl = "https://coe.mws.sankuai.com/detail/";

    private static final String availabilityUrl = "https://coe.sankuai.com/api/v1.0/trend/availability?end=2020-12-31&start=";

    private static final String coeDetailUrl = "https://coe.sankuai.com/api/v1.0/incidents/";

    private static final String coeTypeUrl="https://coe.sankuai.com/api/v1.0/incidents/";

    private static final String coeImprovementsUrl="https://coe.sankuai.com/api/v1.0/incidents/";

    private static final String customUrl="https://coe.sankuai.com/api/v1.0/incidents/";

    @Resource
    private CoeListPOMapper coeListPOMapper;

    @Resource
    private ToDoPoMapper toDoPoMapper;

    String ssoid=SsoUtils.getSsoId();

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
        orgList.add(13181);//到餐测试组

        String pushStr="商家平台新增COE：";
        int newCoe=0;

        JSONObject availabilityResp = HttpUtils.doGet(availabilityUrl+firstDateStr, JSONObject.class, ImmutableMap.of("content-type", "application/json", "Accept", "text/plain, text/html,application/json", "Authorization", "Bearer 4feddd87883b416c6c2d79b9dbdbe47b5284dc57"));
        JSONArray bgAvaiDataArray = availabilityResp.getJSONArray("availabilities");
        List<Integer> coeIdList2 = coeListPOMapper.selectCoeIdList();
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
                            CoeListPO coePO = new CoeListPO();
                            coePO.setCoeId(coeId);
                            coePO.setCoeLink(coeUrl + coeId);
                            coePO.setBrief(incidentDetail.getString("brief"));
                            Date occurTime = (incidentDetail.getDate("occur_time"));
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            String format = sdf.format(occurTime);
                            try {
                                Date occurDate = sdf.parse(format);
                                coePO.setOccurDate(occurDate);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            coePO.setNotifyTime(incidentDetail.getDate("notify_time"));
                            Date findTime = (incidentDetail).getDate("find_time");
                            coePO.setFindTime(findTime);
                            if (findTime!=null){
                                Date findDate = sdf.parse(format);
                                coePO.setFindDate(findDate);
                            }
                            coePO.setLocationTime(incidentDetail.getDate("location_time"));
                            coePO.setHandleTime(incidentDetail.getDate("handle_time"));
                            coePO.setSolvedTime(incidentDetail.getDate("solved_time"));
                            coePO.setFminusoTime(incidentDetail.getInteger("fminuso_time"));
                            coePO.setLminusfTime(incidentDetail.getInteger("lminusf_time"));
                            coePO.setSminushTime(incidentDetail.getInteger("sminush_time"));
                            coePO.setWiki(incidentDetail.getString("wiki"));
                            coePO.setLevel(incidentDetail.getString("level"));
                            String ownerStr=(incidentDetail.getString("owner"));
                            Date clearTime = (incidentDetail).getDate("clear_time");
                            coePO.setClearTime(clearTime);
                            if (clearTime!=null&&findTime!=null){
                                long incluence=(clearTime.getTime()-occurTime.getTime())/1000/60;
                                if (incidentDetail.getInteger("fminuso_time")!=null){
                                    int incluenceTime=new Long(incluence).intValue()+incidentDetail.getInteger("fminuso_time");
                                    coePO.setInfluenceTime(incluenceTime);
                                }
                            }
                            JSONArray finderArray=(incidentDetail).getJSONArray("finders");
                            if (finderArray.size()!=0){
                                String finder = finderArray.get(0).toString();
                                coePO.setFinder(finder);

                            }
                            JSONArray locatorArray=(incidentDetail).getJSONArray("locators");
                            if (locatorArray.size()!=0){
                                String locator=locatorArray.get(0).toString();
                                coePO.setLocator(locator);
                            }
                            coePO.setAvailable(true);
                            coePO.setAppearance(incidentDetail.getString("appearance"));
                            getOwnerMis(ownerStr,coePO);
                            String orgPath = incidentDetail.getString("org_path");
                            getShortOrgName(orgPath,coePO);
                            getOther(coeId,coePO);
                            if (orgPath.contains("到店餐饮研发中心")||orgPath.contains("平台业务研发中心/商家平台研发组/增值平台研发组")||orgPath.contains("平台业务研发中心/商家平台研发组/客户平台研发组")||orgPath.contains("平台终端研发组/到店餐饮研发组")||orgPath.contains("到餐研发组")||orgPath.contains("到店餐饮测试组")){
                                coePO.setCategory(incidentDetail.getString("category"));

                                JSONObject coeTypeResp=HttpUtils.doGet(coeTypeUrl+coeId+"/types",JSONObject.class,ImmutableMap.of("content-type", "application/json", "Accept", "text/plain, text/html,application/json", "Authorization", "Bearer 4feddd87883b416c6c2d79b9dbdbe47b5284dc57"));
                                JSONArray coeTypeArray=coeTypeResp.getJSONArray("types");
                                if (coeTypeArray.size()!=0){
                                    JSONObject reason = (JSONObject) coeTypeArray.get(0);
                                    coePO.setSubCategory(reason.getString("parent"));
                                }
                            }else {
                                coePO.setCategory("第三方");
                                coePO.setSubCategory("第三方");
                            }

                            /*
                             * todoList方法
                             * */
                            getTodoList(coePO,coeId);
                            System.out.println(coePO.toString());

                            if (coeIdList2.contains(coeId)){
                                CoeListPO coeListPO = coeListPOMapper.selectByCoeId(coeId);
                                coePO.setId(coeListPO.getId());
                                coePO.setAvailable(coeListPO.getAvailable());
                                coeListPOMapper.updateByPrimaryKey(coePO);
                            }else {
                                coeListPOMapper.insert(coePO);
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
            List<Integer> coeIdList=coeListPOMapper.selectCoeIdList();

            JSONObject resp=HttpUtils.doPost(url,params.toJSONString(),JSONObject.class,ImmutableMap.of("content-type", "application/json","Accept","text/plain, text/html,application/json","Authorization", "Bearer 4feddd87883b416c6c2d79b9dbdbe47b5284dc57"));
            JSONArray incidentsArray=resp.getJSONArray("incidents");
            if(incidentsArray.size()!=0){
                for (Object o : incidentsArray) {
                    CoeListPO coePO=new CoeListPO();
                    coePO.setBrief(((JSONObject)o).getString("brief"));
                    Date occurTime = ((JSONObject) o).getDate("occur_time");
                    coePO.setOccurDate(occurTime);
                    coePO.setCoeId(((JSONObject)o).getInteger("_id"));
                    coePO.setLevel(((JSONObject)o).getString("level"));
                    String ownerStr=((JSONObject)o).getString("owner");
                    Date findTime = ((JSONObject) o).getDate("find_time");
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    if (findTime!=null){
                        String format = sdf.format(findTime);
                        coePO.setFindTime(findTime);
                        Date findDate = sdf.parse(format);
                        coePO.setFindDate(findDate);
                    }
                    coePO.setFminusoTime(((JSONObject)o).getInteger("fminuso_time"));
                    coePO.setHandleTime(((JSONObject)o).getDate("handle_time"));
                    coePO.setLminusfTime(((JSONObject)o).getInteger("lminusf_time"));
                    coePO.setLocationTime(((JSONObject)o).getDate("location_time"));
                    coePO.setNotifyTime(((JSONObject)o).getDate("notify_time"));
                    coePO.setSminushTime(((JSONObject)o).getInteger("sminush_time"));
                    coePO.setSolvedTime(((JSONObject)o).getDate("solved_time"));
                    coePO.setWiki(((JSONObject)o).getString("wiki"));
                    coePO.setCoeLink(coeUrl+((JSONObject)o).getInteger("_id"));
                    coePO.setCategory(((JSONObject)o).getString("category"));
                    coePO.setAppearance(((JSONObject)o).getString("appearance"));
                    Date clearTime = ((JSONObject) o).getDate("clear_time");
                    coePO.setClearTime(clearTime);
                    if (clearTime!=null&&findTime!=null){
                        long incluence=(clearTime.getTime()-findTime.getTime())/1000/60;
                        if (((JSONObject)o).getInteger("fminuso_time")!=null){
                            int incluenceTime=new Long(incluence).intValue()+((JSONObject)o).getInteger("fminuso_time");
                            coePO.setInfluenceTime(incluenceTime);
                        }
                    }
                    JSONArray finderArray=((JSONObject) o).getJSONArray("finders");
                    if (finderArray.size()!=0){
                        String finder = finderArray.get(0).toString();
                        coePO.setFinder(finder);

                    }

                    JSONArray locatorArray=((JSONObject) o).getJSONArray("locators");
                    if (locatorArray.size()!=0){
                        String locator=locatorArray.get(0).toString();
                        coePO.setLocator(locator);
                    }
                    coePO.setAvailable(true);
                    getOwnerMis(ownerStr,coePO);
                    int coeId=((JSONObject)o).getInteger("_id");

                    JSONObject coeTypeResp=HttpUtils.doGet(coeTypeUrl+coeId+"/types",JSONObject.class,ImmutableMap.of("content-type", "application/json", "Accept", "text/plain, text/html,application/json", "Authorization", "Bearer 4feddd87883b416c6c2d79b9dbdbe47b5284dc57"));
                    JSONArray coeTypeArray=coeTypeResp.getJSONArray("types");
                    if (coeTypeArray.size()!=0){
                        JSONObject reason = (JSONObject) coeTypeArray.get(0);
                        coePO.setSubCategory(reason.getString("parent"));
                    }

                    getTodoList(coePO,coeId);

                    JSONObject coeDetailResp = HttpUtils.doGet(coeDetailUrl + coeId, JSONObject.class, ImmutableMap.of("content-type", "application/json", "Accept", "text/plain, text/html,application/json", "Authorization", "Bearer 4feddd87883b416c6c2d79b9dbdbe47b5284dc57"));
                    JSONObject incidentDetail=coeDetailResp.getJSONObject("incident");
                    String orgPath = incidentDetail.getString("org_path");

                    getShortOrgName(orgPath,coePO);
                    getOther(coeId,coePO);

                    if (orgPath.contains("到综研发组")){
                        coePO.setAvailable(false);
                    }else {
                        coePO.setAvailable(true);
                    }

                    if (coeIdList.contains(coeId)){
                        CoeListPO coeListPO = coeListPOMapper.selectByCoeId(coeId);
                        coePO.setId(coeListPO.getId());
                        coeListPOMapper.updateByPrimaryKey(coePO);
                    }else {
                        if (!coePO.getOrgName().contains("住宿门票研发组")){
                            if (coePO.getOrgName().contains("商家平台研发组")||coePO.getOrgName().contains("商家")){
                                pushStr=pushStr+"\n\n△【" +"["+ coePO.getBrief() +"|"+coePO.getCoeLink()+"]"+ "】";
                                String minorOrgParh;
                                if (orgPath.contains("商家平台研发组")){
                                    minorOrgParh= orgPath.substring(orgPath.indexOf("商家平台研发组/") + 8);
                                } else{
                                    minorOrgParh = orgPath.substring(orgPath.indexOf("到店餐饮研发组/") + 8);
                                }

                                pushStr = pushStr + "\n● 组织："+minorOrgParh+"   RD:"+coePO.getOwnerName()+"("+coePO.getOwnerMis()+")";
                                newCoe++;
                            }
                            coeListPOMapper.insert(coePO);
                        }
                    }
                }
            }
        }
        if (newCoe>0){
            DaXiangUtils.pushToPerson(pushStr,"guomengyao");
            DaXiangUtils.pushToRoom(pushStr,64057026090l);
        }
    }

    public void getTodoList(CoeListPO coePO,int coeId){
        JSONObject coeImprovementsResp=HttpUtils.doGet(coeImprovementsUrl+coeId+"/improvements",JSONObject.class,ImmutableMap.of("content-type", "application/json", "Accept", "text/plain, text/html,application/json", "Authorization", "Bearer 4feddd87883b416c6c2d79b9dbdbe47b5284dc57"));
        JSONArray coeImproArr=coeImprovementsResp.getJSONArray("improvements");
        int doneCount=0;
        int todoCount=0;
        String taskLink="";
        if (coeImproArr.size()!=0){
            coePO.setAllTodo(coeImproArr.size());
            for (Object o1 : coeImproArr) {
                ToDoPo todoPo=new ToDoPo();
                todoPo.setCoeId(coeId);
                int onesId = ((JSONObject) o1).getInteger("_id");
                todoPo.setOnesId(onesId);
                todoPo.setOnesLink(((JSONObject)o1).getString("url"));
                todoPo.setOnesTitle(((JSONObject)o1).getString("brief"));
                todoPo.setOwnerMis(((JSONObject)o1).getString("handler"));
                todoPo.setOwnerName(((JSONObject)o1).getString("handler_name"));
                todoPo.setDealline(((JSONObject)o1).getDate("estimate_time"));
                todoPo.setStartDate(((JSONObject)o1).getDate("start_time"));
                todoPo.setActualDate(((JSONObject)o1).getDate("actual_time"));
                String status=((JSONObject)o1).getString("status");
                if (status.equals("DONE")){
                    doneCount++;
                    todoPo.setIsDelay(false);
                    todoPo.setIsFinish(true);
                }else {
                    todoCount++;
                    if (todoCount!=1){
                        taskLink=taskLink+" ; "+((JSONObject)o1).getString("url");
                    }else {
                        taskLink=taskLink+((JSONObject)o1).getString("url");
                    }
                    if (status.equals("OVERDUE")){
                        todoPo.setIsDelay(true);
                        todoPo.setIsFinish(false);
                    }else {
                        todoPo.setIsDelay(false);
                        todoPo.setIsFinish(false);
                    }
                }

                List<Integer> onesIdList = toDoPoMapper.selectOnesIdList();
                if (!onesIdList.contains(onesId)) {
                    toDoPoMapper.insert(todoPo);
                }else {
                    ToDoPo existToDoPo = toDoPoMapper.selectByOnesId(onesId);
                    todoPo.setId(existToDoPo.getId());
                    toDoPoMapper.updateByPrimaryKey(todoPo);
                }
            }
        }
        coePO.setNotFinishTodo(todoCount);
        coePO.setFinishTodo(doneCount);
        coePO.setNotFinishTodoTask(taskLink);
    }

    public void getShortOrgName(String orgPath,CoeListPO coePO){
        if (orgPath.contains("平台技术部/"))
        {
            String subOrgPath = orgPath.substring(orgPath.indexOf("平台技术部/") + 6);
            coePO.setOrgName(subOrgPath);
            subOrgPath = subOrgPath.substring(subOrgPath.indexOf("/")+1,subOrgPath.indexOf("/",subOrgPath.indexOf("/")+1));
            coePO.setNodeTwoOrg(subOrgPath);

        }else {
            String subOrgPath = orgPath.substring(orgPath.indexOf("集团/") + 3);
            coePO.setNodeTwoOrg(subOrgPath);
            coePO.setOrgName(subOrgPath);
        }
    }

    public void getOwnerMis(String ownerStr,CoeListPO coePO){
        if (ownerStr!=null){
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

    public void getOther(int coeId,CoeListPO po){
        String cUrl=customUrl+coeId+"/custom";
        log.info("损失信息的URL为："+cUrl);
        JSONObject resp=HttpUtils.doGet(cUrl,JSONObject.class,ImmutableMap.of("content-type", "application/json", "Accept", "text/plain, text/html,application/json", "Authorization", "Bearer 4feddd87883b416c6c2d79b9dbdbe47b5284dc57"));
        JSONObject custom = resp.getJSONObject("custom");
        if (custom!=null){
            JSONArray instances = custom.getJSONArray("instances");
            if (instances.size()!=0){
                for (Object instance : instances) {
                    if (instance!=null){
                        String label = ((JSONObject) instance).getJSONObject("custom_template").getString("label");
                        String value= ((JSONObject) instance).getString("value");
                        if (value!=null){
                            if (label.equals("业务线")){
                                po.setLineOfBusiness(value);
                            }else if (label.equals("到餐订单损失量")){
                                po.setOrderLoss(Integer.valueOf(value));
                            }else if (label.equals("资金损失（元）")){
                                po.setCapitalLoss(Integer.valueOf(value));
                            }
                        }
                    }
                }
            }
        }
    }
}
