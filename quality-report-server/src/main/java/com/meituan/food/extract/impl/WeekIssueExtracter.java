package com.meituan.food.extract.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.meituan.food.extract.IDataExtract;
import com.meituan.food.mapper.WeekIssuePOMapper;
import com.meituan.food.po.WeekIssuePO;
import com.meituan.food.utils.HttpUtils;
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

@Component
public class WeekIssueExtracter implements IDataExtract {

    private static final String URL = "http://issue.sankuai.com/api/incidents";

    private static final List<String> DEPARTMENTS = ImmutableList.of("meituan.meishi", "meituan.resv", "meituan.web");

    private static final List<String> CATEGORYS = ImmutableList.of("backend", "app", "extract");

    @Resource
    private WeekIssuePOMapper weekIssuePOMapper;

    @Transactional
    @Override
    public void extractData4Day(LocalDate day) throws Exception {
        String dayStr = day.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String encodedRange = URLEncoder.encode(dayStr + " ~ " + dayStr, "utf-8");
        List<CompletableFuture<List<WeekIssuePO>>> futures = DEPARTMENTS.stream()
                .map(deparment -> CATEGORYS.stream().map(category -> MutablePair.of(deparment, category)).collect(Collectors.toList()))
                .flatMap(Collection::stream)
                .map(deparmentCategoryPair -> queryWeekIssue4EachDeparmentCategoryGroup(deparmentCategoryPair, dayStr, encodedRange))
                .collect(Collectors.toList());
        List<WeekIssuePO> weekIssuePOS = futures.stream()
                .map(CompletableFuture::join)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(weekIssuePOS)) {
            weekIssuePOMapper.batchInsert(weekIssuePOS);
        }
    }


    private CompletableFuture<List<WeekIssuePO>> queryWeekIssue4EachDeparmentCategoryGroup(Pair<String, String> deparmentCategoryPair, String dayStr, String encodedRange) {
        return CompletableFuture.supplyAsync(() -> {
            JSONObject result = HttpUtils.doGet(URL + "?start=" + dayStr + "&end=" + dayStr + "&bg=到店餐饮&department=" + deparmentCategoryPair.getLeft()
                            + "&category=" + deparmentCategoryPair.getRight() + "&range=" + encodedRange,
                    JSONObject.class,
                    ImmutableMap.of("Authorization", "Bearer 9767c6e3488c88135f5032bc6da7339084b1a05b"));
            JSONArray incidents = result.getJSONArray("incidents");

            return incidents.stream()
                    .map(o -> (JSONObject) o)
                    .map(incident -> {
                        WeekIssuePO weekIssuePO = new WeekIssuePO();
                        weekIssuePO.setBrief(incident.getString("brief"));
                        weekIssuePO.setWiki(incident.getString("wiki"));
                        weekIssuePO.setType("");
                        weekIssuePO.setLevel(incident.getString("level"));
                        weekIssuePO.setDepartment(incident.getString("department"));
                        Date now = new Date();
                        weekIssuePO.setCreatedAt(now);
                        weekIssuePO.setUpdatedAt(now);
                        return weekIssuePO;
                    })
                    .collect(Collectors.toList());
        });
    }
}
