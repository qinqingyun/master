package com.meituan.food.web;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import com.meituan.food.mapper.ApiCoveragePOMapper;
import com.meituan.food.mapper.ApiDetailPOMapper;
import com.meituan.food.mapper.BatchDatePOMapper;
import com.meituan.food.mapper.DepartmentApiCoveragePOMapper;
import com.meituan.food.po.ApiDetailPO;
import com.meituan.food.utils.HttpUtils;
import com.meituan.food.web.vo.ApiCoverageStatusVO;
import com.meituan.food.web.vo.CommonResponse;
import com.meituan.food.web.vo.CrashRateVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/coverage")
public class ApiCoverageController {
    @Resource
    private ApiCoveragePOMapper apiCoveragePOMapper;

    @Resource
    private DepartmentApiCoveragePOMapper departmentApiCoveragePOMapper;

    @Resource
    private BatchDatePOMapper batchDatePOMapper;

    @Resource
    private ApiDetailPOMapper apiDetailPOMapper;

    private static final String url = "http://jacocolive.meishi.test.sankuai.com/public/getApiCoverageData?appkey=";

    @GetMapping("delete")
    public String deleteData(@Param("first") int first,@Param("second") int second){
        apiCoveragePOMapper.deleteByTwoKey(first,second);
        return "OK!";
    }

    @GetMapping("/delete/department")
    public String deleteDepartmentData(@Param("first") int first,@Param("second") int second){
        departmentApiCoveragePOMapper.deleteByTwoKey(first,second);
        return "OK!";
    }

    @GetMapping("/delete/batch")
    public String deleteDepartmentData(@Param("first") int first){
        batchDatePOMapper.deleteByPrimaryKey(first);
        return "OK!";
    }

    @GetMapping("/getData")
    public CommonResponse<List<ApiCoverageStatusVO>> getCoverageData(@Param("appkey") String appkey, @Param("core") String core){
        List<ApiDetailPO> apiDetailPOS=new ArrayList<>();
        if (core.equals("true")){
            apiDetailPOS = apiDetailPOMapper.selectCoreApiByAppkey(appkey);
        }else if (core.equals("false")){
            apiDetailPOS = apiDetailPOMapper.selectByAppkey(appkey);
        }else {
            return CommonResponse.errorRes("参数错误！");
        }
        List<String> coverApiNameList=new ArrayList<>();
        List<ApiCoverageStatusVO> apiCoverageStatusVOS=new ArrayList<>();

        JSONObject resp = HttpUtils.doGet(url + appkey, JSONObject.class, ImmutableMap.of());
        int code = resp.getInteger("code");
        if (code == 0){
            JSONObject respData = resp.getJSONObject("data");
            JSONArray apiDetailArray = respData.getJSONObject("totalApiCoverage").getJSONArray("apiDetail");
            for (Object o : apiDetailArray) {
                boolean cover = ((JSONObject) o).getBoolean("cover");
                if (cover==true){
                    coverApiNameList.add(((JSONObject) o).getString("apiSpanName"));
                }
            }
            for (ApiDetailPO apiDetailPO : apiDetailPOS) {
                ApiCoverageStatusVO vo=new ApiCoverageStatusVO();
                int isCore=apiDetailPO.getIsCore();
                if (isCore==1){
                    vo.setCore(true);
                }else {
                    vo.setCore(false);
                }
                vo.setApiSpanName(apiDetailPO.getApiSpanName());
                if (coverApiNameList.contains(apiDetailPO.getApiSpanName())){
                 vo.setCover(true);
                }else {
                    vo.setCover(false);
                }
                apiCoverageStatusVOS.add(vo);
            }

            return CommonResponse.successRes("成功",apiCoverageStatusVOS);
        }

        return CommonResponse.errorRes("无数据");

    }
}
