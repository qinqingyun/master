package com.meituan.food.mapper;

import com.meituan.food.po.AppkeyAdminPO;
import com.meituan.food.po.AppkeyAdminPOExample;

import java.util.Date;
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

public interface AppkeyAdminPOMapper {
    @SelectProvider(type=AppkeyAdminPOSqlProvider.class, method="countByExample")
    long countByExample(AppkeyAdminPOExample example);

    @DeleteProvider(type=AppkeyAdminPOSqlProvider.class, method="deleteByExample")
    int deleteByExample(AppkeyAdminPOExample example);

    @Delete({
        "delete from appkey_admin",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into appkey_admin (id, appkey_id, ",
        "appkey, admin_name, ",
        "admin_id, creator_id, ",
        "created_time, updated_time)",
        "values (#{id,jdbcType=INTEGER}, #{appkeyId,jdbcType=INTEGER}, ",
        "#{appkey,jdbcType=VARCHAR}, #{adminName,jdbcType=VARCHAR}, ",
        "#{adminId,jdbcType=INTEGER}, #{creatorId,jdbcType=INTEGER}, ",
        "#{createdTime,jdbcType=TIMESTAMP}, #{updatedTime,jdbcType=TIMESTAMP})"
    })
    int insert(AppkeyAdminPO record);

    @InsertProvider(type=AppkeyAdminPOSqlProvider.class, method="insertSelective")
    int insertSelective(AppkeyAdminPO record);

    @SelectProvider(type=AppkeyAdminPOSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="appkey_id", property="appkeyId", jdbcType=JdbcType.INTEGER),
        @Result(column="appkey", property="appkey", jdbcType=JdbcType.VARCHAR),
        @Result(column="admin_name", property="adminName", jdbcType=JdbcType.VARCHAR),
        @Result(column="admin_id", property="adminId", jdbcType=JdbcType.INTEGER),
        @Result(column="creator_id", property="creatorId", jdbcType=JdbcType.INTEGER),
        @Result(column="created_time", property="createdTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="updated_time", property="updatedTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<AppkeyAdminPO> selectByExample(AppkeyAdminPOExample example);

    @Select({
        "select",
        "id, appkey_id, appkey, admin_name, admin_id, creator_id, created_time, updated_time",
        "from appkey_admin",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="appkey_id", property="appkeyId", jdbcType=JdbcType.INTEGER),
        @Result(column="appkey", property="appkey", jdbcType=JdbcType.VARCHAR),
        @Result(column="admin_name", property="adminName", jdbcType=JdbcType.VARCHAR),
        @Result(column="admin_id", property="adminId", jdbcType=JdbcType.INTEGER),
        @Result(column="creator_id", property="creatorId", jdbcType=JdbcType.INTEGER),
        @Result(column="created_time", property="createdTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="updated_time", property="updatedTime", jdbcType=JdbcType.TIMESTAMP)
    })
    AppkeyAdminPO selectByPrimaryKey(Integer id);

    @Select({
            "select appkey from appkey_admin",
            "where admin_name = #{mis,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="appkey", property="appkey", jdbcType=JdbcType.VARCHAR),
            @Result(column="admin_name", property="adminName", jdbcType=JdbcType.VARCHAR)
    })
    List<String> selectByMis(@Param("mis") String mis);

    @Select({
            "select * from appkey_admin",
            "where appkey = #{appkey,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="appkey_id", property="appkeyId", jdbcType=JdbcType.INTEGER),
            @Result(column="appkey", property="appkey", jdbcType=JdbcType.VARCHAR),
            @Result(column="admin_name", property="adminName", jdbcType=JdbcType.VARCHAR),
            @Result(column="admin_id", property="adminId", jdbcType=JdbcType.INTEGER),
            @Result(column="creator_id", property="creatorId", jdbcType=JdbcType.INTEGER),
            @Result(column="created_time", property="createdTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="updated_time", property="updatedTime", jdbcType=JdbcType.TIMESTAMP)
    })
    AppkeyAdminPO selectByAppkey(@Param("appkey") String appkey);

    @UpdateProvider(type=AppkeyAdminPOSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") AppkeyAdminPO record, @Param("example") AppkeyAdminPOExample example);

    @UpdateProvider(type=AppkeyAdminPOSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") AppkeyAdminPO record, @Param("example") AppkeyAdminPOExample example);

    @UpdateProvider(type=AppkeyAdminPOSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(AppkeyAdminPO record);

    @Update({
        "update appkey_admin",
        "set appkey_id = #{appkeyId,jdbcType=INTEGER},",
          "appkey = #{appkey,jdbcType=VARCHAR},",
          "admin_name = #{adminName,jdbcType=VARCHAR},",
          "admin_id = #{adminId,jdbcType=INTEGER},",
          "creator_id = #{creatorId,jdbcType=INTEGER},",
          "created_time = #{createdTime,jdbcType=TIMESTAMP},",
          "updated_time = #{updatedTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(AppkeyAdminPO record);

    @Update({
            "update appkey_admin",
            "set admin_name = #{adminName,jdbcType=VARCHAR},",
            "updated_time = #{updatedTime,jdbcType=TIMESTAMP}",
            "where appkey = #{appkey,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="appkey_id", property="appkeyId", jdbcType=JdbcType.INTEGER),
            @Result(column="appkey", property="appkey", jdbcType=JdbcType.VARCHAR),
            @Result(column="admin_name", property="adminName", jdbcType=JdbcType.VARCHAR),
            @Result(column="admin_id", property="adminId", jdbcType=JdbcType.INTEGER),
            @Result(column="creator_id", property="creatorId", jdbcType=JdbcType.INTEGER),
            @Result(column="created_time", property="createdTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="updated_time", property="updatedTime", jdbcType=JdbcType.TIMESTAMP)
    })
    int updateByAppkey(@Param("appkey") String appkey, @Param("adminName") String adminName ,@Param("updatedTime") Date updatedTime);
}