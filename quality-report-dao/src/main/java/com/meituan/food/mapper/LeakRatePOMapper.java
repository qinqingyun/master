package com.meituan.food.mapper;

import com.meituan.food.po.LeakRatePO;
import com.meituan.food.po.LeakRatePOExample;
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

public interface LeakRatePOMapper {
    @SelectProvider(type=LeakRatePOSqlProvider.class, method="countByExample")
    long countByExample(LeakRatePOExample example);

    @DeleteProvider(type=LeakRatePOSqlProvider.class, method="deleteByExample")
    int deleteByExample(LeakRatePOExample example);

    @Delete({
        "delete from leak_rate",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into leak_rate (id, issue_num, ",
        "bug_num, leak_test_rate, ",
        "month, created_at, ",
        "updated_at)",
        "values (#{id,jdbcType=INTEGER}, #{issueNum,jdbcType=INTEGER}, ",
        "#{bugNum,jdbcType=INTEGER}, #{leakTestRate,jdbcType=DECIMAL}, ",
        "#{month,jdbcType=VARCHAR}, #{createdAt,jdbcType=TIMESTAMP}, ",
        "#{updatedAt,jdbcType=TIMESTAMP})"
    })
    int insert(LeakRatePO record);

    @InsertProvider(type=LeakRatePOSqlProvider.class, method="insertSelective")
    int insertSelective(LeakRatePO record);

    @SelectProvider(type=LeakRatePOSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="issue_num", property="issueNum", jdbcType=JdbcType.INTEGER),
        @Result(column="bug_num", property="bugNum", jdbcType=JdbcType.INTEGER),
        @Result(column="leak_test_rate", property="leakTestRate", jdbcType=JdbcType.DECIMAL),
        @Result(column="month", property="month", jdbcType=JdbcType.VARCHAR),
        @Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="updated_at", property="updatedAt", jdbcType=JdbcType.TIMESTAMP)
    })
    List<LeakRatePO> selectByExample(LeakRatePOExample example);

    @Select({
        "select",
        "id, issue_num, bug_num, leak_test_rate, month, created_at, updated_at",
        "from leak_rate",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="issue_num", property="issueNum", jdbcType=JdbcType.INTEGER),
        @Result(column="bug_num", property="bugNum", jdbcType=JdbcType.INTEGER),
        @Result(column="leak_test_rate", property="leakTestRate", jdbcType=JdbcType.DECIMAL),
        @Result(column="month", property="month", jdbcType=JdbcType.VARCHAR),
        @Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="updated_at", property="updatedAt", jdbcType=JdbcType.TIMESTAMP)
    })
    LeakRatePO selectByPrimaryKey(Integer id);

    @UpdateProvider(type=LeakRatePOSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") LeakRatePO record, @Param("example") LeakRatePOExample example);

    @UpdateProvider(type=LeakRatePOSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") LeakRatePO record, @Param("example") LeakRatePOExample example);

    @UpdateProvider(type=LeakRatePOSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(LeakRatePO record);

    @Update({
        "update leak_rate",
        "set issue_num = #{issueNum,jdbcType=INTEGER},",
          "bug_num = #{bugNum,jdbcType=INTEGER},",
          "leak_test_rate = #{leakTestRate,jdbcType=DECIMAL},",
          "month = #{month,jdbcType=VARCHAR},",
          "created_at = #{createdAt,jdbcType=TIMESTAMP},",
          "updated_at = #{updatedAt,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(LeakRatePO record);
}