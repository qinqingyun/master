package com.meituan.food.mapper;

import com.meituan.food.po.WeekMomaCrashPO;
import com.meituan.food.po.WeekMomaCrashPOExample;
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

public interface WeekMomaCrashPOMapper {
    @SelectProvider(type=WeekMomaCrashPOSqlProvider.class, method="countByExample")
    long countByExample(WeekMomaCrashPOExample example);

    @DeleteProvider(type=WeekMomaCrashPOSqlProvider.class, method="deleteByExample")
    int deleteByExample(WeekMomaCrashPOExample example);

    @Delete({
        "delete from week_moma_crash",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into week_moma_crash (id, moma_crash_count, ",
        "moma_crash_rate, bee_crash_count, ",
        "bee_crash_rate, aboluo_crash_count, ",
        "aboluo_crash_rate, start_date, ",
        "end_date, platform, ",
        "os, created_at, ",
        "date_range, flag)",
        "values (#{id,jdbcType=INTEGER}, #{momaCrashCount,jdbcType=INTEGER}, ",
        "#{momaCrashRate,jdbcType=DECIMAL}, #{beeCrashCount,jdbcType=INTEGER}, ",
        "#{beeCrashRate,jdbcType=DECIMAL}, #{aboluoCrashCount,jdbcType=INTEGER}, ",
        "#{aboluoCrashRate,jdbcType=DECIMAL}, #{startDate,jdbcType=VARCHAR}, ",
        "#{endDate,jdbcType=VARCHAR}, #{platform,jdbcType=VARCHAR}, ",
        "#{os,jdbcType=VARCHAR}, #{createdAt,jdbcType=TIMESTAMP}, ",
        "#{dateRange,jdbcType=VARCHAR}, #{flag,jdbcType=INTEGER})"
    })
    int insert(WeekMomaCrashPO record);

    @InsertProvider(type=WeekMomaCrashPOSqlProvider.class, method="insertSelective")
    int insertSelective(WeekMomaCrashPO record);

    @SelectProvider(type=WeekMomaCrashPOSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="moma_crash_count", property="momaCrashCount", jdbcType=JdbcType.INTEGER),
        @Result(column="moma_crash_rate", property="momaCrashRate", jdbcType=JdbcType.DECIMAL),
        @Result(column="bee_crash_count", property="beeCrashCount", jdbcType=JdbcType.INTEGER),
        @Result(column="bee_crash_rate", property="beeCrashRate", jdbcType=JdbcType.DECIMAL),
        @Result(column="aboluo_crash_count", property="aboluoCrashCount", jdbcType=JdbcType.INTEGER),
        @Result(column="aboluo_crash_rate", property="aboluoCrashRate", jdbcType=JdbcType.DECIMAL),
        @Result(column="start_date", property="startDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="end_date", property="endDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="platform", property="platform", jdbcType=JdbcType.VARCHAR),
        @Result(column="os", property="os", jdbcType=JdbcType.VARCHAR),
        @Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="date_range", property="dateRange", jdbcType=JdbcType.VARCHAR),
        @Result(column="flag", property="flag", jdbcType=JdbcType.INTEGER)
    })
    List<WeekMomaCrashPO> selectByExample(WeekMomaCrashPOExample example);

    @Select({
        "select",
        "id, moma_crash_count, moma_crash_rate, bee_crash_count, bee_crash_rate, aboluo_crash_count, ",
        "aboluo_crash_rate, start_date, end_date, platform, os, created_at, date_range, ",
        "flag",
        "from week_moma_crash",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="moma_crash_count", property="momaCrashCount", jdbcType=JdbcType.INTEGER),
        @Result(column="moma_crash_rate", property="momaCrashRate", jdbcType=JdbcType.DECIMAL),
        @Result(column="bee_crash_count", property="beeCrashCount", jdbcType=JdbcType.INTEGER),
        @Result(column="bee_crash_rate", property="beeCrashRate", jdbcType=JdbcType.DECIMAL),
        @Result(column="aboluo_crash_count", property="aboluoCrashCount", jdbcType=JdbcType.INTEGER),
        @Result(column="aboluo_crash_rate", property="aboluoCrashRate", jdbcType=JdbcType.DECIMAL),
        @Result(column="start_date", property="startDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="end_date", property="endDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="platform", property="platform", jdbcType=JdbcType.VARCHAR),
        @Result(column="os", property="os", jdbcType=JdbcType.VARCHAR),
        @Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="date_range", property="dateRange", jdbcType=JdbcType.VARCHAR),
        @Result(column="flag", property="flag", jdbcType=JdbcType.INTEGER)
    })
    WeekMomaCrashPO selectByPrimaryKey(Integer id);

    @UpdateProvider(type=WeekMomaCrashPOSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") WeekMomaCrashPO record, @Param("example") WeekMomaCrashPOExample example);

    @UpdateProvider(type=WeekMomaCrashPOSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") WeekMomaCrashPO record, @Param("example") WeekMomaCrashPOExample example);

    @UpdateProvider(type=WeekMomaCrashPOSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(WeekMomaCrashPO record);

    @Update({
        "update week_moma_crash",
        "set moma_crash_count = #{momaCrashCount,jdbcType=INTEGER},",
          "moma_crash_rate = #{momaCrashRate,jdbcType=DECIMAL},",
          "bee_crash_count = #{beeCrashCount,jdbcType=INTEGER},",
          "bee_crash_rate = #{beeCrashRate,jdbcType=DECIMAL},",
          "aboluo_crash_count = #{aboluoCrashCount,jdbcType=INTEGER},",
          "aboluo_crash_rate = #{aboluoCrashRate,jdbcType=DECIMAL},",
          "start_date = #{startDate,jdbcType=VARCHAR},",
          "end_date = #{endDate,jdbcType=VARCHAR},",
          "platform = #{platform,jdbcType=VARCHAR},",
          "os = #{os,jdbcType=VARCHAR},",
          "created_at = #{createdAt,jdbcType=TIMESTAMP},",
          "date_range = #{dateRange,jdbcType=VARCHAR},",
          "flag = #{flag,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(WeekMomaCrashPO record);
}