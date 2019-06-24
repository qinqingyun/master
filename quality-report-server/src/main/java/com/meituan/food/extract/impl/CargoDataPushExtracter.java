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
        nameMap.put("liyuhua", "李宇华");
        nameMap.put("qinqingyun", "秦庆贇");
        nameMap.put("buyuqi", "部玉琪");
        nameMap.put("wuqifang", "吴启芳");
        nameMap.put("huangguilin", "黄桂琳");
        nameMap.put("fengchen", "冯辰");
        nameMap.put("zhangyancui", "张彦翠");
        nameMap.put("xiongyiping", "熊一平");
        nameMap.put("yingzhixun", "应直巡");
        nameMap.put("chenyunyun", "陈云云");
        nameMap.put("summer.sun", "孙蒙");
        nameMap.put("bei.guo", "郭贝");
        nameMap.put("liuxiangyi", "刘香怡");
        nameMap.put("zhouke", "周克");
        nameMap.put("feilichao", "费立超");

        Calendar yesterday = Calendar.getInstance();
        yesterday.add(Calendar.DATE, -1);
        yesterday.set(yesterday.get(Calendar.YEAR), yesterday.get(Calendar.MONTH), yesterday.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        String yesterdayStr = new SimpleDateFormat("yyyy-MM-dd").format(yesterday.getTime());
        Date yesterdayDate = yesterday.getTime();

        Date mYesterday = Date.from(LocalDate.now().minusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant());


        String stableList = yesterdayStr + "稳定性数据\n";
        int flag=0;
        String bStableList = yesterdayStr + "稳定性数据\n";
        int flag_1=0;

        String avalibleList = yesterdayStr + "可用性数据\n";
        int flag_2=0;
        String bAvalibleList = yesterdayStr + "可用性数据\n";
        int flag_3=0;
        List<CargoDataPO> cargoDataPOS = cargoDataPOMapper.selectByDate(mYesterday);

        for (CargoDataPO cargoDataPO : cargoDataPOS) {
            if (!cargoDataPO.getStableTagPercentage().equals("100.0")) {
                stableList = stableList + cargoDataPO.getDirection() + "_" + cargoDataPO.getTag() + ":" + cargoDataPO.getStableTagPercentage() + " @" + nameMap.get(cargoDataPO.getPerson()) + "\n";
                flag++;
                if (cargoDataPO.getDirection().equals("B端_北京") || cargoDataPO.getDirection().equals("B端_上海")) {
                    bStableList = bStableList + cargoDataPO.getDirection() + "_" + cargoDataPO.getTag() + ":" + cargoDataPO.getStableTagPercentage() + " @" + nameMap.get(cargoDataPO.getPerson()) + "\n";
                    flag_1++;
                }
            }

            if ((!cargoDataPO.getAvalibleTagPercentage().equals("100.0")) && cargoDataPO.getAvalibleTotal() != 0) {
                avalibleList = avalibleList + cargoDataPO.getDirection() + "_" + cargoDataPO.getTag() + ":" + cargoDataPO.getAvalibleTagPercentage() + " @" + nameMap.get(cargoDataPO.getPerson()) + "\n";
                flag_2++;
                if (cargoDataPO.getDirection().equals("B端_北京") || cargoDataPO.getDirection().equals("B端_上海")) {
                    bAvalibleList=bAvalibleList+cargoDataPO.getDirection() + "_" + cargoDataPO.getTag() + ":" + cargoDataPO.getAvalibleTagPercentage() + " @" + nameMap.get(cargoDataPO.getPerson()) + "\n";
                    flag_3++;
                }
            }
        }

        if (flag!=0){
            DaXiangUtils.pushToPerson(stableList,"guomengyao");
        }
        if (flag_1!=0){
            DaXiangUtils.pushToPerson(bStableList,"guomengyao");
        }
        if (flag_2!=0){
            DaXiangUtils.pushToPerson(avalibleList,"guomengyao");
        }
        if (flag_3!=0){
            DaXiangUtils.pushToPerson(bAvalibleList,"guomengyao");
        }
    }

    public static void main(String[] args) throws ParseException {
       /* Calendar yesterday = Calendar.getInstance();
        yesterday.add(Calendar.DATE, -2);
        yesterday.set(yesterday.get(Calendar.YEAR), yesterday.get(Calendar.MONTH), yesterday.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        String yesterdayStr = new SimpleDateFormat("yyyy-MM-dd ").format(yesterday.getTime());
        Date yesterdayDate = yesterday.getTime();
        System.out.println(yesterdayDate);*/

        String time = "2017-10-19";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//yyyy-mm-dd, 会出现时间不对, 因为小写的mm是代表: 秒
        Date utilDate = sdf.parse(time);
        System.out.println(utilDate);//查看utilDate的值

    }
}
