package com.yunyou.androidwmsrf.mvp.model.webservice;

import com.yunyou.androidwmsrf.mvp.model.entity.PickBoxHeaderInfo;

/**
 * @author WMJ
 * @version 2019/07/17
 */
public class QueryPickBoxHeaderResponse extends BaseResponse {
    private PickBoxHeaderInfo body;

    public PickBoxHeaderInfo getBody() {
        return body;
    }

    public void setBody(PickBoxHeaderInfo body) {
        this.body = body;
    }
}