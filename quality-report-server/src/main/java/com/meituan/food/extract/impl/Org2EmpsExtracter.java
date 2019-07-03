package com.meituan.food.extract.impl;

import com.meituan.food.extract.IOneWeekOrg2EmpExtract;
import com.meituan.food.mapper.Org2EmpDataPOMapper;
import com.meituan.food.po.Org2EmpDataPO;
import com.meituan.food.po.Org2EmpDataPOExample;
import com.sankuai.meituan.org.opensdk.model.domain.Org;
import com.sankuai.meituan.org.opensdk.model.domain.items.EmpItems;
import com.sankuai.meituan.org.opensdk.model.domain.items.OrgItems;
import com.sankuai.meituan.org.opensdk.service.EmpService;
import com.sankuai.meituan.org.opensdk.service.OrgService;
import com.sankuai.meituan.org.queryservice.domain.base.Paging;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class Org2EmpsExtracter implements IOneWeekOrg2EmpExtract {


    @Resource
    private Org2EmpDataPOMapper org2EmpDataPOMapper;

    @Resource
    private EmpService empService;

    @Resource
    private OrgService orgService;
    private String v;

    @Override
    public void updateEmpsData(LocalDate day) {
        try{

            List <String> list =new ArrayList<String>();

            OrgItems orgItems = orgService.queryBySuperior("104638",2,null,new Paging());
            orgItems.getCount();
            List<Org> orgList=orgItems.getItems();
            orgList.forEach((org ->{
                list.add(org.getOrgId());
            } ));
            log.info("org list {}"+list);
//            Emp emp1 = empService.queryByMis("wuhaibo",null);
//            EmpItems empItems = empService.queryByOrgHead("60523",1,null,new Paging());

            EmpItems empItems = empService.queryByOrgIds(list,null,new Paging(),Date.from(day.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
            log.info("empItems: "+empItems);
            log.info("empItems: size "+empItems.getCount());


            empItems.getItems().forEach((emp) ->{

                Org2EmpDataPO po = new Org2EmpDataPO();
                po.setName(emp.getName());
                po.setEmpid(emp.getEmpId());
                po.setMis(emp.getMis());
                po.setReportempid(emp.getReportEmpId());
                po.setReportempname(emp.getReportEmpName());
                po.setOrgid(emp.getOrgId());
                po.setOrgname(emp.getOrgName());
                po.setUpdatedDate(new Date().toString());
                Org2EmpDataPOExample org2EmpDataPOExample = new Org2EmpDataPOExample();
                org2EmpDataPOExample.setDistinct(true);
                Org2EmpDataPOExample.Criteria  criteria = org2EmpDataPOExample.createCriteria();
                criteria.andMisEqualTo(po.getMis());
                List<Org2EmpDataPO> org2EmpDataPOList = org2EmpDataPOMapper.selectByExample(org2EmpDataPOExample);
                if (org2EmpDataPOList.size()==0||org2EmpDataPOList==null)
                {
                    int insertresult = org2EmpDataPOMapper.insert(po);
                    log.info("org2empdata insertresult:"+insertresult);

                }else {
                    int updateresult = org2EmpDataPOMapper.updateByExampleSelective(po,org2EmpDataPOExample);
                    log.info("org2empdata updateresult:"+updateresult);
                }

            });

        }catch (Exception e)
        {
            log.info("empService.query error:"+e);
        }
    }
}
