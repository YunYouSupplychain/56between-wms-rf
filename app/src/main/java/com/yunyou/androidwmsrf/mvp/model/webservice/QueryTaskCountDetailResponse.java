package com.yunyou.androidwmsrf.mvp.model.webservice;

import com.yunyou.androidwmsrf.mvp.model.entity.TaskCountDetailInfo;

/**
 * @author WMJ
 * @version 2019/07/04
 */
public class QueryTaskCountDetailResponse extends BaseResponse {
    private TaskCountDetailInfo body;

    public TaskCountDetailInfo getBody() {
        return body;
    }

    public void setBody(TaskCountDetailInfo body) {
        this.body = body;
    }
}