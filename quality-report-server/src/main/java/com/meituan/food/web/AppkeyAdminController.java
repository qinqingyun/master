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
import java.util.List;
import java.util.Spliterator;

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
        if (appkeyListPO==null){
            return "该Appkey（"+appkey+"）不存在，请联系郭孟瑶(guomengyao)！";
        }else {
            po.setAppkeyId(appkeyListPO.getId());
            Date now=new Date();
            po.setCreatedTime(now);
            po.setUpdatedTime(now);
            appkeyAdminPOMapper.insert(po);
            return "Appkey="+appkey+",管理员="+name+"已关联成功";
        }
    }

    @GetMapping("/update")
    public String updateAppkeyAdmin(@RequestParam("appkey") String appkey,@RequestParam("name") String name){
        Date now=new Date();
        AppkeyAdminPO appkeyAdminPO = appkeyAdminPOMapper.selectByAppkey(appkey);
        if (appkeyAdminPO==null){
            return "该Appkey（"+appkey+"）不存在,请直接使用insert方法！";
        }else {
            appkeyAdminPOMapper.updateByAppkey(appkey,name,now);
            return "修改成功！Appkey="+appkey+",管理员="+name+"已关联成功";
        }
    }

    @GetMapping("/check")
    public String checkAppkey(@RequestParam("name") String name){
        List<String> nameList = appkeyAdminPOMapper.selectByMis(name);
        if (nameList.size()==0){
            return "管理员："+name+" 无关联服务";
        }else {
            String resp="管理员："+name+" 关联的服务如下：\n\n";
            int index=1;
            for (String s : nameList) {
                resp=resp+index+"."+s+"\n\n";
                index++;
            }
            return resp;
        }
    }
}
