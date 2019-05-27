package com.meituan.food.extract.impl;

import com.meituan.food.extract.IOneDayNoticeExtract;
import com.meituan.food.extract.IThursdayPushDaXiang;
import com.meituan.food.utils.DaXiangUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
@Component
public class OneDayNoticeExtracter implements IOneDayNoticeExtract{


    @Override
    public void extractData4Day() {

        //推给个人
        //DaXiangUtils.pushToPerson("@所有人 本周项目review地址："+importantProjectReviewPO.getKmLink()+" 尽快填写哦~","guomengyao");
        //推给组群
        DaXiangUtils.pushToRoom("@所有人 记得写周报：https://km.sankuai.com/page/159820418",64014028439L);
    }

    public static void main(String[] args) {
        IOneDayNoticeExtract a=new OneDayNoticeExtracter();
        a.extractData4Day();
    }
}
