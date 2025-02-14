package com.yunyou.androidwmsrf.mvp.model.webservice;

import com.yunyou.androidwmsrf.mvp.model.entity.Version;

/**
 * @author WMJ
 * @version 2019/07/01
 */
public class GetAppVersionResponse extends BaseResponse {
    private Version body;

    public Version getBody() {
        return body;
    }

    public void setBody(Version body) {
        this.body = body;
    }
}