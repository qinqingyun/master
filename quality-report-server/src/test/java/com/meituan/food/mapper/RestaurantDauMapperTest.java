package com.meituan.food.mapper;

import com.meituan.food.ApplicationLoader;
import com.meituan.food.extract.impl.CrashRateExtracter;
import com.meituan.food.extract.impl.GetCoverageExtracter;
import com.meituan.food.job.IGetApiCoverageJob;
import com.meituan.food.po.RestaurantDau;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationLoader.class)
public class RestaurantDauMapperTest {

    @Resource
    private RestaurantDauMapper restaurantDauMapper;

    @Resource
    public AppkeyListPOMapper appkeyListPOMapper;

    @Resource
    private IGetApiCoverageJob apiCoverageJob;

    @Test
    public void getDauListByOsPartitionAppAndPartitionDateRang() {
        List<RestaurantDau> lists = restaurantDauMapper.getDauListByOsPartitionAppAndPartitionDateRang(
                CrashRateExtracter.WidgetEnum.DIANPING_IOS.getDauOs(),
                CrashRateExtracter.WidgetEnum.DIANPING_IOS.getDauPartitionApp(),
                "2018-07-18",
                "2018-07-18");
        log.error("lists: {}", lists);
    }

    @Test
    public void getCoverageTest(){
        apiCoverageJob.sync();
    }

}