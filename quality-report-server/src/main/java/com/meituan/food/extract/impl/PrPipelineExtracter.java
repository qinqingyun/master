package com.meituan.food.extract.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import com.meituan.food.extract.IOneDayPrPipelineExtract;
import com.meituan.food.mapper.PipelinePrMapper;
import com.meituan.food.po.PipelinePrAutoPO;
import com.meituan.food.po.PipelinePrPO;
import com.meituan.food.utils.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
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
        JSONObject resp = HttpUtils.doPost(url, param, JSONObject.class, ImmutableMap.of("content-type", "application/json; charset=utf-8", "Cookie", ""));
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
            resp = HttpUtils.doPost(url, paramTarget, JSONObject.class, ImmutableMap.of("content-type", "application/json; charset=utf-8", "Cookie", ""));
        }catch (Exception ex){
            return "获取跳过原因失败{},paramTarget";
        }
        JSONArray reasonDetal = resp.getJSONArray("detail");
        for(int j=0;j<reasonDetal.size();j++){
            reason+=reasonDetal.getJSONObject(j).getString("stage")+"阶段:"+reasonDetal.getJSONObject(j).getString("desc");
        }
        return reason;
    }


    //pr组织下所有仓库自动化case和覆盖率
//    @Test
    public void UpdatePrAutoData(LocalDate date) {
        LocalDate today = date;
        LocalDate yesterday = today.plusDays(-1);
        String url = "http://qa.sankuai.com/data/pr/image?startTime=" + yesterday + "&endTime=" + today;//昨天数据
//        String url = "http://qa.sankuai.com/data/pr/image?startTime=2020-03-10&endTime=2020-03-17";
        //组织参数参考wiki https://km.sankuai.com/page/201266445
        String param = " [{\"value\":\"\",\"key\":\"260\"},{\"value\":\"\",\"key\":\"262\"},{\"value\":\"\",\"key\":\"264\"},{\"value\":\"\",\"key\":\"261\"},{\"value\":\"\",\"key\":\"265\"},{\"value\":\"\",\"key\":\"253\"},{\"value\":\"\",\"key\":\"254\"},{\"value\":\"\",\"key\":\"255\"},{\"value\":\"\",\"key\":\"296\"},{\"value\":\"\",\"key\":\"321\"},{\"value\":\"\",\"key\":\"251\"},{\"value\":\"\",\"key\":\"252\"},{\"value\":\"\",\"key\":\"256\"},{\"value\":\"\",\"key\":\"258\"},{\"value\":\"\",\"key\":\"259\"},{\"value\":\"\",\"key\":\"257\"},{\"value\":\"\",\"key\":\"241\"},{\"value\":\"\",\"key\":\"217\"}]";
        JSONObject resp = HttpUtils.doPost(url, param, JSONObject.class, ImmutableMap.of("content-type", "application/json; charset=utf-8", "Cookie", ""));
        pipelinePrMapper.deleteRepoByDate(yesterday);
        //遍历每个组织
        for(String strKey:resp.getJSONObject("data").keySet())
        {
            PipelinePrAutoPO pipelinePrAutoPO = new PipelinePrAutoPO();
            JSONObject data = resp.getJSONObject("data").getJSONObject(strKey);
            pipelinePrAutoPO.setDepartment_id(data.getInteger("direction_id"));
            pipelinePrAutoPO.setDirectionName(data.getString("label"));
            JSONObject repos = data.getJSONObject("children");

                for(String strKey2:repos.keySet()) {
                    if(!strKey2.contains("ssh")){//还继续向下分组情况260/262/296
                        JSONObject repos2=repos.getJSONObject(strKey2).getJSONObject("children");
                        //遍历组织下所有仓库
                        for(String strKey3:repos2.keySet()) {

                            JSONObject onePro = repos2.getJSONObject(strKey3);
                            pipelinePrAutoPO.setRepo(strKey3);

                            if(onePro.getBoolean("isAutoTest")!=null) {

                                if (onePro.getBoolean("isAutoTest")) {
                                    pipelinePrAutoPO.setIsAutoOn(1);
                                    PipelinePrAutoPO autoInfo = getAutoInfo(strKey3, yesterday);
                                    pipelinePrAutoPO.setTotalCase(autoInfo.getTotalCase());
                                    pipelinePrAutoPO.setPasses(autoInfo.getPasses());
                                    pipelinePrAutoPO.setCoverage(autoInfo.getCoverage());
                                } else {
                                    pipelinePrAutoPO.setIsAutoOn(0);
                                    //仓库自动化关闭，默认自动化数-1
                                    pipelinePrAutoPO.setTotalCase(-1);
                                    pipelinePrAutoPO.setPasses(BigDecimal.valueOf(-1));
                                    pipelinePrAutoPO.setCoverage(BigDecimal.valueOf(0));
                                }
                            }else {
                                //仓库下未标记isAutoTest
                                pipelinePrAutoPO.setIsAutoOn(0);
                                PipelinePrAutoPO autoInfoNotag = getAutoInfo(strKey3, yesterday);
                                pipelinePrAutoPO.setTotalCase(autoInfoNotag.getTotalCase());
                                pipelinePrAutoPO.setPasses(autoInfoNotag.getPasses());
                                pipelinePrAutoPO.setCoverage(autoInfoNotag.getCoverage());

                            }
                            pipelinePrAutoPO.setAuto_date(yesterday);
                            pipelinePrMapper.insertRepo(pipelinePrAutoPO);
                        }

                    }else {
                        //遍历组织下所有仓库todo
//                            JSONObject onePro = repos.getJSONObject(strKey2);
//                            pipelinePrAutoPO.setRepo(strKey2);
//
//                            if(onePro.getBoolean("isAutoTest")!=null) {
//
//                                if (onePro.getBoolean("isAutoTest")) {
//                                    pipelinePrAutoPO.setIsAutoOn(1);
//                                    PipelinePrAutoPO autoInfo = getAutoInfo(strKey2, yesterday);
//                                    pipelinePrAutoPO.setTotalCase(autoInfo.getTotalCase());
//                                    pipelinePrAutoPO.setPasses(autoInfo.getPasses());
//                                    pipelinePrAutoPO.setCoverage(autoInfo.getCoverage());
//                                } else {
//                                    pipelinePrAutoPO.setIsAutoOn(0);
//                                    //仓库自动化关闭，默认自动化数-1
//                                    pipelinePrAutoPO.setTotalCase(-1);
//                                    pipelinePrAutoPO.setPasses(BigDecimal.valueOf(-1));
//                                    pipelinePrAutoPO.setCoverage(BigDecimal.valueOf(0));
//                                }
//                            }else {
//                                //仓库下未标记isAutoTest
//                                pipelinePrAutoPO.setIsAutoOn(0);
//                                PipelinePrAutoPO autoInfoNotag = getAutoInfo(strKey2, yesterday);
//                                pipelinePrAutoPO.setTotalCase(autoInfoNotag.getTotalCase());
//                                pipelinePrAutoPO.setPasses(autoInfoNotag.getPasses());
//                                pipelinePrAutoPO.setCoverage(autoInfoNotag.getCoverage());
//
//                            }
//                            pipelinePrAutoPO.setAuto_date(yesterday);
//                            pipelinePrMapper.insertRepo(pipelinePrAutoPO);
                        }
//
                }




        }
    }

    public PipelinePrAutoPO getAutoInfo(String repo,LocalDate yesterday){
        LocalDate today = yesterday.plusDays(+1);
        PipelinePrAutoPO pipelinePrAutoPO = new PipelinePrAutoPO();
        String url = "http://qa.sankuai.com/data/autoTest/rd/application?repo="+repo+"&from="+ yesterday+"&to="+today;
        JSONObject resp = HttpUtils.doGet(url, JSONObject.class, ImmutableMap.of("content-type", "application/json; charset=utf-8", "Cookie", ""));

        JSONObject pr = resp.getJSONObject("data").getJSONObject("PR");
        if(pr!=null) {
            Integer k = 0;
            for (String strKey : pr.keySet()) {
                k++;
                if (k == 2) {
                    log.error("ddd");
                }
                JSONObject repoCases = pr.getJSONObject(strKey);
                Integer times = 0;
                    for (String strKey2 : repoCases.keySet()) {
                        times++;
                        if (times == repoCases.size()) {
                            JSONObject onetimeAuto = repoCases.getJSONObject(strKey2);
                            Integer totals = onetimeAuto.getInteger("total");
                            double coverage = onetimeAuto.getDouble("coverage");
                            double passes = onetimeAuto.getDouble("passes")/totals;
                            pipelinePrAutoPO.setTotalCase(totals);
                            pipelinePrAutoPO.setPasses(BigDecimal.valueOf(passes));
                            pipelinePrAutoPO.setCoverage(BigDecimal.valueOf(coverage));
                        }

                    }

            }
        }else {
            //没有PR执行默认-1
            pipelinePrAutoPO.setTotalCase(-1);
            pipelinePrAutoPO.setPasses(BigDecimal.valueOf(-1));
            pipelinePrAutoPO.setCoverage(BigDecimal.valueOf(0));
        }

//                pipelinePrAutoPO.setAllCase();

        return pipelinePrAutoPO;
    }
}
