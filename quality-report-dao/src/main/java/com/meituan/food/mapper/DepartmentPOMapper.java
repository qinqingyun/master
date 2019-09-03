package com.meituan.food.mapper;

import com.meituan.food.po.DepartmentPO;
import com.meituan.food.po.DepartmentPOExample;
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

public interface DepartmentPOMapper {
    @SelectProvider(type=DepartmentPOSqlProvider.class, method="countByExample")
    long countByExample(DepartmentPOExample example);

    @DeleteProvider(type=DepartmentPOSqlProvider.class, method="deleteByExample")
    int deleteByExample(DepartmentPOExample example);

    @Delete({
        "delete from department",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into department (id, department, ",
        "department2)",
        "values (#{id,jdbcType=INTEGER}, #{department,jdbcType=VARCHAR}, ",
        "#{department2,jdbcType=VARCHAR})"
    })
    int insert(DepartmentPO record);

    @InsertProvider(type=DepartmentPOSqlProvider.class, method="insertSelective")
    int insertSelective(DepartmentPO record);

    @SelectProvider(type=DepartmentPOSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="department", property="department", jdbcType=JdbcType.VARCHAR),
        @Result(column="department2", property="department2", jdbcType=JdbcType.VARCHAR)
    })
    List<DepartmentPO> selectByExample(DepartmentPOExample example);

    @Select({
        "select",
        "id, department, department2",
        "from department",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="department", property="department", jdbcType=JdbcType.VARCHAR),
        @Result(column="department2", property="department2", jdbcType=JdbcType.VARCHAR)
    })
    DepartmentPO selectByPrimaryKey(Integer id);

    @UpdateProvider(type=DepartmentPOSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") DepartmentPO record, @Param("example") DepartmentPOExample example);

    @UpdateProvider(type=DepartmentPOSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") DepartmentPO record, @Param("example") DepartmentPOExample example);

    @UpdateProvider(type=DepartmentPOSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(DepartmentPO record);

    @Update({
        "update department",
        "set department = #{department,jdbcType=VARCHAR},",
          "department2 = #{department2,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(DepartmentPO record);

    @Select({
            "select",
            "id",
            "from department",
            "where department2 = #{department2,jdbcType=VARCHAR}"
    })
    Integer selectByDepartment(String department2);
}