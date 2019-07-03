package com.meituan.food.job.impl;

import com.meituan.food.extract.IOneWeekOrg2EmpExtract;
import com.meituan.food.job.IOneWeekOrg2EmpJob;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDate;

@Component
public class OneWeekOrg2EmpJobImpl implements IOneWeekOrg2EmpJob {

    @Resource
    private IOneWeekOrg2EmpExtract oneWeekEmpExtract;

    @Override
    public void sync() {
        LocalDate day=LocalDate.now().minusDays(1);
        oneWeekEmpExtract.updateEmpsData(day);

    }


}
