package com.meituan.food.web;

import com.meituan.food.extract.IGetAppkeyList;
import com.meituan.food.extract.IReleaseNameExtract;
import com.meituan.food.mapper.*;
import com.meituan.food.po.ApiDetailPO;
import com.meituan.food.po.AppkeyAdminPO;
import com.meituan.food.po.AppkeyListPO;
import com.meituan.food.po.AppkeyListPOExample;
import com.meituan.food.po.ReleaseNamePO;
import com.meituan.food.web.vo.ApiVO;
import com.meituan.food.web.vo.AppkeyVO;
import com.meituan.food.web.vo.treeNodeVO;
import com.meituan.food.web.vo.CommonResponse;
import jdk.nashorn.internal.objects.NativeRangeError;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @Resource
    private AppkeyAdminPOMapper appkeyAdminPOMapper;

    @Resource
    private Org2EmpDataPOMapper org2EmpDataPOMapper;

    @Resource
    private ApiDetailPOMapper apiDetailPOMapper;

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
        List<treeNodeVO> nodes = new ArrayList<treeNodeVO>();
        for(int i = 0; i < allOwt.size();i++){
            List<String> pdl = appkeyListPOMapper.selectPdl(allOwt.get(i));
            treeNodeVO node = new treeNodeVO();
            List<treeNodeVO> children = new ArrayList<treeNodeVO>();
            int pdlSize = pdl.size();
            for(int j = 0; j < pdlSize; j++){
                treeNodeVO leaf = new treeNodeVO();
                leaf.setId(j + pdlSize * i);
                leaf.setLabel(pdl.get(j));
                leaf.setChildren(null);
                children.add(leaf);
            }
            node.setId(i);
            node.setLabel(allOwt.get(i));
            node.setChildren(children);
            nodes.add(node);
        }
        return JSON.toJSONString(nodes);
    }

    @GetMapping("/selectByPdl")
    public List<AppkeyVO> selectByPdl(@RequestParam("pdl") String pdl) {
        List<String> allAppkey = appkeyListPOMapper.selectByPdl(pdl);
        List<AppkeyVO> result = new ArrayList<AppkeyVO>();
        AppkeyController a = new AppkeyController();
        for(int i = 0; i < allAppkey.size();i++) {
            result.add(getAppkeyAdmin(allAppkey.get(i)));
        }
            return result;
    }

    public AppkeyVO getAppkeyAdmin(String appkey) {
        AppkeyVO appkeyVO = new AppkeyVO();
        AppkeyAdminPO appkeyAdminPO = appkeyAdminPOMapper.selectByAppkey(appkey);
        appkeyVO.setAppkey(appkey);
        if (appkeyAdminPO == null) {
            appkeyVO.setAdminMis("");
            appkeyVO.setAdminName("暂无负责人");
        } else {
            appkeyVO.setAdminMis(appkeyAdminPO.getAdminName());
            appkeyVO.setAdminName(org2EmpDataPOMapper.selectByMis(appkeyAdminPO.getAdminName()).getName());
        }
        return appkeyVO;
    }

    @GetMapping("/getAppkeyDetail")
    public AppkeyVO getAppkeyDetail(@RequestParam("appkey") String appkey){
        AppkeyVO appkeyVO=new AppkeyVO();
        AppkeyAdminPO appkeyAdminPO = appkeyAdminPOMapper.selectByAppkey(appkey);
        appkeyVO.setAppkey(appkey);
        if (appkeyAdminPO==null){
            appkeyVO.setAdminMis("");
            appkeyVO.setAdminName("暂无负责人");
        }else {
            appkeyVO.setAdminMis(appkeyAdminPO.getAdminName());
            appkeyVO.setAdminName(org2EmpDataPOMapper.selectByMis(appkeyAdminPO.getAdminName()).getName());
        }
        AppkeyListPO appkeyPO = appkeyListPOMapper.selectByAppKey(appkey);
        if (appkeyPO.getRank()==1){
            appkeyVO.setCoreSrv(true);
        }else {
            appkeyVO.setCoreSrv(false);
        }
        List<ApiDetailPO> apiDetailPOS = apiDetailPOMapper.selectByAppkey(appkey);
        if (apiDetailPOS.size()==0){
            return appkeyVO;
        }else {
            List<ApiVO> apiVOList=new ArrayList<>();
            for (ApiDetailPO apiDetailPO : apiDetailPOS) {
                ApiVO apiVO=new ApiVO();
                apiVO.setApiName(apiDetailPO.getApiSpanName());
                apiVO.setCallCount(apiDetailPO.getCallCount());
                apiVO.setIsCore(apiDetailPO.getIsCore());
                apiVO.setProportion(apiDetailPO.getProportion());
                apiVO.setUpdatedTime(apiDetailPO.getUpdatedAt());
                apiVOList.add(apiVO);
            }
            appkeyVO.setApiVOList(apiVOList);
            return appkeyVO;
        }
    }

    @GetMapping("/getApiList")
    public CommonResponse<List<ApiVO>> getApiList(@RequestParam("appkeyList") List<String> appkeyList){
        List<ApiVO> apiVOList=new ArrayList<>();
        for (String appkey : appkeyList) {
            List<ApiDetailPO> apiDetailPOS = apiDetailPOMapper.selectByAppkey(appkey);
            if (apiDetailPOS.size()!=0){
                for (ApiDetailPO apiDetailPO : apiDetailPOS) {
                    ApiVO apiVO=new ApiVO();
                    apiVO.setAppkey(appkey);
                    apiVO.setApiName(apiDetailPO.getApiSpanName());
                    apiVO.setUpdatedTime(apiDetailPO.getUpdatedAt());
                    apiVO.setCallCount(apiDetailPO.getCallCount());
                    apiVOList.add(apiVO);
                }
            }
        }
        if (apiVOList.size()==0){
            return CommonResponse.errorRes("无数据");
        }
        return CommonResponse.successRes("成功",apiVOList);
    }
}
