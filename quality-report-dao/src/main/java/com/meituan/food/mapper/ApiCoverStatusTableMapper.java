package com.meituan.food.mapper;

import com.meituan.food.po.ApiCoverStatusTable;
import com.meituan.food.po.ApiCoverStatusTableExample;
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

import javax.annotation.Resource;

public interface ApiCoverStatusTableMapper {
    @SelectProvider(type=ApiCoverStatusTableSqlProvider.class, method="countByExample")
    long countByExample(ApiCoverStatusTableExample example);

    @DeleteProvider(type=ApiCoverStatusTableSqlProvider.class, method="deleteByExample")
    int deleteByExample(ApiCoverStatusTableExample example);

    @Delete({
        "delete from api_cover_status_table",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into api_cover_status_table (appkey, ",
        "api_name)",
        "values (#{appkey,jdbcType=VARCHAR}, ",
        "#{apiName,jdbcType=VARCHAR})"
    })
    int insert(ApiCoverStatusTable record);

    @InsertProvider(type=ApiCoverStatusTableSqlProvider.class, method="insertSelective")
    int insertSelective(ApiCoverStatusTable record);

    @SelectProvider(type=ApiCoverStatusTableSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="appkey", property="appkey", jdbcType=JdbcType.VARCHAR),
        @Result(column="api_name", property="apiName", jdbcType=JdbcType.VARCHAR)
    })
    List<ApiCoverStatusTable> selectByExample(ApiCoverStatusTableExample example);

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
    ApiCoverStatusTable selectByPrimaryKey(Integer id);

    @UpdateProvider(type=ApiCoverStatusTableSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") ApiCoverStatusTable record, @Param("example") ApiCoverStatusTableExample example);

    @UpdateProvider(type=ApiCoverStatusTableSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") ApiCoverStatusTable record, @Param("example") ApiCoverStatusTableExample example);

    @UpdateProvider(type=ApiCoverStatusTableSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(ApiCoverStatusTable record);

    @Delete({
            "delete from api_cover_status_table",
            "where api_name = #{apiName,jdbcType=VARCHAR} AND appkey = #{appkey,jdbcType=VARCHAR}"
    })
    int deleteByApiName(@Param("appkey") String appkey,@Param("apiName") String apiName);

    @Select({
            "select count(*)",
            "from api_cover_status_table",
            "where api_name = #{apiName,jdbcType=VARCHAR} AND appkey = #{appkey,jdbcType=VARCHAR}"
    })
    int isNull(ApiCoverStatusTable record);

    @Select({
            "select api_name",
            "from api_cover_status_table",
            "where appkey = #{appkey,jdbcType=VARCHAR}"
    })
    List<String> getCoverdApiByAppkey(@Param("appkey") String appkey);
}