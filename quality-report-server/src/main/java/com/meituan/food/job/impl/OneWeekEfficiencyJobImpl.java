package com.meituan.food.job.impl;

import com.meituan.food.extract.IOneMonthEfficiencyDataExtract;
import com.meituan.food.job.IOneMonthEfficiencyJob;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Component
public abstract class OneMonthEfficiencyJobImpl implements IOneMonthEfficiencyJob {

    @Resource
    private List<IOneMonthEfficiencyDataExtract> dataExtracts;

    private static String firstDate;
    private static String endDate;

    @Override
    public void sync() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal_1 = Calendar.getInstance();
        cal_1.add(Calendar.MONTH, -1);
        cal_1.set(Calendar.DAY_OF_MONTH, 1);
        firstDate = format.format(cal_1.getTime());

        Calendar cale = Calendar.getInstance();
        cale.set(Calendar.DAY_OF_MONTH, 0);
        endDate = format.format(cale.getTime());
        dataExtracts.forEach(dataExtract -> dataExtract.extractEfficiencyData4Month(firstDate,endDate));
    }
}
