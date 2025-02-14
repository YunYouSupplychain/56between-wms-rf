package com.yunyou.androidwmsrf.mvp.model.webservice;

/**
 * @author WMJ
 * @version 2019/07/02
 */
public class QueryPutAwayTaskByTaskNoRequest {
    private String paId;
    private String orderNo;
    private String zoneCode;

    public QueryPutAwayTaskByTaskNoRequest(String paId, String orderNo, String zoneCode) {
        this.paId = paId;
        this.orderNo = orderNo;
        this.zoneCode = zoneCode;
    }

    public String getPaId() {
        return paId;
    }

    public void setPaId(String paId) {
        this.paId = paId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getZoneCode() {
        return zoneCode;
    }

    public void setZoneCode(String zoneCode) {
        this.zoneCode = zoneCode;
    }
}