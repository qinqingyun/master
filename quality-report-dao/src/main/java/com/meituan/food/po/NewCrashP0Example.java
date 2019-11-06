package com.meituan.food.po;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class NewCrashP0Example {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public NewCrashP0Example() {
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

        public Criteria andPlatformIsNull() {
            addCriterion("platform is null");
            return (Criteria) this;
        }

        public Criteria andPlatformIsNotNull() {
            addCriterion("platform is not null");
            return (Criteria) this;
        }

        public Criteria andPlatformEqualTo(String value) {
            addCriterion("platform =", value, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformNotEqualTo(String value) {
            addCriterion("platform <>", value, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformGreaterThan(String value) {
            addCriterion("platform >", value, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformGreaterThanOrEqualTo(String value) {
            addCriterion("platform >=", value, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformLessThan(String value) {
            addCriterion("platform <", value, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformLessThanOrEqualTo(String value) {
            addCriterion("platform <=", value, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformLike(String value) {
            addCriterion("platform like", value, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformNotLike(String value) {
            addCriterion("platform not like", value, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformIn(List<String> values) {
            addCriterion("platform in", values, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformNotIn(List<String> values) {
            addCriterion("platform not in", values, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformBetween(String value1, String value2) {
            addCriterion("platform between", value1, value2, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformNotBetween(String value1, String value2) {
            addCriterion("platform not between", value1, value2, "platform");
            return (Criteria) this;
        }

        public Criteria andCrashIsNull() {
            addCriterion("crash is null");
            return (Criteria) this;
        }

        public Criteria andCrashIsNotNull() {
            addCriterion("crash is not null");
            return (Criteria) this;
        }

        public Criteria andCrashEqualTo(Integer value) {
            addCriterion("crash =", value, "crash");
            return (Criteria) this;
        }

        public Criteria andCrashNotEqualTo(Integer value) {
            addCriterion("crash <>", value, "crash");
            return (Criteria) this;
        }

        public Criteria andCrashGreaterThan(Integer value) {
            addCriterion("crash >", value, "crash");
            return (Criteria) this;
        }

        public Criteria andCrashGreaterThanOrEqualTo(Integer value) {
            addCriterion("crash >=", value, "crash");
            return (Criteria) this;
        }

        public Criteria andCrashLessThan(Integer value) {
            addCriterion("crash <", value, "crash");
            return (Criteria) this;
        }

        public Criteria andCrashLessThanOrEqualTo(Integer value) {
            addCriterion("crash <=", value, "crash");
            return (Criteria) this;
        }

        public Criteria andCrashIn(List<Integer> values) {
            addCriterion("crash in", values, "crash");
            return (Criteria) this;
        }

        public Criteria andCrashNotIn(List<Integer> values) {
            addCriterion("crash not in", values, "crash");
            return (Criteria) this;
        }

        public Criteria andCrashBetween(Integer value1, Integer value2) {
            addCriterion("crash between", value1, value2, "crash");
            return (Criteria) this;
        }

        public Criteria andCrashNotBetween(Integer value1, Integer value2) {
            addCriterion("crash not between", value1, value2, "crash");
            return (Criteria) this;
        }

        public Criteria andCrashRateIsNull() {
            addCriterion("crash_rate is null");
            return (Criteria) this;
        }

        public Criteria andCrashRateIsNotNull() {
            addCriterion("crash_rate is not null");
            return (Criteria) this;
        }

        public Criteria andCrashRateEqualTo(BigDecimal value) {
            addCriterion("crash_rate =", value, "crashRate");
            return (Criteria) this;
        }

        public Criteria andCrashRateNotEqualTo(BigDecimal value) {
            addCriterion("crash_rate <>", value, "crashRate");
            return (Criteria) this;
        }

        public Criteria andCrashRateGreaterThan(BigDecimal value) {
            addCriterion("crash_rate >", value, "crashRate");
            return (Criteria) this;
        }

        public Criteria andCrashRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("crash_rate >=", value, "crashRate");
            return (Criteria) this;
        }

        public Criteria andCrashRateLessThan(BigDecimal value) {
            addCriterion("crash_rate <", value, "crashRate");
            return (Criteria) this;
        }

        public Criteria andCrashRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("crash_rate <=", value, "crashRate");
            return (Criteria) this;
        }

        public Criteria andCrashRateIn(List<BigDecimal> values) {
            addCriterion("crash_rate in", values, "crashRate");
            return (Criteria) this;
        }

        public Criteria andCrashRateNotIn(List<BigDecimal> values) {
            addCriterion("crash_rate not in", values, "crashRate");
            return (Criteria) this;
        }

        public Criteria andCrashRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("crash_rate between", value1, value2, "crashRate");
            return (Criteria) this;
        }

        public Criteria andCrashRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("crash_rate not between", value1, value2, "crashRate");
            return (Criteria) this;
        }

        public Criteria andCrashDateIsNull() {
            addCriterion("crash_date is null");
            return (Criteria) this;
        }

        public Criteria andCrashDateIsNotNull() {
            addCriterion("crash_date is not null");
            return (Criteria) this;
        }

        public Criteria andCrashDateEqualTo(Date value) {
            addCriterionForJDBCDate("crash_date =", value, "crashDate");
            return (Criteria) this;
        }

        public Criteria andCrashDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("crash_date <>", value, "crashDate");
            return (Criteria) this;
        }

        public Criteria andCrashDateGreaterThan(Date value) {
            addCriterionForJDBCDate("crash_date >", value, "crashDate");
            return (Criteria) this;
        }

        public Criteria andCrashDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("crash_date >=", value, "crashDate");
            return (Criteria) this;
        }

        public Criteria andCrashDateLessThan(Date value) {
            addCriterionForJDBCDate("crash_date <", value, "crashDate");
            return (Criteria) this;
        }

        public Criteria andCrashDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("crash_date <=", value, "crashDate");
            return (Criteria) this;
        }

        public Criteria andCrashDateIn(List<Date> values) {
            addCriterionForJDBCDate("crash_date in", values, "crashDate");
            return (Criteria) this;
        }

        public Criteria andCrashDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("crash_date not in", values, "crashDate");
            return (Criteria) this;
        }

        public Criteria andCrashDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("crash_date between", value1, value2, "crashDate");
            return (Criteria) this;
        }

        public Criteria andCrashDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("crash_date not between", value1, value2, "crashDate");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeIsNull() {
            addCriterion("created_time is null");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeIsNotNull() {
            addCriterion("created_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeEqualTo(Date value) {
            addCriterion("created_time =", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeNotEqualTo(Date value) {
            addCriterion("created_time <>", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeGreaterThan(Date value) {
            addCriterion("created_time >", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("created_time >=", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeLessThan(Date value) {
            addCriterion("created_time <", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeLessThanOrEqualTo(Date value) {
            addCriterion("created_time <=", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeIn(List<Date> values) {
            addCriterion("created_time in", values, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeNotIn(List<Date> values) {
            addCriterion("created_time not in", values, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeBetween(Date value1, Date value2) {
            addCriterion("created_time between", value1, value2, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeNotBetween(Date value1, Date value2) {
            addCriterion("created_time not between", value1, value2, "createdTime");
            return (Criteria) this;
        }

        public Criteria andFlagIsNull() {
            addCriterion("flag is null");
            return (Criteria) this;
        }

        public Criteria andFlagIsNotNull() {
            addCriterion("flag is not null");
            return (Criteria) this;
        }

        public Criteria andFlagEqualTo(Integer value) {
            addCriterion("flag =", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotEqualTo(Integer value) {
            addCriterion("flag <>", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagGreaterThan(Integer value) {
            addCriterion("flag >", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagGreaterThanOrEqualTo(Integer value) {
            addCriterion("flag >=", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagLessThan(Integer value) {
            addCriterion("flag <", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagLessThanOrEqualTo(Integer value) {
            addCriterion("flag <=", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagIn(List<Integer> values) {
            addCriterion("flag in", values, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotIn(List<Integer> values) {
            addCriterion("flag not in", values, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagBetween(Integer value1, Integer value2) {
            addCriterion("flag between", value1, value2, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotBetween(Integer value1, Integer value2) {
            addCriterion("flag not between", value1, value2, "flag");
            return (Criteria) this;
        }

        public Criteria andDateRangeIsNull() {
            addCriterion("date_range is null");
            return (Criteria) this;
        }

        public Criteria andDateRangeIsNotNull() {
            addCriterion("date_range is not null");
            return (Criteria) this;
        }

        public Criteria andDateRangeEqualTo(String value) {
            addCriterion("date_range =", value, "dateRange");
            return (Criteria) this;
        }

        public Criteria andDateRangeNotEqualTo(String value) {
            addCriterion("date_range <>", value, "dateRange");
            return (Criteria) this;
        }

        public Criteria andDateRangeGreaterThan(String value) {
            addCriterion("date_range >", value, "dateRange");
            return (Criteria) this;
        }

        public Criteria andDateRangeGreaterThanOrEqualTo(String value) {
            addCriterion("date_range >=", value, "dateRange");
            return (Criteria) this;
        }

        public Criteria andDateRangeLessThan(String value) {
            addCriterion("date_range <", value, "dateRange");
            return (Criteria) this;
        }

        public Criteria andDateRangeLessThanOrEqualTo(String value) {
            addCriterion("date_range <=", value, "dateRange");
            return (Criteria) this;
        }

        public Criteria andDateRangeLike(String value) {
            addCriterion("date_range like", value, "dateRange");
            return (Criteria) this;
        }

        public Criteria andDateRangeNotLike(String value) {
            addCriterion("date_range not like", value, "dateRange");
            return (Criteria) this;
        }

        public Criteria andDateRangeIn(List<String> values) {
            addCriterion("date_range in", values, "dateRange");
            return (Criteria) this;
        }

        public Criteria andDateRangeNotIn(List<String> values) {
            addCriterion("date_range not in", values, "dateRange");
            return (Criteria) this;
        }

        public Criteria andDateRangeBetween(String value1, String value2) {
            addCriterion("date_range between", value1, value2, "dateRange");
            return (Criteria) this;
        }

        public Criteria andDateRangeNotBetween(String value1, String value2) {
            addCriterion("date_range not between", value1, value2, "dateRange");
            return (Criteria) this;
        }

        public Criteria andOsIsNull() {
            addCriterion("os is null");
            return (Criteria) this;
        }

        public Criteria andOsIsNotNull() {
            addCriterion("os is not null");
            return (Criteria) this;
        }

        public Criteria andOsEqualTo(String value) {
            addCriterion("os =", value, "os");
            return (Criteria) this;
        }

        public Criteria andOsNotEqualTo(String value) {
            addCriterion("os <>", value, "os");
            return (Criteria) this;
        }

        public Criteria andOsGreaterThan(String value) {
            addCriterion("os >", value, "os");
            return (Criteria) this;
        }

        public Criteria andOsGreaterThanOrEqualTo(String value) {
            addCriterion("os >=", value, "os");
            return (Criteria) this;
        }

        public Criteria andOsLessThan(String value) {
            addCriterion("os <", value, "os");
            return (Criteria) this;
        }

        public Criteria andOsLessThanOrEqualTo(String value) {
            addCriterion("os <=", value, "os");
            return (Criteria) this;
        }

        public Criteria andOsLike(String value) {
            addCriterion("os like", value, "os");
            return (Criteria) this;
        }

        public Criteria andOsNotLike(String value) {
            addCriterion("os not like", value, "os");
            return (Criteria) this;
        }

        public Criteria andOsIn(List<String> values) {
            addCriterion("os in", values, "os");
            return (Criteria) this;
        }

        public Criteria andOsNotIn(List<String> values) {
            addCriterion("os not in", values, "os");
            return (Criteria) this;
        }

        public Criteria andOsBetween(String value1, String value2) {
            addCriterion("os between", value1, value2, "os");
            return (Criteria) this;
        }

        public Criteria andOsNotBetween(String value1, String value2) {
            addCriterion("os not between", value1, value2, "os");
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