package com.meituan.food.mapper;

import com.meituan.food.po.ApiCoverageDetailP0;
import com.meituan.food.po.ApiCoverageDetailP0Example;
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

public interface ApiCoverageDetailP0Mapper {
    @SelectProvider(type=ApiCoverageDetailP0SqlProvider.class, method="countByExample")
    long countByExample(ApiCoverageDetailP0Example example);

    @DeleteProvider(type=ApiCoverageDetailP0SqlProvider.class, method="deleteByExample")
    int deleteByExample(ApiCoverageDetailP0Example example);

    @Delete({
        "delete from api_coverage_detail",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into api_coverage_detail (id, appkey, ",
        "api_name, coverage_date, ",
        "created_time, is_cover)",
        "values (#{id,jdbcType=INTEGER}, #{appkey,jdbcType=VARCHAR}, ",
        "#{apiName,jdbcType=VARCHAR}, #{coverageDate,jdbcType=VARCHAR}, ",
        "#{createdTime,jdbcType=TIMESTAMP}, #{isCover,jdbcType=BIT})"
    })
    int insert(ApiCoverageDetailP0 record);

    @InsertProvider(type=ApiCoverageDetailP0SqlProvider.class, method="insertSelective")
    int insertSelective(ApiCoverageDetailP0 record);

    @SelectProvider(type=ApiCoverageDetailP0SqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="appkey", property="appkey", jdbcType=JdbcType.VARCHAR),
        @Result(column="api_name", property="apiName", jdbcType=JdbcType.VARCHAR),
        @Result(column="coverage_date", property="coverageDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="created_time", property="createdTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="is_cover", property="isCover", jdbcType=JdbcType.BIT)
    })
    List<ApiCoverageDetailP0> selectByExample(ApiCoverageDetailP0Example example);

    @Select({
        "select",
        "id, appkey, api_name, coverage_date, created_time, is_cover",
        "from api_coverage_detail",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="appkey", property="appkey", jdbcType=JdbcType.VARCHAR),
        @Result(column="api_name", property="apiName", jdbcType=JdbcType.VARCHAR),
        @Result(column="coverage_date", property="coverageDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="created_time", property="createdTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="is_cover", property="isCover", jdbcType=JdbcType.BIT)
    })
    ApiCoverageDetailP0 selectByPrimaryKey(Integer id);

    @Select({
            "select",
            "id, appkey, api_name, coverage_date, created_time, is_cover",
            "from api_coverage_detail",
            "where appkey = #{appkey,jdbcType=VARCHAR} ",
            "and coverage_date=#{coverageDate,jdbcType=VARCHAR} ",
            "and api_name=#{apiName,jdbcType=VARCHAR} "
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="appkey", property="appkey", jdbcType=JdbcType.VARCHAR),
            @Result(column="api_name", property="apiName", jdbcType=JdbcType.VARCHAR),
            @Result(column="coverage_date", property="coverageDate", jdbcType=JdbcType.VARCHAR),
            @Result(column="created_time", property="createdTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="is_cover", property="isCover", jdbcType=JdbcType.BIT)
    })
    ApiCoverageDetailP0 selectByDateAndApi(@Param("coverageDate") String coverageDate,@Param("appkey") String appkey,@Param("apiName") String apiName);

    @UpdateProvider(type=ApiCoverageDetailP0SqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") ApiCoverageDetailP0 record, @Param("example") ApiCoverageDetailP0Example example);

    @UpdateProvider(type=ApiCoverageDetailP0SqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") ApiCoverageDetailP0 record, @Param("example") ApiCoverageDetailP0Example example);

    @UpdateProvider(type=ApiCoverageDetailP0SqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(ApiCoverageDetailP0 record);

    @Update({
        "update api_coverage_detail",
        "set appkey = #{appkey,jdbcType=VARCHAR},",
          "api_name = #{apiName,jdbcType=VARCHAR},",
          "coverage_date = #{coverageDate,jdbcType=VARCHAR},",
          "created_time = #{createdTime,jdbcType=TIMESTAMP},",
          "is_cover = #{isCover,jdbcType=BIT}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(ApiCoverageDetailP0 record);
}