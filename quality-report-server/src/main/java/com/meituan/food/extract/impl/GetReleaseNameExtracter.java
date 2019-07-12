package com.meituan.food.extract.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import com.meituan.food.extract.IReleaseNameExtract;
import com.meituan.food.mapper.AppkeyListPOMapper;
import com.meituan.food.mapper.ReleaseNamePOMapper;
import com.meituan.food.po.ReleaseNamePO;
import com.meituan.food.utils.HttpUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Component
public class GetReleaseNameExtracter implements IReleaseNameExtract {
    
    @Resource
    private AppkeyListPOMapper appkeyListPOMapper;

    @Resource
    private ReleaseNamePOMapper releaseNamePOMapper;

    public static final String url="http://ops.vip.sankuai.com/api/v0.2/srvs/";
    public static final String partUrl="/plus?detail=0";
    
    @Override
    public void sync() {
        List<String> allSrv = appkeyListPOMapper.selectAllSrv();
        Date now=new Date();
        for (String srv : allSrv) {
            ReleaseNamePO po=new ReleaseNamePO();
            JSONObject resp=HttpUtils.doGet(url+srv+partUrl,JSONObject.class,ImmutableMap.of("Authorization","Bearer 960526c96313d1cf42b6c3c36751ef931ecac858"));
            JSONArray plus = resp.getJSONArray("plus");
            if (plus==null||plus.size()==0) {
                continue;
            }else {
                String releaseName = plus.get(0).toString();
                po.setReleaseName(releaseName);
                po.setSrv(srv);
                po.setCreatedTime(now);
                po.setUpdatedTime(now);
                releaseNamePOMapper.insert(po);
            }

        }
    }

    public static void main(String[] args) {
        JSONObject resp=HttpUtils.doGet(url+"meituan.web.meishic.fooddebug"+partUrl,JSONObject.class,ImmutableMap.of("Authorization","Bearer 960526c96313d1cf42b6c3c36751ef931ecac858"));
        JSONArray plus = resp.getJSONArray("plus");
        if (plus==null){
            System.out.println("这条数据为空");
        }
        System.out.println(resp.toString());
    }
}
