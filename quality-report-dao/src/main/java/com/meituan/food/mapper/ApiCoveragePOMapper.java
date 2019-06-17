package com.meituan.food.mapper;

import com.meituan.food.po.ApiCoveragePO;
import com.meituan.food.po.ApiCoveragePOExample;

import java.util.Date;
import java.util.List;

import com.meituan.food.po.GroupCoverageVO;
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

public interface ApiCoveragePOMapper {
    @SelectProvider(type=ApiCoveragePOSqlProvider.class, method="countByExample")
    long countByExample(ApiCoveragePOExample example);

    @DeleteProvider(type=ApiCoveragePOSqlProvider.class, method="deleteByExample")
    int deleteByExample(ApiCoveragePOExample example);

    @Delete({
        "delete from api_coverage",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Delete({
            "delete from api_coverage",
            "where id >= #{first,jdbcType=INTEGER} ",
            "and id <= #{second,jdbcType=INTEGER} "
    })
    int deleteByTwoKey(@Param("first") Integer first,@Param("second") Integer second);

    @Insert({
        "insert into api_coverage (id, appkey, ",
        "department, all_api_num, ",
        "cover_api_num, api_coverage, ",
        "department_id, all_core_api_num, ",
        "cover_core_api_num, coverage_date, ",
        "created_time, core_api_coverage, ",
        "department_id_2, department2)",
        "values (#{id,jdbcType=INTEGER}, #{appkey,jdbcType=VARCHAR}, ",
        "#{department,jdbcType=VARCHAR}, #{allApiNum,jdbcType=INTEGER}, ",
        "#{coverApiNum,jdbcType=INTEGER}, #{apiCoverage,jdbcType=DECIMAL}, ",
        "#{departmentId,jdbcType=INTEGER}, #{allCoreApiNum,jdbcType=INTEGER}, ",
        "#{coverCoreApiNum,jdbcType=INTEGER}, #{coverageDate,jdbcType=DATE}, ",
        "#{createdTime,jdbcType=TIMESTAMP}, #{coreApiCoverage,jdbcType=DECIMAL}, ",
        "#{departmentId2,jdbcType=INTEGER}, #{department2,jdbcType=VARCHAR})"
    })
    int insert(ApiCoveragePO record);

    @InsertProvider(type=ApiCoveragePOSqlProvider.class, method="insertSelective")
    int insertSelective(ApiCoveragePO record);

    @SelectProvider(type=ApiCoveragePOSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="appkey", property="appkey", jdbcType=JdbcType.VARCHAR),
        @Result(column="department", property="department", jdbcType=JdbcType.VARCHAR),
        @Result(column="all_api_num", property="allApiNum", jdbcType=JdbcType.INTEGER),
        @Result(column="cover_api_num", property="coverApiNum", jdbcType=JdbcType.INTEGER),
        @Result(column="api_coverage", property="apiCoverage", jdbcType=JdbcType.DECIMAL),
        @Result(column="department_id", property="departmentId", jdbcType=JdbcType.INTEGER),
        @Result(column="all_core_api_num", property="allCoreApiNum", jdbcType=JdbcType.INTEGER),
        @Result(column="cover_core_api_num", property="coverCoreApiNum", jdbcType=JdbcType.INTEGER),
        @Result(column="coverage_date", property="coverageDate", jdbcType=JdbcType.DATE),
        @Result(column="created_time", property="createdTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="core_api_coverage", property="coreApiCoverage", jdbcType=JdbcType.DECIMAL),
        @Result(column="department_id_2", property="departmentId2", jdbcType=JdbcType.INTEGER),
        @Result(column="department2", property="department2", jdbcType=JdbcType.VARCHAR)
    })
    List<ApiCoveragePO> selectByExample(ApiCoveragePOExample example);

    @Select({
        "select",
        "id, appkey, department, all_api_num, cover_api_num, api_coverage, department_id, ",
        "all_core_api_num, cover_core_api_num, coverage_date, created_time, core_api_coverage, ",
        "department_id_2, department2",
        "from api_coverage",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="appkey", property="appkey", jdbcType=JdbcType.VARCHAR),
        @Result(column="department", property="department", jdbcType=JdbcType.VARCHAR),
        @Result(column="all_api_num", property="allApiNum", jdbcType=JdbcType.INTEGER),
        @Result(column="cover_api_num", property="coverApiNum", jdbcType=JdbcType.INTEGER),
        @Result(column="api_coverage", property="apiCoverage", jdbcType=JdbcType.DECIMAL),
        @Result(column="department_id", property="departmentId", jdbcType=JdbcType.INTEGER),
        @Result(column="all_core_api_num", property="allCoreApiNum", jdbcType=JdbcType.INTEGER),
        @Result(column="cover_core_api_num", property="coverCoreApiNum", jdbcType=JdbcType.INTEGER),
        @Result(column="coverage_date", property="coverageDate", jdbcType=JdbcType.DATE),
        @Result(column="created_time", property="createdTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="core_api_coverage", property="coreApiCoverage", jdbcType=JdbcType.DECIMAL),
        @Result(column="department_id_2", property="departmentId2", jdbcType=JdbcType.INTEGER),
        @Result(column="department2", property="department2", jdbcType=JdbcType.VARCHAR)
    })
    ApiCoveragePO selectByPrimaryKey(Integer id);



    @Select({
            "select SUM(all_api_num),SUM(cover_api_num),AVG(api_coverage),SUM(all_core_api_num),SUM(cover_core_api_num),AVG(core_api_coverage),department_id,department,department_id_2,department2" +
                    "    from api_coverage where  coverage_date= #{coverageDate,jdbcType=DATE}"+
                    "  group by department_id"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="appkey", property="appkey", jdbcType=JdbcType.VARCHAR),
            @Result(column="department", property="department", jdbcType=JdbcType.VARCHAR),
            @Result(column="all_api_num", property="allApiNum", jdbcType=JdbcType.INTEGER),
            @Result(column="cover_api_num", property="coverApiNum", jdbcType=JdbcType.INTEGER),
            @Result(column="api_coverage", property="apiCoverage", jdbcType=JdbcType.DECIMAL),
            @Result(column="department_id", property="departmentId", jdbcType=JdbcType.INTEGER),
            @Result(column="all_core_api_num", property="allCoreApiNum", jdbcType=JdbcType.INTEGER),
            @Result(column="cover_core_api_num", property="coverCoreApiNum", jdbcType=JdbcType.INTEGER),
            @Result(column="coverage_date", property="coverageDate", jdbcType=JdbcType.DATE),
            @Result(column="created_time", property="createdTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="core_api_coverage", property="coreApiCoverage", jdbcType=JdbcType.DECIMAL),
            @Result(column="department_id_2", property="departmentId2", jdbcType=JdbcType.INTEGER),
            @Result(column="department2", property="department2", jdbcType=JdbcType.VARCHAR)
    })
    List<GroupCoverageVO> selectByDate(Date coverageDate);


    @Select({
            "select SUM(all_api_num),SUM(cover_api_num),AVG(api_coverage),SUM(all_core_api_num),SUM(cover_core_api_num),AVG(core_api_coverage),department_id,department,department_id_2,department2" +
                    "    from api_coverage where  coverage_date= #{coverageDate,jdbcType=DATE}"+
                    "  group by department_id_2"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="appkey", property="appkey", jdbcType=JdbcType.VARCHAR),
            @Result(column="department", property="department", jdbcType=JdbcType.VARCHAR),
            @Result(column="all_api_num", property="allApiNum", jdbcType=JdbcType.INTEGER),
            @Result(column="cover_api_num", property="coverApiNum", jdbcType=JdbcType.INTEGER),
            @Result(column="api_coverage", property="apiCoverage", jdbcType=JdbcType.DECIMAL),
            @Result(column="department_id", property="departmentId", jdbcType=JdbcType.INTEGER),
            @Result(column="all_core_api_num", property="allCoreApiNum", jdbcType=JdbcType.INTEGER),
            @Result(column="cover_core_api_num", property="coverCoreApiNum", jdbcType=JdbcType.INTEGER),
            @Result(column="coverage_date", property="coverageDate", jdbcType=JdbcType.DATE),
            @Result(column="created_time", property="createdTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="core_api_coverage", property="coreApiCoverage", jdbcType=JdbcType.DECIMAL),
            @Result(column="department_id_2", property="departmentId2", jdbcType=JdbcType.INTEGER),
            @Result(column="department2", property="department2", jdbcType=JdbcType.VARCHAR)
    })
    List<GroupCoverageVO> selectByDateDepartment2(Date coverageDate);


    @Select({
            "select * from api_coverage where  coverage_date= #{coverageDate} "+
                    " and  department_id_2 =  #{id}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="appkey", property="appkey", jdbcType=JdbcType.VARCHAR),
            @Result(column="department", property="department", jdbcType=JdbcType.VARCHAR),
            @Result(column="all_api_num", property="allApiNum", jdbcType=JdbcType.INTEGER),
            @Result(column="cover_api_num", property="coverApiNum", jdbcType=JdbcType.INTEGER),
            @Result(column="api_coverage", property="apiCoverage", jdbcType=JdbcType.DECIMAL),
            @Result(column="department_id", property="departmentId", jdbcType=JdbcType.INTEGER),
            @Result(column="all_core_api_num", property="allCoreApiNum", jdbcType=JdbcType.INTEGER),
            @Result(column="cover_core_api_num", property="coverCoreApiNum", jdbcType=JdbcType.INTEGER),
            @Result(column="coverage_date", property="coverageDate", jdbcType=JdbcType.DATE),
            @Result(column="created_time", property="createdTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="core_api_coverage", property="coreApiCoverage", jdbcType=JdbcType.DECIMAL),
            @Result(column="department_id_2", property="departmentId2", jdbcType=JdbcType.INTEGER),
            @Result(column="department2", property="department2", jdbcType=JdbcType.VARCHAR)
    })
    List<ApiCoveragePO> selectByDateDepartment2id(@Param("coverageDate") Date coverageDate,@Param("id") Integer id);

    @Select({
            "select * from api_coverage where  coverage_date= #{coverageDate} "+
                    " and  department_id =  #{id}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="appkey", property="appkey", jdbcType=JdbcType.VARCHAR),
            @Result(column="department", property="department", jdbcType=JdbcType.VARCHAR),
            @Result(column="all_api_num", property="allApiNum", jdbcType=JdbcType.INTEGER),
            @Result(column="cover_api_num", property="coverApiNum", jdbcType=JdbcType.INTEGER),
            @Result(column="api_coverage", property="apiCoverage", jdbcType=JdbcType.DECIMAL),
            @Result(column="department_id", property="departmentId", jdbcType=JdbcType.INTEGER),
            @Result(column="all_core_api_num", property="allCoreApiNum", jdbcType=JdbcType.INTEGER),
            @Result(column="cover_core_api_num", property="coverCoreApiNum", jdbcType=JdbcType.INTEGER),
            @Result(column="coverage_date", property="coverageDate", jdbcType=JdbcType.DATE),
            @Result(column="created_time", property="createdTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="core_api_coverage", property="coreApiCoverage", jdbcType=JdbcType.DECIMAL),
            @Result(column="department_id_2", property="departmentId2", jdbcType=JdbcType.INTEGER),
            @Result(column="department2", property="department2", jdbcType=JdbcType.VARCHAR)
    })
    List<ApiCoveragePO> selectByDateDepartmentId(@Param("coverageDate") Date coverageDate,@Param("id") Integer id);


    @UpdateProvider(type=ApiCoveragePOSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") ApiCoveragePO record, @Param("example") ApiCoveragePOExample example);

    @UpdateProvider(type=ApiCoveragePOSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") ApiCoveragePO record, @Param("example") ApiCoveragePOExample example);

    @UpdateProvider(type=ApiCoveragePOSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(ApiCoveragePO record);

    @Update({
        "update api_coverage",
        "set appkey = #{appkey,jdbcType=VARCHAR},",
          "department = #{department,jdbcType=VARCHAR},",
          "all_api_num = #{allApiNum,jdbcType=INTEGER},",
          "cover_api_num = #{coverApiNum,jdbcType=INTEGER},",
          "api_coverage = #{apiCoverage,jdbcType=DECIMAL},",
          "department_id = #{departmentId,jdbcType=INTEGER},",
          "all_core_api_num = #{allCoreApiNum,jdbcType=INTEGER},",
          "cover_core_api_num = #{coverCoreApiNum,jdbcType=INTEGER},",
          "coverage_date = #{coverageDate,jdbcType=DATE},",
          "created_time = #{createdTime,jdbcType=TIMESTAMP},",
          "core_api_coverage = #{coreApiCoverage,jdbcType=DECIMAL},",
          "department_id_2 = #{departmentId2,jdbcType=INTEGER},",
          "department2 = #{department2,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(ApiCoveragePO record);
}