package com.meituan.food.extract.impl;

import com.meituan.food.extract.IOneDayEffDataEx;
import com.meituan.food.mapper.RestaurantXueChengPOMapper;
import com.meituan.food.po.RestaurantXueChengPO;

import javax.annotation.Resource;
import java.time.LocalDate;

public class MailDataExtract implements IOneDayEffDataEx {

    @Resource
    private RestaurantXueChengPOMapper restaurantXueChengPOMapper;

    @Override
    public void extractData4EffDay(LocalDate day) {

        RestaurantXueChengPO restaurantXueChengPO = restaurantXueChengPOMapper.selectByPrimaryMis("guomengyao");

    }
}
