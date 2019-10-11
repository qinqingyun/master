package com.meituan.food.web;

import com.meituan.food.mapper.ApiCoverageDetailP0Mapper;
import com.meituan.food.mapper.ApiDetailPOMapper;
import com.meituan.food.mapper.AppkeyAdminPOMapper;
import com.meituan.food.mapper.AppkeyListPOMapper;
import com.meituan.food.po.ApiCoverageDetailP0;
import com.meituan.food.po.ApiDetailPO;
import com.meituan.food.po.AppkeyListPO;
import com.meituan.food.web.vo.ApiCoverageForDateVO;
import com.meituan.food.web.vo.CommonResponse;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/apicoverage")
public class ApiCoverageDateController {
    @Resource
    private ApiCoverageDetailP0Mapper apiCoverageDetailP0Mapper;

    @Resource
    private ApiDetailPOMapper apiDetailPOMapper;

    @Resource
    private AppkeyAdminPOMapper appkeyAdminPOMapper;

    @Resource
    private AppkeyListPOMapper appkeyListPOMapper;

    @GetMapping("")
    public CommonResponse<List<ApiCoverageForDateVO>> getTwoDateCoverage(@Param("appkey") String appkey, @Param("firstdate") String firstdate, @Param("seconddate") String seconddate,@RequestParam("diff") String diff) {
        List<ApiDetailPO> apiDetailPOS = apiDetailPOMapper.selectCoreApiByAppkey(appkey);
        List<ApiCoverageForDateVO> apiCoverageForDateVOS = new ArrayList<>();
        List<ApiCoverageForDateVO> diffApiCoverageForDateVOS = new ArrayList<>();
        List<ApiCoverageDetailP0> firstP0s = new ArrayList<>();
        List<ApiCoverageDetailP0> secondPOs = new ArrayList<>();
        for (ApiDetailPO apiDetailPO : apiDetailPOS) {
            ApiCoverageDetailP0 firstApiCoverageDetailP0 = apiCoverageDetailP0Mapper.selectByDateAndApi(firstdate, appkey, apiDetailPO.getApiSpanName());
            ApiCoverageDetailP0 secondApiCoverageDetailPO = apiCoverageDetailP0Mapper.selectByDateAndApi(seconddate, appkey, apiDetailPO.getApiSpanName());
            if (firstApiCoverageDetailP0 == null && secondApiCoverageDetailPO != null) {
                ApiCoverageForDateVO apiCoverageForDateVO = new ApiCoverageForDateVO();
                apiCoverageForDateVO.setAppkey(appkey);
                apiCoverageForDateVO.setApiSpanName(apiDetailPO.getApiSpanName());
                apiCoverageForDateVO.setFirstDateCoverStatus(false);
                apiCoverageForDateVO.setSecondDateCoverStatus(secondApiCoverageDetailPO.getIsCover());
                apiCoverageForDateVOS.add(apiCoverageForDateVO);
                secondPOs.add(secondApiCoverageDetailPO);
                diffApiCoverageForDateVOS.add(apiCoverageForDateVO);
            } else if (firstApiCoverageDetailP0 != null && secondApiCoverageDetailPO == null) {
                ApiCoverageForDateVO apiCoverageForDateVO = new ApiCoverageForDateVO();
                apiCoverageForDateVO.setAppkey(appkey);
                apiCoverageForDateVO.setApiSpanName(apiDetailPO.getApiSpanName());
                apiCoverageForDateVO.setFirstDateCoverStatus(firstApiCoverageDetailP0.getIsCover());
                apiCoverageForDateVO.setSecondDateCoverStatus(false);
                apiCoverageForDateVOS.add(apiCoverageForDateVO);
                firstP0s.add(firstApiCoverageDetailP0);
                diffApiCoverageForDateVOS.add(apiCoverageForDateVO);
            } else if (firstApiCoverageDetailP0 != null && secondApiCoverageDetailPO != null) {
                ApiCoverageForDateVO apiCoverageForDateVO = new ApiCoverageForDateVO();
                apiCoverageForDateVO.setAppkey(appkey);
                apiCoverageForDateVO.setApiSpanName(apiDetailPO.getApiSpanName());
                apiCoverageForDateVO.setFirstDateCoverStatus(firstApiCoverageDetailP0.getIsCover());
                apiCoverageForDateVO.setSecondDateCoverStatus(secondApiCoverageDetailPO.getIsCover());
                apiCoverageForDateVOS.add(apiCoverageForDateVO);
                firstP0s.add(firstApiCoverageDetailP0);
                secondPOs.add(secondApiCoverageDetailPO);
            } else {
                ApiCoverageForDateVO apiCoverageForDateVO = new ApiCoverageForDateVO();
                apiCoverageForDateVO.setAppkey(appkey);
                apiCoverageForDateVO.setApiSpanName(apiDetailPO.getApiSpanName());
                apiCoverageForDateVO.setFirstDateCoverStatus(false);
                apiCoverageForDateVO.setSecondDateCoverStatus(false);
                apiCoverageForDateVOS.add(apiCoverageForDateVO);
            }
        }
        if (diff.equals("true")){
            if (diffApiCoverageForDateVOS.size()==0){
                return CommonResponse.errorRes("两天数据相同");
            }
            return CommonResponse.successRes("成功",diffApiCoverageForDateVOS);
        }else {
            if (firstP0s.size()!=0&&secondPOs.size()!=0){
                return CommonResponse.successRes("成功", apiCoverageForDateVOS);
            }else if (firstP0s.size() == 0&&secondPOs.size()!=0) {
                return CommonResponse.successRes(firstdate + "无数据",apiCoverageForDateVOS);
            } else if (firstP0s.size()!=0&&secondPOs.size() == 0) {
                return CommonResponse.successRes(seconddate + "无数据",apiCoverageForDateVOS);
            } else {
                return CommonResponse.successRes( "两天均无数据",apiCoverageForDateVOS);
            }
        }

    }

    @GetMapping("/person")
    public CommonResponse<List<ApiCoverageForDateVO>> getDataByMis(@Param("mis") String mis, @Param("firstdate") String firstdate, @Param("seconddate") String seconddate,@RequestParam("diff") String diff){
        List<String> appkeyList = appkeyAdminPOMapper.selectByMis(mis);
        if (appkeyList.size()!=0) {
            List<ApiCoverageForDateVO> apiCoverageForDateVOS = new ArrayList<>();
            for (String appkey : appkeyList) {
                AppkeyListPO appkeyListPO = appkeyListPOMapper.selectByAppKey(appkey);
                if (appkeyListPO.getOffline()==0){
                    CommonResponse<List<ApiCoverageForDateVO>> twoDateCoverage = getTwoDateCoverage(appkey, firstdate, seconddate, diff);
                    if (twoDateCoverage.getCode() == 0) {
                        apiCoverageForDateVOS.addAll(twoDateCoverage.getData());
                    }
                }
            }
            if (diff.equals("true")) {
                if (apiCoverageForDateVOS.size() == 0) {
                    return CommonResponse.errorRes("两天数据相同");
                }
            }
            return CommonResponse.successRes("成功", apiCoverageForDateVOS);
        }
        return CommonResponse.errorRes("Mis:"+mis+" 无关联的服务！");
    }
}
