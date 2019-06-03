package com.meituan.food.mapper;

import com.meituan.food.po.AppkeyAdminPO;
import com.meituan.food.po.AppkeyAdminPOExample.Criteria;
import com.meituan.food.po.AppkeyAdminPOExample.Criterion;
import com.meituan.food.po.AppkeyAdminPOExample;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class AppkeyAdminPOSqlProvider {

    public String countByExample(AppkeyAdminPOExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("appkey_admin");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(AppkeyAdminPOExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("appkey_admin");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(AppkeyAdminPO record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("appkey_admin");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getAppkeyId() != null) {
            sql.VALUES("appkey_id", "#{appkeyId,jdbcType=INTEGER}");
        }
        
        if (record.getAppkey() != null) {
            sql.VALUES("appkey", "#{appkey,jdbcType=VARCHAR}");
        }
        
        if (record.getAdminName() != null) {
            sql.VALUES("admin_name", "#{adminName,jdbcType=VARCHAR}");
        }
        
        if (record.getAdminId() != null) {
            sql.VALUES("admin_id", "#{adminId,jdbcType=INTEGER}");
        }
        
        if (record.getCreatorId() != null) {
            sql.VALUES("creator_id", "#{creatorId,jdbcType=INTEGER}");
        }
        
        if (record.getCreatedTime() != null) {
            sql.VALUES("created_time", "#{createdTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdatedTime() != null) {
            sql.VALUES("updated_time", "#{updatedTime,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    public String selectByExample(AppkeyAdminPOExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("appkey_id");
        sql.SELECT("appkey");
        sql.SELECT("admin_name");
        sql.SELECT("admin_id");
        sql.SELECT("creator_id");
        sql.SELECT("created_time");
        sql.SELECT("updated_time");
        sql.FROM("appkey_admin");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        AppkeyAdminPO record = (AppkeyAdminPO) parameter.get("record");
        AppkeyAdminPOExample example = (AppkeyAdminPOExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("appkey_admin");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=INTEGER}");
        }
        
        if (record.getAppkeyId() != null) {
            sql.SET("appkey_id = #{record.appkeyId,jdbcType=INTEGER}");
        }
        
        if (record.getAppkey() != null) {
            sql.SET("appkey = #{record.appkey,jdbcType=VARCHAR}");
        }
        
        if (record.getAdminName() != null) {
            sql.SET("admin_name = #{record.adminName,jdbcType=VARCHAR}");
        }
        
        if (record.getAdminId() != null) {
            sql.SET("admin_id = #{record.adminId,jdbcType=INTEGER}");
        }
        
        if (record.getCreatorId() != null) {
            sql.SET("creator_id = #{record.creatorId,jdbcType=INTEGER}");
        }
        
        if (record.getCreatedTime() != null) {
            sql.SET("created_time = #{record.createdTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdatedTime() != null) {
            sql.SET("updated_time = #{record.updatedTime,jdbcType=TIMESTAMP}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("appkey_admin");
        
        sql.SET("id = #{record.id,jdbcType=INTEGER}");
        sql.SET("appkey_id = #{record.appkeyId,jdbcType=INTEGER}");
        sql.SET("appkey = #{record.appkey,jdbcType=VARCHAR}");
        sql.SET("admin_name = #{record.adminName,jdbcType=VARCHAR}");
        sql.SET("admin_id = #{record.adminId,jdbcType=INTEGER}");
        sql.SET("creator_id = #{record.creatorId,jdbcType=INTEGER}");
        sql.SET("created_time = #{record.createdTime,jdbcType=TIMESTAMP}");
        sql.SET("updated_time = #{record.updatedTime,jdbcType=TIMESTAMP}");
        
        AppkeyAdminPOExample example = (AppkeyAdminPOExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(AppkeyAdminPO record) {
        SQL sql = new SQL();
        sql.UPDATE("appkey_admin");
        
        if (record.getAppkeyId() != null) {
            sql.SET("appkey_id = #{appkeyId,jdbcType=INTEGER}");
        }
        
        if (record.getAppkey() != null) {
            sql.SET("appkey = #{appkey,jdbcType=VARCHAR}");
        }
        
        if (record.getAdminName() != null) {
            sql.SET("admin_name = #{adminName,jdbcType=VARCHAR}");
        }
        
        if (record.getAdminId() != null) {
            sql.SET("admin_id = #{adminId,jdbcType=INTEGER}");
        }
        
        if (record.getCreatorId() != null) {
            sql.SET("creator_id = #{creatorId,jdbcType=INTEGER}");
        }
        
        if (record.getCreatedTime() != null) {
            sql.SET("created_time = #{createdTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdatedTime() != null) {
            sql.SET("updated_time = #{updatedTime,jdbcType=TIMESTAMP}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }

    protected void applyWhere(SQL sql, AppkeyAdminPOExample example, boolean includeExamplePhrase) {
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