package com.meituan.food.mapper;

import com.meituan.food.po.TaskDurationPO;
import com.meituan.food.po.TaskDurationPOExample.Criteria;
import com.meituan.food.po.TaskDurationPOExample.Criterion;
import com.meituan.food.po.TaskDurationPOExample;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class TaskDurationPOSqlProvider {

    public String countByExample(TaskDurationPOExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("task_duration");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(TaskDurationPOExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("task_duration");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(TaskDurationPO record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("task_duration");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getMisid() != null) {
            sql.VALUES("misid", "#{misid,jdbcType=VARCHAR}");
        }
        
        if (record.getRealName() != null) {
            sql.VALUES("real_name", "#{realName,jdbcType=VARCHAR}");
        }
        
        if (record.getDashboard() != null) {
            sql.VALUES("dashboard", "#{dashboard,jdbcType=VARCHAR}");
        }
        
        if (record.getStartDate() != null) {
            sql.VALUES("start_date", "#{startDate,jdbcType=VARCHAR}");
        }
        
        if (record.getEndDate() != null) {
            sql.VALUES("end_date", "#{endDate,jdbcType=VARCHAR}");
        }
        
        if (record.getDuration() != null) {
            sql.VALUES("duration", "#{duration,jdbcType=DECIMAL}");
        }
        
        if (record.getOrgGroup() != null) {
            sql.VALUES("org_group", "#{orgGroup,jdbcType=VARCHAR}");
        }
        
        if (record.getOrgId() != null) {
            sql.VALUES("org_id", "#{orgId,jdbcType=VARCHAR}");
        }
        
        if (record.getFirstLeader() != null) {
            sql.VALUES("first_leader", "#{firstLeader,jdbcType=VARCHAR}");
        }
        
        if (record.getSecondLeader() != null) {
            sql.VALUES("second_leader", "#{secondLeader,jdbcType=VARCHAR}");
        }
        
        if (record.getCreatedAt() != null) {
            sql.VALUES("created_at", "#{createdAt,jdbcType=TIMESTAMP}");
        }
        
        if (record.getIsnormal() != null) {
            sql.VALUES("isNormal", "#{isnormal,jdbcType=BIT}");
        }
        
        return sql.toString();
    }

    public String selectByExample(TaskDurationPOExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("misid");
        sql.SELECT("real_name");
        sql.SELECT("dashboard");
        sql.SELECT("start_date");
        sql.SELECT("end_date");
        sql.SELECT("duration");
        sql.SELECT("org_group");
        sql.SELECT("org_id");
        sql.SELECT("first_leader");
        sql.SELECT("second_leader");
        sql.SELECT("created_at");
        sql.SELECT("isNormal");
        sql.FROM("task_duration");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        TaskDurationPO record = (TaskDurationPO) parameter.get("record");
        TaskDurationPOExample example = (TaskDurationPOExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("task_duration");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=INTEGER}");
        }
        
        if (record.getMisid() != null) {
            sql.SET("misid = #{record.misid,jdbcType=VARCHAR}");
        }
        
        if (record.getRealName() != null) {
            sql.SET("real_name = #{record.realName,jdbcType=VARCHAR}");
        }
        
        if (record.getDashboard() != null) {
            sql.SET("dashboard = #{record.dashboard,jdbcType=VARCHAR}");
        }
        
        if (record.getStartDate() != null) {
            sql.SET("start_date = #{record.startDate,jdbcType=VARCHAR}");
        }
        
        if (record.getEndDate() != null) {
            sql.SET("end_date = #{record.endDate,jdbcType=VARCHAR}");
        }
        
        if (record.getDuration() != null) {
            sql.SET("duration = #{record.duration,jdbcType=DECIMAL}");
        }
        
        if (record.getOrgGroup() != null) {
            sql.SET("org_group = #{record.orgGroup,jdbcType=VARCHAR}");
        }
        
        if (record.getOrgId() != null) {
            sql.SET("org_id = #{record.orgId,jdbcType=VARCHAR}");
        }
        
        if (record.getFirstLeader() != null) {
            sql.SET("first_leader = #{record.firstLeader,jdbcType=VARCHAR}");
        }
        
        if (record.getSecondLeader() != null) {
            sql.SET("second_leader = #{record.secondLeader,jdbcType=VARCHAR}");
        }
        
        if (record.getCreatedAt() != null) {
            sql.SET("created_at = #{record.createdAt,jdbcType=TIMESTAMP}");
        }
        
        if (record.getIsnormal() != null) {
            sql.SET("isNormal = #{record.isnormal,jdbcType=BIT}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("task_duration");
        
        sql.SET("id = #{record.id,jdbcType=INTEGER}");
        sql.SET("misid = #{record.misid,jdbcType=VARCHAR}");
        sql.SET("real_name = #{record.realName,jdbcType=VARCHAR}");
        sql.SET("dashboard = #{record.dashboard,jdbcType=VARCHAR}");
        sql.SET("start_date = #{record.startDate,jdbcType=VARCHAR}");
        sql.SET("end_date = #{record.endDate,jdbcType=VARCHAR}");
        sql.SET("duration = #{record.duration,jdbcType=DECIMAL}");
        sql.SET("org_group = #{record.orgGroup,jdbcType=VARCHAR}");
        sql.SET("org_id = #{record.orgId,jdbcType=VARCHAR}");
        sql.SET("first_leader = #{record.firstLeader,jdbcType=VARCHAR}");
        sql.SET("second_leader = #{record.secondLeader,jdbcType=VARCHAR}");
        sql.SET("created_at = #{record.createdAt,jdbcType=TIMESTAMP}");
        sql.SET("isNormal = #{record.isnormal,jdbcType=BIT}");
        
        TaskDurationPOExample example = (TaskDurationPOExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(TaskDurationPO record) {
        SQL sql = new SQL();
        sql.UPDATE("task_duration");
        
        if (record.getMisid() != null) {
            sql.SET("misid = #{misid,jdbcType=VARCHAR}");
        }
        
        if (record.getRealName() != null) {
            sql.SET("real_name = #{realName,jdbcType=VARCHAR}");
        }
        
        if (record.getDashboard() != null) {
            sql.SET("dashboard = #{dashboard,jdbcType=VARCHAR}");
        }
        
        if (record.getStartDate() != null) {
            sql.SET("start_date = #{startDate,jdbcType=VARCHAR}");
        }
        
        if (record.getEndDate() != null) {
            sql.SET("end_date = #{endDate,jdbcType=VARCHAR}");
        }
        
        if (record.getDuration() != null) {
            sql.SET("duration = #{duration,jdbcType=DECIMAL}");
        }
        
        if (record.getOrgGroup() != null) {
            sql.SET("org_group = #{orgGroup,jdbcType=VARCHAR}");
        }
        
        if (record.getOrgId() != null) {
            sql.SET("org_id = #{orgId,jdbcType=VARCHAR}");
        }
        
        if (record.getFirstLeader() != null) {
            sql.SET("first_leader = #{firstLeader,jdbcType=VARCHAR}");
        }
        
        if (record.getSecondLeader() != null) {
            sql.SET("second_leader = #{secondLeader,jdbcType=VARCHAR}");
        }
        
        if (record.getCreatedAt() != null) {
            sql.SET("created_at = #{createdAt,jdbcType=TIMESTAMP}");
        }
        
        if (record.getIsnormal() != null) {
            sql.SET("isNormal = #{isnormal,jdbcType=BIT}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }

    protected void applyWhere(SQL sql, TaskDurationPOExample example, boolean includeExamplePhrase) {
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