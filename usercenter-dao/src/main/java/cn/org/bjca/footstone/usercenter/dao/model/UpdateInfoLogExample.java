package cn.org.bjca.footstone.usercenter.dao.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UpdateInfoLogExample {

  protected String orderByClause;

  protected boolean distinct;

  protected List<Criteria> oredCriteria;

  private String sumCol;

  private Integer offset;

  private Integer limit;

  public UpdateInfoLogExample() {
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

  public UpdateInfoLogExample sumId() {
    this.sumCol = "id";
    return this;
  }

  public UpdateInfoLogExample sumUid() {
    this.sumCol = "uid";
    return this;
  }

  public UpdateInfoLogExample sumUserType() {
    this.sumCol = "user_type";
    return this;
  }

  public UpdateInfoLogExample sumOperatorUid() {
    this.sumCol = "operator_uid";
    return this;
  }

  public UpdateInfoLogExample sumOperatorAccount() {
    this.sumCol = "operator_account";
    return this;
  }

  public UpdateInfoLogExample sumAttributeName() {
    this.sumCol = "attribute_name";
    return this;
  }

  public UpdateInfoLogExample sumOldValue() {
    this.sumCol = "old_value";
    return this;
  }

  public UpdateInfoLogExample sumUpdateValue() {
    this.sumCol = "update_value";
    return this;
  }

  public UpdateInfoLogExample sumClientInfo() {
    this.sumCol = "client_info";
    return this;
  }

  public UpdateInfoLogExample sumExtField1() {
    this.sumCol = "ext_field1";
    return this;
  }

  public UpdateInfoLogExample sumExtField2() {
    this.sumCol = "ext_field2";
    return this;
  }

  public UpdateInfoLogExample sumExtField3() {
    this.sumCol = "ext_field3";
    return this;
  }

  public UpdateInfoLogExample sumExtField4() {
    this.sumCol = "ext_field4";
    return this;
  }

  public UpdateInfoLogExample sumVersion() {
    this.sumCol = "version";
    return this;
  }

  public UpdateInfoLogExample sumCreateTime() {
    this.sumCol = "create_time";
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

  public UpdateInfoLogExample page(int offset, int limit) {
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

    public Criteria andUserTypeIsNull() {
      addCriterion("user_type is null");
      return (Criteria) this;
    }

    public Criteria andUserTypeIsNotNull() {
      addCriterion("user_type is not null");
      return (Criteria) this;
    }

    public Criteria andUserTypeEqualTo(String value) {
      addCriterion("user_type =", value, "userType");
      return (Criteria) this;
    }

    public Criteria andUserTypeNotEqualTo(String value) {
      addCriterion("user_type <>", value, "userType");
      return (Criteria) this;
    }

    public Criteria andUserTypeGreaterThan(String value) {
      addCriterion("user_type >", value, "userType");
      return (Criteria) this;
    }

    public Criteria andUserTypeGreaterThanOrEqualTo(String value) {
      addCriterion("user_type >=", value, "userType");
      return (Criteria) this;
    }

    public Criteria andUserTypeLessThan(String value) {
      addCriterion("user_type <", value, "userType");
      return (Criteria) this;
    }

    public Criteria andUserTypeLessThanOrEqualTo(String value) {
      addCriterion("user_type <=", value, "userType");
      return (Criteria) this;
    }

    public Criteria andUserTypeLike(String value) {
      addCriterion("user_type like", value, "userType");
      return (Criteria) this;
    }

    public Criteria andUserTypeNotLike(String value) {
      addCriterion("user_type not like", value, "userType");
      return (Criteria) this;
    }

    public Criteria andUserTypeIn(List<String> values) {
      addCriterion("user_type in", values, "userType");
      return (Criteria) this;
    }

    public Criteria andUserTypeNotIn(List<String> values) {
      addCriterion("user_type not in", values, "userType");
      return (Criteria) this;
    }

    public Criteria andUserTypeBetween(String value1, String value2) {
      addCriterion("user_type between", value1, value2, "userType");
      return (Criteria) this;
    }

    public Criteria andUserTypeNotBetween(String value1, String value2) {
      addCriterion("user_type not between", value1, value2, "userType");
      return (Criteria) this;
    }

    public Criteria andOperatorUidIsNull() {
      addCriterion("operator_uid is null");
      return (Criteria) this;
    }

    public Criteria andOperatorUidIsNotNull() {
      addCriterion("operator_uid is not null");
      return (Criteria) this;
    }

    public Criteria andOperatorUidEqualTo(Integer value) {
      addCriterion("operator_uid =", value, "operatorUid");
      return (Criteria) this;
    }

    public Criteria andOperatorUidNotEqualTo(Integer value) {
      addCriterion("operator_uid <>", value, "operatorUid");
      return (Criteria) this;
    }

    public Criteria andOperatorUidGreaterThan(Integer value) {
      addCriterion("operator_uid >", value, "operatorUid");
      return (Criteria) this;
    }

    public Criteria andOperatorUidGreaterThanOrEqualTo(Integer value) {
      addCriterion("operator_uid >=", value, "operatorUid");
      return (Criteria) this;
    }

    public Criteria andOperatorUidLessThan(Integer value) {
      addCriterion("operator_uid <", value, "operatorUid");
      return (Criteria) this;
    }

    public Criteria andOperatorUidLessThanOrEqualTo(Integer value) {
      addCriterion("operator_uid <=", value, "operatorUid");
      return (Criteria) this;
    }

    public Criteria andOperatorUidIn(List<Integer> values) {
      addCriterion("operator_uid in", values, "operatorUid");
      return (Criteria) this;
    }

    public Criteria andOperatorUidNotIn(List<Integer> values) {
      addCriterion("operator_uid not in", values, "operatorUid");
      return (Criteria) this;
    }

    public Criteria andOperatorUidBetween(Integer value1, Integer value2) {
      addCriterion("operator_uid between", value1, value2, "operatorUid");
      return (Criteria) this;
    }

    public Criteria andOperatorUidNotBetween(Integer value1, Integer value2) {
      addCriterion("operator_uid not between", value1, value2, "operatorUid");
      return (Criteria) this;
    }

    public Criteria andOperatorAccountIsNull() {
      addCriterion("operator_account is null");
      return (Criteria) this;
    }

    public Criteria andOperatorAccountIsNotNull() {
      addCriterion("operator_account is not null");
      return (Criteria) this;
    }

    public Criteria andOperatorAccountEqualTo(String value) {
      addCriterion("operator_account =", value, "operatorAccount");
      return (Criteria) this;
    }

    public Criteria andOperatorAccountNotEqualTo(String value) {
      addCriterion("operator_account <>", value, "operatorAccount");
      return (Criteria) this;
    }

    public Criteria andOperatorAccountGreaterThan(String value) {
      addCriterion("operator_account >", value, "operatorAccount");
      return (Criteria) this;
    }

    public Criteria andOperatorAccountGreaterThanOrEqualTo(String value) {
      addCriterion("operator_account >=", value, "operatorAccount");
      return (Criteria) this;
    }

    public Criteria andOperatorAccountLessThan(String value) {
      addCriterion("operator_account <", value, "operatorAccount");
      return (Criteria) this;
    }

    public Criteria andOperatorAccountLessThanOrEqualTo(String value) {
      addCriterion("operator_account <=", value, "operatorAccount");
      return (Criteria) this;
    }

    public Criteria andOperatorAccountLike(String value) {
      addCriterion("operator_account like", value, "operatorAccount");
      return (Criteria) this;
    }

    public Criteria andOperatorAccountNotLike(String value) {
      addCriterion("operator_account not like", value, "operatorAccount");
      return (Criteria) this;
    }

    public Criteria andOperatorAccountIn(List<String> values) {
      addCriterion("operator_account in", values, "operatorAccount");
      return (Criteria) this;
    }

    public Criteria andOperatorAccountNotIn(List<String> values) {
      addCriterion("operator_account not in", values, "operatorAccount");
      return (Criteria) this;
    }

    public Criteria andOperatorAccountBetween(String value1, String value2) {
      addCriterion("operator_account between", value1, value2, "operatorAccount");
      return (Criteria) this;
    }

    public Criteria andOperatorAccountNotBetween(String value1, String value2) {
      addCriterion("operator_account not between", value1, value2, "operatorAccount");
      return (Criteria) this;
    }

    public Criteria andAttributeNameIsNull() {
      addCriterion("attribute_name is null");
      return (Criteria) this;
    }

    public Criteria andAttributeNameIsNotNull() {
      addCriterion("attribute_name is not null");
      return (Criteria) this;
    }

    public Criteria andAttributeNameEqualTo(String value) {
      addCriterion("attribute_name =", value, "attributeName");
      return (Criteria) this;
    }

    public Criteria andAttributeNameNotEqualTo(String value) {
      addCriterion("attribute_name <>", value, "attributeName");
      return (Criteria) this;
    }

    public Criteria andAttributeNameGreaterThan(String value) {
      addCriterion("attribute_name >", value, "attributeName");
      return (Criteria) this;
    }

    public Criteria andAttributeNameGreaterThanOrEqualTo(String value) {
      addCriterion("attribute_name >=", value, "attributeName");
      return (Criteria) this;
    }

    public Criteria andAttributeNameLessThan(String value) {
      addCriterion("attribute_name <", value, "attributeName");
      return (Criteria) this;
    }

    public Criteria andAttributeNameLessThanOrEqualTo(String value) {
      addCriterion("attribute_name <=", value, "attributeName");
      return (Criteria) this;
    }

    public Criteria andAttributeNameLike(String value) {
      addCriterion("attribute_name like", value, "attributeName");
      return (Criteria) this;
    }

    public Criteria andAttributeNameNotLike(String value) {
      addCriterion("attribute_name not like", value, "attributeName");
      return (Criteria) this;
    }

    public Criteria andAttributeNameIn(List<String> values) {
      addCriterion("attribute_name in", values, "attributeName");
      return (Criteria) this;
    }

    public Criteria andAttributeNameNotIn(List<String> values) {
      addCriterion("attribute_name not in", values, "attributeName");
      return (Criteria) this;
    }

    public Criteria andAttributeNameBetween(String value1, String value2) {
      addCriterion("attribute_name between", value1, value2, "attributeName");
      return (Criteria) this;
    }

    public Criteria andAttributeNameNotBetween(String value1, String value2) {
      addCriterion("attribute_name not between", value1, value2, "attributeName");
      return (Criteria) this;
    }

    public Criteria andOldValueIsNull() {
      addCriterion("old_value is null");
      return (Criteria) this;
    }

    public Criteria andOldValueIsNotNull() {
      addCriterion("old_value is not null");
      return (Criteria) this;
    }

    public Criteria andOldValueEqualTo(String value) {
      addCriterion("old_value =", value, "oldValue");
      return (Criteria) this;
    }

    public Criteria andOldValueNotEqualTo(String value) {
      addCriterion("old_value <>", value, "oldValue");
      return (Criteria) this;
    }

    public Criteria andOldValueGreaterThan(String value) {
      addCriterion("old_value >", value, "oldValue");
      return (Criteria) this;
    }

    public Criteria andOldValueGreaterThanOrEqualTo(String value) {
      addCriterion("old_value >=", value, "oldValue");
      return (Criteria) this;
    }

    public Criteria andOldValueLessThan(String value) {
      addCriterion("old_value <", value, "oldValue");
      return (Criteria) this;
    }

    public Criteria andOldValueLessThanOrEqualTo(String value) {
      addCriterion("old_value <=", value, "oldValue");
      return (Criteria) this;
    }

    public Criteria andOldValueLike(String value) {
      addCriterion("old_value like", value, "oldValue");
      return (Criteria) this;
    }

    public Criteria andOldValueNotLike(String value) {
      addCriterion("old_value not like", value, "oldValue");
      return (Criteria) this;
    }

    public Criteria andOldValueIn(List<String> values) {
      addCriterion("old_value in", values, "oldValue");
      return (Criteria) this;
    }

    public Criteria andOldValueNotIn(List<String> values) {
      addCriterion("old_value not in", values, "oldValue");
      return (Criteria) this;
    }

    public Criteria andOldValueBetween(String value1, String value2) {
      addCriterion("old_value between", value1, value2, "oldValue");
      return (Criteria) this;
    }

    public Criteria andOldValueNotBetween(String value1, String value2) {
      addCriterion("old_value not between", value1, value2, "oldValue");
      return (Criteria) this;
    }

    public Criteria andUpdateValueIsNull() {
      addCriterion("update_value is null");
      return (Criteria) this;
    }

    public Criteria andUpdateValueIsNotNull() {
      addCriterion("update_value is not null");
      return (Criteria) this;
    }

    public Criteria andUpdateValueEqualTo(String value) {
      addCriterion("update_value =", value, "updateValue");
      return (Criteria) this;
    }

    public Criteria andUpdateValueNotEqualTo(String value) {
      addCriterion("update_value <>", value, "updateValue");
      return (Criteria) this;
    }

    public Criteria andUpdateValueGreaterThan(String value) {
      addCriterion("update_value >", value, "updateValue");
      return (Criteria) this;
    }

    public Criteria andUpdateValueGreaterThanOrEqualTo(String value) {
      addCriterion("update_value >=", value, "updateValue");
      return (Criteria) this;
    }

    public Criteria andUpdateValueLessThan(String value) {
      addCriterion("update_value <", value, "updateValue");
      return (Criteria) this;
    }

    public Criteria andUpdateValueLessThanOrEqualTo(String value) {
      addCriterion("update_value <=", value, "updateValue");
      return (Criteria) this;
    }

    public Criteria andUpdateValueLike(String value) {
      addCriterion("update_value like", value, "updateValue");
      return (Criteria) this;
    }

    public Criteria andUpdateValueNotLike(String value) {
      addCriterion("update_value not like", value, "updateValue");
      return (Criteria) this;
    }

    public Criteria andUpdateValueIn(List<String> values) {
      addCriterion("update_value in", values, "updateValue");
      return (Criteria) this;
    }

    public Criteria andUpdateValueNotIn(List<String> values) {
      addCriterion("update_value not in", values, "updateValue");
      return (Criteria) this;
    }

    public Criteria andUpdateValueBetween(String value1, String value2) {
      addCriterion("update_value between", value1, value2, "updateValue");
      return (Criteria) this;
    }

    public Criteria andUpdateValueNotBetween(String value1, String value2) {
      addCriterion("update_value not between", value1, value2, "updateValue");
      return (Criteria) this;
    }

    public Criteria andClientInfoIsNull() {
      addCriterion("client_info is null");
      return (Criteria) this;
    }

    public Criteria andClientInfoIsNotNull() {
      addCriterion("client_info is not null");
      return (Criteria) this;
    }

    public Criteria andClientInfoEqualTo(String value) {
      addCriterion("client_info =", value, "clientInfo");
      return (Criteria) this;
    }

    public Criteria andClientInfoNotEqualTo(String value) {
      addCriterion("client_info <>", value, "clientInfo");
      return (Criteria) this;
    }

    public Criteria andClientInfoGreaterThan(String value) {
      addCriterion("client_info >", value, "clientInfo");
      return (Criteria) this;
    }

    public Criteria andClientInfoGreaterThanOrEqualTo(String value) {
      addCriterion("client_info >=", value, "clientInfo");
      return (Criteria) this;
    }

    public Criteria andClientInfoLessThan(String value) {
      addCriterion("client_info <", value, "clientInfo");
      return (Criteria) this;
    }

    public Criteria andClientInfoLessThanOrEqualTo(String value) {
      addCriterion("client_info <=", value, "clientInfo");
      return (Criteria) this;
    }

    public Criteria andClientInfoLike(String value) {
      addCriterion("client_info like", value, "clientInfo");
      return (Criteria) this;
    }

    public Criteria andClientInfoNotLike(String value) {
      addCriterion("client_info not like", value, "clientInfo");
      return (Criteria) this;
    }

    public Criteria andClientInfoIn(List<String> values) {
      addCriterion("client_info in", values, "clientInfo");
      return (Criteria) this;
    }

    public Criteria andClientInfoNotIn(List<String> values) {
      addCriterion("client_info not in", values, "clientInfo");
      return (Criteria) this;
    }

    public Criteria andClientInfoBetween(String value1, String value2) {
      addCriterion("client_info between", value1, value2, "clientInfo");
      return (Criteria) this;
    }

    public Criteria andClientInfoNotBetween(String value1, String value2) {
      addCriterion("client_info not between", value1, value2, "clientInfo");
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