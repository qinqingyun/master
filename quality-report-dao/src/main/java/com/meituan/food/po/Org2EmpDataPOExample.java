package com.meituan.food.po;

import java.util.ArrayList;
import java.util.List;

public class Org2EmpDataPOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public Org2EmpDataPOExample() {
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

        public Criteria andEmpidIsNull() {
            addCriterion("empId is null");
            return (Criteria) this;
        }

        public Criteria andEmpidIsNotNull() {
            addCriterion("empId is not null");
            return (Criteria) this;
        }

        public Criteria andEmpidEqualTo(String value) {
            addCriterion("empId =", value, "empid");
            return (Criteria) this;
        }

        public Criteria andEmpidNotEqualTo(String value) {
            addCriterion("empId <>", value, "empid");
            return (Criteria) this;
        }

        public Criteria andEmpidGreaterThan(String value) {
            addCriterion("empId >", value, "empid");
            return (Criteria) this;
        }

        public Criteria andEmpidGreaterThanOrEqualTo(String value) {
            addCriterion("empId >=", value, "empid");
            return (Criteria) this;
        }

        public Criteria andEmpidLessThan(String value) {
            addCriterion("empId <", value, "empid");
            return (Criteria) this;
        }

        public Criteria andEmpidLessThanOrEqualTo(String value) {
            addCriterion("empId <=", value, "empid");
            return (Criteria) this;
        }

        public Criteria andEmpidLike(String value) {
            addCriterion("empId like", value, "empid");
            return (Criteria) this;
        }

        public Criteria andEmpidNotLike(String value) {
            addCriterion("empId not like", value, "empid");
            return (Criteria) this;
        }

        public Criteria andEmpidIn(List<String> values) {
            addCriterion("empId in", values, "empid");
            return (Criteria) this;
        }

        public Criteria andEmpidNotIn(List<String> values) {
            addCriterion("empId not in", values, "empid");
            return (Criteria) this;
        }

        public Criteria andEmpidBetween(String value1, String value2) {
            addCriterion("empId between", value1, value2, "empid");
            return (Criteria) this;
        }

