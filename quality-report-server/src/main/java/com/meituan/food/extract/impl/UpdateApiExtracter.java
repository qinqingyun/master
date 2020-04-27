package com.meituan.food.extract.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.meituan.food.Services.ApiDetailService;
import com.meituan.food.extract.IUpdateApiExtract;
import com.meituan.food.mapper.ApiDetailPOMapper;
import com.meituan.food.mapper.AppkeyListPOMapper;
import com.meituan.food.po.ApiDetailPO;
import com.meituan.food.po.ApiDetailPOExample;
import com.meituan.food.utils.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@Slf4j
public class UpdateApiExtracter implements IUpdateApiExtract {

    @Resource
    private AppkeyListPOMapper appkeyListPOMapper;

    @Resource
    private ApiDetailPOMapper apiDetailPOMapper;

    @Resource
    private ExecutorService appApiDetailExtractPool;

    @Resource
    private ApiDetailService apiDetailService;

    private static final String url = "http://octo.sankuai.com/api/apps/availabilities?appkeys=";

    @Override
    public void updateApi() {
        List<String> allAppkey = appkeyListPOMapper.selectAllAppkey();
        List<List<String>> partitionAppkeys = Lists.partition(allAppkey, 300);
        List<ApiDetailPO> totalAppDetailPOS = partitionAppkeys.stream()
                .map(currentBatchAppkeys -> {
                    ApiDetailPOExample example = new ApiDetailPOExample();
                    example.createCriteria()
                            .andAppkeyIn(currentBatchAppkeys);
                    return apiDetailPOMapper.selectByExample(example);
                }).flatMap(Collection::stream)
                .collect(Collectors.toList());
        //<appkey+"_"+apiName,ApiDetailPO>
        Map<String, ApiDetailPO> appKeyApiNameDetailMap = totalAppDetailPOS.stream()
                .collect(Collectors.toMap(e -> e.getAppkey() + "_" + e.getApiSpanName(), Function.identity()));

        Date now = new Date();
        List<CompletableFuture<List<ApiDetailPO>>> futures = Lists.newArrayListWithCapacity(allAppkey.size());
        for (String s : allAppkey) {
            CompletableFuture<List<ApiDetailPO>> currentAppDetailFuture = CompletableFuture.supplyAsync(() -> extractCurrentAppApiDetails(appKeyApiNameDetailMap, now, s), appApiDetailExtractPool);
            futures.add(currentAppDetailFuture);
        }

        List<ApiDetailPO> apiDetailPOList = futures.stream()
                .map(CompletableFuture::join)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());


        apiDetailService.refreshApiDetail(apiDetailPOList);
    }

    private List<ApiDetailPO> extractCurrentAppApiDetails(Map<String, ApiDetailPO> appKeyApiNameDetailMap, Date now, String s) {
        List<ApiDetailPO> currentAppApiDetailPOList = new ArrayList<>();
        Map<String, Long> apiMap = new HashMap<>();
        Long allCount = 0L;
        for (int i = 30; i >= 1; i--) {

            LocalDate day = LocalDate.now().minusDays(i);
            String dayStr = day.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            JSONObject resp = HttpUtils.doGet(url + s + "&day=" + dayStr, JSONObject.class, ImmutableMap.of());
            JSONArray respArr = resp.getJSONArray("data");

            for (Object o : respArr) {
                String spanName = StringUtils.trim(((JSONObject) o).getString("spanname"));
                Long count = ((JSONObject) o).getLong("count");
                if (!spanName.equals("all")) {
                    if (apiMap.get(spanName) == null) {
                        apiMap.put(spanName, count);
                    } else {
                        apiMap.put(spanName, apiMap.get(spanName) + count);
                    }
                } else {
                    allCount = allCount + count;
                }
            }
        }

        for (String key : apiMap.keySet()) {
            ApiDetailPO po = new ApiDetailPO();
            po.setApiSpanName(key);
            po.setAppkey(s);
            po.setCallCount(apiMap.get(key));
            System.out.println("appkey=" + s + "  api=" + key);
            log.info("appkey=" + s + "  api=" + key);
//                    ApiDetailPO po1 = apiDetailPOMapper.selectBySpanName(key, s);
            ApiDetailPO po1 = appKeyApiNameDetailMap.get(s + "_" + key);
            if (po1 != null) {
                po.setIsCore(po1.getIsCore());
            } else {
                po.setIsCore(0);
            }
            BigDecimal pro = new BigDecimal((double) apiMap.get(key) * 100 / allCount);
            po.setProportion(pro);
            po.setCreatedTime(now);
            po.setUpdatedAt(now);
            if (apiMap.get(key)>1){
                currentAppApiDetailPOList.add(po);
            }
            currentAppApiDetailPOList.add(po);
        }
        return currentAppApiDetailPOList;
    }
}
