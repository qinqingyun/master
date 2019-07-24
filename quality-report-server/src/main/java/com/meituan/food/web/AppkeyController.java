package com.meituan.food.web;

import com.meituan.food.extract.IGetAppkeyList;
import com.meituan.food.extract.IReleaseNameExtract;
import com.meituan.food.mapper.AppkeyListPOMapper;
import com.meituan.food.mapper.ReleaseNamePOMapper;
import com.meituan.food.po.AppkeyListPO;
import com.meituan.food.po.ReleaseNamePO;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;


@RestController
@RequestMapping("/appkey")
public class AppkeyController {

    @Resource
    private IGetAppkeyList appkeyList;

    @Resource
    private AppkeyListPOMapper appkeyListPOMapper;

    @Resource
    private ReleaseNamePOMapper releaseNamePOMapper;

    @Resource
    private IReleaseNameExtract releaseNameExtract;

   /* @GetMapping("/insert")
    public String insertAppkey(){
        appkeyList.getAppkeyList();
        return "OK!";
    }*/

    @GetMapping("/update/off")
    public String updateToOff(@Param("appkey") String appkey){
        Date now=new Date();
        appkeyListPOMapper.updateToOffByAppkey(appkey,now);
        AppkeyListPO appkeyListPO = appkeyListPOMapper.selectByAppKey(appkey);
        releaseNamePOMapper.deleteBySrv(appkeyListPO.getSrv());
        return "OK!";
    }

    @GetMapping("/update/on")
    public String updateToOn(@Param("appkey") String appkey){
        Date now=new Date();
        appkeyListPOMapper.updateToOnByAppkey(appkey,now);
        AppkeyListPO appkeyListPO = appkeyListPOMapper.selectByAppKey(appkey);
        releaseNameExtract.insertReleaseName(appkeyListPO.getSrv());
        return "OK!";
    }


    @GetMapping("/update/noncore")
    public String updateAppkeyNonRank(@RequestParam("appkey") String appkey){
        Date now=new Date();
        appkeyListPOMapper.updateToNonCore(appkey,now);
        return "OK!";
    }

    @GetMapping("/update/core")
    public String updateAppkeyRank(@RequestParam("appkey") String appkey){
        Date now=new Date();
        appkeyListPOMapper.updateToCore(appkey,now);
        return "OK!";
    }

    @GetMapping("/update/department")
    public String updateAppkeyDepartment(@RequestParam("appkey") String appkey,@RequestParam("departmentid") int departmentid,@RequestParam("departmentid2") int departmentid2){
        Date now=new Date();
        appkeyListPOMapper.updateDepartment(appkey,now,departmentid,departmentid2);
        return "OK!";
    }
}
