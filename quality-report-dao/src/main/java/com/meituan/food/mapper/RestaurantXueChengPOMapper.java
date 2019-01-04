package com.meituan.food.mapper;

import com.meituan.food.po.RestaurantXueChengPO;
import com.meituan.food.po.RestaurantXueChengPOExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface RestaurantXueChengPOMapper {
    @SelectProvider(type=RestaurantXueChengPOSqlProvider.class, method="countByExample")
    long countByExample(RestaurantXueChengPOExample example);

    @DeleteProvider(type=RestaurantXueChengPOSqlProvider.class, method="deleteByExample")
    int deleteByExample(RestaurantXueChengPOExample example);

    @Delete({
        "delete from restaurant_xuecheng",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into restaurant_xuecheng (id, create_count, ",
        "update_count, partition_date, ",
        "mis_id, create_time, ",
        "update_time)",
        "values (#{id,jdbcType=INTEGER}, #{createCount,jdbcType=BIGINT}, ",
        "#{updateCount,jdbcType=BIGINT}, #{partitionDate,jdbcType=VARCHAR}, ",
        "#{misId,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{updateTime,jdbcType=TIMESTAMP})"
    })
    int insert(RestaurantXueChengPO record);

    @InsertProvider(type=RestaurantXueChengPOSqlProvider.class, method="insertSelective")
    int insertSelective(RestaurantXueChengPO record);

    @SelectProvider(type=RestaurantXueChengPOSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="create_count", property="createCount", jdbcType=JdbcType.BIGINT),
        @Result(column="update_count", property="updateCount", jdbcType=JdbcType.BIGINT),
        @Result(column="partition_date", property="partitionDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="mis_id", property="misId", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<RestaurantXueChengPO> selectByExample(RestaurantXueChengPOExample example);

    @Select({
        "select",
        "id, create_count, update_count, partition_date, mis_id, create_time, update_time",
        "from restaurant_xuecheng",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="create_count", property="createCount", jdbcType=JdbcType.BIGINT),
        @Result(column="update_count", property="updateCount", jdbcType=JdbcType.BIGINT),
        @Result(column="partition_date", property="partitionDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="mis_id", property="misId", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    RestaurantXueChengPO selectByPrimaryKey(Integer id);

    @Select({
            "select",
            "id, create_count, update_count, partition_date, mis_id, create_time, update_time",
            "from restaurant_xuecheng",
            "where mis_id = #{mis,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="create_count", property="createCount", jdbcType=JdbcType.BIGINT),
            @Result(column="update_count", property="updateCount", jdbcType=JdbcType.BIGINT),
            @Result(column="partition_date", property="partitionDate", jdbcType=JdbcType.VARCHAR),
            @Result(column="mis_id", property="misId", jdbcType=JdbcType.VARCHAR),
            @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    RestaurantXueChengPO selectByPrimaryMis(String mis);

    @UpdateProvider(type=RestaurantXueChengPOSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") RestaurantXueChengPO record, @Param("example") RestaurantXueChengPOExample example);

    @UpdateProvider(type=RestaurantXueChengPOSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") RestaurantXueChengPO record, @Param("example") RestaurantXueChengPOExample example);

    @UpdateProvider(type=RestaurantXueChengPOSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(RestaurantXueChengPO record);

    @Update({
        "update restaurant_xuecheng",
        "set create_count = #{createCount,jdbcType=BIGINT},",
          "update_count = #{updateCount,jdbcType=BIGINT},",
          "partition_date = #{partitionDate,jdbcType=VARCHAR},",
          "mis_id = #{misId,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(RestaurantXueChengPO record);
}