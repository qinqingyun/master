package com.meituan.food.extract.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import com.meituan.food.extract.IOneWeekEightDataExtract;
import com.meituan.food.mapper.ImportantProjectReviewPOMapper;
import com.meituan.food.po.ImportantProjectReviewPO;
import com.meituan.food.utils.DaXiangUtils;
import com.meituan.food.utils.HttpUtils;
import com.meituan.food.utils.SsoUtils;
import com.meituan.food.utils.UrlUtils;
import org.apache.tools.ant.taskdefs.Java;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/*
* 重点项目review wiki的定时任务
* */

@Component
public class ImportantProjectReviewExtracter implements IOneWeekEightDataExtract {

    @Resource
    private ImportantProjectReviewPOMapper importantProjectReviewPOMapper;

    private static String body="<ul><li><p>发现问题模型：<a target=\"_blank\" rel=\"noopener\" class=\"ct-link\" href=\"https://km.sankuai.com/page/68879971\" title=\"03项目管理发现问题的模型\" id=\"68879971\" pageid=\"68879971\" data-pageid=\"68879971\">03项目管理发现问题的模型</a></p></li></ul><ul><li><p>快速Check项目的10个点：<a target=\"_blank\" rel=\"noopener\" class=\"ct-link\" href=\"https://km.sankuai.com/page/116572478\" title=\"项目管理核心点CheckList\" id=\"116572478\" pageid=\"116572478\" data-pageid=\"116572478\">项目管理核心点CheckList</a></p></li><li><p>健康等级说明：<a target=\"_blank\" rel=\"noopener\" class=\"ct-link\" href=\"https://km.sankuai.com/page/117355269\" title=\"健康度等级说明\" id=\"117355269\" pageid=\"117355269\" data-pageid=\"117355269\">健康度等级说明</a></p></li><li><p>PRD/技术方案设计中，需要增加关键check节点的表格，<a target=\"_blank\" rel=\"noopener\" class=\"ct-link\" href=\"https://km.sankuai.com/page/118436153\" title=\"00.关键check项表格\" id=\"118436153\" pageid=\"118436153\" data-pageid=\"118436153\">00.关键check项表格</a></p></li><li><p>项目阶段：需求阶段，开发阶段，测试阶段，上线阶段，上线后  -- 选择项目当前阶段</p></li></ul><p style=\"text-align: start;\"><span style=\"color: rgb(0, 0, 0);\">1）每周的top5 耗时的项目，做详细的项目管理review</span></p><p style=\"text-align: start;\"><span style=\"color: rgb(0, 0, 0);\">2）没有被详细review的项目，起点为良</span></p><p style=\"text-align: start;\"><span style=\"color: rgb(0, 0, 0);\">3）有问题的项目，项目review级别从 【良】 开始是降级： 【及格】-【不及格】-【差】</span></p><p style=\"text-align: start;\"><span style=\"color: rgb(0, 0, 0);\">4）QA团队内部强调主R不能多个，如果再次出现主R不清晰，QA Leader背责</span></p><h2 id=\"id-TOP5\" style=\"text-align: start;\"><span style=\"color: rgb(0, 0, 0);\">TOP5</span></h2>";
    private static final String tableFirstLine="<table><tbody><tr><th data-colwidth=\"52\" width=\"52\"  style=\"background-color: rgb(246, 246, 246);\"><p><strong>序号</strong></p></th><th data-colwidth=\"120\" width=\"120\" style=\"background-color: rgb(246, 246, 246);\"><p>项目健康等级（<span style=\"color: rgb(245, 34, 45);\">点击上下箭头可以排序查看</span>）</p></th><th data-colwidth=\"253\" width=\"253\" style=\"background-color: rgb(246, 246, 246);\"><p>项目名称</p></th><th data-colwidth=\"256\" width=\"256\" style=\"background-color: rgb(246, 246, 246);\"><p>产品需求：PRD</p><p>技术需求：设计方案</p></th><th data-colwidth=\"124\" width=\"124\" style=\"background-color: rgb(246, 246, 246);\"><p>项目当前阶段</p></th><th data-colwidth=\"172\" width=\"172\" style=\"background-color: rgb(246, 246, 246);\"><p>问题分类-子类</p></th><th data-colwidth=\"196\" width=\"196\" style=\"background-color: rgb(246, 246, 246);\"><p>问题描述</p></th><th data-colwidth=\"108\" width=\"108\" style=\"background-color: rgb(246, 246, 246);\"><p>问题责任人</p></th><th data-colwidth=\"96\" width=\"96\" style=\"background-color: rgb(246, 246, 246);\"><p>问题责任人Leader</p></th><th data-colwidth=\"108\" width=\"108\" style=\"background-color: rgb(246, 246, 246);\"><p>所属业务线</p></th><th data-colwidth=\"208\" width=\\\"208\" style=\"background-color: rgb(246, 246, 246);\"><p>项目主R人</p></th></tr>";
    private static final String content_url = "https://km.sankuai.com/api/pages/contentId";
    private static final String url = "https://km.sankuai.com/api/pages/8045";

