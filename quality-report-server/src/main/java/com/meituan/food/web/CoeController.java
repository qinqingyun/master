package com.meituan.food.web;

import com.meituan.food.extract.ICOEDataExtract;
import com.meituan.food.mapper.CoeListP0Mapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/coe")
public class CoeController {
    @Resource
    private ICOEDataExtract coeDataExtract;

    @Resource
    private CoeListP0Mapper coeListP0Mapper;

    @GetMapping("/update")
    public String updateHistoryData(@RequestParam("startDate") String startDate,@RequestParam("endDate") String endDate){
        coeDataExtract.getCOEData(startDate,endDate);
        return "OK!";
    }

    @GetMapping("invalid")
    public String updateCoeToInvalid(@RequestParam("coeId") int coeId) {
        int flag = coeListP0Mapper.updateCoeToInvalidByCoeId(coeId);
        if (flag == 1) {
            return "更新成功";
        }
        return "更新失败";
    }

    @GetMapping("valid")
    public String updateCoeToValid(@RequestParam("coeId") int coeId) {
        int flag = coeListP0Mapper.updateCoeToValidByCoeId(coeId);
        if (flag == 1) {
            return "更新成功";
        }
        return "更新失败";
    }
}
