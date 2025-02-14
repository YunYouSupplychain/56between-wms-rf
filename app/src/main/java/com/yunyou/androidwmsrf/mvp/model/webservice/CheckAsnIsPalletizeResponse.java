package com.yunyou.androidwmsrf.mvp.model.webservice;

import java.io.Serializable;

/**
 * @author WMJ
 * @version 2019/06/29
 */
public class CheckAsnIsPalletizeResponse extends BaseResponse {
    private Body body;

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public static class Body implements Serializable {

    }
}