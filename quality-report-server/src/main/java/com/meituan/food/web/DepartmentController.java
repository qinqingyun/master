package com.meituan.food.web;

import com.meituan.food.extract.IGetAppkeyList;
import com.meituan.food.mapper.DepartmentPOMapper;
import com.meituan.food.po.DepartmentPO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Resource
    private DepartmentPOMapper departmentPOMapper;

    @GetMapping("/insert")
    public String insertDepartment(){
        DepartmentPO po=new DepartmentPO();
        /*po.setDepartment("C端服务端");
        po.setDepartment2("C端服务端");
        departmentPOMapper.insert(po);

        DepartmentPO po2=new DepartmentPO();
        po2.setDepartment("CRM");
        po2.setDepartment2("CRM");
        departmentPOMapper.insert(po2);

        DepartmentPO po3=new DepartmentPO();
        po3.setDepartment("商家端");
        po3.setDepartment2("商家端");
        departmentPOMapper.insert(po3);

        DepartmentPO po4=new DepartmentPO();
        po4.setDepartment("M端供应链");
        po4.setDepartment2("M端供应链");
        departmentPOMapper.insert(po4);

        DepartmentPO po5=new DepartmentPO();
        po5.setDepartment("商家财务");
        po5.setDepartment2("商家财务");
        departmentPOMapper.insert(po5);

        DepartmentPO po6=new DepartmentPO();
        po6.setDepartment("商家平台");
        po6.setDepartment2("商家基础平台");
        departmentPOMapper.insert(po6);

        DepartmentPO po7=new DepartmentPO();
        po7.setDepartment("预定");
        po7.setDepartment2("预定");
        departmentPOMapper.insert(po7);

        DepartmentPO po8=new DepartmentPO();
        po8.setDepartment("商家平台");
        po8.setDepartment2("商家运营平台");
        departmentPOMapper.insert(po8);

        DepartmentPO po9=new DepartmentPO();
        po9.setDepartment("商家平台");
        po9.setDepartment2("商家增值平台");
        departmentPOMapper.insert(po9);*/

       /* DepartmentPO po10=new DepartmentPO();
        po10.setDepartment("客户平台");
        po10.setDepartment2("客户平台");
        departmentPOMapper.insert(po10);

        DepartmentPO po11=new DepartmentPO();
        po11.setDepartment("合同平台");
        po11.setDepartment2("合同平台");
        departmentPOMapper.insert(po11);*/

/*
        DepartmentPO po11=new DepartmentPO();
        po11.setDepartment("商家平台-到综研发组");
        po11.setDepartment2("到综研发组");
        departmentPOMapper.insert(po11);*/

        return "OK!";
    }
}
