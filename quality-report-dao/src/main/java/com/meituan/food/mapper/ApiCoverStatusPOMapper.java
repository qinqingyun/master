package com.meituan.food.mapper;

import com.meituan.food.po.ApiCoverStatusPO;
import com.meituan.food.po.ApiCoverStatusPOExample;
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

public interface ApiCoverStatusPOMapper {
    @SelectProvider(type=ApiCoverStatusPOSqlProvider.class, method="countByExample")
    long countByExample(ApiCoverStatusPOExample example);

    @DeleteProvider(type=ApiCoverStatusPOSqlProvider.class, method="deleteByExample")
    int deleteByExample(ApiCoverStatusPOExample example);

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
    int insert(ApiCoverStatusPO record);

    @InsertProvider(type=ApiCoverStatusPOSqlProvider.class, method="insertSelective")
    int insertSelective(ApiCoverStatusPO record);

    @SelectProvider(type=ApiCoverStatusPOSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="appkey", property="appkey", jdbcType=JdbcType.VARCHAR),
        @Result(column="api_name", property="apiName", jdbcType=JdbcType.VARCHAR)
    })
    List<ApiCoverStatusPO> selectByExample(ApiCoverStatusPOExample example);

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
    ApiCoverStatusPO selectByPrimaryKey(Integer id);

    @Select({
            "select",
            "id, appkey, api_name",
            "from api_cover_status_table",
            "where appkey = #{appkey,jdbcType=VARCHAR} ",
            "and api_name=#{api,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="appkey", property="appkey", jdbcType=JdbcType.VARCHAR),
            @Result(column="api_name", property="apiName", jdbcType=JdbcType.VARCHAR)
    })
    ApiCoverStatusPO selectByAppkeyAndApi(@Param("appkey") String  appkey,@Param("api") String api);

    @Select({
            "select",
            "api_name",
            "from api_cover_status_table",
            "where appkey = #{appkey,jdbcType=VARCHAR}"
    })
    List<String> selectByAppkey(@Param("appkey") String  appkey);


    @UpdateProvider(type=ApiCoverStatusPOSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") ApiCoverStatusPO record, @Param("example") ApiCoverStatusPOExample example);

    @UpdateProvider(type=ApiCoverStatusPOSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") ApiCoverStatusPO record, @Param("example") ApiCoverStatusPOExample example);

    @UpdateProvider(type=ApiCoverStatusPOSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(ApiCoverStatusPO record);

    @Update({
        "update api_cover_status_table",
        "set appkey = #{appkey,jdbcType=VARCHAR},",
          "api_name = #{apiName,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(ApiCoverStatusPO record);
}