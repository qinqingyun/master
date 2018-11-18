package com.meituan.food.service.impl;

import com.meituan.food.mapper.RestaurantDauMapper;
import com.meituan.food.po.RestaurantDau;
import com.meituan.food.service.IDauService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class DauServiceImpl implements IDauService {

    @Autowired
    private RestaurantDauMapper restaurantDauMapper;

    @Override
    public long getWeekDaus(String os, String app, String startDate, String endDate) {

        List<RestaurantDau> weekDaus = restaurantDauMapper.getDauListByOsPartitionAppAndPartitionDateRang(os, app, startDate, endDate);

        Long totalDau = weekDaus.stream()
                .map(RestaurantDau::getCateringDau)
                .reduce(0l, (x, y) -> x + y);

        log.info("getWeekDaus os: {},app: {},startDate: {},endDate: {},weekDaus: {},totalDau: {}",
                os, app, startDate, endDate, weekDaus, totalDau);
        return totalDau;

    }
}
