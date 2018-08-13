package cn.org.bjca.footstone.usercenter.dao.model;

import java.util.ArrayList;
import java.util.List;

public class EntInfoExtExample {

  protected String orderByClause;

  protected boolean distinct;

  protected List<Criteria> oredCriteria;

  private String sumCol;

  private Integer offset;

  private Integer limit;

  public EntInfoExtExample() {
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

  public EntInfoExtExample sumId() {
    this.sumCol = "id";
    return this;
  }

  public EntInfoExtExample sumUid() {
    this.sumCol = "uid";
    return this;
  }

  public EntInfoExtExample sumItem0() {
    this.sumCol = "item0";
    return this;
  }

  public EntInfoExtExample sumItem1() {
    this.sumCol = "item1";
    return this;
  }

  public EntInfoExtExample sumItem2() {
    this.sumCol = "item2";
    return this;
  }

  public EntInfoExtExample sumItem3() {
    this.sumCol = "item3";
    return this;
  }

  public EntInfoExtExample sumItem4() {
    this.sumCol = "item4";
    return this;
  }

  public EntInfoExtExample sumItem5() {
    this.sumCol = "item5";
    return this;
  }

  public EntInfoExtExample sumItem6() {
    this.sumCol = "item6";
    return this;
  }

  public EntInfoExtExample sumItem7() {
    this.sumCol = "item7";
    return this;
  }

  public EntInfoExtExample sumItem8() {
    this.sumCol = "item8";
    return this;
  }

  public EntInfoExtExample sumItem9() {
    this.sumCol = "item9";
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

  public EntInfoExtExample page(int offset, int limit) {
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

    public Criteria andUidEqualTo(Integer value) {
      addCriterion("uid =", value, "uid");
      return (Criteria) this;
    }

    public Criteria andUidNotEqualTo(Integer value) {
      addCriterion("uid <>", value, "uid");
      return (Criteria) this;
    }

    public Criteria andUidGreaterThan(Integer value) {
      addCriterion("uid >", value, "uid");
      return (Criteria) this;
    }

    public Criteria andUidGreaterThanOrEqualTo(Integer value) {
      addCriterion("uid >=", value, "uid");
      return (Criteria) this;
    }

    public Criteria andUidLessThan(Integer value) {
      addCriterion("uid <", value, "uid");
      return (Criteria) this;
    }

    public Criteria andUidLessThanOrEqualTo(Integer value) {
      addCriterion("uid <=", value, "uid");
      return (Criteria) this;
    }

    public Criteria andUidIn(List<Integer> values) {
      addCriterion("uid in", values, "uid");
      return (Criteria) this;
    }

    public Criteria andUidNotIn(List<Integer> values) {
      addCriterion("uid not in", values, "uid");
      return (Criteria) this;
    }

    public Criteria andUidBetween(Integer value1, Integer value2) {
      addCriterion("uid between", value1, value2, "uid");
      return (Criteria) this;
    }

    public Criteria andUidNotBetween(Integer value1, Integer value2) {
      addCriterion("uid not between", value1, value2, "uid");
      return (Criteria) this;
    }

    public Criteria andItem0IsNull() {
      addCriterion("item0 is null");
      return (Criteria) this;
    }

    public Criteria andItem0IsNotNull() {
      addCriterion("item0 is not null");
      return (Criteria) this;
    }

    public Criteria andItem0EqualTo(String value) {
      addCriterion("item0 =", value, "item0");
      return (Criteria) this;
    }

    public Criteria andItem0NotEqualTo(String value) {
      addCriterion("item0 <>", value, "item0");
      return (Criteria) this;
    }

    public Criteria andItem0GreaterThan(String value) {
      addCriterion("item0 >", value, "item0");
      return (Criteria) this;
    }

    public Criteria andItem0GreaterThanOrEqualTo(String value) {
      addCriterion("item0 >=", value, "item0");
      return (Criteria) this;
    }

    public Criteria andItem0LessThan(String value) {
      addCriterion("item0 <", value, "item0");
      return (Criteria) this;
    }

    public Criteria andItem0LessThanOrEqualTo(String value) {
      addCriterion("item0 <=", value, "item0");
      return (Criteria) this;
    }

    public Criteria andItem0Like(String value) {
      addCriterion("item0 like", value, "item0");
      return (Criteria) this;
    }

    public Criteria andItem0NotLike(String value) {
      addCriterion("item0 not like", value, "item0");
      return (Criteria) this;
    }

    public Criteria andItem0In(List<String> values) {
      addCriterion("item0 in", values, "item0");
      return (Criteria) this;
    }

    public Criteria andItem0NotIn(List<String> values) {
      addCriterion("item0 not in", values, "item0");
      return (Criteria) this;
    }

    public Criteria andItem0Between(String value1, String value2) {
      addCriterion("item0 between", value1, value2, "item0");
      return (Criteria) this;
    }

    public Criteria andItem0NotBetween(String value1, String value2) {
      addCriterion("item0 not between", value1, value2, "item0");
      return (Criteria) this;
    }

    public Criteria andItem1IsNull() {
      addCriterion("item1 is null");
      return (Criteria) this;
    }

    public Criteria andItem1IsNotNull() {
      addCriterion("item1 is not null");
      return (Criteria) this;
    }

    public Criteria andItem1EqualTo(String value) {
      addCriterion("item1 =", value, "item1");
      return (Criteria) this;
    }

    public Criteria andItem1NotEqualTo(String value) {
      addCriterion("item1 <>", value, "item1");
      return (Criteria) this;
    }

    public Criteria andItem1GreaterThan(String value) {
      addCriterion("item1 >", value, "item1");
      return (Criteria) this;
    }

    public Criteria andItem1GreaterThanOrEqualTo(String value) {
      addCriterion("item1 >=", value, "item1");
      return (Criteria) this;
    }

    public Criteria andItem1LessThan(String value) {
      addCriterion("item1 <", value, "item1");
      return (Criteria) this;
    }

    public Criteria andItem1LessThanOrEqualTo(String value) {
      addCriterion("item1 <=", value, "item1");
      return (Criteria) this;
    }

    public Criteria andItem1Like(String value) {
      addCriterion("item1 like", value, "item1");
      return (Criteria) this;
    }

    public Criteria andItem1NotLike(String value) {
      addCriterion("item1 not like", value, "item1");
      return (Criteria) this;
    }

    public Criteria andItem1In(List<String> values) {
      addCriterion("item1 in", values, "item1");
      return (Criteria) this;
    }

    public Criteria andItem1NotIn(List<String> values) {
      addCriterion("item1 not in", values, "item1");
      return (Criteria) this;
    }

    public Criteria andItem1Between(String value1, String value2) {
      addCriterion("item1 between", value1, value2, "item1");
      return (Criteria) this;
    }

    public Criteria andItem1NotBetween(String value1, String value2) {
      addCriterion("item1 not between", value1, value2, "item1");
      return (Criteria) this;
    }

    public Criteria andItem2IsNull() {
      addCriterion("item2 is null");
      return (Criteria) this;
    }

    public Criteria andItem2IsNotNull() {
      addCriterion("item2 is not null");
      return (Criteria) this;
    }

    public Criteria andItem2EqualTo(String value) {
      addCriterion("item2 =", value, "item2");
      return (Criteria) this;
    }

    public Criteria andItem2NotEqualTo(String value) {
      addCriterion("item2 <>", value, "item2");
      return (Criteria) this;
    }

    public Criteria andItem2GreaterThan(String value) {
      addCriterion("item2 >", value, "item2");
      return (Criteria) this;
    }

    public Criteria andItem2GreaterThanOrEqualTo(String value) {
      addCriterion("item2 >=", value, "item2");
      return (Criteria) this;
    }

    public Criteria andItem2LessThan(String value) {
      addCriterion("item2 <", value, "item2");
      return (Criteria) this;
    }

    public Criteria andItem2LessThanOrEqualTo(String value) {
      addCriterion("item2 <=", value, "item2");
      return (Criteria) this;
    }

    public Criteria andItem2Like(String value) {
      addCriterion("item2 like", value, "item2");
      return (Criteria) this;
    }

    public Criteria andItem2NotLike(String value) {
      addCriterion("item2 not like", value, "item2");
      return (Criteria) this;
    }

    public Criteria andItem2In(List<String> values) {
      addCriterion("item2 in", values, "item2");
      return (Criteria) this;
    }

    public Criteria andItem2NotIn(List<String> values) {
      addCriterion("item2 not in", values, "item2");
      return (Criteria) this;
    }

    public Criteria andItem2Between(String value1, String value2) {
      addCriterion("item2 between", value1, value2, "item2");
      return (Criteria) this;
    }

    public Criteria andItem2NotBetween(String value1, String value2) {
      addCriterion("item2 not between", value1, value2, "item2");
      return (Criteria) this;
    }

    public Criteria andItem3IsNull() {
      addCriterion("item3 is null");
      return (Criteria) this;
    }

    public Criteria andItem3IsNotNull() {
      addCriterion("item3 is not null");
      return (Criteria) this;
    }

    public Criteria andItem3EqualTo(String value) {
      addCriterion("item3 =", value, "item3");
      return (Criteria) this;
    }

    public Criteria andItem3NotEqualTo(String value) {
      addCriterion("item3 <>", value, "item3");
      return (Criteria) this;
    }

    public Criteria andItem3GreaterThan(String value) {
      addCriterion("item3 >", value, "item3");
      return (Criteria) this;
    }

    public Criteria andItem3GreaterThanOrEqualTo(String value) {
      addCriterion("item3 >=", value, "item3");
      return (Criteria) this;
    }

    public Criteria andItem3LessThan(String value) {
      addCriterion("item3 <", value, "item3");
      return (Criteria) this;
    }

    public Criteria andItem3LessThanOrEqualTo(String value) {
      addCriterion("item3 <=", value, "item3");
      return (Criteria) this;
    }

    public Criteria andItem3Like(String value) {
      addCriterion("item3 like", value, "item3");
      return (Criteria) this;
    }

    public Criteria andItem3NotLike(String value) {
      addCriterion("item3 not like", value, "item3");
      return (Criteria) this;
    }

    public Criteria andItem3In(List<String> values) {
      addCriterion("item3 in", values, "item3");
      return (Criteria) this;
    }

    public Criteria andItem3NotIn(List<String> values) {
      addCriterion("item3 not in", values, "item3");
      return (Criteria) this;
    }

    public Criteria andItem3Between(String value1, String value2) {
      addCriterion("item3 between", value1, value2, "item3");
      return (Criteria) this;
    }

    public Criteria andItem3NotBetween(String value1, String value2) {
      addCriterion("item3 not between", value1, value2, "item3");
      return (Criteria) this;
    }

    public Criteria andItem4IsNull() {
      addCriterion("item4 is null");
      return (Criteria) this;
    }

    public Criteria andItem4IsNotNull() {
      addCriterion("item4 is not null");
      return (Criteria) this;
    }

    public Criteria andItem4EqualTo(String value) {
      addCriterion("item4 =", value, "item4");
      return (Criteria) this;
    }

    public Criteria andItem4NotEqualTo(String value) {
      addCriterion("item4 <>", value, "item4");
      return (Criteria) this;
    }

    public Criteria andItem4GreaterThan(String value) {
      addCriterion("item4 >", value, "item4");
      return (Criteria) this;
    }

    public Criteria andItem4GreaterThanOrEqualTo(String value) {
      addCriterion("item4 >=", value, "item4");
      return (Criteria) this;
    }

    public Criteria andItem4LessThan(String value) {
      addCriterion("item4 <", value, "item4");
      return (Criteria) this;
    }

    public Criteria andItem4LessThanOrEqualTo(String value) {
      addCriterion("item4 <=", value, "item4");
      return (Criteria) this;
    }

    public Criteria andItem4Like(String value) {
      addCriterion("item4 like", value, "item4");
      return (Criteria) this;
    }

    public Criteria andItem4NotLike(String value) {
      addCriterion("item4 not like", value, "item4");
      return (Criteria) this;
    }

    public Criteria andItem4In(List<String> values) {
      addCriterion("item4 in", values, "item4");
      return (Criteria) this;
    }

    public Criteria andItem4NotIn(List<String> values) {
      addCriterion("item4 not in", values, "item4");
      return (Criteria) this;
    }

    public Criteria andItem4Between(String value1, String value2) {
      addCriterion("item4 between", value1, value2, "item4");
      return (Criteria) this;
    }

    public Criteria andItem4NotBetween(String value1, String value2) {
      addCriterion("item4 not between", value1, value2, "item4");
      return (Criteria) this;
    }

    public Criteria andItem5IsNull() {
      addCriterion("item5 is null");
      return (Criteria) this;
    }

    public Criteria andItem5IsNotNull() {
      addCriterion("item5 is not null");
      return (Criteria) this;
    }

    public Criteria andItem5EqualTo(String value) {
      addCriterion("item5 =", value, "item5");
      return (Criteria) this;
    }

    public Criteria andItem5NotEqualTo(String value) {
      addCriterion("item5 <>", value, "item5");
      return (Criteria) this;
    }

    public Criteria andItem5GreaterThan(String value) {
      addCriterion("item5 >", value, "item5");
      return (Criteria) this;
    }

    public Criteria andItem5GreaterThanOrEqualTo(String value) {
      addCriterion("item5 >=", value, "item5");
      return (Criteria) this;
    }

    public Criteria andItem5LessThan(String value) {
      addCriterion("item5 <", value, "item5");
      return (Criteria) this;
    }

    public Criteria andItem5LessThanOrEqualTo(String value) {
      addCriterion("item5 <=", value, "item5");
      return (Criteria) this;
    }

    public Criteria andItem5Like(String value) {
      addCriterion("item5 like", value, "item5");
      return (Criteria) this;
    }

    public Criteria andItem5NotLike(String value) {
      addCriterion("item5 not like", value, "item5");
      return (Criteria) this;
    }

    public Criteria andItem5In(List<String> values) {
      addCriterion("item5 in", values, "item5");
      return (Criteria) this;
    }

    public Criteria andItem5NotIn(List<String> values) {
      addCriterion("item5 not in", values, "item5");
      return (Criteria) this;
    }

    public Criteria andItem5Between(String value1, String value2) {
      addCriterion("item5 between", value1, value2, "item5");
      return (Criteria) this;
    }

    public Criteria andItem5NotBetween(String value1, String value2) {
      addCriterion("item5 not between", value1, value2, "item5");
      return (Criteria) this;
    }

    public Criteria andItem6IsNull() {
      addCriterion("item6 is null");
      return (Criteria) this;
    }

    public Criteria andItem6IsNotNull() {
      addCriterion("item6 is not null");
      return (Criteria) this;
    }

    public Criteria andItem6EqualTo(String value) {
      addCriterion("item6 =", value, "item6");
      return (Criteria) this;
    }

    public Criteria andItem6NotEqualTo(String value) {
      addCriterion("item6 <>", value, "item6");
      return (Criteria) this;
    }

    public Criteria andItem6GreaterThan(String value) {
      addCriterion("item6 >", value, "item6");
      return (Criteria) this;
    }

    public Criteria andItem6GreaterThanOrEqualTo(String value) {
      addCriterion("item6 >=", value, "item6");
      return (Criteria) this;
    }

    public Criteria andItem6LessThan(String value) {
      addCriterion("item6 <", value, "item6");
      return (Criteria) this;
    }

    public Criteria andItem6LessThanOrEqualTo(String value) {
      addCriterion("item6 <=", value, "item6");
      return (Criteria) this;
    }

    public Criteria andItem6Like(String value) {
      addCriterion("item6 like", value, "item6");
      return (Criteria) this;
    }

    public Criteria andItem6NotLike(String value) {
      addCriterion("item6 not like", value, "item6");
      return (Criteria) this;
    }

    public Criteria andItem6In(List<String> values) {
      addCriterion("item6 in", values, "item6");
      return (Criteria) this;
    }

    public Criteria andItem6NotIn(List<String> values) {
      addCriterion("item6 not in", values, "item6");
      return (Criteria) this;
    }

    public Criteria andItem6Between(String value1, String value2) {
      addCriterion("item6 between", value1, value2, "item6");
      return (Criteria) this;
    }

    public Criteria andItem6NotBetween(String value1, String value2) {
      addCriterion("item6 not between", value1, value2, "item6");
      return (Criteria) this;
    }

    public Criteria andItem7IsNull() {
      addCriterion("item7 is null");
      return (Criteria) this;
    }

    public Criteria andItem7IsNotNull() {
      addCriterion("item7 is not null");
      return (Criteria) this;
    }

    public Criteria andItem7EqualTo(String value) {
      addCriterion("item7 =", value, "item7");
      return (Criteria) this;
    }

    public Criteria andItem7NotEqualTo(String value) {
      addCriterion("item7 <>", value, "item7");
      return (Criteria) this;
    }

    public Criteria andItem7GreaterThan(String value) {
      addCriterion("item7 >", value, "item7");
      return (Criteria) this;
    }

    public Criteria andItem7GreaterThanOrEqualTo(String value) {
      addCriterion("item7 >=", value, "item7");
      return (Criteria) this;
    }

    public Criteria andItem7LessThan(String value) {
      addCriterion("item7 <", value, "item7");
      return (Criteria) this;
    }

    public Criteria andItem7LessThanOrEqualTo(String value) {
      addCriterion("item7 <=", value, "item7");
      return (Criteria) this;
    }

    public Criteria andItem7Like(String value) {
      addCriterion("item7 like", value, "item7");
      return (Criteria) this;
    }

    public Criteria andItem7NotLike(String value) {
      addCriterion("item7 not like", value, "item7");
      return (Criteria) this;
    }

    public Criteria andItem7In(List<String> values) {
      addCriterion("item7 in", values, "item7");
      return (Criteria) this;
    }

    public Criteria andItem7NotIn(List<String> values) {
      addCriterion("item7 not in", values, "item7");
      return (Criteria) this;
    }

    public Criteria andItem7Between(String value1, String value2) {
      addCriterion("item7 between", value1, value2, "item7");
      return (Criteria) this;
    }

    public Criteria andItem7NotBetween(String value1, String value2) {
      addCriterion("item7 not between", value1, value2, "item7");
      return (Criteria) this;
    }

    public Criteria andItem8IsNull() {
      addCriterion("item8 is null");
      return (Criteria) this;
    }

    public Criteria andItem8IsNotNull() {
      addCriterion("item8 is not null");
      return (Criteria) this;
    }

    public Criteria andItem8EqualTo(String value) {
      addCriterion("item8 =", value, "item8");
      return (Criteria) this;
    }

    public Criteria andItem8NotEqualTo(String value) {
      addCriterion("item8 <>", value, "item8");
      return (Criteria) this;
    }

    public Criteria andItem8GreaterThan(String value) {
      addCriterion("item8 >", value, "item8");
      return (Criteria) this;
    }

    public Criteria andItem8GreaterThanOrEqualTo(String value) {
      addCriterion("item8 >=", value, "item8");
      return (Criteria) this;
    }

    public Criteria andItem8LessThan(String value) {
      addCriterion("item8 <", value, "item8");
      return (Criteria) this;
    }

    public Criteria andItem8LessThanOrEqualTo(String value) {
      addCriterion("item8 <=", value, "item8");
      return (Criteria) this;
    }

    public Criteria andItem8Like(String value) {
      addCriterion("item8 like", value, "item8");
      return (Criteria) this;
    }

    public Criteria andItem8NotLike(String value) {
      addCriterion("item8 not like", value, "item8");
      return (Criteria) this;
    }

    public Criteria andItem8In(List<String> values) {
      addCriterion("item8 in", values, "item8");
      return (Criteria) this;
    }

    public Criteria andItem8NotIn(List<String> values) {
      addCriterion("item8 not in", values, "item8");
      return (Criteria) this;
    }

    public Criteria andItem8Between(String value1, String value2) {
      addCriterion("item8 between", value1, value2, "item8");
      return (Criteria) this;
    }

    public Criteria andItem8NotBetween(String value1, String value2) {
      addCriterion("item8 not between", value1, value2, "item8");
      return (Criteria) this;
    }

    public Criteria andItem9IsNull() {
      addCriterion("item9 is null");
      return (Criteria) this;
    }

    public Criteria andItem9IsNotNull() {
      addCriterion("item9 is not null");
      return (Criteria) this;
    }

    public Criteria andItem9EqualTo(String value) {
      addCriterion("item9 =", value, "item9");
      return (Criteria) this;
    }

    public Criteria andItem9NotEqualTo(String value) {
      addCriterion("item9 <>", value, "item9");
      return (Criteria) this;
    }

    public Criteria andItem9GreaterThan(String value) {
      addCriterion("item9 >", value, "item9");
      return (Criteria) this;
    }

    public Criteria andItem9GreaterThanOrEqualTo(String value) {
      addCriterion("item9 >=", value, "item9");
      return (Criteria) this;
    }

    public Criteria andItem9LessThan(String value) {
      addCriterion("item9 <", value, "item9");
      return (Criteria) this;
    }

    public Criteria andItem9LessThanOrEqualTo(String value) {
      addCriterion("item9 <=", value, "item9");
      return (Criteria) this;
    }

    public Criteria andItem9Like(String value) {
      addCriterion("item9 like", value, "item9");
      return (Criteria) this;
    }

    public Criteria andItem9NotLike(String value) {
      addCriterion("item9 not like", value, "item9");
      return (Criteria) this;
    }

    public Criteria andItem9In(List<String> values) {
      addCriterion("item9 in", values, "item9");
      return (Criteria) this;
    }

    public Criteria andItem9NotIn(List<String> values) {
      addCriterion("item9 not in", values, "item9");
      return (Criteria) this;
    }

    public Criteria andItem9Between(String value1, String value2) {
      addCriterion("item9 between", value1, value2, "item9");
      return (Criteria) this;
    }

    public Criteria andItem9NotBetween(String value1, String value2) {
      addCriterion("item9 not between", value1, value2, "item9");
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