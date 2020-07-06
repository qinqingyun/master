package com.meituan.food.po;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class McdCoePOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public McdCoePOExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCoeIdIsNull() {
            addCriterion("coe_id is null");
            return (Criteria) this;
        }

        public Criteria andCoeIdIsNotNull() {
            addCriterion("coe_id is not null");
            return (Criteria) this;
        }

        public Criteria andCoeIdEqualTo(Integer value) {
            addCriterion("coe_id =", value, "coeId");
            return (Criteria) this;
        }

        public Criteria andCoeIdNotEqualTo(Integer value) {
            addCriterion("coe_id <>", value, "coeId");
            return (Criteria) this;
        }

        public Criteria andCoeIdGreaterThan(Integer value) {
            addCriterion("coe_id >", value, "coeId");
            return (Criteria) this;
        }

        public Criteria andCoeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("coe_id >=", value, "coeId");
            return (Criteria) this;
        }

        public Criteria andCoeIdLessThan(Integer value) {
            addCriterion("coe_id <", value, "coeId");
            return (Criteria) this;
        }

        public Criteria andCoeIdLessThanOrEqualTo(Integer value) {
            addCriterion("coe_id <=", value, "coeId");
            return (Criteria) this;
        }

        public Criteria andCoeIdIn(List<Integer> values) {
            addCriterion("coe_id in", values, "coeId");
            return (Criteria) this;
        }

        public Criteria andCoeIdNotIn(List<Integer> values) {
            addCriterion("coe_id not in", values, "coeId");
            return (Criteria) this;
        }

        public Criteria andCoeIdBetween(Integer value1, Integer value2) {
            addCriterion("coe_id between", value1, value2, "coeId");
            return (Criteria) this;
        }

        public Criteria andCoeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("coe_id not between", value1, value2, "coeId");
            return (Criteria) this;
        }

        public Criteria andBriefIsNull() {
            addCriterion("brief is null");
            return (Criteria) this;
        }

        public Criteria andBriefIsNotNull() {
            addCriterion("brief is not null");
            return (Criteria) this;
        }

        public Criteria andBriefEqualTo(String value) {
            addCriterion("brief =", value, "brief");
            return (Criteria) this;
        }

        public Criteria andBriefNotEqualTo(String value) {
            addCriterion("brief <>", value, "brief");
            return (Criteria) this;
        }

        public Criteria andBriefGreaterThan(String value) {
            addCriterion("brief >", value, "brief");
            return (Criteria) this;
        }

        public Criteria andBriefGreaterThanOrEqualTo(String value) {
            addCriterion("brief >=", value, "brief");
            return (Criteria) this;
        }

        public Criteria andBriefLessThan(String value) {
            addCriterion("brief <", value, "brief");
            return (Criteria) this;
        }

        public Criteria andBriefLessThanOrEqualTo(String value) {
            addCriterion("brief <=", value, "brief");
            return (Criteria) this;
        }

        public Criteria andBriefLike(String value) {
            addCriterion("brief like", value, "brief");
            return (Criteria) this;
        }

        public Criteria andBriefNotLike(String value) {
            addCriterion("brief not like", value, "brief");
            return (Criteria) this;
        }

        public Criteria andBriefIn(List<String> values) {
            addCriterion("brief in", values, "brief");
            return (Criteria) this;
        }

        public Criteria andBriefNotIn(List<String> values) {
            addCriterion("brief not in", values, "brief");
            return (Criteria) this;
        }

        public Criteria andBriefBetween(String value1, String value2) {
            addCriterion("brief between", value1, value2, "brief");
            return (Criteria) this;
        }

        public Criteria andBriefNotBetween(String value1, String value2) {
            addCriterion("brief not between", value1, value2, "brief");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andOccurTimeIsNull() {
            addCriterion("occur_time is null");
            return (Criteria) this;
        }

        public Criteria andOccurTimeIsNotNull() {
            addCriterion("occur_time is not null");
            return (Criteria) this;
        }

        public Criteria andOccurTimeEqualTo(Date value) {
            addCriterion("occur_time =", value, "occurTime");
            return (Criteria) this;
        }

        public Criteria andOccurTimeNotEqualTo(Date value) {
            addCriterion("occur_time <>", value, "occurTime");
            return (Criteria) this;
        }

        public Criteria andOccurTimeGreaterThan(Date value) {
            addCriterion("occur_time >", value, "occurTime");
            return (Criteria) this;
        }

        public Criteria andOccurTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("occur_time >=", value, "occurTime");
            return (Criteria) this;
        }

        public Criteria andOccurTimeLessThan(Date value) {
            addCriterion("occur_time <", value, "occurTime");
            return (Criteria) this;
        }

        public Criteria andOccurTimeLessThanOrEqualTo(Date value) {
            addCriterion("occur_time <=", value, "occurTime");
            return (Criteria) this;
        }

        public Criteria andOccurTimeIn(List<Date> values) {
            addCriterion("occur_time in", values, "occurTime");
            return (Criteria) this;
        }

        public Criteria andOccurTimeNotIn(List<Date> values) {
            addCriterion("occur_time not in", values, "occurTime");
            return (Criteria) this;
        }

        public Criteria andOccurTimeBetween(Date value1, Date value2) {
            addCriterion("occur_time between", value1, value2, "occurTime");
            return (Criteria) this;
        }

        public Criteria andOccurTimeNotBetween(Date value1, Date value2) {
            addCriterion("occur_time not between", value1, value2, "occurTime");
            return (Criteria) this;
        }

        public Criteria andOccurDateIsNull() {
            addCriterion("occur_date is null");
            return (Criteria) this;
        }

        public Criteria andOccurDateIsNotNull() {
            addCriterion("occur_date is not null");
            return (Criteria) this;
        }

        public Criteria andOccurDateEqualTo(Date value) {
            addCriterionForJDBCDate("occur_date =", value, "occurDate");
            return (Criteria) this;
        }

        public Criteria andOccurDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("occur_date <>", value, "occurDate");
            return (Criteria) this;
        }

        public Criteria andOccurDateGreaterThan(Date value) {
            addCriterionForJDBCDate("occur_date >", value, "occurDate");
            return (Criteria) this;
        }

        public Criteria andOccurDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("occur_date >=", value, "occurDate");
            return (Criteria) this;
        }

        public Criteria andOccurDateLessThan(Date value) {
            addCriterionForJDBCDate("occur_date <", value, "occurDate");
            return (Criteria) this;
        }

        public Criteria andOccurDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("occur_date <=", value, "occurDate");
            return (Criteria) this;
        }

        public Criteria andOccurDateIn(List<Date> values) {
            addCriterionForJDBCDate("occur_date in", values, "occurDate");
            return (Criteria) this;
        }

        public Criteria andOccurDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("occur_date not in", values, "occurDate");
            return (Criteria) this;
        }

        public Criteria andOccurDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("occur_date between", value1, value2, "occurDate");
            return (Criteria) this;
        }

        public Criteria andOccurDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("occur_date not between", value1, value2, "occurDate");
            return (Criteria) this;
        }

        public Criteria andBuildTimeIsNull() {
            addCriterion("build_time is null");
            return (Criteria) this;
        }

        public Criteria andBuildTimeIsNotNull() {
            addCriterion("build_time is not null");
            return (Criteria) this;
        }

        public Criteria andBuildTimeEqualTo(Date value) {
            addCriterion("build_time =", value, "buildTime");
            return (Criteria) this;
        }

        public Criteria andBuildTimeNotEqualTo(Date value) {
            addCriterion("build_time <>", value, "buildTime");
            return (Criteria) this;
        }

        public Criteria andBuildTimeGreaterThan(Date value) {
            addCriterion("build_time >", value, "buildTime");
            return (Criteria) this;
        }

        public Criteria andBuildTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("build_time >=", value, "buildTime");
            return (Criteria) this;
        }

        public Criteria andBuildTimeLessThan(Date value) {
            addCriterion("build_time <", value, "buildTime");
            return (Criteria) this;
        }

        public Criteria andBuildTimeLessThanOrEqualTo(Date value) {
            addCriterion("build_time <=", value, "buildTime");
            return (Criteria) this;
        }

        public Criteria andBuildTimeIn(List<Date> values) {
            addCriterion("build_time in", values, "buildTime");
            return (Criteria) this;
        }

        public Criteria andBuildTimeNotIn(List<Date> values) {
            addCriterion("build_time not in", values, "buildTime");
            return (Criteria) this;
        }

        public Criteria andBuildTimeBetween(Date value1, Date value2) {
            addCriterion("build_time between", value1, value2, "buildTime");
            return (Criteria) this;
        }

        public Criteria andBuildTimeNotBetween(Date value1, Date value2) {
            addCriterion("build_time not between", value1, value2, "buildTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andNotifyTimeIsNull() {
            addCriterion("notify_time is null");
            return (Criteria) this;
        }

        public Criteria andNotifyTimeIsNotNull() {
            addCriterion("notify_time is not null");
            return (Criteria) this;
        }

        public Criteria andNotifyTimeEqualTo(Date value) {
            addCriterion("notify_time =", value, "notifyTime");
            return (Criteria) this;
        }

        public Criteria andNotifyTimeNotEqualTo(Date value) {
            addCriterion("notify_time <>", value, "notifyTime");
            return (Criteria) this;
        }

        public Criteria andNotifyTimeGreaterThan(Date value) {
            addCriterion("notify_time >", value, "notifyTime");
            return (Criteria) this;
        }

        public Criteria andNotifyTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("notify_time >=", value, "notifyTime");
            return (Criteria) this;
        }

        public Criteria andNotifyTimeLessThan(Date value) {
            addCriterion("notify_time <", value, "notifyTime");
            return (Criteria) this;
        }

        public Criteria andNotifyTimeLessThanOrEqualTo(Date value) {
            addCriterion("notify_time <=", value, "notifyTime");
            return (Criteria) this;
        }

        public Criteria andNotifyTimeIn(List<Date> values) {
            addCriterion("notify_time in", values, "notifyTime");
            return (Criteria) this;
        }

        public Criteria andNotifyTimeNotIn(List<Date> values) {
            addCriterion("notify_time not in", values, "notifyTime");
            return (Criteria) this;
        }

        public Criteria andNotifyTimeBetween(Date value1, Date value2) {
            addCriterion("notify_time between", value1, value2, "notifyTime");
            return (Criteria) this;
        }

        public Criteria andNotifyTimeNotBetween(Date value1, Date value2) {
            addCriterion("notify_time not between", value1, value2, "notifyTime");
            return (Criteria) this;
        }

        public Criteria andFindTimeIsNull() {
            addCriterion("find_time is null");
            return (Criteria) this;
        }

        public Criteria andFindTimeIsNotNull() {
            addCriterion("find_time is not null");
            return (Criteria) this;
        }

        public Criteria andFindTimeEqualTo(Date value) {
            addCriterion("find_time =", value, "findTime");
            return (Criteria) this;
        }

        public Criteria andFindTimeNotEqualTo(Date value) {
            addCriterion("find_time <>", value, "findTime");
            return (Criteria) this;
        }

        public Criteria andFindTimeGreaterThan(Date value) {
            addCriterion("find_time >", value, "findTime");
            return (Criteria) this;
        }

        public Criteria andFindTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("find_time >=", value, "findTime");
            return (Criteria) this;
        }

        public Criteria andFindTimeLessThan(Date value) {
            addCriterion("find_time <", value, "findTime");
            return (Criteria) this;
        }

        public Criteria andFindTimeLessThanOrEqualTo(Date value) {
            addCriterion("find_time <=", value, "findTime");
            return (Criteria) this;
        }

        public Criteria andFindTimeIn(List<Date> values) {
            addCriterion("find_time in", values, "findTime");
            return (Criteria) this;
        }

        public Criteria andFindTimeNotIn(List<Date> values) {
            addCriterion("find_time not in", values, "findTime");
            return (Criteria) this;
        }

        public Criteria andFindTimeBetween(Date value1, Date value2) {
            addCriterion("find_time between", value1, value2, "findTime");
            return (Criteria) this;
        }

        public Criteria andFindTimeNotBetween(Date value1, Date value2) {
            addCriterion("find_time not between", value1, value2, "findTime");
            return (Criteria) this;
        }

        public Criteria andLocationTimeIsNull() {
            addCriterion("location_time is null");
            return (Criteria) this;
        }

        public Criteria andLocationTimeIsNotNull() {
            addCriterion("location_time is not null");
            return (Criteria) this;
        }

        public Criteria andLocationTimeEqualTo(Date value) {
            addCriterion("location_time =", value, "locationTime");
            return (Criteria) this;
        }

        public Criteria andLocationTimeNotEqualTo(Date value) {
            addCriterion("location_time <>", value, "locationTime");
            return (Criteria) this;
        }

        public Criteria andLocationTimeGreaterThan(Date value) {
            addCriterion("location_time >", value, "locationTime");
            return (Criteria) this;
        }

        public Criteria andLocationTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("location_time >=", value, "locationTime");
            return (Criteria) this;
        }

        public Criteria andLocationTimeLessThan(Date value) {
            addCriterion("location_time <", value, "locationTime");
            return (Criteria) this;
        }

        public Criteria andLocationTimeLessThanOrEqualTo(Date value) {
            addCriterion("location_time <=", value, "locationTime");
            return (Criteria) this;
        }

        public Criteria andLocationTimeIn(List<Date> values) {
            addCriterion("location_time in", values, "locationTime");
            return (Criteria) this;
        }

        public Criteria andLocationTimeNotIn(List<Date> values) {
            addCriterion("location_time not in", values, "locationTime");
            return (Criteria) this;
        }

        public Criteria andLocationTimeBetween(Date value1, Date value2) {
            addCriterion("location_time between", value1, value2, "locationTime");
            return (Criteria) this;
        }

        public Criteria andLocationTimeNotBetween(Date value1, Date value2) {
            addCriterion("location_time not between", value1, value2, "locationTime");
            return (Criteria) this;
        }

        public Criteria andHandleTimeIsNull() {
            addCriterion("handle_time is null");
            return (Criteria) this;
        }

        public Criteria andHandleTimeIsNotNull() {
            addCriterion("handle_time is not null");
            return (Criteria) this;
        }

        public Criteria andHandleTimeEqualTo(Date value) {
            addCriterion("handle_time =", value, "handleTime");
            return (Criteria) this;
        }

        public Criteria andHandleTimeNotEqualTo(Date value) {
            addCriterion("handle_time <>", value, "handleTime");
            return (Criteria) this;
        }

        public Criteria andHandleTimeGreaterThan(Date value) {
            addCriterion("handle_time >", value, "handleTime");
            return (Criteria) this;
        }

        public Criteria andHandleTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("handle_time >=", value, "handleTime");
            return (Criteria) this;
        }

        public Criteria andHandleTimeLessThan(Date value) {
            addCriterion("handle_time <", value, "handleTime");
            return (Criteria) this;
        }

        public Criteria andHandleTimeLessThanOrEqualTo(Date value) {
            addCriterion("handle_time <=", value, "handleTime");
            return (Criteria) this;
        }

        public Criteria andHandleTimeIn(List<Date> values) {
            addCriterion("handle_time in", values, "handleTime");
            return (Criteria) this;
        }

        public Criteria andHandleTimeNotIn(List<Date> values) {
            addCriterion("handle_time not in", values, "handleTime");
            return (Criteria) this;
        }

        public Criteria andHandleTimeBetween(Date value1, Date value2) {
            addCriterion("handle_time between", value1, value2, "handleTime");
            return (Criteria) this;
        }

        public Criteria andHandleTimeNotBetween(Date value1, Date value2) {
            addCriterion("handle_time not between", value1, value2, "handleTime");
            return (Criteria) this;
        }

        public Criteria andSolvedTimeIsNull() {
            addCriterion("solved_time is null");
            return (Criteria) this;
        }

        public Criteria andSolvedTimeIsNotNull() {
            addCriterion("solved_time is not null");
            return (Criteria) this;
        }

        public Criteria andSolvedTimeEqualTo(Date value) {
            addCriterion("solved_time =", value, "solvedTime");
            return (Criteria) this;
        }

        public Criteria andSolvedTimeNotEqualTo(Date value) {
            addCriterion("solved_time <>", value, "solvedTime");
            return (Criteria) this;
        }

        public Criteria andSolvedTimeGreaterThan(Date value) {
            addCriterion("solved_time >", value, "solvedTime");
            return (Criteria) this;
        }

        public Criteria andSolvedTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("solved_time >=", value, "solvedTime");
            return (Criteria) this;
        }

        public Criteria andSolvedTimeLessThan(Date value) {
            addCriterion("solved_time <", value, "solvedTime");
            return (Criteria) this;
        }

        public Criteria andSolvedTimeLessThanOrEqualTo(Date value) {
            addCriterion("solved_time <=", value, "solvedTime");
            return (Criteria) this;
        }

        public Criteria andSolvedTimeIn(List<Date> values) {
            addCriterion("solved_time in", values, "solvedTime");
            return (Criteria) this;
        }

        public Criteria andSolvedTimeNotIn(List<Date> values) {
            addCriterion("solved_time not in", values, "solvedTime");
            return (Criteria) this;
        }

        public Criteria andSolvedTimeBetween(Date value1, Date value2) {
            addCriterion("solved_time between", value1, value2, "solvedTime");
            return (Criteria) this;
        }

        public Criteria andSolvedTimeNotBetween(Date value1, Date value2) {
            addCriterion("solved_time not between", value1, value2, "solvedTime");
            return (Criteria) this;
        }

        public Criteria andFminusoTimeIsNull() {
            addCriterion("fminuso_time is null");
            return (Criteria) this;
        }

        public Criteria andFminusoTimeIsNotNull() {
            addCriterion("fminuso_time is not null");
            return (Criteria) this;
        }

        public Criteria andFminusoTimeEqualTo(Integer value) {
            addCriterion("fminuso_time =", value, "fminusoTime");
            return (Criteria) this;
        }

        public Criteria andFminusoTimeNotEqualTo(Integer value) {
            addCriterion("fminuso_time <>", value, "fminusoTime");
            return (Criteria) this;
        }

        public Criteria andFminusoTimeGreaterThan(Integer value) {
            addCriterion("fminuso_time >", value, "fminusoTime");
            return (Criteria) this;
        }

        public Criteria andFminusoTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("fminuso_time >=", value, "fminusoTime");
            return (Criteria) this;
        }

        public Criteria andFminusoTimeLessThan(Integer value) {
            addCriterion("fminuso_time <", value, "fminusoTime");
            return (Criteria) this;
        }

        public Criteria andFminusoTimeLessThanOrEqualTo(Integer value) {
            addCriterion("fminuso_time <=", value, "fminusoTime");
            return (Criteria) this;
        }

        public Criteria andFminusoTimeIn(List<Integer> values) {
            addCriterion("fminuso_time in", values, "fminusoTime");
            return (Criteria) this;
        }

        public Criteria andFminusoTimeNotIn(List<Integer> values) {
            addCriterion("fminuso_time not in", values, "fminusoTime");
            return (Criteria) this;
        }

        public Criteria andFminusoTimeBetween(Integer value1, Integer value2) {
            addCriterion("fminuso_time between", value1, value2, "fminusoTime");
            return (Criteria) this;
        }

        public Criteria andFminusoTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("fminuso_time not between", value1, value2, "fminusoTime");
            return (Criteria) this;
        }

        public Criteria andLminusfTimeIsNull() {
            addCriterion("lminusf_time is null");
            return (Criteria) this;
        }

        public Criteria andLminusfTimeIsNotNull() {
            addCriterion("lminusf_time is not null");
            return (Criteria) this;
        }

        public Criteria andLminusfTimeEqualTo(Integer value) {
            addCriterion("lminusf_time =", value, "lminusfTime");
            return (Criteria) this;
        }

        public Criteria andLminusfTimeNotEqualTo(Integer value) {
            addCriterion("lminusf_time <>", value, "lminusfTime");
            return (Criteria) this;
        }

        public Criteria andLminusfTimeGreaterThan(Integer value) {
            addCriterion("lminusf_time >", value, "lminusfTime");
            return (Criteria) this;
        }

        public Criteria andLminusfTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("lminusf_time >=", value, "lminusfTime");
            return (Criteria) this;
        }

        public Criteria andLminusfTimeLessThan(Integer value) {
            addCriterion("lminusf_time <", value, "lminusfTime");
            return (Criteria) this;
        }

        public Criteria andLminusfTimeLessThanOrEqualTo(Integer value) {
            addCriterion("lminusf_time <=", value, "lminusfTime");
            return (Criteria) this;
        }

        public Criteria andLminusfTimeIn(List<Integer> values) {
            addCriterion("lminusf_time in", values, "lminusfTime");
            return (Criteria) this;
        }

        public Criteria andLminusfTimeNotIn(List<Integer> values) {
            addCriterion("lminusf_time not in", values, "lminusfTime");
            return (Criteria) this;
        }

        public Criteria andLminusfTimeBetween(Integer value1, Integer value2) {
            addCriterion("lminusf_time between", value1, value2, "lminusfTime");
            return (Criteria) this;
        }

        public Criteria andLminusfTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("lminusf_time not between", value1, value2, "lminusfTime");
            return (Criteria) this;
        }

        public Criteria andSminushTimeIsNull() {
            addCriterion("sminush_time is null");
            return (Criteria) this;
        }

        public Criteria andSminushTimeIsNotNull() {
            addCriterion("sminush_time is not null");
            return (Criteria) this;
        }

        public Criteria andSminushTimeEqualTo(Integer value) {
            addCriterion("sminush_time =", value, "sminushTime");
            return (Criteria) this;
        }

        public Criteria andSminushTimeNotEqualTo(Integer value) {
            addCriterion("sminush_time <>", value, "sminushTime");
            return (Criteria) this;
        }

        public Criteria andSminushTimeGreaterThan(Integer value) {
            addCriterion("sminush_time >", value, "sminushTime");
            return (Criteria) this;
        }

        public Criteria andSminushTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("sminush_time >=", value, "sminushTime");
            return (Criteria) this;
        }

        public Criteria andSminushTimeLessThan(Integer value) {
            addCriterion("sminush_time <", value, "sminushTime");
            return (Criteria) this;
        }

        public Criteria andSminushTimeLessThanOrEqualTo(Integer value) {
            addCriterion("sminush_time <=", value, "sminushTime");
            return (Criteria) this;
        }

        public Criteria andSminushTimeIn(List<Integer> values) {
            addCriterion("sminush_time in", values, "sminushTime");
            return (Criteria) this;
        }

        public Criteria andSminushTimeNotIn(List<Integer> values) {
            addCriterion("sminush_time not in", values, "sminushTime");
            return (Criteria) this;
        }

        public Criteria andSminushTimeBetween(Integer value1, Integer value2) {
            addCriterion("sminush_time between", value1, value2, "sminushTime");
            return (Criteria) this;
        }

        public Criteria andSminushTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("sminush_time not between", value1, value2, "sminushTime");
            return (Criteria) this;
        }

        public Criteria andWikiIsNull() {
            addCriterion("wiki is null");
            return (Criteria) this;
        }

        public Criteria andWikiIsNotNull() {
            addCriterion("wiki is not null");
            return (Criteria) this;
        }

        public Criteria andWikiEqualTo(String value) {
            addCriterion("wiki =", value, "wiki");
            return (Criteria) this;
        }

        public Criteria andWikiNotEqualTo(String value) {
            addCriterion("wiki <>", value, "wiki");
            return (Criteria) this;
        }

        public Criteria andWikiGreaterThan(String value) {
            addCriterion("wiki >", value, "wiki");
            return (Criteria) this;
        }

        public Criteria andWikiGreaterThanOrEqualTo(String value) {
            addCriterion("wiki >=", value, "wiki");
            return (Criteria) this;
        }

        public Criteria andWikiLessThan(String value) {
            addCriterion("wiki <", value, "wiki");
            return (Criteria) this;
        }

        public Criteria andWikiLessThanOrEqualTo(String value) {
            addCriterion("wiki <=", value, "wiki");
            return (Criteria) this;
        }

        public Criteria andWikiLike(String value) {
            addCriterion("wiki like", value, "wiki");
            return (Criteria) this;
        }

        public Criteria andWikiNotLike(String value) {
            addCriterion("wiki not like", value, "wiki");
            return (Criteria) this;
        }

        public Criteria andWikiIn(List<String> values) {
            addCriterion("wiki in", values, "wiki");
            return (Criteria) this;
        }

        public Criteria andWikiNotIn(List<String> values) {
            addCriterion("wiki not in", values, "wiki");
            return (Criteria) this;
        }

        public Criteria andWikiBetween(String value1, String value2) {
            addCriterion("wiki between", value1, value2, "wiki");
            return (Criteria) this;
        }

        public Criteria andWikiNotBetween(String value1, String value2) {
            addCriterion("wiki not between", value1, value2, "wiki");
            return (Criteria) this;
        }

        public Criteria andLevelIsNull() {
            addCriterion("level is null");
            return (Criteria) this;
        }

        public Criteria andLevelIsNotNull() {
            addCriterion("level is not null");
            return (Criteria) this;
        }

        public Criteria andLevelEqualTo(String value) {
            addCriterion("level =", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotEqualTo(String value) {
            addCriterion("level <>", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThan(String value) {
            addCriterion("level >", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThanOrEqualTo(String value) {
            addCriterion("level >=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThan(String value) {
            addCriterion("level <", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThanOrEqualTo(String value) {
            addCriterion("level <=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLike(String value) {
            addCriterion("level like", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotLike(String value) {
            addCriterion("level not like", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelIn(List<String> values) {
            addCriterion("level in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotIn(List<String> values) {
            addCriterion("level not in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelBetween(String value1, String value2) {
            addCriterion("level between", value1, value2, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotBetween(String value1, String value2) {
            addCriterion("level not between", value1, value2, "level");
            return (Criteria) this;
        }

        public Criteria andOwnerNameIsNull() {
            addCriterion("owner_name is null");
            return (Criteria) this;
        }

        public Criteria andOwnerNameIsNotNull() {
            addCriterion("owner_name is not null");
            return (Criteria) this;
        }

        public Criteria andOwnerNameEqualTo(String value) {
            addCriterion("owner_name =", value, "ownerName");
            return (Criteria) this;
        }

        public Criteria andOwnerNameNotEqualTo(String value) {
            addCriterion("owner_name <>", value, "ownerName");
            return (Criteria) this;
        }

        public Criteria andOwnerNameGreaterThan(String value) {
            addCriterion("owner_name >", value, "ownerName");
            return (Criteria) this;
        }

        public Criteria andOwnerNameGreaterThanOrEqualTo(String value) {
            addCriterion("owner_name >=", value, "ownerName");
            return (Criteria) this;
        }

        public Criteria andOwnerNameLessThan(String value) {
            addCriterion("owner_name <", value, "ownerName");
            return (Criteria) this;
        }

        public Criteria andOwnerNameLessThanOrEqualTo(String value) {
            addCriterion("owner_name <=", value, "ownerName");
            return (Criteria) this;
        }

        public Criteria andOwnerNameLike(String value) {
            addCriterion("owner_name like", value, "ownerName");
            return (Criteria) this;
        }

        public Criteria andOwnerNameNotLike(String value) {
            addCriterion("owner_name not like", value, "ownerName");
            return (Criteria) this;
        }

        public Criteria andOwnerNameIn(List<String> values) {
            addCriterion("owner_name in", values, "ownerName");
            return (Criteria) this;
        }

        public Criteria andOwnerNameNotIn(List<String> values) {
            addCriterion("owner_name not in", values, "ownerName");
            return (Criteria) this;
        }

        public Criteria andOwnerNameBetween(String value1, String value2) {
            addCriterion("owner_name between", value1, value2, "ownerName");
            return (Criteria) this;
        }

        public Criteria andOwnerNameNotBetween(String value1, String value2) {
            addCriterion("owner_name not between", value1, value2, "ownerName");
            return (Criteria) this;
        }

        public Criteria andOwnerMisIsNull() {
            addCriterion("owner_mis is null");
            return (Criteria) this;
        }

        public Criteria andOwnerMisIsNotNull() {
            addCriterion("owner_mis is not null");
            return (Criteria) this;
        }

        public Criteria andOwnerMisEqualTo(String value) {
            addCriterion("owner_mis =", value, "ownerMis");
            return (Criteria) this;
        }

        public Criteria andOwnerMisNotEqualTo(String value) {
            addCriterion("owner_mis <>", value, "ownerMis");
            return (Criteria) this;
        }

        public Criteria andOwnerMisGreaterThan(String value) {
            addCriterion("owner_mis >", value, "ownerMis");
            return (Criteria) this;
        }

        public Criteria andOwnerMisGreaterThanOrEqualTo(String value) {
            addCriterion("owner_mis >=", value, "ownerMis");
            return (Criteria) this;
        }

        public Criteria andOwnerMisLessThan(String value) {
            addCriterion("owner_mis <", value, "ownerMis");
            return (Criteria) this;
        }

        public Criteria andOwnerMisLessThanOrEqualTo(String value) {
            addCriterion("owner_mis <=", value, "ownerMis");
            return (Criteria) this;
        }

        public Criteria andOwnerMisLike(String value) {
            addCriterion("owner_mis like", value, "ownerMis");
            return (Criteria) this;
        }

        public Criteria andOwnerMisNotLike(String value) {
            addCriterion("owner_mis not like", value, "ownerMis");
            return (Criteria) this;
        }

        public Criteria andOwnerMisIn(List<String> values) {
            addCriterion("owner_mis in", values, "ownerMis");
            return (Criteria) this;
        }

        public Criteria andOwnerMisNotIn(List<String> values) {
            addCriterion("owner_mis not in", values, "ownerMis");
            return (Criteria) this;
        }

        public Criteria andOwnerMisBetween(String value1, String value2) {
            addCriterion("owner_mis between", value1, value2, "ownerMis");
            return (Criteria) this;
        }

        public Criteria andOwnerMisNotBetween(String value1, String value2) {
            addCriterion("owner_mis not between", value1, value2, "ownerMis");
            return (Criteria) this;
        }

        public Criteria andQaNameIsNull() {
            addCriterion("qa_name is null");
            return (Criteria) this;
        }

        public Criteria andQaNameIsNotNull() {
            addCriterion("qa_name is not null");
            return (Criteria) this;
        }

        public Criteria andQaNameEqualTo(String value) {
            addCriterion("qa_name =", value, "qaName");
            return (Criteria) this;
        }

        public Criteria andQaNameNotEqualTo(String value) {
            addCriterion("qa_name <>", value, "qaName");
            return (Criteria) this;
        }

        public Criteria andQaNameGreaterThan(String value) {
            addCriterion("qa_name >", value, "qaName");
            return (Criteria) this;
        }

        public Criteria andQaNameGreaterThanOrEqualTo(String value) {
            addCriterion("qa_name >=", value, "qaName");
            return (Criteria) this;
        }

        public Criteria andQaNameLessThan(String value) {
            addCriterion("qa_name <", value, "qaName");
            return (Criteria) this;
        }

        public Criteria andQaNameLessThanOrEqualTo(String value) {
            addCriterion("qa_name <=", value, "qaName");
            return (Criteria) this;
        }

        public Criteria andQaNameLike(String value) {
            addCriterion("qa_name like", value, "qaName");
            return (Criteria) this;
        }

        public Criteria andQaNameNotLike(String value) {
            addCriterion("qa_name not like", value, "qaName");
            return (Criteria) this;
        }

        public Criteria andQaNameIn(List<String> values) {
            addCriterion("qa_name in", values, "qaName");
            return (Criteria) this;
        }

        public Criteria andQaNameNotIn(List<String> values) {
            addCriterion("qa_name not in", values, "qaName");
            return (Criteria) this;
        }

        public Criteria andQaNameBetween(String value1, String value2) {
            addCriterion("qa_name between", value1, value2, "qaName");
            return (Criteria) this;
        }

        public Criteria andQaNameNotBetween(String value1, String value2) {
            addCriterion("qa_name not between", value1, value2, "qaName");
            return (Criteria) this;
        }

        public Criteria andQaMisIsNull() {
            addCriterion("qa_mis is null");
            return (Criteria) this;
        }

        public Criteria andQaMisIsNotNull() {
            addCriterion("qa_mis is not null");
            return (Criteria) this;
        }

        public Criteria andQaMisEqualTo(String value) {
            addCriterion("qa_mis =", value, "qaMis");
            return (Criteria) this;
        }

        public Criteria andQaMisNotEqualTo(String value) {
            addCriterion("qa_mis <>", value, "qaMis");
            return (Criteria) this;
        }

        public Criteria andQaMisGreaterThan(String value) {
            addCriterion("qa_mis >", value, "qaMis");
            return (Criteria) this;
        }

        public Criteria andQaMisGreaterThanOrEqualTo(String value) {
            addCriterion("qa_mis >=", value, "qaMis");
            return (Criteria) this;
        }

        public Criteria andQaMisLessThan(String value) {
            addCriterion("qa_mis <", value, "qaMis");
            return (Criteria) this;
        }

        public Criteria andQaMisLessThanOrEqualTo(String value) {
            addCriterion("qa_mis <=", value, "qaMis");
            return (Criteria) this;
        }

        public Criteria andQaMisLike(String value) {
            addCriterion("qa_mis like", value, "qaMis");
            return (Criteria) this;
        }

        public Criteria andQaMisNotLike(String value) {
            addCriterion("qa_mis not like", value, "qaMis");
            return (Criteria) this;
        }

        public Criteria andQaMisIn(List<String> values) {
            addCriterion("qa_mis in", values, "qaMis");
            return (Criteria) this;
        }

        public Criteria andQaMisNotIn(List<String> values) {
            addCriterion("qa_mis not in", values, "qaMis");
            return (Criteria) this;
        }

        public Criteria andQaMisBetween(String value1, String value2) {
            addCriterion("qa_mis between", value1, value2, "qaMis");
            return (Criteria) this;
        }

        public Criteria andQaMisNotBetween(String value1, String value2) {
            addCriterion("qa_mis not between", value1, value2, "qaMis");
            return (Criteria) this;
        }

        public Criteria andCoeLinkIsNull() {
            addCriterion("coe_link is null");
            return (Criteria) this;
        }

        public Criteria andCoeLinkIsNotNull() {
            addCriterion("coe_link is not null");
            return (Criteria) this;
        }

        public Criteria andCoeLinkEqualTo(String value) {
            addCriterion("coe_link =", value, "coeLink");
            return (Criteria) this;
        }

        public Criteria andCoeLinkNotEqualTo(String value) {
            addCriterion("coe_link <>", value, "coeLink");
            return (Criteria) this;
        }

        public Criteria andCoeLinkGreaterThan(String value) {
            addCriterion("coe_link >", value, "coeLink");
            return (Criteria) this;
        }

        public Criteria andCoeLinkGreaterThanOrEqualTo(String value) {
            addCriterion("coe_link >=", value, "coeLink");
            return (Criteria) this;
        }

        public Criteria andCoeLinkLessThan(String value) {
            addCriterion("coe_link <", value, "coeLink");
            return (Criteria) this;
        }

        public Criteria andCoeLinkLessThanOrEqualTo(String value) {
            addCriterion("coe_link <=", value, "coeLink");
            return (Criteria) this;
        }

        public Criteria andCoeLinkLike(String value) {
            addCriterion("coe_link like", value, "coeLink");
            return (Criteria) this;
        }

        public Criteria andCoeLinkNotLike(String value) {
            addCriterion("coe_link not like", value, "coeLink");
            return (Criteria) this;
        }

        public Criteria andCoeLinkIn(List<String> values) {
            addCriterion("coe_link in", values, "coeLink");
            return (Criteria) this;
        }

        public Criteria andCoeLinkNotIn(List<String> values) {
            addCriterion("coe_link not in", values, "coeLink");
            return (Criteria) this;
        }

        public Criteria andCoeLinkBetween(String value1, String value2) {
            addCriterion("coe_link between", value1, value2, "coeLink");
            return (Criteria) this;
        }

        public Criteria andCoeLinkNotBetween(String value1, String value2) {
            addCriterion("coe_link not between", value1, value2, "coeLink");
            return (Criteria) this;
        }

        public Criteria andCategoryIsNull() {
            addCriterion("category is null");
            return (Criteria) this;
        }

        public Criteria andCategoryIsNotNull() {
            addCriterion("category is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryEqualTo(String value) {
            addCriterion("category =", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotEqualTo(String value) {
            addCriterion("category <>", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryGreaterThan(String value) {
            addCriterion("category >", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryGreaterThanOrEqualTo(String value) {
            addCriterion("category >=", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryLessThan(String value) {
            addCriterion("category <", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryLessThanOrEqualTo(String value) {
            addCriterion("category <=", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryLike(String value) {
            addCriterion("category like", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotLike(String value) {
            addCriterion("category not like", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryIn(List<String> values) {
            addCriterion("category in", values, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotIn(List<String> values) {
            addCriterion("category not in", values, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryBetween(String value1, String value2) {
            addCriterion("category between", value1, value2, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotBetween(String value1, String value2) {
            addCriterion("category not between", value1, value2, "category");
            return (Criteria) this;
        }

        public Criteria andRdShareIsNull() {
            addCriterion("rd_share is null");
            return (Criteria) this;
        }

        public Criteria andRdShareIsNotNull() {
            addCriterion("rd_share is not null");
            return (Criteria) this;
        }

        public Criteria andRdShareEqualTo(BigDecimal value) {
            addCriterion("rd_share =", value, "rdShare");
            return (Criteria) this;
        }

        public Criteria andRdShareNotEqualTo(BigDecimal value) {
            addCriterion("rd_share <>", value, "rdShare");
            return (Criteria) this;
        }

        public Criteria andRdShareGreaterThan(BigDecimal value) {
            addCriterion("rd_share >", value, "rdShare");
            return (Criteria) this;
        }

        public Criteria andRdShareGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("rd_share >=", value, "rdShare");
            return (Criteria) this;
        }

        public Criteria andRdShareLessThan(BigDecimal value) {
            addCriterion("rd_share <", value, "rdShare");
            return (Criteria) this;
        }

        public Criteria andRdShareLessThanOrEqualTo(BigDecimal value) {
            addCriterion("rd_share <=", value, "rdShare");
            return (Criteria) this;
        }

        public Criteria andRdShareIn(List<BigDecimal> values) {
            addCriterion("rd_share in", values, "rdShare");
            return (Criteria) this;
        }

        public Criteria andRdShareNotIn(List<BigDecimal> values) {
            addCriterion("rd_share not in", values, "rdShare");
            return (Criteria) this;
        }

        public Criteria andRdShareBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("rd_share between", value1, value2, "rdShare");
            return (Criteria) this;
        }

        public Criteria andRdShareNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("rd_share not between", value1, value2, "rdShare");
            return (Criteria) this;
        }

        public Criteria andQaShareIsNull() {
            addCriterion("qa_share is null");
            return (Criteria) this;
        }

        public Criteria andQaShareIsNotNull() {
            addCriterion("qa_share is not null");
            return (Criteria) this;
        }

        public Criteria andQaShareEqualTo(BigDecimal value) {
            addCriterion("qa_share =", value, "qaShare");
            return (Criteria) this;
        }

        public Criteria andQaShareNotEqualTo(BigDecimal value) {
            addCriterion("qa_share <>", value, "qaShare");
            return (Criteria) this;
        }

        public Criteria andQaShareGreaterThan(BigDecimal value) {
            addCriterion("qa_share >", value, "qaShare");
            return (Criteria) this;
        }

        public Criteria andQaShareGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("qa_share >=", value, "qaShare");
            return (Criteria) this;
        }

        public Criteria andQaShareLessThan(BigDecimal value) {
            addCriterion("qa_share <", value, "qaShare");
            return (Criteria) this;
        }

        public Criteria andQaShareLessThanOrEqualTo(BigDecimal value) {
            addCriterion("qa_share <=", value, "qaShare");
            return (Criteria) this;
        }

        public Criteria andQaShareIn(List<BigDecimal> values) {
            addCriterion("qa_share in", values, "qaShare");
            return (Criteria) this;
        }

        public Criteria andQaShareNotIn(List<BigDecimal> values) {
            addCriterion("qa_share not in", values, "qaShare");
            return (Criteria) this;
        }

        public Criteria andQaShareBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("qa_share between", value1, value2, "qaShare");
            return (Criteria) this;
        }

        public Criteria andQaShareNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("qa_share not between", value1, value2, "qaShare");
            return (Criteria) this;
        }

        public Criteria andJoinStatusIsNull() {
            addCriterion("join_status is null");
            return (Criteria) this;
        }

        public Criteria andJoinStatusIsNotNull() {
            addCriterion("join_status is not null");
            return (Criteria) this;
        }

        public Criteria andJoinStatusEqualTo(Boolean value) {
            addCriterion("join_status =", value, "joinStatus");
            return (Criteria) this;
        }

        public Criteria andJoinStatusNotEqualTo(Boolean value) {
            addCriterion("join_status <>", value, "joinStatus");
            return (Criteria) this;
        }

        public Criteria andJoinStatusGreaterThan(Boolean value) {
            addCriterion("join_status >", value, "joinStatus");
            return (Criteria) this;
        }

        public Criteria andJoinStatusGreaterThanOrEqualTo(Boolean value) {
            addCriterion("join_status >=", value, "joinStatus");
            return (Criteria) this;
        }

        public Criteria andJoinStatusLessThan(Boolean value) {
            addCriterion("join_status <", value, "joinStatus");
            return (Criteria) this;
        }

        public Criteria andJoinStatusLessThanOrEqualTo(Boolean value) {
            addCriterion("join_status <=", value, "joinStatus");
            return (Criteria) this;
        }

        public Criteria andJoinStatusIn(List<Boolean> values) {
            addCriterion("join_status in", values, "joinStatus");
            return (Criteria) this;
        }

        public Criteria andJoinStatusNotIn(List<Boolean> values) {
            addCriterion("join_status not in", values, "joinStatus");
            return (Criteria) this;
        }

        public Criteria andJoinStatusBetween(Boolean value1, Boolean value2) {
            addCriterion("join_status between", value1, value2, "joinStatus");
            return (Criteria) this;
        }

        public Criteria andJoinStatusNotBetween(Boolean value1, Boolean value2) {
            addCriterion("join_status not between", value1, value2, "joinStatus");
            return (Criteria) this;
        }

        public Criteria andAppearanceIsNull() {
            addCriterion("appearance is null");
            return (Criteria) this;
        }

        public Criteria andAppearanceIsNotNull() {
            addCriterion("appearance is not null");
            return (Criteria) this;
        }

        public Criteria andAppearanceEqualTo(String value) {
            addCriterion("appearance =", value, "appearance");
            return (Criteria) this;
        }

        public Criteria andAppearanceNotEqualTo(String value) {
            addCriterion("appearance <>", value, "appearance");
            return (Criteria) this;
        }

        public Criteria andAppearanceGreaterThan(String value) {
            addCriterion("appearance >", value, "appearance");
            return (Criteria) this;
        }

        public Criteria andAppearanceGreaterThanOrEqualTo(String value) {
            addCriterion("appearance >=", value, "appearance");
            return (Criteria) this;
        }

        public Criteria andAppearanceLessThan(String value) {
            addCriterion("appearance <", value, "appearance");
            return (Criteria) this;
        }

        public Criteria andAppearanceLessThanOrEqualTo(String value) {
            addCriterion("appearance <=", value, "appearance");
            return (Criteria) this;
        }

        public Criteria andAppearanceLike(String value) {
            addCriterion("appearance like", value, "appearance");
            return (Criteria) this;
        }

        public Criteria andAppearanceNotLike(String value) {
            addCriterion("appearance not like", value, "appearance");
            return (Criteria) this;
        }

        public Criteria andAppearanceIn(List<String> values) {
            addCriterion("appearance in", values, "appearance");
            return (Criteria) this;
        }

        public Criteria andAppearanceNotIn(List<String> values) {
            addCriterion("appearance not in", values, "appearance");
            return (Criteria) this;
        }

        public Criteria andAppearanceBetween(String value1, String value2) {
            addCriterion("appearance between", value1, value2, "appearance");
            return (Criteria) this;
        }

        public Criteria andAppearanceNotBetween(String value1, String value2) {
            addCriterion("appearance not between", value1, value2, "appearance");
            return (Criteria) this;
        }

        public Criteria andSubCategoryIsNull() {
            addCriterion("sub_category is null");
            return (Criteria) this;
        }

        public Criteria andSubCategoryIsNotNull() {
            addCriterion("sub_category is not null");
            return (Criteria) this;
        }

        public Criteria andSubCategoryEqualTo(String value) {
            addCriterion("sub_category =", value, "subCategory");
            return (Criteria) this;
        }

        public Criteria andSubCategoryNotEqualTo(String value) {
            addCriterion("sub_category <>", value, "subCategory");
            return (Criteria) this;
        }

        public Criteria andSubCategoryGreaterThan(String value) {
            addCriterion("sub_category >", value, "subCategory");
            return (Criteria) this;
        }

        public Criteria andSubCategoryGreaterThanOrEqualTo(String value) {
            addCriterion("sub_category >=", value, "subCategory");
            return (Criteria) this;
        }

        public Criteria andSubCategoryLessThan(String value) {
            addCriterion("sub_category <", value, "subCategory");
            return (Criteria) this;
        }

        public Criteria andSubCategoryLessThanOrEqualTo(String value) {
            addCriterion("sub_category <=", value, "subCategory");
            return (Criteria) this;
        }

        public Criteria andSubCategoryLike(String value) {
            addCriterion("sub_category like", value, "subCategory");
            return (Criteria) this;
        }

        public Criteria andSubCategoryNotLike(String value) {
            addCriterion("sub_category not like", value, "subCategory");
            return (Criteria) this;
        }

        public Criteria andSubCategoryIn(List<String> values) {
            addCriterion("sub_category in", values, "subCategory");
            return (Criteria) this;
        }

        public Criteria andSubCategoryNotIn(List<String> values) {
            addCriterion("sub_category not in", values, "subCategory");
            return (Criteria) this;
        }

        public Criteria andSubCategoryBetween(String value1, String value2) {
            addCriterion("sub_category between", value1, value2, "subCategory");
            return (Criteria) this;
        }

        public Criteria andSubCategoryNotBetween(String value1, String value2) {
            addCriterion("sub_category not between", value1, value2, "subCategory");
            return (Criteria) this;
        }

        public Criteria andAllTodoIsNull() {
            addCriterion("all_todo is null");
            return (Criteria) this;
        }

        public Criteria andAllTodoIsNotNull() {
            addCriterion("all_todo is not null");
            return (Criteria) this;
        }

        public Criteria andAllTodoEqualTo(Integer value) {
            addCriterion("all_todo =", value, "allTodo");
            return (Criteria) this;
        }

        public Criteria andAllTodoNotEqualTo(Integer value) {
            addCriterion("all_todo <>", value, "allTodo");
            return (Criteria) this;
        }

        public Criteria andAllTodoGreaterThan(Integer value) {
            addCriterion("all_todo >", value, "allTodo");
            return (Criteria) this;
        }

        public Criteria andAllTodoGreaterThanOrEqualTo(Integer value) {
            addCriterion("all_todo >=", value, "allTodo");
            return (Criteria) this;
        }

        public Criteria andAllTodoLessThan(Integer value) {
            addCriterion("all_todo <", value, "allTodo");
            return (Criteria) this;
        }

        public Criteria andAllTodoLessThanOrEqualTo(Integer value) {
            addCriterion("all_todo <=", value, "allTodo");
            return (Criteria) this;
        }

        public Criteria andAllTodoIn(List<Integer> values) {
            addCriterion("all_todo in", values, "allTodo");
            return (Criteria) this;
        }

        public Criteria andAllTodoNotIn(List<Integer> values) {
            addCriterion("all_todo not in", values, "allTodo");
            return (Criteria) this;
        }

        public Criteria andAllTodoBetween(Integer value1, Integer value2) {
            addCriterion("all_todo between", value1, value2, "allTodo");
            return (Criteria) this;
        }

        public Criteria andAllTodoNotBetween(Integer value1, Integer value2) {
            addCriterion("all_todo not between", value1, value2, "allTodo");
            return (Criteria) this;
        }

        public Criteria andNotFinishTodoIsNull() {
            addCriterion("not_finish_todo is null");
            return (Criteria) this;
        }

        public Criteria andNotFinishTodoIsNotNull() {
            addCriterion("not_finish_todo is not null");
            return (Criteria) this;
        }

        public Criteria andNotFinishTodoEqualTo(Integer value) {
            addCriterion("not_finish_todo =", value, "notFinishTodo");
            return (Criteria) this;
        }

        public Criteria andNotFinishTodoNotEqualTo(Integer value) {
            addCriterion("not_finish_todo <>", value, "notFinishTodo");
            return (Criteria) this;
        }

        public Criteria andNotFinishTodoGreaterThan(Integer value) {
            addCriterion("not_finish_todo >", value, "notFinishTodo");
            return (Criteria) this;
        }

        public Criteria andNotFinishTodoGreaterThanOrEqualTo(Integer value) {
            addCriterion("not_finish_todo >=", value, "notFinishTodo");
            return (Criteria) this;
        }

        public Criteria andNotFinishTodoLessThan(Integer value) {
            addCriterion("not_finish_todo <", value, "notFinishTodo");
            return (Criteria) this;
        }

        public Criteria andNotFinishTodoLessThanOrEqualTo(Integer value) {
            addCriterion("not_finish_todo <=", value, "notFinishTodo");
            return (Criteria) this;
        }

        public Criteria andNotFinishTodoIn(List<Integer> values) {
            addCriterion("not_finish_todo in", values, "notFinishTodo");
            return (Criteria) this;
        }

        public Criteria andNotFinishTodoNotIn(List<Integer> values) {
            addCriterion("not_finish_todo not in", values, "notFinishTodo");
            return (Criteria) this;
        }

        public Criteria andNotFinishTodoBetween(Integer value1, Integer value2) {
            addCriterion("not_finish_todo between", value1, value2, "notFinishTodo");
            return (Criteria) this;
        }

        public Criteria andNotFinishTodoNotBetween(Integer value1, Integer value2) {
            addCriterion("not_finish_todo not between", value1, value2, "notFinishTodo");
            return (Criteria) this;
        }

        public Criteria andFinishTodoIsNull() {
            addCriterion("finish_todo is null");
            return (Criteria) this;
        }

        public Criteria andFinishTodoIsNotNull() {
            addCriterion("finish_todo is not null");
            return (Criteria) this;
        }

        public Criteria andFinishTodoEqualTo(Integer value) {
            addCriterion("finish_todo =", value, "finishTodo");
            return (Criteria) this;
        }

        public Criteria andFinishTodoNotEqualTo(Integer value) {
            addCriterion("finish_todo <>", value, "finishTodo");
            return (Criteria) this;
        }

        public Criteria andFinishTodoGreaterThan(Integer value) {
            addCriterion("finish_todo >", value, "finishTodo");
            return (Criteria) this;
        }

        public Criteria andFinishTodoGreaterThanOrEqualTo(Integer value) {
            addCriterion("finish_todo >=", value, "finishTodo");
            return (Criteria) this;
        }

        public Criteria andFinishTodoLessThan(Integer value) {
            addCriterion("finish_todo <", value, "finishTodo");
            return (Criteria) this;
        }

        public Criteria andFinishTodoLessThanOrEqualTo(Integer value) {
            addCriterion("finish_todo <=", value, "finishTodo");
            return (Criteria) this;
        }

        public Criteria andFinishTodoIn(List<Integer> values) {
            addCriterion("finish_todo in", values, "finishTodo");
            return (Criteria) this;
        }

        public Criteria andFinishTodoNotIn(List<Integer> values) {
            addCriterion("finish_todo not in", values, "finishTodo");
            return (Criteria) this;
        }

        public Criteria andFinishTodoBetween(Integer value1, Integer value2) {
            addCriterion("finish_todo between", value1, value2, "finishTodo");
            return (Criteria) this;
        }

        public Criteria andFinishTodoNotBetween(Integer value1, Integer value2) {
            addCriterion("finish_todo not between", value1, value2, "finishTodo");
            return (Criteria) this;
        }

        public Criteria andNotFinishTodoTaskIsNull() {
            addCriterion("not_finish_todo_task is null");
            return (Criteria) this;
        }

        public Criteria andNotFinishTodoTaskIsNotNull() {
            addCriterion("not_finish_todo_task is not null");
            return (Criteria) this;
        }

        public Criteria andNotFinishTodoTaskEqualTo(String value) {
            addCriterion("not_finish_todo_task =", value, "notFinishTodoTask");
            return (Criteria) this;
        }

        public Criteria andNotFinishTodoTaskNotEqualTo(String value) {
            addCriterion("not_finish_todo_task <>", value, "notFinishTodoTask");
            return (Criteria) this;
        }

        public Criteria andNotFinishTodoTaskGreaterThan(String value) {
            addCriterion("not_finish_todo_task >", value, "notFinishTodoTask");
            return (Criteria) this;
        }

        public Criteria andNotFinishTodoTaskGreaterThanOrEqualTo(String value) {
            addCriterion("not_finish_todo_task >=", value, "notFinishTodoTask");
            return (Criteria) this;
        }

        public Criteria andNotFinishTodoTaskLessThan(String value) {
            addCriterion("not_finish_todo_task <", value, "notFinishTodoTask");
            return (Criteria) this;
        }

        public Criteria andNotFinishTodoTaskLessThanOrEqualTo(String value) {
            addCriterion("not_finish_todo_task <=", value, "notFinishTodoTask");
            return (Criteria) this;
        }

        public Criteria andNotFinishTodoTaskLike(String value) {
            addCriterion("not_finish_todo_task like", value, "notFinishTodoTask");
            return (Criteria) this;
        }

        public Criteria andNotFinishTodoTaskNotLike(String value) {
            addCriterion("not_finish_todo_task not like", value, "notFinishTodoTask");
            return (Criteria) this;
        }

        public Criteria andNotFinishTodoTaskIn(List<String> values) {
            addCriterion("not_finish_todo_task in", values, "notFinishTodoTask");
            return (Criteria) this;
        }

        public Criteria andNotFinishTodoTaskNotIn(List<String> values) {
            addCriterion("not_finish_todo_task not in", values, "notFinishTodoTask");
            return (Criteria) this;
        }

        public Criteria andNotFinishTodoTaskBetween(String value1, String value2) {
            addCriterion("not_finish_todo_task between", value1, value2, "notFinishTodoTask");
            return (Criteria) this;
        }

        public Criteria andNotFinishTodoTaskNotBetween(String value1, String value2) {
            addCriterion("not_finish_todo_task not between", value1, value2, "notFinishTodoTask");
            return (Criteria) this;
        }

        public Criteria andAvailableIsNull() {
            addCriterion("available is null");
            return (Criteria) this;
        }

        public Criteria andAvailableIsNotNull() {
            addCriterion("available is not null");
            return (Criteria) this;
        }

        public Criteria andAvailableEqualTo(Boolean value) {
            addCriterion("available =", value, "available");
            return (Criteria) this;
        }

        public Criteria andAvailableNotEqualTo(Boolean value) {
            addCriterion("available <>", value, "available");
            return (Criteria) this;
        }

        public Criteria andAvailableGreaterThan(Boolean value) {
            addCriterion("available >", value, "available");
            return (Criteria) this;
        }

        public Criteria andAvailableGreaterThanOrEqualTo(Boolean value) {
            addCriterion("available >=", value, "available");
            return (Criteria) this;
        }

        public Criteria andAvailableLessThan(Boolean value) {
            addCriterion("available <", value, "available");
            return (Criteria) this;
        }

        public Criteria andAvailableLessThanOrEqualTo(Boolean value) {
            addCriterion("available <=", value, "available");
            return (Criteria) this;
        }

        public Criteria andAvailableIn(List<Boolean> values) {
            addCriterion("available in", values, "available");
            return (Criteria) this;
        }

        public Criteria andAvailableNotIn(List<Boolean> values) {
            addCriterion("available not in", values, "available");
            return (Criteria) this;
        }

        public Criteria andAvailableBetween(Boolean value1, Boolean value2) {
            addCriterion("available between", value1, value2, "available");
            return (Criteria) this;
        }

        public Criteria andAvailableNotBetween(Boolean value1, Boolean value2) {
            addCriterion("available not between", value1, value2, "available");
            return (Criteria) this;
        }

        public Criteria andOrgNameIsNull() {
            addCriterion("org_name is null");
            return (Criteria) this;
        }

        public Criteria andOrgNameIsNotNull() {
            addCriterion("org_name is not null");
            return (Criteria) this;
        }

        public Criteria andOrgNameEqualTo(String value) {
            addCriterion("org_name =", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotEqualTo(String value) {
            addCriterion("org_name <>", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameGreaterThan(String value) {
            addCriterion("org_name >", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameGreaterThanOrEqualTo(String value) {
            addCriterion("org_name >=", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameLessThan(String value) {
            addCriterion("org_name <", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameLessThanOrEqualTo(String value) {
            addCriterion("org_name <=", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameLike(String value) {
            addCriterion("org_name like", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotLike(String value) {
            addCriterion("org_name not like", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameIn(List<String> values) {
            addCriterion("org_name in", values, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotIn(List<String> values) {
            addCriterion("org_name not in", values, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameBetween(String value1, String value2) {
            addCriterion("org_name between", value1, value2, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotBetween(String value1, String value2) {
            addCriterion("org_name not between", value1, value2, "orgName");
            return (Criteria) this;
        }

        public Criteria andFindDateIsNull() {
            addCriterion("find_date is null");
            return (Criteria) this;
        }

        public Criteria andFindDateIsNotNull() {
            addCriterion("find_date is not null");
            return (Criteria) this;
        }

        public Criteria andFindDateEqualTo(Date value) {
            addCriterionForJDBCDate("find_date =", value, "findDate");
            return (Criteria) this;
        }

        public Criteria andFindDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("find_date <>", value, "findDate");
            return (Criteria) this;
        }

        public Criteria andFindDateGreaterThan(Date value) {
            addCriterionForJDBCDate("find_date >", value, "findDate");
            return (Criteria) this;
        }

        public Criteria andFindDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("find_date >=", value, "findDate");
            return (Criteria) this;
        }

        public Criteria andFindDateLessThan(Date value) {
            addCriterionForJDBCDate("find_date <", value, "findDate");
            return (Criteria) this;
        }

        public Criteria andFindDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("find_date <=", value, "findDate");
            return (Criteria) this;
        }

        public Criteria andFindDateIn(List<Date> values) {
            addCriterionForJDBCDate("find_date in", values, "findDate");
            return (Criteria) this;
        }

        public Criteria andFindDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("find_date not in", values, "findDate");
            return (Criteria) this;
        }

        public Criteria andFindDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("find_date between", value1, value2, "findDate");
            return (Criteria) this;
        }

        public Criteria andFindDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("find_date not between", value1, value2, "findDate");
            return (Criteria) this;
        }

        public Criteria andFinderIsNull() {
            addCriterion("finder is null");
            return (Criteria) this;
        }

        public Criteria andFinderIsNotNull() {
            addCriterion("finder is not null");
            return (Criteria) this;
        }

        public Criteria andFinderEqualTo(String value) {
            addCriterion("finder =", value, "finder");
            return (Criteria) this;
        }

        public Criteria andFinderNotEqualTo(String value) {
            addCriterion("finder <>", value, "finder");
            return (Criteria) this;
        }

        public Criteria andFinderGreaterThan(String value) {
            addCriterion("finder >", value, "finder");
            return (Criteria) this;
        }

        public Criteria andFinderGreaterThanOrEqualTo(String value) {
            addCriterion("finder >=", value, "finder");
            return (Criteria) this;
        }

        public Criteria andFinderLessThan(String value) {
            addCriterion("finder <", value, "finder");
            return (Criteria) this;
        }

        public Criteria andFinderLessThanOrEqualTo(String value) {
            addCriterion("finder <=", value, "finder");
            return (Criteria) this;
        }

        public Criteria andFinderLike(String value) {
            addCriterion("finder like", value, "finder");
            return (Criteria) this;
        }

        public Criteria andFinderNotLike(String value) {
            addCriterion("finder not like", value, "finder");
            return (Criteria) this;
        }

        public Criteria andFinderIn(List<String> values) {
            addCriterion("finder in", values, "finder");
            return (Criteria) this;
        }

        public Criteria andFinderNotIn(List<String> values) {
            addCriterion("finder not in", values, "finder");
            return (Criteria) this;
        }

        public Criteria andFinderBetween(String value1, String value2) {
            addCriterion("finder between", value1, value2, "finder");
            return (Criteria) this;
        }

        public Criteria andFinderNotBetween(String value1, String value2) {
            addCriterion("finder not between", value1, value2, "finder");
            return (Criteria) this;
        }

        public Criteria andInfluenceTimeIsNull() {
            addCriterion("influence_time is null");
            return (Criteria) this;
        }

        public Criteria andInfluenceTimeIsNotNull() {
            addCriterion("influence_time is not null");
            return (Criteria) this;
        }

        public Criteria andInfluenceTimeEqualTo(Integer value) {
            addCriterion("influence_time =", value, "influenceTime");
            return (Criteria) this;
        }

        public Criteria andInfluenceTimeNotEqualTo(Integer value) {
            addCriterion("influence_time <>", value, "influenceTime");
            return (Criteria) this;
        }

        public Criteria andInfluenceTimeGreaterThan(Integer value) {
            addCriterion("influence_time >", value, "influenceTime");
            return (Criteria) this;
        }

        public Criteria andInfluenceTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("influence_time >=", value, "influenceTime");
            return (Criteria) this;
        }

        public Criteria andInfluenceTimeLessThan(Integer value) {
            addCriterion("influence_time <", value, "influenceTime");
            return (Criteria) this;
        }

        public Criteria andInfluenceTimeLessThanOrEqualTo(Integer value) {
            addCriterion("influence_time <=", value, "influenceTime");
            return (Criteria) this;
        }

        public Criteria andInfluenceTimeIn(List<Integer> values) {
            addCriterion("influence_time in", values, "influenceTime");
            return (Criteria) this;
        }

        public Criteria andInfluenceTimeNotIn(List<Integer> values) {
            addCriterion("influence_time not in", values, "influenceTime");
            return (Criteria) this;
        }

        public Criteria andInfluenceTimeBetween(Integer value1, Integer value2) {
            addCriterion("influence_time between", value1, value2, "influenceTime");
            return (Criteria) this;
        }

        public Criteria andInfluenceTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("influence_time not between", value1, value2, "influenceTime");
            return (Criteria) this;
        }

        public Criteria andClearTimeIsNull() {
            addCriterion("clear_time is null");
            return (Criteria) this;
        }

        public Criteria andClearTimeIsNotNull() {
            addCriterion("clear_time is not null");
            return (Criteria) this;
        }

        public Criteria andClearTimeEqualTo(Date value) {
            addCriterion("clear_time =", value, "clearTime");
            return (Criteria) this;
        }

        public Criteria andClearTimeNotEqualTo(Date value) {
            addCriterion("clear_time <>", value, "clearTime");
            return (Criteria) this;
        }

        public Criteria andClearTimeGreaterThan(Date value) {
            addCriterion("clear_time >", value, "clearTime");
            return (Criteria) this;
        }

        public Criteria andClearTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("clear_time >=", value, "clearTime");
            return (Criteria) this;
        }

        public Criteria andClearTimeLessThan(Date value) {
            addCriterion("clear_time <", value, "clearTime");
            return (Criteria) this;
        }

        public Criteria andClearTimeLessThanOrEqualTo(Date value) {
            addCriterion("clear_time <=", value, "clearTime");
            return (Criteria) this;
        }

        public Criteria andClearTimeIn(List<Date> values) {
            addCriterion("clear_time in", values, "clearTime");
            return (Criteria) this;
        }

        public Criteria andClearTimeNotIn(List<Date> values) {
            addCriterion("clear_time not in", values, "clearTime");
            return (Criteria) this;
        }

        public Criteria andClearTimeBetween(Date value1, Date value2) {
            addCriterion("clear_time between", value1, value2, "clearTime");
            return (Criteria) this;
        }

        public Criteria andClearTimeNotBetween(Date value1, Date value2) {
            addCriterion("clear_time not between", value1, value2, "clearTime");
            return (Criteria) this;
        }

        public Criteria andLocatorIsNull() {
            addCriterion("locator is null");
            return (Criteria) this;
        }

        public Criteria andLocatorIsNotNull() {
            addCriterion("locator is not null");
            return (Criteria) this;
        }

        public Criteria andLocatorEqualTo(String value) {
            addCriterion("locator =", value, "locator");
            return (Criteria) this;
        }

        public Criteria andLocatorNotEqualTo(String value) {
            addCriterion("locator <>", value, "locator");
            return (Criteria) this;
        }

        public Criteria andLocatorGreaterThan(String value) {
            addCriterion("locator >", value, "locator");
            return (Criteria) this;
        }

        public Criteria andLocatorGreaterThanOrEqualTo(String value) {
            addCriterion("locator >=", value, "locator");
            return (Criteria) this;
        }

        public Criteria andLocatorLessThan(String value) {
            addCriterion("locator <", value, "locator");
            return (Criteria) this;
        }

        public Criteria andLocatorLessThanOrEqualTo(String value) {
            addCriterion("locator <=", value, "locator");
            return (Criteria) this;
        }

        public Criteria andLocatorLike(String value) {
            addCriterion("locator like", value, "locator");
            return (Criteria) this;
        }

        public Criteria andLocatorNotLike(String value) {
            addCriterion("locator not like", value, "locator");
            return (Criteria) this;
        }

        public Criteria andLocatorIn(List<String> values) {
            addCriterion("locator in", values, "locator");
            return (Criteria) this;
        }

        public Criteria andLocatorNotIn(List<String> values) {
            addCriterion("locator not in", values, "locator");
            return (Criteria) this;
        }

        public Criteria andLocatorBetween(String value1, String value2) {
            addCriterion("locator between", value1, value2, "locator");
            return (Criteria) this;
        }

        public Criteria andLocatorNotBetween(String value1, String value2) {
            addCriterion("locator not between", value1, value2, "locator");
            return (Criteria) this;
        }

        public Criteria andOrderLossIsNull() {
            addCriterion("order_loss is null");
            return (Criteria) this;
        }

        public Criteria andOrderLossIsNotNull() {
            addCriterion("order_loss is not null");
            return (Criteria) this;
        }

        public Criteria andOrderLossEqualTo(BigDecimal value) {
            addCriterion("order_loss =", value, "orderLoss");
            return (Criteria) this;
        }

        public Criteria andOrderLossNotEqualTo(BigDecimal value) {
            addCriterion("order_loss <>", value, "orderLoss");
            return (Criteria) this;
        }

        public Criteria andOrderLossGreaterThan(BigDecimal value) {
            addCriterion("order_loss >", value, "orderLoss");
            return (Criteria) this;
        }

        public Criteria andOrderLossGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("order_loss >=", value, "orderLoss");
            return (Criteria) this;
        }

        public Criteria andOrderLossLessThan(BigDecimal value) {
            addCriterion("order_loss <", value, "orderLoss");
            return (Criteria) this;
        }

        public Criteria andOrderLossLessThanOrEqualTo(BigDecimal value) {
            addCriterion("order_loss <=", value, "orderLoss");
            return (Criteria) this;
        }

        public Criteria andOrderLossIn(List<BigDecimal> values) {
            addCriterion("order_loss in", values, "orderLoss");
            return (Criteria) this;
        }

        public Criteria andOrderLossNotIn(List<BigDecimal> values) {
            addCriterion("order_loss not in", values, "orderLoss");
            return (Criteria) this;
        }

        public Criteria andOrderLossBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("order_loss between", value1, value2, "orderLoss");
            return (Criteria) this;
        }

        public Criteria andOrderLossNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("order_loss not between", value1, value2, "orderLoss");
            return (Criteria) this;
        }

        public Criteria andCapitalLossIsNull() {
            addCriterion("capital_loss is null");
            return (Criteria) this;
        }

        public Criteria andCapitalLossIsNotNull() {
            addCriterion("capital_loss is not null");
            return (Criteria) this;
        }

        public Criteria andCapitalLossEqualTo(BigDecimal value) {
            addCriterion("capital_loss =", value, "capitalLoss");
            return (Criteria) this;
        }

        public Criteria andCapitalLossNotEqualTo(BigDecimal value) {
            addCriterion("capital_loss <>", value, "capitalLoss");
            return (Criteria) this;
        }

        public Criteria andCapitalLossGreaterThan(BigDecimal value) {
            addCriterion("capital_loss >", value, "capitalLoss");
            return (Criteria) this;
        }

        public Criteria andCapitalLossGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("capital_loss >=", value, "capitalLoss");
            return (Criteria) this;
        }

        public Criteria andCapitalLossLessThan(BigDecimal value) {
            addCriterion("capital_loss <", value, "capitalLoss");
            return (Criteria) this;
        }

        public Criteria andCapitalLossLessThanOrEqualTo(BigDecimal value) {
            addCriterion("capital_loss <=", value, "capitalLoss");
            return (Criteria) this;
        }

        public Criteria andCapitalLossIn(List<BigDecimal> values) {
            addCriterion("capital_loss in", values, "capitalLoss");
            return (Criteria) this;
        }

        public Criteria andCapitalLossNotIn(List<BigDecimal> values) {
            addCriterion("capital_loss not in", values, "capitalLoss");
            return (Criteria) this;
        }

        public Criteria andCapitalLossBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("capital_loss between", value1, value2, "capitalLoss");
            return (Criteria) this;
        }

        public Criteria andCapitalLossNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("capital_loss not between", value1, value2, "capitalLoss");
            return (Criteria) this;
        }

        public Criteria andCouponLossIsNull() {
            addCriterion("coupon_loss is null");
            return (Criteria) this;
        }

        public Criteria andCouponLossIsNotNull() {
            addCriterion("coupon_loss is not null");
            return (Criteria) this;
        }

        public Criteria andCouponLossEqualTo(String value) {
            addCriterion("coupon_loss =", value, "couponLoss");
            return (Criteria) this;
        }

        public Criteria andCouponLossNotEqualTo(String value) {
            addCriterion("coupon_loss <>", value, "couponLoss");
            return (Criteria) this;
        }

        public Criteria andCouponLossGreaterThan(String value) {
            addCriterion("coupon_loss >", value, "couponLoss");
            return (Criteria) this;
        }

        public Criteria andCouponLossGreaterThanOrEqualTo(String value) {
            addCriterion("coupon_loss >=", value, "couponLoss");
            return (Criteria) this;
        }

        public Criteria andCouponLossLessThan(String value) {
            addCriterion("coupon_loss <", value, "couponLoss");
            return (Criteria) this;
        }

        public Criteria andCouponLossLessThanOrEqualTo(String value) {
            addCriterion("coupon_loss <=", value, "couponLoss");
            return (Criteria) this;
        }

        public Criteria andCouponLossLike(String value) {
            addCriterion("coupon_loss like", value, "couponLoss");
            return (Criteria) this;
        }

        public Criteria andCouponLossNotLike(String value) {
            addCriterion("coupon_loss not like", value, "couponLoss");
            return (Criteria) this;
        }

        public Criteria andCouponLossIn(List<String> values) {
            addCriterion("coupon_loss in", values, "couponLoss");
            return (Criteria) this;
        }

        public Criteria andCouponLossNotIn(List<String> values) {
            addCriterion("coupon_loss not in", values, "couponLoss");
            return (Criteria) this;
        }

        public Criteria andCouponLossBetween(String value1, String value2) {
            addCriterion("coupon_loss between", value1, value2, "couponLoss");
            return (Criteria) this;
        }

        public Criteria andCouponLossNotBetween(String value1, String value2) {
            addCriterion("coupon_loss not between", value1, value2, "couponLoss");
            return (Criteria) this;
        }

        public Criteria andOnlineDiscoveryIsNull() {
            addCriterion("online_discovery is null");
            return (Criteria) this;
        }

        public Criteria andOnlineDiscoveryIsNotNull() {
            addCriterion("online_discovery is not null");
            return (Criteria) this;
        }

        public Criteria andOnlineDiscoveryEqualTo(String value) {
            addCriterion("online_discovery =", value, "onlineDiscovery");
            return (Criteria) this;
        }

        public Criteria andOnlineDiscoveryNotEqualTo(String value) {
            addCriterion("online_discovery <>", value, "onlineDiscovery");
            return (Criteria) this;
        }

        public Criteria andOnlineDiscoveryGreaterThan(String value) {
            addCriterion("online_discovery >", value, "onlineDiscovery");
            return (Criteria) this;
        }

        public Criteria andOnlineDiscoveryGreaterThanOrEqualTo(String value) {
            addCriterion("online_discovery >=", value, "onlineDiscovery");
            return (Criteria) this;
        }

        public Criteria andOnlineDiscoveryLessThan(String value) {
            addCriterion("online_discovery <", value, "onlineDiscovery");
            return (Criteria) this;
        }

        public Criteria andOnlineDiscoveryLessThanOrEqualTo(String value) {
            addCriterion("online_discovery <=", value, "onlineDiscovery");
            return (Criteria) this;
        }

        public Criteria andOnlineDiscoveryLike(String value) {
            addCriterion("online_discovery like", value, "onlineDiscovery");
            return (Criteria) this;
        }

        public Criteria andOnlineDiscoveryNotLike(String value) {
            addCriterion("online_discovery not like", value, "onlineDiscovery");
            return (Criteria) this;
        }

        public Criteria andOnlineDiscoveryIn(List<String> values) {
            addCriterion("online_discovery in", values, "onlineDiscovery");
            return (Criteria) this;
        }

        public Criteria andOnlineDiscoveryNotIn(List<String> values) {
            addCriterion("online_discovery not in", values, "onlineDiscovery");
            return (Criteria) this;
        }

        public Criteria andOnlineDiscoveryBetween(String value1, String value2) {
            addCriterion("online_discovery between", value1, value2, "onlineDiscovery");
            return (Criteria) this;
        }

        public Criteria andOnlineDiscoveryNotBetween(String value1, String value2) {
            addCriterion("online_discovery not between", value1, value2, "onlineDiscovery");
            return (Criteria) this;
        }

        public Criteria andOnlineClassificationIsNull() {
            addCriterion("online_classification is null");
            return (Criteria) this;
        }

        public Criteria andOnlineClassificationIsNotNull() {
            addCriterion("online_classification is not null");
            return (Criteria) this;
        }

        public Criteria andOnlineClassificationEqualTo(String value) {
            addCriterion("online_classification =", value, "onlineClassification");
            return (Criteria) this;
        }

        public Criteria andOnlineClassificationNotEqualTo(String value) {
            addCriterion("online_classification <>", value, "onlineClassification");
            return (Criteria) this;
        }

        public Criteria andOnlineClassificationGreaterThan(String value) {
            addCriterion("online_classification >", value, "onlineClassification");
            return (Criteria) this;
        }

        public Criteria andOnlineClassificationGreaterThanOrEqualTo(String value) {
            addCriterion("online_classification >=", value, "onlineClassification");
            return (Criteria) this;
        }

        public Criteria andOnlineClassificationLessThan(String value) {
            addCriterion("online_classification <", value, "onlineClassification");
            return (Criteria) this;
        }

        public Criteria andOnlineClassificationLessThanOrEqualTo(String value) {
            addCriterion("online_classification <=", value, "onlineClassification");
            return (Criteria) this;
        }

        public Criteria andOnlineClassificationLike(String value) {
            addCriterion("online_classification like", value, "onlineClassification");
            return (Criteria) this;
        }

        public Criteria andOnlineClassificationNotLike(String value) {
            addCriterion("online_classification not like", value, "onlineClassification");
            return (Criteria) this;
        }

        public Criteria andOnlineClassificationIn(List<String> values) {
            addCriterion("online_classification in", values, "onlineClassification");
            return (Criteria) this;
        }

        public Criteria andOnlineClassificationNotIn(List<String> values) {
            addCriterion("online_classification not in", values, "onlineClassification");
            return (Criteria) this;
        }

        public Criteria andOnlineClassificationBetween(String value1, String value2) {
            addCriterion("online_classification between", value1, value2, "onlineClassification");
            return (Criteria) this;
        }

        public Criteria andOnlineClassificationNotBetween(String value1, String value2) {
            addCriterion("online_classification not between", value1, value2, "onlineClassification");
            return (Criteria) this;
        }

        public Criteria andLineIsNull() {
            addCriterion("line is null");
            return (Criteria) this;
        }

        public Criteria andLineIsNotNull() {
            addCriterion("line is not null");
            return (Criteria) this;
        }

        public Criteria andLineEqualTo(String value) {
            addCriterion("line =", value, "line");
            return (Criteria) this;
        }

        public Criteria andLineNotEqualTo(String value) {
            addCriterion("line <>", value, "line");
            return (Criteria) this;
        }

        public Criteria andLineGreaterThan(String value) {
            addCriterion("line >", value, "line");
            return (Criteria) this;
        }

        public Criteria andLineGreaterThanOrEqualTo(String value) {
            addCriterion("line >=", value, "line");
            return (Criteria) this;
        }

        public Criteria andLineLessThan(String value) {
            addCriterion("line <", value, "line");
            return (Criteria) this;
        }

        public Criteria andLineLessThanOrEqualTo(String value) {
            addCriterion("line <=", value, "line");
            return (Criteria) this;
        }

        public Criteria andLineLike(String value) {
            addCriterion("line like", value, "line");
            return (Criteria) this;
        }

        public Criteria andLineNotLike(String value) {
            addCriterion("line not like", value, "line");
            return (Criteria) this;
        }

        public Criteria andLineIn(List<String> values) {
            addCriterion("line in", values, "line");
            return (Criteria) this;
        }

        public Criteria andLineNotIn(List<String> values) {
            addCriterion("line not in", values, "line");
            return (Criteria) this;
        }

        public Criteria andLineBetween(String value1, String value2) {
            addCriterion("line between", value1, value2, "line");
            return (Criteria) this;
        }

        public Criteria andLineNotBetween(String value1, String value2) {
            addCriterion("line not between", value1, value2, "line");
            return (Criteria) this;
        }

        public Criteria andCustomLevelIsNull() {
            addCriterion("custom_level is null");
            return (Criteria) this;
        }

        public Criteria andCustomLevelIsNotNull() {
            addCriterion("custom_level is not null");
            return (Criteria) this;
        }

        public Criteria andCustomLevelEqualTo(String value) {
            addCriterion("custom_level =", value, "customLevel");
            return (Criteria) this;
        }

        public Criteria andCustomLevelNotEqualTo(String value) {
            addCriterion("custom_level <>", value, "customLevel");
            return (Criteria) this;
        }

        public Criteria andCustomLevelGreaterThan(String value) {
            addCriterion("custom_level >", value, "customLevel");
            return (Criteria) this;
        }

        public Criteria andCustomLevelGreaterThanOrEqualTo(String value) {
            addCriterion("custom_level >=", value, "customLevel");
            return (Criteria) this;
        }

        public Criteria andCustomLevelLessThan(String value) {
            addCriterion("custom_level <", value, "customLevel");
            return (Criteria) this;
        }

        public Criteria andCustomLevelLessThanOrEqualTo(String value) {
            addCriterion("custom_level <=", value, "customLevel");
            return (Criteria) this;
        }

        public Criteria andCustomLevelLike(String value) {
            addCriterion("custom_level like", value, "customLevel");
            return (Criteria) this;
        }

        public Criteria andCustomLevelNotLike(String value) {
            addCriterion("custom_level not like", value, "customLevel");
            return (Criteria) this;
        }

        public Criteria andCustomLevelIn(List<String> values) {
            addCriterion("custom_level in", values, "customLevel");
            return (Criteria) this;
        }

        public Criteria andCustomLevelNotIn(List<String> values) {
            addCriterion("custom_level not in", values, "customLevel");
            return (Criteria) this;
        }

        public Criteria andCustomLevelBetween(String value1, String value2) {
            addCriterion("custom_level between", value1, value2, "customLevel");
            return (Criteria) this;
        }

        public Criteria andCustomLevelNotBetween(String value1, String value2) {
            addCriterion("custom_level not between", value1, value2, "customLevel");
            return (Criteria) this;
        }

        public Criteria andNofundReasonIsNull() {
            addCriterion("nofund_reason is null");
            return (Criteria) this;
        }

        public Criteria andNofundReasonIsNotNull() {
            addCriterion("nofund_reason is not null");
            return (Criteria) this;
        }

        public Criteria andNofundReasonEqualTo(String value) {
            addCriterion("nofund_reason =", value, "nofundReason");
            return (Criteria) this;
        }

        public Criteria andNofundReasonNotEqualTo(String value) {
            addCriterion("nofund_reason <>", value, "nofundReason");
            return (Criteria) this;
        }

        public Criteria andNofundReasonGreaterThan(String value) {
            addCriterion("nofund_reason >", value, "nofundReason");
            return (Criteria) this;
        }

        public Criteria andNofundReasonGreaterThanOrEqualTo(String value) {
            addCriterion("nofund_reason >=", value, "nofundReason");
            return (Criteria) this;
        }

        public Criteria andNofundReasonLessThan(String value) {
            addCriterion("nofund_reason <", value, "nofundReason");
            return (Criteria) this;
        }

        public Criteria andNofundReasonLessThanOrEqualTo(String value) {
            addCriterion("nofund_reason <=", value, "nofundReason");
            return (Criteria) this;
        }

        public Criteria andNofundReasonLike(String value) {
            addCriterion("nofund_reason like", value, "nofundReason");
            return (Criteria) this;
        }

        public Criteria andNofundReasonNotLike(String value) {
            addCriterion("nofund_reason not like", value, "nofundReason");
            return (Criteria) this;
        }

        public Criteria andNofundReasonIn(List<String> values) {
            addCriterion("nofund_reason in", values, "nofundReason");
            return (Criteria) this;
        }

        public Criteria andNofundReasonNotIn(List<String> values) {
            addCriterion("nofund_reason not in", values, "nofundReason");
            return (Criteria) this;
        }

        public Criteria andNofundReasonBetween(String value1, String value2) {
            addCriterion("nofund_reason between", value1, value2, "nofundReason");
            return (Criteria) this;
        }

        public Criteria andNofundReasonNotBetween(String value1, String value2) {
            addCriterion("nofund_reason not between", value1, value2, "nofundReason");
            return (Criteria) this;
        }

        public Criteria andRootCauseIsNull() {
            addCriterion("root_cause is null");
            return (Criteria) this;
        }

        public Criteria andRootCauseIsNotNull() {
            addCriterion("root_cause is not null");
            return (Criteria) this;
        }

        public Criteria andRootCauseEqualTo(String value) {
            addCriterion("root_cause =", value, "rootCause");
            return (Criteria) this;
        }

        public Criteria andRootCauseNotEqualTo(String value) {
            addCriterion("root_cause <>", value, "rootCause");
            return (Criteria) this;
        }

        public Criteria andRootCauseGreaterThan(String value) {
            addCriterion("root_cause >", value, "rootCause");
            return (Criteria) this;
        }

        public Criteria andRootCauseGreaterThanOrEqualTo(String value) {
            addCriterion("root_cause >=", value, "rootCause");
            return (Criteria) this;
        }

        public Criteria andRootCauseLessThan(String value) {
            addCriterion("root_cause <", value, "rootCause");
            return (Criteria) this;
        }

        public Criteria andRootCauseLessThanOrEqualTo(String value) {
            addCriterion("root_cause <=", value, "rootCause");
            return (Criteria) this;
        }

        public Criteria andRootCauseLike(String value) {
            addCriterion("root_cause like", value, "rootCause");
            return (Criteria) this;
        }

        public Criteria andRootCauseNotLike(String value) {
            addCriterion("root_cause not like", value, "rootCause");
            return (Criteria) this;
        }

        public Criteria andRootCauseIn(List<String> values) {
            addCriterion("root_cause in", values, "rootCause");
            return (Criteria) this;
        }

        public Criteria andRootCauseNotIn(List<String> values) {
            addCriterion("root_cause not in", values, "rootCause");
            return (Criteria) this;
        }

        public Criteria andRootCauseBetween(String value1, String value2) {
            addCriterion("root_cause between", value1, value2, "rootCause");
            return (Criteria) this;
        }

        public Criteria andRootCauseNotBetween(String value1, String value2) {
            addCriterion("root_cause not between", value1, value2, "rootCause");
            return (Criteria) this;
        }

        public Criteria andMcdIdIsNull() {
            addCriterion("mcd_id is null");
            return (Criteria) this;
        }

        public Criteria andMcdIdIsNotNull() {
            addCriterion("mcd_id is not null");
            return (Criteria) this;
        }

        public Criteria andMcdIdEqualTo(Integer value) {
            addCriterion("mcd_id =", value, "mcdId");
            return (Criteria) this;
        }

        public Criteria andMcdIdNotEqualTo(Integer value) {
            addCriterion("mcd_id <>", value, "mcdId");
            return (Criteria) this;
        }

        public Criteria andMcdIdGreaterThan(Integer value) {
            addCriterion("mcd_id >", value, "mcdId");
            return (Criteria) this;
        }

        public Criteria andMcdIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("mcd_id >=", value, "mcdId");
            return (Criteria) this;
        }

        public Criteria andMcdIdLessThan(Integer value) {
            addCriterion("mcd_id <", value, "mcdId");
            return (Criteria) this;
        }

        public Criteria andMcdIdLessThanOrEqualTo(Integer value) {
            addCriterion("mcd_id <=", value, "mcdId");
            return (Criteria) this;
        }

        public Criteria andMcdIdIn(List<Integer> values) {
            addCriterion("mcd_id in", values, "mcdId");
            return (Criteria) this;
        }

        public Criteria andMcdIdNotIn(List<Integer> values) {
            addCriterion("mcd_id not in", values, "mcdId");
            return (Criteria) this;
        }

        public Criteria andMcdIdBetween(Integer value1, Integer value2) {
            addCriterion("mcd_id between", value1, value2, "mcdId");
            return (Criteria) this;
        }

        public Criteria andMcdIdNotBetween(Integer value1, Integer value2) {
            addCriterion("mcd_id not between", value1, value2, "mcdId");
            return (Criteria) this;
        }

        public Criteria andMcdNameIsNull() {
            addCriterion("mcd_name is null");
            return (Criteria) this;
        }

        public Criteria andMcdNameIsNotNull() {
            addCriterion("mcd_name is not null");
            return (Criteria) this;
        }

        public Criteria andMcdNameEqualTo(Integer value) {
            addCriterion("mcd_name =", value, "mcdName");
            return (Criteria) this;
        }

        public Criteria andMcdNameNotEqualTo(Integer value) {
            addCriterion("mcd_name <>", value, "mcdName");
            return (Criteria) this;
        }

        public Criteria andMcdNameGreaterThan(Integer value) {
            addCriterion("mcd_name >", value, "mcdName");
            return (Criteria) this;
        }

        public Criteria andMcdNameGreaterThanOrEqualTo(Integer value) {
            addCriterion("mcd_name >=", value, "mcdName");
            return (Criteria) this;
        }

        public Criteria andMcdNameLessThan(Integer value) {
            addCriterion("mcd_name <", value, "mcdName");
            return (Criteria) this;
        }

        public Criteria andMcdNameLessThanOrEqualTo(Integer value) {
            addCriterion("mcd_name <=", value, "mcdName");
            return (Criteria) this;
        }

        public Criteria andMcdNameIn(List<Integer> values) {
            addCriterion("mcd_name in", values, "mcdName");
            return (Criteria) this;
        }

        public Criteria andMcdNameNotIn(List<Integer> values) {
            addCriterion("mcd_name not in", values, "mcdName");
            return (Criteria) this;
        }

        public Criteria andMcdNameBetween(Integer value1, Integer value2) {
            addCriterion("mcd_name between", value1, value2, "mcdName");
            return (Criteria) this;
        }

        public Criteria andMcdNameNotBetween(Integer value1, Integer value2) {
            addCriterion("mcd_name not between", value1, value2, "mcdName");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}