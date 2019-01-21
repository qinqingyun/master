package com.meituan.food.mapper;

import com.meituan.food.po.DutyTablePO;
import com.meituan.food.po.DutyTablePOExample;
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

public interface DutyTablePOMapper {
    @SelectProvider(type=DutyTablePOSqlProvider.class, method="countByExample")
    long countByExample(DutyTablePOExample example);

    @DeleteProvider(type=DutyTablePOSqlProvider.class, method="deleteByExample")
    int deleteByExample(DutyTablePOExample example);

    @Delete({
        "delete from duty_table",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into duty_table (id, duty_name, ",
        "duty_mis, is_on_duty, duty_date)",
        "values (#{id,jdbcType=INTEGER}, #{dutyName,jdbcType=VARCHAR}, ",
        "#{dutyMis,jdbcType=VARCHAR}, #{isOnDuty,jdbcType=BIT}, #{dutyDate,jdbcType=VARCHAR})"
    })
    int insert(DutyTablePO record);

    @InsertProvider(type=DutyTablePOSqlProvider.class, method="insertSelective")
    int insertSelective(DutyTablePO record);

    @SelectProvider(type=DutyTablePOSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="duty_name", property="dutyName", jdbcType=JdbcType.VARCHAR),
        @Result(column="duty_mis", property="dutyMis", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_on_duty", property="isOnDuty", jdbcType=JdbcType.BIT),
        @Result(column="duty_date", property="dutyDate", jdbcType=JdbcType.VARCHAR)
    })
    List<DutyTablePO> selectByExample(DutyTablePOExample example);

    @Select({
        "select",
        "id, duty_name, duty_mis, is_on_duty, duty_date",
        "from duty_table",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="duty_name", property="dutyName", jdbcType=JdbcType.VARCHAR),
        @Result(column="duty_mis", property="dutyMis", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_on_duty", property="isOnDuty", jdbcType=JdbcType.BIT),
        @Result(column="duty_date", property="dutyDate", jdbcType=JdbcType.VARCHAR)
    })
    DutyTablePO selectByPrimaryKey(Integer id);

    @UpdateProvider(type=DutyTablePOSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") DutyTablePO record, @Param("example") DutyTablePOExample example);

    @UpdateProvider(type=DutyTablePOSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") DutyTablePO record, @Param("example") DutyTablePOExample example);

    @UpdateProvider(type=DutyTablePOSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(DutyTablePO record);

    @Update({
        "update duty_table",
        "set duty_name = #{dutyName,jdbcType=VARCHAR},",
          "duty_mis = #{dutyMis,jdbcType=VARCHAR},",
          "is_on_duty = #{isOnDuty,jdbcType=BIT},",
          "duty_date = #{dutyDate,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(DutyTablePO record);


    @Select({
            "select",
            "id, duty_name, duty_mis, is_on_duty, duty_date",
            "from duty_table",
            "where is_on_duty=0",
            "order by id limit  #{count,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="duty_name", property="dutyName", jdbcType=JdbcType.VARCHAR),
            @Result(column="duty_mis", property="dutyMis", jdbcType=JdbcType.VARCHAR),
            @Result(column="is_on_duty", property="isOnDuty", jdbcType=JdbcType.BIT),
            @Result(column="duty_date", property="dutyDate", jdbcType=JdbcType.VARCHAR)
    })
    List<DutyTablePO> selectDutyPerson(Integer count);


    @Update({
            "update duty_table",
            "set is_on_duty = 1,",
            "duty_date = #{dutyDate}",
            "where duty_mis = #{dutyMis}",
            "and is_on_duty=0"
    })
    @Results({
            @Result(column="duty_mis", property="dutyMis", jdbcType=JdbcType.VARCHAR),
            @Result(column="duty_date", property="dutyDate", jdbcType=JdbcType.VARCHAR)
    })
    int updateByMis(@Param("dutyDate")String dutyDate,@Param("dutyMis") String dutyMis);


    @Update({
            "update duty_table",
            "set duty_name = #{dutyName,jdbcType=VARCHAR},",
            "duty_mis = #{dutyMis,jdbcType=VARCHAR},",
            "is_on_duty = #{isOnDuty,jdbcType=BIT},",
            "duty_date = #{dutyDate,jdbcType=VARCHAR}",
            "where duty_mis = #{mis,jdbcType=VARCHAR}",
            "and is_on_duty=0"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="duty_name", property="dutyName", jdbcType=JdbcType.VARCHAR),
            @Result(column="duty_mis", property="dutyMis", jdbcType=JdbcType.VARCHAR),
            @Result(column="is_on_duty", property="isOnDuty", jdbcType=JdbcType.BIT),
            @Result(column="duty_date", property="dutyDate", jdbcType=JdbcType.VARCHAR)
    })
    int updateByMis2(DutyTablePO record,String mis);
}