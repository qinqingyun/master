package com.meituan.food.mapper;

import com.meituan.food.po.EfficiencyTotalDatePO;
import com.meituan.food.po.EfficiencyTotalDatePOExample;
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

public interface EfficiencyTotalDatePOMapper {
    @SelectProvider(type=EfficiencyTotalDatePOSqlProvider.class, method="countByExample")
    long countByExample(EfficiencyTotalDatePOExample example);

    @DeleteProvider(type=EfficiencyTotalDatePOSqlProvider.class, method="deleteByExample")
    int deleteByExample(EfficiencyTotalDatePOExample example);

    @Delete({
        "delete from efficiency_total_data",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into efficiency_total_data (id, mis, ",
        "name, create_wiki_num, ",
        "update_wiki_num, git_increase, ",
        "git_delete, git_submit, ",
        "git_submit_time, create_bug_num, ",
        "accept_bug_num, partition_date, ",
        "created_at, org_name)",
        "values (#{id,jdbcType=INTEGER}, #{mis,jdbcType=VARCHAR}, ",
        "#{name,jdbcType=VARCHAR}, #{createWikiNum,jdbcType=BIGINT}, ",
        "#{updateWikiNum,jdbcType=BIGINT}, #{gitIncrease,jdbcType=INTEGER}, ",
        "#{gitDelete,jdbcType=INTEGER}, #{gitSubmit,jdbcType=INTEGER}, ",
        "#{gitSubmitTime,jdbcType=INTEGER}, #{createBugNum,jdbcType=INTEGER}, ",
        "#{acceptBugNum,jdbcType=INTEGER}, #{partitionDate,jdbcType=VARCHAR}, ",
        "#{createdAt,jdbcType=TIMESTAMP}, #{orgName,jdbcType=VARCHAR})"
    })
    int insert(EfficiencyTotalDatePO record);

    @InsertProvider(type=EfficiencyTotalDatePOSqlProvider.class, method="insertSelective")
    int insertSelective(EfficiencyTotalDatePO record);

    @SelectProvider(type=EfficiencyTotalDatePOSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="mis", property="mis", jdbcType=JdbcType.VARCHAR),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_wiki_num", property="createWikiNum", jdbcType=JdbcType.BIGINT),
        @Result(column="update_wiki_num", property="updateWikiNum", jdbcType=JdbcType.BIGINT),
        @Result(column="git_increase", property="gitIncrease", jdbcType=JdbcType.INTEGER),
        @Result(column="git_delete", property="gitDelete", jdbcType=JdbcType.INTEGER),
        @Result(column="git_submit", property="gitSubmit", jdbcType=JdbcType.INTEGER),
        @Result(column="git_submit_time", property="gitSubmitTime", jdbcType=JdbcType.INTEGER),
        @Result(column="create_bug_num", property="createBugNum", jdbcType=JdbcType.INTEGER),
        @Result(column="accept_bug_num", property="acceptBugNum", jdbcType=JdbcType.INTEGER),
        @Result(column="partition_date", property="partitionDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="org_name", property="orgName", jdbcType=JdbcType.VARCHAR)
    })
    List<EfficiencyTotalDatePO> selectByExample(EfficiencyTotalDatePOExample example);

    @Select({
        "select",
        "id, mis, name, create_wiki_num, update_wiki_num, git_increase, git_delete, git_submit, ",
        "git_submit_time, create_bug_num, accept_bug_num, partition_date, created_at, ",
        "org_name",
        "from efficiency_total_data",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="mis", property="mis", jdbcType=JdbcType.VARCHAR),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_wiki_num", property="createWikiNum", jdbcType=JdbcType.BIGINT),
        @Result(column="update_wiki_num", property="updateWikiNum", jdbcType=JdbcType.BIGINT),
        @Result(column="git_increase", property="gitIncrease", jdbcType=JdbcType.INTEGER),
        @Result(column="git_delete", property="gitDelete", jdbcType=JdbcType.INTEGER),
        @Result(column="git_submit", property="gitSubmit", jdbcType=JdbcType.INTEGER),
        @Result(column="git_submit_time", property="gitSubmitTime", jdbcType=JdbcType.INTEGER),
        @Result(column="create_bug_num", property="createBugNum", jdbcType=JdbcType.INTEGER),
        @Result(column="accept_bug_num", property="acceptBugNum", jdbcType=JdbcType.INTEGER),
        @Result(column="partition_date", property="partitionDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="org_name", property="orgName", jdbcType=JdbcType.VARCHAR)
    })
    EfficiencyTotalDatePO selectByPrimaryKey(Integer id);

    @UpdateProvider(type=EfficiencyTotalDatePOSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") EfficiencyTotalDatePO record, @Param("example") EfficiencyTotalDatePOExample example);

    @UpdateProvider(type=EfficiencyTotalDatePOSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") EfficiencyTotalDatePO record, @Param("example") EfficiencyTotalDatePOExample example);

    @UpdateProvider(type=EfficiencyTotalDatePOSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(EfficiencyTotalDatePO record);

    @Update({
        "update efficiency_total_data",
        "set mis = #{mis,jdbcType=VARCHAR},",
          "name = #{name,jdbcType=VARCHAR},",
          "create_wiki_num = #{createWikiNum,jdbcType=BIGINT},",
          "update_wiki_num = #{updateWikiNum,jdbcType=BIGINT},",
          "git_increase = #{gitIncrease,jdbcType=INTEGER},",
          "git_delete = #{gitDelete,jdbcType=INTEGER},",
          "git_submit = #{gitSubmit,jdbcType=INTEGER},",
          "git_submit_time = #{gitSubmitTime,jdbcType=INTEGER},",
          "create_bug_num = #{createBugNum,jdbcType=INTEGER},",
          "accept_bug_num = #{acceptBugNum,jdbcType=INTEGER},",
          "partition_date = #{partitionDate,jdbcType=VARCHAR},",
          "created_at = #{createdAt,jdbcType=TIMESTAMP},",
          "org_name = #{orgName,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(EfficiencyTotalDatePO record);
}