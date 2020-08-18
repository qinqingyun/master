package com.meituan.food.extract.impl;

import com.meituan.food.extract.ICargoDataPushExtract;
import com.meituan.food.mapper.CargoDataPOMapper;
import com.meituan.food.po.CargoDataPO;
import com.meituan.food.utils.DaXiangUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Component
public class CargoDataPushExtracter implements ICargoDataPushExtract {

    @Resource
    private CargoDataPOMapper cargoDataPOMapper;

    @Override
    public void pushData() {

        Map<String, String> nameMap = new HashMap<>();
        nameMap.put("wangjiani", "王佳妮");
        nameMap.put("zhouying", "周颖");
        nameMap.put("wenwen", "文闻");
        nameMap.put("wangyang60", "王洋");
        nameMap.put("wanglan13", "王兰");
        nameMap.put("sunnaili", "孙乃利");
        nameMap.put("hanye03","韩烨");


        nameMap.put("liyuhua", "李宇华");
        nameMap.put("qinqingyun", "秦庆贇");
        nameMap.put("buyuqi", "部玉琪");
        nameMap.put("wuqifang", "吴启芳");
        nameMap.put("fengchen", "冯辰");
        nameMap.put("tangwenchao", "唐文超");
        nameMap.put("xiongyiping", "熊一平");
        nameMap.put("yingzhixun", "应直巡");
        nameMap.put("chenyunyun", "陈云云");
        nameMap.put("bei.guo", "郭贝");
        nameMap.put("liuxiangyi", "刘香怡");
        nameMap.put("zhouke", "周克");
        nameMap.put("feilichao", "费立超");

        nameMap.put("honghui.huang", "黄红辉");
        nameMap.put("kelin03", "柯琳");
        nameMap.put("yuyuan", "余媛");
        nameMap.put("tongmeina", "佟美娜");
        nameMap.put("chenhui18", "陈慧");
        nameMap.put("summer.sun", "孙蒙");
        nameMap.put("huangguilin", "黄桂琳");
        nameMap.put("zhangyancui", "张彦翠");

        Calendar yesterday = Calendar.getInstance();
        yesterday.add(Calendar.DATE, -1);
        yesterday.set(yesterday.get(Calendar.YEAR), yesterday.get(Calendar.MONTH), yesterday.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        String yesterdayStr = new SimpleDateFormat("yyyy-MM-dd").format(yesterday.getTime());
        Date yesterdayDate = yesterday.getTime();

        Date mYesterday = Date.from(LocalDate.now().minusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant());
        java.sql.Date sqlDate=new java.sql.Date(mYesterday.getTime());


        String stableList = yesterdayStr + "稳定性数据\n";
        int flag = 0;
        String bStableList = yesterdayStr + "稳定性数据\n";
        int flag_1 = 0;

        String avalibleList = yesterdayStr + "可用性数据\n";
        int flag_2 = 0;
        String bAvalibleList = yesterdayStr + "可用性数据\n";
        int flag_3 = 0;
        List<CargoDataPO> cargoDataPOS = cargoDataPOMapper.selectByDate(sqlDate);

        for (CargoDataPO cargoDataPO : cargoDataPOS) {
            if (cargoDataPO.getDirection()!=null) {
                stableList = stableList + cargoDataPO.getDirection() + "_" + cargoDataPO.getTag() + ":" + cargoDataPO.getStableTagPercentage() + "\n";
                flag++;
                if (cargoDataPO.getDirection().equals("B端-基础测试组") || cargoDataPO.getDirection().equals("B端-运营测试组")|| cargoDataPO.getDirection().equals("B端-增值试组")) {
                    bStableList = bStableList + cargoDataPO.getDirection() + "_" + cargoDataPO.getTag() + ":" + cargoDataPO.getStableTagPercentage() + "\n";
                    flag_1++;
                }
            }else {
                stableList = stableList +  cargoDataPO.getTag() + ":" + cargoDataPO.getStableTagPercentage() + "\n";
            }

            if (cargoDataPO.getAvalibleTotal() != 0 &&cargoDataPO.getDirection()!=null) {
                avalibleList = avalibleList + cargoDataPO.getDirection() + "_" + cargoDataPO.getTag() + ":" + cargoDataPO.getAvalibleTagPercentage() + "\n";
                flag_2++;
                if (cargoDataPO.getDirection().equals("B端-基础测试组") || cargoDataPO.getDirection().equals("B端-运营测试组")|| cargoDataPO.getDirection().equals("B端-增值测试组")) {
                    bAvalibleList = bAvalibleList + cargoDataPO.getDirection() + "_" + cargoDataPO.getTag() + ":" + cargoDataPO.getAvalibleTagPercentage() + "\n";
                    flag_3++;
                }
            }else if (cargoDataPO.getAvalibleTotal() != 0){
                avalibleList = avalibleList + cargoDataPO.getTag() + ":" + cargoDataPO.getAvalibleTagPercentage() + "\n";
            }
        }

        if (flag != 0) {
            DaXiangUtils.pushToRoom(stableList, 64013787000l);
        }
        if (flag_1 != 0) {
            DaXiangUtils.pushToRoom(bStableList, 64013874686l);
        }
        if (flag_2 != 0) {
            DaXiangUtils.pushToRoom(avalibleList, 64013787000l);
        }
        if (flag_3 != 0) {
            DaXiangUtils.pushToRoom(bAvalibleList, 64013874686l);
        }
    }
}
