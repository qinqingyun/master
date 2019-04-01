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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

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

    @Resource
    private MailThriftService mailThriftService;

    @Override
    public void extractMailData4EffDay(LocalDate day) throws MDMThriftException, TException {

        String firstDayStr = day.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        List<EfficiencyTotalDatePO> totalDatePOS = new ArrayList<>();

        EmpHierarchyCond empCond = new EmpHierarchyCond();
        empCond = empCond.jobStatusIdET(15);//在职
        Paging paging = new Paging();
        paging.setSize(1000);

        List<String> orgList = new ArrayList<>();
        orgList.add("100047");
        orgList.add("114614");
        orgList.add("114615");
        orgList.add("104638");

        for (String singleOrg : orgList) {

            EmpItems empItems = empService.queryEmp(singleOrg, 3, empCond, paging);
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
                EfficiencyTotalDatePO efficiencyTotalDatePO = new EfficiencyTotalDatePO();
                EfficiencyBugNumPO efficiencyBugNumPO = new EfficiencyBugNumPO();
                efficiencyBugNumPO = efficiencyBugNumPOMapper.selectByPrimaryMis(orgVO.getMisId(), firstDayStr);
                efficiencyTotalDatePO.setMis(orgVO.getMisId());
                efficiencyTotalDatePO.setName(orgVO.getName());
                efficiencyTotalDatePO.setPartitionDate(firstDayStr);
                efficiencyTotalDatePO.setOrgName(orgVO.getOrgName());
                if (efficiencyBugNumPO != null) {
                    efficiencyTotalDatePO.setCreateBugNum(efficiencyBugNumPO.getCreateNum());
                    efficiencyTotalDatePO.setAcceptBugNum(efficiencyBugNumPO.getAcceptNum());
                } else {
                    efficiencyTotalDatePO.setCreateBugNum(0);
                    efficiencyTotalDatePO.setAcceptBugNum(0);
                }

                GitPO gitPO = new GitPO();
                gitPO = gitPOMapper.selectByPrimaryMis(orgVO.getMisId(), firstDayStr);
                if (gitPO != null) {
                    efficiencyTotalDatePO.setGitDelete(gitPO.getGitCodeDelete());
                    efficiencyTotalDatePO.setGitIncrease(gitPO.getGitCodeIncrease());
                    efficiencyTotalDatePO.setGitSubmit(gitPO.getGitCodeSubmit());
                    efficiencyTotalDatePO.setGitSubmitTime(gitPO.getGitCodeSubmitTime());
                } else {
                    efficiencyTotalDatePO.setGitDelete(0);
                    efficiencyTotalDatePO.setGitIncrease(0);
                    efficiencyTotalDatePO.setGitSubmit(0);
                    efficiencyTotalDatePO.setGitSubmitTime(0);
                }

                Date now = new Date();
                efficiencyTotalDatePO.setCreatedAt(now);

                RestaurantXueCheng restaurantXueCheng = new RestaurantXueCheng();
                restaurantXueCheng = restaurantXueChengMapper.selectByPrimaryMis(orgVO.getMisId(), firstDayStr);
                if (restaurantXueCheng != null) {
                    efficiencyTotalDatePO.setCreateWikiNum(restaurantXueCheng.getCreateCount());
                    efficiencyTotalDatePO.setUpdateWikiNum(restaurantXueCheng.getUpdateCount());
                } else {
                    efficiencyTotalDatePO.setCreateWikiNum(0l);
                    efficiencyTotalDatePO.setUpdateWikiNum(0l);
                }

                efficiencyTotalDatePOMapper.insert(efficiencyTotalDatePO);
           /* mailBody=mailBody+"<tr><td>"+efficiencyTotalDatePO.getMis()
                    +"</td><td>"+efficiencyTotalDatePO.getName()
                    +"</td><td>"+efficiencyTotalDatePO.getCreateWikiNum()
                    +"</td><td>"+efficiencyTotalDatePO.getUpdateWikiNum()
                    +"</td><td>"+efficiencyTotalDatePO.getGitIncrease()
                    +"</td><td>"+efficiencyTotalDatePO.getGitDelete()
                    +"</td><td>"+efficiencyTotalDatePO.getGitSubmit()
                    +"</td><td>"+efficiencyTotalDatePO.getGitSubmitTime()
                    +"</td><td>"+efficiencyTotalDatePO.getCreateBugNum()
                    +"</td><td>"+efficiencyTotalDatePO.getAcceptBugNum()
                    +"</td><td>"+efficiencyTotalDatePO.getPartitionDate()
                    +"</td><td>"+efficiencyTotalDatePO.getOrgName()
                    +"</td></tr>";*/

                totalDatePOS.add(efficiencyTotalDatePO);
            }
        }

        Map<String, List<EfficiencyTotalDatePO>> totalMap = totalDatePOS.stream().collect(Collectors.groupingBy(EfficiencyTotalDatePO::getOrgName));

        String mailBody = "<h1>人效数据</h1>";

        for (String key : totalMap.keySet()) {
            List<EfficiencyTotalDatePO> listEffData = totalMap.get(key);
            mailBody = mailBody + "<h3>" + key + "</h3>";
            mailBody = mailBody + "<body><table border=\"1\" cellspacing=\"0\"><tr><th>mis</th><th>姓名</th><th>创建学城数量</th><th>更新学城数量</th><th>Git代码增加量</th><th>Git代码删除量</th><th>Git代码提交量</th><th>Git代码提交次数</th><th>创建bug数量</th><th>接收bug数量</th><th>日期</th><th>组织</th></tr>";

            for (EfficiencyTotalDatePO listEffDatum : listEffData) {
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
                        + "</td><td>" + listEffDatum.getPartitionDate()
                        + "</td><td>" + listEffDatum.getOrgName()
                        + "</td></tr>";
            }
            mailBody = mailBody + "</table></body>";
        }

        mailBody = mailBody + "</html>";
//        System.out.println(mailBody);

        MailStructDTO mailModel = new MailStructDTO();
        mailModel.setUseHtml(true);
        mailModel.setFromName("到餐质量组");
        mailModel.setBody(mailBody);
        mailModel.setTo(Arrays.asList("guomengyao@meituan.com")); //收件人
        mailModel.setCc(Arrays.asList());  //抄送
        mailModel.setBcc(Arrays.asList());  //密送
        mailModel.setSubject("人效数据表" + firstDayStr);
        //   mailModel.setAttachments(getAttachments()); //带附件发送

        SendMailResultDTO resultModel = mailThriftService.sendMail(mailModel);
        System.out.println(resultModel);
    }
}
