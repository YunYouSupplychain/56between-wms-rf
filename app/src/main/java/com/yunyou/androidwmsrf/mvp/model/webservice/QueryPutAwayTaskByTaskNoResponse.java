package com.yunyou.androidwmsrf.mvp.model.webservice;

import com.yunyou.androidwmsrf.mvp.model.entity.PaTaskInfo;

/**
 * @author WMJ
 * @version 2019/07/02
 */
public class QueryPutAwayTaskByTaskNoResponse extends BaseResponse {
    private PaTaskInfo body;

    public PaTaskInfo getBody() {
        return body;
    }

    public void setBody(PaTaskInfo body) {
        this.body = body;
    }
}