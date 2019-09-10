package com.meituan.food.mapper;

import com.meituan.food.po.CoeListP0;
import com.meituan.food.po.CoeListP0Example.Criteria;
import com.meituan.food.po.CoeListP0Example.Criterion;
import com.meituan.food.po.CoeListP0Example;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class CoeListP0SqlProvider {

    public String countByExample(CoeListP0Example example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("coe_list");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(CoeListP0Example example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("coe_list");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(CoeListP0 record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("coe_list");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getCoeId() != null) {
            sql.VALUES("coe_id", "#{coeId,jdbcType=INTEGER}");
        }
        
        if (record.getBrief() != null) {
            sql.VALUES("brief", "#{brief,jdbcType=VARCHAR}");
        }
        
        if (record.getOccurDate() != null) {
            sql.VALUES("occur_date", "#{occurDate,jdbcType=DATE}");
        }
        
        if (record.getNotifyTime() != null) {
            sql.VALUES("notify_time", "#{notifyTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getFindTime() != null) {
            sql.VALUES("find_time", "#{findTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getLocationTime() != null) {
            sql.VALUES("location_time", "#{locationTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getHandleTime() != null) {
            sql.VALUES("handle_time", "#{handleTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getSolvedTime() != null) {
            sql.VALUES("solved_time", "#{solvedTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getFminusoTime() != null) {
            sql.VALUES("fminuso_time", "#{fminusoTime,jdbcType=INTEGER}");
        }
        
        if (record.getLminusfTime() != null) {
            sql.VALUES("lminusf_time", "#{lminusfTime,jdbcType=INTEGER}");
        }
        
        if (record.getSminushTime() != null) {
            sql.VALUES("sminush_time", "#{sminushTime,jdbcType=INTEGER}");
        }
        
        if (record.getWiki() != null) {
            sql.VALUES("wiki", "#{wiki,jdbcType=VARCHAR}");
        }
        
        if (record.getLevel() != null) {
            sql.VALUES("level", "#{level,jdbcType=VARCHAR}");
        }
        
        if (record.getOwnerName() != null) {
            sql.VALUES("owner_name", "#{ownerName,jdbcType=VARCHAR}");
        }
        
        if (record.getOwnerMis() != null) {
            sql.VALUES("owner_mis", "#{ownerMis,jdbcType=VARCHAR}");
        }
        
        if (record.getQaName() != null) {
            sql.VALUES("qa_name", "#{qaName,jdbcType=VARCHAR}");
        }
        
        if (record.getQaMis() != null) {
            sql.VALUES("qa_mis", "#{qaMis,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String selectByExample(CoeListP0Example example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("coe_id");
        sql.SELECT("brief");
        sql.SELECT("occur_date");
        sql.SELECT("notify_time");
        sql.SELECT("find_time");
        sql.SELECT("location_time");
        sql.SELECT("handle_time");
        sql.SELECT("solved_time");
        sql.SELECT("fminuso_time");
        sql.SELECT("lminusf_time");
        sql.SELECT("sminush_time");
        sql.SELECT("wiki");
        sql.SELECT("level");
        sql.SELECT("owner_name");
        sql.SELECT("owner_mis");
        sql.SELECT("qa_name");
        sql.SELECT("qa_mis");
        sql.FROM("coe_list");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        CoeListP0 record = (CoeListP0) parameter.get("record");
        CoeListP0Example example = (CoeListP0Example) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("coe_list");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=INTEGER}");
        }
        
        if (record.getCoeId() != null) {
            sql.SET("coe_id = #{record.coeId,jdbcType=INTEGER}");
        }
        
        if (record.getBrief() != null) {
            sql.SET("brief = #{record.brief,jdbcType=VARCHAR}");
        }
        
        if (record.getOccurDate() != null) {
            sql.SET("occur_date = #{record.occurDate,jdbcType=DATE}");
        }
        
        if (record.getNotifyTime() != null) {
            sql.SET("notify_time = #{record.notifyTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getFindTime() != null) {
            sql.SET("find_time = #{record.findTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getLocationTime() != null) {
            sql.SET("location_time = #{record.locationTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getHandleTime() != null) {
            sql.SET("handle_time = #{record.handleTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getSolvedTime() != null) {
            sql.SET("solved_time = #{record.solvedTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getFminusoTime() != null) {
            sql.SET("fminuso_time = #{record.fminusoTime,jdbcType=INTEGER}");
        }
        
        if (record.getLminusfTime() != null) {
            sql.SET("lminusf_time = #{record.lminusfTime,jdbcType=INTEGER}");
        }
        
        if (record.getSminushTime() != null) {
            sql.SET("sminush_time = #{record.sminushTime,jdbcType=INTEGER}");
        }
        
        if (record.getWiki() != null) {
            sql.SET("wiki = #{record.wiki,jdbcType=VARCHAR}");
        }
        
        if (record.getLevel() != null) {
            sql.SET("level = #{record.level,jdbcType=VARCHAR}");
        }
        
        if (record.getOwnerName() != null) {
            sql.SET("owner_name = #{record.ownerName,jdbcType=VARCHAR}");
        }
        
        if (record.getOwnerMis() != null) {
            sql.SET("owner_mis = #{record.ownerMis,jdbcType=VARCHAR}");
        }
        
        if (record.getQaName() != null) {
            sql.SET("qa_name = #{record.qaName,jdbcType=VARCHAR}");
        }
        
        if (record.getQaMis() != null) {
            sql.SET("qa_mis = #{record.qaMis,jdbcType=VARCHAR}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("coe_list");
        
        sql.SET("id = #{record.id,jdbcType=INTEGER}");
        sql.SET("coe_id = #{record.coeId,jdbcType=INTEGER}");
        sql.SET("brief = #{record.brief,jdbcType=VARCHAR}");
        sql.SET("occur_date = #{record.occurDate,jdbcType=DATE}");
        sql.SET("notify_time = #{record.notifyTime,jdbcType=TIMESTAMP}");
        sql.SET("find_time = #{record.findTime,jdbcType=TIMESTAMP}");
        sql.SET("location_time = #{record.locationTime,jdbcType=TIMESTAMP}");
        sql.SET("handle_time = #{record.handleTime,jdbcType=TIMESTAMP}");
        sql.SET("solved_time = #{record.solvedTime,jdbcType=TIMESTAMP}");
        sql.SET("fminuso_time = #{record.fminusoTime,jdbcType=INTEGER}");
        sql.SET("lminusf_time = #{record.lminusfTime,jdbcType=INTEGER}");
        sql.SET("sminush_time = #{record.sminushTime,jdbcType=INTEGER}");
        sql.SET("wiki = #{record.wiki,jdbcType=VARCHAR}");
        sql.SET("level = #{record.level,jdbcType=VARCHAR}");
        sql.SET("owner_name = #{record.ownerName,jdbcType=VARCHAR}");
        sql.SET("owner_mis = #{record.ownerMis,jdbcType=VARCHAR}");
        sql.SET("qa_name = #{record.qaName,jdbcType=VARCHAR}");
        sql.SET("qa_mis = #{record.qaMis,jdbcType=VARCHAR}");
        
        CoeListP0Example example = (CoeListP0Example) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(CoeListP0 record) {
        SQL sql = new SQL();
        sql.UPDATE("coe_list");
        
        if (record.getCoeId() != null) {
            sql.SET("coe_id = #{coeId,jdbcType=INTEGER}");
        }
        
        if (record.getBrief() != null) {
            sql.SET("brief = #{brief,jdbcType=VARCHAR}");
        }
        
        if (record.getOccurDate() != null) {
            sql.SET("occur_date = #{occurDate,jdbcType=DATE}");
        }
        
        if (record.getNotifyTime() != null) {
            sql.SET("notify_time = #{notifyTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getFindTime() != null) {
            sql.SET("find_time = #{findTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getLocationTime() != null) {
            sql.SET("location_time = #{locationTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getHandleTime() != null) {
            sql.SET("handle_time = #{handleTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getSolvedTime() != null) {
            sql.SET("solved_time = #{solvedTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getFminusoTime() != null) {
            sql.SET("fminuso_time = #{fminusoTime,jdbcType=INTEGER}");
        }
        
        if (record.getLminusfTime() != null) {
            sql.SET("lminusf_time = #{lminusfTime,jdbcType=INTEGER}");
        }
        
        if (record.getSminushTime() != null) {
            sql.SET("sminush_time = #{sminushTime,jdbcType=INTEGER}");
        }
        
        if (record.getWiki() != null) {
            sql.SET("wiki = #{wiki,jdbcType=VARCHAR}");
        }
        
        if (record.getLevel() != null) {
            sql.SET("level = #{level,jdbcType=VARCHAR}");
        }
        
        if (record.getOwnerName() != null) {
            sql.SET("owner_name = #{ownerName,jdbcType=VARCHAR}");
        }
        
        if (record.getOwnerMis() != null) {
            sql.SET("owner_mis = #{ownerMis,jdbcType=VARCHAR}");
        }
        
        if (record.getQaName() != null) {
            sql.SET("qa_name = #{qaName,jdbcType=VARCHAR}");
        }
        
        if (record.getQaMis() != null) {
            sql.SET("qa_mis = #{qaMis,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }

    protected void applyWhere(SQL sql, CoeListP0Example example, boolean includeExamplePhrase) {
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