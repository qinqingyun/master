package com.meituan.food.mapper;

import com.meituan.food.po.SonarPO;
import com.meituan.food.po.SonarPOExample.Criteria;
import com.meituan.food.po.SonarPOExample.Criterion;
import com.meituan.food.po.SonarPOExample;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class SonarPOSqlProvider {

    public String countByExample(SonarPOExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("sonar");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(SonarPOExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("sonar");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(SonarPO record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("sonar");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getProject() != null) {
            sql.VALUES("project", "#{project,jdbcType=VARCHAR}");
        }
        
        if (record.getIncludesubproject() != null) {
            sql.VALUES("includeSubProject", "#{includesubproject,jdbcType=VARCHAR}");
        }
        
        if (record.getBlocker() != null) {
            sql.VALUES("blocker", "#{blocker,jdbcType=INTEGER}");
        }
        
        if (record.getCritical() != null) {
            sql.VALUES("critical", "#{critical,jdbcType=INTEGER}");
        }
        
        if (record.getLeader() != null) {
            sql.VALUES("leader", "#{leader,jdbcType=VARCHAR}");
        }
        
        if (record.getCreatedAt() != null) {
            sql.VALUES("created_at", "#{createdAt,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdatedAt() != null) {
            sql.VALUES("updated_at", "#{updatedAt,jdbcType=TIMESTAMP}");
        }
        
        if (record.getLink() != null) {
            sql.VALUES("link", "#{link,jdbcType=VARCHAR}");
        }
        
        if (record.getSonarDate() != null) {
            sql.VALUES("sonar_date", "#{sonarDate,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String selectByExample(SonarPOExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("project");
        sql.SELECT("includeSubProject");
        sql.SELECT("blocker");
        sql.SELECT("critical");
        sql.SELECT("leader");
        sql.SELECT("created_at");
        sql.SELECT("updated_at");
        sql.SELECT("link");
        sql.SELECT("sonar_date");
        sql.FROM("sonar");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        SonarPO record = (SonarPO) parameter.get("record");
        SonarPOExample example = (SonarPOExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("sonar");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=INTEGER}");
        }
        
        if (record.getProject() != null) {
            sql.SET("project = #{record.project,jdbcType=VARCHAR}");
        }
        
        if (record.getIncludesubproject() != null) {
            sql.SET("includeSubProject = #{record.includesubproject,jdbcType=VARCHAR}");
        }
        
        if (record.getBlocker() != null) {
            sql.SET("blocker = #{record.blocker,jdbcType=INTEGER}");
        }
        
        if (record.getCritical() != null) {
            sql.SET("critical = #{record.critical,jdbcType=INTEGER}");
        }
        
        if (record.getLeader() != null) {
            sql.SET("leader = #{record.leader,jdbcType=VARCHAR}");
        }
        
        if (record.getCreatedAt() != null) {
            sql.SET("created_at = #{record.createdAt,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdatedAt() != null) {
            sql.SET("updated_at = #{record.updatedAt,jdbcType=TIMESTAMP}");
        }
        
        if (record.getLink() != null) {
            sql.SET("link = #{record.link,jdbcType=VARCHAR}");
        }
        
        if (record.getSonarDate() != null) {
            sql.SET("sonar_date = #{record.sonarDate,jdbcType=VARCHAR}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("sonar");
        
        sql.SET("id = #{record.id,jdbcType=INTEGER}");
        sql.SET("project = #{record.project,jdbcType=VARCHAR}");
        sql.SET("includeSubProject = #{record.includesubproject,jdbcType=VARCHAR}");
        sql.SET("blocker = #{record.blocker,jdbcType=INTEGER}");
        sql.SET("critical = #{record.critical,jdbcType=INTEGER}");
        sql.SET("leader = #{record.leader,jdbcType=VARCHAR}");
        sql.SET("created_at = #{record.createdAt,jdbcType=TIMESTAMP}");
        sql.SET("updated_at = #{record.updatedAt,jdbcType=TIMESTAMP}");
        sql.SET("link = #{record.link,jdbcType=VARCHAR}");
        sql.SET("sonar_date = #{record.sonarDate,jdbcType=VARCHAR}");
        
        SonarPOExample example = (SonarPOExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(SonarPO record) {
        SQL sql = new SQL();
        sql.UPDATE("sonar");
        
        if (record.getProject() != null) {
            sql.SET("project = #{project,jdbcType=VARCHAR}");
        }
        
        if (record.getIncludesubproject() != null) {
            sql.SET("includeSubProject = #{includesubproject,jdbcType=VARCHAR}");
        }
        
        if (record.getBlocker() != null) {
            sql.SET("blocker = #{blocker,jdbcType=INTEGER}");
        }
        
        if (record.getCritical() != null) {
            sql.SET("critical = #{critical,jdbcType=INTEGER}");
        }
        
        if (record.getLeader() != null) {
            sql.SET("leader = #{leader,jdbcType=VARCHAR}");
        }
        
        if (record.getCreatedAt() != null) {
            sql.SET("created_at = #{createdAt,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdatedAt() != null) {
            sql.SET("updated_at = #{updatedAt,jdbcType=TIMESTAMP}");
        }
        
        if (record.getLink() != null) {
            sql.SET("link = #{link,jdbcType=VARCHAR}");
        }
        
        if (record.getSonarDate() != null) {
            sql.SET("sonar_date = #{sonarDate,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }

    protected void applyWhere(SQL sql, SonarPOExample example, boolean includeExamplePhrase) {
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