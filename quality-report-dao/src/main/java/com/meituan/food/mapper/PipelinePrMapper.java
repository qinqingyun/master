package com.meituan.food.mapper;

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

    @Insert({
        "insert into pipeline_pr_data (id, direction_id, direction_name, hotfixTotal,hotfixDetail, skipTotal,skipDetail,skipReason,it_date)",
        "values (#{id,jdbcType=INTEGER},#{direction_id,jdbcType=INTEGER},#{directionName,jdbcType=VARCHAR},#{hotfix,jdbcType=INTEGER},#{hotfixDetailStr,jdbcType=VARCHAR},#{skip,jdbcType=INTEGER}, #{skipDetailStr,jdbcType=VARCHAR},#{skipReasonStr,jdbcType=VARCHAR},#{createTime,jdbcType=DATE})"
    })
    int insert(PipelinePrPO record);

}