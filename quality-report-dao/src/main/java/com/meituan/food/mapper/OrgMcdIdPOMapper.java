package com.meituan.food.mapper;

import com.meituan.food.po.OrgMcdIdPO;
import com.meituan.food.po.OrgMcdIdPOExample;
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

public interface OrgMcdIdPOMapper {
    @SelectProvider(type=OrgMcdIdPOSqlProvider.class, method="countByExample")
    long countByExample(OrgMcdIdPOExample example);

    @DeleteProvider(type=OrgMcdIdPOSqlProvider.class, method="deleteByExample")
    int deleteByExample(OrgMcdIdPOExample example);

    @Delete({
        "delete from org_mcd_id",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into org_mcd_id (id, org_id, ",
        "org_name, mcd_id, ",
        "mcd_name, child_mcd_id)",
        "values (#{id,jdbcType=INTEGER}, #{orgId,jdbcType=INTEGER}, ",
        "#{orgName,jdbcType=VARCHAR}, #{mcdId,jdbcType=VARCHAR}, ",
        "#{mcdName,jdbcType=INTEGER}, #{childMcdId,jdbcType=VARCHAR})"
    })
    int insert(OrgMcdIdPO record);

    @InsertProvider(type=OrgMcdIdPOSqlProvider.class, method="insertSelective")
    int insertSelective(OrgMcdIdPO record);

    @SelectProvider(type=OrgMcdIdPOSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="org_id", property="orgId", jdbcType=JdbcType.INTEGER),
        @Result(column="org_name", property="orgName", jdbcType=JdbcType.VARCHAR),
        @Result(column="mcd_id", property="mcdId", jdbcType=JdbcType.VARCHAR),
        @Result(column="mcd_name", property="mcdName", jdbcType=JdbcType.INTEGER),
        @Result(column="child_mcd_id", property="childMcdId", jdbcType=JdbcType.VARCHAR)
    })
    List<OrgMcdIdPO> selectByExample(OrgMcdIdPOExample example);

    @Select({
        "select",
        "id, org_id, org_name, mcd_id, mcd_name, child_mcd_id",
        "from org_mcd_id",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="org_id", property="orgId", jdbcType=JdbcType.INTEGER),
        @Result(column="org_name", property="orgName", jdbcType=JdbcType.VARCHAR),
        @Result(column="mcd_id", property="mcdId", jdbcType=JdbcType.VARCHAR),
        @Result(column="mcd_name", property="mcdName", jdbcType=JdbcType.INTEGER),
        @Result(column="child_mcd_id", property="childMcdId", jdbcType=JdbcType.VARCHAR)
    })
    OrgMcdIdPO selectByPrimaryKey(Integer id);

    @UpdateProvider(type=OrgMcdIdPOSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") OrgMcdIdPO record, @Param("example") OrgMcdIdPOExample example);

    @UpdateProvider(type=OrgMcdIdPOSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") OrgMcdIdPO record, @Param("example") OrgMcdIdPOExample example);

    @UpdateProvider(type=OrgMcdIdPOSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(OrgMcdIdPO record);

    @Update({
        "update org_mcd_id",
        "set org_id = #{orgId,jdbcType=INTEGER},",
          "org_name = #{orgName,jdbcType=VARCHAR},",
          "mcd_id = #{mcdId,jdbcType=VARCHAR},",
          "mcd_name = #{mcdName,jdbcType=INTEGER},",
          "child_mcd_id = #{childMcdId,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(OrgMcdIdPO record);
}