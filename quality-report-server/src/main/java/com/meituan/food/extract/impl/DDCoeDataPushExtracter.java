package com.meituan.food.extract.impl;

import com.meituan.food.extract.ICargoDataPushExtract;
import com.meituan.food.mapper.McdCoePOMapper;
import com.meituan.food.po.McdCoePO;
import com.meituan.food.utils.DaXiangUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
public class DDCoeDataPushExtracter implements ICargoDataPushExtract {

    @Resource
    private McdCoePOMapper mcdCoePOMapper;

    @Override
    public void pushData() throws ParseException {

        Map<String, String> pushList = new HashMap<>();

        String dateString = "2020-06-01";
        Date inceptionDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);

        LocalDate secondDay=LocalDate.now().minusDays(7);
        String secondDayStr = secondDay.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Date secondDayDate = new SimpleDateFormat("yyyy-MM-dd").parse(secondDayStr);


        //超过一周的提醒
        List<McdCoePO> mcdCoePOList = mcdCoePOMapper.selectByTwoDate(inceptionDate, secondDayDate);

        for (McdCoePO mcdCoePO : mcdCoePOList) {
            if (!(pushList.keySet().contains(mcdCoePO.getOwnerMis()))) {

                String content = mcdCoePO.getOwnerName() + "同学：您负责的COE未按照规范填写，请及时处理。问题如下：";
                content = content + "\n△【" + mcdCoePO.getBrief() + "】";
                int index = 0;

                getPushContext(pushList,mcdCoePO,index,content);

            }else {
                String content = pushList.get(mcdCoePO.getOwnerMis()) + "\n△【" + mcdCoePO.getBrief() + "】";
                int index = 0;

                getPushContext(pushList,mcdCoePO,index,content);
            }
        }

        for (String key : pushList.keySet()) {
            DaXiangUtils.pushToPerson(pushList.get(key), "guomengyao");
            DaXiangUtils.pushToPerson(pushList.get(key), key);
        }


        Map<String, String> remindPushList = new HashMap<>();
        LocalDate remindDay=LocalDate.now().minusDays(5);
        String remindDayStr = remindDay.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Date remindDayDate = new SimpleDateFormat("yyyy-MM-dd").parse(remindDayStr);

        //创建3天后提醒
        List<McdCoePO> threeDayCoePoiList=mcdCoePOMapper.selectByDate(remindDayDate);
        for (McdCoePO po : threeDayCoePoiList) {
            if (!(remindPushList.keySet().contains(po.getOwnerMis()))) {
                String content = po.getOwnerName() + "同学：您负责的COE创建超过3天啦，请及时补充完成。问题如下：";
                content = content + "\n△【" + po.getBrief() + "】";
                int index = 0;

                getPushContext(remindPushList,po,index,content);
            }else {
                String content = remindPushList.get(po.getOwnerMis()) + "\n△【" + po.getBrief() + "】";
                int index = 0;

                getPushContext(remindPushList,po,index,content);
            }
        }

        for (String key : remindPushList.keySet()) {
            DaXiangUtils.pushToPerson(remindPushList.get(key), "guomengyao");
            DaXiangUtils.pushToPerson(remindPushList.get(key), key);
        }


    }

    public void getPushContext(Map<String, String> pushList, McdCoePO mcdCoePO,int index,String content){
        if (mcdCoePO.getFminusoTime() == null || mcdCoePO.getLminusfTime() == null || mcdCoePO.getSminushTime() == null || mcdCoePO.getInfluenceTime() == null) {
            content = content + "\n● 时间线不完整";
            index++;
        }
        if (mcdCoePO.getLine()==null){
            content = content + "\n● 未填写业务线";
            index++;
        }
        if (!(mcdCoePO.getCapitalLoss()!=null||mcdCoePO.getOrderLoss()!=null||mcdCoePO.getCouponLoss()!=null)){
            content = content + "\n● 未填写资损数据(如无损失请填0)";
            index++;
        }
        if (mcdCoePO.getOnlineDiscovery()==null){
            content = content + "\n● 未填写线上问题发现方式";
            index++;
        }
        if (mcdCoePO.getOnlineClassification()==null){
            content = content + "\n● 未填写线上问题原因分类";
            index++;
        }
        if (mcdCoePO.getNofundReason()==null){
            content = content + "\n● 未填写前期测试未发现原因";
            index++;
        }
        if (index != 0) {
            content = content + "\n" + "[点此处理|" + mcdCoePO.getCoeLink() + "]        [COE书写规范|https://km.sankuai.com/page/192873360]";
            pushList.put(mcdCoePO.getOwnerMis(), content);
        }
    }
}
