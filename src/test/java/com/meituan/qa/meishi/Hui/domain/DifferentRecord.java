package com.meituan.qa.meishi.Hui.domain;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.meituan.qa.meishi.util.ClassAnnotation;
import com.sankuai.food.jobscheduleapi.request.InvokeRequest;
import com.sankuai.food.jobscheduleapi.response.InvokeResult;
import com.sankuai.food.jobscheduleapi.service.InvokeTaskServiceI;
import com.sankuai.mptrade.datatoolapi.param.DifferentRecordDTO;
import com.sankuai.mptrade.datatoolapi.param.QueryRequest;
import com.sankuai.mptrade.datatoolapi.service.DataCompareAssistService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static com.sankuai.food.jobscheduleapi.response.InvokeResult.FAIL;

/**
 * Created by buyuqi on 2019/12/12.
 */
@Slf4j
@ClassAnnotation(author = "byq", depart = "C", des = "新老订单diff工具")
public class DifferentRecord extends CommanDiff {
    public DifferentRecord(DataCompareAssistService dataCompareAssistService,InvokeTaskServiceI invokeTaskServiceI) {
        this.dataCompareAssistService = dataCompareAssistService;
        this.invokeTaskServiceI = invokeTaskServiceI;
    }
    DataCompareAssistService dataCompareAssistService;
    InvokeTaskServiceI invokeTaskServiceI;


    public void differentRecordList(String oldKey,String newKey, String caseName){
        //计数
        int failDiffCount = diffFailCount.incrementAndGet();
        log.info("不一致个数:{}",failDiffCount);
        //赋值
        if (diffFailCountMap.size() == 0){
            diffFailCountMap.put(String.valueOf(failDiffCount),caseName);
        }else {
            diffFailCountMap.put(String.valueOf(failDiffCount),diffFailCountMap.get(String.valueOf(failDiffCount - 1)) + "," + caseName);
        }
        log.info("数据不一致case:{}",diffFailCountMap);


        QueryRequest request = new QueryRequest();
        String comparekeyStr = "oldKey=" + oldKey + ",newKey=" + newKey;
        request.setCompareKey(comparekeyStr);
        log.info("对比参数:{}",request);
        List<DifferentRecordDTO> differentRecordDTOS = dataCompareAssistService.listDifferentRecord(request);

        //if(differentRecordDTOS.size()<=0){
            log.info("对比结果:{}", JSON.toJSONString(differentRecordDTOS));
        //}
    }

    public void diffRecordList(String oldKey,String newKey, String caseName){

        int sumDiffCount = diffSumCount.incrementAndGet();
        log.info("{}",sumDiffCount);

        InvokeRequest request = new InvokeRequest();
        //不一致测试数据
        //String comparekeyStr = "oldKey=191217928646374,newKey=4791834405995505251,bizConfigId=10";

        //String comparekeyStr = "oldKey=" + oldKey + ",newKey=" + newKey + ",bizConfigId=10";
        JSONObject msg = new JSONObject();
        msg.put("newKey",newKey);
        msg.put("oldKey",oldKey);
        msg.put("bizConfigId","13");
        String msgStr = msg.toString();

        request.setRetryTime(30);
        request.setMsg(msgStr);
        //request.setMsg();
        log.info("对比参数:{}",request);
        InvokeResult invokeResult = invokeTaskServiceI.invokeV2(request);
        if (invokeResult.equals(FAIL)){
            log.info("总对比次数:{}",invokeResult);
            differentRecordList(oldKey,newKey,caseName);
        }

    }
}
