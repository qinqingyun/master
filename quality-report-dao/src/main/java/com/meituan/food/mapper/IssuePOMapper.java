package com.meituan.food.mapper;

import com.meituan.food.po.IssuePO;
import com.meituan.food.po.IssuePOExample;
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

public interface IssuePOMapper {
    @SelectProvider(type=IssuePOSqlProvider.class, method="countByExample")
    long countByExample(IssuePOExample example);

    @DeleteProvider(type=IssuePOSqlProvider.class, method="deleteByExample")
    int deleteByExample(IssuePOExample example);

    @Delete({
        "delete from issue",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into issue (id, brief, ",
        "level, department, ",
        "type, wiki, created_at, ",
        "updated_at, occur_month)",
        "values (#{id,jdbcType=INTEGER}, #{brief,jdbcType=VARCHAR}, ",
        "#{level,jdbcType=VARCHAR}, #{department,jdbcType=VARCHAR}, ",
        "#{type,jdbcType=VARCHAR}, #{wiki,jdbcType=VARCHAR}, #{createdAt,jdbcType=TIMESTAMP}, ",
        "#{updatedAt,jdbcType=TIMESTAMP}, #{occurMonth,jdbcType=VARCHAR})"
    })
    int insert(IssuePO record);

    @InsertProvider(type=IssuePOSqlProvider.class, method="insertSelective")
    int insertSelective(IssuePO record);

    @SelectProvider(type=IssuePOSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="brief", property="brief", jdbcType=JdbcType.VARCHAR),
        @Result(column="level", property="level", jdbcType=JdbcType.VARCHAR),
        @Result(column="department", property="department", jdbcType=JdbcType.VARCHAR),
        @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
        @Result(column="wiki", property="wiki", jdbcType=JdbcType.VARCHAR),
        @Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="updated_at", property="updatedAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="occur_month", property="occurMonth", jdbcType=JdbcType.VARCHAR)
    })
    List<IssuePO> selectByExample(IssuePOExample example);

    @Select({
        "select",
        "id, brief, level, department, type, wiki, created_at, updated_at, occur_month",
        "from issue",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="brief", property="brief", jdbcType=JdbcType.VARCHAR),
        @Result(column="level", property="level", jdbcType=JdbcType.VARCHAR),
        @Result(column="department", property="department", jdbcType=JdbcType.VARCHAR),
        @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
        @Result(column="wiki", property="wiki", jdbcType=JdbcType.VARCHAR),
        @Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="updated_at", property="updatedAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="occur_month", property="occurMonth", jdbcType=JdbcType.VARCHAR)
    })
    IssuePO selectByPrimaryKey(Integer id);

    @UpdateProvider(type=IssuePOSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") IssuePO record, @Param("example") IssuePOExample example);

    @UpdateProvider(type=IssuePOSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") IssuePO record, @Param("example") IssuePOExample example);

    @UpdateProvider(type=IssuePOSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(IssuePO record);

    @Update({
        "update issue",
        "set brief = #{brief,jdbcType=VARCHAR},",
          "level = #{level,jdbcType=VARCHAR},",
          "department = #{department,jdbcType=VARCHAR},",
          "type = #{type,jdbcType=VARCHAR},",
          "wiki = #{wiki,jdbcType=VARCHAR},",
          "created_at = #{createdAt,jdbcType=TIMESTAMP},",
          "updated_at = #{updatedAt,jdbcType=TIMESTAMP},",
          "occur_month = #{occurMonth,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(IssuePO record);

    @Insert({
            "<script>",
            "insert into issue (id, brief, ",
            "level, department, ",
            "type, wiki, created_at, ",
            "updated_at,occur_month)",
            "values ",
            "<foreach collection='list' item='item' index='index' separator=','>",
            "(#{item.id,jdbcType=INTEGER},#{item.brief,jdbcType=VARCHAR},",
            "#{item.level,jdbcType=VARCHAR},#{item.department,jdbcType=VARCHAR},",
            "#{item.type,jdbcType=VARCHAR},#{item.wiki,jdbcType=VARCHAR},#{item.createdAt,jdbcType=TIMESTAMP},",
            "#{item.updatedAt,jdbcType=TIMESTAMP},#{item.occurMonth,jdbcType=VARCHAR})",
            "</foreach>",
            "</script>"
    })
    int batchInsert(List<IssuePO> records);

    @Select({
            "select",
            "id, brief, level, department, type, wiki, created_at, updated_at, occur_month",
            "from issue",
            "where occur_month = #{occur_month,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="brief", property="brief", jdbcType=JdbcType.VARCHAR),
            @Result(column="level", property="level", jdbcType=JdbcType.VARCHAR),
            @Result(column="department", property="department", jdbcType=JdbcType.VARCHAR),
            @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
            @Result(column="wiki", property="wiki", jdbcType=JdbcType.VARCHAR),
            @Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="updated_at", property="updatedAt", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="occur_month", property="occurMonth", jdbcType=JdbcType.VARCHAR)
    })
    List<IssuePO> selectByMonth(String occur_month);
}