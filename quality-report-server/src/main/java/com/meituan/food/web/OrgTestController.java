package com.meituan.food.web;

import com.meituan.food.web.vo.OrgVO;
import com.sankuai.meituan.org.opensdk.model.domain.Emp;
import com.sankuai.meituan.org.opensdk.model.domain.items.EmpItems;
import com.sankuai.meituan.org.opensdk.service.EmpService;
import com.sankuai.meituan.org.queryservice.domain.base.Paging;
import com.sankuai.meituan.org.queryservice.exception.MDMThriftException;
import com.sankuai.meituan.org.treeservice.domain.EmpHierarchyCond;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/org")
public class OrgTestController {

    @Resource
    private EmpService empService;

    @GetMapping
    public String testOrgController(@RequestParam("mis") String mis) throws MDMThriftException {

        Emp emp = empService.queryByMis(mis,null);

        String stringEmp=emp.toString();

        return stringEmp;
    }

    @GetMapping("/mis")
    public List<OrgVO> myFirstOrgIdController(@RequestParam("orgId") String orgId)throws MDMThriftException{

        EmpHierarchyCond empCond = new EmpHierarchyCond();
        empCond = empCond.jobStatusIdET(15);//在职
        Paging paging = new Paging();
        paging.setSize(1000);

        EmpItems empItems = empService.queryEmp(orgId, 3, empCond, paging);
        List<Emp> items = empItems.getItems();

        List<OrgVO> orgVOList =new ArrayList<>();

        for (Emp item : items) {
            OrgVO orgVO =new OrgVO();
            orgVO.setEmpId(item.getEmpId());
            orgVO.setMisId(item.getMis());
            orgVO.setName(item.getName());
            orgVO.setOrgId(item.getOrgId());
            orgVO.setOrgName(item.getOrgName());
            orgVO.setReportMis(item.getReportEmpMis());
            orgVO.setReportName(item.getReportEmpName());

            orgVOList.add(orgVO);
        }
        String stringEmpItems=empItems.toString();

        return orgVOList;
    }
}
