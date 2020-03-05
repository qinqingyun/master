package com.meituan.food.extract.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import com.meituan.food.extract.IOneDayTpPipelineExtract;
import com.meituan.food.mapper.PipelineTpMapper;
import com.meituan.food.po.PipelinePrPO;
import com.meituan.food.po.PipelineTpPO;
import com.meituan.food.utils.HttpUtils;
import com.meituan.food.utils.SsoUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.ArrayList;


@Slf4j
@Component
public class TpPipelineExtracter  implements IOneDayTpPipelineExtract{
    @Resource
    public PipelineTpMapper pipelineTpMapper;

    ArrayList<String> skipDeatil = new ArrayList<>();
    ArrayList<String> skipReason = new ArrayList<>();
    String hotfixDeatilStr="";
    String skipDeatilStr="";
    String skipReasonStr="";
    //组织参数参考wiki https://km.sankuai.com/page/201266445
    String[] directions = new String[]{"260", "262","264","261","265","253","254","255","296","321","251","252","256","258","257","259","241","217"};
    Integer sum=0 ;
    Integer pass = 0;
    Integer failed = 0;
    Integer  oneTimePassCount =0;
    Integer  autoRunCountNumberList = 0;
    PipelineTpPO pipelineTpPO = new PipelineTpPO();

