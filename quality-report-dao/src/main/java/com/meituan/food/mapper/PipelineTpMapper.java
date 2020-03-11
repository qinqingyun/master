package com.meituan.food.mapper;

import com.meituan.food.po.PipelineDirectionPO;
import com.meituan.food.po.PipelinePrPO;
import com.meituan.food.po.PipelineTpPO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;

import java.time.LocalDate;

public interface PipelineTpMapper {

    @Delete({
        "delete from pipeline_tp_data",
        "where tp_date = #{createTime,jdbcType=DATE}"
    })
    int deleteByDate(LocalDate createTime);

    @Insert({
        "insert into pipeline_tp_data (id, direction_id, direction_name," +
                "task,sum,pass,allCase," +
                "failed,oneTimePassCount,autoRunCountNumberList," +
                "branchCheckOK,branchCheckNO," +
                "enviromentcheckOK," +
                "enviromentcheckNO,codeCheckOK,codeCheckNO," +
                "sonarCheckOK,sonarCheckNO,unitCheckOk," +
                "unitCheckNO,buildCheckOK,buildCheckNO," +
                "autoCheckOK,autoCheckNO,otherOK," +
                "otherNO,rejectReasonString,rejectDescString,tp_date)",
        "values (#{id,jdbcType=INTEGER},#{direction_id,jdbcType=INTEGER},#{direction_name,jdbcType=VARCHAR}," +
                "#{task,jdbcType=INTEGER},#{sum,jdbcType=INTEGER},#{pass,jdbcType=INTEGER},#{allCase,jdbcType=INTEGER}," +
                "#{failed,jdbcType=INTEGER},#{oneTimePassCount,jdbcType=INTEGER},#{autoRunCountNumberList,jdbcType=INTEGER}," +
                "#{branchCheckOK,jdbcType=INTEGER},#{branchCheckNO,jdbcType=INTEGER}," +
                "#{enviromentcheckOK,jdbcType=INTEGER}," +
                "#{enviromentcheckNO,jdbcType=INTEGER},#{codeCheckOK,jdbcType=INTEGER},#{codeCheckNO,jdbcType=INTEGER}," +
                "#{sonarCheckOK,jdbcType=INTEGER},#{sonarCheckNO,jdbcType=INTEGER},#{unitCheckOk,jdbcType=INTEGER}," +
                "#{unitCheckNO,jdbcType=INTEGER},#{buildCheckOK,jdbcType=INTEGER},#{buildCheckNO,jdbcType=INTEGER}," +
                "#{autoCheckOK,jdbcType=INTEGER},#{autoCheckNO,jdbcType=INTEGER},#{otherOK,jdbcType=INTEGER}," +
                "#{otherNO,jdbcType=INTEGER}, #{rejectReasonString,jdbcType=VARCHAR},#{rejectDescString,jdbcType=VARCHAR},#{tp_date,jdbcType=DATE})"
    })
    int insert(PipelineTpPO record);

    @Insert({
            "insert into direction (id, direction_id, direction_name,group_name)",
            "values (#{id,jdbcType=INTEGER},#{direction_id,jdbcType=INTEGER},#{direction_name,jdbcType=VARCHAR},#{group_name,jdbcType=VARCHAR})"
    })
    int insertDirection(PipelineDirectionPO record);

}