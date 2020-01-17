package com.meituan.food.mapper;

import com.meituan.food.po.DepartmentOrgMap;
import com.meituan.food.po.DepartmentOrgMapExample;
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

public interface DepartmentOrgMapMapper {
    @SelectProvider(type=DepartmentOrgMapSqlProvider.class, method="countByExample")
    long countByExample(DepartmentOrgMapExample example);

    @DeleteProvider(type=DepartmentOrgMapSqlProvider.class, method="deleteByExample")
    int deleteByExample(DepartmentOrgMapExample example);

    @Delete({
        "delete from department_org_map",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into department_org_map (id, orgId, ",
        "orgName, department_id_2, ",
        "department_name, department_name_2, ",
        "add_time, update_time)",
        "values (#{id,jdbcType=BIGINT}, #{orgid,jdbcType=INTEGER}, ",
        "#{orgname,jdbcType=VARCHAR}, #{departmentId2,jdbcType=INTEGER}, ",
        "#{departmentName,jdbcType=VARCHAR}, #{departmentName2,jdbcType=VARCHAR}, ",
        "#{addTime,jdbcType=TIMESTAMP}, #{updateTime,jdbcType=TIMESTAMP})"
    })
    int insert(DepartmentOrgMap record);

    @InsertProvider(type=DepartmentOrgMapSqlProvider.class, method="insertSelective")
    int insertSelective(DepartmentOrgMap record);

    @SelectProvider(type=DepartmentOrgMapSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="orgId", property="orgid", jdbcType=JdbcType.INTEGER),
        @Result(column="orgName", property="orgname", jdbcType=JdbcType.VARCHAR),
        @Result(column="department_id_2", property="departmentId2", jdbcType=JdbcType.INTEGER),
        @Result(column="department_name", property="departmentName", jdbcType=JdbcType.VARCHAR),
        @Result(column="department_name_2", property="departmentName2", jdbcType=JdbcType.VARCHAR),
        @Result(column="add_time", property="addTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<DepartmentOrgMap> selectByExample(DepartmentOrgMapExample example);

    @Select({
        "select",
        "id, orgId, orgName, department_id_2, department_name, department_name_2, add_time, ",
        "update_time",
        "from department_org_map",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="orgId", property="orgid", jdbcType=JdbcType.INTEGER),
        @Result(column="orgName", property="orgname", jdbcType=JdbcType.VARCHAR),
        @Result(column="department_id_2", property="departmentId2", jdbcType=JdbcType.INTEGER),
        @Result(column="department_name", property="departmentName", jdbcType=JdbcType.VARCHAR),
        @Result(column="department_name_2", property="departmentName2", jdbcType=JdbcType.VARCHAR),
        @Result(column="add_time", property="addTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    DepartmentOrgMap selectByPrimaryKey(Long id);

    @UpdateProvider(type=DepartmentOrgMapSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") DepartmentOrgMap record, @Param("example") DepartmentOrgMapExample example);

    @UpdateProvider(type=DepartmentOrgMapSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") DepartmentOrgMap record, @Param("example") DepartmentOrgMapExample example);

    @UpdateProvider(type=DepartmentOrgMapSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(DepartmentOrgMap record);

    @Update({
        "update department_org_map",
        "set orgId = #{orgid,jdbcType=INTEGER},",
          "orgName = #{orgname,jdbcType=VARCHAR},",
          "department_id_2 = #{departmentId2,jdbcType=INTEGER},",
          "department_name = #{departmentName,jdbcType=VARCHAR},",
          "department_name_2 = #{departmentName2,jdbcType=VARCHAR},",
          "add_time = #{addTime,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(DepartmentOrgMap record);
}