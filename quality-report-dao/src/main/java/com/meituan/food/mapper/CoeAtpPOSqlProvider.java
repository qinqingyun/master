package com.meituan.food.mapper;

import com.meituan.food.po.CoeAtpPO;
import com.meituan.food.po.CoeAtpPOExample.Criteria;
import com.meituan.food.po.CoeAtpPOExample.Criterion;
import com.meituan.food.po.CoeAtpPOExample;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class CoeAtpPOSqlProvider {

    public String countByExample(CoeAtpPOExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("coe_atp_status_table");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(CoeAtpPOExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("coe_atp_status_table");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(CoeAtpPO record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("coe_atp_status_table");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getCoeId() != null) {
            sql.VALUES("coe_id", "#{coeId,jdbcType=INTEGER}");
        }
        
        if (record.getIsPush() != null) {
            sql.VALUES("is_push", "#{isPush,jdbcType=BIT}");
        }
        
        if (record.getFirstPushDate() != null) {
            sql.VALUES("first_push_date", "#{firstPushDate,jdbcType=DATE}");
        }
        
        if (record.getPushText() != null) {
            sql.VALUES("push_text", "#{pushText,jdbcType=VARCHAR}");
        }
        
        if (record.getReceiver() != null) {
            sql.VALUES("receiver", "#{receiver,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String selectByExample(CoeAtpPOExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("coe_id");
        sql.SELECT("is_push");
        sql.SELECT("first_push_date");
        sql.SELECT("push_text");
        sql.SELECT("receiver");
        sql.FROM("coe_atp_status_table");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        CoeAtpPO record = (CoeAtpPO) parameter.get("record");
        CoeAtpPOExample example = (CoeAtpPOExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("coe_atp_status_table");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=INTEGER}");
        }
        
        if (record.getCoeId() != null) {
            sql.SET("coe_id = #{record.coeId,jdbcType=INTEGER}");
        }
        
        if (record.getIsPush() != null) {
            sql.SET("is_push = #{record.isPush,jdbcType=BIT}");
        }
        
        if (record.getFirstPushDate() != null) {
            sql.SET("first_push_date = #{record.firstPushDate,jdbcType=DATE}");
        }
        
        if (record.getPushText() != null) {
            sql.SET("push_text = #{record.pushText,jdbcType=VARCHAR}");
        }
        
        if (record.getReceiver() != null) {
            sql.SET("receiver = #{record.receiver,jdbcType=VARCHAR}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("coe_atp_status_table");
        
        sql.SET("id = #{record.id,jdbcType=INTEGER}");
        sql.SET("coe_id = #{record.coeId,jdbcType=INTEGER}");
        sql.SET("is_push = #{record.isPush,jdbcType=BIT}");
        sql.SET("first_push_date = #{record.firstPushDate,jdbcType=DATE}");
        sql.SET("push_text = #{record.pushText,jdbcType=VARCHAR}");
        sql.SET("receiver = #{record.receiver,jdbcType=VARCHAR}");
        
        CoeAtpPOExample example = (CoeAtpPOExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(CoeAtpPO record) {
        SQL sql = new SQL();
        sql.UPDATE("coe_atp_status_table");
        
        if (record.getCoeId() != null) {
            sql.SET("coe_id = #{coeId,jdbcType=INTEGER}");
        }
        
        if (record.getIsPush() != null) {
            sql.SET("is_push = #{isPush,jdbcType=BIT}");
        }
        
        if (record.getFirstPushDate() != null) {
            sql.SET("first_push_date = #{firstPushDate,jdbcType=DATE}");
        }
        
        if (record.getPushText() != null) {
            sql.SET("push_text = #{pushText,jdbcType=VARCHAR}");
        }
        
        if (record.getReceiver() != null) {
            sql.SET("receiver = #{receiver,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }

    protected void applyWhere(SQL sql, CoeAtpPOExample example, boolean includeExamplePhrase) {
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