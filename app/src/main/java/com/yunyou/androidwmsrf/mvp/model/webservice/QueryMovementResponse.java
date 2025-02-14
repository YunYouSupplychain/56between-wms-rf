package com.yunyou.androidwmsrf.mvp.model.webservice;

import com.yunyou.androidwmsrf.mvp.model.entity.SkuInvDetailInfo;

public class QueryMovementResponse extends BaseResponse {
    private SkuInvDetailInfo body;

    public SkuInvDetailInfo getBody() {
        return body;
    }

    public void setBody(SkuInvDetailInfo body) {
        this.body = body;
    }
}