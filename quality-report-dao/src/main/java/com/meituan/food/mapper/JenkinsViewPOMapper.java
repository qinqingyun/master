package com.meituan.food.mapper;

import com.meituan.food.po.JenkinsViewPO;
import com.meituan.food.po.JenkinsViewPOExample;
import java.util.List;

import io.swagger.models.auth.In;
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

public interface JenkinsViewPOMapper {
    @SelectProvider(type=JenkinsViewPOSqlProvider.class, method="countByExample")
    long countByExample(JenkinsViewPOExample example);

    @DeleteProvider(type=JenkinsViewPOSqlProvider.class, method="deleteByExample")
    int deleteByExample(JenkinsViewPOExample example);

    @Delete({
        "delete from jenkins_views",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into jenkins_views (id, view, ",
        "job, url, status)",
        "values (#{id,jdbcType=INTEGER}, #{view,jdbcType=VARCHAR}, ",
        "#{job,jdbcType=VARCHAR}, #{url,jdbcType=VARCHAR}, #{status,jdbcType=INTEGER})"
    })
    int insert(JenkinsViewPO record);

    @InsertProvider(type=JenkinsViewPOSqlProvider.class, method="insertSelective")
    int insertSelective(JenkinsViewPO record);

    @SelectProvider(type=JenkinsViewPOSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="view", property="view", jdbcType=JdbcType.VARCHAR),
        @Result(column="job", property="job", jdbcType=JdbcType.VARCHAR),
        @Result(column="url", property="url", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER)
    })
    List<JenkinsViewPO> selectByExample(JenkinsViewPOExample example);

    @Select({
        "select",
        "id, view, job, url, status",
        "from jenkins_views",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="view", property="view", jdbcType=JdbcType.VARCHAR),
        @Result(column="job", property="job", jdbcType=JdbcType.VARCHAR),
        @Result(column="url", property="url", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER)
    })
    JenkinsViewPO selectByPrimaryKey(Integer id);

    @UpdateProvider(type=JenkinsViewPOSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") JenkinsViewPO record, @Param("example") JenkinsViewPOExample example);

    @UpdateProvider(type=JenkinsViewPOSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") JenkinsViewPO record, @Param("example") JenkinsViewPOExample example);

    @UpdateProvider(type=JenkinsViewPOSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(JenkinsViewPO record);

    @Update({
        "update jenkins_views",
        "set view = #{view,jdbcType=VARCHAR},",
          "job = #{job,jdbcType=VARCHAR},",
          "url = #{url,jdbcType=VARCHAR},",
          "status = #{status,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(JenkinsViewPO record);

    @Select({
        "select",
        "job, status",
        "from jenkins_views",
        "where view = #{view,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="job", property="job", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER)
    })
    List<JenkinsViewPO> selectJobAndStatusByView(String view);

    @Update({
        "update jenkins_views",
        "set status = #{status,jdbcType=INTEGER}",
        "where view = #{view,jdbcType=VARCHAR}",
          "and job = #{job,jdbcType=VARCHAR}"
    })
    int updateStatusByViewAndJob(Integer status, String view, String job);
}