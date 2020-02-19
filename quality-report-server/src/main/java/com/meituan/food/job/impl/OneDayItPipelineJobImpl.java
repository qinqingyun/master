package com.meituan.food.job.impl;

import com.meituan.food.extract.IOneDayItPipelineExtract;
import com.meituan.food.extract.IOneWeekOrg2EmpExtract;
import com.meituan.food.job.IOneDayItPipelineJob;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Component
public class OneDayItPipelineJobImpl implements IOneDayItPipelineJob {

    @Resource
    private IOneDayItPipelineExtract oneDayItPipelineExtract;

    @Override
    public void sync() {
        Date date=new Date();//取时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE,-1);//把日期往后增加一天.整数往后推,负数往前移动
        date=calendar.getTime(); //这个时间就是日期往后推一天的结果
        oneDayItPipelineExtract.updateItPipelineData(date);

    }


}
