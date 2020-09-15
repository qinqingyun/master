package com.meituan.food.extract.impl;

import com.meituan.food.extract.IAtpPushExtract;
import com.meituan.food.mapper.CoeAtpPOMapper;
import com.meituan.food.po.CoeAtpPO;
import com.meituan.food.utils.DaXiangUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Component
public class AtpPushExtracter implements IAtpPushExtract {

    @Resource
    private CoeAtpPOMapper coeAtpPOMapper;

    @Override
    public void pushAtp(){

        LocalDate day=LocalDate.now().minusDays(3);
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = day.atStartOfDay().atZone(zone).toInstant();
        Date date = Date.from(instant);
        List<CoeAtpPO> coeList = coeAtpPOMapper.selectUnfinishCoeList(date);
        for (CoeAtpPO atpPO : coeList) {
            DaXiangUtils.pushToPerson(atpPO.getReceiver()+":"+atpPO.getPushText(),"guomengyao","qinqingyun","zhangyangyang17");
            DaXiangUtils.pushToPerson(atpPO.getPushText(),atpPO.getReceiver());
        }
    }

}
