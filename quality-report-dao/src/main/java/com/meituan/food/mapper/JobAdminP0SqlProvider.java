package com.meituan.food.mapper;

import com.meituan.food.po.JobAdminP0;
import com.meituan.food.po.JobAdminP0Example.Criteria;
import com.meituan.food.po.JobAdminP0Example.Criterion;
import com.meituan.food.po.JobAdminP0Example;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class JobAdminP0SqlProvider {

    public String countByExample(JobAdminP0Example example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("job_admin");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(JobAdminP0Example example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("job_admin");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(JobAdminP0 record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("job_admin");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getJobName() != null) {
            sql.VALUES("job_name", "#{jobName,jdbcType=VARCHAR}");
        }
        
        if (record.getAdminMis() != null) {
            sql.VALUES("admin_mis", "#{adminMis,jdbcType=VARCHAR}");
        }
        
        if (record.getCreatedTime() != null) {
            sql.VALUES("created_time", "#{createdTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getCiUrl() != null) {
            sql.VALUES("ci_url", "#{ciUrl,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdatedTime() != null) {
            sql.VALUES("updated_time", "#{updatedTime,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    public String selectByExample(JobAdminP0Example example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("job_name");
        sql.SELECT("admin_mis");
        sql.SELECT("created_time");
        sql.SELECT("ci_url");
        sql.SELECT("updated_time");
        sql.FROM("job_admin");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        JobAdminP0 record = (JobAdminP0) parameter.get("record");
        JobAdminP0Example example = (JobAdminP0Example) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("job_admin");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=INTEGER}");
        }
        
        if (record.getJobName() != null) {
            sql.SET("job_name = #{record.jobName,jdbcType=VARCHAR}");
        }
        
        if (record.getAdminMis() != null) {
            sql.SET("admin_mis = #{record.adminMis,jdbcType=VARCHAR}");
        }
        
        if (record.getCreatedTime() != null) {
            sql.SET("created_time = #{record.createdTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getCiUrl() != null) {
            sql.SET("ci_url = #{record.ciUrl,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdatedTime() != null) {
            sql.SET("updated_time = #{record.updatedTime,jdbcType=TIMESTAMP}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("job_admin");
        
        sql.SET("id = #{record.id,jdbcType=INTEGER}");
        sql.SET("job_name = #{record.jobName,jdbcType=VARCHAR}");
        sql.SET("admin_mis = #{record.adminMis,jdbcType=VARCHAR}");
        sql.SET("created_time = #{record.createdTime,jdbcType=TIMESTAMP}");
        sql.SET("ci_url = #{record.ciUrl,jdbcType=VARCHAR}");
        sql.SET("updated_time = #{record.updatedTime,jdbcType=TIMESTAMP}");
        
        JobAdminP0Example example = (JobAdminP0Example) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(JobAdminP0 record) {
        SQL sql = new SQL();
        sql.UPDATE("job_admin");
        
        if (record.getJobName() != null) {
            sql.SET("job_name = #{jobName,jdbcType=VARCHAR}");
        }
        
        if (record.getAdminMis() != null) {
            sql.SET("admin_mis = #{adminMis,jdbcType=VARCHAR}");
        }
        
        if (record.getCreatedTime() != null) {
            sql.SET("created_time = #{createdTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getCiUrl() != null) {
            sql.SET("ci_url = #{ciUrl,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdatedTime() != null) {
            sql.SET("updated_time = #{updatedTime,jdbcType=TIMESTAMP}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }

    protected void applyWhere(SQL sql, JobAdminP0Example example, boolean includeExamplePhrase) {
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