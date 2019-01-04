package com.meituan.food.mapper;

import com.meituan.food.po.RestaurantDau;
import com.meituan.food.po.RestaurantXueCheng;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface RestaurantXueChengMapper {

  /*  @Select("select * from restaurant_dau where os=#{os} " +
            "and partition_app=#{partitionApp} " +
            "and partition_date>=#{startPartitionDate} " +
            "and partition_date<=#{endPartitionDate}")
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "catering_dau", property = "cateringDau", jdbcType = JdbcType.BIGINT),
            @Result(column = "partition_date", property = "partitionDate", jdbcType = JdbcType.VARCHAR),
            @Result(column = "os", property = "os", jdbcType = JdbcType.VARBINARY),
            @Result(column = "partition_app", property = "partitionApp", jdbcType = JdbcType.VARCHAR),
            @Result(column = "version", property = "version", jdbcType = JdbcType.VARCHAR),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP)
//            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP)
    })
    List<RestaurantDau> getDauListByOsPartitionAppAndPartitionDateRang(@Param("os") String os,
                                                                       @Param("partitionApp") String partitionApp,
                                                                       @Param("startPartitionDate") String startPartitionDate,
                                                                       @Param("endPartitionDate") String endPartitionDate);*/

    @Select("select * from restaurant_xuecheng where mis_id = #{mis}"+
    "and partition_date=#{partitionDate}")
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="create_count", property="createCount", jdbcType=JdbcType.BIGINT),
            @Result(column="update_count", property="updateCount", jdbcType=JdbcType.BIGINT),
            @Result(column="partition_date", property="partitionDate", jdbcType=JdbcType.VARCHAR),
            @Result(column="mis_id", property="misId", jdbcType=JdbcType.VARCHAR),
            @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            //   @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    RestaurantXueCheng selectByPrimaryMis(String mis);
}
