package com.meituan.food.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CargoDataPOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CargoDataPOExample() {
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

        public Criteria andStackuuidIsNull() {
            addCriterion("stackuuid is null");
            return (Criteria) this;
        }

        public Criteria andStackuuidIsNotNull() {
            addCriterion("stackuuid is not null");
            return (Criteria) this;
        }

        public Criteria andStackuuidEqualTo(String value) {
            addCriterion("stackuuid =", value, "stackuuid");
            return (Criteria) this;
        }

        public Criteria andStackuuidNotEqualTo(String value) {
            addCriterion("stackuuid <>", value, "stackuuid");
            return (Criteria) this;
        }

        public Criteria andStackuuidGreaterThan(String value) {
            addCriterion("stackuuid >", value, "stackuuid");
            return (Criteria) this;
        }

        public Criteria andStackuuidGreaterThanOrEqualTo(String value) {
            addCriterion("stackuuid >=", value, "stackuuid");
            return (Criteria) this;
        }

        public Criteria andStackuuidLessThan(String value) {
            addCriterion("stackuuid <", value, "stackuuid");
            return (Criteria) this;
        }

        public Criteria andStackuuidLessThanOrEqualTo(String value) {
            addCriterion("stackuuid <=", value, "stackuuid");
            return (Criteria) this;
        }

        public Criteria andStackuuidLike(String value) {
            addCriterion("stackuuid like", value, "stackuuid");
            return (Criteria) this;
        }

        public Criteria andStackuuidNotLike(String value) {
            addCriterion("stackuuid not like", value, "stackuuid");
            return (Criteria) this;
        }

        public Criteria andStackuuidIn(List<String> values) {
            addCriterion("stackuuid in", values, "stackuuid");
            return (Criteria) this;
        }

        public Criteria andStackuuidNotIn(List<String> values) {
            addCriterion("stackuuid not in", values, "stackuuid");
            return (Criteria) this;
        }

        public Criteria andStackuuidBetween(String value1, String value2) {
            addCriterion("stackuuid between", value1, value2, "stackuuid");
            return (Criteria) this;
        }

        public Criteria andStackuuidNotBetween(String value1, String value2) {
            addCriterion("stackuuid not between", value1, value2, "stackuuid");
            return (Criteria) this;
        }

        public Criteria andStableSuccessIsNull() {
            addCriterion("stable_success is null");
            return (Criteria) this;
        }

        public Criteria andStableSuccessIsNotNull() {
            addCriterion("stable_success is not null");
            return (Criteria) this;
        }

        public Criteria andStableSuccessEqualTo(Integer value) {
            addCriterion("stable_success =", value, "stableSuccess");
            return (Criteria) this;
        }

        public Criteria andStableSuccessNotEqualTo(Integer value) {
            addCriterion("stable_success <>", value, "stableSuccess");
            return (Criteria) this;
        }

        public Criteria andStableSuccessGreaterThan(Integer value) {
            addCriterion("stable_success >", value, "stableSuccess");
            return (Criteria) this;
        }

        public Criteria andStableSuccessGreaterThanOrEqualTo(Integer value) {
            addCriterion("stable_success >=", value, "stableSuccess");
            return (Criteria) this;
        }

        public Criteria andStableSuccessLessThan(Integer value) {
            addCriterion("stable_success <", value, "stableSuccess");
            return (Criteria) this;
        }

        public Criteria andStableSuccessLessThanOrEqualTo(Integer value) {
            addCriterion("stable_success <=", value, "stableSuccess");
            return (Criteria) this;
        }

        public Criteria andStableSuccessIn(List<Integer> values) {
            addCriterion("stable_success in", values, "stableSuccess");
            return (Criteria) this;
        }

        public Criteria andStableSuccessNotIn(List<Integer> values) {
            addCriterion("stable_success not in", values, "stableSuccess");
            return (Criteria) this;
        }

        public Criteria andStableSuccessBetween(Integer value1, Integer value2) {
            addCriterion("stable_success between", value1, value2, "stableSuccess");
            return (Criteria) this;
        }

        public Criteria andStableSuccessNotBetween(Integer value1, Integer value2) {
            addCriterion("stable_success not between", value1, value2, "stableSuccess");
            return (Criteria) this;
        }

        public Criteria andStableTotalIsNull() {
            addCriterion("stable_total is null");
            return (Criteria) this;
        }

        public Criteria andStableTotalIsNotNull() {
            addCriterion("stable_total is not null");
            return (Criteria) this;
        }

        public Criteria andStableTotalEqualTo(Integer value) {
            addCriterion("stable_total =", value, "stableTotal");
            return (Criteria) this;
        }

        public Criteria andStableTotalNotEqualTo(Integer value) {
            addCriterion("stable_total <>", value, "stableTotal");
            return (Criteria) this;
        }

        public Criteria andStableTotalGreaterThan(Integer value) {
            addCriterion("stable_total >", value, "stableTotal");
            return (Criteria) this;
        }

        public Criteria andStableTotalGreaterThanOrEqualTo(Integer value) {
            addCriterion("stable_total >=", value, "stableTotal");
            return (Criteria) this;
        }

        public Criteria andStableTotalLessThan(Integer value) {
            addCriterion("stable_total <", value, "stableTotal");
            return (Criteria) this;
        }

        public Criteria andStableTotalLessThanOrEqualTo(Integer value) {
            addCriterion("stable_total <=", value, "stableTotal");
            return (Criteria) this;
        }

        public Criteria andStableTotalIn(List<Integer> values) {
            addCriterion("stable_total in", values, "stableTotal");
            return (Criteria) this;
        }

        public Criteria andStableTotalNotIn(List<Integer> values) {
            addCriterion("stable_total not in", values, "stableTotal");
            return (Criteria) this;
        }

        public Criteria andStableTotalBetween(Integer value1, Integer value2) {
            addCriterion("stable_total between", value1, value2, "stableTotal");
            return (Criteria) this;
        }

        public Criteria andStableTotalNotBetween(Integer value1, Integer value2) {
            addCriterion("stable_total not between", value1, value2, "stableTotal");
            return (Criteria) this;
        }

        public Criteria andAvalibleSuccessIsNull() {
            addCriterion("avalible_success is null");
            return (Criteria) this;
        }

        public Criteria andAvalibleSuccessIsNotNull() {
            addCriterion("avalible_success is not null");
            return (Criteria) this;
        }

        public Criteria andAvalibleSuccessEqualTo(Integer value) {
            addCriterion("avalible_success =", value, "avalibleSuccess");
            return (Criteria) this;
        }

        public Criteria andAvalibleSuccessNotEqualTo(Integer value) {
            addCriterion("avalible_success <>", value, "avalibleSuccess");
            return (Criteria) this;
        }

        public Criteria andAvalibleSuccessGreaterThan(Integer value) {
            addCriterion("avalible_success >", value, "avalibleSuccess");
            return (Criteria) this;
        }

        public Criteria andAvalibleSuccessGreaterThanOrEqualTo(Integer value) {
            addCriterion("avalible_success >=", value, "avalibleSuccess");
            return (Criteria) this;
        }

        public Criteria andAvalibleSuccessLessThan(Integer value) {
            addCriterion("avalible_success <", value, "avalibleSuccess");
            return (Criteria) this;
        }

        public Criteria andAvalibleSuccessLessThanOrEqualTo(Integer value) {
            addCriterion("avalible_success <=", value, "avalibleSuccess");
            return (Criteria) this;
        }

        public Criteria andAvalibleSuccessIn(List<Integer> values) {
            addCriterion("avalible_success in", values, "avalibleSuccess");
            return (Criteria) this;
        }

        public Criteria andAvalibleSuccessNotIn(List<Integer> values) {
            addCriterion("avalible_success not in", values, "avalibleSuccess");
            return (Criteria) this;
        }

        public Criteria andAvalibleSuccessBetween(Integer value1, Integer value2) {
            addCriterion("avalible_success between", value1, value2, "avalibleSuccess");
            return (Criteria) this;
        }

        public Criteria andAvalibleSuccessNotBetween(Integer value1, Integer value2) {
            addCriterion("avalible_success not between", value1, value2, "avalibleSuccess");
            return (Criteria) this;
        }

        public Criteria andAvalibleTotalIsNull() {
            addCriterion("avalible_total is null");
            return (Criteria) this;
        }

        public Criteria andAvalibleTotalIsNotNull() {
            addCriterion("avalible_total is not null");
            return (Criteria) this;
        }

        public Criteria andAvalibleTotalEqualTo(Integer value) {
            addCriterion("avalible_total =", value, "avalibleTotal");
            return (Criteria) this;
        }

        public Criteria andAvalibleTotalNotEqualTo(Integer value) {
            addCriterion("avalible_total <>", value, "avalibleTotal");
            return (Criteria) this;
        }

        public Criteria andAvalibleTotalGreaterThan(Integer value) {
            addCriterion("avalible_total >", value, "avalibleTotal");
            return (Criteria) this;
        }

        public Criteria andAvalibleTotalGreaterThanOrEqualTo(Integer value) {
            addCriterion("avalible_total >=", value, "avalibleTotal");
            return (Criteria) this;
        }

        public Criteria andAvalibleTotalLessThan(Integer value) {
            addCriterion("avalible_total <", value, "avalibleTotal");
            return (Criteria) this;
        }

        public Criteria andAvalibleTotalLessThanOrEqualTo(Integer value) {
            addCriterion("avalible_total <=", value, "avalibleTotal");
            return (Criteria) this;
        }

        public Criteria andAvalibleTotalIn(List<Integer> values) {
            addCriterion("avalible_total in", values, "avalibleTotal");
            return (Criteria) this;
        }

        public Criteria andAvalibleTotalNotIn(List<Integer> values) {
            addCriterion("avalible_total not in", values, "avalibleTotal");
            return (Criteria) this;
        }

        public Criteria andAvalibleTotalBetween(Integer value1, Integer value2) {
            addCriterion("avalible_total between", value1, value2, "avalibleTotal");
            return (Criteria) this;
        }

        public Criteria andAvalibleTotalNotBetween(Integer value1, Integer value2) {
            addCriterion("avalible_total not between", value1, value2, "avalibleTotal");
            return (Criteria) this;
        }

        public Criteria andTagIsNull() {
            addCriterion("tag is null");
            return (Criteria) this;
        }

        public Criteria andTagIsNotNull() {
            addCriterion("tag is not null");
            return (Criteria) this;
        }

        public Criteria andTagEqualTo(String value) {
            addCriterion("tag =", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagNotEqualTo(String value) {
            addCriterion("tag <>", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagGreaterThan(String value) {
            addCriterion("tag >", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagGreaterThanOrEqualTo(String value) {
            addCriterion("tag >=", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagLessThan(String value) {
            addCriterion("tag <", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagLessThanOrEqualTo(String value) {
            addCriterion("tag <=", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagLike(String value) {
            addCriterion("tag like", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagNotLike(String value) {
            addCriterion("tag not like", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagIn(List<String> values) {
            addCriterion("tag in", values, "tag");
            return (Criteria) this;
        }

        public Criteria andTagNotIn(List<String> values) {
            addCriterion("tag not in", values, "tag");
            return (Criteria) this;
        }

        public Criteria andTagBetween(String value1, String value2) {
            addCriterion("tag between", value1, value2, "tag");
            return (Criteria) this;
        }

        public Criteria andTagNotBetween(String value1, String value2) {
            addCriterion("tag not between", value1, value2, "tag");
            return (Criteria) this;
        }

        public Criteria andPersonIsNull() {
            addCriterion("person is null");
            return (Criteria) this;
        }

        public Criteria andPersonIsNotNull() {
            addCriterion("person is not null");
            return (Criteria) this;
        }

        public Criteria andPersonEqualTo(String value) {
            addCriterion("person =", value, "person");
            return (Criteria) this;
        }

        public Criteria andPersonNotEqualTo(String value) {
            addCriterion("person <>", value, "person");
            return (Criteria) this;
        }

        public Criteria andPersonGreaterThan(String value) {
            addCriterion("person >", value, "person");
            return (Criteria) this;
        }

        public Criteria andPersonGreaterThanOrEqualTo(String value) {
            addCriterion("person >=", value, "person");
            return (Criteria) this;
        }

        public Criteria andPersonLessThan(String value) {
            addCriterion("person <", value, "person");
            return (Criteria) this;
        }

        public Criteria andPersonLessThanOrEqualTo(String value) {
            addCriterion("person <=", value, "person");
            return (Criteria) this;
        }

        public Criteria andPersonLike(String value) {
            addCriterion("person like", value, "person");
            return (Criteria) this;
        }

        public Criteria andPersonNotLike(String value) {
            addCriterion("person not like", value, "person");
            return (Criteria) this;
        }

        public Criteria andPersonIn(List<String> values) {
            addCriterion("person in", values, "person");
            return (Criteria) this;
        }

        public Criteria andPersonNotIn(List<String> values) {
            addCriterion("person not in", values, "person");
            return (Criteria) this;
        }

        public Criteria andPersonBetween(String value1, String value2) {
            addCriterion("person between", value1, value2, "person");
            return (Criteria) this;
        }

        public Criteria andPersonNotBetween(String value1, String value2) {
            addCriterion("person not between", value1, value2, "person");
            return (Criteria) this;
        }

        public Criteria andDirectionIsNull() {
            addCriterion("direction is null");
            return (Criteria) this;
        }

        public Criteria andDirectionIsNotNull() {
            addCriterion("direction is not null");
            return (Criteria) this;
        }

        public Criteria andDirectionEqualTo(String value) {
            addCriterion("direction =", value, "direction");
            return (Criteria) this;
        }

        public Criteria andDirectionNotEqualTo(String value) {
            addCriterion("direction <>", value, "direction");
            return (Criteria) this;
        }

        public Criteria andDirectionGreaterThan(String value) {
            addCriterion("direction >", value, "direction");
            return (Criteria) this;
        }

        public Criteria andDirectionGreaterThanOrEqualTo(String value) {
            addCriterion("direction >=", value, "direction");
            return (Criteria) this;
        }

        public Criteria andDirectionLessThan(String value) {
            addCriterion("direction <", value, "direction");
            return (Criteria) this;
        }

        public Criteria andDirectionLessThanOrEqualTo(String value) {
            addCriterion("direction <=", value, "direction");
            return (Criteria) this;
        }

        public Criteria andDirectionLike(String value) {
            addCriterion("direction like", value, "direction");
            return (Criteria) this;
        }

        public Criteria andDirectionNotLike(String value) {
            addCriterion("direction not like", value, "direction");
            return (Criteria) this;
        }

        public Criteria andDirectionIn(List<String> values) {
            addCriterion("direction in", values, "direction");
            return (Criteria) this;
        }

        public Criteria andDirectionNotIn(List<String> values) {
            addCriterion("direction not in", values, "direction");
            return (Criteria) this;
        }

        public Criteria andDirectionBetween(String value1, String value2) {
            addCriterion("direction between", value1, value2, "direction");
            return (Criteria) this;
        }

        public Criteria andDirectionNotBetween(String value1, String value2) {
            addCriterion("direction not between", value1, value2, "direction");
            return (Criteria) this;
        }

        public Criteria andStableTagPercentageIsNull() {
            addCriterion("stable_tag_percentage is null");
            return (Criteria) this;
        }

        public Criteria andStableTagPercentageIsNotNull() {
            addCriterion("stable_tag_percentage is not null");
            return (Criteria) this;
        }

        public Criteria andStableTagPercentageEqualTo(String value) {
            addCriterion("stable_tag_percentage =", value, "stableTagPercentage");
            return (Criteria) this;
        }

        public Criteria andStableTagPercentageNotEqualTo(String value) {
            addCriterion("stable_tag_percentage <>", value, "stableTagPercentage");
            return (Criteria) this;
        }

        public Criteria andStableTagPercentageGreaterThan(String value) {
            addCriterion("stable_tag_percentage >", value, "stableTagPercentage");
            return (Criteria) this;
        }

        public Criteria andStableTagPercentageGreaterThanOrEqualTo(String value) {
            addCriterion("stable_tag_percentage >=", value, "stableTagPercentage");
            return (Criteria) this;
        }

        public Criteria andStableTagPercentageLessThan(String value) {
            addCriterion("stable_tag_percentage <", value, "stableTagPercentage");
            return (Criteria) this;
        }

        public Criteria andStableTagPercentageLessThanOrEqualTo(String value) {
            addCriterion("stable_tag_percentage <=", value, "stableTagPercentage");
            return (Criteria) this;
        }

        public Criteria andStableTagPercentageLike(String value) {
            addCriterion("stable_tag_percentage like", value, "stableTagPercentage");
            return (Criteria) this;
        }

        public Criteria andStableTagPercentageNotLike(String value) {
            addCriterion("stable_tag_percentage not like", value, "stableTagPercentage");
            return (Criteria) this;
        }

        public Criteria andStableTagPercentageIn(List<String> values) {
            addCriterion("stable_tag_percentage in", values, "stableTagPercentage");
            return (Criteria) this;
        }

        public Criteria andStableTagPercentageNotIn(List<String> values) {
            addCriterion("stable_tag_percentage not in", values, "stableTagPercentage");
            return (Criteria) this;
        }

        public Criteria andStableTagPercentageBetween(String value1, String value2) {
            addCriterion("stable_tag_percentage between", value1, value2, "stableTagPercentage");
            return (Criteria) this;
        }

        public Criteria andStableTagPercentageNotBetween(String value1, String value2) {
            addCriterion("stable_tag_percentage not between", value1, value2, "stableTagPercentage");
            return (Criteria) this;
        }

        public Criteria andAvalibleTagPercentageIsNull() {
            addCriterion("avalible_tag_percentage is null");
            return (Criteria) this;
        }

        public Criteria andAvalibleTagPercentageIsNotNull() {
            addCriterion("avalible_tag_percentage is not null");
            return (Criteria) this;
        }

        public Criteria andAvalibleTagPercentageEqualTo(String value) {
            addCriterion("avalible_tag_percentage =", value, "avalibleTagPercentage");
            return (Criteria) this;
        }

        public Criteria andAvalibleTagPercentageNotEqualTo(String value) {
            addCriterion("avalible_tag_percentage <>", value, "avalibleTagPercentage");
            return (Criteria) this;
        }

        public Criteria andAvalibleTagPercentageGreaterThan(String value) {
            addCriterion("avalible_tag_percentage >", value, "avalibleTagPercentage");
            return (Criteria) this;
        }

        public Criteria andAvalibleTagPercentageGreaterThanOrEqualTo(String value) {
            addCriterion("avalible_tag_percentage >=", value, "avalibleTagPercentage");
            return (Criteria) this;
        }

        public Criteria andAvalibleTagPercentageLessThan(String value) {
            addCriterion("avalible_tag_percentage <", value, "avalibleTagPercentage");
            return (Criteria) this;
        }

        public Criteria andAvalibleTagPercentageLessThanOrEqualTo(String value) {
            addCriterion("avalible_tag_percentage <=", value, "avalibleTagPercentage");
            return (Criteria) this;
        }

        public Criteria andAvalibleTagPercentageLike(String value) {
            addCriterion("avalible_tag_percentage like", value, "avalibleTagPercentage");
            return (Criteria) this;
        }

        public Criteria andAvalibleTagPercentageNotLike(String value) {
            addCriterion("avalible_tag_percentage not like", value, "avalibleTagPercentage");
            return (Criteria) this;
        }

        public Criteria andAvalibleTagPercentageIn(List<String> values) {
            addCriterion("avalible_tag_percentage in", values, "avalibleTagPercentage");
            return (Criteria) this;
        }

        public Criteria andAvalibleTagPercentageNotIn(List<String> values) {
            addCriterion("avalible_tag_percentage not in", values, "avalibleTagPercentage");
            return (Criteria) this;
        }

        public Criteria andAvalibleTagPercentageBetween(String value1, String value2) {
            addCriterion("avalible_tag_percentage between", value1, value2, "avalibleTagPercentage");
            return (Criteria) this;
        }

        public Criteria andAvalibleTagPercentageNotBetween(String value1, String value2) {
            addCriterion("avalible_tag_percentage not between", value1, value2, "avalibleTagPercentage");
            return (Criteria) this;
        }

        public Criteria andDateIsNull() {
            addCriterion("date is null");
            return (Criteria) this;
        }

        public Criteria andDateIsNotNull() {
            addCriterion("date is not null");
            return (Criteria) this;
        }

        public Criteria andDateEqualTo(Date value) {
            addCriterion("date =", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotEqualTo(Date value) {
            addCriterion("date <>", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateGreaterThan(Date value) {
            addCriterion("date >", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateGreaterThanOrEqualTo(Date value) {
            addCriterion("date >=", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateLessThan(Date value) {
            addCriterion("date <", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateLessThanOrEqualTo(Date value) {
            addCriterion("date <=", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateIn(List<Date> values) {
            addCriterion("date in", values, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotIn(List<Date> values) {
            addCriterion("date not in", values, "date");
            return (Criteria) this;
        }

        public Criteria andDateBetween(Date value1, Date value2) {
            addCriterion("date between", value1, value2, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotBetween(Date value1, Date value2) {
            addCriterion("date not between", value1, value2, "date");
            return (Criteria) this;
        }

        public Criteria andCommentIsNull() {
            addCriterion("comment is null");
            return (Criteria) this;
        }

        public Criteria andCommentIsNotNull() {
            addCriterion("comment is not null");
            return (Criteria) this;
        }

        public Criteria andCommentEqualTo(String value) {
            addCriterion("comment =", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotEqualTo(String value) {
            addCriterion("comment <>", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentGreaterThan(String value) {
            addCriterion("comment >", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentGreaterThanOrEqualTo(String value) {
            addCriterion("comment >=", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentLessThan(String value) {
            addCriterion("comment <", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentLessThanOrEqualTo(String value) {
            addCriterion("comment <=", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentLike(String value) {
            addCriterion("comment like", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotLike(String value) {
            addCriterion("comment not like", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentIn(List<String> values) {
            addCriterion("comment in", values, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotIn(List<String> values) {
            addCriterion("comment not in", values, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentBetween(String value1, String value2) {
            addCriterion("comment between", value1, value2, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotBetween(String value1, String value2) {
            addCriterion("comment not between", value1, value2, "comment");
            return (Criteria) this;
        }

        public Criteria andUpdatedDateIsNull() {
            addCriterion("updated_date is null");
            return (Criteria) this;
        }

        public Criteria andUpdatedDateIsNotNull() {
            addCriterion("updated_date is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatedDateEqualTo(Date value) {
            addCriterion("updated_date =", value, "updatedDate");
            return (Criteria) this;
        }

        public Criteria andUpdatedDateNotEqualTo(Date value) {
            addCriterion("updated_date <>", value, "updatedDate");
            return (Criteria) this;
        }

        public Criteria andUpdatedDateGreaterThan(Date value) {
            addCriterion("updated_date >", value, "updatedDate");
            return (Criteria) this;
        }

        public Criteria andUpdatedDateGreaterThanOrEqualTo(Date value) {
            addCriterion("updated_date >=", value, "updatedDate");
            return (Criteria) this;
        }

        public Criteria andUpdatedDateLessThan(Date value) {
            addCriterion("updated_date <", value, "updatedDate");
            return (Criteria) this;
        }

        public Criteria andUpdatedDateLessThanOrEqualTo(Date value) {
            addCriterion("updated_date <=", value, "updatedDate");
            return (Criteria) this;
        }

        public Criteria andUpdatedDateIn(List<Date> values) {
            addCriterion("updated_date in", values, "updatedDate");
            return (Criteria) this;
        }

        public Criteria andUpdatedDateNotIn(List<Date> values) {
            addCriterion("updated_date not in", values, "updatedDate");
            return (Criteria) this;
        }

        public Criteria andUpdatedDateBetween(Date value1, Date value2) {
            addCriterion("updated_date between", value1, value2, "updatedDate");
            return (Criteria) this;
        }

        public Criteria andUpdatedDateNotBetween(Date value1, Date value2) {
            addCriterion("updated_date not between", value1, value2, "updatedDate");
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