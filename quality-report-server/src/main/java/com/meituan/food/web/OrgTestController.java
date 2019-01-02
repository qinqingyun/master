package com.meituan.food.web;

import com.sankuai.meituan.org.openapi.model.EmpCond;
import com.sankuai.meituan.org.opensdk.model.domain.Emp;
import com.sankuai.meituan.org.opensdk.model.domain.items.EmpItems;
import com.sankuai.meituan.org.opensdk.service.EmpService;
import com.sankuai.meituan.org.queryservice.domain.base.Paging;
import com.sankuai.meituan.org.queryservice.exception.MDMThriftException;
import com.sankuai.meituan.org.treeservice.domain.EmpHierarchyCond;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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

    @GetMapping
    public String testOrgIdController(@RequestParam("orgId") String orgId)throws MDMThriftException{

        EmpHierarchyCond empCond = new EmpHierarchyCond();
        empCond = empCond.jobStatusIdET(15);//在职
        Paging paging = new Paging();
        paging.setSize(100);

        EmpItems empItems = empService.queryEmp(orgId, 2, empCond, paging);
        String stringEmpItems=empItems.toString();

        return stringEmpItems;
    }
}
