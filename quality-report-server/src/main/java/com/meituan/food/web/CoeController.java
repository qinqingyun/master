package com.meituan.food.web;

import com.meituan.food.extract.ICOEDataExtract;
import com.meituan.food.extract.ICargoDataPushExtract;
import com.meituan.food.mapper.CoeListP0Mapper;
import com.meituan.food.po.CoeListP0;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    @GetMapping("/insert")
    public String insertCoe() throws ParseException {
        DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        CoeListP0 po=new CoeListP0();
        po.setBrief("CaseStudy-2019-04-08 到餐BD同一时间高频访问盘古导致服务不可用问题");
        po.setOccurDate(dateFormat1.parse("2019-04-08"));
        po.setWiki("https://km.sankuai.com/page/143626959");
        po.setLevel("S4");
        po.setOwnerMis("luqijun");
        po.setOwnerName("卢企军");
        po.setCategory("backend");
        po.setSubCategory("业务方的逻辑");
        po.setAvailable(true);
        po.setOrgName("平台业务研发中心/销售支持平台研发组/到餐研发组");
        po.setFindDate(dateFormat1.parse("2019-04-08"));
        coeListP0Mapper.insert(po);

        CoeListP0 po1=new CoeListP0();
        po1.setBrief("2019年Q2-CaseStudy-20190620-流水信息异常导致商家结算金额不正确");
        po1.setOccurDate(dateFormat1.parse("2019-06-20"));
        po1.setWiki("https://km.sankuai.com/page/173958178");
        po1.setLevel("S4");
        po1.setOwnerMis("luoding02");
        po1.setOwnerName("罗丁");
        po1.setCategory("第三方");
        po1.setSubCategory("第三方");
        po1.setAvailable(true);
        po1.setOrgName("基础研发平台/企业平台研发部/财务研发中心/产品组");
        po1.setFindDate(dateFormat1.parse("2019-06-20"));
        coeListP0Mapper.insert(po1);
        return "OK!";
    }
}
