package com.meituan.food.mapper;

import com.meituan.food.po.ToDoPo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface McdToDoPOMapper {


    //查询TODO表中的ones相关信息
    @Select({
            "select ones_id from todo_list"
    })
    @Results({
            @Result(column="ones_id", property="onesId", jdbcType=JdbcType.INTEGER),

    })
    List<Integer> selectOnesIdList();


   //todo信息入库
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
    int insert(ToDoPo record);


    //根据ID查询todo相关信息
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
    ToDoPo selectByOnesId(Integer id);



    //变更case中TODO信息
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
    int updateByPrimaryKey(ToDoPo record);
}
