package com.meituan.food.web;

import com.meituan.food.extract.IGetApiDetailExtract;
import com.meituan.food.mapper.ApiDetailPOMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping("/api")
public class ApiDetailController {
    @Resource
    private IGetApiDetailExtract apiDetailExtract;

    @Resource
    private ApiDetailPOMapper apiDetailPOMapper;


    @GetMapping("/insert")
    public String insertApi(){
        apiDetailExtract.getApiDetail();
        return "OK!";
    }

    @GetMapping("/update")
    public String updateApiStatus(@Param("appkey") String appkey,@Param("api") String api){
        Date now=new Date();
        apiDetailPOMapper.updateByAppkeyAndApi(api,appkey,now);
        return "OK!";
    }

    @GetMapping("/update/status")
    public String updateStatus(){
        apiDetailExtract.setApiStatus();
        return "OK!";
    }
}
