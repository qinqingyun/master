package com.meituan.food.mapper;

import com.meituan.food.po.NewCrashP0;
import com.meituan.food.po.NewCrashP0Example.Criteria;
import com.meituan.food.po.NewCrashP0Example.Criterion;
import com.meituan.food.po.NewCrashP0Example;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class NewCrashP0SqlProvider {

    public String countByExample(NewCrashP0Example example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("new_crash");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(NewCrashP0Example example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("new_crash");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(NewCrashP0 record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("new_crash");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getPlatform() != null) {
            sql.VALUES("platform", "#{platform,jdbcType=VARCHAR}");
        }
        
        if (record.getCrash() != null) {
            sql.VALUES("crash", "#{crash,jdbcType=INTEGER}");
        }
        
        if (record.getCrashRate() != null) {
            sql.VALUES("crash_rate", "#{crashRate,jdbcType=DECIMAL}");
        }
        
        if (record.getCrashDate() != null) {
            sql.VALUES("crash_date", "#{crashDate,jdbcType=DATE}");
        }
        
        if (record.getCreatedTime() != null) {
            sql.VALUES("created_time", "#{createdTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getFlag() != null) {
            sql.VALUES("flag", "#{flag,jdbcType=INTEGER}");
        }
        
        if (record.getDateRange() != null) {
            sql.VALUES("date_range", "#{dateRange,jdbcType=VARCHAR}");
        }
        
        if (record.getOs() != null) {
            sql.VALUES("os", "#{os,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String selectByExample(NewCrashP0Example example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("platform");
        sql.SELECT("crash");
        sql.SELECT("crash_rate");
        sql.SELECT("crash_date");
        sql.SELECT("created_time");
        sql.SELECT("flag");
        sql.SELECT("date_range");
        sql.SELECT("os");
        sql.FROM("new_crash");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        NewCrashP0 record = (NewCrashP0) parameter.get("record");
        NewCrashP0Example example = (NewCrashP0Example) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("new_crash");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=INTEGER}");
        }
        
        if (record.getPlatform() != null) {
            sql.SET("platform = #{record.platform,jdbcType=VARCHAR}");
        }
        
        if (record.getCrash() != null) {
            sql.SET("crash = #{record.crash,jdbcType=INTEGER}");
        }
        
        if (record.getCrashRate() != null) {
            sql.SET("crash_rate = #{record.crashRate,jdbcType=DECIMAL}");
        }
        
        if (record.getCrashDate() != null) {
            sql.SET("crash_date = #{record.crashDate,jdbcType=DATE}");
        }
        
        if (record.getCreatedTime() != null) {
            sql.SET("created_time = #{record.createdTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getFlag() != null) {
            sql.SET("flag = #{record.flag,jdbcType=INTEGER}");
        }
        
        if (record.getDateRange() != null) {
            sql.SET("date_range = #{record.dateRange,jdbcType=VARCHAR}");
        }
        
        if (record.getOs() != null) {
            sql.SET("os = #{record.os,jdbcType=VARCHAR}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("new_crash");
        
        sql.SET("id = #{record.id,jdbcType=INTEGER}");
        sql.SET("platform = #{record.platform,jdbcType=VARCHAR}");
        sql.SET("crash = #{record.crash,jdbcType=INTEGER}");
        sql.SET("crash_rate = #{record.crashRate,jdbcType=DECIMAL}");
        sql.SET("crash_date = #{record.crashDate,jdbcType=DATE}");
        sql.SET("created_time = #{record.createdTime,jdbcType=TIMESTAMP}");
        sql.SET("flag = #{record.flag,jdbcType=INTEGER}");
        sql.SET("date_range = #{record.dateRange,jdbcType=VARCHAR}");
        sql.SET("os = #{record.os,jdbcType=VARCHAR}");
        
        NewCrashP0Example example = (NewCrashP0Example) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(NewCrashP0 record) {
        SQL sql = new SQL();
        sql.UPDATE("new_crash");
        
        if (record.getPlatform() != null) {
            sql.SET("platform = #{platform,jdbcType=VARCHAR}");
        }
        
        if (record.getCrash() != null) {
            sql.SET("crash = #{crash,jdbcType=INTEGER}");
        }
        
        if (record.getCrashRate() != null) {
            sql.SET("crash_rate = #{crashRate,jdbcType=DECIMAL}");
        }
        
        if (record.getCrashDate() != null) {
            sql.SET("crash_date = #{crashDate,jdbcType=DATE}");
        }
        
        if (record.getCreatedTime() != null) {
            sql.SET("created_time = #{createdTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getFlag() != null) {
            sql.SET("flag = #{flag,jdbcType=INTEGER}");
        }
        
        if (record.getDateRange() != null) {
            sql.SET("date_range = #{dateRange,jdbcType=VARCHAR}");
        }
        
        if (record.getOs() != null) {
            sql.SET("os = #{os,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }

    protected void applyWhere(SQL sql, NewCrashP0Example example, boolean includeExamplePhrase) {
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