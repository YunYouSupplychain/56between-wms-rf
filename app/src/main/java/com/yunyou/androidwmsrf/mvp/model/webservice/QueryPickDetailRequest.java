package com.yunyou.androidwmsrf.mvp.model.webservice;

/**
 * @author WMJ
 * @version 2019/07/02
 */
public class QueryPickDetailRequest {
    private String soNo;
    private String pickNo;
    private String allocId;
    private String waveNo;
    private String currentIndex;

    public QueryPickDetailRequest(String soNo, String pickNo, String allocId, String currentIndex, String waveNo) {
        this.soNo = soNo;
        this.pickNo = pickNo;
        this.allocId = allocId;
        this.waveNo = waveNo;
        this.currentIndex = currentIndex;
    }

    public String getSoNo() {
        return soNo;
    }

    public void setSoNo(String soNo) {
        this.soNo = soNo;
    }

    public String getPickNo() {
        return pickNo;
    }

    public void setPickNo(String pickNo) {
        this.pickNo = pickNo;
    }

    public String getAllocId() {
        return allocId;
    }

    public void setAllocId(String allocId) {
        this.allocId = allocId;
    }

    public String getWaveNo() {
        return waveNo;
    }

    public void setWaveNo(String waveNo) {
        this.waveNo = waveNo;
    }

    public String getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(String currentIndex) {
        this.currentIndex = currentIndex;
    }
}