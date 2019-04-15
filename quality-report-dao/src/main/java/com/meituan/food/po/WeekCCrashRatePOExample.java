package com.meituan.food.po;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WeekCCrashRatePOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public WeekCCrashRatePOExample() {
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

        public Criteria andPlantformIsNull() {
            addCriterion("plantform is null");
            return (Criteria) this;
        }

        public Criteria andPlantformIsNotNull() {
            addCriterion("plantform is not null");
            return (Criteria) this;
        }

        public Criteria andPlantformEqualTo(String value) {
            addCriterion("plantform =", value, "plantform");
            return (Criteria) this;
        }

        public Criteria andPlantformNotEqualTo(String value) {
            addCriterion("plantform <>", value, "plantform");
            return (Criteria) this;
        }

        public Criteria andPlantformGreaterThan(String value) {
            addCriterion("plantform >", value, "plantform");
            return (Criteria) this;
        }

        public Criteria andPlantformGreaterThanOrEqualTo(String value) {
            addCriterion("plantform >=", value, "plantform");
            return (Criteria) this;
        }

        public Criteria andPlantformLessThan(String value) {
            addCriterion("plantform <", value, "plantform");
            return (Criteria) this;
        }

        public Criteria andPlantformLessThanOrEqualTo(String value) {
            addCriterion("plantform <=", value, "plantform");
            return (Criteria) this;
        }

        public Criteria andPlantformLike(String value) {
            addCriterion("plantform like", value, "plantform");
            return (Criteria) this;
        }

        public Criteria andPlantformNotLike(String value) {
            addCriterion("plantform not like", value, "plantform");
            return (Criteria) this;
        }

        public Criteria andPlantformIn(List<String> values) {
            addCriterion("plantform in", values, "plantform");
            return (Criteria) this;
        }

        public Criteria andPlantformNotIn(List<String> values) {
            addCriterion("plantform not in", values, "plantform");
            return (Criteria) this;
        }

        public Criteria andPlantformBetween(String value1, String value2) {
            addCriterion("plantform between", value1, value2, "plantform");
            return (Criteria) this;
        }

        public Criteria andPlantformNotBetween(String value1, String value2) {
            addCriterion("plantform not between", value1, value2, "plantform");
            return (Criteria) this;
        }

        public Criteria andDauIsNull() {
            addCriterion("DAU is null");
            return (Criteria) this;
        }

        public Criteria andDauIsNotNull() {
            addCriterion("DAU is not null");
            return (Criteria) this;
        }

        public Criteria andDauEqualTo(Integer value) {
            addCriterion("DAU =", value, "dau");
            return (Criteria) this;
        }

        public Criteria andDauNotEqualTo(Integer value) {
            addCriterion("DAU <>", value, "dau");
            return (Criteria) this;
        }

        public Criteria andDauGreaterThan(Integer value) {
            addCriterion("DAU >", value, "dau");
            return (Criteria) this;
        }

        public Criteria andDauGreaterThanOrEqualTo(Integer value) {
            addCriterion("DAU >=", value, "dau");
            return (Criteria) this;
        }

        public Criteria andDauLessThan(Integer value) {
            addCriterion("DAU <", value, "dau");
            return (Criteria) this;
        }

        public Criteria andDauLessThanOrEqualTo(Integer value) {
            addCriterion("DAU <=", value, "dau");
            return (Criteria) this;
        }

        public Criteria andDauIn(List<Integer> values) {
            addCriterion("DAU in", values, "dau");
            return (Criteria) this;
        }

        public Criteria andDauNotIn(List<Integer> values) {
            addCriterion("DAU not in", values, "dau");
            return (Criteria) this;
        }

        public Criteria andDauBetween(Integer value1, Integer value2) {
            addCriterion("DAU between", value1, value2, "dau");
            return (Criteria) this;
        }

        public Criteria andDauNotBetween(Integer value1, Integer value2) {
            addCriterion("DAU not between", value1, value2, "dau");
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

        public Criteria andCrashRateEqualTo(String value) {
            addCriterion("crash_rate =", value, "crashRate");
            return (Criteria) this;
        }

        public Criteria andCrashRateNotEqualTo(String value) {
            addCriterion("crash_rate <>", value, "crashRate");
            return (Criteria) this;
        }

        public Criteria andCrashRateGreaterThan(String value) {
            addCriterion("crash_rate >", value, "crashRate");
            return (Criteria) this;
        }

        public Criteria andCrashRateGreaterThanOrEqualTo(String value) {
            addCriterion("crash_rate >=", value, "crashRate");
            return (Criteria) this;
        }

        public Criteria andCrashRateLessThan(String value) {
            addCriterion("crash_rate <", value, "crashRate");
            return (Criteria) this;
        }

        public Criteria andCrashRateLessThanOrEqualTo(String value) {
            addCriterion("crash_rate <=", value, "crashRate");
            return (Criteria) this;
        }

        public Criteria andCrashRateLike(String value) {
            addCriterion("crash_rate like", value, "crashRate");
            return (Criteria) this;
        }

        public Criteria andCrashRateNotLike(String value) {
            addCriterion("crash_rate not like", value, "crashRate");
            return (Criteria) this;
        }

        public Criteria andCrashRateIn(List<String> values) {
            addCriterion("crash_rate in", values, "crashRate");
            return (Criteria) this;
        }

        public Criteria andCrashRateNotIn(List<String> values) {
            addCriterion("crash_rate not in", values, "crashRate");
            return (Criteria) this;
        }

        public Criteria andCrashRateBetween(String value1, String value2) {
            addCriterion("crash_rate between", value1, value2, "crashRate");
            return (Criteria) this;
        }

        public Criteria andCrashRateNotBetween(String value1, String value2) {
            addCriterion("crash_rate not between", value1, value2, "crashRate");
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

        public Criteria andShowDateRangeIsNull() {
            addCriterion("show_date_range is null");
            return (Criteria) this;
        }

        public Criteria andShowDateRangeIsNotNull() {
            addCriterion("show_date_range is not null");
            return (Criteria) this;
        }

        public Criteria andShowDateRangeEqualTo(String value) {
            addCriterion("show_date_range =", value, "showDateRange");
            return (Criteria) this;
        }

        public Criteria andShowDateRangeNotEqualTo(String value) {
            addCriterion("show_date_range <>", value, "showDateRange");
            return (Criteria) this;
        }

        public Criteria andShowDateRangeGreaterThan(String value) {
            addCriterion("show_date_range >", value, "showDateRange");
            return (Criteria) this;
        }

        public Criteria andShowDateRangeGreaterThanOrEqualTo(String value) {
            addCriterion("show_date_range >=", value, "showDateRange");
            return (Criteria) this;
        }

        public Criteria andShowDateRangeLessThan(String value) {
            addCriterion("show_date_range <", value, "showDateRange");
            return (Criteria) this;
        }

        public Criteria andShowDateRangeLessThanOrEqualTo(String value) {
            addCriterion("show_date_range <=", value, "showDateRange");
            return (Criteria) this;
        }

        public Criteria andShowDateRangeLike(String value) {
            addCriterion("show_date_range like", value, "showDateRange");
            return (Criteria) this;
        }

        public Criteria andShowDateRangeNotLike(String value) {
            addCriterion("show_date_range not like", value, "showDateRange");
            return (Criteria) this;
        }

        public Criteria andShowDateRangeIn(List<String> values) {
            addCriterion("show_date_range in", values, "showDateRange");
            return (Criteria) this;
        }

        public Criteria andShowDateRangeNotIn(List<String> values) {
            addCriterion("show_date_range not in", values, "showDateRange");
            return (Criteria) this;
        }

        public Criteria andShowDateRangeBetween(String value1, String value2) {
            addCriterion("show_date_range between", value1, value2, "showDateRange");
            return (Criteria) this;
        }

        public Criteria andShowDateRangeNotBetween(String value1, String value2) {
            addCriterion("show_date_range not between", value1, value2, "showDateRange");
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

        public Criteria andFinalRateIsNull() {
            addCriterion("final_rate is null");
            return (Criteria) this;
        }

        public Criteria andFinalRateIsNotNull() {
            addCriterion("final_rate is not null");
            return (Criteria) this;
        }

        public Criteria andFinalRateEqualTo(BigDecimal value) {
            addCriterion("final_rate =", value, "finalRate");
            return (Criteria) this;
        }

        public Criteria andFinalRateNotEqualTo(BigDecimal value) {
            addCriterion("final_rate <>", value, "finalRate");
            return (Criteria) this;
        }

        public Criteria andFinalRateGreaterThan(BigDecimal value) {
            addCriterion("final_rate >", value, "finalRate");
            return (Criteria) this;
        }

        public Criteria andFinalRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("final_rate >=", value, "finalRate");
            return (Criteria) this;
        }

        public Criteria andFinalRateLessThan(BigDecimal value) {
            addCriterion("final_rate <", value, "finalRate");
            return (Criteria) this;
        }

        public Criteria andFinalRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("final_rate <=", value, "finalRate");
            return (Criteria) this;
        }

        public Criteria andFinalRateIn(List<BigDecimal> values) {
            addCriterion("final_rate in", values, "finalRate");
            return (Criteria) this;
        }

        public Criteria andFinalRateNotIn(List<BigDecimal> values) {
            addCriterion("final_rate not in", values, "finalRate");
            return (Criteria) this;
        }

        public Criteria andFinalRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("final_rate between", value1, value2, "finalRate");
            return (Criteria) this;
        }

        public Criteria andFinalRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("final_rate not between", value1, value2, "finalRate");
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