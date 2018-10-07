package com.meituan.food.mapper;

import com.meituan.food.po.WeekIssuePO;
import org.apache.ibatis.jdbc.SQL;

public class WeekIssuePOSqlProvider {

    public String insertSelective(WeekIssuePO record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("week_issue");

        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }

        if (record.getBrief() != null) {
            sql.VALUES("brief", "#{brief,jdbcType=VARCHAR}");
        }

        if (record.getLevel() != null) {
            sql.VALUES("level", "#{level,jdbcType=VARCHAR}");
        }

        if (record.getDepartment() != null) {
            sql.VALUES("department", "#{department,jdbcType=VARCHAR}");
        }

        if (record.getType() != null) {
            sql.VALUES("type", "#{type,jdbcType=VARCHAR}");
        }

        if (record.getWiki() != null) {
            sql.VALUES("wiki", "#{wiki,jdbcType=VARCHAR}");
        }

        if (record.getCreatedAt() != null) {
            sql.VALUES("created_at", "#{createdAt,jdbcType=TIMESTAMP}");
        }

        if (record.getUpdatedAt() != null) {
            sql.VALUES("updated_at", "#{updatedAt,jdbcType=TIMESTAMP}");
        }

        return sql.toString();
    }

    public String updateByPrimaryKeySelective(WeekIssuePO record) {
        SQL sql = new SQL();
        sql.UPDATE("week_issue");

        if (record.getBrief() != null) {
            sql.SET("brief = #{brief,jdbcType=VARCHAR}");
        }

        if (record.getLevel() != null) {
            sql.SET("level = #{level,jdbcType=VARCHAR}");
        }

        if (record.getDepartment() != null) {
            sql.SET("department = #{department,jdbcType=VARCHAR}");
        }

        if (record.getType() != null) {
            sql.SET("type = #{type,jdbcType=VARCHAR}");
        }

        if (record.getWiki() != null) {
            sql.SET("wiki = #{wiki,jdbcType=VARCHAR}");
        }

        if (record.getCreatedAt() != null) {
            sql.SET("created_at = #{createdAt,jdbcType=TIMESTAMP}");
        }

        if (record.getUpdatedAt() != null) {
            sql.SET("updated_at = #{updatedAt,jdbcType=TIMESTAMP}");
        }

        sql.WHERE("id = #{id,jdbcType=INTEGER}");

        return sql.toString();
    }
}