package com.meituan.food.mapper;

import com.meituan.food.po.ApiCoverageDetailP0;
import com.meituan.food.po.ApiCoverageDetailP0Example.Criteria;
import com.meituan.food.po.ApiCoverageDetailP0Example.Criterion;
import com.meituan.food.po.ApiCoverageDetailP0Example;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class ApiCoverageDetailP0SqlProvider {

    public String countByExample(ApiCoverageDetailP0Example example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("api_coverage_detail");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(ApiCoverageDetailP0Example example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("api_coverage_detail");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(ApiCoverageDetailP0 record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("api_coverage_detail");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getAppkey() != null) {
            sql.VALUES("appkey", "#{appkey,jdbcType=VARCHAR}");
        }
        
        if (record.getApiName() != null) {
            sql.VALUES("api_name", "#{apiName,jdbcType=VARCHAR}");
        }
        
        if (record.getCoverageDate() != null) {
            sql.VALUES("coverage_date", "#{coverageDate,jdbcType=DATE}");
        }
        
        if (record.getCreatedTime() != null) {
            sql.VALUES("created_time", "#{createdTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getIsCover() != null) {
            sql.VALUES("is_cover", "#{isCover,jdbcType=BIT}");
        }
        
        return sql.toString();
    }

    public String selectByExample(ApiCoverageDetailP0Example example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("appkey");
        sql.SELECT("api_name");
        sql.SELECT("coverage_date");
        sql.SELECT("created_time");
        sql.SELECT("is_cover");
        sql.FROM("api_coverage_detail");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        ApiCoverageDetailP0 record = (ApiCoverageDetailP0) parameter.get("record");
        ApiCoverageDetailP0Example example = (ApiCoverageDetailP0Example) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("api_coverage_detail");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=INTEGER}");
        }
        
        if (record.getAppkey() != null) {
            sql.SET("appkey = #{record.appkey,jdbcType=VARCHAR}");
        }
        
        if (record.getApiName() != null) {
            sql.SET("api_name = #{record.apiName,jdbcType=VARCHAR}");
        }
        
        if (record.getCoverageDate() != null) {
            sql.SET("coverage_date = #{record.coverageDate,jdbcType=DATE}");
        }
        
        if (record.getCreatedTime() != null) {
            sql.SET("created_time = #{record.createdTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getIsCover() != null) {
            sql.SET("is_cover = #{record.isCover,jdbcType=BIT}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("api_coverage_detail");
        
        sql.SET("id = #{record.id,jdbcType=INTEGER}");
        sql.SET("appkey = #{record.appkey,jdbcType=VARCHAR}");
        sql.SET("api_name = #{record.apiName,jdbcType=VARCHAR}");
        sql.SET("coverage_date = #{record.coverageDate,jdbcType=DATE}");
        sql.SET("created_time = #{record.createdTime,jdbcType=TIMESTAMP}");
        sql.SET("is_cover = #{record.isCover,jdbcType=BIT}");
        
        ApiCoverageDetailP0Example example = (ApiCoverageDetailP0Example) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(ApiCoverageDetailP0 record) {
        SQL sql = new SQL();
        sql.UPDATE("api_coverage_detail");
        
        if (record.getAppkey() != null) {
            sql.SET("appkey = #{appkey,jdbcType=VARCHAR}");
        }
        
        if (record.getApiName() != null) {
            sql.SET("api_name = #{apiName,jdbcType=VARCHAR}");
        }
        
        if (record.getCoverageDate() != null) {
            sql.SET("coverage_date = #{coverageDate,jdbcType=DATE}");
        }
        
        if (record.getCreatedTime() != null) {
            sql.SET("created_time = #{createdTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getIsCover() != null) {
            sql.SET("is_cover = #{isCover,jdbcType=BIT}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }

    protected void applyWhere(SQL sql, ApiCoverageDetailP0Example example, boolean includeExamplePhrase) {
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