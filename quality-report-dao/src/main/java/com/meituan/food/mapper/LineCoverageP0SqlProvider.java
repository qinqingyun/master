package com.meituan.food.mapper;

import com.meituan.food.po.LineCoverageP0;
import com.meituan.food.po.LineCoverageP0Example.Criteria;
import com.meituan.food.po.LineCoverageP0Example.Criterion;
import com.meituan.food.po.LineCoverageP0Example;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class LineCoverageP0SqlProvider {

    public String countByExample(LineCoverageP0Example example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("line_coverage_table");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(LineCoverageP0Example example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("line_coverage_table");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(LineCoverageP0 record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("line_coverage_table");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getReleaseName() != null) {
            sql.VALUES("release_name", "#{releaseName,jdbcType=VARCHAR}");
        }
        
        if (record.getCoreLineC() != null) {
            sql.VALUES("core_line_c", "#{coreLineC,jdbcType=INTEGER}");
        }
        
        if (record.getCoreLineM() != null) {
            sql.VALUES("core_line_m", "#{coreLineM,jdbcType=INTEGER}");
        }
        
        if (record.getCoreLineTotal() != null) {
            sql.VALUES("core_line_total", "#{coreLineTotal,jdbcType=INTEGER}");
        }
        
        if (record.getCoreLineCoverage() != null) {
            sql.VALUES("core_line_coverage", "#{coreLineCoverage,jdbcType=DECIMAL}");
        }
        
        if (record.getCoreLineCInterface() != null) {
            sql.VALUES("core_line_c_interface", "#{coreLineCInterface,jdbcType=VARCHAR}");
        }
        
        if (record.getTotalLineC() != null) {
            sql.VALUES("total_line_c", "#{totalLineC,jdbcType=INTEGER}");
        }
        
        if (record.getTotalLineM() != null) {
            sql.VALUES("total_line_m", "#{totalLineM,jdbcType=INTEGER}");
        }
        
        if (record.getTotalLineTotal() != null) {
            sql.VALUES("total_line_total", "#{totalLineTotal,jdbcType=INTEGER}");
        }
        
        if (record.getTotalLineCoverage() != null) {
            sql.VALUES("total_line_coverage", "#{totalLineCoverage,jdbcType=DECIMAL}");
        }
        
        if (record.getTotalLineCInterface() != null) {
            sql.VALUES("total_line_c_interface", "#{totalLineCInterface,jdbcType=VARCHAR}");
        }
        
        if (record.getCreatedTime() != null) {
            sql.VALUES("created_time", "#{createdTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.VALUES("update_time", "#{updateTime,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    public String selectByExample(LineCoverageP0Example example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("release_name");
        sql.SELECT("core_line_c");
        sql.SELECT("core_line_m");
        sql.SELECT("core_line_total");
        sql.SELECT("core_line_coverage");
        sql.SELECT("core_line_c_interface");
        sql.SELECT("total_line_c");
        sql.SELECT("total_line_m");
        sql.SELECT("total_line_total");
        sql.SELECT("total_line_coverage");
        sql.SELECT("total_line_c_interface");
        sql.SELECT("created_time");
        sql.SELECT("update_time");
        sql.FROM("line_coverage_table");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        LineCoverageP0 record = (LineCoverageP0) parameter.get("record");
        LineCoverageP0Example example = (LineCoverageP0Example) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("line_coverage_table");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=INTEGER}");
        }
        
        if (record.getReleaseName() != null) {
            sql.SET("release_name = #{record.releaseName,jdbcType=VARCHAR}");
        }
        
        if (record.getCoreLineC() != null) {
            sql.SET("core_line_c = #{record.coreLineC,jdbcType=INTEGER}");
        }
        
        if (record.getCoreLineM() != null) {
            sql.SET("core_line_m = #{record.coreLineM,jdbcType=INTEGER}");
        }
        
        if (record.getCoreLineTotal() != null) {
            sql.SET("core_line_total = #{record.coreLineTotal,jdbcType=INTEGER}");
        }
        
        if (record.getCoreLineCoverage() != null) {
            sql.SET("core_line_coverage = #{record.coreLineCoverage,jdbcType=DECIMAL}");
        }
        
        if (record.getCoreLineCInterface() != null) {
            sql.SET("core_line_c_interface = #{record.coreLineCInterface,jdbcType=VARCHAR}");
        }
        
        if (record.getTotalLineC() != null) {
            sql.SET("total_line_c = #{record.totalLineC,jdbcType=INTEGER}");
        }
        
        if (record.getTotalLineM() != null) {
            sql.SET("total_line_m = #{record.totalLineM,jdbcType=INTEGER}");
        }
        
        if (record.getTotalLineTotal() != null) {
            sql.SET("total_line_total = #{record.totalLineTotal,jdbcType=INTEGER}");
        }
        
        if (record.getTotalLineCoverage() != null) {
            sql.SET("total_line_coverage = #{record.totalLineCoverage,jdbcType=DECIMAL}");
        }
        
        if (record.getTotalLineCInterface() != null) {
            sql.SET("total_line_c_interface = #{record.totalLineCInterface,jdbcType=VARCHAR}");
        }
        
        if (record.getCreatedTime() != null) {
            sql.SET("created_time = #{record.createdTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.SET("update_time = #{record.updateTime,jdbcType=TIMESTAMP}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("line_coverage_table");
        
        sql.SET("id = #{record.id,jdbcType=INTEGER}");
        sql.SET("release_name = #{record.releaseName,jdbcType=VARCHAR}");
        sql.SET("core_line_c = #{record.coreLineC,jdbcType=INTEGER}");
        sql.SET("core_line_m = #{record.coreLineM,jdbcType=INTEGER}");
        sql.SET("core_line_total = #{record.coreLineTotal,jdbcType=INTEGER}");
        sql.SET("core_line_coverage = #{record.coreLineCoverage,jdbcType=DECIMAL}");
        sql.SET("core_line_c_interface = #{record.coreLineCInterface,jdbcType=VARCHAR}");
        sql.SET("total_line_c = #{record.totalLineC,jdbcType=INTEGER}");
        sql.SET("total_line_m = #{record.totalLineM,jdbcType=INTEGER}");
        sql.SET("total_line_total = #{record.totalLineTotal,jdbcType=INTEGER}");
        sql.SET("total_line_coverage = #{record.totalLineCoverage,jdbcType=DECIMAL}");
        sql.SET("total_line_c_interface = #{record.totalLineCInterface,jdbcType=VARCHAR}");
        sql.SET("created_time = #{record.createdTime,jdbcType=TIMESTAMP}");
        sql.SET("update_time = #{record.updateTime,jdbcType=TIMESTAMP}");
        
        LineCoverageP0Example example = (LineCoverageP0Example) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(LineCoverageP0 record) {
        SQL sql = new SQL();
        sql.UPDATE("line_coverage_table");
        
        if (record.getReleaseName() != null) {
            sql.SET("release_name = #{releaseName,jdbcType=VARCHAR}");
        }
        
        if (record.getCoreLineC() != null) {
            sql.SET("core_line_c = #{coreLineC,jdbcType=INTEGER}");
        }
        
        if (record.getCoreLineM() != null) {
            sql.SET("core_line_m = #{coreLineM,jdbcType=INTEGER}");
        }
        
        if (record.getCoreLineTotal() != null) {
            sql.SET("core_line_total = #{coreLineTotal,jdbcType=INTEGER}");
        }
        
        if (record.getCoreLineCoverage() != null) {
            sql.SET("core_line_coverage = #{coreLineCoverage,jdbcType=DECIMAL}");
        }
        
        if (record.getCoreLineCInterface() != null) {
            sql.SET("core_line_c_interface = #{coreLineCInterface,jdbcType=VARCHAR}");
        }
        
        if (record.getTotalLineC() != null) {
            sql.SET("total_line_c = #{totalLineC,jdbcType=INTEGER}");
        }
        
        if (record.getTotalLineM() != null) {
            sql.SET("total_line_m = #{totalLineM,jdbcType=INTEGER}");
        }
        
        if (record.getTotalLineTotal() != null) {
            sql.SET("total_line_total = #{totalLineTotal,jdbcType=INTEGER}");
        }
        
        if (record.getTotalLineCoverage() != null) {
            sql.SET("total_line_coverage = #{totalLineCoverage,jdbcType=DECIMAL}");
        }
        
        if (record.getTotalLineCInterface() != null) {
            sql.SET("total_line_c_interface = #{totalLineCInterface,jdbcType=VARCHAR}");
        }
        
        if (record.getCreatedTime() != null) {
            sql.SET("created_time = #{createdTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.SET("update_time = #{updateTime,jdbcType=TIMESTAMP}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }

    protected void applyWhere(SQL sql, LineCoverageP0Example example, boolean includeExamplePhrase) {
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