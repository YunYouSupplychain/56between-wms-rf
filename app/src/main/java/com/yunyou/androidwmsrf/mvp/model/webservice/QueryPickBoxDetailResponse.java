package com.yunyou.androidwmsrf.mvp.model.webservice;

import com.yunyou.androidwmsrf.mvp.model.entity.PickBoxDetailInfo;

/**
 * @author WMJ
 * @version 2019/07/17
 */
public class QueryPickBoxDetailResponse extends BaseResponse {
    private PickBoxDetailInfo body;

    public PickBoxDetailInfo getBody() {
        return body;
    }

    public void setBody(PickBoxDetailInfo body) {
        this.body = body;
    }
}