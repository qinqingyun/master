package com.meituan.food.mapper;

import com.meituan.food.po.GitPO;
import com.meituan.food.po.GitPOExample;
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

public interface GitPOMapper {
    @SelectProvider(type=GitPOSqlProvider.class, method="countByExample")
    long countByExample(GitPOExample example);

    @DeleteProvider(type=GitPOSqlProvider.class, method="deleteByExample")
    int deleteByExample(GitPOExample example);

    @Delete({
        "delete from git",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into git (id, misid, ",
        "name, git_date, git_code_increase, ",
        "git_code_delete, git_code_submit, ",
        "git_code_submit_time)",
        "values (#{id,jdbcType=INTEGER}, #{misid,jdbcType=VARCHAR}, ",
        "#{name,jdbcType=VARCHAR}, #{gitDate,jdbcType=VARCHAR}, #{gitCodeIncrease,jdbcType=INTEGER}, ",
        "#{gitCodeDelete,jdbcType=INTEGER}, #{gitCodeSubmit,jdbcType=INTEGER}, ",
        "#{gitCodeSubmitTime,jdbcType=INTEGER})"
    })
    int insert(GitPO record);

    @InsertProvider(type=GitPOSqlProvider.class, method="insertSelective")
    int insertSelective(GitPO record);

    @SelectProvider(type=GitPOSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="misid", property="misid", jdbcType=JdbcType.VARCHAR),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="git_date", property="gitDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="git_code_increase", property="gitCodeIncrease", jdbcType=JdbcType.INTEGER),
        @Result(column="git_code_delete", property="gitCodeDelete", jdbcType=JdbcType.INTEGER),
        @Result(column="git_code_submit", property="gitCodeSubmit", jdbcType=JdbcType.INTEGER),
        @Result(column="git_code_submit_time", property="gitCodeSubmitTime", jdbcType=JdbcType.INTEGER)
    })
    List<GitPO> selectByExample(GitPOExample example);

    @Select({
        "select",
        "id, misid, name, git_date, git_code_increase, git_code_delete, git_code_submit, ",
        "git_code_submit_time",
        "from git",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="misid", property="misid", jdbcType=JdbcType.VARCHAR),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="git_date", property="gitDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="git_code_increase", property="gitCodeIncrease", jdbcType=JdbcType.INTEGER),
        @Result(column="git_code_delete", property="gitCodeDelete", jdbcType=JdbcType.INTEGER),
        @Result(column="git_code_submit", property="gitCodeSubmit", jdbcType=JdbcType.INTEGER),
        @Result(column="git_code_submit_time", property="gitCodeSubmitTime", jdbcType=JdbcType.INTEGER)
    })
    GitPO selectByPrimaryKey(Integer id);

    @Select({
            "select",
            "id, misid, name, git_date, git_code_increase, git_code_delete, git_code_submit, ",
            "git_code_submit_time",
            "from git",
            "where misid = #{mis,jdbcType=VARCHAR}",
            "and git_date=#{gitDate,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="misid", property="misid", jdbcType=JdbcType.VARCHAR),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="git_date", property="gitDate", jdbcType=JdbcType.VARCHAR),
            @Result(column="git_code_increase", property="gitCodeIncrease", jdbcType=JdbcType.INTEGER),
            @Result(column="git_code_delete", property="gitCodeDelete", jdbcType=JdbcType.INTEGER),
            @Result(column="git_code_submit", property="gitCodeSubmit", jdbcType=JdbcType.INTEGER),
            @Result(column="git_code_submit_time", property="gitCodeSubmitTime", jdbcType=JdbcType.INTEGER)
    })
    GitPO selectByPrimaryMis(String mis,String gitDate);

    @UpdateProvider(type=GitPOSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") GitPO record, @Param("example") GitPOExample example);

    @UpdateProvider(type=GitPOSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") GitPO record, @Param("example") GitPOExample example);

    @UpdateProvider(type=GitPOSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(GitPO record);

    @Update({
        "update git",
        "set misid = #{misid,jdbcType=VARCHAR},",
          "name = #{name,jdbcType=VARCHAR},",
          "git_date = #{gitDate,jdbcType=VARCHAR},",
          "git_code_increase = #{gitCodeIncrease,jdbcType=INTEGER},",
          "git_code_delete = #{gitCodeDelete,jdbcType=INTEGER},",
          "git_code_submit = #{gitCodeSubmit,jdbcType=INTEGER},",
          "git_code_submit_time = #{gitCodeSubmitTime,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(GitPO record);
}