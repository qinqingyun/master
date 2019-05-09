package com.meituan.food.extract.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import com.meituan.food.extract.IWeekBugDataExtract;
import com.meituan.food.mapper.WeekBugTotalCountPOMapper;
import com.meituan.food.po.WeekBugTotalCountPO;
import com.meituan.food.utils.HttpUtils;
import com.meituan.food.utils.SsoUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Component
public class WeekBugTotalCountExtracter implements IWeekBugDataExtract {

    public static final String URL="https://yuntu.sankuai.com/api/metrics/bug/reports?businessGroupId=104638&statType=statByReporter&horValue=org_name&verValue=severity_name&timeDimension=created&startDate=";

    @Resource
    public WeekBugTotalCountPOMapper weekBugTotalCountPOMapper;

    @Override
    public void extractData4Week(LocalDate firstDay, LocalDate lastDay) {
        String firstDayStr = firstDay.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String lastDayStr = lastDay.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String mSsoid = SsoUtils.getSsoId();
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmm"));

        Map<String,WeekBugTotalCountPO>  bugMap=new HashMap<>();

        JSONObject response=HttpUtils.doGet(URL+firstDayStr+"&endDate="+lastDayStr,JSONObject.class,ImmutableMap.of("Cookie", "Metrics_ssoid=" + mSsoid));
        JSONArray resp= (JSONArray) response.get("data");

        for (int i=1;i<resp.size();i++){
            String name= (String) ((JSONArray)(resp.get(i))).get(1);
            String type=(String) ((JSONArray)(resp.get(i))).get(0);
            int count= (int) ((JSONArray)(resp.get(i))).get(2);

            if (!bugMap.containsKey(name)){
                WeekBugTotalCountPO po=new WeekBugTotalCountPO();
                po.setTotalCount(0);
                po.setBlockerCount(0);
                po.setMajorCount(0);
                po.setMinorCount(0);
                po.setCriticalCount(0);
                po.setTrivialCount(0);
                po.setGroupName(name);
                po.setStartDate(firstDayStr);
                po.setEndDate(lastDayStr);
                po.setTimeFlag(Long.valueOf(timestamp));
                if (type.equals("Critical")){
                    po.setCriticalCount(count);
                }else if (type.equals("Major")){
                    po.setMajorCount(count);
                }else if (type.equals("Minor")){
                    po.setMinorCount(count);
                }else if (type.equals("Trivial")){
                    po.setTrivialCount(count);
                }else if (type.equals("Blocker")){
                    po.setBlockerCount(count);
                    if (count!=0){
                        String blockerLink="https://yuntu.sankuai.com/bug/list?businessGroupId=104638&statType=statByReporter&horValue=org_name&verValue=severity_name&timeDimension=created&startDate="+firstDayStr
                                +"&endDate="+lastDayStr+"&horData="+name+"&verData=Blocker";
                        po.setBugLink(blockerLink);
                    }
                }
                bugMap.put(name,po);
            }else {
                WeekBugTotalCountPO existPO = bugMap.get(name);
                if (type.equals("Critical")){
                    existPO.setCriticalCount(count);
                    bugMap.put(name,existPO);
                }else if (type.equals("Major")){
                    existPO.setMajorCount(count);
                    bugMap.put(name,existPO);
                }else if (type.equals("Minor")){
                    existPO.setMinorCount(count);
                    bugMap.put(name,existPO);
                }else if (type.equals("Trivial")){
                    existPO.setTrivialCount(count);
                    bugMap.put(name,existPO);
                }else if (type.equals("Blocker")){
                    existPO.setBlockerCount(count);
                    if (count!=0){
                        String blockerLink="https://yuntu.sankuai.com/bug/list?businessGroupId=104638&statType=statByReporter&horValue=org_name&verValue=severity_name&timeDimension=created&startDate="+firstDayStr
                                +"&endDate="+lastDayStr+"&horData="+name+"&verData=Blocker";
                        existPO.setBugLink(blockerLink);
                    }
                    bugMap.put(name,existPO);
                }
            }
        }

        for (String key:bugMap.keySet()){
            WeekBugTotalCountPO totalPO=bugMap.get(key);
            int totalCount=totalPO.getBlockerCount()+totalPO.getMajorCount()+totalPO.getCriticalCount()+totalPO.getMinorCount()+totalPO.getTrivialCount();
            totalPO.setTotalCount(totalCount);
            bugMap.put(key,totalPO);

            weekBugTotalCountPOMapper.insert(bugMap.get(key));
        }
    }
}
