package com.meituan.food.extract.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import com.meituan.food.extract.IOneWeekCrashExtract;
import com.meituan.food.extract.IOneWeekEightDataExtract;
import com.meituan.food.mapper.WeekCCrashRatePOMapper;
import com.meituan.food.po.WeekCCrashRatePO;
import com.meituan.food.service.IDauService;
import com.meituan.food.utils.HttpUtils;
import com.meituan.food.utils.SsoUtils;
import com.meituan.food.utils.UrlUtils;
import com.meituan.food.utils.YunTuBa;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Component
public class WeekCCrashExtracter implements IOneWeekCrashExtract {

    //   private static final String URL = "https://yuntu.sankuai.com/api/widget/";

    private static final String URL = "https://yuntu.sankuai.com/api/open/v1/widget/";

    @Resource
    private WeekCCrashRatePOMapper weekCCrashRatePOMapper;

    @Resource
    private IDauService dauService;

    @Transactional
    @Override
    public void extractData4Week(LocalDate firstDay, LocalDate lastDay) {
        int next = Period.between(firstDay, lastDay).getDays();
        String firstDayStr = firstDay.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String lastDayStr = lastDay.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        String dateRange = firstDayStr + "~" + lastDayStr;
        JSONObject params = new JSONObject();
        params.put("start_date", firstDayStr);
        params.put("end_date", lastDayStr);
        params.put("orgId", "100047");
        params.put("dateDim", "DAY_DATE");
        params.put("env", "prod");
        List<CompletableFuture<List<WeekCCrashRatePO>>> futures = Arrays.stream(WeekCCrashExtracter.WidgetEnum.values())
                .map(widget -> CompletableFuture.supplyAsync(() -> queryCrashRate4EachWidget(firstDayStr, lastDayStr, params, widget, next)))
                .collect(Collectors.toList());
        List<WeekCCrashRatePO> crashRatePOS = futures.stream()
                .map(CompletableFuture::join)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        weekCCrashRatePOMapper.batchInsert(crashRatePOS);
    }

    private List<WeekCCrashRatePO> queryCrashRate4EachWidget(String firstDayStr, String lastDayStr, JSONObject params, WeekCCrashExtracter.WidgetEnum widget, int dayNum) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = YunTuBa.getAuthHeader("/api/open/v1/widget/" + widget.getWidget() + "/data/page", "POST");
        MultiValueMap<String, Object> urlVariables = new LinkedMultiValueMap();
        urlVariables.add("widgetId", widget.getWidget());
        urlVariables.add("params", params);
        urlVariables.add("dashKey", widget.getDashKey());
        urlVariables.add("index", 1);
        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity(urlVariables, headers);
        JSONObject response = restTemplate.postForEntity(URL + widget.getWidget() + "/data/page", httpEntity, JSONObject.class).getBody();
        System.out.println(response.toString());

        JSONArray datas = response.getJSONObject("data").getJSONObject("resData").getJSONArray("data");
        return datas.stream()
                .map(o -> JSON.parseArray(JSONObject.toJSONString(o)))
                .filter(arr -> arr.getString(1).equals(widget.getMatchField()))
                .map(arr -> {
                    WeekCCrashRatePO crashRatePO = new WeekCCrashRatePO();
                    crashRatePO.setCrash(arr.getInteger(2));
                    crashRatePO.setPlantform(widget.getPlatform());
                    Long dau = dauService.getWeekDaus(widget.getDauOs(), widget.getDauPartitionApp(), firstDayStr, lastDayStr);
                    int int_dau = dau.intValue();
                    crashRatePO.setDau(int_dau);
                    if (int_dau == 0) {
                        crashRatePO.setCrashRate("异常");
                    } else {
                        BigDecimal crashNum = new BigDecimal(arr.getInteger(2));
                        BigDecimal crashRate = crashNum.divide(new BigDecimal(int_dau), 8, RoundingMode.HALF_UP);
                        BigDecimal permillCrashRate = crashRate.movePointRight(3);

                        String crashRateStr = permillCrashRate.toPlainString() + "‰";
                        crashRatePO.setCrashRate(crashRateStr);
                        crashRatePO.setFinalRate(permillCrashRate);

                    }

                    crashRatePO.setStartDate(firstDayStr);
                    crashRatePO.setEndDate(lastDayStr);
                    crashRatePO.setShowDateRange(firstDayStr + " ~ " + lastDayStr);
                    Date now = new Date();
                    crashRatePO.setCreatedAt(now);
                    crashRatePO.setUpdatedAt(now);
                    if (dayNum > 9) {
                        crashRatePO.setFlag(1);
                    } else {
                        crashRatePO.setFlag(0);
                    }
                    return crashRatePO;
                }).collect(Collectors.toList());
    }

    public enum WidgetEnum {
        MEITUAN_IOS("美团iOS", "widget-93ae6fb8-50db-4eae-bf3f-ff2a014ffe4f", "餐饮北京", "ios", "mt_main_app", "dashboard-2fdfb6fd-244a-40ef-b944-851433e68d59"),
        MEITUAN_ANDROID("美团Android", "widget-0d28990b-2cd0-4628-8e0a-363dc5f9ba98", "美食频道", "android", "mt_main_app", "dashboard-2fdfb6fd-244a-40ef-b944-851433e68d59"),
        DIANPING_IOS("点评iOS", "widget-7402ba2a-16f2-4dd3-984e-6dc56df381ec", "餐饮(北京)", "ios", "dp_main_app", "dashboard-2fdfb6fd-244a-40ef-b944-851433e68d59"),
        DIANPING_ANDROID("点评Android", "widget-5d0b1da5-3ff0-48d2-a9af-b94402cb02e5", "餐饮(北京)", "android", "dp_main_app", "dashboard-2fdfb6fd-244a-40ef-b944-851433e68d59");

        private String platform;
        private String widget;
        private String matchField;
        private String dauOs;
        private String dauPartitionApp;
        private String dashKey;

        WidgetEnum(String platform, String widget, String matchField, String dauOs, String dauPartitionApp, String dashKey) {
            this.platform = platform;
            this.widget = widget;
            this.matchField = matchField;
            this.dauOs = dauOs;
            this.dauPartitionApp = dauPartitionApp;
            this.dashKey = dashKey;
        }

        public String getPlatform() {
            return platform;
        }

        public void setPlatform(String platform) {
            this.platform = platform;
        }

        public String getWidget() {
            return widget;
        }

        public void setWidget(String widget) {
            this.widget = widget;
        }

        public String getMatchField() {
            return matchField;
        }

        public void setMatchField(String matchField) {
            this.matchField = matchField;
        }

        public String getDauOs() {
            return dauOs;
        }

        public void setDauOs(String dauOs) {
            this.dauOs = dauOs;
        }

        public String getDauPartitionApp() {
            return dauPartitionApp;
        }

        public void setDauPartitionApp(String dauPartitionApp) {
            this.dauPartitionApp = dauPartitionApp;
        }

        public String getDashKey() {
            return dashKey;
        }

        public void setDashKey(String dashKey) {
            this.dashKey = dashKey;
        }
    }

}
