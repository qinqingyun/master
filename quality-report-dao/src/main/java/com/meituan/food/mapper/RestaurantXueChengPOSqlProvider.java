package com.meituan.food.mapper;

import com.meituan.food.po.RestaurantXueChengPO;
import com.meituan.food.po.RestaurantXueChengPOExample.Criteria;
import com.meituan.food.po.RestaurantXueChengPOExample.Criterion;
import com.meituan.food.po.RestaurantXueChengPOExample;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class RestaurantXueChengPOSqlProvider {

    public String countByExample(RestaurantXueChengPOExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("restaurant_xuecheng");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(RestaurantXueChengPOExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("restaurant_xuecheng");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(RestaurantXueChengPO record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("restaurant_xuecheng");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getCreateCount() != null) {
            sql.VALUES("create_count", "#{createCount,jdbcType=BIGINT}");
        }
        
        if (record.getUpdateCount() != null) {
            sql.VALUES("update_count", "#{updateCount,jdbcType=BIGINT}");
        }
        
        if (record.getPartitionDate() != null) {
            sql.VALUES("partition_date", "#{partitionDate,jdbcType=VARCHAR}");
        }
        
        if (record.getMisId() != null) {
            sql.VALUES("mis_id", "#{misId,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.VALUES("update_time", "#{updateTime,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    public String selectByExample(RestaurantXueChengPOExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("create_count");
        sql.SELECT("update_count");
        sql.SELECT("partition_date");
        sql.SELECT("mis_id");
        sql.SELECT("create_time");
        sql.SELECT("update_time");
        sql.FROM("restaurant_xuecheng");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        RestaurantXueChengPO record = (RestaurantXueChengPO) parameter.get("record");
        RestaurantXueChengPOExample example = (RestaurantXueChengPOExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("restaurant_xuecheng");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=INTEGER}");
        }
        
        if (record.getCreateCount() != null) {
            sql.SET("create_count = #{record.createCount,jdbcType=BIGINT}");
        }
        
        if (record.getUpdateCount() != null) {
            sql.SET("update_count = #{record.updateCount,jdbcType=BIGINT}");
        }
        
        if (record.getPartitionDate() != null) {
            sql.SET("partition_date = #{record.partitionDate,jdbcType=VARCHAR}");
        }
        
        if (record.getMisId() != null) {
            sql.SET("mis_id = #{record.misId,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.SET("update_time = #{record.updateTime,jdbcType=TIMESTAMP}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("restaurant_xuecheng");
        
        sql.SET("id = #{record.id,jdbcType=INTEGER}");
        sql.SET("create_count = #{record.createCount,jdbcType=BIGINT}");
        sql.SET("update_count = #{record.updateCount,jdbcType=BIGINT}");
        sql.SET("partition_date = #{record.partitionDate,jdbcType=VARCHAR}");
        sql.SET("mis_id = #{record.misId,jdbcType=VARCHAR}");
        sql.SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
        sql.SET("update_time = #{record.updateTime,jdbcType=TIMESTAMP}");
        
        RestaurantXueChengPOExample example = (RestaurantXueChengPOExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(RestaurantXueChengPO record) {
        SQL sql = new SQL();
        sql.UPDATE("restaurant_xuecheng");
        
        if (record.getCreateCount() != null) {
            sql.SET("create_count = #{createCount,jdbcType=BIGINT}");
        }
        
        if (record.getUpdateCount() != null) {
            sql.SET("update_count = #{updateCount,jdbcType=BIGINT}");
        }
        
        if (record.getPartitionDate() != null) {
            sql.SET("partition_date = #{partitionDate,jdbcType=VARCHAR}");
        }
        
        if (record.getMisId() != null) {
            sql.SET("mis_id = #{misId,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.SET("update_time = #{updateTime,jdbcType=TIMESTAMP}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }

    protected void applyWhere(SQL sql, RestaurantXueChengPOExample example, boolean includeExamplePhrase) {
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