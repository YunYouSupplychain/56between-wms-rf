package com.yunyou.androidwmsrf.mvp.model.webservice;

import com.yunyou.androidwmsrf.mvp.model.entity.SkuInvDetailInfo;

/**
 * @author WMJ
 * @version 2019/07/11
 */
public class QuerySkuInvResponse extends BaseResponse {
    private SkuInvDetailInfo body;

    public SkuInvDetailInfo getBody() {
        return body;
    }

    public void setBody(SkuInvDetailInfo body) {
        this.body = body;
    }
}