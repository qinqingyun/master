package com.meituan.food.po;

import java.util.ArrayList;
import java.util.List;

public class OrgMcdIdPOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OrgMcdIdPOExample() {
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

        public Criteria andOrgIdIsNull() {
            addCriterion("org_id is null");
            return (Criteria) this;
        }

        public Criteria andOrgIdIsNotNull() {
            addCriterion("org_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrgIdEqualTo(Integer value) {
            addCriterion("org_id =", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdNotEqualTo(Integer value) {
            addCriterion("org_id <>", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdGreaterThan(Integer value) {
            addCriterion("org_id >", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("org_id >=", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdLessThan(Integer value) {
            addCriterion("org_id <", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdLessThanOrEqualTo(Integer value) {
            addCriterion("org_id <=", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdIn(List<Integer> values) {
            addCriterion("org_id in", values, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdNotIn(List<Integer> values) {
            addCriterion("org_id not in", values, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdBetween(Integer value1, Integer value2) {
            addCriterion("org_id between", value1, value2, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdNotBetween(Integer value1, Integer value2) {
            addCriterion("org_id not between", value1, value2, "orgId");
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

        public Criteria andMcdIdIsNull() {
            addCriterion("mcd_id is null");
            return (Criteria) this;
        }

        public Criteria andMcdIdIsNotNull() {
            addCriterion("mcd_id is not null");
            return (Criteria) this;
        }

        public Criteria andMcdIdEqualTo(String value) {
            addCriterion("mcd_id =", value, "mcdId");
            return (Criteria) this;
        }

        public Criteria andMcdIdNotEqualTo(String value) {
            addCriterion("mcd_id <>", value, "mcdId");
            return (Criteria) this;
        }

        public Criteria andMcdIdGreaterThan(String value) {
            addCriterion("mcd_id >", value, "mcdId");
            return (Criteria) this;
        }

        public Criteria andMcdIdGreaterThanOrEqualTo(String value) {
            addCriterion("mcd_id >=", value, "mcdId");
            return (Criteria) this;
        }

        public Criteria andMcdIdLessThan(String value) {
            addCriterion("mcd_id <", value, "mcdId");
            return (Criteria) this;
        }

        public Criteria andMcdIdLessThanOrEqualTo(String value) {
            addCriterion("mcd_id <=", value, "mcdId");
            return (Criteria) this;
        }

        public Criteria andMcdIdLike(String value) {
            addCriterion("mcd_id like", value, "mcdId");
            return (Criteria) this;
        }

        public Criteria andMcdIdNotLike(String value) {
            addCriterion("mcd_id not like", value, "mcdId");
            return (Criteria) this;
        }

        public Criteria andMcdIdIn(List<String> values) {
            addCriterion("mcd_id in", values, "mcdId");
            return (Criteria) this;
        }

        public Criteria andMcdIdNotIn(List<String> values) {
            addCriterion("mcd_id not in", values, "mcdId");
            return (Criteria) this;
        }

        public Criteria andMcdIdBetween(String value1, String value2) {
            addCriterion("mcd_id between", value1, value2, "mcdId");
            return (Criteria) this;
        }

        public Criteria andMcdIdNotBetween(String value1, String value2) {
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

        public Criteria andChildMcdIdIsNull() {
            addCriterion("child_mcd_id is null");
            return (Criteria) this;
        }

        public Criteria andChildMcdIdIsNotNull() {
            addCriterion("child_mcd_id is not null");
            return (Criteria) this;
        }

        public Criteria andChildMcdIdEqualTo(String value) {
            addCriterion("child_mcd_id =", value, "childMcdId");
            return (Criteria) this;
        }

        public Criteria andChildMcdIdNotEqualTo(String value) {
            addCriterion("child_mcd_id <>", value, "childMcdId");
            return (Criteria) this;
        }

        public Criteria andChildMcdIdGreaterThan(String value) {
            addCriterion("child_mcd_id >", value, "childMcdId");
            return (Criteria) this;
        }

        public Criteria andChildMcdIdGreaterThanOrEqualTo(String value) {
            addCriterion("child_mcd_id >=", value, "childMcdId");
            return (Criteria) this;
        }

        public Criteria andChildMcdIdLessThan(String value) {
            addCriterion("child_mcd_id <", value, "childMcdId");
            return (Criteria) this;
        }

        public Criteria andChildMcdIdLessThanOrEqualTo(String value) {
            addCriterion("child_mcd_id <=", value, "childMcdId");
            return (Criteria) this;
        }

        public Criteria andChildMcdIdLike(String value) {
            addCriterion("child_mcd_id like", value, "childMcdId");
            return (Criteria) this;
        }

        public Criteria andChildMcdIdNotLike(String value) {
            addCriterion("child_mcd_id not like", value, "childMcdId");
            return (Criteria) this;
        }

        public Criteria andChildMcdIdIn(List<String> values) {
            addCriterion("child_mcd_id in", values, "childMcdId");
            return (Criteria) this;
        }

        public Criteria andChildMcdIdNotIn(List<String> values) {
            addCriterion("child_mcd_id not in", values, "childMcdId");
            return (Criteria) this;
        }

        public Criteria andChildMcdIdBetween(String value1, String value2) {
            addCriterion("child_mcd_id between", value1, value2, "childMcdId");
            return (Criteria) this;
        }

        public Criteria andChildMcdIdNotBetween(String value1, String value2) {
            addCriterion("child_mcd_id not between", value1, value2, "childMcdId");
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