    @Override
    public void extractData4Week(LocalDate firstDay, LocalDate lastDay) throws UnsupportedEncodingException {
        String firstDayStr = firstDay.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String lastDayStr = lastDay.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String kmTitle="到餐项目Review（"+firstDayStr+"-"+lastDayStr+"）";

        String yunTuUrl="https://yuntu.sankuai.com/api/widget/widget-987b89cc-0f18-409b-b287-367acc6de404/data?params=";

        JSONObject params=new JSONObject();
        params.put("endDate",lastDayStr);
        params.put("startDate",firstDayStr);
        params.put("orgId","104638");
        String encodeParam= UrlUtils.encode(params.toJSONString());
        String mSsoid=SsoUtils.getSsoId();

        JSONObject response=HttpUtils.doGet(yunTuUrl+encodeParam+"&index=1&useCache=true",JSONObject.class,ImmutableMap.of("Cookie", "Metrics_ssoid=" + mSsoid));
        JSONArray result=response.getJSONObject("data").getJSONObject("resData").getJSONArray("data");

        body=body+tableFirstLine;

        if (result.size()<=6){
            for (int i=1;i<result.size();i++){
                String allProject=((JSONArray)(result.get(i))).getString(0);
                String projectLink = allProject.substring(allProject.indexOf("href=") + 5, allProject.lastIndexOf("}")+1);
                String unencodedParam=projectLink.substring(projectLink.indexOf("{")+1,projectLink.indexOf("}"));
                String projectName=allProject.substring(allProject.indexOf("}>") + 2, allProject.lastIndexOf("</a>"));
                String s=URLEncoder.encode(unencodedParam,"UTF-8");
                String finalPartLink=projectLink.substring(0,projectLink.indexOf("orgId")-1)+s+"}";
                String newLine="<tr><td data-colwidth=\"52\" width=\"52\" contenteditable=\"false\" class=\"num-cell\"><p></p></td><td data-colwidth=\"120\" width=\"120\"><p style=\"text-align: start;\"></p></td><td data-colwidth=\"253\" width=\"253\"><p><a target=\"_blank\" rel=\"noopener\" class=\"ct-link\" href=\""
                        +finalPartLink+"\" title=\""+projectName+"\" id=\"\"><u>"+projectName
                        +"</u></a></p></td><td data-colwidth=\"256\" width=\"256\\\"><p></p></td><td data-colwidth=\"124\" width=\"124\"><p></p></td><td data-colwidth=\"172\" width=\"172\"><p></p></td><td data-colwidth=\"196\" width=\"196\"><p></p></td><td data-colwidth=\"108\" width=\"108\"><p></p></td><td data-colwidth=\"96\" width=\"96\"><p></p></td><td data-colwidth=\"108\" width=\"108\"><p></p></td><td data-colwidth=\"208\" width=\"208\"><p></p></td></tr>";

                body=body+newLine;
            }
            body=body+"</tbody></table>";
        }else {
            for (int i=1;i<=5;i++){
                String allProject=((JSONArray)(result.get(i))).getString(0);
                String projectLink = allProject.substring(allProject.indexOf("href=") + 5, allProject.lastIndexOf("}")+1);
                String unencodedParam=projectLink.substring(projectLink.indexOf("{")+1,projectLink.indexOf("}"));
                String projectName=allProject.substring(allProject.indexOf("}>") + 2, allProject.lastIndexOf("</a>"));
                String s=URLEncoder.encode(unencodedParam,"UTF-8");
                String finalPartLink=projectLink.substring(0,projectLink.indexOf("orgId")-1)+s+"}";
                String newLine="<tr><td data-colwidth=\"52\" width=\"52\" contenteditable=\"false\" class=\"num-cell\"><p></p></td><td data-colwidth=\"120\" width=\"120\"><p style=\"text-align: start;\"></p></td><td data-colwidth=\"253\" width=\"253\"><p><a target=\"_blank\" rel=\"noopener\" class=\"ct-link\" href=\""
                        +finalPartLink+"\" title=\""+projectName+"\" id=\"\"><u>"+projectName
                        +"</u></a></p></td><td data-colwidth=\"256\" width=\"256\\\"><p></p></td><td data-colwidth=\"124\" width=\"124\"><p></p></td><td data-colwidth=\"172\" width=\"172\"><p></p></td><td data-colwidth=\"196\" width=\"196\"><p></p></td><td data-colwidth=\"108\" width=\"108\"><p></p></td><td data-colwidth=\"96\" width=\"96\"><p></p></td><td data-colwidth=\"108\" width=\"108\"><p></p></td><td data-colwidth=\"208\" width=\"208\"><p></p></td></tr>";

                body=body+newLine;
            }
            body=body+"</tbody></table>";

        }

        Map<String,String> orgMap=new HashMap();
        orgMap.put("C端客户端","106453");
        orgMap.put("C端服务端","106452");
        orgMap.put("B端商家端","106454");
        orgMap.put("M端CRM","106457");
        orgMap.put("M端供应链","106455");
        orgMap.put("财务结算","106456");

        for (String org : orgMap.keySet()) {

            body=body
                    +"<h2 id=\"id-"+org+"\" style=\"text-align: start;\"><strong>"+org+"</strong></h2>"
                    +tableFirstLine;

            String orgIds=orgMap.get(org);

            JSONObject param3=new JSONObject();
            param3.put("endDate",lastDayStr);
            param3.put("startDate",firstDayStr);
            param3.put("orgId",orgIds);
            String encodeParam3= UrlUtils.encode(param3.toJSONString());

            JSONObject resp=HttpUtils.doGet(yunTuUrl+encodeParam3+"&index=1&useCache=true",JSONObject.class,ImmutableMap.of("Cookie", "Metrics_ssoid=" + mSsoid));
            int count=resp.getJSONObject("data").getJSONObject("resData").getInteger("indexCounts");

            for(int j=1;j<=count;j++){
                JSONObject respIndex=HttpUtils.doGet(yunTuUrl+encodeParam3+"&index="+j+"&useCache=true",JSONObject.class,ImmutableMap.of("Cookie", "Metrics_ssoid=" + mSsoid));
                JSONArray resultIndex=respIndex.getJSONObject("data").getJSONObject("resData").getJSONArray("data");
                if(resultIndex.size() > 1) {
                    for (int i = 1; i < resultIndex.size(); i++) {
                        String allProject = ((JSONArray) (resultIndex.get(i))).getString(0);
                        if (!allProject.equals("")) {
                            String projectLink = allProject.substring(allProject.indexOf("href=") + 5, allProject.lastIndexOf("}") + 1);
                            String unencodedParam = projectLink.substring(projectLink.indexOf("{") + 1, projectLink.indexOf("}"));
                            String projectName = allProject.substring(allProject.indexOf("}>") + 2, allProject.lastIndexOf("</a>"));
                            String s = URLEncoder.encode(unencodedParam, "UTF-8");
                            String finalPartLink = projectLink.substring(0, projectLink.indexOf("orgId") - 1) + s + "}";
                            String newLine = "<tr><td data-colwidth=\"52\" width=\"52\" contenteditable=\"false\" class=\"num-cell\"><p></p></td><td data-colwidth=\"120\" width=\"120\"><p style=\"text-align: start;\"></p></td><td data-colwidth=\"253\" width=\"253\"><p><a target=\"_blank\" rel=\"noopener\" class=\"ct-link\" href=\""
                                    + finalPartLink + "\" title=\"" + projectName + "\" id=\"\"><u>" + projectName
                                    + "</u></a></p></td><td data-colwidth=\"256\" width=\"256\\\"><p></p></td><td data-colwidth=\"124\" width=\"124\"><p></p></td><td data-colwidth=\"172\" width=\"172\"><p></p></td><td data-colwidth=\"196\" width=\"196\"><p></p></td><td data-colwidth=\"108\" width=\"108\"><p></p></td><td data-colwidth=\"96\" width=\"96\"><p></p></td><td data-colwidth=\"108\" width=\"108\"><p></p></td><td data-colwidth=\"208\" width=\"208\"><p></p></td></tr>";

                            body = body + newLine;
                        }
                    }
                }
            }
            body=body+"</tbody></table>";
        }

        JSONObject jsonObject = HttpUtils.doGet(content_url, JSONObject.class, ImmutableMap.of("Cookie", "com.sankuai.it.ead.citadel_ssoid=" + mSsoid));
        Long contentId = jsonObject.getLong("data");

        JSONObject param = new JSONObject();
        param.put("linkedContent", "");

        JSONObject params2 = new JSONObject();
        params2.put("title", kmTitle);
        params2.put("body", body);
        //大组目录
        params2.put("parentId", "122541012");
        // 测试地址
       // params2.put("parentId", "119029743");
        params2.put("bodyText", "测试内容" + contentId);
        params2.put("contentId", contentId);
        params2.put("whatUpdate", "");

        JSONObject resp = HttpUtils.doPost(url, params2.toJSONString(), JSONObject.class, ImmutableMap.of("content-type", "application/json; charset=utf-8", "Cookie", "com.sankuai.it.ead.citadel_ssoid=" + mSsoid));

        String kmLink="https://km.sankuai.com/page/"+contentId;
        System.out.println(kmLink);
        ImportantProjectReviewPO importantProjectReviewPO=new ImportantProjectReviewPO();
        importantProjectReviewPO.setContentId(contentId);
        importantProjectReviewPO.setKmLink(kmLink);
        importantProjectReviewPO.setStartDate(firstDayStr);
        importantProjectReviewPO.setEndDate(lastDayStr);
        Date now =new Date();
        importantProjectReviewPO.setCreatedAt(now);
        importantProjectReviewPO.setUpdatedAt(now);

        importantProjectReviewPOMapper.insert(importantProjectReviewPO);
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        IOneWeekEightDataExtract s=new ImportantProjectReviewExtracter();
        LocalDate firstDay=LocalDate.now().minusDays(8);
        LocalDate lastDay=LocalDate.now().minusDays(1);
        s.extractData4Week(firstDay,lastDay);
    }
}
