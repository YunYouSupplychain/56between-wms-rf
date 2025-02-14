package com.yunyou.androidwmsrf.mvp.model.webservice;

import com.yunyou.androidwmsrf.mvp.model.entity.OwnerInfo;

/**
 * @author WMJ
 * @version 2019/07/04
 */
public class QueryOwnerResponse extends BaseResponse {
    private OwnerInfo body;

    public OwnerInfo getBody() {
        return body;
    }

    public void setBody(OwnerInfo body) {
        this.body = body;
    }
}