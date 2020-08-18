package com.meituan.food.mapper;

import com.meituan.food.po.ApiDetailPO;
import com.meituan.food.po.PipelinePrAutoPO;
import com.meituan.food.po.PipelinePrPO;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface PipelinePrMapper {

    @Delete({
        "delete from pipeline_pr_data",
        "where it_date = #{createTime,jdbcType=DATE}"
    })
    int deleteByDate(LocalDate createTime);

    @Delete({
            "delete from pipeline_pr_repo_data",
            "where auto_date = #{createTime,jdbcType=DATE}"
    })
    int deleteRepoByDate(LocalDate createTime);


    @Insert({
            "insert into direction(id,direction_id,direction_name,group_name,repo,priority,isAutoOn)",
            "values (#{id,jdbcType=INTEGER},#{department_id,jdbcType=INTEGER},#{directionName,jdbcType=VARCHAR},#{group_name,jdbcType=VARCHAR},#{repo,jdbcType=VARCHAR},#{priority,jdbcType=VARCHAR},#{isAutoOn,jdbcType=INTEGER})"
    })
    int insertRepoInfo(PipelinePrAutoPO record);


    @Insert({
            "<script>",
            "insert into direction(id,direction_id,direction_name,group_name,repo,priority,isAutoOn)",
            "values ",
            "<foreach collection='list' item='item' index='index' separator=','>",
            "(#{item.id,jdbcType=INTEGER},#{item.department_id,jdbcType=INTEGER},#{item.directionName,jdbcType=VARCHAR},#{item.group_name,jdbcType=VARCHAR},#{item.repo,jdbcType=VARCHAR},#{item.priority,jdbcType=VARCHAR},#{item.isAutoOn,jdbcType=INTEGER})",
            "</foreach>",
            "</script>"
    })
    int insertRepoInfoList(List<PipelinePrAutoPO> records);

    @Insert({
            "<script>",
            "insert into pipeline_pr_repo_data (id,department_id, department_name, repo,priority,isAutoOn, totalCase,passes,coverage,pr_times,auto_date,times)",
            "values ",
            "<foreach collection='list' item='item' index='index' separator=','>",
            "(#{item.id,jdbcType=INTEGER},#{item.department_id,jdbcType=INTEGER},#{item.directionName,jdbcType=VARCHAR},#{item.repo,jdbcType=VARCHAR},#{item.priority,jdbcType=VARCHAR},#{item.isAutoOn,jdbcType=INTEGER}, #{item.totalCase,jdbcType=INTEGER},#{item.passes,jdbcType=DECIMAL},#{item.coverage,jdbcType=DECIMAL},#{item.pr_times,jdbcType=INTEGER},#{item.auto_date,jdbcType=DATE},#{item.times,jdbcType=INTEGER})",
            "</foreach>",
            "</script>"
    })
    int insertRepoList(List<PipelinePrAutoPO> records);

    @Delete({
            "delete from direction",
            "where id!=0"
    })
    int deleteDirRepoByDate();

    @Insert({
        "insert into pipeline_pr_data (id, direction_id, direction_name, hotfixTotal,hotfixDetail, skipTotal,skipDetail,skipReason,it_date)",
        "values (#{id,jdbcType=INTEGER},#{direction_id,jdbcType=INTEGER},#{directionName,jdbcType=VARCHAR},#{hotfix,jdbcType=INTEGER},#{hotfixDetailStr,jdbcType=VARCHAR},#{skip,jdbcType=INTEGER}, #{skipDetailStr,jdbcType=VARCHAR},#{skipReasonStr,jdbcType=VARCHAR},#{createTime,jdbcType=DATE})"
    })
    int insert(PipelinePrPO record);

    @Insert({
            "insert into pipeline_pr_repo_data (id,department_id, department_name, repo,priority,isAutoOn, totalCase,passes,coverage,pr_times,auto_date,times,auto_times,auto_success_times,cov_detail)",
            "values (#{id,jdbcType=INTEGER},#{department_id,jdbcType=INTEGER},#{directionName,jdbcType=VARCHAR},#{repo,jdbcType=VARCHAR},#{priority,jdbcType=VARCHAR},#{isAutoOn,jdbcType=INTEGER}, #{totalCase,jdbcType=INTEGER},#{passes,jdbcType=DECIMAL},#{coverage,jdbcType=VARCHAR},#{pr_times,jdbcType=INTEGER},#{auto_date,jdbcType=DATE},#{times,jdbcType=INTEGER},#{auto_times,jdbcType=INTEGER},#{auto_success_times,jdbcType=INTEGER},#{cov_detail,jdbcType=VARCHAR})"
    })
    int insertRepo(PipelinePrAutoPO record);





    @Update({
            "update direction",
            "set mis = #{mis,jdbcType=VARCHAR}",
            "where repo = #{repo,jdbcType=VARCHAR}"
    })
    int updateDirectionMis(PipelinePrAutoPO record);



}