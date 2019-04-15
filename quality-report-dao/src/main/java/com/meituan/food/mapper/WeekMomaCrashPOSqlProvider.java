package com.meituan.food.mapper;

import com.meituan.food.po.WeekMomaCrashPO;
import com.meituan.food.po.WeekMomaCrashPOExample.Criteria;
import com.meituan.food.po.WeekMomaCrashPOExample.Criterion;
import com.meituan.food.po.WeekMomaCrashPOExample;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class WeekMomaCrashPOSqlProvider {

    public String countByExample(WeekMomaCrashPOExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("week_moma_crash");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(WeekMomaCrashPOExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("week_moma_crash");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(WeekMomaCrashPO record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("week_moma_crash");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getMomaCrashCount() != null) {
            sql.VALUES("moma_crash_count", "#{momaCrashCount,jdbcType=INTEGER}");
        }
        
        if (record.getMomaCrashRate() != null) {
            sql.VALUES("moma_crash_rate", "#{momaCrashRate,jdbcType=DECIMAL}");
        }
        
        if (record.getBeeCrashCount() != null) {
            sql.VALUES("bee_crash_count", "#{beeCrashCount,jdbcType=INTEGER}");
        }
        
        if (record.getBeeCrashRate() != null) {
            sql.VALUES("bee_crash_rate", "#{beeCrashRate,jdbcType=DECIMAL}");
        }
        
        if (record.getAboluoCrashCount() != null) {
            sql.VALUES("aboluo_crash_count", "#{aboluoCrashCount,jdbcType=INTEGER}");
        }
        
        if (record.getAboluoCrashRate() != null) {
            sql.VALUES("aboluo_crash_rate", "#{aboluoCrashRate,jdbcType=DECIMAL}");
        }
        
        if (record.getStartDate() != null) {
            sql.VALUES("start_date", "#{startDate,jdbcType=VARCHAR}");
        }
        
        if (record.getEndDate() != null) {
            sql.VALUES("end_date", "#{endDate,jdbcType=VARCHAR}");
        }
        
        if (record.getPlatform() != null) {
            sql.VALUES("platform", "#{platform,jdbcType=VARCHAR}");
        }
        
        if (record.getOs() != null) {
            sql.VALUES("os", "#{os,jdbcType=VARCHAR}");
        }
        
        if (record.getCreatedAt() != null) {
            sql.VALUES("created_at", "#{createdAt,jdbcType=TIMESTAMP}");
        }
        
        if (record.getDateRange() != null) {
            sql.VALUES("date_range", "#{dateRange,jdbcType=VARCHAR}");
        }
        
        if (record.getFlag() != null) {
            sql.VALUES("flag", "#{flag,jdbcType=INTEGER}");
        }
        
        return sql.toString();
    }

    public String selectByExample(WeekMomaCrashPOExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("moma_crash_count");
        sql.SELECT("moma_crash_rate");
        sql.SELECT("bee_crash_count");
        sql.SELECT("bee_crash_rate");
        sql.SELECT("aboluo_crash_count");
        sql.SELECT("aboluo_crash_rate");
        sql.SELECT("start_date");
        sql.SELECT("end_date");
        sql.SELECT("platform");
        sql.SELECT("os");
        sql.SELECT("created_at");
        sql.SELECT("date_range");
        sql.SELECT("flag");
        sql.FROM("week_moma_crash");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        WeekMomaCrashPO record = (WeekMomaCrashPO) parameter.get("record");
        WeekMomaCrashPOExample example = (WeekMomaCrashPOExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("week_moma_crash");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=INTEGER}");
        }
        
        if (record.getMomaCrashCount() != null) {
            sql.SET("moma_crash_count = #{record.momaCrashCount,jdbcType=INTEGER}");
        }
        
        if (record.getMomaCrashRate() != null) {
            sql.SET("moma_crash_rate = #{record.momaCrashRate,jdbcType=DECIMAL}");
        }
        
        if (record.getBeeCrashCount() != null) {
            sql.SET("bee_crash_count = #{record.beeCrashCount,jdbcType=INTEGER}");
        }
        
        if (record.getBeeCrashRate() != null) {
            sql.SET("bee_crash_rate = #{record.beeCrashRate,jdbcType=DECIMAL}");
        }
        
        if (record.getAboluoCrashCount() != null) {
            sql.SET("aboluo_crash_count = #{record.aboluoCrashCount,jdbcType=INTEGER}");
        }
        
        if (record.getAboluoCrashRate() != null) {
            sql.SET("aboluo_crash_rate = #{record.aboluoCrashRate,jdbcType=DECIMAL}");
        }
        
        if (record.getStartDate() != null) {
            sql.SET("start_date = #{record.startDate,jdbcType=VARCHAR}");
        }
        
        if (record.getEndDate() != null) {
            sql.SET("end_date = #{record.endDate,jdbcType=VARCHAR}");
        }
        
        if (record.getPlatform() != null) {
            sql.SET("platform = #{record.platform,jdbcType=VARCHAR}");
        }
        
        if (record.getOs() != null) {
            sql.SET("os = #{record.os,jdbcType=VARCHAR}");
        }
        
        if (record.getCreatedAt() != null) {
            sql.SET("created_at = #{record.createdAt,jdbcType=TIMESTAMP}");
        }
        
        if (record.getDateRange() != null) {
            sql.SET("date_range = #{record.dateRange,jdbcType=VARCHAR}");
        }
        
        if (record.getFlag() != null) {
            sql.SET("flag = #{record.flag,jdbcType=INTEGER}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("week_moma_crash");
        
        sql.SET("id = #{record.id,jdbcType=INTEGER}");
        sql.SET("moma_crash_count = #{record.momaCrashCount,jdbcType=INTEGER}");
        sql.SET("moma_crash_rate = #{record.momaCrashRate,jdbcType=DECIMAL}");
        sql.SET("bee_crash_count = #{record.beeCrashCount,jdbcType=INTEGER}");
        sql.SET("bee_crash_rate = #{record.beeCrashRate,jdbcType=DECIMAL}");
        sql.SET("aboluo_crash_count = #{record.aboluoCrashCount,jdbcType=INTEGER}");
        sql.SET("aboluo_crash_rate = #{record.aboluoCrashRate,jdbcType=DECIMAL}");
        sql.SET("start_date = #{record.startDate,jdbcType=VARCHAR}");
        sql.SET("end_date = #{record.endDate,jdbcType=VARCHAR}");
        sql.SET("platform = #{record.platform,jdbcType=VARCHAR}");
        sql.SET("os = #{record.os,jdbcType=VARCHAR}");
        sql.SET("created_at = #{record.createdAt,jdbcType=TIMESTAMP}");
        sql.SET("date_range = #{record.dateRange,jdbcType=VARCHAR}");
        sql.SET("flag = #{record.flag,jdbcType=INTEGER}");
        
        WeekMomaCrashPOExample example = (WeekMomaCrashPOExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(WeekMomaCrashPO record) {
        SQL sql = new SQL();
        sql.UPDATE("week_moma_crash");
        
        if (record.getMomaCrashCount() != null) {
            sql.SET("moma_crash_count = #{momaCrashCount,jdbcType=INTEGER}");
        }
        
        if (record.getMomaCrashRate() != null) {
            sql.SET("moma_crash_rate = #{momaCrashRate,jdbcType=DECIMAL}");
        }
        
        if (record.getBeeCrashCount() != null) {
            sql.SET("bee_crash_count = #{beeCrashCount,jdbcType=INTEGER}");
        }
        
        if (record.getBeeCrashRate() != null) {
            sql.SET("bee_crash_rate = #{beeCrashRate,jdbcType=DECIMAL}");
        }
        
        if (record.getAboluoCrashCount() != null) {
            sql.SET("aboluo_crash_count = #{aboluoCrashCount,jdbcType=INTEGER}");
        }
        
        if (record.getAboluoCrashRate() != null) {
            sql.SET("aboluo_crash_rate = #{aboluoCrashRate,jdbcType=DECIMAL}");
        }
        
        if (record.getStartDate() != null) {
            sql.SET("start_date = #{startDate,jdbcType=VARCHAR}");
        }
        
        if (record.getEndDate() != null) {
            sql.SET("end_date = #{endDate,jdbcType=VARCHAR}");
        }
        
        if (record.getPlatform() != null) {
            sql.SET("platform = #{platform,jdbcType=VARCHAR}");
        }
        
        if (record.getOs() != null) {
            sql.SET("os = #{os,jdbcType=VARCHAR}");
        }
        
        if (record.getCreatedAt() != null) {
            sql.SET("created_at = #{createdAt,jdbcType=TIMESTAMP}");
        }
        
        if (record.getDateRange() != null) {
            sql.SET("date_range = #{dateRange,jdbcType=VARCHAR}");
        }
        
        if (record.getFlag() != null) {
            sql.SET("flag = #{flag,jdbcType=INTEGER}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }

    protected void applyWhere(SQL sql, WeekMomaCrashPOExample example, boolean includeExamplePhrase) {
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