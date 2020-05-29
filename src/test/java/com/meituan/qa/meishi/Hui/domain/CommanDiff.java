package com.meituan.qa.meishi.Hui.domain;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by buyuqi on 2019/12/17.
 */
public class CommanDiff {
  //  private static Map<String ,List<String>> map= new HashMap<>();
//
//    public static void setMap(String key,String value){
//        map.put(key,value);
//    }
//
//    public static Map<String,String> getMap(){
//        return map;
//    }

    public static AtomicInteger diffSumCount = new AtomicInteger(0);
    public static AtomicInteger diffFailCount = new AtomicInteger(0);
    public static Map<String,String> diffFailCountMap = new ConcurrentHashMap<>();

}
