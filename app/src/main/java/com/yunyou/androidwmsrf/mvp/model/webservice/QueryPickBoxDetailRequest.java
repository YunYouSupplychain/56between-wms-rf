package com.yunyou.androidwmsrf.mvp.model.webservice;

/**
 * @author WMJ
 * @version 2019/07/17
 */
public class QueryPickBoxDetailRequest {
    private String soNo;
    private String toId;

    public QueryPickBoxDetailRequest(String soNo, String toId) {
        this.soNo = soNo;
        this.toId = toId;
    }

    public String getSoNo() {
        return soNo;
    }

    public void setSoNo(String soNo) {
        this.soNo = soNo;
    }

    public String getToId() {
        return toId;
    }

    public void setToId(String toId) {
        this.toId = toId;
    }
}