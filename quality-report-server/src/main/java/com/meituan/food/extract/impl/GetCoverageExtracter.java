package com.meituan.food.extract.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import com.meituan.food.extract.IGetCoverageExtract;
import com.meituan.food.mapper.*;
import com.meituan.food.po.*;
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

    private static final String url = "http://jacocolive.meishi.test.sankuai.com/public/getApiCoverageData?appkey=";

    @Resource
    public AppkeyListPOMapper appkeyListPOMapper;

    @Resource
    public ApiCoveragePOMapper apiCoveragePOMapper;

    @Resource
    public ApiDetailPOMapper apiDetailPOMapper;

    @Resource
    public DepartmentPOMapper departmentPOMapper;

    @Resource
    public BatchDatePOMapper batchDatePOMapper;

    @Resource
    public DepartmentApiCoveragePOMapper departmentApiCoveragePOMapper;

    @Override
    public void getCoverage() {
        List<String> allAppkey = appkeyListPOMapper.selectAllAppkey();
        Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(now);
        ParsePosition pos = new ParsePosition(0);
        Date currentTime_2 = formatter.parse(dateString, pos);

        BatchDatePO batchDatePO=new BatchDatePO();
        batchDatePO.setCoverageDate(currentTime_2);
        batchDatePOMapper.insert(batchDatePO);

        for (String s : allAppkey) {
            JSONObject resp = HttpUtils.doGet(url + s, JSONObject.class, ImmutableMap.of());
            int code = resp.getInteger("code");
            ApiCoveragePO po = new ApiCoveragePO();
            po.setAppkey(s);
            po.setCreatedTime(now);
            po.setCoverageDate(currentTime_2);
            AppkeyListPO appkeyListPO=appkeyListPOMapper.selectByAppKey(s);
            po.setDepartmentId(appkeyListPO.getDepartmentId());
            po.setDepartmentId2(appkeyListPO.getDepartmentId2());
            DepartmentPO departmentPO=departmentPOMapper.selectByPrimaryKey(appkeyListPO.getDepartmentId2());
            po.setDepartment(departmentPO.getDepartment());
            po.setDepartment2(departmentPO.getDepartment2());
            List<ApiDetailPO> allApiList=apiDetailPOMapper.selectByAppkey(s);
            int allApiNum=allApiList.size();
            po.setAllApiNum(allApiNum);
            if (code == 0) {
                JSONObject respData = resp.getJSONObject("data");
                int coverApiNum = respData.getJSONObject("totalApiCoverage").getInteger("apiCovered");
                int allCoreApiNum = 0;
                int coverCoreApiNum = 0;
                po.setCoverApiNum(coverApiNum);
                if (allApiNum!=0){
                    double apiCoverage = (double) coverApiNum *100/ allApiNum;
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
                    double coreCoverage = (double) coverCoreApiNum *100/ allCoreApiNum;
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
          //      po.setAllApiNum(0);
                po.setCoverApiNum(0);
                po.setApiCoverage(new BigDecimal(0));
                po.setCoreApiCoverage(new BigDecimal(0));
            }
            apiCoveragePOMapper.insert(po);
        }

       for (int i=1;i<=11;i++){
            List<ApiCoveragePO> groupList=apiCoveragePOMapper.selectByDateDepartment2id(currentTime_2,i);
            int allAPiCount=0;
            int coverApiCount=0;
            int allCoreApiCount=0;
            int coverCoreApiCount=0;
           DepartmentApiCoveragePO departmentApiCoveragePO=new DepartmentApiCoveragePO();
           String departmentName=groupList.get(0).getDepartment2();
            for (ApiCoveragePO apiCoveragePO : groupList) {
                allAPiCount=allAPiCount+apiCoveragePO.getAllApiNum();
                coverApiCount=coverApiCount+apiCoveragePO.getCoverApiNum();
                allCoreApiCount=allCoreApiCount+apiCoveragePO.getAllCoreApiNum();
                coverCoreApiCount=coverCoreApiCount+apiCoveragePO.getCoverCoreApiNum();
            }
            if (allAPiCount!=0){
                BigDecimal apiCoverage=new BigDecimal((double)coverApiCount*100/allAPiCount);
                departmentApiCoveragePO.setApiCoverage(apiCoverage);
            }else {
                BigDecimal apiCoverage=new BigDecimal(0);
                departmentApiCoveragePO.setApiCoverage(apiCoverage);
            }
            if (allCoreApiCount!=0){
                BigDecimal coreApiCoverage=new BigDecimal((double)coverCoreApiCount*100/allCoreApiCount);
                departmentApiCoveragePO.setCoreApiCoverage(coreApiCoverage);
            }else {
                BigDecimal coreApiCoverage=new BigDecimal(0);
                departmentApiCoveragePO.setCoreApiCoverage(coreApiCoverage);
            }
            departmentApiCoveragePO.setDepartmentId(i);
            departmentApiCoveragePO.setDepartmentName(departmentName);
            departmentApiCoveragePO.setAllApiNum(allAPiCount);
            departmentApiCoveragePO.setCoverApiNum(coverApiCount);
            departmentApiCoveragePO.setAllCoreApiNum(allCoreApiCount);
            departmentApiCoveragePO.setCoverCoreApiNum(coverCoreApiCount);
            departmentApiCoveragePO.setCoverageDate(currentTime_2);
            departmentApiCoveragePO.setStatus(1);

            departmentApiCoveragePOMapper.insert(departmentApiCoveragePO);
        }

        for (int i=1;i<=11;i++){
            if (i!=8&&i!=9){
                List<ApiCoveragePO> groupList = apiCoveragePOMapper.selectByDateDepartmentId(currentTime_2, i);
                DepartmentApiCoveragePO departmentApiCoveragePO = new DepartmentApiCoveragePO();
                int allAPiCount = 0;
                int coverApiCount = 0;
                int allCoreApiCount = 0;
                int coverCoreApiCount = 0;
                String departmentName = groupList.get(0).getDepartment();
                for (ApiCoveragePO po : groupList) {
                    allAPiCount = allAPiCount + po.getAllApiNum();
                    coverApiCount = coverApiCount + po.getCoverApiNum();
                    allCoreApiCount = allCoreApiCount + po.getAllCoreApiNum();
                    coverCoreApiCount = coverCoreApiCount + po.getCoverCoreApiNum();
                }
                if (allAPiCount != 0) {
                    BigDecimal apiCoverage = new BigDecimal((double) coverApiCount * 100 / allAPiCount);
                    departmentApiCoveragePO.setApiCoverage(apiCoverage);
                } else {
                    BigDecimal apiCoverage = new BigDecimal(0);
                    departmentApiCoveragePO.setApiCoverage(apiCoverage);
                }
                if (allCoreApiCount != 0) {
                    BigDecimal coreApiCoverage = new BigDecimal((double) coverCoreApiCount * 100 / allCoreApiCount);
                    departmentApiCoveragePO.setCoreApiCoverage(coreApiCoverage);
                } else {
                    BigDecimal coreApiCoverage = new BigDecimal(0);
                    departmentApiCoveragePO.setCoreApiCoverage(coreApiCoverage);
                }
                departmentApiCoveragePO.setDepartmentId(i);
                departmentApiCoveragePO.setDepartmentName(departmentName);
                departmentApiCoveragePO.setAllApiNum(allAPiCount);
                departmentApiCoveragePO.setCoverApiNum(coverApiCount);
                departmentApiCoveragePO.setAllCoreApiNum(allCoreApiCount);
                departmentApiCoveragePO.setCoverCoreApiNum(coverCoreApiCount);
                departmentApiCoveragePO.setCoverageDate(currentTime_2);
                departmentApiCoveragePO.setStatus(0);

                departmentApiCoveragePOMapper.insert(departmentApiCoveragePO);
            }
        }

    }
}
