package com.meituan.food.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SonarPOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SonarPOExample() {
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

        public Criteria andProjectIsNull() {
            addCriterion("project is null");
            return (Criteria) this;
        }

        public Criteria andProjectIsNotNull() {
            addCriterion("project is not null");
            return (Criteria) this;
        }

        public Criteria andProjectEqualTo(String value) {
            addCriterion("project =", value, "project");
            return (Criteria) this;
        }

        public Criteria andProjectNotEqualTo(String value) {
            addCriterion("project <>", value, "project");
            return (Criteria) this;
        }

        public Criteria andProjectGreaterThan(String value) {
            addCriterion("project >", value, "project");
            return (Criteria) this;
        }

        public Criteria andProjectGreaterThanOrEqualTo(String value) {
            addCriterion("project >=", value, "project");
            return (Criteria) this;
        }

        public Criteria andProjectLessThan(String value) {
            addCriterion("project <", value, "project");
            return (Criteria) this;
        }

        public Criteria andProjectLessThanOrEqualTo(String value) {
            addCriterion("project <=", value, "project");
            return (Criteria) this;
        }

        public Criteria andProjectLike(String value) {
            addCriterion("project like", value, "project");
            return (Criteria) this;
        }

        public Criteria andProjectNotLike(String value) {
            addCriterion("project not like", value, "project");
            return (Criteria) this;
        }

        public Criteria andProjectIn(List<String> values) {
            addCriterion("project in", values, "project");
            return (Criteria) this;
        }

        public Criteria andProjectNotIn(List<String> values) {
            addCriterion("project not in", values, "project");
            return (Criteria) this;
        }

        public Criteria andProjectBetween(String value1, String value2) {
            addCriterion("project between", value1, value2, "project");
            return (Criteria) this;
        }

        public Criteria andProjectNotBetween(String value1, String value2) {
            addCriterion("project not between", value1, value2, "project");
            return (Criteria) this;
        }

        public Criteria andIncludesubprojectIsNull() {
            addCriterion("includeSubProject is null");
            return (Criteria) this;
        }

        public Criteria andIncludesubprojectIsNotNull() {
            addCriterion("includeSubProject is not null");
            return (Criteria) this;
        }

        public Criteria andIncludesubprojectEqualTo(String value) {
            addCriterion("includeSubProject =", value, "includesubproject");
            return (Criteria) this;
        }

        public Criteria andIncludesubprojectNotEqualTo(String value) {
            addCriterion("includeSubProject <>", value, "includesubproject");
            return (Criteria) this;
        }

        public Criteria andIncludesubprojectGreaterThan(String value) {
            addCriterion("includeSubProject >", value, "includesubproject");
            return (Criteria) this;
        }

        public Criteria andIncludesubprojectGreaterThanOrEqualTo(String value) {
            addCriterion("includeSubProject >=", value, "includesubproject");
            return (Criteria) this;
        }

        public Criteria andIncludesubprojectLessThan(String value) {
            addCriterion("includeSubProject <", value, "includesubproject");
            return (Criteria) this;
        }

        public Criteria andIncludesubprojectLessThanOrEqualTo(String value) {
            addCriterion("includeSubProject <=", value, "includesubproject");
            return (Criteria) this;
        }

        public Criteria andIncludesubprojectLike(String value) {
            addCriterion("includeSubProject like", value, "includesubproject");
            return (Criteria) this;
        }

        public Criteria andIncludesubprojectNotLike(String value) {
            addCriterion("includeSubProject not like", value, "includesubproject");
            return (Criteria) this;
        }

        public Criteria andIncludesubprojectIn(List<String> values) {
            addCriterion("includeSubProject in", values, "includesubproject");
            return (Criteria) this;
        }

        public Criteria andIncludesubprojectNotIn(List<String> values) {
            addCriterion("includeSubProject not in", values, "includesubproject");
            return (Criteria) this;
        }

        public Criteria andIncludesubprojectBetween(String value1, String value2) {
            addCriterion("includeSubProject between", value1, value2, "includesubproject");
            return (Criteria) this;
        }

        public Criteria andIncludesubprojectNotBetween(String value1, String value2) {
            addCriterion("includeSubProject not between", value1, value2, "includesubproject");
            return (Criteria) this;
        }

        public Criteria andBlockerIsNull() {
            addCriterion("blocker is null");
            return (Criteria) this;
        }

        public Criteria andBlockerIsNotNull() {
            addCriterion("blocker is not null");
            return (Criteria) this;
        }

        public Criteria andBlockerEqualTo(Integer value) {
            addCriterion("blocker =", value, "blocker");
            return (Criteria) this;
        }

        public Criteria andBlockerNotEqualTo(Integer value) {
            addCriterion("blocker <>", value, "blocker");
            return (Criteria) this;
        }

        public Criteria andBlockerGreaterThan(Integer value) {
            addCriterion("blocker >", value, "blocker");
            return (Criteria) this;
        }

        public Criteria andBlockerGreaterThanOrEqualTo(Integer value) {
            addCriterion("blocker >=", value, "blocker");
            return (Criteria) this;
        }

        public Criteria andBlockerLessThan(Integer value) {
            addCriterion("blocker <", value, "blocker");
            return (Criteria) this;
        }

        public Criteria andBlockerLessThanOrEqualTo(Integer value) {
            addCriterion("blocker <=", value, "blocker");
            return (Criteria) this;
        }

        public Criteria andBlockerIn(List<Integer> values) {
            addCriterion("blocker in", values, "blocker");
            return (Criteria) this;
        }

        public Criteria andBlockerNotIn(List<Integer> values) {
            addCriterion("blocker not in", values, "blocker");
            return (Criteria) this;
        }

        public Criteria andBlockerBetween(Integer value1, Integer value2) {
            addCriterion("blocker between", value1, value2, "blocker");
            return (Criteria) this;
        }

        public Criteria andBlockerNotBetween(Integer value1, Integer value2) {
            addCriterion("blocker not between", value1, value2, "blocker");
            return (Criteria) this;
        }

        public Criteria andCriticalIsNull() {
            addCriterion("critical is null");
            return (Criteria) this;
        }

        public Criteria andCriticalIsNotNull() {
            addCriterion("critical is not null");
            return (Criteria) this;
        }

        public Criteria andCriticalEqualTo(Integer value) {
            addCriterion("critical =", value, "critical");
            return (Criteria) this;
        }

        public Criteria andCriticalNotEqualTo(Integer value) {
            addCriterion("critical <>", value, "critical");
            return (Criteria) this;
        }

        public Criteria andCriticalGreaterThan(Integer value) {
            addCriterion("critical >", value, "critical");
            return (Criteria) this;
        }

        public Criteria andCriticalGreaterThanOrEqualTo(Integer value) {
            addCriterion("critical >=", value, "critical");
            return (Criteria) this;
        }

        public Criteria andCriticalLessThan(Integer value) {
            addCriterion("critical <", value, "critical");
            return (Criteria) this;
        }

        public Criteria andCriticalLessThanOrEqualTo(Integer value) {
            addCriterion("critical <=", value, "critical");
            return (Criteria) this;
        }

        public Criteria andCriticalIn(List<Integer> values) {
            addCriterion("critical in", values, "critical");
            return (Criteria) this;
        }

        public Criteria andCriticalNotIn(List<Integer> values) {
            addCriterion("critical not in", values, "critical");
            return (Criteria) this;
        }

        public Criteria andCriticalBetween(Integer value1, Integer value2) {
            addCriterion("critical between", value1, value2, "critical");
            return (Criteria) this;
        }

        public Criteria andCriticalNotBetween(Integer value1, Integer value2) {
            addCriterion("critical not between", value1, value2, "critical");
            return (Criteria) this;
        }

        public Criteria andLeaderIsNull() {
            addCriterion("leader is null");
            return (Criteria) this;
        }

        public Criteria andLeaderIsNotNull() {
            addCriterion("leader is not null");
            return (Criteria) this;
        }

        public Criteria andLeaderEqualTo(String value) {
            addCriterion("leader =", value, "leader");
            return (Criteria) this;
        }

        public Criteria andLeaderNotEqualTo(String value) {
            addCriterion("leader <>", value, "leader");
            return (Criteria) this;
        }

        public Criteria andLeaderGreaterThan(String value) {
            addCriterion("leader >", value, "leader");
            return (Criteria) this;
        }

        public Criteria andLeaderGreaterThanOrEqualTo(String value) {
            addCriterion("leader >=", value, "leader");
            return (Criteria) this;
        }

        public Criteria andLeaderLessThan(String value) {
            addCriterion("leader <", value, "leader");
            return (Criteria) this;
        }

        public Criteria andLeaderLessThanOrEqualTo(String value) {
            addCriterion("leader <=", value, "leader");
            return (Criteria) this;
        }

        public Criteria andLeaderLike(String value) {
            addCriterion("leader like", value, "leader");
            return (Criteria) this;
        }

        public Criteria andLeaderNotLike(String value) {
            addCriterion("leader not like", value, "leader");
            return (Criteria) this;
        }

        public Criteria andLeaderIn(List<String> values) {
            addCriterion("leader in", values, "leader");
            return (Criteria) this;
        }

        public Criteria andLeaderNotIn(List<String> values) {
            addCriterion("leader not in", values, "leader");
            return (Criteria) this;
        }

        public Criteria andLeaderBetween(String value1, String value2) {
            addCriterion("leader between", value1, value2, "leader");
            return (Criteria) this;
        }

        public Criteria andLeaderNotBetween(String value1, String value2) {
            addCriterion("leader not between", value1, value2, "leader");
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

        public Criteria andUpdatedAtIsNull() {
            addCriterion("updated_at is null");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtIsNotNull() {
            addCriterion("updated_at is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtEqualTo(Date value) {
            addCriterion("updated_at =", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtNotEqualTo(Date value) {
            addCriterion("updated_at <>", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThan(Date value) {
            addCriterion("updated_at >", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThanOrEqualTo(Date value) {
            addCriterion("updated_at >=", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThan(Date value) {
            addCriterion("updated_at <", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThanOrEqualTo(Date value) {
            addCriterion("updated_at <=", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtIn(List<Date> values) {
            addCriterion("updated_at in", values, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtNotIn(List<Date> values) {
            addCriterion("updated_at not in", values, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtBetween(Date value1, Date value2) {
            addCriterion("updated_at between", value1, value2, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtNotBetween(Date value1, Date value2) {
            addCriterion("updated_at not between", value1, value2, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andLinkIsNull() {
            addCriterion("link is null");
            return (Criteria) this;
        }

        public Criteria andLinkIsNotNull() {
            addCriterion("link is not null");
            return (Criteria) this;
        }

        public Criteria andLinkEqualTo(String value) {
            addCriterion("link =", value, "link");
            return (Criteria) this;
        }

        public Criteria andLinkNotEqualTo(String value) {
            addCriterion("link <>", value, "link");
            return (Criteria) this;
        }

        public Criteria andLinkGreaterThan(String value) {
            addCriterion("link >", value, "link");
            return (Criteria) this;
        }

        public Criteria andLinkGreaterThanOrEqualTo(String value) {
            addCriterion("link >=", value, "link");
            return (Criteria) this;
        }

        public Criteria andLinkLessThan(String value) {
            addCriterion("link <", value, "link");
            return (Criteria) this;
        }

        public Criteria andLinkLessThanOrEqualTo(String value) {
            addCriterion("link <=", value, "link");
            return (Criteria) this;
        }

        public Criteria andLinkLike(String value) {
            addCriterion("link like", value, "link");
            return (Criteria) this;
        }

        public Criteria andLinkNotLike(String value) {
            addCriterion("link not like", value, "link");
            return (Criteria) this;
        }

        public Criteria andLinkIn(List<String> values) {
            addCriterion("link in", values, "link");
            return (Criteria) this;
        }

        public Criteria andLinkNotIn(List<String> values) {
            addCriterion("link not in", values, "link");
            return (Criteria) this;
        }

        public Criteria andLinkBetween(String value1, String value2) {
            addCriterion("link between", value1, value2, "link");
            return (Criteria) this;
        }

        public Criteria andLinkNotBetween(String value1, String value2) {
            addCriterion("link not between", value1, value2, "link");
            return (Criteria) this;
        }

        public Criteria andSonarDateIsNull() {
            addCriterion("sonar_date is null");
            return (Criteria) this;
        }

        public Criteria andSonarDateIsNotNull() {
            addCriterion("sonar_date is not null");
            return (Criteria) this;
        }

        public Criteria andSonarDateEqualTo(String value) {
            addCriterion("sonar_date =", value, "sonarDate");
            return (Criteria) this;
        }

        public Criteria andSonarDateNotEqualTo(String value) {
            addCriterion("sonar_date <>", value, "sonarDate");
            return (Criteria) this;
        }

        public Criteria andSonarDateGreaterThan(String value) {
            addCriterion("sonar_date >", value, "sonarDate");
            return (Criteria) this;
        }

        public Criteria andSonarDateGreaterThanOrEqualTo(String value) {
            addCriterion("sonar_date >=", value, "sonarDate");
            return (Criteria) this;
        }

        public Criteria andSonarDateLessThan(String value) {
            addCriterion("sonar_date <", value, "sonarDate");
            return (Criteria) this;
        }

        public Criteria andSonarDateLessThanOrEqualTo(String value) {
            addCriterion("sonar_date <=", value, "sonarDate");
            return (Criteria) this;
        }

        public Criteria andSonarDateLike(String value) {
            addCriterion("sonar_date like", value, "sonarDate");
            return (Criteria) this;
        }

        public Criteria andSonarDateNotLike(String value) {
            addCriterion("sonar_date not like", value, "sonarDate");
            return (Criteria) this;
        }

        public Criteria andSonarDateIn(List<String> values) {
            addCriterion("sonar_date in", values, "sonarDate");
            return (Criteria) this;
        }

        public Criteria andSonarDateNotIn(List<String> values) {
            addCriterion("sonar_date not in", values, "sonarDate");
            return (Criteria) this;
        }

        public Criteria andSonarDateBetween(String value1, String value2) {
            addCriterion("sonar_date between", value1, value2, "sonarDate");
            return (Criteria) this;
        }

        public Criteria andSonarDateNotBetween(String value1, String value2) {
            addCriterion("sonar_date not between", value1, value2, "sonarDate");
            return (Criteria) this;
        }

        public Criteria andGroupCountIsNull() {
            addCriterion("group_count is null");
            return (Criteria) this;
        }

        public Criteria andGroupCountIsNotNull() {
            addCriterion("group_count is not null");
            return (Criteria) this;
        }

        public Criteria andGroupCountEqualTo(Long value) {
            addCriterion("group_count =", value, "groupCount");
            return (Criteria) this;
        }

        public Criteria andGroupCountNotEqualTo(Long value) {
            addCriterion("group_count <>", value, "groupCount");
            return (Criteria) this;
        }

        public Criteria andGroupCountGreaterThan(Long value) {
            addCriterion("group_count >", value, "groupCount");
            return (Criteria) this;
        }

        public Criteria andGroupCountGreaterThanOrEqualTo(Long value) {
            addCriterion("group_count >=", value, "groupCount");
            return (Criteria) this;
        }

        public Criteria andGroupCountLessThan(Long value) {
            addCriterion("group_count <", value, "groupCount");
            return (Criteria) this;
        }

        public Criteria andGroupCountLessThanOrEqualTo(Long value) {
            addCriterion("group_count <=", value, "groupCount");
            return (Criteria) this;
        }

        public Criteria andGroupCountIn(List<Long> values) {
            addCriterion("group_count in", values, "groupCount");
            return (Criteria) this;
        }

        public Criteria andGroupCountNotIn(List<Long> values) {
            addCriterion("group_count not in", values, "groupCount");
            return (Criteria) this;
        }

        public Criteria andGroupCountBetween(Long value1, Long value2) {
            addCriterion("group_count between", value1, value2, "groupCount");
            return (Criteria) this;
        }

        public Criteria andGroupCountNotBetween(Long value1, Long value2) {
            addCriterion("group_count not between", value1, value2, "groupCount");
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