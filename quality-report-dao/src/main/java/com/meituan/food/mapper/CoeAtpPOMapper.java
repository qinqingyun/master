package com.meituan.food.mapper;

import com.meituan.food.po.CoeAtpPO;
import com.meituan.food.po.CoeAtpPOExample;

import java.util.Date;
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

public interface CoeAtpPOMapper {
    @SelectProvider(type=CoeAtpPOSqlProvider.class, method="countByExample")
    long countByExample(CoeAtpPOExample example);

    @DeleteProvider(type=CoeAtpPOSqlProvider.class, method="deleteByExample")
    int deleteByExample(CoeAtpPOExample example);

    @Delete({
        "delete from coe_atp_status_table",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into coe_atp_status_table (id, coe_id, ",
        "is_push, first_push_date, ",
        "push_text, receiver)",
        "values (#{id,jdbcType=INTEGER}, #{coeId,jdbcType=INTEGER}, ",
        "#{isPush,jdbcType=BIT}, #{firstPushDate,jdbcType=DATE}, ",
        "#{pushText,jdbcType=VARCHAR}, #{receiver,jdbcType=VARCHAR})"
    })
    int insert(CoeAtpPO record);

    @InsertProvider(type=CoeAtpPOSqlProvider.class, method="insertSelective")
    int insertSelective(CoeAtpPO record);

    @SelectProvider(type=CoeAtpPOSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="coe_id", property="coeId", jdbcType=JdbcType.INTEGER),
        @Result(column="is_push", property="isPush", jdbcType=JdbcType.BIT),
        @Result(column="first_push_date", property="firstPushDate", jdbcType=JdbcType.DATE),
        @Result(column="push_text", property="pushText", jdbcType=JdbcType.VARCHAR),
        @Result(column="receiver", property="receiver", jdbcType=JdbcType.VARCHAR)
    })
    List<CoeAtpPO> selectByExample(CoeAtpPOExample example);

    @Select({
        "select",
        "id, coe_id, is_push, first_push_date, push_text, receiver",
        "from coe_atp_status_table",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="coe_id", property="coeId", jdbcType=JdbcType.INTEGER),
        @Result(column="is_push", property="isPush", jdbcType=JdbcType.BIT),
        @Result(column="first_push_date", property="firstPushDate", jdbcType=JdbcType.DATE),
        @Result(column="push_text", property="pushText", jdbcType=JdbcType.VARCHAR),
        @Result(column="receiver", property="receiver", jdbcType=JdbcType.VARCHAR)
    })
    CoeAtpPO selectByPrimaryKey(Integer id);

    @UpdateProvider(type=CoeAtpPOSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") CoeAtpPO record, @Param("example") CoeAtpPOExample example);

    @UpdateProvider(type=CoeAtpPOSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") CoeAtpPO record, @Param("example") CoeAtpPOExample example);

    @UpdateProvider(type=CoeAtpPOSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(CoeAtpPO record);

    @Update({
        "update coe_atp_status_table",
        "set coe_id = #{coeId,jdbcType=INTEGER},",
          "is_push = #{isPush,jdbcType=BIT},",
          "first_push_date = #{firstPushDate,jdbcType=DATE},",
          "push_text = #{pushText,jdbcType=VARCHAR},",
          "receiver = #{receiver,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(CoeAtpPO record);

    @Update({
            "update coe_atp_status_table",
            "set is_push = true",
            "where coe_id = #{id,jdbcType=INTEGER}"
    })
    int updateByCoeId(int  id);


    @Select({
            "select coe_id from coe_atp_status_table"
    })
    @Results({
            @Result(column="coe_id", property="coeId", jdbcType=JdbcType.INTEGER)
    })
    List<Integer> selectAllCoeList();

    @Select({
            "select",
            "id, coe_id, is_push, first_push_date, push_text, receiver",
            "from coe_atp_status_table",
            "where is_push=false and first_push_date< #{firstPushDate,jdbcType=DATE}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="coe_id", property="coeId", jdbcType=JdbcType.INTEGER),
            @Result(column="is_push", property="isPush", jdbcType=JdbcType.BIT),
            @Result(column="first_push_date", property="firstPushDate", jdbcType=JdbcType.DATE),
            @Result(column="push_text", property="pushText", jdbcType=JdbcType.VARCHAR),
            @Result(column="receiver", property="receiver", jdbcType=JdbcType.VARCHAR)
    })
    List<CoeAtpPO> selectUnfinishCoeList(Date firstPushDate);


}