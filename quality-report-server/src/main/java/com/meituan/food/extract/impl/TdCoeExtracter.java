package com.meituan.food.extract.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import com.meituan.food.extract.ITdCoeExtract;
import com.meituan.food.mapper.McdCoePOMapper;
import com.meituan.food.mapper.McdCoeTodoPOMapper;
import com.meituan.food.po.McdCoePO;
import com.meituan.food.po.McdCoeTodoPO;
import com.meituan.food.utils.HttpUtils;
import com.meituan.food.utils.SsoUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;



/**
 * @author qinqingyun
 * @date 2020/7/27 1:20 PM
 */

@Component
@Slf4j
public class TdCoeExtracter implements ITdCoeExtract {


    @Resource
    public McdCoePOMapper mcdCoePOMapper;

    @Resource
    public McdCoeTodoPOMapper mcdCoeTodoPOMapper;


    private String tableOneHead = "<table data-bordercolor=\\\"#cccccc\\\" data-diff-id=\\\"ct-diff-id-XkDWM2UF\\\"><tbody><tr data-row-diff-id=\\\"ct-tr-diff-id-iqQAkeMN\\\"><th data-colwidth=\\\"121\\\" width=\\\"121\\\" data-cell-diff-id=\\\"ct-cell-diff-id-uGxBg17w\\\"><p data-diff-id=\\\"ct-diff-id-uPD68DMQ\\\">组织结构</p></th><th data-colwidth=\\\"68\\\" width=\\\"68\\\" data-cell-diff-id=\\\"ct-cell-diff-id-hoxuigSv\\\"><p data-diff-id=\\\"ct-diff-id-t0Xy2F1A\\\">S1</p></th><th data-colwidth=\\\"68\\\" width=\\\"68\\\" data-cell-diff-id=\\\"ct-cell-diff-id-V2C5NfVu\\\"><p data-diff-id=\\\"ct-diff-id-TsYVx4mH\\\">S2</p></th><th data-colwidth=\\\"68\\\" width=\\\"68\\\" data-cell-diff-id=\\\"ct-cell-diff-id-2KWhu6DH\\\"><p data-diff-id=\\\"ct-diff-id-Xnkgawuq\\\">S3</p></th><th data-colwidth=\\\"68\\\" width=\\\"68\\\" data-cell-diff-id=\\\"ct-cell-diff-id-nc4pRMia\\\"><p data-diff-id=\\\"ct-diff-id-svHR20sw\\\">S4</p></th><th data-colwidth=\\\"68\\\" width=\\\"68\\\" data-cell-diff-id=\\\"ct-cell-diff-id-495wK1E7\\\"><p data-diff-id=\\\"ct-diff-id-MeM72csQ\\\">S9</p></th><th data-colwidth=\\\"78\\\" width=\\\"78\\\" data-cell-diff-id=\\\"ct-cell-diff-id-eWAQxCEN\\\"><p data-diff-id=\\\"ct-diff-id-rSsCFekH\\\">事件</p></th><th data-colwidth=\\\"135\\\" width=\\\"135\\\" style=\\\"background-color: rgb(250, 219, 22);\\\" data-cell-diff-id=\\\"ct-cell-diff-id-wMGPPi0z\\\"><p data-diff-id=\\\"ct-diff-id-AB18paat\\\"><strong><span style=\\\"color: rgb(0, 0, 0)\\\">新增COE汇总</span></strong></p></th><th data-colwidth=\\\"151\\\" width=\\\"151\\\" style=\\\"background-color: rgb(250, 140, 23);\\\" data-cell-diff-id=\\\"ct-cell-diff-id-CGDQzJHV\\\"><p data-diff-id=\\\"ct-diff-id-PZUcwo3g\\\">超1周未完善COE数</p></th><th data-colwidth=\\\"172\\\" width=\\\"172\\\" style=\\\"background-color: rgb(250, 84, 29);\\\" data-cell-diff-id=\\\"ct-cell-diff-id-JDumS4OL\\\"><p data-diff-id=\\\"ct-diff-id-sW0K0yf0\\\">逾期未完成Todo数</p></th></tr>";
    private String tableTwoHead = "<table data-diff-id=\\\"ct-diff-id-vX99VVqe\\\"><tbody><tr data-row-diff-id=\\\"ct-tr-diff-id-Allmbr99\\\"><th data-colwidth=\\\"121\\\" width=\\\"121\\\" data-cell-diff-id=\\\"ct-cell-diff-id-Er325NcT\\\"><p data-diff-id=\\\"ct-diff-id-oXaKN5Vp\\\">组织结构</p></th><th data-colwidth=\\\"120\\\" width=\\\"120\\\" data-cell-diff-id=\\\"ct-cell-diff-id-5LUcN4cQ\\\"><p data-diff-id=\\\"ct-diff-id-qXpa6rMa\\\">COE标题</p></th><th data-colwidth=\\\"95\\\" width=\\\"95\\\" data-cell-diff-id=\\\"ct-cell-diff-id-Pq5CMLQS\\\"><p data-diff-id=\\\"ct-diff-id-93yLcolz\\\">级别</p></th><th data-colwidth=\\\"108\\\" width=\\\"108\\\" data-cell-diff-id=\\\"ct-cell-diff-id-IirlvFeB\\\"><p data-diff-id=\\\"ct-diff-id-HqoCfpow\\\">发生时间</p></th><th data-colwidth=\\\"108\\\" width=\\\"108\\\" data-cell-diff-id=\\\"ct-cell-diff-id-bwiFH4Pn\\\"><p data-diff-id=\\\"ct-diff-id-xeYFPWSJ\\\">COE负责人</p></th><th data-colwidth=\\\"262\\\" width=\\\"262\\\" data-cell-diff-id=\\\"ct-cell-diff-id-wUPRUffq\\\"><p data-diff-id=\\\"ct-diff-id-xnzsuP41\\\">时间线</p></th><th data-colwidth=\\\"108\\\" width=\\\"108\\\" data-cell-diff-id=\\\"ct-cell-diff-id-Mc49bgaK\\\"><p data-diff-id=\\\"ct-diff-id-63QBlETJ\\\">详细信息（留空由业务跟进即可）</p></th></tr>";
    private String tableThreeHead = "<table data-diff-id=\\\"ct-diff-id-IqGRPQ7S\\\"><tbody><tr data-row-diff-id=\\\"ct-tr-diff-id-Allmbr99\\\"><th data-colwidth=\\\"121\\\" width=\\\"121\\\" data-cell-diff-id=\\\"ct-cell-diff-id-Er325NcT\\\"><p data-diff-id=\\\"ct-diff-id-51e0jlCz\\\">组织结构</p></th><th data-colwidth=\\\"120\\\" width=\\\"120\\\" data-cell-diff-id=\\\"ct-cell-diff-id-5LUcN4cQ\\\"><p data-diff-id=\\\"ct-diff-id-hu6TRTyf\\\">COE标题</p></th><th data-colwidth=\\\"108\\\" width=\\\"108\\\" data-cell-diff-id=\\\"ct-cell-diff-id-ppZ4yBEA\\\"><p data-diff-id=\\\"ct-diff-id-oRLr40On\\\">COE创建时间</p></th><th data-colwidth=\\\"133\\\" width=\\\"133\\\" data-cell-diff-id=\\\"ct-cell-diff-id-alh5WVRf\\\"><p data-diff-id=\\\"ct-diff-id-8VgZVLQr\\\">截止今日（天）</p></th><th data-colwidth=\\\"109\\\" width=\\\"109\\\" data-cell-diff-id=\\\"ct-cell-diff-id-OhwMleJN\\\"><p data-diff-id=\\\"ct-diff-id-BW8dYFq9\\\">COE负责人</p></th><th data-colwidth=\\\"350\\\" width=\\\"350\\\" data-cell-diff-id=\\\"ct-cell-diff-id-pwm0Uswb\\\"><p data-diff-id=\\\"ct-diff-id-VQL6NeUW\\\">未填写信息</p></th></tr>";
    private String tableFourHead = "<table data-diff-id=\\\"ct-diff-id-bHmyutQW\\\"><tbody><tr data-row-diff-id=\\\"ct-tr-diff-id-Allmbr99\\\"><th data-colwidth=\\\"121\\\" width=\\\"121\\\" data-cell-diff-id=\\\"ct-cell-diff-id-Er325NcT\\\"><p data-diff-id=\\\"ct-diff-id-GXp5MVBe\\\">组织结构</p></th><th data-colwidth=\\\"120\\\" width=\\\"120\\\" data-cell-diff-id=\\\"ct-cell-diff-id-5LUcN4cQ\\\"><p data-diff-id=\\\"ct-diff-id-qSQLkRmd\\\">COE标题</p></th><th data-colwidth=\\\"108\\\" width=\\\"108\\\" data-cell-diff-id=\\\"ct-cell-diff-id-ppZ4yBEA\\\"><p data-diff-id=\\\"ct-diff-id-4JDj1dD1\\\">Todo</p></th><th data-colwidth=\\\"117\\\" width=\\\"117\\\" data-cell-diff-id=\\\"ct-cell-diff-id-rFcTS5os\\\"><p data-diff-id=\\\"ct-diff-id-B7JSTP8y\\\">Todo跟进人</p></th><th data-colwidth=\\\"133\\\" width=\\\"133\\\" data-cell-diff-id=\\\"ct-cell-diff-id-alh5WVRf\\\"><p data-diff-id=\\\"ct-diff-id-m5FvIE5y\\\">预计完成时间</p></th><th data-colwidth=\\\"109\\\" width=\\\"109\\\" data-cell-diff-id=\\\"ct-cell-diff-id-OhwMleJN\\\"><p data-diff-id=\\\"ct-diff-id-gZdReNwv\\\">逾期天数</p></th></tr>";

