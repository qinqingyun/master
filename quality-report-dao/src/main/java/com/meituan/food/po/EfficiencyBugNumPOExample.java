package com.meituan.food.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EfficiencyBugNumPOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EfficiencyBugNumPOExample() {
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

        public Criteria andMisIsNull() {
            addCriterion("mis is null");
            return (Criteria) this;
        }

        public Criteria andMisIsNotNull() {
            addCriterion("mis is not null");
            return (Criteria) this;
        }

        public Criteria andMisEqualTo(String value) {
            addCriterion("mis =", value, "mis");
            return (Criteria) this;
        }

        public Criteria andMisNotEqualTo(String value) {
            addCriterion("mis <>", value, "mis");
            return (Criteria) this;
        }

        public Criteria andMisGreaterThan(String value) {
            addCriterion("mis >", value, "mis");
            return (Criteria) this;
        }

        public Criteria andMisGreaterThanOrEqualTo(String value) {
            addCriterion("mis >=", value, "mis");
            return (Criteria) this;
        }

        public Criteria andMisLessThan(String value) {
            addCriterion("mis <", value, "mis");
            return (Criteria) this;
        }

        public Criteria andMisLessThanOrEqualTo(String value) {
            addCriterion("mis <=", value, "mis");
            return (Criteria) this;
        }

        public Criteria andMisLike(String value) {
            addCriterion("mis like", value, "mis");
            return (Criteria) this;
        }

        public Criteria andMisNotLike(String value) {
            addCriterion("mis not like", value, "mis");
            return (Criteria) this;
        }

        public Criteria andMisIn(List<String> values) {
            addCriterion("mis in", values, "mis");
            return (Criteria) this;
        }

        public Criteria andMisNotIn(List<String> values) {
            addCriterion("mis not in", values, "mis");
            return (Criteria) this;
        }

        public Criteria andMisBetween(String value1, String value2) {
            addCriterion("mis between", value1, value2, "mis");
            return (Criteria) this;
        }

        public Criteria andMisNotBetween(String value1, String value2) {
            addCriterion("mis not between", value1, value2, "mis");
            return (Criteria) this;
        }

        public Criteria andCreateNumIsNull() {
            addCriterion("create_num is null");
            return (Criteria) this;
        }

        public Criteria andCreateNumIsNotNull() {
            addCriterion("create_num is not null");
            return (Criteria) this;
        }

        public Criteria andCreateNumEqualTo(Integer value) {
            addCriterion("create_num =", value, "createNum");
            return (Criteria) this;
        }

        public Criteria andCreateNumNotEqualTo(Integer value) {
            addCriterion("create_num <>", value, "createNum");
            return (Criteria) this;
        }

        public Criteria andCreateNumGreaterThan(Integer value) {
            addCriterion("create_num >", value, "createNum");
            return (Criteria) this;
        }

        public Criteria andCreateNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("create_num >=", value, "createNum");
            return (Criteria) this;
        }

        public Criteria andCreateNumLessThan(Integer value) {
            addCriterion("create_num <", value, "createNum");
            return (Criteria) this;
        }

        public Criteria andCreateNumLessThanOrEqualTo(Integer value) {
            addCriterion("create_num <=", value, "createNum");
            return (Criteria) this;
        }

        public Criteria andCreateNumIn(List<Integer> values) {
            addCriterion("create_num in", values, "createNum");
            return (Criteria) this;
        }

        public Criteria andCreateNumNotIn(List<Integer> values) {
            addCriterion("create_num not in", values, "createNum");
            return (Criteria) this;
        }

        public Criteria andCreateNumBetween(Integer value1, Integer value2) {
            addCriterion("create_num between", value1, value2, "createNum");
            return (Criteria) this;
        }

        public Criteria andCreateNumNotBetween(Integer value1, Integer value2) {
            addCriterion("create_num not between", value1, value2, "createNum");
            return (Criteria) this;
        }

        public Criteria andAcceptNumIsNull() {
            addCriterion("accept_num is null");
            return (Criteria) this;
        }

        public Criteria andAcceptNumIsNotNull() {
            addCriterion("accept_num is not null");
            return (Criteria) this;
        }

        public Criteria andAcceptNumEqualTo(Integer value) {
            addCriterion("accept_num =", value, "acceptNum");
            return (Criteria) this;
        }

        public Criteria andAcceptNumNotEqualTo(Integer value) {
            addCriterion("accept_num <>", value, "acceptNum");
            return (Criteria) this;
        }

        public Criteria andAcceptNumGreaterThan(Integer value) {
            addCriterion("accept_num >", value, "acceptNum");
            return (Criteria) this;
        }

        public Criteria andAcceptNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("accept_num >=", value, "acceptNum");
            return (Criteria) this;
        }

        public Criteria andAcceptNumLessThan(Integer value) {
            addCriterion("accept_num <", value, "acceptNum");
            return (Criteria) this;
        }

        public Criteria andAcceptNumLessThanOrEqualTo(Integer value) {
            addCriterion("accept_num <=", value, "acceptNum");
            return (Criteria) this;
        }

        public Criteria andAcceptNumIn(List<Integer> values) {
            addCriterion("accept_num in", values, "acceptNum");
            return (Criteria) this;
        }

        public Criteria andAcceptNumNotIn(List<Integer> values) {
            addCriterion("accept_num not in", values, "acceptNum");
            return (Criteria) this;
        }

        public Criteria andAcceptNumBetween(Integer value1, Integer value2) {
            addCriterion("accept_num between", value1, value2, "acceptNum");
            return (Criteria) this;
        }

        public Criteria andAcceptNumNotBetween(Integer value1, Integer value2) {
            addCriterion("accept_num not between", value1, value2, "acceptNum");
            return (Criteria) this;
        }

        public Criteria andEfficiencyDateIsNull() {
            addCriterion("efficiency_date is null");
            return (Criteria) this;
        }

        public Criteria andEfficiencyDateIsNotNull() {
            addCriterion("efficiency_date is not null");
            return (Criteria) this;
        }

        public Criteria andEfficiencyDateEqualTo(String value) {
            addCriterion("efficiency_date =", value, "efficiencyDate");
            return (Criteria) this;
        }

        public Criteria andEfficiencyDateNotEqualTo(String value) {
            addCriterion("efficiency_date <>", value, "efficiencyDate");
            return (Criteria) this;
        }

        public Criteria andEfficiencyDateGreaterThan(String value) {
            addCriterion("efficiency_date >", value, "efficiencyDate");
            return (Criteria) this;
        }

        public Criteria andEfficiencyDateGreaterThanOrEqualTo(String value) {
            addCriterion("efficiency_date >=", value, "efficiencyDate");
            return (Criteria) this;
        }

        public Criteria andEfficiencyDateLessThan(String value) {
            addCriterion("efficiency_date <", value, "efficiencyDate");
            return (Criteria) this;
        }

        public Criteria andEfficiencyDateLessThanOrEqualTo(String value) {
            addCriterion("efficiency_date <=", value, "efficiencyDate");
            return (Criteria) this;
        }

        public Criteria andEfficiencyDateLike(String value) {
            addCriterion("efficiency_date like", value, "efficiencyDate");
            return (Criteria) this;
        }

        public Criteria andEfficiencyDateNotLike(String value) {
            addCriterion("efficiency_date not like", value, "efficiencyDate");
            return (Criteria) this;
        }

        public Criteria andEfficiencyDateIn(List<String> values) {
            addCriterion("efficiency_date in", values, "efficiencyDate");
            return (Criteria) this;
        }

        public Criteria andEfficiencyDateNotIn(List<String> values) {
            addCriterion("efficiency_date not in", values, "efficiencyDate");
            return (Criteria) this;
        }

        public Criteria andEfficiencyDateBetween(String value1, String value2) {
            addCriterion("efficiency_date between", value1, value2, "efficiencyDate");
            return (Criteria) this;
        }

        public Criteria andEfficiencyDateNotBetween(String value1, String value2) {
            addCriterion("efficiency_date not between", value1, value2, "efficiencyDate");
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