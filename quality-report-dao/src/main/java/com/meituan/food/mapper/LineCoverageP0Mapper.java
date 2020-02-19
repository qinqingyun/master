package com.meituan.food.mapper;

import com.meituan.food.po.LineCoverageP0;
import com.meituan.food.po.LineCoverageP0Example;
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

public interface LineCoverageP0Mapper {
    @SelectProvider(type=LineCoverageP0SqlProvider.class, method="countByExample")
    long countByExample(LineCoverageP0Example example);

    @DeleteProvider(type=LineCoverageP0SqlProvider.class, method="deleteByExample")
    int deleteByExample(LineCoverageP0Example example);

    @Delete({
        "delete from line_coverage_table",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into line_coverage_table (id, srv, ",
        "release_name, core_line_c, ",
        "core_line_m, core_line_total, ",
        "core_line_coverage, core_line_c_interface, ",
        "total_line_c, total_line_m, ",
        "total_line_total, total_line_coverage, ",
        "total_line_c_interface, department_id, ",
        "department_name, department_id_2, ",
        "department_name_2, created_time, ",
        "update_time, error_message)",
        "values (#{id,jdbcType=INTEGER}, #{srv,jdbcType=VARCHAR}, ",
        "#{releaseName,jdbcType=VARCHAR}, #{coreLineC,jdbcType=INTEGER}, ",
        "#{coreLineM,jdbcType=INTEGER}, #{coreLineTotal,jdbcType=INTEGER}, ",
        "#{coreLineCoverage,jdbcType=DECIMAL}, #{coreLineCInterface,jdbcType=VARCHAR}, ",
        "#{totalLineC,jdbcType=INTEGER}, #{totalLineM,jdbcType=INTEGER}, ",
        "#{totalLineTotal,jdbcType=INTEGER}, #{totalLineCoverage,jdbcType=DECIMAL}, ",
        "#{totalLineCInterface,jdbcType=VARCHAR}, #{departmentId,jdbcType=INTEGER}, ",
        "#{departmentName,jdbcType=VARCHAR}, #{departmentId2,jdbcType=INTEGER}, ",
        "#{departmentName2,jdbcType=VARCHAR}, #{createdTime,jdbcType=TIMESTAMP}, ",
        "#{updateTime,jdbcType=TIMESTAMP}, #{errorMessage,jdbcType=VARCHAR})"
    })
    int insert(LineCoverageP0 record);

    @InsertProvider(type=LineCoverageP0SqlProvider.class, method="insertSelective")
    int insertSelective(LineCoverageP0 record);

    @SelectProvider(type=LineCoverageP0SqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="srv", property="srv", jdbcType=JdbcType.VARCHAR),
        @Result(column="release_name", property="releaseName", jdbcType=JdbcType.VARCHAR),
        @Result(column="core_line_c", property="coreLineC", jdbcType=JdbcType.INTEGER),
        @Result(column="core_line_m", property="coreLineM", jdbcType=JdbcType.INTEGER),
        @Result(column="core_line_total", property="coreLineTotal", jdbcType=JdbcType.INTEGER),
        @Result(column="core_line_coverage", property="coreLineCoverage", jdbcType=JdbcType.DECIMAL),
        @Result(column="core_line_c_interface", property="coreLineCInterface", jdbcType=JdbcType.VARCHAR),
        @Result(column="total_line_c", property="totalLineC", jdbcType=JdbcType.INTEGER),
        @Result(column="total_line_m", property="totalLineM", jdbcType=JdbcType.INTEGER),
        @Result(column="total_line_total", property="totalLineTotal", jdbcType=JdbcType.INTEGER),
        @Result(column="total_line_coverage", property="totalLineCoverage", jdbcType=JdbcType.DECIMAL),
        @Result(column="total_line_c_interface", property="totalLineCInterface", jdbcType=JdbcType.VARCHAR),
        @Result(column="department_id", property="departmentId", jdbcType=JdbcType.INTEGER),
        @Result(column="department_name", property="departmentName", jdbcType=JdbcType.VARCHAR),
        @Result(column="department_id_2", property="departmentId2", jdbcType=JdbcType.INTEGER),
        @Result(column="department_name_2", property="departmentName2", jdbcType=JdbcType.VARCHAR),
        @Result(column="created_time", property="createdTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="error_message", property="errorMessage", jdbcType=JdbcType.VARCHAR)
    })
    List<LineCoverageP0> selectByExample(LineCoverageP0Example example);

