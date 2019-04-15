package com.meituan.food.po;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WeekMomaCrashPOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public WeekMomaCrashPOExample() {
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

        public Criteria andMomaCrashCountIsNull() {
            addCriterion("moma_crash_count is null");
            return (Criteria) this;
        }

        public Criteria andMomaCrashCountIsNotNull() {
            addCriterion("moma_crash_count is not null");
            return (Criteria) this;
        }

        public Criteria andMomaCrashCountEqualTo(Integer value) {
            addCriterion("moma_crash_count =", value, "momaCrashCount");
            return (Criteria) this;
        }

        public Criteria andMomaCrashCountNotEqualTo(Integer value) {
            addCriterion("moma_crash_count <>", value, "momaCrashCount");
            return (Criteria) this;
        }

        public Criteria andMomaCrashCountGreaterThan(Integer value) {
            addCriterion("moma_crash_count >", value, "momaCrashCount");
            return (Criteria) this;
        }

        public Criteria andMomaCrashCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("moma_crash_count >=", value, "momaCrashCount");
            return (Criteria) this;
        }

        public Criteria andMomaCrashCountLessThan(Integer value) {
            addCriterion("moma_crash_count <", value, "momaCrashCount");
            return (Criteria) this;
        }

        public Criteria andMomaCrashCountLessThanOrEqualTo(Integer value) {
            addCriterion("moma_crash_count <=", value, "momaCrashCount");
            return (Criteria) this;
        }

        public Criteria andMomaCrashCountIn(List<Integer> values) {
            addCriterion("moma_crash_count in", values, "momaCrashCount");
            return (Criteria) this;
        }

        public Criteria andMomaCrashCountNotIn(List<Integer> values) {
            addCriterion("moma_crash_count not in", values, "momaCrashCount");
            return (Criteria) this;
        }

        public Criteria andMomaCrashCountBetween(Integer value1, Integer value2) {
            addCriterion("moma_crash_count between", value1, value2, "momaCrashCount");
            return (Criteria) this;
        }

        public Criteria andMomaCrashCountNotBetween(Integer value1, Integer value2) {
            addCriterion("moma_crash_count not between", value1, value2, "momaCrashCount");
            return (Criteria) this;
        }

        public Criteria andMomaCrashRateIsNull() {
            addCriterion("moma_crash_rate is null");
            return (Criteria) this;
        }

        public Criteria andMomaCrashRateIsNotNull() {
            addCriterion("moma_crash_rate is not null");
            return (Criteria) this;
        }

        public Criteria andMomaCrashRateEqualTo(BigDecimal value) {
            addCriterion("moma_crash_rate =", value, "momaCrashRate");
            return (Criteria) this;
        }

        public Criteria andMomaCrashRateNotEqualTo(BigDecimal value) {
            addCriterion("moma_crash_rate <>", value, "momaCrashRate");
            return (Criteria) this;
        }

        public Criteria andMomaCrashRateGreaterThan(BigDecimal value) {
            addCriterion("moma_crash_rate >", value, "momaCrashRate");
            return (Criteria) this;
        }

        public Criteria andMomaCrashRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("moma_crash_rate >=", value, "momaCrashRate");
            return (Criteria) this;
        }

        public Criteria andMomaCrashRateLessThan(BigDecimal value) {
            addCriterion("moma_crash_rate <", value, "momaCrashRate");
            return (Criteria) this;
        }

        public Criteria andMomaCrashRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("moma_crash_rate <=", value, "momaCrashRate");
            return (Criteria) this;
        }

        public Criteria andMomaCrashRateIn(List<BigDecimal> values) {
            addCriterion("moma_crash_rate in", values, "momaCrashRate");
            return (Criteria) this;
        }

        public Criteria andMomaCrashRateNotIn(List<BigDecimal> values) {
            addCriterion("moma_crash_rate not in", values, "momaCrashRate");
            return (Criteria) this;
        }

        public Criteria andMomaCrashRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("moma_crash_rate between", value1, value2, "momaCrashRate");
            return (Criteria) this;
        }

        public Criteria andMomaCrashRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("moma_crash_rate not between", value1, value2, "momaCrashRate");
            return (Criteria) this;
        }

        public Criteria andBeeCrashCountIsNull() {
            addCriterion("bee_crash_count is null");
            return (Criteria) this;
        }

        public Criteria andBeeCrashCountIsNotNull() {
            addCriterion("bee_crash_count is not null");
            return (Criteria) this;
        }

        public Criteria andBeeCrashCountEqualTo(Integer value) {
            addCriterion("bee_crash_count =", value, "beeCrashCount");
            return (Criteria) this;
        }

        public Criteria andBeeCrashCountNotEqualTo(Integer value) {
            addCriterion("bee_crash_count <>", value, "beeCrashCount");
            return (Criteria) this;
        }

        public Criteria andBeeCrashCountGreaterThan(Integer value) {
            addCriterion("bee_crash_count >", value, "beeCrashCount");
            return (Criteria) this;
        }

        public Criteria andBeeCrashCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("bee_crash_count >=", value, "beeCrashCount");
            return (Criteria) this;
        }

        public Criteria andBeeCrashCountLessThan(Integer value) {
            addCriterion("bee_crash_count <", value, "beeCrashCount");
            return (Criteria) this;
        }

        public Criteria andBeeCrashCountLessThanOrEqualTo(Integer value) {
            addCriterion("bee_crash_count <=", value, "beeCrashCount");
            return (Criteria) this;
        }

        public Criteria andBeeCrashCountIn(List<Integer> values) {
            addCriterion("bee_crash_count in", values, "beeCrashCount");
            return (Criteria) this;
        }

        public Criteria andBeeCrashCountNotIn(List<Integer> values) {
            addCriterion("bee_crash_count not in", values, "beeCrashCount");
            return (Criteria) this;
        }

        public Criteria andBeeCrashCountBetween(Integer value1, Integer value2) {
            addCriterion("bee_crash_count between", value1, value2, "beeCrashCount");
            return (Criteria) this;
        }

        public Criteria andBeeCrashCountNotBetween(Integer value1, Integer value2) {
            addCriterion("bee_crash_count not between", value1, value2, "beeCrashCount");
            return (Criteria) this;
        }

        public Criteria andBeeCrashRateIsNull() {
            addCriterion("bee_crash_rate is null");
            return (Criteria) this;
        }

        public Criteria andBeeCrashRateIsNotNull() {
            addCriterion("bee_crash_rate is not null");
            return (Criteria) this;
        }

        public Criteria andBeeCrashRateEqualTo(BigDecimal value) {
            addCriterion("bee_crash_rate =", value, "beeCrashRate");
            return (Criteria) this;
        }

        public Criteria andBeeCrashRateNotEqualTo(BigDecimal value) {
            addCriterion("bee_crash_rate <>", value, "beeCrashRate");
            return (Criteria) this;
        }

        public Criteria andBeeCrashRateGreaterThan(BigDecimal value) {
            addCriterion("bee_crash_rate >", value, "beeCrashRate");
            return (Criteria) this;
        }

        public Criteria andBeeCrashRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("bee_crash_rate >=", value, "beeCrashRate");
            return (Criteria) this;
        }

        public Criteria andBeeCrashRateLessThan(BigDecimal value) {
            addCriterion("bee_crash_rate <", value, "beeCrashRate");
            return (Criteria) this;
        }

        public Criteria andBeeCrashRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("bee_crash_rate <=", value, "beeCrashRate");
            return (Criteria) this;
        }

        public Criteria andBeeCrashRateIn(List<BigDecimal> values) {
            addCriterion("bee_crash_rate in", values, "beeCrashRate");
            return (Criteria) this;
        }

        public Criteria andBeeCrashRateNotIn(List<BigDecimal> values) {
            addCriterion("bee_crash_rate not in", values, "beeCrashRate");
            return (Criteria) this;
        }

        public Criteria andBeeCrashRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("bee_crash_rate between", value1, value2, "beeCrashRate");
            return (Criteria) this;
        }

        public Criteria andBeeCrashRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("bee_crash_rate not between", value1, value2, "beeCrashRate");
            return (Criteria) this;
        }

        public Criteria andAboluoCrashCountIsNull() {
            addCriterion("aboluo_crash_count is null");
            return (Criteria) this;
        }

        public Criteria andAboluoCrashCountIsNotNull() {
            addCriterion("aboluo_crash_count is not null");
            return (Criteria) this;
        }

        public Criteria andAboluoCrashCountEqualTo(Integer value) {
            addCriterion("aboluo_crash_count =", value, "aboluoCrashCount");
            return (Criteria) this;
        }

        public Criteria andAboluoCrashCountNotEqualTo(Integer value) {
            addCriterion("aboluo_crash_count <>", value, "aboluoCrashCount");
            return (Criteria) this;
        }

        public Criteria andAboluoCrashCountGreaterThan(Integer value) {
            addCriterion("aboluo_crash_count >", value, "aboluoCrashCount");
            return (Criteria) this;
        }

        public Criteria andAboluoCrashCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("aboluo_crash_count >=", value, "aboluoCrashCount");
            return (Criteria) this;
        }

        public Criteria andAboluoCrashCountLessThan(Integer value) {
            addCriterion("aboluo_crash_count <", value, "aboluoCrashCount");
            return (Criteria) this;
        }

        public Criteria andAboluoCrashCountLessThanOrEqualTo(Integer value) {
            addCriterion("aboluo_crash_count <=", value, "aboluoCrashCount");
            return (Criteria) this;
        }

        public Criteria andAboluoCrashCountIn(List<Integer> values) {
            addCriterion("aboluo_crash_count in", values, "aboluoCrashCount");
            return (Criteria) this;
        }

        public Criteria andAboluoCrashCountNotIn(List<Integer> values) {
            addCriterion("aboluo_crash_count not in", values, "aboluoCrashCount");
            return (Criteria) this;
        }

        public Criteria andAboluoCrashCountBetween(Integer value1, Integer value2) {
            addCriterion("aboluo_crash_count between", value1, value2, "aboluoCrashCount");
            return (Criteria) this;
        }

        public Criteria andAboluoCrashCountNotBetween(Integer value1, Integer value2) {
            addCriterion("aboluo_crash_count not between", value1, value2, "aboluoCrashCount");
            return (Criteria) this;
        }

        public Criteria andAboluoCrashRateIsNull() {
            addCriterion("aboluo_crash_rate is null");
            return (Criteria) this;
        }

        public Criteria andAboluoCrashRateIsNotNull() {
            addCriterion("aboluo_crash_rate is not null");
            return (Criteria) this;
        }

        public Criteria andAboluoCrashRateEqualTo(BigDecimal value) {
            addCriterion("aboluo_crash_rate =", value, "aboluoCrashRate");
            return (Criteria) this;
        }

        public Criteria andAboluoCrashRateNotEqualTo(BigDecimal value) {
            addCriterion("aboluo_crash_rate <>", value, "aboluoCrashRate");
            return (Criteria) this;
        }

        public Criteria andAboluoCrashRateGreaterThan(BigDecimal value) {
            addCriterion("aboluo_crash_rate >", value, "aboluoCrashRate");
            return (Criteria) this;
        }

        public Criteria andAboluoCrashRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("aboluo_crash_rate >=", value, "aboluoCrashRate");
            return (Criteria) this;
        }

        public Criteria andAboluoCrashRateLessThan(BigDecimal value) {
            addCriterion("aboluo_crash_rate <", value, "aboluoCrashRate");
            return (Criteria) this;
        }

        public Criteria andAboluoCrashRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("aboluo_crash_rate <=", value, "aboluoCrashRate");
            return (Criteria) this;
        }

        public Criteria andAboluoCrashRateIn(List<BigDecimal> values) {
            addCriterion("aboluo_crash_rate in", values, "aboluoCrashRate");
            return (Criteria) this;
        }

        public Criteria andAboluoCrashRateNotIn(List<BigDecimal> values) {
            addCriterion("aboluo_crash_rate not in", values, "aboluoCrashRate");
            return (Criteria) this;
        }

        public Criteria andAboluoCrashRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("aboluo_crash_rate between", value1, value2, "aboluoCrashRate");
            return (Criteria) this;
        }

        public Criteria andAboluoCrashRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("aboluo_crash_rate not between", value1, value2, "aboluoCrashRate");
            return (Criteria) this;
        }

        public Criteria andStartDateIsNull() {
            addCriterion("start_date is null");
            return (Criteria) this;
        }

        public Criteria andStartDateIsNotNull() {
            addCriterion("start_date is not null");
            return (Criteria) this;
        }

        public Criteria andStartDateEqualTo(String value) {
            addCriterion("start_date =", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotEqualTo(String value) {
            addCriterion("start_date <>", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateGreaterThan(String value) {
            addCriterion("start_date >", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateGreaterThanOrEqualTo(String value) {
            addCriterion("start_date >=", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateLessThan(String value) {
            addCriterion("start_date <", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateLessThanOrEqualTo(String value) {
            addCriterion("start_date <=", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateLike(String value) {
            addCriterion("start_date like", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotLike(String value) {
            addCriterion("start_date not like", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateIn(List<String> values) {
            addCriterion("start_date in", values, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotIn(List<String> values) {
            addCriterion("start_date not in", values, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateBetween(String value1, String value2) {
            addCriterion("start_date between", value1, value2, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotBetween(String value1, String value2) {
            addCriterion("start_date not between", value1, value2, "startDate");
            return (Criteria) this;
        }

        public Criteria andEndDateIsNull() {
            addCriterion("end_date is null");
            return (Criteria) this;
        }

        public Criteria andEndDateIsNotNull() {
            addCriterion("end_date is not null");
            return (Criteria) this;
        }

        public Criteria andEndDateEqualTo(String value) {
            addCriterion("end_date =", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotEqualTo(String value) {
            addCriterion("end_date <>", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateGreaterThan(String value) {
            addCriterion("end_date >", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateGreaterThanOrEqualTo(String value) {
            addCriterion("end_date >=", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLessThan(String value) {
            addCriterion("end_date <", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLessThanOrEqualTo(String value) {
            addCriterion("end_date <=", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLike(String value) {
            addCriterion("end_date like", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotLike(String value) {
            addCriterion("end_date not like", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateIn(List<String> values) {
            addCriterion("end_date in", values, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotIn(List<String> values) {
            addCriterion("end_date not in", values, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateBetween(String value1, String value2) {
            addCriterion("end_date between", value1, value2, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotBetween(String value1, String value2) {
            addCriterion("end_date not between", value1, value2, "endDate");
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