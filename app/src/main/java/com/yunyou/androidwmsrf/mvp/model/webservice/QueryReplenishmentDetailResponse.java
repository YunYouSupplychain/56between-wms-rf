package com.yunyou.androidwmsrf.mvp.model.webservice;

import com.yunyou.androidwmsrf.mvp.model.entity.ReplenishmentDetailInfo;

/**
 * @author WMJ
 * @version 2019/07/04
 */
public class QueryReplenishmentDetailResponse extends BaseResponse {
    private ReplenishmentDetailInfo body;

    public ReplenishmentDetailInfo getBody() {
        return body;
    }

    public void setBody(ReplenishmentDetailInfo body) {
        this.body = body;
    }
}