    @Select({
            "select",
            "id, srv, release_name, core_line_c, core_line_m, core_line_total, core_line_coverage, ",
            "core_line_c_interface, total_line_c, total_line_m, total_line_total, total_line_coverage, ",
            "total_line_c_interface, department_id, department_name, department_id_2, department_name_2, ",
            "created_time, update_time, error_message",
            "from line_coverage_table",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="srv", property="srv", jdbcType=JdbcType.VARCHAR),
            @Result(column="release_name", property="releaseName", jdbcType=JdbcType.VARCHAR),
            @Result(column="core_line_c", property="coreLineC", jdbcType=JdbcType.INTEGER),
            @Result(column="core_line_m", property="coreLineM", jdbcType=JdbcType.INTEGER),
            @Result(column="core_line_total", property="coreLineTotal", jdbcType=JdbcType.INTEGER),
            @Result(column="core_line_coverage", property="coreLineCoverage", jdbcType=JdbcType.DECIMAL),
            @Result(column="core_line_c_interface", property="coreLineCInterface", jdbcType=JdbcType.VARCHAR),
            @Result(column="total_line_c", property="totalLineC", jdbcType=JdbcType.INTEGER),
            @Result(column="total_line_m", property="totalLineM", jdbcType=JdbcType.INTEGER),
            @Result(column="total_line_total", property="totalLineTotal", jdbcType=JdbcType.INTEGER),
            @Result(column="total_line_coverage", property="totalLineCoverage", jdbcType=JdbcType.DECIMAL),
            @Result(column="total_line_c_interface", property="totalLineCInterface", jdbcType=JdbcType.VARCHAR),
            @Result(column="department_id", property="departmentId", jdbcType=JdbcType.INTEGER),
            @Result(column="department_name", property="departmentName", jdbcType=JdbcType.VARCHAR),
            @Result(column="department_id_2", property="departmentId2", jdbcType=JdbcType.INTEGER),
            @Result(column="department_name_2", property="departmentName2", jdbcType=JdbcType.VARCHAR),
            @Result(column="created_time", property="createdTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="error_message", property="errorMessage", jdbcType=JdbcType.VARCHAR)
    })
    LineCoverageP0 selectByPrimaryKey(Integer id);


    @Select({
            "select CONCAT(if(total_line_m=0,'-.-',core_line_coverage),'%') from line_coverage_table",
            "where srv = #{srv,jdbcType=VARCHAR} and created_time >= date_sub(curdate(),interval 1 day) ",
            " order by created_time desc limit 1"
    })
    String selectYesterdayCoverageBySrv(String srv);

    @Select({
            "select CONCAT(if(total_line_m=0,'-.-',core_line_coverage),'%') from line_coverage_table",
            "where srv = #{srv,jdbcType=VARCHAR} and created_time >= date_sub(curdate(),interval 8 day) and  created_time <= date_sub(curdate(),interval 7 day)",
            " order by created_time desc limit 1"
    })
    String selectLast7dayCoverageBySrv(String srv);

    @UpdateProvider(type=LineCoverageP0SqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") LineCoverageP0 record, @Param("example") LineCoverageP0Example example);

    @UpdateProvider(type=LineCoverageP0SqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") LineCoverageP0 record, @Param("example") LineCoverageP0Example example);

    @UpdateProvider(type=LineCoverageP0SqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(LineCoverageP0 record);

    @Update({
        "update line_coverage_table",
        "set srv = #{srv,jdbcType=VARCHAR},",
          "release_name = #{releaseName,jdbcType=VARCHAR},",
          "core_line_c = #{coreLineC,jdbcType=INTEGER},",
          "core_line_m = #{coreLineM,jdbcType=INTEGER},",
          "core_line_total = #{coreLineTotal,jdbcType=INTEGER},",
          "core_line_coverage = #{coreLineCoverage,jdbcType=DECIMAL},",
          "core_line_c_interface = #{coreLineCInterface,jdbcType=VARCHAR},",
          "total_line_c = #{totalLineC,jdbcType=INTEGER},",
          "total_line_m = #{totalLineM,jdbcType=INTEGER},",
          "total_line_total = #{totalLineTotal,jdbcType=INTEGER},",
          "total_line_coverage = #{totalLineCoverage,jdbcType=DECIMAL},",
          "total_line_c_interface = #{totalLineCInterface,jdbcType=VARCHAR},",
          "department_id = #{departmentId,jdbcType=INTEGER},",
          "department_name = #{departmentName,jdbcType=VARCHAR},",
          "department_id_2 = #{departmentId2,jdbcType=INTEGER},",
          "department_name_2 = #{departmentName2,jdbcType=VARCHAR},",
          "created_time = #{createdTime,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "error_message = #{errorMessage,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(LineCoverageP0 record);
}