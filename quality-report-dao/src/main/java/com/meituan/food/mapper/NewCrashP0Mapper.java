package com.meituan.food.mapper;

import com.meituan.food.po.NewCrashP0;
import com.meituan.food.po.NewCrashP0Example;
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

public interface NewCrashP0Mapper {
    @SelectProvider(type=NewCrashP0SqlProvider.class, method="countByExample")
    long countByExample(NewCrashP0Example example);

    @DeleteProvider(type=NewCrashP0SqlProvider.class, method="deleteByExample")
    int deleteByExample(NewCrashP0Example example);

    @Delete({
        "delete from new_crash",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into new_crash (id, platform, ",
        "crash, crash_rate, ",
        "crash_date, created_time, ",
        "flag, date_range, ",
        "os)",
        "values (#{id,jdbcType=INTEGER}, #{platform,jdbcType=VARCHAR}, ",
        "#{crash,jdbcType=INTEGER}, #{crashRate,jdbcType=DECIMAL}, ",
        "#{crashDate,jdbcType=DATE}, #{createdTime,jdbcType=TIMESTAMP}, ",
        "#{flag,jdbcType=INTEGER}, #{dateRange,jdbcType=VARCHAR}, ",
        "#{os,jdbcType=VARCHAR})"
    })
    int insert(NewCrashP0 record);

    @InsertProvider(type=NewCrashP0SqlProvider.class, method="insertSelective")
    int insertSelective(NewCrashP0 record);

    @SelectProvider(type=NewCrashP0SqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="platform", property="platform", jdbcType=JdbcType.VARCHAR),
        @Result(column="crash", property="crash", jdbcType=JdbcType.INTEGER),
        @Result(column="crash_rate", property="crashRate", jdbcType=JdbcType.DECIMAL),
        @Result(column="crash_date", property="crashDate", jdbcType=JdbcType.DATE),
        @Result(column="created_time", property="createdTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="flag", property="flag", jdbcType=JdbcType.INTEGER),
        @Result(column="date_range", property="dateRange", jdbcType=JdbcType.VARCHAR),
        @Result(column="os", property="os", jdbcType=JdbcType.VARCHAR)
    })
    List<NewCrashP0> selectByExample(NewCrashP0Example example);

    @Select({
        "select",
        "id, platform, crash, crash_rate, crash_date, created_time, flag, date_range, ",
        "os",
        "from new_crash",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="platform", property="platform", jdbcType=JdbcType.VARCHAR),
        @Result(column="crash", property="crash", jdbcType=JdbcType.INTEGER),
        @Result(column="crash_rate", property="crashRate", jdbcType=JdbcType.DECIMAL),
        @Result(column="crash_date", property="crashDate", jdbcType=JdbcType.DATE),
        @Result(column="created_time", property="createdTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="flag", property="flag", jdbcType=JdbcType.INTEGER),
        @Result(column="date_range", property="dateRange", jdbcType=JdbcType.VARCHAR),
        @Result(column="os", property="os", jdbcType=JdbcType.VARCHAR)
    })
    NewCrashP0 selectByPrimaryKey(Integer id);

    @UpdateProvider(type=NewCrashP0SqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") NewCrashP0 record, @Param("example") NewCrashP0Example example);

    @UpdateProvider(type=NewCrashP0SqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") NewCrashP0 record, @Param("example") NewCrashP0Example example);

    @UpdateProvider(type=NewCrashP0SqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(NewCrashP0 record);

    @Update({
        "update new_crash",
        "set platform = #{platform,jdbcType=VARCHAR},",
          "crash = #{crash,jdbcType=INTEGER},",
          "crash_rate = #{crashRate,jdbcType=DECIMAL},",
          "crash_date = #{crashDate,jdbcType=DATE},",
          "created_time = #{createdTime,jdbcType=TIMESTAMP},",
          "flag = #{flag,jdbcType=INTEGER},",
          "date_range = #{dateRange,jdbcType=VARCHAR},",
          "os = #{os,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(NewCrashP0 record);
}