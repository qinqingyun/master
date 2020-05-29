package com.meituan.qa.meishi.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.dianping.lion.client.api.LionAPI;
import com.dianping.lion.client.api.Response;
import com.dianping.lion.client.api.service.ConfigService;
import com.meituan.toolchain.mario.config.ConfigMange;

/**
 * Created by liyuhua on 02/12/2019.
 */
public class LionUtil {
    public static String lionUsername = "meishi-hui-autoTest";
    public static String lionPassword = "UMSIO9JKYK";
    public static String userBlackList = "hui-mapi-web.create_order_black_list";
    public static String userWriteList = "hui-mapi-web.create_order_white_list";//新平台为主下单白名单，走以新为主老回放，如果同一个user在黑白名单都存在，user走白名单
    public static String env  = ConfigMange.getTestData();

    /**
     * 修改用户黑名单，黑名单用户走以老为主新回放
     * @param userid, userid格式是userid_platfromid, 点评=0, 美团=1
     * @throws Exception
     */
    public static void setUserBlackList(String userid) throws Exception{
        LionAPI lionAPI = LionAPI.createBuilder().setName(lionUsername).setPassword(lionPassword).build();
        ConfigService configService = lionAPI.create(ConfigService.class);

        //如果该用户在白名单中，需要先删除
        Response response = configService.getByKey(env, userWriteList,null,null);
        JSONArray jsonArray= JSON.parseArray(response.getValue());
        if( jsonArray.contains(userid)) {
            jsonArray.remove(userid);
            configService.set(env, userWriteList, jsonArray.toString(), null, null);
        }

        response = configService.getByKey(env, userBlackList,null,null);
        jsonArray = JSON.parseArray(response.getValue());
        if( !jsonArray.contains(userid)) {
            jsonArray.add(userid);
            response = configService.set(env, userBlackList, jsonArray.toString(), null, null);
            if(response.hasError())
                throw new Exception("设置用户黑名单失败，报错信息："+response.getError());
        }
        Thread.sleep(2000);//等待配置生效

    }

    /**
     * 修改用户白名单，白名单用户走以新为主老回放
     * @param userid, userid格式是userid_platfromid, 点评=0, 美团=1
     * @throws Exception
     */
    public static void setUserWriteList(String userid) throws Exception {
        LionAPI lionAPI = LionAPI.createBuilder().setName(lionUsername).setPassword(lionPassword).build();
        ConfigService configService = lionAPI.create(ConfigService.class);

        Response response = configService.getByKey(env, userWriteList,null,null);
        JSONArray jsonArray = JSON.parseArray(response.getValue());
        if( !jsonArray.contains(userid)) {
            jsonArray.add(userid);
            configService.set(env, userWriteList, jsonArray.toString(), null, null);
            if(response.hasError())
                throw new Exception("设置用户白名单失败，报错信息："+response.getError());
        }
        Thread.sleep(2000);//等待配置生效
    }
}
