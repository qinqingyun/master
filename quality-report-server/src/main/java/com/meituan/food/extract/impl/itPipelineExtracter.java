package com.meituan.food.extract.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import com.meituan.food.extract.IOneDayItPipelineExtract;
import com.meituan.food.mapper.PipelineItMapper;
import com.meituan.food.po.PipelineItPO;
import com.meituan.food.utils.HttpUtils;
import com.meituan.food.utils.SsoUtils;
import com.sankuai.meituan.org.opensdk.service.OrgService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Slf4j
@Component
public class itPipelineExtracter implements IOneDayItPipelineExtract {
    @Resource
    public PipelineItMapper pipelineItMapper;

    @Resource
    private OrgService orgService;
    private String v;

    @Override
    public void updateItPipelineData(Date date) {
        LocalDate today = LocalDate.now();
        LocalDate yesterday = LocalDate.now().plusDays(-1);
        String url = "http://qa.sankuai.com/data/it/operate?from=" + yesterday + "&to=" + today;
//        参数469为到店餐饮测试组id
        String param = "[{\"value\":\"\",\"key\":\"469\"}]";
        JSONObject resp = HttpUtils.doPost(url, param, JSONObject.class, ImmutableMap.of("content-type", "application/json; charset=utf-8", "Cookie", "com.sankuai.it.ead.citadel_ssoid=" + SsoUtils.getSsoId()));
        JSONArray itPipelineData = resp.getJSONObject("data").getJSONArray("到店餐饮测试组#469");
        PipelineItPO pipelineItPO = new PipelineItPO();
        double passRate = 0.00;
        double casePassRate = 0.00;
        pipelineItMapper.deleteByDate(date);

        for (int i = 1; i < itPipelineData.size(); i++) {
            JSONObject data = itPipelineData.getJSONObject(i);
            pipelineItPO.setDepartmentId(data.getInteger("id"));
            pipelineItPO.setDepartmentName(data.getString("name"));
            Integer pipelinePass = data.getInteger("pipelinePass");
            Integer pipelineTotal = data.getInteger("pipelineTotal");
            pipelineItPO.setPipelinePass(pipelinePass);
            pipelineItPO.setPipelineTotal(pipelineTotal);
            if (pipelinePass!=0){
                passRate = new BigDecimal((float)pipelinePass/pipelineTotal).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            }
            pipelineItPO.setPassRate(BigDecimal.valueOf(passRate));
            Integer wholeCasePass = data.getInteger("wholeCasePass");
            Integer wholeCaseTotal = data.getInteger("wholeCaseTotal");
            pipelineItPO.setWholeCasePass(wholeCasePass);
            pipelineItPO.setWholeCaseTotal(wholeCaseTotal);
            if (wholeCasePass!=0) {
                casePassRate = new BigDecimal((float) wholeCasePass / wholeCaseTotal).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                }
            pipelineItPO.setCasePassRate(BigDecimal.valueOf(casePassRate));
            pipelineItPO.setCreateTime(date);
            pipelineItMapper.insert(pipelineItPO);

        }
    }
}
