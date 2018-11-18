package com.meituan.food.web;


import com.meituan.food.mapper.WeekIssuePOMapper;
import com.meituan.food.po.WeekIssuePO;
import com.meituan.food.web.vo.CommonResponse;
import com.meituan.food.web.vo.IssueVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/issue")
public class IssueController {


    @Resource
    private WeekIssuePOMapper weekIssuePOMapper;

    @GetMapping("/{department}/{startDate}/{endDate}")
    public CommonResponse<List<IssueVO>> getIssue(@RequestParam("department") String department, @RequestParam("startDate")Date startDate, @RequestParam("endDate") Date endDate){

        List<WeekIssuePO> weekIssuePOList=weekIssuePOMapper.getIssueListByDepartmentAndDate(department,startDate,endDate);



        List<IssueVO> issueVOList=new ArrayList<>();

        IssueVO issueVO=new IssueVO();

        for(int i=0;i<weekIssuePOList.size();i++){
            issueVO.setBrief(weekIssuePOList.get(i).getBrief());
            issueVO.setDepartment(weekIssuePOList.get(i).getDepartment());
            issueVO.setType(weekIssuePOList.get(i).getType());
            issueVO.setLevel(weekIssuePOList.get(i).getLevel());
            issueVO.setWiki(weekIssuePOList.get(i).getWiki());
            issueVO.setCreatedAt(weekIssuePOList.get(i).getCreatedAt());
            issueVO.setUpdatedAt(weekIssuePOList.get(i).getUpdatedAt());

            issueVOList.add(issueVO);
        }

        if(issueVOList.size()==0){
            return CommonResponse.errorRes("无数据");
        }

        return CommonResponse.successRes("success",issueVOList);

    }


}
