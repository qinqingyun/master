package com.meituan.food.mapper;

import com.meituan.food.po.CargoDataPO;
import com.meituan.food.po.CargoDataPOExample.Criteria;
import com.meituan.food.po.CargoDataPOExample.Criterion;
import com.meituan.food.po.CargoDataPOExample;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class CargoDataPOSqlProvider {

    public String countByExample(CargoDataPOExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("cargo_data");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(CargoDataPOExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("cargo_data");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(CargoDataPO record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("cargo_data");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getStackuuid() != null) {
            sql.VALUES("stackuuid", "#{stackuuid,jdbcType=VARCHAR}");
        }
        
        if (record.getStableSuccess() != null) {
            sql.VALUES("stable_success", "#{stableSuccess,jdbcType=INTEGER}");
        }
        
        if (record.getStableTotal() != null) {
            sql.VALUES("stable_total", "#{stableTotal,jdbcType=INTEGER}");
        }
        
        if (record.getAvalibleSuccess() != null) {
            sql.VALUES("avalible_success", "#{avalibleSuccess,jdbcType=INTEGER}");
        }
        
        if (record.getAvalibleTotal() != null) {
            sql.VALUES("avalible_total", "#{avalibleTotal,jdbcType=INTEGER}");
        }
        
        if (record.getTag() != null) {
            sql.VALUES("tag", "#{tag,jdbcType=VARCHAR}");
        }
        
        if (record.getPerson() != null) {
            sql.VALUES("person", "#{person,jdbcType=VARCHAR}");
        }
        
        if (record.getDirection() != null) {
            sql.VALUES("direction", "#{direction,jdbcType=VARCHAR}");
        }
        
        if (record.getStableTagPercentage() != null) {
            sql.VALUES("stable_tag_percentage", "#{stableTagPercentage,jdbcType=VARCHAR}");
        }
        
        if (record.getAvalibleTagPercentage() != null) {
            sql.VALUES("avalible_tag_percentage", "#{avalibleTagPercentage,jdbcType=VARCHAR}");
        }
        
        if (record.getDate() != null) {
            sql.VALUES("date", "#{date,jdbcType=TIMESTAMP}");
        }
        
        if (record.getComment() != null) {
            sql.VALUES("comment", "#{comment,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdatedDate() != null) {
            sql.VALUES("updated_date", "#{updatedDate,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    public String selectByExample(CargoDataPOExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("stackuuid");
        sql.SELECT("stable_success");
        sql.SELECT("stable_total");
        sql.SELECT("avalible_success");
        sql.SELECT("avalible_total");
        sql.SELECT("tag");
        sql.SELECT("person");
        sql.SELECT("direction");
        sql.SELECT("stable_tag_percentage");
        sql.SELECT("avalible_tag_percentage");
        sql.SELECT("date");
        sql.SELECT("comment");
        sql.SELECT("updated_date");
        sql.FROM("cargo_data");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        CargoDataPO record = (CargoDataPO) parameter.get("record");
        CargoDataPOExample example = (CargoDataPOExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("cargo_data");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=INTEGER}");
        }
        
        if (record.getStackuuid() != null) {
            sql.SET("stackuuid = #{record.stackuuid,jdbcType=VARCHAR}");
        }
        
        if (record.getStableSuccess() != null) {
            sql.SET("stable_success = #{record.stableSuccess,jdbcType=INTEGER}");
        }
        
        if (record.getStableTotal() != null) {
            sql.SET("stable_total = #{record.stableTotal,jdbcType=INTEGER}");
        }
        
        if (record.getAvalibleSuccess() != null) {
            sql.SET("avalible_success = #{record.avalibleSuccess,jdbcType=INTEGER}");
        }
        
        if (record.getAvalibleTotal() != null) {
            sql.SET("avalible_total = #{record.avalibleTotal,jdbcType=INTEGER}");
        }
        
        if (record.getTag() != null) {
            sql.SET("tag = #{record.tag,jdbcType=VARCHAR}");
        }
        
        if (record.getPerson() != null) {
            sql.SET("person = #{record.person,jdbcType=VARCHAR}");
        }
        
        if (record.getDirection() != null) {
            sql.SET("direction = #{record.direction,jdbcType=VARCHAR}");
        }
        
        if (record.getStableTagPercentage() != null) {
            sql.SET("stable_tag_percentage = #{record.stableTagPercentage,jdbcType=VARCHAR}");
        }
        
        if (record.getAvalibleTagPercentage() != null) {
            sql.SET("avalible_tag_percentage = #{record.avalibleTagPercentage,jdbcType=VARCHAR}");
        }
        
        if (record.getDate() != null) {
            sql.SET("date = #{record.date,jdbcType=TIMESTAMP}");
        }
        
        if (record.getComment() != null) {
            sql.SET("comment = #{record.comment,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdatedDate() != null) {
            sql.SET("updated_date = #{record.updatedDate,jdbcType=TIMESTAMP}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("cargo_data");
        
        sql.SET("id = #{record.id,jdbcType=INTEGER}");
        sql.SET("stackuuid = #{record.stackuuid,jdbcType=VARCHAR}");
        sql.SET("stable_success = #{record.stableSuccess,jdbcType=INTEGER}");
        sql.SET("stable_total = #{record.stableTotal,jdbcType=INTEGER}");
        sql.SET("avalible_success = #{record.avalibleSuccess,jdbcType=INTEGER}");
        sql.SET("avalible_total = #{record.avalibleTotal,jdbcType=INTEGER}");
        sql.SET("tag = #{record.tag,jdbcType=VARCHAR}");
        sql.SET("person = #{record.person,jdbcType=VARCHAR}");
        sql.SET("direction = #{record.direction,jdbcType=VARCHAR}");
        sql.SET("stable_tag_percentage = #{record.stableTagPercentage,jdbcType=VARCHAR}");
        sql.SET("avalible_tag_percentage = #{record.avalibleTagPercentage,jdbcType=VARCHAR}");
        sql.SET("date = #{record.date,jdbcType=TIMESTAMP}");
        sql.SET("comment = #{record.comment,jdbcType=VARCHAR}");
        sql.SET("updated_date = #{record.updatedDate,jdbcType=TIMESTAMP}");
        
        CargoDataPOExample example = (CargoDataPOExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(CargoDataPO record) {
        SQL sql = new SQL();
        sql.UPDATE("cargo_data");
        
        if (record.getStackuuid() != null) {
            sql.SET("stackuuid = #{stackuuid,jdbcType=VARCHAR}");
        }
        
        if (record.getStableSuccess() != null) {
            sql.SET("stable_success = #{stableSuccess,jdbcType=INTEGER}");
        }
        
        if (record.getStableTotal() != null) {
            sql.SET("stable_total = #{stableTotal,jdbcType=INTEGER}");
        }
        
        if (record.getAvalibleSuccess() != null) {
            sql.SET("avalible_success = #{avalibleSuccess,jdbcType=INTEGER}");
        }
        
        if (record.getAvalibleTotal() != null) {
            sql.SET("avalible_total = #{avalibleTotal,jdbcType=INTEGER}");
        }
        
        if (record.getTag() != null) {
            sql.SET("tag = #{tag,jdbcType=VARCHAR}");
        }
        
        if (record.getPerson() != null) {
            sql.SET("person = #{person,jdbcType=VARCHAR}");
        }
        
        if (record.getDirection() != null) {
            sql.SET("direction = #{direction,jdbcType=VARCHAR}");
        }
        
        if (record.getStableTagPercentage() != null) {
            sql.SET("stable_tag_percentage = #{stableTagPercentage,jdbcType=VARCHAR}");
        }
        
        if (record.getAvalibleTagPercentage() != null) {
            sql.SET("avalible_tag_percentage = #{avalibleTagPercentage,jdbcType=VARCHAR}");
        }
        
        if (record.getDate() != null) {
            sql.SET("date = #{date,jdbcType=TIMESTAMP}");
        }
        
        if (record.getComment() != null) {
            sql.SET("comment = #{comment,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdatedDate() != null) {
            sql.SET("updated_date = #{updatedDate,jdbcType=TIMESTAMP}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }

    protected void applyWhere(SQL sql, CargoDataPOExample example, boolean includeExamplePhrase) {
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