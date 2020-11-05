package com.meituan.food.extract.impl;

import com.meituan.food.extract.IAtpPushExtract;
import com.meituan.food.mapper.CoeAtpPOMapper;
import com.meituan.food.mapper.McdCoePOMapper;
import com.meituan.food.po.CoeAtpPO;
import com.meituan.food.po.McdCoePO;
import com.meituan.food.utils.DaXiangUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class AtpPushExtracter implements IAtpPushExtract {

    @Resource
    private CoeAtpPOMapper coeAtpPOMapper;
    @Resource
    private McdCoePOMapper mcdcoePOMapper;

    @Override
    public void pushAtp(){

        LocalDate day=LocalDate.now().minusDays(3);
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = day.atStartOfDay().atZone(zone).toInstant();
        Date date = Date.from(instant);
        List<CoeAtpPO> coeList = coeAtpPOMapper.selectUnfinishCoeList(date);

        for (CoeAtpPO atpPO : coeList) {
            int id=atpPO.getCoeId();
            log.info("这条有订单损失的COE是: {}", atpPO.getCoeId());
            McdCoePO po= mcdcoePOMapper.selectByCoeId(id);

            String pushText = "";
            String business = po.getLine();
            if (po.getCouponLoss() != null && !po.getCouponLoss().equals("")) {
                pushText = pushText + "\n●损失支付间夜/门票/消费券" + po.getCouponLoss() + "张";
            } else if (po.getOrderLoss() != null && po.getOrderLoss().compareTo(BigDecimal.ZERO) != 0) {
                log.info("mcd_coe_list订单损失的COE是: {}", po.getCoeLink());
                pushText = pushText + "\n●订单损失" + po.getOrderLoss().setScale(0, BigDecimal.ROUND_HALF_UP) + "单";
            } else if (po.getCapitalLoss() != null && po.getCapitalLoss().compareTo(BigDecimal.ZERO) != 0) {
                pushText = pushText + "\n●资金损失" + po.getCapitalLoss().setScale(2, BigDecimal.ROUND_HALF_UP) + "元";
            }
            pushText = pushText + "\n[如已录入请点击此处|http://10.41.94.92:8080/atp/update?coeId=" + po.getCoeId() + "]";
            if (business.equals("住宿")) {
                pushText = business + "业务下新增有损失的COE，请及时录入ATP\nATP地址：http://jiudian.sankuai.com/atp/atp.jsp#/\n【[" + po.getBrief() + "|" + po.getCoeLink() + "]】" + pushText;

                atpPO.setReceiver("chenchaoyi");
                atpPO.setPushText(pushText);
                coeAtpPOMapper.updateByPrimaryKey(atpPO);
            } else if (business.equals("门票")) {
                pushText = business + "业务下新增有损失的COE，请及时录入ATP\nATP地址：http://jiudian.sankuai.com/atp/dual.jsp#/apt_trip\n【[" + po.getBrief() + "|" + po.getCoeLink() + "]】" + pushText;

                atpPO.setReceiver("chenchaoyi");
                atpPO.setPushText(pushText);
                coeAtpPOMapper.updateByPrimaryKey(atpPO);

            } else if (business.equals("到餐") || business.equals("收单")) {
                pushText = business + "业务下新增有损失的COE，请及时录入ATP\n【[" + po.getBrief() + "|" + po.getCoeLink() + "]】" + pushText;
                atpPO.setReceiver("wangjianming02");
                atpPO.setPushText(pushText);
                coeAtpPOMapper.updateByPrimaryKey(atpPO);
            } else if (business.equals("到综")) {
                pushText = business + "业务下新增有损失的COE，请及时录入ATP\n地址：https://service.sankuai.com/#/services/com.sankuai.sre.coe.api/docs/Incidentrestful1988824033/QueryIncidents-1503461554\n【[" + po.getBrief() + "|" + po.getCoeLink() + "]】" + pushText;

                atpPO.setReceiver("yuan.ding");
                atpPO.setPushText(pushText);
                coeAtpPOMapper.updateByPrimaryKey(atpPO);
            }
            DaXiangUtils.pushToPerson(atpPO.getReceiver()+":"+atpPO.getPushText(),"qinqingyun","zhangyangyang17");
            DaXiangUtils.pushToPerson(atpPO.getPushText(),atpPO.getReceiver());
            DaXiangUtils.pushToPerson(atpPO.getReceiver()+":"+atpPO.getPushText(),"yangchunxia");
        }
    }

}
