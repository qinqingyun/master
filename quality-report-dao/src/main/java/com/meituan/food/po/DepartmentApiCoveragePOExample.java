package com.meituan.food.po;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class DepartmentApiCoveragePOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DepartmentApiCoveragePOExample() {
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

        public Criteria andDepartmentIdIsNull() {
            addCriterion("department_id is null");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdIsNotNull() {
            addCriterion("department_id is not null");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdEqualTo(Integer value) {
            addCriterion("department_id =", value, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdNotEqualTo(Integer value) {
            addCriterion("department_id <>", value, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdGreaterThan(Integer value) {
            addCriterion("department_id >", value, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("department_id >=", value, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdLessThan(Integer value) {
            addCriterion("department_id <", value, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdLessThanOrEqualTo(Integer value) {
            addCriterion("department_id <=", value, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdIn(List<Integer> values) {
            addCriterion("department_id in", values, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdNotIn(List<Integer> values) {
            addCriterion("department_id not in", values, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdBetween(Integer value1, Integer value2) {
            addCriterion("department_id between", value1, value2, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdNotBetween(Integer value1, Integer value2) {
            addCriterion("department_id not between", value1, value2, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameIsNull() {
            addCriterion("department_name is null");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameIsNotNull() {
            addCriterion("department_name is not null");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameEqualTo(String value) {
            addCriterion("department_name =", value, "departmentName");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameNotEqualTo(String value) {
            addCriterion("department_name <>", value, "departmentName");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameGreaterThan(String value) {
            addCriterion("department_name >", value, "departmentName");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameGreaterThanOrEqualTo(String value) {
            addCriterion("department_name >=", value, "departmentName");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameLessThan(String value) {
            addCriterion("department_name <", value, "departmentName");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameLessThanOrEqualTo(String value) {
            addCriterion("department_name <=", value, "departmentName");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameLike(String value) {
            addCriterion("department_name like", value, "departmentName");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameNotLike(String value) {
            addCriterion("department_name not like", value, "departmentName");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameIn(List<String> values) {
            addCriterion("department_name in", values, "departmentName");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameNotIn(List<String> values) {
            addCriterion("department_name not in", values, "departmentName");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameBetween(String value1, String value2) {
            addCriterion("department_name between", value1, value2, "departmentName");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameNotBetween(String value1, String value2) {
            addCriterion("department_name not between", value1, value2, "departmentName");
            return (Criteria) this;
        }

        public Criteria andAllApiNumIsNull() {
            addCriterion("all_api_num is null");
            return (Criteria) this;
        }

        public Criteria andAllApiNumIsNotNull() {
            addCriterion("all_api_num is not null");
            return (Criteria) this;
        }

        public Criteria andAllApiNumEqualTo(Integer value) {
            addCriterion("all_api_num =", value, "allApiNum");
            return (Criteria) this;
        }

        public Criteria andAllApiNumNotEqualTo(Integer value) {
            addCriterion("all_api_num <>", value, "allApiNum");
            return (Criteria) this;
        }

        public Criteria andAllApiNumGreaterThan(Integer value) {
            addCriterion("all_api_num >", value, "allApiNum");
            return (Criteria) this;
        }

        public Criteria andAllApiNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("all_api_num >=", value, "allApiNum");
            return (Criteria) this;
        }

        public Criteria andAllApiNumLessThan(Integer value) {
            addCriterion("all_api_num <", value, "allApiNum");
            return (Criteria) this;
        }

        public Criteria andAllApiNumLessThanOrEqualTo(Integer value) {
            addCriterion("all_api_num <=", value, "allApiNum");
            return (Criteria) this;
        }

        public Criteria andAllApiNumIn(List<Integer> values) {
            addCriterion("all_api_num in", values, "allApiNum");
            return (Criteria) this;
        }

        public Criteria andAllApiNumNotIn(List<Integer> values) {
            addCriterion("all_api_num not in", values, "allApiNum");
            return (Criteria) this;
        }

        public Criteria andAllApiNumBetween(Integer value1, Integer value2) {
            addCriterion("all_api_num between", value1, value2, "allApiNum");
            return (Criteria) this;
        }

        public Criteria andAllApiNumNotBetween(Integer value1, Integer value2) {
            addCriterion("all_api_num not between", value1, value2, "allApiNum");
            return (Criteria) this;
        }

        public Criteria andCoverApiNumIsNull() {
            addCriterion("cover_api_num is null");
            return (Criteria) this;
        }

        public Criteria andCoverApiNumIsNotNull() {
            addCriterion("cover_api_num is not null");
            return (Criteria) this;
        }

        public Criteria andCoverApiNumEqualTo(Integer value) {
            addCriterion("cover_api_num =", value, "coverApiNum");
            return (Criteria) this;
        }

        public Criteria andCoverApiNumNotEqualTo(Integer value) {
            addCriterion("cover_api_num <>", value, "coverApiNum");
            return (Criteria) this;
        }

        public Criteria andCoverApiNumGreaterThan(Integer value) {
            addCriterion("cover_api_num >", value, "coverApiNum");
            return (Criteria) this;
        }

        public Criteria andCoverApiNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("cover_api_num >=", value, "coverApiNum");
            return (Criteria) this;
        }

        public Criteria andCoverApiNumLessThan(Integer value) {
            addCriterion("cover_api_num <", value, "coverApiNum");
            return (Criteria) this;
        }

        public Criteria andCoverApiNumLessThanOrEqualTo(Integer value) {
            addCriterion("cover_api_num <=", value, "coverApiNum");
            return (Criteria) this;
        }

        public Criteria andCoverApiNumIn(List<Integer> values) {
            addCriterion("cover_api_num in", values, "coverApiNum");
            return (Criteria) this;
        }

        public Criteria andCoverApiNumNotIn(List<Integer> values) {
            addCriterion("cover_api_num not in", values, "coverApiNum");
            return (Criteria) this;
        }

        public Criteria andCoverApiNumBetween(Integer value1, Integer value2) {
            addCriterion("cover_api_num between", value1, value2, "coverApiNum");
            return (Criteria) this;
        }

        public Criteria andCoverApiNumNotBetween(Integer value1, Integer value2) {
            addCriterion("cover_api_num not between", value1, value2, "coverApiNum");
            return (Criteria) this;
        }

        public Criteria andApiCoverageIsNull() {
            addCriterion("api_coverage is null");
            return (Criteria) this;
        }

        public Criteria andApiCoverageIsNotNull() {
            addCriterion("api_coverage is not null");
            return (Criteria) this;
        }

        public Criteria andApiCoverageEqualTo(BigDecimal value) {
            addCriterion("api_coverage =", value, "apiCoverage");
            return (Criteria) this;
        }

        public Criteria andApiCoverageNotEqualTo(BigDecimal value) {
            addCriterion("api_coverage <>", value, "apiCoverage");
            return (Criteria) this;
        }

        public Criteria andApiCoverageGreaterThan(BigDecimal value) {
            addCriterion("api_coverage >", value, "apiCoverage");
            return (Criteria) this;
        }

        public Criteria andApiCoverageGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("api_coverage >=", value, "apiCoverage");
            return (Criteria) this;
        }

        public Criteria andApiCoverageLessThan(BigDecimal value) {
            addCriterion("api_coverage <", value, "apiCoverage");
            return (Criteria) this;
        }

        public Criteria andApiCoverageLessThanOrEqualTo(BigDecimal value) {
            addCriterion("api_coverage <=", value, "apiCoverage");
            return (Criteria) this;
        }

        public Criteria andApiCoverageIn(List<BigDecimal> values) {
            addCriterion("api_coverage in", values, "apiCoverage");
            return (Criteria) this;
        }

        public Criteria andApiCoverageNotIn(List<BigDecimal> values) {
            addCriterion("api_coverage not in", values, "apiCoverage");
            return (Criteria) this;
        }

        public Criteria andApiCoverageBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("api_coverage between", value1, value2, "apiCoverage");
            return (Criteria) this;
        }

        public Criteria andApiCoverageNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("api_coverage not between", value1, value2, "apiCoverage");
            return (Criteria) this;
        }

        public Criteria andAllCoreApiNumIsNull() {
            addCriterion("all_core_api_num is null");
            return (Criteria) this;
        }

        public Criteria andAllCoreApiNumIsNotNull() {
            addCriterion("all_core_api_num is not null");
            return (Criteria) this;
        }

        public Criteria andAllCoreApiNumEqualTo(Integer value) {
            addCriterion("all_core_api_num =", value, "allCoreApiNum");
            return (Criteria) this;
        }

        public Criteria andAllCoreApiNumNotEqualTo(Integer value) {
            addCriterion("all_core_api_num <>", value, "allCoreApiNum");
            return (Criteria) this;
        }

        public Criteria andAllCoreApiNumGreaterThan(Integer value) {
            addCriterion("all_core_api_num >", value, "allCoreApiNum");
            return (Criteria) this;
        }

        public Criteria andAllCoreApiNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("all_core_api_num >=", value, "allCoreApiNum");
            return (Criteria) this;
        }

        public Criteria andAllCoreApiNumLessThan(Integer value) {
            addCriterion("all_core_api_num <", value, "allCoreApiNum");
            return (Criteria) this;
        }

        public Criteria andAllCoreApiNumLessThanOrEqualTo(Integer value) {
            addCriterion("all_core_api_num <=", value, "allCoreApiNum");
            return (Criteria) this;
        }

        public Criteria andAllCoreApiNumIn(List<Integer> values) {
            addCriterion("all_core_api_num in", values, "allCoreApiNum");
            return (Criteria) this;
        }

        public Criteria andAllCoreApiNumNotIn(List<Integer> values) {
            addCriterion("all_core_api_num not in", values, "allCoreApiNum");
            return (Criteria) this;
        }

        public Criteria andAllCoreApiNumBetween(Integer value1, Integer value2) {
            addCriterion("all_core_api_num between", value1, value2, "allCoreApiNum");
            return (Criteria) this;
        }

        public Criteria andAllCoreApiNumNotBetween(Integer value1, Integer value2) {
            addCriterion("all_core_api_num not between", value1, value2, "allCoreApiNum");
            return (Criteria) this;
        }

        public Criteria andCoverCoreApiNumIsNull() {
            addCriterion("cover_core_api_num is null");
            return (Criteria) this;
        }

        public Criteria andCoverCoreApiNumIsNotNull() {
            addCriterion("cover_core_api_num is not null");
            return (Criteria) this;
        }

        public Criteria andCoverCoreApiNumEqualTo(Integer value) {
            addCriterion("cover_core_api_num =", value, "coverCoreApiNum");
            return (Criteria) this;
        }

        public Criteria andCoverCoreApiNumNotEqualTo(Integer value) {
            addCriterion("cover_core_api_num <>", value, "coverCoreApiNum");
            return (Criteria) this;
        }

        public Criteria andCoverCoreApiNumGreaterThan(Integer value) {
            addCriterion("cover_core_api_num >", value, "coverCoreApiNum");
            return (Criteria) this;
        }

        public Criteria andCoverCoreApiNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("cover_core_api_num >=", value, "coverCoreApiNum");
            return (Criteria) this;
        }

        public Criteria andCoverCoreApiNumLessThan(Integer value) {
            addCriterion("cover_core_api_num <", value, "coverCoreApiNum");
            return (Criteria) this;
        }

        public Criteria andCoverCoreApiNumLessThanOrEqualTo(Integer value) {
            addCriterion("cover_core_api_num <=", value, "coverCoreApiNum");
            return (Criteria) this;
        }

        public Criteria andCoverCoreApiNumIn(List<Integer> values) {
            addCriterion("cover_core_api_num in", values, "coverCoreApiNum");
            return (Criteria) this;
        }

        public Criteria andCoverCoreApiNumNotIn(List<Integer> values) {
            addCriterion("cover_core_api_num not in", values, "coverCoreApiNum");
            return (Criteria) this;
        }

        public Criteria andCoverCoreApiNumBetween(Integer value1, Integer value2) {
            addCriterion("cover_core_api_num between", value1, value2, "coverCoreApiNum");
            return (Criteria) this;
        }

        public Criteria andCoverCoreApiNumNotBetween(Integer value1, Integer value2) {
            addCriterion("cover_core_api_num not between", value1, value2, "coverCoreApiNum");
            return (Criteria) this;
        }

        public Criteria andCoreApiCoverageIsNull() {
            addCriterion("core_api_coverage is null");
            return (Criteria) this;
        }

        public Criteria andCoreApiCoverageIsNotNull() {
            addCriterion("core_api_coverage is not null");
            return (Criteria) this;
        }

        public Criteria andCoreApiCoverageEqualTo(BigDecimal value) {
            addCriterion("core_api_coverage =", value, "coreApiCoverage");
            return (Criteria) this;
        }

        public Criteria andCoreApiCoverageNotEqualTo(BigDecimal value) {
            addCriterion("core_api_coverage <>", value, "coreApiCoverage");
            return (Criteria) this;
        }

        public Criteria andCoreApiCoverageGreaterThan(BigDecimal value) {
            addCriterion("core_api_coverage >", value, "coreApiCoverage");
            return (Criteria) this;
        }

        public Criteria andCoreApiCoverageGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("core_api_coverage >=", value, "coreApiCoverage");
            return (Criteria) this;
        }

        public Criteria andCoreApiCoverageLessThan(BigDecimal value) {
            addCriterion("core_api_coverage <", value, "coreApiCoverage");
            return (Criteria) this;
        }

        public Criteria andCoreApiCoverageLessThanOrEqualTo(BigDecimal value) {
            addCriterion("core_api_coverage <=", value, "coreApiCoverage");
            return (Criteria) this;
        }

        public Criteria andCoreApiCoverageIn(List<BigDecimal> values) {
            addCriterion("core_api_coverage in", values, "coreApiCoverage");
            return (Criteria) this;
        }

        public Criteria andCoreApiCoverageNotIn(List<BigDecimal> values) {
            addCriterion("core_api_coverage not in", values, "coreApiCoverage");
            return (Criteria) this;
        }

        public Criteria andCoreApiCoverageBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("core_api_coverage between", value1, value2, "coreApiCoverage");
            return (Criteria) this;
        }

        public Criteria andCoreApiCoverageNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("core_api_coverage not between", value1, value2, "coreApiCoverage");
            return (Criteria) this;
        }

        public Criteria andCoverageDateIsNull() {
            addCriterion("coverage_date is null");
            return (Criteria) this;
        }

        public Criteria andCoverageDateIsNotNull() {
            addCriterion("coverage_date is not null");
            return (Criteria) this;
        }

        public Criteria andCoverageDateEqualTo(Date value) {
            addCriterionForJDBCDate("coverage_date =", value, "coverageDate");
            return (Criteria) this;
        }

        public Criteria andCoverageDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("coverage_date <>", value, "coverageDate");
            return (Criteria) this;
        }

        public Criteria andCoverageDateGreaterThan(Date value) {
            addCriterionForJDBCDate("coverage_date >", value, "coverageDate");
            return (Criteria) this;
        }

        public Criteria andCoverageDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("coverage_date >=", value, "coverageDate");
            return (Criteria) this;
        }

        public Criteria andCoverageDateLessThan(Date value) {
            addCriterionForJDBCDate("coverage_date <", value, "coverageDate");
            return (Criteria) this;
        }

        public Criteria andCoverageDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("coverage_date <=", value, "coverageDate");
            return (Criteria) this;
        }

        public Criteria andCoverageDateIn(List<Date> values) {
            addCriterionForJDBCDate("coverage_date in", values, "coverageDate");
            return (Criteria) this;
        }

        public Criteria andCoverageDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("coverage_date not in", values, "coverageDate");
            return (Criteria) this;
        }

        public Criteria andCoverageDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("coverage_date between", value1, value2, "coverageDate");
            return (Criteria) this;
        }

        public Criteria andCoverageDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("coverage_date not between", value1, value2, "coverageDate");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andCoreSrvApiNumIsNull() {
            addCriterion("core_srv_api_num is null");
            return (Criteria) this;
        }

        public Criteria andCoreSrvApiNumIsNotNull() {
            addCriterion("core_srv_api_num is not null");
            return (Criteria) this;
        }

        public Criteria andCoreSrvApiNumEqualTo(Integer value) {
            addCriterion("core_srv_api_num =", value, "coreSrvApiNum");
            return (Criteria) this;
        }

        public Criteria andCoreSrvApiNumNotEqualTo(Integer value) {
            addCriterion("core_srv_api_num <>", value, "coreSrvApiNum");
            return (Criteria) this;
        }

        public Criteria andCoreSrvApiNumGreaterThan(Integer value) {
            addCriterion("core_srv_api_num >", value, "coreSrvApiNum");
            return (Criteria) this;
        }

        public Criteria andCoreSrvApiNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("core_srv_api_num >=", value, "coreSrvApiNum");
            return (Criteria) this;
        }

        public Criteria andCoreSrvApiNumLessThan(Integer value) {
            addCriterion("core_srv_api_num <", value, "coreSrvApiNum");
            return (Criteria) this;
        }

        public Criteria andCoreSrvApiNumLessThanOrEqualTo(Integer value) {
            addCriterion("core_srv_api_num <=", value, "coreSrvApiNum");
            return (Criteria) this;
        }

        public Criteria andCoreSrvApiNumIn(List<Integer> values) {
            addCriterion("core_srv_api_num in", values, "coreSrvApiNum");
            return (Criteria) this;
        }

        public Criteria andCoreSrvApiNumNotIn(List<Integer> values) {
            addCriterion("core_srv_api_num not in", values, "coreSrvApiNum");
            return (Criteria) this;
        }

        public Criteria andCoreSrvApiNumBetween(Integer value1, Integer value2) {
            addCriterion("core_srv_api_num between", value1, value2, "coreSrvApiNum");
            return (Criteria) this;
        }

        public Criteria andCoreSrvApiNumNotBetween(Integer value1, Integer value2) {
            addCriterion("core_srv_api_num not between", value1, value2, "coreSrvApiNum");
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