    private String tableTail = "</tbody></table><p data-diff-id=\\\"ct-diff-id-h0mwpILl\\\"></p><p data-diff-id=\\\"ct-diff-id-I7QftEbM\\\"></p>";
    private String trHead = "<tr>";
    private String trTail = "</tr>";
    private String tdHead = "<td data-colwidth=\\\"694\\\" width=\\\"694\\\" style=\\\"background-color: rgb(255, 255, 255);\\\"><p>";
    private String tdTail = "</p></td>";
    private String emptyData = "<td data-colwidth=\\\"694\\\" width=\\\"694\\\" style=\\\"background-color: rgb(255, 255, 255);\\\"><p style=\\\"text-align: start;\\\"></p></td>";
    private static final String content_url = "https://km.sankuai.com/api/pages/contentId";
    private static final String url = "https://km.sankuai.com/api/pages/8045";
    private String kmTitleHead = "";
    private String body = "";
    private String time = "<p data-diff-id=\\\"ct-diff-id-tM1gLF7v\\\">统计周期：";
    private String range_head = "</p><p data-diff-id=\\\"ct-diff-id-v8kNoH54\\\">统计范围：";
    private String question = "</p><p data-diff-id=\"ct-diff-id-qqubsC6R\">问题趋势：<a class=\"ct-link\" href=\"https://bi.sankuai.com/dashboard/38576\" data-pageid=\"\" data-auto_update=\"0\">https://bi.sankuai.com/dashboard/38576</a></p><p data-diff-id=\"ct-diff-id-I3kWi3tI\"></p>";


