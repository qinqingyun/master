package com.meituan.food.web;


import com.meituan.food.mapper.CrashRatePOMapper;
import com.meituan.food.po.CrashRatePO;
import com.meituan.food.service.IDauService;
import com.meituan.food.web.vo.CommonResponse;
import com.meituan.food.web.vo.CrashRateVO;
import com.meituan.food.web.vo.CrashVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("")
public class CrashRateController {

    @Resource
    private CrashRatePOMapper crashRatePOMapper;

    @Resource
    private IDauService dauService;

   // @GetMapping("/crash/{platform}/{startDate}/{endDate}")
    @GetMapping(value = "/crash")
    public CommonResponse<List<CrashVO>> getCrashs(@RequestParam("platform") String platform, @RequestParam("startDate") String startDate,@RequestParam("endDate") String endDate){

        List<CrashRatePO> crashListByOsAndDate = crashRatePOMapper.getCrashListByOsAndDate(platform, startDate, endDate);

        List<CrashVO>  crashVOList=new ArrayList<>();
        CrashVO crashVO;

        for (CrashRatePO crashRatePO : crashListByOsAndDate) {
            crashVO=new CrashVO();
            crashVO.setPlantform(crashRatePO.getPlantform());
            crashVO.setStartDate(crashRatePO.getStartDate());
            crashVO.setEndDate(crashRatePO.getEndDate());
            crashVO.setCrash(crashRatePO.getCrash());

            crashVOList.add(crashVO);
        }

        if(crashVOList.size()==0){
            return CommonResponse.errorRes("无数据");
        }
        return CommonResponse.successRes("success",crashVOList);
    }

    //@GetMapping("/crash_rate/{platform}/{startDate}/{endDate}")
    @GetMapping(value ="/crash_rate")
    public CommonResponse<List<CrashRateVO>> getCrashRates(@RequestParam("platform") String platform,@RequestParam("startDate") String startDate,@RequestParam("endDate") String endDate){
        List<CrashRatePO> crashRatePOList=crashRatePOMapper.getCrashListByOsAndDate(platform,startDate,endDate);

        List<CrashRateVO> crashRateVOList=new ArrayList<>();
        CrashRateVO crashRateVO;

        for (CrashRatePO crashRatePO : crashRatePOList) {
            crashRateVO=new CrashRateVO();
            crashRateVO.setPlatform(crashRatePO.getPlantform());
            crashRateVO.setStartDate(crashRatePO.getStartDate());
            crashRateVO.setEndDate(crashRatePO.getEndDate());
            crashRateVO.setCrash(crashRatePO.getCrash());
            crashRateVO.setCrashRate(crashRatePO.getCrashRate());

            crashRateVOList.add(crashRateVO);
        }

        if(crashRateVOList.size()==0){
            return CommonResponse.errorRes("无数据");
        }

        return CommonResponse.successRes("success",crashRateVOList);

    }

    @GetMapping(value = "/dau")
    public long getDau(@RequestParam("os") String os,@RequestParam("platform") String platform,@RequestParam("startDate") String startDate,@RequestParam("endDate") String endDate){


        Map<String,String> platformMap=new HashMap<String, String>();
        platformMap.put("美团","mt_main_app");
        platformMap.put("点评","dp_main_app");


        return  dauService.getWeekDaus(os,platformMap.get(platform),startDate,endDate);
    }

}
