package com.meituan.food.mapper;

import com.meituan.food.po.AppkeyListPO;
import com.meituan.food.po.AppkeyListPOExample;

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
import org.springframework.web.bind.annotation.RequestParam;

public interface AppkeyListPOMapper {
    @SelectProvider(type=AppkeyListPOSqlProvider.class, method="countByExample")
    long countByExample(AppkeyListPOExample example);

    @DeleteProvider(type=AppkeyListPOSqlProvider.class, method="deleteByExample")
    int deleteByExample(AppkeyListPOExample example);

    @Delete({
        "delete from appkey_list_table",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into appkey_list_table (id, owt, ",
        "pdl, srv, appkey, ",
        "department_id, offline, ",
        "created_time, updated_time, ",
        "rank, department_id_2)",
        "values (#{id,jdbcType=INTEGER}, #{owt,jdbcType=VARCHAR}, ",
        "#{pdl,jdbcType=VARCHAR}, #{srv,jdbcType=VARCHAR}, #{appkey,jdbcType=VARCHAR}, ",
        "#{departmentId,jdbcType=INTEGER}, #{offline,jdbcType=INTEGER}, ",
        "#{createdTime,jdbcType=TIMESTAMP}, #{updatedTime,jdbcType=TIMESTAMP}, ",
        "#{rank,jdbcType=INTEGER}, #{departmentId2,jdbcType=INTEGER})"
    })
    int insert(AppkeyListPO record);

    @InsertProvider(type=AppkeyListPOSqlProvider.class, method="insertSelective")
    int insertSelective(AppkeyListPO record);

    @SelectProvider(type=AppkeyListPOSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="owt", property="owt", jdbcType=JdbcType.VARCHAR),
        @Result(column="pdl", property="pdl", jdbcType=JdbcType.VARCHAR),
        @Result(column="srv", property="srv", jdbcType=JdbcType.VARCHAR),
        @Result(column="appkey", property="appkey", jdbcType=JdbcType.VARCHAR),
        @Result(column="department_id", property="departmentId", jdbcType=JdbcType.INTEGER),
        @Result(column="offline", property="offline", jdbcType=JdbcType.INTEGER),
        @Result(column="created_time", property="createdTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="updated_time", property="updatedTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="rank", property="rank", jdbcType=JdbcType.INTEGER),
        @Result(column="department_id_2", property="departmentId2", jdbcType=JdbcType.INTEGER)
    })
    List<AppkeyListPO> selectByExample(AppkeyListPOExample example);

    @Select({
        "select",
        "id, owt, pdl, srv, appkey, department_id, offline, created_time, updated_time, ",
        "rank, department_id_2",
        "from appkey_list_table",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="owt", property="owt", jdbcType=JdbcType.VARCHAR),
        @Result(column="pdl", property="pdl", jdbcType=JdbcType.VARCHAR),
        @Result(column="srv", property="srv", jdbcType=JdbcType.VARCHAR),
        @Result(column="appkey", property="appkey", jdbcType=JdbcType.VARCHAR),
        @Result(column="department_id", property="departmentId", jdbcType=JdbcType.INTEGER),
        @Result(column="offline", property="offline", jdbcType=JdbcType.INTEGER),
        @Result(column="created_time", property="createdTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="updated_time", property="updatedTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="rank", property="rank", jdbcType=JdbcType.INTEGER),
        @Result(column="department_id_2", property="departmentId2", jdbcType=JdbcType.INTEGER)
    })
    AppkeyListPO selectByPrimaryKey(Integer id);

    @Select({
            "select",
            "id, owt, pdl, srv, appkey, department_id, offline, created_time, updated_time, ",
            "rank, department_id_2",
            "from appkey_list_table",
            "where appkey = #{appkey,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="owt", property="owt", jdbcType=JdbcType.VARCHAR),
            @Result(column="pdl", property="pdl", jdbcType=JdbcType.VARCHAR),
            @Result(column="srv", property="srv", jdbcType=JdbcType.VARCHAR),
            @Result(column="appkey", property="appkey", jdbcType=JdbcType.VARCHAR),
            @Result(column="department_id", property="departmentId", jdbcType=JdbcType.INTEGER),
            @Result(column="offline", property="offline", jdbcType=JdbcType.INTEGER),
            @Result(column="created_time", property="createdTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="updated_time", property="updatedTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="rank", property="rank", jdbcType=JdbcType.INTEGER),
            @Result(column="department_id_2", property="departmentId2", jdbcType=JdbcType.INTEGER)
    })
    AppkeyListPO selectByAppKey(String appkey);

    @Select({
            "select srv from appkey_list_table",
            "where appkey = #{appkey,jdbcType=VARCHAR} and rank=1 and offline=0"
    })
    String selectOnlineP1ByAppKey(String appkey);

    @Select({
            "select srv from appkey_list_table",
            "where appkey = #{appkey,jdbcType=VARCHAR} and rank=3 and offline=0"
    })
    String selectOnlineP2ByAppKey(String appkey);

    @Select({
            "select",
            "count(*) ",
            "from appkey_list_table",
            "where appkey = #{appkey,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="appkey", property="appkey", jdbcType=JdbcType.VARCHAR)
    })
    int selectCountByAppKey(@Param("appkey") String appkey);
    @Select({
            "select appkey from appkey_list_table where offline = 0"
    })
    List<String> selectAllAppkey();


    @Select({
            "select srv from appkey_list_table where offline = 0"
    })
    List<String> selectAllSrv();

    @Select({
        "select",
                "department_id, department_id_2",
                "from appkey_list_table",
                "where srv = #{srv,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="department_id", property="departmentId", jdbcType=JdbcType.INTEGER),
            @Result(column="department_id_2", property="departmentId2", jdbcType=JdbcType.INTEGER)
    })
    AppkeyListPO selectBySrv(String srv);

    @UpdateProvider(type=AppkeyListPOSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") AppkeyListPO record, @Param("example") AppkeyListPOExample example);

    @UpdateProvider(type=AppkeyListPOSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") AppkeyListPO record, @Param("example") AppkeyListPOExample example);

    @UpdateProvider(type=AppkeyListPOSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(AppkeyListPO record);

    @Update({
        "update appkey_list_table",
        "set owt = #{owt,jdbcType=VARCHAR},",
          "pdl = #{pdl,jdbcType=VARCHAR},",
          "srv = #{srv,jdbcType=VARCHAR},",
          "appkey = #{appkey,jdbcType=VARCHAR},",
          "department_id = #{departmentId,jdbcType=INTEGER},",
          "offline = #{offline,jdbcType=INTEGER},",
          "created_time = #{createdTime,jdbcType=TIMESTAMP},",
          "updated_time = #{updatedTime,jdbcType=TIMESTAMP},",
          "rank = #{rank,jdbcType=INTEGER},",
          "department_id_2 = #{departmentId2,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(AppkeyListPO record);

    @Update({
            "update appkey_list_table set offline = 1,",
            "updated_time = #{updatedTime,jdbcType=TIMESTAMP} ",
            "where appkey = #{appkey,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="owt", property="owt", jdbcType=JdbcType.VARCHAR),
            @Result(column="pdl", property="pdl", jdbcType=JdbcType.VARCHAR),
            @Result(column="srv", property="srv", jdbcType=JdbcType.VARCHAR),
            @Result(column="appkey", property="appkey", jdbcType=JdbcType.VARCHAR),
            @Result(column="department_id", property="departmentId", jdbcType=JdbcType.INTEGER),
            @Result(column="offline", property="offline", jdbcType=JdbcType.INTEGER),
            @Result(column="created_time", property="createdTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="updated_time", property="updatedTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="rank", property="rank", jdbcType=JdbcType.INTEGER),
            @Result(column="department_id_2", property="departmentId2", jdbcType=JdbcType.INTEGER)
    })
    int updateToOffByAppkey(@Param("appkey") String appkey, @Param("updatedTime") Date updatedTime);

    @Update({
            "update appkey_list_table",
            "set offline = 0,",
            "updated_time = #{updatedTime,jdbcType=TIMESTAMP} ",
            "where appkey = #{appkey,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="owt", property="owt", jdbcType=JdbcType.VARCHAR),
            @Result(column="pdl", property="pdl", jdbcType=JdbcType.VARCHAR),
            @Result(column="srv", property="srv", jdbcType=JdbcType.VARCHAR),
            @Result(column="appkey", property="appkey", jdbcType=JdbcType.VARCHAR),
            @Result(column="department_id", property="departmentId", jdbcType=JdbcType.INTEGER),
            @Result(column="offline", property="offline", jdbcType=JdbcType.INTEGER),
            @Result(column="created_time", property="createdTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="updated_time", property="updatedTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="rank", property="rank", jdbcType=JdbcType.INTEGER),
            @Result(column="department_id_2", property="departmentId2", jdbcType=JdbcType.INTEGER)
    })
    int updateToOnByAppkey(@Param("appkey") String appkey, @Param("updatedTime") Date updatedTime);

    @Update({
            "update appkey_list_table",
            "set rank = 2,",
            "updated_time = #{updatedTime,jdbcType=TIMESTAMP} ",
            "where appkey = #{appkey,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="owt", property="owt", jdbcType=JdbcType.VARCHAR),
            @Result(column="pdl", property="pdl", jdbcType=JdbcType.VARCHAR),
            @Result(column="srv", property="srv", jdbcType=JdbcType.VARCHAR),
            @Result(column="appkey", property="appkey", jdbcType=JdbcType.VARCHAR),
            @Result(column="department_id", property="departmentId", jdbcType=JdbcType.INTEGER),
            @Result(column="offline", property="offline", jdbcType=JdbcType.INTEGER),
            @Result(column="created_time", property="createdTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="updated_time", property="updatedTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="rank", property="rank", jdbcType=JdbcType.INTEGER),
            @Result(column="department_id_2", property="departmentId2", jdbcType=JdbcType.INTEGER)
    })
    int updateToNonCore(@Param("appkey") String appkey, @Param("updatedTime") Date updatedTime);


    @Update({
            "update appkey_list_table",
            "set rank = 1,",
            "updated_time = #{updatedTime,jdbcType=TIMESTAMP} ",
            "where appkey = #{appkey,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="owt", property="owt", jdbcType=JdbcType.VARCHAR),
            @Result(column="pdl", property="pdl", jdbcType=JdbcType.VARCHAR),
            @Result(column="srv", property="srv", jdbcType=JdbcType.VARCHAR),
            @Result(column="appkey", property="appkey", jdbcType=JdbcType.VARCHAR),
            @Result(column="department_id", property="departmentId", jdbcType=JdbcType.INTEGER),
            @Result(column="offline", property="offline", jdbcType=JdbcType.INTEGER),
            @Result(column="created_time", property="createdTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="updated_time", property="updatedTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="rank", property="rank", jdbcType=JdbcType.INTEGER),
            @Result(column="department_id_2", property="departmentId2", jdbcType=JdbcType.INTEGER)
    })
    int updateToCore(@Param("appkey") String appkey, @Param("updatedTime") Date updatedTime);

    @Update({
            "update appkey_list_table",
            "set department_id = #{departmentId,jdbcType=INTEGER},",
            "department_id_2=#{departmentId2,jdbcType=INTEGER},",
            "updated_time = #{updatedTime,jdbcType=TIMESTAMP} ",
            "where appkey = #{appkey,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="owt", property="owt", jdbcType=JdbcType.VARCHAR),
            @Result(column="pdl", property="pdl", jdbcType=JdbcType.VARCHAR),
            @Result(column="srv", property="srv", jdbcType=JdbcType.VARCHAR),
            @Result(column="appkey", property="appkey", jdbcType=JdbcType.VARCHAR),
            @Result(column="department_id", property="departmentId", jdbcType=JdbcType.INTEGER),
            @Result(column="offline", property="offline", jdbcType=JdbcType.INTEGER),
            @Result(column="created_time", property="createdTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="updated_time", property="updatedTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="rank", property="rank", jdbcType=JdbcType.INTEGER),
            @Result(column="department_id_2", property="departmentId2", jdbcType=JdbcType.INTEGER)
    })
    int updateDepartment(@Param("appkey") String appkey, @Param("updatedTime") Date updatedTime,@Param("departmentId") Integer departmentId,@Param("departmentId2") Integer departmentId2);


    @Update({
            "truncate table appkey_list_table"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="owt", property="owt", jdbcType=JdbcType.VARCHAR),
            @Result(column="pdl", property="pdl", jdbcType=JdbcType.VARCHAR),
            @Result(column="srv", property="srv", jdbcType=JdbcType.VARCHAR),
            @Result(column="appkey", property="appkey", jdbcType=JdbcType.VARCHAR),
            @Result(column="department_id", property="departmentId", jdbcType=JdbcType.INTEGER),
            @Result(column="offline", property="offline", jdbcType=JdbcType.INTEGER),
            @Result(column="created_time", property="createdTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="updated_time", property="updatedTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="rank", property="rank", jdbcType=JdbcType.INTEGER),
            @Result(column="department_id_2", property="departmentId2", jdbcType=JdbcType.INTEGER)
    })
    int deleteAllData();

    @Select({
            "select",
            "appkey",
            "from appkey_list_table",
            "where department_id_2 = #{department_id_2,jdbcType=INTEGER}"
    })
    List<String> selectByDepartment_id_2(@Param("department_id_2") Integer departmentId2);

    @Select({
            "select",
            "appkey",
            "from appkey_list_table",
            "where department_id_2 = #{department_id_2,jdbcType=INTEGER}",
            "limit (pageNum - 1) * pageSize + 1, pageSize"
    })
    List<String> selectByDepartmentPage(@Param("department_id_2") Integer departmentId2 , @Param("pageNum") Integer pageNum , @Param("pageSize") Integer pageSize);

    @Select({
            "select",
            "count(*)",
            "from appkey_list_table",
            "where department_id_2 = #{department_id_2,jdbcType=INTEGER}"
    })
    Integer selectCountByDepartment(@Param("department_id_2") Integer departmentId2);

    @Select({
            "select",
            "distinct",
            "owt",
            "from appkey_list_table",
    })
    List<String> selectOwt();

    @Select({
            "select",
            "distinct",
            "pdl",
            "from appkey_list_table",
            "where owt = #{owt,jdbcType=VARCHAR}"
    })
    List<String> selectPdl(@Param("owt") String owt);

    @Select({
            "select",
            "appkey",
            "from appkey_list_table",
            "where pdl = #{pdl,jdbcType=VARCHAR}"
    })
    List<String> selectByPdl(@Param("pdl") String pdl);

    @Update({
            "update appkey_list_table",
            "set rank = 3,",
            "updated_time = #{updatedTime,jdbcType=TIMESTAMP} ",
            "where appkey = #{appkey,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="owt", property="owt", jdbcType=JdbcType.VARCHAR),
            @Result(column="pdl", property="pdl", jdbcType=JdbcType.VARCHAR),
            @Result(column="srv", property="srv", jdbcType=JdbcType.VARCHAR),
            @Result(column="appkey", property="appkey", jdbcType=JdbcType.VARCHAR),
            @Result(column="department_id", property="departmentId", jdbcType=JdbcType.INTEGER),
            @Result(column="offline", property="offline", jdbcType=JdbcType.INTEGER),
            @Result(column="created_time", property="createdTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="updated_time", property="updatedTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="rank", property="rank", jdbcType=JdbcType.INTEGER),
            @Result(column="department_id_2", property="departmentId2", jdbcType=JdbcType.INTEGER)
    })
    int updateToP2Core(@Param("appkey") String appkey, @Param("updatedTime") Date updatedTime);
}