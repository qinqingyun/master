package com.meituan.food.mapper;

import com.meituan.food.po.ApiCoveragePO;
import com.meituan.food.po.ApiCoveragePOExample.Criteria;
import com.meituan.food.po.ApiCoveragePOExample.Criterion;
import com.meituan.food.po.ApiCoveragePOExample;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class ApiCoveragePOSqlProvider {

    public String countByExample(ApiCoveragePOExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("api_coverage");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(ApiCoveragePOExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("api_coverage");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(ApiCoveragePO record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("api_coverage");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getAppkey() != null) {
            sql.VALUES("appkey", "#{appkey,jdbcType=VARCHAR}");
        }
        
        if (record.getDepartment() != null) {
            sql.VALUES("department", "#{department,jdbcType=VARCHAR}");
        }
        
        if (record.getAllApiNum() != null) {
            sql.VALUES("all_api_num", "#{allApiNum,jdbcType=INTEGER}");
        }
        
        if (record.getCoverApiNum() != null) {
            sql.VALUES("cover_api_num", "#{coverApiNum,jdbcType=INTEGER}");
        }
        
        if (record.getApiCoverage() != null) {
            sql.VALUES("api_coverage", "#{apiCoverage,jdbcType=DECIMAL}");
        }
        
        if (record.getDepartmentId() != null) {
            sql.VALUES("department_id", "#{departmentId,jdbcType=INTEGER}");
        }
        
        if (record.getAllCoreApiNum() != null) {
            sql.VALUES("all_core_api_num", "#{allCoreApiNum,jdbcType=INTEGER}");
        }
        
        if (record.getCoverCoreApiNum() != null) {
            sql.VALUES("cover_core_api_num", "#{coverCoreApiNum,jdbcType=INTEGER}");
        }
        
        if (record.getCoverageDate() != null) {
            sql.VALUES("coverage_date", "#{coverageDate,jdbcType=DATE}");
        }
        
        if (record.getCreatedTime() != null) {
            sql.VALUES("created_time", "#{createdTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getCoreApiCoverage() != null) {
            sql.VALUES("core_api_coverage", "#{coreApiCoverage,jdbcType=DECIMAL}");
        }
        
        if (record.getDepartmentId2() != null) {
            sql.VALUES("department_id_2", "#{departmentId2,jdbcType=INTEGER}");
        }
        
        if (record.getDepartment2() != null) {
            sql.VALUES("department2", "#{department2,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String selectByExample(ApiCoveragePOExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("appkey");
        sql.SELECT("department");
        sql.SELECT("all_api_num");
        sql.SELECT("cover_api_num");
        sql.SELECT("api_coverage");
        sql.SELECT("department_id");
        sql.SELECT("all_core_api_num");
        sql.SELECT("cover_core_api_num");
        sql.SELECT("coverage_date");
        sql.SELECT("created_time");
        sql.SELECT("core_api_coverage");
        sql.SELECT("department_id_2");
        sql.SELECT("department2");
        sql.FROM("api_coverage");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        ApiCoveragePO record = (ApiCoveragePO) parameter.get("record");
        ApiCoveragePOExample example = (ApiCoveragePOExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("api_coverage");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=INTEGER}");
        }
        
        if (record.getAppkey() != null) {
            sql.SET("appkey = #{record.appkey,jdbcType=VARCHAR}");
        }
        
        if (record.getDepartment() != null) {
            sql.SET("department = #{record.department,jdbcType=VARCHAR}");
        }
        
        if (record.getAllApiNum() != null) {
            sql.SET("all_api_num = #{record.allApiNum,jdbcType=INTEGER}");
        }
        
        if (record.getCoverApiNum() != null) {
            sql.SET("cover_api_num = #{record.coverApiNum,jdbcType=INTEGER}");
        }
        
        if (record.getApiCoverage() != null) {
            sql.SET("api_coverage = #{record.apiCoverage,jdbcType=DECIMAL}");
        }
        
        if (record.getDepartmentId() != null) {
            sql.SET("department_id = #{record.departmentId,jdbcType=INTEGER}");
        }
        
        if (record.getAllCoreApiNum() != null) {
            sql.SET("all_core_api_num = #{record.allCoreApiNum,jdbcType=INTEGER}");
        }
        
        if (record.getCoverCoreApiNum() != null) {
            sql.SET("cover_core_api_num = #{record.coverCoreApiNum,jdbcType=INTEGER}");
        }
        
        if (record.getCoverageDate() != null) {
            sql.SET("coverage_date = #{record.coverageDate,jdbcType=DATE}");
        }
        
        if (record.getCreatedTime() != null) {
            sql.SET("created_time = #{record.createdTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getCoreApiCoverage() != null) {
            sql.SET("core_api_coverage = #{record.coreApiCoverage,jdbcType=DECIMAL}");
        }
        
        if (record.getDepartmentId2() != null) {
            sql.SET("department_id_2 = #{record.departmentId2,jdbcType=INTEGER}");
        }
        
        if (record.getDepartment2() != null) {
            sql.SET("department2 = #{record.department2,jdbcType=VARCHAR}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("api_coverage");
        
        sql.SET("id = #{record.id,jdbcType=INTEGER}");
        sql.SET("appkey = #{record.appkey,jdbcType=VARCHAR}");
        sql.SET("department = #{record.department,jdbcType=VARCHAR}");
        sql.SET("all_api_num = #{record.allApiNum,jdbcType=INTEGER}");
        sql.SET("cover_api_num = #{record.coverApiNum,jdbcType=INTEGER}");
        sql.SET("api_coverage = #{record.apiCoverage,jdbcType=DECIMAL}");
        sql.SET("department_id = #{record.departmentId,jdbcType=INTEGER}");
        sql.SET("all_core_api_num = #{record.allCoreApiNum,jdbcType=INTEGER}");
        sql.SET("cover_core_api_num = #{record.coverCoreApiNum,jdbcType=INTEGER}");
        sql.SET("coverage_date = #{record.coverageDate,jdbcType=DATE}");
        sql.SET("created_time = #{record.createdTime,jdbcType=TIMESTAMP}");
        sql.SET("core_api_coverage = #{record.coreApiCoverage,jdbcType=DECIMAL}");
        sql.SET("department_id_2 = #{record.departmentId2,jdbcType=INTEGER}");
        sql.SET("department2 = #{record.department2,jdbcType=VARCHAR}");
        
        ApiCoveragePOExample example = (ApiCoveragePOExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(ApiCoveragePO record) {
        SQL sql = new SQL();
        sql.UPDATE("api_coverage");
        
        if (record.getAppkey() != null) {
            sql.SET("appkey = #{appkey,jdbcType=VARCHAR}");
        }
        
        if (record.getDepartment() != null) {
            sql.SET("department = #{department,jdbcType=VARCHAR}");
        }
        
        if (record.getAllApiNum() != null) {
            sql.SET("all_api_num = #{allApiNum,jdbcType=INTEGER}");
        }
        
        if (record.getCoverApiNum() != null) {
            sql.SET("cover_api_num = #{coverApiNum,jdbcType=INTEGER}");
        }
        
        if (record.getApiCoverage() != null) {
            sql.SET("api_coverage = #{apiCoverage,jdbcType=DECIMAL}");
        }
        
        if (record.getDepartmentId() != null) {
            sql.SET("department_id = #{departmentId,jdbcType=INTEGER}");
        }
        
        if (record.getAllCoreApiNum() != null) {
            sql.SET("all_core_api_num = #{allCoreApiNum,jdbcType=INTEGER}");
        }
        
        if (record.getCoverCoreApiNum() != null) {
            sql.SET("cover_core_api_num = #{coverCoreApiNum,jdbcType=INTEGER}");
        }
        
        if (record.getCoverageDate() != null) {
            sql.SET("coverage_date = #{coverageDate,jdbcType=DATE}");
        }
        
        if (record.getCreatedTime() != null) {
            sql.SET("created_time = #{createdTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getCoreApiCoverage() != null) {
            sql.SET("core_api_coverage = #{coreApiCoverage,jdbcType=DECIMAL}");
        }
        
        if (record.getDepartmentId2() != null) {
            sql.SET("department_id_2 = #{departmentId2,jdbcType=INTEGER}");
        }
        
        if (record.getDepartment2() != null) {
            sql.SET("department2 = #{department2,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }

    protected void applyWhere(SQL sql, ApiCoveragePOExample example, boolean includeExamplePhrase) {
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