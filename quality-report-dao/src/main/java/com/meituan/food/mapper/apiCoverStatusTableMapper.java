package com.meituan.food.mapper;

import com.meituan.food.po.apiCoverStatusTable;
import com.meituan.food.po.apiCoverStatusTableExample;
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

public interface apiCoverStatusTableMapper {
    @SelectProvider(type=apiCoverStatusTableSqlProvider.class, method="countByExample")
    long countByExample(apiCoverStatusTableExample example);

    @DeleteProvider(type=apiCoverStatusTableSqlProvider.class, method="deleteByExample")
    int deleteByExample(apiCoverStatusTableExample example);

    @Delete({
        "delete from api_cover_status_table",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into api_cover_status_table (id, appkey, ",
        "api_name)",
        "values (#{id,jdbcType=INTEGER}, #{appkey,jdbcType=VARCHAR}, ",
        "#{apiName,jdbcType=VARCHAR})"
    })
    int insert(apiCoverStatusTable record);

    @InsertProvider(type=apiCoverStatusTableSqlProvider.class, method="insertSelective")
    int insertSelective(apiCoverStatusTable record);

    @SelectProvider(type=apiCoverStatusTableSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="appkey", property="appkey", jdbcType=JdbcType.VARCHAR),
        @Result(column="api_name", property="apiName", jdbcType=JdbcType.VARCHAR)
    })
    List<apiCoverStatusTable> selectByExample(apiCoverStatusTableExample example);

    @Select({
        "select",
        "id, appkey, api_name",
        "from api_cover_status_table",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="appkey", property="appkey", jdbcType=JdbcType.VARCHAR),
        @Result(column="api_name", property="apiName", jdbcType=JdbcType.VARCHAR)
    })
    apiCoverStatusTable selectByPrimaryKey(Integer id);

    @UpdateProvider(type=apiCoverStatusTableSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") apiCoverStatusTable record, @Param("example") apiCoverStatusTableExample example);

    @UpdateProvider(type=apiCoverStatusTableSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") apiCoverStatusTable record, @Param("example") apiCoverStatusTableExample example);

    @UpdateProvider(type=apiCoverStatusTableSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(apiCoverStatusTable record);

    @Update({
        "update api_cover_status_table",
        "set appkey = #{appkey,jdbcType=VARCHAR},",
          "api_name = #{apiName,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(apiCoverStatusTable record);
}