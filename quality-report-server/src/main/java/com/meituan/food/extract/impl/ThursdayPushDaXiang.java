package com.meituan.food.extract.impl;

import com.meituan.food.extract.IThursdayPushDaXiang;
import com.meituan.food.mapper.ImportantProjectReviewPOMapper;
import com.meituan.food.po.ImportantProjectReviewPO;
import com.meituan.food.utils.DaXiangUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ThursdayPushDaXiang implements IThursdayPushDaXiang {

    @Resource
    private ImportantProjectReviewPOMapper importantProjectReviewPOMapper;

    @Override
    public void pushDaXiang() {

        ImportantProjectReviewPO importantProjectReviewPO=new ImportantProjectReviewPO();
        importantProjectReviewPO=importantProjectReviewPOMapper.selectLastData();

        DaXiangUtils.pushToPerson("@所有人 本周项目review地址："+importantProjectReviewPO.getKmLink()+" 尽快填写哦~","guomengyao");
      //  DaXiangUtils.pushToRoom("@所有人 本周项目review地址："+importantProjectReviewPO.getKmLink()+" 尽快填写哦~",879074L);
       //    DaXiangUtils.pushToRoom("@所有人 本周项目review地址："+importantProjectReviewPO.getKmLink()+" 尽快填写哦~",64012858900L);

    }

    public static void main(String[] args) {
        IThursdayPushDaXiang a=new ThursdayPushDaXiang();
        a.pushDaXiang();
    }
}
