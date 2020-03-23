package com.meituan.food.extract.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.meituan.food.extract.IOneDayCargoExtract;
import com.meituan.food.mapper.CargoDataPOMapper;
import com.meituan.food.po.CargoDataPO;
import com.meituan.food.utils.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Slf4j
@Component
public class CargoExtracter implements IOneDayCargoExtract {

    private static final String APPKEY = "Ynmyab4ITPqlTdcWLhPofQ";
    private static final String APPSECRET = "XYEo0r3ZQ0i3RBtkLFgS_gBF4hevp0SBeNiKRF1EHPWw";

    private static String cargo_host = "http://api.cargo.sankuai.com";
    private static String availble_uri = "/stack?type=stack_monitor&stack_uuid={stack_uuid}&recent_days=3&interval=1d&monitor_type=service_available_check";
    private static String stable_uri = "/stack?type=stack_monitor&stack_uuid={stack_uuid}&recent_days=3&interval=1d";

    @Resource
    private CargoDataPOMapper cargoDataPOMapper;

    private static Map<String, String> person = new HashMap<String, String>();
    private static Map<String, String> direct = new HashMap<String, String>();
    private static Map<String, String> stackuuid = new HashMap<String, String>();

    private Date mYesterday;


    @Override
    public void updateCargoAva(LocalDate day) {

        person.clear();
        direct.clear();
        stackuuid.clear();
        {
            stackuuid.put("到餐信息", "41e67c3e-0084-474d-810f-a63ec44bb21b");

            stackuuid.put("到餐交易", "3d3b2fbf-98df-4055-aded-6f6036062f86");
            stackuuid.put("到餐促销", "3d6d5203-01f8-4af6-86a4-62cdbd9c6798");

            stackuuid.put("到餐预订", "53580e3d-2246-4978-a21d-39fd74939d4d");


            stackuuid.put("到餐CRM", "cdb4e9e9-cd71-45d9-8d35-77eaa1910149");

            stackuuid.put("到餐商家", "168f940e-397d-4eb5-8979-988d3c3be4fa");

            stackuuid.put("到餐数据", "6f665b72-2ae7-4861-8650-8e346141539c");
            stackuuid.put("到餐策略", "1f79cd74-afdc-450b-a3ff-c1a2672d1161");

            stackuuid.put("到餐SCP", "5ac864c0-d0e0-40cb-8585-1b09783ab08a");


            stackuuid.put("到餐点评", "e616e37b-c179-452f-bb2b-ce7d2e1dbdc6");


            stackuuid.put("到餐财务", "81fee706-1354-4a1f-91cb-d67270b6f61d");


            stackuuid.put("商家平台", "f5784acb-d30a-4a83-8046-de1212944317");

            stackuuid.put("商家增值平台-BP", "717b7b57-13bd-4e6e-a5d3-0bc3f4f83f98");


            stackuuid.put("客户平台", "b6f29059-baa9-44da-a063-73174e69a471");
            stackuuid.put("到综客户", "cc69efbc-7c5c-4e66-b460-2cb6198aa7c8");
            stackuuid.put("酒旅客户", "1b522725-fee4-4401-97fd-84c65b96e1e6");
            stackuuid.put("到餐TDC", "c80efd36-7b67-40a8-a53d-ba9ea804e66e");

            stackuuid.put("到综团购交易", "7f5f395d-9ff9-4481-a292-a3927e466109");



        }
        {
            person.put(stackuuid.get("到餐信息") + ".Deal", "hanye03");
            person.put(stackuuid.get("到餐信息") + ".Filter", "qinqingyun");
            person.put(stackuuid.get("到餐信息") + ".运营", "qinqingyun");
            person.put(stackuuid.get("到餐信息") + ".POI", "qinqingyun");
            person.put(stackuuid.get("到餐信息") + "._others", "qinqingyun");

            person.put(stackuuid.get("到餐交易") + "._others", "sunnaili");
            person.put(stackuuid.get("到餐交易") + ".到综团购", "sunnaili");
            person.put(stackuuid.get("到餐交易") + ".到餐交易", "sunnaili");
            person.put(stackuuid.get("到餐交易") + ".券码", "sunnaili");
            person.put(stackuuid.get("到餐交易") + ".退款", "sunnaili");

            person.put(stackuuid.get("到餐促销") + "._others", "konghongwei");

            person.put(stackuuid.get("到餐预订") + "._others", "buyuqi");
            person.put(stackuuid.get("到餐预订") + ".预订FE", "buyuqi");
            person.put(stackuuid.get("到餐预订") + ".预订C", "buyuqi");
            person.put(stackuuid.get("到餐预订") + ".预订B", "buyuqi");
            person.put(stackuuid.get("到餐预订") + ".预订M", "buyuqi");

            person.put(stackuuid.get("到餐CRM") + "._others", "liuxiangyi");

            person.put(stackuuid.get("到餐商家") + "._others", "zhangyancui");
            person.put(stackuuid.get("到餐商家") + ".商家BP", "zhangyancui");
            person.put(stackuuid.get("到餐商家") + ".商家fe", "zhangyancui");
            person.put(stackuuid.get("到餐商家") + ".基础平台", "zhangyancui");
            person.put(stackuuid.get("到餐商家") + ".增值平台", "zhangyancui");
            person.put(stackuuid.get("到餐商家") + ".运营平台", "zhangyancui");
            person.put(stackuuid.get("到餐商家") + ".增值平台", "zhangyancui");


            person.put(stackuuid.get("到餐数据") + "._others", "zhouke");

            person.put(stackuuid.get("到餐策略") + "._others", "hanye03");
            person.put(stackuuid.get("到餐策略") + ".C端依赖", "hanye03");

            person.put(stackuuid.get("到餐TDC") + "._others", "wanglan13");

            person.put(stackuuid.get("到综团购交易") + "._others", "liyuhua");
            person.put(stackuuid.get("到综团购交易") + ".到综团购", "liyuhua");


            person.put(stackuuid.get("到餐SCP") + "._others", "wangjiani");
            person.put(stackuuid.get("到餐SCP") + ".客户", "feilichao");
            person.put(stackuuid.get("到餐SCP") + ".上单", "wangjiani");
            person.put(stackuuid.get("到餐SCP") + ".代理商", "wangjiani");

            person.put(stackuuid.get("到餐点评") + "._others", "buyuqi");
            person.put(stackuuid.get("到餐点评") + ".促销", "konghongwei");
            person.put(stackuuid.get("到餐点评") + ".买单", "buyuqi");
            person.put(stackuuid.get("到餐点评") + ".列表", "qinqingyun");

            person.put(stackuuid.get("到餐财务") + "._others", "yingzhixun");
            person.put(stackuuid.get("到餐财务") + ".结款", "yingzhixun");
            person.put(stackuuid.get("到餐财务") + ".账户", "yingzhixun");
            person.put(stackuuid.get("到餐财务") + ".包销", "yingzhixun");
            person.put(stackuuid.get("到餐财务") + ".清算", "yingzhixun");
            person.put(stackuuid.get("到餐财务") + ".mis后台", "yingzhixun");
            person.put(stackuuid.get("到餐财务") + ".对账系统", "yingzhixun");
            person.put(stackuuid.get("到餐财务") + ".商家后台", "yingzhixun");
            person.put(stackuuid.get("到餐财务") + ".监控平台", "yingzhixun");

            person.put(stackuuid.get("商家平台") + ".业务应用技术组", "fengchen");
            person.put(stackuuid.get("商家平台") + ".公共能力研发组", "fengchen");
            person.put(stackuuid.get("商家平台") + ".商家运营平台", "huangguilin");
            person.put(stackuuid.get("商家平台") + ".商家基础平台", "zhangyancui");
            person.put(stackuuid.get("商家平台") + ".触达平台", "zhangyancui");
            person.put(stackuuid.get("商家平台") + "._others", "zhangyancui");


            person.put(stackuuid.get("商家增值平台-BP") + ".业务应用技术组", "tangwenchao");
            person.put(stackuuid.get("商家增值平台-BP") + ".公共能力研发组", "bei.guo");
            person.put(stackuuid.get("商家增值平台-BP") + "._others", "bei.guo");





            person.put(stackuuid.get("客户平台") + "._others", "xiongyiping");

            person.put(stackuuid.get("到综客户") + "._others", "zhouying");
            person.put(stackuuid.get("到综客户") + ".客户", "zhouying");

            person.put(stackuuid.get("酒旅客户") + "._others", "xiongyiping");
            person.put(stackuuid.get("酒旅客户") + ".住宿", "xiongyiping");
            person.put(stackuuid.get("酒旅客户") + ".度假", "xiongyiping");


        }


        {
            direct.put("wuqifang", "C端");
            direct.put("liyuhua", "C端");
            direct.put("wangyang60", "C端");
            direct.put("qinqingyun", "C端");
            direct.put("buyuqi", "C端");
            direct.put("wanglan13","TDC门店信息");
            direct.put("sunnaili","C端");
            direct.put("hanye03","C端");


            direct.put("liuxiangyi", "M端");
            direct.put("zhangyancui", "B端_北京");
            direct.put("zhouke", "M端");
            direct.put("wangjiani", "M端");
            direct.put("yingzhixun", "M端");
            direct.put("fengchen", "B端_上海");
            direct.put("chenyunyun", "B端_上海");
            direct.put("tangwenchao", "B端_上海");
            direct.put("xiongyiping", "M端");
            direct.put("zhouying", "M端");
            direct.put("bei.guo", "B端_上海");
            direct.put("wenwen", "B端_上海");
            direct.put("feilichao", "M端");
            direct.put("summer.sun", "B端_上海");
            direct.put("honghui.huang", "B端_上海");
            direct.put("huangguilin", "B端_北京");


        }
        stackuuid.forEach((k, v) -> {
            String stable_uri_new = stable_uri.replace("{stack_uuid}", v);
            String availble_uri_new = availble_uri.replace("{stack_uuid}", v);
            JSONObject stable_json = HttpUtils.doGetWithAuth(URI.create(cargo_host + stable_uri_new), JSONObject.class, APPKEY, APPSECRET);
            JSONObject availble_json = HttpUtils.doGetWithAuth(URI.create(cargo_host + availble_uri_new), JSONObject.class, APPKEY, APPSECRET);
            log.info("The cargo stable_uri_new is :{}", stable_uri_new);
            log.info("The cargo stable_json is :{}", stable_json);
            log.info("The cargo stable_json is :{}", stable_json);
            log.info("The cargo availble_uri_new is :{}", availble_uri_new);
            log.info("The cargo availble_json is :{}", availble_json);


            mYesterday = Date.from(LocalDate.now().minusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant());

            List<CargoDataPO> yesStableData = getYesterdayStableData(mYesterday, stable_json.toJSONString());

            List<CargoDataPO> finalData = getAvailbleData(yesStableData, mYesterday, availble_json.toJSONString());

            saveCargoDataPO(finalData);
        });


    }

