package com.meituan.food.extract.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.meituan.food.extract.IOneDayDataExtract;
import com.meituan.food.extract.IOneMonthDataExtract;
import com.meituan.food.mapper.IssuePOMapper;
import com.meituan.food.po.IssuePO;
import com.meituan.food.utils.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Slf4j
@Component
public class IssueExtracter implements IOneMonthDataExtract {

    private static final String URL = "http://issue.sankuai.com/api/incidents";

    private static final List<String> DEPARTMENTS = ImmutableList.of("meituan.meishi", "meituan.resv", "meituan.web");

    private static final List<String> CATEGORYS = ImmutableList.of("backend", "app", "extract");

    @Resource
    private IssuePOMapper issuePOMapper;

    @Transactional
    @Override
    public void extractData4Month(String firstDay,String lastDay) {
     //   String dayStr = day.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String encodedRange;
        try {
            encodedRange = URLEncoder.encode(firstDay + " ~ " + lastDay, "utf-8");
        } catch (Exception e) {
            log.error(" URLEncoder#encode error.", e);
            throw new RuntimeException("URLEncoder#encode error.", e);
        }
        List<CompletableFuture<List<IssuePO>>> futures = DEPARTMENTS.stream()
                .map(deparment -> CATEGORYS.stream().map(category -> MutablePair.of(deparment, category)).collect(Collectors.toList()))
                .flatMap(Collection::stream)
                .map(deparmentCategoryPair -> queryIssue4EachDeparmentCategoryGroup(deparmentCategoryPair, firstDay,lastDay, encodedRange))
                .collect(Collectors.toList());
        List<IssuePO> issuePOS = futures.stream()
                .map(CompletableFuture::join)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(issuePOS)) {
            issuePOMapper.batchInsert(issuePOS);
        }
    }


    private CompletableFuture<List<IssuePO>> queryIssue4EachDeparmentCategoryGroup(Pair<String, String> deparmentCategoryPair, String firstDay,String lastDay, String encodedRange) {
        return CompletableFuture.supplyAsync(() -> {
            JSONObject result = HttpUtils.doGet(URL + "?start=" + firstDay + "&end=" + lastDay + "&bg=到店餐饮&department=" + deparmentCategoryPair.getLeft()
                            + "&category=" + deparmentCategoryPair.getRight() + "&range=" + encodedRange,
                    JSONObject.class,
                    ImmutableMap.of("Authorization", "Bearer 9767c6e3488c88135f5032bc6da7339084b1a05b"));
            JSONArray incidents = result.getJSONArray("incidents");

            return incidents.stream()
                    .map(o -> (JSONObject) o)
                    .map(incident -> {
                        IssuePO issuePO = new IssuePO();
                        issuePO.setBrief(incident.getString("brief"));
                        issuePO.setWiki(incident.getString("wiki"));
                        issuePO.setType("");
                        issuePO.setLevel(incident.getString("level"));
                        issuePO.setDepartment(incident.getString("department"));
                        Date now = new Date();
                        issuePO.setCreatedAt(now);
                        issuePO.setUpdatedAt(now);
                        return issuePO;
                    })
                    .collect(Collectors.toList());
        });
    }
}
