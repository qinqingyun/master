package com.meituan.food.mapper;

import com.meituan.food.po.ApiDetailPO;
import com.meituan.food.po.ApiDetailPOExample;
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

public interface ApiDetailPOMapper {
    @SelectProvider(type=ApiDetailPOSqlProvider.class, method="countByExample")
    long countByExample(ApiDetailPOExample example);

    @DeleteProvider(type=ApiDetailPOSqlProvider.class, method="deleteByExample")
    int deleteByExample(ApiDetailPOExample example);

    @Delete({
        "delete from api_detail",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into api_detail (id, appkey, ",
        "api_full_name, call_count, ",
        "api_span_name, proportion, ",
        "is_core, created_time, ",
        "updated_at)",
        "values (#{id,jdbcType=INTEGER}, #{appkey,jdbcType=VARCHAR}, ",
        "#{apiFullName,jdbcType=VARCHAR}, #{callCount,jdbcType=BIGINT}, ",
        "#{apiSpanName,jdbcType=VARCHAR}, #{proportion,jdbcType=DECIMAL}, ",
        "#{isCore,jdbcType=INTEGER}, #{createdTime,jdbcType=TIMESTAMP}, ",
        "#{updatedAt,jdbcType=TIMESTAMP})"
    })
    int insert(ApiDetailPO record);

    @InsertProvider(type=ApiDetailPOSqlProvider.class, method="insertSelective")
    int insertSelective(ApiDetailPO record);

    @SelectProvider(type=ApiDetailPOSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="appkey", property="appkey", jdbcType=JdbcType.VARCHAR),
        @Result(column="api_full_name", property="apiFullName", jdbcType=JdbcType.VARCHAR),
        @Result(column="call_count", property="callCount", jdbcType=JdbcType.BIGINT),
        @Result(column="api_span_name", property="apiSpanName", jdbcType=JdbcType.VARCHAR),
        @Result(column="proportion", property="proportion", jdbcType=JdbcType.DECIMAL),
        @Result(column="is_core", property="isCore", jdbcType=JdbcType.INTEGER),
        @Result(column="created_time", property="createdTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="updated_at", property="updatedAt", jdbcType=JdbcType.TIMESTAMP)
    })
    List<ApiDetailPO> selectByExample(ApiDetailPOExample example);

    @Select({
        "select",
        "id, appkey, api_full_name, call_count, api_span_name, proportion, is_core, created_time, ",
        "updated_at",
        "from api_detail",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="appkey", property="appkey", jdbcType=JdbcType.VARCHAR),
        @Result(column="api_full_name", property="apiFullName", jdbcType=JdbcType.VARCHAR),
        @Result(column="call_count", property="callCount", jdbcType=JdbcType.BIGINT),
        @Result(column="api_span_name", property="apiSpanName", jdbcType=JdbcType.VARCHAR),
        @Result(column="proportion", property="proportion", jdbcType=JdbcType.DECIMAL),
        @Result(column="is_core", property="isCore", jdbcType=JdbcType.INTEGER),
        @Result(column="created_time", property="createdTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="updated_at", property="updatedAt", jdbcType=JdbcType.TIMESTAMP)
    })
    ApiDetailPO selectByPrimaryKey(Integer id);

    @UpdateProvider(type=ApiDetailPOSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") ApiDetailPO record, @Param("example") ApiDetailPOExample example);

    @UpdateProvider(type=ApiDetailPOSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") ApiDetailPO record, @Param("example") ApiDetailPOExample example);

    @UpdateProvider(type=ApiDetailPOSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(ApiDetailPO record);

    @Update({
        "update api_detail",
        "set appkey = #{appkey,jdbcType=VARCHAR},",
          "api_full_name = #{apiFullName,jdbcType=VARCHAR},",
          "call_count = #{callCount,jdbcType=BIGINT},",
          "api_span_name = #{apiSpanName,jdbcType=VARCHAR},",
          "proportion = #{proportion,jdbcType=DECIMAL},",
          "is_core = #{isCore,jdbcType=INTEGER},",
          "created_time = #{createdTime,jdbcType=TIMESTAMP},",
          "updated_at = #{updatedAt,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(ApiDetailPO record);
}