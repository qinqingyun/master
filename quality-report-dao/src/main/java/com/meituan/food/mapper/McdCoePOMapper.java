package com.meituan.food.mapper;

import com.meituan.food.po.McdCoePO;
import com.meituan.food.po.McdCoePOExample;
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

public interface McdCoePOMapper {
    @SelectProvider(type=McdCoePOSqlProvider.class, method="countByExample")
    long countByExample(McdCoePOExample example);

    @DeleteProvider(type=McdCoePOSqlProvider.class, method="deleteByExample")
    int deleteByExample(McdCoePOExample example);

    @Delete({
        "delete from mcd_coe_list",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into mcd_coe_list (id, coe_id, ",
        "brief, create_time, ",
        "occur_time, occur_date, ",
        "build_time, update_time, ",
        "notify_time, find_time, ",
        "location_time, handle_time, ",
        "solved_time, fminuso_time, ",
        "lminusf_time, sminush_time, ",
        "wiki, level, owner_name, ",
        "owner_mis, qa_name, ",
        "qa_mis, coe_link, ",
        "category, rd_share, ",
        "qa_share, join_status, ",
        "appearance, sub_category, ",
        "all_todo, not_finish_todo, ",
        "finish_todo, not_finish_todo_task, ",
        "available, org_name, ",
        "find_date, finder, influence_time, ",
        "clear_time, locator, ",
        "order_loss, capital_loss, ",
        "coupon_loss, online_discovery, ",
        "online_classification, line, ",
        "custom_level, nofund_reason, ",
        "root_cause, mcd_id, ",
        "mcd_name)",
        "values (#{id,jdbcType=INTEGER}, #{coeId,jdbcType=INTEGER}, ",
        "#{brief,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{occurTime,jdbcType=TIMESTAMP}, #{occurDate,jdbcType=DATE}, ",
        "#{buildTime,jdbcType=TIMESTAMP}, #{updateTime,jdbcType=TIMESTAMP}, ",
        "#{notifyTime,jdbcType=TIMESTAMP}, #{findTime,jdbcType=TIMESTAMP}, ",
        "#{locationTime,jdbcType=TIMESTAMP}, #{handleTime,jdbcType=TIMESTAMP}, ",
        "#{solvedTime,jdbcType=TIMESTAMP}, #{fminusoTime,jdbcType=INTEGER}, ",
        "#{lminusfTime,jdbcType=INTEGER}, #{sminushTime,jdbcType=INTEGER}, ",
        "#{wiki,jdbcType=VARCHAR}, #{level,jdbcType=VARCHAR}, #{ownerName,jdbcType=VARCHAR}, ",
        "#{ownerMis,jdbcType=VARCHAR}, #{qaName,jdbcType=VARCHAR}, ",
        "#{qaMis,jdbcType=VARCHAR}, #{coeLink,jdbcType=VARCHAR}, ",
        "#{category,jdbcType=VARCHAR}, #{rdShare,jdbcType=DECIMAL}, ",
        "#{qaShare,jdbcType=DECIMAL}, #{joinStatus,jdbcType=BIT}, ",
        "#{appearance,jdbcType=VARCHAR}, #{subCategory,jdbcType=VARCHAR}, ",
        "#{allTodo,jdbcType=INTEGER}, #{notFinishTodo,jdbcType=INTEGER}, ",
        "#{finishTodo,jdbcType=INTEGER}, #{notFinishTodoTask,jdbcType=VARCHAR}, ",
        "#{available,jdbcType=BIT}, #{orgName,jdbcType=VARCHAR}, ",
        "#{findDate,jdbcType=DATE}, #{finder,jdbcType=VARCHAR}, #{influenceTime,jdbcType=INTEGER}, ",
        "#{clearTime,jdbcType=TIMESTAMP}, #{locator,jdbcType=VARCHAR}, ",
        "#{orderLoss,jdbcType=DECIMAL}, #{capitalLoss,jdbcType=DECIMAL}, ",
        "#{couponLoss,jdbcType=VARCHAR}, #{onlineDiscovery,jdbcType=VARCHAR}, ",
        "#{onlineClassification,jdbcType=VARCHAR}, #{line,jdbcType=VARCHAR}, ",
        "#{customLevel,jdbcType=VARCHAR}, #{nofundReason,jdbcType=VARCHAR}, ",
        "#{rootCause,jdbcType=VARCHAR}, #{mcdId,jdbcType=INTEGER}, ",
        "#{mcdName,jdbcType=INTEGER})"
    })
    int insert(McdCoePO record);

    @InsertProvider(type=McdCoePOSqlProvider.class, method="insertSelective")
    int insertSelective(McdCoePO record);

    @SelectProvider(type=McdCoePOSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="coe_id", property="coeId", jdbcType=JdbcType.INTEGER),
        @Result(column="brief", property="brief", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="occur_time", property="occurTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="occur_date", property="occurDate", jdbcType=JdbcType.DATE),
        @Result(column="build_time", property="buildTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="notify_time", property="notifyTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="find_time", property="findTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="location_time", property="locationTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="handle_time", property="handleTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="solved_time", property="solvedTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="fminuso_time", property="fminusoTime", jdbcType=JdbcType.INTEGER),
        @Result(column="lminusf_time", property="lminusfTime", jdbcType=JdbcType.INTEGER),
        @Result(column="sminush_time", property="sminushTime", jdbcType=JdbcType.INTEGER),
        @Result(column="wiki", property="wiki", jdbcType=JdbcType.VARCHAR),
        @Result(column="level", property="level", jdbcType=JdbcType.VARCHAR),
        @Result(column="owner_name", property="ownerName", jdbcType=JdbcType.VARCHAR),
        @Result(column="owner_mis", property="ownerMis", jdbcType=JdbcType.VARCHAR),
        @Result(column="qa_name", property="qaName", jdbcType=JdbcType.VARCHAR),
        @Result(column="qa_mis", property="qaMis", jdbcType=JdbcType.VARCHAR),
        @Result(column="coe_link", property="coeLink", jdbcType=JdbcType.VARCHAR),
        @Result(column="category", property="category", jdbcType=JdbcType.VARCHAR),
        @Result(column="rd_share", property="rdShare", jdbcType=JdbcType.DECIMAL),
        @Result(column="qa_share", property="qaShare", jdbcType=JdbcType.DECIMAL),
        @Result(column="join_status", property="joinStatus", jdbcType=JdbcType.BIT),
        @Result(column="appearance", property="appearance", jdbcType=JdbcType.VARCHAR),
        @Result(column="sub_category", property="subCategory", jdbcType=JdbcType.VARCHAR),
        @Result(column="all_todo", property="allTodo", jdbcType=JdbcType.INTEGER),
        @Result(column="not_finish_todo", property="notFinishTodo", jdbcType=JdbcType.INTEGER),
        @Result(column="finish_todo", property="finishTodo", jdbcType=JdbcType.INTEGER),
        @Result(column="not_finish_todo_task", property="notFinishTodoTask", jdbcType=JdbcType.VARCHAR),
        @Result(column="available", property="available", jdbcType=JdbcType.BIT),
        @Result(column="org_name", property="orgName", jdbcType=JdbcType.VARCHAR),
        @Result(column="find_date", property="findDate", jdbcType=JdbcType.DATE),
        @Result(column="finder", property="finder", jdbcType=JdbcType.VARCHAR),
        @Result(column="influence_time", property="influenceTime", jdbcType=JdbcType.INTEGER),
        @Result(column="clear_time", property="clearTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="locator", property="locator", jdbcType=JdbcType.VARCHAR),
        @Result(column="order_loss", property="orderLoss", jdbcType=JdbcType.DECIMAL),
        @Result(column="capital_loss", property="capitalLoss", jdbcType=JdbcType.DECIMAL),
        @Result(column="coupon_loss", property="couponLoss", jdbcType=JdbcType.VARCHAR),
        @Result(column="online_discovery", property="onlineDiscovery", jdbcType=JdbcType.VARCHAR),
        @Result(column="online_classification", property="onlineClassification", jdbcType=JdbcType.VARCHAR),
        @Result(column="line", property="line", jdbcType=JdbcType.VARCHAR),
        @Result(column="custom_level", property="customLevel", jdbcType=JdbcType.VARCHAR),
        @Result(column="nofund_reason", property="nofundReason", jdbcType=JdbcType.VARCHAR),
        @Result(column="root_cause", property="rootCause", jdbcType=JdbcType.VARCHAR),
        @Result(column="mcd_id", property="mcdId", jdbcType=JdbcType.INTEGER),
        @Result(column="mcd_name", property="mcdName", jdbcType=JdbcType.INTEGER)
    })
    List<McdCoePO> selectByExample(McdCoePOExample example);

    @Select({
        "select",
        "id, coe_id, brief, create_time, occur_time, occur_date, build_time, update_time, ",
        "notify_time, find_time, location_time, handle_time, solved_time, fminuso_time, ",
        "lminusf_time, sminush_time, wiki, level, owner_name, owner_mis, qa_name, qa_mis, ",
        "coe_link, category, rd_share, qa_share, join_status, appearance, sub_category, ",
        "all_todo, not_finish_todo, finish_todo, not_finish_todo_task, available, org_name, ",
        "find_date, finder, influence_time, clear_time, locator, order_loss, capital_loss, ",
        "coupon_loss, online_discovery, online_classification, line, custom_level, nofund_reason, ",
        "root_cause, mcd_id, mcd_name",
        "from mcd_coe_list",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="coe_id", property="coeId", jdbcType=JdbcType.INTEGER),
        @Result(column="brief", property="brief", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="occur_time", property="occurTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="occur_date", property="occurDate", jdbcType=JdbcType.DATE),
        @Result(column="build_time", property="buildTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="notify_time", property="notifyTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="find_time", property="findTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="location_time", property="locationTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="handle_time", property="handleTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="solved_time", property="solvedTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="fminuso_time", property="fminusoTime", jdbcType=JdbcType.INTEGER),
        @Result(column="lminusf_time", property="lminusfTime", jdbcType=JdbcType.INTEGER),
        @Result(column="sminush_time", property="sminushTime", jdbcType=JdbcType.INTEGER),
        @Result(column="wiki", property="wiki", jdbcType=JdbcType.VARCHAR),
        @Result(column="level", property="level", jdbcType=JdbcType.VARCHAR),
        @Result(column="owner_name", property="ownerName", jdbcType=JdbcType.VARCHAR),
        @Result(column="owner_mis", property="ownerMis", jdbcType=JdbcType.VARCHAR),
        @Result(column="qa_name", property="qaName", jdbcType=JdbcType.VARCHAR),
        @Result(column="qa_mis", property="qaMis", jdbcType=JdbcType.VARCHAR),
        @Result(column="coe_link", property="coeLink", jdbcType=JdbcType.VARCHAR),
        @Result(column="category", property="category", jdbcType=JdbcType.VARCHAR),
        @Result(column="rd_share", property="rdShare", jdbcType=JdbcType.DECIMAL),
        @Result(column="qa_share", property="qaShare", jdbcType=JdbcType.DECIMAL),
        @Result(column="join_status", property="joinStatus", jdbcType=JdbcType.BIT),
        @Result(column="appearance", property="appearance", jdbcType=JdbcType.VARCHAR),
        @Result(column="sub_category", property="subCategory", jdbcType=JdbcType.VARCHAR),
        @Result(column="all_todo", property="allTodo", jdbcType=JdbcType.INTEGER),
        @Result(column="not_finish_todo", property="notFinishTodo", jdbcType=JdbcType.INTEGER),
        @Result(column="finish_todo", property="finishTodo", jdbcType=JdbcType.INTEGER),
        @Result(column="not_finish_todo_task", property="notFinishTodoTask", jdbcType=JdbcType.VARCHAR),
        @Result(column="available", property="available", jdbcType=JdbcType.BIT),
        @Result(column="org_name", property="orgName", jdbcType=JdbcType.VARCHAR),
        @Result(column="find_date", property="findDate", jdbcType=JdbcType.DATE),
        @Result(column="finder", property="finder", jdbcType=JdbcType.VARCHAR),
        @Result(column="influence_time", property="influenceTime", jdbcType=JdbcType.INTEGER),
        @Result(column="clear_time", property="clearTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="locator", property="locator", jdbcType=JdbcType.VARCHAR),
        @Result(column="order_loss", property="orderLoss", jdbcType=JdbcType.DECIMAL),
        @Result(column="capital_loss", property="capitalLoss", jdbcType=JdbcType.DECIMAL),
        @Result(column="coupon_loss", property="couponLoss", jdbcType=JdbcType.VARCHAR),
        @Result(column="online_discovery", property="onlineDiscovery", jdbcType=JdbcType.VARCHAR),
        @Result(column="online_classification", property="onlineClassification", jdbcType=JdbcType.VARCHAR),
        @Result(column="line", property="line", jdbcType=JdbcType.VARCHAR),
        @Result(column="custom_level", property="customLevel", jdbcType=JdbcType.VARCHAR),
        @Result(column="nofund_reason", property="nofundReason", jdbcType=JdbcType.VARCHAR),
        @Result(column="root_cause", property="rootCause", jdbcType=JdbcType.VARCHAR),
        @Result(column="mcd_id", property="mcdId", jdbcType=JdbcType.INTEGER),
        @Result(column="mcd_name", property="mcdName", jdbcType=JdbcType.INTEGER)
    })
    McdCoePO selectByPrimaryKey(Integer id);

    @UpdateProvider(type=McdCoePOSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") McdCoePO record, @Param("example") McdCoePOExample example);

    @UpdateProvider(type=McdCoePOSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") McdCoePO record, @Param("example") McdCoePOExample example);

    @UpdateProvider(type=McdCoePOSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(McdCoePO record);

    @Update({
        "update mcd_coe_list",
        "set coe_id = #{coeId,jdbcType=INTEGER},",
          "brief = #{brief,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "occur_time = #{occurTime,jdbcType=TIMESTAMP},",
          "occur_date = #{occurDate,jdbcType=DATE},",
          "build_time = #{buildTime,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "notify_time = #{notifyTime,jdbcType=TIMESTAMP},",
          "find_time = #{findTime,jdbcType=TIMESTAMP},",
          "location_time = #{locationTime,jdbcType=TIMESTAMP},",
          "handle_time = #{handleTime,jdbcType=TIMESTAMP},",
          "solved_time = #{solvedTime,jdbcType=TIMESTAMP},",
          "fminuso_time = #{fminusoTime,jdbcType=INTEGER},",
          "lminusf_time = #{lminusfTime,jdbcType=INTEGER},",
          "sminush_time = #{sminushTime,jdbcType=INTEGER},",
          "wiki = #{wiki,jdbcType=VARCHAR},",
          "level = #{level,jdbcType=VARCHAR},",
          "owner_name = #{ownerName,jdbcType=VARCHAR},",
          "owner_mis = #{ownerMis,jdbcType=VARCHAR},",
          "qa_name = #{qaName,jdbcType=VARCHAR},",
          "qa_mis = #{qaMis,jdbcType=VARCHAR},",
          "coe_link = #{coeLink,jdbcType=VARCHAR},",
          "category = #{category,jdbcType=VARCHAR},",
          "rd_share = #{rdShare,jdbcType=DECIMAL},",
          "qa_share = #{qaShare,jdbcType=DECIMAL},",
          "join_status = #{joinStatus,jdbcType=BIT},",
          "appearance = #{appearance,jdbcType=VARCHAR},",
          "sub_category = #{subCategory,jdbcType=VARCHAR},",
          "all_todo = #{allTodo,jdbcType=INTEGER},",
          "not_finish_todo = #{notFinishTodo,jdbcType=INTEGER},",
          "finish_todo = #{finishTodo,jdbcType=INTEGER},",
          "not_finish_todo_task = #{notFinishTodoTask,jdbcType=VARCHAR},",
          "available = #{available,jdbcType=BIT},",
          "org_name = #{orgName,jdbcType=VARCHAR},",
          "find_date = #{findDate,jdbcType=DATE},",
          "finder = #{finder,jdbcType=VARCHAR},",
          "influence_time = #{influenceTime,jdbcType=INTEGER},",
          "clear_time = #{clearTime,jdbcType=TIMESTAMP},",
          "locator = #{locator,jdbcType=VARCHAR},",
          "order_loss = #{orderLoss,jdbcType=DECIMAL},",
          "capital_loss = #{capitalLoss,jdbcType=DECIMAL},",
          "coupon_loss = #{couponLoss,jdbcType=VARCHAR},",
          "online_discovery = #{onlineDiscovery,jdbcType=VARCHAR},",
          "online_classification = #{onlineClassification,jdbcType=VARCHAR},",
          "line = #{line,jdbcType=VARCHAR},",
          "custom_level = #{customLevel,jdbcType=VARCHAR},",
          "nofund_reason = #{nofundReason,jdbcType=VARCHAR},",
          "root_cause = #{rootCause,jdbcType=VARCHAR},",
          "mcd_id = #{mcdId,jdbcType=INTEGER},",
          "mcd_name = #{mcdName,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(McdCoePO record);


    //新增

    @Select({
            "select coe_id from mcd_coe_list"
    })
    @Results({
            @Result(column="coe_id", property="coeId", jdbcType=JdbcType.INTEGER)
    })
    List<Integer> selectMcdCoeIdList();


    @Select({
            "select",
            "id, coe_id, brief, create_time, occur_time, occur_date, build_time, update_time, ",
            "notify_time, find_time, location_time, handle_time, solved_time, fminuso_time, ",
            "lminusf_time, sminush_time, wiki, level, owner_name, owner_mis, qa_name, qa_mis, ",
            "coe_link, category, rd_share, qa_share, join_status, appearance, sub_category, ",
            "all_todo, not_finish_todo, finish_todo, not_finish_todo_task, available, org_name, ",
            "find_date, finder, influence_time, clear_time, locator, order_loss, capital_loss, ",
            "coupon_loss, online_discovery, online_classification, line, custom_level, nofund_reason, ",
            "root_cause, mcd_id, mcd_name",
            "from mcd_coe_list",
            "where coe_id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="coe_id", property="coeId", jdbcType=JdbcType.INTEGER),
            @Result(column="brief", property="brief", jdbcType=JdbcType.VARCHAR),
            @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="occur_time", property="occurTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="occur_date", property="occurDate", jdbcType=JdbcType.DATE),
            @Result(column="build_time", property="buildTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="notify_time", property="notifyTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="find_time", property="findTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="location_time", property="locationTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="handle_time", property="handleTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="solved_time", property="solvedTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="fminuso_time", property="fminusoTime", jdbcType=JdbcType.INTEGER),
            @Result(column="lminusf_time", property="lminusfTime", jdbcType=JdbcType.INTEGER),
            @Result(column="sminush_time", property="sminushTime", jdbcType=JdbcType.INTEGER),
            @Result(column="wiki", property="wiki", jdbcType=JdbcType.VARCHAR),
            @Result(column="level", property="level", jdbcType=JdbcType.VARCHAR),
            @Result(column="owner_name", property="ownerName", jdbcType=JdbcType.VARCHAR),
            @Result(column="owner_mis", property="ownerMis", jdbcType=JdbcType.VARCHAR),
            @Result(column="qa_name", property="qaName", jdbcType=JdbcType.VARCHAR),
            @Result(column="qa_mis", property="qaMis", jdbcType=JdbcType.VARCHAR),
            @Result(column="coe_link", property="coeLink", jdbcType=JdbcType.VARCHAR),
            @Result(column="category", property="category", jdbcType=JdbcType.VARCHAR),
            @Result(column="rd_share", property="rdShare", jdbcType=JdbcType.DECIMAL),
            @Result(column="qa_share", property="qaShare", jdbcType=JdbcType.DECIMAL),
            @Result(column="join_status", property="joinStatus", jdbcType=JdbcType.BIT),
            @Result(column="appearance", property="appearance", jdbcType=JdbcType.VARCHAR),
            @Result(column="sub_category", property="subCategory", jdbcType=JdbcType.VARCHAR),
            @Result(column="all_todo", property="allTodo", jdbcType=JdbcType.INTEGER),
            @Result(column="not_finish_todo", property="notFinishTodo", jdbcType=JdbcType.INTEGER),
            @Result(column="finish_todo", property="finishTodo", jdbcType=JdbcType.INTEGER),
            @Result(column="not_finish_todo_task", property="notFinishTodoTask", jdbcType=JdbcType.VARCHAR),
            @Result(column="available", property="available", jdbcType=JdbcType.BIT),
            @Result(column="org_name", property="orgName", jdbcType=JdbcType.VARCHAR),
            @Result(column="find_date", property="findDate", jdbcType=JdbcType.DATE),
            @Result(column="finder", property="finder", jdbcType=JdbcType.VARCHAR),
            @Result(column="influence_time", property="influenceTime", jdbcType=JdbcType.INTEGER),
            @Result(column="clear_time", property="clearTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="locator", property="locator", jdbcType=JdbcType.VARCHAR),
            @Result(column="order_loss", property="orderLoss", jdbcType=JdbcType.DECIMAL),
            @Result(column="capital_loss", property="capitalLoss", jdbcType=JdbcType.DECIMAL),
            @Result(column="coupon_loss", property="couponLoss", jdbcType=JdbcType.VARCHAR),
            @Result(column="online_discovery", property="onlineDiscovery", jdbcType=JdbcType.VARCHAR),
            @Result(column="online_classification", property="onlineClassification", jdbcType=JdbcType.VARCHAR),
            @Result(column="line", property="line", jdbcType=JdbcType.VARCHAR),
            @Result(column="custom_level", property="customLevel", jdbcType=JdbcType.VARCHAR),
            @Result(column="nofund_reason", property="nofundReason", jdbcType=JdbcType.VARCHAR),
            @Result(column="root_cause", property="rootCause", jdbcType=JdbcType.VARCHAR),
            @Result(column="mcd_id", property="mcdId", jdbcType=JdbcType.INTEGER),
            @Result(column="mcd_name", property="mcdName", jdbcType=JdbcType.INTEGER)
    })
    McdCoePO selectByCoeId(Integer id);





    @Select({
            "select",
            "id, coe_id, brief, create_time, occur_time, occur_date, build_time, update_time, ",
            "notify_time, find_time, location_time, handle_time, solved_time, fminuso_time, ",
            "lminusf_time, sminush_time, wiki, level, owner_name, owner_mis, qa_name, qa_mis, ",
            "coe_link, category, rd_share, qa_share, join_status, appearance, sub_category, ",
            "all_todo, not_finish_todo, finish_todo, not_finish_todo_task, available, org_name, ",
            "find_date, finder, influence_time, clear_time, locator, order_loss, capital_loss, ",
            "coupon_loss, online_discovery, online_classification, line, custom_level, nofund_reason, ",
            "root_cause, mcd_id, mcd_name",
            "from mcd_coe_list",
            "where available=1 and occur_date >= #{occur,jdbcType=DATE} and occur_date < #{occur2,jdbcType=DATE}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="coe_id", property="coeId", jdbcType=JdbcType.INTEGER),
            @Result(column="brief", property="brief", jdbcType=JdbcType.VARCHAR),
            @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="occur_time", property="occurTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="occur_date", property="occurDate", jdbcType=JdbcType.DATE),
            @Result(column="build_time", property="buildTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="notify_time", property="notifyTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="find_time", property="findTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="location_time", property="locationTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="handle_time", property="handleTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="solved_time", property="solvedTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="fminuso_time", property="fminusoTime", jdbcType=JdbcType.INTEGER),
            @Result(column="lminusf_time", property="lminusfTime", jdbcType=JdbcType.INTEGER),
            @Result(column="sminush_time", property="sminushTime", jdbcType=JdbcType.INTEGER),
            @Result(column="wiki", property="wiki", jdbcType=JdbcType.VARCHAR),
            @Result(column="level", property="level", jdbcType=JdbcType.VARCHAR),
            @Result(column="owner_name", property="ownerName", jdbcType=JdbcType.VARCHAR),
            @Result(column="owner_mis", property="ownerMis", jdbcType=JdbcType.VARCHAR),
            @Result(column="qa_name", property="qaName", jdbcType=JdbcType.VARCHAR),
            @Result(column="qa_mis", property="qaMis", jdbcType=JdbcType.VARCHAR),
            @Result(column="coe_link", property="coeLink", jdbcType=JdbcType.VARCHAR),
            @Result(column="category", property="category", jdbcType=JdbcType.VARCHAR),
            @Result(column="rd_share", property="rdShare", jdbcType=JdbcType.DECIMAL),
            @Result(column="qa_share", property="qaShare", jdbcType=JdbcType.DECIMAL),
            @Result(column="join_status", property="joinStatus", jdbcType=JdbcType.BIT),
            @Result(column="appearance", property="appearance", jdbcType=JdbcType.VARCHAR),
            @Result(column="sub_category", property="subCategory", jdbcType=JdbcType.VARCHAR),
            @Result(column="all_todo", property="allTodo", jdbcType=JdbcType.INTEGER),
            @Result(column="not_finish_todo", property="notFinishTodo", jdbcType=JdbcType.INTEGER),
            @Result(column="finish_todo", property="finishTodo", jdbcType=JdbcType.INTEGER),
            @Result(column="not_finish_todo_task", property="notFinishTodoTask", jdbcType=JdbcType.VARCHAR),
            @Result(column="available", property="available", jdbcType=JdbcType.BIT),
            @Result(column="org_name", property="orgName", jdbcType=JdbcType.VARCHAR),
            @Result(column="find_date", property="findDate", jdbcType=JdbcType.DATE),
            @Result(column="finder", property="finder", jdbcType=JdbcType.VARCHAR),
            @Result(column="influence_time", property="influenceTime", jdbcType=JdbcType.INTEGER),
            @Result(column="clear_time", property="clearTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="locator", property="locator", jdbcType=JdbcType.VARCHAR),
            @Result(column="order_loss", property="orderLoss", jdbcType=JdbcType.DECIMAL),
            @Result(column="capital_loss", property="capitalLoss", jdbcType=JdbcType.DECIMAL),
            @Result(column="coupon_loss", property="couponLoss", jdbcType=JdbcType.VARCHAR),
            @Result(column="online_discovery", property="onlineDiscovery", jdbcType=JdbcType.VARCHAR),
            @Result(column="online_classification", property="onlineClassification", jdbcType=JdbcType.VARCHAR),
            @Result(column="line", property="line", jdbcType=JdbcType.VARCHAR),
            @Result(column="custom_level", property="customLevel", jdbcType=JdbcType.VARCHAR),
            @Result(column="nofund_reason", property="nofundReason", jdbcType=JdbcType.VARCHAR),
            @Result(column="root_cause", property="rootCause", jdbcType=JdbcType.VARCHAR),
            @Result(column="mcd_id", property="mcdId", jdbcType=JdbcType.INTEGER),
            @Result(column="mcd_name", property="mcdName", jdbcType=JdbcType.INTEGER)
    })
    List<McdCoePO> selectByTwoDate(@Param("occur") java.util.Date occur,@Param("occur2") java.util.Date occur2);



    @Select({
            "select",
            "id, coe_id, brief, create_time, occur_time, occur_date, build_time, update_time, ",
            "notify_time, find_time, location_time, handle_time, solved_time, fminuso_time, ",
            "lminusf_time, sminush_time, wiki, level, owner_name, owner_mis, qa_name, qa_mis, ",
            "coe_link, category, rd_share, qa_share, join_status, appearance, sub_category, ",
            "all_todo, not_finish_todo, finish_todo, not_finish_todo_task, available, org_name, ",
            "find_date, finder, influence_time, clear_time, locator, order_loss, capital_loss, ",
            "coupon_loss, online_discovery, online_classification, line, custom_level, nofund_reason, ",
            "root_cause, mcd_id, mcd_name",
            "from mcd_coe_list",
            "where available=1 and occur_date = #{occur,jdbcType=DATE}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="coe_id", property="coeId", jdbcType=JdbcType.INTEGER),
            @Result(column="brief", property="brief", jdbcType=JdbcType.VARCHAR),
            @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="occur_time", property="occurTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="occur_date", property="occurDate", jdbcType=JdbcType.DATE),
            @Result(column="build_time", property="buildTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="notify_time", property="notifyTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="find_time", property="findTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="location_time", property="locationTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="handle_time", property="handleTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="solved_time", property="solvedTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="fminuso_time", property="fminusoTime", jdbcType=JdbcType.INTEGER),
            @Result(column="lminusf_time", property="lminusfTime", jdbcType=JdbcType.INTEGER),
            @Result(column="sminush_time", property="sminushTime", jdbcType=JdbcType.INTEGER),
            @Result(column="wiki", property="wiki", jdbcType=JdbcType.VARCHAR),
            @Result(column="level", property="level", jdbcType=JdbcType.VARCHAR),
            @Result(column="owner_name", property="ownerName", jdbcType=JdbcType.VARCHAR),
            @Result(column="owner_mis", property="ownerMis", jdbcType=JdbcType.VARCHAR),
            @Result(column="qa_name", property="qaName", jdbcType=JdbcType.VARCHAR),
            @Result(column="qa_mis", property="qaMis", jdbcType=JdbcType.VARCHAR),
            @Result(column="coe_link", property="coeLink", jdbcType=JdbcType.VARCHAR),
            @Result(column="category", property="category", jdbcType=JdbcType.VARCHAR),
            @Result(column="rd_share", property="rdShare", jdbcType=JdbcType.DECIMAL),
            @Result(column="qa_share", property="qaShare", jdbcType=JdbcType.DECIMAL),
            @Result(column="join_status", property="joinStatus", jdbcType=JdbcType.BIT),
            @Result(column="appearance", property="appearance", jdbcType=JdbcType.VARCHAR),
            @Result(column="sub_category", property="subCategory", jdbcType=JdbcType.VARCHAR),
            @Result(column="all_todo", property="allTodo", jdbcType=JdbcType.INTEGER),
            @Result(column="not_finish_todo", property="notFinishTodo", jdbcType=JdbcType.INTEGER),
            @Result(column="finish_todo", property="finishTodo", jdbcType=JdbcType.INTEGER),
            @Result(column="not_finish_todo_task", property="notFinishTodoTask", jdbcType=JdbcType.VARCHAR),
            @Result(column="available", property="available", jdbcType=JdbcType.BIT),
            @Result(column="org_name", property="orgName", jdbcType=JdbcType.VARCHAR),
            @Result(column="find_date", property="findDate", jdbcType=JdbcType.DATE),
            @Result(column="finder", property="finder", jdbcType=JdbcType.VARCHAR),
            @Result(column="influence_time", property="influenceTime", jdbcType=JdbcType.INTEGER),
            @Result(column="clear_time", property="clearTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="locator", property="locator", jdbcType=JdbcType.VARCHAR),
            @Result(column="order_loss", property="orderLoss", jdbcType=JdbcType.DECIMAL),
            @Result(column="capital_loss", property="capitalLoss", jdbcType=JdbcType.DECIMAL),
            @Result(column="coupon_loss", property="couponLoss", jdbcType=JdbcType.VARCHAR),
            @Result(column="online_discovery", property="onlineDiscovery", jdbcType=JdbcType.VARCHAR),
            @Result(column="online_classification", property="onlineClassification", jdbcType=JdbcType.VARCHAR),
            @Result(column="line", property="line", jdbcType=JdbcType.VARCHAR),
            @Result(column="custom_level", property="customLevel", jdbcType=JdbcType.VARCHAR),
            @Result(column="nofund_reason", property="nofundReason", jdbcType=JdbcType.VARCHAR),
            @Result(column="root_cause", property="rootCause", jdbcType=JdbcType.VARCHAR),
            @Result(column="mcd_id", property="mcdId", jdbcType=JdbcType.INTEGER),
            @Result(column="mcd_name", property="mcdName", jdbcType=JdbcType.INTEGER)
    })
    List<McdCoePO> selectByDate(@Param("occur") java.util.Date occur);
}