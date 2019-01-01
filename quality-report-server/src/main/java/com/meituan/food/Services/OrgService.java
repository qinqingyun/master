package com.meituan.food.Services;

import com.sankuai.meituan.org.openapi.model.Hierarchy;
import com.sankuai.meituan.org.opensdk.model.domain.OrgEmp;
import com.sankuai.meituan.org.opensdk.model.domain.items.EmpItems;
import com.sankuai.meituan.org.opensdk.service.EmpService;
import com.sankuai.meituan.org.queryservice.domain.base.Paging;
import com.sankuai.meituan.org.treeservice.domain.EmpHierarchyCond;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class OrgService {

    @Resource
    private EmpService empService;

    public EmpItems allEmp()throws Exception{
        String orgId="100047";
        EmpItems empItems = empService.queryEmp("100047", 2, EmpHierarchyCond.of().jobStatusIdET(15), new Paging());

        System.out.println(empItems.toString());

        return empItems;
    }

}
