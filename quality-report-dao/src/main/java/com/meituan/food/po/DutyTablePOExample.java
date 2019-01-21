package com.meituan.food.po;

import java.util.ArrayList;
import java.util.List;

public class DutyTablePOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DutyTablePOExample() {
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

        public Criteria andDutyNameIsNull() {
            addCriterion("duty_name is null");
            return (Criteria) this;
        }

        public Criteria andDutyNameIsNotNull() {
            addCriterion("duty_name is not null");
            return (Criteria) this;
        }

        public Criteria andDutyNameEqualTo(String value) {
            addCriterion("duty_name =", value, "dutyName");
            return (Criteria) this;
        }

        public Criteria andDutyNameNotEqualTo(String value) {
            addCriterion("duty_name <>", value, "dutyName");
            return (Criteria) this;
        }

        public Criteria andDutyNameGreaterThan(String value) {
            addCriterion("duty_name >", value, "dutyName");
            return (Criteria) this;
        }

        public Criteria andDutyNameGreaterThanOrEqualTo(String value) {
            addCriterion("duty_name >=", value, "dutyName");
            return (Criteria) this;
        }

        public Criteria andDutyNameLessThan(String value) {
            addCriterion("duty_name <", value, "dutyName");
            return (Criteria) this;
        }

        public Criteria andDutyNameLessThanOrEqualTo(String value) {
            addCriterion("duty_name <=", value, "dutyName");
            return (Criteria) this;
        }

        public Criteria andDutyNameLike(String value) {
            addCriterion("duty_name like", value, "dutyName");
            return (Criteria) this;
        }

        public Criteria andDutyNameNotLike(String value) {
            addCriterion("duty_name not like", value, "dutyName");
            return (Criteria) this;
        }

        public Criteria andDutyNameIn(List<String> values) {
            addCriterion("duty_name in", values, "dutyName");
            return (Criteria) this;
        }

        public Criteria andDutyNameNotIn(List<String> values) {
            addCriterion("duty_name not in", values, "dutyName");
            return (Criteria) this;
        }

        public Criteria andDutyNameBetween(String value1, String value2) {
            addCriterion("duty_name between", value1, value2, "dutyName");
            return (Criteria) this;
        }

        public Criteria andDutyNameNotBetween(String value1, String value2) {
            addCriterion("duty_name not between", value1, value2, "dutyName");
            return (Criteria) this;
        }

        public Criteria andDutyMisIsNull() {
            addCriterion("duty_mis is null");
            return (Criteria) this;
        }

        public Criteria andDutyMisIsNotNull() {
            addCriterion("duty_mis is not null");
            return (Criteria) this;
        }

        public Criteria andDutyMisEqualTo(String value) {
            addCriterion("duty_mis =", value, "dutyMis");
            return (Criteria) this;
        }

        public Criteria andDutyMisNotEqualTo(String value) {
            addCriterion("duty_mis <>", value, "dutyMis");
            return (Criteria) this;
        }

        public Criteria andDutyMisGreaterThan(String value) {
            addCriterion("duty_mis >", value, "dutyMis");
            return (Criteria) this;
        }

        public Criteria andDutyMisGreaterThanOrEqualTo(String value) {
            addCriterion("duty_mis >=", value, "dutyMis");
            return (Criteria) this;
        }

        public Criteria andDutyMisLessThan(String value) {
            addCriterion("duty_mis <", value, "dutyMis");
            return (Criteria) this;
        }

        public Criteria andDutyMisLessThanOrEqualTo(String value) {
            addCriterion("duty_mis <=", value, "dutyMis");
            return (Criteria) this;
        }

        public Criteria andDutyMisLike(String value) {
            addCriterion("duty_mis like", value, "dutyMis");
            return (Criteria) this;
        }

        public Criteria andDutyMisNotLike(String value) {
            addCriterion("duty_mis not like", value, "dutyMis");
            return (Criteria) this;
        }

        public Criteria andDutyMisIn(List<String> values) {
            addCriterion("duty_mis in", values, "dutyMis");
            return (Criteria) this;
        }

        public Criteria andDutyMisNotIn(List<String> values) {
            addCriterion("duty_mis not in", values, "dutyMis");
            return (Criteria) this;
        }

        public Criteria andDutyMisBetween(String value1, String value2) {
            addCriterion("duty_mis between", value1, value2, "dutyMis");
            return (Criteria) this;
        }

