package com.meituan.food.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReleaseNamePOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ReleaseNamePOExample() {
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

        public Criteria andReleaseNameIsNull() {
            addCriterion("release_name is null");
            return (Criteria) this;
        }

        public Criteria andReleaseNameIsNotNull() {
            addCriterion("release_name is not null");
            return (Criteria) this;
        }

        public Criteria andReleaseNameEqualTo(String value) {
            addCriterion("release_name =", value, "releaseName");
            return (Criteria) this;
        }

        public Criteria andReleaseNameNotEqualTo(String value) {
            addCriterion("release_name <>", value, "releaseName");
            return (Criteria) this;
        }

        public Criteria andReleaseNameGreaterThan(String value) {
            addCriterion("release_name >", value, "releaseName");
            return (Criteria) this;
        }

        public Criteria andReleaseNameGreaterThanOrEqualTo(String value) {
            addCriterion("release_name >=", value, "releaseName");
            return (Criteria) this;
        }

        public Criteria andReleaseNameLessThan(String value) {
            addCriterion("release_name <", value, "releaseName");
            return (Criteria) this;
        }

        public Criteria andReleaseNameLessThanOrEqualTo(String value) {
            addCriterion("release_name <=", value, "releaseName");
            return (Criteria) this;
        }

        public Criteria andReleaseNameLike(String value) {
            addCriterion("release_name like", value, "releaseName");
            return (Criteria) this;
        }

        public Criteria andReleaseNameNotLike(String value) {
            addCriterion("release_name not like", value, "releaseName");
            return (Criteria) this;
        }

        public Criteria andReleaseNameIn(List<String> values) {
            addCriterion("release_name in", values, "releaseName");
            return (Criteria) this;
        }

        public Criteria andReleaseNameNotIn(List<String> values) {
            addCriterion("release_name not in", values, "releaseName");
            return (Criteria) this;
        }

        public Criteria andReleaseNameBetween(String value1, String value2) {
            addCriterion("release_name between", value1, value2, "releaseName");
            return (Criteria) this;
        }

        public Criteria andReleaseNameNotBetween(String value1, String value2) {
            addCriterion("release_name not between", value1, value2, "releaseName");
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

        public Criteria andUpdatedTimeIsNull() {
            addCriterion("updated_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeIsNotNull() {
            addCriterion("updated_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeEqualTo(Date value) {
            addCriterion("updated_time =", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeNotEqualTo(Date value) {
            addCriterion("updated_time <>", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeGreaterThan(Date value) {
            addCriterion("updated_time >", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("updated_time >=", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeLessThan(Date value) {
            addCriterion("updated_time <", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeLessThanOrEqualTo(Date value) {
            addCriterion("updated_time <=", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeIn(List<Date> values) {
            addCriterion("updated_time in", values, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeNotIn(List<Date> values) {
            addCriterion("updated_time not in", values, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeBetween(Date value1, Date value2) {
            addCriterion("updated_time between", value1, value2, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeNotBetween(Date value1, Date value2) {
            addCriterion("updated_time not between", value1, value2, "updatedTime");
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