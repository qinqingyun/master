package com.meituan.food.Services;

import com.google.common.collect.Lists;
import com.meituan.food.mapper.ApiDetailPOMapper;
import com.meituan.food.po.ApiDetailPO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class ApiDetailService {

    @Resource
    private ApiDetailPOMapper apiDetailPOMapper;

    @Transactional
    public void refreshApiDetail(List<ApiDetailPO> apiDetailPOList) {
        apiDetailPOMapper.deleteAllData();
        List<List<ApiDetailPO>> partitionDetails = Lists.partition(apiDetailPOList, 500);
        partitionDetails.forEach(apiDetailPOS -> apiDetailPOMapper.batchInsert(apiDetailPOS));
    }
}
