package com.meituan.food.mapper;

import com.meituan.food.po.MomaCrashRatePO;
import com.meituan.food.po.MomaCrashRatePOExample;
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

public interface MomaCrashRatePOMapper {
    @SelectProvider(type=MomaCrashRatePOSqlProvider.class, method="countByExample")
    long countByExample(MomaCrashRatePOExample example);

    @DeleteProvider(type=MomaCrashRatePOSqlProvider.class, method="deleteByExample")
    int deleteByExample(MomaCrashRatePOExample example);

    @Delete({
        "delete from moma_crash_rate",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into moma_crash_rate (id, moma_crash_count, ",
        "moma_crash_rate, bee_crash_count, ",
        "bee_crash_rate, aboluo_crash_count, ",
        "aboluo_crash_rate, month, ",
        "platform, os, created_at, ",
        "updated_at)",
        "values (#{id,jdbcType=INTEGER}, #{momaCrashCount,jdbcType=INTEGER}, ",
        "#{momaCrashRate,jdbcType=DECIMAL}, #{beeCrashCount,jdbcType=INTEGER}, ",
        "#{beeCrashRate,jdbcType=DECIMAL}, #{aboluoCrashCount,jdbcType=INTEGER}, ",
        "#{aboluoCrashRate,jdbcType=DECIMAL}, #{month,jdbcType=VARCHAR}, ",
        "#{platform,jdbcType=VARCHAR}, #{os,jdbcType=VARCHAR}, #{createdAt,jdbcType=TIMESTAMP}, ",
        "#{updatedAt,jdbcType=TIMESTAMP})"
    })
    int insert(MomaCrashRatePO record);

    @InsertProvider(type=MomaCrashRatePOSqlProvider.class, method="insertSelective")
    int insertSelective(MomaCrashRatePO record);

    @SelectProvider(type=MomaCrashRatePOSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="moma_crash_count", property="momaCrashCount", jdbcType=JdbcType.INTEGER),
        @Result(column="moma_crash_rate", property="momaCrashRate", jdbcType=JdbcType.DECIMAL),
        @Result(column="bee_crash_count", property="beeCrashCount", jdbcType=JdbcType.INTEGER),
        @Result(column="bee_crash_rate", property="beeCrashRate", jdbcType=JdbcType.DECIMAL),
        @Result(column="aboluo_crash_count", property="aboluoCrashCount", jdbcType=JdbcType.INTEGER),
        @Result(column="aboluo_crash_rate", property="aboluoCrashRate", jdbcType=JdbcType.DECIMAL),
        @Result(column="month", property="month", jdbcType=JdbcType.VARCHAR),
        @Result(column="platform", property="platform", jdbcType=JdbcType.VARCHAR),
        @Result(column="os", property="os", jdbcType=JdbcType.VARCHAR),
        @Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="updated_at", property="updatedAt", jdbcType=JdbcType.TIMESTAMP)
    })
    List<MomaCrashRatePO> selectByExample(MomaCrashRatePOExample example);

    @Select({
        "select",
        "id, moma_crash_count, moma_crash_rate, bee_crash_count, bee_crash_rate, aboluo_crash_count, ",
        "aboluo_crash_rate, month, platform, os, created_at, updated_at",
        "from moma_crash_rate",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="moma_crash_count", property="momaCrashCount", jdbcType=JdbcType.INTEGER),
        @Result(column="moma_crash_rate", property="momaCrashRate", jdbcType=JdbcType.DECIMAL),
        @Result(column="bee_crash_count", property="beeCrashCount", jdbcType=JdbcType.INTEGER),
        @Result(column="bee_crash_rate", property="beeCrashRate", jdbcType=JdbcType.DECIMAL),
        @Result(column="aboluo_crash_count", property="aboluoCrashCount", jdbcType=JdbcType.INTEGER),
        @Result(column="aboluo_crash_rate", property="aboluoCrashRate", jdbcType=JdbcType.DECIMAL),
        @Result(column="month", property="month", jdbcType=JdbcType.VARCHAR),
        @Result(column="platform", property="platform", jdbcType=JdbcType.VARCHAR),
        @Result(column="os", property="os", jdbcType=JdbcType.VARCHAR),
        @Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="updated_at", property="updatedAt", jdbcType=JdbcType.TIMESTAMP)
    })
    MomaCrashRatePO selectByPrimaryKey(Integer id);

    @UpdateProvider(type=MomaCrashRatePOSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") MomaCrashRatePO record, @Param("example") MomaCrashRatePOExample example);

    @UpdateProvider(type=MomaCrashRatePOSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") MomaCrashRatePO record, @Param("example") MomaCrashRatePOExample example);

    @UpdateProvider(type=MomaCrashRatePOSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(MomaCrashRatePO record);

    @Update({
        "update moma_crash_rate",
        "set moma_crash_count = #{momaCrashCount,jdbcType=INTEGER},",
          "moma_crash_rate = #{momaCrashRate,jdbcType=DECIMAL},",
          "bee_crash_count = #{beeCrashCount,jdbcType=INTEGER},",
          "bee_crash_rate = #{beeCrashRate,jdbcType=DECIMAL},",
          "aboluo_crash_count = #{aboluoCrashCount,jdbcType=INTEGER},",
          "aboluo_crash_rate = #{aboluoCrashRate,jdbcType=DECIMAL},",
          "month = #{month,jdbcType=VARCHAR},",
          "platform = #{platform,jdbcType=VARCHAR},",
          "os = #{os,jdbcType=VARCHAR},",
          "created_at = #{createdAt,jdbcType=TIMESTAMP},",
          "updated_at = #{updatedAt,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(MomaCrashRatePO record);
}