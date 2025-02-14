package com.yunyou.androidwmsrf.mvp.model.webservice;

/**
 * @author WMJ
 * @version 2019/07/04
 */
public class QueryTaskCountDetailRequest {
    private String countNo;
    private String zoneCode;
    private String lane;
    private String locCode;

    public QueryTaskCountDetailRequest(String countNo, String zoneCode, String lane, String locCode) {
        this.countNo = countNo;
        this.zoneCode = zoneCode;
        this.lane = lane;
        this.locCode = locCode;
    }

    public String getCountNo() {
        return countNo;
    }

    public void setCountNo(String countNo) {
        this.countNo = countNo;
    }

    public String getZoneCode() {
        return zoneCode;
    }

    public void setZoneCode(String zoneCode) {
        this.zoneCode = zoneCode;
    }

    public String getLane() {
        return lane;
    }

    public void setLane(String lane) {
        this.lane = lane;
    }

    public String getLocCode() {
        return locCode;
    }

    public void setLocCode(String locCode) {
        this.locCode = locCode;
    }
}