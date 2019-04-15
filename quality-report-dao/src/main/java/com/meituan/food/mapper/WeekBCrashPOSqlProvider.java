package com.meituan.food.mapper;

import com.meituan.food.po.WeekBCrashPO;
import com.meituan.food.po.WeekBCrashPOExample.Criteria;
import com.meituan.food.po.WeekBCrashPOExample.Criterion;
import com.meituan.food.po.WeekBCrashPOExample;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class WeekBCrashPOSqlProvider {

    public String countByExample(WeekBCrashPOExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("week_b_crash");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(WeekBCrashPOExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("week_b_crash");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(WeekBCrashPO record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("week_b_crash");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getbCrashRate() != null) {
            sql.VALUES("b_crash_rate", "#{bCrashRate,jdbcType=DECIMAL}");
        }
        
        if (record.getbDianping() != null) {
            sql.VALUES("b_dianping", "#{bDianping,jdbcType=DECIMAL}");
        }
        
        if (record.getbWaimai() != null) {
            sql.VALUES("b_waimai", "#{bWaimai,jdbcType=DECIMAL}");
        }
        
        if (record.getPlatform() != null) {
            sql.VALUES("platform", "#{platform,jdbcType=VARCHAR}");
        }
        
        if (record.getOs() != null) {
            sql.VALUES("os", "#{os,jdbcType=VARCHAR}");
        }
        
        if (record.getbCrashCount() != null) {
            sql.VALUES("b_crash_count", "#{bCrashCount,jdbcType=INTEGER}");
        }
        
        if (record.getbDianpingCrashCount() != null) {
            sql.VALUES("b_dianping_crash_count", "#{bDianpingCrashCount,jdbcType=INTEGER}");
        }
        
        if (record.getbWaimaiCrashCount() != null) {
            sql.VALUES("b_waimai_crash_count", "#{bWaimaiCrashCount,jdbcType=INTEGER}");
        }
        
        if (record.getStartDate() != null) {
            sql.VALUES("start_date", "#{startDate,jdbcType=VARCHAR}");
        }
        
        if (record.getEndDate() != null) {
            sql.VALUES("end_date", "#{endDate,jdbcType=VARCHAR}");
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

    public String selectByExample(WeekBCrashPOExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("b_crash_rate");
        sql.SELECT("b_dianping");
        sql.SELECT("b_waimai");
        sql.SELECT("platform");
        sql.SELECT("os");
        sql.SELECT("b_crash_count");
        sql.SELECT("b_dianping_crash_count");
        sql.SELECT("b_waimai_crash_count");
        sql.SELECT("start_date");
        sql.SELECT("end_date");
        sql.SELECT("created_at");
        sql.SELECT("date_range");
        sql.SELECT("flag");
        sql.FROM("week_b_crash");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        WeekBCrashPO record = (WeekBCrashPO) parameter.get("record");
        WeekBCrashPOExample example = (WeekBCrashPOExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("week_b_crash");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=INTEGER}");
        }
        
        if (record.getbCrashRate() != null) {
            sql.SET("b_crash_rate = #{record.bCrashRate,jdbcType=DECIMAL}");
        }
        
        if (record.getbDianping() != null) {
            sql.SET("b_dianping = #{record.bDianping,jdbcType=DECIMAL}");
        }
        
        if (record.getbWaimai() != null) {
            sql.SET("b_waimai = #{record.bWaimai,jdbcType=DECIMAL}");
        }
        
        if (record.getPlatform() != null) {
            sql.SET("platform = #{record.platform,jdbcType=VARCHAR}");
        }
        
        if (record.getOs() != null) {
            sql.SET("os = #{record.os,jdbcType=VARCHAR}");
        }
        
        if (record.getbCrashCount() != null) {
            sql.SET("b_crash_count = #{record.bCrashCount,jdbcType=INTEGER}");
        }
        
        if (record.getbDianpingCrashCount() != null) {
            sql.SET("b_dianping_crash_count = #{record.bDianpingCrashCount,jdbcType=INTEGER}");
        }
        
        if (record.getbWaimaiCrashCount() != null) {
            sql.SET("b_waimai_crash_count = #{record.bWaimaiCrashCount,jdbcType=INTEGER}");
        }
        
        if (record.getStartDate() != null) {
            sql.SET("start_date = #{record.startDate,jdbcType=VARCHAR}");
        }
        
        if (record.getEndDate() != null) {
            sql.SET("end_date = #{record.endDate,jdbcType=VARCHAR}");
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
        sql.UPDATE("week_b_crash");
        
        sql.SET("id = #{record.id,jdbcType=INTEGER}");
        sql.SET("b_crash_rate = #{record.bCrashRate,jdbcType=DECIMAL}");
        sql.SET("b_dianping = #{record.bDianping,jdbcType=DECIMAL}");
        sql.SET("b_waimai = #{record.bWaimai,jdbcType=DECIMAL}");
        sql.SET("platform = #{record.platform,jdbcType=VARCHAR}");
        sql.SET("os = #{record.os,jdbcType=VARCHAR}");
        sql.SET("b_crash_count = #{record.bCrashCount,jdbcType=INTEGER}");
        sql.SET("b_dianping_crash_count = #{record.bDianpingCrashCount,jdbcType=INTEGER}");
        sql.SET("b_waimai_crash_count = #{record.bWaimaiCrashCount,jdbcType=INTEGER}");
        sql.SET("start_date = #{record.startDate,jdbcType=VARCHAR}");
        sql.SET("end_date = #{record.endDate,jdbcType=VARCHAR}");
        sql.SET("created_at = #{record.createdAt,jdbcType=TIMESTAMP}");
        sql.SET("date_range = #{record.dateRange,jdbcType=VARCHAR}");
        sql.SET("flag = #{record.flag,jdbcType=INTEGER}");
        
        WeekBCrashPOExample example = (WeekBCrashPOExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(WeekBCrashPO record) {
        SQL sql = new SQL();
        sql.UPDATE("week_b_crash");
        
        if (record.getbCrashRate() != null) {
            sql.SET("b_crash_rate = #{bCrashRate,jdbcType=DECIMAL}");
        }
        
        if (record.getbDianping() != null) {
            sql.SET("b_dianping = #{bDianping,jdbcType=DECIMAL}");
        }
        
        if (record.getbWaimai() != null) {
            sql.SET("b_waimai = #{bWaimai,jdbcType=DECIMAL}");
        }
        
        if (record.getPlatform() != null) {
            sql.SET("platform = #{platform,jdbcType=VARCHAR}");
        }
        
        if (record.getOs() != null) {
            sql.SET("os = #{os,jdbcType=VARCHAR}");
        }
        
        if (record.getbCrashCount() != null) {
            sql.SET("b_crash_count = #{bCrashCount,jdbcType=INTEGER}");
        }
        
        if (record.getbDianpingCrashCount() != null) {
            sql.SET("b_dianping_crash_count = #{bDianpingCrashCount,jdbcType=INTEGER}");
        }
        
        if (record.getbWaimaiCrashCount() != null) {
            sql.SET("b_waimai_crash_count = #{bWaimaiCrashCount,jdbcType=INTEGER}");
        }
        
        if (record.getStartDate() != null) {
            sql.SET("start_date = #{startDate,jdbcType=VARCHAR}");
        }
        
        if (record.getEndDate() != null) {
            sql.SET("end_date = #{endDate,jdbcType=VARCHAR}");
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

    protected void applyWhere(SQL sql, WeekBCrashPOExample example, boolean includeExamplePhrase) {
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