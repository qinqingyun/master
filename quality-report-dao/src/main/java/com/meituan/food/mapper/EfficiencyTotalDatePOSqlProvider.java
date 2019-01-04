package com.meituan.food.mapper;

import com.meituan.food.po.EfficiencyTotalDatePO;
import com.meituan.food.po.EfficiencyTotalDatePOExample.Criteria;
import com.meituan.food.po.EfficiencyTotalDatePOExample.Criterion;
import com.meituan.food.po.EfficiencyTotalDatePOExample;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class EfficiencyTotalDatePOSqlProvider {

    public String countByExample(EfficiencyTotalDatePOExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("efficiency_total_data");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(EfficiencyTotalDatePOExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("efficiency_total_data");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(EfficiencyTotalDatePO record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("efficiency_total_data");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getMis() != null) {
            sql.VALUES("mis", "#{mis,jdbcType=VARCHAR}");
        }
        
        if (record.getName() != null) {
            sql.VALUES("name", "#{name,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateWikiNum() != null) {
            sql.VALUES("create_wiki_num", "#{createWikiNum,jdbcType=BIGINT}");
        }
        
        if (record.getUpdateWikiNum() != null) {
            sql.VALUES("update_wiki_num", "#{updateWikiNum,jdbcType=BIGINT}");
        }
        
        if (record.getGitIncrease() != null) {
            sql.VALUES("git_increase", "#{gitIncrease,jdbcType=INTEGER}");
        }
        
        if (record.getGitDelete() != null) {
            sql.VALUES("git_delete", "#{gitDelete,jdbcType=INTEGER}");
        }
        
        if (record.getGitSubmit() != null) {
            sql.VALUES("git_submit", "#{gitSubmit,jdbcType=INTEGER}");
        }
        
        if (record.getGitSubmitTime() != null) {
            sql.VALUES("git_submit_time", "#{gitSubmitTime,jdbcType=INTEGER}");
        }
        
        if (record.getCreateBugNum() != null) {
            sql.VALUES("create_bug_num", "#{createBugNum,jdbcType=INTEGER}");
        }
        
        if (record.getAcceptBugNum() != null) {
            sql.VALUES("accept_bug_num", "#{acceptBugNum,jdbcType=INTEGER}");
        }
        
        if (record.getPartitionDate() != null) {
            sql.VALUES("partition_date", "#{partitionDate,jdbcType=VARCHAR}");
        }
        
        if (record.getCreatedAt() != null) {
            sql.VALUES("created_at", "#{createdAt,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    public String selectByExample(EfficiencyTotalDatePOExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("mis");
        sql.SELECT("name");
        sql.SELECT("create_wiki_num");
        sql.SELECT("update_wiki_num");
        sql.SELECT("git_increase");
        sql.SELECT("git_delete");
        sql.SELECT("git_submit");
        sql.SELECT("git_submit_time");
        sql.SELECT("create_bug_num");
        sql.SELECT("accept_bug_num");
        sql.SELECT("partition_date");
        sql.SELECT("created_at");
        sql.FROM("efficiency_total_data");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        EfficiencyTotalDatePO record = (EfficiencyTotalDatePO) parameter.get("record");
        EfficiencyTotalDatePOExample example = (EfficiencyTotalDatePOExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("efficiency_total_data");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=INTEGER}");
        }
        
        if (record.getMis() != null) {
            sql.SET("mis = #{record.mis,jdbcType=VARCHAR}");
        }
        
        if (record.getName() != null) {
            sql.SET("name = #{record.name,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateWikiNum() != null) {
            sql.SET("create_wiki_num = #{record.createWikiNum,jdbcType=BIGINT}");
        }
        
        if (record.getUpdateWikiNum() != null) {
            sql.SET("update_wiki_num = #{record.updateWikiNum,jdbcType=BIGINT}");
        }
        
        if (record.getGitIncrease() != null) {
            sql.SET("git_increase = #{record.gitIncrease,jdbcType=INTEGER}");
        }
        
        if (record.getGitDelete() != null) {
            sql.SET("git_delete = #{record.gitDelete,jdbcType=INTEGER}");
        }
        
        if (record.getGitSubmit() != null) {
            sql.SET("git_submit = #{record.gitSubmit,jdbcType=INTEGER}");
        }
        
        if (record.getGitSubmitTime() != null) {
            sql.SET("git_submit_time = #{record.gitSubmitTime,jdbcType=INTEGER}");
        }
        
        if (record.getCreateBugNum() != null) {
            sql.SET("create_bug_num = #{record.createBugNum,jdbcType=INTEGER}");
        }
        
        if (record.getAcceptBugNum() != null) {
            sql.SET("accept_bug_num = #{record.acceptBugNum,jdbcType=INTEGER}");
        }
        
        if (record.getPartitionDate() != null) {
            sql.SET("partition_date = #{record.partitionDate,jdbcType=VARCHAR}");
        }
        
        if (record.getCreatedAt() != null) {
            sql.SET("created_at = #{record.createdAt,jdbcType=TIMESTAMP}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("efficiency_total_data");
        
        sql.SET("id = #{record.id,jdbcType=INTEGER}");
        sql.SET("mis = #{record.mis,jdbcType=VARCHAR}");
        sql.SET("name = #{record.name,jdbcType=VARCHAR}");
        sql.SET("create_wiki_num = #{record.createWikiNum,jdbcType=BIGINT}");
        sql.SET("update_wiki_num = #{record.updateWikiNum,jdbcType=BIGINT}");
        sql.SET("git_increase = #{record.gitIncrease,jdbcType=INTEGER}");
        sql.SET("git_delete = #{record.gitDelete,jdbcType=INTEGER}");
        sql.SET("git_submit = #{record.gitSubmit,jdbcType=INTEGER}");
        sql.SET("git_submit_time = #{record.gitSubmitTime,jdbcType=INTEGER}");
        sql.SET("create_bug_num = #{record.createBugNum,jdbcType=INTEGER}");
        sql.SET("accept_bug_num = #{record.acceptBugNum,jdbcType=INTEGER}");
        sql.SET("partition_date = #{record.partitionDate,jdbcType=VARCHAR}");
        sql.SET("created_at = #{record.createdAt,jdbcType=TIMESTAMP}");
        
        EfficiencyTotalDatePOExample example = (EfficiencyTotalDatePOExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(EfficiencyTotalDatePO record) {
        SQL sql = new SQL();
        sql.UPDATE("efficiency_total_data");
        
        if (record.getMis() != null) {
            sql.SET("mis = #{mis,jdbcType=VARCHAR}");
        }
        
        if (record.getName() != null) {
            sql.SET("name = #{name,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateWikiNum() != null) {
            sql.SET("create_wiki_num = #{createWikiNum,jdbcType=BIGINT}");
        }
        
        if (record.getUpdateWikiNum() != null) {
            sql.SET("update_wiki_num = #{updateWikiNum,jdbcType=BIGINT}");
        }
        
        if (record.getGitIncrease() != null) {
            sql.SET("git_increase = #{gitIncrease,jdbcType=INTEGER}");
        }
        
        if (record.getGitDelete() != null) {
            sql.SET("git_delete = #{gitDelete,jdbcType=INTEGER}");
        }
        
        if (record.getGitSubmit() != null) {
            sql.SET("git_submit = #{gitSubmit,jdbcType=INTEGER}");
        }
        
        if (record.getGitSubmitTime() != null) {
            sql.SET("git_submit_time = #{gitSubmitTime,jdbcType=INTEGER}");
        }
        
        if (record.getCreateBugNum() != null) {
            sql.SET("create_bug_num = #{createBugNum,jdbcType=INTEGER}");
        }
        
        if (record.getAcceptBugNum() != null) {
            sql.SET("accept_bug_num = #{acceptBugNum,jdbcType=INTEGER}");
        }
        
        if (record.getPartitionDate() != null) {
            sql.SET("partition_date = #{partitionDate,jdbcType=VARCHAR}");
        }
        
        if (record.getCreatedAt() != null) {
            sql.SET("created_at = #{createdAt,jdbcType=TIMESTAMP}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }

    protected void applyWhere(SQL sql, EfficiencyTotalDatePOExample example, boolean includeExamplePhrase) {
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