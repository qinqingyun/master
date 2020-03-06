package com.meituan.food.extract.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import com.meituan.food.extract.IOneDayTpPipelineExtract;
import com.meituan.food.mapper.PipelineTpMapper;
import com.meituan.food.po.PipelineTpPO;
import com.meituan.food.utils.HttpUtils;
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


    @Override
    public void UpdateTpPipelineData(LocalDate date) {
        String url = "http://qa.sankuai.com/cq/cq/pipeline/data/portal-union-by-direction";
        String param = "{\"start\":\""+date+"\",\"end\":\""+date+"\",\"typeList\":[\"total\"]}";
        JSONObject resp = HttpUtils.doPost(url, param, JSONObject.class, ImmutableMap.of("content-type", "application/json; charset=utf-8", "Cookie", ""));
        pipelineTpMapper.deleteByDate(date);
        for (int i = 0 ; i<directions.length; i++){
            JSONArray dirTDs=null;
            JSONArray dirAll = resp.getJSONObject("data").getJSONObject("direciton-relationship").getJSONArray(String.valueOf((directions[i])));
            if(dirAll!=null){
                dirTDs = removeST(dirAll);
            }
            if(dirTDs!=null) {
                for (int k = 0; k < dirTDs.size(); k++) {
                    JSONObject detail = resp.getJSONObject("data").getJSONObject("detail").getJSONObject("issue").getJSONObject((String) dirTDs.get(k));
                    if (detail != null) {
                        sum = sum + detail.getInteger("sum");
                        pass = pass + detail.getInteger("pass");
                        failed = failed + detail.getInteger("failed");
                        oneTimePassCount = oneTimePassCount + detail.getInteger("oneTimePassCount");
                        autoRunCountNumberList = autoRunCountNumberList + (Integer) detail.getJSONArray("lastUseCountNumberList").get(6);
                    }
                }
                PipelineTpPO pipelineTpPO = new PipelineTpPO();
                pipelineTpPO.setDirection_id(Integer.valueOf(directions[i]));
                pipelineTpPO.setTask(dirTDs.size());
                pipelineTpPO.setSum(sum);
                pipelineTpPO.setPass(pass);
                pipelineTpPO.setFailed(failed);
                pipelineTpPO.setOneTimePassCount(oneTimePassCount);
                pipelineTpPO.setAutoRunCountNumberList(autoRunCountNumberList);
                PipelineTpPO rejectResult = getReason(dirTDs, date);
                pipelineTpPO.setRejectReasonString(rejectResult.getRejectReasons().toString());
                String descSlip = org.apache.commons.lang.StringUtils.join(rejectResult.getRejectDesc(), "###");
                pipelineTpPO.setRejectDescString(descSlip);
                pipelineTpPO.setTp_date(date);
                pipelineTpMapper.insert(pipelineTpPO);
            }
        }
    }

    public JSONArray removeST(JSONArray dirs){
        for(int j =0;j<dirs.size();j++){
            if(dirs.get(j).toString().contains("st")){
                dirs.remove(j);
            }
        }
        return dirs;
    }

    public PipelineTpPO getReason(JSONArray issueKey,LocalDate date){
        PipelineTpPO pipelineTpPO=new PipelineTpPO();
        String param="";
        JSONObject resp = new JSONObject();
        String url = "http://qa.sankuai.com/cq/cq/pipeline/data/detail";
        for(int j = 0 ;j<issueKey.size();j++){
            param = "{\"start\":\""+date+"\",\"end\":\""+date+"\",\"typeList\":[\"total\"],\"issueKey\":\""+issueKey.get(j)+"\"}";
            resp = HttpUtils.doPost(url, param, JSONObject.class, ImmutableMap.of("content-type", "application/json; charset=utf-8", "Cookie", ""));
            JSONArray rejects = resp.getJSONObject("data").getJSONArray("rejectInfo");
            for(int k=0;k<rejects.size();k++) {
                    JSONObject rejectInfo = rejects.getJSONObject(k);
                    String rejectStage = rejectInfo.getString("stage");
                    if (!rejectInfo.getString("description").equals("")) {
                        pipelineTpPO.addRejectDesc(rejectInfo.getString("description"));
                    }
                    pipelineTpPO.addRejectReasons(rejectInfo.getString("reason"));

                    if (rejectInfo.getBoolean("valid")) {
                        switch (rejectStage) {
                            case "分支规范检查":
                                pipelineTpPO.setBranchCheckOK(pipelineTpPO.getBranchCheckOK() + 1);
                                break;
                            case "测试环境初始化":
                                pipelineTpPO.setEnviromentcheckOK(pipelineTpPO.getEnviromentcheckOK() + 1);
                                break;
                            case "代码冲突检查":
                                pipelineTpPO.setCodeCheckOK(pipelineTpPO.getCodeCheckOK() + 1);
                                break;
                            case "静态代码检查":
                                pipelineTpPO.setSonarCheckOK(pipelineTpPO.getSonarCheckOK() + 1);
                                break;
                            case "单元测试":
                                pipelineTpPO.setUnitCheckOk(pipelineTpPO.getUnitCheckOk() + 1);
                                break;
                            case "部署":
                                pipelineTpPO.setBuildCheckOK(pipelineTpPO.getBuildCheckOK() + 1);
                                break;
                            case "自动化测试":
                                pipelineTpPO.setAutoCheckOK(pipelineTpPO.getAutoCheckOK() + 1);
                                break;
                            default:
                                pipelineTpPO.setOtherOK(pipelineTpPO.getOtherOK() + 1);
                        }
                    } else {
                        switch (rejectStage) {
                            case "分支规范检查":
                                pipelineTpPO.setBranchCheckNO(pipelineTpPO.getBranchCheckNO() + 1);
                                break;
                            case "测试环境初始化":
                                pipelineTpPO.setEnviromentcheckNO(pipelineTpPO.getEnviromentcheckNO() + 1);
                                break;
                            case "代码冲突检查":
                                pipelineTpPO.setCodeCheckNO(pipelineTpPO.getCodeCheckNO() + 1);
                                break;
                            case "静态代码检查":
                                pipelineTpPO.setSonarCheckNO(pipelineTpPO.getSonarCheckNO() + 1);
                                break;
                            case "单元测试":
                                pipelineTpPO.setUnitCheckNO(pipelineTpPO.getUnitCheckNO() + 1);
                                break;
                            case "部署":
                                pipelineTpPO.setBuildCheckNO(pipelineTpPO.getBuildCheckNO() + 1);
                                break;
                            case "自动化测试":
                                pipelineTpPO.setAutoCheckNO(pipelineTpPO.getAutoCheckNO() + 1);
                                break;
                            default:
                                pipelineTpPO.setOtherNO(pipelineTpPO.getOtherNO() + 1);

                        }
                    }
                }

        }
        return pipelineTpPO;
    }
}
