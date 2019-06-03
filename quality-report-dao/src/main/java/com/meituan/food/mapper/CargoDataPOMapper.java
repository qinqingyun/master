package com.meituan.food.mapper;

import com.meituan.food.po.CargoDataPO;
import com.meituan.food.po.CargoDataPOExample;
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

public interface CargoDataPOMapper {
    @SelectProvider(type=CargoDataPOSqlProvider.class, method="countByExample")
    long countByExample(CargoDataPOExample example);

    @DeleteProvider(type=CargoDataPOSqlProvider.class, method="deleteByExample")
    int deleteByExample(CargoDataPOExample example);

    @Delete({
        "delete from cargo_data",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into cargo_data (id, stackuuid, ",
        "stable_success, stable_total, ",
        "avalible_success, avalible_total, ",
        "tag, person, direction, ",
        "stable_tag_percentage, avalible_tag_percentage, ",
        "date, comment, ",
        "updated_date)",
        "values (#{id,jdbcType=INTEGER}, #{stackuuid,jdbcType=VARCHAR}, ",
        "#{stableSuccess,jdbcType=INTEGER}, #{stableTotal,jdbcType=INTEGER}, ",
        "#{avalibleSuccess,jdbcType=INTEGER}, #{avalibleTotal,jdbcType=INTEGER}, ",
        "#{tag,jdbcType=VARCHAR}, #{person,jdbcType=VARCHAR}, #{direction,jdbcType=VARCHAR}, ",
        "#{stableTagPercentage,jdbcType=VARCHAR}, #{avalibleTagPercentage,jdbcType=VARCHAR}, ",
        "#{date,jdbcType=TIMESTAMP}, #{comment,jdbcType=VARCHAR}, ",
        "#{updatedDate,jdbcType=TIMESTAMP})"
    })
    int insert(CargoDataPO record);

    @InsertProvider(type=CargoDataPOSqlProvider.class, method="insertSelective")
    int insertSelective(CargoDataPO record);

    @SelectProvider(type=CargoDataPOSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="stackuuid", property="stackuuid", jdbcType=JdbcType.VARCHAR),
        @Result(column="stable_success", property="stableSuccess", jdbcType=JdbcType.INTEGER),
        @Result(column="stable_total", property="stableTotal", jdbcType=JdbcType.INTEGER),
        @Result(column="avalible_success", property="avalibleSuccess", jdbcType=JdbcType.INTEGER),
        @Result(column="avalible_total", property="avalibleTotal", jdbcType=JdbcType.INTEGER),
        @Result(column="tag", property="tag", jdbcType=JdbcType.VARCHAR),
        @Result(column="person", property="person", jdbcType=JdbcType.VARCHAR),
        @Result(column="direction", property="direction", jdbcType=JdbcType.VARCHAR),
        @Result(column="stable_tag_percentage", property="stableTagPercentage", jdbcType=JdbcType.VARCHAR),
        @Result(column="avalible_tag_percentage", property="avalibleTagPercentage", jdbcType=JdbcType.VARCHAR),
        @Result(column="date", property="date", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="comment", property="comment", jdbcType=JdbcType.VARCHAR),
        @Result(column="updated_date", property="updatedDate", jdbcType=JdbcType.TIMESTAMP)
    })
    List<CargoDataPO> selectByExample(CargoDataPOExample example);

    @Select({
        "select",
        "id, stackuuid, stable_success, stable_total, avalible_success, avalible_total, ",
        "tag, person, direction, stable_tag_percentage, avalible_tag_percentage, date, ",
        "comment, updated_date",
        "from cargo_data",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="stackuuid", property="stackuuid", jdbcType=JdbcType.VARCHAR),
        @Result(column="stable_success", property="stableSuccess", jdbcType=JdbcType.INTEGER),
        @Result(column="stable_total", property="stableTotal", jdbcType=JdbcType.INTEGER),
        @Result(column="avalible_success", property="avalibleSuccess", jdbcType=JdbcType.INTEGER),
        @Result(column="avalible_total", property="avalibleTotal", jdbcType=JdbcType.INTEGER),
        @Result(column="tag", property="tag", jdbcType=JdbcType.VARCHAR),
        @Result(column="person", property="person", jdbcType=JdbcType.VARCHAR),
        @Result(column="direction", property="direction", jdbcType=JdbcType.VARCHAR),
        @Result(column="stable_tag_percentage", property="stableTagPercentage", jdbcType=JdbcType.VARCHAR),
        @Result(column="avalible_tag_percentage", property="avalibleTagPercentage", jdbcType=JdbcType.VARCHAR),
        @Result(column="date", property="date", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="comment", property="comment", jdbcType=JdbcType.VARCHAR),
        @Result(column="updated_date", property="updatedDate", jdbcType=JdbcType.TIMESTAMP)
    })
    CargoDataPO selectByPrimaryKey(Integer id);

    @UpdateProvider(type=CargoDataPOSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") CargoDataPO record, @Param("example") CargoDataPOExample example);

    @UpdateProvider(type=CargoDataPOSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") CargoDataPO record, @Param("example") CargoDataPOExample example);

    @UpdateProvider(type=CargoDataPOSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(CargoDataPO record);

    @Update({
        "update cargo_data",
        "set stackuuid = #{stackuuid,jdbcType=VARCHAR},",
          "stable_success = #{stableSuccess,jdbcType=INTEGER},",
          "stable_total = #{stableTotal,jdbcType=INTEGER},",
          "avalible_success = #{avalibleSuccess,jdbcType=INTEGER},",
          "avalible_total = #{avalibleTotal,jdbcType=INTEGER},",
          "tag = #{tag,jdbcType=VARCHAR},",
          "person = #{person,jdbcType=VARCHAR},",
          "direction = #{direction,jdbcType=VARCHAR},",
          "stable_tag_percentage = #{stableTagPercentage,jdbcType=VARCHAR},",
          "avalible_tag_percentage = #{avalibleTagPercentage,jdbcType=VARCHAR},",
          "date = #{date,jdbcType=TIMESTAMP},",
          "comment = #{comment,jdbcType=VARCHAR},",
          "updated_date = #{updatedDate,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(CargoDataPO record);
}