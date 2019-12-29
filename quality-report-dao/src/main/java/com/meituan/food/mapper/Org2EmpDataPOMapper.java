package com.meituan.food.mapper;

import com.meituan.food.po.Org2EmpDataPO;
import com.meituan.food.po.Org2EmpDataPOExample;
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
import org.codehaus.groovy.tools.shell.completion.VariableSyntaxCompletor;

public interface Org2EmpDataPOMapper {
    @SelectProvider(type=Org2EmpDataPOSqlProvider.class, method="countByExample")
    long countByExample(Org2EmpDataPOExample example);

    @DeleteProvider(type=Org2EmpDataPOSqlProvider.class, method="deleteByExample")
    int deleteByExample(Org2EmpDataPOExample example);

    @Delete({
        "delete from org2_emp_data",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into org2_emp_data (id, empId, ",
        "name, mis, reportEmpId, ",
        "reportEmpName, orgId, ",
        "orgName, virtualreportEmpId, ",
        "virtualreportEmpName, comment, ",
        "updated_date,virtualOrgName)",
        "values (#{id,jdbcType=INTEGER}, #{empid,jdbcType=VARCHAR}, ",
        "#{name,jdbcType=VARCHAR}, #{mis,jdbcType=VARCHAR}, #{reportempid,jdbcType=VARCHAR}, ",
        "#{reportempname,jdbcType=VARCHAR}, #{orgid,jdbcType=VARCHAR}, ",
        "#{orgname,jdbcType=VARCHAR}, #{virtualreportempid,jdbcType=VARCHAR}, ",
        "#{virtualreportempname,jdbcType=VARCHAR}, #{comment,jdbcType=VARCHAR}, ",
        "#{updatedDate,jdbcType=VARCHAR},#{virtualOrgName,jdbcType=VARCHAR})"
    })
    int insert(Org2EmpDataPO record);

    @Delete({
            "delete from org2_emp_data"
    })
    int deleteTable();

    @InsertProvider(type=Org2EmpDataPOSqlProvider.class, method="insertSelective")
    int insertSelective(Org2EmpDataPO record);

    @SelectProvider(type=Org2EmpDataPOSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="empId", property="empid", jdbcType=JdbcType.VARCHAR),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="mis", property="mis", jdbcType=JdbcType.VARCHAR),
        @Result(column="reportEmpId", property="reportempid", jdbcType=JdbcType.VARCHAR),
        @Result(column="reportEmpName", property="reportempname", jdbcType=JdbcType.VARCHAR),
        @Result(column="orgId", property="orgid", jdbcType=JdbcType.VARCHAR),
        @Result(column="orgName", property="orgname", jdbcType=JdbcType.VARCHAR),
        @Result(column="virtualreportEmpId", property="virtualreportempid", jdbcType=JdbcType.VARCHAR),
        @Result(column="virtualreportEmpName", property="virtualreportempname", jdbcType=JdbcType.VARCHAR),
        @Result(column="comment", property="comment", jdbcType=JdbcType.VARCHAR),
        @Result(column="updated_date", property="updatedDate", jdbcType=JdbcType.VARCHAR)
    })
    List<Org2EmpDataPO> selectByExample(Org2EmpDataPOExample example);

    @Select({
        "select",
        "id, empId, name, mis, reportEmpId, reportEmpName, orgId, orgName, virtualreportEmpId, ",
        "virtualreportEmpName, comment, updated_date",
        "from org2_emp_data",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="empId", property="empid", jdbcType=JdbcType.VARCHAR),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="mis", property="mis", jdbcType=JdbcType.VARCHAR),
        @Result(column="reportEmpId", property="reportempid", jdbcType=JdbcType.VARCHAR),
        @Result(column="reportEmpName", property="reportempname", jdbcType=JdbcType.VARCHAR),
        @Result(column="orgId", property="orgid", jdbcType=JdbcType.VARCHAR),
        @Result(column="orgName", property="orgname", jdbcType=JdbcType.VARCHAR),
        @Result(column="virtualreportEmpId", property="virtualreportempid", jdbcType=JdbcType.VARCHAR),
        @Result(column="virtualreportEmpName", property="virtualreportempname", jdbcType=JdbcType.VARCHAR),
        @Result(column="comment", property="comment", jdbcType=JdbcType.VARCHAR),
        @Result(column="updated_date", property="updatedDate", jdbcType=JdbcType.VARCHAR)
    })
    Org2EmpDataPO selectByPrimaryKey(Integer id);


    @Select({
            "select",
            "id, empId, name, mis, reportEmpId, reportEmpName, orgId, orgName, virtualreportEmpId, ",
            "virtualreportEmpName, comment, updated_date",
            "from org2_emp_data",
            "where mis = #{mis,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="empId", property="empid", jdbcType=JdbcType.VARCHAR),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="mis", property="mis", jdbcType=JdbcType.VARCHAR),
            @Result(column="reportEmpId", property="reportempid", jdbcType=JdbcType.VARCHAR),
            @Result(column="reportEmpName", property="reportempname", jdbcType=JdbcType.VARCHAR),
            @Result(column="orgId", property="orgid", jdbcType=JdbcType.VARCHAR),
            @Result(column="orgName", property="orgname", jdbcType=JdbcType.VARCHAR),
            @Result(column="virtualreportEmpId", property="virtualreportempid", jdbcType=JdbcType.VARCHAR),
            @Result(column="virtualreportEmpName", property="virtualreportempname", jdbcType=JdbcType.VARCHAR),
            @Result(column="comment", property="comment", jdbcType=JdbcType.VARCHAR),
            @Result(column="updated_date", property="updatedDate", jdbcType=JdbcType.VARCHAR),
            @Result(column="virtualOrgName", property="virtualOrgName", jdbcType=JdbcType.VARCHAR)
    })
    Org2EmpDataPO selectByMis(String mis);

    @UpdateProvider(type=Org2EmpDataPOSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Org2EmpDataPO record, @Param("example") Org2EmpDataPOExample example);

    @UpdateProvider(type=Org2EmpDataPOSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Org2EmpDataPO record, @Param("example") Org2EmpDataPOExample example);

    @UpdateProvider(type=Org2EmpDataPOSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Org2EmpDataPO record);

    @Update({
        "update org2_emp_data",
        "set empId = #{empid,jdbcType=VARCHAR},",
          "name = #{name,jdbcType=VARCHAR},",
          "mis = #{mis,jdbcType=VARCHAR},",
          "reportEmpId = #{reportempid,jdbcType=VARCHAR},",
          "reportEmpName = #{reportempname,jdbcType=VARCHAR},",
          "orgId = #{orgid,jdbcType=VARCHAR},",
          "orgName = #{orgname,jdbcType=VARCHAR},",
          "virtualreportEmpId = #{virtualreportempid,jdbcType=VARCHAR},",
          "virtualreportEmpName = #{virtualreportempname,jdbcType=VARCHAR},",
          "comment = #{comment,jdbcType=VARCHAR},",
          "updated_date = #{updatedDate,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Org2EmpDataPO record);
}