package com.meituan.food.extract.impl;

import com.meituan.food.extract.ICargoDataPushExtract;
import com.meituan.food.mapper.CoeListPOMapper;
import com.meituan.food.po.CoeListPO;
import com.meituan.food.utils.DaXiangUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Component
@Service("COEPush")
public class CoeDataPushExtracter implements ICargoDataPushExtract {

    @Resource
    private CoeListPOMapper coeListPOMapper;

    @Override
    public void pushData() {
        Map<String, String> pushList = new HashMap<>();
        Calendar yesterday = Calendar.getInstance();
        yesterday.add(Calendar.DATE, -1);
        yesterday.set(yesterday.get(Calendar.YEAR), yesterday.get(Calendar.MONTH), yesterday.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        Date mYesterday = Date.from(LocalDate.now().minusDays(60).atStartOfDay(ZoneId.systemDefault()).toInstant());

        List<CoeListPO> coeListPOs = coeListPOMapper.selectByDate(mYesterday);
        for (CoeListPO coeListPO : coeListPOs) {
            if (!(pushList.keySet().contains(coeListPO.getOwnerMis()))) {
                String content = coeListPO.getOwnerName() + "同学：您负责的COE未按照规范填写，请及时处理。问题如下：";
                content = content + "\n△【" + coeListPO.getBrief() + "】";
                int index = 0;
                if (coeListPO.getFminusoTime() == null || coeListPO.getLminusfTime() == null || coeListPO.getSminushTime() == null) {
                    content = content + "\n● 时间线不完整";
                    index++;
                }
                if (coeListPO.getSubCategory() == null) {
                    content = content + "\n● 未填写原因分类";
                    index++;
                }
                if (index != 0) {
                    content = content + "\n" + "[点此处理|" + coeListPO.getCoeLink() + "]        [COE书写规范|https://km.sankuai.com/page/192873360]";
                    pushList.put(coeListPO.getOwnerMis(), content);
                }
            } else {
                int index = 0;
                String content = pushList.get(coeListPO.getOwnerMis()) + "\n△【" + coeListPO.getBrief() + "】";
                if (coeListPO.getFminusoTime() == null || coeListPO.getLminusfTime() == null || coeListPO.getSminushTime() == null) {
                    content = content + "\n● 时间线不完整";
                    index++;
                }
                if (coeListPO.getSubCategory() == null) {
                    content = content + "\n● 未填写原因分类";
                    index++;
                }
                if (index != 0) {
                    content = content + "\n" + "[点此处理|" + coeListPO.getCoeLink() + "]        [COE书写规范|https://km.sankuai.com/page/192873360]";
                    pushList.put(coeListPO.getOwnerMis(), content);
                }
            }


        }
        for (String key : pushList.keySet()) {
            DaXiangUtils.pushToPerson(pushList.get(key), "guomengyao","jiaxiaoqi","wuqifang","yangchunxia","liyuhua","wangjianming02");
            DaXiangUtils.pushToPerson(pushList.get(key), key);
        }
    }
}
