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
import java.util.ArrayList;
import java.util.Arrays;
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

    @Resource
    private MailThriftService mailThriftService;

    @Override
    public void extractMailData4EffDay(LocalDate day) throws MDMThriftException, TException {

        String firstDayStr = day.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        String mailBody="<body><h4>人效数据表：</h4><table border=\"1\"><tr><th>mis</th><th>姓名</th><th>创建学城数量</th><th>更新学城数量</th><th>Git代码增加量</th><th>Git代码删除量</th><th>Git代码提交量</th><th>Git代码提交次数</th><th>创建bug数量</th><th>接收bug数量</th><th>日期</th></tr>";

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

            RestaurantXueCheng restaurantXueCheng = new RestaurantXueCheng();
            restaurantXueCheng=restaurantXueChengMapper.selectByPrimaryMis(orgVO.getMisId(),firstDayStr);
            if (restaurantXueCheng!=null){
                efficiencyTotalDatePO.setCreateWikiNum(restaurantXueCheng.getCreateCount());
                efficiencyTotalDatePO.setUpdateWikiNum(restaurantXueCheng.getUpdateCount());
            }else {
                efficiencyTotalDatePO.setCreateWikiNum(0l);
                efficiencyTotalDatePO.setUpdateWikiNum(0l);
            }

            efficiencyTotalDatePOMapper.insert(efficiencyTotalDatePO);
            mailBody=mailBody+"<tr><td>"+efficiencyTotalDatePO.getMis()
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
                    +"</td></tr>";

        }

        mailBody=mailBody+"</table></body></html>";

        MailStructDTO mailModel = new MailStructDTO();
        mailModel.setUseHtml(true);
        mailModel.setFromName("到餐质量组");
//        mailModel.setBody("<html><head></head><body>我是测试邮件</body></html>");
        mailModel.setBody(mailBody);
        mailModel.setTo(Arrays.asList("guomengyao@meituan.com")); //收件人
        mailModel.setCc(Arrays.asList());  //抄送
        mailModel.setBcc(Arrays.asList());  //密送
        mailModel.setSubject("人效数据表");
        //   mailModel.setAttachments(getAttachments()); //带附件发送

        SendMailResultDTO resultModel = mailThriftService.sendMail(mailModel);
        System.out.println(resultModel);

    }
}
