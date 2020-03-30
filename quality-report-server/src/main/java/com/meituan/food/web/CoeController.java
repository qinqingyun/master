package com.meituan.food.web;

import com.meituan.food.extract.ICOEDataExtract;
import com.meituan.food.extract.ICargoDataPushExtract;
import com.meituan.food.mapper.CoeListPOMapper;
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
    private CoeListPOMapper coeListPOMapper;

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
        int flag = coeListPOMapper.updateCoeToInvalidByCoeId(coeId);
        if (flag == 1) {
            return "更新成功";
        }
        return "更新失败";
    }

    @GetMapping("valid")
    public String updateCoeToValid(@RequestParam("coeId") int coeId) {
        int flag = coeListPOMapper.updateCoeToValidByCoeId(coeId);
        if (flag == 1) {
            return "更新成功";
        }
        return "更新失败";
    }

    @GetMapping("/delete")
    public String deleteCoe(@RequestParam("coeId") int coeId) {
        int flag = coeListPOMapper.deleteByCoeId(coeId);
        if (flag == 1) {
            return "删除成功";
        }
        return "删除失败";
    }

    @GetMapping("/push")
    public String pushCoe() throws ParseException {
        cargoDataPushExtract.pushData();
        return "OK!";
    }

    @GetMapping("/updatebusiness")
    public String updateBusiness(@RequestParam("coeId") int coeId,@RequestParam("business") String business){
        int flag = coeListPOMapper.updateBusiness(coeId, business);
        if (flag == 1) {
            return "更新成功";
        }
        return "更新失败";
    }

    @GetMapping("/updateorder")
    public String updateOrder(@RequestParam("coeId") int coeId,@RequestParam("order") int order){
        int flag = coeListPOMapper.updateOrder(coeId, order);
        if (flag == 1) {
            return "更新成功";
        }
        return "更新失败";
    }

    @GetMapping("/updatemoney")
    public String updateMoney(@RequestParam("coeId") int coeId,@RequestParam("money") int money){
        int flag = coeListPOMapper.updateMoney(coeId, money);
        if (flag == 1) {
            return "更新成功";
        }
        return "更新失败";
    }
}
