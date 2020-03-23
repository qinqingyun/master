package com.meituan.food.mapper;

import com.meituan.food.po.PipelinePrAutoPO;
import com.meituan.food.po.PipelinePrPO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;

import java.time.LocalDate;

public interface PipelinePrMapper {

    @Delete({
        "delete from pipeline_pr_data",
        "where it_date = #{createTime,jdbcType=DATE}"
    })
    int deleteByDate(LocalDate createTime);

    @Delete({
            "delete from pipeline_pr_repo_data",
            "where auto_date = #{createTime,jdbcType=DATE} and department_id in (260,262,296)"
    })
    int deleteRepoByDate(LocalDate createTime);

    @Insert({
        "insert into pipeline_pr_data (id, direction_id, direction_name, hotfixTotal,hotfixDetail, skipTotal,skipDetail,skipReason,it_date)",
        "values (#{id,jdbcType=INTEGER},#{direction_id,jdbcType=INTEGER},#{directionName,jdbcType=VARCHAR},#{hotfix,jdbcType=INTEGER},#{hotfixDetailStr,jdbcType=VARCHAR},#{skip,jdbcType=INTEGER}, #{skipDetailStr,jdbcType=VARCHAR},#{skipReasonStr,jdbcType=VARCHAR},#{createTime,jdbcType=DATE})"
    })
    int insert(PipelinePrPO record);

    @Insert({
            "insert into pipeline_pr_repo_data (id,department_id, department_name, repo,isAutoOn, totalCase,passes,coverage,auto_date)",
            "values (#{id,jdbcType=INTEGER},#{department_id,jdbcType=INTEGER},#{directionName,jdbcType=VARCHAR},#{repo,jdbcType=VARCHAR},#{isAutoOn,jdbcType=INTEGER}, #{totalCase,jdbcType=INTEGER},#{passes,jdbcType=DECIMAL},#{coverage,jdbcType=DECIMAL},#{auto_date,jdbcType=DATE})"
    })
    int insertRepo(PipelinePrAutoPO record);

}