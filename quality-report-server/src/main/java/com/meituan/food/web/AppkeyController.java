package com.meituan.food.web;

import com.meituan.food.extract.IGetAppkeyList;
import com.meituan.food.extract.IReleaseNameExtract;
import com.meituan.food.mapper.*;
import com.meituan.food.po.ApiDetailPO;
import com.meituan.food.po.AppkeyAdminPO;
import com.meituan.food.po.AppkeyListPO;
import com.meituan.food.po.ReleaseNamePO;
import com.meituan.food.web.vo.ApiVO;
import com.meituan.food.web.vo.AppkeyVO;
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

    public AppkeyVO getAppkeyDetail(String appkey){
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
}
