package com.yunyou.androidwmsrf.mvp.model.webservice;

/**
 * @author WMJ
 * @version 2019/07/11
 */
public class QuerySkuInvRequest {
    private String skuCode;
    private String traceId;
    private String locationCode;
    private String lotNum;

    public QuerySkuInvRequest(String skuCode, String traceId, String locationCode, String lotNum) {
        this.skuCode = skuCode;
        this.traceId = traceId;
        this.locationCode = locationCode;
        this.lotNum = lotNum;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    public String getLotNum() {
        return lotNum;
    }

    public void setLotNum(String lotNum) {
        this.lotNum = lotNum;
    }
}