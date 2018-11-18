package com.meituan.food.mapper;

import com.meituan.food.po.WeekIssuePO;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.Date;
import java.util.List;

public interface WeekIssuePOMapper {
    @Delete({
            "delete from week_issue",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
            "insert into week_issue (id, brief, ",
            "level, department, ",
            "type, wiki, created_at, ",
            "updated_at)",
            "values (#{id,jdbcType=INTEGER}, #{brief,jdbcType=VARCHAR}, ",
            "#{level,jdbcType=VARCHAR}, #{department,jdbcType=VARCHAR}, ",
            "#{type,jdbcType=VARCHAR}, #{wiki,jdbcType=VARCHAR}, #{createdAt,jdbcType=TIMESTAMP}, ",
            "#{updatedAt,jdbcType=TIMESTAMP})"
    })
    int insert(WeekIssuePO record);

    @Insert({
            "<script>",
            "insert into week_issue (id, brief, ",
            "level, department, ",
            "type, wiki, created_at, ",
            "updated_at)",
            "values ",
            "<foreach collection='list' item='item' index='index' separator=','>",
            "(#{item.id,jdbcType=INTEGER},#{item.brief,jdbcType=VARCHAR},",
            "#{item.level,jdbcType=VARCHAR},#{item.department,jdbcType=VARCHAR},",
            "#{item.type,jdbcType=VARCHAR},#{item.wiki,jdbcType=VARCHAR},#{item.createdAt,jdbcType=TIMESTAMP},",
            "#{item.updatedAt,jdbcType=TIMESTAMP})",
            "</foreach>",
            "</script>"
    })
    int batchInsert(List<WeekIssuePO> records);

    @InsertProvider(type = WeekIssuePOSqlProvider.class, method = "insertSelective")
    int insertSelective(WeekIssuePO record);

    @Select({
            "select",
            "id, brief, level, department, type, wiki, created_at, updated_at",
            "from week_issue",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "brief", property = "brief", jdbcType = JdbcType.VARCHAR),
            @Result(column = "level", property = "level", jdbcType = JdbcType.VARCHAR),
            @Result(column = "department", property = "department", jdbcType = JdbcType.VARCHAR),
            @Result(column = "type", property = "type", jdbcType = JdbcType.VARCHAR),
            @Result(column = "wiki", property = "wiki", jdbcType = JdbcType.VARCHAR),
            @Result(column = "created_at", property = "createdAt", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "updated_at", property = "updatedAt", jdbcType = JdbcType.TIMESTAMP)
    })
    WeekIssuePO selectByPrimaryKey(Integer id);

    @UpdateProvider(type = WeekIssuePOSqlProvider.class, method = "updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(WeekIssuePO record);

    @Update({
            "update week_issue",
            "set brief = #{brief,jdbcType=VARCHAR},",
            "level = #{level,jdbcType=VARCHAR},",
            "department = #{department,jdbcType=VARCHAR},",
            "type = #{type,jdbcType=VARCHAR},",
            "wiki = #{wiki,jdbcType=VARCHAR},",
            "created_at = #{createdAt,jdbcType=TIMESTAMP},",
            "updated_at = #{updatedAt,jdbcType=TIMESTAMP}",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(WeekIssuePO record);



    @Select("select * from crash_rate where department=#{department} " +
            "and created_at>=#{start_date} " +
            "and created_at<=#{end_date} " )
    List<WeekIssuePO> getIssueListByDepartmentAndDate(@Param("department") String department,
                                              @Param("start_date") Date start_date,
                                              @Param("end_date") Date end_date);
}