package com.meituan.food.extract.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import com.meituan.food.extract.IOneDayDutyDataExtract;
import com.meituan.food.mapper.DutyTablePOMapper;
import com.meituan.food.po.DutyTablePO;
import com.meituan.food.utils.DaXiangUtils;
import com.meituan.food.utils.HttpUtils;
import net.sf.cglib.core.Local;
import org.springframework.format.datetime.joda.ReadablePartialPrinter;
import org.springframework.stereotype.Component;
import org.w3c.dom.NameList;

import javax.annotation.Resource;
import javax.naming.Name;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class DutyDataExtracter implements IOneDayDutyDataExtract {

    public static final String url="http://api.goseek.cn/Tools/holiday?date=";

    public static final String content="周末值班QA须知\n" +
            "1、客服商服反馈群，有问题反馈，半小时之内进行响应，拉取相关PM+RD+QA入群，解决问题\n" +
            "2、问题处理后，对该问题进行记录存档，编写人：负责RD，QA负责督促RD完成问题记录。\n" +
            "记录位置：https://km.sankuai.com/page/57675978\n" +
            "3、群里问题反馈时间，基本早9点到晚11点之间，辛苦值班同学在该时间段紧密关注\n" +
            "4、本周值班人，需要对下周值班人做爱心提示和值班须知讲解\n" +
            "5、https://km.sankuai.com/page/56420384 这里有相关的对接人 可以根据这个wiki找一下";

    @Resource
    private DutyTablePOMapper dutyTablePOMapper;

    @Override
    public void extractData4Day(LocalDate day) {
        String firstDayStr = day.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String secondDayStr=day.minusDays(1).format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        JSONObject response=HttpUtils.doGet(url+firstDayStr,JSONObject.class,ImmutableMap.of());
        JSONObject nextDateResponse=HttpUtils.doGet(url+secondDayStr,JSONObject.class,ImmutableMap.of());

        int count=0;

        //dayStatus代表某天的状态，0：工作日；1：周末；2：法定节假日
        int dayStatus=response.getInteger("data");
        int secondDayStatus=nextDateResponse.getInteger("data");
        if (dayStatus!=0 &&  secondDayStatus==0){
            count++;
            for(int i=1;i<=100;i++){
                LocalDate nextDay=day.plusDays(i);
                String nextDayStr=nextDay.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
                JSONObject nextResp=HttpUtils.doGet(url+nextDayStr,JSONObject.class,ImmutableMap.of());
                 int nextDayStatus=nextResp.getInteger("data");
                 if (nextDayStatus!=0){
                     count++;
                 }else {
                     break;
                 }
            }
            List<DutyTablePO> dutyTablePOS = dutyTablePOMapper.selectDutyPerson(count);
            String nameList="";
            int i=0;
            for (DutyTablePO dutyTablePO : dutyTablePOS) {
                dutyTablePOMapper.updateByMis(day.plusDays(i).format(DateTimeFormatter.ofPattern("yyyyMMdd")),dutyTablePO.getDutyMis());
                nameList=nameList+"@"+dutyTablePO.getDutyName()+" ";
                i++;
            }

            DaXiangUtils.pushToPerson(nameList+"轮到你们值班了\n"+content,"guomengyao");
            DaXiangUtils.pushToRoom(nameList+"轮到你们值班了\n"+content,879074L);
        }
    }

    public static void main(String[] args) {
        LocalDate now=LocalDate.now().plusDays(14);
        IOneDayDutyDataExtract a=new DutyDataExtracter();
        a.extractData4Day(now);
    }
}
