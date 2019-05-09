package com.meituan.food.mapper;

import com.meituan.food.po.WeekBugTotalCountPO;
import com.meituan.food.po.WeekBugTotalCountPOExample.Criteria;
import com.meituan.food.po.WeekBugTotalCountPOExample.Criterion;
import com.meituan.food.po.WeekBugTotalCountPOExample;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class WeekBugTotalCountPOSqlProvider {

    public String countByExample(WeekBugTotalCountPOExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("week_bug_total_count");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(WeekBugTotalCountPOExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("week_bug_total_count");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(WeekBugTotalCountPO record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("week_bug_total_count");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getGroupName() != null) {
            sql.VALUES("group_name", "#{groupName,jdbcType=VARCHAR}");
        }
        
        if (record.getMajorCount() != null) {
            sql.VALUES("major_count", "#{majorCount,jdbcType=INTEGER}");
        }
        
        if (record.getBlockerCount() != null) {
            sql.VALUES("blocker_count", "#{blockerCount,jdbcType=INTEGER}");
        }
        
        if (record.getCriticalCount() != null) {
            sql.VALUES("critical_count", "#{criticalCount,jdbcType=INTEGER}");
        }
        
        if (record.getMinorCount() != null) {
            sql.VALUES("minor_count", "#{minorCount,jdbcType=INTEGER}");
        }
        
        if (record.getTrivialCount() != null) {
            sql.VALUES("trivial_count", "#{trivialCount,jdbcType=INTEGER}");
        }
        
        if (record.getTotalCount() != null) {
            sql.VALUES("total_count", "#{totalCount,jdbcType=INTEGER}");
        }
        
        if (record.getBugLink() != null) {
            sql.VALUES("bug_link", "#{bugLink,jdbcType=VARCHAR}");
        }
        
        if (record.getStartDate() != null) {
            sql.VALUES("start_date", "#{startDate,jdbcType=VARCHAR}");
        }
        
        if (record.getEndDate() != null) {
            sql.VALUES("end_date", "#{endDate,jdbcType=VARCHAR}");
        }
        
        if (record.getTimeFlag() != null) {
            sql.VALUES("time_flag", "#{timeFlag,jdbcType=BIGINT}");
        }
        
        return sql.toString();
    }

    public String selectByExample(WeekBugTotalCountPOExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("group_name");
        sql.SELECT("major_count");
        sql.SELECT("blocker_count");
        sql.SELECT("critical_count");
        sql.SELECT("minor_count");
        sql.SELECT("trivial_count");
        sql.SELECT("total_count");
        sql.SELECT("bug_link");
        sql.SELECT("start_date");
        sql.SELECT("end_date");
        sql.SELECT("time_flag");
        sql.FROM("week_bug_total_count");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        WeekBugTotalCountPO record = (WeekBugTotalCountPO) parameter.get("record");
        WeekBugTotalCountPOExample example = (WeekBugTotalCountPOExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("week_bug_total_count");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=INTEGER}");
        }
        
        if (record.getGroupName() != null) {
            sql.SET("group_name = #{record.groupName,jdbcType=VARCHAR}");
        }
        
        if (record.getMajorCount() != null) {
            sql.SET("major_count = #{record.majorCount,jdbcType=INTEGER}");
        }
        
        if (record.getBlockerCount() != null) {
            sql.SET("blocker_count = #{record.blockerCount,jdbcType=INTEGER}");
        }
        
        if (record.getCriticalCount() != null) {
            sql.SET("critical_count = #{record.criticalCount,jdbcType=INTEGER}");
        }
        
        if (record.getMinorCount() != null) {
            sql.SET("minor_count = #{record.minorCount,jdbcType=INTEGER}");
        }
        
        if (record.getTrivialCount() != null) {
            sql.SET("trivial_count = #{record.trivialCount,jdbcType=INTEGER}");
        }
        
        if (record.getTotalCount() != null) {
            sql.SET("total_count = #{record.totalCount,jdbcType=INTEGER}");
        }
        
        if (record.getBugLink() != null) {
            sql.SET("bug_link = #{record.bugLink,jdbcType=VARCHAR}");
        }
        
        if (record.getStartDate() != null) {
            sql.SET("start_date = #{record.startDate,jdbcType=VARCHAR}");
        }
        
        if (record.getEndDate() != null) {
            sql.SET("end_date = #{record.endDate,jdbcType=VARCHAR}");
        }
        
        if (record.getTimeFlag() != null) {
            sql.SET("time_flag = #{record.timeFlag,jdbcType=BIGINT}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("week_bug_total_count");
        
        sql.SET("id = #{record.id,jdbcType=INTEGER}");
        sql.SET("group_name = #{record.groupName,jdbcType=VARCHAR}");
        sql.SET("major_count = #{record.majorCount,jdbcType=INTEGER}");
        sql.SET("blocker_count = #{record.blockerCount,jdbcType=INTEGER}");
        sql.SET("critical_count = #{record.criticalCount,jdbcType=INTEGER}");
        sql.SET("minor_count = #{record.minorCount,jdbcType=INTEGER}");
        sql.SET("trivial_count = #{record.trivialCount,jdbcType=INTEGER}");
        sql.SET("total_count = #{record.totalCount,jdbcType=INTEGER}");
        sql.SET("bug_link = #{record.bugLink,jdbcType=VARCHAR}");
        sql.SET("start_date = #{record.startDate,jdbcType=VARCHAR}");
        sql.SET("end_date = #{record.endDate,jdbcType=VARCHAR}");
        sql.SET("time_flag = #{record.timeFlag,jdbcType=BIGINT}");
        
        WeekBugTotalCountPOExample example = (WeekBugTotalCountPOExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(WeekBugTotalCountPO record) {
        SQL sql = new SQL();
        sql.UPDATE("week_bug_total_count");
        
        if (record.getGroupName() != null) {
            sql.SET("group_name = #{groupName,jdbcType=VARCHAR}");
        }
        
        if (record.getMajorCount() != null) {
            sql.SET("major_count = #{majorCount,jdbcType=INTEGER}");
        }
        
        if (record.getBlockerCount() != null) {
            sql.SET("blocker_count = #{blockerCount,jdbcType=INTEGER}");
        }
        
        if (record.getCriticalCount() != null) {
            sql.SET("critical_count = #{criticalCount,jdbcType=INTEGER}");
        }
        
        if (record.getMinorCount() != null) {
            sql.SET("minor_count = #{minorCount,jdbcType=INTEGER}");
        }
        
        if (record.getTrivialCount() != null) {
            sql.SET("trivial_count = #{trivialCount,jdbcType=INTEGER}");
        }
        
        if (record.getTotalCount() != null) {
            sql.SET("total_count = #{totalCount,jdbcType=INTEGER}");
        }
        
        if (record.getBugLink() != null) {
            sql.SET("bug_link = #{bugLink,jdbcType=VARCHAR}");
        }
        
        if (record.getStartDate() != null) {
            sql.SET("start_date = #{startDate,jdbcType=VARCHAR}");
        }
        
        if (record.getEndDate() != null) {
            sql.SET("end_date = #{endDate,jdbcType=VARCHAR}");
        }
        
        if (record.getTimeFlag() != null) {
            sql.SET("time_flag = #{timeFlag,jdbcType=BIGINT}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }

    protected void applyWhere(SQL sql, WeekBugTotalCountPOExample example, boolean includeExamplePhrase) {
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