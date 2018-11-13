package com.meituan.food.mapper;

import com.meituan.food.po.CrashRatePO;
import org.apache.ibatis.jdbc.SQL;

public class CrashRatePOSqlProvider {

    public String insertSelective(CrashRatePO record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("crash_rate");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getCrash() != null) {
            sql.VALUES("crash", "#{crash,jdbcType=INTEGER}");
        }
        
        if (record.getPlantform() != null) {
            sql.VALUES("plantform", "#{plantform,jdbcType=VARCHAR}");
        }
        
        if (record.getDau() != null) {
            sql.VALUES("DAU", "#{dau,jdbcType=INTEGER}");
        }
        
        if (record.getCrashRate() != null) {
            sql.VALUES("crash_rate", "#{crashRate,jdbcType=VARCHAR}");
        }
        
        if (record.getStartDate() != null) {
            sql.VALUES("start_date", "#{startDate,jdbcType=VARCHAR}");
        }
        
        if (record.getEndDate() != null) {
            sql.VALUES("end_date", "#{endDate,jdbcType=VARCHAR}");
        }
        
        if (record.getShowDateRange() != null) {
            sql.VALUES("show_date_range", "#{showDateRange,jdbcType=VARCHAR}");
        }
        
        if (record.getCreatedAt() != null) {
            sql.VALUES("created_at", "#{createdAt,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdatedAt() != null) {
            sql.VALUES("updated_at", "#{updatedAt,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(CrashRatePO record) {
        SQL sql = new SQL();
        sql.UPDATE("crash_rate");
        
        if (record.getCrash() != null) {
            sql.SET("crash = #{crash,jdbcType=INTEGER}");
        }
        
        if (record.getPlantform() != null) {
            sql.SET("plantform = #{plantform,jdbcType=VARCHAR}");
        }
        
        if (record.getDau() != null) {
            sql.SET("DAU = #{dau,jdbcType=INTEGER}");
        }
        
        if (record.getCrashRate() != null) {
            sql.SET("crash_rate = #{crashRate,jdbcType=VARCHAR}");
        }
        
        if (record.getStartDate() != null) {
            sql.SET("start_date = #{startDate,jdbcType=VARCHAR}");
        }
        
        if (record.getEndDate() != null) {
            sql.SET("end_date = #{endDate,jdbcType=VARCHAR}");
        }
        
        if (record.getShowDateRange() != null) {
            sql.SET("show_date_range = #{showDateRange,jdbcType=VARCHAR}");
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