package com.meituan.food.extract.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import com.meituan.food.extract.IGetLineCoverageExtract;
import com.meituan.food.mapper.LineCoverageP0Mapper;
import com.meituan.food.mapper.ReleaseNamePOMapper;
import com.meituan.food.po.LineCoverageP0;
import com.meituan.food.utils.HttpUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class GetLineCoverageExtracter implements IGetLineCoverageExtract {
    @Resource
    private ReleaseNamePOMapper releaseNamePOMapper;

    @Resource
    private LineCoverageP0Mapper lineCoverageP0Mapper;

    public static final String url = "http://jacocolive.meishi.test.sankuai.com/api/asyn/stopCoverageAndGetDataAsyn?ipOrCargoSwimlane=mainbranch&startTimeInSecond=3&releaseName=";

    @Override
    public void getLineCoverage() throws InterruptedException {
        List<String> allReleaseName = releaseNamePOMapper.selectAllReleaseName();
        Date now = new Date();
        for (String releaseName : allReleaseName) {
            LineCoverageP0 po = new LineCoverageP0();
            JSONObject resp = HttpUtils.doGet(url+releaseName,JSONObject.class, ImmutableMap.of());
            TimeUnit.MILLISECONDS.sleep(700);
            int code = resp.getInteger("code");

            if (code == 0) {
                //核心接口覆盖行数
                int coreLineC = resp.getJSONObject("data").getJSONObject("coreData").getInteger("lineC");
                //核心接口未覆盖行数
                int coreLineM = resp.getJSONObject("data").getJSONObject("coreData").getInteger("lineM");
                //核心接口总行数
                int coreLineTotal = coreLineC + coreLineM;
                //计算核心接口行覆盖率（四舍五入BigDecimal.ROUND_HALF_U）
                if (coreLineTotal != 0) {
                    BigDecimal coreLineCoverage = (new BigDecimal(coreLineC)).divide (new BigDecimal(coreLineTotal),4,BigDecimal.ROUND_HALF_UP);
                    coreLineCoverage = coreLineCoverage.movePointRight(2);
                    po.setCoreLineCoverage(coreLineCoverage);
                } else {
                    po.setCoreLineCoverage(null);
                }
                //核心接口行覆盖率（接口获取，string，直接丢弃未四舍五入）
                String coreLineCoverageInterface = resp.getJSONObject("data").getJSONObject("coreData").getString("lineCoverage");
                //全部接口覆盖行数
                int totalLineC = resp.getJSONObject("data").getJSONObject("totalData").getInteger("lineC");
                //全部接口未覆盖行数
                int totalLineM = resp.getJSONObject("data").getJSONObject("totalData").getInteger("lineM");
                //全部接口总行数
                int totalLineTotal = totalLineC + totalLineM;
                //计算全部接口行覆盖率（四舍五入BigDecimal.ROUND_HALF_U）
                if (totalLineTotal != 0) {
                    BigDecimal totalLineCoverage = (new BigDecimal(totalLineC)).divide (new BigDecimal(totalLineTotal),4,BigDecimal.ROUND_HALF_UP);
                    totalLineCoverage = totalLineCoverage.movePointRight(2);
                    po.setTotalLineCoverage(totalLineCoverage);
                } else {
                    po.setTotalLineCoverage(null);
                }
                //全部接口行覆盖率（接口获取，string，直接丢弃未四舍五入）
                String totalLineCoverageInterface = resp.getJSONObject("data").getJSONObject("totalData").getString("lineCoverage");

                po.setReleaseName(releaseName);
                po.setCoreLineC(coreLineC);
                po.setCoreLineM(coreLineM);
                po.setCoreLineTotal(coreLineTotal);
                //po.setCoreLineCoverage放在if判断中
                //po.setTotalLineCoverage(totalLineCoverage);
                po.setCoreLineCInterface(coreLineCoverageInterface);
                po.setTotalLineC(totalLineC);
                po.setTotalLineM(totalLineM);
                po.setTotalLineTotal(totalLineTotal);
                //po.setTotalLineCoverage放在if判断中
                //po.setTotalLineCoverage(totalLineCoverage);
                po.setTotalLineCInterface(totalLineCoverageInterface);
                po.setCreatedTime(now);
                po.setUpdateTime(now);

            } else {
                po.setReleaseName(releaseName);
                po.setCoreLineC(null);
                po.setCoreLineM(null);
                po.setCoreLineTotal(null);
                po.setCoreLineCoverage(null);
                po.setCoreLineCInterface(null);
                po.setTotalLineC(null);
                po.setTotalLineM(null);
                po.setTotalLineTotal(null);
                po.setTotalLineCoverage(null);
                po.setTotalLineCInterface(null);
                po.setCreatedTime(now);
                po.setUpdateTime(now);
            }
            lineCoverageP0Mapper.insert(po);
        }
    }
}
