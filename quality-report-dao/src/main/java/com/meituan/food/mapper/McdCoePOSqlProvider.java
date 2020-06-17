package com.meituan.food.mapper;

import com.meituan.food.po.McdCoePO;
import com.meituan.food.po.McdCoePOExample.Criteria;
import com.meituan.food.po.McdCoePOExample.Criterion;
import com.meituan.food.po.McdCoePOExample;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class McdCoePOSqlProvider {

    public String countByExample(McdCoePOExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("mcd_coe_list");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(McdCoePOExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("mcd_coe_list");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(McdCoePO record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("mcd_coe_list");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getCoeId() != null) {
            sql.VALUES("coe_id", "#{coeId,jdbcType=INTEGER}");
        }
        
        if (record.getBrief() != null) {
            sql.VALUES("brief", "#{brief,jdbcType=VARCHAR}");
        }
        
        if (record.getOccurDate() != null) {
            sql.VALUES("occur_date", "#{occurDate,jdbcType=DATE}");
        }
        
        if (record.getNotifyTime() != null) {
            sql.VALUES("notify_time", "#{notifyTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getFindTime() != null) {
            sql.VALUES("find_time", "#{findTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getLocationTime() != null) {
            sql.VALUES("location_time", "#{locationTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getHandleTime() != null) {
            sql.VALUES("handle_time", "#{handleTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getSolvedTime() != null) {
            sql.VALUES("solved_time", "#{solvedTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getFminusoTime() != null) {
            sql.VALUES("fminuso_time", "#{fminusoTime,jdbcType=INTEGER}");
        }
        
        if (record.getLminusfTime() != null) {
            sql.VALUES("lminusf_time", "#{lminusfTime,jdbcType=INTEGER}");
        }
        
        if (record.getSminushTime() != null) {
            sql.VALUES("sminush_time", "#{sminushTime,jdbcType=INTEGER}");
        }
        
        if (record.getWiki() != null) {
            sql.VALUES("wiki", "#{wiki,jdbcType=VARCHAR}");
        }
        
        if (record.getLevel() != null) {
            sql.VALUES("level", "#{level,jdbcType=VARCHAR}");
        }
        
        if (record.getOwnerName() != null) {
            sql.VALUES("owner_name", "#{ownerName,jdbcType=VARCHAR}");
        }
        
        if (record.getOwnerMis() != null) {
            sql.VALUES("owner_mis", "#{ownerMis,jdbcType=VARCHAR}");
        }
        
        if (record.getQaName() != null) {
            sql.VALUES("qa_name", "#{qaName,jdbcType=VARCHAR}");
        }
        
        if (record.getQaMis() != null) {
            sql.VALUES("qa_mis", "#{qaMis,jdbcType=VARCHAR}");
        }
        
        if (record.getCoeLink() != null) {
            sql.VALUES("coe_link", "#{coeLink,jdbcType=VARCHAR}");
        }
        
        if (record.getCategory() != null) {
            sql.VALUES("category", "#{category,jdbcType=VARCHAR}");
        }
        
        if (record.getRdShare() != null) {
            sql.VALUES("rd_share", "#{rdShare,jdbcType=DECIMAL}");
        }
        
        if (record.getQaShare() != null) {
            sql.VALUES("qa_share", "#{qaShare,jdbcType=DECIMAL}");
        }
        
        if (record.getJoinStatus() != null) {
            sql.VALUES("join_status", "#{joinStatus,jdbcType=BIT}");
        }
        
        if (record.getAppearance() != null) {
            sql.VALUES("appearance", "#{appearance,jdbcType=VARCHAR}");
        }
        
        if (record.getSubCategory() != null) {
            sql.VALUES("sub_category", "#{subCategory,jdbcType=VARCHAR}");
        }
        
        if (record.getAllTodo() != null) {
            sql.VALUES("all_todo", "#{allTodo,jdbcType=INTEGER}");
        }
        
        if (record.getNotFinishTodo() != null) {
            sql.VALUES("not_finish_todo", "#{notFinishTodo,jdbcType=INTEGER}");
        }
        
        if (record.getFinishTodo() != null) {
            sql.VALUES("finish_todo", "#{finishTodo,jdbcType=INTEGER}");
        }
        
        if (record.getNotFinishTodoTask() != null) {
            sql.VALUES("not_finish_todo_task", "#{notFinishTodoTask,jdbcType=VARCHAR}");
        }
        
        if (record.getAvailable() != null) {
            sql.VALUES("available", "#{available,jdbcType=BIT}");
        }
        
        if (record.getOrgName() != null) {
            sql.VALUES("org_name", "#{orgName,jdbcType=VARCHAR}");
        }
        
        if (record.getFindDate() != null) {
            sql.VALUES("find_date", "#{findDate,jdbcType=DATE}");
        }
        
        if (record.getFinder() != null) {
            sql.VALUES("finder", "#{finder,jdbcType=VARCHAR}");
        }
        
        if (record.getInfluenceTime() != null) {
            sql.VALUES("influence_time", "#{influenceTime,jdbcType=INTEGER}");
        }
        
        if (record.getClearTime() != null) {
            sql.VALUES("clear_time", "#{clearTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getLocator() != null) {
            sql.VALUES("locator", "#{locator,jdbcType=VARCHAR}");
        }
        
        if (record.getOrderLoss() != null) {
            sql.VALUES("order_loss", "#{orderLoss,jdbcType=DECIMAL}");
        }
        
        if (record.getCapitalLoss() != null) {
            sql.VALUES("capital_loss", "#{capitalLoss,jdbcType=DECIMAL}");
        }
        
        if (record.getCouponLoss() != null) {
            sql.VALUES("coupon_loss", "#{couponLoss,jdbcType=VARCHAR}");
        }
        
        if (record.getOnlineDiscovery() != null) {
            sql.VALUES("online_discovery", "#{onlineDiscovery,jdbcType=VARCHAR}");
        }
        
        if (record.getOnlineClassification() != null) {
            sql.VALUES("online_classification", "#{onlineClassification,jdbcType=VARCHAR}");
        }
        
        if (record.getLine() != null) {
            sql.VALUES("line", "#{line,jdbcType=VARCHAR}");
        }
        
        if (record.getCustomLevel() != null) {
            sql.VALUES("custom_level", "#{customLevel,jdbcType=VARCHAR}");
        }
        
        if (record.getNofundReason() != null) {
            sql.VALUES("nofund_reason", "#{nofundReason,jdbcType=VARCHAR}");
        }
        
        if (record.getMcdId() != null) {
            sql.VALUES("mcd_id", "#{mcdId,jdbcType=INTEGER}");
        }
        
        if (record.getMcdName() != null) {
            sql.VALUES("mcd_name", "#{mcdName,jdbcType=INTEGER}");
        }
        
        return sql.toString();
    }

    public String selectByExample(McdCoePOExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("coe_id");
        sql.SELECT("brief");
        sql.SELECT("occur_date");
        sql.SELECT("notify_time");
        sql.SELECT("find_time");
        sql.SELECT("location_time");
        sql.SELECT("handle_time");
        sql.SELECT("solved_time");
        sql.SELECT("fminuso_time");
        sql.SELECT("lminusf_time");
        sql.SELECT("sminush_time");
        sql.SELECT("wiki");
        sql.SELECT("level");
        sql.SELECT("owner_name");
        sql.SELECT("owner_mis");
        sql.SELECT("qa_name");
        sql.SELECT("qa_mis");
        sql.SELECT("coe_link");
        sql.SELECT("category");
        sql.SELECT("rd_share");
        sql.SELECT("qa_share");
        sql.SELECT("join_status");
        sql.SELECT("appearance");
        sql.SELECT("sub_category");
        sql.SELECT("all_todo");
        sql.SELECT("not_finish_todo");
        sql.SELECT("finish_todo");
        sql.SELECT("not_finish_todo_task");
        sql.SELECT("available");
        sql.SELECT("org_name");
        sql.SELECT("find_date");
        sql.SELECT("finder");
        sql.SELECT("influence_time");
        sql.SELECT("clear_time");
        sql.SELECT("locator");
        sql.SELECT("order_loss");
        sql.SELECT("capital_loss");
        sql.SELECT("coupon_loss");
        sql.SELECT("online_discovery");
        sql.SELECT("online_classification");
        sql.SELECT("line");
        sql.SELECT("custom_level");
        sql.SELECT("nofund_reason");
        sql.SELECT("mcd_id");
        sql.SELECT("mcd_name");
        sql.FROM("mcd_coe_list");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        McdCoePO record = (McdCoePO) parameter.get("record");
        McdCoePOExample example = (McdCoePOExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("mcd_coe_list");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=INTEGER}");
        }
        
        if (record.getCoeId() != null) {
            sql.SET("coe_id = #{record.coeId,jdbcType=INTEGER}");
        }
        
        if (record.getBrief() != null) {
            sql.SET("brief = #{record.brief,jdbcType=VARCHAR}");
        }
        
        if (record.getOccurDate() != null) {
            sql.SET("occur_date = #{record.occurDate,jdbcType=DATE}");
        }
        
        if (record.getNotifyTime() != null) {
            sql.SET("notify_time = #{record.notifyTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getFindTime() != null) {
            sql.SET("find_time = #{record.findTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getLocationTime() != null) {
            sql.SET("location_time = #{record.locationTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getHandleTime() != null) {
            sql.SET("handle_time = #{record.handleTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getSolvedTime() != null) {
            sql.SET("solved_time = #{record.solvedTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getFminusoTime() != null) {
            sql.SET("fminuso_time = #{record.fminusoTime,jdbcType=INTEGER}");
        }
        
        if (record.getLminusfTime() != null) {
            sql.SET("lminusf_time = #{record.lminusfTime,jdbcType=INTEGER}");
        }
        
        if (record.getSminushTime() != null) {
            sql.SET("sminush_time = #{record.sminushTime,jdbcType=INTEGER}");
        }
        
        if (record.getWiki() != null) {
            sql.SET("wiki = #{record.wiki,jdbcType=VARCHAR}");
        }
        
        if (record.getLevel() != null) {
            sql.SET("level = #{record.level,jdbcType=VARCHAR}");
        }
        
        if (record.getOwnerName() != null) {
            sql.SET("owner_name = #{record.ownerName,jdbcType=VARCHAR}");
        }
        
        if (record.getOwnerMis() != null) {
            sql.SET("owner_mis = #{record.ownerMis,jdbcType=VARCHAR}");
        }
        
        if (record.getQaName() != null) {
            sql.SET("qa_name = #{record.qaName,jdbcType=VARCHAR}");
        }
        
        if (record.getQaMis() != null) {
            sql.SET("qa_mis = #{record.qaMis,jdbcType=VARCHAR}");
        }
        
        if (record.getCoeLink() != null) {
            sql.SET("coe_link = #{record.coeLink,jdbcType=VARCHAR}");
        }
        
        if (record.getCategory() != null) {
            sql.SET("category = #{record.category,jdbcType=VARCHAR}");
        }
        
        if (record.getRdShare() != null) {
            sql.SET("rd_share = #{record.rdShare,jdbcType=DECIMAL}");
        }
        
        if (record.getQaShare() != null) {
            sql.SET("qa_share = #{record.qaShare,jdbcType=DECIMAL}");
        }
        
        if (record.getJoinStatus() != null) {
            sql.SET("join_status = #{record.joinStatus,jdbcType=BIT}");
        }
        
        if (record.getAppearance() != null) {
            sql.SET("appearance = #{record.appearance,jdbcType=VARCHAR}");
        }
        
        if (record.getSubCategory() != null) {
            sql.SET("sub_category = #{record.subCategory,jdbcType=VARCHAR}");
        }
        
        if (record.getAllTodo() != null) {
            sql.SET("all_todo = #{record.allTodo,jdbcType=INTEGER}");
        }
        
        if (record.getNotFinishTodo() != null) {
            sql.SET("not_finish_todo = #{record.notFinishTodo,jdbcType=INTEGER}");
        }
        
        if (record.getFinishTodo() != null) {
            sql.SET("finish_todo = #{record.finishTodo,jdbcType=INTEGER}");
        }
        
        if (record.getNotFinishTodoTask() != null) {
            sql.SET("not_finish_todo_task = #{record.notFinishTodoTask,jdbcType=VARCHAR}");
        }
        
        if (record.getAvailable() != null) {
            sql.SET("available = #{record.available,jdbcType=BIT}");
        }
        
        if (record.getOrgName() != null) {
            sql.SET("org_name = #{record.orgName,jdbcType=VARCHAR}");
        }
        
        if (record.getFindDate() != null) {
            sql.SET("find_date = #{record.findDate,jdbcType=DATE}");
        }
        
        if (record.getFinder() != null) {
            sql.SET("finder = #{record.finder,jdbcType=VARCHAR}");
        }
        
        if (record.getInfluenceTime() != null) {
            sql.SET("influence_time = #{record.influenceTime,jdbcType=INTEGER}");
        }
        
        if (record.getClearTime() != null) {
            sql.SET("clear_time = #{record.clearTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getLocator() != null) {
            sql.SET("locator = #{record.locator,jdbcType=VARCHAR}");
        }
        
        if (record.getOrderLoss() != null) {
            sql.SET("order_loss = #{record.orderLoss,jdbcType=DECIMAL}");
        }
        
        if (record.getCapitalLoss() != null) {
            sql.SET("capital_loss = #{record.capitalLoss,jdbcType=DECIMAL}");
        }
        
        if (record.getCouponLoss() != null) {
            sql.SET("coupon_loss = #{record.couponLoss,jdbcType=VARCHAR}");
        }
        
        if (record.getOnlineDiscovery() != null) {
            sql.SET("online_discovery = #{record.onlineDiscovery,jdbcType=VARCHAR}");
        }
        
        if (record.getOnlineClassification() != null) {
            sql.SET("online_classification = #{record.onlineClassification,jdbcType=VARCHAR}");
        }
        
        if (record.getLine() != null) {
            sql.SET("line = #{record.line,jdbcType=VARCHAR}");
        }
        
        if (record.getCustomLevel() != null) {
            sql.SET("custom_level = #{record.customLevel,jdbcType=VARCHAR}");
        }
        
        if (record.getNofundReason() != null) {
            sql.SET("nofund_reason = #{record.nofundReason,jdbcType=VARCHAR}");
        }
        
        if (record.getMcdId() != null) {
            sql.SET("mcd_id = #{record.mcdId,jdbcType=INTEGER}");
        }
        
        if (record.getMcdName() != null) {
            sql.SET("mcd_name = #{record.mcdName,jdbcType=INTEGER}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("mcd_coe_list");
        
        sql.SET("id = #{record.id,jdbcType=INTEGER}");
        sql.SET("coe_id = #{record.coeId,jdbcType=INTEGER}");
        sql.SET("brief = #{record.brief,jdbcType=VARCHAR}");
        sql.SET("occur_date = #{record.occurDate,jdbcType=DATE}");
        sql.SET("notify_time = #{record.notifyTime,jdbcType=TIMESTAMP}");
        sql.SET("find_time = #{record.findTime,jdbcType=TIMESTAMP}");
        sql.SET("location_time = #{record.locationTime,jdbcType=TIMESTAMP}");
        sql.SET("handle_time = #{record.handleTime,jdbcType=TIMESTAMP}");
        sql.SET("solved_time = #{record.solvedTime,jdbcType=TIMESTAMP}");
        sql.SET("fminuso_time = #{record.fminusoTime,jdbcType=INTEGER}");
        sql.SET("lminusf_time = #{record.lminusfTime,jdbcType=INTEGER}");
        sql.SET("sminush_time = #{record.sminushTime,jdbcType=INTEGER}");
        sql.SET("wiki = #{record.wiki,jdbcType=VARCHAR}");
        sql.SET("level = #{record.level,jdbcType=VARCHAR}");
        sql.SET("owner_name = #{record.ownerName,jdbcType=VARCHAR}");
        sql.SET("owner_mis = #{record.ownerMis,jdbcType=VARCHAR}");
        sql.SET("qa_name = #{record.qaName,jdbcType=VARCHAR}");
        sql.SET("qa_mis = #{record.qaMis,jdbcType=VARCHAR}");
        sql.SET("coe_link = #{record.coeLink,jdbcType=VARCHAR}");
        sql.SET("category = #{record.category,jdbcType=VARCHAR}");
        sql.SET("rd_share = #{record.rdShare,jdbcType=DECIMAL}");
        sql.SET("qa_share = #{record.qaShare,jdbcType=DECIMAL}");
        sql.SET("join_status = #{record.joinStatus,jdbcType=BIT}");
        sql.SET("appearance = #{record.appearance,jdbcType=VARCHAR}");
        sql.SET("sub_category = #{record.subCategory,jdbcType=VARCHAR}");
        sql.SET("all_todo = #{record.allTodo,jdbcType=INTEGER}");
        sql.SET("not_finish_todo = #{record.notFinishTodo,jdbcType=INTEGER}");
        sql.SET("finish_todo = #{record.finishTodo,jdbcType=INTEGER}");
        sql.SET("not_finish_todo_task = #{record.notFinishTodoTask,jdbcType=VARCHAR}");
        sql.SET("available = #{record.available,jdbcType=BIT}");
        sql.SET("org_name = #{record.orgName,jdbcType=VARCHAR}");
        sql.SET("find_date = #{record.findDate,jdbcType=DATE}");
        sql.SET("finder = #{record.finder,jdbcType=VARCHAR}");
        sql.SET("influence_time = #{record.influenceTime,jdbcType=INTEGER}");
        sql.SET("clear_time = #{record.clearTime,jdbcType=TIMESTAMP}");
        sql.SET("locator = #{record.locator,jdbcType=VARCHAR}");
        sql.SET("order_loss = #{record.orderLoss,jdbcType=DECIMAL}");
        sql.SET("capital_loss = #{record.capitalLoss,jdbcType=DECIMAL}");
        sql.SET("coupon_loss = #{record.couponLoss,jdbcType=VARCHAR}");
        sql.SET("online_discovery = #{record.onlineDiscovery,jdbcType=VARCHAR}");
        sql.SET("online_classification = #{record.onlineClassification,jdbcType=VARCHAR}");
        sql.SET("line = #{record.line,jdbcType=VARCHAR}");
        sql.SET("custom_level = #{record.customLevel,jdbcType=VARCHAR}");
        sql.SET("nofund_reason = #{record.nofundReason,jdbcType=VARCHAR}");
        sql.SET("mcd_id = #{record.mcdId,jdbcType=INTEGER}");
        sql.SET("mcd_name = #{record.mcdName,jdbcType=INTEGER}");
        
        McdCoePOExample example = (McdCoePOExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(McdCoePO record) {
        SQL sql = new SQL();
        sql.UPDATE("mcd_coe_list");
        
        if (record.getCoeId() != null) {
            sql.SET("coe_id = #{coeId,jdbcType=INTEGER}");
        }
        
        if (record.getBrief() != null) {
            sql.SET("brief = #{brief,jdbcType=VARCHAR}");
        }
        
        if (record.getOccurDate() != null) {
            sql.SET("occur_date = #{occurDate,jdbcType=DATE}");
        }
        
        if (record.getNotifyTime() != null) {
            sql.SET("notify_time = #{notifyTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getFindTime() != null) {
            sql.SET("find_time = #{findTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getLocationTime() != null) {
            sql.SET("location_time = #{locationTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getHandleTime() != null) {
            sql.SET("handle_time = #{handleTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getSolvedTime() != null) {
            sql.SET("solved_time = #{solvedTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getFminusoTime() != null) {
            sql.SET("fminuso_time = #{fminusoTime,jdbcType=INTEGER}");
        }
        
        if (record.getLminusfTime() != null) {
            sql.SET("lminusf_time = #{lminusfTime,jdbcType=INTEGER}");
        }
        
        if (record.getSminushTime() != null) {
            sql.SET("sminush_time = #{sminushTime,jdbcType=INTEGER}");
        }
        
        if (record.getWiki() != null) {
            sql.SET("wiki = #{wiki,jdbcType=VARCHAR}");
        }
        
        if (record.getLevel() != null) {
            sql.SET("level = #{level,jdbcType=VARCHAR}");
        }
        
        if (record.getOwnerName() != null) {
            sql.SET("owner_name = #{ownerName,jdbcType=VARCHAR}");
        }
        
        if (record.getOwnerMis() != null) {
            sql.SET("owner_mis = #{ownerMis,jdbcType=VARCHAR}");
        }
        
        if (record.getQaName() != null) {
            sql.SET("qa_name = #{qaName,jdbcType=VARCHAR}");
        }
        
        if (record.getQaMis() != null) {
            sql.SET("qa_mis = #{qaMis,jdbcType=VARCHAR}");
        }
        
        if (record.getCoeLink() != null) {
            sql.SET("coe_link = #{coeLink,jdbcType=VARCHAR}");
        }
        
        if (record.getCategory() != null) {
            sql.SET("category = #{category,jdbcType=VARCHAR}");
        }
        
        if (record.getRdShare() != null) {
            sql.SET("rd_share = #{rdShare,jdbcType=DECIMAL}");
        }
        
        if (record.getQaShare() != null) {
            sql.SET("qa_share = #{qaShare,jdbcType=DECIMAL}");
        }
        
        if (record.getJoinStatus() != null) {
            sql.SET("join_status = #{joinStatus,jdbcType=BIT}");
        }
        
        if (record.getAppearance() != null) {
            sql.SET("appearance = #{appearance,jdbcType=VARCHAR}");
        }
        
        if (record.getSubCategory() != null) {
            sql.SET("sub_category = #{subCategory,jdbcType=VARCHAR}");
        }
        
        if (record.getAllTodo() != null) {
            sql.SET("all_todo = #{allTodo,jdbcType=INTEGER}");
        }
        
        if (record.getNotFinishTodo() != null) {
            sql.SET("not_finish_todo = #{notFinishTodo,jdbcType=INTEGER}");
        }
        
        if (record.getFinishTodo() != null) {
            sql.SET("finish_todo = #{finishTodo,jdbcType=INTEGER}");
        }
        
        if (record.getNotFinishTodoTask() != null) {
            sql.SET("not_finish_todo_task = #{notFinishTodoTask,jdbcType=VARCHAR}");
        }
        
        if (record.getAvailable() != null) {
            sql.SET("available = #{available,jdbcType=BIT}");
        }
        
        if (record.getOrgName() != null) {
            sql.SET("org_name = #{orgName,jdbcType=VARCHAR}");
        }
        
        if (record.getFindDate() != null) {
            sql.SET("find_date = #{findDate,jdbcType=DATE}");
        }
        
        if (record.getFinder() != null) {
            sql.SET("finder = #{finder,jdbcType=VARCHAR}");
        }
        
        if (record.getInfluenceTime() != null) {
            sql.SET("influence_time = #{influenceTime,jdbcType=INTEGER}");
        }
        
        if (record.getClearTime() != null) {
            sql.SET("clear_time = #{clearTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getLocator() != null) {
            sql.SET("locator = #{locator,jdbcType=VARCHAR}");
        }
        
        if (record.getOrderLoss() != null) {
            sql.SET("order_loss = #{orderLoss,jdbcType=DECIMAL}");
        }
        
        if (record.getCapitalLoss() != null) {
            sql.SET("capital_loss = #{capitalLoss,jdbcType=DECIMAL}");
        }
        
        if (record.getCouponLoss() != null) {
            sql.SET("coupon_loss = #{couponLoss,jdbcType=VARCHAR}");
        }
        
        if (record.getOnlineDiscovery() != null) {
            sql.SET("online_discovery = #{onlineDiscovery,jdbcType=VARCHAR}");
        }
        
        if (record.getOnlineClassification() != null) {
            sql.SET("online_classification = #{onlineClassification,jdbcType=VARCHAR}");
        }
        
        if (record.getLine() != null) {
            sql.SET("line = #{line,jdbcType=VARCHAR}");
        }
        
        if (record.getCustomLevel() != null) {
            sql.SET("custom_level = #{customLevel,jdbcType=VARCHAR}");
        }
        
        if (record.getNofundReason() != null) {
            sql.SET("nofund_reason = #{nofundReason,jdbcType=VARCHAR}");
        }
        
        if (record.getMcdId() != null) {
            sql.SET("mcd_id = #{mcdId,jdbcType=INTEGER}");
        }
        
        if (record.getMcdName() != null) {
            sql.SET("mcd_name = #{mcdName,jdbcType=INTEGER}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }

    protected void applyWhere(SQL sql, McdCoePOExample example, boolean includeExamplePhrase) {
        if (example == null) {
            return;
        }
        
        String parmPhrase1;
        String parmPhrase1_th;
        String parmPhrase2;
        String parmPhrase2_th;
        String parmPhrase3;
        String parmPhrase3_th;
        if (includeExamplePhrase) {
            parmPhrase1 = "%s #{example.oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{example.oredCriteria[%d].allCriteria[%d].value} and #{example.oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{example.oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{example.oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{example.oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        } else {
            parmPhrase1 = "%s #{oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{oredCriteria[%d].allCriteria[%d].value} and #{oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        }
        
        StringBuilder sb = new StringBuilder();
        List<Criteria> oredCriteria = example.getOredCriteria();
        boolean firstCriteria = true;
        for (int i = 0; i < oredCriteria.size(); i++) {
            Criteria criteria = oredCriteria.get(i);
            if (criteria.isValid()) {
                if (firstCriteria) {
                    firstCriteria = false;
                } else {
                    sb.append(" or ");
                }
                
                sb.append('(');
                List<Criterion> criterions = criteria.getAllCriteria();
                boolean firstCriterion = true;
                for (int j = 0; j < criterions.size(); j++) {
                    Criterion criterion = criterions.get(j);
                    if (firstCriterion) {
                        firstCriterion = false;
                    } else {
                        sb.append(" and ");
                    }
                    
                    if (criterion.isNoValue()) {
                        sb.append(criterion.getCondition());
                    } else if (criterion.isSingleValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase1, criterion.getCondition(), i, j));
                        } else {
                            sb.append(String.format(parmPhrase1_th, criterion.getCondition(), i, j,criterion.getTypeHandler()));
                        }
                    } else if (criterion.isBetweenValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase2, criterion.getCondition(), i, j, i, j));
                        } else {
                            sb.append(String.format(parmPhrase2_th, criterion.getCondition(), i, j, criterion.getTypeHandler(), i, j, criterion.getTypeHandler()));
                        }
                    } else if (criterion.isListValue()) {
                        sb.append(criterion.getCondition());
                        sb.append(" (");
                        List<?> listItems = (List<?>) criterion.getValue();
                        boolean comma = false;
                        for (int k = 0; k < listItems.size(); k++) {
                            if (comma) {
                                sb.append(", ");
                            } else {
                                comma = true;
                            }
                            if (criterion.getTypeHandler() == null) {
                                sb.append(String.format(parmPhrase3, i, j, k));
                            } else {
                                sb.append(String.format(parmPhrase3_th, i, j, k, criterion.getTypeHandler()));
                            }
                        }
                        sb.append(')');
                    }
                }
                sb.append(')');
            }
        }
        
        if (sb.length() > 0) {
            sql.WHERE(sb.toString());
        }
    }
}