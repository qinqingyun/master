package com.meituan.food.extract.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import com.meituan.food.extract.IGetApiDetailExtract;
import com.meituan.food.mapper.ApiDetailPOMapper;
import com.meituan.food.mapper.AppkeyListPOMapper;
import com.meituan.food.po.ApiDetailPO;
import com.meituan.food.utils.HttpUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class GetApiDetailExtracter implements IGetApiDetailExtract {

    @Resource
    private AppkeyListPOMapper appkeyListPOMapper;

    @Resource
    private ApiDetailPOMapper apiDetailPOMapper;

    private static final String url="http://octo.sankuai.com/api/apps/availabilities?appkeys=";


    @Override
    public void getApiDetail() {

        List<String> allAppkey = appkeyListPOMapper.selectAllAppkey();
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
                po.setIsCore(0);
                BigDecimal pro=new BigDecimal((double)apiMap.get(key)*100/allCount);
                po.setProportion(pro);
                po.setCreatedTime(now);
                po.setUpdatedAt(now);

                apiDetailPOMapper.insert(po);
            }

        }
    }

    public static void main(String[] args) {
       /* IGetApiDetailExtract a=new GetApiDetailExtracter();
        a.getApiDetail();*/

        Long a = 326232396l;
        Long b=294l;

        double c=(double) b/a*100;
        System.out.println(c);
        BigDecimal bigDecimal=new BigDecimal(c);
        System.out.println(bigDecimal);
    }
}
