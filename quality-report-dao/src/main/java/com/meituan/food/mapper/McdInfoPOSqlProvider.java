package com.meituan.food.mapper;

import com.meituan.food.po.McdInfoPO;
import com.meituan.food.po.McdInfoPOExample.Criteria;
import com.meituan.food.po.McdInfoPOExample.Criterion;
import com.meituan.food.po.McdInfoPOExample;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class McdInfoPOSqlProvider {

    public String countByExample(McdInfoPOExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("mcd_info_table");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(McdInfoPOExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("mcd_info_table");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(McdInfoPO record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("mcd_info_table");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getMcdId() != null) {
            sql.VALUES("mcd_id", "#{mcdId,jdbcType=INTEGER}");
        }
        
        if (record.getMcdName() != null) {
            sql.VALUES("mcd_name", "#{mcdName,jdbcType=VARCHAR}");
        }
        
        if (record.getMcdFatherId() != null) {
            sql.VALUES("mcd_father_id", "#{mcdFatherId,jdbcType=INTEGER}");
        }
        
        return sql.toString();
    }

    public String selectByExample(McdInfoPOExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("mcd_id");
        sql.SELECT("mcd_name");
        sql.SELECT("mcd_father_id");
        sql.FROM("mcd_info_table");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        McdInfoPO record = (McdInfoPO) parameter.get("record");
        McdInfoPOExample example = (McdInfoPOExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("mcd_info_table");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=INTEGER}");
        }
        
        if (record.getMcdId() != null) {
            sql.SET("mcd_id = #{record.mcdId,jdbcType=INTEGER}");
        }
        
        if (record.getMcdName() != null) {
            sql.SET("mcd_name = #{record.mcdName,jdbcType=VARCHAR}");
        }
        
        if (record.getMcdFatherId() != null) {
            sql.SET("mcd_father_id = #{record.mcdFatherId,jdbcType=INTEGER}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("mcd_info_table");
        
        sql.SET("id = #{record.id,jdbcType=INTEGER}");
        sql.SET("mcd_id = #{record.mcdId,jdbcType=INTEGER}");
        sql.SET("mcd_name = #{record.mcdName,jdbcType=VARCHAR}");
        sql.SET("mcd_father_id = #{record.mcdFatherId,jdbcType=INTEGER}");
        
        McdInfoPOExample example = (McdInfoPOExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(McdInfoPO record) {
        SQL sql = new SQL();
        sql.UPDATE("mcd_info_table");
        
        if (record.getMcdId() != null) {
            sql.SET("mcd_id = #{mcdId,jdbcType=INTEGER}");
        }
        
        if (record.getMcdName() != null) {
            sql.SET("mcd_name = #{mcdName,jdbcType=VARCHAR}");
        }
        
        if (record.getMcdFatherId() != null) {
            sql.SET("mcd_father_id = #{mcdFatherId,jdbcType=INTEGER}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }

    protected void applyWhere(SQL sql, McdInfoPOExample example, boolean includeExamplePhrase) {
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