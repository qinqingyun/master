package com.meituan.food.web;

import com.meituan.food.extract.IGetApiDetailExtract;
import com.meituan.food.mapper.ApiCoverStatusTableMapper;
import com.meituan.food.mapper.ApiDetailPOMapper;
import com.meituan.food.po.ApiCoverStatusTable;
import com.meituan.food.web.vo.ApiVO;
import com.meituan.food.web.vo.CommonResponse;
import io.swagger.util.Json;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.joda.time.DateTime;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Slf4j
@RestController
@RequestMapping("/api")
public class ApiDetailController {
    @Resource
    private IGetApiDetailExtract apiDetailExtract;

    @Resource
    private ApiDetailPOMapper apiDetailPOMapper;

    @Resource
    private ApiCoverStatusTableMapper apiCoverStatusTableMapper;

   /* @GetMapping("/insert")
    public String insertApi(){
        apiDetailExtract.getApiDetail();
        return "OK!";
    }*/

    @GetMapping("/update")
    public String updateApiStatus(@Param("appkey") String appkey,@Param("api") String api){
        Date now=new Date();
        apiDetailPOMapper.updateByAppkeyAndApi(api,appkey,now);
        return "OK!";
    }

    @GetMapping("/update/noncore")
    public String updateApiStatusToNoncore(@Param("appkey") String appkey,@Param("api") String api){
        Date now=new Date();
        apiDetailPOMapper.updateToNoncoreByAppkeyAndApi(api,appkey,now);
        return "OK!";
    }

    @PostMapping(value = "/updateApiCoreMark")
    public CommonResponse updateApiCoreMark(@RequestBody ArrayList<ApiVO> data) {
        Date now=new Date();
        for(int i = 0 ;i < data.size(); i++) {
            if(data.get(i).getIsCore() == 0 && data.get(i).getIsCovered() == 1){
                return CommonResponse.errorRes("只有核心接口才可标记为已覆盖，请确认数据");//bad param
            }
            if(data.get(i).getIsCore() == 1){
                apiDetailPOMapper.updateByAppkeyAndApi(data.get(i).getApiName(), data.get(i).getAppkey(), now);
            }else{
                apiDetailPOMapper.updateToNoncoreByAppkeyAndApi(data.get(i).getApiName(), data.get(i).getAppkey(), now);
            }
            ApiCoverStatusTable record = new ApiCoverStatusTable();
            record.setApiName(data.get(i).getApiName());
            record.setAppkey(data.get(i).getAppkey());
            if(data.get(i).getIsCovered() == 1 && apiCoverStatusTableMapper.isNull(record) == 0) {
                apiCoverStatusTableMapper.insert(record);
            }else if(data.get(i).getIsCovered() == 0 && apiCoverStatusTableMapper.isNull(record) > 0) {
                apiCoverStatusTableMapper.deleteByApiName(data.get(i).getAppkey(), data.get(i).getApiName());
            }
        }

        return CommonResponse.successRes("保存成功","");
    }
}
