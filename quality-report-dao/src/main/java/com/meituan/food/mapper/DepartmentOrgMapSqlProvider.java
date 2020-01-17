package com.meituan.food.mapper;

import com.meituan.food.po.DepartmentOrgMap;
import com.meituan.food.po.DepartmentOrgMapExample.Criteria;
import com.meituan.food.po.DepartmentOrgMapExample.Criterion;
import com.meituan.food.po.DepartmentOrgMapExample;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class DepartmentOrgMapSqlProvider {

    public String countByExample(DepartmentOrgMapExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("department_org_map");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(DepartmentOrgMapExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("department_org_map");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(DepartmentOrgMap record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("department_org_map");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=BIGINT}");
        }
        
        if (record.getOrgid() != null) {
            sql.VALUES("orgId", "#{orgid,jdbcType=INTEGER}");
        }
        
        if (record.getOrgname() != null) {
            sql.VALUES("orgName", "#{orgname,jdbcType=VARCHAR}");
        }
        
        if (record.getDepartmentId2() != null) {
            sql.VALUES("department_id_2", "#{departmentId2,jdbcType=INTEGER}");
        }
        
        if (record.getDepartmentName() != null) {
            sql.VALUES("department_name", "#{departmentName,jdbcType=VARCHAR}");
        }
        
        if (record.getDepartmentName2() != null) {
            sql.VALUES("department_name_2", "#{departmentName2,jdbcType=VARCHAR}");
        }
        
        if (record.getAddTime() != null) {
            sql.VALUES("add_time", "#{addTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.VALUES("update_time", "#{updateTime,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    public String selectByExample(DepartmentOrgMapExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("orgId");
        sql.SELECT("orgName");
        sql.SELECT("department_id_2");
        sql.SELECT("department_name");
        sql.SELECT("department_name_2");
        sql.SELECT("add_time");
        sql.SELECT("update_time");
        sql.FROM("department_org_map");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        DepartmentOrgMap record = (DepartmentOrgMap) parameter.get("record");
        DepartmentOrgMapExample example = (DepartmentOrgMapExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("department_org_map");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=BIGINT}");
        }
        
        if (record.getOrgid() != null) {
            sql.SET("orgId = #{record.orgid,jdbcType=INTEGER}");
        }
        
        if (record.getOrgname() != null) {
            sql.SET("orgName = #{record.orgname,jdbcType=VARCHAR}");
        }
        
        if (record.getDepartmentId2() != null) {
            sql.SET("department_id_2 = #{record.departmentId2,jdbcType=INTEGER}");
        }
        
        if (record.getDepartmentName() != null) {
            sql.SET("department_name = #{record.departmentName,jdbcType=VARCHAR}");
        }
        
        if (record.getDepartmentName2() != null) {
            sql.SET("department_name_2 = #{record.departmentName2,jdbcType=VARCHAR}");
        }
        
        if (record.getAddTime() != null) {
            sql.SET("add_time = #{record.addTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.SET("update_time = #{record.updateTime,jdbcType=TIMESTAMP}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("department_org_map");
        
        sql.SET("id = #{record.id,jdbcType=BIGINT}");
        sql.SET("orgId = #{record.orgid,jdbcType=INTEGER}");
        sql.SET("orgName = #{record.orgname,jdbcType=VARCHAR}");
        sql.SET("department_id_2 = #{record.departmentId2,jdbcType=INTEGER}");
        sql.SET("department_name = #{record.departmentName,jdbcType=VARCHAR}");
        sql.SET("department_name_2 = #{record.departmentName2,jdbcType=VARCHAR}");
        sql.SET("add_time = #{record.addTime,jdbcType=TIMESTAMP}");
        sql.SET("update_time = #{record.updateTime,jdbcType=TIMESTAMP}");
        
        DepartmentOrgMapExample example = (DepartmentOrgMapExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(DepartmentOrgMap record) {
        SQL sql = new SQL();
        sql.UPDATE("department_org_map");
        
        if (record.getOrgid() != null) {
            sql.SET("orgId = #{orgid,jdbcType=INTEGER}");
        }
        
        if (record.getOrgname() != null) {
            sql.SET("orgName = #{orgname,jdbcType=VARCHAR}");
        }
        
        if (record.getDepartmentId2() != null) {
            sql.SET("department_id_2 = #{departmentId2,jdbcType=INTEGER}");
        }
        
        if (record.getDepartmentName() != null) {
            sql.SET("department_name = #{departmentName,jdbcType=VARCHAR}");
        }
        
        if (record.getDepartmentName2() != null) {
            sql.SET("department_name_2 = #{departmentName2,jdbcType=VARCHAR}");
        }
        
        if (record.getAddTime() != null) {
            sql.SET("add_time = #{addTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.SET("update_time = #{updateTime,jdbcType=TIMESTAMP}");
        }
        
        sql.WHERE("id = #{id,jdbcType=BIGINT}");
        
        return sql.toString();
    }

    protected void applyWhere(SQL sql, DepartmentOrgMapExample example, boolean includeExamplePhrase) {
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