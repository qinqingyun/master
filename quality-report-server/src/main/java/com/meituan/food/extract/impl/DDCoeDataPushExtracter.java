package com.meituan.food.extract.impl;

import com.meituan.food.extract.ICargoDataPushExtract;
import com.meituan.food.mapper.CoeAtpPOMapper;
import com.meituan.food.mapper.McdCoePOMapper;
import com.meituan.food.po.McdCoePO;
import com.meituan.food.utils.DaXiangUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
public class DDCoeDataPushExtracter implements ICargoDataPushExtract {

    @Resource
    private McdCoePOMapper mcdCoePOMapper;

    @Resource
    private CoeAtpPOMapper coeAtpPOMapper;

    @Override
    public void pushData() throws ParseException {

        Map<String, String> pushList = new HashMap<>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String dateString = "2020-06-01 00:00:00";
        Date inceptionDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateString);

        LocalDate secondDay=LocalDate.now().minusDays(8);
        String secondDayStr = secondDay.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
   //     Date secondDayDate = new SimpleDateFormat("yyyy-MM-dd").parse(secondDayStr);
        Date firstEndDate = sdf.parse(secondDayStr +" 23:59:59");


        //超过一周的提醒
        List<McdCoePO> mcdCoePOList = mcdCoePOMapper.selectByTwoDate(inceptionDate, firstEndDate);

        for (McdCoePO mcdCoePO : mcdCoePOList) {
            if (!(pushList.keySet().contains(mcdCoePO.getOwnerMis()))) {

                String content = mcdCoePO.getOwnerName() + "同学：您负责的COE创建已经超过一周，请及时补充完整。问题如下：";
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
            DaXiangUtils.pushToPerson(pushList.get(key), "guomengyao","ting.liu","yuan.ding");
            DaXiangUtils.pushToPerson(pushList.get(key), key);
        }


        Map<String, String> remindPushList = new HashMap<>();
        LocalDate remindDay=LocalDate.now().minusDays(5);
        String remindDayStr = remindDay.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Date startDate = sdf.parse(remindDayStr +" 00:00:00");
        Date endDate = sdf.parse(remindDayStr +" 23:59:59");


        //创建3天后提醒
     //   List<McdCoePO> threeDayCoePoiList=mcdCoePOMapper.selectByDate(startDate,endDate);
        List<McdCoePO> threeDayCoePoiList=mcdCoePOMapper.selectByTwoDate(startDate,endDate);

        for (McdCoePO po : threeDayCoePoiList) {
            if (!(remindPushList.keySet().contains(po.getOwnerMis()))) {
                String content = po.getOwnerName() + "同学：您负责的COE创建超过3天啦，请及时补充完整。问题如下：";
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
            DaXiangUtils.pushToPerson(remindPushList.get(key), "guomengyao","ting.liu","yuan.ding");
            DaXiangUtils.pushToPerson(remindPushList.get(key), key);
        }

        List<McdCoePO> lossCoePOList = mcdCoePOMapper.selectLossCoe();
        List<Integer> allCoeList = coeAtpPOMapper.selectAllCoeList();

        if (lossCoePOList != null) {
            for (McdCoePO po : lossCoePOList) {
                if (!allCoeList.contains(po.getCoeId())) {
                    String business = po.getLine();
                    if (business != null && !business.equals("")) {
                        String pushText = "";
                        if (po.getCouponLoss() != null && !po.getCouponLoss().equals("")) {
                            pushText = pushText + "\n●损失支付间夜/门票/消费券" + po.getCouponLoss() + "张";
                        } else if (po.getOrderLoss() != null && po.getOrderLoss().compareTo(BigDecimal.ZERO) != 0) {
                            pushText = pushText + "\n●订单损失" + po.getOrderLoss() + "单";
                        } else if (po.getCapitalLoss() != null && po.getCapitalLoss().compareTo(BigDecimal.ZERO) != 0) {
                            pushText = pushText + "\n●资金损失" + po.getCapitalLoss() + "元";
                        }
                        pushText=pushText+"\n[如已录入请点击此处|http://10.41.94.92:8080/atp/update?coeId="+po.getCoeId()+"]";
                        if (business.equals("住宿") ) {
                            pushText = business + "业务下新增有损失的COE，请及时录入ATP\nATP地址：http://jiudian.sankuai.com/atp/atp.jsp#/\n【[" + po.getBrief() + "|" + po.getCoeLink() + "]】" + pushText;
                            DaXiangUtils.pushToPerson(pushText,"guomengyao","yuan.ding");
                            DaXiangUtils.pushToPerson(pushText,"chenchaoyi");
                        } else if (business.equals("门票")) {
                            pushText = business + "业务下新增有损失的COE，请及时录入ATP\nATP地址：http://jiudian.sankuai.com/atp/dual.jsp#/apt_trip\n【[" + po.getBrief() + "|" + po.getCoeLink() + "]】" + pushText;
                            DaXiangUtils.pushToPerson(pushText,"guomengyao","yuan.ding");
                            DaXiangUtils.pushToPerson(pushText,"chenchaoyi");
                        } else if (business.equals("到餐") || business.equals("收单")) {
                            pushText = business + "业务下新增有损失的COE，请及时录入ATP\n【[" + po.getBrief() + "|" + po.getCoeLink() + "]】" + pushText;
                            DaXiangUtils.pushToPerson(pushText,"guomengyao");
                            DaXiangUtils.pushToPerson(pushText,"wangjianming02");
                        } else if (business.equals("到综")) {
                            pushText = business + "业务下新增有损失的COE，请及时录入ATP\n地址：https://km.sankuai.com/page/259031052\n【[" + po.getBrief() + "|" + po.getCoeLink() + "]】" + pushText;
                            DaXiangUtils.pushToPerson(pushText,"guomengyao","yuan.ding");
                            DaXiangUtils.pushToPerson(pushText,"yuan.ding");
                        }
                    }
                }
            }
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
            content = content + "\n" + "[点此处理|" + mcdCoePO.getCoeLink() + "]        [COE书写规范|https://km.sankuai.com/page/192873360]   （如有疑问请大象联系郭孟瑶(guomengyao)）";
            pushList.put(mcdCoePO.getOwnerMis(), content);
        }
    }
}
