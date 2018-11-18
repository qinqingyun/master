package com.meituan.food.web;


import com.meituan.food.mapper.CrashRatePOMapper;
import com.meituan.food.po.CrashRatePO;
import com.meituan.food.web.vo.CommonResponse;
import com.meituan.food.web.vo.CrashRateVO;
import com.meituan.food.web.vo.CrashVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("")
public class CrashRateController {

    @Resource
    private CrashRatePOMapper crashRatePOMapper;

    @GetMapping("/crash/{plantform}/{os}/{startDate}/{endDate}")
    public CommonResponse<List<CrashVO>> getCrashs(@RequestParam("plantform") String plantform, @RequestParam("os") String os, @RequestParam("startDate") String startDate,@RequestParam("endDate") String endDate){

        List<CrashRatePO> crashListByOsAndDate = crashRatePOMapper.getCrashListByOsAndDate(plantform, startDate, endDate);

        List<CrashVO>  crashVOList=new ArrayList<>();
        CrashVO crashVO=new CrashVO();
        for(int i=0;i<crashListByOsAndDate.size();i++){

            crashVO.setPlantform(crashListByOsAndDate.get(i).getPlantform());
            crashVO.setStartDate(crashListByOsAndDate.get(i).getStartDate());
            crashVO.setEndDate(crashListByOsAndDate.get(i).getEndDate());
            crashVO.setCrash(crashListByOsAndDate.get(i).getCrash());

            crashVOList.add(crashVO);
        }
        if(crashVOList.size()==0){
            return CommonResponse.errorRes("无数据");
        }
        return CommonResponse.successRes("success",crashVOList);
    }

    @GetMapping("/crash_rate/{plantform}/{os}/{startDate}/{endDate}")
    public CommonResponse<List<CrashRateVO>> getCrashRates(@RequestParam("plantfrom") String plantform,@RequestParam("os") String os,@RequestParam("startDate") String startDate,@RequestParam("endDate") String endDate){
        List<CrashRatePO> crashRatePOList=crashRatePOMapper.getCrashListByOsAndDate(plantform,startDate,endDate);

        List<CrashRateVO> crashRateVOList=new ArrayList<>();
        CrashRateVO crashRateVO=new CrashRateVO();

        for(int i=0;i<crashRatePOList.size();i++){
            crashRateVO.setPlantform(crashRatePOList.get(i).getPlantform());
            crashRateVO.setStartDate(crashRatePOList.get(i).getStartDate());
            crashRateVO.setEndDate(crashRatePOList.get(i).getEndDate());
            crashRateVO.setCrash(crashRatePOList.get(i).getCrash());
            crashRateVO.setCrashRate(crashRatePOList.get(i).getCrashRate());

            crashRateVOList.add(crashRateVO);
        }

        if(crashRateVOList.size()==0){
            return CommonResponse.errorRes("无数据");
        }

        return CommonResponse.successRes("success",crashRateVOList);

    }



}
