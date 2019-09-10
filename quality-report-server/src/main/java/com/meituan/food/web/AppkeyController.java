package com.meituan.food.web;

import com.meituan.food.extract.IGetAppkeyList;
import com.meituan.food.extract.IReleaseNameExtract;
import com.meituan.food.mapper.*;
import com.meituan.food.po.ApiDetailPO;
import com.meituan.food.po.AppkeyAdminPO;
import com.meituan.food.po.AppkeyListPO;
import com.meituan.food.po.AppkeyListPOExample;
import com.meituan.food.po.ReleaseNamePO;
import com.meituan.food.web.vo.*;
import jdk.nashorn.internal.objects.NativeRangeError;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

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
    public List<String> getOwt() {
        List<String> allOwt = appkeyListPOMapper.selectOwt();
        return allOwt;
    }

    @GetMapping("/getPdl")
    public List<Node> getPdl() {
        List<String> allOwt = appkeyListPOMapper.selectOwt();
        List<Node> nodes = new ArrayList<Node>();
        for(int i = 0; i < allOwt.size(); i++){
            List<String> pdl = appkeyListPOMapper.selectPdl(allOwt.get(i));
            List<Node> children = new ArrayList<Node>();
            int pdlSize = pdl.size();
            for(int j = 0; j < pdlSize; j++){
                children.add(new Node(pdl.get(j),null));
            }
            nodes.add(new Node(allOwt.get(i),children));
        }
        return nodes;
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
        AppkeyListPO appkeyPO = appkeyListPOMapper.selectByAppKey(appkey);
        appkeyVO.setRank(appkeyPO.getRank());
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
        appkeyVO.setRank(appkeyPO.getRank());
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

    @PostMapping(value = "/updateAppkeyCoreMark")
    public CommonResponse updateAppkeyCoreMark(@RequestBody ArrayList<AppkeyVO> data) {
        Date now=new Date();
        for(int i = 0;i < data.size();i++) {
            if(data.get(i).getAdminMis() == "") continue;
            if(org2EmpDataPOMapper.selectByMis(data.get(i).getAdminMis()) == null){
                String errInfo = "mis号(" + data.get(i).getAdminMis() + ")有误，请检查后再提交！";
                return CommonResponse.errorRes(errInfo);
            }
        }
        for(int i = 0 ;i < data.size(); i++) {
            appkeyAdminPOMapper.updateByAppkey(data.get(i).getAppkey(),data.get(i).getAdminMis(),now);

            switch (data.get(i).getRank()){
                case 1:
                appkeyListPOMapper.updateToCore(data.get(i).getAppkey(), now);
                break;
                case 2:
                    appkeyListPOMapper.updateToNonCore(data.get(i).getAppkey(), now);
                    break;
                case 3:
                    appkeyListPOMapper.updateToP2Core(data.get(i).getAppkey(), now);
                    break;
                default:
                    appkeyListPOMapper.updateToNonCore(data.get(i).getAppkey(), now);
            }
        }

        return CommonResponse.successRes("保存成功","");
    }
}
