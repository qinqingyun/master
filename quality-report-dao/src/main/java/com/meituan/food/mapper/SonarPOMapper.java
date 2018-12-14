package com.meituan.food.mapper;

import com.meituan.food.po.SonarPO;
import com.meituan.food.po.SonarPOExample;
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

public interface SonarPOMapper {
    @SelectProvider(type=SonarPOSqlProvider.class, method="countByExample")
    long countByExample(SonarPOExample example);

    @DeleteProvider(type=SonarPOSqlProvider.class, method="deleteByExample")
    int deleteByExample(SonarPOExample example);

    @Delete({
        "delete from sonar",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into sonar (id, project, ",
        "includeSubProject, blocker, ",
        "critical, leader, ",
        "created_at, updated_at, ",
        "link, sonar_date)",
        "values (#{id,jdbcType=INTEGER}, #{project,jdbcType=VARCHAR}, ",
        "#{includesubproject,jdbcType=VARCHAR}, #{blocker,jdbcType=INTEGER}, ",
        "#{critical,jdbcType=INTEGER}, #{leader,jdbcType=VARCHAR}, ",
        "#{createdAt,jdbcType=TIMESTAMP}, #{updatedAt,jdbcType=TIMESTAMP}, ",
        "#{link,jdbcType=VARCHAR}, #{sonarDate,jdbcType=VARCHAR})"
    })
    int insert(SonarPO record);

    @InsertProvider(type=SonarPOSqlProvider.class, method="insertSelective")
    int insertSelective(SonarPO record);

    @SelectProvider(type=SonarPOSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="project", property="project", jdbcType=JdbcType.VARCHAR),
        @Result(column="includeSubProject", property="includesubproject", jdbcType=JdbcType.VARCHAR),
        @Result(column="blocker", property="blocker", jdbcType=JdbcType.INTEGER),
        @Result(column="critical", property="critical", jdbcType=JdbcType.INTEGER),
        @Result(column="leader", property="leader", jdbcType=JdbcType.VARCHAR),
        @Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="updated_at", property="updatedAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="link", property="link", jdbcType=JdbcType.VARCHAR),
        @Result(column="sonar_date", property="sonarDate", jdbcType=JdbcType.VARCHAR)
    })
    List<SonarPO> selectByExample(SonarPOExample example);

    @Select({
        "select",
        "id, project, includeSubProject, blocker, critical, leader, created_at, updated_at, ",
        "link, sonar_date",
        "from sonar",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="project", property="project", jdbcType=JdbcType.VARCHAR),
        @Result(column="includeSubProject", property="includesubproject", jdbcType=JdbcType.VARCHAR),
        @Result(column="blocker", property="blocker", jdbcType=JdbcType.INTEGER),
        @Result(column="critical", property="critical", jdbcType=JdbcType.INTEGER),
        @Result(column="leader", property="leader", jdbcType=JdbcType.VARCHAR),
        @Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="updated_at", property="updatedAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="link", property="link", jdbcType=JdbcType.VARCHAR),
        @Result(column="sonar_date", property="sonarDate", jdbcType=JdbcType.VARCHAR)
    })
    SonarPO selectByPrimaryKey(Integer id);

    @UpdateProvider(type=SonarPOSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SonarPO record, @Param("example") SonarPOExample example);

    @UpdateProvider(type=SonarPOSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SonarPO record, @Param("example") SonarPOExample example);

    @UpdateProvider(type=SonarPOSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SonarPO record);

    @Update({
        "update sonar",
        "set project = #{project,jdbcType=VARCHAR},",
          "includeSubProject = #{includesubproject,jdbcType=VARCHAR},",
          "blocker = #{blocker,jdbcType=INTEGER},",
          "critical = #{critical,jdbcType=INTEGER},",
          "leader = #{leader,jdbcType=VARCHAR},",
          "created_at = #{createdAt,jdbcType=TIMESTAMP},",
          "updated_at = #{updatedAt,jdbcType=TIMESTAMP},",
          "link = #{link,jdbcType=VARCHAR},",
          "sonar_date = #{sonarDate,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SonarPO record);
}