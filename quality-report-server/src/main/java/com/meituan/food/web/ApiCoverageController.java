package com.meituan.food.web;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import com.meituan.food.mapper.*;
import com.meituan.food.po.ApiDetailPO;
import com.meituan.food.po.AppkeyListPO;
import com.meituan.food.utils.HttpUtils;
import com.meituan.food.web.vo.ApiCoverageStatusVO;
import com.meituan.food.web.vo.CommonResponse;
import com.meituan.food.web.vo.CrashRateVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.boot.util.LambdaSafe;
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

    @Resource
    private AppkeyAdminPOMapper appkeyAdminPOMapper;

    @Resource
    private AppkeyListPOMapper appkeyListPOMapper;


    @Resource
    private LineCoverageP0Mapper lineCoverageP0Mapper;

    private String trHead = "<tr>";
    private String trTail = "</tr>";
    private String tdHead = "<td data-colwidth=\\\"694\\\" width=\\\"694\\\" style=\\\"background-color: rgb(255, 255, 255);\\\"><p>";
    private String tdTail = "</p></td>";
    private String emptyData = "<td data-colwidth=\\\"694\\\" width=\\\"694\\\" style=\\\"background-color: rgb(255, 255, 255);\\\"><p style=\\\"text-align: start;\\\"></p></td>";
    private String tableHead = "<table data-bordercolor=\\\"\\\\&quot;#cccccc\\\\&quot;\\\"><tbody><tr><th data-colwidth=\\\"61\\\" width=\\\"61\\\" style=\\\"background-color: rgb(246, 246, 246);\\\"><p>负责QA</p></th><th data-colwidth=\\\"354\\\" width=\\\"354\\\" style=\\\"background-color: rgb(246, 246, 246);\\\"><p>appKey</p></th><th data-colwidth=\\\"694\\\" width=\\\"694\\\" style=\\\"background-color: rgb(246, 246, 246);\\\"><p>上一周覆盖率数据（取当天往前第7天的数据）</p></th><th data-colwidth=\\\"411\\\" width=\\\"411\\\" style=\\\"background-color: rgb(246, 246, 246);\\\"><p>本周覆盖率数据（取当天数据）</p></th><th data-colwidth=\\\"392\\\" width=\\\"392\\\" style=\\\"background-color: rgb(246, 246, 246);\\\"><p>本周主要工作</p></th><th data-colwidth=\\\"392\\\" width=\\\"392\\\" style=\\\"background-color: rgb(246, 246, 246);\\\"><p>备注</p></th></tr>";
    private String tableTail = "</tbody></table><p><br></p><p></p>";

    private static final String url = "http://jacocolive.meishi.test.sankuai.com/public/getApiCoverageData?appkey=";

    @GetMapping("delete")
    public String deleteData(@Param("first") int first, @Param("second") int second) {
        apiCoveragePOMapper.deleteByTwoKey(first, second);
        return "OK!";
    }

    @GetMapping("/delete/department")
    public String deleteDepartmentData(@Param("first") int first, @Param("second") int second) {
        departmentApiCoveragePOMapper.deleteByTwoKey(first, second);
        return "OK!";
    }

    @GetMapping("/delete/batch")
    public String deleteDepartmentData(@Param("first") int first) {
        batchDatePOMapper.deleteByPrimaryKey(first);
        return "OK!";
    }


    @GetMapping("/getData")
    public CommonResponse<List<ApiCoverageStatusVO>> getCoverageData(@Param("appkey") String appkey, @Param("core") String core) {
        List<ApiDetailPO> apiDetailPOS = new ArrayList<>();
        if (core.equals("true")) {
            apiDetailPOS = apiDetailPOMapper.selectCoreApiByAppkey(appkey);
        } else if (core.equals("false")) {
            apiDetailPOS = apiDetailPOMapper.selectByAppkey(appkey);
        } else {
            return CommonResponse.errorRes("参数错误！");
        }
        List<String> coverApiNameList = new ArrayList<>();
        List<ApiCoverageStatusVO> apiCoverageStatusVOS = new ArrayList<>();

        JSONObject resp = HttpUtils.doGet(url + appkey, JSONObject.class, ImmutableMap.of());
        int code = resp.getInteger("code");
        if (code == 0) {
            JSONObject respData = resp.getJSONObject("data");
            JSONArray apiDetailArray = respData.getJSONObject("totalApiCoverage").getJSONArray("apiDetail");
            for (Object o : apiDetailArray) {
                boolean cover = ((JSONObject) o).getBoolean("cover");
                if (cover == true) {
                    coverApiNameList.add(((JSONObject) o).getString("apiSpanName"));
                }
            }
            for (ApiDetailPO apiDetailPO : apiDetailPOS) {
                ApiCoverageStatusVO vo = new ApiCoverageStatusVO();
                int isCore = apiDetailPO.getIsCore();
                if (isCore == 1) {
                    vo.setCore(true);
                } else {
                    vo.setCore(false);
                }
                vo.setApiSpanName(apiDetailPO.getApiSpanName());
                vo.setAppkey(appkey);
                if (coverApiNameList.contains(apiDetailPO.getApiSpanName())) {
                    vo.setCover(true);
                } else {
                    vo.setCover(false);
                }
                apiCoverageStatusVOS.add(vo);
            }

            return CommonResponse.successRes("成功", apiCoverageStatusVOS);
        }

        return CommonResponse.errorRes("无数据");

    }

    @GetMapping("/person/data")
    public CommonResponse<List<ApiCoverageStatusVO>> getPersonalData(@Param("mis") String mis) {
        List<String> appkeyList = appkeyAdminPOMapper.selectByMis(mis);
        List<String> coverApiNameList = new ArrayList<>();
        List<ApiCoverageStatusVO> apiCoverageStatusVOS = new ArrayList<>();
        if (appkeyList.size() != 0) {
            for (String appkey : appkeyList) {
                AppkeyListPO appkeyListPO = appkeyListPOMapper.selectByAppKey(appkey);
                if (appkeyListPO.getOffline()==0){
                    List<ApiDetailPO> apiDetailPOS = apiDetailPOMapper.selectCoreApiByAppkey(appkey);
                    JSONObject resp = HttpUtils.doGet(url + appkey, JSONObject.class, ImmutableMap.of());
                    int code = resp.getInteger("code");
                    if (code == 0) {
                        JSONObject respData = resp.getJSONObject("data");
                        JSONArray apiDetailArray = respData.getJSONObject("totalApiCoverage").getJSONArray("apiDetail");
                        for (Object o : apiDetailArray) {
                            boolean cover = ((JSONObject) o).getBoolean("cover");
                            if (cover == true) {
                                coverApiNameList.add(((JSONObject) o).getString("apiSpanName"));
                            }
                        }
                        for (ApiDetailPO apiDetailPO : apiDetailPOS) {
                            ApiCoverageStatusVO vo = new ApiCoverageStatusVO();
                            vo.setCore(true);
                            vo.setApiSpanName(apiDetailPO.getApiSpanName());
                            vo.setAppkey(appkey);
                            if (!(coverApiNameList.contains(apiDetailPO.getApiSpanName()))) {
                                vo.setCover(false);
                                apiCoverageStatusVOS.add(vo);
                            }
                        }
                    }
                }
            }
        }
        if (apiCoverageStatusVOS.size()!=0){
            return CommonResponse.successRes("成功", apiCoverageStatusVOS);
        }
        return CommonResponse.errorRes("无数据");
    }
}
