package com.meituan.food.mapper;

import com.meituan.food.po.WeekBugTotalCountPO;
import com.meituan.food.po.WeekBugTotalCountPOExample;
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

public interface WeekBugTotalCountPOMapper {
    @SelectProvider(type=WeekBugTotalCountPOSqlProvider.class, method="countByExample")
    long countByExample(WeekBugTotalCountPOExample example);

    @DeleteProvider(type=WeekBugTotalCountPOSqlProvider.class, method="deleteByExample")
    int deleteByExample(WeekBugTotalCountPOExample example);

    @Delete({
        "delete from week_bug_total_count",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into week_bug_total_count (id, group_name, ",
        "major_count, blocker_count, ",
        "critical_count, minor_count, ",
        "trivial_count, total_count, ",
        "bug_link, start_date, ",
        "end_date, time_flag, ",
        "bug_date)",
        "values (#{id,jdbcType=INTEGER}, #{groupName,jdbcType=VARCHAR}, ",
        "#{majorCount,jdbcType=INTEGER}, #{blockerCount,jdbcType=INTEGER}, ",
        "#{criticalCount,jdbcType=INTEGER}, #{minorCount,jdbcType=INTEGER}, ",
        "#{trivialCount,jdbcType=INTEGER}, #{totalCount,jdbcType=INTEGER}, ",
        "#{bugLink,jdbcType=VARCHAR}, #{startDate,jdbcType=VARCHAR}, ",
        "#{endDate,jdbcType=VARCHAR}, #{timeFlag,jdbcType=BIGINT}, ",
        "#{bugDate,jdbcType=DATE})"
    })
    int insert(WeekBugTotalCountPO record);

    @InsertProvider(type=WeekBugTotalCountPOSqlProvider.class, method="insertSelective")
    int insertSelective(WeekBugTotalCountPO record);

    @SelectProvider(type=WeekBugTotalCountPOSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="group_name", property="groupName", jdbcType=JdbcType.VARCHAR),
        @Result(column="major_count", property="majorCount", jdbcType=JdbcType.INTEGER),
        @Result(column="blocker_count", property="blockerCount", jdbcType=JdbcType.INTEGER),
        @Result(column="critical_count", property="criticalCount", jdbcType=JdbcType.INTEGER),
        @Result(column="minor_count", property="minorCount", jdbcType=JdbcType.INTEGER),
        @Result(column="trivial_count", property="trivialCount", jdbcType=JdbcType.INTEGER),
        @Result(column="total_count", property="totalCount", jdbcType=JdbcType.INTEGER),
        @Result(column="bug_link", property="bugLink", jdbcType=JdbcType.VARCHAR),
        @Result(column="start_date", property="startDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="end_date", property="endDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="time_flag", property="timeFlag", jdbcType=JdbcType.BIGINT),
        @Result(column="bug_date", property="bugDate", jdbcType=JdbcType.DATE)
    })
    List<WeekBugTotalCountPO> selectByExample(WeekBugTotalCountPOExample example);

    @Select({
        "select",
        "id, group_name, major_count, blocker_count, critical_count, minor_count, trivial_count, ",
        "total_count, bug_link, start_date, end_date, time_flag, bug_date",
        "from week_bug_total_count",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="group_name", property="groupName", jdbcType=JdbcType.VARCHAR),
        @Result(column="major_count", property="majorCount", jdbcType=JdbcType.INTEGER),
        @Result(column="blocker_count", property="blockerCount", jdbcType=JdbcType.INTEGER),
        @Result(column="critical_count", property="criticalCount", jdbcType=JdbcType.INTEGER),
        @Result(column="minor_count", property="minorCount", jdbcType=JdbcType.INTEGER),
        @Result(column="trivial_count", property="trivialCount", jdbcType=JdbcType.INTEGER),
        @Result(column="total_count", property="totalCount", jdbcType=JdbcType.INTEGER),
        @Result(column="bug_link", property="bugLink", jdbcType=JdbcType.VARCHAR),
        @Result(column="start_date", property="startDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="end_date", property="endDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="time_flag", property="timeFlag", jdbcType=JdbcType.BIGINT),
        @Result(column="bug_date", property="bugDate", jdbcType=JdbcType.DATE)
    })
    WeekBugTotalCountPO selectByPrimaryKey(Integer id);

    @UpdateProvider(type=WeekBugTotalCountPOSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") WeekBugTotalCountPO record, @Param("example") WeekBugTotalCountPOExample example);

    @UpdateProvider(type=WeekBugTotalCountPOSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") WeekBugTotalCountPO record, @Param("example") WeekBugTotalCountPOExample example);

    @UpdateProvider(type=WeekBugTotalCountPOSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(WeekBugTotalCountPO record);

    @Update({
        "update week_bug_total_count",
        "set group_name = #{groupName,jdbcType=VARCHAR},",
          "major_count = #{majorCount,jdbcType=INTEGER},",
          "blocker_count = #{blockerCount,jdbcType=INTEGER},",
          "critical_count = #{criticalCount,jdbcType=INTEGER},",
          "minor_count = #{minorCount,jdbcType=INTEGER},",
          "trivial_count = #{trivialCount,jdbcType=INTEGER},",
          "total_count = #{totalCount,jdbcType=INTEGER},",
          "bug_link = #{bugLink,jdbcType=VARCHAR},",
          "start_date = #{startDate,jdbcType=VARCHAR},",
          "end_date = #{endDate,jdbcType=VARCHAR},",
          "time_flag = #{timeFlag,jdbcType=BIGINT},",
          "bug_date = #{bugDate,jdbcType=DATE}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(WeekBugTotalCountPO record);
}