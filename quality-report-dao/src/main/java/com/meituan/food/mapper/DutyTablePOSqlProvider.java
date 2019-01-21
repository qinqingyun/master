package com.meituan.food.mapper;

import com.meituan.food.po.DutyTablePO;
import com.meituan.food.po.DutyTablePOExample.Criteria;
import com.meituan.food.po.DutyTablePOExample.Criterion;
import com.meituan.food.po.DutyTablePOExample;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class DutyTablePOSqlProvider {

    public String countByExample(DutyTablePOExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("duty_table");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(DutyTablePOExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("duty_table");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(DutyTablePO record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("duty_table");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getDutyName() != null) {
            sql.VALUES("duty_name", "#{dutyName,jdbcType=VARCHAR}");
        }
        
        if (record.getDutyMis() != null) {
            sql.VALUES("duty_mis", "#{dutyMis,jdbcType=VARCHAR}");
        }
        
        if (record.getIsOnDuty() != null) {
            sql.VALUES("is_on_duty", "#{isOnDuty,jdbcType=BIT}");
        }
        
        if (record.getDutyDate() != null) {
            sql.VALUES("duty_date", "#{dutyDate,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String selectByExample(DutyTablePOExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("duty_name");
        sql.SELECT("duty_mis");
        sql.SELECT("is_on_duty");
        sql.SELECT("duty_date");
        sql.FROM("duty_table");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        DutyTablePO record = (DutyTablePO) parameter.get("record");
        DutyTablePOExample example = (DutyTablePOExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("duty_table");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=INTEGER}");
        }
        
        if (record.getDutyName() != null) {
            sql.SET("duty_name = #{record.dutyName,jdbcType=VARCHAR}");
        }
        
        if (record.getDutyMis() != null) {
            sql.SET("duty_mis = #{record.dutyMis,jdbcType=VARCHAR}");
        }
        
        if (record.getIsOnDuty() != null) {
            sql.SET("is_on_duty = #{record.isOnDuty,jdbcType=BIT}");
        }
        
        if (record.getDutyDate() != null) {
            sql.SET("duty_date = #{record.dutyDate,jdbcType=VARCHAR}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("duty_table");
        
        sql.SET("id = #{record.id,jdbcType=INTEGER}");
        sql.SET("duty_name = #{record.dutyName,jdbcType=VARCHAR}");
        sql.SET("duty_mis = #{record.dutyMis,jdbcType=VARCHAR}");
        sql.SET("is_on_duty = #{record.isOnDuty,jdbcType=BIT}");
        sql.SET("duty_date = #{record.dutyDate,jdbcType=VARCHAR}");
        
        DutyTablePOExample example = (DutyTablePOExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(DutyTablePO record) {
        SQL sql = new SQL();
        sql.UPDATE("duty_table");
        
        if (record.getDutyName() != null) {
            sql.SET("duty_name = #{dutyName,jdbcType=VARCHAR}");
        }
        
        if (record.getDutyMis() != null) {
            sql.SET("duty_mis = #{dutyMis,jdbcType=VARCHAR}");
        }
        
        if (record.getIsOnDuty() != null) {
            sql.SET("is_on_duty = #{isOnDuty,jdbcType=BIT}");
        }
        
        if (record.getDutyDate() != null) {
            sql.SET("duty_date = #{dutyDate,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }

    protected void applyWhere(SQL sql, DutyTablePOExample example, boolean includeExamplePhrase) {
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