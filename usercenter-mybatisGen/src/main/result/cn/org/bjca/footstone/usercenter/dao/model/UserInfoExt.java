package cn.org.bjca.footstone.usercenter.dao.model;

import java.io.Serializable;

public class UserInfoExt implements Serializable {
    private Integer id;

    private Long uid;

    private String item0;

    private String item1;

    private String item2;

    private String item3;

    private String item4;

    private String item5;

    private String item6;

    private String item7;

    private String item8;

    private String item9;

    private static final long serialVersionUID = 1L;

    private String updateSql;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getItem0() {
        return item0;
    }

    public void setItem0(String item0) {
        this.item0 = item0 == null ? null : item0.trim();
    }

    public String getItem1() {
        return item1;
    }

    public void setItem1(String item1) {
        this.item1 = item1 == null ? null : item1.trim();
    }

    public String getItem2() {
        return item2;
    }

    public void setItem2(String item2) {
        this.item2 = item2 == null ? null : item2.trim();
    }

    public String getItem3() {
        return item3;
    }

    public void setItem3(String item3) {
        this.item3 = item3 == null ? null : item3.trim();
    }

    public String getItem4() {
        return item4;
    }

    public void setItem4(String item4) {
        this.item4 = item4 == null ? null : item4.trim();
    }

    public String getItem5() {
        return item5;
    }

    public void setItem5(String item5) {
        this.item5 = item5 == null ? null : item5.trim();
    }

    public String getItem6() {
        return item6;
    }

    public void setItem6(String item6) {
        this.item6 = item6 == null ? null : item6.trim();
    }

    public String getItem7() {
        return item7;
    }

    public void setItem7(String item7) {
        this.item7 = item7 == null ? null : item7.trim();
    }

    public String getItem8() {
        return item8;
    }

    public void setItem8(String item8) {
        this.item8 = item8 == null ? null : item8.trim();
    }

    public String getItem9() {
        return item9;
    }

    public void setItem9(String item9) {
        this.item9 = item9 == null ? null : item9.trim();
    }

    public String getUpdateSql() {
        return this.updateSql;
    }

    public void setUpdateSql(String updateSql) {
        this.updateSql = updateSql;
    }
}