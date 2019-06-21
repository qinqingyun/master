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
            "1、客服商服反馈群，有问题反馈，会根据配置自动拉群处理，值班同学需关注群内的进度。\n" +
            "2、若提报问题自动匹配错误，需值班同学手动拉相关人进群。若提报问题未自动匹配，则需要值班同学手动拉群处理，并将问题编号记录，同步给buyuqi同学\n" +
            "4、值班同学需关注自己值班当天，群内问题的解决情况，若问题已解决，督促提报人关闭问题。";

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
            DaXiangUtils.pushToRoom("本周值班人员："+nameList,64013968876L);
        }
    }

    public static void main(String[] args) {
    }
}
