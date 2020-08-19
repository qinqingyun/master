package com.meituan.food.mapper;

import com.meituan.food.po.CoeListPO;
import com.meituan.food.po.CoeListPOExample;
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

public interface CoeListPOMapper {
    @SelectProvider(type=CoeListPOSqlProvider.class, method="countByExample")
    long countByExample(CoeListPOExample example);

    @DeleteProvider(type=CoeListPOSqlProvider.class, method="deleteByExample")
    int deleteByExample(CoeListPOExample example);

    @Delete({
        "delete from coe_list",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Delete({
            "delete from coe_list",
            "where coe_id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="coe_id", property="coeId", jdbcType=JdbcType.INTEGER)
    })
    int deleteByCoeId(Integer id);

    @Insert({
        "insert into coe_list (id, coe_id, ",
        "brief, occur_date, notify_time, ",
        "find_time, location_time, ",
        "handle_time, solved_time, ",
        "fminuso_time, lminusf_time, ",
        "sminush_time, wiki, ",
        "level, owner_name, ",
        "owner_mis, qa_name, ",
        "qa_mis, coe_link, ",
        "category, rd_share, ",
        "qa_share, join_status, ",
        "appearance, sub_category, ",
        "all_todo, not_finish_todo, ",
        "finish_todo, not_finish_todo_task, ",
        "available, org_name, ",
        "find_date, finder, influence_time, ",
        "clear_time, node_two_org, ",
        "locator, order_loss, ",
        "capital_loss, line_of_business, ",
        "business)",
        "values (#{id,jdbcType=INTEGER}, #{coeId,jdbcType=INTEGER}, ",
        "#{brief,jdbcType=VARCHAR}, #{occurDate,jdbcType=DATE}, #{notifyTime,jdbcType=TIMESTAMP}, ",
        "#{findTime,jdbcType=TIMESTAMP}, #{locationTime,jdbcType=TIMESTAMP}, ",
        "#{handleTime,jdbcType=TIMESTAMP}, #{solvedTime,jdbcType=TIMESTAMP}, ",
        "#{fminusoTime,jdbcType=INTEGER}, #{lminusfTime,jdbcType=INTEGER}, ",
        "#{sminushTime,jdbcType=INTEGER}, #{wiki,jdbcType=VARCHAR}, ",
        "#{level,jdbcType=VARCHAR}, #{ownerName,jdbcType=VARCHAR}, ",
        "#{ownerMis,jdbcType=VARCHAR}, #{qaName,jdbcType=VARCHAR}, ",
        "#{qaMis,jdbcType=VARCHAR}, #{coeLink,jdbcType=VARCHAR}, ",
        "#{category,jdbcType=VARCHAR}, #{rdShare,jdbcType=DECIMAL}, ",
        "#{qaShare,jdbcType=DECIMAL}, #{joinStatus,jdbcType=BIT}, ",
        "#{appearance,jdbcType=VARCHAR}, #{subCategory,jdbcType=VARCHAR}, ",
        "#{allTodo,jdbcType=INTEGER}, #{notFinishTodo,jdbcType=INTEGER}, ",
        "#{finishTodo,jdbcType=INTEGER}, #{notFinishTodoTask,jdbcType=VARCHAR}, ",
        "#{available,jdbcType=BIT}, #{orgName,jdbcType=VARCHAR}, ",
        "#{findDate,jdbcType=DATE}, #{finder,jdbcType=VARCHAR}, #{influenceTime,jdbcType=INTEGER}, ",
        "#{clearTime,jdbcType=TIMESTAMP}, #{nodeTwoOrg,jdbcType=VARCHAR}, ",
        "#{locator,jdbcType=VARCHAR}, #{orderLoss,jdbcType=DECIMAL}, ",
        "#{capitalLoss,jdbcType=DECIMAL}, #{lineOfBusiness,jdbcType=VARCHAR}, ",
        "#{business,jdbcType=VARCHAR})"
    })
    int insert(CoeListPO record);

    @InsertProvider(type=CoeListPOSqlProvider.class, method="insertSelective")
    int insertSelective(CoeListPO record);

    @SelectProvider(type=CoeListPOSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="coe_id", property="coeId", jdbcType=JdbcType.INTEGER),
        @Result(column="brief", property="brief", jdbcType=JdbcType.VARCHAR),
        @Result(column="occur_date", property="occurDate", jdbcType=JdbcType.DATE),
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
        @Result(column="node_two_org", property="nodeTwoOrg", jdbcType=JdbcType.VARCHAR),
        @Result(column="locator", property="locator", jdbcType=JdbcType.VARCHAR),
        @Result(column="order_loss", property="orderLoss", jdbcType=JdbcType.DECIMAL),
        @Result(column="capital_loss", property="capitalLoss", jdbcType=JdbcType.DECIMAL),
        @Result(column="line_of_business", property="lineOfBusiness", jdbcType=JdbcType.VARCHAR),
        @Result(column="business", property="business", jdbcType=JdbcType.VARCHAR)
    })
    List<CoeListPO> selectByExample(CoeListPOExample example);

