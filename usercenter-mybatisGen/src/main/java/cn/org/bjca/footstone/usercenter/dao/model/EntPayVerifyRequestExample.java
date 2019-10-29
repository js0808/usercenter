package cn.org.bjca.footstone.usercenter.dao.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EntPayVerifyRequestExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private String sumCol;

    private Integer offset;

    private Integer limit;

    public EntPayVerifyRequestExample() {
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

    public EntPayVerifyRequestExample sumId() {
        this.sumCol="id";
        return this;
    }

    public EntPayVerifyRequestExample sumRealNameId() {
        this.sumCol="real_name_id";
        return this;
    }

    public EntPayVerifyRequestExample sumRealNameRecordVersion() {
        this.sumCol="real_name_record_version";
        return this;
    }

    public EntPayVerifyRequestExample sumUid() {
        this.sumCol="uid";
        return this;
    }

    public EntPayVerifyRequestExample sumAccountName() {
        this.sumCol="account_name";
        return this;
    }

    public EntPayVerifyRequestExample sumBankAccount() {
        this.sumCol="bank_account";
        return this;
    }

    public EntPayVerifyRequestExample sumBankName() {
        this.sumCol="bank_name";
        return this;
    }

    public EntPayVerifyRequestExample sumBankAddressCode() {
        this.sumCol="bank_address_code";
        return this;
    }

    public EntPayVerifyRequestExample sumIdsTransId() {
        this.sumCol="ids_trans_id";
        return this;
    }

    public EntPayVerifyRequestExample sumStatus() {
        this.sumCol="status";
        return this;
    }

    public EntPayVerifyRequestExample sumMessage() {
        this.sumCol="message";
        return this;
    }

    public EntPayVerifyRequestExample sumExtField1() {
        this.sumCol="ext_field1";
        return this;
    }

    public EntPayVerifyRequestExample sumExtField2() {
        this.sumCol="ext_field2";
        return this;
    }

    public EntPayVerifyRequestExample sumVersion() {
        this.sumCol="version";
        return this;
    }

    public EntPayVerifyRequestExample sumCreateTime() {
        this.sumCol="create_time";
        return this;
    }

    public EntPayVerifyRequestExample sumUpdateTime() {
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

    public EntPayVerifyRequestExample page(int offset, int limit) {
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

        public Criteria andRealNameIdIsNull() {
            addCriterion("real_name_id is null");
            return (Criteria) this;
        }

        public Criteria andRealNameIdIsNotNull() {
            addCriterion("real_name_id is not null");
            return (Criteria) this;
        }

        public Criteria andRealNameIdEqualTo(Integer value) {
            addCriterion("real_name_id =", value, "realNameId");
            return (Criteria) this;
        }

        public Criteria andRealNameIdNotEqualTo(Integer value) {
            addCriterion("real_name_id <>", value, "realNameId");
            return (Criteria) this;
        }

        public Criteria andRealNameIdGreaterThan(Integer value) {
            addCriterion("real_name_id >", value, "realNameId");
            return (Criteria) this;
        }

        public Criteria andRealNameIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("real_name_id >=", value, "realNameId");
            return (Criteria) this;
        }

        public Criteria andRealNameIdLessThan(Integer value) {
            addCriterion("real_name_id <", value, "realNameId");
            return (Criteria) this;
        }

        public Criteria andRealNameIdLessThanOrEqualTo(Integer value) {
            addCriterion("real_name_id <=", value, "realNameId");
            return (Criteria) this;
        }

        public Criteria andRealNameIdIn(List<Integer> values) {
            addCriterion("real_name_id in", values, "realNameId");
            return (Criteria) this;
        }

        public Criteria andRealNameIdNotIn(List<Integer> values) {
            addCriterion("real_name_id not in", values, "realNameId");
            return (Criteria) this;
        }

        public Criteria andRealNameIdBetween(Integer value1, Integer value2) {
            addCriterion("real_name_id between", value1, value2, "realNameId");
            return (Criteria) this;
        }

        public Criteria andRealNameIdNotBetween(Integer value1, Integer value2) {
            addCriterion("real_name_id not between", value1, value2, "realNameId");
            return (Criteria) this;
        }

        public Criteria andRealNameRecordVersionIsNull() {
            addCriterion("real_name_record_version is null");
            return (Criteria) this;
        }

        public Criteria andRealNameRecordVersionIsNotNull() {
            addCriterion("real_name_record_version is not null");
            return (Criteria) this;
        }

        public Criteria andRealNameRecordVersionEqualTo(Integer value) {
            addCriterion("real_name_record_version =", value, "realNameRecordVersion");
            return (Criteria) this;
        }

        public Criteria andRealNameRecordVersionNotEqualTo(Integer value) {
            addCriterion("real_name_record_version <>", value, "realNameRecordVersion");
            return (Criteria) this;
        }

        public Criteria andRealNameRecordVersionGreaterThan(Integer value) {
            addCriterion("real_name_record_version >", value, "realNameRecordVersion");
            return (Criteria) this;
        }

        public Criteria andRealNameRecordVersionGreaterThanOrEqualTo(Integer value) {
            addCriterion("real_name_record_version >=", value, "realNameRecordVersion");
            return (Criteria) this;
        }

        public Criteria andRealNameRecordVersionLessThan(Integer value) {
            addCriterion("real_name_record_version <", value, "realNameRecordVersion");
            return (Criteria) this;
        }

        public Criteria andRealNameRecordVersionLessThanOrEqualTo(Integer value) {
            addCriterion("real_name_record_version <=", value, "realNameRecordVersion");
            return (Criteria) this;
        }

        public Criteria andRealNameRecordVersionIn(List<Integer> values) {
            addCriterion("real_name_record_version in", values, "realNameRecordVersion");
            return (Criteria) this;
        }

        public Criteria andRealNameRecordVersionNotIn(List<Integer> values) {
            addCriterion("real_name_record_version not in", values, "realNameRecordVersion");
            return (Criteria) this;
        }

        public Criteria andRealNameRecordVersionBetween(Integer value1, Integer value2) {
            addCriterion("real_name_record_version between", value1, value2, "realNameRecordVersion");
            return (Criteria) this;
        }

        public Criteria andRealNameRecordVersionNotBetween(Integer value1, Integer value2) {
            addCriterion("real_name_record_version not between", value1, value2, "realNameRecordVersion");
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

        public Criteria andAccountNameIsNull() {
            addCriterion("account_name is null");
            return (Criteria) this;
        }

        public Criteria andAccountNameIsNotNull() {
            addCriterion("account_name is not null");
            return (Criteria) this;
        }

        public Criteria andAccountNameEqualTo(String value) {
            addCriterion("account_name =", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameNotEqualTo(String value) {
            addCriterion("account_name <>", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameGreaterThan(String value) {
            addCriterion("account_name >", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameGreaterThanOrEqualTo(String value) {
            addCriterion("account_name >=", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameLessThan(String value) {
            addCriterion("account_name <", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameLessThanOrEqualTo(String value) {
            addCriterion("account_name <=", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameLike(String value) {
            addCriterion("account_name like", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameNotLike(String value) {
            addCriterion("account_name not like", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameIn(List<String> values) {
            addCriterion("account_name in", values, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameNotIn(List<String> values) {
            addCriterion("account_name not in", values, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameBetween(String value1, String value2) {
            addCriterion("account_name between", value1, value2, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameNotBetween(String value1, String value2) {
            addCriterion("account_name not between", value1, value2, "accountName");
            return (Criteria) this;
        }

        public Criteria andBankAccountIsNull() {
            addCriterion("bank_account is null");
            return (Criteria) this;
        }

        public Criteria andBankAccountIsNotNull() {
            addCriterion("bank_account is not null");
            return (Criteria) this;
        }

        public Criteria andBankAccountEqualTo(String value) {
            addCriterion("bank_account =", value, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountNotEqualTo(String value) {
            addCriterion("bank_account <>", value, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountGreaterThan(String value) {
            addCriterion("bank_account >", value, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountGreaterThanOrEqualTo(String value) {
            addCriterion("bank_account >=", value, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountLessThan(String value) {
            addCriterion("bank_account <", value, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountLessThanOrEqualTo(String value) {
            addCriterion("bank_account <=", value, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountLike(String value) {
            addCriterion("bank_account like", value, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountNotLike(String value) {
            addCriterion("bank_account not like", value, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountIn(List<String> values) {
            addCriterion("bank_account in", values, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountNotIn(List<String> values) {
            addCriterion("bank_account not in", values, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountBetween(String value1, String value2) {
            addCriterion("bank_account between", value1, value2, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountNotBetween(String value1, String value2) {
            addCriterion("bank_account not between", value1, value2, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankNameIsNull() {
            addCriterion("bank_name is null");
            return (Criteria) this;
        }

        public Criteria andBankNameIsNotNull() {
            addCriterion("bank_name is not null");
            return (Criteria) this;
        }

        public Criteria andBankNameEqualTo(String value) {
            addCriterion("bank_name =", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotEqualTo(String value) {
            addCriterion("bank_name <>", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameGreaterThan(String value) {
            addCriterion("bank_name >", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameGreaterThanOrEqualTo(String value) {
            addCriterion("bank_name >=", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameLessThan(String value) {
            addCriterion("bank_name <", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameLessThanOrEqualTo(String value) {
            addCriterion("bank_name <=", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameLike(String value) {
            addCriterion("bank_name like", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotLike(String value) {
            addCriterion("bank_name not like", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameIn(List<String> values) {
            addCriterion("bank_name in", values, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotIn(List<String> values) {
            addCriterion("bank_name not in", values, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameBetween(String value1, String value2) {
            addCriterion("bank_name between", value1, value2, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotBetween(String value1, String value2) {
            addCriterion("bank_name not between", value1, value2, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankAddressCodeIsNull() {
            addCriterion("bank_address_code is null");
            return (Criteria) this;
        }

        public Criteria andBankAddressCodeIsNotNull() {
            addCriterion("bank_address_code is not null");
            return (Criteria) this;
        }

        public Criteria andBankAddressCodeEqualTo(String value) {
            addCriterion("bank_address_code =", value, "bankAddressCode");
            return (Criteria) this;
        }

        public Criteria andBankAddressCodeNotEqualTo(String value) {
            addCriterion("bank_address_code <>", value, "bankAddressCode");
            return (Criteria) this;
        }

        public Criteria andBankAddressCodeGreaterThan(String value) {
            addCriterion("bank_address_code >", value, "bankAddressCode");
            return (Criteria) this;
        }

        public Criteria andBankAddressCodeGreaterThanOrEqualTo(String value) {
            addCriterion("bank_address_code >=", value, "bankAddressCode");
            return (Criteria) this;
        }

        public Criteria andBankAddressCodeLessThan(String value) {
            addCriterion("bank_address_code <", value, "bankAddressCode");
            return (Criteria) this;
        }

        public Criteria andBankAddressCodeLessThanOrEqualTo(String value) {
            addCriterion("bank_address_code <=", value, "bankAddressCode");
            return (Criteria) this;
        }

        public Criteria andBankAddressCodeLike(String value) {
            addCriterion("bank_address_code like", value, "bankAddressCode");
            return (Criteria) this;
        }

        public Criteria andBankAddressCodeNotLike(String value) {
            addCriterion("bank_address_code not like", value, "bankAddressCode");
            return (Criteria) this;
        }

        public Criteria andBankAddressCodeIn(List<String> values) {
            addCriterion("bank_address_code in", values, "bankAddressCode");
            return (Criteria) this;
        }

        public Criteria andBankAddressCodeNotIn(List<String> values) {
            addCriterion("bank_address_code not in", values, "bankAddressCode");
            return (Criteria) this;
        }

        public Criteria andBankAddressCodeBetween(String value1, String value2) {
            addCriterion("bank_address_code between", value1, value2, "bankAddressCode");
            return (Criteria) this;
        }

        public Criteria andBankAddressCodeNotBetween(String value1, String value2) {
            addCriterion("bank_address_code not between", value1, value2, "bankAddressCode");
            return (Criteria) this;
        }

        public Criteria andIdsTransIdIsNull() {
            addCriterion("ids_trans_id is null");
            return (Criteria) this;
        }

        public Criteria andIdsTransIdIsNotNull() {
            addCriterion("ids_trans_id is not null");
            return (Criteria) this;
        }

        public Criteria andIdsTransIdEqualTo(String value) {
            addCriterion("ids_trans_id =", value, "idsTransId");
            return (Criteria) this;
        }

        public Criteria andIdsTransIdNotEqualTo(String value) {
            addCriterion("ids_trans_id <>", value, "idsTransId");
            return (Criteria) this;
        }

        public Criteria andIdsTransIdGreaterThan(String value) {
            addCriterion("ids_trans_id >", value, "idsTransId");
            return (Criteria) this;
        }

        public Criteria andIdsTransIdGreaterThanOrEqualTo(String value) {
            addCriterion("ids_trans_id >=", value, "idsTransId");
            return (Criteria) this;
        }

        public Criteria andIdsTransIdLessThan(String value) {
            addCriterion("ids_trans_id <", value, "idsTransId");
            return (Criteria) this;
        }

        public Criteria andIdsTransIdLessThanOrEqualTo(String value) {
            addCriterion("ids_trans_id <=", value, "idsTransId");
            return (Criteria) this;
        }

        public Criteria andIdsTransIdLike(String value) {
            addCriterion("ids_trans_id like", value, "idsTransId");
            return (Criteria) this;
        }

        public Criteria andIdsTransIdNotLike(String value) {
            addCriterion("ids_trans_id not like", value, "idsTransId");
            return (Criteria) this;
        }

        public Criteria andIdsTransIdIn(List<String> values) {
            addCriterion("ids_trans_id in", values, "idsTransId");
            return (Criteria) this;
        }

        public Criteria andIdsTransIdNotIn(List<String> values) {
            addCriterion("ids_trans_id not in", values, "idsTransId");
            return (Criteria) this;
        }

        public Criteria andIdsTransIdBetween(String value1, String value2) {
            addCriterion("ids_trans_id between", value1, value2, "idsTransId");
            return (Criteria) this;
        }

        public Criteria andIdsTransIdNotBetween(String value1, String value2) {
            addCriterion("ids_trans_id not between", value1, value2, "idsTransId");
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

        public Criteria andMessageIsNull() {
            addCriterion("message is null");
            return (Criteria) this;
        }

        public Criteria andMessageIsNotNull() {
            addCriterion("message is not null");
            return (Criteria) this;
        }

        public Criteria andMessageEqualTo(String value) {
            addCriterion("message =", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageNotEqualTo(String value) {
            addCriterion("message <>", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageGreaterThan(String value) {
            addCriterion("message >", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageGreaterThanOrEqualTo(String value) {
            addCriterion("message >=", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageLessThan(String value) {
            addCriterion("message <", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageLessThanOrEqualTo(String value) {
            addCriterion("message <=", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageLike(String value) {
            addCriterion("message like", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageNotLike(String value) {
            addCriterion("message not like", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageIn(List<String> values) {
            addCriterion("message in", values, "message");
            return (Criteria) this;
        }

        public Criteria andMessageNotIn(List<String> values) {
            addCriterion("message not in", values, "message");
            return (Criteria) this;
        }

        public Criteria andMessageBetween(String value1, String value2) {
            addCriterion("message between", value1, value2, "message");
            return (Criteria) this;
        }

        public Criteria andMessageNotBetween(String value1, String value2) {
            addCriterion("message not between", value1, value2, "message");
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