    public String getKey(Map map, Object value) {
        String key = "";
        for (Object k : map.keySet()) {
            if (map.get(k).equals(value)) {
                key = k.toString();
            }
        }
        return key;
    }

    private List<CargoDataPO> getYesterdayStableData(Date yesterday, String stable_json) {
        int iter = getIndexByDay(yesterday, stable_json, "$.data._source[*]");
        log.info(stable_json);
        if (iter == -1) {
            return null;
        }

        final List<CargoDataPO> cps = new ArrayList<>();
        String tag_str = String.valueOf(JSONPath.read(stable_json, "$.data.tag"));
        JSONObject tag_json = JSONObject.parseObject(tag_str);

        for (String tag : tag_json.keySet()) {
            CargoDataPO cp = new CargoDataPO();
            cp.init();
            cp.setTag(tag);
            cp.setStackuuid(String.valueOf(JSONPath.read(stable_json, "$.data.stack.stackuuid")));
            String person_value = person.get(cp.getStackuuid() + "." + tag);
            if (person_value != null) {
                cp.setPerson(person_value);
            }

            cp.setUpdatedDate(new Date());


            cp.setDate(yesterday);

            cp.setDirection(direct.get(person_value));
            cp.setComment(getKey(stackuuid, String.valueOf(JSONPath.read(stable_json, "$.data.stack.stackuuid"))));


            String success = String.valueOf(JSONPath.read(stable_json, "$.data._source[" + iter + "]." + tag + ".success"));
            String error = String.valueOf(JSONPath.read(stable_json, "$.data._source[" + iter + "]." + tag + ".error"));
            String stableTagPercentage = String.valueOf(JSONPath.read(stable_json, "$.data.tag." + tag + "[" + iter + "]"));
            update(cp, "stableTagPercentage", stableTagPercentage);
            update(cp, "stableSuccess", success);
            update(cp, "stableTotal", error, success);

            cps.add(cp);

        }
        return cps;
    }


