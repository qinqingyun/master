package com.meituan.food.mapper;

import com.meituan.food.po.WeekBugDetailPO;
import com.meituan.food.po.WeekBugDetailPOExample;
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

public interface WeekBugDetailPOMapper {
    @SelectProvider(type=WeekBugDetailPOSqlProvider.class, method="countByExample")
    long countByExample(WeekBugDetailPOExample example);

    @DeleteProvider(type=WeekBugDetailPOSqlProvider.class, method="deleteByExample")
    int deleteByExample(WeekBugDetailPOExample example);

    @Delete({
        "delete from week_bug_detail",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into week_bug_detail (id, title, ",
        "bug_level, reason, ",
        "creator, receiver, ",
        "bug_status, created_time, ",
        "time_flag, orgname, ",
        "orgid, all_title, ",
        "bug_link, start_date, ",
        "end_date)",
        "values (#{id,jdbcType=INTEGER}, #{title,jdbcType=VARCHAR}, ",
        "#{bugLevel,jdbcType=VARCHAR}, #{reason,jdbcType=VARCHAR}, ",
        "#{creator,jdbcType=VARCHAR}, #{receiver,jdbcType=VARCHAR}, ",
        "#{bugStatus,jdbcType=VARCHAR}, #{createdTime,jdbcType=VARCHAR}, ",
        "#{timeFlag,jdbcType=BIGINT}, #{orgname,jdbcType=VARCHAR}, ",
        "#{orgid,jdbcType=VARCHAR}, #{allTitle,jdbcType=VARCHAR}, ",
        "#{bugLink,jdbcType=VARCHAR}, #{startDate,jdbcType=VARCHAR}, ",
        "#{endDate,jdbcType=VARCHAR})"
    })
    int insert(WeekBugDetailPO record);

    @InsertProvider(type=WeekBugDetailPOSqlProvider.class, method="insertSelective")
    int insertSelective(WeekBugDetailPO record);

    @SelectProvider(type=WeekBugDetailPOSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="bug_level", property="bugLevel", jdbcType=JdbcType.VARCHAR),
        @Result(column="reason", property="reason", jdbcType=JdbcType.VARCHAR),
        @Result(column="creator", property="creator", jdbcType=JdbcType.VARCHAR),
        @Result(column="receiver", property="receiver", jdbcType=JdbcType.VARCHAR),
        @Result(column="bug_status", property="bugStatus", jdbcType=JdbcType.VARCHAR),
        @Result(column="created_time", property="createdTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="time_flag", property="timeFlag", jdbcType=JdbcType.BIGINT),
        @Result(column="orgname", property="orgname", jdbcType=JdbcType.VARCHAR),
        @Result(column="orgid", property="orgid", jdbcType=JdbcType.VARCHAR),
        @Result(column="all_title", property="allTitle", jdbcType=JdbcType.VARCHAR),
        @Result(column="bug_link", property="bugLink", jdbcType=JdbcType.VARCHAR),
        @Result(column="start_date", property="startDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="end_date", property="endDate", jdbcType=JdbcType.VARCHAR)
    })
    List<WeekBugDetailPO> selectByExample(WeekBugDetailPOExample example);

    @Select({
        "select",
        "id, title, bug_level, reason, creator, receiver, bug_status, created_time, time_flag, ",
        "orgname, orgid, all_title, bug_link, start_date, end_date",
        "from week_bug_detail",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="bug_level", property="bugLevel", jdbcType=JdbcType.VARCHAR),
        @Result(column="reason", property="reason", jdbcType=JdbcType.VARCHAR),
        @Result(column="creator", property="creator", jdbcType=JdbcType.VARCHAR),
        @Result(column="receiver", property="receiver", jdbcType=JdbcType.VARCHAR),
        @Result(column="bug_status", property="bugStatus", jdbcType=JdbcType.VARCHAR),
        @Result(column="created_time", property="createdTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="time_flag", property="timeFlag", jdbcType=JdbcType.BIGINT),
        @Result(column="orgname", property="orgname", jdbcType=JdbcType.VARCHAR),
        @Result(column="orgid", property="orgid", jdbcType=JdbcType.VARCHAR),
        @Result(column="all_title", property="allTitle", jdbcType=JdbcType.VARCHAR),
        @Result(column="bug_link", property="bugLink", jdbcType=JdbcType.VARCHAR),
        @Result(column="start_date", property="startDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="end_date", property="endDate", jdbcType=JdbcType.VARCHAR)
    })
    WeekBugDetailPO selectByPrimaryKey(Integer id);

    @UpdateProvider(type=WeekBugDetailPOSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") WeekBugDetailPO record, @Param("example") WeekBugDetailPOExample example);

    @UpdateProvider(type=WeekBugDetailPOSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") WeekBugDetailPO record, @Param("example") WeekBugDetailPOExample example);

    @UpdateProvider(type=WeekBugDetailPOSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(WeekBugDetailPO record);

    @Update({
        "update week_bug_detail",
        "set title = #{title,jdbcType=VARCHAR},",
          "bug_level = #{bugLevel,jdbcType=VARCHAR},",
          "reason = #{reason,jdbcType=VARCHAR},",
          "creator = #{creator,jdbcType=VARCHAR},",
          "receiver = #{receiver,jdbcType=VARCHAR},",
          "bug_status = #{bugStatus,jdbcType=VARCHAR},",
          "created_time = #{createdTime,jdbcType=VARCHAR},",
          "time_flag = #{timeFlag,jdbcType=BIGINT},",
          "orgname = #{orgname,jdbcType=VARCHAR},",
          "orgid = #{orgid,jdbcType=VARCHAR},",
          "all_title = #{allTitle,jdbcType=VARCHAR},",
          "bug_link = #{bugLink,jdbcType=VARCHAR},",
          "start_date = #{startDate,jdbcType=VARCHAR},",
          "end_date = #{endDate,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(WeekBugDetailPO record);
}