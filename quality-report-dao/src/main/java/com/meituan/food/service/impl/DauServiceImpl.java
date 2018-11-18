package com.meituan.food.service.impl;

import com.meituan.food.mapper.RestaurantDauMapper;
import com.meituan.food.po.RestaurantDau;
import com.meituan.food.service.IDauService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DauServiceImpl implements IDauService {

    private final static Logger log=LoggerFactory.getLogger(DauServiceImpl.class);

    @Autowired
    private RestaurantDauMapper restaurantDauMapper;

    @Override
    public long getWeekDaus(String os, String app, String startDate, String endDate){

        List<RestaurantDau>  weekDaus=restaurantDauMapper.getDauListByOsPartitionAppAndPartitionDateRang(os,app,startDate,endDate);

        Long totalDau=weekDaus.stream().map(RestaurantDau::getCateringDau).reduce(0l,(x,y)->x+y);

        return totalDau;

    }
}
