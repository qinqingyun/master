package com.meituan.food.mapper;

import com.meituan.food.po.ApiDetailPO;
import com.meituan.food.po.JobAdminP0;
import com.meituan.food.po.JobAdminP0Example;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

public interface JobAdminP0Mapper {
    @SelectProvider(type=JobAdminP0SqlProvider.class, method="countByExample")
    long countByExample(JobAdminP0Example example);

    @DeleteProvider(type=JobAdminP0SqlProvider.class, method="deleteByExample")
    int deleteByExample(JobAdminP0Example example);

    @Insert({
        "insert into job_admin (id, job_name, ",
        "admin_name, department_id, ",
        "department_name, created_time, ",
        "updated_time)",
        "values (#{id,jdbcType=INTEGER}, #{jobName,jdbcType=VARCHAR}, ",
        "#{adminName,jdbcType=VARCHAR}, #{departmentId,jdbcType=INTEGER}, ",
        "#{departmentName,jdbcType=VARCHAR}, #{createdTime,jdbcType=TIMESTAMP}, ",
        "#{updatedTime,jdbcType=TIMESTAMP})"
    })
    int insert(JobAdminP0 record);

    @InsertProvider(type=JobAdminP0SqlProvider.class, method="insertSelective")
    int insertSelective(JobAdminP0 record);

    @SelectProvider(type=JobAdminP0SqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER),
        @Result(column="job_name", property="jobName", jdbcType=JdbcType.VARCHAR),
        @Result(column="admin_name", property="adminName", jdbcType=JdbcType.VARCHAR),
        @Result(column="department_id", property="departmentId", jdbcType=JdbcType.INTEGER),
        @Result(column="department_name", property="departmentName", jdbcType=JdbcType.VARCHAR),
        @Result(column="created_time", property="createdTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="updated_time", property="updatedTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<JobAdminP0> selectByExample(JobAdminP0Example example);

    @UpdateProvider(type=JobAdminP0SqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") JobAdminP0 record, @Param("example") JobAdminP0Example example);

    @UpdateProvider(type=JobAdminP0SqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") JobAdminP0 record, @Param("example") JobAdminP0Example example);

    @Update({
            "update job_admin",
            "set admin_name = #{adminName,jdbcType=VARCHAR},",
            "updated_time = #{updatedAt,jdbcType=TIMESTAMP}",
            "where job_name = #{jobName,jdbcType=VARCHAR}",
    })
    int updateJobAdmin(@Param("jobName") String jobName, @Param("adminName") String adminName, @Param("updatedAt") Date updatedAt);

    @Select(
            "select * from job_admin order by department_id")
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER),
            @Result(column="job_name", property="jobName", jdbcType=JdbcType.VARCHAR),
            @Result(column="admin_name", property="adminName", jdbcType=JdbcType.VARCHAR),
            @Result(column="department_id", property="departmentId", jdbcType=JdbcType.INTEGER),
            @Result(column="department_name", property="departmentName", jdbcType=JdbcType.VARCHAR),
            @Result(column="created_time", property="createdTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="updated_time", property="updatedTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<JobAdminP0> selectAllJobInfo();


}