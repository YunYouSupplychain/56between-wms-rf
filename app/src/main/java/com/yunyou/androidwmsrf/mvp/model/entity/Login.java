package com.yunyou.androidwmsrf.mvp.model.entity;

/**
 * @author WMJ
 * @version 2019/07/01
 */
public class Login {
    private String userId;
    private String institutionCode;
    private String institutionName;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getInstitutionCode() {
        return institutionCode;
    }

    public void setInstitutionCode(String institutionCode) {
        this.institutionCode = institutionCode;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }
}