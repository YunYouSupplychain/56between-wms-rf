package com.yunyou.androidwmsrf.mvp.model.webservice;

import com.yunyou.androidwmsrf.mvp.model.entity.PickDetailInfo;

/**
 * @author WMJ
 * @version 2019/07/02
 */
public class QueryPickDetailResponse extends BaseResponse {
    private PickDetailInfo body;

    public PickDetailInfo getBody() {
        return body;
    }

    public void setBody(PickDetailInfo body) {
        this.body = body;
    }
}