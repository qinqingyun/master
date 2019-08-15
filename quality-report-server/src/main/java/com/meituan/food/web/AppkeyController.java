package com.meituan.food.web;

import com.meituan.food.extract.IGetAppkeyList;
import com.meituan.food.extract.IReleaseNameExtract;
import com.meituan.food.mapper.AppkeyListPOMapper;
import com.meituan.food.mapper.ReleaseNamePOMapper;
import com.meituan.food.po.AppkeyListPO;
import com.meituan.food.po.AppkeyListPOExample;
import com.meituan.food.po.ReleaseNamePO;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.meituan.food.web.vo.Node;
import com.alibaba.fastjson.*;

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
    public String updateToOff(@Param("appkey") String appkey) {
        Date now = new Date();
        appkeyListPOMapper.updateToOffByAppkey(appkey, now);
        AppkeyListPO appkeyListPO = appkeyListPOMapper.selectByAppKey(appkey);
        releaseNamePOMapper.deleteBySrv(appkeyListPO.getSrv());
        return "OK!";
    }

    @GetMapping("/update/on")
    public String updateToOn(@Param("appkey") String appkey) {
        Date now = new Date();
        appkeyListPOMapper.updateToOnByAppkey(appkey, now);
        AppkeyListPO appkeyListPO = appkeyListPOMapper.selectByAppKey(appkey);
        releaseNameExtract.insertReleaseName(appkeyListPO.getSrv());
        return "OK!";
    }


    @GetMapping("/update/noncore")
    public String updateAppkeyNonRank(@RequestParam("appkey") String appkey) {
        Date now = new Date();
        appkeyListPOMapper.updateToNonCore(appkey, now);
        return "OK!";
    }

    @GetMapping("/update/core")
    public String updateAppkeyRank(@RequestParam("appkey") String appkey) {
        Date now = new Date();
        appkeyListPOMapper.updateToCore(appkey, now);
        return "OK!";
    }

    @GetMapping("/update/department")
    public String updateAppkeyDepartment(@RequestParam("appkey") String appkey, @RequestParam("departmentid") int departmentid, @RequestParam("departmentid2") int departmentid2) {
        Date now = new Date();
        appkeyListPOMapper.updateDepartment(appkey, now, departmentid, departmentid2);
        return "OK!";
    }

    @GetMapping("/selectByDepartment")
    public List<String> selectByDepartment(@RequestParam("departmentId2") Integer departmentId2) {
        List<String> allAppkey = appkeyListPOMapper.selectByDepartment_id_2(departmentId2);
        return allAppkey;
    }

    @GetMapping("/selectByDepartmentPage")
    public List<String> selectByDepartmentPage(@RequestParam("departmentId2") Integer departmentId2, @RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        List<String> allAppkey = appkeyListPOMapper.selectByDepartmentPage(departmentId2, pageNum, pageSize);
        return allAppkey;
    }

    @GetMapping("/countOfAppkey")
    public Integer selectCountByDepartment(@RequestParam("departmentId2") Integer departmentId2) {
        return appkeyListPOMapper.selectCountByDepartment(departmentId2);
    }

    @GetMapping("/getOwt")
    public String getOwt() {
        List<String> allOwt = appkeyListPOMapper.selectOwt();
        return JSON.toJSONString(allOwt);
    }

    @GetMapping("/getPdl")
    public String getPdl() {
        List<String> allOwt = appkeyListPOMapper.selectOwt();
        List<Node> nodes = new ArrayList<Node>();
        for(int i = 0; i < allOwt.size();i++){
            List<String> pdl = appkeyListPOMapper.selectPdl(allOwt.get(i));
            List<Node> nodeI = new ArrayList<Node>();
            for(int j = 0; j < pdl.size(); j++){
                nodeI.add(new Node(pdl.get(j), null));
            }
            nodes.add(new Node(allOwt.get(i), nodeI));
        }
        return JSON.toJSONString(nodes);
    }
    @GetMapping("/selectByPdl")
    public List<String> selectByPdl(@RequestParam("pdl") String pdl) {
        List<String> allAppkey = appkeyListPOMapper.selectByPdl(pdl);
        return allAppkey;
    }
}