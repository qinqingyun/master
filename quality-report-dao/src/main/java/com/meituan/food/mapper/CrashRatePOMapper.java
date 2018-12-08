package com.meituan.food.mapper;

import com.meituan.food.po.CrashRatePO;
import com.meituan.food.po.CrashRatePOExample;
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

public interface CrashRatePOMapper {
    @SelectProvider(type=CrashRatePOSqlProvider.class, method="countByExample")
    long countByExample(CrashRatePOExample example);

    @DeleteProvider(type=CrashRatePOSqlProvider.class, method="deleteByExample")
    int deleteByExample(CrashRatePOExample example);

    @Delete({
        "delete from crash_rate",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into crash_rate (id, crash, ",
        "plantform, DAU, ",
        "crash_rate, start_date, ",
        "end_date, show_date_range, ",
        "created_at, updated_at, ",
        "final_rate)",
        "values (#{id,jdbcType=INTEGER}, #{crash,jdbcType=INTEGER}, ",
        "#{plantform,jdbcType=VARCHAR}, #{dau,jdbcType=INTEGER}, ",
        "#{crashRate,jdbcType=VARCHAR}, #{startDate,jdbcType=VARCHAR}, ",
        "#{endDate,jdbcType=VARCHAR}, #{showDateRange,jdbcType=VARCHAR}, ",
        "#{createdAt,jdbcType=TIMESTAMP}, #{updatedAt,jdbcType=TIMESTAMP}, ",
        "#{finalRate,jdbcType=DECIMAL})"
    })
    int insert(CrashRatePO record);

    @InsertProvider(type=CrashRatePOSqlProvider.class, method="insertSelective")
    int insertSelective(CrashRatePO record);

    @SelectProvider(type=CrashRatePOSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="crash", property="crash", jdbcType=JdbcType.INTEGER),
        @Result(column="plantform", property="plantform", jdbcType=JdbcType.VARCHAR),
        @Result(column="DAU", property="dau", jdbcType=JdbcType.INTEGER),
        @Result(column="crash_rate", property="crashRate", jdbcType=JdbcType.VARCHAR),
        @Result(column="start_date", property="startDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="end_date", property="endDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="show_date_range", property="showDateRange", jdbcType=JdbcType.VARCHAR),
        @Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="updated_at", property="updatedAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="final_rate", property="finalRate", jdbcType=JdbcType.DECIMAL)
    })
    List<CrashRatePO> selectByExample(CrashRatePOExample example);

    @Select({
        "select",
        "id, crash, plantform, DAU, crash_rate, start_date, end_date, show_date_range, ",
        "created_at, updated_at, final_rate",
        "from crash_rate",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="crash", property="crash", jdbcType=JdbcType.INTEGER),
        @Result(column="plantform", property="plantform", jdbcType=JdbcType.VARCHAR),
        @Result(column="DAU", property="dau", jdbcType=JdbcType.INTEGER),
        @Result(column="crash_rate", property="crashRate", jdbcType=JdbcType.VARCHAR),
        @Result(column="start_date", property="startDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="end_date", property="endDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="show_date_range", property="showDateRange", jdbcType=JdbcType.VARCHAR),
        @Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="updated_at", property="updatedAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="final_rate", property="finalRate", jdbcType=JdbcType.DECIMAL)
    })
    CrashRatePO selectByPrimaryKey(Integer id);

    @UpdateProvider(type=CrashRatePOSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") CrashRatePO record, @Param("example") CrashRatePOExample example);

    @UpdateProvider(type=CrashRatePOSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") CrashRatePO record, @Param("example") CrashRatePOExample example);

    @UpdateProvider(type=CrashRatePOSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(CrashRatePO record);

    @Update({
        "update crash_rate",
        "set crash = #{crash,jdbcType=INTEGER},",
          "plantform = #{plantform,jdbcType=VARCHAR},",
          "DAU = #{dau,jdbcType=INTEGER},",
          "crash_rate = #{crashRate,jdbcType=VARCHAR},",
          "start_date = #{startDate,jdbcType=VARCHAR},",
          "end_date = #{endDate,jdbcType=VARCHAR},",
          "show_date_range = #{showDateRange,jdbcType=VARCHAR},",
          "created_at = #{createdAt,jdbcType=TIMESTAMP},",
          "updated_at = #{updatedAt,jdbcType=TIMESTAMP},",
          "final_rate = #{finalRate,jdbcType=DECIMAL}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(CrashRatePO record);


    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "crash", property = "crash", jdbcType = JdbcType.INTEGER),
            @Result(column = "plantform", property = "plantform", jdbcType = JdbcType.VARCHAR),
            @Result(column = "DAU", property = "dau", jdbcType = JdbcType.INTEGER),
            @Result(column = "crash_rate", property = "crashRate", jdbcType = JdbcType.VARCHAR),
            @Result(column = "start_date", property = "startDate", jdbcType = JdbcType.VARCHAR),
            @Result(column = "end_date", property = "endDate", jdbcType = JdbcType.VARCHAR),
            @Result(column = "show_date_range", property = "showDateRange", jdbcType = JdbcType.VARCHAR),
            @Result(column = "created_at", property = "createdAt", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "updated_at", property = "updatedAt", jdbcType = JdbcType.TIMESTAMP),
            @Result(column="final_rate", property="finalRate", jdbcType=JdbcType.DECIMAL)

    })
    List<CrashRatePO> getCrashListByOsAndDate(@Param("plantform") String plantform,
                                              @Param("start_date") String start_date,
                                              @Param("end_date") String end_date);


    @Insert({
            "<script>",
            "insert into crash_rate (id, crash, ",
            "plantform, DAU, ",
            "crash_rate, start_date, ",
            "end_date, show_date_range, ",
            "created_at, updated_at,",
            "final_rate)",
            "values ",
            "<foreach collection='list' item='item' index='index' separator=','>",
            "(#{item.id,jdbcType=INTEGER}, #{item.crash,jdbcType=INTEGER}, ",
            "#{item.plantform,jdbcType=VARCHAR}, #{item.dau,jdbcType=INTEGER}, ",
            "#{item.crashRate,jdbcType=VARCHAR}, #{item.startDate,jdbcType=VARCHAR}, ",
            "#{item.endDate,jdbcType=VARCHAR}, #{item.showDateRange,jdbcType=VARCHAR}, ",
            "#{item.createdAt,jdbcType=TIMESTAMP}, #{item.updatedAt,jdbcType=TIMESTAMP},",
            "#{finalRate,jdbcType=DECIMAL})",
            "</foreach>",
            "</script>"
    })
    int batchInsert(List<CrashRatePO> records);
}