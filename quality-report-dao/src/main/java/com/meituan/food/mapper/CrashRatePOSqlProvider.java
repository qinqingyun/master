package com.meituan.food.mapper;

import com.meituan.food.po.CrashRatePO;
import com.meituan.food.po.CrashRatePOExample.Criteria;
import com.meituan.food.po.CrashRatePOExample.Criterion;
import com.meituan.food.po.CrashRatePOExample;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class CrashRatePOSqlProvider {

    public String countByExample(CrashRatePOExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("crash_rate");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(CrashRatePOExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("crash_rate");
        applyWhere(sql, example, false);
        return sql.toString();
    }

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

    public String selectByExample(CrashRatePOExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("crash");
        sql.SELECT("plantform");
        sql.SELECT("DAU");
        sql.SELECT("crash_rate");
        sql.SELECT("start_date");
        sql.SELECT("end_date");
        sql.SELECT("show_date_range");
        sql.SELECT("created_at");
        sql.SELECT("updated_at");
        sql.FROM("crash_rate");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        CrashRatePO record = (CrashRatePO) parameter.get("record");
        CrashRatePOExample example = (CrashRatePOExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("crash_rate");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=INTEGER}");
        }
        
        if (record.getCrash() != null) {
            sql.SET("crash = #{record.crash,jdbcType=INTEGER}");
        }
        
        if (record.getPlantform() != null) {
            sql.SET("plantform = #{record.plantform,jdbcType=VARCHAR}");
        }
        
        if (record.getDau() != null) {
            sql.SET("DAU = #{record.dau,jdbcType=INTEGER}");
        }
        
        if (record.getCrashRate() != null) {
            sql.SET("crash_rate = #{record.crashRate,jdbcType=VARCHAR}");
        }
        
        if (record.getStartDate() != null) {
            sql.SET("start_date = #{record.startDate,jdbcType=VARCHAR}");
        }
        
        if (record.getEndDate() != null) {
            sql.SET("end_date = #{record.endDate,jdbcType=VARCHAR}");
        }
        
        if (record.getShowDateRange() != null) {
            sql.SET("show_date_range = #{record.showDateRange,jdbcType=VARCHAR}");
        }
        
        if (record.getCreatedAt() != null) {
            sql.SET("created_at = #{record.createdAt,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdatedAt() != null) {
            sql.SET("updated_at = #{record.updatedAt,jdbcType=TIMESTAMP}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("crash_rate");
        
        sql.SET("id = #{record.id,jdbcType=INTEGER}");
        sql.SET("crash = #{record.crash,jdbcType=INTEGER}");
        sql.SET("plantform = #{record.plantform,jdbcType=VARCHAR}");
        sql.SET("DAU = #{record.dau,jdbcType=INTEGER}");
        sql.SET("crash_rate = #{record.crashRate,jdbcType=VARCHAR}");
        sql.SET("start_date = #{record.startDate,jdbcType=VARCHAR}");
        sql.SET("end_date = #{record.endDate,jdbcType=VARCHAR}");
        sql.SET("show_date_range = #{record.showDateRange,jdbcType=VARCHAR}");
        sql.SET("created_at = #{record.createdAt,jdbcType=TIMESTAMP}");
        sql.SET("updated_at = #{record.updatedAt,jdbcType=TIMESTAMP}");
        
        CrashRatePOExample example = (CrashRatePOExample) parameter.get("example");
        applyWhere(sql, example, true);
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

    protected void applyWhere(SQL sql, CrashRatePOExample example, boolean includeExamplePhrase) {
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