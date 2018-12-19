package com.meituan.food.mapper;

import com.meituan.food.po.BCrashRatePO;
import com.meituan.food.po.BCrashRatePOExample;
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

public interface BCrashRatePOMapper {
    @SelectProvider(type=BCrashRatePOSqlProvider.class, method="countByExample")
    long countByExample(BCrashRatePOExample example);

    @DeleteProvider(type=BCrashRatePOSqlProvider.class, method="deleteByExample")
    int deleteByExample(BCrashRatePOExample example);

    @Delete({
        "delete from b_crash_rate",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into b_crash_rate (id, b_crash_rate, ",
        "month, b_dianping, ",
        "b_waimai, created_at, ",
        "updated_at, platfrom, ",
        "os, b_crash_count, ",
        "b_dianping_crash_count, b_waimai_crash_count)",
        "values (#{id,jdbcType=INTEGER}, #{bCrashRate,jdbcType=DECIMAL}, ",
        "#{month,jdbcType=VARCHAR}, #{bDianping,jdbcType=DECIMAL}, ",
        "#{bWaimai,jdbcType=DECIMAL}, #{createdAt,jdbcType=TIMESTAMP}, ",
        "#{updatedAt,jdbcType=TIMESTAMP}, #{platfrom,jdbcType=VARCHAR}, ",
        "#{os,jdbcType=VARCHAR}, #{bCrashCount,jdbcType=INTEGER}, ",
        "#{bDianpingCrashCount,jdbcType=INTEGER}, #{bWaimaiCrashCount,jdbcType=INTEGER})"
    })
    int insert(BCrashRatePO record);

    @InsertProvider(type=BCrashRatePOSqlProvider.class, method="insertSelective")
    int insertSelective(BCrashRatePO record);

    @SelectProvider(type=BCrashRatePOSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="b_crash_rate", property="bCrashRate", jdbcType=JdbcType.DECIMAL),
        @Result(column="month", property="month", jdbcType=JdbcType.VARCHAR),
        @Result(column="b_dianping", property="bDianping", jdbcType=JdbcType.DECIMAL),
        @Result(column="b_waimai", property="bWaimai", jdbcType=JdbcType.DECIMAL),
        @Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="updated_at", property="updatedAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="platfrom", property="platfrom", jdbcType=JdbcType.VARCHAR),
        @Result(column="os", property="os", jdbcType=JdbcType.VARCHAR),
        @Result(column="b_crash_count", property="bCrashCount", jdbcType=JdbcType.INTEGER),
        @Result(column="b_dianping_crash_count", property="bDianpingCrashCount", jdbcType=JdbcType.INTEGER),
        @Result(column="b_waimai_crash_count", property="bWaimaiCrashCount", jdbcType=JdbcType.INTEGER)
    })
    List<BCrashRatePO> selectByExample(BCrashRatePOExample example);

    @Select({
        "select",
        "id, b_crash_rate, month, b_dianping, b_waimai, created_at, updated_at, platfrom, ",
        "os, b_crash_count, b_dianping_crash_count, b_waimai_crash_count",
        "from b_crash_rate",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="b_crash_rate", property="bCrashRate", jdbcType=JdbcType.DECIMAL),
        @Result(column="month", property="month", jdbcType=JdbcType.VARCHAR),
        @Result(column="b_dianping", property="bDianping", jdbcType=JdbcType.DECIMAL),
        @Result(column="b_waimai", property="bWaimai", jdbcType=JdbcType.DECIMAL),
        @Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="updated_at", property="updatedAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="platfrom", property="platfrom", jdbcType=JdbcType.VARCHAR),
        @Result(column="os", property="os", jdbcType=JdbcType.VARCHAR),
        @Result(column="b_crash_count", property="bCrashCount", jdbcType=JdbcType.INTEGER),
        @Result(column="b_dianping_crash_count", property="bDianpingCrashCount", jdbcType=JdbcType.INTEGER),
        @Result(column="b_waimai_crash_count", property="bWaimaiCrashCount", jdbcType=JdbcType.INTEGER)
    })
    BCrashRatePO selectByPrimaryKey(Integer id);

    @UpdateProvider(type=BCrashRatePOSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") BCrashRatePO record, @Param("example") BCrashRatePOExample example);

    @UpdateProvider(type=BCrashRatePOSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") BCrashRatePO record, @Param("example") BCrashRatePOExample example);

    @UpdateProvider(type=BCrashRatePOSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(BCrashRatePO record);

    @Update({
        "update b_crash_rate",
        "set b_crash_rate = #{bCrashRate,jdbcType=DECIMAL},",
          "month = #{month,jdbcType=VARCHAR},",
          "b_dianping = #{bDianping,jdbcType=DECIMAL},",
          "b_waimai = #{bWaimai,jdbcType=DECIMAL},",
          "created_at = #{createdAt,jdbcType=TIMESTAMP},",
          "updated_at = #{updatedAt,jdbcType=TIMESTAMP},",
          "platfrom = #{platfrom,jdbcType=VARCHAR},",
          "os = #{os,jdbcType=VARCHAR},",
          "b_crash_count = #{bCrashCount,jdbcType=INTEGER},",
          "b_dianping_crash_count = #{bDianpingCrashCount,jdbcType=INTEGER},",
          "b_waimai_crash_count = #{bWaimaiCrashCount,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(BCrashRatePO record);
}