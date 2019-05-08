package com.meituan.food.mapper;

import com.meituan.food.po.WeekBugDetailPO;
import com.meituan.food.po.WeekBugDetailPOExample.Criteria;
import com.meituan.food.po.WeekBugDetailPOExample.Criterion;
import com.meituan.food.po.WeekBugDetailPOExample;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class WeekBugDetailPOSqlProvider {

    public String countByExample(WeekBugDetailPOExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("week_bug_detail");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(WeekBugDetailPOExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("week_bug_detail");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(WeekBugDetailPO record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("week_bug_detail");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getTitle() != null) {
            sql.VALUES("title", "#{title,jdbcType=VARCHAR}");
        }
        
        if (record.getBugLevel() != null) {
            sql.VALUES("bug_level", "#{bugLevel,jdbcType=VARCHAR}");
        }
        
        if (record.getReason() != null) {
            sql.VALUES("reason", "#{reason,jdbcType=VARCHAR}");
        }
        
        if (record.getCreator() != null) {
            sql.VALUES("creator", "#{creator,jdbcType=VARCHAR}");
        }
        
        if (record.getReceiver() != null) {
            sql.VALUES("receiver", "#{receiver,jdbcType=VARCHAR}");
        }
        
        if (record.getBugStatus() != null) {
            sql.VALUES("bug_status", "#{bugStatus,jdbcType=VARCHAR}");
        }
        
        if (record.getCreatedTime() != null) {
            sql.VALUES("created_time", "#{createdTime,jdbcType=VARCHAR}");
        }
        
        if (record.getTimeFlag() != null) {
            sql.VALUES("time_flag", "#{timeFlag,jdbcType=BIGINT}");
        }
        
        if (record.getOrgname() != null) {
            sql.VALUES("orgname", "#{orgname,jdbcType=VARCHAR}");
        }
        
        if (record.getOrgid() != null) {
            sql.VALUES("orgid", "#{orgid,jdbcType=VARCHAR}");
        }
        
        if (record.getAllTitle() != null) {
            sql.VALUES("all_title", "#{allTitle,jdbcType=VARCHAR}");
        }
        
        if (record.getBugLink() != null) {
            sql.VALUES("bug_link", "#{bugLink,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String selectByExample(WeekBugDetailPOExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("title");
        sql.SELECT("bug_level");
        sql.SELECT("reason");
        sql.SELECT("creator");
        sql.SELECT("receiver");
        sql.SELECT("bug_status");
        sql.SELECT("created_time");
        sql.SELECT("time_flag");
        sql.SELECT("orgname");
        sql.SELECT("orgid");
        sql.SELECT("all_title");
        sql.SELECT("bug_link");
        sql.FROM("week_bug_detail");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        WeekBugDetailPO record = (WeekBugDetailPO) parameter.get("record");
        WeekBugDetailPOExample example = (WeekBugDetailPOExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("week_bug_detail");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=INTEGER}");
        }
        
        if (record.getTitle() != null) {
            sql.SET("title = #{record.title,jdbcType=VARCHAR}");
        }
        
        if (record.getBugLevel() != null) {
            sql.SET("bug_level = #{record.bugLevel,jdbcType=VARCHAR}");
        }
        
        if (record.getReason() != null) {
            sql.SET("reason = #{record.reason,jdbcType=VARCHAR}");
        }
        
        if (record.getCreator() != null) {
            sql.SET("creator = #{record.creator,jdbcType=VARCHAR}");
        }
        
        if (record.getReceiver() != null) {
            sql.SET("receiver = #{record.receiver,jdbcType=VARCHAR}");
        }
        
        if (record.getBugStatus() != null) {
            sql.SET("bug_status = #{record.bugStatus,jdbcType=VARCHAR}");
        }
        
        if (record.getCreatedTime() != null) {
            sql.SET("created_time = #{record.createdTime,jdbcType=VARCHAR}");
        }
        
        if (record.getTimeFlag() != null) {
            sql.SET("time_flag = #{record.timeFlag,jdbcType=BIGINT}");
        }
        
        if (record.getOrgname() != null) {
            sql.SET("orgname = #{record.orgname,jdbcType=VARCHAR}");
        }
        
        if (record.getOrgid() != null) {
            sql.SET("orgid = #{record.orgid,jdbcType=VARCHAR}");
        }
        
        if (record.getAllTitle() != null) {
            sql.SET("all_title = #{record.allTitle,jdbcType=VARCHAR}");
        }
        
        if (record.getBugLink() != null) {
            sql.SET("bug_link = #{record.bugLink,jdbcType=VARCHAR}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("week_bug_detail");
        
        sql.SET("id = #{record.id,jdbcType=INTEGER}");
        sql.SET("title = #{record.title,jdbcType=VARCHAR}");
        sql.SET("bug_level = #{record.bugLevel,jdbcType=VARCHAR}");
        sql.SET("reason = #{record.reason,jdbcType=VARCHAR}");
        sql.SET("creator = #{record.creator,jdbcType=VARCHAR}");
        sql.SET("receiver = #{record.receiver,jdbcType=VARCHAR}");
        sql.SET("bug_status = #{record.bugStatus,jdbcType=VARCHAR}");
        sql.SET("created_time = #{record.createdTime,jdbcType=VARCHAR}");
        sql.SET("time_flag = #{record.timeFlag,jdbcType=BIGINT}");
        sql.SET("orgname = #{record.orgname,jdbcType=VARCHAR}");
        sql.SET("orgid = #{record.orgid,jdbcType=VARCHAR}");
        sql.SET("all_title = #{record.allTitle,jdbcType=VARCHAR}");
        sql.SET("bug_link = #{record.bugLink,jdbcType=VARCHAR}");
        
        WeekBugDetailPOExample example = (WeekBugDetailPOExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(WeekBugDetailPO record) {
        SQL sql = new SQL();
        sql.UPDATE("week_bug_detail");
        
        if (record.getTitle() != null) {
            sql.SET("title = #{title,jdbcType=VARCHAR}");
        }
        
        if (record.getBugLevel() != null) {
            sql.SET("bug_level = #{bugLevel,jdbcType=VARCHAR}");
        }
        
        if (record.getReason() != null) {
            sql.SET("reason = #{reason,jdbcType=VARCHAR}");
        }
        
        if (record.getCreator() != null) {
            sql.SET("creator = #{creator,jdbcType=VARCHAR}");
        }
        
        if (record.getReceiver() != null) {
            sql.SET("receiver = #{receiver,jdbcType=VARCHAR}");
        }
        
        if (record.getBugStatus() != null) {
            sql.SET("bug_status = #{bugStatus,jdbcType=VARCHAR}");
        }
        
        if (record.getCreatedTime() != null) {
            sql.SET("created_time = #{createdTime,jdbcType=VARCHAR}");
        }
        
        if (record.getTimeFlag() != null) {
            sql.SET("time_flag = #{timeFlag,jdbcType=BIGINT}");
        }
        
        if (record.getOrgname() != null) {
            sql.SET("orgname = #{orgname,jdbcType=VARCHAR}");
        }
        
        if (record.getOrgid() != null) {
            sql.SET("orgid = #{orgid,jdbcType=VARCHAR}");
        }
        
        if (record.getAllTitle() != null) {
            sql.SET("all_title = #{allTitle,jdbcType=VARCHAR}");
        }
        
        if (record.getBugLink() != null) {
            sql.SET("bug_link = #{bugLink,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }

    protected void applyWhere(SQL sql, WeekBugDetailPOExample example, boolean includeExamplePhrase) {
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