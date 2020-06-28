package com.meituan.food.mapper;

import com.meituan.food.po.OrgDaxiangPO;
import com.meituan.food.po.OrgDaxiangPOExample;
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

public interface OrgDaxiangPOMapper {
    @SelectProvider(type=OrgDaxiangPOSqlProvider.class, method="countByExample")
    long countByExample(OrgDaxiangPOExample example);

    @DeleteProvider(type=OrgDaxiangPOSqlProvider.class, method="deleteByExample")
    int deleteByExample(OrgDaxiangPOExample example);

    @Delete({
        "delete from org_daxiang_table",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into org_daxiang_table (id, daxiang_id, ",
        "org_id)",
        "values (#{id,jdbcType=INTEGER}, #{daxiangId,jdbcType=BIGINT}, ",
        "#{orgId,jdbcType=INTEGER})"
    })
    int insert(OrgDaxiangPO record);

    @InsertProvider(type=OrgDaxiangPOSqlProvider.class, method="insertSelective")
    int insertSelective(OrgDaxiangPO record);

    @SelectProvider(type=OrgDaxiangPOSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="daxiang_id", property="daxiangId", jdbcType=JdbcType.BIGINT),
        @Result(column="org_id", property="orgId", jdbcType=JdbcType.INTEGER)
    })
    List<OrgDaxiangPO> selectByExample(OrgDaxiangPOExample example);

    @Select({
        "select",
        "id, daxiang_id, org_id",
        "from org_daxiang_table",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="daxiang_id", property="daxiangId", jdbcType=JdbcType.BIGINT),
        @Result(column="org_id", property="orgId", jdbcType=JdbcType.INTEGER)
    })
    OrgDaxiangPO selectByPrimaryKey(Integer id);

    @Select({
            "select daxiang_id",
            "from org_daxiang_table",
            "where org_id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="daxiang_id", property="daxiangId", jdbcType=JdbcType.BIGINT)
    })
    List<Long> selectByOrgId(@Param("id") int id);

    @UpdateProvider(type=OrgDaxiangPOSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") OrgDaxiangPO record, @Param("example") OrgDaxiangPOExample example);

    @UpdateProvider(type=OrgDaxiangPOSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") OrgDaxiangPO record, @Param("example") OrgDaxiangPOExample example);

    @UpdateProvider(type=OrgDaxiangPOSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(OrgDaxiangPO record);

    @Update({
        "update org_daxiang_table",
        "set daxiang_id = #{daxiangId,jdbcType=BIGINT},",
          "org_id = #{orgId,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(OrgDaxiangPO record);
}