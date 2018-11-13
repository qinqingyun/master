package com.meituan.food.mapper;

import com.meituan.food.po.RestaurantDau;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RestaurantDauMapper {

    @Select("select * from restaurant_dau where os=#{os} " +
            "and partition_app=#{partitionApp} " +
            "and partition_date>=#{startPartitionDate} " +
            "and partition_date<=#{endPartitionDate}")
    List<RestaurantDau> getDauListByOsPartitionAppAndPartitionDateRang(@Param("os") String os,
                                                                       @Param("partitionApp") String partitionApp,
                                                                       @Param("startPartitionDate") String startPartitionDate,
                                                                       @Param("endPartitionDate") String endPartitionDate);
}
