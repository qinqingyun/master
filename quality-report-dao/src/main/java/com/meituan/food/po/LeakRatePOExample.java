package com.meituan.food.po;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LeakRatePOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public LeakRatePOExample() {
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

        public Criteria andIssueNumIsNull() {
            addCriterion("issue_num is null");
            return (Criteria) this;
        }

        public Criteria andIssueNumIsNotNull() {
            addCriterion("issue_num is not null");
            return (Criteria) this;
        }

        public Criteria andIssueNumEqualTo(Integer value) {
            addCriterion("issue_num =", value, "issueNum");
            return (Criteria) this;
        }

        public Criteria andIssueNumNotEqualTo(Integer value) {
            addCriterion("issue_num <>", value, "issueNum");
            return (Criteria) this;
        }

        public Criteria andIssueNumGreaterThan(Integer value) {
            addCriterion("issue_num >", value, "issueNum");
            return (Criteria) this;
        }

        public Criteria andIssueNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("issue_num >=", value, "issueNum");
            return (Criteria) this;
        }

        public Criteria andIssueNumLessThan(Integer value) {
            addCriterion("issue_num <", value, "issueNum");
            return (Criteria) this;
        }

        public Criteria andIssueNumLessThanOrEqualTo(Integer value) {
            addCriterion("issue_num <=", value, "issueNum");
            return (Criteria) this;
        }

        public Criteria andIssueNumIn(List<Integer> values) {
            addCriterion("issue_num in", values, "issueNum");
            return (Criteria) this;
        }

        public Criteria andIssueNumNotIn(List<Integer> values) {
            addCriterion("issue_num not in", values, "issueNum");
            return (Criteria) this;
        }

        public Criteria andIssueNumBetween(Integer value1, Integer value2) {
            addCriterion("issue_num between", value1, value2, "issueNum");
            return (Criteria) this;
        }

        public Criteria andIssueNumNotBetween(Integer value1, Integer value2) {
            addCriterion("issue_num not between", value1, value2, "issueNum");
            return (Criteria) this;
        }

        public Criteria andBugNumIsNull() {
            addCriterion("bug_num is null");
            return (Criteria) this;
        }

        public Criteria andBugNumIsNotNull() {
            addCriterion("bug_num is not null");
            return (Criteria) this;
        }

        public Criteria andBugNumEqualTo(Integer value) {
            addCriterion("bug_num =", value, "bugNum");
            return (Criteria) this;
        }

        public Criteria andBugNumNotEqualTo(Integer value) {
            addCriterion("bug_num <>", value, "bugNum");
            return (Criteria) this;
        }

        public Criteria andBugNumGreaterThan(Integer value) {
            addCriterion("bug_num >", value, "bugNum");
            return (Criteria) this;
        }

        public Criteria andBugNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("bug_num >=", value, "bugNum");
            return (Criteria) this;
        }

        public Criteria andBugNumLessThan(Integer value) {
            addCriterion("bug_num <", value, "bugNum");
            return (Criteria) this;
        }

        public Criteria andBugNumLessThanOrEqualTo(Integer value) {
            addCriterion("bug_num <=", value, "bugNum");
            return (Criteria) this;
        }

        public Criteria andBugNumIn(List<Integer> values) {
            addCriterion("bug_num in", values, "bugNum");
            return (Criteria) this;
        }

        public Criteria andBugNumNotIn(List<Integer> values) {
            addCriterion("bug_num not in", values, "bugNum");
            return (Criteria) this;
        }

        public Criteria andBugNumBetween(Integer value1, Integer value2) {
            addCriterion("bug_num between", value1, value2, "bugNum");
            return (Criteria) this;
        }

        public Criteria andBugNumNotBetween(Integer value1, Integer value2) {
            addCriterion("bug_num not between", value1, value2, "bugNum");
            return (Criteria) this;
        }

        public Criteria andLeakTestRateIsNull() {
            addCriterion("leak_test_rate is null");
            return (Criteria) this;
        }

        public Criteria andLeakTestRateIsNotNull() {
            addCriterion("leak_test_rate is not null");
            return (Criteria) this;
        }

        public Criteria andLeakTestRateEqualTo(BigDecimal value) {
            addCriterion("leak_test_rate =", value, "leakTestRate");
            return (Criteria) this;
        }

        public Criteria andLeakTestRateNotEqualTo(BigDecimal value) {
            addCriterion("leak_test_rate <>", value, "leakTestRate");
            return (Criteria) this;
        }

        public Criteria andLeakTestRateGreaterThan(BigDecimal value) {
            addCriterion("leak_test_rate >", value, "leakTestRate");
            return (Criteria) this;
        }

        public Criteria andLeakTestRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("leak_test_rate >=", value, "leakTestRate");
            return (Criteria) this;
        }

        public Criteria andLeakTestRateLessThan(BigDecimal value) {
            addCriterion("leak_test_rate <", value, "leakTestRate");
            return (Criteria) this;
        }

        public Criteria andLeakTestRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("leak_test_rate <=", value, "leakTestRate");
            return (Criteria) this;
        }

        public Criteria andLeakTestRateIn(List<BigDecimal> values) {
            addCriterion("leak_test_rate in", values, "leakTestRate");
            return (Criteria) this;
        }

        public Criteria andLeakTestRateNotIn(List<BigDecimal> values) {
            addCriterion("leak_test_rate not in", values, "leakTestRate");
            return (Criteria) this;
        }

        public Criteria andLeakTestRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("leak_test_rate between", value1, value2, "leakTestRate");
            return (Criteria) this;
        }

        public Criteria andLeakTestRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("leak_test_rate not between", value1, value2, "leakTestRate");
            return (Criteria) this;
        }

        public Criteria andMonthIsNull() {
            addCriterion("month is null");
            return (Criteria) this;
        }

        public Criteria andMonthIsNotNull() {
            addCriterion("month is not null");
            return (Criteria) this;
        }

        public Criteria andMonthEqualTo(String value) {
            addCriterion("month =", value, "month");
            return (Criteria) this;
        }

        public Criteria andMonthNotEqualTo(String value) {
            addCriterion("month <>", value, "month");
            return (Criteria) this;
        }

        public Criteria andMonthGreaterThan(String value) {
            addCriterion("month >", value, "month");
            return (Criteria) this;
        }

        public Criteria andMonthGreaterThanOrEqualTo(String value) {
            addCriterion("month >=", value, "month");
            return (Criteria) this;
        }

        public Criteria andMonthLessThan(String value) {
            addCriterion("month <", value, "month");
            return (Criteria) this;
        }

        public Criteria andMonthLessThanOrEqualTo(String value) {
            addCriterion("month <=", value, "month");
            return (Criteria) this;
        }

        public Criteria andMonthLike(String value) {
            addCriterion("month like", value, "month");
            return (Criteria) this;
        }

        public Criteria andMonthNotLike(String value) {
            addCriterion("month not like", value, "month");
            return (Criteria) this;
        }

        public Criteria andMonthIn(List<String> values) {
            addCriterion("month in", values, "month");
            return (Criteria) this;
        }

        public Criteria andMonthNotIn(List<String> values) {
            addCriterion("month not in", values, "month");
            return (Criteria) this;
        }

        public Criteria andMonthBetween(String value1, String value2) {
            addCriterion("month between", value1, value2, "month");
            return (Criteria) this;
        }

        public Criteria andMonthNotBetween(String value1, String value2) {
            addCriterion("month not between", value1, value2, "month");
            return (Criteria) this;
        }

        public Criteria andCreatedAtIsNull() {
            addCriterion("created_at is null");
            return (Criteria) this;
        }

        public Criteria andCreatedAtIsNotNull() {
            addCriterion("created_at is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedAtEqualTo(Date value) {
            addCriterion("created_at =", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotEqualTo(Date value) {
            addCriterion("created_at <>", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThan(Date value) {
            addCriterion("created_at >", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThanOrEqualTo(Date value) {
            addCriterion("created_at >=", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThan(Date value) {
            addCriterion("created_at <", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThanOrEqualTo(Date value) {
            addCriterion("created_at <=", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtIn(List<Date> values) {
            addCriterion("created_at in", values, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotIn(List<Date> values) {
            addCriterion("created_at not in", values, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtBetween(Date value1, Date value2) {
            addCriterion("created_at between", value1, value2, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotBetween(Date value1, Date value2) {
            addCriterion("created_at not between", value1, value2, "createdAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtIsNull() {
            addCriterion("updated_at is null");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtIsNotNull() {
            addCriterion("updated_at is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtEqualTo(Date value) {
            addCriterion("updated_at =", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtNotEqualTo(Date value) {
            addCriterion("updated_at <>", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThan(Date value) {
            addCriterion("updated_at >", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThanOrEqualTo(Date value) {
            addCriterion("updated_at >=", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThan(Date value) {
            addCriterion("updated_at <", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThanOrEqualTo(Date value) {
            addCriterion("updated_at <=", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtIn(List<Date> values) {
            addCriterion("updated_at in", values, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtNotIn(List<Date> values) {
            addCriterion("updated_at not in", values, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtBetween(Date value1, Date value2) {
            addCriterion("updated_at between", value1, value2, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtNotBetween(Date value1, Date value2) {
            addCriterion("updated_at not between", value1, value2, "updatedAt");
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