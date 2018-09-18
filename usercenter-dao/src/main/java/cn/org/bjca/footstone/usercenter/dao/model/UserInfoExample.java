package cn.org.bjca.footstone.usercenter.dao.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private String sumCol;

    private Integer offset;

    private Integer limit;

    public UserInfoExample() {
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

    public UserInfoExample sumId() {
        this.sumCol="id";
        return this;
    }

    public UserInfoExample sumHeadImgUrl() {
        this.sumCol="head_img_url";
        return this;
    }

    public UserInfoExample sumName() {
        this.sumCol="name";
        return this;
    }

    public UserInfoExample sumIdType() {
        this.sumCol="id_type";
        return this;
    }

    public UserInfoExample sumIdNum() {
        this.sumCol="id_num";
        return this;
    }

    public UserInfoExample sumMobile() {
        this.sumCol="mobile";
        return this;
    }

    public UserInfoExample sumEmail() {
        this.sumCol="email";
        return this;
    }

    public UserInfoExample sumRealNameFlag() {
        this.sumCol="real_name_flag";
        return this;
    }

    public UserInfoExample sumRealNameType() {
        this.sumCol="real_name_type";
        return this;
    }

    public UserInfoExample sumReviewFlag() {
        this.sumCol="review_flag";
        return this;
    }

    public UserInfoExample sumBankCardNum() {
        this.sumCol="bank_card_num";
        return this;
    }

    public UserInfoExample sumFaceIdImageUrl() {
        this.sumCol="face_id_image_url";
        return this;
    }

    public UserInfoExample sumIdCardFrontImageUrl() {
        this.sumCol="id_card_front_image_url";
        return this;
    }

    public UserInfoExample sumIdCardBackImageUrl() {
        this.sumCol="id_card_back_image_url";
        return this;
    }

    public UserInfoExample sumAppId() {
        this.sumCol="app_id";
        return this;
    }

    public UserInfoExample sumStatus() {
        this.sumCol="status";
        return this;
    }

    public UserInfoExample sumExtField1() {
        this.sumCol="ext_field1";
        return this;
    }

    public UserInfoExample sumExtField2() {
        this.sumCol="ext_field2";
        return this;
    }

    public UserInfoExample sumExtField3() {
        this.sumCol="ext_field3";
        return this;
    }

    public UserInfoExample sumExtField4() {
        this.sumCol="ext_field4";
        return this;
    }

    public UserInfoExample sumExtConfig() {
        this.sumCol="ext_config";
        return this;
    }

    public UserInfoExample sumVersion() {
        this.sumCol="version";
        return this;
    }

    public UserInfoExample sumCreateTime() {
        this.sumCol="create_time";
        return this;
    }

    public UserInfoExample sumUpdateTime() {
        this.sumCol="update_time";
        return this;
    }

    public UserInfoExample sumOper() {
        this.sumCol="oper";
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

    public UserInfoExample page(int offset, int limit) {
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

        public Criteria andHeadImgUrlIsNull() {
            addCriterion("head_img_url is null");
            return (Criteria) this;
        }

        public Criteria andHeadImgUrlIsNotNull() {
            addCriterion("head_img_url is not null");
            return (Criteria) this;
        }

        public Criteria andHeadImgUrlEqualTo(String value) {
            addCriterion("head_img_url =", value, "headImgUrl");
            return (Criteria) this;
        }

        public Criteria andHeadImgUrlNotEqualTo(String value) {
            addCriterion("head_img_url <>", value, "headImgUrl");
            return (Criteria) this;
        }

        public Criteria andHeadImgUrlGreaterThan(String value) {
            addCriterion("head_img_url >", value, "headImgUrl");
            return (Criteria) this;
        }

        public Criteria andHeadImgUrlGreaterThanOrEqualTo(String value) {
            addCriterion("head_img_url >=", value, "headImgUrl");
            return (Criteria) this;
        }

        public Criteria andHeadImgUrlLessThan(String value) {
            addCriterion("head_img_url <", value, "headImgUrl");
            return (Criteria) this;
        }

        public Criteria andHeadImgUrlLessThanOrEqualTo(String value) {
            addCriterion("head_img_url <=", value, "headImgUrl");
            return (Criteria) this;
        }

        public Criteria andHeadImgUrlLike(String value) {
            addCriterion("head_img_url like", value, "headImgUrl");
            return (Criteria) this;
        }

        public Criteria andHeadImgUrlNotLike(String value) {
            addCriterion("head_img_url not like", value, "headImgUrl");
            return (Criteria) this;
        }

        public Criteria andHeadImgUrlIn(List<String> values) {
            addCriterion("head_img_url in", values, "headImgUrl");
            return (Criteria) this;
        }

        public Criteria andHeadImgUrlNotIn(List<String> values) {
            addCriterion("head_img_url not in", values, "headImgUrl");
            return (Criteria) this;
        }

        public Criteria andHeadImgUrlBetween(String value1, String value2) {
            addCriterion("head_img_url between", value1, value2, "headImgUrl");
            return (Criteria) this;
        }

        public Criteria andHeadImgUrlNotBetween(String value1, String value2) {
            addCriterion("head_img_url not between", value1, value2, "headImgUrl");
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

        public Criteria andIdTypeIsNull() {
            addCriterion("id_type is null");
            return (Criteria) this;
        }

        public Criteria andIdTypeIsNotNull() {
            addCriterion("id_type is not null");
            return (Criteria) this;
        }

        public Criteria andIdTypeEqualTo(String value) {
            addCriterion("id_type =", value, "idType");
            return (Criteria) this;
        }

        public Criteria andIdTypeNotEqualTo(String value) {
            addCriterion("id_type <>", value, "idType");
            return (Criteria) this;
        }

        public Criteria andIdTypeGreaterThan(String value) {
            addCriterion("id_type >", value, "idType");
            return (Criteria) this;
        }

        public Criteria andIdTypeGreaterThanOrEqualTo(String value) {
            addCriterion("id_type >=", value, "idType");
            return (Criteria) this;
        }

        public Criteria andIdTypeLessThan(String value) {
            addCriterion("id_type <", value, "idType");
            return (Criteria) this;
        }

        public Criteria andIdTypeLessThanOrEqualTo(String value) {
            addCriterion("id_type <=", value, "idType");
            return (Criteria) this;
        }

        public Criteria andIdTypeLike(String value) {
            addCriterion("id_type like", value, "idType");
            return (Criteria) this;
        }

        public Criteria andIdTypeNotLike(String value) {
            addCriterion("id_type not like", value, "idType");
            return (Criteria) this;
        }

        public Criteria andIdTypeIn(List<String> values) {
            addCriterion("id_type in", values, "idType");
            return (Criteria) this;
        }

        public Criteria andIdTypeNotIn(List<String> values) {
            addCriterion("id_type not in", values, "idType");
            return (Criteria) this;
        }

        public Criteria andIdTypeBetween(String value1, String value2) {
            addCriterion("id_type between", value1, value2, "idType");
            return (Criteria) this;
        }

        public Criteria andIdTypeNotBetween(String value1, String value2) {
            addCriterion("id_type not between", value1, value2, "idType");
            return (Criteria) this;
        }

        public Criteria andIdNumIsNull() {
            addCriterion("id_num is null");
            return (Criteria) this;
        }

        public Criteria andIdNumIsNotNull() {
            addCriterion("id_num is not null");
            return (Criteria) this;
        }

        public Criteria andIdNumEqualTo(String value) {
            addCriterion("id_num =", value, "idNum");
            return (Criteria) this;
        }

        public Criteria andIdNumNotEqualTo(String value) {
            addCriterion("id_num <>", value, "idNum");
            return (Criteria) this;
        }

        public Criteria andIdNumGreaterThan(String value) {
            addCriterion("id_num >", value, "idNum");
            return (Criteria) this;
        }

        public Criteria andIdNumGreaterThanOrEqualTo(String value) {
            addCriterion("id_num >=", value, "idNum");
            return (Criteria) this;
        }

        public Criteria andIdNumLessThan(String value) {
            addCriterion("id_num <", value, "idNum");
            return (Criteria) this;
        }

        public Criteria andIdNumLessThanOrEqualTo(String value) {
            addCriterion("id_num <=", value, "idNum");
            return (Criteria) this;
        }

        public Criteria andIdNumLike(String value) {
            addCriterion("id_num like", value, "idNum");
            return (Criteria) this;
        }

        public Criteria andIdNumNotLike(String value) {
            addCriterion("id_num not like", value, "idNum");
            return (Criteria) this;
        }

        public Criteria andIdNumIn(List<String> values) {
            addCriterion("id_num in", values, "idNum");
            return (Criteria) this;
        }

        public Criteria andIdNumNotIn(List<String> values) {
            addCriterion("id_num not in", values, "idNum");
            return (Criteria) this;
        }

        public Criteria andIdNumBetween(String value1, String value2) {
            addCriterion("id_num between", value1, value2, "idNum");
            return (Criteria) this;
        }

        public Criteria andIdNumNotBetween(String value1, String value2) {
            addCriterion("id_num not between", value1, value2, "idNum");
            return (Criteria) this;
        }

        public Criteria andMobileIsNull() {
            addCriterion("mobile is null");
            return (Criteria) this;
        }

        public Criteria andMobileIsNotNull() {
            addCriterion("mobile is not null");
            return (Criteria) this;
        }

        public Criteria andMobileEqualTo(String value) {
            addCriterion("mobile =", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotEqualTo(String value) {
            addCriterion("mobile <>", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileGreaterThan(String value) {
            addCriterion("mobile >", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileGreaterThanOrEqualTo(String value) {
            addCriterion("mobile >=", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLessThan(String value) {
            addCriterion("mobile <", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLessThanOrEqualTo(String value) {
            addCriterion("mobile <=", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLike(String value) {
            addCriterion("mobile like", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotLike(String value) {
            addCriterion("mobile not like", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileIn(List<String> values) {
            addCriterion("mobile in", values, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotIn(List<String> values) {
            addCriterion("mobile not in", values, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileBetween(String value1, String value2) {
            addCriterion("mobile between", value1, value2, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotBetween(String value1, String value2) {
            addCriterion("mobile not between", value1, value2, "mobile");
            return (Criteria) this;
        }

        public Criteria andEmailIsNull() {
            addCriterion("email is null");
            return (Criteria) this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("email is not null");
            return (Criteria) this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("email =", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("email <>", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("email >", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("email >=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("email <", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("email <=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("email like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("email not like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailIn(List<String> values) {
            addCriterion("email in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotIn(List<String> values) {
            addCriterion("email not in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("email between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("email not between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andRealNameFlagIsNull() {
            addCriterion("real_name_flag is null");
            return (Criteria) this;
        }

        public Criteria andRealNameFlagIsNotNull() {
            addCriterion("real_name_flag is not null");
            return (Criteria) this;
        }

        public Criteria andRealNameFlagEqualTo(Integer value) {
            addCriterion("real_name_flag =", value, "realNameFlag");
            return (Criteria) this;
        }

        public Criteria andRealNameFlagNotEqualTo(Integer value) {
            addCriterion("real_name_flag <>", value, "realNameFlag");
            return (Criteria) this;
        }

        public Criteria andRealNameFlagGreaterThan(Integer value) {
            addCriterion("real_name_flag >", value, "realNameFlag");
            return (Criteria) this;
        }

        public Criteria andRealNameFlagGreaterThanOrEqualTo(Integer value) {
            addCriterion("real_name_flag >=", value, "realNameFlag");
            return (Criteria) this;
        }

        public Criteria andRealNameFlagLessThan(Integer value) {
            addCriterion("real_name_flag <", value, "realNameFlag");
            return (Criteria) this;
        }

        public Criteria andRealNameFlagLessThanOrEqualTo(Integer value) {
            addCriterion("real_name_flag <=", value, "realNameFlag");
            return (Criteria) this;
        }

        public Criteria andRealNameFlagIn(List<Integer> values) {
            addCriterion("real_name_flag in", values, "realNameFlag");
            return (Criteria) this;
        }

        public Criteria andRealNameFlagNotIn(List<Integer> values) {
            addCriterion("real_name_flag not in", values, "realNameFlag");
            return (Criteria) this;
        }

        public Criteria andRealNameFlagBetween(Integer value1, Integer value2) {
            addCriterion("real_name_flag between", value1, value2, "realNameFlag");
            return (Criteria) this;
        }

        public Criteria andRealNameFlagNotBetween(Integer value1, Integer value2) {
            addCriterion("real_name_flag not between", value1, value2, "realNameFlag");
            return (Criteria) this;
        }

        public Criteria andRealNameTypeIsNull() {
            addCriterion("real_name_type is null");
            return (Criteria) this;
        }

        public Criteria andRealNameTypeIsNotNull() {
            addCriterion("real_name_type is not null");
            return (Criteria) this;
        }

        public Criteria andRealNameTypeEqualTo(String value) {
            addCriterion("real_name_type =", value, "realNameType");
            return (Criteria) this;
        }

        public Criteria andRealNameTypeNotEqualTo(String value) {
            addCriterion("real_name_type <>", value, "realNameType");
            return (Criteria) this;
        }

        public Criteria andRealNameTypeGreaterThan(String value) {
            addCriterion("real_name_type >", value, "realNameType");
            return (Criteria) this;
        }

        public Criteria andRealNameTypeGreaterThanOrEqualTo(String value) {
            addCriterion("real_name_type >=", value, "realNameType");
            return (Criteria) this;
        }

        public Criteria andRealNameTypeLessThan(String value) {
            addCriterion("real_name_type <", value, "realNameType");
            return (Criteria) this;
        }

        public Criteria andRealNameTypeLessThanOrEqualTo(String value) {
            addCriterion("real_name_type <=", value, "realNameType");
            return (Criteria) this;
        }

        public Criteria andRealNameTypeLike(String value) {
            addCriterion("real_name_type like", value, "realNameType");
            return (Criteria) this;
        }

        public Criteria andRealNameTypeNotLike(String value) {
            addCriterion("real_name_type not like", value, "realNameType");
            return (Criteria) this;
        }

        public Criteria andRealNameTypeIn(List<String> values) {
            addCriterion("real_name_type in", values, "realNameType");
            return (Criteria) this;
        }

        public Criteria andRealNameTypeNotIn(List<String> values) {
            addCriterion("real_name_type not in", values, "realNameType");
            return (Criteria) this;
        }

        public Criteria andRealNameTypeBetween(String value1, String value2) {
            addCriterion("real_name_type between", value1, value2, "realNameType");
            return (Criteria) this;
        }

        public Criteria andRealNameTypeNotBetween(String value1, String value2) {
            addCriterion("real_name_type not between", value1, value2, "realNameType");
            return (Criteria) this;
        }

        public Criteria andReviewFlagIsNull() {
            addCriterion("review_flag is null");
            return (Criteria) this;
        }

        public Criteria andReviewFlagIsNotNull() {
            addCriterion("review_flag is not null");
            return (Criteria) this;
        }

        public Criteria andReviewFlagEqualTo(Boolean value) {
            addCriterion("review_flag =", value, "reviewFlag");
            return (Criteria) this;
        }

        public Criteria andReviewFlagNotEqualTo(Boolean value) {
            addCriterion("review_flag <>", value, "reviewFlag");
            return (Criteria) this;
        }

        public Criteria andReviewFlagGreaterThan(Boolean value) {
            addCriterion("review_flag >", value, "reviewFlag");
            return (Criteria) this;
        }

        public Criteria andReviewFlagGreaterThanOrEqualTo(Boolean value) {
            addCriterion("review_flag >=", value, "reviewFlag");
            return (Criteria) this;
        }

        public Criteria andReviewFlagLessThan(Boolean value) {
            addCriterion("review_flag <", value, "reviewFlag");
            return (Criteria) this;
        }

        public Criteria andReviewFlagLessThanOrEqualTo(Boolean value) {
            addCriterion("review_flag <=", value, "reviewFlag");
            return (Criteria) this;
        }

        public Criteria andReviewFlagIn(List<Boolean> values) {
            addCriterion("review_flag in", values, "reviewFlag");
            return (Criteria) this;
        }

        public Criteria andReviewFlagNotIn(List<Boolean> values) {
            addCriterion("review_flag not in", values, "reviewFlag");
            return (Criteria) this;
        }

        public Criteria andReviewFlagBetween(Boolean value1, Boolean value2) {
            addCriterion("review_flag between", value1, value2, "reviewFlag");
            return (Criteria) this;
        }

        public Criteria andReviewFlagNotBetween(Boolean value1, Boolean value2) {
            addCriterion("review_flag not between", value1, value2, "reviewFlag");
            return (Criteria) this;
        }

        public Criteria andBankCardNumIsNull() {
            addCriterion("bank_card_num is null");
            return (Criteria) this;
        }

        public Criteria andBankCardNumIsNotNull() {
            addCriterion("bank_card_num is not null");
            return (Criteria) this;
        }

        public Criteria andBankCardNumEqualTo(String value) {
            addCriterion("bank_card_num =", value, "bankCardNum");
            return (Criteria) this;
        }

        public Criteria andBankCardNumNotEqualTo(String value) {
            addCriterion("bank_card_num <>", value, "bankCardNum");
            return (Criteria) this;
        }

        public Criteria andBankCardNumGreaterThan(String value) {
            addCriterion("bank_card_num >", value, "bankCardNum");
            return (Criteria) this;
        }

        public Criteria andBankCardNumGreaterThanOrEqualTo(String value) {
            addCriterion("bank_card_num >=", value, "bankCardNum");
            return (Criteria) this;
        }

        public Criteria andBankCardNumLessThan(String value) {
            addCriterion("bank_card_num <", value, "bankCardNum");
            return (Criteria) this;
        }

        public Criteria andBankCardNumLessThanOrEqualTo(String value) {
            addCriterion("bank_card_num <=", value, "bankCardNum");
            return (Criteria) this;
        }

        public Criteria andBankCardNumLike(String value) {
            addCriterion("bank_card_num like", value, "bankCardNum");
            return (Criteria) this;
        }

        public Criteria andBankCardNumNotLike(String value) {
            addCriterion("bank_card_num not like", value, "bankCardNum");
            return (Criteria) this;
        }

        public Criteria andBankCardNumIn(List<String> values) {
            addCriterion("bank_card_num in", values, "bankCardNum");
            return (Criteria) this;
        }

        public Criteria andBankCardNumNotIn(List<String> values) {
            addCriterion("bank_card_num not in", values, "bankCardNum");
            return (Criteria) this;
        }

        public Criteria andBankCardNumBetween(String value1, String value2) {
            addCriterion("bank_card_num between", value1, value2, "bankCardNum");
            return (Criteria) this;
        }

        public Criteria andBankCardNumNotBetween(String value1, String value2) {
            addCriterion("bank_card_num not between", value1, value2, "bankCardNum");
            return (Criteria) this;
        }

        public Criteria andFaceIdImageUrlIsNull() {
            addCriterion("face_id_image_url is null");
            return (Criteria) this;
        }

        public Criteria andFaceIdImageUrlIsNotNull() {
            addCriterion("face_id_image_url is not null");
            return (Criteria) this;
        }

        public Criteria andFaceIdImageUrlEqualTo(String value) {
            addCriterion("face_id_image_url =", value, "faceIdImageUrl");
            return (Criteria) this;
        }

        public Criteria andFaceIdImageUrlNotEqualTo(String value) {
            addCriterion("face_id_image_url <>", value, "faceIdImageUrl");
            return (Criteria) this;
        }

        public Criteria andFaceIdImageUrlGreaterThan(String value) {
            addCriterion("face_id_image_url >", value, "faceIdImageUrl");
            return (Criteria) this;
        }

        public Criteria andFaceIdImageUrlGreaterThanOrEqualTo(String value) {
            addCriterion("face_id_image_url >=", value, "faceIdImageUrl");
            return (Criteria) this;
        }

        public Criteria andFaceIdImageUrlLessThan(String value) {
            addCriterion("face_id_image_url <", value, "faceIdImageUrl");
            return (Criteria) this;
        }

        public Criteria andFaceIdImageUrlLessThanOrEqualTo(String value) {
            addCriterion("face_id_image_url <=", value, "faceIdImageUrl");
            return (Criteria) this;
        }

        public Criteria andFaceIdImageUrlLike(String value) {
            addCriterion("face_id_image_url like", value, "faceIdImageUrl");
            return (Criteria) this;
        }

        public Criteria andFaceIdImageUrlNotLike(String value) {
            addCriterion("face_id_image_url not like", value, "faceIdImageUrl");
            return (Criteria) this;
        }

        public Criteria andFaceIdImageUrlIn(List<String> values) {
            addCriterion("face_id_image_url in", values, "faceIdImageUrl");
            return (Criteria) this;
        }

        public Criteria andFaceIdImageUrlNotIn(List<String> values) {
            addCriterion("face_id_image_url not in", values, "faceIdImageUrl");
            return (Criteria) this;
        }

        public Criteria andFaceIdImageUrlBetween(String value1, String value2) {
            addCriterion("face_id_image_url between", value1, value2, "faceIdImageUrl");
            return (Criteria) this;
        }

        public Criteria andFaceIdImageUrlNotBetween(String value1, String value2) {
            addCriterion("face_id_image_url not between", value1, value2, "faceIdImageUrl");
            return (Criteria) this;
        }

        public Criteria andIdCardFrontImageUrlIsNull() {
            addCriterion("id_card_front_image_url is null");
            return (Criteria) this;
        }

        public Criteria andIdCardFrontImageUrlIsNotNull() {
            addCriterion("id_card_front_image_url is not null");
            return (Criteria) this;
        }

        public Criteria andIdCardFrontImageUrlEqualTo(String value) {
            addCriterion("id_card_front_image_url =", value, "idCardFrontImageUrl");
            return (Criteria) this;
        }

        public Criteria andIdCardFrontImageUrlNotEqualTo(String value) {
            addCriterion("id_card_front_image_url <>", value, "idCardFrontImageUrl");
            return (Criteria) this;
        }

        public Criteria andIdCardFrontImageUrlGreaterThan(String value) {
            addCriterion("id_card_front_image_url >", value, "idCardFrontImageUrl");
            return (Criteria) this;
        }

        public Criteria andIdCardFrontImageUrlGreaterThanOrEqualTo(String value) {
            addCriterion("id_card_front_image_url >=", value, "idCardFrontImageUrl");
            return (Criteria) this;
        }

        public Criteria andIdCardFrontImageUrlLessThan(String value) {
            addCriterion("id_card_front_image_url <", value, "idCardFrontImageUrl");
            return (Criteria) this;
        }

        public Criteria andIdCardFrontImageUrlLessThanOrEqualTo(String value) {
            addCriterion("id_card_front_image_url <=", value, "idCardFrontImageUrl");
            return (Criteria) this;
        }

        public Criteria andIdCardFrontImageUrlLike(String value) {
            addCriterion("id_card_front_image_url like", value, "idCardFrontImageUrl");
            return (Criteria) this;
        }

        public Criteria andIdCardFrontImageUrlNotLike(String value) {
            addCriterion("id_card_front_image_url not like", value, "idCardFrontImageUrl");
            return (Criteria) this;
        }

        public Criteria andIdCardFrontImageUrlIn(List<String> values) {
            addCriterion("id_card_front_image_url in", values, "idCardFrontImageUrl");
            return (Criteria) this;
        }

        public Criteria andIdCardFrontImageUrlNotIn(List<String> values) {
            addCriterion("id_card_front_image_url not in", values, "idCardFrontImageUrl");
            return (Criteria) this;
        }

        public Criteria andIdCardFrontImageUrlBetween(String value1, String value2) {
            addCriterion("id_card_front_image_url between", value1, value2, "idCardFrontImageUrl");
            return (Criteria) this;
        }

        public Criteria andIdCardFrontImageUrlNotBetween(String value1, String value2) {
            addCriterion("id_card_front_image_url not between", value1, value2, "idCardFrontImageUrl");
            return (Criteria) this;
        }

        public Criteria andIdCardBackImageUrlIsNull() {
            addCriterion("id_card_back_image_url is null");
            return (Criteria) this;
        }

        public Criteria andIdCardBackImageUrlIsNotNull() {
            addCriterion("id_card_back_image_url is not null");
            return (Criteria) this;
        }

        public Criteria andIdCardBackImageUrlEqualTo(String value) {
            addCriterion("id_card_back_image_url =", value, "idCardBackImageUrl");
            return (Criteria) this;
        }

        public Criteria andIdCardBackImageUrlNotEqualTo(String value) {
            addCriterion("id_card_back_image_url <>", value, "idCardBackImageUrl");
            return (Criteria) this;
        }

        public Criteria andIdCardBackImageUrlGreaterThan(String value) {
            addCriterion("id_card_back_image_url >", value, "idCardBackImageUrl");
            return (Criteria) this;
        }

        public Criteria andIdCardBackImageUrlGreaterThanOrEqualTo(String value) {
            addCriterion("id_card_back_image_url >=", value, "idCardBackImageUrl");
            return (Criteria) this;
        }

        public Criteria andIdCardBackImageUrlLessThan(String value) {
            addCriterion("id_card_back_image_url <", value, "idCardBackImageUrl");
            return (Criteria) this;
        }

        public Criteria andIdCardBackImageUrlLessThanOrEqualTo(String value) {
            addCriterion("id_card_back_image_url <=", value, "idCardBackImageUrl");
            return (Criteria) this;
        }

        public Criteria andIdCardBackImageUrlLike(String value) {
            addCriterion("id_card_back_image_url like", value, "idCardBackImageUrl");
            return (Criteria) this;
        }

        public Criteria andIdCardBackImageUrlNotLike(String value) {
            addCriterion("id_card_back_image_url not like", value, "idCardBackImageUrl");
            return (Criteria) this;
        }

        public Criteria andIdCardBackImageUrlIn(List<String> values) {
            addCriterion("id_card_back_image_url in", values, "idCardBackImageUrl");
            return (Criteria) this;
        }

        public Criteria andIdCardBackImageUrlNotIn(List<String> values) {
            addCriterion("id_card_back_image_url not in", values, "idCardBackImageUrl");
            return (Criteria) this;
        }

        public Criteria andIdCardBackImageUrlBetween(String value1, String value2) {
            addCriterion("id_card_back_image_url between", value1, value2, "idCardBackImageUrl");
            return (Criteria) this;
        }

        public Criteria andIdCardBackImageUrlNotBetween(String value1, String value2) {
            addCriterion("id_card_back_image_url not between", value1, value2, "idCardBackImageUrl");
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

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("status like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("status not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andExtField1IsNull() {
            addCriterion("ext_field1 is null");
            return (Criteria) this;
        }

        public Criteria andExtField1IsNotNull() {
            addCriterion("ext_field1 is not null");
            return (Criteria) this;
        }

        public Criteria andExtField1EqualTo(String value) {
            addCriterion("ext_field1 =", value, "extField1");
            return (Criteria) this;
        }

        public Criteria andExtField1NotEqualTo(String value) {
            addCriterion("ext_field1 <>", value, "extField1");
            return (Criteria) this;
        }

        public Criteria andExtField1GreaterThan(String value) {
            addCriterion("ext_field1 >", value, "extField1");
            return (Criteria) this;
        }

        public Criteria andExtField1GreaterThanOrEqualTo(String value) {
            addCriterion("ext_field1 >=", value, "extField1");
            return (Criteria) this;
        }

        public Criteria andExtField1LessThan(String value) {
            addCriterion("ext_field1 <", value, "extField1");
            return (Criteria) this;
        }

        public Criteria andExtField1LessThanOrEqualTo(String value) {
            addCriterion("ext_field1 <=", value, "extField1");
            return (Criteria) this;
        }

        public Criteria andExtField1Like(String value) {
            addCriterion("ext_field1 like", value, "extField1");
            return (Criteria) this;
        }

        public Criteria andExtField1NotLike(String value) {
            addCriterion("ext_field1 not like", value, "extField1");
            return (Criteria) this;
        }

        public Criteria andExtField1In(List<String> values) {
            addCriterion("ext_field1 in", values, "extField1");
            return (Criteria) this;
        }

        public Criteria andExtField1NotIn(List<String> values) {
            addCriterion("ext_field1 not in", values, "extField1");
            return (Criteria) this;
        }

        public Criteria andExtField1Between(String value1, String value2) {
            addCriterion("ext_field1 between", value1, value2, "extField1");
            return (Criteria) this;
        }

        public Criteria andExtField1NotBetween(String value1, String value2) {
            addCriterion("ext_field1 not between", value1, value2, "extField1");
            return (Criteria) this;
        }

        public Criteria andExtField2IsNull() {
            addCriterion("ext_field2 is null");
            return (Criteria) this;
        }

        public Criteria andExtField2IsNotNull() {
            addCriterion("ext_field2 is not null");
            return (Criteria) this;
        }

        public Criteria andExtField2EqualTo(String value) {
            addCriterion("ext_field2 =", value, "extField2");
            return (Criteria) this;
        }

        public Criteria andExtField2NotEqualTo(String value) {
            addCriterion("ext_field2 <>", value, "extField2");
            return (Criteria) this;
        }

        public Criteria andExtField2GreaterThan(String value) {
            addCriterion("ext_field2 >", value, "extField2");
            return (Criteria) this;
        }

        public Criteria andExtField2GreaterThanOrEqualTo(String value) {
            addCriterion("ext_field2 >=", value, "extField2");
            return (Criteria) this;
        }

        public Criteria andExtField2LessThan(String value) {
            addCriterion("ext_field2 <", value, "extField2");
            return (Criteria) this;
        }

        public Criteria andExtField2LessThanOrEqualTo(String value) {
            addCriterion("ext_field2 <=", value, "extField2");
            return (Criteria) this;
        }

        public Criteria andExtField2Like(String value) {
            addCriterion("ext_field2 like", value, "extField2");
            return (Criteria) this;
        }

        public Criteria andExtField2NotLike(String value) {
            addCriterion("ext_field2 not like", value, "extField2");
            return (Criteria) this;
        }

        public Criteria andExtField2In(List<String> values) {
            addCriterion("ext_field2 in", values, "extField2");
            return (Criteria) this;
        }

        public Criteria andExtField2NotIn(List<String> values) {
            addCriterion("ext_field2 not in", values, "extField2");
            return (Criteria) this;
        }

        public Criteria andExtField2Between(String value1, String value2) {
            addCriterion("ext_field2 between", value1, value2, "extField2");
            return (Criteria) this;
        }

        public Criteria andExtField2NotBetween(String value1, String value2) {
            addCriterion("ext_field2 not between", value1, value2, "extField2");
            return (Criteria) this;
        }

        public Criteria andExtField3IsNull() {
            addCriterion("ext_field3 is null");
            return (Criteria) this;
        }

        public Criteria andExtField3IsNotNull() {
            addCriterion("ext_field3 is not null");
            return (Criteria) this;
        }

        public Criteria andExtField3EqualTo(String value) {
            addCriterion("ext_field3 =", value, "extField3");
            return (Criteria) this;
        }

        public Criteria andExtField3NotEqualTo(String value) {
            addCriterion("ext_field3 <>", value, "extField3");
            return (Criteria) this;
        }

        public Criteria andExtField3GreaterThan(String value) {
            addCriterion("ext_field3 >", value, "extField3");
            return (Criteria) this;
        }

        public Criteria andExtField3GreaterThanOrEqualTo(String value) {
            addCriterion("ext_field3 >=", value, "extField3");
            return (Criteria) this;
        }

        public Criteria andExtField3LessThan(String value) {
            addCriterion("ext_field3 <", value, "extField3");
            return (Criteria) this;
        }

        public Criteria andExtField3LessThanOrEqualTo(String value) {
            addCriterion("ext_field3 <=", value, "extField3");
            return (Criteria) this;
        }

        public Criteria andExtField3Like(String value) {
            addCriterion("ext_field3 like", value, "extField3");
            return (Criteria) this;
        }

        public Criteria andExtField3NotLike(String value) {
            addCriterion("ext_field3 not like", value, "extField3");
            return (Criteria) this;
        }

        public Criteria andExtField3In(List<String> values) {
            addCriterion("ext_field3 in", values, "extField3");
            return (Criteria) this;
        }

        public Criteria andExtField3NotIn(List<String> values) {
            addCriterion("ext_field3 not in", values, "extField3");
            return (Criteria) this;
        }

        public Criteria andExtField3Between(String value1, String value2) {
            addCriterion("ext_field3 between", value1, value2, "extField3");
            return (Criteria) this;
        }

        public Criteria andExtField3NotBetween(String value1, String value2) {
            addCriterion("ext_field3 not between", value1, value2, "extField3");
            return (Criteria) this;
        }

        public Criteria andExtField4IsNull() {
            addCriterion("ext_field4 is null");
            return (Criteria) this;
        }

        public Criteria andExtField4IsNotNull() {
            addCriterion("ext_field4 is not null");
            return (Criteria) this;
        }

        public Criteria andExtField4EqualTo(String value) {
            addCriterion("ext_field4 =", value, "extField4");
            return (Criteria) this;
        }

        public Criteria andExtField4NotEqualTo(String value) {
            addCriterion("ext_field4 <>", value, "extField4");
            return (Criteria) this;
        }

        public Criteria andExtField4GreaterThan(String value) {
            addCriterion("ext_field4 >", value, "extField4");
            return (Criteria) this;
        }

        public Criteria andExtField4GreaterThanOrEqualTo(String value) {
            addCriterion("ext_field4 >=", value, "extField4");
            return (Criteria) this;
        }

        public Criteria andExtField4LessThan(String value) {
            addCriterion("ext_field4 <", value, "extField4");
            return (Criteria) this;
        }

        public Criteria andExtField4LessThanOrEqualTo(String value) {
            addCriterion("ext_field4 <=", value, "extField4");
            return (Criteria) this;
        }

        public Criteria andExtField4Like(String value) {
            addCriterion("ext_field4 like", value, "extField4");
            return (Criteria) this;
        }

        public Criteria andExtField4NotLike(String value) {
            addCriterion("ext_field4 not like", value, "extField4");
            return (Criteria) this;
        }

        public Criteria andExtField4In(List<String> values) {
            addCriterion("ext_field4 in", values, "extField4");
            return (Criteria) this;
        }

        public Criteria andExtField4NotIn(List<String> values) {
            addCriterion("ext_field4 not in", values, "extField4");
            return (Criteria) this;
        }

        public Criteria andExtField4Between(String value1, String value2) {
            addCriterion("ext_field4 between", value1, value2, "extField4");
            return (Criteria) this;
        }

        public Criteria andExtField4NotBetween(String value1, String value2) {
            addCriterion("ext_field4 not between", value1, value2, "extField4");
            return (Criteria) this;
        }

        public Criteria andExtConfigIsNull() {
            addCriterion("ext_config is null");
            return (Criteria) this;
        }

        public Criteria andExtConfigIsNotNull() {
            addCriterion("ext_config is not null");
            return (Criteria) this;
        }

        public Criteria andExtConfigEqualTo(String value) {
            addCriterion("ext_config =", value, "extConfig");
            return (Criteria) this;
        }

        public Criteria andExtConfigNotEqualTo(String value) {
            addCriterion("ext_config <>", value, "extConfig");
            return (Criteria) this;
        }

        public Criteria andExtConfigGreaterThan(String value) {
            addCriterion("ext_config >", value, "extConfig");
            return (Criteria) this;
        }

        public Criteria andExtConfigGreaterThanOrEqualTo(String value) {
            addCriterion("ext_config >=", value, "extConfig");
            return (Criteria) this;
        }

        public Criteria andExtConfigLessThan(String value) {
            addCriterion("ext_config <", value, "extConfig");
            return (Criteria) this;
        }

        public Criteria andExtConfigLessThanOrEqualTo(String value) {
            addCriterion("ext_config <=", value, "extConfig");
            return (Criteria) this;
        }

        public Criteria andExtConfigLike(String value) {
            addCriterion("ext_config like", value, "extConfig");
            return (Criteria) this;
        }

        public Criteria andExtConfigNotLike(String value) {
            addCriterion("ext_config not like", value, "extConfig");
            return (Criteria) this;
        }

        public Criteria andExtConfigIn(List<String> values) {
            addCriterion("ext_config in", values, "extConfig");
            return (Criteria) this;
        }

        public Criteria andExtConfigNotIn(List<String> values) {
            addCriterion("ext_config not in", values, "extConfig");
            return (Criteria) this;
        }

        public Criteria andExtConfigBetween(String value1, String value2) {
            addCriterion("ext_config between", value1, value2, "extConfig");
            return (Criteria) this;
        }

        public Criteria andExtConfigNotBetween(String value1, String value2) {
            addCriterion("ext_config not between", value1, value2, "extConfig");
            return (Criteria) this;
        }

        public Criteria andVersionIsNull() {
            addCriterion("version is null");
            return (Criteria) this;
        }

        public Criteria andVersionIsNotNull() {
            addCriterion("version is not null");
            return (Criteria) this;
        }

        public Criteria andVersionEqualTo(Integer value) {
            addCriterion("version =", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotEqualTo(Integer value) {
            addCriterion("version <>", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThan(Integer value) {
            addCriterion("version >", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThanOrEqualTo(Integer value) {
            addCriterion("version >=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThan(Integer value) {
            addCriterion("version <", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThanOrEqualTo(Integer value) {
            addCriterion("version <=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionIn(List<Integer> values) {
            addCriterion("version in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotIn(List<Integer> values) {
            addCriterion("version not in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionBetween(Integer value1, Integer value2) {
            addCriterion("version between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotBetween(Integer value1, Integer value2) {
            addCriterion("version not between", value1, value2, "version");
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

        public Criteria andOperIsNull() {
            addCriterion("oper is null");
            return (Criteria) this;
        }

        public Criteria andOperIsNotNull() {
            addCriterion("oper is not null");
            return (Criteria) this;
        }

        public Criteria andOperEqualTo(String value) {
            addCriterion("oper =", value, "oper");
            return (Criteria) this;
        }

        public Criteria andOperNotEqualTo(String value) {
            addCriterion("oper <>", value, "oper");
            return (Criteria) this;
        }

        public Criteria andOperGreaterThan(String value) {
            addCriterion("oper >", value, "oper");
            return (Criteria) this;
        }

        public Criteria andOperGreaterThanOrEqualTo(String value) {
            addCriterion("oper >=", value, "oper");
            return (Criteria) this;
        }

        public Criteria andOperLessThan(String value) {
            addCriterion("oper <", value, "oper");
            return (Criteria) this;
        }

        public Criteria andOperLessThanOrEqualTo(String value) {
            addCriterion("oper <=", value, "oper");
            return (Criteria) this;
        }

        public Criteria andOperLike(String value) {
            addCriterion("oper like", value, "oper");
            return (Criteria) this;
        }

        public Criteria andOperNotLike(String value) {
            addCriterion("oper not like", value, "oper");
            return (Criteria) this;
        }

        public Criteria andOperIn(List<String> values) {
            addCriterion("oper in", values, "oper");
            return (Criteria) this;
        }

        public Criteria andOperNotIn(List<String> values) {
            addCriterion("oper not in", values, "oper");
            return (Criteria) this;
        }

        public Criteria andOperBetween(String value1, String value2) {
            addCriterion("oper between", value1, value2, "oper");
            return (Criteria) this;
        }

        public Criteria andOperNotBetween(String value1, String value2) {
            addCriterion("oper not between", value1, value2, "oper");
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