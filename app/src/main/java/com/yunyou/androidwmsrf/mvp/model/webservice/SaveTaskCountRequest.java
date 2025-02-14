package com.yunyou.androidwmsrf.mvp.model.webservice;

/**
 * @author WMJ
 * @version 2019/07/04
 */
public class SaveTaskCountRequest {
    private String id;
    private String countId;
    private String countNo;
    private Double qtyCountEa;
    private Double qtyDiff;
    private String isSerial;

    public SaveTaskCountRequest(String id, String countId, String countNo, Double qtyCountEa, Double qtyDiff, String isSerial) {
        this.id = id;
        this.countId = countId;
        this.countNo = countNo;
        this.qtyCountEa = qtyCountEa;
        this.qtyDiff = qtyDiff;
        this.isSerial = isSerial;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCountId() {
        return countId;
    }

    public void setCountId(String countId) {
        this.countId = countId;
    }

    public String getCountNo() {
        return countNo;
    }

    public void setCountNo(String countNo) {
        this.countNo = countNo;
    }

    public Double getQtyCountEa() {
        return qtyCountEa;
    }

    public void setQtyCountEa(Double qtyCountEa) {
        this.qtyCountEa = qtyCountEa;
    }

    public Double getQtyDiff() {
        return qtyDiff;
    }

    public void setQtyDiff(Double qtyDiff) {
        this.qtyDiff = qtyDiff;
    }

    public String getIsSerial() {
        return isSerial;
    }

    public void setIsSerial(String isSerial) {
        this.isSerial = isSerial;
    }
}