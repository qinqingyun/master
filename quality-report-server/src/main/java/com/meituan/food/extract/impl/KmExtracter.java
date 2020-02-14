package com.meituan.food.extract.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.meituan.food.extract.IOneWeekDataExtract;
import com.meituan.food.mapper.*;
import com.meituan.food.po.ApiDetailPO;
import com.meituan.food.po.AppkeyAdminPO;
import com.meituan.food.po.AppkeyListPO;
import com.meituan.food.utils.DaXiangUtils;
import com.meituan.food.utils.HttpUtils;
import com.meituan.food.utils.SsoUtils;
import com.meituan.food.web.vo.ApiCoverageStatusVO;
import com.meituan.food.web.vo.AppkeyVO;
import com.meituan.food.web.vo.CommonResponse;
import org.junit.Test;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class KmExtracter implements IOneWeekDataExtract {

    @Resource
    private AppkeyAdminPOMapper appkeyAdminPOMapper;

    @Resource
    private AppkeyListPOMapper appkeyListPOMapper;

    @Resource
    private LineCoverageP0Mapper lineCoverageP0Mapper;


    private String tableHead = "<table data-bordercolor=\\\"\\\\&quot;#cccccc\\\\&quot;\\\"><tbody><tr><th data-colwidth=\\\"61\\\" width=\\\"61\\\" style=\\\"background-color: rgb(246, 246, 246);\\\"><p>负责QA</p></th><th data-colwidth=\\\"354\\\" width=\\\"354\\\" style=\\\"background-color: rgb(246, 246, 246);\\\"><p>appKey</p></th><th data-colwidth=\\\"694\\\" width=\\\"694\\\" style=\\\"background-color: rgb(246, 246, 246);\\\"><p>上一周覆盖率数据（取当天往前第7天的数据）</p></th><th data-colwidth=\\\"411\\\" width=\\\"411\\\" style=\\\"background-color: rgb(246, 246, 246);\\\"><p>本周覆盖率数据（取当天数据）</p></th><th data-colwidth=\\\"392\\\" width=\\\"392\\\" style=\\\"background-color: rgb(246, 246, 246);\\\"><p>本周主要工作</p></th><th data-colwidth=\\\"392\\\" width=\\\"392\\\" style=\\\"background-color: rgb(246, 246, 246);\\\"><p>备注</p></th></tr>";
    private String tableTail = "</tbody></table><p><br></p><p></p>";
    private String trHead = "<tr>";
    private String trTail = "</tr>";
    private String tdHead = "<td data-colwidth=\\\"694\\\" width=\\\"694\\\" style=\\\"background-color: rgb(255, 255, 255);\\\"><p>";
    private String tdTail = "</p></td>";
    private String emptyData = "<td data-colwidth=\\\"694\\\" width=\\\"694\\\" style=\\\"background-color: rgb(255, 255, 255);\\\"><p style=\\\"text-align: start;\\\"></p></td>";
    private static final String content_url = "https://km.sankuai.com/api/pages/contentId";
    private static final String url = "https://km.sankuai.com/api/pages/8045";

    @Override
    public void extractData4Week(LocalDate firstDay, LocalDate lastDay,String body) {

        String firstDayStr = firstDay.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String lastDayStr = lastDay.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        String kmTitle="交易组覆盖率统计周报（"+firstDayStr+"-"+lastDayStr+"）";


        JSONObject jsonObject = HttpUtils.doGet(content_url, JSONObject.class, ImmutableMap.of("Cookie", "com.sankuai.it.ead.citadel_ssoid=" + SsoUtils.getSsoId()));
        Long contentId = jsonObject.getLong("data");

        JSONObject param = new JSONObject();
        param.put("linkedContent", "");

        JSONObject params = new JSONObject();
        params.put("title", kmTitle);
        params.put("body",body);//combineBody
        params.put("parentId", "122541012");//122541012  258410650
        params.put("bodyText", "测试内容" + contentId);
        params.put("contentId", contentId);
        params.put("whatUpdate", "");

        JSONObject resp = HttpUtils.doPost(url, params.toJSONString(), JSONObject.class, ImmutableMap.of("content-type", "application/json; charset=utf-8", "Cookie", "com.sankuai.it.ead.citadel_ssoid=" + SsoUtils.getSsoId()));

        String kmLink="https://km.sankuai.com/page/"+contentId;
        System.out.println(kmLink);

        //   DaXiangUtils.pushToRoom(" @郭孟瑶 "+kmLink,64012858900L);

    }

    private String getCoverageData(String mis){

        String multiRows = "";
        List<String> appkeyList = appkeyAdminPOMapper.selectByMis(mis);
        if (appkeyList.size() != 0) {
            //找P1服务
            for (String appkey : appkeyList) {
                String oneRow = "";
                String srv = appkeyListPOMapper.selectOnlineP1ByAppKey(appkey);
                if( srv != null && !"".equals(srv)){
                    String yestedayCoverage = lineCoverageP0Mapper.selectYesterdayCoverageBySrv(srv);
                    if(yestedayCoverage == null || "".equals(yestedayCoverage))
                        yestedayCoverage = "-.-";

                    String last7dayCoverage = lineCoverageP0Mapper.selectLast7dayCoverageBySrv(srv);
                    if(last7dayCoverage == null || "".equals(last7dayCoverage))
                        last7dayCoverage = "-.-";

                    oneRow = tdHead + mis + tdTail +tdHead+ appkey+ tdTail
                            +tdHead+last7dayCoverage + tdTail
                            +tdHead+yestedayCoverage + tdTail + emptyData + emptyData;
                }
                if( !"".equals(oneRow))
                    multiRows = multiRows + trHead + oneRow + trTail;
            }
            //找P2服务
            for (String appkey : appkeyList) {
                String oneRow = "";
                String srv = appkeyListPOMapper.selectOnlineP2ByAppKey(appkey);
                if( srv != null && !"".equals(srv)){
                    String yestedayCoverage = lineCoverageP0Mapper.selectYesterdayCoverageBySrv(srv);
                    if(yestedayCoverage == null || "".equals(yestedayCoverage))
                        yestedayCoverage = "-.-";

                    String last7dayCoverage = lineCoverageP0Mapper.selectLast7dayCoverageBySrv(srv);
                    if(last7dayCoverage == null || "".equals(last7dayCoverage))
                        last7dayCoverage = "-.-";

                    oneRow = tdHead + mis + tdTail +tdHead+ appkey+ tdTail
                            +tdHead+last7dayCoverage + tdTail
                            +tdHead+yestedayCoverage + tdTail + emptyData + emptyData;
                }
                if( !"".equals(oneRow))
                    multiRows = multiRows + trHead + oneRow + trTail;
            }

        }
        return multiRows;

    }




    private String combineBody(){
        String body = "";
        body += tableHead;

        List<String> orderMisNames = ImmutableList.of("liyuhua", "sunnaili", "buyuqi");
        for(String mis:orderMisNames){
            body += getCoverageData(mis);
        }
        body += tableTail;
        return body;
    }

//    private static final String body="<table data-bordercolor=\\\"\\\\&quot;#cccccc\\\\&quot;\\\"><tbody><tr><th data-colwidth=\\\"61\\\" width=\\\"61\\\" style=\\\"background-color: rgb(246, 246, 246);\\\"><p>主R</p></th><th data-colwidth=\\\"354\\\" width=\\\"354\\\" style=\\\"background-color: rgb(246, 246, 246);\\\"><p>项目</p></th><th data-colwidth=\\\"694\\\" width=\\\"694\\\" style=\\\"background-color: rgb(246, 246, 246);\\\"><p>本周进展</p></th><th data-colwidth=\\\"411\\\" width=\\\"411\\\" style=\\\"background-color: rgb(246, 246, 246);\\\"><p>下周计划</p></th><th data-colwidth=\\\"392\\\" width=\\\"392\\\" style=\\\"background-color: rgb(246, 246, 246);\\\"><p>问题与思考</p></th></tr><tr><td data-colwidth=\\\"61\\\" width=\\\"61\\\" style=\\\"background-color: rgb(255, 255, 255);\\\"><p>王洋</p></td><td data-colwidth=\\\"354\\\" width=\\\"354\\\" style=\\\"background-color: rgb(255, 255, 255);\\\"><p>到餐全链路压测（执行+工具建设）</p></td><td data-colwidth=\\\"694\\\" width=\\\"694\\\" style=\\\"background-color: rgb(255, 255, 255);\\\"><p></p></td><td data-colwidth=\\\"411\\\" width=\\\"411\\\" style=\\\"background-color: rgb(255, 255, 255);\\\"><p></p></td><td data-colwidth=\\\"392\\\" width=\\\"392\\\" style=\\\"background-color: rgb(255, 255, 255);\\\"><p></p></td></tr><tr><td data-colwidth=\\\"61\\\" width=\\\"61\\\" style=\\\"background-color: rgb(255, 255, 255);\\\"><p>孟瑶</p></td><td data-colwidth=\\\"354\\\" width=\\\"354\\\" style=\\\"background-color: rgb(255, 255, 255);\\\"><p>report</p></td><td data-colwidth=\\\"694\\\" width=\\\"694\\\" style=\\\"background-color: rgb(255, 255, 255);\\\"><p></p></td><td data-colwidth=\\\"411\\\" width=\\\"411\\\" style=\\\"background-color: rgb(255, 255, 255);\\\"><p></p></td><td data-colwidth=\\\"392\\\" width=\\\"392\\\" style=\\\"background-color: rgb(255, 255, 255);\\\"><p></p></td></tr><tr><td data-colwidth=\\\"61\\\" width=\\\"61\\\" style=\\\"background-color: rgb(255, 255, 255);\\\"><p>启芳</p></td><td data-colwidth=\\\"354\\\" width=\\\"354\\\" style=\\\"background-color: rgb(255, 255, 255);\\\"><p>故障演练</p></td><td data-colwidth=\\\"694\\\" width=\\\"694\\\" style=\\\"background-color: rgb(255, 255, 255);\\\"><p></p></td><td data-colwidth=\\\"411\\\" width=\\\"411\\\" style=\\\"background-color: rgb(255, 255, 255);\\\"><p></p></td><td data-colwidth=\\\"392\\\" width=\\\"392\\\" style=\\\"background-color: rgb(255, 255, 255);\\\"><p></p></td></tr><tr><td data-colwidth=\\\"61\\\" width=\\\"61\\\" style=\\\"background-color: rgb(255, 255, 255);\\\"><p>杨帅</p></td><td data-colwidth=\\\"354\\\" width=\\\"354\\\" style=\\\"background-color: rgb(255, 255, 255);\\\"><p>客户端性能测试</p></td><td data-colwidth=\\\"694\\\" width=\\\"694\\\" style=\\\"background-color: rgb(255, 255, 255);\\\"><p></p></td><td data-colwidth=\\\"411\\\" width=\\\"411\\\" style=\\\"background-color: rgb(255, 255, 255);\\\"><p></p></td><td data-colwidth=\\\"392\\\" width=\\\"392\\\" style=\\\"background-color: rgb(255, 255, 255);\\\"><p></p></td></tr><tr><td data-colwidth=\\\"61\\\" width=\\\"61\\\" style=\\\"background-color: rgb(255, 255, 255);\\\"><p>伟豪</p></td><td data-colwidth=\\\"354\\\" width=\\\"354\\\" style=\\\"background-color: rgb(255, 255, 255);\\\"><p>数据构造平台</p></td><td data-colwidth=\\\"694\\\" width=\\\"694\\\" style=\\\"background-color: rgb(255, 255, 255);\\\"><p></p></td><td data-colwidth=\\\"411\\\" width=\\\"411\\\" style=\\\"background-color: rgb(255, 255, 255);\\\"><p></p></td><td data-colwidth=\\\"392\\\" width=\\\"392\\\" style=\\\"background-color: rgb(255, 255, 255);\\\"><p></p></td></tr><tr><td data-colwidth=\\\"61\\\" width=\\\"61\\\" style=\\\"background-color: rgb(255, 255, 255);\\\"><p>张波</p></td><td data-colwidth=\\\"354\\\" width=\\\"354\\\" style=\\\"background-color: rgb(255, 255, 255);\\\"><p>代码覆盖率平台</p></td><td data-colwidth=\\\"694\\\" width=\\\"694\\\" style=\\\"background-color: rgb(255, 255, 255);\\\"><p style=\\\"text-align: start;\\\"></p></td><td data-colwidth=\\\"411\\\" width=\\\"411\\\" style=\\\"background-color: rgb(255, 255, 255);\\\"><p style=\\\"text-align: start;\\\"></p></td><td data-colwidth=\\\"392\\\" width=\\\"392\\\" style=\\\"background-color: rgb(255, 255, 255);\\\"><p></p></td></tr></tbody></table><p><br></p><p></p>";
//    private static final String content_url = "https://km.sankuai.com/api/pages/contentId";
//    private static final String url = "https://km.sankuai.com/api/pages/8045";
//
//    @Override
//    public void extractData4Week(LocalDate firstDay, LocalDate lastDay) {
//
//        String firstDayStr = firstDay.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//        String lastDayStr = lastDay.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//
//        String kmTitle="到餐技术专项周报（"+firstDayStr+"-"+lastDayStr+"）";
//
//
//        JSONObject jsonObject = HttpUtils.doGet(content_url, JSONObject.class, ImmutableMap.of("Cookie", "com.sankuai.it.ead.citadel_ssoid=" + SsoUtils.getSsoId()));
//        Long contentId = jsonObject.getLong("data");
//
//        JSONObject param = new JSONObject();
//        param.put("linkedContent", "");
//
//        JSONObject params = new JSONObject();
//        params.put("title", kmTitle);
//        params.put("body", body);
//        params.put("parentId", "84462621");
//        params.put("bodyText", "测试内容" + contentId);
//        params.put("contentId", contentId);
//        params.put("whatUpdate", "");
//
//        JSONObject resp = HttpUtils.doPost(url, params.toJSONString(), JSONObject.class, ImmutableMap.of("content-type", "application/json; charset=utf-8", "Cookie", "com.sankuai.it.ead.citadel_ssoid=" + SsoUtils.getSsoId()));
//
//        String kmLink="https://km.sankuai.com/page/"+contentId;
//        System.out.println(kmLink);
//
//        //测试群id：64012858900
//        //虚拟小组群id：64011826182
//
//        DaXiangUtils.pushToPerson("@王洋 @郭孟瑶  @吴启芳  @杨帅  @张伟豪  @张波  本周周报地址"+kmLink+"辛苦各位填写一下~~","guomengyao");
//        DaXiangUtils.pushToRoom("@王洋 @郭孟瑶  @吴启芳  @杨帅  @张伟豪  @张波  本周周报地址"+kmLink+"辛苦各位填写一下~~",64011826182L);
//     //   DaXiangUtils.pushToRoom(" @郭孟瑶 "+kmLink,64012858900L);
//
//    }


//    public static void main (String[] args) {
//        LocalDate firstDay=LocalDate.now().minusDays(7);
//        LocalDate lastDay=LocalDate.now();
//
//        KmExtracter km = new KmExtracter();
//        km.extractData4Week(firstDay,lastDay);
//
//     //   DaXiangUtils.pushToPerson("test","guomengyao");
//    }
}
