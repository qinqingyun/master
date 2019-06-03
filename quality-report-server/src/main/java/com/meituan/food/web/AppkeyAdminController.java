package com.meituan.food.web;

import com.meituan.food.mapper.AppkeyAdminPOMapper;
import com.meituan.food.mapper.AppkeyListPOMapper;
import com.meituan.food.po.AppkeyAdminPO;
import com.meituan.food.po.AppkeyListPO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping("/appkeyadmin")
public class AppkeyAdminController {

    @Resource
    private AppkeyAdminPOMapper appkeyAdminPOMapper;

    @Resource
    private AppkeyListPOMapper appkeyListPOMapper;

    @GetMapping("/insert")
    public String insertAppkeyAdmin(@RequestParam("appkey") String appkey,@RequestParam("name") String name){
        AppkeyAdminPO po=new AppkeyAdminPO();
        po.setAdminName(name);
        po.setAppkey(appkey);
        AppkeyListPO appkeyListPO=appkeyListPOMapper.selectByAppKey(appkey);
        po.setAppkeyId(appkeyListPO.getId());
        Date now=new Date();
        po.setCreatedTime(now);
        po.setUpdatedTime(now);
        appkeyAdminPOMapper.insert(po);
        return "OK!";
    }

    @GetMapping("/update")
    public String updateAppkeyAdmin(@RequestParam("appkey") String appkey,@RequestParam("name") String name){
        Date now=new Date();
        appkeyAdminPOMapper.updateByAppkey(appkey,name,now);
        return "OK!";
    }
}
