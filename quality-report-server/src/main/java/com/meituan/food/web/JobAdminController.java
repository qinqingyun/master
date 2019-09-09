package com.meituan.food.web;

import com.meituan.food.mapper.JobAdminP0Mapper;
import com.meituan.food.po.JobAdminP0;
import com.meituan.food.web.vo.JobAdminVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/job")
public class JobAdminController {

    @Resource
    private JobAdminP0Mapper jobAdminP0Mapper;

    @PostMapping("/updateJobAdmin")
    public String updateJobAdmin(@RequestBody ArrayList<JobAdminVO> data){
        Date now = new Date();
        for(int i = 0;i < data.size();i++) {
            jobAdminP0Mapper.updateJobAdmin(data.get(i).getJobName(), data.get(i).getAdminName(), now);
        }
        return "OK!";
    }

    @GetMapping("/selectAllJobInfo")
    public List<JobAdminVO> selectAllJobInfo(){
        List<JobAdminP0> jobAdminP0 = jobAdminP0Mapper.selectAllJobInfo();
        List<JobAdminVO> jobAdminVO = new ArrayList<JobAdminVO>();
        for(int i = 0;i < jobAdminP0.size();i++){
            JobAdminVO vo = new JobAdminVO();
            vo.setAdminName(jobAdminP0.get(i).getAdminName());
            vo.setDepartmentName(jobAdminP0.get(i).getDepartmentName());
            vo.setJobName(jobAdminP0.get(i).getJobName());
            vo.setUpdatedTime(jobAdminP0.get(i).getUpdatedTime());
            vo.setDepartmentId(jobAdminP0.get(i).getDepartmentId());
            jobAdminVO.add(vo);
        }
        return jobAdminVO;
    }

//    @GetMapping("/selectJobInfoByDepartment")
//    public List<JobAdminVO> selectJobInfoByDepartment(@RequestParam("dapartmentName") String dapartmentName){
//        List<JobAdminP0> jobAdminP0 =  jobAdminP0Mapper.selectJobInfoByDepartment(dapartmentName);
//        List<JobAdminVO> jobAdminVO = new ArrayList<JobAdminVO>();
//        for(int i = 0;i < jobAdminP0.size();i++){
//            JobAdminVO vo = new JobAdminVO();
//            vo.setAdminName(jobAdminP0.get(i).getAdminName());
//            vo.setDepartmentName(jobAdminP0.get(i).getDepartmentName());
//            vo.setJobName(jobAdminP0.get(i).getJobName());
//            vo.setUpdatedTime(jobAdminP0.get(i).getUpdatedTime());
//            vo.setDapartmentId(jobAdminP0.get(i).getDapartmentId());
//            jobAdminVO.add(vo);
//        }
//        return jobAdminVO;
//    }
}