    @Select({
        "select",
        "id, coe_id, brief, occur_date, notify_time, find_time, location_time, handle_time, ",
        "solved_time, fminuso_time, lminusf_time, sminush_time, wiki, level, owner_name, ",
        "owner_mis, qa_name, qa_mis, coe_link, category, rd_share, qa_share, join_status, ",
        "appearance, sub_category, all_todo, not_finish_todo, finish_todo, not_finish_todo_task, ",
        "available, org_name, find_date, finder, influence_time, clear_time, node_two_org, ",
        "locator, order_loss, capital_loss, line_of_business, business",
        "from coe_list",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="coe_id", property="coeId", jdbcType=JdbcType.INTEGER),
        @Result(column="brief", property="brief", jdbcType=JdbcType.VARCHAR),
        @Result(column="occur_date", property="occurDate", jdbcType=JdbcType.DATE),
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
        @Result(column="node_two_org", property="nodeTwoOrg", jdbcType=JdbcType.VARCHAR),
        @Result(column="locator", property="locator", jdbcType=JdbcType.VARCHAR),
        @Result(column="order_loss", property="orderLoss", jdbcType=JdbcType.DECIMAL),
        @Result(column="capital_loss", property="capitalLoss", jdbcType=JdbcType.DECIMAL),
        @Result(column="line_of_business", property="lineOfBusiness", jdbcType=JdbcType.VARCHAR),
        @Result(column="business", property="business", jdbcType=JdbcType.VARCHAR)
    })
    CoeListPO selectByPrimaryKey(Integer id);

    @UpdateProvider(type=CoeListPOSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") CoeListPO record, @Param("example") CoeListPOExample example);

    @UpdateProvider(type=CoeListPOSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") CoeListPO record, @Param("example") CoeListPOExample example);

    @UpdateProvider(type=CoeListPOSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(CoeListPO record);

    @Update({
        "update coe_list",
        "set coe_id = #{coeId,jdbcType=INTEGER},",
          "brief = #{brief,jdbcType=VARCHAR},",
          "occur_date = #{occurDate,jdbcType=DATE},",
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
          "node_two_org = #{nodeTwoOrg,jdbcType=VARCHAR},",
          "locator = #{locator,jdbcType=VARCHAR},",
          "order_loss = #{orderLoss,jdbcType=DECIMAL},",
          "capital_loss = #{capitalLoss,jdbcType=DECIMAL},",
          "line_of_business = #{lineOfBusiness,jdbcType=VARCHAR},",
          "business = #{business,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(CoeListPO record);



    @Select({
            "select",
            "id, coe_id, brief, occur_date, notify_time, find_time, location_time, handle_time, ",
            "solved_time, fminuso_time, lminusf_time, sminush_time, wiki, level, owner_name, ",
            "owner_mis, qa_name, qa_mis, coe_link, category, rd_share, qa_share, join_status, ",
            "appearance, sub_category, all_todo, not_finish_todo, finish_todo, not_finish_todo_task, ",
            "available, org_name, find_date, finder, influence_time, clear_time, node_two_org, ",
            "locator, order_loss, capital_loss, line_of_business,business",
            "from coe_list",
            "where available=1 and occur_date >= #{occur,jdbcType=DATE}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="coe_id", property="coeId", jdbcType=JdbcType.INTEGER),
            @Result(column="brief", property="brief", jdbcType=JdbcType.VARCHAR),
            @Result(column="occur_date", property="occurDate", jdbcType=JdbcType.DATE),
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
            @Result(column="business", property="business", jdbcType=JdbcType.VARCHAR),
            @Result(column="order_loss", property="orderLoss", jdbcType=JdbcType.DECIMAL),
            @Result(column="capital_loss", property="capitalLoss", jdbcType=JdbcType.DECIMAL),
            @Result(column="line_of_business", property="lineOfBusiness", jdbcType=JdbcType.VARCHAR)
    })
    List<CoeListPO> selectByDate(@Param("occur") java.util.Date occur);

    @Select({
            "select",
            "id, coe_id, brief, occur_date, notify_time, find_time, location_time, handle_time, ",
            "solved_time, fminuso_time, lminusf_time, sminush_time, wiki, level, owner_name, ",
            "owner_mis, qa_name, qa_mis, coe_link, category, rd_share, qa_share, join_status, ",
            "appearance, sub_category, all_todo, not_finish_todo, finish_todo, not_finish_todo_task, ",
            "available, org_name, find_date, finder, influence_time, clear_time, node_two_org, ",
            "locator, order_loss, capital_loss, line_of_business,business",
            "from coe_list",
            "where coe_id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="coe_id", property="coeId", jdbcType=JdbcType.INTEGER),
            @Result(column="brief", property="brief", jdbcType=JdbcType.VARCHAR),
            @Result(column="occur_date", property="occurDate", jdbcType=JdbcType.DATE),
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
            @Result(column="node_two_org", property="nodeTwoOrg", jdbcType=JdbcType.VARCHAR),
            @Result(column="locator", property="locator", jdbcType=JdbcType.VARCHAR),
            @Result(column="order_loss", property="orderLoss", jdbcType=JdbcType.DECIMAL),
            @Result(column="capital_loss", property="capitalLoss", jdbcType=JdbcType.DECIMAL),
            @Result(column="line_of_business", property="lineOfBusiness", jdbcType=JdbcType.VARCHAR),
            @Result(column="business", property="business", jdbcType=JdbcType.VARCHAR)
    })
    CoeListPO selectByCoeId(Integer id);


    @Update({
            "update coe_list",
            "set available = 0",
            "where coe_id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="coe_id", property="coeId", jdbcType=JdbcType.INTEGER)
    })
    int updateCoeToInvalidByCoeId(Integer id);

    @Update({
            "update coe_list",
            "set available = 1",
            "where coe_id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="coe_id", property="coeId", jdbcType=JdbcType.INTEGER)
    })
    int updateCoeToValidByCoeId(Integer id);

    @Update({
            "update coe_list",
            "set line_of_business = #{line_of_business,jdbcType=VARCHAR}",
            "where coe_id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="coe_id", property="coeId", jdbcType=JdbcType.INTEGER),
            @Result(column="line_of_business", property="lineOfBusiness", jdbcType=JdbcType.VARCHAR)
    })
    int updateBusiness(@Param("id") Integer id,@Param("line_of_business") String line_of_business);

    @Update({
            "update coe_list",
            "set business = #{business,jdbcType=VARCHAR}",
            "where coe_id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="coe_id", property="coeId", jdbcType=JdbcType.INTEGER),
            @Result(column="business", property="business", jdbcType=JdbcType.VARCHAR)
    })
    int updateBusiness2(@Param("id") Integer id,@Param("business") String business);

    @Update({
            "update coe_list",
            "set order_loss = #{order,jdbcType=INTEGER}",
            "where coe_id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="coe_id", property="coeId", jdbcType=JdbcType.INTEGER),
            @Result(column="order_loss", property="orderLoss", jdbcType=JdbcType.DECIMAL)
    })
    int updateOrder(@Param("id") Integer id,@Param("order") Integer order);

    @Update({
            "update coe_list",
            "set capital_loss = #{money,jdbcType=DECIMAL}",
            "where coe_id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="coe_id", property="coeId", jdbcType=JdbcType.INTEGER),
            @Result(column="capital_loss", property="capitalLoss", jdbcType=JdbcType.DECIMAL),
    })
    int updateMoney(@Param("id") Integer id,@Param("money") Integer money);

    @Select({
            "select coe_id from coe_list"
    })
    @Results({
            @Result(column="coe_id", property="coeId", jdbcType=JdbcType.INTEGER)
    })
    List<Integer> selectCoeIdList();
}