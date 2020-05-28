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


   @Select({
            "select",
            "url,view",
            "from ( select url,view,concat('meishi_b/',job) as job from( select url,job, view from jenkins_views  where status=1 and view in ('B端','C端test环境覆盖率计算','M端-CRM代理商','M端-MOMA','供应链自动化','结算自动化','商家平台-北京Test环境接口自动化','商家平台-上海Test环境接口自动化','客户平台-Test环境接口自动化','TDC门店信息') group by job  order by view )a) b  left outer join ( select dc_job_name,depart_name from dc_case_issue  where build_date>=#{build_date,jdbcType=VARCHAR} group by dc_job_name order by view_name) c on b.job COLLATE utf8mb4_unicode_ci = c.dc_job_name COLLATE utf8mb4_unicode_ci" ,
            "where c.dc_job_name is NULL"
    })
    @Results({
            @Result(column="url", property="url", jdbcType=JdbcType.VARCHAR),
            @Result(column="view", property="view", jdbcType=JdbcType.VARCHAR)
    })
    List<JenkinsViewPO> selectUnRunJobAndVieByBuildDate(String buil_date);
}