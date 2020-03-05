package com.meituan.food.mapper;

import com.meituan.food.po.ApiCoverageDetailP0Example;
import com.meituan.food.po.PipelineItPO;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;

public interface PipelineItMapper {
    @SelectProvider(type=ApiCoverageDetailP0SqlProvider.class, method="countByExample")
    long countByExample(ApiCoverageDetailP0Example example);

    @DeleteProvider(type=ApiCoverageDetailP0SqlProvider.class, method="deleteByExample")
    int deleteByExample(ApiCoverageDetailP0Example example);

    @Delete({
        "delete from pipeline_it_data",
        "where it_date = #{createTime,jdbcType=DATE}"
    })
    int deleteByDate(LocalDate createTime);

    @Insert({
        "insert into pipeline_it_data (id, department_id, department_name, pipelineTotal,pipelinePass, pass_rate,wholeCasePass,wholeCaseTotal,case_pass_rate,it_date)",
        "values (#{id,jdbcType=INTEGER},#{departmentId,jdbcType=INTEGER},#{departmentName,jdbcType=VARCHAR},#{pipelineTotal,jdbcType=INTEGER},#{pipelinePass,jdbcType=INTEGER},#{passRate,jdbcType=DECIMAL},#{wholeCasePass,jdbcType=INTEGER}, #{wholeCaseTotal,jdbcType=INTEGER},#{casePassRate,jdbcType=DECIMAL},#{createTime,jdbcType=DATE})"
    })
    int insert(PipelineItPO record);

}