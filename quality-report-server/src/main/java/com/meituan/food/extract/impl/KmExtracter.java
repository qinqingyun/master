package com.meituan.food.extract.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import com.meituan.food.extract.IOneWeekDataExtract;
import com.meituan.food.utils.DaXiangUtils;
import com.meituan.food.utils.HttpUtils;
import com.meituan.food.utils.SsoUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class KmExtracter implements IOneWeekDataExtract {

    private static final String body = "<table data-bordercolor=\\\"#cccccc\\\"><tbody><tr><th data-colwidth=\\\"66\\\" width=\\\"66\\\" style=\\\"background-color: rgb(246, 246, 246);\\\"><p>主R</p></th><th data-colwidth=\\\"192\\\" width=\\\"192\\\" style=\\\"background-color: rgb(246, 246, 246);\\\"><p>项目</p></th><th data-colwidth=\\\"352\\\" width=\\\"352\\\" style=\\\"background-color: rgb(246, 246, 246);\\\"><p>本周进展</p></th><th data-colwidth=\\\"612\\\" width=\\\"612\\\" style=\\\"background-color: rgb(246, 246, 246);\\\"><p>下周计划</p></th><th data-colwidth=\\\"219\\\" width=\\\"219\\\" style=\\\"background-color: rgb(246, 246, 246);\\\"><p>问题与思考</p></th></tr><tr><td data-colwidth=\\\"66\\\" width=\\\"66\\\" style=\\\"background-color: rgb(255, 255, 255);\\\"><p>王洋</p></td><td data-colwidth=\\\"192\\\" width=\\\"192\\\" style=\\\"background-color: rgb(255, 255, 255);\\\"><p>到餐全链路压测执行</p></td><td data-colwidth=\\\"352\\\" width=\\\"352\\\" style=\\\"background-color: rgb(255, 255, 255);\\\"><p></p></td><td data-colwidth=\\\"612\\\" width=\\\"612\\\" style=\\\"background-color: rgb(255, 255, 255);\\\"><p></p></td><td data-colwidth=\\\"219\\\" width=\\\"219\\\" style=\\\"background-color: rgb(255, 255, 255);\\\"><p></p></td></tr><tr><td data-colwidth=\\\"66\\\" width=\\\"66\\\" style=\\\"background-color: rgb(255, 255, 255);\\\"><p>张波</p><p>王洋</p></td><td data-colwidth=\\\"192\\\" width=\\\"192\\\" style=\\\"background-color: rgb(255, 255, 255);\\\"><p>到餐全链路压测-辅助工具开发</p></td><td data-colwidth=\\\"352\\\" width=\\\"352\\\" style=\\\"background-color: rgb(255, 255, 255);\\\"><p></p></td><td data-colwidth=\\\"612\\\" width=\\\"612\\\" style=\\\"background-color: rgb(255, 255, 255);\\\"><p></p><p></p><p>   </p></td><td data-colwidth=\\\"219\\\" width=\\\"219\\\" style=\\\"background-color: rgb(255, 255, 255);\\\"><p></p></td></tr><tr><td data-colwidth=\\\"66\\\" width=\\\"66\\\" style=\\\"background-color: rgb(255, 255, 255);\\\"><p>孟瑶</p></td><td data-colwidth=\\\"192\\\" width=\\\"192\\\" style=\\\"background-color: rgb(255, 255, 255);\\\"><p>report</p></td><td data-colwidth=\\\"352\\\" width=\\\"352\\\" style=\\\"background-color: rgb(255, 255, 255);\\\"><p></p></td><td data-colwidth=\\\"612\\\" width=\\\"612\\\" style=\\\"background-color: rgb(255, 255, 255);\\\"><p></p></td><td data-colwidth=\\\"219\\\" width=\\\"219\\\" style=\\\"background-color: rgb(255, 255, 255);\\\"><p></p></td></tr><tr><td data-colwidth=\\\"66\\\" width=\\\"66\\\" style=\\\"background-color: rgb(255, 255, 255);\\\"><p>艳艳</p></td><td data-colwidth=\\\"192\\\" width=\\\"192\\\" style=\\\"background-color: rgb(255, 255, 255);\\\"><p>故障演练</p></td><td data-colwidth=\\\"352\\\" width=\\\"352\\\" style=\\\"background-color: rgb(255, 255, 255);\\\"><p style=\\\"text-align: start;\\\"></p></td><td data-colwidth=\\\"612\\\" width=\\\"612\\\" style=\\\"background-color: rgb(255, 255, 255);\\\"><p style=\\\"text-align: start;\\\"></p></td><td data-colwidth=\\\"219\\\" width=\\\"219\\\" style=\\\"background-color: rgb(255, 255, 255);\\\"><p></p></td></tr><tr><td data-colwidth=\\\"66\\\" width=\\\"66\\\" style=\\\"background-color: rgb(255, 255, 255);\\\"><p>学超</p></td><td data-colwidth=\\\"192\\\" width=\\\"192\\\" style=\\\"background-color: rgb(255, 255, 255);\\\"><p>弱网环境测试</p></td><td data-colwidth=\\\"352\\\" width=\\\"352\\\" style=\\\"background-color: rgb(255, 255, 255);\\\"><p></p></td><td data-colwidth=\\\"612\\\" width=\\\"612\\\" style=\\\"background-color: rgb(255, 255, 255);\\\"><p></p></td><td data-colwidth=\\\"219\\\" width=\\\"219\\\" style=\\\"background-color: rgb(255, 255, 255);\\\"><p></p></td></tr><tr><td data-colwidth=\\\"66\\\" width=\\\"66\\\" style=\\\"background-color: rgb(255, 255, 255);\\\"><p>玉琪</p></td><td data-colwidth=\\\"192\\\" width=\\\"192\\\" style=\\\"background-color: rgb(255, 255, 255);\\\"><p>前端性能</p></td><td data-colwidth=\\\"352\\\" width=\\\"352\\\" style=\\\"background-color: rgb(255, 255, 255);\\\"><p></p></td><td data-colwidth=\\\"612\\\" width=\\\"612\\\" style=\\\"background-color: rgb(255, 255, 255);\\\"><p></p></td><td data-colwidth=\\\"219\\\" width=\\\"219\\\" style=\\\"background-color: rgb(255, 255, 255);\\\"><p></p></td></tr><tr><td data-colwidth=\\\"66\\\" width=\\\"66\\\" style=\\\"background-color: rgb(255, 255, 255);\\\"><p>杨帅</p></td><td data-colwidth=\\\"192\\\" width=\\\"192\\\" style=\\\"background-color: rgb(255, 255, 255);\\\"><p>客户端性能测试</p></td><td data-colwidth=\\\"352\\\" width=\\\"352\\\" style=\\\"background-color: rgb(255, 255, 255);\\\"><p></p></td><td data-colwidth=\\\"612\\\" width=\\\"612\\\" style=\\\"background-color: rgb(255, 255, 255);\\\"><p></p></td><td data-colwidth=\\\"219\\\" width=\\\"219\\\" style=\\\"background-color: rgb(255, 255, 255);\\\"><p></p></td></tr><tr><td data-colwidth=\\\"66\\\" width=\\\"66\\\" style=\\\"background-color: rgb(255, 255, 255);\\\"><p>马转</p></td><td data-colwidth=\\\"192\\\" width=\\\"192\\\" style=\\\"background-color: rgb(255, 255, 255);\\\"><p>monkey测试</p></td><td data-colwidth=\\\"352\\\" width=\\\"352\\\" style=\\\"background-color: rgb(255, 255, 255);\\\"><p></p></td><td data-colwidth=\\\"612\\\" width=\\\"612\\\" style=\\\"background-color: rgb(255, 255, 255);\\\"><p></p></td><td data-colwidth=\\\"219\\\" width=\\\"219\\\" style=\\\"background-color: rgb(255, 255, 255);\\\"><p></p></td></tr><tr><td data-colwidth=\\\"66\\\" width=\\\"66\\\" style=\\\"background-color: rgb(255, 255, 255);\\\"><p>伟豪</p></td><td data-colwidth=\\\"192\\\" width=\\\"192\\\" style=\\\"background-color: rgb(255, 255, 255);\\\"><p>数据构造平台</p></td><td data-colwidth=\\\"352\\\" width=\\\"352\\\" style=\\\"background-color: rgb(255, 255, 255);\\\"><p></p></td><td data-colwidth=\\\"612\\\" width=\\\"612\\\" style=\\\"background-color: rgb(255, 255, 255);\\\"><p></p></td><td data-colwidth=\\\"219\\\" width=\\\"219\\\" style=\\\"background-color: rgb(255, 255, 255);\\\"><p></p></td></tr><tr><td data-colwidth=\\\"66\\\" width=\\\"66\\\" style=\\\"background-color: rgb(255, 255, 255);\\\"><p>乾宇</p></td><td data-colwidth=\\\"192\\\" width=\\\"192\\\" style=\\\"background-color: rgb(255, 255, 255);\\\"><p>代码覆盖率平台</p></td><td data-colwidth=\\\"352\\\" width=\\\"352\\\" style=\\\"background-color: rgb(255, 255, 255);\\\"><p></p></td><td data-colwidth=\\\"612\\\" width=\\\"612\\\" style=\\\"background-color: rgb(255, 255, 255);\\\"><p></p></td><td data-colwidth=\\\"219\\\" width=\\\"219\\\" style=\\\"background-color: rgb(255, 255, 255);\\\"><p></p></td></tr><tr><td data-colwidth=\\\"66\\\" width=\\\"66\\\" style=\\\"background-color: rgb(255, 255, 255);\\\"><p>熊一平</p></td><td data-colwidth=\\\"192\\\" width=\\\"192\\\" style=\\\"background-color: rgb(255, 255, 255);\\\"><p>项目管理工具</p></td><td data-colwidth=\\\"352\\\" width=\\\"352\\\" style=\\\"background-color: rgb(255, 255, 255);\\\"><p></p></td><td data-colwidth=\\\"612\\\" width=\\\"612\\\" style=\\\"background-color: rgb(255, 255, 255);\\\"><p></p></td><td data-colwidth=\\\"219\\\" width=\\\"219\\\" style=\\\"background-color: rgb(255, 255, 255);\\\"><p></p></td></tr></tbody></table><p></p>";

    private static final String content_url = "https://km.sankuai.com/api/pages/contentId";
    private static final String url = "https://km.sankuai.com/api/pages/8045";

    @Override
    public void extractData4Week(LocalDate firstDay, LocalDate lastDay) {

        String firstDayStr = firstDay.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String lastDayStr = lastDay.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        String kmTitle="到餐技术专项周报（"+firstDayStr+"-"+lastDayStr+"）";


        JSONObject jsonObject = HttpUtils.doGet(content_url, JSONObject.class, ImmutableMap.of("Cookie", "com.sankuai.it.ead.citadel_ssoid=" + SsoUtils.getSsoId()));
        Long contentId = jsonObject.getLong("data");

        JSONObject param = new JSONObject();
        param.put("linkedContent", "");

        JSONObject params = new JSONObject();
        params.put("title", kmTitle);
        params.put("body", body);
        params.put("parentId", "84462621");
        params.put("bodyText", "测试内容" + contentId);
        params.put("contentId", contentId);
        params.put("whatUpdate", "");

        JSONObject resp = HttpUtils.doPost(url, params.toJSONString(), JSONObject.class, ImmutableMap.of("content-type", "application/json; charset=utf-8", "Cookie", "com.sankuai.it.ead.citadel_ssoid=" + SsoUtils.getSsoId()));

        String kmLink="https://km.sankuai.com/page/"+contentId;
        System.out.println(kmLink);

     //   DaXiangUtils.pushToPerson("@王洋  @张波  @郭孟瑶 @龚艳艳 @李学超  @杨帅  @马转  @张伟豪  @鄂乾宇  @熊一平 本周周报地址"+kmLink,"guomengyao");
        DaXiangUtils.pushToRoom(" @郭孟瑶 "+kmLink,64012858900L);

    }

   /* public static void main(String[] args) {
        LocalDate firstDay=LocalDate.now().minusDays(7);
        LocalDate lastDay=LocalDate.now();

        KmExtracter km = new KmExtracter();
        km.extractData4Week(firstDay,lastDay);

     //   DaXiangUtils.pushToPerson("test","guomengyao");
}*/
}
