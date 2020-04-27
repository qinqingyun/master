package com.meituan.food.extract.impl;

import com.meituan.food.ApplicationLoader;
import com.meituan.food.extract.IUpdateApiExtract;
import com.meituan.food.mapper.ApiDetailPOMapper;
import com.meituan.food.po.ApiDetailPO;
import groovy.util.logging.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationLoader.class)
public class UpdateApiExtracterTest {

    @Resource
    private IUpdateApiExtract updateApiExtract;

    @Resource
    private ApiDetailPOMapper apiDetailPOMapper;

    @Test
    public void updateApi() {
        updateApiExtract.updateApi();
    }

    @Test
    public void testBatchInsert(){
        ApiDetailPO po=new ApiDetailPO();
        po.setAppkey("com.sankuai.sjst.ecom.feepassport");
        po.setApiSpanName("PATH.GET./Editor/UEditor/beans");
        po.setCallCount(3l);
        List<ApiDetailPO> list=new ArrayList<>();
        list.add(po);
        apiDetailPOMapper.batchInsert(list);
    }
}