package com.meituan.food.extract.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import com.meituan.food.extract.IGetCoverageExtract;
import com.meituan.food.mapper.ApiCoveragePOMapper;
import com.meituan.food.mapper.ApiDetailPOMapper;
import com.meituan.food.mapper.AppkeyListPOMapper;
import com.meituan.food.po.ApiCoveragePO;
import com.meituan.food.po.ApiDetailPO;
import com.meituan.food.utils.HttpUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class GetCoverageExtracter implements IGetCoverageExtract {

    private static final String url = "http://10.23.8.224:8080/public/getApiCoverageData?appkey=";

    @Resource
    public AppkeyListPOMapper appkeyListPOMapper;

    @Resource
    public ApiCoveragePOMapper apiCoveragePOMapper;

    @Resource
    public ApiDetailPOMapper apiDetailPOMapper;

    @Override
    public void getCoverage() {
        List<String> allAppkey = appkeyListPOMapper.selectAllAppkey();
        Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(now);
        ParsePosition pos = new ParsePosition(8);
        Date currentTime_2 = formatter.parse(dateString, pos);
        for (String s : allAppkey) {
            JSONObject resp = HttpUtils.doGet(url + s, JSONObject.class, ImmutableMap.of());
            int code = resp.getInteger("code");
            ApiCoveragePO po = new ApiCoveragePO();
            po.setAppkey(s);
            po.setCreatedTime(now);
            po.setCoverageDate(currentTime_2);
            if (code == 0) {
                JSONObject respData = resp.getJSONObject("data");
                po.setDepartment(respData.getString("department"));
                int allApiNum = respData.getJSONObject("totalApiCoverage").getInteger("apiNumber");
                int coverApiNum = respData.getJSONObject("totalApiCoverage").getInteger("apiCovered");
                int allCoreApiNum = 0;
                int coverCoreApiNum = 0;
                po.setAllApiNum(allApiNum);
                po.setCoverApiNum(coverApiNum);
                if (allApiNum!=0){
                    double apiCoverage = (double) coverApiNum / allApiNum;
                    BigDecimal coverage = new BigDecimal(apiCoverage);
                    po.setApiCoverage(coverage);
                }else {
                    BigDecimal coverage = new BigDecimal(0);
                    po.setApiCoverage(coverage);
                }
                JSONArray apiDetailArray = respData.getJSONObject("totalApiCoverage").getJSONArray("apiDetail");
                for (Object o : apiDetailArray) {
                    String spanName = ((JSONObject) o).getString("apiSpanName");
                    boolean cover = ((JSONObject) o).getBoolean("cover");
                    System.out.println("这条数据的spanName="+spanName);
                    ApiDetailPO apiPo = apiDetailPOMapper.selectBySpanName(spanName,s);
                    if (apiPo != null) {
                        int isCore=apiPo.getIsCore();
                        if (isCore == 1) {
                            allCoreApiNum++;
                            if (cover == true) {
                                coverCoreApiNum++;
                            }
                        }
                    }
                }
                if (allCoreApiNum != 0) {
                    double coreCoverage = (double) coverCoreApiNum / allCoreApiNum;
                    BigDecimal coverageDecimal = new BigDecimal(coreCoverage);
                    po.setCoreApiCoverage(coverageDecimal);
                } else {
                    BigDecimal coverageDecimal = new BigDecimal(0);
                    po.setCoreApiCoverage(coverageDecimal);
                }
                po.setAllCoreApiNum(allCoreApiNum);
                po.setCoverCoreApiNum(coverCoreApiNum);
            } else {
                po.setCoverCoreApiNum(0);
                po.setAllCoreApiNum(0);
                po.setDepartment("");
                po.setAllApiNum(0);
                po.setCoverApiNum(0);
                po.setApiCoverage(new BigDecimal(0));
                po.setCoreApiCoverage(new BigDecimal(0));
            }
            apiCoveragePOMapper.insert(po);
        }
    }
}
