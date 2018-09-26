package cn.org.bjca.footstone.usercenter.dao.model;

import java.io.Serializable;
import java.util.Date;

public class EntInfo implements Serializable {
    private Integer id;

    private String headImgUrl;

    private String name;

    private String phone;

    private String orgCode;

    private String bizLicense;

    private String socialCreditCode;

    private String legalName;

    private String legalIdNum;

    private Integer realNameFlag;

    private String realNameType;

    private Integer reviewFlag;

    private String bizLicenseImageUrl;

    private String orgCodeImageUrl;

  private String legalIdFrontImageUrl;

  private String legalIdBackImageUrl;

  private String bankAccount;

  private String bankName;

  private String bankAddressCode;

    private String appId;

    private String status;

    private String extField1;

    private String extField2;

    private String extField3;

    private String extField4;

    private String extConfig;

    private Integer version;

    private String oper;

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

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl == null ? null : headImgUrl.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    public String getBizLicense() {
        return bizLicense;
    }

    public void setBizLicense(String bizLicense) {
        this.bizLicense = bizLicense == null ? null : bizLicense.trim();
    }

    public String getSocialCreditCode() {
        return socialCreditCode;
    }

    public void setSocialCreditCode(String socialCreditCode) {
        this.socialCreditCode = socialCreditCode == null ? null : socialCreditCode.trim();
    }

    public String getLegalName() {
        return legalName;
    }

    public void setLegalName(String legalName) {
        this.legalName = legalName == null ? null : legalName.trim();
    }

    public String getLegalIdNum() {
        return legalIdNum;
    }

    public void setLegalIdNum(String legalIdNum) {
        this.legalIdNum = legalIdNum == null ? null : legalIdNum.trim();
    }

    public Integer getRealNameFlag() {
        return realNameFlag;
    }

    public void setRealNameFlag(Integer realNameFlag) {
        this.realNameFlag = realNameFlag;
    }

    public String getRealNameType() {
        return realNameType;
    }

    public void setRealNameType(String realNameType) {
        this.realNameType = realNameType == null ? null : realNameType.trim();
    }

    public Integer getReviewFlag() {
        return reviewFlag;
    }

    public void setReviewFlag(Integer reviewFlag) {
        this.reviewFlag = reviewFlag;
    }

    public String getBizLicenseImageUrl() {
        return bizLicenseImageUrl;
    }

    public void setBizLicenseImageUrl(String bizLicenseImageUrl) {
        this.bizLicenseImageUrl = bizLicenseImageUrl == null ? null : bizLicenseImageUrl.trim();
    }

    public String getOrgCodeImageUrl() {
        return orgCodeImageUrl;
    }

    public void setOrgCodeImageUrl(String orgCodeImageUrl) {
        this.orgCodeImageUrl = orgCodeImageUrl == null ? null : orgCodeImageUrl.trim();
    }

  public String getLegalIdFrontImageUrl() {
    return legalIdFrontImageUrl;
  }

  public void setLegalIdFrontImageUrl(String legalIdFrontImageUrl) {
    this.legalIdFrontImageUrl = legalIdFrontImageUrl == null ? null : legalIdFrontImageUrl.trim();
  }

  public String getLegalIdBackImageUrl() {
    return legalIdBackImageUrl;
  }

  public void setLegalIdBackImageUrl(String legalIdBackImageUrl) {
    this.legalIdBackImageUrl = legalIdBackImageUrl == null ? null : legalIdBackImageUrl.trim();
  }

  public String getBankAccount() {
    return bankAccount;
  }

  public void setBankAccount(String bankAccount) {
    this.bankAccount = bankAccount == null ? null : bankAccount.trim();
  }

  public String getBankName() {
    return bankName;
  }

  public void setBankName(String bankName) {
    this.bankName = bankName == null ? null : bankName.trim();
  }

  public String getBankAddressCode() {
    return bankAddressCode;
  }

  public void setBankAddressCode(String bankAddressCode) {
    this.bankAddressCode = bankAddressCode == null ? null : bankAddressCode.trim();
  }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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

    public String getExtField3() {
        return extField3;
    }

    public void setExtField3(String extField3) {
        this.extField3 = extField3 == null ? null : extField3.trim();
    }

    public String getExtField4() {
        return extField4;
    }

    public void setExtField4(String extField4) {
        this.extField4 = extField4 == null ? null : extField4.trim();
    }

    public String getExtConfig() {
        return extConfig;
    }

    public void setExtConfig(String extConfig) {
        this.extConfig = extConfig == null ? null : extConfig.trim();
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getOper() {
        return oper;
    }

    public void setOper(String oper) {
        this.oper = oper == null ? null : oper.trim();
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