        public Criteria andDutyMisNotBetween(String value1, String value2) {
            addCriterion("duty_mis not between", value1, value2, "dutyMis");
            return (Criteria) this;
        }

        public Criteria andIsOnDutyIsNull() {
            addCriterion("is_on_duty is null");
            return (Criteria) this;
        }

        public Criteria andIsOnDutyIsNotNull() {
            addCriterion("is_on_duty is not null");
            return (Criteria) this;
        }

        public Criteria andIsOnDutyEqualTo(Boolean value) {
            addCriterion("is_on_duty =", value, "isOnDuty");
            return (Criteria) this;
        }

        public Criteria andIsOnDutyNotEqualTo(Boolean value) {
            addCriterion("is_on_duty <>", value, "isOnDuty");
            return (Criteria) this;
        }

        public Criteria andIsOnDutyGreaterThan(Boolean value) {
            addCriterion("is_on_duty >", value, "isOnDuty");
            return (Criteria) this;
        }

        public Criteria andIsOnDutyGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_on_duty >=", value, "isOnDuty");
            return (Criteria) this;
        }

        public Criteria andIsOnDutyLessThan(Boolean value) {
            addCriterion("is_on_duty <", value, "isOnDuty");
            return (Criteria) this;
        }

        public Criteria andIsOnDutyLessThanOrEqualTo(Boolean value) {
            addCriterion("is_on_duty <=", value, "isOnDuty");
            return (Criteria) this;
        }

        public Criteria andIsOnDutyIn(List<Boolean> values) {
            addCriterion("is_on_duty in", values, "isOnDuty");
            return (Criteria) this;
        }

        public Criteria andIsOnDutyNotIn(List<Boolean> values) {
            addCriterion("is_on_duty not in", values, "isOnDuty");
            return (Criteria) this;
        }

        public Criteria andIsOnDutyBetween(Boolean value1, Boolean value2) {
            addCriterion("is_on_duty between", value1, value2, "isOnDuty");
            return (Criteria) this;
        }

        public Criteria andIsOnDutyNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_on_duty not between", value1, value2, "isOnDuty");
            return (Criteria) this;
        }

        public Criteria andDutyDateIsNull() {
            addCriterion("duty_date is null");
            return (Criteria) this;
        }

        public Criteria andDutyDateIsNotNull() {
            addCriterion("duty_date is not null");
            return (Criteria) this;
        }

        public Criteria andDutyDateEqualTo(String value) {
            addCriterion("duty_date =", value, "dutyDate");
            return (Criteria) this;
        }

        public Criteria andDutyDateNotEqualTo(String value) {
            addCriterion("duty_date <>", value, "dutyDate");
            return (Criteria) this;
        }

        public Criteria andDutyDateGreaterThan(String value) {
            addCriterion("duty_date >", value, "dutyDate");
            return (Criteria) this;
        }

        public Criteria andDutyDateGreaterThanOrEqualTo(String value) {
            addCriterion("duty_date >=", value, "dutyDate");
            return (Criteria) this;
        }

        public Criteria andDutyDateLessThan(String value) {
            addCriterion("duty_date <", value, "dutyDate");
            return (Criteria) this;
        }

        public Criteria andDutyDateLessThanOrEqualTo(String value) {
            addCriterion("duty_date <=", value, "dutyDate");
            return (Criteria) this;
        }

        public Criteria andDutyDateLike(String value) {
            addCriterion("duty_date like", value, "dutyDate");
            return (Criteria) this;
        }

        public Criteria andDutyDateNotLike(String value) {
            addCriterion("duty_date not like", value, "dutyDate");
            return (Criteria) this;
        }

        public Criteria andDutyDateIn(List<String> values) {
            addCriterion("duty_date in", values, "dutyDate");
            return (Criteria) this;
        }

        public Criteria andDutyDateNotIn(List<String> values) {
            addCriterion("duty_date not in", values, "dutyDate");
            return (Criteria) this;
        }

        public Criteria andDutyDateBetween(String value1, String value2) {
            addCriterion("duty_date between", value1, value2, "dutyDate");
            return (Criteria) this;
        }

        public Criteria andDutyDateNotBetween(String value1, String value2) {
            addCriterion("duty_date not between", value1, value2, "dutyDate");
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