package com.meituan.food.extract.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import com.meituan.food.extract.IGetLineCoverageExtract;
import com.meituan.food.mapper.AppkeyListPOMapper;
import com.meituan.food.mapper.DepartmentPOMapper;
import com.meituan.food.mapper.LineCoverageP0Mapper;
import com.meituan.food.mapper.ReleaseNamePOMapper;
import com.meituan.food.po.LineCoverageP0;
import com.meituan.food.po.ReleaseNamePO;
import com.meituan.food.utils.HttpUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Component
public class GetLineCoverageExtracter implements IGetLineCoverageExtract {
    @Resource
    private ReleaseNamePOMapper releaseNamePOMapper;

    @Resource
    private LineCoverageP0Mapper lineCoverageP0Mapper;

    @Resource
    private AppkeyListPOMapper appkeyListPOMapper;

    @Resource
    private DepartmentPOMapper departmentPOMapper;

    private Date now;
    public static final String url = "http://jacocolive.meishi.test.sankuai.com/api/asyn/stopCoverageAndGetDataAsyn?ipOrCargoSwimlane=mainbranch&startTimeInSecond=3&releaseName=";
    private JSONObject resp;
    private String srv;
    private String releaseName;
    private int departmentId;
    private int departmentId2;
    private String departmentName;
    private String departmentName2;

    // 数据库默认值
    // 不包含error_message
    public void insterPO( LineCoverageP0 po) {
        po.setReleaseName(releaseName);
        po.setSrv(srv);
        po.setCoreLineC(0);
        po.setCoreLineM(0);
        po.setCoreLineTotal(0);
        po.setCoreLineCoverage(BigDecimal.valueOf(0));
        po.setCoreLineCInterface(null);
        po.setTotalLineC(0);
        po.setTotalLineM(0);
        po.setTotalLineTotal(0);
        po.setTotalLineCoverage(BigDecimal.valueOf(0));
        po.setTotalLineCInterface(null);
        po.setDepartmentId(departmentId);
        po.setDepartmentName(departmentName);
        po.setDepartmentId2(departmentId2);
        po.setDepartmentName2(departmentName2);
        po.setCreatedTime(now);
        po.setUpdateTime(now);
    }

