package com.meituan.food.po;

import java.util.ArrayList;
import java.util.List;

public class WeekBugDetailPOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public WeekBugDetailPOExample() {
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

        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andBugLevelIsNull() {
            addCriterion("bug_level is null");
            return (Criteria) this;
        }

        public Criteria andBugLevelIsNotNull() {
            addCriterion("bug_level is not null");
            return (Criteria) this;
        }

        public Criteria andBugLevelEqualTo(String value) {
            addCriterion("bug_level =", value, "bugLevel");
            return (Criteria) this;
        }

        public Criteria andBugLevelNotEqualTo(String value) {
            addCriterion("bug_level <>", value, "bugLevel");
            return (Criteria) this;
        }

        public Criteria andBugLevelGreaterThan(String value) {
            addCriterion("bug_level >", value, "bugLevel");
            return (Criteria) this;
        }

        public Criteria andBugLevelGreaterThanOrEqualTo(String value) {
            addCriterion("bug_level >=", value, "bugLevel");
            return (Criteria) this;
        }

        public Criteria andBugLevelLessThan(String value) {
            addCriterion("bug_level <", value, "bugLevel");
            return (Criteria) this;
        }

        public Criteria andBugLevelLessThanOrEqualTo(String value) {
            addCriterion("bug_level <=", value, "bugLevel");
            return (Criteria) this;
        }

        public Criteria andBugLevelLike(String value) {
            addCriterion("bug_level like", value, "bugLevel");
            return (Criteria) this;
        }

        public Criteria andBugLevelNotLike(String value) {
            addCriterion("bug_level not like", value, "bugLevel");
            return (Criteria) this;
        }

        public Criteria andBugLevelIn(List<String> values) {
            addCriterion("bug_level in", values, "bugLevel");
            return (Criteria) this;
        }

        public Criteria andBugLevelNotIn(List<String> values) {
            addCriterion("bug_level not in", values, "bugLevel");
            return (Criteria) this;
        }

        public Criteria andBugLevelBetween(String value1, String value2) {
            addCriterion("bug_level between", value1, value2, "bugLevel");
            return (Criteria) this;
        }

        public Criteria andBugLevelNotBetween(String value1, String value2) {
            addCriterion("bug_level not between", value1, value2, "bugLevel");
            return (Criteria) this;
        }

        public Criteria andReasonIsNull() {
            addCriterion("reason is null");
            return (Criteria) this;
        }

        public Criteria andReasonIsNotNull() {
            addCriterion("reason is not null");
            return (Criteria) this;
        }

