package com.meituan.food.mapper;

import com.meituan.food.po.CoeAtpPO;
import com.meituan.food.po.CoeAtpPOExample;
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

public interface CoeAtpPOMapper {
    @SelectProvider(type=CoeAtpPOSqlProvider.class, method="countByExample")
    long countByExample(CoeAtpPOExample example);

    @DeleteProvider(type=CoeAtpPOSqlProvider.class, method="deleteByExample")
    int deleteByExample(CoeAtpPOExample example);

    @Delete({
        "delete from coe_atp_status_table",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into coe_atp_status_table (id, coe_id, ",
        "is_push)",
        "values (#{id,jdbcType=INTEGER}, #{coeId,jdbcType=INTEGER}, ",
        "#{isPush,jdbcType=BIT})"
    })
    int insert(CoeAtpPO record);

    @InsertProvider(type=CoeAtpPOSqlProvider.class, method="insertSelective")
    int insertSelective(CoeAtpPO record);

    @SelectProvider(type=CoeAtpPOSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="coe_id", property="coeId", jdbcType=JdbcType.INTEGER),
        @Result(column="is_push", property="isPush", jdbcType=JdbcType.BIT)
    })
    List<CoeAtpPO> selectByExample(CoeAtpPOExample example);

    @Select({
        "select",
        "id, coe_id, is_push",
        "from coe_atp_status_table",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="coe_id", property="coeId", jdbcType=JdbcType.INTEGER),
        @Result(column="is_push", property="isPush", jdbcType=JdbcType.BIT)
    })
    CoeAtpPO selectByPrimaryKey(Integer id);

    @Select({
            "select coe_id from coe_atp_status_table where is_push=true"
    })
    @Results({
            @Result(column="coe_id", property="coeId", jdbcType=JdbcType.INTEGER)
    })
    List<Integer> selectAllCoeList();

    @UpdateProvider(type=CoeAtpPOSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") CoeAtpPO record, @Param("example") CoeAtpPOExample example);

    @UpdateProvider(type=CoeAtpPOSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") CoeAtpPO record, @Param("example") CoeAtpPOExample example);

    @UpdateProvider(type=CoeAtpPOSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(CoeAtpPO record);

    @Update({
        "update coe_atp_status_table",
        "set coe_id = #{coeId,jdbcType=INTEGER},",
          "is_push = #{isPush,jdbcType=BIT}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(CoeAtpPO record);
}