        public Criteria andEmpidNotBetween(String value1, String value2) {
            addCriterion("empId not between", value1, value2, "empid");
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

        public Criteria andReportempidIsNull() {
            addCriterion("reportEmpId is null");
            return (Criteria) this;
        }

        public Criteria andReportempidIsNotNull() {
            addCriterion("reportEmpId is not null");
            return (Criteria) this;
        }

        public Criteria andReportempidEqualTo(String value) {
            addCriterion("reportEmpId =", value, "reportempid");
            return (Criteria) this;
        }

        public Criteria andReportempidNotEqualTo(String value) {
            addCriterion("reportEmpId <>", value, "reportempid");
            return (Criteria) this;
        }

        public Criteria andReportempidGreaterThan(String value) {
            addCriterion("reportEmpId >", value, "reportempid");
            return (Criteria) this;
        }

        public Criteria andReportempidGreaterThanOrEqualTo(String value) {
            addCriterion("reportEmpId >=", value, "reportempid");
            return (Criteria) this;
        }

        public Criteria andReportempidLessThan(String value) {
            addCriterion("reportEmpId <", value, "reportempid");
            return (Criteria) this;
        }

        public Criteria andReportempidLessThanOrEqualTo(String value) {
            addCriterion("reportEmpId <=", value, "reportempid");
            return (Criteria) this;
        }

        public Criteria andReportempidLike(String value) {
            addCriterion("reportEmpId like", value, "reportempid");
            return (Criteria) this;
        }

        public Criteria andReportempidNotLike(String value) {
            addCriterion("reportEmpId not like", value, "reportempid");
            return (Criteria) this;
        }

        public Criteria andReportempidIn(List<String> values) {
            addCriterion("reportEmpId in", values, "reportempid");
            return (Criteria) this;
        }

        public Criteria andReportempidNotIn(List<String> values) {
            addCriterion("reportEmpId not in", values, "reportempid");
            return (Criteria) this;
        }

        public Criteria andReportempidBetween(String value1, String value2) {
            addCriterion("reportEmpId between", value1, value2, "reportempid");
            return (Criteria) this;
        }

        public Criteria andReportempidNotBetween(String value1, String value2) {
            addCriterion("reportEmpId not between", value1, value2, "reportempid");
            return (Criteria) this;
        }

        public Criteria andReportempnameIsNull() {
            addCriterion("reportEmpName is null");
            return (Criteria) this;
        }

        public Criteria andReportempnameIsNotNull() {
            addCriterion("reportEmpName is not null");
            return (Criteria) this;
        }

        public Criteria andReportempnameEqualTo(String value) {
            addCriterion("reportEmpName =", value, "reportempname");
            return (Criteria) this;
        }

        public Criteria andReportempnameNotEqualTo(String value) {
            addCriterion("reportEmpName <>", value, "reportempname");
            return (Criteria) this;
        }

        public Criteria andReportempnameGreaterThan(String value) {
            addCriterion("reportEmpName >", value, "reportempname");
            return (Criteria) this;
        }

        public Criteria andReportempnameGreaterThanOrEqualTo(String value) {
            addCriterion("reportEmpName >=", value, "reportempname");
            return (Criteria) this;
        }

        public Criteria andReportempnameLessThan(String value) {
            addCriterion("reportEmpName <", value, "reportempname");
            return (Criteria) this;
        }

        public Criteria andReportempnameLessThanOrEqualTo(String value) {
            addCriterion("reportEmpName <=", value, "reportempname");
            return (Criteria) this;
        }

        public Criteria andReportempnameLike(String value) {
            addCriterion("reportEmpName like", value, "reportempname");
            return (Criteria) this;
        }

        public Criteria andReportempnameNotLike(String value) {
            addCriterion("reportEmpName not like", value, "reportempname");
            return (Criteria) this;
        }

        public Criteria andReportempnameIn(List<String> values) {
            addCriterion("reportEmpName in", values, "reportempname");
            return (Criteria) this;
        }

        public Criteria andReportempnameNotIn(List<String> values) {
            addCriterion("reportEmpName not in", values, "reportempname");
            return (Criteria) this;
        }

        public Criteria andReportempnameBetween(String value1, String value2) {
            addCriterion("reportEmpName between", value1, value2, "reportempname");
            return (Criteria) this;
        }

        public Criteria andReportempnameNotBetween(String value1, String value2) {
            addCriterion("reportEmpName not between", value1, value2, "reportempname");
            return (Criteria) this;
        }

        public Criteria andOrgidIsNull() {
            addCriterion("orgId is null");
            return (Criteria) this;
        }

        public Criteria andOrgidIsNotNull() {
            addCriterion("orgId is not null");
            return (Criteria) this;
        }

        public Criteria andOrgidEqualTo(String value) {
            addCriterion("orgId =", value, "orgid");
            return (Criteria) this;
        }

        public Criteria andOrgidNotEqualTo(String value) {
            addCriterion("orgId <>", value, "orgid");
            return (Criteria) this;
        }

        public Criteria andOrgidGreaterThan(String value) {
            addCriterion("orgId >", value, "orgid");
            return (Criteria) this;
        }

        public Criteria andOrgidGreaterThanOrEqualTo(String value) {
            addCriterion("orgId >=", value, "orgid");
            return (Criteria) this;
        }

        public Criteria andOrgidLessThan(String value) {
            addCriterion("orgId <", value, "orgid");
            return (Criteria) this;
        }

        public Criteria andOrgidLessThanOrEqualTo(String value) {
            addCriterion("orgId <=", value, "orgid");
            return (Criteria) this;
        }

        public Criteria andOrgidLike(String value) {
            addCriterion("orgId like", value, "orgid");
            return (Criteria) this;
        }

        public Criteria andOrgidNotLike(String value) {
            addCriterion("orgId not like", value, "orgid");
            return (Criteria) this;
        }

        public Criteria andOrgidIn(List<String> values) {
            addCriterion("orgId in", values, "orgid");
            return (Criteria) this;
        }

        public Criteria andOrgidNotIn(List<String> values) {
            addCriterion("orgId not in", values, "orgid");
            return (Criteria) this;
        }

        public Criteria andOrgidBetween(String value1, String value2) {
            addCriterion("orgId between", value1, value2, "orgid");
            return (Criteria) this;
        }

        public Criteria andOrgidNotBetween(String value1, String value2) {
            addCriterion("orgId not between", value1, value2, "orgid");
            return (Criteria) this;
        }

        public Criteria andOrgnameIsNull() {
            addCriterion("orgName is null");
            return (Criteria) this;
        }

        public Criteria andOrgnameIsNotNull() {
            addCriterion("orgName is not null");
            return (Criteria) this;
        }

        public Criteria andOrgnameEqualTo(String value) {
            addCriterion("orgName =", value, "orgname");
            return (Criteria) this;
        }

        public Criteria andOrgnameNotEqualTo(String value) {
            addCriterion("orgName <>", value, "orgname");
            return (Criteria) this;
        }

        public Criteria andOrgnameGreaterThan(String value) {
            addCriterion("orgName >", value, "orgname");
            return (Criteria) this;
        }

        public Criteria andOrgnameGreaterThanOrEqualTo(String value) {
            addCriterion("orgName >=", value, "orgname");
            return (Criteria) this;
        }

        public Criteria andOrgnameLessThan(String value) {
            addCriterion("orgName <", value, "orgname");
            return (Criteria) this;
        }

        public Criteria andOrgnameLessThanOrEqualTo(String value) {
            addCriterion("orgName <=", value, "orgname");
            return (Criteria) this;
        }

        public Criteria andOrgnameLike(String value) {
            addCriterion("orgName like", value, "orgname");
            return (Criteria) this;
        }

        public Criteria andOrgnameNotLike(String value) {
            addCriterion("orgName not like", value, "orgname");
            return (Criteria) this;
        }

        public Criteria andOrgnameIn(List<String> values) {
            addCriterion("orgName in", values, "orgname");
            return (Criteria) this;
        }

        public Criteria andOrgnameNotIn(List<String> values) {
            addCriterion("orgName not in", values, "orgname");
            return (Criteria) this;
        }

        public Criteria andOrgnameBetween(String value1, String value2) {
            addCriterion("orgName between", value1, value2, "orgname");
            return (Criteria) this;
        }

        public Criteria andOrgnameNotBetween(String value1, String value2) {
            addCriterion("orgName not between", value1, value2, "orgname");
            return (Criteria) this;
        }

        public Criteria andVirtualreportempidIsNull() {
            addCriterion("virtualreportEmpId is null");
            return (Criteria) this;
        }

        public Criteria andVirtualreportempidIsNotNull() {
            addCriterion("virtualreportEmpId is not null");
            return (Criteria) this;
        }

        public Criteria andVirtualreportempidEqualTo(String value) {
            addCriterion("virtualreportEmpId =", value, "virtualreportempid");
            return (Criteria) this;
        }

        public Criteria andVirtualreportempidNotEqualTo(String value) {
            addCriterion("virtualreportEmpId <>", value, "virtualreportempid");
            return (Criteria) this;
        }

        public Criteria andVirtualreportempidGreaterThan(String value) {
            addCriterion("virtualreportEmpId >", value, "virtualreportempid");
            return (Criteria) this;
        }

        public Criteria andVirtualreportempidGreaterThanOrEqualTo(String value) {
            addCriterion("virtualreportEmpId >=", value, "virtualreportempid");
            return (Criteria) this;
        }

        public Criteria andVirtualreportempidLessThan(String value) {
            addCriterion("virtualreportEmpId <", value, "virtualreportempid");
            return (Criteria) this;
        }

        public Criteria andVirtualreportempidLessThanOrEqualTo(String value) {
            addCriterion("virtualreportEmpId <=", value, "virtualreportempid");
            return (Criteria) this;
        }

        public Criteria andVirtualreportempidLike(String value) {
            addCriterion("virtualreportEmpId like", value, "virtualreportempid");
            return (Criteria) this;
        }

        public Criteria andVirtualreportempidNotLike(String value) {
            addCriterion("virtualreportEmpId not like", value, "virtualreportempid");
            return (Criteria) this;
        }

        public Criteria andVirtualreportempidIn(List<String> values) {
            addCriterion("virtualreportEmpId in", values, "virtualreportempid");
            return (Criteria) this;
        }

        public Criteria andVirtualreportempidNotIn(List<String> values) {
            addCriterion("virtualreportEmpId not in", values, "virtualreportempid");
            return (Criteria) this;
        }

        public Criteria andVirtualreportempidBetween(String value1, String value2) {
            addCriterion("virtualreportEmpId between", value1, value2, "virtualreportempid");
            return (Criteria) this;
        }

        public Criteria andVirtualreportempidNotBetween(String value1, String value2) {
            addCriterion("virtualreportEmpId not between", value1, value2, "virtualreportempid");
            return (Criteria) this;
        }

        public Criteria andVirtualreportempnameIsNull() {
            addCriterion("virtualreportEmpName is null");
            return (Criteria) this;
        }

        public Criteria andVirtualreportempnameIsNotNull() {
            addCriterion("virtualreportEmpName is not null");
            return (Criteria) this;
        }

        public Criteria andVirtualreportempnameEqualTo(String value) {
            addCriterion("virtualreportEmpName =", value, "virtualreportempname");
            return (Criteria) this;
        }

        public Criteria andVirtualreportempnameNotEqualTo(String value) {
            addCriterion("virtualreportEmpName <>", value, "virtualreportempname");
            return (Criteria) this;
        }

        public Criteria andVirtualreportempnameGreaterThan(String value) {
            addCriterion("virtualreportEmpName >", value, "virtualreportempname");
            return (Criteria) this;
        }

        public Criteria andVirtualreportempnameGreaterThanOrEqualTo(String value) {
            addCriterion("virtualreportEmpName >=", value, "virtualreportempname");
            return (Criteria) this;
        }

        public Criteria andVirtualreportempnameLessThan(String value) {
            addCriterion("virtualreportEmpName <", value, "virtualreportempname");
            return (Criteria) this;
        }

        public Criteria andVirtualreportempnameLessThanOrEqualTo(String value) {
            addCriterion("virtualreportEmpName <=", value, "virtualreportempname");
            return (Criteria) this;
        }

        public Criteria andVirtualreportempnameLike(String value) {
            addCriterion("virtualreportEmpName like", value, "virtualreportempname");
            return (Criteria) this;
        }

        public Criteria andVirtualreportempnameNotLike(String value) {
            addCriterion("virtualreportEmpName not like", value, "virtualreportempname");
            return (Criteria) this;
        }

        public Criteria andVirtualreportempnameIn(List<String> values) {
            addCriterion("virtualreportEmpName in", values, "virtualreportempname");
            return (Criteria) this;
        }

        public Criteria andVirtualreportempnameNotIn(List<String> values) {
            addCriterion("virtualreportEmpName not in", values, "virtualreportempname");
            return (Criteria) this;
        }

        public Criteria andVirtualreportempnameBetween(String value1, String value2) {
            addCriterion("virtualreportEmpName between", value1, value2, "virtualreportempname");
            return (Criteria) this;
        }

        public Criteria andVirtualreportempnameNotBetween(String value1, String value2) {
            addCriterion("virtualreportEmpName not between", value1, value2, "virtualreportempname");
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

        public Criteria andUpdatedDateEqualTo(String value) {
            addCriterion("updated_date =", value, "updatedDate");
            return (Criteria) this;
        }

        public Criteria andUpdatedDateNotEqualTo(String value) {
            addCriterion("updated_date <>", value, "updatedDate");
            return (Criteria) this;
        }

        public Criteria andUpdatedDateGreaterThan(String value) {
            addCriterion("updated_date >", value, "updatedDate");
            return (Criteria) this;
        }

        public Criteria andUpdatedDateGreaterThanOrEqualTo(String value) {
            addCriterion("updated_date >=", value, "updatedDate");
            return (Criteria) this;
        }

        public Criteria andUpdatedDateLessThan(String value) {
            addCriterion("updated_date <", value, "updatedDate");
            return (Criteria) this;
        }

        public Criteria andUpdatedDateLessThanOrEqualTo(String value) {
            addCriterion("updated_date <=", value, "updatedDate");
            return (Criteria) this;
        }

        public Criteria andUpdatedDateLike(String value) {
            addCriterion("updated_date like", value, "updatedDate");
            return (Criteria) this;
        }

        public Criteria andUpdatedDateNotLike(String value) {
            addCriterion("updated_date not like", value, "updatedDate");
            return (Criteria) this;
        }

        public Criteria andUpdatedDateIn(List<String> values) {
            addCriterion("updated_date in", values, "updatedDate");
            return (Criteria) this;
        }

        public Criteria andUpdatedDateNotIn(List<String> values) {
            addCriterion("updated_date not in", values, "updatedDate");
            return (Criteria) this;
        }

        public Criteria andUpdatedDateBetween(String value1, String value2) {
            addCriterion("updated_date between", value1, value2, "updatedDate");
            return (Criteria) this;
        }

        public Criteria andUpdatedDateNotBetween(String value1, String value2) {
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