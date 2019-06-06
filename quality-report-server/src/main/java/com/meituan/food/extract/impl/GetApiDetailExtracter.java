package com.meituan.food.extract.impl;

import com.alibaba.druid.support.json.JSONParser;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import com.meituan.food.extract.IGetApiDetailExtract;
import com.meituan.food.mapper.ApiDetailPOMapper;
import com.meituan.food.mapper.AppkeyListPOMapper;
import com.meituan.food.po.ApiDetailPO;
import com.meituan.food.utils.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class GetApiDetailExtracter implements IGetApiDetailExtract {

    @Resource
    private AppkeyListPOMapper appkeyListPOMapper;

    @Resource
    private ApiDetailPOMapper apiDetailPOMapper;

    private static final String url="http://octo.sankuai.com/api/apps/availabilities?appkeys=";

    private static final String apiurl="http://10.21.169.136:8888/api/detail/getApiDetail?appkey=";


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

    @Override
    public void setApiStatus() {
        List<ApiDetailPO> apiDetailPOS=apiDetailPOMapper.selectAllApi();
        Date now=new Date();
        for (ApiDetailPO apiDetailPO : apiDetailPOS) {
            Long num=apiDetailPO.getCallCount();
            if (num>40) {
                String apiName = apiDetailPO.getApiSpanName();
                String apiNameStr = apiName.replaceAll("\\{", "%7B").replaceAll("\\}", "%7D");
                String resp = HttpUtils.doGet(apiurl + apiDetailPO.getAppkey() + "&apiSpanName=" + apiNameStr, String.class, ImmutableMap.of());
                String respJsonStr = resp.substring(resp.indexOf("_json") + 7, resp.indexOf("</div>"));
                JSONObject respJson = JSONObject.parseObject(respJsonStr);
                JSONObject data = respJson.getJSONObject("data");
                log.info(data.toString());
                if (data != null) {
                    int isCore = data.getInteger("isCore");
                    if (isCore == 1) {
                        apiDetailPOMapper.updateByAppkeyAndApi(apiDetailPO.getApiSpanName(), apiDetailPO.getAppkey(), now);
                    }
                }
            }
        }
    }
}