    @Override
    public void UpdateTpPipelineData(LocalDate date) {
        String url = "http://qa.sankuai.com/cq/cq/pipeline/data/portal-union-by-direction";
        String param = "{\"start\":\"2019-07-26\",\"end\":\"2019-11-04\",\"typeList\":[\"total\"]}";
        JSONObject resp = HttpUtils.doPost(url, param, JSONObject.class, ImmutableMap.of("content-type", "application/json; charset=utf-8", "Cookie", "com.sankuai.it.ead.citadel_ssoid=" + SsoUtils.getSsoId()));
        for (int i = 0 ; i<directions.length; i++){
            JSONArray dirAll = resp.getJSONObject("data").getJSONObject("direciton-relationship").getJSONArray("241");//String.valueOf((directions[i]))
            JSONArray dirTDs = removeST(dirAll);
            dirTDs.size();//提测task次数
            PipelineTpPO rejectResult = getReason(dirTDs);
            for(int k = 0 ;k<dirTDs.size();k++){
                JSONObject detail = resp.getJSONObject("data").getJSONObject("detail").getJSONObject("issue").getJSONObject((String) dirTDs.get(k));
                sum = sum + detail.getInteger("sum");
                pass = pass + detail.getInteger("pass");
                failed = failed + detail.getInteger("failed");
                oneTimePassCount =oneTimePassCount + detail.getInteger("oneTimePassCount");
                autoRunCountNumberList = autoRunCountNumberList + (Integer) detail.getJSONArray("lastUseCountNumberList").get(6);
            }
            pipelineTpPO.setDirection_id(Integer.valueOf(directions[i]));
//            pipelineTpPO.setDirection_name(dir);
            pipelineTpPO.setTask(dirTDs.size());
            pipelineTpPO.setSum(sum);
            pipelineTpPO.setPass(pass);
            pipelineTpPO.setFailed(failed);
            pipelineTpPO.setOneTimePassCount(oneTimePassCount);
            pipelineTpPO.setAutoRunCountNumberList(autoRunCountNumberList);
            pipelineTpPO.setRejectReasonString(rejectResult.getRejectReasons().toString());
            pipelineTpPO.setRejectDescString(rejectResult.getRejectDesc().toString());
            pipelineTpMapper.insert(pipelineTpPO);

        }

//        PipelinePrPO pipelinePrPO = new PipelinePrPO();
//        pipelinePrMapper.deleteByDate(date);
//        for(String strKey:resp.getJSONObject("data").keySet())
//        {
//            JSONObject data = resp.getJSONObject("data").getJSONObject(strKey);
//            pipelinePrPO.setDirection_id(data.getInteger("direction_id"));
//            pipelinePrPO.setDirectionName(data.getString("label"));
//            pipelinePrPO.setHotfix(data.getJSONArray("hotfix").size());
//            //hotfix详情
//            hotfixDeatilStr = getHotfixDetail(data.getJSONArray("hotfix"));
//            pipelinePrPO.setHotfixDetailStr(hotfixDeatilStr);
//            pipelinePrPO.setSkip(data.getJSONArray("skip").size());
//            //skip跳过详情
//            skipDeatil = getSkipDetail(data.getJSONArray("skip"));
//            pipelinePrPO.setSkipDetailStr(skipDeatil.toString());
//            //skip跳过原因
//            skipReasonStr = getSkipReason(skipDeatil);
//            pipelinePrPO.setSkipReasonStr(skipReasonStr);
//            pipelinePrPO.setCreateTime(date);
//            pipelinePrMapper.insert(pipelinePrPO);

//            }
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
        String url = "http://qa.sankuai.com/cq/cq/pipeline/data/detail ";
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
    public JSONArray removeST(JSONArray dirs){
        for(int j =0;j<dirs.size();j++){
            if(dirs.get(j).toString().contains("st")){
                dirs.remove(j);
            }
        }
        return dirs;

    }
    public PipelineTpPO getReason(JSONArray issueKey){
        String param="";
//        PipelineTpPO pipelineRejectPO = new PipelineTpPO();
        JSONObject resp = new JSONObject();
        String url = "http://qa.sankuai.com/cq/cq/pipeline/data/detail";
        for(int j = 0 ;j<issueKey.size();j++){
            param = "{\"start\":\"2019-07-26\",\"end\":\"2019-11-04\",\"typeList\":[\"total\"],\"issueKey\":\""+issueKey.get(j)+"\"}";
            resp = HttpUtils.doPost(url, param, JSONObject.class, ImmutableMap.of("content-type", "application/json; charset=utf-8", "Cookie", "com.sankuai.it.ead.citadel_ssoid=" + SsoUtils.getSsoId()));
            String a = "";
            JSONArray rejects = resp.getJSONObject("data").getJSONArray("rejectInfo");
            for(int k=0;k<rejects.size();k++){
                JSONObject rejectInfo = resp.getJSONObject("data").getJSONArray("rejectInfo").getJSONObject(k);
                String rejectStage = rejectInfo.getString("stage");
                if(!rejectInfo.getString("description").equals("")){
                    pipelineTpPO.addRejectDesc(rejectInfo.getString("description"));
                }
                pipelineTpPO.addRejectReasons(rejectInfo.getString("reason"));

                if(rejectInfo.getBoolean("valid")){
                    switch(rejectStage){
                        case "分支规范检查":
                            pipelineTpPO.setBranchCheckOK(pipelineTpPO.getBranchCheckOK()+1);
                            break;
                        case "测试环境初始化":
                            pipelineTpPO.setEnviromentcheckOK(pipelineTpPO.getEnviromentcheckOK()+1);
                            break;
                        case "代码冲突检查":
                            pipelineTpPO.setCodeCheckOK(pipelineTpPO.getCodeCheckOK()+1);
                            break;
                        case "静态代码检查":
                            pipelineTpPO.setSonarCheckOK(pipelineTpPO.getSonarCheckOK()+1);
                            break;
                        case "单元测试":
                            pipelineTpPO.setUnitCheckOk(pipelineTpPO.getUnitCheckOk()+1);
                            break;
                        case "部署":
                            pipelineTpPO.setBuildCheckOK(pipelineTpPO.getBuildCheckOK()+1);
                            break;
                        case "自动化测试":
                            pipelineTpPO.setAutoCheckOK(pipelineTpPO.getAutoCheckOK()+1);
                            break;
                            default:
                                pipelineTpPO.setOtherOK(pipelineTpPO.getOtherOK()+1);
                    }
                }else {
                    switch(rejectStage){
                        case "分支规范检查":
                            pipelineTpPO.setBranchCheckNO(pipelineTpPO.getBranchCheckNO()+1);
                            break;
                        case "测试环境初始化":
                            pipelineTpPO.setEnviromentcheckNO(pipelineTpPO.getEnviromentcheckNO()+1);
                            break;
                        case "代码冲突检查":
                            pipelineTpPO.setCodeCheckNO(pipelineTpPO.getCodeCheckNO()+1);
                            break;
                        case "静态代码检查":
                            pipelineTpPO.setSonarCheckNO(pipelineTpPO.getSonarCheckNO()+1);
                            break;
                        case "单元测试":
                            pipelineTpPO.setUnitCheckNO(pipelineTpPO.getUnitCheckNO()+1);
                            break;
                        case "部署":
                            pipelineTpPO.setBuildCheckNO(pipelineTpPO.getBuildCheckNO()+1);
                            break;
                        case "自动化测试":
                            pipelineTpPO.setAutoCheckNO(pipelineTpPO.getAutoCheckNO()+1);
                            break;
                        default:
                            pipelineTpPO.setOtherNO(pipelineTpPO.getOtherNO()+1);

                    }
                }
            }

        }
        return pipelineTpPO;
    }
}
