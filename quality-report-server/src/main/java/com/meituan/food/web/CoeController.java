package com.meituan.food.web;

import com.meituan.food.extract.ICOEDataExtract;
import com.meituan.food.extract.ICOETdDataExtract;
import com.meituan.food.extract.ICargoDataPushExtract;
import com.meituan.food.extract.ITdCoeExtract;
import com.meituan.food.extract.impl.CoeDataPushExtracter;
import com.meituan.food.mapper.CoeListPOMapper;
import com.meituan.food.mapper.OrgDaxiangPOMapper;
import com.meituan.food.mapper.OrgMcdIdPOMapper;
import com.meituan.food.po.OrgDaxiangPO;
import com.meituan.food.po.OrgDaxiangPOExample;
import com.meituan.food.po.OrgMcdIdPO;
import com.sankuai.meituan.org.openapi.model.Hierarchy;
import com.sankuai.meituan.org.opensdk.model.domain.Org;
import com.sankuai.meituan.org.opensdk.model.domain.items.OrgItems;
import com.sankuai.meituan.org.opensdk.service.OrgService;
import com.sankuai.meituan.org.queryservice.domain.base.Paging;
import com.sankuai.meituan.org.queryservice.exception.MDMThriftException;
import com.sankuai.meituan.org.treeservice.domain.EmpHierarchyCond;
import com.sankuai.meituan.org.treeservice.domain.param.OrgHierarchyCond;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.text.*;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/coe")
public class CoeController {
    @Resource
    private ICOEDataExtract coeDataExtract;

    @Resource
    private ICOETdDataExtract coeTdDataExtract;

    @Resource
    private ITdCoeExtract TdCoeExtracter;

    @Resource
    private CoeListPOMapper coeListPOMapper;

    @Resource(name = "COEPush")
    private CoeDataPushExtracter cargoDataPushExtract;

    @Resource
    private OrgDaxiangPOMapper orgDaxiangPOMapper;

    @Resource
    private OrgMcdIdPOMapper orgMcdIdPOMapper;

    @Resource
    private OrgService orgService;

    @GetMapping("/update")
    public String updateHistoryData(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate) throws ParseException {
        try {
            coeDataExtract.getCOEData(startDate, endDate);
        } catch (Exception e) {
            log.error("updateHistoryData error, startDate: {},endDate: {}", startDate, endDate, e);
        }
        return "OK!";
    }

    //获取平台技术部的coe数据
    @GetMapping("/td/update")
    public String updateTdHistoryData(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate) throws ParseException {
        try {
            coeTdDataExtract.getCOETdData(startDate, endDate);
        } catch (Exception e) {
            log.error("updateTdHistoryData error, startDate: {},endDate: {}", startDate, endDate, e);
        }
        return "OK!";
    }

    @GetMapping("/td/extractData4Week")
    public String extractData4Week(@RequestParam("startDate") String  startDate, @RequestParam("endDate") String endDate) throws ParseException {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd");
            Date startDate_1 =formatter.parse(startDate);
            Date endDate_1 =formatter.parse(endDate);
            TdCoeExtracter.extractData4Week(startDate_1, endDate_1);
        } catch (Exception e) {
            log.error("updateTdHistoryData error, startDate: {},endDate: {}", startDate, endDate, e);
        }
        return "OK!";
    }

    //新增到店组织架构与大象的对应关系
    @GetMapping("/org")
    public String insertDaxiangAndOrg(@RequestParam("orgName") String orgName, @RequestParam("daxiangId") Long daxiangId) throws MDMThriftException {
        Integer orgId = orgMcdIdPOMapper.selectOrgIdByOrgName(orgName);
        if (Objects.isNull(orgId)) {
            return "组织【" + orgName + "】不存在，请自查是否正确。确认无问题请联系郭孟瑶！";
        }

        OrgDaxiangPOExample orgDaxiangExample = new OrgDaxiangPOExample();
        orgDaxiangExample.createCriteria()
                .andOrgIdEqualTo(orgId)
                .andDaxiangIdEqualTo(daxiangId);
        List<OrgDaxiangPO> orgDaxiangPOS = orgDaxiangPOMapper.selectByExample(orgDaxiangExample);
        if (CollectionUtils.isEmpty(orgDaxiangPOS)) {
            try {
                OrgDaxiangPO orgDaxiangPO = new OrgDaxiangPO();
                orgDaxiangPO.setOrgId(orgId);
                orgDaxiangPO.setDaxiangId(daxiangId);
                orgDaxiangPOMapper.insertSelective(orgDaxiangPO);
            } catch (Exception e) {
                log.error("新增组织与大象群关系异常，orgName: {},orgId: {},daxiangId: {}", orgName, orgId, daxiangId, e);
//                return "新增组织与大象群关系异常!";
            }
        }

        Paging paging = new Paging();
        paging.setSize(1000);

        OrgHierarchyCond orgHierarchyCond = new OrgHierarchyCond();
        OrgItems orgItems = orgService.queryBySuperior(String.valueOf(orgId), 0, orgHierarchyCond, paging);

        if (Objects.nonNull(orgItems)) {
            orgItems.getItems().forEach((org) -> {
                String childOrgId = org.getOrgId();
                int childOrgIdInt = Integer.valueOf(childOrgId);
                OrgDaxiangPOExample example = new OrgDaxiangPOExample();
                example.createCriteria()
                        .andOrgIdEqualTo(childOrgIdInt)
                        .andDaxiangIdEqualTo(daxiangId);
                List<OrgDaxiangPO> orgDaxiangs = orgDaxiangPOMapper.selectByExample(example);
                if (CollectionUtils.isNotEmpty(orgDaxiangs)) {
                    //      return "组织【" + orgName + "】与大象群ID【" + daxiangId + "】的关系已存在！";
                    return;
                }
                try {
                    OrgDaxiangPO orgDaxiangPO = new OrgDaxiangPO();
                    orgDaxiangPO.setOrgId(childOrgIdInt);
                    orgDaxiangPO.setDaxiangId(daxiangId);
                    orgDaxiangPOMapper.insertSelective(orgDaxiangPO);
                } catch (Exception e) {
                    log.error("新增组织与大象群关系异常，orgName: {},orgId: {},daxiangId: {}", orgName, childOrgIdInt, daxiangId, e);
                    // return "新增组织与大象群关系异常!";
                }
            });
        }


        return "新增组织与大象群关系成功！";
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
    public String updateBusiness(@RequestParam("coeId") int coeId, @RequestParam("business") String business) {
        int flag = coeListPOMapper.updateBusiness(coeId, business);
        if (flag == 1) {
            return "更新成功";
        }
        return "更新失败";
    }

    @GetMapping("/updateorder")
    public String updateOrder(@RequestParam("coeId") int coeId, @RequestParam("order") int order) {
        int flag = coeListPOMapper.updateOrder(coeId, order);
        if (flag == 1) {
            return "更新成功";
        }
        return "更新失败";
    }

    @GetMapping("/updatemoney")
    public String updateMoney(@RequestParam("coeId") int coeId, @RequestParam("money") int money) {
        int flag = coeListPOMapper.updateMoney(coeId, money);
        if (flag == 1) {
            return "更新成功";
        }
        return "更新失败";
    }

    @GetMapping("/updatebusiness2")
    public String updateBusiness2(@RequestParam("coeId") int coeId, @RequestParam("business") String business) {
        int flag = coeListPOMapper.updateBusiness2(coeId, business);
        if (flag == 1) {
            return "更新成功";
        }
        return "更新失败";
    }
}
