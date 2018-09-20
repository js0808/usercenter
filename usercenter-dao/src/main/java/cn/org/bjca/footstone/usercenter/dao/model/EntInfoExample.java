package cn.org.bjca.footstone.usercenter.dao.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EntInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private String sumCol;

    private Integer offset;

    private Integer limit;

    public EntInfoExample() {
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

    public EntInfoExample sumId() {
        this.sumCol="id";
        return this;
    }

    public EntInfoExample sumHeadImgUrl() {
        this.sumCol="head_img_url";
        return this;
    }

    public EntInfoExample sumName() {
        this.sumCol="name";
        return this;
    }

    public EntInfoExample sumPhone() {
        this.sumCol="phone";
        return this;
    }

    public EntInfoExample sumOrgCode() {
        this.sumCol="org_code";
        return this;
    }

    public EntInfoExample sumBizLicense() {
        this.sumCol="biz_license";
        return this;
    }

    public EntInfoExample sumSocialCreditCode() {
        this.sumCol="social_credit_code";
        return this;
    }

    public EntInfoExample sumLegalName() {
        this.sumCol="legal_name";
        return this;
    }

    public EntInfoExample sumLegalIdNum() {
        this.sumCol="legal_id_num";
        return this;
    }

    public EntInfoExample sumRealNameFlag() {
        this.sumCol="real_name_flag";
        return this;
    }

    public EntInfoExample sumRealNameType() {
        this.sumCol="real_name_type";
        return this;
    }

    public EntInfoExample sumReviewFlag() {
        this.sumCol="review_flag";
        return this;
    }

    public EntInfoExample sumBizLicenseImageUrl() {
        this.sumCol="biz_license_image_url";
        return this;
    }

    public EntInfoExample sumOrgCodeImageUrl() {
        this.sumCol="org_code_image_url";
        return this;
    }

    public EntInfoExample sumAppId() {
        this.sumCol="app_id";
        return this;
    }

    public EntInfoExample sumStatus() {
        this.sumCol="status";
        return this;
    }

    public EntInfoExample sumExtField1() {
        this.sumCol="ext_field1";
        return this;
    }

    public EntInfoExample sumExtField2() {
        this.sumCol="ext_field2";
        return this;
    }

    public EntInfoExample sumExtField3() {
        this.sumCol="ext_field3";
        return this;
    }

    public EntInfoExample sumExtField4() {
        this.sumCol="ext_field4";
        return this;
    }

    public EntInfoExample sumExtConfig() {
        this.sumCol="ext_config";
        return this;
    }

    public EntInfoExample sumVersion() {
        this.sumCol="version";
        return this;
    }

    public EntInfoExample sumOper() {
        this.sumCol="oper";
        return this;
    }

    public EntInfoExample sumCreateTime() {
        this.sumCol="create_time";
        return this;
    }

    public EntInfoExample sumUpdateTime() {
        this.sumCol="update_time";
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

    public EntInfoExample page(int offset, int limit) {
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

        public Criteria andPhoneIsNull() {
            addCriterion("phone is null");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNotNull() {
            addCriterion("phone is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneEqualTo(String value) {
            addCriterion("phone =", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotEqualTo(String value) {
            addCriterion("phone <>", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThan(String value) {
            addCriterion("phone >", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("phone >=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThan(String value) {
            addCriterion("phone <", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThanOrEqualTo(String value) {
            addCriterion("phone <=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLike(String value) {
            addCriterion("phone like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotLike(String value) {
            addCriterion("phone not like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneIn(List<String> values) {
            addCriterion("phone in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotIn(List<String> values) {
            addCriterion("phone not in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneBetween(String value1, String value2) {
            addCriterion("phone between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotBetween(String value1, String value2) {
            addCriterion("phone not between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andOrgCodeIsNull() {
            addCriterion("org_code is null");
            return (Criteria) this;
        }

        public Criteria andOrgCodeIsNotNull() {
            addCriterion("org_code is not null");
            return (Criteria) this;
        }

        public Criteria andOrgCodeEqualTo(String value) {
            addCriterion("org_code =", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeNotEqualTo(String value) {
            addCriterion("org_code <>", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeGreaterThan(String value) {
            addCriterion("org_code >", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeGreaterThanOrEqualTo(String value) {
            addCriterion("org_code >=", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeLessThan(String value) {
            addCriterion("org_code <", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeLessThanOrEqualTo(String value) {
            addCriterion("org_code <=", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeLike(String value) {
            addCriterion("org_code like", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeNotLike(String value) {
            addCriterion("org_code not like", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeIn(List<String> values) {
            addCriterion("org_code in", values, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeNotIn(List<String> values) {
            addCriterion("org_code not in", values, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeBetween(String value1, String value2) {
            addCriterion("org_code between", value1, value2, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeNotBetween(String value1, String value2) {
            addCriterion("org_code not between", value1, value2, "orgCode");
            return (Criteria) this;
        }

        public Criteria andBizLicenseIsNull() {
            addCriterion("biz_license is null");
            return (Criteria) this;
        }

        public Criteria andBizLicenseIsNotNull() {
            addCriterion("biz_license is not null");
            return (Criteria) this;
        }

        public Criteria andBizLicenseEqualTo(String value) {
            addCriterion("biz_license =", value, "bizLicense");
            return (Criteria) this;
        }

        public Criteria andBizLicenseNotEqualTo(String value) {
            addCriterion("biz_license <>", value, "bizLicense");
            return (Criteria) this;
        }

        public Criteria andBizLicenseGreaterThan(String value) {
            addCriterion("biz_license >", value, "bizLicense");
            return (Criteria) this;
        }

        public Criteria andBizLicenseGreaterThanOrEqualTo(String value) {
            addCriterion("biz_license >=", value, "bizLicense");
            return (Criteria) this;
        }

        public Criteria andBizLicenseLessThan(String value) {
            addCriterion("biz_license <", value, "bizLicense");
            return (Criteria) this;
        }

        public Criteria andBizLicenseLessThanOrEqualTo(String value) {
            addCriterion("biz_license <=", value, "bizLicense");
            return (Criteria) this;
        }

        public Criteria andBizLicenseLike(String value) {
            addCriterion("biz_license like", value, "bizLicense");
            return (Criteria) this;
        }

        public Criteria andBizLicenseNotLike(String value) {
            addCriterion("biz_license not like", value, "bizLicense");
            return (Criteria) this;
        }

        public Criteria andBizLicenseIn(List<String> values) {
            addCriterion("biz_license in", values, "bizLicense");
            return (Criteria) this;
        }

        public Criteria andBizLicenseNotIn(List<String> values) {
            addCriterion("biz_license not in", values, "bizLicense");
            return (Criteria) this;
        }

        public Criteria andBizLicenseBetween(String value1, String value2) {
            addCriterion("biz_license between", value1, value2, "bizLicense");
            return (Criteria) this;
        }

        public Criteria andBizLicenseNotBetween(String value1, String value2) {
            addCriterion("biz_license not between", value1, value2, "bizLicense");
            return (Criteria) this;
        }

        public Criteria andSocialCreditCodeIsNull() {
            addCriterion("social_credit_code is null");
            return (Criteria) this;
        }

        public Criteria andSocialCreditCodeIsNotNull() {
            addCriterion("social_credit_code is not null");
            return (Criteria) this;
        }

        public Criteria andSocialCreditCodeEqualTo(String value) {
            addCriterion("social_credit_code =", value, "socialCreditCode");
            return (Criteria) this;
        }

        public Criteria andSocialCreditCodeNotEqualTo(String value) {
            addCriterion("social_credit_code <>", value, "socialCreditCode");
            return (Criteria) this;
        }

        public Criteria andSocialCreditCodeGreaterThan(String value) {
            addCriterion("social_credit_code >", value, "socialCreditCode");
            return (Criteria) this;
        }

        public Criteria andSocialCreditCodeGreaterThanOrEqualTo(String value) {
            addCriterion("social_credit_code >=", value, "socialCreditCode");
            return (Criteria) this;
        }

        public Criteria andSocialCreditCodeLessThan(String value) {
            addCriterion("social_credit_code <", value, "socialCreditCode");
            return (Criteria) this;
        }

        public Criteria andSocialCreditCodeLessThanOrEqualTo(String value) {
            addCriterion("social_credit_code <=", value, "socialCreditCode");
            return (Criteria) this;
        }

        public Criteria andSocialCreditCodeLike(String value) {
            addCriterion("social_credit_code like", value, "socialCreditCode");
            return (Criteria) this;
        }

        public Criteria andSocialCreditCodeNotLike(String value) {
            addCriterion("social_credit_code not like", value, "socialCreditCode");
            return (Criteria) this;
        }

        public Criteria andSocialCreditCodeIn(List<String> values) {
            addCriterion("social_credit_code in", values, "socialCreditCode");
            return (Criteria) this;
        }

        public Criteria andSocialCreditCodeNotIn(List<String> values) {
            addCriterion("social_credit_code not in", values, "socialCreditCode");
            return (Criteria) this;
        }

        public Criteria andSocialCreditCodeBetween(String value1, String value2) {
            addCriterion("social_credit_code between", value1, value2, "socialCreditCode");
            return (Criteria) this;
        }

        public Criteria andSocialCreditCodeNotBetween(String value1, String value2) {
            addCriterion("social_credit_code not between", value1, value2, "socialCreditCode");
            return (Criteria) this;
        }

        public Criteria andLegalNameIsNull() {
            addCriterion("legal_name is null");
            return (Criteria) this;
        }

        public Criteria andLegalNameIsNotNull() {
            addCriterion("legal_name is not null");
            return (Criteria) this;
        }

        public Criteria andLegalNameEqualTo(String value) {
            addCriterion("legal_name =", value, "legalName");
            return (Criteria) this;
        }

        public Criteria andLegalNameNotEqualTo(String value) {
            addCriterion("legal_name <>", value, "legalName");
            return (Criteria) this;
        }

        public Criteria andLegalNameGreaterThan(String value) {
            addCriterion("legal_name >", value, "legalName");
            return (Criteria) this;
        }

        public Criteria andLegalNameGreaterThanOrEqualTo(String value) {
            addCriterion("legal_name >=", value, "legalName");
            return (Criteria) this;
        }

        public Criteria andLegalNameLessThan(String value) {
            addCriterion("legal_name <", value, "legalName");
            return (Criteria) this;
        }

        public Criteria andLegalNameLessThanOrEqualTo(String value) {
            addCriterion("legal_name <=", value, "legalName");
            return (Criteria) this;
        }

        public Criteria andLegalNameLike(String value) {
            addCriterion("legal_name like", value, "legalName");
            return (Criteria) this;
        }

        public Criteria andLegalNameNotLike(String value) {
            addCriterion("legal_name not like", value, "legalName");
            return (Criteria) this;
        }

        public Criteria andLegalNameIn(List<String> values) {
            addCriterion("legal_name in", values, "legalName");
            return (Criteria) this;
        }

        public Criteria andLegalNameNotIn(List<String> values) {
            addCriterion("legal_name not in", values, "legalName");
            return (Criteria) this;
        }

        public Criteria andLegalNameBetween(String value1, String value2) {
            addCriterion("legal_name between", value1, value2, "legalName");
            return (Criteria) this;
        }

        public Criteria andLegalNameNotBetween(String value1, String value2) {
            addCriterion("legal_name not between", value1, value2, "legalName");
            return (Criteria) this;
        }

        public Criteria andLegalIdNumIsNull() {
            addCriterion("legal_id_num is null");
            return (Criteria) this;
        }

        public Criteria andLegalIdNumIsNotNull() {
            addCriterion("legal_id_num is not null");
            return (Criteria) this;
        }

        public Criteria andLegalIdNumEqualTo(String value) {
            addCriterion("legal_id_num =", value, "legalIdNum");
            return (Criteria) this;
        }

        public Criteria andLegalIdNumNotEqualTo(String value) {
            addCriterion("legal_id_num <>", value, "legalIdNum");
            return (Criteria) this;
        }

        public Criteria andLegalIdNumGreaterThan(String value) {
            addCriterion("legal_id_num >", value, "legalIdNum");
            return (Criteria) this;
        }

        public Criteria andLegalIdNumGreaterThanOrEqualTo(String value) {
            addCriterion("legal_id_num >=", value, "legalIdNum");
            return (Criteria) this;
        }

        public Criteria andLegalIdNumLessThan(String value) {
            addCriterion("legal_id_num <", value, "legalIdNum");
            return (Criteria) this;
        }

        public Criteria andLegalIdNumLessThanOrEqualTo(String value) {
            addCriterion("legal_id_num <=", value, "legalIdNum");
            return (Criteria) this;
        }

        public Criteria andLegalIdNumLike(String value) {
            addCriterion("legal_id_num like", value, "legalIdNum");
            return (Criteria) this;
        }

        public Criteria andLegalIdNumNotLike(String value) {
            addCriterion("legal_id_num not like", value, "legalIdNum");
            return (Criteria) this;
        }

        public Criteria andLegalIdNumIn(List<String> values) {
            addCriterion("legal_id_num in", values, "legalIdNum");
            return (Criteria) this;
        }

        public Criteria andLegalIdNumNotIn(List<String> values) {
            addCriterion("legal_id_num not in", values, "legalIdNum");
            return (Criteria) this;
        }

        public Criteria andLegalIdNumBetween(String value1, String value2) {
            addCriterion("legal_id_num between", value1, value2, "legalIdNum");
            return (Criteria) this;
        }

        public Criteria andLegalIdNumNotBetween(String value1, String value2) {
            addCriterion("legal_id_num not between", value1, value2, "legalIdNum");
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

        public Criteria andReviewFlagEqualTo(Integer value) {
            addCriterion("review_flag =", value, "reviewFlag");
            return (Criteria) this;
        }

        public Criteria andReviewFlagNotEqualTo(Integer value) {
            addCriterion("review_flag <>", value, "reviewFlag");
            return (Criteria) this;
        }

        public Criteria andReviewFlagGreaterThan(Integer value) {
            addCriterion("review_flag >", value, "reviewFlag");
            return (Criteria) this;
        }

        public Criteria andReviewFlagGreaterThanOrEqualTo(Integer value) {
            addCriterion("review_flag >=", value, "reviewFlag");
            return (Criteria) this;
        }

        public Criteria andReviewFlagLessThan(Integer value) {
            addCriterion("review_flag <", value, "reviewFlag");
            return (Criteria) this;
        }

        public Criteria andReviewFlagLessThanOrEqualTo(Integer value) {
            addCriterion("review_flag <=", value, "reviewFlag");
            return (Criteria) this;
        }

        public Criteria andReviewFlagIn(List<Integer> values) {
            addCriterion("review_flag in", values, "reviewFlag");
            return (Criteria) this;
        }

        public Criteria andReviewFlagNotIn(List<Integer> values) {
            addCriterion("review_flag not in", values, "reviewFlag");
            return (Criteria) this;
        }

        public Criteria andReviewFlagBetween(Integer value1, Integer value2) {
            addCriterion("review_flag between", value1, value2, "reviewFlag");
            return (Criteria) this;
        }

        public Criteria andReviewFlagNotBetween(Integer value1, Integer value2) {
            addCriterion("review_flag not between", value1, value2, "reviewFlag");
            return (Criteria) this;
        }

        public Criteria andBizLicenseImageUrlIsNull() {
            addCriterion("biz_license_image_url is null");
            return (Criteria) this;
        }

        public Criteria andBizLicenseImageUrlIsNotNull() {
            addCriterion("biz_license_image_url is not null");
            return (Criteria) this;
        }

        public Criteria andBizLicenseImageUrlEqualTo(String value) {
            addCriterion("biz_license_image_url =", value, "bizLicenseImageUrl");
            return (Criteria) this;
        }

        public Criteria andBizLicenseImageUrlNotEqualTo(String value) {
            addCriterion("biz_license_image_url <>", value, "bizLicenseImageUrl");
            return (Criteria) this;
        }

        public Criteria andBizLicenseImageUrlGreaterThan(String value) {
            addCriterion("biz_license_image_url >", value, "bizLicenseImageUrl");
            return (Criteria) this;
        }

        public Criteria andBizLicenseImageUrlGreaterThanOrEqualTo(String value) {
            addCriterion("biz_license_image_url >=", value, "bizLicenseImageUrl");
            return (Criteria) this;
        }

        public Criteria andBizLicenseImageUrlLessThan(String value) {
            addCriterion("biz_license_image_url <", value, "bizLicenseImageUrl");
            return (Criteria) this;
        }

        public Criteria andBizLicenseImageUrlLessThanOrEqualTo(String value) {
            addCriterion("biz_license_image_url <=", value, "bizLicenseImageUrl");
            return (Criteria) this;
        }

        public Criteria andBizLicenseImageUrlLike(String value) {
            addCriterion("biz_license_image_url like", value, "bizLicenseImageUrl");
            return (Criteria) this;
        }

        public Criteria andBizLicenseImageUrlNotLike(String value) {
            addCriterion("biz_license_image_url not like", value, "bizLicenseImageUrl");
            return (Criteria) this;
        }

        public Criteria andBizLicenseImageUrlIn(List<String> values) {
            addCriterion("biz_license_image_url in", values, "bizLicenseImageUrl");
            return (Criteria) this;
        }

        public Criteria andBizLicenseImageUrlNotIn(List<String> values) {
            addCriterion("biz_license_image_url not in", values, "bizLicenseImageUrl");
            return (Criteria) this;
        }

        public Criteria andBizLicenseImageUrlBetween(String value1, String value2) {
            addCriterion("biz_license_image_url between", value1, value2, "bizLicenseImageUrl");
            return (Criteria) this;
        }

        public Criteria andBizLicenseImageUrlNotBetween(String value1, String value2) {
            addCriterion("biz_license_image_url not between", value1, value2, "bizLicenseImageUrl");
            return (Criteria) this;
        }

        public Criteria andOrgCodeImageUrlIsNull() {
            addCriterion("org_code_image_url is null");
            return (Criteria) this;
        }

        public Criteria andOrgCodeImageUrlIsNotNull() {
            addCriterion("org_code_image_url is not null");
            return (Criteria) this;
        }

        public Criteria andOrgCodeImageUrlEqualTo(String value) {
            addCriterion("org_code_image_url =", value, "orgCodeImageUrl");
            return (Criteria) this;
        }

        public Criteria andOrgCodeImageUrlNotEqualTo(String value) {
            addCriterion("org_code_image_url <>", value, "orgCodeImageUrl");
            return (Criteria) this;
        }

        public Criteria andOrgCodeImageUrlGreaterThan(String value) {
            addCriterion("org_code_image_url >", value, "orgCodeImageUrl");
            return (Criteria) this;
        }

        public Criteria andOrgCodeImageUrlGreaterThanOrEqualTo(String value) {
            addCriterion("org_code_image_url >=", value, "orgCodeImageUrl");
            return (Criteria) this;
        }

        public Criteria andOrgCodeImageUrlLessThan(String value) {
            addCriterion("org_code_image_url <", value, "orgCodeImageUrl");
            return (Criteria) this;
        }

        public Criteria andOrgCodeImageUrlLessThanOrEqualTo(String value) {
            addCriterion("org_code_image_url <=", value, "orgCodeImageUrl");
            return (Criteria) this;
        }

        public Criteria andOrgCodeImageUrlLike(String value) {
            addCriterion("org_code_image_url like", value, "orgCodeImageUrl");
            return (Criteria) this;
        }

        public Criteria andOrgCodeImageUrlNotLike(String value) {
            addCriterion("org_code_image_url not like", value, "orgCodeImageUrl");
            return (Criteria) this;
        }

        public Criteria andOrgCodeImageUrlIn(List<String> values) {
            addCriterion("org_code_image_url in", values, "orgCodeImageUrl");
            return (Criteria) this;
        }

        public Criteria andOrgCodeImageUrlNotIn(List<String> values) {
            addCriterion("org_code_image_url not in", values, "orgCodeImageUrl");
            return (Criteria) this;
        }

        public Criteria andOrgCodeImageUrlBetween(String value1, String value2) {
            addCriterion("org_code_image_url between", value1, value2, "orgCodeImageUrl");
            return (Criteria) this;
        }

        public Criteria andOrgCodeImageUrlNotBetween(String value1, String value2) {
            addCriterion("org_code_image_url not between", value1, value2, "orgCodeImageUrl");
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