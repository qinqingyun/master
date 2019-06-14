package com.meituan.food.extract.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import com.meituan.food.extract.IUpdateApiExtract;
import com.meituan.food.mapper.ApiDetailPOMapper;
import com.meituan.food.mapper.AppkeyListPOMapper;
import com.meituan.food.po.ApiDetailPO;
import com.meituan.food.utils.HttpUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
public class UpdateApiExtracter implements IUpdateApiExtract {

    @Resource
    private AppkeyListPOMapper appkeyListPOMapper;

    @Resource
    private ApiDetailPOMapper apiDetailPOMapper;

    private static final String url="http://octo.sankuai.com/api/apps/availabilities?appkeys=";

    @Override
    public void updateApi() {
        List<String> allAppkey = appkeyListPOMapper.selectAllAppkey();
        List<ApiDetailPO> apiDetailPOList=new ArrayList<>();
        Date now=new Date();
        for (String s : allAppkey) {
            Map<String,Long> apiMap=new HashMap<>();
            Long allCount=0l;
            for(int i=30;i>=1;i--){
                LocalDate day = LocalDate.now().minusDays(i);
                String dayStr = day.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                JSONObject resp=HttpUtils.doGet(url+s+"&day="+dayStr,JSONObject.class,ImmutableMap.of());
                JSONArray respArr=resp.getJSONArray("data");
                for (Object o : respArr) {
                    String spanName=((JSONObject)o).getString("spanname");
                    Long count=((JSONObject)o).getLong("count");
                    if (!spanName.equals("all")){
                        if (apiMap.get(spanName)==null){
                            apiMap.put(spanName,count);
                        }else {
                            apiMap.put(spanName,apiMap.get(spanName)+count);
                        }
                    }else {
                        allCount = allCount+ count;
                    }
                }
            }

            for(String key:apiMap.keySet()){
                ApiDetailPO po=new ApiDetailPO();
                po.setApiSpanName(key);
                po.setAppkey(s);
                po.setCallCount(apiMap.get(key));
                System.out.println("appkey="+s+"  api="+key);
                ApiDetailPO po1 = apiDetailPOMapper.selectBySpanName(key, s);
                if (po1!=null){
                    po.setIsCore(po1.getIsCore());
                }else {
                    po.setIsCore(0);
                }
                BigDecimal pro=new BigDecimal((double)apiMap.get(key)*100/allCount);
                po.setProportion(pro);
                po.setCreatedTime(now);
                po.setUpdatedAt(now);
                apiDetailPOList.add(po);
                }

        }

        apiDetailPOMapper.deleteAllData();
        for (ApiDetailPO apiDetailPO : apiDetailPOList) {
            apiDetailPOMapper.insert(apiDetailPO);
        }
      //  apiDetailPOMapper.batchInsert(apiDetailPOList);
    }
}
