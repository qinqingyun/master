package com.meituan.food.mapper;

import com.meituan.food.po.WeekBCrashPO;
import com.meituan.food.po.WeekBCrashPOExample;
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

public interface WeekBCrashPOMapper {
    @SelectProvider(type=WeekBCrashPOSqlProvider.class, method="countByExample")
    long countByExample(WeekBCrashPOExample example);

    @DeleteProvider(type=WeekBCrashPOSqlProvider.class, method="deleteByExample")
    int deleteByExample(WeekBCrashPOExample example);

    @Delete({
        "delete from week_b_crash",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into week_b_crash (id, b_crash_rate, ",
        "b_dianping, b_waimai, ",
        "platform, os, b_crash_count, ",
        "b_dianping_crash_count, b_waimai_crash_count, ",
        "start_date, end_date, ",
        "created_at, date_range, ",
        "flag)",
        "values (#{id,jdbcType=INTEGER}, #{bCrashRate,jdbcType=DECIMAL}, ",
        "#{bDianping,jdbcType=DECIMAL}, #{bWaimai,jdbcType=DECIMAL}, ",
        "#{platform,jdbcType=VARCHAR}, #{os,jdbcType=VARCHAR}, #{bCrashCount,jdbcType=INTEGER}, ",
        "#{bDianpingCrashCount,jdbcType=INTEGER}, #{bWaimaiCrashCount,jdbcType=INTEGER}, ",
        "#{startDate,jdbcType=VARCHAR}, #{endDate,jdbcType=VARCHAR}, ",
        "#{createdAt,jdbcType=TIMESTAMP}, #{dateRange,jdbcType=VARCHAR}, ",
        "#{flag,jdbcType=INTEGER})"
    })
    int insert(WeekBCrashPO record);

    @InsertProvider(type=WeekBCrashPOSqlProvider.class, method="insertSelective")
    int insertSelective(WeekBCrashPO record);

    @SelectProvider(type=WeekBCrashPOSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="b_crash_rate", property="bCrashRate", jdbcType=JdbcType.DECIMAL),
        @Result(column="b_dianping", property="bDianping", jdbcType=JdbcType.DECIMAL),
        @Result(column="b_waimai", property="bWaimai", jdbcType=JdbcType.DECIMAL),
        @Result(column="platform", property="platform", jdbcType=JdbcType.VARCHAR),
        @Result(column="os", property="os", jdbcType=JdbcType.VARCHAR),
        @Result(column="b_crash_count", property="bCrashCount", jdbcType=JdbcType.INTEGER),
        @Result(column="b_dianping_crash_count", property="bDianpingCrashCount", jdbcType=JdbcType.INTEGER),
        @Result(column="b_waimai_crash_count", property="bWaimaiCrashCount", jdbcType=JdbcType.INTEGER),
        @Result(column="start_date", property="startDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="end_date", property="endDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="date_range", property="dateRange", jdbcType=JdbcType.VARCHAR),
        @Result(column="flag", property="flag", jdbcType=JdbcType.INTEGER)
    })
    List<WeekBCrashPO> selectByExample(WeekBCrashPOExample example);

    @Select({
        "select",
        "id, b_crash_rate, b_dianping, b_waimai, platform, os, b_crash_count, b_dianping_crash_count, ",
        "b_waimai_crash_count, start_date, end_date, created_at, date_range, flag",
        "from week_b_crash",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="b_crash_rate", property="bCrashRate", jdbcType=JdbcType.DECIMAL),
        @Result(column="b_dianping", property="bDianping", jdbcType=JdbcType.DECIMAL),
        @Result(column="b_waimai", property="bWaimai", jdbcType=JdbcType.DECIMAL),
        @Result(column="platform", property="platform", jdbcType=JdbcType.VARCHAR),
        @Result(column="os", property="os", jdbcType=JdbcType.VARCHAR),
        @Result(column="b_crash_count", property="bCrashCount", jdbcType=JdbcType.INTEGER),
        @Result(column="b_dianping_crash_count", property="bDianpingCrashCount", jdbcType=JdbcType.INTEGER),
        @Result(column="b_waimai_crash_count", property="bWaimaiCrashCount", jdbcType=JdbcType.INTEGER),
        @Result(column="start_date", property="startDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="end_date", property="endDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="date_range", property="dateRange", jdbcType=JdbcType.VARCHAR),
        @Result(column="flag", property="flag", jdbcType=JdbcType.INTEGER)
    })
    WeekBCrashPO selectByPrimaryKey(Integer id);

    @UpdateProvider(type=WeekBCrashPOSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") WeekBCrashPO record, @Param("example") WeekBCrashPOExample example);

    @UpdateProvider(type=WeekBCrashPOSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") WeekBCrashPO record, @Param("example") WeekBCrashPOExample example);

    @UpdateProvider(type=WeekBCrashPOSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(WeekBCrashPO record);

    @Update({
        "update week_b_crash",
        "set b_crash_rate = #{bCrashRate,jdbcType=DECIMAL},",
          "b_dianping = #{bDianping,jdbcType=DECIMAL},",
          "b_waimai = #{bWaimai,jdbcType=DECIMAL},",
          "platform = #{platform,jdbcType=VARCHAR},",
          "os = #{os,jdbcType=VARCHAR},",
          "b_crash_count = #{bCrashCount,jdbcType=INTEGER},",
          "b_dianping_crash_count = #{bDianpingCrashCount,jdbcType=INTEGER},",
          "b_waimai_crash_count = #{bWaimaiCrashCount,jdbcType=INTEGER},",
          "start_date = #{startDate,jdbcType=VARCHAR},",
          "end_date = #{endDate,jdbcType=VARCHAR},",
          "created_at = #{createdAt,jdbcType=TIMESTAMP},",
          "date_range = #{dateRange,jdbcType=VARCHAR},",
          "flag = #{flag,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(WeekBCrashPO record);
}