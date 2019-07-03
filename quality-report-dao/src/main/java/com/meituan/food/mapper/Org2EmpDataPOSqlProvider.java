package com.meituan.food.mapper;

import com.meituan.food.po.Org2EmpDataPO;
import com.meituan.food.po.Org2EmpDataPOExample.Criteria;
import com.meituan.food.po.Org2EmpDataPOExample.Criterion;
import com.meituan.food.po.Org2EmpDataPOExample;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class Org2EmpDataPOSqlProvider {

    public String countByExample(Org2EmpDataPOExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("org2_emp_data");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(Org2EmpDataPOExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("org2_emp_data");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(Org2EmpDataPO record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("org2_emp_data");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getEmpid() != null) {
            sql.VALUES("empId", "#{empid,jdbcType=VARCHAR}");
        }
        
        if (record.getName() != null) {
            sql.VALUES("name", "#{name,jdbcType=VARCHAR}");
        }
        
        if (record.getMis() != null) {
            sql.VALUES("mis", "#{mis,jdbcType=VARCHAR}");
        }
        
        if (record.getReportempid() != null) {
            sql.VALUES("reportEmpId", "#{reportempid,jdbcType=VARCHAR}");
        }
        
        if (record.getReportempname() != null) {
            sql.VALUES("reportEmpName", "#{reportempname,jdbcType=VARCHAR}");
        }
        
        if (record.getOrgid() != null) {
            sql.VALUES("orgId", "#{orgid,jdbcType=VARCHAR}");
        }
        
        if (record.getOrgname() != null) {
            sql.VALUES("orgName", "#{orgname,jdbcType=VARCHAR}");
        }
        
        if (record.getVirtualreportempid() != null) {
            sql.VALUES("virtualreportEmpId", "#{virtualreportempid,jdbcType=VARCHAR}");
        }
        
        if (record.getVirtualreportempname() != null) {
            sql.VALUES("virtualreportEmpName", "#{virtualreportempname,jdbcType=VARCHAR}");
        }
        
        if (record.getComment() != null) {
            sql.VALUES("comment", "#{comment,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdatedDate() != null) {
            sql.VALUES("updated_date", "#{updatedDate,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String selectByExample(Org2EmpDataPOExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("empId");
        sql.SELECT("name");
        sql.SELECT("mis");
        sql.SELECT("reportEmpId");
        sql.SELECT("reportEmpName");
        sql.SELECT("orgId");
        sql.SELECT("orgName");
        sql.SELECT("virtualreportEmpId");
        sql.SELECT("virtualreportEmpName");
        sql.SELECT("comment");
        sql.SELECT("updated_date");
        sql.FROM("org2_emp_data");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        Org2EmpDataPO record = (Org2EmpDataPO) parameter.get("record");
        Org2EmpDataPOExample example = (Org2EmpDataPOExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("org2_emp_data");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=INTEGER}");
        }
        
        if (record.getEmpid() != null) {
            sql.SET("empId = #{record.empid,jdbcType=VARCHAR}");
        }
        
        if (record.getName() != null) {
            sql.SET("name = #{record.name,jdbcType=VARCHAR}");
        }
        
        if (record.getMis() != null) {
            sql.SET("mis = #{record.mis,jdbcType=VARCHAR}");
        }
        
        if (record.getReportempid() != null) {
            sql.SET("reportEmpId = #{record.reportempid,jdbcType=VARCHAR}");
        }
        
        if (record.getReportempname() != null) {
            sql.SET("reportEmpName = #{record.reportempname,jdbcType=VARCHAR}");
        }
        
        if (record.getOrgid() != null) {
            sql.SET("orgId = #{record.orgid,jdbcType=VARCHAR}");
        }
        
        if (record.getOrgname() != null) {
            sql.SET("orgName = #{record.orgname,jdbcType=VARCHAR}");
        }
        
        if (record.getVirtualreportempid() != null) {
            sql.SET("virtualreportEmpId = #{record.virtualreportempid,jdbcType=VARCHAR}");
        }
        
        if (record.getVirtualreportempname() != null) {
            sql.SET("virtualreportEmpName = #{record.virtualreportempname,jdbcType=VARCHAR}");
        }
        
        if (record.getComment() != null) {
            sql.SET("comment = #{record.comment,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdatedDate() != null) {
            sql.SET("updated_date = #{record.updatedDate,jdbcType=VARCHAR}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("org2_emp_data");
        
        sql.SET("id = #{record.id,jdbcType=INTEGER}");
        sql.SET("empId = #{record.empid,jdbcType=VARCHAR}");
        sql.SET("name = #{record.name,jdbcType=VARCHAR}");
        sql.SET("mis = #{record.mis,jdbcType=VARCHAR}");
        sql.SET("reportEmpId = #{record.reportempid,jdbcType=VARCHAR}");
        sql.SET("reportEmpName = #{record.reportempname,jdbcType=VARCHAR}");
        sql.SET("orgId = #{record.orgid,jdbcType=VARCHAR}");
        sql.SET("orgName = #{record.orgname,jdbcType=VARCHAR}");
        sql.SET("virtualreportEmpId = #{record.virtualreportempid,jdbcType=VARCHAR}");
        sql.SET("virtualreportEmpName = #{record.virtualreportempname,jdbcType=VARCHAR}");
        sql.SET("comment = #{record.comment,jdbcType=VARCHAR}");
        sql.SET("updated_date = #{record.updatedDate,jdbcType=VARCHAR}");
        
        Org2EmpDataPOExample example = (Org2EmpDataPOExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Org2EmpDataPO record) {
        SQL sql = new SQL();
        sql.UPDATE("org2_emp_data");
        
        if (record.getEmpid() != null) {
            sql.SET("empId = #{empid,jdbcType=VARCHAR}");
        }
        
        if (record.getName() != null) {
            sql.SET("name = #{name,jdbcType=VARCHAR}");
        }
        
        if (record.getMis() != null) {
            sql.SET("mis = #{mis,jdbcType=VARCHAR}");
        }
        
        if (record.getReportempid() != null) {
            sql.SET("reportEmpId = #{reportempid,jdbcType=VARCHAR}");
        }
        
        if (record.getReportempname() != null) {
            sql.SET("reportEmpName = #{reportempname,jdbcType=VARCHAR}");
        }
        
        if (record.getOrgid() != null) {
            sql.SET("orgId = #{orgid,jdbcType=VARCHAR}");
        }
        
        if (record.getOrgname() != null) {
            sql.SET("orgName = #{orgname,jdbcType=VARCHAR}");
        }
        
        if (record.getVirtualreportempid() != null) {
            sql.SET("virtualreportEmpId = #{virtualreportempid,jdbcType=VARCHAR}");
        }
        
        if (record.getVirtualreportempname() != null) {
            sql.SET("virtualreportEmpName = #{virtualreportempname,jdbcType=VARCHAR}");
        }
        
        if (record.getComment() != null) {
            sql.SET("comment = #{comment,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdatedDate() != null) {
            sql.SET("updated_date = #{updatedDate,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }

    protected void applyWhere(SQL sql, Org2EmpDataPOExample example, boolean includeExamplePhrase) {
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