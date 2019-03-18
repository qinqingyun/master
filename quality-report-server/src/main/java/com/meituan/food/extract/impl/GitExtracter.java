package com.meituan.food.extract.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import com.meituan.food.extract.IOneDayForEfficiencyDataExtract;
import com.meituan.food.mapper.GitPOMapper;
import com.meituan.food.po.GitPO;
import com.meituan.food.utils.HttpUtils;
import com.meituan.food.utils.SsoUtils;
import com.meituan.food.utils.UrlUtils;
import com.sun.prism.shader.Solid_ImagePattern_Loader;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class GitExtracter implements IOneDayForEfficiencyDataExtract {

    private static final String URL = "https://yuntu.sankuai.com/api/widget/widget-eebdd812-3817-4672-9421-1d59bb1f8164/data?";

    @Resource
    private GitPOMapper gitPOMapper;

    @Override
    public void extractEfficiencyData4Day(LocalDate day) {

        String dayStr = day.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        List<String> orgList = new ArrayList<>();
        orgList.add("100047");
        orgList.add("114614");
        orgList.add("114615");
        orgList.add("104638");
        /*todoindex来源获取*/
        for (String singleOrg : orgList) {


            //每一页数据获取
            JSONObject param = new JSONObject();
            param.put("endDate", dayStr);
            param.put("dateDim", "DAY_DATE");
            param.put("orgId",singleOrg );
            param.put("startDate", dayStr);

            String encodeParam = UrlUtils.encode(param.toJSONString());

            //先发一个请求，取出页数
            String urlForPageNum = URL + "params=" + encodeParam + "&index=1&useCache=true";

            JSONObject jsonObjectForPageNum = HttpUtils.doGet(urlForPageNum, JSONObject.class, ImmutableMap.of("Cookie", "Metrics_ssoid=" + SsoUtils.getSsoId()));

//        System.out.print(jsonObject.toString());    //调试看输出是否正确

            int pageNum = jsonObjectForPageNum.getJSONObject("data").getJSONObject("resData").getInteger("indexCounts");
//        System.out.print(pageNum);

            for (int pageIndex = 1; pageIndex <= pageNum; pageIndex++) {
                String url = URL + "params=" + encodeParam + "&index=" + pageIndex + "&useCache=true";
                JSONObject jsonObject = HttpUtils.doGet(url, JSONObject.class, ImmutableMap.of("Cookie", "Metrics_ssoid=" + SsoUtils.getSsoId()));
                JSONArray gitResult = jsonObject.getJSONObject("data").getJSONObject("resData").getJSONArray("data");
//            System.out.print(gitResult.toString());

                for (int i = 1; i < gitResult.size(); i++) {
                    GitPO gitPO = new GitPO();

                    String misAndName = ((JSONArray) gitResult.get(i)).getString(0);

                    String createName = misAndName.substring(0, misAndName.indexOf("("));
                    String createMis = misAndName.substring(misAndName.indexOf("(") + 1, misAndName.lastIndexOf(")"));

                    gitPO.setName(createName);
                    gitPO.setMisid(createMis);
                    gitPO.setGitDate(dayStr);

                    gitPO.setGitCodeIncrease(((JSONArray) gitResult.get(i)).getInteger(1));
                    gitPO.setGitCodeDelete(((JSONArray) gitResult.get(i)).getInteger(2));
                    gitPO.setGitCodeSubmit(((JSONArray) gitResult.get(i)).getInteger(3));
                    gitPO.setGitCodeSubmitTime(((JSONArray) gitResult.get(i)).getInteger(4));

                    gitPOMapper.insert(gitPO);

                }


            }
        }

    }


    public static void main(String[] args) {
        LocalDate localDate = LocalDate.now().minusDays(2);
        IOneDayForEfficiencyDataExtract m = new GitExtracter();
        m.extractEfficiencyData4Day(localDate);
    }
}
