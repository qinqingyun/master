package com.meituan.food.mapper;

import com.meituan.food.po.McdInfoPO;
import com.meituan.food.po.McdInfoPOExample;
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

public interface McdInfoPOMapper {
    @SelectProvider(type=McdInfoPOSqlProvider.class, method="countByExample")
    long countByExample(McdInfoPOExample example);

    @DeleteProvider(type=McdInfoPOSqlProvider.class, method="deleteByExample")
    int deleteByExample(McdInfoPOExample example);

    @Delete({
        "delete from mcd_info_table",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into mcd_info_table (id, mcd_id, ",
        "mcd_name, mcd_father_id)",
        "values (#{id,jdbcType=INTEGER}, #{mcdId,jdbcType=INTEGER}, ",
        "#{mcdName,jdbcType=VARCHAR}, #{mcdFatherId,jdbcType=INTEGER})"
    })
    int insert(McdInfoPO record);

    @InsertProvider(type=McdInfoPOSqlProvider.class, method="insertSelective")
    int insertSelective(McdInfoPO record);

    @SelectProvider(type=McdInfoPOSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="mcd_id", property="mcdId", jdbcType=JdbcType.INTEGER),
        @Result(column="mcd_name", property="mcdName", jdbcType=JdbcType.VARCHAR),
        @Result(column="mcd_father_id", property="mcdFatherId", jdbcType=JdbcType.INTEGER)
    })
    List<McdInfoPO> selectByExample(McdInfoPOExample example);

    @Select({
        "select",
        "id, mcd_id, mcd_name, mcd_father_id",
        "from mcd_info_table",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="mcd_id", property="mcdId", jdbcType=JdbcType.INTEGER),
        @Result(column="mcd_name", property="mcdName", jdbcType=JdbcType.VARCHAR),
        @Result(column="mcd_father_id", property="mcdFatherId", jdbcType=JdbcType.INTEGER)
    })
    McdInfoPO selectByPrimaryKey(Integer id);

    @UpdateProvider(type=McdInfoPOSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") McdInfoPO record, @Param("example") McdInfoPOExample example);

    @UpdateProvider(type=McdInfoPOSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") McdInfoPO record, @Param("example") McdInfoPOExample example);

    @UpdateProvider(type=McdInfoPOSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(McdInfoPO record);

    @Update({
        "update mcd_info_table",
        "set mcd_id = #{mcdId,jdbcType=INTEGER},",
          "mcd_name = #{mcdName,jdbcType=VARCHAR},",
          "mcd_father_id = #{mcdFatherId,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(McdInfoPO record);
}