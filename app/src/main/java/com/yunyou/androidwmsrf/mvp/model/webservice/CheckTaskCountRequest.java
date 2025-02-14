package com.yunyou.androidwmsrf.mvp.model.webservice;

/**
 * @author WMJ
 * @version 2019/07/04
 */
public class CheckTaskCountRequest {
    private String countNo;

    public CheckTaskCountRequest(String countNo) {
        this.countNo = countNo;
    }

    public String getCountNo() {
        return countNo;
    }

    public void setCountNo(String countNo) {
        this.countNo = countNo;
    }
}