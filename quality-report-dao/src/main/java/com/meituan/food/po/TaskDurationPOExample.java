package com.meituan.food.po;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskDurationPOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TaskDurationPOExample() {
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

        public Criteria andRealNameIsNull() {
            addCriterion("real_name is null");
            return (Criteria) this;
        }

        public Criteria andRealNameIsNotNull() {
            addCriterion("real_name is not null");
            return (Criteria) this;
        }

        public Criteria andRealNameEqualTo(String value) {
            addCriterion("real_name =", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameNotEqualTo(String value) {
            addCriterion("real_name <>", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameGreaterThan(String value) {
            addCriterion("real_name >", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameGreaterThanOrEqualTo(String value) {
            addCriterion("real_name >=", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameLessThan(String value) {
            addCriterion("real_name <", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameLessThanOrEqualTo(String value) {
            addCriterion("real_name <=", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameLike(String value) {
            addCriterion("real_name like", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameNotLike(String value) {
            addCriterion("real_name not like", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameIn(List<String> values) {
            addCriterion("real_name in", values, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameNotIn(List<String> values) {
            addCriterion("real_name not in", values, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameBetween(String value1, String value2) {
            addCriterion("real_name between", value1, value2, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameNotBetween(String value1, String value2) {
            addCriterion("real_name not between", value1, value2, "realName");
            return (Criteria) this;
        }

        public Criteria andDashboardIsNull() {
            addCriterion("dashboard is null");
            return (Criteria) this;
        }

        public Criteria andDashboardIsNotNull() {
            addCriterion("dashboard is not null");
            return (Criteria) this;
        }

        public Criteria andDashboardEqualTo(String value) {
            addCriterion("dashboard =", value, "dashboard");
            return (Criteria) this;
        }

        public Criteria andDashboardNotEqualTo(String value) {
            addCriterion("dashboard <>", value, "dashboard");
            return (Criteria) this;
        }

        public Criteria andDashboardGreaterThan(String value) {
            addCriterion("dashboard >", value, "dashboard");
            return (Criteria) this;
        }

        public Criteria andDashboardGreaterThanOrEqualTo(String value) {
            addCriterion("dashboard >=", value, "dashboard");
            return (Criteria) this;
        }

        public Criteria andDashboardLessThan(String value) {
            addCriterion("dashboard <", value, "dashboard");
            return (Criteria) this;
        }

        public Criteria andDashboardLessThanOrEqualTo(String value) {
            addCriterion("dashboard <=", value, "dashboard");
            return (Criteria) this;
        }

        public Criteria andDashboardLike(String value) {
            addCriterion("dashboard like", value, "dashboard");
            return (Criteria) this;
        }

        public Criteria andDashboardNotLike(String value) {
            addCriterion("dashboard not like", value, "dashboard");
            return (Criteria) this;
        }

        public Criteria andDashboardIn(List<String> values) {
            addCriterion("dashboard in", values, "dashboard");
            return (Criteria) this;
        }

        public Criteria andDashboardNotIn(List<String> values) {
            addCriterion("dashboard not in", values, "dashboard");
            return (Criteria) this;
        }

        public Criteria andDashboardBetween(String value1, String value2) {
            addCriterion("dashboard between", value1, value2, "dashboard");
            return (Criteria) this;
        }

        public Criteria andDashboardNotBetween(String value1, String value2) {
            addCriterion("dashboard not between", value1, value2, "dashboard");
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

        public Criteria andDurationIsNull() {
            addCriterion("duration is null");
            return (Criteria) this;
        }

        public Criteria andDurationIsNotNull() {
            addCriterion("duration is not null");
            return (Criteria) this;
        }

        public Criteria andDurationEqualTo(BigDecimal value) {
            addCriterion("duration =", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationNotEqualTo(BigDecimal value) {
            addCriterion("duration <>", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationGreaterThan(BigDecimal value) {
            addCriterion("duration >", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("duration >=", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationLessThan(BigDecimal value) {
            addCriterion("duration <", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationLessThanOrEqualTo(BigDecimal value) {
            addCriterion("duration <=", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationIn(List<BigDecimal> values) {
            addCriterion("duration in", values, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationNotIn(List<BigDecimal> values) {
            addCriterion("duration not in", values, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("duration between", value1, value2, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("duration not between", value1, value2, "duration");
            return (Criteria) this;
        }

        public Criteria andOrgGroupIsNull() {
            addCriterion("org_group is null");
            return (Criteria) this;
        }

        public Criteria andOrgGroupIsNotNull() {
            addCriterion("org_group is not null");
            return (Criteria) this;
        }

        public Criteria andOrgGroupEqualTo(String value) {
            addCriterion("org_group =", value, "orgGroup");
            return (Criteria) this;
        }

        public Criteria andOrgGroupNotEqualTo(String value) {
            addCriterion("org_group <>", value, "orgGroup");
            return (Criteria) this;
        }

        public Criteria andOrgGroupGreaterThan(String value) {
            addCriterion("org_group >", value, "orgGroup");
            return (Criteria) this;
        }

        public Criteria andOrgGroupGreaterThanOrEqualTo(String value) {
            addCriterion("org_group >=", value, "orgGroup");
            return (Criteria) this;
        }

        public Criteria andOrgGroupLessThan(String value) {
            addCriterion("org_group <", value, "orgGroup");
            return (Criteria) this;
        }

        public Criteria andOrgGroupLessThanOrEqualTo(String value) {
            addCriterion("org_group <=", value, "orgGroup");
            return (Criteria) this;
        }

        public Criteria andOrgGroupLike(String value) {
            addCriterion("org_group like", value, "orgGroup");
            return (Criteria) this;
        }

        public Criteria andOrgGroupNotLike(String value) {
            addCriterion("org_group not like", value, "orgGroup");
            return (Criteria) this;
        }

        public Criteria andOrgGroupIn(List<String> values) {
            addCriterion("org_group in", values, "orgGroup");
            return (Criteria) this;
        }

        public Criteria andOrgGroupNotIn(List<String> values) {
            addCriterion("org_group not in", values, "orgGroup");
            return (Criteria) this;
        }

        public Criteria andOrgGroupBetween(String value1, String value2) {
            addCriterion("org_group between", value1, value2, "orgGroup");
            return (Criteria) this;
        }

        public Criteria andOrgGroupNotBetween(String value1, String value2) {
            addCriterion("org_group not between", value1, value2, "orgGroup");
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

        public Criteria andOrgIdEqualTo(String value) {
            addCriterion("org_id =", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdNotEqualTo(String value) {
            addCriterion("org_id <>", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdGreaterThan(String value) {
            addCriterion("org_id >", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdGreaterThanOrEqualTo(String value) {
            addCriterion("org_id >=", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdLessThan(String value) {
            addCriterion("org_id <", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdLessThanOrEqualTo(String value) {
            addCriterion("org_id <=", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdLike(String value) {
            addCriterion("org_id like", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdNotLike(String value) {
            addCriterion("org_id not like", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdIn(List<String> values) {
            addCriterion("org_id in", values, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdNotIn(List<String> values) {
            addCriterion("org_id not in", values, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdBetween(String value1, String value2) {
            addCriterion("org_id between", value1, value2, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdNotBetween(String value1, String value2) {
            addCriterion("org_id not between", value1, value2, "orgId");
            return (Criteria) this;
        }

        public Criteria andFirstLeaderIsNull() {
            addCriterion("first_leader is null");
            return (Criteria) this;
        }

        public Criteria andFirstLeaderIsNotNull() {
            addCriterion("first_leader is not null");
            return (Criteria) this;
        }

        public Criteria andFirstLeaderEqualTo(String value) {
            addCriterion("first_leader =", value, "firstLeader");
            return (Criteria) this;
        }

        public Criteria andFirstLeaderNotEqualTo(String value) {
            addCriterion("first_leader <>", value, "firstLeader");
            return (Criteria) this;
        }

        public Criteria andFirstLeaderGreaterThan(String value) {
            addCriterion("first_leader >", value, "firstLeader");
            return (Criteria) this;
        }

        public Criteria andFirstLeaderGreaterThanOrEqualTo(String value) {
            addCriterion("first_leader >=", value, "firstLeader");
            return (Criteria) this;
        }

        public Criteria andFirstLeaderLessThan(String value) {
            addCriterion("first_leader <", value, "firstLeader");
            return (Criteria) this;
        }

        public Criteria andFirstLeaderLessThanOrEqualTo(String value) {
            addCriterion("first_leader <=", value, "firstLeader");
            return (Criteria) this;
        }

        public Criteria andFirstLeaderLike(String value) {
            addCriterion("first_leader like", value, "firstLeader");
            return (Criteria) this;
        }

        public Criteria andFirstLeaderNotLike(String value) {
            addCriterion("first_leader not like", value, "firstLeader");
            return (Criteria) this;
        }

        public Criteria andFirstLeaderIn(List<String> values) {
            addCriterion("first_leader in", values, "firstLeader");
            return (Criteria) this;
        }

        public Criteria andFirstLeaderNotIn(List<String> values) {
            addCriterion("first_leader not in", values, "firstLeader");
            return (Criteria) this;
        }

        public Criteria andFirstLeaderBetween(String value1, String value2) {
            addCriterion("first_leader between", value1, value2, "firstLeader");
            return (Criteria) this;
        }

        public Criteria andFirstLeaderNotBetween(String value1, String value2) {
            addCriterion("first_leader not between", value1, value2, "firstLeader");
            return (Criteria) this;
        }

        public Criteria andSecondLeaderIsNull() {
            addCriterion("second_leader is null");
            return (Criteria) this;
        }

        public Criteria andSecondLeaderIsNotNull() {
            addCriterion("second_leader is not null");
            return (Criteria) this;
        }

        public Criteria andSecondLeaderEqualTo(String value) {
            addCriterion("second_leader =", value, "secondLeader");
            return (Criteria) this;
        }

        public Criteria andSecondLeaderNotEqualTo(String value) {
            addCriterion("second_leader <>", value, "secondLeader");
            return (Criteria) this;
        }

        public Criteria andSecondLeaderGreaterThan(String value) {
            addCriterion("second_leader >", value, "secondLeader");
            return (Criteria) this;
        }

        public Criteria andSecondLeaderGreaterThanOrEqualTo(String value) {
            addCriterion("second_leader >=", value, "secondLeader");
            return (Criteria) this;
        }

        public Criteria andSecondLeaderLessThan(String value) {
            addCriterion("second_leader <", value, "secondLeader");
            return (Criteria) this;
        }

        public Criteria andSecondLeaderLessThanOrEqualTo(String value) {
            addCriterion("second_leader <=", value, "secondLeader");
            return (Criteria) this;
        }

        public Criteria andSecondLeaderLike(String value) {
            addCriterion("second_leader like", value, "secondLeader");
            return (Criteria) this;
        }

        public Criteria andSecondLeaderNotLike(String value) {
            addCriterion("second_leader not like", value, "secondLeader");
            return (Criteria) this;
        }

        public Criteria andSecondLeaderIn(List<String> values) {
            addCriterion("second_leader in", values, "secondLeader");
            return (Criteria) this;
        }

        public Criteria andSecondLeaderNotIn(List<String> values) {
            addCriterion("second_leader not in", values, "secondLeader");
            return (Criteria) this;
        }

        public Criteria andSecondLeaderBetween(String value1, String value2) {
            addCriterion("second_leader between", value1, value2, "secondLeader");
            return (Criteria) this;
        }

        public Criteria andSecondLeaderNotBetween(String value1, String value2) {
            addCriterion("second_leader not between", value1, value2, "secondLeader");
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

        public Criteria andIsnormalIsNull() {
            addCriterion("isNormal is null");
            return (Criteria) this;
        }

        public Criteria andIsnormalIsNotNull() {
            addCriterion("isNormal is not null");
            return (Criteria) this;
        }

        public Criteria andIsnormalEqualTo(Boolean value) {
            addCriterion("isNormal =", value, "isnormal");
            return (Criteria) this;
        }

        public Criteria andIsnormalNotEqualTo(Boolean value) {
            addCriterion("isNormal <>", value, "isnormal");
            return (Criteria) this;
        }

        public Criteria andIsnormalGreaterThan(Boolean value) {
            addCriterion("isNormal >", value, "isnormal");
            return (Criteria) this;
        }

        public Criteria andIsnormalGreaterThanOrEqualTo(Boolean value) {
            addCriterion("isNormal >=", value, "isnormal");
            return (Criteria) this;
        }

        public Criteria andIsnormalLessThan(Boolean value) {
            addCriterion("isNormal <", value, "isnormal");
            return (Criteria) this;
        }

        public Criteria andIsnormalLessThanOrEqualTo(Boolean value) {
            addCriterion("isNormal <=", value, "isnormal");
            return (Criteria) this;
        }

        public Criteria andIsnormalIn(List<Boolean> values) {
            addCriterion("isNormal in", values, "isnormal");
            return (Criteria) this;
        }

        public Criteria andIsnormalNotIn(List<Boolean> values) {
            addCriterion("isNormal not in", values, "isnormal");
            return (Criteria) this;
        }

        public Criteria andIsnormalBetween(Boolean value1, Boolean value2) {
            addCriterion("isNormal between", value1, value2, "isnormal");
            return (Criteria) this;
        }

        public Criteria andIsnormalNotBetween(Boolean value1, Boolean value2) {
            addCriterion("isNormal not between", value1, value2, "isnormal");
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