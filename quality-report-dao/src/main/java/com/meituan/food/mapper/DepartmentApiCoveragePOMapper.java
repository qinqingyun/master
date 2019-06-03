package com.meituan.food.mapper;

import com.meituan.food.po.DepartmentApiCoveragePO;
import com.meituan.food.po.DepartmentApiCoveragePOExample;
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

public interface DepartmentApiCoveragePOMapper {
    @SelectProvider(type=DepartmentApiCoveragePOSqlProvider.class, method="countByExample")
    long countByExample(DepartmentApiCoveragePOExample example);

    @DeleteProvider(type=DepartmentApiCoveragePOSqlProvider.class, method="deleteByExample")
    int deleteByExample(DepartmentApiCoveragePOExample example);

    @Delete({
        "delete from department_api_coverage",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into department_api_coverage (id, department_id, ",
        "department_name, all_api_num, ",
        "cover_api_num, api_coverage, ",
        "all_core_api_num, cover_core_api_num, ",
        "core_api_coverage, coverage_date, ",
        "status)",
        "values (#{id,jdbcType=INTEGER}, #{departmentId,jdbcType=INTEGER}, ",
        "#{departmentName,jdbcType=VARCHAR}, #{allApiNum,jdbcType=INTEGER}, ",
        "#{coverApiNum,jdbcType=INTEGER}, #{apiCoverage,jdbcType=DECIMAL}, ",
        "#{allCoreApiNum,jdbcType=INTEGER}, #{coverCoreApiNum,jdbcType=INTEGER}, ",
        "#{coreApiCoverage,jdbcType=DECIMAL}, #{coverageDate,jdbcType=DATE}, ",
        "#{status,jdbcType=INTEGER})"
    })
    int insert(DepartmentApiCoveragePO record);

    @InsertProvider(type=DepartmentApiCoveragePOSqlProvider.class, method="insertSelective")
    int insertSelective(DepartmentApiCoveragePO record);

    @SelectProvider(type=DepartmentApiCoveragePOSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="department_id", property="departmentId", jdbcType=JdbcType.INTEGER),
        @Result(column="department_name", property="departmentName", jdbcType=JdbcType.VARCHAR),
        @Result(column="all_api_num", property="allApiNum", jdbcType=JdbcType.INTEGER),
        @Result(column="cover_api_num", property="coverApiNum", jdbcType=JdbcType.INTEGER),
        @Result(column="api_coverage", property="apiCoverage", jdbcType=JdbcType.DECIMAL),
        @Result(column="all_core_api_num", property="allCoreApiNum", jdbcType=JdbcType.INTEGER),
        @Result(column="cover_core_api_num", property="coverCoreApiNum", jdbcType=JdbcType.INTEGER),
        @Result(column="core_api_coverage", property="coreApiCoverage", jdbcType=JdbcType.DECIMAL),
        @Result(column="coverage_date", property="coverageDate", jdbcType=JdbcType.DATE),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER)
    })
    List<DepartmentApiCoveragePO> selectByExample(DepartmentApiCoveragePOExample example);

    @Select({
        "select",
        "id, department_id, department_name, all_api_num, cover_api_num, api_coverage, ",
        "all_core_api_num, cover_core_api_num, core_api_coverage, coverage_date, status",
        "from department_api_coverage",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="department_id", property="departmentId", jdbcType=JdbcType.INTEGER),
        @Result(column="department_name", property="departmentName", jdbcType=JdbcType.VARCHAR),
        @Result(column="all_api_num", property="allApiNum", jdbcType=JdbcType.INTEGER),
        @Result(column="cover_api_num", property="coverApiNum", jdbcType=JdbcType.INTEGER),
        @Result(column="api_coverage", property="apiCoverage", jdbcType=JdbcType.DECIMAL),
        @Result(column="all_core_api_num", property="allCoreApiNum", jdbcType=JdbcType.INTEGER),
        @Result(column="cover_core_api_num", property="coverCoreApiNum", jdbcType=JdbcType.INTEGER),
        @Result(column="core_api_coverage", property="coreApiCoverage", jdbcType=JdbcType.DECIMAL),
        @Result(column="coverage_date", property="coverageDate", jdbcType=JdbcType.DATE),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER)
    })
    DepartmentApiCoveragePO selectByPrimaryKey(Integer id);

    @UpdateProvider(type=DepartmentApiCoveragePOSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") DepartmentApiCoveragePO record, @Param("example") DepartmentApiCoveragePOExample example);

    @UpdateProvider(type=DepartmentApiCoveragePOSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") DepartmentApiCoveragePO record, @Param("example") DepartmentApiCoveragePOExample example);

    @UpdateProvider(type=DepartmentApiCoveragePOSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(DepartmentApiCoveragePO record);

    @Update({
        "update department_api_coverage",
        "set department_id = #{departmentId,jdbcType=INTEGER},",
          "department_name = #{departmentName,jdbcType=VARCHAR},",
          "all_api_num = #{allApiNum,jdbcType=INTEGER},",
          "cover_api_num = #{coverApiNum,jdbcType=INTEGER},",
          "api_coverage = #{apiCoverage,jdbcType=DECIMAL},",
          "all_core_api_num = #{allCoreApiNum,jdbcType=INTEGER},",
          "cover_core_api_num = #{coverCoreApiNum,jdbcType=INTEGER},",
          "core_api_coverage = #{coreApiCoverage,jdbcType=DECIMAL},",
          "coverage_date = #{coverageDate,jdbcType=DATE},",
          "status = #{status,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(DepartmentApiCoveragePO record);
}