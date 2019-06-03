package com.meituan.food.po;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ApiDetailPOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ApiDetailPOExample() {
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

        public Criteria andAppkeyIsNull() {
            addCriterion("appkey is null");
            return (Criteria) this;
        }

        public Criteria andAppkeyIsNotNull() {
            addCriterion("appkey is not null");
            return (Criteria) this;
        }

        public Criteria andAppkeyEqualTo(String value) {
            addCriterion("appkey =", value, "appkey");
            return (Criteria) this;
        }

        public Criteria andAppkeyNotEqualTo(String value) {
            addCriterion("appkey <>", value, "appkey");
            return (Criteria) this;
        }

        public Criteria andAppkeyGreaterThan(String value) {
            addCriterion("appkey >", value, "appkey");
            return (Criteria) this;
        }

        public Criteria andAppkeyGreaterThanOrEqualTo(String value) {
            addCriterion("appkey >=", value, "appkey");
            return (Criteria) this;
        }

        public Criteria andAppkeyLessThan(String value) {
            addCriterion("appkey <", value, "appkey");
            return (Criteria) this;
        }

        public Criteria andAppkeyLessThanOrEqualTo(String value) {
            addCriterion("appkey <=", value, "appkey");
            return (Criteria) this;
        }

        public Criteria andAppkeyLike(String value) {
            addCriterion("appkey like", value, "appkey");
            return (Criteria) this;
        }

        public Criteria andAppkeyNotLike(String value) {
            addCriterion("appkey not like", value, "appkey");
            return (Criteria) this;
        }

        public Criteria andAppkeyIn(List<String> values) {
            addCriterion("appkey in", values, "appkey");
            return (Criteria) this;
        }

        public Criteria andAppkeyNotIn(List<String> values) {
            addCriterion("appkey not in", values, "appkey");
            return (Criteria) this;
        }

        public Criteria andAppkeyBetween(String value1, String value2) {
            addCriterion("appkey between", value1, value2, "appkey");
            return (Criteria) this;
        }

        public Criteria andAppkeyNotBetween(String value1, String value2) {
            addCriterion("appkey not between", value1, value2, "appkey");
            return (Criteria) this;
        }

        public Criteria andApiFullNameIsNull() {
            addCriterion("api_full_name is null");
            return (Criteria) this;
        }

        public Criteria andApiFullNameIsNotNull() {
            addCriterion("api_full_name is not null");
            return (Criteria) this;
        }

        public Criteria andApiFullNameEqualTo(String value) {
            addCriterion("api_full_name =", value, "apiFullName");
            return (Criteria) this;
        }

        public Criteria andApiFullNameNotEqualTo(String value) {
            addCriterion("api_full_name <>", value, "apiFullName");
            return (Criteria) this;
        }

        public Criteria andApiFullNameGreaterThan(String value) {
            addCriterion("api_full_name >", value, "apiFullName");
            return (Criteria) this;
        }

        public Criteria andApiFullNameGreaterThanOrEqualTo(String value) {
            addCriterion("api_full_name >=", value, "apiFullName");
            return (Criteria) this;
        }

        public Criteria andApiFullNameLessThan(String value) {
            addCriterion("api_full_name <", value, "apiFullName");
            return (Criteria) this;
        }

        public Criteria andApiFullNameLessThanOrEqualTo(String value) {
            addCriterion("api_full_name <=", value, "apiFullName");
            return (Criteria) this;
        }

        public Criteria andApiFullNameLike(String value) {
            addCriterion("api_full_name like", value, "apiFullName");
            return (Criteria) this;
        }

        public Criteria andApiFullNameNotLike(String value) {
            addCriterion("api_full_name not like", value, "apiFullName");
            return (Criteria) this;
        }

        public Criteria andApiFullNameIn(List<String> values) {
            addCriterion("api_full_name in", values, "apiFullName");
            return (Criteria) this;
        }

        public Criteria andApiFullNameNotIn(List<String> values) {
            addCriterion("api_full_name not in", values, "apiFullName");
            return (Criteria) this;
        }

        public Criteria andApiFullNameBetween(String value1, String value2) {
            addCriterion("api_full_name between", value1, value2, "apiFullName");
            return (Criteria) this;
        }

        public Criteria andApiFullNameNotBetween(String value1, String value2) {
            addCriterion("api_full_name not between", value1, value2, "apiFullName");
            return (Criteria) this;
        }

        public Criteria andCallCountIsNull() {
            addCriterion("call_count is null");
            return (Criteria) this;
        }

        public Criteria andCallCountIsNotNull() {
            addCriterion("call_count is not null");
            return (Criteria) this;
        }

        public Criteria andCallCountEqualTo(Long value) {
            addCriterion("call_count =", value, "callCount");
            return (Criteria) this;
        }

        public Criteria andCallCountNotEqualTo(Long value) {
            addCriterion("call_count <>", value, "callCount");
            return (Criteria) this;
        }

        public Criteria andCallCountGreaterThan(Long value) {
            addCriterion("call_count >", value, "callCount");
            return (Criteria) this;
        }

        public Criteria andCallCountGreaterThanOrEqualTo(Long value) {
            addCriterion("call_count >=", value, "callCount");
            return (Criteria) this;
        }

        public Criteria andCallCountLessThan(Long value) {
            addCriterion("call_count <", value, "callCount");
            return (Criteria) this;
        }

        public Criteria andCallCountLessThanOrEqualTo(Long value) {
            addCriterion("call_count <=", value, "callCount");
            return (Criteria) this;
        }

        public Criteria andCallCountIn(List<Long> values) {
            addCriterion("call_count in", values, "callCount");
            return (Criteria) this;
        }

        public Criteria andCallCountNotIn(List<Long> values) {
            addCriterion("call_count not in", values, "callCount");
            return (Criteria) this;
        }

        public Criteria andCallCountBetween(Long value1, Long value2) {
            addCriterion("call_count between", value1, value2, "callCount");
            return (Criteria) this;
        }

        public Criteria andCallCountNotBetween(Long value1, Long value2) {
            addCriterion("call_count not between", value1, value2, "callCount");
            return (Criteria) this;
        }

        public Criteria andApiSpanNameIsNull() {
            addCriterion("api_span_name is null");
            return (Criteria) this;
        }

        public Criteria andApiSpanNameIsNotNull() {
            addCriterion("api_span_name is not null");
            return (Criteria) this;
        }

        public Criteria andApiSpanNameEqualTo(String value) {
            addCriterion("api_span_name =", value, "apiSpanName");
            return (Criteria) this;
        }

        public Criteria andApiSpanNameNotEqualTo(String value) {
            addCriterion("api_span_name <>", value, "apiSpanName");
            return (Criteria) this;
        }

        public Criteria andApiSpanNameGreaterThan(String value) {
            addCriterion("api_span_name >", value, "apiSpanName");
            return (Criteria) this;
        }

        public Criteria andApiSpanNameGreaterThanOrEqualTo(String value) {
            addCriterion("api_span_name >=", value, "apiSpanName");
            return (Criteria) this;
        }

        public Criteria andApiSpanNameLessThan(String value) {
            addCriterion("api_span_name <", value, "apiSpanName");
            return (Criteria) this;
        }

        public Criteria andApiSpanNameLessThanOrEqualTo(String value) {
            addCriterion("api_span_name <=", value, "apiSpanName");
            return (Criteria) this;
        }

        public Criteria andApiSpanNameLike(String value) {
            addCriterion("api_span_name like", value, "apiSpanName");
            return (Criteria) this;
        }

        public Criteria andApiSpanNameNotLike(String value) {
            addCriterion("api_span_name not like", value, "apiSpanName");
            return (Criteria) this;
        }

        public Criteria andApiSpanNameIn(List<String> values) {
            addCriterion("api_span_name in", values, "apiSpanName");
            return (Criteria) this;
        }

        public Criteria andApiSpanNameNotIn(List<String> values) {
            addCriterion("api_span_name not in", values, "apiSpanName");
            return (Criteria) this;
        }

        public Criteria andApiSpanNameBetween(String value1, String value2) {
            addCriterion("api_span_name between", value1, value2, "apiSpanName");
            return (Criteria) this;
        }

        public Criteria andApiSpanNameNotBetween(String value1, String value2) {
            addCriterion("api_span_name not between", value1, value2, "apiSpanName");
            return (Criteria) this;
        }

        public Criteria andProportionIsNull() {
            addCriterion("proportion is null");
            return (Criteria) this;
        }

        public Criteria andProportionIsNotNull() {
            addCriterion("proportion is not null");
            return (Criteria) this;
        }

        public Criteria andProportionEqualTo(BigDecimal value) {
            addCriterion("proportion =", value, "proportion");
            return (Criteria) this;
        }

        public Criteria andProportionNotEqualTo(BigDecimal value) {
            addCriterion("proportion <>", value, "proportion");
            return (Criteria) this;
        }

        public Criteria andProportionGreaterThan(BigDecimal value) {
            addCriterion("proportion >", value, "proportion");
            return (Criteria) this;
        }

        public Criteria andProportionGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("proportion >=", value, "proportion");
            return (Criteria) this;
        }

        public Criteria andProportionLessThan(BigDecimal value) {
            addCriterion("proportion <", value, "proportion");
            return (Criteria) this;
        }

        public Criteria andProportionLessThanOrEqualTo(BigDecimal value) {
            addCriterion("proportion <=", value, "proportion");
            return (Criteria) this;
        }

        public Criteria andProportionIn(List<BigDecimal> values) {
            addCriterion("proportion in", values, "proportion");
            return (Criteria) this;
        }

        public Criteria andProportionNotIn(List<BigDecimal> values) {
            addCriterion("proportion not in", values, "proportion");
            return (Criteria) this;
        }

        public Criteria andProportionBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("proportion between", value1, value2, "proportion");
            return (Criteria) this;
        }

        public Criteria andProportionNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("proportion not between", value1, value2, "proportion");
            return (Criteria) this;
        }

        public Criteria andIsCoreIsNull() {
            addCriterion("is_core is null");
            return (Criteria) this;
        }

        public Criteria andIsCoreIsNotNull() {
            addCriterion("is_core is not null");
            return (Criteria) this;
        }

        public Criteria andIsCoreEqualTo(Integer value) {
            addCriterion("is_core =", value, "isCore");
            return (Criteria) this;
        }

        public Criteria andIsCoreNotEqualTo(Integer value) {
            addCriterion("is_core <>", value, "isCore");
            return (Criteria) this;
        }

        public Criteria andIsCoreGreaterThan(Integer value) {
            addCriterion("is_core >", value, "isCore");
            return (Criteria) this;
        }

        public Criteria andIsCoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_core >=", value, "isCore");
            return (Criteria) this;
        }

        public Criteria andIsCoreLessThan(Integer value) {
            addCriterion("is_core <", value, "isCore");
            return (Criteria) this;
        }

        public Criteria andIsCoreLessThanOrEqualTo(Integer value) {
            addCriterion("is_core <=", value, "isCore");
            return (Criteria) this;
        }

        public Criteria andIsCoreIn(List<Integer> values) {
            addCriterion("is_core in", values, "isCore");
            return (Criteria) this;
        }

        public Criteria andIsCoreNotIn(List<Integer> values) {
            addCriterion("is_core not in", values, "isCore");
            return (Criteria) this;
        }

        public Criteria andIsCoreBetween(Integer value1, Integer value2) {
            addCriterion("is_core between", value1, value2, "isCore");
            return (Criteria) this;
        }

        public Criteria andIsCoreNotBetween(Integer value1, Integer value2) {
            addCriterion("is_core not between", value1, value2, "isCore");
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