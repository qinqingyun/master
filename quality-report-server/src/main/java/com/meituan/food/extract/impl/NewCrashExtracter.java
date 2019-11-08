package com.meituan.food.extract.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import com.meituan.food.mapper.NewCrashP0Mapper;
import com.meituan.food.mapper.RestaurantDauMapper;
import com.meituan.food.po.NewCrashP0;
import com.meituan.food.po.RestaurantDau;
import com.meituan.food.utils.HttpUtils;
import com.meituan.food.utils.UrlUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class NewCrashExtracter {

    @Resource
    private NewCrashP0Mapper newCrashP0Mapper;

    @Resource
    private RestaurantDauMapper restaurantDauMapper;

    private static final String crashUrl="https://crash.sankuai.com/new/api/crash/count?access_token=5a7914027be9024a11dd1fb2&type=crash&project=";
    private static final String crashRateUrl="https://crash.sankuai.com/new/api/crash/ratio?access_token=5a7914027be9024a11dd1fb2&type=crash&project=";

    public void sync(){
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - 1);
        Date lastDay=calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");

        String firstDayStr=sdf.format(getStartOfDay(lastDay));
        String lastDayStr=sdf.format(getEndOfDay(lastDay));
        String dayStr=sdf1.format(lastDay);

        for (ProjectEnum project:ProjectEnum.values()){
            String value=project.getEq();
            NewCrashP0 po=new NewCrashP0();
            po.setPlatform(project.getApp());
            po.setOs(project.getOs());
            po.setFlag(0);
            if (value.equals("")){
                String crashPartUrl=project.getPlatform()+"&start="+UrlUtils.encode(firstDayStr)+"&end="+UrlUtils.encode(lastDayStr);
                JSONObject resp=HttpUtils.doGet(crashUrl+crashPartUrl,JSONObject.class,ImmutableMap.of());
                JSONArray respArr=resp.getJSONArray("data");
                JSONObject crashJson=(JSONObject) respArr.get(0);
                po.setCrash(crashJson.getInteger("y"));
                po.setDateRange(crashJson.getString("x"));
                po.setCrashDate(crashJson.getDate("x"));
                po.setCreatedTime(date);
                JSONObject crashRateJson=HttpUtils.doGet(crashRateUrl+crashPartUrl,JSONObject.class,ImmutableMap.of());
                JSONArray crashRateArr=crashRateJson.getJSONArray("data");
                JSONObject rateJson=(JSONObject) crashRateArr.get(0);
                po.setCrashRate(rateJson.getBigDecimal("y").multiply(new BigDecimal("10000")));
            }else {
                String crashPartUrl=project.getPlatform()+"&start="+UrlUtils.encode(firstDayStr)+"&end="+UrlUtils.encode(lastDayStr)+"&eq="+UrlUtils.encode(value);
                JSONObject resp=HttpUtils.doGet(crashUrl+crashPartUrl,JSONObject.class,ImmutableMap.of());
                JSONArray respArr=resp.getJSONArray("data");
                JSONObject crashJson=(JSONObject) respArr.get(0);
                int crash=crashJson.getInteger("y");
                po.setCrash(crash);
                String dateStr=crashJson.getString("x");
                po.setDateRange(dateStr);
                po.setCrashDate(crashJson.getDate("x"));
                po.setCreatedTime(date);

                List<RestaurantDau> dauList;
                BigDecimal crashRate = null;
                if (project.getOs().equals("iOS")){
                    dauList = restaurantDauMapper.getDauListByOsPartitionAppAndPartitionDateRang("ios", "mt_main_app", dayStr,dayStr);
                }else {
                    dauList = restaurantDauMapper.getDauListByOsPartitionAppAndPartitionDateRang("android", "mt_main_app", dayStr,dayStr);
                }
                if (dauList.size()!=0){
                    long cateringDau = dauList.get(0).getCateringDau();
                    if (cateringDau!=0){
                        double result = crash * 10000.0 / (double)cateringDau;
                        crashRate=new BigDecimal(result);
                    }
                }else {
                    crashRate=new BigDecimal("0");
                }
                po.setCrashRate(crashRate);
            }
            newCrashP0Mapper.insert(po);

        }
    }

    public static Date getEndOfDay(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());;
        LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
        return Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }

    // 获得某天最小时间 2017-10-15 00:00:00
    public static Date getStartOfDay(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
        LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
        return Date.from(startOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }

    public enum ProjectEnum{
        B_IOS("开店宝","iOS","group_e",""),
        B_ANDROID("开店宝","Android","merchant-android",""),
        DPGJ_IOS("点评管家","iOS","dpmerchant_ios",""),
        DPGJ_ANDROID("点评管家","Android","_dpmerchant_android",""),
        WMSJ_IOS("外卖商家","iOS","waimai_e_ios",""),
        WMSJ_ANDROID("外卖商家","Android","waimai_e_android",""),
        MOMA_IOS("MOMA","iOS","moma_ios",""),
        MOMA_ANDROID("MOMA","Android","momaandroid",""),
        BEE_IOS("蜜蜂","iOS","waimai_mfe_bee_ios",""),
        BEE_ANDROID("蜜蜂","Android","waimai_mfe_bee_android",""),
        MT_IOS("美团","iOS","meituan","optional_biz_group,group_food"),
        MT_ANDROID("美团","Android","android_platform_monitor","optional_channel,group_food");

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
