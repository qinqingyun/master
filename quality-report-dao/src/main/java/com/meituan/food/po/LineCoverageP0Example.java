package com.meituan.food.po;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LineCoverageP0Example {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public LineCoverageP0Example() {
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

        public Criteria andCoreLineCIsNull() {
            addCriterion("core_line_c is null");
            return (Criteria) this;
        }

        public Criteria andCoreLineCIsNotNull() {
            addCriterion("core_line_c is not null");
            return (Criteria) this;
        }

        public Criteria andCoreLineCEqualTo(Integer value) {
            addCriterion("core_line_c =", value, "coreLineC");
            return (Criteria) this;
        }

        public Criteria andCoreLineCNotEqualTo(Integer value) {
            addCriterion("core_line_c <>", value, "coreLineC");
            return (Criteria) this;
        }

        public Criteria andCoreLineCGreaterThan(Integer value) {
            addCriterion("core_line_c >", value, "coreLineC");
            return (Criteria) this;
        }

        public Criteria andCoreLineCGreaterThanOrEqualTo(Integer value) {
            addCriterion("core_line_c >=", value, "coreLineC");
            return (Criteria) this;
        }

        public Criteria andCoreLineCLessThan(Integer value) {
            addCriterion("core_line_c <", value, "coreLineC");
            return (Criteria) this;
        }

        public Criteria andCoreLineCLessThanOrEqualTo(Integer value) {
            addCriterion("core_line_c <=", value, "coreLineC");
            return (Criteria) this;
        }

        public Criteria andCoreLineCIn(List<Integer> values) {
            addCriterion("core_line_c in", values, "coreLineC");
            return (Criteria) this;
        }

        public Criteria andCoreLineCNotIn(List<Integer> values) {
            addCriterion("core_line_c not in", values, "coreLineC");
            return (Criteria) this;
        }

        public Criteria andCoreLineCBetween(Integer value1, Integer value2) {
            addCriterion("core_line_c between", value1, value2, "coreLineC");
            return (Criteria) this;
        }

        public Criteria andCoreLineCNotBetween(Integer value1, Integer value2) {
            addCriterion("core_line_c not between", value1, value2, "coreLineC");
            return (Criteria) this;
        }

        public Criteria andCoreLineMIsNull() {
            addCriterion("core_line_m is null");
            return (Criteria) this;
        }

        public Criteria andCoreLineMIsNotNull() {
            addCriterion("core_line_m is not null");
            return (Criteria) this;
        }

        public Criteria andCoreLineMEqualTo(Integer value) {
            addCriterion("core_line_m =", value, "coreLineM");
            return (Criteria) this;
        }

        public Criteria andCoreLineMNotEqualTo(Integer value) {
            addCriterion("core_line_m <>", value, "coreLineM");
            return (Criteria) this;
        }

        public Criteria andCoreLineMGreaterThan(Integer value) {
            addCriterion("core_line_m >", value, "coreLineM");
            return (Criteria) this;
        }

        public Criteria andCoreLineMGreaterThanOrEqualTo(Integer value) {
            addCriterion("core_line_m >=", value, "coreLineM");
            return (Criteria) this;
        }

        public Criteria andCoreLineMLessThan(Integer value) {
            addCriterion("core_line_m <", value, "coreLineM");
            return (Criteria) this;
        }

        public Criteria andCoreLineMLessThanOrEqualTo(Integer value) {
            addCriterion("core_line_m <=", value, "coreLineM");
            return (Criteria) this;
        }

        public Criteria andCoreLineMIn(List<Integer> values) {
            addCriterion("core_line_m in", values, "coreLineM");
            return (Criteria) this;
        }

        public Criteria andCoreLineMNotIn(List<Integer> values) {
            addCriterion("core_line_m not in", values, "coreLineM");
            return (Criteria) this;
        }

        public Criteria andCoreLineMBetween(Integer value1, Integer value2) {
            addCriterion("core_line_m between", value1, value2, "coreLineM");
            return (Criteria) this;
        }

        public Criteria andCoreLineMNotBetween(Integer value1, Integer value2) {
            addCriterion("core_line_m not between", value1, value2, "coreLineM");
            return (Criteria) this;
        }

        public Criteria andCoreLineTotalIsNull() {
            addCriterion("core_line_total is null");
            return (Criteria) this;
        }

        public Criteria andCoreLineTotalIsNotNull() {
            addCriterion("core_line_total is not null");
            return (Criteria) this;
        }

        public Criteria andCoreLineTotalEqualTo(Integer value) {
            addCriterion("core_line_total =", value, "coreLineTotal");
            return (Criteria) this;
        }

        public Criteria andCoreLineTotalNotEqualTo(Integer value) {
            addCriterion("core_line_total <>", value, "coreLineTotal");
            return (Criteria) this;
        }

        public Criteria andCoreLineTotalGreaterThan(Integer value) {
            addCriterion("core_line_total >", value, "coreLineTotal");
            return (Criteria) this;
        }

        public Criteria andCoreLineTotalGreaterThanOrEqualTo(Integer value) {
            addCriterion("core_line_total >=", value, "coreLineTotal");
            return (Criteria) this;
        }

        public Criteria andCoreLineTotalLessThan(Integer value) {
            addCriterion("core_line_total <", value, "coreLineTotal");
            return (Criteria) this;
        }

        public Criteria andCoreLineTotalLessThanOrEqualTo(Integer value) {
            addCriterion("core_line_total <=", value, "coreLineTotal");
            return (Criteria) this;
        }

        public Criteria andCoreLineTotalIn(List<Integer> values) {
            addCriterion("core_line_total in", values, "coreLineTotal");
            return (Criteria) this;
        }

        public Criteria andCoreLineTotalNotIn(List<Integer> values) {
            addCriterion("core_line_total not in", values, "coreLineTotal");
            return (Criteria) this;
        }

        public Criteria andCoreLineTotalBetween(Integer value1, Integer value2) {
            addCriterion("core_line_total between", value1, value2, "coreLineTotal");
            return (Criteria) this;
        }

        public Criteria andCoreLineTotalNotBetween(Integer value1, Integer value2) {
            addCriterion("core_line_total not between", value1, value2, "coreLineTotal");
            return (Criteria) this;
        }

        public Criteria andCoreLineCoverageIsNull() {
            addCriterion("core_line_coverage is null");
            return (Criteria) this;
        }

        public Criteria andCoreLineCoverageIsNotNull() {
            addCriterion("core_line_coverage is not null");
            return (Criteria) this;
        }

        public Criteria andCoreLineCoverageEqualTo(BigDecimal value) {
            addCriterion("core_line_coverage =", value, "coreLineCoverage");
            return (Criteria) this;
        }

        public Criteria andCoreLineCoverageNotEqualTo(BigDecimal value) {
            addCriterion("core_line_coverage <>", value, "coreLineCoverage");
            return (Criteria) this;
        }

        public Criteria andCoreLineCoverageGreaterThan(BigDecimal value) {
            addCriterion("core_line_coverage >", value, "coreLineCoverage");
            return (Criteria) this;
        }

        public Criteria andCoreLineCoverageGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("core_line_coverage >=", value, "coreLineCoverage");
            return (Criteria) this;
        }

        public Criteria andCoreLineCoverageLessThan(BigDecimal value) {
            addCriterion("core_line_coverage <", value, "coreLineCoverage");
            return (Criteria) this;
        }

        public Criteria andCoreLineCoverageLessThanOrEqualTo(BigDecimal value) {
            addCriterion("core_line_coverage <=", value, "coreLineCoverage");
            return (Criteria) this;
        }

        public Criteria andCoreLineCoverageIn(List<BigDecimal> values) {
            addCriterion("core_line_coverage in", values, "coreLineCoverage");
            return (Criteria) this;
        }

        public Criteria andCoreLineCoverageNotIn(List<BigDecimal> values) {
            addCriterion("core_line_coverage not in", values, "coreLineCoverage");
            return (Criteria) this;
        }

        public Criteria andCoreLineCoverageBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("core_line_coverage between", value1, value2, "coreLineCoverage");
            return (Criteria) this;
        }

        public Criteria andCoreLineCoverageNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("core_line_coverage not between", value1, value2, "coreLineCoverage");
            return (Criteria) this;
        }

        public Criteria andCoreLineCInterfaceIsNull() {
            addCriterion("core_line_c_interface is null");
            return (Criteria) this;
        }

        public Criteria andCoreLineCInterfaceIsNotNull() {
            addCriterion("core_line_c_interface is not null");
            return (Criteria) this;
        }

        public Criteria andCoreLineCInterfaceEqualTo(String value) {
            addCriterion("core_line_c_interface =", value, "coreLineCInterface");
            return (Criteria) this;
        }

        public Criteria andCoreLineCInterfaceNotEqualTo(String value) {
            addCriterion("core_line_c_interface <>", value, "coreLineCInterface");
            return (Criteria) this;
        }

        public Criteria andCoreLineCInterfaceGreaterThan(String value) {
            addCriterion("core_line_c_interface >", value, "coreLineCInterface");
            return (Criteria) this;
        }

        public Criteria andCoreLineCInterfaceGreaterThanOrEqualTo(String value) {
            addCriterion("core_line_c_interface >=", value, "coreLineCInterface");
            return (Criteria) this;
        }

        public Criteria andCoreLineCInterfaceLessThan(String value) {
            addCriterion("core_line_c_interface <", value, "coreLineCInterface");
            return (Criteria) this;
        }

        public Criteria andCoreLineCInterfaceLessThanOrEqualTo(String value) {
            addCriterion("core_line_c_interface <=", value, "coreLineCInterface");
            return (Criteria) this;
        }

        public Criteria andCoreLineCInterfaceLike(String value) {
            addCriterion("core_line_c_interface like", value, "coreLineCInterface");
            return (Criteria) this;
        }

        public Criteria andCoreLineCInterfaceNotLike(String value) {
            addCriterion("core_line_c_interface not like", value, "coreLineCInterface");
            return (Criteria) this;
        }

        public Criteria andCoreLineCInterfaceIn(List<String> values) {
            addCriterion("core_line_c_interface in", values, "coreLineCInterface");
            return (Criteria) this;
        }

        public Criteria andCoreLineCInterfaceNotIn(List<String> values) {
            addCriterion("core_line_c_interface not in", values, "coreLineCInterface");
            return (Criteria) this;
        }

        public Criteria andCoreLineCInterfaceBetween(String value1, String value2) {
            addCriterion("core_line_c_interface between", value1, value2, "coreLineCInterface");
            return (Criteria) this;
        }

        public Criteria andCoreLineCInterfaceNotBetween(String value1, String value2) {
            addCriterion("core_line_c_interface not between", value1, value2, "coreLineCInterface");
            return (Criteria) this;
        }

        public Criteria andTotalLineCIsNull() {
            addCriterion("total_line_c is null");
            return (Criteria) this;
        }

        public Criteria andTotalLineCIsNotNull() {
            addCriterion("total_line_c is not null");
            return (Criteria) this;
        }

        public Criteria andTotalLineCEqualTo(Integer value) {
            addCriterion("total_line_c =", value, "totalLineC");
            return (Criteria) this;
        }

        public Criteria andTotalLineCNotEqualTo(Integer value) {
            addCriterion("total_line_c <>", value, "totalLineC");
            return (Criteria) this;
        }

        public Criteria andTotalLineCGreaterThan(Integer value) {
            addCriterion("total_line_c >", value, "totalLineC");
            return (Criteria) this;
        }

        public Criteria andTotalLineCGreaterThanOrEqualTo(Integer value) {
            addCriterion("total_line_c >=", value, "totalLineC");
            return (Criteria) this;
        }

        public Criteria andTotalLineCLessThan(Integer value) {
            addCriterion("total_line_c <", value, "totalLineC");
            return (Criteria) this;
        }

        public Criteria andTotalLineCLessThanOrEqualTo(Integer value) {
            addCriterion("total_line_c <=", value, "totalLineC");
            return (Criteria) this;
        }

        public Criteria andTotalLineCIn(List<Integer> values) {
            addCriterion("total_line_c in", values, "totalLineC");
            return (Criteria) this;
        }

        public Criteria andTotalLineCNotIn(List<Integer> values) {
            addCriterion("total_line_c not in", values, "totalLineC");
            return (Criteria) this;
        }

        public Criteria andTotalLineCBetween(Integer value1, Integer value2) {
            addCriterion("total_line_c between", value1, value2, "totalLineC");
            return (Criteria) this;
        }

        public Criteria andTotalLineCNotBetween(Integer value1, Integer value2) {
            addCriterion("total_line_c not between", value1, value2, "totalLineC");
            return (Criteria) this;
        }

        public Criteria andTotalLineMIsNull() {
            addCriterion("total_line_m is null");
            return (Criteria) this;
        }

        public Criteria andTotalLineMIsNotNull() {
            addCriterion("total_line_m is not null");
            return (Criteria) this;
        }

        public Criteria andTotalLineMEqualTo(Integer value) {
            addCriterion("total_line_m =", value, "totalLineM");
            return (Criteria) this;
        }

        public Criteria andTotalLineMNotEqualTo(Integer value) {
            addCriterion("total_line_m <>", value, "totalLineM");
            return (Criteria) this;
        }

        public Criteria andTotalLineMGreaterThan(Integer value) {
            addCriterion("total_line_m >", value, "totalLineM");
            return (Criteria) this;
        }

        public Criteria andTotalLineMGreaterThanOrEqualTo(Integer value) {
            addCriterion("total_line_m >=", value, "totalLineM");
            return (Criteria) this;
        }

        public Criteria andTotalLineMLessThan(Integer value) {
            addCriterion("total_line_m <", value, "totalLineM");
            return (Criteria) this;
        }

        public Criteria andTotalLineMLessThanOrEqualTo(Integer value) {
            addCriterion("total_line_m <=", value, "totalLineM");
            return (Criteria) this;
        }

        public Criteria andTotalLineMIn(List<Integer> values) {
            addCriterion("total_line_m in", values, "totalLineM");
            return (Criteria) this;
        }

        public Criteria andTotalLineMNotIn(List<Integer> values) {
            addCriterion("total_line_m not in", values, "totalLineM");
            return (Criteria) this;
        }

        public Criteria andTotalLineMBetween(Integer value1, Integer value2) {
            addCriterion("total_line_m between", value1, value2, "totalLineM");
            return (Criteria) this;
        }

        public Criteria andTotalLineMNotBetween(Integer value1, Integer value2) {
            addCriterion("total_line_m not between", value1, value2, "totalLineM");
            return (Criteria) this;
        }

        public Criteria andTotalLineTotalIsNull() {
            addCriterion("total_line_total is null");
            return (Criteria) this;
        }

        public Criteria andTotalLineTotalIsNotNull() {
            addCriterion("total_line_total is not null");
            return (Criteria) this;
        }

        public Criteria andTotalLineTotalEqualTo(Integer value) {
            addCriterion("total_line_total =", value, "totalLineTotal");
            return (Criteria) this;
        }

        public Criteria andTotalLineTotalNotEqualTo(Integer value) {
            addCriterion("total_line_total <>", value, "totalLineTotal");
            return (Criteria) this;
        }

        public Criteria andTotalLineTotalGreaterThan(Integer value) {
            addCriterion("total_line_total >", value, "totalLineTotal");
            return (Criteria) this;
        }

        public Criteria andTotalLineTotalGreaterThanOrEqualTo(Integer value) {
            addCriterion("total_line_total >=", value, "totalLineTotal");
            return (Criteria) this;
        }

        public Criteria andTotalLineTotalLessThan(Integer value) {
            addCriterion("total_line_total <", value, "totalLineTotal");
            return (Criteria) this;
        }

        public Criteria andTotalLineTotalLessThanOrEqualTo(Integer value) {
            addCriterion("total_line_total <=", value, "totalLineTotal");
            return (Criteria) this;
        }

        public Criteria andTotalLineTotalIn(List<Integer> values) {
            addCriterion("total_line_total in", values, "totalLineTotal");
            return (Criteria) this;
        }

        public Criteria andTotalLineTotalNotIn(List<Integer> values) {
            addCriterion("total_line_total not in", values, "totalLineTotal");
            return (Criteria) this;
        }

        public Criteria andTotalLineTotalBetween(Integer value1, Integer value2) {
            addCriterion("total_line_total between", value1, value2, "totalLineTotal");
            return (Criteria) this;
        }

        public Criteria andTotalLineTotalNotBetween(Integer value1, Integer value2) {
            addCriterion("total_line_total not between", value1, value2, "totalLineTotal");
            return (Criteria) this;
        }

        public Criteria andTotalLineCoverageIsNull() {
            addCriterion("total_line_coverage is null");
            return (Criteria) this;
        }

        public Criteria andTotalLineCoverageIsNotNull() {
            addCriterion("total_line_coverage is not null");
            return (Criteria) this;
        }

        public Criteria andTotalLineCoverageEqualTo(BigDecimal value) {
            addCriterion("total_line_coverage =", value, "totalLineCoverage");
            return (Criteria) this;
        }

        public Criteria andTotalLineCoverageNotEqualTo(BigDecimal value) {
            addCriterion("total_line_coverage <>", value, "totalLineCoverage");
            return (Criteria) this;
        }

        public Criteria andTotalLineCoverageGreaterThan(BigDecimal value) {
            addCriterion("total_line_coverage >", value, "totalLineCoverage");
            return (Criteria) this;
        }

        public Criteria andTotalLineCoverageGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("total_line_coverage >=", value, "totalLineCoverage");
            return (Criteria) this;
        }

        public Criteria andTotalLineCoverageLessThan(BigDecimal value) {
            addCriterion("total_line_coverage <", value, "totalLineCoverage");
            return (Criteria) this;
        }

        public Criteria andTotalLineCoverageLessThanOrEqualTo(BigDecimal value) {
            addCriterion("total_line_coverage <=", value, "totalLineCoverage");
            return (Criteria) this;
        }

        public Criteria andTotalLineCoverageIn(List<BigDecimal> values) {
            addCriterion("total_line_coverage in", values, "totalLineCoverage");
            return (Criteria) this;
        }

        public Criteria andTotalLineCoverageNotIn(List<BigDecimal> values) {
            addCriterion("total_line_coverage not in", values, "totalLineCoverage");
            return (Criteria) this;
        }

        public Criteria andTotalLineCoverageBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_line_coverage between", value1, value2, "totalLineCoverage");
            return (Criteria) this;
        }

        public Criteria andTotalLineCoverageNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_line_coverage not between", value1, value2, "totalLineCoverage");
            return (Criteria) this;
        }

        public Criteria andTotalLineCInterfaceIsNull() {
            addCriterion("total_line_c_interface is null");
            return (Criteria) this;
        }

        public Criteria andTotalLineCInterfaceIsNotNull() {
            addCriterion("total_line_c_interface is not null");
            return (Criteria) this;
        }

        public Criteria andTotalLineCInterfaceEqualTo(String value) {
            addCriterion("total_line_c_interface =", value, "totalLineCInterface");
            return (Criteria) this;
        }

        public Criteria andTotalLineCInterfaceNotEqualTo(String value) {
            addCriterion("total_line_c_interface <>", value, "totalLineCInterface");
            return (Criteria) this;
        }

        public Criteria andTotalLineCInterfaceGreaterThan(String value) {
            addCriterion("total_line_c_interface >", value, "totalLineCInterface");
            return (Criteria) this;
        }

        public Criteria andTotalLineCInterfaceGreaterThanOrEqualTo(String value) {
            addCriterion("total_line_c_interface >=", value, "totalLineCInterface");
            return (Criteria) this;
        }

        public Criteria andTotalLineCInterfaceLessThan(String value) {
            addCriterion("total_line_c_interface <", value, "totalLineCInterface");
            return (Criteria) this;
        }

        public Criteria andTotalLineCInterfaceLessThanOrEqualTo(String value) {
            addCriterion("total_line_c_interface <=", value, "totalLineCInterface");
            return (Criteria) this;
        }

        public Criteria andTotalLineCInterfaceLike(String value) {
            addCriterion("total_line_c_interface like", value, "totalLineCInterface");
            return (Criteria) this;
        }

        public Criteria andTotalLineCInterfaceNotLike(String value) {
            addCriterion("total_line_c_interface not like", value, "totalLineCInterface");
            return (Criteria) this;
        }

        public Criteria andTotalLineCInterfaceIn(List<String> values) {
            addCriterion("total_line_c_interface in", values, "totalLineCInterface");
            return (Criteria) this;
        }

        public Criteria andTotalLineCInterfaceNotIn(List<String> values) {
            addCriterion("total_line_c_interface not in", values, "totalLineCInterface");
            return (Criteria) this;
        }

        public Criteria andTotalLineCInterfaceBetween(String value1, String value2) {
            addCriterion("total_line_c_interface between", value1, value2, "totalLineCInterface");
            return (Criteria) this;
        }

        public Criteria andTotalLineCInterfaceNotBetween(String value1, String value2) {
            addCriterion("total_line_c_interface not between", value1, value2, "totalLineCInterface");
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

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
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