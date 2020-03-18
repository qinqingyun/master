package com.meituan.food.mapper;

import com.meituan.food.po.ReleaseNamePO;
import com.meituan.food.po.ReleaseNamePOExample;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

public interface ReleaseNamePOMapper {
    @SelectProvider(type=ReleaseNamePOSqlProvider.class, method="countByExample")
    long countByExample(ReleaseNamePOExample example);

    @DeleteProvider(type=ReleaseNamePOSqlProvider.class, method="deleteByExample")
    int deleteByExample(ReleaseNamePOExample example);

    @Delete({
        "delete from release_name_table",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Delete({
            "delete from release_name_table",
            "where srv = #{srv,jdbcType=VARCHAR}"
    })
    int deleteBySrv(String srv);

    @Insert({
        "insert into release_name_table (id, srv, ",
        "release_name, created_time, ",
        "updated_time)",
        "values (#{id,jdbcType=INTEGER}, #{srv,jdbcType=VARCHAR}, ",
        "#{releaseName,jdbcType=VARCHAR}, #{createdTime,jdbcType=TIMESTAMP}, ",
        "#{updatedTime,jdbcType=TIMESTAMP})"
    })
    int insert(ReleaseNamePO record);

    @InsertProvider(type=ReleaseNamePOSqlProvider.class, method="insertSelective")
    int insertSelective(ReleaseNamePO record);

    @SelectProvider(type=ReleaseNamePOSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="srv", property="srv", jdbcType=JdbcType.VARCHAR),
        @Result(column="release_name", property="releaseName", jdbcType=JdbcType.VARCHAR),
        @Result(column="created_time", property="createdTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="updated_time", property="updatedTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<ReleaseNamePO> selectByExample(ReleaseNamePOExample example);

    @Select({
        "select",
        "id, srv, release_name, created_time, updated_time",
        "from release_name_table",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="srv", property="srv", jdbcType=JdbcType.VARCHAR),
        @Result(column="release_name", property="releaseName", jdbcType=JdbcType.VARCHAR),
        @Result(column="created_time", property="createdTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="updated_time", property="updatedTime", jdbcType=JdbcType.TIMESTAMP)
    })
    ReleaseNamePO selectByPrimaryKey(Integer id);

    @Select({
            "select  release_name from release_name_table",
            "where srv = #{srv,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="srv", property="srv", jdbcType=JdbcType.VARCHAR),
            @Result(column="release_name", property="releaseName", jdbcType=JdbcType.VARCHAR),
            @Result(column="created_time", property="createdTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="updated_time", property="updatedTime", jdbcType=JdbcType.TIMESTAMP)
    })
    String selectBySrv(@Param("srv") String srv);


    @Select({
            "select",
            "srv, release_name",
            "from release_name_table"
    })@Results({
            @Result(column="srv", property="srv", jdbcType=JdbcType.VARCHAR),
            @Result(column="release_name", property="releaseName", jdbcType=JdbcType.VARCHAR),
    })
    List<ReleaseNamePO> selectReleaseNameSrv();

    @UpdateProvider(type=ReleaseNamePOSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") ReleaseNamePO record, @Param("example") ReleaseNamePOExample example);

    @UpdateProvider(type=ReleaseNamePOSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") ReleaseNamePO record, @Param("example") ReleaseNamePOExample example);

    @UpdateProvider(type=ReleaseNamePOSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(ReleaseNamePO record);

    @Update({
        "update release_name_table",
        "set srv = #{srv,jdbcType=VARCHAR},",
          "release_name = #{releaseName,jdbcType=VARCHAR},",
          "created_time = #{createdTime,jdbcType=TIMESTAMP},",
          "updated_time = #{updatedTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(ReleaseNamePO record);


    @Update({
            "update release_name_table",
            "set srv = #{srv,jdbcType=VARCHAR},",
            "updated_time = #{updatedTime,jdbcType=TIMESTAMP}",
            "where release_name = #{release,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="srv", property="srv", jdbcType=JdbcType.VARCHAR),
            @Result(column="release_name", property="releaseName", jdbcType=JdbcType.VARCHAR),
            @Result(column="created_time", property="createdTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="updated_time", property="updatedTime", jdbcType=JdbcType.TIMESTAMP)
    })
    int updateByReleaseName(@Param("srv") String srv,@Param("release") String release,@Param("updatedTime") Date updatedTime);
}