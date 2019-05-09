package com.meituan.food.po;

import java.util.ArrayList;
import java.util.List;

public class WeekBugTotalCountPOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public WeekBugTotalCountPOExample() {
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

        public Criteria andGroupNameIsNull() {
            addCriterion("group_name is null");
            return (Criteria) this;
        }

        public Criteria andGroupNameIsNotNull() {
            addCriterion("group_name is not null");
            return (Criteria) this;
        }

        public Criteria andGroupNameEqualTo(String value) {
            addCriterion("group_name =", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotEqualTo(String value) {
            addCriterion("group_name <>", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameGreaterThan(String value) {
            addCriterion("group_name >", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameGreaterThanOrEqualTo(String value) {
            addCriterion("group_name >=", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameLessThan(String value) {
            addCriterion("group_name <", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameLessThanOrEqualTo(String value) {
            addCriterion("group_name <=", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameLike(String value) {
            addCriterion("group_name like", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotLike(String value) {
            addCriterion("group_name not like", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameIn(List<String> values) {
            addCriterion("group_name in", values, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotIn(List<String> values) {
            addCriterion("group_name not in", values, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameBetween(String value1, String value2) {
            addCriterion("group_name between", value1, value2, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotBetween(String value1, String value2) {
            addCriterion("group_name not between", value1, value2, "groupName");
            return (Criteria) this;
        }

        public Criteria andMajorCountIsNull() {
            addCriterion("major_count is null");
            return (Criteria) this;
        }

        public Criteria andMajorCountIsNotNull() {
            addCriterion("major_count is not null");
            return (Criteria) this;
        }

        public Criteria andMajorCountEqualTo(Integer value) {
            addCriterion("major_count =", value, "majorCount");
            return (Criteria) this;
        }

        public Criteria andMajorCountNotEqualTo(Integer value) {
            addCriterion("major_count <>", value, "majorCount");
            return (Criteria) this;
        }

        public Criteria andMajorCountGreaterThan(Integer value) {
            addCriterion("major_count >", value, "majorCount");
            return (Criteria) this;
        }

        public Criteria andMajorCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("major_count >=", value, "majorCount");
            return (Criteria) this;
        }

        public Criteria andMajorCountLessThan(Integer value) {
            addCriterion("major_count <", value, "majorCount");
            return (Criteria) this;
        }

        public Criteria andMajorCountLessThanOrEqualTo(Integer value) {
            addCriterion("major_count <=", value, "majorCount");
            return (Criteria) this;
        }

        public Criteria andMajorCountIn(List<Integer> values) {
            addCriterion("major_count in", values, "majorCount");
            return (Criteria) this;
        }

        public Criteria andMajorCountNotIn(List<Integer> values) {
            addCriterion("major_count not in", values, "majorCount");
            return (Criteria) this;
        }

        public Criteria andMajorCountBetween(Integer value1, Integer value2) {
            addCriterion("major_count between", value1, value2, "majorCount");
            return (Criteria) this;
        }

        public Criteria andMajorCountNotBetween(Integer value1, Integer value2) {
            addCriterion("major_count not between", value1, value2, "majorCount");
            return (Criteria) this;
        }

        public Criteria andBlockerCountIsNull() {
            addCriterion("blocker_count is null");
            return (Criteria) this;
        }

        public Criteria andBlockerCountIsNotNull() {
            addCriterion("blocker_count is not null");
            return (Criteria) this;
        }

        public Criteria andBlockerCountEqualTo(Integer value) {
            addCriterion("blocker_count =", value, "blockerCount");
            return (Criteria) this;
        }

        public Criteria andBlockerCountNotEqualTo(Integer value) {
            addCriterion("blocker_count <>", value, "blockerCount");
            return (Criteria) this;
        }

        public Criteria andBlockerCountGreaterThan(Integer value) {
            addCriterion("blocker_count >", value, "blockerCount");
            return (Criteria) this;
        }

        public Criteria andBlockerCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("blocker_count >=", value, "blockerCount");
            return (Criteria) this;
        }

        public Criteria andBlockerCountLessThan(Integer value) {
            addCriterion("blocker_count <", value, "blockerCount");
            return (Criteria) this;
        }

        public Criteria andBlockerCountLessThanOrEqualTo(Integer value) {
            addCriterion("blocker_count <=", value, "blockerCount");
            return (Criteria) this;
        }

        public Criteria andBlockerCountIn(List<Integer> values) {
            addCriterion("blocker_count in", values, "blockerCount");
            return (Criteria) this;
        }

        public Criteria andBlockerCountNotIn(List<Integer> values) {
            addCriterion("blocker_count not in", values, "blockerCount");
            return (Criteria) this;
        }

        public Criteria andBlockerCountBetween(Integer value1, Integer value2) {
            addCriterion("blocker_count between", value1, value2, "blockerCount");
            return (Criteria) this;
        }

        public Criteria andBlockerCountNotBetween(Integer value1, Integer value2) {
            addCriterion("blocker_count not between", value1, value2, "blockerCount");
            return (Criteria) this;
        }

        public Criteria andCriticalCountIsNull() {
            addCriterion("critical_count is null");
            return (Criteria) this;
        }

        public Criteria andCriticalCountIsNotNull() {
            addCriterion("critical_count is not null");
            return (Criteria) this;
        }

        public Criteria andCriticalCountEqualTo(Integer value) {
            addCriterion("critical_count =", value, "criticalCount");
            return (Criteria) this;
        }

        public Criteria andCriticalCountNotEqualTo(Integer value) {
            addCriterion("critical_count <>", value, "criticalCount");
            return (Criteria) this;
        }

        public Criteria andCriticalCountGreaterThan(Integer value) {
            addCriterion("critical_count >", value, "criticalCount");
            return (Criteria) this;
        }

        public Criteria andCriticalCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("critical_count >=", value, "criticalCount");
            return (Criteria) this;
        }

        public Criteria andCriticalCountLessThan(Integer value) {
            addCriterion("critical_count <", value, "criticalCount");
            return (Criteria) this;
        }

        public Criteria andCriticalCountLessThanOrEqualTo(Integer value) {
            addCriterion("critical_count <=", value, "criticalCount");
            return (Criteria) this;
        }

        public Criteria andCriticalCountIn(List<Integer> values) {
            addCriterion("critical_count in", values, "criticalCount");
            return (Criteria) this;
        }

        public Criteria andCriticalCountNotIn(List<Integer> values) {
            addCriterion("critical_count not in", values, "criticalCount");
            return (Criteria) this;
        }

        public Criteria andCriticalCountBetween(Integer value1, Integer value2) {
            addCriterion("critical_count between", value1, value2, "criticalCount");
            return (Criteria) this;
        }

        public Criteria andCriticalCountNotBetween(Integer value1, Integer value2) {
            addCriterion("critical_count not between", value1, value2, "criticalCount");
            return (Criteria) this;
        }

        public Criteria andMinorCountIsNull() {
            addCriterion("minor_count is null");
            return (Criteria) this;
        }

        public Criteria andMinorCountIsNotNull() {
            addCriterion("minor_count is not null");
            return (Criteria) this;
        }

        public Criteria andMinorCountEqualTo(Integer value) {
            addCriterion("minor_count =", value, "minorCount");
            return (Criteria) this;
        }

        public Criteria andMinorCountNotEqualTo(Integer value) {
            addCriterion("minor_count <>", value, "minorCount");
            return (Criteria) this;
        }

        public Criteria andMinorCountGreaterThan(Integer value) {
            addCriterion("minor_count >", value, "minorCount");
            return (Criteria) this;
        }

        public Criteria andMinorCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("minor_count >=", value, "minorCount");
            return (Criteria) this;
        }

        public Criteria andMinorCountLessThan(Integer value) {
            addCriterion("minor_count <", value, "minorCount");
            return (Criteria) this;
        }

        public Criteria andMinorCountLessThanOrEqualTo(Integer value) {
            addCriterion("minor_count <=", value, "minorCount");
            return (Criteria) this;
        }

        public Criteria andMinorCountIn(List<Integer> values) {
            addCriterion("minor_count in", values, "minorCount");
            return (Criteria) this;
        }

        public Criteria andMinorCountNotIn(List<Integer> values) {
            addCriterion("minor_count not in", values, "minorCount");
            return (Criteria) this;
        }

        public Criteria andMinorCountBetween(Integer value1, Integer value2) {
            addCriterion("minor_count between", value1, value2, "minorCount");
            return (Criteria) this;
        }

        public Criteria andMinorCountNotBetween(Integer value1, Integer value2) {
            addCriterion("minor_count not between", value1, value2, "minorCount");
            return (Criteria) this;
        }

        public Criteria andTrivialCountIsNull() {
            addCriterion("trivial_count is null");
            return (Criteria) this;
        }

        public Criteria andTrivialCountIsNotNull() {
            addCriterion("trivial_count is not null");
            return (Criteria) this;
        }

        public Criteria andTrivialCountEqualTo(Integer value) {
            addCriterion("trivial_count =", value, "trivialCount");
            return (Criteria) this;
        }

        public Criteria andTrivialCountNotEqualTo(Integer value) {
            addCriterion("trivial_count <>", value, "trivialCount");
            return (Criteria) this;
        }

        public Criteria andTrivialCountGreaterThan(Integer value) {
            addCriterion("trivial_count >", value, "trivialCount");
            return (Criteria) this;
        }

        public Criteria andTrivialCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("trivial_count >=", value, "trivialCount");
            return (Criteria) this;
        }

        public Criteria andTrivialCountLessThan(Integer value) {
            addCriterion("trivial_count <", value, "trivialCount");
            return (Criteria) this;
        }

        public Criteria andTrivialCountLessThanOrEqualTo(Integer value) {
            addCriterion("trivial_count <=", value, "trivialCount");
            return (Criteria) this;
        }

        public Criteria andTrivialCountIn(List<Integer> values) {
            addCriterion("trivial_count in", values, "trivialCount");
            return (Criteria) this;
        }

        public Criteria andTrivialCountNotIn(List<Integer> values) {
            addCriterion("trivial_count not in", values, "trivialCount");
            return (Criteria) this;
        }

        public Criteria andTrivialCountBetween(Integer value1, Integer value2) {
            addCriterion("trivial_count between", value1, value2, "trivialCount");
            return (Criteria) this;
        }

        public Criteria andTrivialCountNotBetween(Integer value1, Integer value2) {
            addCriterion("trivial_count not between", value1, value2, "trivialCount");
            return (Criteria) this;
        }

        public Criteria andTotalCountIsNull() {
            addCriterion("total_count is null");
            return (Criteria) this;
        }

        public Criteria andTotalCountIsNotNull() {
            addCriterion("total_count is not null");
            return (Criteria) this;
        }

        public Criteria andTotalCountEqualTo(Integer value) {
            addCriterion("total_count =", value, "totalCount");
            return (Criteria) this;
        }

        public Criteria andTotalCountNotEqualTo(Integer value) {
            addCriterion("total_count <>", value, "totalCount");
            return (Criteria) this;
        }

        public Criteria andTotalCountGreaterThan(Integer value) {
            addCriterion("total_count >", value, "totalCount");
            return (Criteria) this;
        }

        public Criteria andTotalCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("total_count >=", value, "totalCount");
            return (Criteria) this;
        }

        public Criteria andTotalCountLessThan(Integer value) {
            addCriterion("total_count <", value, "totalCount");
            return (Criteria) this;
        }

        public Criteria andTotalCountLessThanOrEqualTo(Integer value) {
            addCriterion("total_count <=", value, "totalCount");
            return (Criteria) this;
        }

        public Criteria andTotalCountIn(List<Integer> values) {
            addCriterion("total_count in", values, "totalCount");
            return (Criteria) this;
        }

        public Criteria andTotalCountNotIn(List<Integer> values) {
            addCriterion("total_count not in", values, "totalCount");
            return (Criteria) this;
        }

        public Criteria andTotalCountBetween(Integer value1, Integer value2) {
            addCriterion("total_count between", value1, value2, "totalCount");
            return (Criteria) this;
        }

        public Criteria andTotalCountNotBetween(Integer value1, Integer value2) {
            addCriterion("total_count not between", value1, value2, "totalCount");
            return (Criteria) this;
        }

        public Criteria andBugLinkIsNull() {
            addCriterion("bug_link is null");
            return (Criteria) this;
        }

        public Criteria andBugLinkIsNotNull() {
            addCriterion("bug_link is not null");
            return (Criteria) this;
        }

        public Criteria andBugLinkEqualTo(String value) {
            addCriterion("bug_link =", value, "bugLink");
            return (Criteria) this;
        }

        public Criteria andBugLinkNotEqualTo(String value) {
            addCriterion("bug_link <>", value, "bugLink");
            return (Criteria) this;
        }

        public Criteria andBugLinkGreaterThan(String value) {
            addCriterion("bug_link >", value, "bugLink");
            return (Criteria) this;
        }

        public Criteria andBugLinkGreaterThanOrEqualTo(String value) {
            addCriterion("bug_link >=", value, "bugLink");
            return (Criteria) this;
        }

        public Criteria andBugLinkLessThan(String value) {
            addCriterion("bug_link <", value, "bugLink");
            return (Criteria) this;
        }

        public Criteria andBugLinkLessThanOrEqualTo(String value) {
            addCriterion("bug_link <=", value, "bugLink");
            return (Criteria) this;
        }

        public Criteria andBugLinkLike(String value) {
            addCriterion("bug_link like", value, "bugLink");
            return (Criteria) this;
        }

        public Criteria andBugLinkNotLike(String value) {
            addCriterion("bug_link not like", value, "bugLink");
            return (Criteria) this;
        }

        public Criteria andBugLinkIn(List<String> values) {
            addCriterion("bug_link in", values, "bugLink");
            return (Criteria) this;
        }

        public Criteria andBugLinkNotIn(List<String> values) {
            addCriterion("bug_link not in", values, "bugLink");
            return (Criteria) this;
        }

        public Criteria andBugLinkBetween(String value1, String value2) {
            addCriterion("bug_link between", value1, value2, "bugLink");
            return (Criteria) this;
        }

        public Criteria andBugLinkNotBetween(String value1, String value2) {
            addCriterion("bug_link not between", value1, value2, "bugLink");
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

        public Criteria andTimeFlagIsNull() {
            addCriterion("time_flag is null");
            return (Criteria) this;
        }

        public Criteria andTimeFlagIsNotNull() {
            addCriterion("time_flag is not null");
            return (Criteria) this;
        }

        public Criteria andTimeFlagEqualTo(Long value) {
            addCriterion("time_flag =", value, "timeFlag");
            return (Criteria) this;
        }

        public Criteria andTimeFlagNotEqualTo(Long value) {
            addCriterion("time_flag <>", value, "timeFlag");
            return (Criteria) this;
        }

        public Criteria andTimeFlagGreaterThan(Long value) {
            addCriterion("time_flag >", value, "timeFlag");
            return (Criteria) this;
        }

        public Criteria andTimeFlagGreaterThanOrEqualTo(Long value) {
            addCriterion("time_flag >=", value, "timeFlag");
            return (Criteria) this;
        }

        public Criteria andTimeFlagLessThan(Long value) {
            addCriterion("time_flag <", value, "timeFlag");
            return (Criteria) this;
        }

        public Criteria andTimeFlagLessThanOrEqualTo(Long value) {
            addCriterion("time_flag <=", value, "timeFlag");
            return (Criteria) this;
        }

        public Criteria andTimeFlagIn(List<Long> values) {
            addCriterion("time_flag in", values, "timeFlag");
            return (Criteria) this;
        }

        public Criteria andTimeFlagNotIn(List<Long> values) {
            addCriterion("time_flag not in", values, "timeFlag");
            return (Criteria) this;
        }

        public Criteria andTimeFlagBetween(Long value1, Long value2) {
            addCriterion("time_flag between", value1, value2, "timeFlag");
            return (Criteria) this;
        }

        public Criteria andTimeFlagNotBetween(Long value1, Long value2) {
            addCriterion("time_flag not between", value1, value2, "timeFlag");
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