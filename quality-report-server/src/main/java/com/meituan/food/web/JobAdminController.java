package com.meituan.food.web;

import com.meituan.food.mapper.JobAdminP0Mapper;
import com.meituan.food.mapper.Org2EmpDataPOMapper;
import com.meituan.food.po.JobAdminP0;
import com.meituan.food.web.vo.CommonResponse;
import com.meituan.food.web.vo.JobAdminVO;
import org.springframework.web.bind.annotation.*;
import scala.xml.Null;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/job")
public class JobAdminController {

    @Resource
    private JobAdminP0Mapper jobAdminP0Mapper;
    @Resource
    private Org2EmpDataPOMapper org2EmpDataPOMapper;

    @PostMapping("/updateJobAdmin")
    public CommonResponse updateJobAdmin(@RequestBody ArrayList<JobAdminVO> data){
        Date now = new Date();
        for(int i = 0;i < data.size();i++) {
            if(data.get(i).getAdminMis()== "") continue;
            if(org2EmpDataPOMapper.selectByMis(data.get(i).getAdminMis()) == null){
                String errInfo = "mis号(" + data.get(i).getAdminMis() + ")有误，请检查后再提交！";
                return CommonResponse.errorRes(errInfo);
            }
        }
        for(int i = 0;i < data.size();i++) {
            jobAdminP0Mapper.updateJobAdmin(data.get(i).getJobName(), data.get(i).getAdminMis(), now);
        }
        return CommonResponse.successRes("成功","");
    }

    @GetMapping("/selectAllJobInfo")
    public CommonResponse selectAllJobInfo(){
        List<JobAdminP0> jobAdminP0 = jobAdminP0Mapper.selectAllJobInfo();
        List<JobAdminVO> jobAdminVO = new ArrayList<JobAdminVO>();
        for(int i = 0;i < jobAdminP0.size();i++){
            JobAdminVO vo = new JobAdminVO();
            vo.setAdminMis(jobAdminP0.get(i).getAdminName());
            vo.setDepartmentName(jobAdminP0.get(i).getDepartmentName());
            vo.setJobName(jobAdminP0.get(i).getJobName());
            vo.setUpdatedTime(jobAdminP0.get(i).getUpdatedTime());
            vo.setDepartmentId(jobAdminP0.get(i).getDepartmentId());
            jobAdminVO.add(vo);
        }
        return CommonResponse.successRes("成功",jobAdminVO);
    }

}
