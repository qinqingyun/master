package com.meituan.food.extract.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dianping.pigeon.remoting.invoker.concurrent.Callback;
import com.google.common.collect.ImmutableMap;
import com.meituan.food.extract.IOneDayPrPipelineExtract;
import com.meituan.food.mapper.PipelinePrMapper;
import com.meituan.food.po.PipelinePrAutoPO;
import com.meituan.food.po.PipelinePrPO;
import com.meituan.food.utils.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

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
        //组织参数参考wiki: https://km.sankuai.com/page/201266445-排除254
        String param = " [{\"value\":\"\",\"key\":\"260\"},{\"value\":\"\",\"key\":\"262\"},{\"value\":\"\",\"key\":\"264\"},{\"value\":\"\",\"key\":\"261\"},{\"value\":\"\",\"key\":\"253\"},{\"value\":\"\",\"key\":\"255\"},{\"value\":\"\",\"key\":\"296\"},{\"value\":\"\",\"key\":\"321\"},{\"value\":\"\",\"key\":\"251\"},{\"value\":\"\",\"key\":\"256\"},{\"value\":\"\",\"key\":\"258\"},{\"value\":\"\",\"key\":\"259\"},{\"value\":\"\",\"key\":\"257\"},{\"value\":\"\",\"key\":\"241\"},{\"value\":\"\",\"key\":\"217\"},{\"value\":\"\",\"key\":\"497\"}]";
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
    public void UpdatePrAutoData(LocalDate date) {
        LocalDate yesterday = date.plusDays(-1);
        pipelinePrMapper.deleteRepoByDate(yesterday);
        //组织参数参考wiki https://km.sankuai.com/page/201266445-去除252-254-265
        String param = "{\"value\":\"\",\"key\":\"253\"};{\"value\":\"\",\"key\":\"241\"};{\"value\":\"\",\"key\":\"217\"};{\"value\":\"\",\"key\":\"262\"};{\"value\":\"\",\"key\":\"264\"};{\"value\":\"\",\"key\":\"261\"};{\"value\":\"\",\"key\":\"296\"};{\"value\":\"\",\"key\":\"255\"};{\"value\":\"\",\"key\":\"260\"};{\"value\":\"\",\"key\":\"321\"};{\"value\":\"\",\"key\":\"251\"};{\"value\":\"\",\"key\":\"256\"};{\"value\":\"\",\"key\":\"258\"};{\"value\":\"\",\"key\":\"259\"};{\"value\":\"\",\"key\":\"257\"};{\"value\":\"\",\"key\":\"497\"}";
        List<String> dirList= Arrays.asList(param.split(";"));
        // 遍历每个组织
        List<PipelinePrAutoPO> prDatasArry= new CopyOnWriteArrayList<>();
        long s = System.currentTimeMillis();
        dirList.parallelStream().forEach(e -> prDatasArry.addAll(insertData(e,date)));
//        更新组织下仓库数据
        pipelinePrMapper.deleteDirRepoByDate();
        pipelinePrMapper.insertRepoInfoList(prDatasArry);
        long e = System.currentTimeMillis();
        System.out.println((e - s));
    }

    public List<PipelinePrAutoPO> insertData(String dir,LocalDate date){
        List <PipelinePrAutoPO> pipelinePrAutoArry = new ArrayList<PipelinePrAutoPO>();
        String param = "["+dir+"]";
        LocalDate yesterday = date.plusDays(-1);
        String url = "http://qa.sankuai.com/data/pr/image?startTime=" + yesterday + "&endTime=" + date;//昨天数据
        JSONObject resp = HttpUtils.doPost(url, param, JSONObject.class, ImmutableMap.of("content-type", "application/json; charset=utf-8", "Cookie", ""));
        for(String strKey:resp.getJSONObject("data").keySet()) {
            //一个key，还必须遍历。。
            JSONObject data = resp.getJSONObject("data").getJSONObject(strKey);


            JSONObject repos = data.getJSONObject("children");
            for (String strKey2 : repos.keySet()) {

                if (!strKey2.contains("ssh")) {//还继续向下分组情况260/262/296
                    JSONObject repos2 = repos.getJSONObject(strKey2).getJSONObject("children");
                    //遍历组织下所有仓库
                    for (String strKey3 : repos2.keySet()) {

                        PipelinePrAutoPO pipelinePrAutoPO = new PipelinePrAutoPO();
                        pipelinePrAutoPO.setDepartment_id(data.getInteger("direction_id"));
                        pipelinePrAutoPO.setDirectionName(data.getString("label"));

                        //获取仓库pr次数
                        PipelinePrAutoPO getPRTimes=getPRTimes(strKey3, date);
                        pipelinePrAutoPO.setTimes(getPRTimes.getTimes());
                        pipelinePrAutoPO.setAuto_times(getPRTimes.getAuto_times());
                        pipelinePrAutoPO.setAuto_success_times(getPRTimes.getAuto_success_times());
                        pipelinePrAutoPO.setTotalCase(getPRTimes.getTotalCase());
                        pipelinePrAutoPO.setCoverage(getPRTimes.getCoverage());
                        JSONObject onePro = repos2.getJSONObject(strKey3);
                        pipelinePrAutoPO.setRepo(strKey3);
                        pipelinePrAutoPO.setPriority(onePro.getString("priority"));
                        pipelinePrAutoPO.setGroup_name(pipelinePrAutoPO.getDepartment_id());

                        if (onePro.getBoolean("isAutoTest") != null) {
                            if (onePro.getBoolean("isAutoTest")) {
                                pipelinePrAutoPO.setIsAutoOn(1);

                            } else {
                                pipelinePrAutoPO.setIsAutoOn(0);
                                //仓库自动化关闭，默认自动化数-1
                                pipelinePrAutoPO.setTotalCase(-1);
                            }
                        } else {
                            //仓库下未标记isAutoTest
                            pipelinePrAutoPO.setIsAutoOn(0);
                        }
                        if (pipelinePrAutoPO.getTimes() >0) {
                            //执行PR的存库
                            pipelinePrAutoPO.setAuto_date(yesterday);
                            pipelinePrMapper.insertRepo(pipelinePrAutoPO);
                        }
                          pipelinePrAutoArry.add(pipelinePrAutoPO);
                    }

                } else {//遍历组织下所有仓库
                    PipelinePrAutoPO pipelinePrAutoPO = new PipelinePrAutoPO();
                    pipelinePrAutoPO.setDepartment_id(data.getInteger("direction_id"));
                    pipelinePrAutoPO.setDirectionName(data.getString("label"));
                    //获取仓库pr次数
                    PipelinePrAutoPO getPRTimes=getPRTimes(strKey2, date);
                    pipelinePrAutoPO.setTimes(getPRTimes.getTimes());
                    pipelinePrAutoPO.setAuto_times(getPRTimes.getAuto_times());
                    pipelinePrAutoPO.setAuto_success_times(getPRTimes.getAuto_success_times());
                    pipelinePrAutoPO.setTotalCase(getPRTimes.getTotalCase());
                    pipelinePrAutoPO.setCoverage(getPRTimes.getCoverage());
                    JSONObject onePro = repos.getJSONObject(strKey2);
                    pipelinePrAutoPO.setRepo(strKey2);
                    pipelinePrAutoPO.setPriority(onePro.getString("priority"));
                    pipelinePrAutoPO.setGroup_name(pipelinePrAutoPO.getDepartment_id());

                    if (onePro.getBoolean("isAutoTest") != null) {

                        if (onePro.getBoolean("isAutoTest")) {
                            pipelinePrAutoPO.setIsAutoOn(1);
                        } else {
                            pipelinePrAutoPO.setIsAutoOn(0);
                        }
                    } else {
                        //仓库下未标记isAutoTest
                        pipelinePrAutoPO.setIsAutoOn(0);
                    }
                    if (pipelinePrAutoPO.getTimes() >0) {
                        //执行PR的存库
                        pipelinePrAutoPO.setAuto_date(yesterday);
                        pipelinePrMapper.insertRepo(pipelinePrAutoPO);
                    }
                    pipelinePrAutoArry.add(pipelinePrAutoPO);
                }
            }

        }
        return pipelinePrAutoArry;
    }


    public PipelinePrAutoPO getPRTimes(String repo,LocalDate today){
        if(repo=="ssh://git@git.sankuai.com/nib/consumer-process.git"){
            String test="";
        }


        PipelinePrAutoPO prBuild = new PipelinePrAutoPO();

        int prTimes = 0;
        int lastPRTag = 0;
        int auto_times=0;
        int auto_success_times=0;
        LocalDate yesterday = today.plusDays(-1);
        ZoneId zone = ZoneId.systemDefault();
        Date yesdayDate = Date.from(yesterday.atStartOfDay().atZone(zone).toInstant());
        Date todayDate = Date.from(today.atStartOfDay().atZone(zone).toInstant());
        String url = "http://qa.sankuai.com/data/pr/build/list?git_addr="+repo+"&startTime="+yesterday+"&endTime="+yesterday;
        JSONObject resp = HttpUtils.doGet(url, JSONObject.class, ImmutableMap.of("content-type", "application/json; charset=utf-8", "Cookie", ""));
        JSONArray repos = resp.getJSONArray("data");
        if (repos!=null){
            for (int i = 0; i < repos.size(); i++) {
                PipelinePrAutoPO prAutoBuild = new PipelinePrAutoPO();
                Date prTime = repos.getJSONObject(i).getDate("createdTime");
                if (prTime.before(todayDate) && prTime.after(yesdayDate)) {
                    prTimes++;
                    prAutoBuild = getAutoTimes(repos.getJSONObject(i).getString("traceId"));
                    auto_times+=prAutoBuild.getAuto_times();
                    auto_success_times+=prAutoBuild.getAuto_success_times();
                    if (lastPRTag==0&&prAutoBuild.getTotalCase()!=null&&prAutoBuild.getTotalCase()>0){
                        //覆盖率默认一天最后一次覆盖率和总数
                        prBuild.setCoverage(prAutoBuild.getCoverage());
                        prBuild.setTotalCase(prAutoBuild.getTotalCase());
                        lastPRTag++;
                    }
                }

            }
        }
        prBuild.setTimes(prTimes);
        prBuild.setAuto_times(auto_times);
        prBuild.setAuto_success_times(auto_success_times);


        return prBuild;
    }

    public PipelinePrAutoPO getAutoTimes(String tracerId){
        PipelinePrAutoPO prBuild = new PipelinePrAutoPO();
        int autoPrTimes = 0;
        int autoSuccessPrTimes = 0;
        String url = "http://qa.sankuai.com/data/pr/build/result?tracerId="+tracerId;
        JSONObject resp = HttpUtils.doGet(url, JSONObject.class, ImmutableMap.of("content-type", "application/json; charset=utf-8", "Cookie", ""));
        JSONObject prInfo = resp.getJSONObject("data");
        if (prInfo!=null&&prInfo.getJSONObject("stageResultMap").size()!=0&&prInfo.getJSONObject("stageResultMap").getJSONObject("自动化测试")!=null){//todo
                boolean autoSuccessTag = "SUCCESS".equals(prInfo.getJSONObject("stageResultMap").getJSONObject("自动化测试").getString("pipelineStatusEnum"));
                boolean autoFailedTag = "FAILED".equals(prInfo.getJSONObject("stageResultMap").getJSONObject("自动化测试").getString("pipelineStatusEnum"));
            String cov="";
                if (autoSuccessTag==true) {
                    autoPrTimes++;
                    autoSuccessPrTimes++;
                        int passedNum = prInfo.getJSONObject("stageResultMap").getJSONObject("自动化测试").getJSONArray("autoTestResults").getJSONObject(0).getInteger("passedNum");;
                        prBuild.setTotalCase(passedNum);
                        if (prInfo.getJSONObject("stageResultMap").getJSONObject("自动化测试覆盖率")!=null&&prInfo.getJSONObject("stageResultMap").getJSONObject("自动化测试覆盖率").getJSONArray("jacocoLiveReports").size()!=0) {
                            cov = prInfo.getJSONObject("stageResultMap").getJSONObject("自动化测试覆盖率").getJSONArray("jacocoLiveReports").getJSONObject(0).getString("lineCoveragePercentStr");
                        }
                        prBuild.setCoverage(cov);
                }else if (autoFailedTag==true){
                    autoPrTimes++;
                    int passedNum = prInfo.getJSONObject("stageResultMap").getJSONObject("自动化测试").getJSONArray("autoTestResults").getJSONObject(0).getInteger("passedNum");
                    int failedNum = prInfo.getJSONObject("stageResultMap").getJSONObject("自动化测试").getJSONArray("autoTestResults").getJSONObject(0).getInteger("failedNum");
                    prBuild.setTotalCase(passedNum+failedNum);//自动化总数
                    if (prInfo.getJSONObject("stageResultMap").getJSONObject("自动化测试覆盖率")!=null&&prInfo.getJSONObject("stageResultMap").getJSONObject("自动化测试覆盖率").getJSONArray("jacocoLiveReports").size()!=0) {
                        cov = prInfo.getJSONObject("stageResultMap").getJSONObject("自动化测试覆盖率").getJSONArray("jacocoLiveReports").getJSONObject(0).getString("lineCoveragePercentStr");
                    }
                        prBuild.setCoverage(cov);

                }

        }
        prBuild.setAuto_times(autoPrTimes);
        prBuild.setAuto_success_times(autoSuccessPrTimes);
        return prBuild;
    }


    public int UpdateDirectionMis(String repo,String mis)
    {
        PipelinePrAutoPO misPo = new PipelinePrAutoPO();
        misPo.setRepo(repo);
        misPo.setMis(mis);
        return pipelinePrMapper.updateDirectionMis(misPo);
    }


}
