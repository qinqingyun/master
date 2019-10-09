package com.meituan.food.mapper;

import com.meituan.food.po.JobAdminP0;
import com.meituan.food.po.JobAdminP0Example;

import java.util.Date;
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

public interface JobAdminP0Mapper {
    @SelectProvider(type=JobAdminP0SqlProvider.class, method="countByExample")
    long countByExample(JobAdminP0Example example);

    @DeleteProvider(type=JobAdminP0SqlProvider.class, method="deleteByExample")
    int deleteByExample(JobAdminP0Example example);

    @Delete({
        "delete from job_admin",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into job_admin (id, job_name, ",
        "admin_mis, created_time, ",
        "ci_url, updated_time)",
        "values (#{id,jdbcType=INTEGER}, #{jobName,jdbcType=VARCHAR}, ",
        "#{adminMis,jdbcType=VARCHAR}, #{createdTime,jdbcType=TIMESTAMP}, ",
        "#{ciUrl,jdbcType=VARCHAR}, #{updatedTime,jdbcType=TIMESTAMP})"
    })
    int insert(JobAdminP0 record);

    @InsertProvider(type=JobAdminP0SqlProvider.class, method="insertSelective")
    int insertSelective(JobAdminP0 record);

    @SelectProvider(type=JobAdminP0SqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="job_name", property="jobName", jdbcType=JdbcType.VARCHAR),
        @Result(column="admin_mis", property="adminMis", jdbcType=JdbcType.VARCHAR),
        @Result(column="created_time", property="createdTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="ci_url", property="ciUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="updated_time", property="updatedTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<JobAdminP0> selectByExample(JobAdminP0Example example);

    @Select({
        "select",
        "id, job_name, admin_mis, created_time, ci_url, updated_time",
        "from job_admin",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="job_name", property="jobName", jdbcType=JdbcType.VARCHAR),
        @Result(column="admin_mis", property="adminMis", jdbcType=JdbcType.VARCHAR),
        @Result(column="created_time", property="createdTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="ci_url", property="ciUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="updated_time", property="updatedTime", jdbcType=JdbcType.TIMESTAMP)
    })
    JobAdminP0 selectByPrimaryKey(Integer id);

    @UpdateProvider(type=JobAdminP0SqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") JobAdminP0 record, @Param("example") JobAdminP0Example example);

    @UpdateProvider(type=JobAdminP0SqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") JobAdminP0 record, @Param("example") JobAdminP0Example example);

    @UpdateProvider(type=JobAdminP0SqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(JobAdminP0 record);

    @Update({
        "update job_admin",
        "set job_name = #{jobName,jdbcType=VARCHAR},",
          "admin_mis = #{adminMis,jdbcType=VARCHAR},",
          "created_time = #{createdTime,jdbcType=TIMESTAMP},",
          "ci_url = #{ciUrl,jdbcType=VARCHAR},",
          "updated_time = #{updatedTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(JobAdminP0 record);

    @Update({
            "update job_admin",
            "set admin_mis = #{adminMis,jdbcType=VARCHAR},",
            "updated_time = #{updatedAt,jdbcType=TIMESTAMP}",
            "where job_name = #{jobName,jdbcType=VARCHAR}",
    })
    int updateJobAdmin(@Param("jobName") String jobName, @Param("adminMis") String adminMis, @Param("updatedAt") Date updatedAt);

    @Update({
            "update job_admin",
            "set ci_url = #{ciUrl,jdbcType=VARCHAR},",
            "updated_time = #{updatedAt,jdbcType=TIMESTAMP}",
            "where job_name = #{jobName,jdbcType=VARCHAR}",
    })
    int updateCiUrl(@Param("jobName") String jobName, @Param("ciUrl") String ciUrl, @Param("updatedAt") Date updatedAt);

    @Select(
            "select * from job_admin order by admin_mis")
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="job_name", property="jobName", jdbcType=JdbcType.VARCHAR),
            @Result(column="admin_mis", property="adminMis", jdbcType=JdbcType.VARCHAR),
            @Result(column="created_time", property="createdTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="ci_url", property="ciUrl", jdbcType=JdbcType.VARCHAR),
            @Result(column="updated_time", property="updatedTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<JobAdminP0> selectAllJobInfo();

    @Update({
            "update job_admin",
            "set job_name = #{newJobName,jdbcType=VARCHAR},",
            "updated_time = #{updatedAt,jdbcType=TIMESTAMP}",
            "where job_name = #{oldJobName,jdbcType=VARCHAR}",
    })
    int updateJobName(@Param("oldJobName") String oldJobName, @Param("newJobName") String newJobName, @Param("updatedAt") Date updatedAt);
}