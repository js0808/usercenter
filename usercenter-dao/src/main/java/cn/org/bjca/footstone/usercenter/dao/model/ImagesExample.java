package cn.org.bjca.footstone.usercenter.dao.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ImagesExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private String sumCol;

    private Integer offset;

    private Integer limit;

    public ImagesExample() {
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
        this.sumCol = null;
        this.offset = null;
        this.limit = null;
    }

    public String getSumCol() {
        return this.sumCol;
    }

    public void setSumCol(String sumCol) {
        this.sumCol = sumCol;
    }

    public ImagesExample sumId() {
        this.sumCol="id";
        return this;
    }

    public ImagesExample sumAppId() {
        this.sumCol="app_id";
        return this;
    }

    public ImagesExample sumUid() {
        this.sumCol="uid";
        return this;
    }

    public ImagesExample sumOriginFileName() {
        this.sumCol="origin_file_name";
        return this;
    }

    public ImagesExample sumCreateTime() {
        this.sumCol="create_time";
        return this;
    }

    public ImagesExample sumName() {
        this.sumCol="name";
        return this;
    }

    public ImagesExample sumOutFileName() {
        this.sumCol="out_file_name";
        return this;
    }

    public ImagesExample sumSaveStatus() {
        this.sumCol="save_status";
        return this;
    }

    public Integer getOffset() {
        return this.offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return this.limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public ImagesExample page(int offset, int limit) {
        this.offset = offset;
        this.limit = limit;
        return this;
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

        public Criteria andAppIdIsNull() {
            addCriterion("app_id is null");
            return (Criteria) this;
        }

        public Criteria andAppIdIsNotNull() {
            addCriterion("app_id is not null");
            return (Criteria) this;
        }

        public Criteria andAppIdEqualTo(String value) {
            addCriterion("app_id =", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdNotEqualTo(String value) {
            addCriterion("app_id <>", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdGreaterThan(String value) {
            addCriterion("app_id >", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdGreaterThanOrEqualTo(String value) {
            addCriterion("app_id >=", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdLessThan(String value) {
            addCriterion("app_id <", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdLessThanOrEqualTo(String value) {
            addCriterion("app_id <=", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdLike(String value) {
            addCriterion("app_id like", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdNotLike(String value) {
            addCriterion("app_id not like", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdIn(List<String> values) {
            addCriterion("app_id in", values, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdNotIn(List<String> values) {
            addCriterion("app_id not in", values, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdBetween(String value1, String value2) {
            addCriterion("app_id between", value1, value2, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdNotBetween(String value1, String value2) {
            addCriterion("app_id not between", value1, value2, "appId");
            return (Criteria) this;
        }

        public Criteria andUidIsNull() {
            addCriterion("uid is null");
            return (Criteria) this;
        }

        public Criteria andUidIsNotNull() {
            addCriterion("uid is not null");
            return (Criteria) this;
        }

        public Criteria andUidEqualTo(Long value) {
            addCriterion("uid =", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotEqualTo(Long value) {
            addCriterion("uid <>", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThan(Long value) {
            addCriterion("uid >", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThanOrEqualTo(Long value) {
            addCriterion("uid >=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThan(Long value) {
            addCriterion("uid <", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThanOrEqualTo(Long value) {
            addCriterion("uid <=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidIn(List<Long> values) {
            addCriterion("uid in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotIn(List<Long> values) {
            addCriterion("uid not in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidBetween(Long value1, Long value2) {
            addCriterion("uid between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotBetween(Long value1, Long value2) {
            addCriterion("uid not between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andOriginFileNameIsNull() {
            addCriterion("origin_file_name is null");
            return (Criteria) this;
        }

        public Criteria andOriginFileNameIsNotNull() {
            addCriterion("origin_file_name is not null");
            return (Criteria) this;
        }

        public Criteria andOriginFileNameEqualTo(String value) {
            addCriterion("origin_file_name =", value, "originFileName");
            return (Criteria) this;
        }

        public Criteria andOriginFileNameNotEqualTo(String value) {
            addCriterion("origin_file_name <>", value, "originFileName");
            return (Criteria) this;
        }

        public Criteria andOriginFileNameGreaterThan(String value) {
            addCriterion("origin_file_name >", value, "originFileName");
            return (Criteria) this;
        }

        public Criteria andOriginFileNameGreaterThanOrEqualTo(String value) {
            addCriterion("origin_file_name >=", value, "originFileName");
            return (Criteria) this;
        }

        public Criteria andOriginFileNameLessThan(String value) {
            addCriterion("origin_file_name <", value, "originFileName");
            return (Criteria) this;
        }

        public Criteria andOriginFileNameLessThanOrEqualTo(String value) {
            addCriterion("origin_file_name <=", value, "originFileName");
            return (Criteria) this;
        }

        public Criteria andOriginFileNameLike(String value) {
            addCriterion("origin_file_name like", value, "originFileName");
            return (Criteria) this;
        }

        public Criteria andOriginFileNameNotLike(String value) {
            addCriterion("origin_file_name not like", value, "originFileName");
            return (Criteria) this;
        }

        public Criteria andOriginFileNameIn(List<String> values) {
            addCriterion("origin_file_name in", values, "originFileName");
            return (Criteria) this;
        }

        public Criteria andOriginFileNameNotIn(List<String> values) {
            addCriterion("origin_file_name not in", values, "originFileName");
            return (Criteria) this;
        }

        public Criteria andOriginFileNameBetween(String value1, String value2) {
            addCriterion("origin_file_name between", value1, value2, "originFileName");
            return (Criteria) this;
        }

        public Criteria andOriginFileNameNotBetween(String value1, String value2) {
            addCriterion("origin_file_name not between", value1, value2, "originFileName");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
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

        public Criteria andOutFileNameIsNull() {
            addCriterion("out_file_name is null");
            return (Criteria) this;
        }

        public Criteria andOutFileNameIsNotNull() {
            addCriterion("out_file_name is not null");
            return (Criteria) this;
        }

        public Criteria andOutFileNameEqualTo(String value) {
            addCriterion("out_file_name =", value, "outFileName");
            return (Criteria) this;
        }

        public Criteria andOutFileNameNotEqualTo(String value) {
            addCriterion("out_file_name <>", value, "outFileName");
            return (Criteria) this;
        }

        public Criteria andOutFileNameGreaterThan(String value) {
            addCriterion("out_file_name >", value, "outFileName");
            return (Criteria) this;
        }

        public Criteria andOutFileNameGreaterThanOrEqualTo(String value) {
            addCriterion("out_file_name >=", value, "outFileName");
            return (Criteria) this;
        }

        public Criteria andOutFileNameLessThan(String value) {
            addCriterion("out_file_name <", value, "outFileName");
            return (Criteria) this;
        }

        public Criteria andOutFileNameLessThanOrEqualTo(String value) {
            addCriterion("out_file_name <=", value, "outFileName");
            return (Criteria) this;
        }

        public Criteria andOutFileNameLike(String value) {
            addCriterion("out_file_name like", value, "outFileName");
            return (Criteria) this;
        }

        public Criteria andOutFileNameNotLike(String value) {
            addCriterion("out_file_name not like", value, "outFileName");
            return (Criteria) this;
        }

        public Criteria andOutFileNameIn(List<String> values) {
            addCriterion("out_file_name in", values, "outFileName");
            return (Criteria) this;
        }

        public Criteria andOutFileNameNotIn(List<String> values) {
            addCriterion("out_file_name not in", values, "outFileName");
            return (Criteria) this;
        }

        public Criteria andOutFileNameBetween(String value1, String value2) {
            addCriterion("out_file_name between", value1, value2, "outFileName");
            return (Criteria) this;
        }

        public Criteria andOutFileNameNotBetween(String value1, String value2) {
            addCriterion("out_file_name not between", value1, value2, "outFileName");
            return (Criteria) this;
        }

        public Criteria andSaveStatusIsNull() {
            addCriterion("save_status is null");
            return (Criteria) this;
        }

        public Criteria andSaveStatusIsNotNull() {
            addCriterion("save_status is not null");
            return (Criteria) this;
        }

        public Criteria andSaveStatusEqualTo(String value) {
            addCriterion("save_status =", value, "saveStatus");
            return (Criteria) this;
        }

        public Criteria andSaveStatusNotEqualTo(String value) {
            addCriterion("save_status <>", value, "saveStatus");
            return (Criteria) this;
        }

        public Criteria andSaveStatusGreaterThan(String value) {
            addCriterion("save_status >", value, "saveStatus");
            return (Criteria) this;
        }

        public Criteria andSaveStatusGreaterThanOrEqualTo(String value) {
            addCriterion("save_status >=", value, "saveStatus");
            return (Criteria) this;
        }

        public Criteria andSaveStatusLessThan(String value) {
            addCriterion("save_status <", value, "saveStatus");
            return (Criteria) this;
        }

        public Criteria andSaveStatusLessThanOrEqualTo(String value) {
            addCriterion("save_status <=", value, "saveStatus");
            return (Criteria) this;
        }

        public Criteria andSaveStatusLike(String value) {
            addCriterion("save_status like", value, "saveStatus");
            return (Criteria) this;
        }

        public Criteria andSaveStatusNotLike(String value) {
            addCriterion("save_status not like", value, "saveStatus");
            return (Criteria) this;
        }

        public Criteria andSaveStatusIn(List<String> values) {
            addCriterion("save_status in", values, "saveStatus");
            return (Criteria) this;
        }

        public Criteria andSaveStatusNotIn(List<String> values) {
            addCriterion("save_status not in", values, "saveStatus");
            return (Criteria) this;
        }

        public Criteria andSaveStatusBetween(String value1, String value2) {
            addCriterion("save_status between", value1, value2, "saveStatus");
            return (Criteria) this;
        }

        public Criteria andSaveStatusNotBetween(String value1, String value2) {
            addCriterion("save_status not between", value1, value2, "saveStatus");
            return (Criteria) this;
        }

        public Criteria addConditionSql(String conditionSql) {
            addCriterion(conditionSql);
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