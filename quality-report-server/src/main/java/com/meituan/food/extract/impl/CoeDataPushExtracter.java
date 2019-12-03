package com.meituan.food.extract.impl;

import com.meituan.food.extract.ICargoDataPushExtract;
import com.meituan.food.mapper.CoeListP0Mapper;
import com.meituan.food.po.CoeListP0;
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
    private CoeListP0Mapper coeListP0Mapper;

    @Override
    public void pushData() {
        Map<String, String> pushList = new HashMap<>();
        Calendar yesterday = Calendar.getInstance();
        yesterday.add(Calendar.DATE, -1);
        yesterday.set(yesterday.get(Calendar.YEAR), yesterday.get(Calendar.MONTH), yesterday.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        Date mYesterday = Date.from(LocalDate.now().minusDays(60).atStartOfDay(ZoneId.systemDefault()).toInstant());

        List<CoeListP0> coeListP0s = coeListP0Mapper.selectByDate(mYesterday);
        for (CoeListP0 coeListP0 : coeListP0s) {
            if (!(pushList.keySet().contains(coeListP0.getOwnerMis()))) {
                String content = coeListP0.getOwnerName() + "同学：您负责的COE未按照规范填写，请及时处理。问题如下：";
                content = content + "\n△【" + coeListP0.getBrief() + "】";
                int index = 0;
                if (coeListP0.getFminusoTime() == null || coeListP0.getLminusfTime() == null || coeListP0.getSminushTime() == null) {
                    content = content + "\n● 时间线不完整";
                    index++;
                }
                if (coeListP0.getSubCategory() == null) {
                    content = content + "\n● 未填写原因分类";
                    index++;
                }
                if (index != 0) {
                    content = content + "\n" + "[点此处理|" + coeListP0.getCoeLink() + "]        [COE书写规范|https://km.sankuai.com/page/192873360]";
                    pushList.put(coeListP0.getOwnerMis(), content);
                }
            } else {
                int index = 0;
                String content = pushList.get(coeListP0.getOwnerMis()) + "\n△【" + coeListP0.getBrief() + "】";
                if (coeListP0.getFminusoTime() == null || coeListP0.getLminusfTime() == null || coeListP0.getSminushTime() == null) {
                    content = content + "\n● 时间线不完整";
                    index++;
                }
                if (coeListP0.getSubCategory() == null) {
                    content = content + "\n● 未填写原因分类";
                    index++;
                }
                if (index != 0) {
                    content = content + "\n" + "[点此处理|" + coeListP0.getCoeLink() + "]        [COE书写规范|https://km.sankuai.com/page/192873360]";
                    pushList.put(coeListP0.getOwnerMis(), content);
                }
            }


        }
        for (String key : pushList.keySet()) {
            DaXiangUtils.pushToPerson(pushList.get(key), "guomengyao");
            DaXiangUtils.pushToPerson(pushList.get(key), key);
        }
    }
}
