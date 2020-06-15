package com.meituan.food.mapper;

import com.meituan.food.po.McdCoeTodoPO;
import com.meituan.food.po.McdCoeTodoPOExample;
import java.util.List;

import com.meituan.food.po.ToDoPo;
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

public interface McdCoeTodoPOMapper {
    @SelectProvider(type=McdCoeTodoPOSqlProvider.class, method="countByExample")
    long countByExample(McdCoeTodoPOExample example);

    @DeleteProvider(type=McdCoeTodoPOSqlProvider.class, method="deleteByExample")
    int deleteByExample(McdCoeTodoPOExample example);

    @Delete({
        "delete from mcd_todo_list",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into mcd_todo_list (id, coe_id, ",
        "ones_id, ones_link, ",
        "ones_title, is_finish, ",
        "is_delay, dealline, owner_mis, ",
        "owner_name, start_date, ",
        "actual_date)",
        "values (#{id,jdbcType=INTEGER}, #{coeId,jdbcType=INTEGER}, ",
        "#{onesId,jdbcType=INTEGER}, #{onesLink,jdbcType=VARCHAR}, ",
        "#{onesTitle,jdbcType=VARCHAR}, #{isFinish,jdbcType=BIT}, ",
        "#{isDelay,jdbcType=BIT}, #{dealline,jdbcType=DATE}, #{ownerMis,jdbcType=VARCHAR}, ",
        "#{ownerName,jdbcType=VARCHAR}, #{startDate,jdbcType=DATE}, ",
        "#{actualDate,jdbcType=DATE})"
    })
    int insert(McdCoeTodoPO record);

    @InsertProvider(type=McdCoeTodoPOSqlProvider.class, method="insertSelective")
    int insertSelective(McdCoeTodoPO record);

    @SelectProvider(type=McdCoeTodoPOSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="coe_id", property="coeId", jdbcType=JdbcType.INTEGER),
        @Result(column="ones_id", property="onesId", jdbcType=JdbcType.INTEGER),
        @Result(column="ones_link", property="onesLink", jdbcType=JdbcType.VARCHAR),
        @Result(column="ones_title", property="onesTitle", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_finish", property="isFinish", jdbcType=JdbcType.BIT),
        @Result(column="is_delay", property="isDelay", jdbcType=JdbcType.BIT),
        @Result(column="dealline", property="dealline", jdbcType=JdbcType.DATE),
        @Result(column="owner_mis", property="ownerMis", jdbcType=JdbcType.VARCHAR),
        @Result(column="owner_name", property="ownerName", jdbcType=JdbcType.VARCHAR),
        @Result(column="start_date", property="startDate", jdbcType=JdbcType.DATE),
        @Result(column="actual_date", property="actualDate", jdbcType=JdbcType.DATE)
    })
    List<McdCoeTodoPO> selectByExample(McdCoeTodoPOExample example);

    @Select({
        "select",
        "id, coe_id, ones_id, ones_link, ones_title, is_finish, is_delay, dealline, owner_mis, ",
        "owner_name, start_date, actual_date",
        "from mcd_todo_list",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="coe_id", property="coeId", jdbcType=JdbcType.INTEGER),
        @Result(column="ones_id", property="onesId", jdbcType=JdbcType.INTEGER),
        @Result(column="ones_link", property="onesLink", jdbcType=JdbcType.VARCHAR),
        @Result(column="ones_title", property="onesTitle", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_finish", property="isFinish", jdbcType=JdbcType.BIT),
        @Result(column="is_delay", property="isDelay", jdbcType=JdbcType.BIT),
        @Result(column="dealline", property="dealline", jdbcType=JdbcType.DATE),
        @Result(column="owner_mis", property="ownerMis", jdbcType=JdbcType.VARCHAR),
        @Result(column="owner_name", property="ownerName", jdbcType=JdbcType.VARCHAR),
        @Result(column="start_date", property="startDate", jdbcType=JdbcType.DATE),
        @Result(column="actual_date", property="actualDate", jdbcType=JdbcType.DATE)
    })
    McdCoeTodoPO selectByPrimaryKey(Integer id);

    @UpdateProvider(type=McdCoeTodoPOSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") McdCoeTodoPO record, @Param("example") McdCoeTodoPOExample example);

    @UpdateProvider(type=McdCoeTodoPOSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") McdCoeTodoPO record, @Param("example") McdCoeTodoPOExample example);

    @UpdateProvider(type=McdCoeTodoPOSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(McdCoeTodoPO record);

    @Update({
        "update mcd_todo_list",
        "set coe_id = #{coeId,jdbcType=INTEGER},",
          "ones_id = #{onesId,jdbcType=INTEGER},",
          "ones_link = #{onesLink,jdbcType=VARCHAR},",
          "ones_title = #{onesTitle,jdbcType=VARCHAR},",
          "is_finish = #{isFinish,jdbcType=BIT},",
          "is_delay = #{isDelay,jdbcType=BIT},",
          "dealline = #{dealline,jdbcType=DATE},",
          "owner_mis = #{ownerMis,jdbcType=VARCHAR},",
          "owner_name = #{ownerName,jdbcType=VARCHAR},",
          "start_date = #{startDate,jdbcType=DATE},",
          "actual_date = #{actualDate,jdbcType=DATE}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(McdCoeTodoPO record);




    @Select({
            "select ones_id from mcd_todo_list"
    })
    @Results({
            @Result(column="ones_id", property="onesId", jdbcType=JdbcType.INTEGER),

    })
    List<Integer> selectOnesIdList();




    @Select({
            "select",
            "id, coe_id, ones_id, ones_link, ones_title, is_finish, is_delay, dealline, owner_mis, ",
            "owner_name, start_date, actual_date",
            "from mcd_todo_list",
            "where ones_id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="coe_id", property="coeId", jdbcType=JdbcType.INTEGER),
            @Result(column="ones_id", property="onesId", jdbcType=JdbcType.INTEGER),
            @Result(column="ones_link", property="onesLink", jdbcType=JdbcType.VARCHAR),
            @Result(column="ones_title", property="onesTitle", jdbcType=JdbcType.VARCHAR),
            @Result(column="is_finish", property="isFinish", jdbcType=JdbcType.BIT),
            @Result(column="is_delay", property="isDelay", jdbcType=JdbcType.BIT),
            @Result(column="dealline", property="dealline", jdbcType=JdbcType.DATE),
            @Result(column="owner_mis", property="ownerMis", jdbcType=JdbcType.VARCHAR),
            @Result(column="owner_name", property="ownerName", jdbcType=JdbcType.VARCHAR),
            @Result(column="start_date", property="startDate", jdbcType=JdbcType.DATE),
            @Result(column="actual_date", property="actualDate", jdbcType=JdbcType.DATE)
    })
    McdCoeTodoPO selectByOnesId(Integer id);
}