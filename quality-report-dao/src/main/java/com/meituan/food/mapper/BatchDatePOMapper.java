package com.meituan.food.mapper;

import com.meituan.food.po.BatchDatePO;
import com.meituan.food.po.BatchDatePOExample;
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

public interface BatchDatePOMapper {
    @SelectProvider(type=BatchDatePOSqlProvider.class, method="countByExample")
    long countByExample(BatchDatePOExample example);

    @DeleteProvider(type=BatchDatePOSqlProvider.class, method="deleteByExample")
    int deleteByExample(BatchDatePOExample example);

    @Delete({
        "delete from batch_date",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into batch_date (id, coverage_date)",
        "values (#{id,jdbcType=INTEGER}, #{coverageDate,jdbcType=DATE})"
    })
    int insert(BatchDatePO record);

    @InsertProvider(type=BatchDatePOSqlProvider.class, method="insertSelective")
    int insertSelective(BatchDatePO record);

    @SelectProvider(type=BatchDatePOSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="coverage_date", property="coverageDate", jdbcType=JdbcType.DATE)
    })
    List<BatchDatePO> selectByExample(BatchDatePOExample example);

    @Select({
        "select",
        "id, coverage_date",
        "from batch_date",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="coverage_date", property="coverageDate", jdbcType=JdbcType.DATE)
    })
    BatchDatePO selectByPrimaryKey(Integer id);

    @UpdateProvider(type=BatchDatePOSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") BatchDatePO record, @Param("example") BatchDatePOExample example);

    @UpdateProvider(type=BatchDatePOSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") BatchDatePO record, @Param("example") BatchDatePOExample example);

    @UpdateProvider(type=BatchDatePOSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(BatchDatePO record);

    @Update({
        "update batch_date",
        "set coverage_date = #{coverageDate,jdbcType=DATE}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(BatchDatePO record);
}