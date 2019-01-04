package com.meituan.food.mapper;

import com.meituan.food.po.EfficiencyBugNumPO;
import com.meituan.food.po.EfficiencyBugNumPOExample;
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

public interface EfficiencyBugNumPOMapper {
    @SelectProvider(type=EfficiencyBugNumPOSqlProvider.class, method="countByExample")
    long countByExample(EfficiencyBugNumPOExample example);

    @DeleteProvider(type=EfficiencyBugNumPOSqlProvider.class, method="deleteByExample")
    int deleteByExample(EfficiencyBugNumPOExample example);

    @Delete({
        "delete from efficiency_bug_num",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into efficiency_bug_num (id, mis, ",
        "create_num, accept_num, ",
        "efficiency_date, created_at, ",
        "updated_at)",
        "values (#{id,jdbcType=INTEGER}, #{mis,jdbcType=VARCHAR}, ",
        "#{createNum,jdbcType=INTEGER}, #{acceptNum,jdbcType=INTEGER}, ",
        "#{efficiencyDate,jdbcType=VARCHAR}, #{createdAt,jdbcType=TIMESTAMP}, ",
        "#{updatedAt,jdbcType=TIMESTAMP})"
    })
    int insert(EfficiencyBugNumPO record);

    @InsertProvider(type=EfficiencyBugNumPOSqlProvider.class, method="insertSelective")
    int insertSelective(EfficiencyBugNumPO record);

    @SelectProvider(type=EfficiencyBugNumPOSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="mis", property="mis", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_num", property="createNum", jdbcType=JdbcType.INTEGER),
        @Result(column="accept_num", property="acceptNum", jdbcType=JdbcType.INTEGER),
        @Result(column="efficiency_date", property="efficiencyDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="updated_at", property="updatedAt", jdbcType=JdbcType.TIMESTAMP)
    })
    List<EfficiencyBugNumPO> selectByExample(EfficiencyBugNumPOExample example);

    @Select({
        "select",
        "id, mis, create_num, accept_num, efficiency_date, created_at, updated_at",
        "from efficiency_bug_num",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="mis", property="mis", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_num", property="createNum", jdbcType=JdbcType.INTEGER),
        @Result(column="accept_num", property="acceptNum", jdbcType=JdbcType.INTEGER),
        @Result(column="efficiency_date", property="efficiencyDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="updated_at", property="updatedAt", jdbcType=JdbcType.TIMESTAMP)
    })
    EfficiencyBugNumPO selectByPrimaryKey(Integer id);

    @Select({
            "select",
            "id, mis, create_num, accept_num, efficiency_date, created_at, updated_at",
            "from efficiency_bug_num",
            "where mis = #{mis,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="mis", property="mis", jdbcType=JdbcType.VARCHAR),
            @Result(column="create_num", property="createNum", jdbcType=JdbcType.INTEGER),
            @Result(column="accept_num", property="acceptNum", jdbcType=JdbcType.INTEGER),
            @Result(column="efficiency_date", property="efficiencyDate", jdbcType=JdbcType.VARCHAR),
            @Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="updated_at", property="updatedAt", jdbcType=JdbcType.TIMESTAMP)
    })
    EfficiencyBugNumPO selectByPrimaryMis(String mis);

    @UpdateProvider(type=EfficiencyBugNumPOSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") EfficiencyBugNumPO record, @Param("example") EfficiencyBugNumPOExample example);

    @UpdateProvider(type=EfficiencyBugNumPOSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") EfficiencyBugNumPO record, @Param("example") EfficiencyBugNumPOExample example);

    @UpdateProvider(type=EfficiencyBugNumPOSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(EfficiencyBugNumPO record);

    @Update({
        "update efficiency_bug_num",
        "set mis = #{mis,jdbcType=VARCHAR},",
          "create_num = #{createNum,jdbcType=INTEGER},",
          "accept_num = #{acceptNum,jdbcType=INTEGER},",
          "efficiency_date = #{efficiencyDate,jdbcType=VARCHAR},",
          "created_at = #{createdAt,jdbcType=TIMESTAMP},",
          "updated_at = #{updatedAt,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(EfficiencyBugNumPO record);
}