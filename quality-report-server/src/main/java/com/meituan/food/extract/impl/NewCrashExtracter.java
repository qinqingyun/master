package com.meituan.food.extract.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import com.meituan.food.extract.INewCrashExtract;
import com.meituan.food.mapper.NewCrashP0Mapper;
import com.meituan.food.mapper.RestaurantDauMapper;
import com.meituan.food.po.NewCrashP0;
import com.meituan.food.po.RestaurantDau;
import com.meituan.food.utils.HttpUtils;
import com.meituan.food.utils.UrlUtils;
import com.meituan.food.utils.YunTuBa;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.annotation.RequestScope;
import sun.rmi.server.InactiveGroupException;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Calendar;
import java.util.Date;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class NewCrashExtracter implements INewCrashExtract {

    @Resource
    private NewCrashP0Mapper newCrashP0Mapper;

    @Resource
    private RestaurantDauMapper restaurantDauMapper;

    private static final String crashUrl = "https://crash.sankuai.com/new/api/crash/count?access_token=5a7914027be9024a11dd1fb2&type=crash&project=";
    private static final String crashRateUrl = "https://crash.sankuai.com/new/api/crash/ratio?access_token=5a7914027be9024a11dd1fb2&type=crash&project=";
    private static final String URL = "https://yuntu.sankuai.com/api/open/v1/widget/";

    @Override
    public void sync(LocalDate lastDay) {

        ZonedDateTime zonedDateTime = lastDay.atStartOfDay(ZoneId.systemDefault());
        Date date = Date.from(zonedDateTime.toInstant());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE));
        Date lastDayDate = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");


        String firstDayStr = sdf.format(getStartOfDay(lastDayDate));
        String lastDayStr = sdf.format(getEndOfDay(lastDayDate));
        String dayStr = sdf1.format(lastDayDate);
        String dayStr2=sdf2.format(lastDayDate);

        for (ProjectEnum project : ProjectEnum.values()) {
            String value = project.getEq();
            NewCrashP0 po = new NewCrashP0();
            po.setPlatform(project.getApp());
            po.setOs(project.getOs());
            po.setFlag(0);
            po.setCreatedTime(new Date());
            if (value.equals("")) {
                String crashPartUrl = project.getPlatform() + "&start=" + UrlUtils.encode(firstDayStr) + "&end=" + UrlUtils.encode(lastDayStr);
                JSONObject resp = HttpUtils.doGet(crashUrl + crashPartUrl, JSONObject.class, ImmutableMap.of());
                JSONArray respArr = resp.getJSONArray("data");
                if (respArr.size()!=0){
                    JSONObject crashJson = (JSONObject) respArr.get(0);
                    po.setCrash(crashJson.getInteger("y"));
                    po.setDateRange(crashJson.getString("x"));
                    po.setCrashDate(crashJson.getDate("x"));
                    JSONObject crashRateJson = HttpUtils.doGet(crashRateUrl + crashPartUrl, JSONObject.class, ImmutableMap.of());
                    JSONArray crashRateArr = crashRateJson.getJSONArray("data");
                    JSONObject rateJson = (JSONObject) crashRateArr.get(0);
                    po.setCrashRate(rateJson.getBigDecimal("y").multiply(new BigDecimal("10000")));
                }else {
                    po.setCrash(0);
                }
            } else {
                List<RestaurantDau> dauList=null;
                BigDecimal crashRate = null;
                if (project.getApp().equals("美团")) {
                    String crashPartUrl = project.getPlatform() + "&start=" + UrlUtils.encode(firstDayStr) + "&end=" + UrlUtils.encode(lastDayStr) + "&eq=" + UrlUtils.encode(value);
                    JSONObject resp = HttpUtils.doGet(crashUrl + crashPartUrl, JSONObject.class, ImmutableMap.of());
                    JSONArray respArr = resp.getJSONArray("data");
                    if (respArr.size()!=0) {

                        JSONObject crashJson = (JSONObject) respArr.get(0);
                        int crash = crashJson.getInteger("y");
                        po.setCrash(crash);
                        String dateStr = crashJson.getString("x");
                        po.setDateRange(dateStr);
                        po.setCrashDate(crashJson.getDate("x"));

                        if (project.getOs().equals("iOS")) {
                            dauList = restaurantDauMapper.getDauListByOsPartitionAppAndPartitionDateRang("ios", "mt_main_app", dayStr, dayStr);
                        } else {
                            dauList = restaurantDauMapper.getDauListByOsPartitionAppAndPartitionDateRang("android", "mt_main_app", dayStr, dayStr);
                        }
                    }else {
                        po.setCrash(0);
                    }
                } else if (project.getApp().equals("点评")) {

                    JSONObject params = new JSONObject();
                    params.put("start_date", dayStr);
                    params.put("end_date", dayStr);
                    params.put("orgId", "100047");
                    params.put("dateDim", "DAY_DATE");
                    params.put("env", "prod");
                    po.setDateRange(dayStr2);
                    po.setCrashDate(lastDayDate);

                    RestTemplate restTemplate = new RestTemplate();
                    HttpHeaders headers = YunTuBa.getAuthHeader("/api/open/v1/widget/" + project.getPlatform() + "/data/page", "POST");
                    MultiValueMap<String, Object> urlVariables = new LinkedMultiValueMap();
                    urlVariables.add("widgetId", project.getPlatform());
                    urlVariables.add("params", params);
                    urlVariables.add("dashKey", project.getEq());
                    urlVariables.add("index", 1);
                    HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity(urlVariables, headers);
                    JSONObject response = restTemplate.postForEntity(URL + project.getPlatform() + "/data/page", httpEntity, JSONObject.class).getBody();

                    JSONArray datas = response.getJSONObject("data").getJSONObject("resData").getJSONArray("data");
                    for (Object data : datas) {
                        if (((JSONArray) data).getString(1).equals("餐饮(北京)")) {
                            int crash=((JSONArray) data).getInteger(2);
                            po.setCrash(crash);
                        }
                    }
                    if(po.getCrash()==null){
                        po.setCrash(0);
                    }
                    if (project.getOs().equals("iOS")){
                        dauList = restaurantDauMapper.getDauListByOsPartitionAppAndPartitionDateRang("ios", "dp_main_app", dayStr, dayStr);
                    }else {
                        dauList = restaurantDauMapper.getDauListByOsPartitionAppAndPartitionDateRang("android", "dp_main_app", dayStr, dayStr);
                    }
                }

                if (dauList.size() != 0) {
                    long cateringDau = dauList.get(0).getCateringDau();
                    if (cateringDau != 0) {
                        double result = po.getCrash() * 10000.0 / (double) cateringDau;
                        crashRate = new BigDecimal(result);
                    }
                } else {
                    crashRate = new BigDecimal("0");
                }
                po.setCrashRate(crashRate);
            }
            newCrashP0Mapper.insert(po);

        }
    }

    @Override
    public void syncForDays(Date startDate, Date endDate) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM");

        for (ProjectEnum project : ProjectEnum.values()) {
            List<NewCrashP0> newCrashP0s = newCrashP0Mapper.selectByDateAndPlatform(startDate, endDate,project.getApp(),project.getOs());
            if (newCrashP0s.size() != 0) {
                NewCrashP0 po=new NewCrashP0();
                int crash=0;
                BigDecimal crashRate=new BigDecimal(0);
                for (NewCrashP0 newCrashP0 : newCrashP0s) {
                    crash=crash+newCrashP0.getCrash();
                    crashRate=crashRate.add(newCrashP0.getCrashRate());
                }
                BigDecimal decimal = new BigDecimal(String.valueOf(newCrashP0s.size()));
                crashRate=crashRate.divide(decimal,5);
                po.setPlatform(project.getApp());
                po.setOs(project.getOs());
                po.setCrash(crash);
                po.setCrashRate(crashRate);
                po.setCreatedTime(new Date());
                if (newCrashP0s.size()<8){
                    po.setFlag(1);
                    po.setDateRange(sdf1.format(startDate)+"~"+sdf1.format(endDate));
                }else {
                    po.setFlag(2);
                    po.setDateRange(sdf2.format(startDate));
                }
               newCrashP0Mapper.insert(po);
            }
        }
    }

    public static Date getEndOfDay(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
        ;
        LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
        return Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }

    // 获得某天最小时间 2017-10-15 00:00:00
    public static Date getStartOfDay(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
        LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
        return Date.from(startOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }

    public enum ProjectEnum {
        B_IOS("开店宝", "iOS", "group_e", ""),
        B_ANDROID("开店宝", "Android", "merchant-android", ""),
        DPGJ_IOS("点评管家", "iOS", "dpmerchant_ios", ""),
        DPGJ_ANDROID("点评管家", "Android", "_dpmerchant_android", ""),
        WMSJ_IOS("外卖商家", "iOS", "waimai_e_ios", ""),
        WMSJ_ANDROID("外卖商家", "Android", "waimai_e_android", ""),
        MOMA_IOS("MOMA", "iOS", "moma_ios", ""),
        MOMA_ANDROID("MOMA", "Android", "momaandroid", ""),
        BEE_IOS("蜜蜂", "iOS", "waimai_mfe_bee_ios", ""),
        BEE_ANDROID("蜜蜂", "Android", "waimai_mfe_bee_android", ""),
  /*      MT_IOS("美团", "iOS", "meituan", "optional_biz_group,group_food"),
        MT_ANDROID("美团", "Android", "android_platform_monitor", "optional_channel,group_food"),*/
        DP_IOS("点评", "iOS", "widget-7402ba2a-16f2-4dd3-984e-6dc56df381ec", "dashboard-2fdfb6fd-244a-40ef-b944-851433e68d59"),
        DP_ANDROID("点评", "Android", "widget-5d0b1da5-3ff0-48d2-a9af-b94402cb02e5", "dashboard-2fdfb6fd-244a-40ef-b944-851433e68d59");

        private String app;
        private String os;
        private String platform;
        private String eq;

        ProjectEnum(String app, String os, String platform, String eq) {
            this.app = app;
            this.os = os;
            this.platform = platform;
            this.eq = eq;
        }

        public String getApp() {
            return app;
        }

        public void setApp(String app) {
            this.app = app;
        }

        public String getOs() {
            return os;
        }

        public void setOs(String os) {
            this.os = os;
        }

        public String getPlatform() {
            return platform;
        }

        public void setPlatform(String platform) {
            this.platform = platform;
        }

        public String getEq() {
            return eq;
        }

        public void setEq(String eq) {
            this.eq = eq;
        }
    }
}
