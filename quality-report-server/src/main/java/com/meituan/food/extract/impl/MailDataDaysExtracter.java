package com.meituan.food.extract.impl;

import com.meituan.food.extract.IMailDataDaysExtract;
import com.meituan.food.mapper.*;
import com.meituan.food.po.*;
import com.meituan.food.web.vo.OrgVO;
import com.sankuai.it.mail.sdk.service.MailThriftService;
import com.sankuai.it.mail.sdk.structs.MailStructDTO;
import com.sankuai.it.mail.sdk.structs.SendMailResultDTO;
import com.sankuai.meituan.org.opensdk.model.domain.Emp;
import com.sankuai.meituan.org.opensdk.model.domain.items.EmpItems;
import com.sankuai.meituan.org.opensdk.service.EmpService;
import com.sankuai.meituan.org.queryservice.domain.base.Paging;
import com.sankuai.meituan.org.queryservice.exception.MDMThriftException;
import com.sankuai.meituan.org.treeservice.domain.EmpHierarchyCond;
import org.apache.thrift.TException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class MailDataDaysExtracter implements IMailDataDaysExtract {

    @Resource
    private EmpService empService;

    @Resource
    private EfficiencyTotalDateDaysPOMapper efficiencyTotalDateDaysPOMapper;

    @Resource
    private EfficiencyTotalDatePOMapper efficiencyTotalDatePOMapper;

    @Resource
    private MailThriftService mailThriftService;

    @Override
    public void extractEfficiencyData4Days(String startDate, String endDate) throws MDMThriftException, TException {

        List<EfficiencyTotalDateDaysPO> totalDateDaysPOS = new ArrayList<>();

        EmpHierarchyCond empHierarchyCond = new EmpHierarchyCond();
        empHierarchyCond = empHierarchyCond.jobStatusIdET(15);//在职
        Paging paging = new Paging();
        paging.setSize(1000);

        List<String> orgList = new ArrayList<>();
        orgList.add("100047");
        orgList.add("114614");
        orgList.add("114615");
        orgList.add("104638");
        orgList.add("110621");

        for (String singleOrg : orgList) {
            EmpItems empItems = empService.queryEmp(singleOrg,3,empHierarchyCond,paging);
            List<Emp> items = empItems.getItems();

            List<OrgVO> orgVOList = new ArrayList<>();

            for (Emp item : items) {
                OrgVO orgVO = new OrgVO();
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
                EfficiencyTotalDateDaysPO efficiencyTotalDateDaysPO = new EfficiencyTotalDateDaysPO();
                efficiencyTotalDateDaysPO.setMis(orgVO.getMisId());
                efficiencyTotalDateDaysPO.setName(orgVO.getName());
                efficiencyTotalDateDaysPO.setOrgName(orgVO.getOrgName());
                efficiencyTotalDateDaysPO.setStartDate(startDate);
                efficiencyTotalDateDaysPO.setEndDate(endDate);
                Date now = new Date();
                efficiencyTotalDateDaysPO.setCreatedAt(now);

                EfficiencyTotalDatePO efficiencyTotalDatePO = new EfficiencyTotalDatePO();
                efficiencyTotalDatePO = efficiencyTotalDatePOMapper.selectByPrimaryMisByTime(orgVO.getMisId(),startDate,endDate);

                if (efficiencyTotalDatePO != null) {
                    efficiencyTotalDateDaysPO.setCreateBugNum(efficiencyTotalDatePO.getSumCreateBugNum());
                    efficiencyTotalDateDaysPO.setAcceptBugNum(efficiencyTotalDatePO.getSumAcceptBugNum());
                    efficiencyTotalDateDaysPO.setCreateWikiNum(efficiencyTotalDatePO.getSumCreateWikiNum());
                    efficiencyTotalDateDaysPO.setUpdateWikiNum(efficiencyTotalDatePO.getSumUpdateWikiNum());
                    efficiencyTotalDateDaysPO.setGitIncrease(efficiencyTotalDatePO.getSumGitIncrease());
                    efficiencyTotalDateDaysPO.setGitDelete(efficiencyTotalDatePO.getSumGitDelete());
                    efficiencyTotalDateDaysPO.setGitSubmit(efficiencyTotalDatePO.getSumGitSubmit());
                    efficiencyTotalDateDaysPO.setGitSubmitTime(efficiencyTotalDatePO.getSumGitSubmitTime());
                } else {
                    efficiencyTotalDateDaysPO.setCreateBugNum(0);
                    efficiencyTotalDateDaysPO.setAcceptBugNum(0);
                    efficiencyTotalDateDaysPO.setCreateWikiNum(0l);
                    efficiencyTotalDateDaysPO.setUpdateWikiNum(0l);
                    efficiencyTotalDateDaysPO.setGitIncrease(0);
                    efficiencyTotalDateDaysPO.setGitDelete(0);
                    efficiencyTotalDateDaysPO.setGitSubmit(0);
                    efficiencyTotalDateDaysPO.setGitSubmitTime(0);
                }

                efficiencyTotalDateDaysPOMapper.insert(efficiencyTotalDateDaysPO);
                totalDateDaysPOS.add(efficiencyTotalDateDaysPO);
            }
        }

        Map<String, List<EfficiencyTotalDateDaysPO>> totalMap = totalDateDaysPOS.stream().collect(Collectors.groupingBy(EfficiencyTotalDateDaysPO::getOrgName));

        String mailBody = "<h1>人效数据" + startDate + "~" + endDate + "</h1>";

        for (String key : totalMap.keySet()) {
            List<EfficiencyTotalDateDaysPO> listEffData = totalMap.get(key);
            mailBody = mailBody + "<h3>" + key + "</h3>";
            mailBody = mailBody + "<body><table border=\"1\" cellspacing=\"0\"><tr><th>mis</th><th>姓名</th><th>创建学城数量</th><th>更新学城数量</th><th>Git代码增加量</th><th>Git代码删除量</th><th>Git代码提交量</th><th>Git代码提交次数</th><th>创建bug数量</th><th>接收bug数量</th><th>组织</th></tr>";

            for (EfficiencyTotalDateDaysPO listEffDatum : listEffData) {
                mailBody = mailBody + "<tr><td>" + listEffDatum.getMis()
                        + "</td><td>" + listEffDatum.getName()
                        + "</td><td>" + listEffDatum.getCreateWikiNum()
                        + "</td><td>" + listEffDatum.getUpdateWikiNum()
                        + "</td><td>" + listEffDatum.getGitIncrease()
                        + "</td><td>" + listEffDatum.getGitDelete()
                        + "</td><td>" + listEffDatum.getGitSubmit()
                        + "</td><td>" + listEffDatum.getGitSubmitTime()
                        + "</td><td>" + listEffDatum.getCreateBugNum()
                        + "</td><td>" + listEffDatum.getAcceptBugNum()
                        + "</td><td>" + listEffDatum.getOrgName()
                        + "</td></tr>";
            }
            mailBody = mailBody + "</table></body>";
        }

        mailBody = mailBody + "</html>";
        System.out.println(mailBody);

        MailStructDTO mailModel = new MailStructDTO();
        mailModel.setUseHtml(true);
        mailModel.setFromName("到餐质量组");
        mailModel.setBody(mailBody);
        mailModel.setTo(Arrays.asList("guomengyao@meituan.com")); //收件人
        mailModel.setCc(Arrays.asList());  //抄送
        mailModel.setBcc(Arrays.asList());  //密送
        mailModel.setSubject("人效数据表"+ startDate + "~" + endDate);
        //   mailModel.setAttachments(getAttachments()); //带附件发送

        SendMailResultDTO resultModel = mailThriftService.sendMail(mailModel);
        System.out.println(resultModel);
    }
}
