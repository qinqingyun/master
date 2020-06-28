package com.meituan.food.po;

import java.util.ArrayList;
import java.util.List;

public class McdInfoPOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public McdInfoPOExample() {
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

        public Criteria andMcdNameEqualTo(String value) {
            addCriterion("mcd_name =", value, "mcdName");
            return (Criteria) this;
        }

        public Criteria andMcdNameNotEqualTo(String value) {
            addCriterion("mcd_name <>", value, "mcdName");
            return (Criteria) this;
        }

        public Criteria andMcdNameGreaterThan(String value) {
            addCriterion("mcd_name >", value, "mcdName");
            return (Criteria) this;
        }

        public Criteria andMcdNameGreaterThanOrEqualTo(String value) {
            addCriterion("mcd_name >=", value, "mcdName");
            return (Criteria) this;
        }

        public Criteria andMcdNameLessThan(String value) {
            addCriterion("mcd_name <", value, "mcdName");
            return (Criteria) this;
        }

        public Criteria andMcdNameLessThanOrEqualTo(String value) {
            addCriterion("mcd_name <=", value, "mcdName");
            return (Criteria) this;
        }

        public Criteria andMcdNameLike(String value) {
            addCriterion("mcd_name like", value, "mcdName");
            return (Criteria) this;
        }

        public Criteria andMcdNameNotLike(String value) {
            addCriterion("mcd_name not like", value, "mcdName");
            return (Criteria) this;
        }

        public Criteria andMcdNameIn(List<String> values) {
            addCriterion("mcd_name in", values, "mcdName");
            return (Criteria) this;
        }

        public Criteria andMcdNameNotIn(List<String> values) {
            addCriterion("mcd_name not in", values, "mcdName");
            return (Criteria) this;
        }

        public Criteria andMcdNameBetween(String value1, String value2) {
            addCriterion("mcd_name between", value1, value2, "mcdName");
            return (Criteria) this;
        }

        public Criteria andMcdNameNotBetween(String value1, String value2) {
            addCriterion("mcd_name not between", value1, value2, "mcdName");
            return (Criteria) this;
        }

        public Criteria andMcdFatherIdIsNull() {
            addCriterion("mcd_father_id is null");
            return (Criteria) this;
        }

        public Criteria andMcdFatherIdIsNotNull() {
            addCriterion("mcd_father_id is not null");
            return (Criteria) this;
        }

        public Criteria andMcdFatherIdEqualTo(Integer value) {
            addCriterion("mcd_father_id =", value, "mcdFatherId");
            return (Criteria) this;
        }

        public Criteria andMcdFatherIdNotEqualTo(Integer value) {
            addCriterion("mcd_father_id <>", value, "mcdFatherId");
            return (Criteria) this;
        }

        public Criteria andMcdFatherIdGreaterThan(Integer value) {
            addCriterion("mcd_father_id >", value, "mcdFatherId");
            return (Criteria) this;
        }

        public Criteria andMcdFatherIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("mcd_father_id >=", value, "mcdFatherId");
            return (Criteria) this;
        }

        public Criteria andMcdFatherIdLessThan(Integer value) {
            addCriterion("mcd_father_id <", value, "mcdFatherId");
            return (Criteria) this;
        }

        public Criteria andMcdFatherIdLessThanOrEqualTo(Integer value) {
            addCriterion("mcd_father_id <=", value, "mcdFatherId");
            return (Criteria) this;
        }

        public Criteria andMcdFatherIdIn(List<Integer> values) {
            addCriterion("mcd_father_id in", values, "mcdFatherId");
            return (Criteria) this;
        }

        public Criteria andMcdFatherIdNotIn(List<Integer> values) {
            addCriterion("mcd_father_id not in", values, "mcdFatherId");
            return (Criteria) this;
        }

        public Criteria andMcdFatherIdBetween(Integer value1, Integer value2) {
            addCriterion("mcd_father_id between", value1, value2, "mcdFatherId");
            return (Criteria) this;
        }

        public Criteria andMcdFatherIdNotBetween(Integer value1, Integer value2) {
            addCriterion("mcd_father_id not between", value1, value2, "mcdFatherId");
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