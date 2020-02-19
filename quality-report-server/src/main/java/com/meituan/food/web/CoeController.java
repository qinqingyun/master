package com.meituan.food.web;

import com.meituan.food.extract.ICOEDataExtract;
import com.meituan.food.extract.ICargoDataPushExtract;
import com.meituan.food.mapper.CoeListP0Mapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.ParseException;

@Slf4j
@RestController
@RequestMapping("/coe")
public class CoeController {
    @Resource
    private ICOEDataExtract coeDataExtract;

    @Resource
    private CoeListP0Mapper coeListP0Mapper;

    @Resource(name = "COEPush")
    private ICargoDataPushExtract cargoDataPushExtract;

    @GetMapping("/update")
    public String updateHistoryData(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate) throws ParseException {
        try {
            coeDataExtract.getCOEData(startDate, endDate);
        } catch (Exception e) {
            log.error("updateHistoryData error, startDate: {},endDate: {}", startDate, endDate, e);
        }
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

    @GetMapping("/delete")
    public String deleteCoe(@RequestParam("coeId") int coeId) {
        int flag = coeListP0Mapper.deleteByCoeId(coeId);
        if (flag == 1) {
            return "删除成功";
        }
        return "删除失败";
    }

    @GetMapping("/push")
    public String pushCoe() {
        cargoDataPushExtract.pushData();
        return "OK!";
    }
}
