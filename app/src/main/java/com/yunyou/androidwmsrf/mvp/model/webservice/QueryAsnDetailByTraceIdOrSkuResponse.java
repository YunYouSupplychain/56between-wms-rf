package com.yunyou.androidwmsrf.mvp.model.webservice;

import com.yunyou.androidwmsrf.mvp.model.entity.AsnDetailInfo;

import java.io.Serializable;

/**
 * @author WMJ
 * @version 2019/06/29
 */
public class QueryAsnDetailByTraceIdOrSkuResponse extends BaseResponse implements Serializable {
    private AsnDetailInfo body;

    public AsnDetailInfo getBody() {
        return body;
    }

    public void setBody(AsnDetailInfo body) {
        this.body = body;
    }
}