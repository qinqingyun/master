package com.meituan.food.web;

import com.sankuai.meituan.org.opensdk.model.domain.Emp;
import com.sankuai.meituan.org.opensdk.service.EmpService;
import com.sankuai.meituan.org.queryservice.exception.MDMThriftException;
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
}
