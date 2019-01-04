package com.meituan.food.mapper;

import com.meituan.food.po.EfficiencyBugNumPO;
import com.meituan.food.po.EfficiencyBugNumPOExample.Criteria;
import com.meituan.food.po.EfficiencyBugNumPOExample.Criterion;
import com.meituan.food.po.EfficiencyBugNumPOExample;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class EfficiencyBugNumPOSqlProvider {

    public String countByExample(EfficiencyBugNumPOExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("efficiency_bug_num");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(EfficiencyBugNumPOExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("efficiency_bug_num");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(EfficiencyBugNumPO record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("efficiency_bug_num");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getMis() != null) {
            sql.VALUES("mis", "#{mis,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateNum() != null) {
            sql.VALUES("create_num", "#{createNum,jdbcType=INTEGER}");
        }
        
        if (record.getAcceptNum() != null) {
            sql.VALUES("accept_num", "#{acceptNum,jdbcType=INTEGER}");
        }
        
        if (record.getEfficiencyDate() != null) {
            sql.VALUES("efficiency_date", "#{efficiencyDate,jdbcType=VARCHAR}");
        }
        
        if (record.getCreatedAt() != null) {
            sql.VALUES("created_at", "#{createdAt,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdatedAt() != null) {
            sql.VALUES("updated_at", "#{updatedAt,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    public String selectByExample(EfficiencyBugNumPOExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("mis");
        sql.SELECT("create_num");
        sql.SELECT("accept_num");
        sql.SELECT("efficiency_date");
        sql.SELECT("created_at");
        sql.SELECT("updated_at");
        sql.FROM("efficiency_bug_num");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        EfficiencyBugNumPO record = (EfficiencyBugNumPO) parameter.get("record");
        EfficiencyBugNumPOExample example = (EfficiencyBugNumPOExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("efficiency_bug_num");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=INTEGER}");
        }
        
        if (record.getMis() != null) {
            sql.SET("mis = #{record.mis,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateNum() != null) {
            sql.SET("create_num = #{record.createNum,jdbcType=INTEGER}");
        }
        
        if (record.getAcceptNum() != null) {
            sql.SET("accept_num = #{record.acceptNum,jdbcType=INTEGER}");
        }
        
        if (record.getEfficiencyDate() != null) {
            sql.SET("efficiency_date = #{record.efficiencyDate,jdbcType=VARCHAR}");
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
        sql.UPDATE("efficiency_bug_num");
        
        sql.SET("id = #{record.id,jdbcType=INTEGER}");
        sql.SET("mis = #{record.mis,jdbcType=VARCHAR}");
        sql.SET("create_num = #{record.createNum,jdbcType=INTEGER}");
        sql.SET("accept_num = #{record.acceptNum,jdbcType=INTEGER}");
        sql.SET("efficiency_date = #{record.efficiencyDate,jdbcType=VARCHAR}");
        sql.SET("created_at = #{record.createdAt,jdbcType=TIMESTAMP}");
        sql.SET("updated_at = #{record.updatedAt,jdbcType=TIMESTAMP}");
        
        EfficiencyBugNumPOExample example = (EfficiencyBugNumPOExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(EfficiencyBugNumPO record) {
        SQL sql = new SQL();
        sql.UPDATE("efficiency_bug_num");
        
        if (record.getMis() != null) {
            sql.SET("mis = #{mis,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateNum() != null) {
            sql.SET("create_num = #{createNum,jdbcType=INTEGER}");
        }
        
        if (record.getAcceptNum() != null) {
            sql.SET("accept_num = #{acceptNum,jdbcType=INTEGER}");
        }
        
        if (record.getEfficiencyDate() != null) {
            sql.SET("efficiency_date = #{efficiencyDate,jdbcType=VARCHAR}");
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

    protected void applyWhere(SQL sql, EfficiencyBugNumPOExample example, boolean includeExamplePhrase) {
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