package com.yunyou.androidwmsrf.mvp.model.webservice;

/**
 * @author WMJ
 * @version 2019/06/29
 */
public class QueryAsnDetailByTraceIdOrSkuRequest {
    private String asnNo;
    private String funType;
    private String planId;
    private String skuCode;

    public QueryAsnDetailByTraceIdOrSkuRequest(String asnNo, String funType, String planId, String skuCode) {
        this.asnNo = asnNo;
        this.funType = funType;
        this.planId = planId;
        this.skuCode = skuCode;
    }

    public String getAsnNo() {
        return asnNo;
    }

    public void setAsnNo(String asnNo) {
        this.asnNo = asnNo;
    }

    public String getFunType() {
        return funType;
    }

    public void setFunType(String funType) {
        this.funType = funType;
    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }
}