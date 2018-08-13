package cn.org.bjca.footstone.usercenter.dao.model;

import java.io.Serializable;
import java.util.Date;

public class UserInfo implements Serializable {
    private Integer id;

    private Integer uid;

    private String headImgUrl;

    private String name;

    private String idType;

    private String idNum;

    private String mobile;

    private String email;

    private Integer realNameFlag;

    private String realNameType;

    private Boolean reviewFlag;

    private String bankCardNum;

    private String faceIdImageUrl;

    private String idCardFrontImageUrl;

    private String idCardBackImageUrl;

    private String fromPlatform;

    private String status;

    private String extField1;

    private String extField2;

    private String extField3;

    private String extField4;

    private String extConfig;

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

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
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

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType == null ? null : idType.trim();
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum == null ? null : idNum.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
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

    public Boolean getReviewFlag() {
        return reviewFlag;
    }

    public void setReviewFlag(Boolean reviewFlag) {
        this.reviewFlag = reviewFlag;
    }

    public String getBankCardNum() {
        return bankCardNum;
    }

    public void setBankCardNum(String bankCardNum) {
        this.bankCardNum = bankCardNum == null ? null : bankCardNum.trim();
    }

    public String getFaceIdImageUrl() {
        return faceIdImageUrl;
    }

    public void setFaceIdImageUrl(String faceIdImageUrl) {
        this.faceIdImageUrl = faceIdImageUrl == null ? null : faceIdImageUrl.trim();
    }

    public String getIdCardFrontImageUrl() {
        return idCardFrontImageUrl;
    }

    public void setIdCardFrontImageUrl(String idCardFrontImageUrl) {
        this.idCardFrontImageUrl = idCardFrontImageUrl == null ? null : idCardFrontImageUrl.trim();
    }

    public String getIdCardBackImageUrl() {
        return idCardBackImageUrl;
    }

    public void setIdCardBackImageUrl(String idCardBackImageUrl) {
        this.idCardBackImageUrl = idCardBackImageUrl == null ? null : idCardBackImageUrl.trim();
    }

    public String getFromPlatform() {
        return fromPlatform;
    }

    public void setFromPlatform(String fromPlatform) {
        this.fromPlatform = fromPlatform == null ? null : fromPlatform.trim();
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