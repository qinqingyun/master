package com.meituan.food.mapper;

import com.meituan.food.po.TaskDurationPO;
import com.meituan.food.po.TaskDurationPOExample;
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

public interface TaskDurationPOMapper {
    @SelectProvider(type=TaskDurationPOSqlProvider.class, method="countByExample")
    long countByExample(TaskDurationPOExample example);

    @DeleteProvider(type=TaskDurationPOSqlProvider.class, method="deleteByExample")
    int deleteByExample(TaskDurationPOExample example);

    @Delete({
        "delete from task_duration",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into task_duration (id, misid, ",
        "real_name, dashboard, ",
        "start_date, end_date, ",
        "duration, org_group, ",
        "org_id, first_leader, ",
        "second_leader, created_at, ",
        "isNormal)",
        "values (#{id,jdbcType=INTEGER}, #{misid,jdbcType=VARCHAR}, ",
        "#{realName,jdbcType=VARCHAR}, #{dashboard,jdbcType=VARCHAR}, ",
        "#{startDate,jdbcType=VARCHAR}, #{endDate,jdbcType=VARCHAR}, ",
        "#{duration,jdbcType=DECIMAL}, #{orgGroup,jdbcType=VARCHAR}, ",
        "#{orgId,jdbcType=VARCHAR}, #{firstLeader,jdbcType=VARCHAR}, ",
        "#{secondLeader,jdbcType=VARCHAR}, #{createdAt,jdbcType=TIMESTAMP}, ",
        "#{isnormal,jdbcType=BIT})"
    })
    int insert(TaskDurationPO record);

    @InsertProvider(type=TaskDurationPOSqlProvider.class, method="insertSelective")
    int insertSelective(TaskDurationPO record);

    @SelectProvider(type=TaskDurationPOSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="misid", property="misid", jdbcType=JdbcType.VARCHAR),
        @Result(column="real_name", property="realName", jdbcType=JdbcType.VARCHAR),
        @Result(column="dashboard", property="dashboard", jdbcType=JdbcType.VARCHAR),
        @Result(column="start_date", property="startDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="end_date", property="endDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="duration", property="duration", jdbcType=JdbcType.DECIMAL),
        @Result(column="org_group", property="orgGroup", jdbcType=JdbcType.VARCHAR),
        @Result(column="org_id", property="orgId", jdbcType=JdbcType.VARCHAR),
        @Result(column="first_leader", property="firstLeader", jdbcType=JdbcType.VARCHAR),
        @Result(column="second_leader", property="secondLeader", jdbcType=JdbcType.VARCHAR),
        @Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="isNormal", property="isnormal", jdbcType=JdbcType.BIT)
    })
    List<TaskDurationPO> selectByExample(TaskDurationPOExample example);

    @Select({
        "select",
        "id, misid, real_name, dashboard, start_date, end_date, duration, org_group, ",
        "org_id, first_leader, second_leader, created_at, isNormal",
        "from task_duration",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="misid", property="misid", jdbcType=JdbcType.VARCHAR),
        @Result(column="real_name", property="realName", jdbcType=JdbcType.VARCHAR),
        @Result(column="dashboard", property="dashboard", jdbcType=JdbcType.VARCHAR),
        @Result(column="start_date", property="startDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="end_date", property="endDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="duration", property="duration", jdbcType=JdbcType.DECIMAL),
        @Result(column="org_group", property="orgGroup", jdbcType=JdbcType.VARCHAR),
        @Result(column="org_id", property="orgId", jdbcType=JdbcType.VARCHAR),
        @Result(column="first_leader", property="firstLeader", jdbcType=JdbcType.VARCHAR),
        @Result(column="second_leader", property="secondLeader", jdbcType=JdbcType.VARCHAR),
        @Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="isNormal", property="isnormal", jdbcType=JdbcType.BIT)
    })
    TaskDurationPO selectByPrimaryKey(Integer id);

    @UpdateProvider(type=TaskDurationPOSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") TaskDurationPO record, @Param("example") TaskDurationPOExample example);

    @UpdateProvider(type=TaskDurationPOSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") TaskDurationPO record, @Param("example") TaskDurationPOExample example);

    @UpdateProvider(type=TaskDurationPOSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TaskDurationPO record);

    @Update({
        "update task_duration",
        "set misid = #{misid,jdbcType=VARCHAR},",
          "real_name = #{realName,jdbcType=VARCHAR},",
          "dashboard = #{dashboard,jdbcType=VARCHAR},",
          "start_date = #{startDate,jdbcType=VARCHAR},",
          "end_date = #{endDate,jdbcType=VARCHAR},",
          "duration = #{duration,jdbcType=DECIMAL},",
          "org_group = #{orgGroup,jdbcType=VARCHAR},",
          "org_id = #{orgId,jdbcType=VARCHAR},",
          "first_leader = #{firstLeader,jdbcType=VARCHAR},",
          "second_leader = #{secondLeader,jdbcType=VARCHAR},",
          "created_at = #{createdAt,jdbcType=TIMESTAMP},",
          "isNormal = #{isnormal,jdbcType=BIT}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(TaskDurationPO record);
}