    public int getIndexByDay(Date day, String json, String path) {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String daystr = sdf.format(day);
        String source_str = String.valueOf(JSONPath.read(json, path));
        JSONArray source = JSONArray.parseArray(source_str);
        List<Map<String, Object>> tag_dates = (List) source;
        int index = -1;

        for (int i = 0; i < tag_dates.size(); i++) {

            if (tag_dates.get(i).values().toString().contains(daystr))//所需要date
            {
                index = i;
                break;
            }
        }
        return index;
    }

    public List<CargoDataPO> getAvailbleData(List<CargoDataPO> yesStableData, Date yesterday, String availble_json) {

        int iter = getIndexByDay(yesterday, availble_json, "$.data._source[*]");
        if (iter == -1) {
            return yesStableData;
        }
        if (yesStableData !=null) {

            for (CargoDataPO cp : yesStableData) {

                String tag = cp.getTag();
                String success = String.valueOf(JSONPath.read(availble_json, "$.data._source[" + iter + "]." + tag + ".success"));
                String error = String.valueOf(JSONPath.read(availble_json, "$.data._source[" + iter + "]." + tag + ".error"));
                update(cp, "avalibleSuccess", success);
                update(cp, "avalibleTotal", error, success);

                String availble_total_percentage = String.valueOf(JSONPath.read(availble_json, "$.data.tag." + tag + "[" + iter + "]"));
                update(cp, "avalibleTagPercentage", availble_total_percentage);

            }
        }

        return yesStableData;

    }


