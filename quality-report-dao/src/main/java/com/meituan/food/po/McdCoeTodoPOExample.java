package com.meituan.food.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class McdCoeTodoPOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public McdCoeTodoPOExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
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

        public Criteria andCoeIdIsNull() {
            addCriterion("coe_id is null");
            return (Criteria) this;
        }

        public Criteria andCoeIdIsNotNull() {
            addCriterion("coe_id is not null");
            return (Criteria) this;
        }

        public Criteria andCoeIdEqualTo(Integer value) {
            addCriterion("coe_id =", value, "coeId");
            return (Criteria) this;
        }

        public Criteria andCoeIdNotEqualTo(Integer value) {
            addCriterion("coe_id <>", value, "coeId");
            return (Criteria) this;
        }

        public Criteria andCoeIdGreaterThan(Integer value) {
            addCriterion("coe_id >", value, "coeId");
            return (Criteria) this;
        }

        public Criteria andCoeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("coe_id >=", value, "coeId");
            return (Criteria) this;
        }

        public Criteria andCoeIdLessThan(Integer value) {
            addCriterion("coe_id <", value, "coeId");
            return (Criteria) this;
        }

        public Criteria andCoeIdLessThanOrEqualTo(Integer value) {
            addCriterion("coe_id <=", value, "coeId");
            return (Criteria) this;
        }

        public Criteria andCoeIdIn(List<Integer> values) {
            addCriterion("coe_id in", values, "coeId");
            return (Criteria) this;
        }

        public Criteria andCoeIdNotIn(List<Integer> values) {
            addCriterion("coe_id not in", values, "coeId");
            return (Criteria) this;
        }

        public Criteria andCoeIdBetween(Integer value1, Integer value2) {
            addCriterion("coe_id between", value1, value2, "coeId");
            return (Criteria) this;
        }

        public Criteria andCoeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("coe_id not between", value1, value2, "coeId");
            return (Criteria) this;
        }

        public Criteria andOnesIdIsNull() {
            addCriterion("ones_id is null");
            return (Criteria) this;
        }

        public Criteria andOnesIdIsNotNull() {
            addCriterion("ones_id is not null");
            return (Criteria) this;
        }

        public Criteria andOnesIdEqualTo(Integer value) {
            addCriterion("ones_id =", value, "onesId");
            return (Criteria) this;
        }

        public Criteria andOnesIdNotEqualTo(Integer value) {
            addCriterion("ones_id <>", value, "onesId");
            return (Criteria) this;
        }

        public Criteria andOnesIdGreaterThan(Integer value) {
            addCriterion("ones_id >", value, "onesId");
            return (Criteria) this;
        }

        public Criteria andOnesIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ones_id >=", value, "onesId");
            return (Criteria) this;
        }

        public Criteria andOnesIdLessThan(Integer value) {
            addCriterion("ones_id <", value, "onesId");
            return (Criteria) this;
        }

        public Criteria andOnesIdLessThanOrEqualTo(Integer value) {
            addCriterion("ones_id <=", value, "onesId");
            return (Criteria) this;
        }

        public Criteria andOnesIdIn(List<Integer> values) {
            addCriterion("ones_id in", values, "onesId");
            return (Criteria) this;
        }

        public Criteria andOnesIdNotIn(List<Integer> values) {
            addCriterion("ones_id not in", values, "onesId");
            return (Criteria) this;
        }

        public Criteria andOnesIdBetween(Integer value1, Integer value2) {
            addCriterion("ones_id between", value1, value2, "onesId");
            return (Criteria) this;
        }

        public Criteria andOnesIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ones_id not between", value1, value2, "onesId");
            return (Criteria) this;
        }

        public Criteria andOnesLinkIsNull() {
            addCriterion("ones_link is null");
            return (Criteria) this;
        }

        public Criteria andOnesLinkIsNotNull() {
            addCriterion("ones_link is not null");
            return (Criteria) this;
        }

        public Criteria andOnesLinkEqualTo(String value) {
            addCriterion("ones_link =", value, "onesLink");
            return (Criteria) this;
        }

        public Criteria andOnesLinkNotEqualTo(String value) {
            addCriterion("ones_link <>", value, "onesLink");
            return (Criteria) this;
        }

        public Criteria andOnesLinkGreaterThan(String value) {
            addCriterion("ones_link >", value, "onesLink");
            return (Criteria) this;
        }

        public Criteria andOnesLinkGreaterThanOrEqualTo(String value) {
            addCriterion("ones_link >=", value, "onesLink");
            return (Criteria) this;
        }

        public Criteria andOnesLinkLessThan(String value) {
            addCriterion("ones_link <", value, "onesLink");
            return (Criteria) this;
        }

        public Criteria andOnesLinkLessThanOrEqualTo(String value) {
            addCriterion("ones_link <=", value, "onesLink");
            return (Criteria) this;
        }

        public Criteria andOnesLinkLike(String value) {
            addCriterion("ones_link like", value, "onesLink");
            return (Criteria) this;
        }

        public Criteria andOnesLinkNotLike(String value) {
            addCriterion("ones_link not like", value, "onesLink");
            return (Criteria) this;
        }

        public Criteria andOnesLinkIn(List<String> values) {
            addCriterion("ones_link in", values, "onesLink");
            return (Criteria) this;
        }

        public Criteria andOnesLinkNotIn(List<String> values) {
            addCriterion("ones_link not in", values, "onesLink");
            return (Criteria) this;
        }

        public Criteria andOnesLinkBetween(String value1, String value2) {
            addCriterion("ones_link between", value1, value2, "onesLink");
            return (Criteria) this;
        }

        public Criteria andOnesLinkNotBetween(String value1, String value2) {
            addCriterion("ones_link not between", value1, value2, "onesLink");
            return (Criteria) this;
        }

        public Criteria andOnesTitleIsNull() {
            addCriterion("ones_title is null");
            return (Criteria) this;
        }

        public Criteria andOnesTitleIsNotNull() {
            addCriterion("ones_title is not null");
            return (Criteria) this;
        }

        public Criteria andOnesTitleEqualTo(String value) {
            addCriterion("ones_title =", value, "onesTitle");
            return (Criteria) this;
        }

        public Criteria andOnesTitleNotEqualTo(String value) {
            addCriterion("ones_title <>", value, "onesTitle");
            return (Criteria) this;
        }

        public Criteria andOnesTitleGreaterThan(String value) {
            addCriterion("ones_title >", value, "onesTitle");
            return (Criteria) this;
        }

        public Criteria andOnesTitleGreaterThanOrEqualTo(String value) {
            addCriterion("ones_title >=", value, "onesTitle");
            return (Criteria) this;
        }

        public Criteria andOnesTitleLessThan(String value) {
            addCriterion("ones_title <", value, "onesTitle");
            return (Criteria) this;
        }

        public Criteria andOnesTitleLessThanOrEqualTo(String value) {
            addCriterion("ones_title <=", value, "onesTitle");
            return (Criteria) this;
        }

        public Criteria andOnesTitleLike(String value) {
            addCriterion("ones_title like", value, "onesTitle");
            return (Criteria) this;
        }

        public Criteria andOnesTitleNotLike(String value) {
            addCriterion("ones_title not like", value, "onesTitle");
            return (Criteria) this;
        }

        public Criteria andOnesTitleIn(List<String> values) {
            addCriterion("ones_title in", values, "onesTitle");
            return (Criteria) this;
        }

        public Criteria andOnesTitleNotIn(List<String> values) {
            addCriterion("ones_title not in", values, "onesTitle");
            return (Criteria) this;
        }

        public Criteria andOnesTitleBetween(String value1, String value2) {
            addCriterion("ones_title between", value1, value2, "onesTitle");
            return (Criteria) this;
        }

        public Criteria andOnesTitleNotBetween(String value1, String value2) {
            addCriterion("ones_title not between", value1, value2, "onesTitle");
            return (Criteria) this;
        }

        public Criteria andIsFinishIsNull() {
            addCriterion("is_finish is null");
            return (Criteria) this;
        }

        public Criteria andIsFinishIsNotNull() {
            addCriterion("is_finish is not null");
            return (Criteria) this;
        }

        public Criteria andIsFinishEqualTo(Boolean value) {
            addCriterion("is_finish =", value, "isFinish");
            return (Criteria) this;
        }

        public Criteria andIsFinishNotEqualTo(Boolean value) {
            addCriterion("is_finish <>", value, "isFinish");
            return (Criteria) this;
        }

        public Criteria andIsFinishGreaterThan(Boolean value) {
            addCriterion("is_finish >", value, "isFinish");
            return (Criteria) this;
        }

        public Criteria andIsFinishGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_finish >=", value, "isFinish");
            return (Criteria) this;
        }

        public Criteria andIsFinishLessThan(Boolean value) {
            addCriterion("is_finish <", value, "isFinish");
            return (Criteria) this;
        }

        public Criteria andIsFinishLessThanOrEqualTo(Boolean value) {
            addCriterion("is_finish <=", value, "isFinish");
            return (Criteria) this;
        }

        public Criteria andIsFinishIn(List<Boolean> values) {
            addCriterion("is_finish in", values, "isFinish");
            return (Criteria) this;
        }

        public Criteria andIsFinishNotIn(List<Boolean> values) {
            addCriterion("is_finish not in", values, "isFinish");
            return (Criteria) this;
        }

        public Criteria andIsFinishBetween(Boolean value1, Boolean value2) {
            addCriterion("is_finish between", value1, value2, "isFinish");
            return (Criteria) this;
        }

        public Criteria andIsFinishNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_finish not between", value1, value2, "isFinish");
            return (Criteria) this;
        }

        public Criteria andIsDelayIsNull() {
            addCriterion("is_delay is null");
            return (Criteria) this;
        }

        public Criteria andIsDelayIsNotNull() {
            addCriterion("is_delay is not null");
            return (Criteria) this;
        }

        public Criteria andIsDelayEqualTo(Boolean value) {
            addCriterion("is_delay =", value, "isDelay");
            return (Criteria) this;
        }

        public Criteria andIsDelayNotEqualTo(Boolean value) {
            addCriterion("is_delay <>", value, "isDelay");
            return (Criteria) this;
        }

        public Criteria andIsDelayGreaterThan(Boolean value) {
            addCriterion("is_delay >", value, "isDelay");
            return (Criteria) this;
        }

        public Criteria andIsDelayGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_delay >=", value, "isDelay");
            return (Criteria) this;
        }

        public Criteria andIsDelayLessThan(Boolean value) {
            addCriterion("is_delay <", value, "isDelay");
            return (Criteria) this;
        }

        public Criteria andIsDelayLessThanOrEqualTo(Boolean value) {
            addCriterion("is_delay <=", value, "isDelay");
            return (Criteria) this;
        }

        public Criteria andIsDelayIn(List<Boolean> values) {
            addCriterion("is_delay in", values, "isDelay");
            return (Criteria) this;
        }

        public Criteria andIsDelayNotIn(List<Boolean> values) {
            addCriterion("is_delay not in", values, "isDelay");
            return (Criteria) this;
        }

        public Criteria andIsDelayBetween(Boolean value1, Boolean value2) {
            addCriterion("is_delay between", value1, value2, "isDelay");
            return (Criteria) this;
        }

        public Criteria andIsDelayNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_delay not between", value1, value2, "isDelay");
            return (Criteria) this;
        }

        public Criteria andDeallineIsNull() {
            addCriterion("dealline is null");
            return (Criteria) this;
        }

        public Criteria andDeallineIsNotNull() {
            addCriterion("dealline is not null");
            return (Criteria) this;
        }

        public Criteria andDeallineEqualTo(Date value) {
            addCriterionForJDBCDate("dealline =", value, "dealline");
            return (Criteria) this;
        }

        public Criteria andDeallineNotEqualTo(Date value) {
            addCriterionForJDBCDate("dealline <>", value, "dealline");
            return (Criteria) this;
        }

        public Criteria andDeallineGreaterThan(Date value) {
            addCriterionForJDBCDate("dealline >", value, "dealline");
            return (Criteria) this;
        }

        public Criteria andDeallineGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("dealline >=", value, "dealline");
            return (Criteria) this;
        }

        public Criteria andDeallineLessThan(Date value) {
            addCriterionForJDBCDate("dealline <", value, "dealline");
            return (Criteria) this;
        }

        public Criteria andDeallineLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("dealline <=", value, "dealline");
            return (Criteria) this;
        }

        public Criteria andDeallineIn(List<Date> values) {
            addCriterionForJDBCDate("dealline in", values, "dealline");
            return (Criteria) this;
        }

        public Criteria andDeallineNotIn(List<Date> values) {
            addCriterionForJDBCDate("dealline not in", values, "dealline");
            return (Criteria) this;
        }

        public Criteria andDeallineBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("dealline between", value1, value2, "dealline");
            return (Criteria) this;
        }

        public Criteria andDeallineNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("dealline not between", value1, value2, "dealline");
            return (Criteria) this;
        }

        public Criteria andOwnerMisIsNull() {
            addCriterion("owner_mis is null");
            return (Criteria) this;
        }

        public Criteria andOwnerMisIsNotNull() {
            addCriterion("owner_mis is not null");
            return (Criteria) this;
        }

        public Criteria andOwnerMisEqualTo(String value) {
            addCriterion("owner_mis =", value, "ownerMis");
            return (Criteria) this;
        }

        public Criteria andOwnerMisNotEqualTo(String value) {
            addCriterion("owner_mis <>", value, "ownerMis");
            return (Criteria) this;
        }

        public Criteria andOwnerMisGreaterThan(String value) {
            addCriterion("owner_mis >", value, "ownerMis");
            return (Criteria) this;
        }

        public Criteria andOwnerMisGreaterThanOrEqualTo(String value) {
            addCriterion("owner_mis >=", value, "ownerMis");
            return (Criteria) this;
        }

        public Criteria andOwnerMisLessThan(String value) {
            addCriterion("owner_mis <", value, "ownerMis");
            return (Criteria) this;
        }

        public Criteria andOwnerMisLessThanOrEqualTo(String value) {
            addCriterion("owner_mis <=", value, "ownerMis");
            return (Criteria) this;
        }

        public Criteria andOwnerMisLike(String value) {
            addCriterion("owner_mis like", value, "ownerMis");
            return (Criteria) this;
        }

        public Criteria andOwnerMisNotLike(String value) {
            addCriterion("owner_mis not like", value, "ownerMis");
            return (Criteria) this;
        }

        public Criteria andOwnerMisIn(List<String> values) {
            addCriterion("owner_mis in", values, "ownerMis");
            return (Criteria) this;
        }

        public Criteria andOwnerMisNotIn(List<String> values) {
            addCriterion("owner_mis not in", values, "ownerMis");
            return (Criteria) this;
        }

        public Criteria andOwnerMisBetween(String value1, String value2) {
            addCriterion("owner_mis between", value1, value2, "ownerMis");
            return (Criteria) this;
        }

        public Criteria andOwnerMisNotBetween(String value1, String value2) {
            addCriterion("owner_mis not between", value1, value2, "ownerMis");
            return (Criteria) this;
        }

        public Criteria andOwnerNameIsNull() {
            addCriterion("owner_name is null");
            return (Criteria) this;
        }

        public Criteria andOwnerNameIsNotNull() {
            addCriterion("owner_name is not null");
            return (Criteria) this;
        }

        public Criteria andOwnerNameEqualTo(String value) {
            addCriterion("owner_name =", value, "ownerName");
            return (Criteria) this;
        }

        public Criteria andOwnerNameNotEqualTo(String value) {
            addCriterion("owner_name <>", value, "ownerName");
            return (Criteria) this;
        }

        public Criteria andOwnerNameGreaterThan(String value) {
            addCriterion("owner_name >", value, "ownerName");
            return (Criteria) this;
        }

        public Criteria andOwnerNameGreaterThanOrEqualTo(String value) {
            addCriterion("owner_name >=", value, "ownerName");
            return (Criteria) this;
        }

        public Criteria andOwnerNameLessThan(String value) {
            addCriterion("owner_name <", value, "ownerName");
            return (Criteria) this;
        }

        public Criteria andOwnerNameLessThanOrEqualTo(String value) {
            addCriterion("owner_name <=", value, "ownerName");
            return (Criteria) this;
        }

        public Criteria andOwnerNameLike(String value) {
            addCriterion("owner_name like", value, "ownerName");
            return (Criteria) this;
        }

        public Criteria andOwnerNameNotLike(String value) {
            addCriterion("owner_name not like", value, "ownerName");
            return (Criteria) this;
        }

        public Criteria andOwnerNameIn(List<String> values) {
            addCriterion("owner_name in", values, "ownerName");
            return (Criteria) this;
        }

        public Criteria andOwnerNameNotIn(List<String> values) {
            addCriterion("owner_name not in", values, "ownerName");
            return (Criteria) this;
        }

        public Criteria andOwnerNameBetween(String value1, String value2) {
            addCriterion("owner_name between", value1, value2, "ownerName");
            return (Criteria) this;
        }

        public Criteria andOwnerNameNotBetween(String value1, String value2) {
            addCriterion("owner_name not between", value1, value2, "ownerName");
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

        public Criteria andStartDateEqualTo(Date value) {
            addCriterionForJDBCDate("start_date =", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("start_date <>", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateGreaterThan(Date value) {
            addCriterionForJDBCDate("start_date >", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("start_date >=", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateLessThan(Date value) {
            addCriterionForJDBCDate("start_date <", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("start_date <=", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateIn(List<Date> values) {
            addCriterionForJDBCDate("start_date in", values, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("start_date not in", values, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("start_date between", value1, value2, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("start_date not between", value1, value2, "startDate");
            return (Criteria) this;
        }

        public Criteria andActualDateIsNull() {
            addCriterion("actual_date is null");
            return (Criteria) this;
        }

        public Criteria andActualDateIsNotNull() {
            addCriterion("actual_date is not null");
            return (Criteria) this;
        }

        public Criteria andActualDateEqualTo(Date value) {
            addCriterionForJDBCDate("actual_date =", value, "actualDate");
            return (Criteria) this;
        }

        public Criteria andActualDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("actual_date <>", value, "actualDate");
            return (Criteria) this;
        }

        public Criteria andActualDateGreaterThan(Date value) {
            addCriterionForJDBCDate("actual_date >", value, "actualDate");
            return (Criteria) this;
        }

        public Criteria andActualDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("actual_date >=", value, "actualDate");
            return (Criteria) this;
        }

        public Criteria andActualDateLessThan(Date value) {
            addCriterionForJDBCDate("actual_date <", value, "actualDate");
            return (Criteria) this;
        }

        public Criteria andActualDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("actual_date <=", value, "actualDate");
            return (Criteria) this;
        }

        public Criteria andActualDateIn(List<Date> values) {
            addCriterionForJDBCDate("actual_date in", values, "actualDate");
            return (Criteria) this;
        }

        public Criteria andActualDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("actual_date not in", values, "actualDate");
            return (Criteria) this;
        }

        public Criteria andActualDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("actual_date between", value1, value2, "actualDate");
            return (Criteria) this;
        }

        public Criteria andActualDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("actual_date not between", value1, value2, "actualDate");
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