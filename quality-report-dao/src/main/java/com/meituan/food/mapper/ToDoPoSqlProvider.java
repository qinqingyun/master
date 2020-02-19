package com.meituan.food.mapper;

import com.meituan.food.po.ToDoPo;
import com.meituan.food.po.ToDoPoExample.Criteria;
import com.meituan.food.po.ToDoPoExample.Criterion;
import com.meituan.food.po.ToDoPoExample;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class ToDoPoSqlProvider {

    public String countByExample(ToDoPoExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("todo_list");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(ToDoPoExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("todo_list");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(ToDoPo record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("todo_list");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getCoeId() != null) {
            sql.VALUES("coe_id", "#{coeId,jdbcType=INTEGER}");
        }
        
        if (record.getOnesId() != null) {
            sql.VALUES("ones_id", "#{onesId,jdbcType=INTEGER}");
        }
        
        if (record.getOnesLink() != null) {
            sql.VALUES("ones_link", "#{onesLink,jdbcType=VARCHAR}");
        }
        
        if (record.getOnesTitle() != null) {
            sql.VALUES("ones_title", "#{onesTitle,jdbcType=VARCHAR}");
        }
        
        if (record.getIsFinish() != null) {
            sql.VALUES("is_finish", "#{isFinish,jdbcType=BIT}");
        }
        
        if (record.getIsDelay() != null) {
            sql.VALUES("is_delay", "#{isDelay,jdbcType=BIT}");
        }
        
        if (record.getDealline() != null) {
            sql.VALUES("dealline", "#{dealline,jdbcType=DATE}");
        }
        
        if (record.getOwnerMis() != null) {
            sql.VALUES("owner_mis", "#{ownerMis,jdbcType=VARCHAR}");
        }
        
        if (record.getOwnerName() != null) {
            sql.VALUES("owner_name", "#{ownerName,jdbcType=VARCHAR}");
        }
        
        if (record.getStartDate() != null) {
            sql.VALUES("start_date", "#{startDate,jdbcType=DATE}");
        }
        
        if (record.getActualDate() != null) {
            sql.VALUES("actual_date", "#{actualDate,jdbcType=DATE}");
        }
        
        return sql.toString();
    }

    public String selectByExample(ToDoPoExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("coe_id");
        sql.SELECT("ones_id");
        sql.SELECT("ones_link");
        sql.SELECT("ones_title");
        sql.SELECT("is_finish");
        sql.SELECT("is_delay");
        sql.SELECT("dealline");
        sql.SELECT("owner_mis");
        sql.SELECT("owner_name");
        sql.SELECT("start_date");
        sql.SELECT("actual_date");
        sql.FROM("todo_list");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        ToDoPo record = (ToDoPo) parameter.get("record");
        ToDoPoExample example = (ToDoPoExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("todo_list");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=INTEGER}");
        }
        
        if (record.getCoeId() != null) {
            sql.SET("coe_id = #{record.coeId,jdbcType=INTEGER}");
        }
        
        if (record.getOnesId() != null) {
            sql.SET("ones_id = #{record.onesId,jdbcType=INTEGER}");
        }
        
        if (record.getOnesLink() != null) {
            sql.SET("ones_link = #{record.onesLink,jdbcType=VARCHAR}");
        }
        
        if (record.getOnesTitle() != null) {
            sql.SET("ones_title = #{record.onesTitle,jdbcType=VARCHAR}");
        }
        
        if (record.getIsFinish() != null) {
            sql.SET("is_finish = #{record.isFinish,jdbcType=BIT}");
        }
        
        if (record.getIsDelay() != null) {
            sql.SET("is_delay = #{record.isDelay,jdbcType=BIT}");
        }
        
        if (record.getDealline() != null) {
            sql.SET("dealline = #{record.dealline,jdbcType=DATE}");
        }
        
        if (record.getOwnerMis() != null) {
            sql.SET("owner_mis = #{record.ownerMis,jdbcType=VARCHAR}");
        }
        
        if (record.getOwnerName() != null) {
            sql.SET("owner_name = #{record.ownerName,jdbcType=VARCHAR}");
        }
        
        if (record.getStartDate() != null) {
            sql.SET("start_date = #{record.startDate,jdbcType=DATE}");
        }
        
        if (record.getActualDate() != null) {
            sql.SET("actual_date = #{record.actualDate,jdbcType=DATE}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("todo_list");
        
        sql.SET("id = #{record.id,jdbcType=INTEGER}");
        sql.SET("coe_id = #{record.coeId,jdbcType=INTEGER}");
        sql.SET("ones_id = #{record.onesId,jdbcType=INTEGER}");
        sql.SET("ones_link = #{record.onesLink,jdbcType=VARCHAR}");
        sql.SET("ones_title = #{record.onesTitle,jdbcType=VARCHAR}");
        sql.SET("is_finish = #{record.isFinish,jdbcType=BIT}");
        sql.SET("is_delay = #{record.isDelay,jdbcType=BIT}");
        sql.SET("dealline = #{record.dealline,jdbcType=DATE}");
        sql.SET("owner_mis = #{record.ownerMis,jdbcType=VARCHAR}");
        sql.SET("owner_name = #{record.ownerName,jdbcType=VARCHAR}");
        sql.SET("start_date = #{record.startDate,jdbcType=DATE}");
        sql.SET("actual_date = #{record.actualDate,jdbcType=DATE}");
        
        ToDoPoExample example = (ToDoPoExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(ToDoPo record) {
        SQL sql = new SQL();
        sql.UPDATE("todo_list");
        
        if (record.getCoeId() != null) {
            sql.SET("coe_id = #{coeId,jdbcType=INTEGER}");
        }
        
        if (record.getOnesId() != null) {
            sql.SET("ones_id = #{onesId,jdbcType=INTEGER}");
        }
        
        if (record.getOnesLink() != null) {
            sql.SET("ones_link = #{onesLink,jdbcType=VARCHAR}");
        }
        
        if (record.getOnesTitle() != null) {
            sql.SET("ones_title = #{onesTitle,jdbcType=VARCHAR}");
        }
        
        if (record.getIsFinish() != null) {
            sql.SET("is_finish = #{isFinish,jdbcType=BIT}");
        }
        
        if (record.getIsDelay() != null) {
            sql.SET("is_delay = #{isDelay,jdbcType=BIT}");
        }
        
        if (record.getDealline() != null) {
            sql.SET("dealline = #{dealline,jdbcType=DATE}");
        }
        
        if (record.getOwnerMis() != null) {
            sql.SET("owner_mis = #{ownerMis,jdbcType=VARCHAR}");
        }
        
        if (record.getOwnerName() != null) {
            sql.SET("owner_name = #{ownerName,jdbcType=VARCHAR}");
        }
        
        if (record.getStartDate() != null) {
            sql.SET("start_date = #{startDate,jdbcType=DATE}");
        }
        
        if (record.getActualDate() != null) {
            sql.SET("actual_date = #{actualDate,jdbcType=DATE}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }

    protected void applyWhere(SQL sql, ToDoPoExample example, boolean includeExamplePhrase) {
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