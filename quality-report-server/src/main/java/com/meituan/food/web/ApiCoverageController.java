package com.meituan.food.web;

import com.meituan.food.mapper.ApiCoveragePOMapper;
import com.meituan.food.mapper.BatchDatePOMapper;
import com.meituan.food.mapper.DepartmentApiCoveragePOMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/coverage")
public class ApiCoverageController {
    @Resource
    private ApiCoveragePOMapper apiCoveragePOMapper;

    @Resource
    private DepartmentApiCoveragePOMapper departmentApiCoveragePOMapper;

    @Resource
    private BatchDatePOMapper batchDatePOMapper;

    @GetMapping("delete")
    public String deleteData(@Param("first") int first,@Param("second") int second){
        apiCoveragePOMapper.deleteByTwoKey(first,second);
        return "OK!";
    }

    @GetMapping("/delete/department")
    public String deleteDepartmentData(@Param("first") int first,@Param("second") int second){
        departmentApiCoveragePOMapper.deleteByTwoKey(first,second);
        return "OK!";
    }

    @GetMapping("/delete/batch")
    public String deleteDepartmentData(@Param("first") int first){
        batchDatePOMapper.deleteByPrimaryKey(first);
        return "OK!";
    }
}
