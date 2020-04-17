package com.meituan.food.extract.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.meituan.food.extract.IOneDayJenkinsViewsExtract;
import com.meituan.food.mapper.JenkinsViewPOMapper;
import com.meituan.food.po.JenkinsViewPO;
import com.meituan.food.utils.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.MessageFormat;
import java.util.*;

/**
 * Created by ntflc on 2019-04-26.
 */
@Slf4j
@Component
public class JenkinsViewsExtracter implements IOneDayJenkinsViewsExtract {

    private static final String allViewsApi = "http://ci.sankuai.com/job/meishi_b/api/json";

    private static final String allJobsByViewApi = "http://ci.sankuai.com/job/meishi_b/view/{0}/api/json";

    private static final String username = "test_sh";

    private static final String password = "Ceshi.123";

    @Resource
    private JenkinsViewPOMapper jenkinsViewPOMapper;

    @Override
    public void updateJenkinsView() {
        List <String> views = getAllViews();
        for (String view : views) {
            // 获取已存在的view数据
            Map<String, Integer> existJobMap = new HashMap<>();
            List<JenkinsViewPO> jenkinsViewPOS = jenkinsViewPOMapper.selectJobAndStatusByView(view);
            for (JenkinsViewPO jenkinsViewPO : jenkinsViewPOS) {
                existJobMap.put(jenkinsViewPO.getJob(), jenkinsViewPO.getStatus());
            }
            // 获取jobs
            List<Map<String, String>> jobsFromJenkins = getAllJobsByView(view);
            for (Map<String, String> map : jobsFromJenkins) {
                String job = map.get("name");
                String url = map.get("url");
                if (!existJobMap.containsKey(job)) {
                    JenkinsViewPO jenkinsViewPO = new JenkinsViewPO();
                    jenkinsViewPO.setView(view);
                    jenkinsViewPO.setJob(job);
                    jenkinsViewPO.setUrl(url);
                    jenkinsViewPO.setStatus(1);
                    jenkinsViewPOMapper.insert(jenkinsViewPO);
                } else {
                    if (existJobMap.get(job) == 0) {
                        jenkinsViewPOMapper.updateStatusByViewAndJob(1, view, job);
                    }
                    existJobMap.remove(job);
                }
            }
            // 更新不再存在的状态
            for (Map.Entry<String, Integer> entry : existJobMap.entrySet()) {
                jenkinsViewPOMapper.updateStatusByViewAndJob(0, view, entry.getKey());
            }
        }
    }

    private Map<String, String> getHeader() {
        String auth = MessageFormat.format("{0}:{1}", username, password);
        String encoding = Base64.getEncoder().encodeToString(auth.getBytes());
        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.AUTHORIZATION, MessageFormat.format("Basic {0}", encoding));
        return headers;
    }

    private List<String> getAllViews() {
        JSONObject respObject = HttpUtils.doGet(allViewsApi, JSONObject.class, getHeader());
        JSONArray viewsArray = respObject.getJSONArray("views");
        List<String> viewList = new ArrayList<>();
        for (Object item : viewsArray) {
            JSONObject viewObject = JSONObject.parseObject(item.toString());
            String view = viewObject.getString("name");
            if (!"All".equals(view)) {
                viewList.add(view);
            }
        }
        return viewList;
    }

    private List<Map<String, String>> getAllJobsByView(String view) {
        JSONObject respObject = HttpUtils.doGet(MessageFormat.format(allJobsByViewApi, view),
                JSONObject.class, getHeader());
        JSONArray jobsArray = respObject.getJSONArray("jobs");
        List<Map<String, String>> jobList = new ArrayList<>();
        for (Object item : jobsArray) {
            JSONObject jobObject = JSONObject.parseObject(item.toString());
            Map<String, String> map = new HashMap<>();
            map.put("name", jobObject.getString("name"));
            map.put("url", jobObject.getString("url"));
            jobList.add(map);
        }
        return jobList;
    }

}