    @Override
    public void getLineCoverage() {
        List<ReleaseNamePO> allReleaseNameSrv = releaseNamePOMapper.selectReleaseNameSrv();
        now = new Date();

        for (ReleaseNamePO releaseNameSrv : allReleaseNameSrv) {
            LineCoverageP0 po = new LineCoverageP0();

            srv = releaseNameSrv.getSrv();
            releaseName = releaseNameSrv.getReleaseName();
            departmentId = appkeyListPOMapper.selectBySrv(releaseNameSrv.getSrv()).getDepartmentId();
            departmentId2 = appkeyListPOMapper.selectBySrv(releaseNameSrv.getSrv()).getDepartmentId2();
            departmentName = departmentPOMapper.selectByPrimaryKey(departmentId).getDepartment();
            departmentName2 = departmentPOMapper.selectByPrimaryKey(departmentId2).getDepartment2();

            String urlF = url+releaseName;
            resp = HttpUtils.doGet(urlF,JSONObject.class, ImmutableMap.of());

            int code = resp.getInteger("code");
            int preparationStatus;
            int resultStatus;

            // 最外层code，1(fail), 直接退出
            // 最外层code，0，继续：
            //       内侧状态码，preparationStatus 2 轮询（任务执行中）
            //                 preparationStatus 非2 错误退出
            //                 preparationStatus 不存在：判断resultStatus 0正常 2轮询 非0且非2异常
            if (code == 0) {
                // preparationStatus和resultStatus都不存在：存默认数据，记录错误信息
                if ((!resp.getJSONObject("data").containsKey("preparationStatus")) && (!resp.getJSONObject("data").containsKey("resultStatus"))) {
                    insterPO(po);
                    po.setErrorMessage("preparationStatus不存在；resultStatus不存在");
                }
                // preparationStatus或resultStatus存在，执行循环
                // 或条件原因：preparationStatus=2或resultStatus=2，都会重新发请求，结果可能有preparationStatus，也可能有resultStatus
                // break条件：preparationStatus-非2-错误退出；resultStatus-0-完成计算并存数据；resultStatus-非0且非2-错误退出
                while (resp.getJSONObject("data").containsKey("preparationStatus") || resp.getJSONObject("data").containsKey("resultStatus")) {
                    // 内侧状态码preparationStatus存在：2继续轮序 其他值退出
                    if (resp.getJSONObject("data").containsKey("preparationStatus")) {
                        preparationStatus = resp.getJSONObject("data").getInteger("preparationStatus");
                        po.setErrorMessage("preparationStatus="+preparationStatus);

                        if (preparationStatus == 2) {
                            resp = HttpUtils.doGet(urlF,JSONObject.class, ImmutableMap.of());
                        } else {
                            insterPO(po);
                            break;
                        }
                    }
                    // 内侧状态码preparationStatus不存在：判断resultStatus 0正常 1异常 2轮询
                    if (resp.getJSONObject("data").containsKey("resultStatus")) {
                        resultStatus = resp.getJSONObject("data").getInteger("resultStatus");

                        if (resultStatus == 0) {
                            // 核心接口覆盖行数
                            int coreLineC = resp.getJSONObject("data").getJSONObject("coreData").getInteger("lineC");
                            // 核心接口未覆盖行数
                            int coreLineM = resp.getJSONObject("data").getJSONObject("coreData").getInteger("lineM");
                            // 核心接口总行数
                            int coreLineTotal = coreLineC + coreLineM;
                            // 计算核心接口行覆盖率（四舍五入BigDecimal.ROUND_HALF_U）
                            if (coreLineTotal != 0) {
                                BigDecimal coreLineCoverage = (new BigDecimal(coreLineC)).divide (new BigDecimal(coreLineTotal),4,BigDecimal.ROUND_HALF_UP);
                                coreLineCoverage = coreLineCoverage.movePointRight(2);
                                po.setCoreLineCoverage(coreLineCoverage);
                            } else {
                                po.setCoreLineCoverage(BigDecimal.valueOf(0));
                            }
                            // 核心接口行覆盖率（接口获取，string，直接丢弃未四舍五入）
                            String coreLineCoverageInterface = resp.getJSONObject("data").getJSONObject("coreData").getString("lineCoverage");
                            // 全部接口覆盖行数
                            int totalLineC = resp.getJSONObject("data").getJSONObject("totalData").getInteger("lineC");
                            // 全部接口未覆盖行数
                            int totalLineM = resp.getJSONObject("data").getJSONObject("totalData").getInteger("lineM");
                            // 全部接口总行数
                            int totalLineTotal = totalLineC + totalLineM;
                            // 计算全部接口行覆盖率（四舍五入BigDecimal.ROUND_HALF_U）
                            if (totalLineTotal != 0) {
                                BigDecimal totalLineCoverage = (new BigDecimal(totalLineC)).divide (new BigDecimal(totalLineTotal),4,BigDecimal.ROUND_HALF_UP);
                                totalLineCoverage = totalLineCoverage.movePointRight(2);
                                po.setTotalLineCoverage(totalLineCoverage);
                            } else {
                                po.setTotalLineCoverage(BigDecimal.valueOf(0));
                            }
                            // 全部接口行覆盖率（接口获取，string，直接丢弃未四舍五入）
                            String totalLineCoverageInterface = resp.getJSONObject("data").getJSONObject("totalData").getString("lineCoverage");

                            po.setReleaseName(releaseName);
                            po.setSrv(srv);
                            po.setCoreLineC(coreLineC);
                            po.setCoreLineM(coreLineM);
                            po.setCoreLineTotal(coreLineTotal);
                            // po.setTotalLineCoverage(totalLineCoverage);放在if判断中
                            po.setCoreLineCInterface(coreLineCoverageInterface);
                            po.setTotalLineC(totalLineC);
                            po.setTotalLineM(totalLineM);
                            po.setTotalLineTotal(totalLineTotal);
                            // po.setTotalLineCoverage(totalLineCoverage);放在if判断中
                            po.setTotalLineCInterface(totalLineCoverageInterface);
                            po.setDepartmentId(departmentId);
                            po.setDepartmentName(departmentName);
                            po.setDepartmentId2(departmentId2);
                            po.setDepartmentName2(departmentName2);
                            po.setCreatedTime(now);
                            po.setUpdateTime(now);
                            break;
                        } else if (resultStatus == 2){
                            resp = HttpUtils.doGet(urlF,JSONObject.class, ImmutableMap.of());
                        } else {
                            insterPO(po);
                            po.setErrorMessage("resultStatus="+resultStatus);
                            break;
                        }
                    }
                }
            } else {
                insterPO(po);
                po.setErrorMessage("code="+code);
            }
            lineCoverageP0Mapper.insert(po);
        }
    }
}
