package com.yunyou.androidwmsrf.mvp.model.webservice;

/**
 * @author WMJ
 * @version 2019/07/04
 */
public class QueryOwnerRequest {
    private String ownerCode;

    public QueryOwnerRequest(String ownerCode) {
        this.ownerCode = ownerCode;
    }

    public String getOwnerCode() {
        return ownerCode;
    }

    public void setOwnerCode(String ownerCode) {
        this.ownerCode = ownerCode;
    }
}