    public void update(CargoDataPO cargoDataPO, String para_key, String value) {
        if (value == null || value.isEmpty() || value.equals("null")) {
            return;
        }
        switch (para_key) {
            case "avalibleSuccess":
                cargoDataPO.setAvalibleSuccess(Integer.valueOf(value));
                break;
            case "avalibleTotal":
                cargoDataPO.setAvalibleTotal(Integer.valueOf(value));
                break;
            case "stableSuccess":
                cargoDataPO.setStableSuccess(Integer.valueOf(value));
                break;
            case "stableTotal":
                cargoDataPO.setStableTotal(Integer.valueOf(value));
                break;
            case "avalibleTagPercentage":
                cargoDataPO.setAvalibleTagPercentage(value);
                break;
            case "stableTagPercentage":
                cargoDataPO.setStableTagPercentage(value);
                break;

        }

    }

    public void update(CargoDataPO cargoDataPO, String para_key, String error, String success) {
        int value = 0;
        if (error != null && !error.isEmpty() && !error.equals("null")) {
            value = Integer.valueOf(error);
        }
        if (success != null && !success.isEmpty() && !success.equals("null")) {
            value += Integer.valueOf(success);
        }
        update(cargoDataPO, para_key, String.valueOf(value));

    }

    public void saveCargoDataPO(List<CargoDataPO> data) {

        if(data !=null) {
            for (CargoDataPO cargoDataPO : data) {
                List<CargoDataPO> oldData = cargoDataPOMapper.selectByTagAndCreatedateAndComment(cargoDataPO.getTag(), cargoDataPO.getDate(),cargoDataPO.getComment());
                int result = -1;
                if (oldData == null || oldData.size() == 0) {
                    result = cargoDataPOMapper.insert(cargoDataPO);
                } else {
                    result = cargoDataPOMapper.updateByTagAndDate(cargoDataPO);
                }

                log.info("The cargo save result is :{}", cargoDataPO.toString() + result);

            }
        }
    }
}