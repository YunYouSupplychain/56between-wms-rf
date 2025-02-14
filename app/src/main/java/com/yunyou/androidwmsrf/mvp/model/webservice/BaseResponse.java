package com.yunyou.androidwmsrf.mvp.model.webservice;

import java.io.Serializable;

/**
 * @author WMJ
 * @version 2019/07/01
 */
public class BaseResponse implements Serializable {
    private boolean success;
    private String errorCode;
    private String msg;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}