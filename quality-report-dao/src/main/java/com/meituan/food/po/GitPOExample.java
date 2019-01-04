package com.meituan.food.po;

import java.util.ArrayList;
import java.util.List;

public class GitPOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GitPOExample() {
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

        public Criteria andMisidIsNull() {
            addCriterion("misid is null");
            return (Criteria) this;
        }

        public Criteria andMisidIsNotNull() {
            addCriterion("misid is not null");
            return (Criteria) this;
        }

        public Criteria andMisidEqualTo(String value) {
            addCriterion("misid =", value, "misid");
            return (Criteria) this;
        }

        public Criteria andMisidNotEqualTo(String value) {
            addCriterion("misid <>", value, "misid");
            return (Criteria) this;
        }

        public Criteria andMisidGreaterThan(String value) {
            addCriterion("misid >", value, "misid");
            return (Criteria) this;
        }

        public Criteria andMisidGreaterThanOrEqualTo(String value) {
            addCriterion("misid >=", value, "misid");
            return (Criteria) this;
        }

        public Criteria andMisidLessThan(String value) {
            addCriterion("misid <", value, "misid");
            return (Criteria) this;
        }

        public Criteria andMisidLessThanOrEqualTo(String value) {
            addCriterion("misid <=", value, "misid");
            return (Criteria) this;
        }

        public Criteria andMisidLike(String value) {
            addCriterion("misid like", value, "misid");
            return (Criteria) this;
        }

        public Criteria andMisidNotLike(String value) {
            addCriterion("misid not like", value, "misid");
            return (Criteria) this;
        }

        public Criteria andMisidIn(List<String> values) {
            addCriterion("misid in", values, "misid");
            return (Criteria) this;
        }

        public Criteria andMisidNotIn(List<String> values) {
            addCriterion("misid not in", values, "misid");
            return (Criteria) this;
        }

        public Criteria andMisidBetween(String value1, String value2) {
            addCriterion("misid between", value1, value2, "misid");
            return (Criteria) this;
        }

