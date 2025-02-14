package com.yunyou.androidwmsrf.mvp.model.webservice;

/**
 * @author WMJ
 * @version 2019/07/02
 */
public class QueryPutAwayTaskByTraceIdRequest {
    private String toId;

    public QueryPutAwayTaskByTraceIdRequest(String toId) {
        this.toId = toId;
    }

    public String getToId() {
        return toId;
    }

    public void setToId(String toId) {
        this.toId = toId;
    }
}