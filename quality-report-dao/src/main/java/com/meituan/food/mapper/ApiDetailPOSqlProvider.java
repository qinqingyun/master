package com.meituan.food.mapper;

import com.meituan.food.po.ApiDetailPO;
import com.meituan.food.po.ApiDetailPOExample.Criteria;
import com.meituan.food.po.ApiDetailPOExample.Criterion;
import com.meituan.food.po.ApiDetailPOExample;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class ApiDetailPOSqlProvider {

    public String countByExample(ApiDetailPOExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("api_detail");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(ApiDetailPOExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("api_detail");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(ApiDetailPO record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("api_detail");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getAppkey() != null) {
            sql.VALUES("appkey", "#{appkey,jdbcType=VARCHAR}");
        }
        
        if (record.getApiFullName() != null) {
            sql.VALUES("api_full_name", "#{apiFullName,jdbcType=VARCHAR}");
        }
        
        if (record.getCallCount() != null) {
            sql.VALUES("call_count", "#{callCount,jdbcType=BIGINT}");
        }
        
        if (record.getApiSpanName() != null) {
            sql.VALUES("api_span_name", "#{apiSpanName,jdbcType=VARCHAR}");
        }
        
        if (record.getProportion() != null) {
            sql.VALUES("proportion", "#{proportion,jdbcType=DECIMAL}");
        }
        
        if (record.getIsCore() != null) {
            sql.VALUES("is_core", "#{isCore,jdbcType=INTEGER}");
        }
        
        if (record.getCreatedTime() != null) {
            sql.VALUES("created_time", "#{createdTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdatedAt() != null) {
            sql.VALUES("updated_at", "#{updatedAt,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    public String selectByExample(ApiDetailPOExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("appkey");
        sql.SELECT("api_full_name");
        sql.SELECT("call_count");
        sql.SELECT("api_span_name");
        sql.SELECT("proportion");
        sql.SELECT("is_core");
        sql.SELECT("created_time");
        sql.SELECT("updated_at");
        sql.FROM("api_detail");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        ApiDetailPO record = (ApiDetailPO) parameter.get("record");
        ApiDetailPOExample example = (ApiDetailPOExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("api_detail");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=INTEGER}");
        }
        
        if (record.getAppkey() != null) {
            sql.SET("appkey = #{record.appkey,jdbcType=VARCHAR}");
        }
        
        if (record.getApiFullName() != null) {
            sql.SET("api_full_name = #{record.apiFullName,jdbcType=VARCHAR}");
        }
        
        if (record.getCallCount() != null) {
            sql.SET("call_count = #{record.callCount,jdbcType=BIGINT}");
        }
        
        if (record.getApiSpanName() != null) {
            sql.SET("api_span_name = #{record.apiSpanName,jdbcType=VARCHAR}");
        }
        
        if (record.getProportion() != null) {
            sql.SET("proportion = #{record.proportion,jdbcType=DECIMAL}");
        }
        
        if (record.getIsCore() != null) {
            sql.SET("is_core = #{record.isCore,jdbcType=INTEGER}");
        }
        
        if (record.getCreatedTime() != null) {
            sql.SET("created_time = #{record.createdTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdatedAt() != null) {
            sql.SET("updated_at = #{record.updatedAt,jdbcType=TIMESTAMP}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("api_detail");
        
        sql.SET("id = #{record.id,jdbcType=INTEGER}");
        sql.SET("appkey = #{record.appkey,jdbcType=VARCHAR}");
        sql.SET("api_full_name = #{record.apiFullName,jdbcType=VARCHAR}");
        sql.SET("call_count = #{record.callCount,jdbcType=BIGINT}");
        sql.SET("api_span_name = #{record.apiSpanName,jdbcType=VARCHAR}");
        sql.SET("proportion = #{record.proportion,jdbcType=DECIMAL}");
        sql.SET("is_core = #{record.isCore,jdbcType=INTEGER}");
        sql.SET("created_time = #{record.createdTime,jdbcType=TIMESTAMP}");
        sql.SET("updated_at = #{record.updatedAt,jdbcType=TIMESTAMP}");
        
        ApiDetailPOExample example = (ApiDetailPOExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(ApiDetailPO record) {
        SQL sql = new SQL();
        sql.UPDATE("api_detail");
        
        if (record.getAppkey() != null) {
            sql.SET("appkey = #{appkey,jdbcType=VARCHAR}");
        }
        
        if (record.getApiFullName() != null) {
            sql.SET("api_full_name = #{apiFullName,jdbcType=VARCHAR}");
        }
        
        if (record.getCallCount() != null) {
            sql.SET("call_count = #{callCount,jdbcType=BIGINT}");
        }
        
        if (record.getApiSpanName() != null) {
            sql.SET("api_span_name = #{apiSpanName,jdbcType=VARCHAR}");
        }
        
        if (record.getProportion() != null) {
            sql.SET("proportion = #{proportion,jdbcType=DECIMAL}");
        }
        
        if (record.getIsCore() != null) {
            sql.SET("is_core = #{isCore,jdbcType=INTEGER}");
        }
        
        if (record.getCreatedTime() != null) {
            sql.SET("created_time = #{createdTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdatedAt() != null) {
            sql.SET("updated_at = #{updatedAt,jdbcType=TIMESTAMP}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }

    protected void applyWhere(SQL sql, ApiDetailPOExample example, boolean includeExamplePhrase) {
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