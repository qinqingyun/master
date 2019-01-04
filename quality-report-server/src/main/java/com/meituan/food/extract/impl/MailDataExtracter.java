package com.meituan.food.extract.impl;

import com.meituan.food.extract.IMailDataExtract;
import com.meituan.food.mapper.EfficiencyBugNumPOMapper;
import com.meituan.food.mapper.EfficiencyTotalDatePOMapper;
import com.meituan.food.mapper.GitPOMapper;
import com.meituan.food.mapper.RestaurantXueChengMapper;
import com.meituan.food.po.EfficiencyBugNumPO;
import com.meituan.food.po.EfficiencyTotalDatePO;
import com.meituan.food.po.GitPO;
import com.meituan.food.po.RestaurantXueCheng;
import com.meituan.food.web.vo.OrgVO;
import com.sankuai.meituan.org.opensdk.model.domain.Emp;
import com.sankuai.meituan.org.opensdk.model.domain.items.EmpItems;
import com.sankuai.meituan.org.opensdk.service.EmpService;
import com.sankuai.meituan.org.queryservice.domain.base.Paging;
import com.sankuai.meituan.org.queryservice.exception.MDMThriftException;
import com.sankuai.meituan.org.treeservice.domain.EmpHierarchyCond;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class MailDataExtracter implements IMailDataExtract {

    @Resource
    private GitPOMapper gitPOMapper;

    @Resource
    private EfficiencyBugNumPOMapper efficiencyBugNumPOMapper;

    @Resource
    private EmpService empService;

    @Resource
    private EfficiencyTotalDatePOMapper efficiencyTotalDatePOMapper;

    @Autowired
    private RestaurantXueChengMapper restaurantXueChengMapper;

    @Override
    public void extractMailData4EffDay(LocalDate day) throws MDMThriftException {

        String firstDayStr = day.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        EmpHierarchyCond empCond = new EmpHierarchyCond();
        empCond = empCond.jobStatusIdET(15);//在职
        Paging paging = new Paging();
        paging.setSize(1000);

        EmpItems empItems = empService.queryEmp("100047", 3, empCond, paging);
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

        for (OrgVO orgVO : orgVOList) {
            EfficiencyTotalDatePO efficiencyTotalDatePO=new EfficiencyTotalDatePO();
            EfficiencyBugNumPO efficiencyBugNumPO=new EfficiencyBugNumPO();
            efficiencyBugNumPO= efficiencyBugNumPOMapper.selectByPrimaryMis(orgVO.getMisId(),firstDayStr);
            efficiencyTotalDatePO.setMis(orgVO.getMisId());
            efficiencyTotalDatePO.setName(orgVO.getName());
            efficiencyTotalDatePO.setPartitionDate(firstDayStr);
            if(efficiencyBugNumPO!=null) {
                efficiencyTotalDatePO.setCreateBugNum(efficiencyBugNumPO.getCreateNum());
                efficiencyTotalDatePO.setAcceptBugNum(efficiencyBugNumPO.getAcceptNum());
            }else {
                efficiencyTotalDatePO.setCreateBugNum(0);
                efficiencyTotalDatePO.setAcceptBugNum(0);
            }

            GitPO gitPO=new GitPO();
            gitPO=gitPOMapper.selectByPrimaryMis(orgVO.getMisId(),firstDayStr);
            if (gitPO!=null){
                efficiencyTotalDatePO.setGitDelete(gitPO.getGitCodeDelete());
                efficiencyTotalDatePO.setGitIncrease(gitPO.getGitCodeIncrease());
                efficiencyTotalDatePO.setGitSubmit(gitPO.getGitCodeSubmit());
                efficiencyTotalDatePO.setGitSubmitTime(gitPO.getGitCodeSubmitTime());
            }else {
                efficiencyTotalDatePO.setGitDelete(0);
                efficiencyTotalDatePO.setGitIncrease(0);
                efficiencyTotalDatePO.setGitSubmit(0);
                efficiencyTotalDatePO.setGitSubmitTime(0);
            }

            Date now=new Date();
            efficiencyTotalDatePO.setCreatedAt(now);

            efficiencyTotalDatePOMapper.insert(efficiencyTotalDatePO);

            RestaurantXueCheng restaurantXueCheng = new RestaurantXueCheng();
            restaurantXueCheng=restaurantXueChengMapper.selectByPrimaryMis(orgVO.getMisId(),firstDayStr);
            if (restaurantXueCheng!=null){
                efficiencyTotalDatePO.setCreateWikiNum(restaurantXueCheng.getCreateCount());
                efficiencyTotalDatePO.setUpdateWikiNum(restaurantXueCheng.getUpdateCount());
            }else {
                efficiencyTotalDatePO.setCreateWikiNum(0l);
                efficiencyTotalDatePO.setUpdateWikiNum(0l);
            }
        }

    }
}
