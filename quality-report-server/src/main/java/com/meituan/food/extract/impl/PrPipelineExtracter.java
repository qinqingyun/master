package com.meituan.food.extract.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import com.meituan.food.extract.IOneDayPrPipelineExtract;
import com.meituan.food.mapper.PipelinePrMapper;
import com.meituan.food.po.PipelinePrPO;
import com.meituan.food.utils.HttpUtils;
import com.meituan.food.utils.SsoUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.ArrayList;

@Slf4j
@Component
public class PrPipelineExtracter implements IOneDayPrPipelineExtract {
    @Resource
    public PipelinePrMapper pipelinePrMapper;

    ArrayList<String> skipDeatil = new ArrayList<>();
    ArrayList<String> skipReason = new ArrayList<>();
    String hotfixDeatilStr="";
    String skipDeatilStr="";
    String skipReasonStr="";


    @Override
    public void UpdatePrPipelineData(LocalDate date) {
        String url = "http://qa.sankuai.com/data/pr/detail?startTime=" + date + "&endTime=" + date;
        //组织参数参考wiki https://km.sankuai.com/page/201266445
        String param = " [{\"value\":\"\",\"key\":\"260\"},{\"value\":\"\",\"key\":\"262\"},{\"value\":\"\",\"key\":\"264\"},{\"value\":\"\",\"key\":\"261\"},{\"value\":\"\",\"key\":\"265\"},{\"value\":\"\",\"key\":\"253\"},{\"value\":\"\",\"key\":\"254\"},{\"value\":\"\",\"key\":\"255\"},{\"value\":\"\",\"key\":\"296\"},{\"value\":\"\",\"key\":\"321\"},{\"value\":\"\",\"key\":\"251\"},{\"value\":\"\",\"key\":\"252\"},{\"value\":\"\",\"key\":\"256\"},{\"value\":\"\",\"key\":\"258\"},{\"value\":\"\",\"key\":\"259\"},{\"value\":\"\",\"key\":\"257\"},{\"value\":\"\",\"key\":\"241\"},{\"value\":\"\",\"key\":\"217\"}]";
        JSONObject resp = HttpUtils.doPost(url, param, JSONObject.class, ImmutableMap.of("content-type", "application/json; charset=utf-8", "Cookie", "com.sankuai.it.ead.citadel_ssoid=" + SsoUtils.getSsoId()));
        PipelinePrPO pipelinePrPO = new PipelinePrPO();
        pipelinePrMapper.deleteByDate(date);
        for(String strKey:resp.getJSONObject("data").keySet())
        {
            JSONObject data = resp.getJSONObject("data").getJSONObject(strKey);
            pipelinePrPO.setDirection_id(data.getInteger("direction_id"));
            pipelinePrPO.setDirectionName(data.getString("label"));
            pipelinePrPO.setHotfix(data.getJSONArray("hotfix").size());
            //hotfix详情
            hotfixDeatilStr = getHotfixDetail(data.getJSONArray("hotfix"));
            pipelinePrPO.setHotfixDetailStr(hotfixDeatilStr);
            pipelinePrPO.setSkip(data.getJSONArray("skip").size());
            //skip跳过详情
            skipDeatil = getSkipDetail(data.getJSONArray("skip"));
            pipelinePrPO.setSkipDetailStr(skipDeatil.toString());
            //skip跳过原因
            skipReasonStr = getSkipReason(skipDeatil);
            pipelinePrPO.setSkipReasonStr(skipReasonStr);
            pipelinePrPO.setCreateTime(date);
            pipelinePrMapper.insert(pipelinePrPO);

            }
    }
    public String getHotfixDetail(JSONArray hotfixInfo){
        String detail ="";
        for(int i=0;i<hotfixInfo.size();i++){
            detail+=hotfixInfo.getJSONObject(i).getString("git")+"-"+hotfixInfo.getJSONObject(i).getString("branch")+"-"+hotfixInfo.getJSONObject(i).getString("commitor");
        }
        return detail;
    }
    public ArrayList<String> getSkipDetail(JSONArray skipInfo){
        skipDeatilStr="";
        ArrayList<String> detail = new ArrayList<>();
        for(int i=0;i<skipInfo.size();i++){
            detail.add(i,'"'+skipInfo.getJSONObject(i).getString("git")+"#"+skipInfo.getJSONObject(i).getString("pullId")+'"');
        }
        return detail;
    }
    public String getSkipReason(ArrayList<String> skipDetail){
        String url = "http://qa.sankuai.com/cq/cq/pr-skip/reason/query/multi";
        String param = "";
        String paramTarget = "";
        JSONObject resp;
       String reason = "";
        if(skipDetail.size()==0){
            return "";
        }
        for(int i=0;i<skipDetail.size();i++){
            if (i==skipDetail.size()-1){
                param+=skipDetail.get(i);
            }else {
                param+=skipDetail.get(i)+',';
            }
        }
        paramTarget = "{\"target\":["+param+"]}";
        try {
            resp = HttpUtils.doPost(url, paramTarget, JSONObject.class, ImmutableMap.of("content-type", "application/json; charset=utf-8", "Cookie", "com.sankuai.it.ead.citadel_ssoid=" + SsoUtils.getSsoId()));
        }catch (Exception ex){
            return "获取跳过原因失败{},paramTarget";
        }
        JSONArray reasonDetal = resp.getJSONArray("detail");
        for(int j=0;j<reasonDetal.size();j++){
            reason+=reasonDetal.getJSONObject(j).getString("stage")+"阶段:"+reasonDetal.getJSONObject(j).getString("desc");
        }
        return reason;
    }
}
