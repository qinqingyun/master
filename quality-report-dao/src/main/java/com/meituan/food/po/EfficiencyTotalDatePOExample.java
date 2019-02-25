package com.meituan.food.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EfficiencyTotalDatePOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EfficiencyTotalDatePOExample() {
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

        public Criteria andCreateWikiNumIsNull() {
            addCriterion("create_wiki_num is null");
            return (Criteria) this;
        }

        public Criteria andCreateWikiNumIsNotNull() {
            addCriterion("create_wiki_num is not null");
            return (Criteria) this;
        }

        public Criteria andCreateWikiNumEqualTo(Long value) {
            addCriterion("create_wiki_num =", value, "createWikiNum");
            return (Criteria) this;
        }

        public Criteria andCreateWikiNumNotEqualTo(Long value) {
            addCriterion("create_wiki_num <>", value, "createWikiNum");
            return (Criteria) this;
        }

        public Criteria andCreateWikiNumGreaterThan(Long value) {
            addCriterion("create_wiki_num >", value, "createWikiNum");
            return (Criteria) this;
        }

        public Criteria andCreateWikiNumGreaterThanOrEqualTo(Long value) {
            addCriterion("create_wiki_num >=", value, "createWikiNum");
            return (Criteria) this;
        }

        public Criteria andCreateWikiNumLessThan(Long value) {
            addCriterion("create_wiki_num <", value, "createWikiNum");
            return (Criteria) this;
        }

        public Criteria andCreateWikiNumLessThanOrEqualTo(Long value) {
            addCriterion("create_wiki_num <=", value, "createWikiNum");
            return (Criteria) this;
        }

        public Criteria andCreateWikiNumIn(List<Long> values) {
            addCriterion("create_wiki_num in", values, "createWikiNum");
            return (Criteria) this;
        }

        public Criteria andCreateWikiNumNotIn(List<Long> values) {
            addCriterion("create_wiki_num not in", values, "createWikiNum");
            return (Criteria) this;
        }

        public Criteria andCreateWikiNumBetween(Long value1, Long value2) {
            addCriterion("create_wiki_num between", value1, value2, "createWikiNum");
            return (Criteria) this;
        }

        public Criteria andCreateWikiNumNotBetween(Long value1, Long value2) {
            addCriterion("create_wiki_num not between", value1, value2, "createWikiNum");
            return (Criteria) this;
        }

        public Criteria andUpdateWikiNumIsNull() {
            addCriterion("update_wiki_num is null");
            return (Criteria) this;
        }

        public Criteria andUpdateWikiNumIsNotNull() {
            addCriterion("update_wiki_num is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateWikiNumEqualTo(Long value) {
            addCriterion("update_wiki_num =", value, "updateWikiNum");
            return (Criteria) this;
        }

        public Criteria andUpdateWikiNumNotEqualTo(Long value) {
            addCriterion("update_wiki_num <>", value, "updateWikiNum");
            return (Criteria) this;
        }

        public Criteria andUpdateWikiNumGreaterThan(Long value) {
            addCriterion("update_wiki_num >", value, "updateWikiNum");
            return (Criteria) this;
        }

        public Criteria andUpdateWikiNumGreaterThanOrEqualTo(Long value) {
            addCriterion("update_wiki_num >=", value, "updateWikiNum");
            return (Criteria) this;
        }

        public Criteria andUpdateWikiNumLessThan(Long value) {
            addCriterion("update_wiki_num <", value, "updateWikiNum");
            return (Criteria) this;
        }

        public Criteria andUpdateWikiNumLessThanOrEqualTo(Long value) {
            addCriterion("update_wiki_num <=", value, "updateWikiNum");
            return (Criteria) this;
        }

        public Criteria andUpdateWikiNumIn(List<Long> values) {
            addCriterion("update_wiki_num in", values, "updateWikiNum");
            return (Criteria) this;
        }

        public Criteria andUpdateWikiNumNotIn(List<Long> values) {
            addCriterion("update_wiki_num not in", values, "updateWikiNum");
            return (Criteria) this;
        }

        public Criteria andUpdateWikiNumBetween(Long value1, Long value2) {
            addCriterion("update_wiki_num between", value1, value2, "updateWikiNum");
            return (Criteria) this;
        }

        public Criteria andUpdateWikiNumNotBetween(Long value1, Long value2) {
            addCriterion("update_wiki_num not between", value1, value2, "updateWikiNum");
            return (Criteria) this;
        }

        public Criteria andGitIncreaseIsNull() {
            addCriterion("git_increase is null");
            return (Criteria) this;
        }

        public Criteria andGitIncreaseIsNotNull() {
            addCriterion("git_increase is not null");
            return (Criteria) this;
        }

        public Criteria andGitIncreaseEqualTo(Integer value) {
            addCriterion("git_increase =", value, "gitIncrease");
            return (Criteria) this;
        }

        public Criteria andGitIncreaseNotEqualTo(Integer value) {
            addCriterion("git_increase <>", value, "gitIncrease");
            return (Criteria) this;
        }

        public Criteria andGitIncreaseGreaterThan(Integer value) {
            addCriterion("git_increase >", value, "gitIncrease");
            return (Criteria) this;
        }

        public Criteria andGitIncreaseGreaterThanOrEqualTo(Integer value) {
            addCriterion("git_increase >=", value, "gitIncrease");
            return (Criteria) this;
        }

        public Criteria andGitIncreaseLessThan(Integer value) {
            addCriterion("git_increase <", value, "gitIncrease");
            return (Criteria) this;
        }

        public Criteria andGitIncreaseLessThanOrEqualTo(Integer value) {
            addCriterion("git_increase <=", value, "gitIncrease");
            return (Criteria) this;
        }

        public Criteria andGitIncreaseIn(List<Integer> values) {
            addCriterion("git_increase in", values, "gitIncrease");
            return (Criteria) this;
        }

        public Criteria andGitIncreaseNotIn(List<Integer> values) {
            addCriterion("git_increase not in", values, "gitIncrease");
            return (Criteria) this;
        }

        public Criteria andGitIncreaseBetween(Integer value1, Integer value2) {
            addCriterion("git_increase between", value1, value2, "gitIncrease");
            return (Criteria) this;
        }

        public Criteria andGitIncreaseNotBetween(Integer value1, Integer value2) {
            addCriterion("git_increase not between", value1, value2, "gitIncrease");
            return (Criteria) this;
        }

        public Criteria andGitDeleteIsNull() {
            addCriterion("git_delete is null");
            return (Criteria) this;
        }

        public Criteria andGitDeleteIsNotNull() {
            addCriterion("git_delete is not null");
            return (Criteria) this;
        }

        public Criteria andGitDeleteEqualTo(Integer value) {
            addCriterion("git_delete =", value, "gitDelete");
            return (Criteria) this;
        }

        public Criteria andGitDeleteNotEqualTo(Integer value) {
            addCriterion("git_delete <>", value, "gitDelete");
            return (Criteria) this;
        }

        public Criteria andGitDeleteGreaterThan(Integer value) {
            addCriterion("git_delete >", value, "gitDelete");
            return (Criteria) this;
        }

        public Criteria andGitDeleteGreaterThanOrEqualTo(Integer value) {
            addCriterion("git_delete >=", value, "gitDelete");
            return (Criteria) this;
        }

        public Criteria andGitDeleteLessThan(Integer value) {
            addCriterion("git_delete <", value, "gitDelete");
            return (Criteria) this;
        }

        public Criteria andGitDeleteLessThanOrEqualTo(Integer value) {
            addCriterion("git_delete <=", value, "gitDelete");
            return (Criteria) this;
        }

        public Criteria andGitDeleteIn(List<Integer> values) {
            addCriterion("git_delete in", values, "gitDelete");
            return (Criteria) this;
        }

        public Criteria andGitDeleteNotIn(List<Integer> values) {
            addCriterion("git_delete not in", values, "gitDelete");
            return (Criteria) this;
        }

        public Criteria andGitDeleteBetween(Integer value1, Integer value2) {
            addCriterion("git_delete between", value1, value2, "gitDelete");
            return (Criteria) this;
        }

        public Criteria andGitDeleteNotBetween(Integer value1, Integer value2) {
            addCriterion("git_delete not between", value1, value2, "gitDelete");
            return (Criteria) this;
        }

        public Criteria andGitSubmitIsNull() {
            addCriterion("git_submit is null");
            return (Criteria) this;
        }

        public Criteria andGitSubmitIsNotNull() {
            addCriterion("git_submit is not null");
            return (Criteria) this;
        }

        public Criteria andGitSubmitEqualTo(Integer value) {
            addCriterion("git_submit =", value, "gitSubmit");
            return (Criteria) this;
        }

        public Criteria andGitSubmitNotEqualTo(Integer value) {
            addCriterion("git_submit <>", value, "gitSubmit");
            return (Criteria) this;
        }

        public Criteria andGitSubmitGreaterThan(Integer value) {
            addCriterion("git_submit >", value, "gitSubmit");
            return (Criteria) this;
        }

        public Criteria andGitSubmitGreaterThanOrEqualTo(Integer value) {
            addCriterion("git_submit >=", value, "gitSubmit");
            return (Criteria) this;
        }

        public Criteria andGitSubmitLessThan(Integer value) {
            addCriterion("git_submit <", value, "gitSubmit");
            return (Criteria) this;
        }

        public Criteria andGitSubmitLessThanOrEqualTo(Integer value) {
            addCriterion("git_submit <=", value, "gitSubmit");
            return (Criteria) this;
        }

        public Criteria andGitSubmitIn(List<Integer> values) {
            addCriterion("git_submit in", values, "gitSubmit");
            return (Criteria) this;
        }

        public Criteria andGitSubmitNotIn(List<Integer> values) {
            addCriterion("git_submit not in", values, "gitSubmit");
            return (Criteria) this;
        }

        public Criteria andGitSubmitBetween(Integer value1, Integer value2) {
            addCriterion("git_submit between", value1, value2, "gitSubmit");
            return (Criteria) this;
        }

        public Criteria andGitSubmitNotBetween(Integer value1, Integer value2) {
            addCriterion("git_submit not between", value1, value2, "gitSubmit");
            return (Criteria) this;
        }

        public Criteria andGitSubmitTimeIsNull() {
            addCriterion("git_submit_time is null");
            return (Criteria) this;
        }

        public Criteria andGitSubmitTimeIsNotNull() {
            addCriterion("git_submit_time is not null");
            return (Criteria) this;
        }

        public Criteria andGitSubmitTimeEqualTo(Integer value) {
            addCriterion("git_submit_time =", value, "gitSubmitTime");
            return (Criteria) this;
        }

        public Criteria andGitSubmitTimeNotEqualTo(Integer value) {
            addCriterion("git_submit_time <>", value, "gitSubmitTime");
            return (Criteria) this;
        }

        public Criteria andGitSubmitTimeGreaterThan(Integer value) {
            addCriterion("git_submit_time >", value, "gitSubmitTime");
            return (Criteria) this;
        }

        public Criteria andGitSubmitTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("git_submit_time >=", value, "gitSubmitTime");
            return (Criteria) this;
        }

        public Criteria andGitSubmitTimeLessThan(Integer value) {
            addCriterion("git_submit_time <", value, "gitSubmitTime");
            return (Criteria) this;
        }

        public Criteria andGitSubmitTimeLessThanOrEqualTo(Integer value) {
            addCriterion("git_submit_time <=", value, "gitSubmitTime");
            return (Criteria) this;
        }

        public Criteria andGitSubmitTimeIn(List<Integer> values) {
            addCriterion("git_submit_time in", values, "gitSubmitTime");
            return (Criteria) this;
        }

        public Criteria andGitSubmitTimeNotIn(List<Integer> values) {
            addCriterion("git_submit_time not in", values, "gitSubmitTime");
            return (Criteria) this;
        }

        public Criteria andGitSubmitTimeBetween(Integer value1, Integer value2) {
            addCriterion("git_submit_time between", value1, value2, "gitSubmitTime");
            return (Criteria) this;
        }

        public Criteria andGitSubmitTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("git_submit_time not between", value1, value2, "gitSubmitTime");
            return (Criteria) this;
        }

        public Criteria andCreateBugNumIsNull() {
            addCriterion("create_bug_num is null");
            return (Criteria) this;
        }

        public Criteria andCreateBugNumIsNotNull() {
            addCriterion("create_bug_num is not null");
            return (Criteria) this;
        }

        public Criteria andCreateBugNumEqualTo(Integer value) {
            addCriterion("create_bug_num =", value, "createBugNum");
            return (Criteria) this;
        }

        public Criteria andCreateBugNumNotEqualTo(Integer value) {
            addCriterion("create_bug_num <>", value, "createBugNum");
            return (Criteria) this;
        }

        public Criteria andCreateBugNumGreaterThan(Integer value) {
            addCriterion("create_bug_num >", value, "createBugNum");
            return (Criteria) this;
        }

        public Criteria andCreateBugNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("create_bug_num >=", value, "createBugNum");
            return (Criteria) this;
        }

        public Criteria andCreateBugNumLessThan(Integer value) {
            addCriterion("create_bug_num <", value, "createBugNum");
            return (Criteria) this;
        }

        public Criteria andCreateBugNumLessThanOrEqualTo(Integer value) {
            addCriterion("create_bug_num <=", value, "createBugNum");
            return (Criteria) this;
        }

        public Criteria andCreateBugNumIn(List<Integer> values) {
            addCriterion("create_bug_num in", values, "createBugNum");
            return (Criteria) this;
        }

        public Criteria andCreateBugNumNotIn(List<Integer> values) {
            addCriterion("create_bug_num not in", values, "createBugNum");
            return (Criteria) this;
        }

        public Criteria andCreateBugNumBetween(Integer value1, Integer value2) {
            addCriterion("create_bug_num between", value1, value2, "createBugNum");
            return (Criteria) this;
        }

        public Criteria andCreateBugNumNotBetween(Integer value1, Integer value2) {
            addCriterion("create_bug_num not between", value1, value2, "createBugNum");
            return (Criteria) this;
        }

        public Criteria andAcceptBugNumIsNull() {
            addCriterion("accept_bug_num is null");
            return (Criteria) this;
        }

        public Criteria andAcceptBugNumIsNotNull() {
            addCriterion("accept_bug_num is not null");
            return (Criteria) this;
        }

        public Criteria andAcceptBugNumEqualTo(Integer value) {
            addCriterion("accept_bug_num =", value, "acceptBugNum");
            return (Criteria) this;
        }

        public Criteria andAcceptBugNumNotEqualTo(Integer value) {
            addCriterion("accept_bug_num <>", value, "acceptBugNum");
            return (Criteria) this;
        }

        public Criteria andAcceptBugNumGreaterThan(Integer value) {
            addCriterion("accept_bug_num >", value, "acceptBugNum");
            return (Criteria) this;
        }

        public Criteria andAcceptBugNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("accept_bug_num >=", value, "acceptBugNum");
            return (Criteria) this;
        }

        public Criteria andAcceptBugNumLessThan(Integer value) {
            addCriterion("accept_bug_num <", value, "acceptBugNum");
            return (Criteria) this;
        }

        public Criteria andAcceptBugNumLessThanOrEqualTo(Integer value) {
            addCriterion("accept_bug_num <=", value, "acceptBugNum");
            return (Criteria) this;
        }

        public Criteria andAcceptBugNumIn(List<Integer> values) {
            addCriterion("accept_bug_num in", values, "acceptBugNum");
            return (Criteria) this;
        }

        public Criteria andAcceptBugNumNotIn(List<Integer> values) {
            addCriterion("accept_bug_num not in", values, "acceptBugNum");
            return (Criteria) this;
        }

        public Criteria andAcceptBugNumBetween(Integer value1, Integer value2) {
            addCriterion("accept_bug_num between", value1, value2, "acceptBugNum");
            return (Criteria) this;
        }

        public Criteria andAcceptBugNumNotBetween(Integer value1, Integer value2) {
            addCriterion("accept_bug_num not between", value1, value2, "acceptBugNum");
            return (Criteria) this;
        }

        public Criteria andPartitionDateIsNull() {
            addCriterion("partition_date is null");
            return (Criteria) this;
        }

        public Criteria andPartitionDateIsNotNull() {
            addCriterion("partition_date is not null");
            return (Criteria) this;
        }

        public Criteria andPartitionDateEqualTo(String value) {
            addCriterion("partition_date =", value, "partitionDate");
            return (Criteria) this;
        }

        public Criteria andPartitionDateNotEqualTo(String value) {
            addCriterion("partition_date <>", value, "partitionDate");
            return (Criteria) this;
        }

        public Criteria andPartitionDateGreaterThan(String value) {
            addCriterion("partition_date >", value, "partitionDate");
            return (Criteria) this;
        }

        public Criteria andPartitionDateGreaterThanOrEqualTo(String value) {
            addCriterion("partition_date >=", value, "partitionDate");
            return (Criteria) this;
        }

        public Criteria andPartitionDateLessThan(String value) {
            addCriterion("partition_date <", value, "partitionDate");
            return (Criteria) this;
        }

        public Criteria andPartitionDateLessThanOrEqualTo(String value) {
            addCriterion("partition_date <=", value, "partitionDate");
            return (Criteria) this;
        }

        public Criteria andPartitionDateLike(String value) {
            addCriterion("partition_date like", value, "partitionDate");
            return (Criteria) this;
        }

        public Criteria andPartitionDateNotLike(String value) {
            addCriterion("partition_date not like", value, "partitionDate");
            return (Criteria) this;
        }

        public Criteria andPartitionDateIn(List<String> values) {
            addCriterion("partition_date in", values, "partitionDate");
            return (Criteria) this;
        }

        public Criteria andPartitionDateNotIn(List<String> values) {
            addCriterion("partition_date not in", values, "partitionDate");
            return (Criteria) this;
        }

        public Criteria andPartitionDateBetween(String value1, String value2) {
            addCriterion("partition_date between", value1, value2, "partitionDate");
            return (Criteria) this;
        }

        public Criteria andPartitionDateNotBetween(String value1, String value2) {
            addCriterion("partition_date not between", value1, value2, "partitionDate");
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