        public Criteria andMisidNotBetween(String value1, String value2) {
            addCriterion("misid not between", value1, value2, "misid");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andGitDateIsNull() {
            addCriterion("git_date is null");
            return (Criteria) this;
        }

        public Criteria andGitDateIsNotNull() {
            addCriterion("git_date is not null");
            return (Criteria) this;
        }

        public Criteria andGitDateEqualTo(String value) {
            addCriterion("git_date =", value, "gitDate");
            return (Criteria) this;
        }

        public Criteria andGitDateNotEqualTo(String value) {
            addCriterion("git_date <>", value, "gitDate");
            return (Criteria) this;
        }

        public Criteria andGitDateGreaterThan(String value) {
            addCriterion("git_date >", value, "gitDate");
            return (Criteria) this;
        }

        public Criteria andGitDateGreaterThanOrEqualTo(String value) {
            addCriterion("git_date >=", value, "gitDate");
            return (Criteria) this;
        }

        public Criteria andGitDateLessThan(String value) {
            addCriterion("git_date <", value, "gitDate");
            return (Criteria) this;
        }

        public Criteria andGitDateLessThanOrEqualTo(String value) {
            addCriterion("git_date <=", value, "gitDate");
            return (Criteria) this;
        }

        public Criteria andGitDateLike(String value) {
            addCriterion("git_date like", value, "gitDate");
            return (Criteria) this;
        }

        public Criteria andGitDateNotLike(String value) {
            addCriterion("git_date not like", value, "gitDate");
            return (Criteria) this;
        }

        public Criteria andGitDateIn(List<String> values) {
            addCriterion("git_date in", values, "gitDate");
            return (Criteria) this;
        }

        public Criteria andGitDateNotIn(List<String> values) {
            addCriterion("git_date not in", values, "gitDate");
            return (Criteria) this;
        }

        public Criteria andGitDateBetween(String value1, String value2) {
            addCriterion("git_date between", value1, value2, "gitDate");
            return (Criteria) this;
        }

        public Criteria andGitDateNotBetween(String value1, String value2) {
            addCriterion("git_date not between", value1, value2, "gitDate");
            return (Criteria) this;
        }

        public Criteria andGitCodeIncreaseIsNull() {
            addCriterion("git_code_increase is null");
            return (Criteria) this;
        }

        public Criteria andGitCodeIncreaseIsNotNull() {
            addCriterion("git_code_increase is not null");
            return (Criteria) this;
        }

        public Criteria andGitCodeIncreaseEqualTo(Integer value) {
            addCriterion("git_code_increase =", value, "gitCodeIncrease");
            return (Criteria) this;
        }

        public Criteria andGitCodeIncreaseNotEqualTo(Integer value) {
            addCriterion("git_code_increase <>", value, "gitCodeIncrease");
            return (Criteria) this;
        }

        public Criteria andGitCodeIncreaseGreaterThan(Integer value) {
            addCriterion("git_code_increase >", value, "gitCodeIncrease");
            return (Criteria) this;
        }

        public Criteria andGitCodeIncreaseGreaterThanOrEqualTo(Integer value) {
            addCriterion("git_code_increase >=", value, "gitCodeIncrease");
            return (Criteria) this;
        }

        public Criteria andGitCodeIncreaseLessThan(Integer value) {
            addCriterion("git_code_increase <", value, "gitCodeIncrease");
            return (Criteria) this;
        }

        public Criteria andGitCodeIncreaseLessThanOrEqualTo(Integer value) {
            addCriterion("git_code_increase <=", value, "gitCodeIncrease");
            return (Criteria) this;
        }

        public Criteria andGitCodeIncreaseIn(List<Integer> values) {
            addCriterion("git_code_increase in", values, "gitCodeIncrease");
            return (Criteria) this;
        }

        public Criteria andGitCodeIncreaseNotIn(List<Integer> values) {
            addCriterion("git_code_increase not in", values, "gitCodeIncrease");
            return (Criteria) this;
        }

        public Criteria andGitCodeIncreaseBetween(Integer value1, Integer value2) {
            addCriterion("git_code_increase between", value1, value2, "gitCodeIncrease");
            return (Criteria) this;
        }

        public Criteria andGitCodeIncreaseNotBetween(Integer value1, Integer value2) {
            addCriterion("git_code_increase not between", value1, value2, "gitCodeIncrease");
            return (Criteria) this;
        }

        public Criteria andGitCodeDeleteIsNull() {
            addCriterion("git_code_delete is null");
            return (Criteria) this;
        }

        public Criteria andGitCodeDeleteIsNotNull() {
            addCriterion("git_code_delete is not null");
            return (Criteria) this;
        }

        public Criteria andGitCodeDeleteEqualTo(Integer value) {
            addCriterion("git_code_delete =", value, "gitCodeDelete");
            return (Criteria) this;
        }

        public Criteria andGitCodeDeleteNotEqualTo(Integer value) {
            addCriterion("git_code_delete <>", value, "gitCodeDelete");
            return (Criteria) this;
        }

        public Criteria andGitCodeDeleteGreaterThan(Integer value) {
            addCriterion("git_code_delete >", value, "gitCodeDelete");
            return (Criteria) this;
        }

        public Criteria andGitCodeDeleteGreaterThanOrEqualTo(Integer value) {
            addCriterion("git_code_delete >=", value, "gitCodeDelete");
            return (Criteria) this;
        }

        public Criteria andGitCodeDeleteLessThan(Integer value) {
            addCriterion("git_code_delete <", value, "gitCodeDelete");
            return (Criteria) this;
        }

        public Criteria andGitCodeDeleteLessThanOrEqualTo(Integer value) {
            addCriterion("git_code_delete <=", value, "gitCodeDelete");
            return (Criteria) this;
        }

        public Criteria andGitCodeDeleteIn(List<Integer> values) {
            addCriterion("git_code_delete in", values, "gitCodeDelete");
            return (Criteria) this;
        }

        public Criteria andGitCodeDeleteNotIn(List<Integer> values) {
            addCriterion("git_code_delete not in", values, "gitCodeDelete");
            return (Criteria) this;
        }

        public Criteria andGitCodeDeleteBetween(Integer value1, Integer value2) {
            addCriterion("git_code_delete between", value1, value2, "gitCodeDelete");
            return (Criteria) this;
        }

        public Criteria andGitCodeDeleteNotBetween(Integer value1, Integer value2) {
            addCriterion("git_code_delete not between", value1, value2, "gitCodeDelete");
            return (Criteria) this;
        }

        public Criteria andGitCodeSubmitIsNull() {
            addCriterion("git_code_submit is null");
            return (Criteria) this;
        }

        public Criteria andGitCodeSubmitIsNotNull() {
            addCriterion("git_code_submit is not null");
            return (Criteria) this;
        }

        public Criteria andGitCodeSubmitEqualTo(Integer value) {
            addCriterion("git_code_submit =", value, "gitCodeSubmit");
            return (Criteria) this;
        }

        public Criteria andGitCodeSubmitNotEqualTo(Integer value) {
            addCriterion("git_code_submit <>", value, "gitCodeSubmit");
            return (Criteria) this;
        }

        public Criteria andGitCodeSubmitGreaterThan(Integer value) {
            addCriterion("git_code_submit >", value, "gitCodeSubmit");
            return (Criteria) this;
        }

        public Criteria andGitCodeSubmitGreaterThanOrEqualTo(Integer value) {
            addCriterion("git_code_submit >=", value, "gitCodeSubmit");
            return (Criteria) this;
        }

        public Criteria andGitCodeSubmitLessThan(Integer value) {
            addCriterion("git_code_submit <", value, "gitCodeSubmit");
            return (Criteria) this;
        }

        public Criteria andGitCodeSubmitLessThanOrEqualTo(Integer value) {
            addCriterion("git_code_submit <=", value, "gitCodeSubmit");
            return (Criteria) this;
        }

        public Criteria andGitCodeSubmitIn(List<Integer> values) {
            addCriterion("git_code_submit in", values, "gitCodeSubmit");
            return (Criteria) this;
        }

        public Criteria andGitCodeSubmitNotIn(List<Integer> values) {
            addCriterion("git_code_submit not in", values, "gitCodeSubmit");
            return (Criteria) this;
        }

        public Criteria andGitCodeSubmitBetween(Integer value1, Integer value2) {
            addCriterion("git_code_submit between", value1, value2, "gitCodeSubmit");
            return (Criteria) this;
        }

        public Criteria andGitCodeSubmitNotBetween(Integer value1, Integer value2) {
            addCriterion("git_code_submit not between", value1, value2, "gitCodeSubmit");
            return (Criteria) this;
        }

        public Criteria andGitCodeSubmitTimeIsNull() {
            addCriterion("git_code_submit_time is null");
            return (Criteria) this;
        }

        public Criteria andGitCodeSubmitTimeIsNotNull() {
            addCriterion("git_code_submit_time is not null");
            return (Criteria) this;
        }

        public Criteria andGitCodeSubmitTimeEqualTo(Integer value) {
            addCriterion("git_code_submit_time =", value, "gitCodeSubmitTime");
            return (Criteria) this;
        }

        public Criteria andGitCodeSubmitTimeNotEqualTo(Integer value) {
            addCriterion("git_code_submit_time <>", value, "gitCodeSubmitTime");
            return (Criteria) this;
        }

        public Criteria andGitCodeSubmitTimeGreaterThan(Integer value) {
            addCriterion("git_code_submit_time >", value, "gitCodeSubmitTime");
            return (Criteria) this;
        }

        public Criteria andGitCodeSubmitTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("git_code_submit_time >=", value, "gitCodeSubmitTime");
            return (Criteria) this;
        }

        public Criteria andGitCodeSubmitTimeLessThan(Integer value) {
            addCriterion("git_code_submit_time <", value, "gitCodeSubmitTime");
            return (Criteria) this;
        }

        public Criteria andGitCodeSubmitTimeLessThanOrEqualTo(Integer value) {
            addCriterion("git_code_submit_time <=", value, "gitCodeSubmitTime");
            return (Criteria) this;
        }

        public Criteria andGitCodeSubmitTimeIn(List<Integer> values) {
            addCriterion("git_code_submit_time in", values, "gitCodeSubmitTime");
            return (Criteria) this;
        }

        public Criteria andGitCodeSubmitTimeNotIn(List<Integer> values) {
            addCriterion("git_code_submit_time not in", values, "gitCodeSubmitTime");
            return (Criteria) this;
        }

        public Criteria andGitCodeSubmitTimeBetween(Integer value1, Integer value2) {
            addCriterion("git_code_submit_time between", value1, value2, "gitCodeSubmitTime");
            return (Criteria) this;
        }

        public Criteria andGitCodeSubmitTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("git_code_submit_time not between", value1, value2, "gitCodeSubmitTime");
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