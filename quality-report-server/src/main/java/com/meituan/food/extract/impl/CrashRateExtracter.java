package com.meituan.food.extract.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import com.meituan.food.extract.IDataExtract;
import com.meituan.food.mapper.CrashRatePOMapper;
import com.meituan.food.po.CrashRatePO;
import com.meituan.food.service.IDauService;
import com.meituan.food.utils.HttpUtils;
import com.meituan.food.utils.SsoUtils;
import com.meituan.food.utils.UrlUtils;
import org.apache.xmlgraphics.util.DoubleFormatUtil;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Component
public class CrashRateExtracter implements IDataExtract {

    private static final String URL = "https://yuntu.sankuai.com/api/widget/";

    @Resource
    private CrashRatePOMapper crashRatePOMapper;

    @Resource
    private IDauService dauService;


    @Transactional
    @Override
    public void extractData4Day(LocalDate day) {
        String dayStr = day.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        JSONObject params = new JSONObject();
        params.put("start_date", dayStr);
        params.put("end_date", dayStr);
        params.put("orgId", "-11");
        String encodedParams = UrlUtils.encode(params.toJSONString());
        List<CompletableFuture<List<CrashRatePO>>> futures = Arrays.stream(WidgetEnum.values())
                .map(widget -> CompletableFuture.supplyAsync(() -> queryCrashRate4EachWidget(dayStr, encodedParams, widget)))
                .collect(Collectors.toList());
        List<CrashRatePO> crashRatePOS = futures.stream()
                .map(CompletableFuture::join)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        crashRatePOMapper.batchInsert(crashRatePOS);
    }

    private List<CrashRatePO> queryCrashRate4EachWidget(String dayStr, String encodedParams, WidgetEnum widget) {
        String url = URL + widget.getWidget() + "/data?params=" + encodedParams;
        JSONObject crashRes = HttpUtils.doGet(url, JSONObject.class, ImmutableMap.of("Cookie", "Metrics_ssoid=" + SsoUtils.getSsoId()));
        JSONArray datas = crashRes.getJSONObject("data").getJSONArray("data");
        return datas.stream()
                .map(o -> (JSONArray) o)
                .filter(arr -> arr.getString(1).equals(widget.getMatchField()))
                .map(arr -> {
                    CrashRatePO crashRatePO = new CrashRatePO();
                    crashRatePO.setCrash(arr.getInteger(2));
                    crashRatePO.setPlantform(widget.getPlantform());
                    Long dau=dauService.getWeekDaus(widget.getPlantform(),widget.getPlantfrom_2(),dayStr,dayStr);
                    int int_dau=dau.intValue();
                    crashRatePO.setDau(int_dau);
                    crashRatePO.setDau(12);
                    double crashRate=((double)arr.getInteger(2)/int_dau)*100;
                    crashRate=Double.parseDouble(String.format("%5f",crashRate));
                    String crashRateStr=crashRate+"%";
                    crashRatePO.setCrashRate(crashRateStr);
                    crashRatePO.setStartDate(dayStr);
                    crashRatePO.setEndDate(dayStr);
                    crashRatePO.setShowDateRange(dayStr + " ~ " + dayStr);
                    Date now = new Date();
                    crashRatePO.setCreatedAt(now);
                    crashRatePO.setUpdatedAt(now);
                    return crashRatePO;
                }).collect(Collectors.toList());
    }

    enum WidgetEnum {
        MEITUAN_IOS("美团iOS","美团", "widget-93ae6fb8-50db-4eae-bf3f-ff2a014ffe4f", "餐饮北京"),
        MEITUAN_ANDROID("美团Android","美团", "widget-0d28990b-2cd0-4628-8e0a-363dc5f9ba98", "美食频道"),
        DIANPING_IOS("点评iOS","点评","widget-7402ba2a-16f2-4dd3-984e-6dc56df381ec", "餐饮(北京)"),
        DIANPING_ANDROID("点评Android","点评", "widget-5d0b1da5-3ff0-48d2-a9af-b94402cb02e5", "餐饮(北京)");

        private String plantform;
        private String plantfrom_2;
        private String widget;
        private String matchField;


        WidgetEnum(String plantform, String plantfrom_2, String widget, String matchField) {
            this.plantform = plantform;
            this.plantfrom_2=plantfrom_2;
            this.widget = widget;
            this.matchField = matchField;
        }

        public String getPlantform() {
            return plantform;
        }


        public String getPlantfrom_2() {
            return plantfrom_2;
        }

        public String getWidget() {
            return widget;
        }

        public String getMatchField() {
            return matchField;
        }
    }
}
