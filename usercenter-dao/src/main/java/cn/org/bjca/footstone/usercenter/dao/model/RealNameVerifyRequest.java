package cn.org.bjca.footstone.usercenter.dao.model;

import java.io.Serializable;
import java.util.Date;

public class RealNameVerifyRequest implements Serializable {

  private Integer id;

  private Integer realNameId;

  private Long uid;

  private String idsTransId;

  private String status;

  private String message;

  private String realNameType;

  private String extField1;

  private String extField2;

  private Integer version;

  private Date createTime;

  private Date updateTime;

  private static final long serialVersionUID = 1L;

  private String updateSql;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getRealNameId() {
    return realNameId;
  }

  public void setRealNameId(Integer realNameId) {
    this.realNameId = realNameId;
  }

  public Long getUid() {
    return uid;
  }

  public void setUid(Long uid) {
    this.uid = uid;
  }

  public String getIdsTransId() {
    return idsTransId;
  }

  public void setIdsTransId(String idsTransId) {
    this.idsTransId = idsTransId == null ? null : idsTransId.trim();
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status == null ? null : status.trim();
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message == null ? null : message.trim();
  }

  public String getRealNameType() {
    return realNameType;
  }

  public void setRealNameType(String realNameType) {
    this.realNameType = realNameType == null ? null : realNameType.trim();
  }

  public String getExtField1() {
    return extField1;
  }

  public void setExtField1(String extField1) {
    this.extField1 = extField1 == null ? null : extField1.trim();
  }

  public String getExtField2() {
    return extField2;
  }

  public void setExtField2(String extField2) {
    this.extField2 = extField2 == null ? null : extField2.trim();
  }

  public Integer getVersion() {
    return version;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public Date getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
  }

  public String getUpdateSql() {
    return this.updateSql;
  }

  public void setUpdateSql(String updateSql) {
    this.updateSql = updateSql;
  }
}