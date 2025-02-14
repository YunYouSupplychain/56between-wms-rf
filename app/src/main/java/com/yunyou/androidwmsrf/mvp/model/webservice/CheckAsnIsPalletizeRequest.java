package com.yunyou.androidwmsrf.mvp.model.webservice;

/**
 * @author WMJ
 * @version 2019/06/29
 */
public class CheckAsnIsPalletizeRequest {
    private String asnNo;
    private String funType;

    public CheckAsnIsPalletizeRequest(String asnNo, String funType) {
        this.asnNo = asnNo;
        this.funType = funType;
    }

    public String getAsnNo() {
        return asnNo;
    }

    public void setAsnNo(String asnNo) {
        this.asnNo = asnNo;
    }

    public String getFunType() {
        return funType;
    }

    public void setFunType(String funType) {
        this.funType = funType;
    }
}