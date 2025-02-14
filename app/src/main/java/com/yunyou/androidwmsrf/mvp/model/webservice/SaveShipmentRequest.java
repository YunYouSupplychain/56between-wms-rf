package com.yunyou.androidwmsrf.mvp.model.webservice;

/**
 * @author WMJ
 * @version 2019/07/03
 */
public class SaveShipmentRequest {
    private String toId;
    private String soNo;
    private String trackingNo;

    public SaveShipmentRequest(String toId, String soNo, String trackingNo) {
        this.toId = toId;
        this.soNo = soNo;
        this.trackingNo = trackingNo;
    }

    public String getToId() {
        return toId;
    }

    public void setToId(String toId) {
        this.toId = toId;
    }

    public String getSoNo() {
        return soNo;
    }

    public void setSoNo(String soNo) {
        this.soNo = soNo;
    }

    public String getTrackingNo() {
        return trackingNo;
    }

    public void setTrackingNo(String trackingNo) {
        this.trackingNo = trackingNo;
    }
}