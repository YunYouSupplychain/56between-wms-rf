package com.yunyou.androidwmsrf.mvp.model.entity;

import java.io.Serializable;

public class BaseResponse<T> implements Serializable {
    private T data;
    private boolean success;
    private String msg;
    private int errorCode;

    public T getData() {
        return data;
    }

    public String getMsg() {
        return msg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public boolean isSuccess() {
        return success;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "data=" + data +
                ", success=" + success +
                ", msg='" + msg + '\'' +
                '}';
    }
}