    @Override
    public void extractData4Week(Date firstDate, Date secondDate) throws ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String firstDayStr = formatter.format(firstDate);
        String lastDayStr = formatter.format(secondDate);
        String kmTitleTail = firstDayStr + "～" + lastDayStr;
        Map<String, List<String>> orgname = setOrgName();


        for (Map.Entry<String, List<String>> m : orgname.entrySet()) {
            String tilte = m.getKey();
            List<String> org = m.getValue();
            String range = "";
            for (int i = 0; i < org.size(); i++) {
                if (i != org.size() - 1) {
                    range += org.get(i) + "、";
                } else {
                    range += org.get(i);

                }
            }
            kmTitleHead = time + kmTitleTail + range_head + range + question;
            System.out.println("key:" + m.getKey() + " value:" + m.getValue());
            createReport("到店技术部-" + tilte + "-" + kmTitleTail, firstDate, secondDate, org, "", kmTitleHead);


        }


    }

    private String combineBody_1(String tableHead, Map<String, CoePushDataVO> orgCoeContext) {


        String th_1 = "<tr data-row-diff-id=\\\"ct-tr-diff-id-QKhwXkb9\\\"><th data-colwidth=\\\"121\\\" width=\\\"121\\\" data-cell-diff-id=\\\"ct-cell-diff-id-doo3s481\\\"><p data-diff-id=\\\"ct-diff-id-eWIQ3pKc\\\">";
        String th_2 = "<td data-colwidth=\\\"68\\\" width=\\\"68\\\" data-cell-diff-id=\\\"ct-cell-diff-id-KGr92vpA\\\"><p data-diff-id=\\\"ct-diff-id-ev250GQG\\\">";
        String subtitle = "<h3 id=\\\"G2Qmf7nf\\\" class=\\\"ct-heading\\\" data-diff-id=\\\"ct-diff-id-ul2gVTIe\\\">新增COE及SOP落地情况汇总</h3>";

        body += subtitle + tableHead;

        for (Map.Entry<String, CoePushDataVO> m : orgCoeContext.entrySet()) {

            body += th_1 + m.getKey() + tdTail + th_2 + m.getValue().getS1Conut() + tdTail
                    + th_2 + m.getValue().getS2Conut() + tdTail
                    + th_2 + m.getValue().getS3Conut() + tdTail
                    + th_2 + m.getValue().getS4Count() + tdTail
                    + th_2 + m.getValue().getS9Count() + tdTail
                    + th_2 + m.getValue().getECount() + tdTail
                    + th_2 + m.getValue().getAllCount() + tdTail
                    + th_2 + m.getValue().getIncompleteCount() + tdTail
                    + th_2 + m.getValue().getOverdueTodoCount() + tdTail;

            body += trTail;

        }

        int s1Count = 0;
        int s2Count = 0;
        int s3Count = 0;
        int s4Count = 0;
        int s9Count = 0;
        int eCount = 0;
        int allCount = 0;
        int incompleteCount = 0;
        int overdueTodoCount = 0;
        for (CoePushDataVO coePushDataVO : orgCoeContext.values()) {
            s1Count += coePushDataVO.getS1Conut();
            s2Count += coePushDataVO.getS2Conut();
            s3Count += coePushDataVO.getS3Conut();
            s4Count += coePushDataVO.getS4Count();
            s9Count += coePushDataVO.getS9Count();
            eCount += coePushDataVO.getECount();
            allCount += coePushDataVO.getAllCount();
            incompleteCount += coePushDataVO.getIncompleteCount();
            overdueTodoCount += coePushDataVO.getOverdueTodoCount();


        }
        body += th_1 + "汇总" + tdTail + th_2 + s1Count + tdTail
                + th_2 + s2Count + th_2 + s3Count + tdTail
                + th_2 + s4Count + th_2 + s9Count + th_2 + eCount + tdTail
                + th_2 + allCount + th_2 + incompleteCount + tdTail
                + th_2 + overdueTodoCount + tdTail;


        return body += trTail + tableTail;
    }

    private String combineBody_2(String tableHead, Map<String, ArrayList<COEInfoVo>> orgCoeInfo) {


        String th_1 = "<tr data-row-diff-id=\\\"ct-tr-diff-id-TrqpWsS1\\\">";
        String th_2 = "<th data-colwidth=\\\"121\\\" width=\\\"121\\\" data-cell-diff-id=\\\"ct-cell-diff-id-pWjFEUWg\\\"><p data-diff-id=\\\"ct-diff-id-R0nWL7RV\\\">";
        String th_3 = "<td data-colwidth=\\\"262\\\" width=\\\"262\\\" data-cell-diff-id=\\\"ct-cell-diff-id-CTgpyVOu\\\"><p data-diff-id=\\\"ct-diff-id-H0gDCfbY\\\">";
        String th_4 = "<td data-colwidth=\\\"100\\\" width=\\\"100\\\" data-cell-diff-id=\\\"ct-cell-diff-id-CTgpyVOu\\\"><p data-diff-id=\\\"ct-diff-id-H0gDCfbY\\\">";
        String subtile = "<h3 id=\\\"RkQKgGqR\\\" class=\\\"ct-heading\\\" data-diff-id=\\\"ct-diff-id-4zwkAwjw\\\">新增COE明细</h3>";
        body += subtile + tableHead;

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");


        for (Map.Entry<String, ArrayList<COEInfoVo>> m : orgCoeInfo.entrySet()) {
            for (COEInfoVo coeInfoVo : m.getValue()) {
                String time_total = "";

                time_total = "● 发现时长(分)：" + coeInfoVo.getTime().get(0) + "\n● 定位时长(分)：" + coeInfoVo.getTime().get(1) + "\n● 处理时长(分)：" + coeInfoVo.getTime().get(2);
                body += th_1 + th_2 + m.getKey() + tdTail + th_4 + coeInfoVo.getBrief() + tdTail + th_4 + coeInfoVo.getLevel() + tdTail + th_4 + formatter.format(coeInfoVo.getOccur_time()) + tdTail + th_4 + coeInfoVo.getOwner_name() + tdTail + th_3 + time_total + tdTail + th_4 +""+ tdTail + trTail;


            }

        }

        return body += tableTail;
    }


    private String combineBody_3(String tableHead, Map<String, ArrayList<COEIneligibleVo>> orgCoeIneligibleInfo) {


        String th_1 = "<tr data-row-diff-id=\\\"ct-tr-diff-id-TrqpWsS1\\\">";
        String th_2 = "<th data-colwidth=\\\"121\\\" width=\\\"121\\\" data-cell-diff-id=\\\"ct-cell-diff-id-pWjFEUWg\\\"><p data-diff-id=\\\"ct-diff-id-R0nWL7RV\\\">";
        String th_3 = "<td data-colwidth=\\\"120\\\" width=\\\"120\\\" data-cell-diff-id=\\\"ct-cell-diff-id-CTgpyVOu\\\"><p data-diff-id=\\\"ct-diff-id-H0gDCfbY\\\">";
        String th_4 = "<tr data-row-diff-id=\\\"ct-tr-diff-id-KicZrmYd\\\"><td data-colwidth=\\\"120\\\" width=\\\"120\\\" data-cell-diff-id=\\\"ct-cell-diff-id-kgLFafYS\\\"><p data-diff-id=\\\"ct-diff-id-yHS7rfx5\\\">";
        String subtitle = "<h3 id=\\\"wI7mamlT\\\" class=\\\"ct-heading\\\" data-diff-id=\\\"ct-diff-id-WVe9XOwF\\\">超1周未完善的COE明细</h3>";
        body += subtitle + tableHead;

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");


        for (Map.Entry<String, ArrayList<COEIneligibleVo>> m : orgCoeIneligibleInfo.entrySet()) {
            for (COEIneligibleVo coeIneligibleVo : m.getValue()) {

                body += th_1 + th_2 + m.getKey() + tdTail + th_3 + coeIneligibleVo.getBrief() + tdTail + th_3 + formatter.format(coeIneligibleVo.getCreate_time()) + tdTail + th_3 + coeIneligibleVo.getTotalTime() + tdTail + th_3 + coeIneligibleVo.getOwner_name() + tdTail + th_3 + coeIneligibleVo.getMissInformation() + tdTail + trTail;


            }

        }

        return body += tableTail;
    }


    private String combineBody_4(String tableHead, Map<String, Map<String, ArrayList<COETodoVo>>> orgCoeTodoInfo) {

        String th_1 = "<tr data-row-diff-id=\\\"ct-tr-diff-id-TrqpWsS1\\\">";
        String th_2 = "<th data-colwidth=\\\"121\\\" width=\\\"121\\\" data-cell-diff-id=\\\"ct-cell-diff-id-pWjFEUWg\\\"><p data-diff-id=\\\"ct-diff-id-R0nWL7RV\\\">";
        String th_3 = "data-colwidth=\\\"120\\\" width=\\\"120\\\" data-cell-diff-id=\\\"ct-cell-diff-id-CTgpyVOu\\\"><p data-diff-id=\\\"ct-diff-id-H0gDCfbY\\\">";
        String th_4 = "<tr data-row-diff-id=\\\"ct-tr-diff-id-KicZrmYd\\\"><td data-colwidth=\\\"120\\\" width=\\\"120\\\" data-cell-diff-id=\\\"ct-cell-diff-id-kgLFafYS\\\"><p data-diff-id=\\\"ct-diff-id-yHS7rfx5\\\">";
        String th_5 = "<td data-colwidth=\\\"108\\\" width=\\\"108\\\" data-cell-diff-id=\\\"ct-cell-diff-id-uK49Fg1B\\\"><p data-diff-id=\\\"ct-diff-id-zSzIEUvR\\\">";
        String subtitle = "<h3 id=\\\"QuerKHJk\\\" class=\\\"ct-heading\\\" data-diff-id=\\\"ct-diff-id-1PxPvEzD\\\">逾期未完成的Todo明细</h3>";
        body += subtitle + tableHead;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        for (Map.Entry<String, Map<String, ArrayList<COETodoVo>>> m : orgCoeTodoInfo.entrySet()) {


            for (Map.Entry<String, ArrayList<COETodoVo>> coeTodo : m.getValue().entrySet()) {
                for (COETodoVo coeTodoInfo : coeTodo.getValue()) {
                    body += th_1 + th_2 + m.getKey() + tdTail + th_5 + coeTodo.getKey() + tdTail + th_5 + coeTodoInfo.getTodotitle() + tdTail + th_5 + coeTodoInfo.getOwner_name() + tdTail + th_5 + formatter.format(coeTodoInfo.getFinishtime()) + tdTail + th_5 + coeTodoInfo.getTime() + tdTail + trTail;

                }

            }

        }


        return body += tableTail;
    }


    public Map<String, List<String>> setOrgName() {
        Map<String, List<String>> org_name = new HashMap<>();
        List<String> org_1 = new ArrayList<>();
        org_1.add("平台业务研发部/结算平台研发组/到综研发组");
        org_name.put("到综结算", org_1);
        List<String> org_2 = new ArrayList<>();
        org_2.add("平台业务研发部/供应链平台研发组/到综研发组");
        org_name.put("到综供应链", org_2);
        List<String> org_3 = new ArrayList<>();
        org_3.add("平台业务研发部/商品平台研发组/到综研发组");
        org_name.put("到综商品", org_3);
        List<String> org_4 = new ArrayList<>();
        org_4.add("平台业务研发部/销售支持平台研发组/到综研发组");
        org_name.put("到综CRM", org_4);
        List<String> org_5 = new ArrayList<>();
        org_5.add("平台业务研发部/销售支持平台研发组/平台研发组");
        org_name.put("CRM平台", org_5);
        List<String> org_6 = new ArrayList<>();
        org_6.add("平台业务研发部/销售支持平台研发组/住宿门票研发组");
        org_6.add("平台业务研发部/销售支持平台研发组/住宿门票研发组/运营管理组");
        org_name.put("酒旅CRM", org_6);

        List<String> org_7 = new ArrayList<>();
        org_7.add("平台业务研发部/结算平台研发组/住宿门票研发组");
        org_7.add("平台业务研发部/结算平台研发组/到餐研发组");
        org_7.add("平台业务研发部/结算平台研发组/平台研发组");
        org_7.add("平台业务研发部/结算平台研发组/到综研发组");
        org_name.put("到店结算", org_7);

        List<String> org_8 = new ArrayList<>();
        org_8.add("平台业务研发部/营销平台研发组");

        org_name.put("营销平台", org_8);
        List<String> org_9 = new ArrayList<>();
        org_9.add("平台业务研发部/商家平台研发组/到餐研发组");
        org_9.add("平台业务研发部/商家平台研发组/到综研发组");
        org_9.add("平台业务研发部/商家平台研发组/基础平台研发组");
        org_9.add("平台业务研发部/商家平台研发组/运营平台研发组");
        org_9.add("平台业务研发部/商家平台研发组/增值平台研发组");
        org_name.put("商家平台", org_9);

        List<String> org_10 = new ArrayList<>();
        org_10.add("平台业务研发部/交易平台研发组/到餐研发组");
        org_name.put("到餐交易方向", org_10);
        List<String> org_11 = new ArrayList<>();
        org_11.add("平台业务研发部/客户平台研发组/基础平台研发组");
        org_11.add("平台业务研发部/客户平台研发组/到餐客户研发组");
        org_11.add("平台业务研发部/客户平台研发组/住宿门票客户研发组");
        org_11.add("平台业务研发部/客户平台研发组/到综客户研发组");
        org_11.add("平台业务研发部/门店信息研发组/基础信息研发组");
        org_11.add("平台业务研发部/门店信息研发组/业务支持研发组");
        org_11.add("到店餐饮研发部/业务后台研发组/渠道门店组");
        org_name.put("客户平台", org_11);

        List<String> org_13 = new ArrayList<>();
        org_13.add("到店餐饮研发部/业务前端研发组");
        org_name.put("到餐业务前端", org_13);

        List<String> org_14 = new ArrayList<>();
        org_14.add("到店餐饮研发部/业务后台研发组/套代运营组");
        org_14.add("到店餐饮研发部/业务后台研发组/信息平台组");
        org_name.put("到餐信息方向", org_14);

        List<String> org_15 = new ArrayList<>();
        org_15.add("平台业务研发部/营销平台研发组/到餐研发组");
        org_name.put("到餐营销方向", org_15);

        List<String> org_16 = new ArrayList<>();
        org_16.add("到店综合研发部/业务后端开发组/到综教育及亲子技术组");
        org_16.add("到店综合研发部/业务后端开发组/到综丽人及医美技术组");
        org_16.add("到店综合研发部/业务后端开发组/到综丽人及医美技术组/丽人用户技术组");
        org_16.add("到店综合研发部/业务后端开发组/到综Life Event技术组");
        org_16.add("到店综合研发部/业务后端开发组/到综休闲娱乐技术组");
        org_16.add("到店综合研发部/业务后端开发组/到综平台产品技术组/综合Saas技术组");
        org_16.add("到店综合研发部/数据应用开发组/应用开发组");
        org_name.put("到综业务", org_16);


        List<String> org_17 = new ArrayList<>();
        org_17.add("住宿门票研发部");
        org_name.put("住宿门票业务", org_17);

        List<String> org_18 = new ArrayList<>();
        org_18.add("平台业务研发部/交易平台研发组/到综研发组");
        org_name.put("到综交易", org_18);

        List<String> org_19 = new ArrayList<>();
        org_19.add("平台业务研发部/交易平台研发组/平台支持组");
        org_name.put("交易平台", org_19);
        return org_name;
    }

    private void createReport(String kmTitle, Date firstDate, Date secondDate, List<String> org, String bodylist, String kmdetail) throws ParseException {
        body = bodylist + kmdetail;
        Map<String, CoePushDataVO> orgCoeContext = new HashMap<>();

        Map<String, ArrayList<COEInfoVo>> orgCoeInfo = new HashMap<>();
        Map<String, ArrayList<COEIneligibleVo>> orgCoeIneligibleInfo = new HashMap<>();

        Map<String, Map<String, ArrayList<COETodoVo>>> orgCoeTodoInfo = new HashMap<>();

        List<McdCoePO> coelist = mcdCoePOMapper.selectByTwoDate(firstDate, secondDate);

        Iterator<McdCoePO> iterator = coelist.iterator();
        while (iterator.hasNext()) {
            McdCoePO mcdCoePO = iterator.next();

            if (!org.contains(mcdCoePO.getOrgName())) {
                iterator.remove();
            }
        }


          //编写第一模块
          log.info("需要校验的coe的数据:{}",coelist.toString());

          getOrgCoeContext(orgCoeContext, coelist);
          getIneligibleCoe(orgCoeContext, firstDate, secondDate, org);
          getIneligibleCoeTodo(orgCoeContext, firstDate, secondDate, org);
          combineBody_1(tableOneHead, orgCoeContext);

          //编写第二模块
          getOrgCoeInfo(orgCoeInfo, coelist);
          combineBody_2(tableTwoHead, orgCoeInfo);

          //编写第三模块
          getOrgIneligibleCoe(orgCoeIneligibleInfo, firstDate, secondDate, org);
          combineBody_3(tableThreeHead, orgCoeIneligibleInfo);

          //编写第四模块
          getTodoInfo(orgCoeTodoInfo, org);
          combineBody_4(tableFourHead, orgCoeTodoInfo);

          String ssoid=SsoUtils.getSsoId();
          log.info("线上的SSOID:{}",ssoid);
          JSONObject jsonObject = HttpUtils.doGet(content_url, JSONObject.class, ImmutableMap.of("Cookie", "com.sankuai.it.ead.citadel_ssoid="+ssoid));
          Long contentId = jsonObject.getLong("data");

          JSONObject param = new JSONObject();
          param.put("linkedContent", "");

          JSONObject params = new JSONObject();

          params.put("body", body);//combineBody
          params.put("parentId", "378665201");// 周报父目录
          params.put("bodyText", "测试内容" + contentId);
          params.put("contentId", contentId);
          params.put("whatUpdate", "");

          params.put("title", kmTitle);
          JSONObject resp = HttpUtils.doPost(url, params.toJSONString(), JSONObject.class, ImmutableMap.of("content-type", "application/json; charset=utf-8", "Cookie", "com.sankuai.it.ead.citadel_ssoid=" + SsoUtils.getSsoId()));







       }





    //COE的汇总数据

    public Map<String, CoePushDataVO> getOrgCoeContext(Map<String, CoePushDataVO> orgCoeContext, List<McdCoePO> mcdCoePOList) throws ParseException {

        for (McdCoePO mcdCoePO : mcdCoePOList) {
            String orgName = mcdCoePO.getOrgName();
            if (!orgCoeContext.keySet().contains(orgName)) {
                CoePushDataVO coePushDataVO = new CoePushDataVO();
                coePushDataVO.newVO();
                coeContext(mcdCoePO, coePushDataVO);
                orgCoeContext.put(orgName, coePushDataVO);
            } else {
                coeContext(mcdCoePO, orgCoeContext.get(orgName));
                orgCoeContext.put(orgName, orgCoeContext.get(orgName));
            }


        }

        return orgCoeContext;


    }

    //新增COE明细
    public Map<String, ArrayList<COEInfoVo>> getOrgCoeInfo(Map<String, ArrayList<COEInfoVo>> orgCoeInfo, List<McdCoePO> mcdCoePOList) throws ParseException {


        for (McdCoePO mcdCoePO : mcdCoePOList) {

            String orgName = mcdCoePO.getOrgName();
            if (!orgCoeInfo.keySet().contains(orgName)) {
                ArrayList<COEInfoVo> coelist = new ArrayList<COEInfoVo>();
                COEInfoVo coeInfoVo = new COEInfoVo();
                coeInfoVo.setBrief(mcdCoePO.getBrief());
                coeInfoVo.setLevel(mcdCoePO.getCustomLevel());
                coeInfoVo.setOccur_time(mcdCoePO.getOccurDate());
                coeInfoVo.setOwner_name(mcdCoePO.getOwnerName());
                List<Integer> time = new ArrayList<>();
                time.add(mcdCoePO.getFminusoTime());
                time.add(mcdCoePO.getLminusfTime());
                time.add(mcdCoePO.getSminushTime());
                coeInfoVo.setTime(time);
                coelist.add(coeInfoVo);
                orgCoeInfo.put(orgName, coelist);

            } else {
                COEInfoVo coeInfoVo = new COEInfoVo();
                coeInfoVo.setBrief(mcdCoePO.getBrief());
                coeInfoVo.setLevel(mcdCoePO.getCustomLevel());
                coeInfoVo.setOccur_time(mcdCoePO.getOccurDate());
                coeInfoVo.setOwner_name(mcdCoePO.getOwnerName());
                List<Integer> time = new ArrayList<>();
                time.add(mcdCoePO.getFminusoTime());
                time.add(mcdCoePO.getLminusfTime());
                time.add(mcdCoePO.getSminushTime());
                coeInfoVo.setTime(time);
                orgCoeInfo.get(orgName).add(coeInfoVo);
                orgCoeInfo.put(orgName, orgCoeInfo.get(orgName));


            }


        }


        return orgCoeInfo;


    }


    //超1周未完善的COE明细
    public Map<String, ArrayList<COEIneligibleVo>> getOrgIneligibleCoe(Map<String, ArrayList<COEIneligibleVo>> orgCoeIneligibleInfo, Date firstDate, Date secondDate, List<String> org) throws ParseException {


        Date date_1 = getdelayDate(firstDate, -7);
        Date date_2 = getdelayDate(secondDate, -7);


        List<McdCoePO> mcdCoePOList = mcdCoePOMapper.selectByTwoDate(date_1, date_2);


        Iterator<McdCoePO> iterator = mcdCoePOList.iterator();
        while (iterator.hasNext()) {
            McdCoePO mcdCoePO = iterator.next();

            if (!org.contains(mcdCoePO.getOrgName())) {
                iterator.remove();//使用迭代器的删除方法删除
            }
        }

        for (McdCoePO mcdCoePO : mcdCoePOList) {
            int index = 0;
            String content = "";

            if (mcdCoePO.getFminusoTime() == null || mcdCoePO.getLminusfTime() == null || mcdCoePO.getSminushTime() == null || mcdCoePO.getInfluenceTime() == null) {
                content = content + "● 时间线不完整";
                index++;
            }
            if (mcdCoePO.getLine() == null) {
                content = content + "\n● 未填写业务线";
                index++;
            }
            if (!(mcdCoePO.getCapitalLoss() != null || mcdCoePO.getOrderLoss() != null || mcdCoePO.getCouponLoss() != null)) {
                content = content + "\n● 未填写资损数据(如无损失请填0)";
                index++;
            }
            if (mcdCoePO.getOnlineDiscovery() == null) {
                content = content + "\n● 未填写线上问题发现方式";
                index++;
            }
            if (mcdCoePO.getOnlineClassification() == null) {
                content = content + "\n● 未填写线上问题原因分类";
                index++;
            }
            if (mcdCoePO.getNofundReason() == null) {
                content = content + "\n● 未填写前期测试未发现原因";
                index++;
            }

            if (index != 0) {
                String orgName = mcdCoePO.getOrgName();
                if (!orgCoeIneligibleInfo.keySet().contains(orgName)) {
                    ArrayList<COEIneligibleVo> coelist = new ArrayList<COEIneligibleVo>();
                    COEIneligibleVo coeIneligibleVo = new COEIneligibleVo();
                    coeIneligibleVo.setBrief(mcdCoePO.getBrief());
                    coeIneligibleVo.setCreate_time(mcdCoePO.getCreateTime());
                    coeIneligibleVo.setTotalTime(daysBetween(mcdCoePO.getCreateTime(), new Date()));
                    coeIneligibleVo.setOwner_name(mcdCoePO.getOwnerName());
                    coeIneligibleVo.setMissInformation(content);
                    coelist.add(coeIneligibleVo);
                    orgCoeIneligibleInfo.put(orgName, coelist);
                } else {
                    COEIneligibleVo coeIneligibleVo = new COEIneligibleVo();
                    coeIneligibleVo.setBrief(mcdCoePO.getBrief());
                    coeIneligibleVo.setCreate_time(mcdCoePO.getCreateTime());
                    coeIneligibleVo.setOwner_name(mcdCoePO.getOwnerName());
                    coeIneligibleVo.setTotalTime(daysBetween(mcdCoePO.getCreateTime(), new Date()));
                    coeIneligibleVo.setMissInformation(content);
                    orgCoeIneligibleInfo.get(orgName).add(coeIneligibleVo);
                    orgCoeIneligibleInfo.put(orgName, orgCoeIneligibleInfo.get(orgName));

                }


            }


        }

        return orgCoeIneligibleInfo;


    }


    //逾期未完成的Todo明细
    public Map<String, Map<String, ArrayList<COETodoVo>>> getTodoInfo(Map<String, Map<String, ArrayList<COETodoVo>>> orgCoeTodoInfo, List<String> org) throws ParseException {
        Map<String, ArrayList<COETodoVo>> orgCoeInfo = new HashMap<>();
        List<Integer> coeList = mcdCoeTodoPOMapper.selectOverdueCoeIdList();
        for (Integer coeId : coeList) {
            McdCoePO po = mcdCoePOMapper.selectByCoeId(coeId);
            if (po != null && org.contains(po.getOrgName())) {

                List<McdCoeTodoPO> mcdCoeTodoPOS = mcdCoeTodoPOMapper.selectOverdueByCoeId(coeId);


                String orgName = po.getOrgName();
                for (McdCoeTodoPO mcdtodo : mcdCoeTodoPOS) {
                    COETodoVo coeTodoVo = new COETodoVo();


                    if (!orgCoeTodoInfo.keySet().contains(orgName)) {

                        ArrayList<COETodoVo> coetodolist = new ArrayList<COETodoVo>();

                        McdCoePO coe = mcdCoePOMapper.selectByCoeId(mcdtodo.getCoeId());
                        coeTodoVo.setTodotitle(mcdtodo.getOnesTitle());
                        coeTodoVo.setFinishtime(mcdtodo.getDealline());
                        coeTodoVo.setOwner_name(mcdtodo.getOwnerName());
                        coeTodoVo.setTime(daysBetween(mcdtodo.getDealline(), new Date()));
                        coetodolist.add(coeTodoVo);
                        orgCoeInfo.put(coe.getBrief(), coetodolist);
                        orgCoeTodoInfo.put(orgName, orgCoeInfo);


                    } else {
                        ArrayList<COETodoVo> coetodolist = new ArrayList<COETodoVo>();
                        McdCoePO coe = mcdCoePOMapper.selectByCoeId(mcdtodo.getCoeId());
                        coeTodoVo.setTodotitle(mcdtodo.getOnesTitle());
                        coeTodoVo.setFinishtime(mcdtodo.getDealline());
                        coeTodoVo.setOwner_name(mcdtodo.getOwnerName());
                        coeTodoVo.setTime(daysBetween(mcdtodo.getDealline(), new Date()));
                        if (!orgCoeInfo.keySet().contains(coe.getBrief())) {
                            coetodolist.add(coeTodoVo);
                            orgCoeInfo.put(coe.getBrief(), coetodolist);
                            orgCoeTodoInfo.put(orgName, orgCoeInfo);

                        } else {
                            orgCoeInfo.get(coe.getBrief()).add(coeTodoVo);
                            orgCoeInfo.put(coe.getBrief(), orgCoeInfo.get(coe.getBrief()));
                            orgCoeTodoInfo.put(orgName, orgCoeInfo);


                        }


                    }

                }


            }
        }


        return orgCoeTodoInfo;

    }

    //COE的各个等级

    public void coeContext(McdCoePO mcdCoePO, CoePushDataVO coePushDataVO) throws ParseException {

        //各等级个数
        String level = mcdCoePO.getLevel();
        if (level != null && (!" ".equals(level))) {
            if (level.equals("S1")) {
                coePushDataVO.setS1Conut(coePushDataVO.getS1Conut() + 1);
                coePushDataVO.setAllCount(coePushDataVO.getAllCount() + 1);
            } else if (level.equals("S2")) {
                coePushDataVO.setS2Conut(coePushDataVO.getS2Conut() + 1);
                coePushDataVO.setAllCount(coePushDataVO.getAllCount() + 1);
            } else if (level.equals("S3")) {
                coePushDataVO.setS3Conut(coePushDataVO.getS3Conut() + 1);
                coePushDataVO.setAllCount(coePushDataVO.getAllCount() + 1);
            } else if (level.equals("S4")) {
                coePushDataVO.setS4Count(coePushDataVO.getS4Count() + 1);
                coePushDataVO.setAllCount(coePushDataVO.getAllCount() + 1);
            } else if (level.equals("S9")) {
                coePushDataVO.setS9Count(coePushDataVO.getS9Count() + 1);
                coePushDataVO.setAllCount(coePushDataVO.getAllCount() + 1);
            } else if (level.equals("E")) {
                coePushDataVO.setECount(coePushDataVO.getECount() + 1);
                coePushDataVO.setAllCount(coePushDataVO.getAllCount() + 1);
            } else {
                coePushDataVO.setOtherCount(coePushDataVO.getOtherCount() + 1);
                coePushDataVO.setAllCount(coePushDataVO.getAllCount() + 1);
            }
        } else {
            coePushDataVO.setOtherCount(coePushDataVO.getOtherCount() + 1);
            coePushDataVO.setAllCount(coePushDataVO.getAllCount() + 1);
            mcdCoePO.setLevel("未填写");
        }


    }


    public Date getdelayDate(Date date, int n) {

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE, n);//把日期往后增加一天.整数往后推,负数往前移动
        Date date_delay = calendar.getTime(); //这个时间就是日期往后推一天的结果
        return date_delay;


    }

    //COE总的todo
    public void getIneligibleCoeTodo(Map<String, CoePushDataVO> orgCoeContext, Date firstDate, Date secondDate, List<String> org) throws ParseException {

        //逾期的todo
        List<Integer> coeList = mcdCoeTodoPOMapper.selectOverdueCoeIdList();

        for (Integer coeId : coeList) {
            McdCoePO po = mcdCoePOMapper.selectByCoeId(coeId);

            if (po != null && org.contains(po.getOrgName())) {
                List<McdCoeTodoPO> mcdCoeTodoPOS = mcdCoeTodoPOMapper.selectOverdueByCoeId(coeId);
                int overdueCount = mcdCoeTodoPOS.size();

                log.info("这条COE的逾期TODO个数为{}", overdueCount);

                String orgName = po.getOrgName();
                if (orgCoeContext.keySet().contains(orgName)) {
                    CoePushDataVO vo = orgCoeContext.get(orgName);
                    vo.setOverdueTodoCount(vo.getOverdueTodoCount()+overdueCount);
                    orgCoeContext.put(orgName, vo);
                }
                else{
                    CoePushDataVO vo=new CoePushDataVO();
                    vo.setOverdueTodoCount(overdueCount);
                    orgCoeContext.put(orgName, vo);

                }
            }
        }


    }

    public void getIneligibleCoe(Map<String, CoePushDataVO> orgCoeContext, Date firstDate, Date secondDate, List<String> org) throws ParseException {
        //未完善
        Date date_1 = getdelayDate(firstDate, -7);
        Date date_2 = getdelayDate(secondDate, -7);
        int index = 0;
        String content = "";

        List<McdCoePO> mcdCoePOList = mcdCoePOMapper.selectByTwoDate(date_1, date_2);


        Iterator<McdCoePO> iterator = mcdCoePOList.iterator();
        while (iterator.hasNext()) {
            McdCoePO mcdCoePO = iterator.next();

            if (!org.contains(mcdCoePO.getOrgName())) {
                iterator.remove();//使用迭代器的删除方法删除
            }
        }
        for (McdCoePO mcdCoePO : mcdCoePOList) {


            if (mcdCoePO.getFminusoTime() == null || mcdCoePO.getLminusfTime() == null || mcdCoePO.getSminushTime() == null || mcdCoePO.getInfluenceTime() == null) {
                content = content + "● 时间线不完整";
                index++;
            }
            if (mcdCoePO.getLine() == null) {
                content = content + "\n● 未填写业务线";
                index++;
            }
            if (!(mcdCoePO.getCapitalLoss() != null || mcdCoePO.getOrderLoss() != null || mcdCoePO.getCouponLoss() != null)) {
                content = content + "\n● 未填写资损数据(如无损失请填0)";
                index++;
            }
            if (mcdCoePO.getOnlineDiscovery() == null) {
                content = content + "\n● 未填写线上问题发现方式";
                index++;
            }
            if (mcdCoePO.getOnlineClassification() == null) {
                content = content + "\n● 未填写线上问题原因分类";
                index++;
            }
            if (mcdCoePO.getNofundReason() == null) {
                content = content + "\n● 未填写前期测试未发现原因";
                index++;
            }
            if (index != 0) {
                String orgName = mcdCoePO.getOrgName();

                if (orgCoeContext.keySet().contains(orgName)) {
                    orgCoeContext.get(orgName).setIncompleteCount(orgCoeContext.get(orgName).getIncompleteCount() + 1);
                    orgCoeContext.put(orgName, orgCoeContext.get(orgName));
                }

            }

        }
    }


    public int daysBetween(Date smdate, Date bdate) throws ParseException

    {

        Calendar cal = Calendar.getInstance();

        cal.setTime(smdate);

        long time1 = cal.getTimeInMillis();

        cal.setTime(bdate);

        long time2 = cal.getTimeInMillis();

        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));

    }


}
