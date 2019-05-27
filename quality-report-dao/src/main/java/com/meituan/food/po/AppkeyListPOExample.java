package com.meituan.food.po;

import java.util.ArrayList;
import java.util.List;

public class AppkeyListPOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AppkeyListPOExample() {
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

        public Criteria andOwtIsNull() {
            addCriterion("owt is null");
            return (Criteria) this;
        }

        public Criteria andOwtIsNotNull() {
            addCriterion("owt is not null");
            return (Criteria) this;
        }

        public Criteria andOwtEqualTo(String value) {
            addCriterion("owt =", value, "owt");
            return (Criteria) this;
        }

        public Criteria andOwtNotEqualTo(String value) {
            addCriterion("owt <>", value, "owt");
            return (Criteria) this;
        }

        public Criteria andOwtGreaterThan(String value) {
            addCriterion("owt >", value, "owt");
            return (Criteria) this;
        }

        public Criteria andOwtGreaterThanOrEqualTo(String value) {
            addCriterion("owt >=", value, "owt");
            return (Criteria) this;
        }

        public Criteria andOwtLessThan(String value) {
            addCriterion("owt <", value, "owt");
            return (Criteria) this;
        }

        public Criteria andOwtLessThanOrEqualTo(String value) {
            addCriterion("owt <=", value, "owt");
            return (Criteria) this;
        }

        public Criteria andOwtLike(String value) {
            addCriterion("owt like", value, "owt");
            return (Criteria) this;
        }

        public Criteria andOwtNotLike(String value) {
            addCriterion("owt not like", value, "owt");
            return (Criteria) this;
        }

        public Criteria andOwtIn(List<String> values) {
            addCriterion("owt in", values, "owt");
            return (Criteria) this;
        }

        public Criteria andOwtNotIn(List<String> values) {
            addCriterion("owt not in", values, "owt");
            return (Criteria) this;
        }

        public Criteria andOwtBetween(String value1, String value2) {
            addCriterion("owt between", value1, value2, "owt");
            return (Criteria) this;
        }

        public Criteria andOwtNotBetween(String value1, String value2) {
            addCriterion("owt not between", value1, value2, "owt");
            return (Criteria) this;
        }

        public Criteria andPdlIsNull() {
            addCriterion("pdl is null");
            return (Criteria) this;
        }

        public Criteria andPdlIsNotNull() {
            addCriterion("pdl is not null");
            return (Criteria) this;
        }

        public Criteria andPdlEqualTo(String value) {
            addCriterion("pdl =", value, "pdl");
            return (Criteria) this;
        }

        public Criteria andPdlNotEqualTo(String value) {
            addCriterion("pdl <>", value, "pdl");
            return (Criteria) this;
        }

        public Criteria andPdlGreaterThan(String value) {
            addCriterion("pdl >", value, "pdl");
            return (Criteria) this;
        }

        public Criteria andPdlGreaterThanOrEqualTo(String value) {
            addCriterion("pdl >=", value, "pdl");
            return (Criteria) this;
        }

        public Criteria andPdlLessThan(String value) {
            addCriterion("pdl <", value, "pdl");
            return (Criteria) this;
        }

        public Criteria andPdlLessThanOrEqualTo(String value) {
            addCriterion("pdl <=", value, "pdl");
            return (Criteria) this;
        }

        public Criteria andPdlLike(String value) {
            addCriterion("pdl like", value, "pdl");
            return (Criteria) this;
        }

        public Criteria andPdlNotLike(String value) {
            addCriterion("pdl not like", value, "pdl");
            return (Criteria) this;
        }

        public Criteria andPdlIn(List<String> values) {
            addCriterion("pdl in", values, "pdl");
            return (Criteria) this;
        }

        public Criteria andPdlNotIn(List<String> values) {
            addCriterion("pdl not in", values, "pdl");
            return (Criteria) this;
        }

        public Criteria andPdlBetween(String value1, String value2) {
            addCriterion("pdl between", value1, value2, "pdl");
            return (Criteria) this;
        }

        public Criteria andPdlNotBetween(String value1, String value2) {
            addCriterion("pdl not between", value1, value2, "pdl");
            return (Criteria) this;
        }

        public Criteria andSrvIsNull() {
            addCriterion("srv is null");
            return (Criteria) this;
        }

        public Criteria andSrvIsNotNull() {
            addCriterion("srv is not null");
            return (Criteria) this;
        }

        public Criteria andSrvEqualTo(String value) {
            addCriterion("srv =", value, "srv");
            return (Criteria) this;
        }

        public Criteria andSrvNotEqualTo(String value) {
            addCriterion("srv <>", value, "srv");
            return (Criteria) this;
        }

        public Criteria andSrvGreaterThan(String value) {
            addCriterion("srv >", value, "srv");
            return (Criteria) this;
        }

        public Criteria andSrvGreaterThanOrEqualTo(String value) {
            addCriterion("srv >=", value, "srv");
            return (Criteria) this;
        }

        public Criteria andSrvLessThan(String value) {
            addCriterion("srv <", value, "srv");
            return (Criteria) this;
        }

        public Criteria andSrvLessThanOrEqualTo(String value) {
            addCriterion("srv <=", value, "srv");
            return (Criteria) this;
        }

        public Criteria andSrvLike(String value) {
            addCriterion("srv like", value, "srv");
            return (Criteria) this;
        }

        public Criteria andSrvNotLike(String value) {
            addCriterion("srv not like", value, "srv");
            return (Criteria) this;
        }

        public Criteria andSrvIn(List<String> values) {
            addCriterion("srv in", values, "srv");
            return (Criteria) this;
        }

        public Criteria andSrvNotIn(List<String> values) {
            addCriterion("srv not in", values, "srv");
            return (Criteria) this;
        }

        public Criteria andSrvBetween(String value1, String value2) {
            addCriterion("srv between", value1, value2, "srv");
            return (Criteria) this;
        }

        public Criteria andSrvNotBetween(String value1, String value2) {
            addCriterion("srv not between", value1, value2, "srv");
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

        public Criteria andDepartmentIdIsNull() {
            addCriterion("department_id is null");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdIsNotNull() {
            addCriterion("department_id is not null");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdEqualTo(Integer value) {
            addCriterion("department_id =", value, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdNotEqualTo(Integer value) {
            addCriterion("department_id <>", value, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdGreaterThan(Integer value) {
            addCriterion("department_id >", value, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("department_id >=", value, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdLessThan(Integer value) {
            addCriterion("department_id <", value, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdLessThanOrEqualTo(Integer value) {
            addCriterion("department_id <=", value, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdIn(List<Integer> values) {
            addCriterion("department_id in", values, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdNotIn(List<Integer> values) {
            addCriterion("department_id not in", values, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdBetween(Integer value1, Integer value2) {
            addCriterion("department_id between", value1, value2, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdNotBetween(Integer value1, Integer value2) {
            addCriterion("department_id not between", value1, value2, "departmentId");
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