        public Criteria andReasonEqualTo(String value) {
            addCriterion("reason =", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotEqualTo(String value) {
            addCriterion("reason <>", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonGreaterThan(String value) {
            addCriterion("reason >", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonGreaterThanOrEqualTo(String value) {
            addCriterion("reason >=", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLessThan(String value) {
            addCriterion("reason <", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLessThanOrEqualTo(String value) {
            addCriterion("reason <=", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLike(String value) {
            addCriterion("reason like", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotLike(String value) {
            addCriterion("reason not like", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonIn(List<String> values) {
            addCriterion("reason in", values, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotIn(List<String> values) {
            addCriterion("reason not in", values, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonBetween(String value1, String value2) {
            addCriterion("reason between", value1, value2, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotBetween(String value1, String value2) {
            addCriterion("reason not between", value1, value2, "reason");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNull() {
            addCriterion("creator is null");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNotNull() {
            addCriterion("creator is not null");
            return (Criteria) this;
        }

        public Criteria andCreatorEqualTo(String value) {
            addCriterion("creator =", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotEqualTo(String value) {
            addCriterion("creator <>", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThan(String value) {
            addCriterion("creator >", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThanOrEqualTo(String value) {
            addCriterion("creator >=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThan(String value) {
            addCriterion("creator <", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThanOrEqualTo(String value) {
            addCriterion("creator <=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLike(String value) {
            addCriterion("creator like", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotLike(String value) {
            addCriterion("creator not like", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorIn(List<String> values) {
            addCriterion("creator in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotIn(List<String> values) {
            addCriterion("creator not in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorBetween(String value1, String value2) {
            addCriterion("creator between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotBetween(String value1, String value2) {
            addCriterion("creator not between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andReceiverIsNull() {
            addCriterion("receiver is null");
            return (Criteria) this;
        }

        public Criteria andReceiverIsNotNull() {
            addCriterion("receiver is not null");
            return (Criteria) this;
        }

        public Criteria andReceiverEqualTo(String value) {
            addCriterion("receiver =", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverNotEqualTo(String value) {
            addCriterion("receiver <>", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverGreaterThan(String value) {
            addCriterion("receiver >", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverGreaterThanOrEqualTo(String value) {
            addCriterion("receiver >=", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverLessThan(String value) {
            addCriterion("receiver <", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverLessThanOrEqualTo(String value) {
            addCriterion("receiver <=", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverLike(String value) {
            addCriterion("receiver like", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverNotLike(String value) {
            addCriterion("receiver not like", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverIn(List<String> values) {
            addCriterion("receiver in", values, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverNotIn(List<String> values) {
            addCriterion("receiver not in", values, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverBetween(String value1, String value2) {
            addCriterion("receiver between", value1, value2, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverNotBetween(String value1, String value2) {
            addCriterion("receiver not between", value1, value2, "receiver");
            return (Criteria) this;
        }

        public Criteria andBugStatusIsNull() {
            addCriterion("bug_status is null");
            return (Criteria) this;
        }

        public Criteria andBugStatusIsNotNull() {
            addCriterion("bug_status is not null");
            return (Criteria) this;
        }

        public Criteria andBugStatusEqualTo(String value) {
            addCriterion("bug_status =", value, "bugStatus");
            return (Criteria) this;
        }

        public Criteria andBugStatusNotEqualTo(String value) {
            addCriterion("bug_status <>", value, "bugStatus");
            return (Criteria) this;
        }

        public Criteria andBugStatusGreaterThan(String value) {
            addCriterion("bug_status >", value, "bugStatus");
            return (Criteria) this;
        }

        public Criteria andBugStatusGreaterThanOrEqualTo(String value) {
            addCriterion("bug_status >=", value, "bugStatus");
            return (Criteria) this;
        }

        public Criteria andBugStatusLessThan(String value) {
            addCriterion("bug_status <", value, "bugStatus");
            return (Criteria) this;
        }

        public Criteria andBugStatusLessThanOrEqualTo(String value) {
            addCriterion("bug_status <=", value, "bugStatus");
            return (Criteria) this;
        }

        public Criteria andBugStatusLike(String value) {
            addCriterion("bug_status like", value, "bugStatus");
            return (Criteria) this;
        }

        public Criteria andBugStatusNotLike(String value) {
            addCriterion("bug_status not like", value, "bugStatus");
            return (Criteria) this;
        }

        public Criteria andBugStatusIn(List<String> values) {
            addCriterion("bug_status in", values, "bugStatus");
            return (Criteria) this;
        }

        public Criteria andBugStatusNotIn(List<String> values) {
            addCriterion("bug_status not in", values, "bugStatus");
            return (Criteria) this;
        }

        public Criteria andBugStatusBetween(String value1, String value2) {
            addCriterion("bug_status between", value1, value2, "bugStatus");
            return (Criteria) this;
        }

        public Criteria andBugStatusNotBetween(String value1, String value2) {
            addCriterion("bug_status not between", value1, value2, "bugStatus");
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

        public Criteria andCreatedTimeEqualTo(String value) {
            addCriterion("created_time =", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeNotEqualTo(String value) {
            addCriterion("created_time <>", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeGreaterThan(String value) {
            addCriterion("created_time >", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeGreaterThanOrEqualTo(String value) {
            addCriterion("created_time >=", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeLessThan(String value) {
            addCriterion("created_time <", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeLessThanOrEqualTo(String value) {
            addCriterion("created_time <=", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeLike(String value) {
            addCriterion("created_time like", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeNotLike(String value) {
            addCriterion("created_time not like", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeIn(List<String> values) {
            addCriterion("created_time in", values, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeNotIn(List<String> values) {
            addCriterion("created_time not in", values, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeBetween(String value1, String value2) {
            addCriterion("created_time between", value1, value2, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeNotBetween(String value1, String value2) {
            addCriterion("created_time not between", value1, value2, "createdTime");
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

        public Criteria andOrgnameIsNull() {
            addCriterion("orgname is null");
            return (Criteria) this;
        }

        public Criteria andOrgnameIsNotNull() {
            addCriterion("orgname is not null");
            return (Criteria) this;
        }

        public Criteria andOrgnameEqualTo(String value) {
            addCriterion("orgname =", value, "orgname");
            return (Criteria) this;
        }

        public Criteria andOrgnameNotEqualTo(String value) {
            addCriterion("orgname <>", value, "orgname");
            return (Criteria) this;
        }

        public Criteria andOrgnameGreaterThan(String value) {
            addCriterion("orgname >", value, "orgname");
            return (Criteria) this;
        }

        public Criteria andOrgnameGreaterThanOrEqualTo(String value) {
            addCriterion("orgname >=", value, "orgname");
            return (Criteria) this;
        }

        public Criteria andOrgnameLessThan(String value) {
            addCriterion("orgname <", value, "orgname");
            return (Criteria) this;
        }

        public Criteria andOrgnameLessThanOrEqualTo(String value) {
            addCriterion("orgname <=", value, "orgname");
            return (Criteria) this;
        }

        public Criteria andOrgnameLike(String value) {
            addCriterion("orgname like", value, "orgname");
            return (Criteria) this;
        }

        public Criteria andOrgnameNotLike(String value) {
            addCriterion("orgname not like", value, "orgname");
            return (Criteria) this;
        }

        public Criteria andOrgnameIn(List<String> values) {
            addCriterion("orgname in", values, "orgname");
            return (Criteria) this;
        }

        public Criteria andOrgnameNotIn(List<String> values) {
            addCriterion("orgname not in", values, "orgname");
            return (Criteria) this;
        }

        public Criteria andOrgnameBetween(String value1, String value2) {
            addCriterion("orgname between", value1, value2, "orgname");
            return (Criteria) this;
        }

        public Criteria andOrgnameNotBetween(String value1, String value2) {
            addCriterion("orgname not between", value1, value2, "orgname");
            return (Criteria) this;
        }

        public Criteria andOrgidIsNull() {
            addCriterion("orgid is null");
            return (Criteria) this;
        }

        public Criteria andOrgidIsNotNull() {
            addCriterion("orgid is not null");
            return (Criteria) this;
        }

        public Criteria andOrgidEqualTo(String value) {
            addCriterion("orgid =", value, "orgid");
            return (Criteria) this;
        }

        public Criteria andOrgidNotEqualTo(String value) {
            addCriterion("orgid <>", value, "orgid");
            return (Criteria) this;
        }

        public Criteria andOrgidGreaterThan(String value) {
            addCriterion("orgid >", value, "orgid");
            return (Criteria) this;
        }

        public Criteria andOrgidGreaterThanOrEqualTo(String value) {
            addCriterion("orgid >=", value, "orgid");
            return (Criteria) this;
        }

        public Criteria andOrgidLessThan(String value) {
            addCriterion("orgid <", value, "orgid");
            return (Criteria) this;
        }

        public Criteria andOrgidLessThanOrEqualTo(String value) {
            addCriterion("orgid <=", value, "orgid");
            return (Criteria) this;
        }

        public Criteria andOrgidLike(String value) {
            addCriterion("orgid like", value, "orgid");
            return (Criteria) this;
        }

        public Criteria andOrgidNotLike(String value) {
            addCriterion("orgid not like", value, "orgid");
            return (Criteria) this;
        }

        public Criteria andOrgidIn(List<String> values) {
            addCriterion("orgid in", values, "orgid");
            return (Criteria) this;
        }

        public Criteria andOrgidNotIn(List<String> values) {
            addCriterion("orgid not in", values, "orgid");
            return (Criteria) this;
        }

        public Criteria andOrgidBetween(String value1, String value2) {
            addCriterion("orgid between", value1, value2, "orgid");
            return (Criteria) this;
        }

        public Criteria andOrgidNotBetween(String value1, String value2) {
            addCriterion("orgid not between", value1, value2, "orgid");
            return (Criteria) this;
        }

        public Criteria andAllTitleIsNull() {
            addCriterion("all_title is null");
            return (Criteria) this;
        }

        public Criteria andAllTitleIsNotNull() {
            addCriterion("all_title is not null");
            return (Criteria) this;
        }

        public Criteria andAllTitleEqualTo(String value) {
            addCriterion("all_title =", value, "allTitle");
            return (Criteria) this;
        }

        public Criteria andAllTitleNotEqualTo(String value) {
            addCriterion("all_title <>", value, "allTitle");
            return (Criteria) this;
        }

        public Criteria andAllTitleGreaterThan(String value) {
            addCriterion("all_title >", value, "allTitle");
            return (Criteria) this;
        }

        public Criteria andAllTitleGreaterThanOrEqualTo(String value) {
            addCriterion("all_title >=", value, "allTitle");
            return (Criteria) this;
        }

        public Criteria andAllTitleLessThan(String value) {
            addCriterion("all_title <", value, "allTitle");
            return (Criteria) this;
        }

        public Criteria andAllTitleLessThanOrEqualTo(String value) {
            addCriterion("all_title <=", value, "allTitle");
            return (Criteria) this;
        }

        public Criteria andAllTitleLike(String value) {
            addCriterion("all_title like", value, "allTitle");
            return (Criteria) this;
        }

        public Criteria andAllTitleNotLike(String value) {
            addCriterion("all_title not like", value, "allTitle");
            return (Criteria) this;
        }

        public Criteria andAllTitleIn(List<String> values) {
            addCriterion("all_title in", values, "allTitle");
            return (Criteria) this;
        }

        public Criteria andAllTitleNotIn(List<String> values) {
            addCriterion("all_title not in", values, "allTitle");
            return (Criteria) this;
        }

        public Criteria andAllTitleBetween(String value1, String value2) {
            addCriterion("all_title between", value1, value2, "allTitle");
            return (Criteria) this;
        }

        public Criteria andAllTitleNotBetween(String value1, String value2) {
            addCriterion("all_title not between", value1, value2, "allTitle");
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