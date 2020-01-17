package com.meituan.food.web;

import com.meituan.food.mapper.DepartmentOrgMapMapper;
import com.meituan.food.po.DepartmentOrgMap;
import com.meituan.food.web.vo.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@Slf4j
@RestController
@RequestMapping("/DepartmentOrgMap")
public class DepartmentOrgMapController {
    @Resource
    private DepartmentOrgMapMapper departmentOrgMapMapper;
    @GetMapping("/insert")
    public CommonResponse insertDepartmentOrgMap(@RequestParam("orgid") int orgid,
                                         @RequestParam("orgname") String orgname,
                                         @RequestParam("departmentId2") Integer departmentId2,
                                         @RequestParam("departmentName") String departmentName,
                                         @RequestParam("departmentName2") String departmentName2)
    {
        Date now = new Date();
        DepartmentOrgMap departmentOrgMap = new DepartmentOrgMap();
        departmentOrgMap.setOrgid(orgid);
        departmentOrgMap.setOrgname(orgname);
        departmentOrgMap.setDepartmentId2(departmentId2);
        departmentOrgMap.setDepartmentName(departmentName);
        departmentOrgMap.setDepartmentName2(departmentName2);
        departmentOrgMap.setAddTime(now);
        departmentOrgMap.setUpdateTime(now);
        departmentOrgMapMapper.insert(departmentOrgMap);
        return CommonResponse.successRes("保存成功","");

    }

}
