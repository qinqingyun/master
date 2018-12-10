package com.meituan.food.extract.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import com.meituan.food.extract.IOneMonthDataExtract;
import com.meituan.food.mapper.IssuePOMapper;
import com.meituan.food.mapper.LeakRatePOMapper;
import com.meituan.food.po.IssuePO;
import com.meituan.food.po.LeakRatePO;
import com.meituan.food.utils.HttpUtils;
import com.meituan.food.utils.SsoUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Order(2)
@Component
public class BugExtracter  implements IOneMonthDataExtract {

    private static final String URL="https://yuntu.sankuai.com/api/metrics/bug/reports";

    @Resource
    private LeakRatePOMapper leakRatePOMapper;

    @Resource
    private IssuePOMapper issuePOMapper;

    @Override
    public void extractData4Month(String firstDay, String lastDay) {

        String url=URL+"?businessGroupId=100047&statType=statByAssignee&horValue=org_name&verValue=severity_name&timeDimension=created&startDate="+firstDay+"&endDate="+lastDay;

        LeakRatePO leakRatePO=new LeakRatePO();

        JSONObject result=HttpUtils.doGet(url,JSONObject.class,ImmutableMap.of("Cookie", "Metrics_ssoid=" + SsoUtils.getSsoId()));

        JSONArray bugArray=result.getJSONArray("data");

        int total=0;

        for(int i=1;i<bugArray.size();i++){
            total=total+((JSONArray)bugArray.get(i)).getInteger(2);
        }

        List<IssuePO> issuePOS=new ArrayList<>();
        issuePOS=issuePOMapper.selectByMonth(firstDay);

        if(total!=0){
            leakRatePO.setIssueNum(issuePOS.size());
            leakRatePO.setBugNum(total);

            BigDecimal issueNum = new BigDecimal(issuePOS.size());
            BigDecimal leakRate = issueNum.divide(new BigDecimal(total), 8, RoundingMode.HALF_UP);
            BigDecimal perCentLeakRate = leakRate.movePointRight(2);

            leakRatePO.setLeakTestRate(perCentLeakRate);
            String issueMonth=firstDay.substring(0,firstDay.indexOf("-",firstDay.indexOf("-")+1));
            leakRatePO.setMonth(issueMonth);
            Date now = new Date();
            leakRatePO.setCreatedAt(now);
            leakRatePO.setUpdatedAt(now);
        }

        leakRatePOMapper.insert(leakRatePO);
    }

}
