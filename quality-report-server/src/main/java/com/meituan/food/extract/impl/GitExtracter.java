package com.meituan.food.extract.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import com.meituan.food.extract.IOneDayForEfficiencyDataExtract;
import com.meituan.food.mapper.GitPOMapper;
import com.meituan.food.po.GitPO;
import com.meituan.food.utils.HttpUtils;
import com.meituan.food.utils.SsoUtils;
import com.meituan.food.utils.UrlUtils;
import com.meituan.food.utils.YunTuBa;
import com.sun.prism.shader.Solid_ImagePattern_Loader;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class GitExtracter implements IOneDayForEfficiencyDataExtract {

    private static final String URL = "https://yuntu.sankuai.com/api/open/v1/widget/widget-eebdd812-3817-4672-9421-1d59bb1f8164/data/page";

    @Resource
    private GitPOMapper gitPOMapper;

    @Override
    public void extractEfficiencyData4Day(LocalDate day) {

        String dayStr = day.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

//        List<String> orgList = new ArrayList<>();
//        orgList.add("100047");
//        orgList.add("114614");
//        orgList.add("114615");
//        orgList.add("104638");
        //orgId为平台技术部的orgId
        String orgId = "112515";

        JSONObject param = new JSONObject();
        param.put("endDate", dayStr);
        param.put("dateDim", "DAY_DATE");
        param.put("orgId",orgId );
        param.put("startDate", dayStr);
        param.put("env", "prod");

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = YunTuBa.getAuthHeader("/api/open/v1/widget/widget-eebdd812-3817-4672-9421-1d59bb1f8164/data/page", "POST");
        MultiValueMap<String, Object> urlVariables = new LinkedMultiValueMap();
        urlVariables.add("widgetId", "widget-eebdd812-3817-4672-9421-1d59bb1f8164");
        urlVariables.add("index", 1);
        urlVariables.add("params", param);
        urlVariables.add("dashKey", "dashboard-08c7942f-3d6c-44ed-8d76-3d5c047531f5");
        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity(urlVariables, headers);
        JSONObject jsonObjectForPageNum = restTemplate.postForEntity(URL, httpEntity, JSONObject.class).getBody();
        int pageNum = jsonObjectForPageNum.getJSONObject("data").getJSONObject("resData").getInteger("indexCounts");

        for (int pageIndex = 1; pageIndex <= pageNum; pageIndex++) {
            MultiValueMap<String, Object> partUrlVariables = new LinkedMultiValueMap();
            partUrlVariables.add("widgetId", "widget-eebdd812-3817-4672-9421-1d59bb1f8164");
            partUrlVariables.add("index", pageIndex);
            partUrlVariables.add("params", param);
            partUrlVariables.add("dashKey", "dashboard-08c7942f-3d6c-44ed-8d76-3d5c047531f5");
            HttpEntity<MultiValueMap<String, Object>> partHttpEntity = new HttpEntity(partUrlVariables, headers);
            JSONObject jsonObject = restTemplate.postForEntity(URL, partHttpEntity, JSONObject.class).getBody();
            JSONArray gitResult = jsonObject.getJSONObject("data").getJSONObject("resData").getJSONArray("data");

            for (int i = 1; i < gitResult.size(); i++) {
                GitPO gitPO = new GitPO();
                JSONArray gitResultArr=JSON.parseArray(JSONObject.toJSONString(gitResult.get(i)));

                String misAndName = gitResultArr.getString(0);

                String createName = misAndName.substring(0, misAndName.indexOf("("));
                String createMis = misAndName.substring(misAndName.indexOf("(") + 1, misAndName.lastIndexOf(")"));

                gitPO.setName(createName);
                gitPO.setMisid(createMis);
                gitPO.setGitDate(dayStr);

                gitPO.setGitCodeIncrease(gitResultArr.getInteger(1));
                gitPO.setGitCodeDelete(gitResultArr.getInteger(2));
                gitPO.setGitCodeSubmit(gitResultArr.getInteger(3));
                gitPO.setGitCodeSubmitTime(gitResultArr.getInteger(4));

                gitPOMapper.insert(gitPO);
            }
        }
    }
}
